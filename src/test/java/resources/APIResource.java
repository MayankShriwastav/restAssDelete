package resources;

//enum is special class in java which has collection of constants and method.

public enum APIResource {

	AddPlaceApi("/maps/api/place/add/json") ,
	getPlaceApi("/maps/api/place/get/json"),
	deletePlaceApi("/maps/api/place/delete/json");
	private String resource;
	
	APIResource(String resource){
		this.resource=resource;
	}
	
	public String getResource(){
		return resource;
	}
	
	
}
