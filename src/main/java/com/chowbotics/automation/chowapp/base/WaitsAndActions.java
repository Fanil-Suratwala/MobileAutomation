package com.chowbotics.automation.chowapp.base;

import java.time.Duration;

import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.chowbotics.automation.chowapp.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WaitsAndActions {

	AppiumDriver<MobileElement> driver;
	WebDriverWait wait;
	TouchActions action;
	TestUtils utils = new TestUtils();

	public WaitsAndActions(AppiumDriver<MobileElement> driver) {
		wait = new WebDriverWait(driver, 20);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(20)), this);
	}

	public void waitForVisibility(MobileElement e) {
		utils.log("waiting for visibility of element : " + e);
		wait.until(ExpectedConditions.visibilityOf(e));
	}

	public void clear(MobileElement e) {
		waitForVisibility(e);
		utils.log("clearing element : " + e);
		e.clear();
	}

	public void click(MobileElement e) {
		waitForVisibility(e);
		utils.log("clicking the element : " + e);
		e.click();
	}

	public void sendKeys(MobileElement e, String txt) {
		waitForVisibility(e);
		utils.log("entering text " + txt + " on element : " + e);
		e.sendKeys(txt);
	}
}
