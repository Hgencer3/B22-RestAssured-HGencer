package com.cybertek.Day3;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithParam {
    @BeforeAll
    public static void init() {
        baseURI = "http://3.87.209.124:1000/ords/hr";

 /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */
    }

    @DisplayName("GET request to /countries with q = {\"region_id\":2} ")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":2}")
                .log().all()
                .when().
                        get("/countries");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        //verify Blythe in the json payload/body
        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();

    }

    /*
       Send a GET request to employees and get only employees who works as a IT_PROG

    */
    @DisplayName("GET request to /employees with query param ")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"Job_id\":\"IT_PROG\"}")
                .log().all()
                .when().
                        get("/employees");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));

        response.prettyPrint();
    }
}