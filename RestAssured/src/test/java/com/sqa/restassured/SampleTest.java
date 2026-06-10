package com.sqa.restassured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.json.simple.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class SampleTest {

    /**
     * Library HttpClient
     * Pemfokusan nya untuk Testing
     */
    JSONObject jsonObject;

    String firstName = "";
    String lastName = "";
    String avatar = "";

    @BeforeClass
    public void setUp() {
        // Set base URI for the API under test
        jsonObject = new JSONObject();
        // RestAssured.baseURI = "https://reqres.in";
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(priority = -1)
    public void save() {
        firstName = "Janet";
        lastName = "Weaver";
        avatar = "https://reqres.in/img/faces/2-image.jpg";

    }

    @Test(priority = 0)
    public void testGetUser() {
        Long varLong = 2L;
        System.out.println("Testing...GET USER");
        // Perform GET request and validate response using RestAssured assertions
        Response response = given()
                .header("x-api-key", "pro_d2f00c13edb2b09b51b47f4c95aad0a81c65746d3b81af5a833f8151b3ccf756")
                .header("accept", "*/*")
                .header("Connection", "keep-alive")
                .get("/api/users/" + varLong);

        JsonPath jpath = response.getBody().jsonPath();
        Map<String, Object> m = new HashMap<String, Object>();
        m = jpath.get("data");
        // System.out.println(m.get("first_name").toString());
        // System.out.println(m.get("last_name").toString());
        // System.out.println(m.get("avatar").toString());
        for (Map.Entry<String, Object> entry : m.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // jpath.prettyPrint();
        // Assert.assertEquals(response.getStatusCode(), 200);
        // Assert.assertEquals(jpath.getLong("data.id"), 2L);// E2E
        // Assert.assertEquals(jpath.getString("data.first_name"), firstName);// E2E
        // Assert.assertEquals(jpath.getString("data.last_name"), lastName);// E2E
        // Assert.assertEquals(jpath.getString("data.avatar"), avatar);// E2E
    }

    @Test
    public void testInterupsi() {

    }

    @Test(priority = 5)
    public void testCreateUser() {
        // PAYLOAD
        // Javascript -> Object -> JSON
        // Java -> Object -> CLASS
        jsonObject.put("userId", 22);
        jsonObject.put("id", 22);
        jsonObject.put("title", "Staff");
        jsonObject.put("body", "Ini contoh Jabatan");
        System.out.println("Testing...CREATE USER");
        Response response = given()
                .body(jsonObject)
                .header("x-api-key", "pro_d2f00c13edb2b09b51b47f4c95aad0a81c65746d3b81af5a833f8151b3ccf756")
                .header("accept", "application/json")// */* */
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .post("/posts");

        response.prettyPrint();
    }
}