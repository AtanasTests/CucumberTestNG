package utils;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tools.Browser;
import tools.WaitTool;

/**
 * This class represent component in http://shop.pragmatic.bg/ which is always visible when we move between the pages in this website.
 * You can add later or choose different elements in this component.
 */

public class HomePageHeaderNavigation {

    @FindBy(how = How.XPATH, using = "//*[@id=\"menu\"]/div[2]/ul/li[6]/a ")
    public static WebElement phoneandpdas;
    @FindBy(how = How.ID, using = "content")
    public static WebElement content;
    //*[@id="menu"]//*[contains(text(), 'Phones & PDAs')]

    private String url = "http://shop.pragmatic.bg/";

    /**
     * Constructor to create new object of this class, in the test, to use all methods.
     */
    public HomePageHeaderNavigation() {
        PageFactory.initElements(Browser.driver, this);
    }


    /**
     * Locate Iphones & PDAs Product
     */
    public void pickPhonesAndPDAs() {
        phoneandpdas.click();
        WaitTool.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("content")));
    }


    /**
     * Open website "http://shop.pragmatic.bg/".
     */
    public void goToHomePage() {
        Browser.driver.get(this.url);
    }
}
