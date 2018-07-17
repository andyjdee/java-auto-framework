package selenium;

import org.openqa.selenium.WebDriver;

/**
 * Created by Andrew Demetriou on 17/07/2018.
 */
public interface DriverFactory {

    String ENV_EXEC_PATH = "ExecPath";
    String ENV_EXEC_ARGS = "ExecArgs";
    String ENV_REMOTE_DRIVER_URL = "RemoteDriverUrl";

    WebDriver getDriver();
}
