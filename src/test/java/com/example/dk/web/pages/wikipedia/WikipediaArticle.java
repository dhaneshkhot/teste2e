package com.example.dk.web.pages.wikipedia;

import org.openqa.selenium.By;

public class WikipediaArticle {
    private By articleHeader = By.id("firstHeading");
    private By toc = By.id("toc");

    public WikipediaArticle() {}

    public By getArticleHeader() {
        return articleHeader;
    }

    public By getToc() {
        return toc;
    }
}
