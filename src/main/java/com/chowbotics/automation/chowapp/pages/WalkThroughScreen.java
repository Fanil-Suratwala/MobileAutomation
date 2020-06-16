package com.chowbotics.automation.chowapp.pages;

import org.openqa.selenium.support.PageFactory;

import com.chowbotics.automation.chowapp.base.WaitsAndActions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class WalkThroughScreen extends WaitsAndActions{

	AppiumDriver<MobileElement> driver;
	
	@AndroidFindBy(xpath = "//*[@text='Skip']")
	public MobileElement skipCTA;
	
	@AndroidFindBy(xpath="//*[@text='Welcome to ChowApp']")
	public MobileElement welcomeText;

	@AndroidFindBy(xpath="//*[@text='Experience the salad bar reinvented']")
	public MobileElement headerText;
	
	@AndroidFindBy(xpath="//*[@text='NEXT']")
	public MobileElement nextCTA;
	
	@AndroidFindBy(xpath="//*[@text='LET\'S START']")
	public MobileElement letsStartCTA;

	public WalkThroughScreen(AppiumDriver<MobileElement> driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	 
	public WalkThroughScreen verifyWelcomeText() {
		waitForVisibility(welcomeText);
		return this;
	}
	
	public WalkThroughScreen tapNextCTA() {
		click(nextCTA);
		return this;
	}
	
	public WalkThroughScreen verifySkipIsDisplayed() {	
		waitForVisibility(skipCTA);		
		return this;
	}
		
	public HomeScreen tapSkipCTA() {
		click(skipCTA);
		return new HomeScreen(driver);
	}

	public HomeScreen tapLetsStartCTA() {
		click(letsStartCTA);
		return new HomeScreen(driver);
	}

}
