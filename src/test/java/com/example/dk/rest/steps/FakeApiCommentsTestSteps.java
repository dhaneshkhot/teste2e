package com.example.dk.rest.steps;

import com.example.dk.rest.model.Comment;
import com.google.gson.Gson;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.steps.ScenarioSteps;
import org.eclipse.jetty.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class FakeApiCommentsTestSteps extends ScenarioSteps {
    private Response response;
    String baseUrl = "https://jsonplaceholder.typicode.com";
    String apiUrl;
    Gson gson = new Gson();

    @Given("I have a \\/comments API endpoint")
    public void i_have_a_comments_API_endpoint() {
        apiUrl = baseUrl + "/comments";
    }

    @Then("I can request GET on \\/comments endpoint successfully")
    public void i_can_request_GET_on_comments_endpoint_successfully() {
        response = SerenityRest.given()
                .header("Content-type","application/json")
                .get(apiUrl);
        assertThat("Response status did not match!", response.getStatusCode(), equalTo(HttpStatus.OK_200));
    }

    @Then("I see all the comments")
    public void i_see_all_the_comments() {
        List<?> comments = Arrays.asList(response.then().extract().body().as(Comment[].class));
        assertThat("Number of posts did not match for given user!", comments.size(), equalTo(500));

        // Note that on actual API, I'd set tests data before testing this particular scenario
    }

    @Given("I have a \\/comments API endpoint with postId as url parameter postId={int}")
    public void i_have_a_comments_API_endpoint_with_postId_as_url_parameter_postId(Integer postId) {
        apiUrl = baseUrl + "/comments?postId="+postId;
    }

    @Then("I see correct number of comments for given post")
    public void i_see_correct_number_of_comments_for_given_post() {
        List<?> comments = Arrays.asList(response.then().extract().body().as(Comment[].class));
        assertThat("Number of posts did not match for given user!", comments.size(), equalTo(5));

        // Note that on actual API, I'd set tests data before testing this particular scenario
    }
}
