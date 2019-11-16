package com.example.dk.web;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        plugin = {"pretty"},
        features="src/test/resources/features/",
        glue = { "com.example.dk.web.steps", "com.example.dk.rest.steps" }
)
public class CucumberAcceptanceTest {
}
