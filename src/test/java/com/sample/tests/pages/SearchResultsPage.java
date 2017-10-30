package com.sample.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Edit;

public class SearchResultsPage extends Page {

    public Edit editDestination;
	public SearchResultsPage(WebDriver driverValue) {
		super(driverValue);
        editDestination = new Edit(this, By.id("ss"));
	}

}
