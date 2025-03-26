package com.e2eTests.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.e2eTests.common.TestBase;

public class ScreenshotsHandler extends TestBase{
	
	public void takeScreenshot(String testName) throws IOException {
		
		Date currentDate = new Date();
		String screenshotFileName = currentDate.toString().replace(" ", "-").replace(":", "-");
		File screenshotFile = ((TakesScreenshot) TestBase.getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File( ".//screenshot//"+ testName + screenshotFileName + ".png"));
		
	}
	
	public void embedScreenshot() {
		
		final byte[] reportScreenshot = ((TakesScreenshot) TestBase.getDriver()).getScreenshotAs(OutputType.BYTES);
		ChainTestListener.embed(reportScreenshot, "image/png");
	}

}
