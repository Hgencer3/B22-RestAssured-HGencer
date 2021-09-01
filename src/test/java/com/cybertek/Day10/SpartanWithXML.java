package com.cybertek.Day10;

import com.cybertek.utilies.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SpartanWithXML extends SpartanAuthTestBase {

    @DisplayName("GET request to /api/spartans and verify xml")
    @Test
    public void getSpartanXml(){

        given()
                .auth().basic("admin","admin")
                .and().accept(ContentType.XML)
                .when()
                .get("/api/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.XML)
                .and().body("List.item[0].name",is("Meade"))
        .log().all();


    }
    @DisplayName("GET request /api/spartans with xmlPath")
    @Test
    public void testXmlPath(){

        Response response = given()
                .accept(ContentType.XML) // we want xml response
                .and()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans");


        XmlPath xmlPath = response.xmlPath();

        String name= xmlPath.getString("List.item[0].name");
        System.out.println("First name "+ name);
        int id= xmlPath.getInt("List.item[2].id");
        System.out.println("third id "+ id);

        List<String> names=xmlPath.getList("List.item.name");
        System.out.println(names);



    }

}
