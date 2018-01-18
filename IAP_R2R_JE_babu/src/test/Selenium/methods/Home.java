package methods;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;

import driver.GenericMethods2;
import pageObjects.HomePageObjects;
import pageObjects.LoginPageObjects;

public class Home {
	static GenericMethods2 gm = new GenericMethods2();

	// Home Page Verification
	public void homePageVerification(WebDriver driver, String menus, String subMenus, String subSectionMenus,
			ExtentTest logger) throws InterruptedException {
		if (gm.waitForObjectExistence(driver, HomePageObjects.headerDisplayed, "Home Page Header", logger)) {
			gm.logPass(logger, "Client Service Portal Header is displayed in the Home Page");
			// check for Home Page menus
			if (menus != "") {
				String[] str = menus.split(",");
				// get list of menus present in the Home Page
				List<WebElement> homeMenusList = driver.findElements(HomePageObjects.menusClient);
				int menus_count = homeMenusList.size();
				for (int i = 0; i < str.length; i++) {
					int flag = 0;
					for (int col = 0; col < menus_count; col++) {
						// check for menu matches with the menus present in the
						// list
						if (homeMenusList.get(col).getText().equals(str[i])) {

							gm.logPass(logger, str[i] + " Menu found in the Home Page");
							flag = 1;
							break;

						}

					}
					if (!(flag == 1)) {
						gm.logFail(logger, str[i] + " Menu is not found in the Home Page", driver);
					}

				}

			}
			// check for Sub Menus
			if (subMenus != "") {
				Actions action = new Actions(driver);
				// get main Menu web element
				WebElement we = driver.findElement(LoginPageObjects.MenuClient("PROFILE"));

				// mouse hover on the menu
				action.moveToElement(we).click().build().perform();
				gm.logInfo(logger, "sub Menus",driver);
				String[] strSubMenu = subMenus.split(",");
				for (int i = 0; i < strSubMenu.length; i++) {
					gm.verifyObejctExistence(driver, HomePageObjects.SubMenuClient(strSubMenu[i]), strSubMenu[i],
							logger);

				}

			}
			// navigate to Submit Journal Entry link 5 times to check Quick
			// links
			for (int i = 0; i < 6; i++) {
				gm.clickLink(driver, HomePageObjects.MenuClient("SUBMIT"), "SUBMIT Menu", logger);
				gm.waitForObjectExistence(driver, HomePageObjects.submitJournalEntryLink, "submitJournalEntryLink",
						logger);
				Thread.sleep(2000);
				gm.clickLink(driver, HomePageObjects.submitJournalEntryLink, "submit Journal Entry", logger);
			}
			// click on Home Menu
			gm.clickLink(driver, HomePageObjects.MenuClient("HOME"), "HOME Menu", logger);
			// check for quick links
			if (gm.waitForObjectExistence(driver, HomePageObjects.quickLinkSection, "quick Link Section", logger)) {
				if (gm.waitForObjectExistence(driver, HomePageObjects.quickLinks("Submit Journal Entry"),
						"Submit Journal Entry", logger)) {
					gm.logPass(logger, "Submit Journal Entry is present under the quick Link Section",driver);
				} else
					gm.logFail(logger, "Submit Journal Entry is not present under the quick Link Section");

			}
			// verify Sub Section Menus
			if (subSectionMenus != "") {
				String[] str = subSectionMenus.split(",");
				// get list of sub Section menus present in the Home Page
				List<WebElement> subSectionMenusList = driver.findElements(HomePageObjects.subSectionMenues);
				int subSectionMenus_count = subSectionMenusList.size();
				for (int i = 0; i < str.length; i++) {
					int flag = 0;
					for (int col = 0; col < subSectionMenus_count; col++) {
						// check for menu matches with the menus present in the
						// list
						if (subSectionMenusList.get(col).getText().equals(str[i])) {

							gm.logPass(logger, str[i] + " Menu found in the Home Page Sub Section");
							// check on clicking menu Under Sub Section whether
							// it is active
							subSectionMenusList.get(col).click();
							if (subSectionMenusList.get(col).getAttribute("class").contains("active")) {
								gm.logPass(logger, str[i] + " menu is active on clicking it",driver);
							} else
								gm.logFail(logger, str[i] + " menu is not active on clicking it", driver);
							flag = 1;
							break;

						}

					}
					if (!(flag == 1)) {
						gm.logFail(logger, str[i] + " Menu is not found in the Home Page Sub Section", driver);
					}

				}

			}

		}
	}
}