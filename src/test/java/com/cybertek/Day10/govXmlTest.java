package com.cybertek.Day10;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class govXmlTest {


    public static void setUp() {

        baseURI = "https://data.ct.gov/api/views/qm34-pq7e/rows.xml";


    }
    @Test
    public void test1(){
        //send a get request to
        //https://data.ct.gov/api/views/qm34-pq7e/rows.xml
        //get all the years
        //get all unknowns
        //get 2006 other
        //get 2007 _address

        Response response= given()
                .when().get("https://data.ct.gov/api/views/qm34-pq7e/rows.xml")
                .then().statusCode(200)
                .and().extract().response();

        XmlPath xmlPath = response.xmlPath();
        List<Integer> allYear= xmlPath.getList("response.row.row.year");
        System.out.println("allYears = " + allYear);
        List<Integer> allUnKnows= xmlPath.getList("response.row.row.unknown");
        System.out.println("allUnKnows = " + allUnKnows);
        int other=xmlPath.getInt("response.row.row[2].other");
        System.out.println("other = " + other);
        String _address=xmlPath.getString("response.row.row[3].@_address");
        System.out.println("_address = " + _address);

    }
}
