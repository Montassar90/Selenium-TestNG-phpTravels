package com.e2eTests.tests;

// Import the static assertEquals method from TestNG for assertions in tests
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.e2eTests.common.AlertsWaitsHandler;
import com.e2eTests.common.TestBase;
import com.e2eTests.pageObjects.LoginPage;
import com.e2eTests.utils.ConfigFileReader;

@Listeners(ChainTestListener.class)
// This class contains tests for login functionality and extends the common test base
public class LoginTests extends TestBase {

	// Declare an instance of LoginPage to interact with login elements
	private LoginPage loginPage;
	// Instance of ConfigFileReader to read configuration properties (credentials, expected messages)
	private ConfigFileReader configFileReader = new ConfigFileReader();
	// Instance of AlertsWaitsHandler to handle waiting for alerts and element
	// visibility
	private AlertsWaitsHandler alertsWaitsHandler = new AlertsWaitsHandler();

	// Override the login method from TestBase to prevent automatic login execution
	// before each test
	@BeforeMethod
	@Override
	public void login() {
	}

	// Test case for a valid login scenario
	@Test
	public void validLoginTest() {
		// Create a new instance of the LoginPage
		loginPage = new LoginPage();
		// Perform login using valid admin credentials retrieved from the configuration
		// file
		loginPage.performLogin(configFileReader.getProperties("validAdminEmail"),
				configFileReader.getProperties("validAdminPassword"));
		// Wait explicitly for the admin success message element to become visible
		alertsWaitsHandler.explicitVisibleWait(loginPage.getAdminSuccesMsg());
		// Verify that the displayed success message matches the expected message from
		// the configuration file
		assertEquals(loginPage.getAdminSuccesMsg().getText(),
				configFileReader.getProperties("loginExpectedSuccessMsg"));
	}

	// Test case for an invalid login scenario (wrong password)
	@Test
	public void invalidLoginTest() {
		// Create a new instance of the LoginPage
		loginPage = new LoginPage();
		// Attempt login with a valid email and an invalid password from configuration
		loginPage.performLogin(configFileReader.getProperties("validAdminEmail"),
				configFileReader.getProperties("invalidAdminPassword"));
		// Wait explicitly for the error message element to become visible
		alertsWaitsHandler.explicitVisibleWait(loginPage.getErrorMsg());
		// Verify that the displayed error message matches the expected error message
		// from configuration
		assertEquals(loginPage.getErrorMsg().getText(), configFileReader.getProperties("loginExpectedErrorMsg"));
	}

	// Test case to verify login behavior when the password field is left empty
	@Test
	public void emptyPasswordLoginTest() {
		// Create a new instance of the LoginPage
		loginPage = new LoginPage();
		// Perform login using a valid email but an empty string for the password
		loginPage.performLogin(configFileReader.getProperties("validAdminEmail"), "");
		// Retrieve the text from the alert that pops up when the password is empty
		String alertActualMsg = alertsWaitsHandler.getAlertText();
		// Verify that the alert text matches the expected error message for an empty
		// password from configuration
		assertEquals(alertActualMsg, configFileReader.getProperties("emptyPwExpectedErrMsg"));
	}

	// Test case to verify that language change functionality works during login
	@Test
	public void changeLanguageTest() {
		// Create a new instance of the LoginPage
		loginPage = new LoginPage();
		// Change the language setting on the login page
		loginPage.selectLanguage();
		// Perform login using valid admin credentials after the language change
		loginPage.performLogin(configFileReader.getProperties("validAdminEmail"),
				configFileReader.getProperties("validAdminPassword"));
		// Verify that the confirmation message for the language change matches the
		// expected message from configuration
		alertsWaitsHandler.explicitVisibleWait(loginPage.getLangChangeConfirmMsg());
		assertEquals(loginPage.getLangChangeConfirmMsg().getText(),
				configFileReader.getProperties("langChangeExpectedMsg"));
	}

}
