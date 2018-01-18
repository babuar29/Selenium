package methods;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

import driver.GenericMethods2;
import pageObjects.HomePageObjects;
import pageObjects.PortalHelpSectionPageObjects;

public class PortalHelpSection {
	static GenericMethods2 gm = new GenericMethods2();

	public void VerifyHelpSection(WebDriver driver, String tabs, ExtentTest logger) {

		// verify Tabs in help Section
		if (tabs != "") {
			String[] str = tabs.split(",");
			// get list of Tabs present in the help Page
			List<WebElement> helpTabsList = driver.findElements(PortalHelpSectionPageObjects.tabsHelp);
			int helpTabs_count = helpTabsList.size();
			for (int i = 0; i < str.length; i++) {
				int flag = 0;
				for (int col = 0; col < helpTabs_count; col++) {
					// check for Tabs matches with the Tabs present in the
					// list
					if (helpTabsList.get(col).getText().equals(str[i])) {

						gm.logPass(logger, str[i] + " tab found in the help page");
						// check on clicking Tab Under help page whether it is
						// active
						helpTabsList.get(col).click();
						if (helpTabsList.get(col).getAttribute("class").contains("active")) {
							gm.logPass(logger, str[i] + " tab is active on clicking it",driver);
						} else
							gm.logFail(logger, str[i] + " tab is not active on clicking it", driver);
						flag = 1;
						break;

					}

				}
				if (!(flag == 1)) {
					gm.logFail(logger, str[i] + " tab is not found in the help page", driver);
				}

			}

		}
	}
}
