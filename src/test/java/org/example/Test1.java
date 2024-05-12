package org.example;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.network.Network;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Test1 extends TestConfig {

    @Test
    public void addToWishlistTest() throws InterruptedException {
        Object[][] data1 = {{1,2,3},{456}};
        Object data2[][] ={{},{}};
        List<String> productNames = MainPage.getProductNames();
        /*
        js.executeScript("arguments[0].scrollIntoView(true);",MainPage.sizeDropDown);
        MainPage.productList.stream().limit(4).forEach
                (i -> i.findElement(By.cssSelector("button[data-testid='addToWishlist']")).click());

        MainPage.clickFavoritelist();
        MainPage.favoriteItemWaitLoad();

        boolean match = MainPage.favoriteList.stream().anyMatch
                (i -> i.findElement(By.cssSelector("p[data-testid='brandName']")).getText().equals(productNames.get(0)));

        Assert.assertTrue(match);*/
    }

    @Test(dataProvider = "getData")
    public void func1(String product, String brand, String size) throws InterruptedException {
        MainPage.searchBar.sendKeys(product + brand + Keys.ENTER);
        Thread.sleep(1000);
        MainPage.loadWait(MainPage.sizeDropDown);
        js.executeScript("arguments[0].scrollIntoView(true);",MainPage.sizeDropDown);
        MainPage.sizeDropDown.click();
        MainPage.sizeDropDown.findElement(By.xpath("//button[text()="+ size +"]")).click();
    }

    @DataProvider
    public Object[][]getData()
    {
        return new Object[][]{{"pantofi ", "adidas", "36"},{"pantofi ", "nike", "36"}};
    }

    @Test
    public void networkTest()
    {
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));



    }
}




