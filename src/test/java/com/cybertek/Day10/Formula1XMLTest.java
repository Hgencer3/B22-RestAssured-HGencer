package com.cybertek.Day10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class Formula1XMLTest {

    @BeforeAll
    public static void setUp(){

        baseURI = "http://ergast.com";
        basePath="/api/f1";


    }
    @Test
    public void test1(){

     Response response= given().pathParam("driver","alonso")
                .when().get("drivers/{driver}")
                .then().statusCode(200).log().all()
                .and().extract().response();



        XmlPath xmlPath = response.xmlPath();
        String givenName= xmlPath.getString("MRData.DriverTable.Driver.GivenName");
        System.out.println("givenName = " + givenName);
        String familyName= xmlPath.getString("MRData.DriverTable.Driver.FamilyName");
        String driverId= xmlPath.getString("MRData.DriverTable.Driver.@driverId");
        System.out.println("driverId = " + driverId);
        String code= xmlPath.getString("MRData.DriverTable.Driver.@code");
        System.out.println("code = " + code);
        String url= xmlPath.getString("MRData.DriverTable.Driver.@url");
        System.out.println("url = " + url);

        assertThat(givenName,is("Fernando"));
        assertThat(familyName,is("Alonso"));
        assertThat(driverId,is("alonso"));
        assertThat(code,is("ALO"));
        assertThat(url,is("http://en.wikipedia.org/wiki/Fernando_Alonso"));
    }
}
