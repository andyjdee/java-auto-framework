package runners;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Created by Andrew Demetriou on 18/07/2018.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features"
)
public class TestRunner {
}