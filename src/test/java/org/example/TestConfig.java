package org.example;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;

import org.openqa.selenium.devtools.v125.fetch.Fetch;
import org.openqa.selenium.devtools.v125.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v125.network.Network;
import org.openqa.selenium.devtools.v125.network.model.ErrorReason;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestConfig {
    WebDriver driver;
    MainPage MainPage;
    JavascriptExecutor js;

    public WebDriver getDriver(){

        if (System.getProperty("browser").contains("firefox"))
        {
            if (System.getProperty("browser").contains("headless")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("headless");
                driver = new FirefoxDriver(options);
                driver.manage().window().setSize(new Dimension(1440, 900));
            }
            else driver= new FirefoxDriver();driver.manage().window().maximize();

        }
        else if (System.getProperty("browser").contains("edge"))
        {if (System.getProperty("browser").contains("headless")) {

            EdgeOptions options = new EdgeOptions();
            options.addArguments("headless");
            driver = new EdgeDriver(options);
            driver.manage().window().setSize(new Dimension(1440, 900));
        }
        else driver= new EdgeDriver();driver.manage().window().maximize();
        }
        else if (System.getProperty("browser").contains("chrome"))
        {
            if (System.getProperty("browser").contains("headless")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("headless");
                driver = new ChromeDriver(options);
                driver.manage().window().setSize(new Dimension(1440, 900));
            }
            else driver= new ChromeDriver();driver.manage().window().maximize();
        }

        return driver;
    }
    public void networkWatch(WebDriver webdriver)
    {
        ChromeDriver driver = (ChromeDriver) webdriver;
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(java.util.Optional.empty(), java.util.Optional.empty(), Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), req ->
                System.out.println(req.getRequest().getUrl()));
        devTools.addListener(Network.responseReceived(), res ->
                System.out.println(res.getResponse().getUrl() + " -> " +res.getResponse().getStatus()));

    }



    @BeforeMethod
    public void init()  {

        //driver = getDriver();

        driver = new ChromeDriver();driver.manage().window().maximize();

        //networkWatch(driver);

        js = (JavascriptExecutor)driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        driver.get("https://www.aboutyou.ro/c/barbati/pantofi-20215");

        MainPage = new MainPage(driver);


    }

    @AfterMethod
    public void close()
    {
        driver.close();
    }
}
