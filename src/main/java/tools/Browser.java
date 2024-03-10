package tools;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Browser {

    public static WebDriver driver ;

    /**
     * Open new browser and maximize it
     */

    public static void setup(){

        driver = new ChromeDriver(); //Creates new browser instance
        driver.manage().window().minimize(); //Minimize browser


    }

    /**
     * Quit the browser
     */

    public static void quit(){
        driver.quit();
    }


    /**
     * This method capture a screenshot of a specific WebElement (web element) in a web page
     */
    public static File captureElementBitmap(WebElement element) throws Exception {
        WrapsDriver wrapsDriver = (WrapsDriver) element;
        File screen = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
        BufferedImage img = ImageIO.read(screen);
        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();
        java.awt.Rectangle rect = new java.awt.Rectangle(width, height);
        Point p = element.getLocation();
        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);
        ImageIO.write(dest, "png", screen);
        return screen;
    }
}
