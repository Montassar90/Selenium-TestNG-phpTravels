package com.e2eTests.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.e2eTests.pageObjects.LoginPage;
import com.e2eTests.utils.ConfigFileReader;


public class TestBase {
	// A ConfigFileReader instance to read the configuration properties
	   ConfigFileReader configFileReader = new ConfigFileReader();
	// A static WebDriver instance to be shared among tests
	private static WebDriver driver;

	// This method will be executed before each test method
	@BeforeMethod
	public void setup() {
		// Retrieve the browser type from system properties. Default to "chrome" if not
		// provided.
		String browser = System.getProperty("browser");
		if (browser == null) {
			browser = "chrome";
		}

		// Switch between different browsers based on the provided system property value
		switch (browser) {
		case "chrome":
			// Initialize ChromeDriver and open the admin URL
			driver = new ChromeDriver();
			driver.get(configFileReader.getProperties("baseUrl"));
			driver.manage().window().maximize(); // Maximize the browser window
			break;

		case "firefox":
			// Initialize FirefoxDriver and open the admin URL
			driver = new FirefoxDriver();
			driver.get("https://phptravels.net/admin");
			driver.manage().window().maximize(); // Maximize the browser window
			break;

		case "edge":
			// Initialize EdgeDriver and open the admin URL
			driver = new EdgeDriver();
			driver.get("https://phptravels.net/admin");
			driver.manage().window().maximize(); // Maximize the browser window
			break;

		default:
			// Throw an exception if the provided browser is not supported
			throw new IllegalArgumentException("Browser \"" + browser + "\" is not supported");
		}
	}
	// Default login logic for most test cases
	   @BeforeMethod(dependsOnMethods = "setup")
	   public void login() {
		   LoginPage loginpage = new LoginPage();
		    loginpage.performLogin(configFileReader.getProperties("validAdminEmail"), 
		    		configFileReader.getProperties("validAdminPassword"));
		}

	// This method will be executed after each test method to clean up
	@AfterMethod
	public void tearDown() {
		// Close the browser and quit the driver
		driver.quit();
	}

	// Getter method to retrieve the WebDriver instance for use in tests
	public static WebDriver getDriver() {
		return driver;
	}
}
