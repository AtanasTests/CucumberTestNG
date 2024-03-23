package adminarea;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import tools.Browser;
import tools.WaitTool;

public class PageReviews {

    /**
     * Navigate to Admin Area to find "Reviews" and select it
     */
    public static void chooseReviewSection() {
        Browser.driver.findElement(By.xpath("//*[@id=\"menu-catalog\"]/a")).click();
        WaitTool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#collapse1 li:nth-of-type(10)")));
        Browser.driver.findElement(By.cssSelector("#collapse1 li:nth-of-type(9)")).click();
        WaitTool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[1]/div/h1")));
    }

    /**
     * Check if the new review is submit successfully
     * @param name name of the person who write the review
     * @param message text review
     */
    public static void checkReviewExist(String name, String message) {
        Browser.driver.findElement(By.id("input-author")).sendKeys(name);
        Browser.driver.findElement(By.id("button-filter")).click();
        WaitTool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/table/tbody/tr/td[7]/a"))).click();
        Assert.assertTrue(Browser.driver.findElement(By.id("input-text")).getText().contains(message));
    }

    /**
     *This method changes the status of the review to "Enable" and saves it
     */
    public static void enableReview() {
        WaitTool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-status")));
        Select dropDown = new Select(Browser.driver.findElement(By.id("input-status")));
        dropDown.selectByValue("1");
        Browser.driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div/button")).click();
        System.out.println(Browser.driver.findElement(By.xpath("//*[@id=\"content\"]//*[contains(text(), ' Success: You have modified reviews!')]  ")).getText());
    }
}
