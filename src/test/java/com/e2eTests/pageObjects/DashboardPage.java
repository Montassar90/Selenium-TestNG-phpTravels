package com.e2eTests.pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import com.e2eTests.common.TestBase;
import lombok.Getter;

@Getter // Lombok annotation to generate getters for all private fields in this class.
public class DashboardPage {

    // WebElement representing the "Users" card, located by its XPath.
    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'users') and span[@class='bg-dark']]")
    private WebElement usersCard;

    // WebElement representing the "Pages" card, located by its XPath.
    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'pages') and span[@class='bg-primary']]")
    private WebElement pagesCard;

    // WebElement representing the "Bookings" card, located by its XPath.
    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'bookings') and span[@class='bg-success']]")
    private WebElement bookingsCard;

    // WebElement representing the "Cancelled Bookings" card, located by its XPath.
    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'bookings') and span[@class='bg-danger']]")
    private WebElement cancelledBookingsCard;

    // WebElement representing the "Unpaid Bookings" card, located by its XPath.
    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'bookings') and span[@class='bg-warning']]")
    private WebElement unpaidBookingsCard;

    // WebElement representing the "Pending Transactions" card, located by its XPath.
    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'transactions') and span[@class='bg-warning']]")
    private WebElement pendingTransactionsCard;

    // WebElement for confirming the Users page loaded, identified using a CSS selector.
    @FindBy(how = How.CSS, using = ".m-0.page_title")
    private WebElement usersConfirm;
    
    // WebElement for confirming the Pages page loaded, identified using a CSS selector.
    @FindBy(how = How.CSS, using = ".m-0.page_title")
    private WebElement pagesConfirm;
    
    // WebElement for confirming the Bookings page loaded, identified using a CSS selector.
    @FindBy(how = How.CSS, using = ".m-0.page_title")
    private WebElement bookingsConfirm;
    
    // WebElement for confirming the Pending Transactions page loaded, identified using a CSS selector.
    @FindBy(how = How.CSS, using = ".m-0.page_title")
    private WebElement pendingTransactionsConfirm;
    
    // WebElement representing the dropdown for booking status, identified by its CSS selector.
    @FindBy(how = How.CSS, using = ".form-select.booking_status")
    private WebElement bookingStatusDropdown;

    // Constructor initializes web elements using the PageFactory and the WebDriver from TestBase.
    public DashboardPage() {
        PageFactory.initElements(TestBase.getDriver(), this);
    }

    // Clicks on the "Users" card using JavaScript execution to overcome potential click issues.
    public void clickOnUsersCard() {
        ((JavascriptExecutor) TestBase.getDriver()).executeScript("arguments[0].click();", usersCard);
    }

    // Clicks on the "Pages" card using JavaScript execution.
    public void clickOnPagesCard() {
        ((JavascriptExecutor) TestBase.getDriver()).executeScript("arguments[0].click();", pagesCard);
    }

    // Clicks on the "Bookings" card using JavaScript execution.
    public void clickOnBookingsCard() {
        ((JavascriptExecutor) TestBase.getDriver()).executeScript("arguments[0].click();", bookingsCard);
    }

    // Clicks on the "Cancelled Bookings" card using JavaScript execution.
    public void clickOnCancelledBookingsCard() {
        ((JavascriptExecutor) TestBase.getDriver()).executeScript("arguments[0].click();", cancelledBookingsCard);
    }

    // Clicks on the "Unpaid Bookings" card using JavaScript execution.
    public void clickOnUnpaidBookingsCard() {
        ((JavascriptExecutor) TestBase.getDriver()).executeScript("arguments[0].click();", unpaidBookingsCard);
    }

    // Clicks on the "Pending Transactions" card using JavaScript execution.
    public void clickOnPendingTransactionsCard() {
        ((JavascriptExecutor) TestBase.getDriver()).executeScript("arguments[0].click();", pendingTransactionsCard);
    }
    
    // Retrieves the text of the currently selected option from the booking status dropdown.
    public String getBookingStatus() {
        Select select = new Select(bookingStatusDropdown);
        return select.getFirstSelectedOption().getText();
    }
}
