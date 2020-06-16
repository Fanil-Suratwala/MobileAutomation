package com.chowbotics.automation.chowapp.tests;

import org.testng.annotations.Test;

import com.chowbotics.automation.chowapp.base.BaseTest;
import com.chowbotics.automation.chowapp.pages.HomeScreen;
import com.chowbotics.automation.chowapp.pages.WalkThroughScreen;

public class WalkThroughSuite extends BaseTest{

	WalkThroughScreen wts;
	HomeScreen hs;

	@Test
	public void verifySkipFunctionlity() {
		wts=new WalkThroughScreen(getDriver());
		hs=wts.verifyWelcomeText()
				.verifySkipIsDisplayed()
				.tapSkipCTA();
		hs.verifyHomeScreenText();
	}
	
	@Test
	public void verifyNextFunctionlity() {
		wts=new WalkThroughScreen(getDriver());
		hs=wts.verifyWelcomeText()
				.tapNextCTA()
				.tapNextCTA()
				.tapNextCTA()
				.tapNextCTA()
				.tapLetsStartCTA();
		hs.verifyHomeScreenText();
	}
}
