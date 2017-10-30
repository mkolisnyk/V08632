package com.sample.tests.testng;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;

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
        Edit editDestination = new Edit(driver, By.id("ss"));
        Control buttonDownShevron = new Control(driver, By.cssSelector("i.sb-date-field__chevron.bicon-downchevron"));
        Control buttonTodaysDate = new Control(driver, By.xpath("//td[contains(@class, 'c2-day-s-today')]"));
        Control radioBusiness = new Control(driver, By.name("sb_travel_purpose"));
        Control buttonSubmit = new Control(driver, By.xpath("//button[@type='submit']"));
        
        editDestination.setText("London");
        buttonDownShevron.click();
        buttonTodaysDate.click();
        radioBusiness.click();
        buttonSubmit.click();
        editDestination.click();
    }
}
