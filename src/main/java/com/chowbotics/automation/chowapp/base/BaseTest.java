package com.chowbotics.automation.chowapp.base;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.chowbotics.automation.chowapp.utils.TestUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;;

public class BaseTest {

	protected static ThreadLocal<AppiumDriver> driver = new ThreadLocal<AppiumDriver>();
	TestUtils utils = new TestUtils();
	public static String platform;
	public static String dateTime;
	public static String deviceName;

	public AppiumDriver<MobileElement> getDriver() {
		return driver.get();
	}

	public void setDriver(AppiumDriver<MobileElement> driver2) {
		driver.set(driver2);
	}

	// Verify Appium is Running :
	@BeforeSuite
	public void verifyAppiumRunning() throws Exception {
		if (!checkIfAppiumServerIsRunnning(4723)) {
			utils.log().error("Appium server not running");
			System.exit(0);
		} else {
			utils.log().info("Appium server is running");
		}
	}

	public boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
		boolean isAppiumServerRunning = false;
		ServerSocket socket;
		try {
			socket = new ServerSocket(port);
			socket.close();
		} catch (IOException e) {
			isAppiumServerRunning = true;
		} finally {
			socket = null;
		}
		return isAppiumServerRunning;
	}

	// Initialize Appium Driver :
	@Parameters({ "platformName" })
	@BeforeTest
	public void setup(@Optional("Android") String platformName) throws Exception {
		Properties prop = new Properties();
		InputStream inputStream = null;
		AppiumDriver<MobileElement> driver;
		String propFileName = "config.properties";
		utils.log().info("load " + propFileName);
		platform = platformName;
		dateTime = utils.dateTime();
		try {
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			prop.load(inputStream);
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			URL url = new URL(prop.getProperty("appiumURL"));
			deviceName = prop.getProperty("deviceName");
			switch (platformName) {
			case "Android":
				desiredCapabilities.setCapability("automationName", prop.getProperty("androidAutomationName"));
				desiredCapabilities.setCapability("platformName", platformName);
				desiredCapabilities.setCapability("deviceName", prop.getProperty("deviceName"));
				desiredCapabilities.setCapability("udid", prop.getProperty("udid"));
				desiredCapabilities.setCapability("appPackage", prop.getProperty("androidAppPackage"));
				desiredCapabilities.setCapability("appActivity", prop.getProperty("androidAppActivity"));
				desiredCapabilities.setCapability("appWaitPackage", prop.getProperty("androidAppPackage"));
				desiredCapabilities.setCapability("appWaitActivity", prop.getProperty("androidAppActivity"));
				desiredCapabilities.setCapability("app", prop.getProperty("androidAppLocation"));
				desiredCapabilities.setCapability("fullReset", true);
				desiredCapabilities.setCapability("skipServerInstallation", true);
				desiredCapabilities.setCapability("systemPort", 10000);
				driver = new AndroidDriver<MobileElement>(url, desiredCapabilities);
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				break;
			case "iOS":
				// We will write capabilites for iOS here.
				driver = new IOSDriver<MobileElement>(url, desiredCapabilities);
				break;
			default:
				throw new Exception("Invalid platform! - " + platformName);
			}
			setDriver(driver);
			utils.log().info("driver initialized: " + driver);
		} catch (Exception e) {
			utils.log().fatal("driver initialization failure \n" + e.toString());
		} finally {
			inputStream.close();
		}
	}

	@BeforeMethod
	public void beforeMethod() {
		getDriver().resetApp();
	}

	@AfterSuite
	public void tearDown() {
		if (getDriver() != null)
			getDriver().quit();
	}
}
