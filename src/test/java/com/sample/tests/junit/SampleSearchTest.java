package com.sample.tests.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.sample.framework.Configuration;
import com.sample.framework.Driver;
import com.sample.framework.ui.controls.Control;
import com.sample.framework.ui.controls.Edit;

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
