package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Andrew Demetriou on 17/07/2018.
 */
public class DefaultDriverFactory implements DriverFactory {

    public DefaultDriverFactory() {
        String chromeDriverPath = System.getProperty(ENV_EXEC_PATH);
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
    }

    @Override
    public WebDriver getDriver() {
        return new ChromeDriver();
    }
}
