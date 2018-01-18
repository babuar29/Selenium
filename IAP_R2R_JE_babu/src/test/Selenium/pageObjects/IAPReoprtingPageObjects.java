package pageObjects;

import org.openqa.selenium.By;

public class IAPReoprtingPageObjects {
	public static By btnRun = By.id("run-report");
	public static By containerReport = By.id("report-container-builder");

	public static By report(String ReportName) {
		return By.xpath("//span[contains(text(),'Reports')]/../..//li//div/div[contains(text(),'" + ReportName + "')]");
	}

	public static By RITNumber(String RITNumber) {
		return By.xpath("//table[@id='sc_req_item_table']/tbody//td/a[text()='" + RITNumber + "']");
	}

	public static By searchBox = By.xpath("//label[text()='Search']/following-sibling::input");
	public static By searchDropdown = By.xpath("//select[@class='form-control']");
	
	
	
	
	public static By assignedToMyGroupLink = By.xpath("//div[contains(text(),'Assigned To My Group')]");
	public static By RITMNums = By.xpath("//a[@class='linked formlink']");
	
}
