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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SampleTest {

    /**
     * Library HttpClient
     * Pemfokusan nya untuk Testing
     */
    JSONObject jsonObject;

    @BeforeClass
    public void setUp() {
        // Set base URI for the API under test
        jsonObject = new JSONObject();
        // RestAssured.baseURI = "https://reqres.in";
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test(priority = 0)
    public void testGetUser() {
        Long varLong = 2L;
        System.out.println("Testing...GET USER");
        // Perform GET request and validate response using RestAssured assertions
        Response response = given()
                .header("accept", "application/json")
                .header("Connection", "keep-alive")
                .get("posts/1/comments");

        Boolean isFound = false;
        JsonPath jpath = response.getBody().jsonPath();
        List<Map<String, Object>> list = new ArrayList<>();
        list = jpath.get();
        System.out.println("Size : " + list.size());
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> m = list.get(i);
            for (Map.Entry<String, Object> entry : m.entrySet()) {
                // System.out.println(entry.getKey() + " : " + entry.getValue());
                if (m.get("email").equals("Jayne_Kuhic@sydney.com")) {
                    System.out.println("Ketemu !! di Index ke " + i);
                    isFound = true;
                    break;
                }
            }
        }
        // Map<String, Object> m = new HashMap<String, Object>();
        // m = jpath.get("data");
        // System.out.println(m.get("first_name").toString());
        // System.out.println(m.get("last_name").toString());
        // System.out.println(m.get("avatar").toString());
        // for (Map.Entry<String, Object> entry : m.entrySet()) {
        // System.out.println(entry.getKey() + " : " + entry.getValue());
        // }

        Assert.assertTrue(isFound);
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
        Integer userId = 23;
        Integer id = 23;
        String title = "Staff";
        String body = "Ini Contoh Jabatan";

        // PAYLOAD
        // Javascript -> Object -> JSON
        // Java -> Object -> CLASS
        jsonObject.put("userId", 23);
        jsonObject.put("id", id);
        jsonObject.put("title", title);
        jsonObject.put("body", body);
        System.out.println("Testing...CREATE USER");
        Response response = given()
                .body(jsonObject)
                .header("accept", "application/json")// */* */
                .header("Connection", "keep-alive")
                .header("Content-Type", "application/json")
                .post("/posts");
        response.prettyPrint();
        JsonPath jpath = response.getBody().jsonPath();
        System.out.println("Status Code: " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(jpath.getInt("userId"), userId);
        Assert.assertEquals(jpath.getString("title"), title);
        Assert.assertEquals(jpath.getString("body"), body);
    }
}