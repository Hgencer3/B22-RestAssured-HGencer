package com.cybertek.Day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class SpartanTestWithPath {
    @BeforeAll
    public static void init() {
        baseURI = "http://3.87.209.124:8000";
    }

    /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936

     */

    @DisplayName("GET one spartan with Path Method5")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id",10)
                .when().
                        get("/api/spartans/{id}");


        assertEquals(200, response.statusCode());
        assertEquals( "application/json", response.contentType());
        response.prettyPrint();

//        assertTrue(response.body().asString().contains("Lorenza"));
//        assertTrue(response.body().asString().contains("10"));
//        assertTrue(response.body().asString().contains("Female"));
//        assertTrue(response.body().asString().contains("3312820936"));

        System.out.println("response.path(\"id\").toString() = " + response.path("id").toString());
        System.out.println("response.path(\"name\").toString() = " + response.path("name").toString());
        System.out.println("response.path(\"gender\").toString() = " + response.path("gender").toString());
        System.out.println("response.path(\"phone\").toString() = " + response.path("phone").toString());

       int id=response.path("id");
       String name=response.path("name");
       String gender=response.path("gender");
       long phone=response.path("phone");

        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936l, phone);

    }
    @DisplayName("GET all spartan and navigate with Path Method5")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON)
              //  .and().pathParam("id",10)
                .when().
                        get("/api/spartans/");
        int firstId=response.path("id[0]");
        System.out.println(firstId);

        String name=response.path("name[0]");
        System.out.println(name);

        String LastFisrtName=response.path("name[-1]");
        System.out.println(LastFisrtName);


        List<String> names=response.path("name");
        System.out.println(names);
        for (String each:names){
            System.out.println(each);
        }

    }}
