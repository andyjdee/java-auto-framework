package selenium.actionhandlers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Andrew Demetriou on 17/07/2018.
 */
public class ClickHandler {

    private WebDriver driver;


    public ClickHandler(WebDriver driver) {
        this.driver = driver;
    }


    public void leftClick(WebElement element) {
        element.click();
    }

    public void rightClick(WebElement element) {
        Actions actions = new Actions(this.driver);
        actions.contextClick(element);
    }

}
