package pageObjects;

import org.openqa.selenium.By;

public class LoginPageObjects {

	public static By loginPage = By.id("loginPage");
	public static By userName_txt = By.id("user_name");
	public static By password_txt = By.id("user_password");
	public static By LogIn_Btn = By.id("sysverb_login");
	public static By LogOut_Btn = By.xpath(
			"//div[@id='myDropdown']/a[contains(text(),'LOGOUT')]|//a[contains(text(),'Logout')]");
	public static By headerDisplayed = By.xpath("//*[contains(text(),'Client Service Portal')]");
	public static By displayedUserName = By
			.xpath("//button[@id='user_info_dropdown']//span[contains(@class,'user-name')]| //*[@id='deskName']");

	public static By MenuClient(String Menu) {
		return By.xpath("//a[@id='menu'][text()='" + Menu + "']|//a[@id='profile'][text()='" + Menu + "']");
	}

	public static By SubMenuClient(String SubMenu) {
		return By.xpath("//li//a/following-sibling::ul//a[contains(text(),'" + SubMenu + "')]");
	}

}
