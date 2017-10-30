package com.sample.tests.junit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
import com.sample.tests.pages.SearchPage;
import com.sample.tests.pages.SearchResultsPage;

public class SampleSearchTest {

    private WebDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities cap = new DesiredCapabilities();
        Driver.add(Configuration.get("browser"), cap);
        driver = Driver.current();
        driver.get(Configuration.get("url"));
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
    @Test
    public void testSampleSearch() throws Exception {
        SearchPage searchPage = PageFactory.init(driver, SearchPage.class);
        searchPage.editDestination.setText("London");
        searchPage.buttonDownShevron.click();
        searchPage.buttonTodaysDate.click();
        searchPage.radioBusiness.click();
        SearchResultsPage searchResultsPage = searchPage.buttonSubmit
                                                .click(SearchResultsPage.class);
        searchResultsPage.editDestination.click();
        Assert.assertTrue(searchResultsPage.isTextPresent("London"));
        searchResultsPage.captureScreenShot("./image-London.png");
    }
}
