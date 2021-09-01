package com.cybertek.Day6;

import com.cybertek.pojo.*;
import com.cybertek.utilies.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void test1() {

        JsonPath jsonPath = get("/regions/")
                .then().statusCode(200)
                .extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRegionId());
        System.out.println("region1.getRegionName() = " + region1.getRegionName());
        System.out.println();
        //System.out.println("href=" + region1.getLinks().get(0).getHref());


        // Link link1=jsonPath.getObject("items[0].links[0].href",Link.class);
        //System.out.println("link1.getHref() = " + link1.getHref());


    }

    @Test
    public void test2() {
        EmployeeInfo employee1 = get("/employees").then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", EmployeeInfo.class);

        System.out.println(employee1);
    }

    /* send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non used fields

     */
    @DisplayName("GET request to region only some fields test")
    @Test
    public void regionPojoTest(){
        //send a get request and save everthing inside the regions object
        //since we prepare pojo also for the all properties we dont need to use any path so as() method is enough
        Regions regions = get("/regions").then().statusCode(200).extract().response().as(Regions.class);

        //verify count is 4
        assertThat(regions.getCount(),is(4));
        //create empty list to store values
        List<String> regionNames = new ArrayList<>();
        List<Integer> regionIds = new ArrayList<>();

        //get list of regions out of regions object
        List<Region> items = regions.getItems();

        //loop through each of the region, save their ids and names to empty list that we prepare
        for (Region region : items) {
            regionIds.add(region.getRegionId());
            regionNames.add(region.getRegionName());
        }
        System.out.println("regionIds = " + regionIds);
        System.out.println("regionNames = " + regionNames);
        //prepare expected result
        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");

        //compare two result
        assertThat(regionIds,is(expectedRegionIds));
        assertThat(regionNames,is(equalTo(expectedRegionNames)));
    }
}




















