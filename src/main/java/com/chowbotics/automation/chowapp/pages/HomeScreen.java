package com.chowbotics.automation.chowapp.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.chowbotics.automation.chowapp.base.WaitsAndActions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class HomeScreen extends WaitsAndActions {

	AppiumDriver<MobileElement> driver;

	@AndroidFindBy(xpath = "//*[@text='Welcome to #FreshForAll']")
	@iOSXCUITFindBy(xpath = "//*text")
	public MobileElement homeScreenWelcome;

	@AndroidFindBy(xpath = "//*[@text='PAIR']")
	public MobileElement pairCTA;

	@AndroidFindBy(xpath = "//android.widget.EditText")
	public MobileElement robotTextBox;

	@AndroidFindBy(xpath = "//*[@text='Salad Bowls']")
	public MobileElement saladBowl;

	@AndroidFindBy(xpath = "//*[@text='Grain Bowls']")
	public MobileElement grainBowl;

	@AndroidFindBy(xpath = "//*[@text='Cereal Bowls']")
	public MobileElement cerealBowl;

	@AndroidFindBy(xpath = "//android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup")
	public MobileElement crossCTA;

	public HomeScreen(AppiumDriver<MobileElement> driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	public HomeScreen verifyHomeScreenText() {
		waitForVisibility(homeScreenWelcome);
		Assert.assertEquals(homeScreenWelcome.getText(), "Welcome to #FreshForAll");
		return this;
	}

	public HomeScreen tapPair() {
		click(pairCTA);
		return this;
	}

	public HomeScreen enterRobotID(String id) {
		sendKeys(robotTextBox, id);
		return this;
	}

	public HomeScreen verifyHeaders() {
		Assert.assertTrue(saladBowl.isDisplayed());
		Assert.assertTrue(grainBowl.isDisplayed());
		Assert.assertTrue(cerealBowl.isDisplayed());
		return this;
	}

	public HomeScreen tapClose() {
		click(crossCTA);
		return this;
	}
}
