package stepdefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import tools.Browser;
import tools.WaitTool;

public class GiftVoucherSteps {

    @Before
    public void setUp() {
        Browser.setup();
    }

    @After
    public void tearDown() {
        Browser.quit();
    }


    @Given("admin open the browser")

    public void adminStartTheBrowser() {

        Browser.driver.get("http://shop.pragmatic.bg/admin");

    }
    /**
     * This method logs you into the admin area with the
     * provided credentials as parameters
     * @param username the username you would like to login with
     * @param password the password you would like to login with
     */

    @When("he fulfill username {string} and fulfill password {string}")

    public void heFulfillUsernameAndFulfillPassword(String username, String password) {


        Browser.driver.findElement(By.name("username")).sendKeys(username);
        Browser.driver.findElement(By.name("password")).sendKeys(password);
        Browser.driver.findElement(By.cssSelector(".btn-primary")).click();

        WaitTool.wait.until(ExpectedConditions.titleContains("Dashboard"));
    }


    /**
     *  Assert you're on the admin page
     * @param text
     */

    @And("he check if he is on administrator Page with text {string}")

    public void heCheckIfHeIsOnAdministratorPageWithText(String text) {


        String actualMessage = Browser.driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/h1")).getText();
        Assert.assertEquals(actualMessage, text);
        System.out.println(Browser.driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/h1")).getText());
    }

    /**
     * Navigate to the section menu, start with the "Sales" section and go to the "Gift menu", then select "Gift vouchers"
     * Asserts  if the voucher that we would like to create is already existed.
     * @param code If the voucher exists, a message with the name or Code number will appear
     * @param failure message if the voucher does not exist
     */
    @And("he assert if the voucher exist with existing code {string} and message if failure {string}")

    public void heAssertIfTheVoucherExistWithExistingCodeAndMessageIfFailure(String code, String failure) {

        Browser.driver.findElement(By.xpath("//a[normalize-space()='Sales']")).click();
        WaitTool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#collapse4 a[href=\"#collapse4-3\"]"))).click();
        Browser.driver.findElement(By.id("collapse4-3")).click();


        String actualVExistText = Browser.driver.findElement(By.xpath("//*[@id=\"form-voucher\"]/div/table/tbody/tr[1]/td[2]")).getText();
        Assert.assertEquals(actualVExistText, code, failure);
        System.out.println(Browser.driver.findElement(By.xpath("//*[@id=\"form-voucher\"]/div/table/tbody/tr[1]/td[2]")).getText());

    }

    /**
     * This method will delete the existing voucher
     * @param FromName name of the voucher owner
     */
    @Then("he delete the Voucher  FromName {string}")

    public void heDeleteTheVoucherFromName(String FromName) {

        WaitTool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.table-responsive tbody")));

        WebElement GiftTableVouchers = Browser.driver.findElement(By.cssSelector("div.table-responsive tbody"));

        if (GiftTableVouchers.getText().contains(FromName)) {
            Browser.driver.findElement(By.xpath("//tbody//tr//td[text()=\"Atanas Yordanov\"]//parent::tr//td[1]")).click();
            Browser.driver.findElement(By.cssSelector(".btn-danger")).click();
            try {
                Alert alert = Browser.driver.switchTo().alert();
                alert.accept();

            } catch (NoAlertPresentException e) {
                e.printStackTrace();
            }
        }
        Browser.driver.findElement(By.xpath("//div[1]/div/div/a")).click();

    }

    /**
     * This method will create a new voucher
     * @param Code name or number of the voucher
     * @param FromName name of the person who will present the voucher
     * @param FromEmail e_mail of the person who will present the voucher
     * @param ToName name of the person who will use the voucher
     * @param ToEmail email of the person who will use the voucher
     * @param Amount amount that the person can use with this voucher
     */
    @And("he create a new voucher with Code {string} and FromName {string} and FromEmail{string} for ToName {string} and ToEmail {string} and Amount {string}")

    public void heCreateANewVoucherWithCodeAndFromNameAndFromEmailForToNameAndToEmailAndAmount(String Code, String FromName, String FromEmail, String ToName, String ToEmail, String Amount) {

        WaitTool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-code")));

        Browser.driver.findElement(By.id("input-code")).sendKeys(Code);
        Browser.driver.findElement(By.name("from_name")).sendKeys(FromName);
        Browser.driver.findElement(By.id("input-from-email")).sendKeys(FromEmail);
        Browser.driver.findElement(By.id("input-to-name")).sendKeys(ToName);
        Browser.driver.findElement(By.id("input-to-email")).sendKeys(ToEmail);
        Browser.driver.findElement(By.name("amount")).sendKeys(Amount);
        Browser.driver.findElement(By.xpath("//div[1]/div/div/button/i")).click();
    }

    /**
     * Verify the voucher is successfully created
     * @param Success message if voucher is successfully created
     * @param notCreated message on failure
     */
    @Then("he verify the New Voucher is created with Successfully {} and notCreated {} message")
    public void heVerifyTheNewVoucherIsCreatedWithSuccessfullyAndNotCreatedMessage(String Success, String notCreated) {

        WaitTool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success alert-dismissible']")));
        Assert.assertTrue(Browser.driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).getText().contains(Success), notCreated);
        System.out.println(Browser.driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissible']")).getText());

    }
}
