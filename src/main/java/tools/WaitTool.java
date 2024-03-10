package tools;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitTool extends Browser{

    public static WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
}
