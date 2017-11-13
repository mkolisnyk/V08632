package com.sample.tests.pages;

import org.openqa.selenium.WebDriver;

import com.sample.framework.ui.Alias;
import com.sample.framework.ui.FindBy;
import com.sample.framework.ui.Page;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;

@Alias("Search")
public class SearchPage extends Page {
    @Alias("Destination")
    @FindBy(locator = "ss")
    public Edit editDestination;
    @Alias("Down Shevron")
    @FindBy(locator = "css=i.sb-date-field__chevron.bicon-downchevron")
    public Control buttonDownShevron;
    @Alias("Today's Date")
    @FindBy(locator = "//td[contains(@class, 'c2-day-s-today')]")
    public Control buttonTodaysDate;
    @Alias("Business")
    @FindBy(locator = "//input[@name='sb_travel_purpose' and @value='business']")
    public Control radioBusiness;
    @Alias("Leisure")
    @FindBy(locator = "//input[@name='sb_travel_purpose' and @value='leisure']")
    public Control radioLeisure;
    @Alias("Search")
    @FindBy(locator = "//button[@type='submit']")
    public Control buttonSubmit;
    
	public SearchPage(WebDriver driverValue) {
		super(driverValue);
	}

	public SearchPage setTravelPurpose(boolean isBusiness) {
	    if (isBusiness) {
	        this.radioBusiness.click();
	    } else {
	        this.radioLeisure.click();
	    }
	    return this;
	}
}
