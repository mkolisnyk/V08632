package com.sample.framework.ui.controls;

import org.openqa.selenium.By;

import com.sample.framework.ui.Page;

public class Edit extends Control {

    public Edit(Page parentValue, By locatorValue) {
        super(parentValue, locatorValue);
    }

    public void setText(String value) {
        this.click();
        this.element().clear();
        this.element().sendKeys(value);
    }
}
