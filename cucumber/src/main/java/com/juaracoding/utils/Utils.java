package com.juaracoding.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static void delay(long second){
        try {
            Thread.sleep(second*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int testCount = 0;

    private static String reportTimestamp;

    public static synchronized String getReportTimestamp() {
        if (reportTimestamp == null) {
            reportTimestamp = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        }
        return reportTimestamp;
    }

    public static String getScreenshot(WebDriver driver,String screenshotName) throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String timestamp = getReportTimestamp();
        String pathDestination = System.getProperty("user.dir") + "/reports/" + timestamp + "/FailedTestScreenshot/"
                + screenshotName + "_" + dateName + ".png";
        File destination = new File(pathDestination);
        FileUtils.copyFile(source,destination);
        return pathDestination;
    }

}
