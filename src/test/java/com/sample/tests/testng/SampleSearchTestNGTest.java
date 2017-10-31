package com.sample.tests.testng;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.PageFactory;
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

    @DataProvider(name = "inclass_provider")
    public Object[][] createData() {
        return new Object[][] {
            { "London", true },
            { "Manchester", false }
        };
    }
    public static class StaticProvider {
        @DataProvider(name = "sample_provider")
        public static Object[][] staticData() {
            return new Object[][] {
                { "Leeds", true },
                { "Newcastle", false }
            };
        }
    }
    @DataProvider(name = "file_provider")
    public Object[][] getDataFromFile() {
        return null;
    }
    @DataProvider(name = "service_provider")
    public Object[][] getDataFromService() {
        HttpClient client = new HttpClient();

        //Instantiate a GET HTTP method
        GetMethod method = new GetMethod("http://localhost:4999/test");
        String result = "";
        try {
            int statusCode = client.executeMethod(method);
            result = method.getResponseBodyAsString();
            method.releaseConnection();
            System.out.println(statusCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void sampleSearch(String destination, boolean isBusiness) throws Exception {
        SearchPage searchPage = PageFactory.init(driver, SearchPage.class);
        searchPage.editDestination.setText(destination);
        searchPage.buttonDownShevron.click();
        searchPage.buttonTodaysDate.click();
        SearchResultsPage searchResultsPage = searchPage
                .setTravelPurpose(isBusiness).buttonSubmit.click(SearchResultsPage.class);
        searchResultsPage.editDestination.click();
        Assert.assertTrue(searchResultsPage.isTextPresent(destination));
        searchResultsPage.captureScreenShot("./image-" + destination + ".png");
    }
    
    @Test(dataProvider = "inclass_provider")
    public void testSampleSearchFromTheSameClass(String destination, boolean isBusiness) throws Exception {
        sampleSearch(destination, isBusiness);
    }
    @Test(dataProvider = "sample_provider", dataProviderClass = StaticProvider.class)
    public void testSampleSearchClassProvider(String destination, boolean isBusiness) throws Exception {
        sampleSearch(destination, isBusiness);
    }
    @Parameters({"destination", "isBusiness"})
    @Test
    public void testParamsFromTestNGXML(String destination, String isBusiness) throws Exception {
        sampleSearch(destination, isBusiness.equalsIgnoreCase("true"));
    }
}
