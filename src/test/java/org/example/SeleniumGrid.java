package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumGrid {

    @Test
    public void testCheck1() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");

        WebDriver driver = new RemoteWebDriver(new URL("http://10.45.120.39:4444/"), caps);
        driver.get("http://google.com");
        System.out.println(driver.getTitle());

        driver.close();

    }
    @Test
    public void testCheck2() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setBrowserName("chrome");

        WebDriver driver = new RemoteWebDriver(new URL("http://10.45.120.39:4444/"), caps);
        driver.get("http://youtube.com");
        System.out.println(driver.getTitle());

        driver.close();
    }


}
