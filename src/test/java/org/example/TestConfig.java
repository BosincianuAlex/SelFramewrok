package org.example;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.*;
import java.time.Duration;

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

    @BeforeMethod
    public MainPage init()
    {
        driver = getDriver();
        js = (JavascriptExecutor)driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.aboutyou.ro/c/barbati/pantofi-20215");
        MainPage = new MainPage(driver);
        return MainPage;
    }

    @AfterMethod
    public void close()
    {
        driver.close();
    }
}
