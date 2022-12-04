package soap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import groovyjarjarantlr4.v4.runtime.tree.xpath.XPath;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import reusable.Reusable;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import static io.restassured.path.xml.config.XmlPathConfig.xmlPathConfig;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.xml.HasXPath.hasXPath;

import java.rmi.server.RemoteObject;

import reusable.Reusable;


public class SoapTestScenario1 {
	
	public static Reusable reObj;
	/*
	@BeforeClass
	public void TestDataSetup()
	{
		reObj=new Reusable();
		
	}

	@Test
	public void CelsiusToFahrenheitResponse()
	{
		
		String testData=reObj.readPropertiesFile("CelsiusToFahrenheitResponse_testData");
		if(testData.contains(","))
		{
			String[] splitData=testData.split(",");
			for (int a=0;a<splitData.length;a++)
			{
				String[] againsplitDatacolon=splitData[0].split(":");
				CelsiusToFahrenheitResponse_Implemantation(againsplitDatacolon[0], againsplitDatacolon[1]);
			}
		}
		else
		{
			String[] splitData=testData.split(":");
			CelsiusToFahrenheitResponse_Implemantation(splitData[0], splitData[1]);
		}
		
	}
	
	public void CelsiusToFahrenheitResponse_Implemantation(String Celsius, String Fahrenheit)
	{
		Response res=given()
				.contentType(ContentType.XML)
				.header("Content-Type","application/soap+xml;charset=utf-8")
				.body("<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\r\n"
				+ "  <soap12:Body>\r\n"
				+ "    <CelsiusToFahrenheit xmlns=\"https://www.w3schools.com/xml/\">\r\n"
				+ "      <Celsius>"+Celsius+"</Celsius>\r\n"
				+ "    </CelsiusToFahrenheit>\r\n"
				+ "  </soap12:Body>\r\n"
				+ "</soap12:Envelope>")
				.when()
				.post("https://www.w3schools.com/xml/tempconvert.asmx\n");
		System.out.println("The status code is " +res.getStatusCode());
		System.out.println("The Response body is " +res.getBody().asString());
		
	      XmlPath xml_path_obj = new XmlPath(res.getBody().asString()).using(xmlPathConfig().namespaceAware(false));
	      String CelsiusToFahrenheitResult = xml_path_obj.getString("soap:Envelope.soap:Body.CelsiusToFahrenheitResponse.CelsiusToFahrenheitResult");
	      System.out.println("The CelsiusToFahrenheitResult is==>"+CelsiusToFahrenheitResult);
	      Assert.assertEquals(Fahrenheit, CelsiusToFahrenheitResult);
	}
	
	@Test
	public void DoGetSoapCall()
	{
		//Positive Test Case
		get("https://chercher.tech/sample/api/books.xml")
		.then()
		.assertThat()
		.body("bookstore.book[0].title",equalTo("The Nightingale"))
		.body("bookstore.book[0].price.hardcover",equalTo("570"))
		.body("bookstore.book[1].price",equalTo("29.99"))
		//.body("bookstore.book.price.title",containsString("Harry Potter"));
		.body("bookstore.book[0].@category",equalTo("cooking"))
		.body("bookstore.book[0].title.@lang",equalTo("en"))
		.body("bookstore.book[1].@category",equalTo("children"));


		//Negative Test Case
		//get("https://chercher.tech/sample/api/books.xml").then().assertThat().body("bookstore.book[0].title",equalTo("The Nightingale1"));
	
	
	}
	
	//Above method can be written in this way also
	@Test
	public void DoGetSoapCall1()
	{
		//Positive Test Case
		get("https://chercher.tech/sample/api/books.xml")
        .then().assertThat()
        .body("bookstore.book[0].title",equalTo( "The Nightingale")
        ,"bookstore.book[0].price.hardcover",equalTo("570")  
        ,"bookstore.book[1].price",equalTo("29.99")
        //.body("bookstore.book.title",containsString("Harry Potter"))
        ,"bookstore.book[0].@category",equalTo("cooking")
        ,"bookstore.book[0].title.@lang",equalTo("en") 
        ,"bookstore.book[1].@category",equalTo("children"));	
	
	}
	
	@Test
	//Using Xpath
	public void DoGetSoapCall2()
	{
		  get("https://chercher.tech/sample/api/books.xml")
          .then().assertThat()
          .body(hasXPath("/bookstore/book[1]/title",containsString("The Nightingale")));

	
	}*/
	
	@Test
	public void ValidateWithContains_SOAP()
	{
		Response response=get("https://chercher.tech/sample/api/books.xml");
		String res=response.getBody().asString();
		
		XmlPath xml_path_obj=new XmlPath(res).using(xmlPathConfig().namespaceAware(false));
		
		//first get the count of node you want to test
		int xmlpathcount=xml_path_obj.get("bookstore.book.title.size()");
		System.out.println("Count is--> " +xmlpathcount);
		
		for (int a=0;a<xmlpathcount;a++)
		{
			String title=xml_path_obj.getString("bookstore.book["+a+"].title");
			if(title.equals("Happry Potter"))
			{
				System.out.println("Yes,The Expected Text is available "+" Harry Potter");
				break;
			}
			else
			{
				System.out.println("No,The Expected Text isnot available "+" Harry Potter");
				break;
			}
						
		}
		
		//Below if code is not good way of writing code
		/*if(res.contains("Harry Potter"))
		{
			System.out.println("Yes,The Expected Text is available"+"Harry Potter");
		}
		else
		{
			System.out.println("No,The Expected Text is available"+"Harry Potter");
		}*/
	}
	
	
}
