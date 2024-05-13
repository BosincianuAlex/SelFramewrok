package org.example;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;


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
    public void loadWait( WebElement findBy)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
    }

    public static void getDoc() throws IOException {
        FileInputStream file = new FileInputStream("file.xlsx");
        XSSFWorkbook doc = new XSSFWorkbook(file);
        //String a, b, c = doc.getSheetAt(0).getRow(1).cellIterator();
        System.out.println(doc.getNumberOfSheets());
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
}
