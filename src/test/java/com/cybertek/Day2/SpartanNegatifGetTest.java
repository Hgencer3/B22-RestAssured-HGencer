package com.cybertek.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegatifGetTest {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name

    @BeforeAll
    public static void init() {
        baseURI="http://3.87.209.124:8000";

    }
    /*TASK
    Given Accept type application/xml
    When user send GET request to api/spartans/10 end point
    Then status code must be 406
    And response Content Type must be application/xml
    */

    @DisplayName("GET request to api/spartans/10")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.XML)
                .when().
         get("/api/spartans/10");


        assertEquals(406, response.statusCode());
        assertEquals(response.contentType(), "application/xml;charset=UTF-8");

    }

}
