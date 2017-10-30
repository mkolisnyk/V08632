package com.sample.framework.ui.controls;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Edit extends Control {

    public Edit(WebDriver driverValue, By locatorValue) {
        super(driverValue, locatorValue);
    }

    public void setText(String value) {
        this.click();
        this.element().clear();
        this.element().sendKeys(value);
    }
}
