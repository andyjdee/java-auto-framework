package selenium.actionhandlers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by Andrew Demetriou on 17/07/2018.
 */
public class KeyHandler {

    private WebDriver driver;

    public KeyHandler(WebDriver driver) {
        this.driver = driver;
    }

    public void sendText(WebElement element, String text) {
        element.sendKeys(text);
    }
}
