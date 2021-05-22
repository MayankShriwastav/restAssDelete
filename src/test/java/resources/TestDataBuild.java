package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlacePayload;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlacePayload addPlacePayLoad(String name, String language, String address){
		
		AddPlacePayload pl = new AddPlacePayload();
		pl.setAccuracy(50);
		pl.setName(name);
		pl.setAddress(address);
		pl.setWebsite("http://google.com");
		pl.setLanguage(language);
		pl.setPhone_number("859639999");
		
		List<String> mylist = new  ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		pl.setTypes(mylist);
		
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		pl.setLocation(location);
		
		return pl;		
	}
	
	public String deletePlacePayLoad(String placeId){
		return "{\r\n" + 
				"  \"place_id\":\""+placeId+"\"\r\n" + 
				"}";
	}

}
