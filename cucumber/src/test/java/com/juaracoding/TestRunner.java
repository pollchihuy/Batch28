package com.juaracoding;

import com.juaracoding.utils.Utils;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/main/resources/features", glue = "com.juaracoding")
public class TestRunner extends AbstractTestNGCucumberTests {
    static {
        String timestamp = Utils.getReportTimestamp();
        System.setProperty("cucumber.plugin",
                "pretty," +
                        "html:reports/" + timestamp + "/" + timestamp + "-cucumber-report.html," +
                        "json:reports/" + timestamp + "/" + timestamp + "-cucumber.json");
    }
}
// per size 50GB
// per time 3 bulan