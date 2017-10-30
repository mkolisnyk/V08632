package com.sample.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sample.framework.Driver;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;

public class SearchPage extends Page {
    public Edit editDestination;
    public Control buttonDownShevron;
    public Control buttonTodaysDate;
    public Control radioBusiness;
    public Control buttonSubmit;
    
	public SearchPage(WebDriver driverValue) {
		super(driverValue);
		editDestination = new Edit(this, By.id("ss"));
        buttonDownShevron = new Control(this, By.cssSelector("i.sb-date-field__chevron.bicon-downchevron"));
        buttonTodaysDate = new Control(this, By.xpath("//td[contains(@class, 'c2-day-s-today')]"));
        radioBusiness = new Control(this, By.name("sb_travel_purpose"));
        buttonSubmit = new Control(this, By.xpath("//button[@type='submit']"));
	}

}
