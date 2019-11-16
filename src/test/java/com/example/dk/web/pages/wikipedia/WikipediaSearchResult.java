package com.example.dk.web.pages.wikipedia;

import org.openqa.selenium.By;

public class WikipediaSearchResult {
    private By didYouMean = By.className("searchdidyoumean");
    private By searchSuggestion = By.cssSelector(".searchdidyoumean > a");
    private By searchResults = By.className("mw-search-result");
    private By firstResult = By.cssSelector(".mw-search-result:first-child > div > a");

    public WikipediaSearchResult(){}

    public By getDidYouMean() {
        return didYouMean;
    }

    public By getSearchSuggestion() {
        return searchSuggestion;
    }

    public By getSearchResults() {
        return searchResults;
    }

    public By getFirstResult() {
        return firstResult;
    }

}
