package com.sample.framework;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
    private Driver() {
    }

    private static ConcurrentHashMap<String, WebDriver> driverThreadMap
        = new ConcurrentHashMap<String, WebDriver>();
    private static final HashMap<String, Class<?>> driverMap = new HashMap<String, Class<?>>() {
        {
            put("chrome", ChromeDriver.class);
            put("firefox", FirefoxDriver.class);
            put("ie", InternetExplorerDriver.class);
            put("safari", SafariDriver.class);
            put("opera", OperaDriver.class);
            put("remote", RemoteWebDriver.class);
        }
    };
    public static void add(String browser, Capabilities capabilities) throws Exception {
        Class<?> driverClass = driverMap.get(browser);
        WebDriver driver = null;
        if (browser.trim().equalsIgnoreCase("remote")) {
            driver = new RemoteWebDriver(new URL(Configuration.get("remote_url")), capabilities);
        } else {
            driver = (WebDriver) driverClass.getConstructor(Capabilities.class).newInstance(capabilities);
        }
        driverThreadMap.put(getThreadName(), driver);
    }
    public static WebDriver current() {
        return driverThreadMap.get(getThreadName());
    }
    public static String getThreadName() {
        return Thread.currentThread().getName() + "-" + Thread.currentThread().getId();
    }
}
