package org.example.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class ApiSteps {
    private String endpoint;
    private Response response;
    
    @Given("I have the API endpoint {string}")
    public void i_have_the_api_endpoint(String url) {
        this.endpoint = url;
    }
    
    @When("I send a GET request")
    public void i_send_a_get_request() {
        response = given().when().get(endpoint);
    }
    
    @When("I send a POST request with user data")
    public void i_send_a_post_request_with_user_data() {
        String requestBody = "{\"name\": \"John Doe\", \"username\": \"johndoe\", \"email\": \"john@example.com\"}";
        response = given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(endpoint);
    }
    
    @Then("the response status should be {int}")
    public void the_response_status_should_be(int statusCode) {
        assertEquals(response.getStatusCode(), statusCode);
    }
    
    @Then("the response should contain {string}")
    public void the_response_should_contain(String text) {
        assertTrue(response.getBody().asString().contains(text+"1"), "Assertion Failed, Expected: "+ text +" Actual: "+ text+"1");
    }
    
    @Then("the response should contain the created user name")
    public void the_response_should_contain_the_created_user_name() {
        assertTrue(response.getBody().asString().contains("John Doe"));
    }
}