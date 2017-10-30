package com.sample.tests.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;
import com.sample.tests.pages.SearchPage;
import com.sample.tests.pages.SearchResultsPage;

public class SampleSearchTestNGTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        Driver.add(Configuration.get("browser"), cap);
        driver = Driver.current();
        driver.get(Configuration.get("url"));
    }
    
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void testSampleSearch() {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.editDestination.setText("London");
        searchPage.buttonDownShevron.click();
        searchPage.buttonTodaysDate.click();
        searchPage.radioBusiness.click();
        searchPage.buttonSubmit.click();
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.editDestination.click();
        Assert.assertTrue(searchResultsPage.isTextPresent("London"));
    }
}
