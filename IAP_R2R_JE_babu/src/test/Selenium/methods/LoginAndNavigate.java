package methods;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;

import driver.GenericMethods2;
import pageObjects.LoginPageObjects;

public class LoginAndNavigate {
	static GenericMethods2 gm = new GenericMethods2();

	// Login Method
	public void login(WebDriver driver, String usrName, String usrPswd, String profileName, ExtentTest logger) {
		// Check for login Page
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switchToFrame(driver, "gsft_main", logger);
		if (gm.waitForObjectExistence(driver, LoginPageObjects.loginPage, "login Page", logger)) {
			gm.logInfo(logger, "login Page",driver);
			gm.enterText(driver, LoginPageObjects.userName_txt, usrName, "UserName Field", logger);
			gm.enterText(driver, LoginPageObjects.password_txt, usrPswd, "UserPassword Field", logger);
			gm.clickButton(driver, LoginPageObjects.LogIn_Btn, "Login", logger);
			if (gm.waitForObjectExistence(driver, LoginPageObjects.headerDisplayed, "Home Page Header", logger)) {
				gm.logPass(logger, "User navigated to Client Service Portal",driver);
				// check for profile Name
				if (gm.waitForObjectExistence(driver, LoginPageObjects.displayedUserName, "profile Name", logger)) {
					if (driver.findElement(LoginPageObjects.displayedUserName).getText().contains(profileName)) {

						gm.logPass(logger, "Correct profile name is displayed");
					} else
						gm.logFail(logger, "Incorrect profile name is displayed", driver);
				}

			} else
				gm.logFail(logger, "User not Navigated to Client Service Portal page On clicking Login Button", driver);

		} else
			gm.logFail(logger, "Login Page is not Displayed", driver);

		// JOptionPane.showMessageDialog(null, "Click on Ok");

	}

	/**
	 * Switching Frames
	 * 
	 * @param driver
	 *            - WebDriver
	 * @param frameName
	 *            - FrameName
	 */
	public void switchToFrame(WebDriver driver, String frameName, ExtentTest logger) {
		try {
			driver.switchTo().frame(frameName);
			gm.logInfo(logger, "Successfully switched to Frame. Frame Name :" + frameName);
		} catch (Exception e) {
			gm.logFail(logger, "Unable to switch Frames", driver);
		}
	}

	public void Navigate(WebDriver driver, String Menu, String SubMenu, ExtentTest logger) {

		try {

			// To check whether the main menu is exists in the page
			if (gm.waitForObjectExistence(driver, LoginPageObjects.MenuClient(Menu), Menu + " Menu", logger)) {
				gm.logInfo(logger, Menu + " Menu is displayed", driver);

				// check if SubMenu present
				if (!(SubMenu == "")) {
					Actions action = new Actions(driver);
					// get main Menu web element
					WebElement we = driver.findElement(LoginPageObjects.MenuClient(Menu));

					// mouse hover on the menu
					action.moveToElement(we).click().build().perform();
					gm.clickButton(driver, LoginPageObjects.SubMenuClient(SubMenu), SubMenu + " Sub Menu", logger);

					gm.logPass(logger, "Clicked on " + SubMenu + " Menu", driver);
				} else {
					// To click on Main Menu
					gm.clickButton(driver, LoginPageObjects.MenuClient(Menu), Menu + " Menu", logger);
					gm.logPass(logger, "Clicked on " + Menu + " Menu", driver);
				}

			} else
				gm.logFail(logger, Menu + " Menu is not displayed");

		} catch (Exception e) {
			gm.logFail(logger, e.getMessage());
		}

	}

	// logout and re login
	public void logOutAndRelogin(WebDriver driver, String UserName, String Password, ExtentTest logger) throws InterruptedException {
		// move over on UserName
		driver.switchTo().defaultContent();
		if(gm.waitForObjectExistence(driver, LoginPageObjects.displayedUserName, "User Name",logger))
		{
			
			gm.mouseHover(driver, LoginPageObjects.displayedUserName, "User Name", logger);
			Thread.sleep(2000);
			//Double click is required here
				gm.clickButton(driver, LoginPageObjects.displayedUserName, "User Name", logger);
				gm.clickButton(driver, LoginPageObjects.displayedUserName, "User Name", logger);

				Thread.sleep(5000);
		}
		

		if (gm.waitForObjectExistence(driver, LoginPageObjects.LogOut_Btn, "LogOut Button", logger)) {

			
	
			Thread.sleep(5000);
			gm.clickButton(driver, LoginPageObjects.LogOut_Btn, "LogOut Button", logger);
			
			
				Thread.sleep(2000);
	
			//JOptionPane.showMessageDialog(null, "click on logout Button");
			
			switchToFrame(driver, "gsft_main", logger);
			if (gm.waitForObjectExistence(driver, LoginPageObjects.LogIn_Btn, "LogIn Button", logger)) {
				gm.logInfo(logger, "user is succesfully Logged Out",driver);
				// To Enter the user name and password in the login screen
				gm.enterText(driver, LoginPageObjects.userName_txt, UserName, "User Name", logger);
				gm.enterText(driver, LoginPageObjects.password_txt, Password, "Password", logger);

				// Click on the Login Button
				gm.clickButton(driver, LoginPageObjects.LogIn_Btn, "LogIn Button", logger);
				if (gm.waitForObjectExistence(driver, LoginPageObjects.displayedUserName, "UserName", logger)) {
					gm.logPass(logger, "User sucessfully logged in");
				} else
					gm.logFail(logger, "User not sucessfully logged in", driver);

			} else
				gm.logFail(logger, "user unable to logOut", driver);
		} else
			gm.logFail(logger, "LogOut Button is not presnt", driver);

	}

}
