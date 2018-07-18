package selenium.actionhandlers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by Andrew Demetriou on 18/07/2018.
 */
public class WaitHandler {

    private WebDriver driver;

    public WaitHandler(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForSomething() {
        //TODO: This method is not complete, requires a proper implementation.
        waitFor(driver, d -> (Boolean)((JavascriptExecutor) driver)
                .executeScript("return jQuery.active==0"), 10);
    }

    private static void waitFor(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Integer timeoutInSeconds) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
        webDriverWait.withTimeout(timeoutInSeconds, TimeUnit.SECONDS);

        try{
            webDriverWait.until(waitCondition);
        } catch (RuntimeException e){
            e.printStackTrace();
        }
    }
}