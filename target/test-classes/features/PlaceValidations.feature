Feature: Valiation place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being succesfully added using AddPlaceAPI
Given Add place payload with "<Name>" "<Language>" "<Address>"
When user calls "AddPlaceApi" with "Post" http request
Then API call got success with status Code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id in created map to "<Name>" using "getPlaceApi"


Examples:
	|    Name	|   Language	|       Address			|
	|    Shini	|   English		|  world trade Center	|
#	|    myke	|   Spanish		|  North America		|


@DeletePlace @Regression
Scenario: verify delete place API is working

Given delete place payload
When user calls "deletePlaceApi" with "Post" http request
Then API call got success with status Code 200
And "status" in response body is "OK"