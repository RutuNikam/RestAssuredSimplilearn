package soap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import reusable.Reusable;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;
import static io.restassured.path.xml.config.XmlPathConfig.xmlPathConfig;

import java.rmi.server.RemoteObject;

import reusable.Reusable;


public class SoapTestScenario1 {
	
	public static Reusable reObj;
	
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
}
