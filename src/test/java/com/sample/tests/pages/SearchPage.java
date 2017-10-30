package com.sample.tests.pages;

import org.openqa.selenium.WebDriver;

import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;

public class SearchPage extends Page {
    @FindBy(locator = "ss")
    public Edit editDestination;
    @FindBy(locator = "css=i.sb-date-field__chevron.bicon-downchevron")
    public Control buttonDownShevron;
    @FindBy(locator = "//td[contains(@class, 'c2-day-s-today')]")
    public Control buttonTodaysDate;
    @FindBy(locator = "name=sb_travel_purpose")
    public Control radioBusiness;
    @FindBy(locator = "//button[@type='submit']")
    public Control buttonSubmit;
    
	public SearchPage(WebDriver driverValue) {
		super(driverValue);
	}

}
