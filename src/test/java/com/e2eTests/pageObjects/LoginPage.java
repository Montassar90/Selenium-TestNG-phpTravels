package com.e2eTests.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.e2eTests.common.TestBase;
import lombok.Getter;

@Getter // Lombok annotation that automatically creates getter methods for each private field
public class LoginPage {

    // Define a WebElement for the email input field, located by its id attribute "email"
    @FindBy(how = How.ID, using = "email")
    private WebElement emailInput;

    // Define a WebElement for the password input field, located by its id attribute "password"
    @FindBy(how = How.ID, using = "password")
    private WebElement pwInput;

    // Define a WebElement for the login button, located using a CSS selector with id "submit"
    @FindBy(how = How.CSS, using = "#submit")
    private WebElement loginBtn;
    
    // Define a WebElement for the element that shows a success message for admin login, located by a CSS selector
    @FindBy(how = How.CSS, using = "#dropdownUser1")
    private WebElement adminSuccesMsg;
    
    // Define a WebElement for the error message element, located by an XPath expression 
    @FindBy(how = How.XPATH, using = "//div[@class='text-group']/h4")
    private WebElement errorMsg;
    
    // Define a WebElement for the language selection button, located by a CSS selector identifying it with a title attribute "English"
    @FindBy(how = How.CSS, using = "button[title='English']")
    private WebElement selectLangBtn;
    
    // Define a WebElement for the French language option in the dropdown, located by a CSS selector with id "#bs-select-1-4"
    @FindBy(how = How.CSS, using = "#bs-select-1-4")
    private WebElement FrLangOption;
    
    // Define a WebElement for the confirmation message after changing the language, located by an XPath expression targeting a span element
    @FindBy(how = How.XPATH, using = "//span[@class='fw-semibold']")
    private WebElement langChangeConfirmMsg;

    // Constructor for the LoginPage class. It initializes all the WebElements using Selenium's PageFactory.
    public LoginPage() {
        // Initializes the WebElements with the driver provided by TestBase
        PageFactory.initElements(TestBase.getDriver(), this);
    }

    // Method to perform the login action using the provided email and password.
    public void performLogin(String email, String password) {
        // Enter the email into the email input field
        emailInput.sendKeys(email); 
        // Enter the password into the password input field
        pwInput.sendKeys(password); 
        // Click on the login button to attempt login
        loginBtn.click(); 
    }
    
    // Method to change the language of the application
    public void selectLanguage() {
        // Click on the button to open the language selection options
        selectLangBtn.click();
        // Click on the French language option to change the language to French
        FrLangOption.click();
    }
}
