package testrunners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(plugin = { "progress" , "html:target/cucumber-html-report01" } ,
        features = "src/test/java/features/NewUserCreation",
        glue = {"stepdefinitions"} )

public class NewUserCreationRunner extends AbstractTestNGCucumberTests {
}
