package com.example.dk.web.steps;

import com.example.dk.web.pages.travelex.TravelexHome;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TravelexTestSteps extends ScenarioSteps {

    @Managed
    WebDriver driver;

    @Steps
    TravelexHome travelexHome;

    @Given("I hit Travelex home page")
    public void i_hit_Travelex_home_page() {
        driver.get("https://www.travelex.co.uk/");
    }

    @When("I resize the window to {int} px width")
    public void i_resize_the_window_to_px(Integer newWindowWidth) {
        Integer windowHeight = driver.manage().window().getSize().getHeight();
        Dimension newDimension = new Dimension(newWindowWidth, windowHeight);
        driver.manage().window().setSize(newDimension);

        if(driver.findElement(travelexHome.getAlertCookiePolicy()).isDisplayed())
            driver.findElement(travelexHome.getAlertCookiePolicy()).click();
    }

    @And("I scroll vertically down by {int} px to travelex services")
    public void i_scroll_vertically_down_by_px_to_travelex_services(Integer byPixel) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0," + byPixel  +")");
    }

    @And("A swipe left on the slider")
    public void a_swipe_left_on_the_slider() throws InterruptedException{
        WebElement slider = driver.findElement(travelexHome.getTravelexServicesSlider());
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(slider));

        Actions action=new Actions(driver);
        action.dragAndDropBy(slider, -200, 0).build().perform();
        Thread.sleep(2500);
    }

    @And("I swipe left again")
    public void another_swipe_left_on_the_slider() throws InterruptedException {
        a_swipe_left_on_the_slider();
    }

    @Then("I see the item displayed is the third item")
    public void i_see_the_item_displayed_is_the_third_item() {
        String slideNo = driver.findElement(travelexHome.getActiveSlide()).getAttribute("index");
        assertThat("Active slide is not the third!", slideNo, equalTo("2"));
    }
}
