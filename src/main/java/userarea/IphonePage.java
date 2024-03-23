package userarea;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import tools.Browser;
import tools.WaitTool;

public class IphonePage {

    /**
     *Find "Iphone" element,select it and choose "Reviews"
     */
    public static void iPhoneAndReview() {
        Browser.driver.findElement(By.xpath("//*[@id=\"content\"]//*[contains(text(), 'iPhone')]")).click();
        WaitTool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.nav.nav-tabs li:nth-of-type(2) a"))).click();

    }

    /**
     * After "Reviews" element is opened, you can write a review
     * @param name name of the person who write the review
     * @param reviewText reviewText your review
     *  Rate the product whit "Good" radiobutton and check
     */
    public static void reviewCreation(String name, String reviewText) {
        Browser.driver.findElement(By.id("input-name")).sendKeys(name);
        Browser.driver.findElement(By.id("input-review")).sendKeys(reviewText);
        WebElement ratingRadioButtonGood = Browser.driver.findElement(By.xpath("//*[@id=\"form-review\"]/div[4]/div/input[5]"));

        if (!ratingRadioButtonGood.isSelected()){
            ratingRadioButtonGood.click();
        }

        Assert.assertTrue(ratingRadioButtonGood.isSelected());
        Browser. driver.findElement(By.id("button-review")).click();
        WaitTool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"alert alert-success alert-dismissible\"]")));


    }


    /**
     *  Check if the new review is submit successfully
     * @param expectedMessage that appear when we submit the review successfully
     */
    public static void verifySuccessfulCreateReview(String expectedMessage) {
        Assert.assertTrue(Browser.driver.findElement(By.xpath("//div[@class=\"alert alert-success alert-dismissible\"]")).getText().contains(expectedMessage));
    }
}
