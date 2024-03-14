package adminarea;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tools.Browser;
import tools.WaitTool;

public class AdminLogin extends Browser {

    public static void login(String username, String password) {

        Browser.driver.get("http://shop.pragmatic.bg/admin");

        Browser.driver.findElement(By.name("username")).sendKeys(username);
        Browser.driver.findElement(By.name("password")).sendKeys(password);
        Browser.driver.findElement(By.cssSelector(".btn-primary")).click();

        WaitTool.wait.until(ExpectedConditions.titleContains("Dashboard"));
    }
}
