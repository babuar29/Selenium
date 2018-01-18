package pageObjects;

import org.openqa.selenium.By;

public class HomePage 
{
	public static By menu(String menu) { return By.xpath("//div[@id='myNavbar']//li//a[contains(text(),'"+menu+"')]");}
	public static By subMenu(String subMenu) { return By.xpath("//div[@id='myNavbar']//li//a/following-sibling::ul//a[contains(text(),'"+subMenu+"')]");}
	public static By topRightUserName = By.xpath("//span[@id='deskName']/parent::a");
	public static By topRightDropdown(String option) { return By.xpath("//div[@id='myDropdown']/a[contains(text(),'"+option+"')]");}
	public static By quickLinkText= By.xpath("//b[text()='Quick Link']");
	public static By quickLinks(String link) { return By.xpath("//div[contains(text(),'"+link+"')]");}
	public static By subLinks(String link) { return By.xpath("//div[contains(text(),'"+link+"')]");}
	public static By tableHeaders(String columnName) { return By.xpath("//thead/tr/th[text()='"+columnName+"']");}
	public static By RITM(String RITM) { return By.xpath("//tbody/tr/td/a[text()='"+RITM+"']");}
	public static By viewAllButton = By.xpath("//a[text()='View All']");
}