package com.chowbotics.automation.chowapp.tests;

import org.testng.annotations.Test;

import com.chowbotics.automation.chowapp.base.BaseTest;
import com.chowbotics.automation.chowapp.pages.HomeScreen;
import com.chowbotics.automation.chowapp.pages.WalkThroughScreen;

public class HomeSuite extends BaseTest{

	WalkThroughScreen wts;
	HomeScreen hs;

	@Test
	public void verifyRobotIsPaired() {
		wts=new WalkThroughScreen(getDriver());
		hs=wts.verifyWelcomeText()
				.verifySkipIsDisplayed()
				.tapSkipCTA();
		hs.verifyHomeScreenText();
		hs.tapPair()
		  .enterRobotID("AAAAA");
		hs.verifyHeaders();
	}
	
	@Test
	public void verifyRobotPairCancel() {
		wts=new WalkThroughScreen(getDriver());
		hs=wts.verifyWelcomeText()
				.verifySkipIsDisplayed()
				.tapSkipCTA();
		hs.verifyHomeScreenText();
		hs.tapPair()
		  .tapClose();
		hs.verifyHomeScreenText();
	}
}
