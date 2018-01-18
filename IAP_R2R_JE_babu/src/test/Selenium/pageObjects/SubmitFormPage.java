package pageObjects;

import org.openqa.selenium.By;

public class SubmitFormPage {
	public static By submitJEButton = By.xpath("//h4[text()='Submit Journal Entry']/parent::a");
	public static By clickArea = By.xpath("//label/span[text()='Request Details']");
	public static By attachmentLink = By.xpath("//a/img[@data-original-title = 'Attachments...']");
	public static By chooseFileButton = By.xpath("//input[@id='attachFile']");

	public static By checkbox(String label) {
		return By.xpath("//label[contains(@class,'checkbox') and contains(text(),'" + label + "')]");
	}

	public static By searchBox(String label) {
		return By.xpath("//span[text()='" + label + "']/parent::label/parent::div/following-sibling::div/div/input");
	}

	public static By dropdown(String label) {
		return By.xpath("//span[text()='" + label + "']/parent::label/parent::div/following-sibling::div/select");
	}

	public static By textBox(String label) {
		return By
				.xpath("//span[text()='" + label + "']/parent::label/parent::div/following-sibling::div/input[last()]");
	}

	public static By submitButton = By.id("order_now");

	public static By successMsg = By.xpath(
			"//div[@id='sc_order_status_intro_text']//span[text()='Thank you, your request has been submitted']");
	public static By JELink = By
			.xpath("//div[@id='sc_cart_view']/table/tbody//tr//td//a[text()='Submit Journal Entry']");
	public static By RITMno = By.id("sys_readonly.sc_req_item.number");
	public static By searchCriteriaDropdown = By.xpath("//nav[@id='list_nav_sc_req_item']//span[text()='Go to']/following-sibling::div//select"); 
}