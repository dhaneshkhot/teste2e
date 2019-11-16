package com.example.dk.web.pages.travelex;

import org.openqa.selenium.By;

public class TravelexHome {
    By travelexServicesSlider = By.cssSelector(".eighteen-col.clearfix > .matchHeights.slick-slider > .slick-list > .slick-track");
    By alertCookiePolicy = By.cssSelector(".optanon-alert-box-close.banner-close-button");
    By activeSlide = By.cssSelector(".eighteen-col.clearfix > .matchHeights.slick-slider > .slick-list > .slick-track > .slick-active");

    public TravelexHome() {}

    public By getTravelexServicesSlider() {
        return travelexServicesSlider;
    }

    public By getAlertCookiePolicy() {
        return alertCookiePolicy;
    }

    public By getActiveSlide() {
        return activeSlide;
    }
}
