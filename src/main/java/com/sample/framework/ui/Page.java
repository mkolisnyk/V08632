package com.sample.framework.ui;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import com.sample.framework.ui.controls.Control;

public class Page {

    private WebDriver driver;

    public Page(WebDriver driverValue) {
        this.driver = driverValue;
    }

    public WebDriver getDriver() {
        return driver;
    }
    public Page navigate() {
        return this;
    }
    public boolean isTextPresent(String text) {
        String locator = String.format("//*[text()='%s' or contains(text(), '%s')]", text, text);
        Control element = new Control(this, By.xpath(locator));
        return element.exists();
    }
    public byte[] captureScreenShot() throws IOException {
        WebDriver augmentedDriver = new Augmenter().augment(this.getDriver());
        byte[] data = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.BYTES);
        return data;
    }
    public File captureScreenShot(String destination) throws IOException {
        WebDriver augmentedDriver = new Augmenter().augment(this.getDriver());
        File srcFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        File output = new File(destination);
        FileUtils.copyFile(srcFile, output);
        return output;
    }
}
