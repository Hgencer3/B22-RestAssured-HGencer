package com.cybertek.Day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.everyItem;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class CsvFileSourceParameterisedTest {

    @ParameterizedTest
    @CsvFileSource(resources ="/postalcode.csv" ,numLinesToSkip = 1)
    public void zippopotamParameterized(String state,String city, int zipCount){

        System.out.println("state = " + state);
        System.out.println("city = " + city);
        System.out.println("zipCode = " + zipCount);

       given()
                .baseUri("https://api.zippopotam.us")
                .accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)

                .when()
                .get("/us/{state}/{city}")
                .then()
                .statusCode(200)
               //
               .body("places",hasSize(zipCount));



    }
}
