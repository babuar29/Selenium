package pageObjects;

import org.openqa.selenium.By;

public class OutOffOfficePageObjects {
	public static By btnNew = By.id("sysverb_new");
	public static By delegatePage = By.id("sysverb_view_change");
	public static By tabsHelp = By.xpath("//nav[@id='submit-menu-bar']//a/..");
	public static By fieldDelegate = By.id("sys_display.sys_user_delegate.u_delegate_name");
	public static By tommorowDate = By.xpath("//td[@class='calText calCurrentDate']/following-sibling::td");
	public static By endDatePicker = By.xpath("//span[@class='input-group ']//a[@id='sys_user_delegate.ends.ui_policy_sensitive']");
	public static By goToTodayLink = By.xpath("//td[@class='calText calTodayText pointerhand']");
	public static By btnSubmit = By.id("sysverb_insert");

	public static By subSectionTab(String subTab) {
		return By.xpath("//div[@id='Pending-Actions-id'][text()='" + subTab + "']");
	}

	public static By delegateName(String delegate) {
		return By
				.xpath("//table[@id='sys_user.sys_user_delegate.user_table']/tbody/tr/td/a[@class='linked'][contains(text(),'"
						+ delegate + "')]");
	}

	public static By RITNum(String RITNum) {
		return By.xpath("//div[@id='pending-action-tab-content']//tbody//tr//a[text()='" + RITNum + "']");
	}

	public static By linkViewTasks = By.xpath("//a[text()='View Tasks']");
	
}
