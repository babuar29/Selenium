package pageObjects;

import org.openqa.selenium.By;

public class SearchPage {
	public static By requestLink = By.xpath("//div[@id='searchNavbar']//li/a[text()='Requests']");
	public static By RITMSearchbox = By
			.xpath("//nav[@id='list_nav_sc_req_item']//label[text()='Search']/following-sibling::input");
	public static By searcCriteriaDropdown = By
			.xpath("//nav[@id='list_nav_sc_req_item']//span[text()='Go to']/following-sibling::div//select");

	public static By RITMLink(String RITM) {
		return By.xpath("//table[@id='sc_req_item_table']/tbody//td[3]/a[text()='" + RITM + "']");
	}
	public static By searchCriteriaDropdown = By.xpath("//nav[@id='list_nav_sc_req_item']//span[text()='Go to']/following-sibling::div//select"); 
}