package methods;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import driver.GenericMethods2;
import pageObjects.IAPReoprtingPageObjects;


public class IAPReoprting {
	static GenericMethods2 gm = new GenericMethods2();

	/**
	 * Switches to the left nav frame of ServiceNow
	 */
	public void switchToLeftFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("gsft_nav");// nav_west
	}

	/**
	 * Switches to the main frame of ServiceNow
	 */
	public void switchToMainFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("gsft_main"); // edge_center

	}

	public void searchRITNumber(WebDriver driver, String RITNumber, ExtentTest logger) throws InterruptedException {
		//select dropdown
		if(gm.waitForObjectExistence(driver, IAPReoprtingPageObjects.searchDropdown, "searchDropdown",logger))
		{
			Select search=new Select(driver.findElement(IAPReoprtingPageObjects.searchDropdown));
			search.selectByVisibleText("Number");
		}
		

		if (gm.waitForObjectExistence(driver, IAPReoprtingPageObjects.searchBox, "searchBox", logger)) {
			gm.enterText(driver, IAPReoprtingPageObjects.searchBox, RITNumber, "searchBox", logger);
			driver.findElement(IAPReoprtingPageObjects.searchBox).sendKeys(Keys.ENTER);
			if (gm.waitForObjectExistence(driver, IAPReoprtingPageObjects.RITNumber(RITNumber), RITNumber, logger)) {
				gm.logPass(logger, RITNumber + " RIT number is present in the report",driver);
			} else
				gm.logFail(logger, RITNumber + " RIT number is not present in the report", driver);

		}

	}

	/**
	 * Switches frames to the left nav, clicks on a given element, then switches
	 * back to the main frame
	 * 
	 * @param element
	 *            A link element to click on
	 */
	public void navigateToElement(WebDriver driver, WebElement element) {
		switchToLeftFrame(driver);
		element.click();
		switchToMainFrame(driver);
	}

	public void verifyIAPReports(WebDriver driver, String Reports, String RITNumber, ExtentTest logger) {

		// check for reports
		String[] strReports = Reports.split(",");
		for (int i = 0; i < strReports.length; i++) {
			if (gm.waitForObjectExistence(driver, IAPReoprtingPageObjects.report(strReports[i]), strReports[i],
					logger)) {
				gm.logPass(logger, strReports[i] + " Report is present under left navigation bar");
				gm.clickLink(driver, IAPReoprtingPageObjects.report(strReports[i]), strReports[i], logger);
				if (strReports[i].equals("Daily status")) {
					// switch to frame
					switchToMainFrame(driver);
					// check for Run Button
					if (gm.waitForObjectExistence(driver, IAPReoprtingPageObjects.btnRun, "RUN Button", logger)) {
						gm.clickButton(driver, IAPReoprtingPageObjects.btnRun, "RUN", logger);
						// check for Report
						if (gm.waitForObjectExistence(driver, IAPReoprtingPageObjects.containerReport,
								"Report Container", logger)) {
							gm.logPass(logger, "Daily status report is displayed",driver);
						} else
							gm.logFail(logger, "Daily status report is not displayed", driver);

					}
					// switch to default frame
					driver.switchTo().defaultContent();
				} else if (strReports[i].equals("Ageing")) {
					// switch to frame
					switchToMainFrame(driver);
					try {
						searchRITNumber(driver, RITNumber, logger);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// switch to default frame
					driver.switchTo().defaultContent();
				}
			} else
				gm.logFail(logger, strReports[i] + " Report is not present under left navigation bar", driver);

		}

	}
}
