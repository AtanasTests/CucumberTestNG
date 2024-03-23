package testrunners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(plugin = { "progress" , "html:target/cucumber-html-report03" } ,
        features = "src/test/java/features/GiftVoucher",
        glue = {"stepdefinitions"} )

public class GiftVoucherRunner extends AbstractTestNGCucumberTests {
}
