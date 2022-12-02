package restassured1;

import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import reusable.Reusable;

public class Recres_Post extends Reusable{
	
	public static String my_request_body="{\r\n"
			+ "    \"name\": \"Rutuja\",\r\n"
			+ "    \"job\": \"Tester\"\r\n"
			+ "}";
	

	public static void main(String[] args) throws Exception{
	
	    user_post_call();
		//validateGoogleURL1();

	}
	public static void user_post_call()
	{
		RestAssured.baseURI=readPropertiesFile("recresURI");
		Response res=RestAssured.given().contentType(ContentType.JSON).body(my_request_body).post();
		int status_code=res.getStatusCode();
		System.out.println("After hitting google URL status code is--> " +status_code);
		
	    Assert.assertEquals(Integer.parseInt(readPropertiesFile("responseStatus_201")) , status_code);
		
		String resBody=res.getBody().asString();
		System.out.println("After hitting google URL response looks like--> " +resBody);
		
	}
	
	public static void validateGoogleURL1()
	{
		RestAssured.baseURI="https://www.google.com";
		RestAssured.given().when().get().then().statusCode(200);
		//System.out.println("In validateGoogleURL1() method");
	}
}
