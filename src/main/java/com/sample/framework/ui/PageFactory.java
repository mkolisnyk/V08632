package com.sample.framework.ui;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageFactory {

    public PageFactory() {
        // TODO Auto-generated constructor stub
    }

    private static By toLocator(String input) {
        if (input.matches("^(xpath=|/)(.*)")) {
            return By.xpath(input.replaceAll("^xpath=", ""));
        } else if (input.matches("^id=(.*)")) {
            return By.id(input.substring("id=".length()));
        } else if (input.matches("^name=(.*)")) {
            return By.name(input.substring("name=".length()));
        } else if (input.matches("^css=(.*)")) {
            return By.cssSelector(input.substring("css=".length()));
        } else if (input.matches("^class=(.*)")) {
            return By.className(input.substring("class=".length()));
        } else {
            return By.id(input);
        }
    }

    public static <T extends Page> T init(WebDriver driver, Class<T> pageClass)
            throws Exception {
        T page = pageClass.getConstructor(WebDriver.class).newInstance(driver);
        for (Field field : pageClass.getFields()) {
            FindBy locator = field.getAnnotation(FindBy.class);
            
            if (locator != null) {
                Object control = field
                        .getType()
                        .getConstructor(Page.class, By.class)
                        .newInstance(
                                page,
                                toLocator(locator.locator()));
                field.set(page, control);
            }
        }
        return page;
    }
}
