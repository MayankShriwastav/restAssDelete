package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException{
		System.out.println("hooks is calling.........");
		PlaceValidationStep pv = new PlaceValidationStep();
		if(PlaceValidationStep.placeId==null){
			pv.add_place_payload_with("srivastava", "Russian", "Golden road");
			pv.user_calls_with_http_request("AddPlaceApi", "POST");
			pv.verify_place_id_in_created_map_to_using("srivastava", "getPlaceApi");
		}
		
	}

}
