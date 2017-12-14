package com.sample.framework.ui.controls;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sample.framework.Configuration;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.PageFactory;

public class Control {
    protected static final long TIMEOUT = Configuration.timeout();
    private Page parent;
    private By locator;
    public Control(Page parentValue, By locatorValue) {
        this.parent = parentValue;
        this.locator = locatorValue;
    }
    public WebDriver getDriver() {
        return parent.getDriver();
    }
    public Page getParent() {
		return parent;
	}
	public By getLocator() {
		return locator;
	}
	public WebElement element() {
        return getDriver().findElement(locator);
    }
	public WebElement element(int index) {
        return getDriver().findElements(locator).get(index);
    }
    public boolean exists(long timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
    public boolean visible(long timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
    public boolean exists() {
        return exists(TIMEOUT);
    }
    public boolean visible() {
        return visible(TIMEOUT);
    }
    public void click() {
        Assert.assertTrue(
            "Unable to find element: " + this.locator.toString(),
            exists());
        Assert.assertTrue(
                "Element isn't visible: " + this.locator.toString(),
                visible());
        this.element().click();
    }
    public <T extends Page> T click(Class<T> pageClass) throws Exception {
        	this.click();
        	return PageFactory.init(this.getDriver(), pageClass);
    }
    public String getText() {
        	Assert.assertTrue("Unable to find element with locator: " + this.getLocator(), this.exists());
        	return this.element().getText();
    }
}
