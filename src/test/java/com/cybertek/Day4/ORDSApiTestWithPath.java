package com.cybertek.Day4;

import com.cybertek.utilies.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSApiTestWithPath extends HRTestBase {


    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                                   .queryParam("q", "{\"region_id\":2}")
                           .when().
                                    get("/countries");


        assertEquals(200, response.statusCode());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));
        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));
        //print Contry_id
        String firstCountryId=response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);
        //print countryname
        String secondContryname=response.path("items[1].country_name");
        System.out.println("secondContryname = " + secondContryname);

        //print 3rd href link
        String thirdHref=response.path("items[2].links[0].href");
        System.out.println("thirdHref = " + thirdHref);
       
        //get me all country names
        List<String> CountryNames=response.path("items.country_name");
        System.out.println("CountryNames = " + CountryNames);

        //Assert that all region ids are equel to 2
        List<Integer> allRegionId=response.path("items.region_id");
        System.out.println("allRegionId = " + allRegionId);
        
        for (Integer regionId:allRegionId){
            assertEquals(2,regionId);
        }

    }
    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have only IT_PROG as a job_id
        List<String> allJobIDs = response.path("items.job_id");

        for (String jobID : allJobIDs) {
            System.out.println("jobID = " + jobID);
            assertEquals("IT_PROG", jobID);
        }


        //TASK
        //get ip address from configuration



    }
    }










    