package com.cybertek.Day11;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class ParameterizedTestJunit5 {


    @ParameterizedTest
    @ValueSource(ints = {1,3,5,6,7,9,10,5,21,32})
    public void testMultipleNumbers(int number){

        System.out.println("number = " + number);
        Assertions.assertTrue(number > 5);

    }

    @ParameterizedTest
    @ValueSource(strings = {"john","abbas","ali","TJ"})
    public void testMultipleNames(String name){
        System.out.println("name = " + name);

    }


    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200


    @Test
    public void zippopotam(){
        given().contentType(ContentType.JSON)
                .when().pathParam("zipcode", 22030)
                .and().get("https://api.zippopotam.us/us/{zipcode}")
                .then().statusCode(200);

    }
    @ParameterizedTest
    @ValueSource(ints = {22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void zippopotamParameterized(int zipCode){

        given().contentType(ContentType.JSON)
                .pathParam("zipcode", zipCode)
        .when()
                .get("https://api.zippopotam.us/us/{zipcode}")
        .then()
                .statusCode(200);

    }

}
