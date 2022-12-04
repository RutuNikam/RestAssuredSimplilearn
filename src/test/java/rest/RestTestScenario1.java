package rest;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static org.hamcrest.CoreMatchers.*;

import org.apache.commons.lang3.RandomStringUtils;

import static io.restassured.RestAssured.*;
import static io.restassured.path.xml.config.XmlPathConfig.xmlPathConfig;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.hasKey;
import org.apache.log4j.*;

public class RestTestScenario1 {
	
	static Logger log=Logger.getLogger(RestTestScenario1.class);
	
	@BeforeClass
	public void setup()
	{
		PropertyConfigurator.configure(System.getProperty("user.dir" + "/RestAssuredProject/src/main/resources/log4j.properties"));
	}
	
	@Test
	public void DoGetRestCall()
	{
		  get("https://reqres.in/api/users?page=2")
          .then().assertThat()
          .body("page",equalTo(2))
          .body("per_page",equalTo(6))
          .body("total",equalTo(12))
          .body("data.email[1]",equalTo("lindsay.ferguson@reqres.in"))
          .body("support.url",equalTo("https://reqres.in/#support-heading"))
          .body("data.id[0]",equalTo(7));
		  
   }
	@Test
	public void DoPostRestCall()
	{
		
		String email=RandomStringUtils.randomAlphabetic(10).toUpperCase()+"@test.com";
		String mobile=RandomStringUtils.randomNumeric(10).toUpperCase();
		
		log.info("The Email is: " +email);
		log.info("The Mobile no. is: " +mobile);
		
				given()
				.contentType(ContentType.JSON).accept(ContentType.JSON)
				.body("{\n"+"\"name\": \"Rutuja\",\n"
						+ "    \"job\": \"Tester\",\n"
						+ "    \"email\": \""+email+"\",\n"
						+ "    \"mobile\": \""+mobile+"\"\n"+"}")
				.when()
				.post("https://reqres.in/api/users").then().assertThat()
				.body("email",equalTo(email))
				.body("mobile",equalTo(mobile))
				.body("$", hasKey("createdAt"))
				.body("$", hasKey("id"))
				.body("id",is(notNullValue()))
				.body("createdAt",is(notNullValue()));
	}
	
	@Test
	public void ValidateWithContains_Rest()
	{
	     Response response =get("https://reqres.in/api/users?page=2");
	     String res = response.getBody().asString();
	        // First get the count of node you want to test ...
	        int jsonPathcount = response.getBody().jsonPath().getList("data.first_name").size();
	        System.out.println("Count is ==>"+jsonPathcount);
	        for(int a=0;a<jsonPathcount;a++){
	            String title = response.getBody().jsonPath().getString("data.first_name["+a+"]");
	            if(title.equals("George")){
	                System.out.println("Yes , the expected text is available==>"+"George");
	                log.info("Yes , the expected text is available==>"+"George");
	                break;   
	            }
			/*else
			{
				System.out.println("No,The Expected Text isnot available "+" George");
				break;
			}*/
						
		}
	}
	
	 @Test
	    public void authentication_REST_1_Get(){
	        Response response = given().header("Authorization" , "Bearer-4j3b43i49reyr98r832")
	                .get("https://reqres.in/api/users?page=2");
	    }
	 
	 @Test
	    public void authentication_REST_2_Post(){
	        Response response = given().auth().basic("username1","password1").auth().basic("username2","password2")
	                .post("https://reqres.in/api/users?page=2");
	    }




}
