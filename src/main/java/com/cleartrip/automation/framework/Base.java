package com.cleartrip.automation.framework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Base extends TestSupport {

    public void enterText(By locator, String value) {
        System.out.println("locating element with locator " + locator.toString() + " to enter text");
        scrollToView(locator);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }

    public void click(By locator) {
        System.out.println("locating element with locator " + locator.toString() + " to click");
        scrollToView(locator);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    public void scrollToView(By locator) {
        System.out.println("locating element with locator " + locator.toString() + " to scroll");
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        executor.executeScript("arguments[0].scrollIntoView(true);", element);
        System.out.println("element with locator " + locator + " is scrolled to view");
    }

    public void selectCheckbox(By locator) {
        System.out.println("locating checkbox element with locator " + locator.toString());
        scrollToView(locator);
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public String getPageState() {
        System.out.println("Getting the page state");
        return executor.executeScript("return document.readyState").toString();
    }

    public void waitTillPageLoad() {
        ExpectedCondition<Boolean> jsLoad = driver -> getPageState().equals("complete");
        if (!getPageState().equals("complete")) {
            wait.until(jsLoad);
        }
    }
}
