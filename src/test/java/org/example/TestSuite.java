package org.example;
import org.openqa.selenium.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static org.example.MainPage.*;

public class TestSuite extends TestConfig {

    @Test
    public void addToWishlistTest(){

        List<String> productNames = MainPage.getProductNames(productCatalogue);

        productCatalogue.stream().limit(4).forEach
                (i -> i.findElement(By.cssSelector("button[data-testid='addToWishlist']")).click());

        MainPage.clickFavoritelist();
        MainPage.loadWait(favoriteItems);

        List<String> favoriteProductNames = MainPage.getProductNames(favoriteList);
        boolean match =productNames.containsAll(favoriteProductNames);

        Assert.assertTrue(match);
    }

    @Test(dataProvider = "getData")
    public void sizeDropDownTest(String product, String brand, String size) throws InterruptedException {

        MainPage.searchBar.sendKeys(product + " " + brand + Keys.ENTER);

        Thread.sleep(1000);

        js.executeScript("arguments[0].scrollIntoView(true);",MainPage.sizeDropDown);
        MainPage.sizeDropDown.click();
        MainPage.sizeDropDown.findElement(By.xpath("//button[text()="+ size +"]")).click();

    }

    @DataProvider
    public String[][]getData() throws IOException {

        return Util.getfileData();

    }

    @Test //Test blocks network request to check for website response
    public void errorTest()
    {
        Util.blockNetwork(driver);
        driver.navigate().refresh();

        try {
            driver.findElement(By.cssSelector("div[class=\"sc-v3qhy6-4 hSAiPt\"]"));
            Assert.assertEquals(driver.findElement(By.cssSelector("div[class=\"sc-v3qhy6-4 hSAiPt\"]"))
                    .getText(), "Ups, a apărut o mică eroare!");
        }catch (NoSuchElementException e)
        {
            Assert.fail("you are here");
        }
    }

}

