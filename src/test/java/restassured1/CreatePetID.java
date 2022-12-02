package restassured1;

import org.junit.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import reusable.Reusable;
import jsonbody.TestJsonBody;

public class CreatePetID extends Reusable{
	
	public static TestJsonBody postbody;
	

	public static void main(String[] args) throws Exception{
	
		postbody=new TestJsonBody();
		for (int i=1;i<=10;i++)
		{
			user_post_call_pet(i);
		}
		

	}
	public static void user_post_call_pet(int pet_id)
	{
		RestAssured.baseURI=readPropertiesFile("recresURI");
		Response res=RestAssured.given().contentType(ContentType.JSON).body(postbody.createPetBody(pet_id)).post();
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
