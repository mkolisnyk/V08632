package com.sample.tests.pages;

import org.openqa.selenium.WebDriver;

import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Edit;

public class SearchResultsPage extends Page {

    @FindBy(locator = "ss")
    public Edit editDestination;
	public SearchResultsPage(WebDriver driverValue) {
		super(driverValue);
	}

}
