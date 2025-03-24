package com.e2eTests.common;

import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsWaitsHandler extends TestBase{

	   /**
     * Waits explicitly until the given element is visible on the page.
     * 
     * @param element the WebElement to wait for
     * @return the visible WebElement once it appears
     */
    public WebElement explicitVisibleWait(WebElement element) {
        // Create a WebDriverWait instance with a timeout of 30 seconds
        WebDriverWait wait = new WebDriverWait(TestBase.getDriver(), Duration.ofSeconds(30));
        // Wait until the specified element is visible and return it
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    
 // Method to get the text of the alert
 	public String getAlertText() {
 		return getDriver().switchTo().alert().getText();
 	}

}