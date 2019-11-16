package com.example.dk.rest.steps;

import com.example.dk.rest.model.Comment;
import com.example.dk.rest.model.Post;
import com.google.gson.Gson;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.steps.ScenarioSteps;
import org.eclipse.jetty.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FakeApiPostsTestSteps extends ScenarioSteps {
    private Response response;
    String baseUrl = "https://jsonplaceholder.typicode.com";
    String apiUrl;
    Gson gson = new Gson();

    @Given("^I have a /posts API endpoint$")
    public void i_have_a_posts_API_endpoint() {
        apiUrl = baseUrl + "/posts";
    }

    @Then("I can request GET on /posts endpoint successfully")
    public void i_can_request_GET_on_post_endpoint() {
        response = SerenityRest.given()
                .header("Content-type","application/json")
                .get(apiUrl);
        assertThat("Response status did not match!", response.getStatusCode(), equalTo(HttpStatus.OK_200));
    }

    @Then("I see all the posts")
    public void i_see_all_the_posts() {
        List<?> posts = Arrays.asList(response.then().extract().body().as(Post[].class));
        assertThat("Number of posts did not match for given user!", posts.size(), equalTo(100));

        // Note that on actual API, I'd set tests data before testing this particular scenario
    }

    @Given("I have a /posts API endpoint with userId as url parameter userId={int}")
    public void i_have_a_posts_API_endpoint_with_userId_as_url_parameter_userId(Integer userId) {
        apiUrl = baseUrl + "/posts?userId="+userId;
    }

    @And("I see correct number of posts for given user")
    public void i_see_correct_number_of_posts_for_given_user(){
        List<?> posts = Arrays.asList(response.then().extract().body().as(Post[].class));
        assertThat("Number of posts did not match for given user!", posts.size(), equalTo(10));

        // Note that on actual API, I'd set tests data before testing this particular scenario
    }

    @Given("I have a \\/posts\\/id API endpoint for an individual id={int}")
    public void i_have_a_posts_id_API_endpoint_for_an_individual_id(Integer postId) {
        apiUrl = baseUrl + "/posts" + "/" + postId;
    }

    @When("I request GET on \\/posts\\/id endpoint successfully")
    public void i_request_GET_on_posts_id_endpoint_successfully() {
        response = SerenityRest.given()
                .header("Content-type","application/json")
                .header("charset", "UTF-8")
                .get(apiUrl);
        assertThat("Response status did not match!", response.getStatusCode(), equalTo(HttpStatus.OK_200));
    }

    @When("I request POST on /posts endpoint successfully for {int} with {string}, and {string}")
    public void i_request_POST_on_posts_endpoint_successfully_for_with_and(Integer userId, String title, String body) {
        Post post = new Post();
        post.setUserId(userId);
        post.setTitle(title);
        post.setBody(body);

        String jsonPost = gson.toJson(post);

        response = SerenityRest.given()
                .header("Content-type","application/json")
                .header("charset", "UTF-8")
                .body(jsonPost)
                .post(apiUrl);
        assertThat("Response status did not match!", response.getStatusCode(), equalTo(HttpStatus.CREATED_201));
    }

    @Then("I get newly created post for {int} with {string}, and {string}")
    public void i_get_newly_created_post_for_with_and(Integer userId, String title, String body) {
        Post newPost = response.getBody().as(Post.class);
        assertThat("UserId did not match!", newPost.getUserId(), equalTo(userId));
        assertThat("Id did not have any value!", newPost.getId() > 0, equalTo(true));
        assertThat("Title did not match!", newPost.getTitle(), equalTo(title));
        assertThat("Body did not match!", newPost.getBody(), equalTo(body));

        // Setting posts/id To do a GET call on the new post id
        i_have_a_posts_id_API_endpoint_for_an_individual_id(newPost.getId());
    }

    @Then("When request GET on \\/posts\\/id, I see new post is indeed persisted")
    public void when_request_GET_on_posts_id_I_see_new_post_and_indeed_persisted() {
        i_request_GET_on_posts_id_endpoint_successfully();
    }

    @When("I request PUT on \\/posts\\/id endpoint successfully with {int}, {string}, and {string}")
    public void i_request_PUT_on_posts_endpoint_successfully_with_and(Integer userId, String title, String body) {
        Post post = new Post();
        post.setUserId(userId);
        post.setTitle(title);
        post.setBody(body);

        String jsonBody = gson.toJson(post);

        response = SerenityRest.given()
                .header("Content-type","application/json")
                .header("charset", "UTF-8")
                .body(jsonBody)
                .put(apiUrl);
        assertThat("Response status did not match!", response.getStatusCode(), equalTo(HttpStatus.OK_200));
    }

    @Then("I get updated post for {int} with updated values {int}, {string}, and {string}")
    public void i_get_updated_post_for_with_updated_values_and(Integer postId, Integer userId, String title, String body) {
        Post newPost = response.getBody().as(Post.class);
        assertThat("UserId did not match!", newPost.getUserId(), equalTo(userId));
        assertThat("Id did not have any value!", newPost.getId(), equalTo(postId));
        assertThat("Title did not match!", newPost.getTitle(), equalTo(title));
        assertThat("Body did not match!", newPost.getBody(), equalTo(body));
    }

    @Then("When request GET on \\/posts\\/id, I see updated post {int}, {int}, {string}, and {string} is indeed persisted")
    public void when_request_GET_on_posts_id_I_see_updated_post_and_is_indeed_persisted(Integer postId, Integer userId, String title, String body) {
        i_request_GET_on_posts_id_endpoint_successfully();

        Post newPost = response.getBody().as(Post.class);
        assertThat("UserId did not match!", newPost.getUserId(), equalTo(userId));
        assertThat("Id did not have any value!", newPost.getId(), equalTo(postId));
        assertThat("Title did not match!", newPost.getTitle(), equalTo(title));
        assertThat("Body did not match!", newPost.getBody(), equalTo(body));
    }

    @When("I request PATCH on \\/posts\\/id endpoint successfully with {string}")
    public void i_request_PATCH_on_posts_id_endpoint_successfully_with(String newTitleToUpdate) {
        String jsonBody = "{\"title\": \""+ newTitleToUpdate + "\"}";

        response = SerenityRest.given()
                .header("Content-type","application/json")
                .header("charset", "UTF-8")
                .body(jsonBody)
                .patch(apiUrl);
        assertThat("Response status did not match!", response.getStatusCode(), equalTo(HttpStatus.OK_200));
    }

    @Then("I get updated post for {int} with updated values for title {string}")
    public void i_get_updated_post_for_with_updated_values_for_title(Integer postId, String title) {
        Post newPost = response.getBody().as(Post.class);
        assertThat("Id did not have any value!", newPost.getId(), equalTo(postId));
        assertThat("Title did not match!", newPost.getTitle(), equalTo(title));
    }

    @Then("When request GET on \\/posts\\/id, I see partially updated post with title {string} is indeed persisted")
    public void when_request_GET_on_posts_id_I_see_partially_updated_post_with_title_is_indeed_persisted(String title) {
        i_request_GET_on_posts_id_endpoint_successfully();

        Post newPost = response.getBody().as(Post.class);
        assertThat("Title did not match!", newPost.getTitle(), equalTo(title));
    }

    @Then("I get and individual post for {int}")
    public void i_get_and_individual_post_for(Integer postId) {
        Post newPost = response.getBody().as(Post.class);
        assertThat("Id did not have any value!", newPost.getId(), equalTo(postId));
    }

    @When("I request DELETE on \\/posts\\/id endpoint successfully")
    public void i_request_DELETE_on_posts_id_endpoint_successfully() {
        response = SerenityRest.given()
                .header("Content-type","application/json")
                .header("charset", "UTF-8")
                .delete(apiUrl);
        assertThat("Response status did not match!", response.getStatusCode(), equalTo(HttpStatus.OK_200));
    }

    @Then("I should not be able to request GET post for {int}")
    public void i_should_not_be_able_to_request_GET_post_for(Integer int1) {
        response = SerenityRest.given()
                .header("Content-type","application/json")
                .header("charset", "UTF-8")
                .get(apiUrl);
        assertThat("Response status did not match!", response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND_404));
    }

    @Given("I have a \\/posts\\/id\\/comments API endpoint for an individual id={int}")
    public void i_have_a_posts_id_comments_API_endpoint_for_an_individual_id(Integer postId) {
        apiUrl = baseUrl + "/posts" + "/" + postId + "/comments";
    }

    @When("I request GET on \\/posts\\/id\\/comments endpoint successfully")
    public void i_request_GET_on_posts_id_comments_endpoint_successfully() {
        response = SerenityRest.given()
                .header("Content-type","application/json")
                .get(apiUrl);
        assertThat("Response status did not match!", response.getStatusCode(), equalTo(HttpStatus.OK_200));
    }

    @Then("I should be able to see all the comments for the given post")
    public void i_should_be__able_to_see_all_the_comments_for_the_given_post() {
        List<?> posts = Arrays.asList(response.then().extract().body().as(Comment[].class));
        assertThat("Number of comments did not match for given user!", posts.size(), equalTo(5));

        // Note that on actual API, I'd set tests data before testing this particular scenario
    }

}
