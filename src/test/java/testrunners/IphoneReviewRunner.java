package testrunners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(plugin = { "progress" , "html:target/cucumber-html-report04" } ,
        features = "src/test/java/features/IphoneReview" ,
        glue = { "stepdefinitions"})

public class IphoneReviewRunner extends AbstractTestNGCucumberTests {
}
