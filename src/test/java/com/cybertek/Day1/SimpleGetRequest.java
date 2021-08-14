package com.cybertek.Day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url="http://3.87.209.124:8000/api/spartans";

    @Test
    public void test1(){

        Response response = RestAssured.get(url);

        //print response status code
        System.out.println("response.statusCode() = " + response.statusCode());

        //print response body
        System.out.println("response.body = " + response.prettyPrint());


    }



}
