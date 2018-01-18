package methods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;

import driver.GenericMethods2;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;
import pageObjects.OutOffOfficePageObjects;

public class OutOffOffice {
	static GenericMethods2 gm = new GenericMethods2();
	LoginAndNavigate login = new LoginAndNavigate();

	public void addDelegate(WebDriver driver,String Menu,String SubMenu, String delegate, ExtentTest logger) throws InterruptedException {
		
		Actions action = new Actions(driver);
		// get main Menu web element
		WebElement we = driver.findElement(LoginPageObjects.MenuClient(Menu));

		// mouse hover on the menu
		action.moveToElement(we).click().build().perform();
		gm.clickButton(driver, LoginPageObjects.SubMenuClient(SubMenu), SubMenu + " Sub Menu", logger);
		// switch to frame
		login.switchToFrame(driver, "frame1", logger);
		// check for New Button
		if (gm.waitForObjectExistence(driver, OutOffOfficePageObjects.btnNew, "New Button", logger)) {
			gm.clickButton(driver, OutOffOfficePageObjects.btnNew, "New Button", logger);
			gm.enterText(driver, OutOffOfficePageObjects.fieldDelegate, delegate, "Delegate TextBox", logger);
			// select end date
			gm.clickButton(driver, OutOffOfficePageObjects.endDatePicker, "endDate Picker", logger);
			Thread.sleep(2000);
			gm.clickButton(driver, OutOffOfficePageObjects.goToTodayLink, "Go To Today", logger);
			// double click on Tomorrow's date
			gm.clickButton(driver, OutOffOfficePageObjects.tommorowDate, "tomorrowDate", logger);
			gm.clickButton(driver, OutOffOfficePageObjects.tommorowDate, "tomorrowDate", logger);

			gm.clickButton(driver, OutOffOfficePageObjects.btnSubmit, "Submit", logger);
			// after creation check for delegate in the result
			if (gm.waitForObjectExistence(driver, OutOffOfficePageObjects.delegateName(delegate), delegate, logger)) {
				gm.logPass(logger, delegate + " is created and it is present Delegate result section",driver);
			}

		}
		// switch to default frame
		driver.switchTo().defaultContent();
	}

	// to search RIT Number under Pending Response/Pending Approval
	public void searchRITNum(WebDriver driver, String subTab, String RITNum, ExtentTest logger) {
		// click on Home menu
		if (gm.waitForObjectExistence(driver, HomePageObjects.MenuClient("HOME"), "HOME menu", logger)) {
			gm.clickLink(driver, HomePageObjects.MenuClient("HOME"), "HOME menu", logger);
			// click on Pending Response/Pending Approval
			gm.clickLink(driver, OutOffOfficePageObjects.subSectionTab(subTab), subTab, logger);

			// check for RIT Number
			if (gm.waitForObjectExistence(driver, OutOffOfficePageObjects.RITNum(RITNum), RITNum, logger)) {
				gm.logPass(logger, RITNum + " RIT Number is displayed under Pending Response",driver);
			} else
				gm.logFail(logger, RITNum + " RIT Number is not displayed under Pending Response");

		}

	}

}
