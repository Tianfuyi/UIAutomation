package com.zetyun.uitest.utility;

import com.zetyun.driver.log.LogWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ElementUtil {

    private static boolean isSelectorExpression(String expression) {
        if (expression.isEmpty()) {
            return false;
        }
        if (expression.startsWith("x:") ||
                expression.startsWith("i:") ||
                expression.startsWith("c:") ||
                expression.startsWith("n:")) {
            return true;
        } else {
            return false;
        }
    }

    public static WebElement findElement(WebDriver driver, String expression) {
        if (!isSelectorExpression(expression)) {
            return null;
        }

        WebElement element = null;

        String[] cons = expression.split(":", 2);

        try {
            switch (cons[0]) {

                case "i":
                    ToolKit.waitForWebElement(driver, By.id(cons[1]));
                    element = driver.findElement(By.id(cons[1]));
                    break;
                case "c":
                    ToolKit.waitForWebElement(driver, By.className(cons[1]));
                    element = driver.findElement(By.className(cons[1]));
                    break;
                case "x":
                    ToolKit.waitForWebElement(driver, By.xpath(cons[1]));
                    element = driver.findElement(By.xpath(cons[1]));
                    break;
                case "n":
                    ToolKit.waitForWebElement(driver, By.name(cons[1]));
                    element = driver.findElement(By.name(cons[1]));
                    break;
            }

            return element;
        } catch (Exception e) {
            LogWriter.error(ElementUtil.class, "元素 " + expression + "查询失败");
            return null;
        }
    }
    public static WebElement findElement(WebDriver driver, String expression,int second) {
        if (!isSelectorExpression(expression)) {
            return null;
        }

        WebElement element = null;

        String[] cons = expression.split(":", 2);

        try {
            switch (cons[0]) {

                case "i":


                    new WebDriverWait(driver, second).until(ExpectedConditions.presenceOfElementLocated(By.id(cons[1]))); // e.g. //
                    element = driver.findElement(By.id(cons[1]));
                    break;
                case "c":
                    new WebDriverWait(driver, second).until(ExpectedConditions.presenceOfElementLocated(By.className(cons[1])));
                    element = driver.findElement(By.className(cons[1]));
                    break;
                case "x":
                    new WebDriverWait(driver, second).until(ExpectedConditions.presenceOfElementLocated(By.xpath(cons[1]))); // e.g. //
                    element = driver.findElement(By.xpath(cons[1]));
                    break;
                case "n":
                    new WebDriverWait(driver, second).until(ExpectedConditions.presenceOfElementLocated(By.name(cons[1])));
                    element = driver.findElement(By.name(cons[1]));
                    break;
            }

            return element;
        } catch (Exception e) {
            LogWriter.error(ElementUtil.class, "元素 " + expression + "查询失败");
            return null;
        }
    }

    public static List<WebElement> findElements(WebDriver driver, String expression,int second) {
        if (!isSelectorExpression(expression)) {
            return null;
        }

        String[] cons = expression.split(":");
        List<WebElement> list = null;
        try {
            switch (cons[0]) {

                case "i":
                    new WebDriverWait(driver, second).until(ExpectedConditions.presenceOfElementLocated(By.id(cons[1])));

                    list = driver.findElements(By.id(cons[1]));
                    break;
                case "c":
                    new WebDriverWait(driver, second).until(ExpectedConditions.presenceOfElementLocated(By.className(cons[1])));
                    list = driver.findElements(By.className(cons[1]));
                    break;
                case "x":
                    new WebDriverWait(driver, second).until(ExpectedConditions.presenceOfElementLocated(By.xpath(cons[1])));
                    list = driver.findElements(By.xpath(cons[1]));

                    break;
                case "n":
                    new WebDriverWait(driver, second).until(ExpectedConditions.presenceOfElementLocated(By.name(cons[1])));
                    list = driver.findElements(By.name(cons[1]));

                    break;
            }
            return list;
        } catch (Exception e) {
            LogWriter.error(ElementUtil.class, "元素 " + expression + "查询失败");
            return null;
        }

    }

    public static List<WebElement> findElements(WebDriver driver, String expression) {
        if (!isSelectorExpression(expression)) {
            return null;
        }

        String[] cons = expression.split(":");
        List<WebElement> list = null;
        try {
            switch (cons[0]) {

                case "i":
                    ToolKit.waitForWebElement(driver, By.id(cons[1]));
                    list = driver.findElements(By.id(cons[1]));
                    break;
                case "c":
                    ToolKit.waitForWebElement(driver, By.className(cons[1]));
                    list = driver.findElements(By.className(cons[1]));
                    break;
                case "x":
                    ToolKit.waitForWebElement(driver, By.xpath(cons[1]));
                    list = driver.findElements(By.xpath(cons[1]));

                    break;
                case "n":
                    ToolKit.waitForWebElement(driver, By.name(cons[1]));
                    list = driver.findElements(By.name(cons[1]));

                    break;
            }
            return list;
        } catch (Exception e) {
            LogWriter.error(ElementUtil.class, "元素 " + expression + "查询失败");
            return null;
        }

    }
}
