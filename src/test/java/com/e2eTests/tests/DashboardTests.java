package com.e2eTests.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.e2eTests.common.AlertsWaitsHandler;
import com.e2eTests.common.TestBase;
import com.e2eTests.pageObjects.DashboardPage;
import com.e2eTests.utils.ConfigFileReader;

// Attach the ChainTestListener to this test class
@Listeners(ChainTestListener.class)
public class DashboardTests extends TestBase {

	// Declare an instance of the DashboardPage page object
	private DashboardPage dashboardPage;
	// Create an instance to read configuration properties from file
	private ConfigFileReader configFileReader = new ConfigFileReader();
	// Create an instance to handle explicit waits and alerts
	private AlertsWaitsHandler alertsWaitsHandler = new AlertsWaitsHandler();

	// Test method to verify the Users Card functionality on the Dashboard
	@Test()
	public void usersCardTest() {
		// Instantiate the DashboardPage object
		dashboardPage = new DashboardPage();
		// Wait explicitly until the Users Card element is visible
		alertsWaitsHandler.explicitVisibleWait(dashboardPage.getUsersCard());
		// Assert that the Users Card is displayed on the page
		assertTrue(dashboardPage.getUsersCard().isDisplayed());

		// Try to click on the Users Card, catch any exception if the card is not
		// clickable
		try {
			dashboardPage.clickOnUsersCard();
		} catch (Exception e) {
			System.out.println("usersCard is not clickable " + e);
		}
		// Assert that the confirmation text after clicking the card matches the
		// expected value from configuration
		assertEquals(configFileReader.getProperties("expectedUsersConfirm"), dashboardPage.getPageTitle().getText());
	}

	// Test method to verify the Pages Card functionality on the Dashboard
	@Test()
	public void pagesCardTest() {
		dashboardPage = new DashboardPage();
		alertsWaitsHandler.explicitVisibleWait(dashboardPage.getPagesCard());
		assertTrue(dashboardPage.getPagesCard().isDisplayed());
		try {
			dashboardPage.clickOnPagesCard();
		} catch (Exception e) {
			System.out.println("pagesCard is not clickable " + e);
		}
		// Verify that the confirmation text for the Pages Card matches the expected
		// value from configuration
		assertEquals(configFileReader.getProperties("expectedPagesConfirm"), dashboardPage.getPageTitle().getText());
	}

	// Test method to verify the Bookings Card functionality on the Dashboard
	@Test()
	public void bookingsCardTest() {
		dashboardPage = new DashboardPage();
		alertsWaitsHandler.explicitVisibleWait(dashboardPage.getBookingsCard());
		assertTrue(dashboardPage.getBookingsCard().isDisplayed());
		try {
			dashboardPage.clickOnBookingsCard();
		} catch (Exception e) {
			System.out.println("bookingsCard is not clickable " + e);
		}
		// Check if the confirmation text for Bookings Card is as expected based on the
		// configuration
		assertEquals(configFileReader.getProperties("expectedBookingsConfirm"),
				dashboardPage.getPageTitle().getText());
	}

	// Test method to verify the Cancelled Bookings Card functionality on the
	// Dashboard
	@Test()
	public void cancelledBookingsCardTest() {
		dashboardPage = new DashboardPage();
		alertsWaitsHandler.explicitVisibleWait(dashboardPage.getCancelledBookingsCard());
		assertTrue(dashboardPage.getCancelledBookingsCard().isDisplayed());
		try {
			dashboardPage.clickOnCancelledBookingsCard();
		} catch (Exception e) {
			System.out.println("cancelledBookingsCard is not clickable " + e);
		}
		// Assert that the booking status after clicking matches the expected
		// configuration value for cancelled bookings
		assertEquals(configFileReader.getProperties("expectedCancelledBookingsConfirm"),
				dashboardPage.getBookingStatus());
	}

	// Test method to verify the Unpaid Bookings Card functionality on the Dashboard
	@Test()
	public void unpaidBookingsCardTest() {
		dashboardPage = new DashboardPage();
		alertsWaitsHandler.explicitVisibleWait(dashboardPage.getUnpaidBookingsCard());
		assertTrue(dashboardPage.getUnpaidBookingsCard().isDisplayed());
		try {
			dashboardPage.clickOnUnpaidBookingsCard();
		} catch (Exception e) {
			System.out.println("unpaidBookingsCard is not clickable " + e);
		}
		// Validate that the payment status text after clicking the Unpaid Bookings Card
		// matches the expected value
		assertEquals(configFileReader.getProperties("expectedUnpaidBookingsConfim"), dashboardPage.getPaymentStatus());
	}
	
	// Test method to verify the Pending Transactions Card functionality on the Dashboard
		@Test()
		public void pendingTransactionsCardTest() {
			dashboardPage = new DashboardPage();
			alertsWaitsHandler.explicitVisibleWait(dashboardPage.getPendingTransactionsCard());
			assertTrue(dashboardPage.getPendingTransactionsCard().isDisplayed());
			try {
				dashboardPage.clickOnPendingTransactionsCard();
			} catch (Exception e) {
				System.out.println("pendingTransactionsCard is not clickable " + e);
			}
			// Check if the confirmation text for Pending Transactions Card is as expected based on the
			// configuration
			assertEquals(configFileReader.getProperties("expectedTransactionsconfirm"),
					dashboardPage.getPageTitle().getText());
		}

}
