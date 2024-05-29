package org.example;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.network.UrlPattern;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v125.fetch.Fetch;
import org.openqa.selenium.devtools.v125.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v125.network.model.ErrorReason;
import org.openqa.selenium.devtools.v125.network.model.ResourceType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class Util {
    WebDriver driver;
    public Util(WebDriver driver)
    {
        this.driver=driver;
    }
    public void loadWait( By findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public static String[][] getfileData() throws IOException {
        FileInputStream file = new FileInputStream("file.xlsx");
        XSSFWorkbook doc = new XSSFWorkbook(file);

        String [][] data = new String[doc.getSheetAt(0).getPhysicalNumberOfRows()][3];

        for(int i=0; i<doc.getSheetAt(0).getPhysicalNumberOfRows();i++)
        {
            Row row = doc.getSheetAt(0).getRow(i);

            for(int n=0; n<row.getPhysicalNumberOfCells();n++)
            {
                row.getCell(n).setCellType(CellType.STRING);
                data[i][n]= row.getCell(n).getStringCellValue();
            }
        }
        return data;
    }

    public static void blockNetwork(WebDriver webdriver){

        ChromeDriver driver = (ChromeDriver) webdriver;
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Optional<List<RequestPattern>> pattern = Optional.of(Arrays.asList(
                new RequestPattern(Optional.of("*assets*"),
                        Optional.of(ResourceType.valueOf("SCRIPT")),Optional.empty())));

        devTools.send(Fetch.enable(pattern,Optional.empty()));

        devTools.addListener(Fetch.requestPaused(), request ->{
            System.out.println(request.getRequest().getUrl());
            devTools.send(Fetch.failRequest(request.getRequestId(), ErrorReason.FAILED));
        });

    }
}
