package cucumber.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty"},
        monochrome = true,
        glue = {"cucumber.steps"},
        features = {"classpath:features"}
)

public class RunCucumberTest {
}
