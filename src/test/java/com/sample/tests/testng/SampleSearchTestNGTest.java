package com.sample.tests.testng;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
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
    public Object[][] getDataFromFile() throws IOException {
        List<String> content = FileUtils.readLines(new File("./src/test/resources/data.csv"), Charset.defaultCharset());
        Object[][] data = new Object[][] {};
        for (String line : content) {
            data = (Object[][]) ArrayUtils.add(data, line.split("\t"));
        }
        return data;
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
        String[] content = result.split("\n");
        Object[][] data = new Object[][] {};
        for (String line : content) {
            data = (Object[][]) ArrayUtils.add(data, line.split("\t"));
        }
        return data;
    }
    @DataProvider(name = "db_provider")
    public Object[][] getDataFromDB() throws SQLException {
        Object[][] data = new Object[][] {};
        Properties connectionProps = new Properties();
        connectionProps.put("user", "postgres");
        connectionProps.put("password", "admin");
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/test", connectionProps);
            statement = connection.createStatement();
            String query = "SELECT * FROM public.\"Cities\"";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String city = rs.getString("Name");
                boolean isBusiness = rs.getBoolean("isBusiness");
                data = (Object[][]) ArrayUtils.add(data, new Object[] {city, isBusiness});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return data;
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
    @Test(dataProvider = "file_provider")
    public void testSampleSearchFromFile(String destination, String isBusiness) throws Exception {
        sampleSearch(destination, isBusiness.equalsIgnoreCase("true"));
    }
    @Test(dataProvider = "service_provider")
    public void testSampleSearchFromService(String destination, String isBusiness) throws Exception {
        sampleSearch(destination, isBusiness.equalsIgnoreCase("true"));
    }
    @Test(dataProvider = "db_provider")
    public void testSampleSearchFromDB(String destination, boolean isBusiness) throws Exception {
        sampleSearch(destination, isBusiness);
    }
}
