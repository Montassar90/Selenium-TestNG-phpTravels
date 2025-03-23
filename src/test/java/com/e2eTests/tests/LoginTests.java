package com.e2eTests.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.e2eTests.common.AlertsWaitsHandler;
import com.e2eTests.common.TestBase;
import com.e2eTests.pageObjects.LoginPage;
import com.e2eTests.utils.ConfigFileReader;

public class LoginTests extends TestBase {

	private LoginPage loginPage;
	private ConfigFileReader configFileReader = new ConfigFileReader();
	private AlertsWaitsHandler alertsWaitsHandler = new AlertsWaitsHandler();	
	@Test
	public void validLoginTest() {
		loginPage = new LoginPage();
		loginPage.performLogin(configFileReader.getProperties("validAdminEmail"),
				configFileReader.getProperties("validAdminPassword"));
		alertsWaitsHandler.explicitVisibleWait(loginPage.getAdminSuccesMsg());
		assertEquals(loginPage.getAdminSuccesMsg().getText(), configFileReader.getProperties("loginExpectedSuccessMsg"));
	}
	
	@Test
	public void invalidLoginTest()  {
		loginPage = new LoginPage();
		loginPage.performLogin(configFileReader.getProperties("validAdminEmail"),
				configFileReader.getProperties("invalidAdminPassword"));
		alertsWaitsHandler.explicitVisibleWait(loginPage.getErrorMsg());
		assertEquals(loginPage.getErrorMsg().getText(), configFileReader.getProperties("loginExpectedErrorMsg"));
	}
	
	@Test
	public void emptyPasswordLoginTest()  {
		loginPage = new LoginPage();
		loginPage.performLogin(configFileReader.getProperties("validAdminEmail"),"");
		String alertActualMsg = alertsWaitsHandler.getAlertText();
		assertEquals(alertActualMsg, configFileReader.getProperties("emptyPwExpectedErrMsg"));
	}
	
	@Test
	public void changeLanguageTest() {
		loginPage = new LoginPage();
		loginPage.selectLanguage();
		loginPage.performLogin(configFileReader.getProperties("validAdminEmail"),
				configFileReader.getProperties("validAdminPassword"));
		assertEquals(loginPage.getLangChangeConfirmMsg().getText(), configFileReader.getProperties("langChangeExpectedMsg"));
	}
	
}
