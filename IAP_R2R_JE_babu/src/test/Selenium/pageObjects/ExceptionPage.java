package pageObjects;

import org.openqa.selenium.By;

public class ExceptionPage 
{
	/*public static By menu(String menu) { return By.xpath("//div[@id='myNavbar']//li//a[contains(text(),'"+menu+"')]");}
	public static By subMenu(String subMenu) { return By.xpath("//div[@id='myNavbar']//li//a/following-sibling::ul//a[contains(text(),'"+subMenu+"')]");}
	public static By topRightUserName = By.xpath("//span[@id='deskName']/parent::a");
	public static By topRightDropdown(String option) { return By.xpath("//div[@id='myDropdown']/a[contains(text(),'"+option+"')]");}
	public static By quickLinkText= By.xpath("//b[text()='Quick Link']");
	public static By quickLinks(String link) { return By.xpath("//div[contains(text(),'"+link+"')]");}
	public static By tableHeaders(String columnName) { return By.xpath("//thead/tr/th[text()='"+columnName+"']");}
	public static By RITM(String RITM) { return By.xpath("xpathExpression//tbody/tr/td/a[text()='"+RITM+"']");}
	public static By viewAllButton = By.xpath("//a[text()='View All']");*/
	
	public static By searchBox_left = By.xpath("//input[@id='filter']");
	public static By listItem(String text){ return By.xpath("//div[@id='nav_west']//a[@title='"+text+"']");}
	public static By firstRITM = By.xpath("//tr[2]/td[3]/a");
	
	public static By stateRITM = By.xpath("//*[@id='sc_req_item.state']");
	public static By exceptionTab = By.xpath("//div[@id='tabs2_section']/span[3]/span/span[text()='Exception/Rejection']");
	public static By checkBox(String option) {return By.xpath("//div[contains(@id,'"+option+"')]//label[contains(@id,'"+option+"')]");}//preceding-sibling::input[@type='checkbox']
	public static By reason(String option) {return By.xpath("//div[contains(@id,'"+option+"')]//select[contains(@id,'"+option+"_reason')]");}
	public static By comments(String option) {return By.xpath("//div[contains(@id,'"+option+"')]//textarea[contains(@id,'"+option+"')]");}
	public static By updateButton = By.xpath("//div[@class='form_action_button_container']/button[@id='sysverb_update']");
	public static By taskId = By.xpath("//input[@id='sys_readonly.u_rtr_customer_task.u_number']");
	public static By submitButton = By.xpath("//div[@class='form_action_button_container']/button[@id='sysverb_insert']");
	public static By customerTaskTab = By.xpath("//div[@id='tabs2_list']//span[contains(text(),'Customer') and contains(text(), 'Tasks')]");
	public static By customerTaskId(String taskID) {return By.xpath("//tbody//td[text()='"+taskID+"']");}
	public static By customerTaskState(String taskID){ return By.xpath("//tbody//td[text()='"+taskID+"']/following-sibling::td[2]");}
	public static By requestedFor = By.xpath("//input[contains(@id,'requested_for')]");
	public static By requestedForIdLookup = By.xpath("//a[contains(@id,'requested_for') and contains(@id,'view')]");
	public static By requestdForID = By.id("sys_user.user_name");
	public static By requestdForRITNum = By.id("sys_readonly.sc_req_item.number");
	public static By requestorName = By.id("sc_req_item.request.requested_for_label");
	public static By backButton = By.xpath("//button[@data-original-title='Back']");
	public static By notification = By.xpath("//div[@id='output_messages']//div[@class='outputmsg_text']");
	public static By userNameDropdown = By.id("user_info_dropdown");
	public static By logout = By.xpath("//ul/li/a[text()='Logout']");
	public static By RITM = By.id("sys_readonly.sc_req_item.number"); 
	public static By rejectionConfirm = By.xpath("//div/div[1]/div[@class='outputmsg_text']");
}