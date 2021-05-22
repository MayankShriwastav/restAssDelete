package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import static org.testng.Assert.*;


//import static org.junit.Assert.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlacePayload;
import pojo.Location;
import resources.APIResource;
import resources.TestDataBuild;
import resources.Utils;



public class PlaceValidationStep extends Utils{
	
	RequestSpecification request;
	ResponseSpecification respo;
	Response response;	
	static String placeId;
	TestDataBuild data = new TestDataBuild();
	

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		request = given().spec(requestSpecification()).body(data.addPlacePayLoad(name,language,address));
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		
		//constructor will called with value of resource which you pass
		APIResource resourceApi = APIResource.valueOf(resource);		
		System.out.println("resource Name : "+resourceApi.getResource());
			     
		if(method.equalsIgnoreCase("POST")){
			response = request.when().post(resourceApi.getResource());
		}
		else if(method.equalsIgnoreCase("GET")){
			response = request.when().get(resourceApi.getResource());
		}else if(method.equalsIgnoreCase("DELETE")){
			response = request.when().delete(resourceApi.getResource());
		}else{
			System.out.println("no http method is calling...");
		}
          
	}
	
	@Then("API call got success with status Code {int}")
	public void api_call_got_success_with_status_Code(int expectedStatusCode) {
	Assert.assertEquals(response.getStatusCode(),expectedStatusCode);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String Expectedvalue) {		
	 String actualValue = getJsonPath(response,key);
	 assertEquals( actualValue , Expectedvalue);
	}

	@Then("verify place_id in created map to {string} using {string}")
	public void verify_place_id_in_created_map_to_using(String expectedName, String resource) throws IOException {
	   placeId = getJsonPath(response,"place_id");
		request = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_http_request(resource,"GET");
		 String actualValue = getJsonPath(response,"name");
		 assertEquals( actualValue , expectedName);
	}
		
	@Given("delete place payload")
	public void delete_place_payload() throws IOException {
		request =  given().spec(requestSpecification()).body(data.deletePlacePayLoad(placeId));
	}
	
	
}
