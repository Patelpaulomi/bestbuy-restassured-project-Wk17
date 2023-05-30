package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasKey;

public class StoresAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }
    //Verify the if the total is equal to 1561
    @Test
    public void verifyTheTotal(){
        response.body("total",equalTo(1561));
    }
    //Verify the if the stores of limit is equal to 10
    @Test
    public void verifyLimit() {
        response.body("limit", equalTo(10));
    }


    @Test
    //Check the single ‘Name’ in the Array list (Inver Grove Heights)
    public void verifySingleName() {
        response.body("data.name", hasItem("Inver Grove Heights"));
    }

    @Test
    //Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    public void verifyMultipleName() {
        response.body("data.name", hasItems("Roseville", "Burnsville", "Maplewood"));

    }

    //5. Verify the storied=7 inside storeservices of the third store of second services
    @Test
    public void verifyStoreID() {
        response.body("data[2].services[1].storeservices.storeId", equalTo(7));

    }

    @Test
    public void storeName() {
        //Check hash map values ‘createdAt’ inside storeservices map where store name = Roseville
        response.body("data.find{it.name == 'Roseville'}.services.storeservices.find{it.createdAt}", hasKey("createdAt"));

    }

    @Test
    public void verifyState() {
        //Verify the state = MN of forth store
        String state = response.extract().path("data[3].state");
        System.out.println("State name is " + state);
    }

    @Test
    public void verifyStoreName() {
        //Verify the store name = Rochester of 9th store
        String storeName = response.extract().path("data[8].state");
        System.out.println("9th store name is " + storeName);

    }

    @Test
    //Verify the storeId = 11 for the 6th store
    public void verifyStoreId() {
        int id = response.extract().path("data[5].id");
        System.out.println("6th Store Id is " + id);
        //data[5].id
    }

    //10. Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void verifyServiceId() {
        int serviceId = response.extract().path("data[6].services[3].id");
        System.out.println("6th Store Id is " + serviceId);


    }




}
