package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class MainPage extends Util {
    WebDriver driver;
     public MainPage(WebDriver driver){
         super(driver);
         this.driver=driver;
         PageFactory.initElements(driver, this);
     }
     @FindBy(css="li[class='sc-oelsaz-0 YkKBp']")
     static List<WebElement> productCatalogue, favoriteList;
     @FindBy(css="input[data-testid='searchBarInput']")
     WebElement searchBar;
     @FindBy(xpath = "//div[@class='sc-oal5i0-0 eSnLEj'][@data-testid='filterDropdownSizeGrouped']")
     WebElement sizeDropDown;
     By productTitle = By.cssSelector("p[data-testid='brandName']");
     static By favoriteItems = By.cssSelector("li[class='sc-oelsaz-0 YkKBp']");
     public void clickFavoritelist()
     {
         driver.findElement(By.cssSelector("button[data-testid='Wishlist']")).click();
     }
     public List<String> getProductNames(List<WebElement> webList)
    {
        return webList.stream().limit(4).map(i->i.findElement(productTitle).getText()).collect(Collectors.toList());
    }

}
