package restassured1;

import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import reusable.Reusable;

public class GoogleTest extends Reusable{

	public static void main(String[] args) throws Exception{
		validateGoogleURL();
		validateGoogleURL1();

	}
	public static void validateGoogleURL()
	{
		RestAssured.baseURI=readPropertiesFile("baseURI");
		Response res=RestAssured.get();
		int status_code=res.getStatusCode();
		System.out.println("After hitting google URL status code is--> " +status_code);
		
	    Assert.assertEquals(Integer.parseInt(readPropertiesFile("responseStatus")) , status_code);
		
		//String resBody=res.getBody().asString();
		//System.out.println("After hitting google URL response looks like--> " +resBody);
		
	}
	
	public static void validateGoogleURL1()
	{
		RestAssured.baseURI="https://www.google.com";
		RestAssured.given().when().get().then().statusCode(200);
		//System.out.println("In validateGoogleURL1() method");
	}
}
