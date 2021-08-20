package com.cybertek.Day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequest {

    //BeforeAll is a annotation equals to @BeforeClass in testNg, we use with static method name

    @BeforeAll
    public static void init() {
        RestAssured.baseURI="http://3.87.209.124:1000/ords/hr/";

    }

    @DisplayName("Get request to /regions")
    @Test
    public void test1(){

        Response response = get("/regions");

        //print status code
        System.out.println(response.statusCode());

    }
    /*
        Given accept type is json
        When user sends get request to regions/2
        Then response status code must be 200
        and body is json format
        and response body contains Americas
     */

    @DisplayName("get request to regions/2")
    @Test
    public void test2(){

        Response response = get("regions/2");

        //print status code
        System.out.println(response.statusCode());

        assertEquals(200,response.statusCode());
        assertEquals(response.contentType(), "application/json");
        assertTrue(response.body().asString().contains("Americas"));


    }





}
