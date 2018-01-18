package pageObjects;

import org.openqa.selenium.By;

public class HomePageObjects {
	public static By headerDisplayed = By.xpath("//*[contains(text(),'Client Service Portal')]");

	public static By MenuClient(String Menu) {
		return By.xpath("//a[@id='menu'][text()='" + Menu + "']|//a[@id='profile'][text()='" + Menu + "']");
	}

	public static By menusClient = By.xpath("//a[@id='menu']|//a[@id='profile']");

	public static By SubMenuClient(String SubMenu) {
		return By.xpath("//li//a/following-sibling::ul//a[contains(text(),'" + SubMenu + "')]");
	}

	public static By quickLinks(String link) {
		return By.xpath("//nav[@id='quicklinks']//ul/li//a[contains(text(),'" + link + "')]");
	}

	public static By quickLinkSection = By.xpath("//nav[@id='quicklinks']//*[text()='Quick Link']");
	public static By subSectionMenues = By.xpath("//div[@class='content-tabs']//div");
	public static By submitJournalEntryLink = By.xpath("//*[contains(text(),'Submit Journal Entry')]/../span");
}
