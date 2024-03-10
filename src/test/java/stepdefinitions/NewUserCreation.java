package stepdefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import tools.Browser;

public class NewUserCreation {
    @Before
    public void setUp() {
        Browser.setup();
    }

    @After
    public void tearDown() {
        Browser.quit();
    }
    /**
     * Invoking this method will navigate you to the user area of the project
     */
    @Given("the user open the browser")
    public void theUserOpenTheBrowser() {
        Browser.driver.get("http://shop.pragmatic.bg/");
    }
    /**
     * Applying this method will open a new page to register a new user
     */
    @And("ne go to account creation Page")
    public void neGoToAccountCreationPage() {
        Actions actions = new Actions(Browser.driver);
        actions.moveToElement(Browser.driver.findElement(By.cssSelector("[title^='My Account']"))).click().build().perform();
        Browser.driver.findElement(By.linkText("Register")).click();
    }
    /**
     *
     This method opens a new account registration page where new User Personal Details is entered
     * @param firstname  name of new User
     * @param lastname family name of new User
     * @param email personal e-mail// new User e-mail
     * @param phone current phone number// new User phone
     * @param password New user's personal password
     * @param repeatpassword Confirm the new user's personal password
     */
    @When("he enters {string}for firstname and {string} for lastname and {string} and personal phonenumber {string} and  password {string} and for repeatpassword {string}")
    public void heEntersForFirstnameAndForLastnameAndAndPersonalPhonenumberAndPaswordAndForRepeatpassword(String firstname, String lastname, String email, String phone, String password, String repeatpassword) {

        Browser.driver.findElement(By.id("input-firstname")).sendKeys(firstname);
        Browser.driver.findElement(By.id("input-lastname")).sendKeys(lastname);
        Browser.driver.findElement(By.id("input-email")).sendKeys(email);
        Browser.driver.findElement(By.id("input-telephone")).sendKeys(phone);
        Browser.driver.findElement(By.id("input-password")).sendKeys(password);
        Browser.driver.findElement(By.id("input-confirm")).sendKeys(repeatpassword);
        Browser.driver.findElement(By.name("agree")).click();
        Browser.driver.findElement(By.xpath("//div/input[2]")).click();

    }
    /**
     *
     Assert that a new user registration has been created with verification text included
     * @param expectedAccountCreatedText the expected validation message

     */
    @Then("ensure the new account is create successfully whit {string} message")
    public void ensureTheNewAccountIsCreateSuccessfullyWhitMessage(String expectedAccountCreatedText) {

        String actualAccountCreatedText = Browser.driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();

        Assert.assertEquals(actualAccountCreatedText, expectedAccountCreatedText);

        System.out.println(Browser.driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText());
        Browser.driver.findElement(By.linkText("Continue")).click();
    }
    /**
     *
     Assert that a new user registration has been created with verification text included
     * @param expectedFailureMessage the message that will appear in your reports in case of failure
     */
    @Then("ensure the new account is failure whit message {string} is displayed")
    public void ensureTheNewAccountIsFailureWhitMessageIsDisplayed(String expectedFailureMessage) {
        Assert.assertEquals(Browser.driver.findElement(By.xpath("//*[@id=\"account-register\"]/div[1]")).getText(), expectedFailureMessage);
    }
}
