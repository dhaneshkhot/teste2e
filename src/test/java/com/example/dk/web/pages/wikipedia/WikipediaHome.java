package com.example.dk.web.pages.wikipedia;

import org.openqa.selenium.By;

public class WikipediaHome {
    private By searchInputBox = By.id("searchInput");
    private By searchButton = By.cssSelector(".pure-button.pure-button-primary-progressive");

    public WikipediaHome() {}

    public By getSearchInputBox() {
        return searchInputBox;
    }

    public By getSearchButton() {
        return searchButton;
    }
}
