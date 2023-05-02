package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		
		features = {".//Feature/Customer.feature"},
		glue = "stepdefinition",
		dryRun = false,
		monochrome = true,
		plugin = {"pretty", "html:target/CucumberReport.html", "json:target/CucumberJson"},
		tags = "@sanity"
		
		
		
		)
public class TestRun {

}
