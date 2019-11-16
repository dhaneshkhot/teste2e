package com.example.dk.rest;

import com.example.dk.rest.steps.FakeApiPostsTestSteps;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This is another approach instead of CucumberRunner. However, need to see why index.html report is not generated.
 */
@RunWith(SerenityRunner.class)
public class FakeApiPostsTests {
    @Steps
    FakeApiPostsTestSteps fakeApiPostsTestSteps;

    @Test
    public void shouldGetAllPosts(){
        //Given
        fakeApiPostsTestSteps.i_have_a_posts_API_endpoint();
        //Then
        fakeApiPostsTestSteps.i_can_request_GET_on_post_endpoint();
    }
}
