package com.example.dk.web.steps;

import com.example.dk.web.pages.wikipedia.WikipediaArticle;
import com.example.dk.web.pages.wikipedia.WikipediaSearchResult;
import com.example.dk.web.pages.wikipedia.WikipediaHome;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WikipediaTestSteps extends ScenarioSteps {

    @Managed
    WebDriver driver;

    @Steps
    WikipediaHome wikipedia;

    @Steps
    WikipediaSearchResult wikipediaSearchResultPage;

    @Steps
    WikipediaArticle wikipediaArticle;

    @Given("I hit Wikipedia home page")
    public void i_hit_wikipedia_home_page() {
        driver.get("https://wikipedia.org");
    }

    @Then("I see page title as {string}")
    public void i_see_page_title_as(String string) {
        assertThat("Page title is wrong! ", driver.getTitle(), equalTo(string));
    }

    @When("I search for {string}")
    public void i_search_for(String searchString) {
        driver.findElement(wikipedia.getSearchInputBox()).clear();
        driver.findElement(wikipedia.getSearchInputBox()).sendKeys(searchString);
        driver.findElement(wikipedia.getSearchButton()).click();
    }

    @Then("A {string} suggestion appears")
    public void a_suggestion_appears(String suggestion) {
        assertThat("Search suggestion did not appear!",
                driver.findElement(wikipediaSearchResultPage.getDidYouMean()).getText().contains(suggestion),
                equalTo(true));
    }

    @And("I click on appeared suggestion")
    public void i_click_on_appeared_suggestion() {
        driver.findElement(wikipediaSearchResultPage.getSearchSuggestion()).click();
    }

    @Then("I see {int} search results")
    public void i_see_search_results(Integer expectedTotalResults) {
        List<WebElement> searchResults = driver.findElements(wikipediaSearchResultPage.getSearchResults());
        assertThat("Total search result did not match!", searchResults.size(), equalTo(expectedTotalResults));
    }

    @And("On clicking first suggestion, I see that article has title and table of content")
    public void on_clicking_first_suggestion_I_see_that_article_has_title_and_table_of_content() {
        driver.findElement(wikipediaSearchResultPage.getFirstResult()).click();
        assertThat("Article header is not displayed!",
                driver.findElement(wikipediaArticle.getArticleHeader()).isDisplayed(),
                equalTo(true));
        assertThat("Table of Content is not displayed!",
                driver.findElement(wikipediaArticle.getToc()).isDisplayed(),
                equalTo(true));
    }

}
