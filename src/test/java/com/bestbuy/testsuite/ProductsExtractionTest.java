package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }
    //Extract the limit
    @Test
    public void limit(){
        int limit= response.extract().path("limit");
        System.out.println("Extract the limit : + limit"  );
    }
    //Extract the total
    @Test
    public void total(){
        int total = response.extract().path("total");
        System.out.println("Extract the total : + total");
    }
    //Extract the name of 5th product
    @Test
    public void nameOfThe5thProduct(){
        String productName = response.extract().path("data[4].name");
        System.out.println("5th Product Name : " + "Duracell - C Batteries (4-Pack)");
    }
    //Extract the names of all the products
    @Test
    public void nameOFAllTheProducts(){
        List<String> listOfAllProducts = response.extract().path("data.name");
        System.out.println("List of all the Products " + listOfAllProducts);
    }
    //Extract the productId of all the products
    @Test
    public void allTheIds(){
        List<Integer> listOfIds = response.extract().path("data.id");
        System.out.println("List Of Ids " + listOfIds);
    }
    //Print the size of the data list
    @Test
    public void sizeOfData(){
        List<Integer> sizeOfData = response.extract().path("data.findAll{it}.list");
        System.out.println("Size Of Data List " + sizeOfData.size());
    }
    //Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void valueOfTheProduct(){
       List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name =='Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("The Values of the Product Name 'Energizer - MAX Batteries AA (4-Pack)' are : " + values);
    }
    //Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void modelOfTheProduct(){
        String modelName = response.extract().path("data[8].model");
        System.out.println("Model Name : " + modelName);
    }
    //Get all the categories of 8th products
    @Test
    public void categoriesOf8thProducts(){
        List<String> allTheCategories = response.extract().path("data[8].categories");
        System.out.println("All The Categories Of 8th Product : " + allTheCategories);
    }
    //Get categories of the store where product id = 150115
    @Test
    public void categoriesOfTheStore(){
        List<String> categories = response.extract().path("data[3].categories");
        System.out.println("categories of the store where product id = 150115 : " + categories);
    }
    //Get all the descriptions of all the products
    @Test
            public void allTheDescription() {
        List<String> descriptions = response.extract().path("data.description");
        System.out.println("All The Description Of All the Product : " + descriptions);
    }
    //Get id of all the all categories of all the products
    @Test
    public void allIdsOfAllProducts(){
        List<Integer> allIds = response.extract().path("data.categories.id");
        System.out.println("Ids for All The Categories for All Products : " + allIds);
    }
    //Find the product names Where type = HardGood
    @Test
    public void productName(){
        List<String> productName = response.extract().path("data.name");
        System.out.println("Product Name Where type = HarGood :" + productName);
    }
    //Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void totalNumberOfCategories(){
        List<Integer> sizeOfData = response.extract().path("data[1].categories");
        System.out.println("Total Number Of Categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack) : " + sizeOfData.size());
    }
    // Find the createdAt for all products whose price < 5.49
    @Test
    public void createdAllProduct(){
        List<Integer> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("CreatedAt for all products whose price < 5.49 : " + createdAt);
    }
    // Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void nameOFAllCategories(){
        List<String>nameOFAllCategories = response.extract().path("data[3].categories");
        System.out.println("Name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)” : " + nameOFAllCategories);
    }
    //Find the manufacturer of all the products
    @Test
    public void manufacturer(){
        List<String>manufacturer = response.extract().path("data.manufacturer");
        System.out.println("The manufacturer of all the products : " + manufacturer);
    }
    //Find the imge of products whose manufacturer is = Energizer
    @Test
    public void imageOfProduct(){
        List<String>image = response.extract().path("data[3,8].image");
        System.out.println("The image of products whose manufacturer is = Energizer : " + image);
    }
    //Find the createdAt for all categories products whose price > 5.99
    @Test
    public void createdAtAllCategories(){
        List<Integer> createdAtProduct = response.extract().path("data.findAll{it.categories.'price > 5.99'}.createdAt");
        System.out.println("Find the createdAt for all products " + createdAtProduct);
    }
   //Find the uri of all the products
    @Test
    public void uriOfAll(){
        List<String> uriOfAll = response.extract().path("data.url");
        System.out.println("The uri of all the products : " + uriOfAll);
    }

}
