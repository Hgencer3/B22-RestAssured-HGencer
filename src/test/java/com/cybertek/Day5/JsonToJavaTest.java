package com.cybertek.Day5;

import com.cybertek.utilies.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class JsonToJavaTest extends SpartanTestBase {

    @DisplayName("Get one Spartan and desariazilatin")
    @Test
    public void oneSpartanToMap(){

        Response response=given().pathParam("id",15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        Map<String,Object> jsonMap=response.as(Map.class);

        System.out.println(jsonMap.toString());

        //after we got the map, we can use hamcrest or junit assertions to do assertion
        String actualName = (String) jsonMap.get("name");
        assertThat(actualName,is("Meta"));

    }
    @DisplayName("GET all spartans to JAVA data structure")
    @Test
    public void getAllSpartan(){

        Response response=get("/api/spartans/")
                .then().statusCode(200).extract().response();

        List<Map<String,Object>> jsonList= response.as(List.class);

        for (Map<String, Object> map:jsonList){
            System.out.println(map.toString());
        }

    }


}
