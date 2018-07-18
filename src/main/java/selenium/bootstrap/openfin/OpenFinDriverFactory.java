package selenium.bootstrap.openfin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import selenium.bootstrap.DriverFactory;

import java.net.MalformedURLException;
import java.net.URL;

import static selenium.bootstrap.DriverJvmArgs.ENV_EXEC_ARGS;
import static selenium.bootstrap.DriverJvmArgs.ENV_EXEC_PATH;
import static selenium.bootstrap.DriverJvmArgs.ENV_REMOTE_DRIVER_URL;

/**
 * Created by Andrew Demetriou on 17/07/2018.
 */
public class OpenFinDriverFactory implements DriverFactory {

    private String execPath;
    private String execArgs;
    private String remoteDriverUrl;


    public OpenFinDriverFactory() {
        execPath = System.getProperty(ENV_EXEC_PATH);
        execArgs = System.getProperty(ENV_EXEC_ARGS);
        remoteDriverUrl = System.getProperty(ENV_REMOTE_DRIVER_URL);
    }

    @Override
    public WebDriver getDriver() {
        URL url = null;
        ChromeOptions options = getChromeOptions();
        DesiredCapabilities capabilities = getDesiredCapabilities(options);

        try {
            url = new URL(remoteDriverUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return new RemoteWebDriver(url, capabilities);
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary(execPath);
        options.addArguments(execArgs);

        return options;
    }

    private DesiredCapabilities getDesiredCapabilities(ChromeOptions options) {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        return capabilities;
    }
}
