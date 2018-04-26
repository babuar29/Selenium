package methods;

import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.Keys;

import driver.GenericMethods;
import driver.GenericMethods2;
import pageObjects.ExceptionPage;
import pageObjects.HomePage;
import pageObjects.SearchPage;
import pageObjects.SubmitFormPage;

public class SubmitFormMethods
{
	GenericMethods2 gm = new GenericMethods2();
	IAPMethods im = new IAPMethods();


	public String verifySubmission(WebDriver driver, String filepath,String sheetName, ExtentTest logger) throws InterruptedException 
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		driver.findElement(HomePage.menu("SUBMIT")).click();

		// get Test data from excel
		String[][] SubmitFields = gm.getExcelData(filepath, sheetName);
		Thread.sleep(4000);
		gm.waitForObjectExistence(driver, SubmitFormPage.submitJEButton, "Journal Entry Button", logger);
		if(gm.elementExist(driver, SubmitFormPage.submitJEButton))
		{
			gm.logInfo(logger, "Submit JE button is present", driver);
			//click on Submit JE button
			gm.clickLink(driver, SubmitFormPage.submitJEButton, "Submit JE button", logger);
		}
		Thread.sleep(10000);
		gm.switchToFrame(driver, "myFrame", logger);

		for (int eachRow = 0; eachRow < SubmitFields.length; eachRow++) 
		{
			System.out.println("First : ["+eachRow+"][0] "+SubmitFields[eachRow][0]);
			if(SubmitFields[eachRow][0].equalsIgnoreCase("Y"))
			{
				System.out.println(eachRow);
				String fieldName = SubmitFields[eachRow][1];System.out.println(eachRow +" = "+SubmitFields[eachRow][1]);
				String fieldValue = SubmitFields[eachRow][2];System.out.println(eachRow +" = "+SubmitFields[eachRow][2]);
				String fieldType = SubmitFields[eachRow][3];System.out.println(eachRow +" = "+SubmitFields[eachRow][3]);
				//if it is Text box
				if ((fieldValue != "")&&(fieldType.equalsIgnoreCase("Textbox")))
				{
					System.out.println("Textbox found : "+fieldName);
					if (gm.elementExist(driver, SubmitFormPage.textBox(fieldName)))
					{
						WebElement text = driver.findElement(SubmitFormPage.textBox(fieldName));
						js.executeScript("arguments[0].click;", text);
						text.sendKeys(fieldValue);
					}
				} //if it is lookUp field
				//else if (gm.elementExist(driver, SubmitFormPage.searchBox(fieldName)))
				else if ((fieldValue != "")&&(fieldType.equalsIgnoreCase("Lookup")))
				{
					System.out.println("Lookup found : "+fieldName);
					if (gm.elementExist(driver, SubmitFormPage.searchBox(fieldName)))
					{
						/*String currentWindow = driver.getWindowHandle();
							gm.clickSelectValueInLookup(driver, fieldName, fieldValue, logger);
							driver.switchTo().window(currentWindow);*/
						driver.findElement(SubmitFormPage.searchBox(fieldName)).sendKeys(fieldValue);
						driver.findElement(SubmitFormPage.clickArea).click();
					}
				}
				//if it is Dropdown field
				else if ((fieldValue != "")&&(fieldType.equalsIgnoreCase("DropDown")))
				{
					System.out.println("Dropdown found : "+fieldName);
					if (gm.elementExist(driver, SubmitFormPage.dropdown(fieldName)))
					{
						gm.selectValueFromListByText(driver,SubmitFormPage.dropdown(fieldName), fieldValue, fieldName,logger);
					}
				} 
				//if it is Checkbox field
				//else if (gm.elementExist(driver, SubmitFormPage.checkbox(fieldName)))
				else if ((fieldValue != "")&&(fieldType.equalsIgnoreCase("CheckBox")))
				{
					System.out.println("Checkbox found : "+fieldName);
					if (gm.elementExist(driver, SubmitFormPage.checkbox(fieldName)))
					{
						if(fieldValue.equalsIgnoreCase("Yes"))
						{
							/*driver.findElement(SubmitFormPage.listField(fieldName)).click();
								selectMultipleListValues(driver, fieldName, "Check All", fieldValue,
										logger);*/
							driver.findElement(SubmitFormPage.checkbox(fieldName)).click();
						}
					}
				}


				else
				{
					System.out.println("This is a different type of data field");
				}
			}
		} 
		System.out.println("Executed-------------------------------------------------------");
		Thread.sleep(3000);
		//WebElement attach = driver.findElement(SubmitFormPage.attachmentLink);
		driver.findElement(SubmitFormPage.attachmentLink).click();
		//WebElement chooseFile = driver.findElement(SubmitFormPage.chooseFileButton);
		//js.executeScript("window.scrollBy(0,-10000)", "");
		//js.executeScript("arguments[0].scrollIntoView;", attach);
		//js.executeScript("arguments[0].click;", attach);

		JOptionPane.showMessageDialog(null, "Manually select the necessary files to be attached.");
		//driver.findElement(SubmitFormPage.dropdown("Main Template")).click();
		gm.selectValueFromList(driver, SubmitFormPage.dropdown("Main Template"), 1, "Main Template", logger);
		Thread.sleep(2000);
		//driver.findElement(SubmitFormPage.dropdown("Supporting Document")).click();
		gm.selectValueFromList(driver, SubmitFormPage.dropdown("Supporting Document"), 2, "Supporting Document", logger);
		//WebElement click2 = driver.findElement(SubmitFormPage.clickArea);
		//js.executeScript("arguments[0].click;", click2);
		Thread.sleep(3000);
		JavascriptExecutor je = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(SubmitFormPage.submitButton);
		je.executeScript("arguments[0].scrollIntoView(true);",element);
		//JOptionPane.showMessageDialog(null, "Submit the request manually.");
		/*WebElement submit = driver.findElement(SubmitFormPage.submitButton);
		Actions action = new Actions(driver);
		action.moveToElement(submit).click().perform();*/
		//js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		//js.executeScript("arguments[0].click;", submit);
		driver.findElement(SubmitFormPage.submitButton).click();
		Thread.sleep(4000);
		if(gm.isAlertPresent(driver))
		{
			driver.switchTo().alert().accept();
		}
		if(gm.verifyObejctExistence(driver, SubmitFormPage.successMsg, "RITM Success message", logger))
		{
			gm.logPass(logger, "The RITM has been created successfully. The success message is also displayed",driver);

		}
		else
		{
			gm.logFail(logger, "The success message is not displayed. There is some problem in the RITM creation.", driver);

		}
		Thread.sleep(3000);
		gm.switchToFrame(driver, "myFrame", logger);
		driver.findElement(SubmitFormPage.JELink).click();
		Thread.sleep(3000);
		String RITM = driver.findElement(SubmitFormPage.RITMno).getAttribute("value");
		System.out.println(RITM);
		driver.switchTo().defaultContent();
		driver.findElement(HomePage.menu("HOME")).click();
		Thread.sleep(5000);
		driver.findElement(HomePage.subLinks("My Requests")).click();
		Thread.sleep(2500);
		if(gm.verifyObejctExistence(driver, HomePage.RITM(RITM), "RITM value in the table", logger))
		{
			gm.logPass(logger, "Created RITM is present in the My Requests section of the user", driver);
		}
		else
		{
			gm.logFail(logger, "Created RITM is not present in the Pending Response section of the user", driver);
		}
		if(SubmitFields[8][0].equalsIgnoreCase("Y"))
		{
			String approver = im.NameToId(SubmitFields[8][2]);
			im.logoutAndLogin(driver, approver, "Welcome123", logger);
			driver.findElement(HomePage.subLinks("Pending Approval")).click();
			Thread.sleep(3000);
			if(gm.verifyObejctExistence(driver, HomePage.RITM(RITM), "RITM value in the table", logger))
			{
				gm.logPass(logger, "RITM with approval is present in the Pending Response section of the approver", driver);
			}
			else
			{
				gm.logFail(logger, "RITM with approval is not present in the Pending Response section of the approver", driver);
			}
			//driver.findElement(HomePage.viewAllButton).click();
		}

		return RITM;
	}

	public boolean search(WebDriver driver, String username, String password, String Menu, String RITM, String criteria, ExtentTest logger)throws InterruptedException
	{
		if(username!="")
			im.logoutAndLogin(driver, username, password, logger);
		if(!gm.elementExist(driver,ExceptionPage.searchBox_left))
		{
			driver.switchTo().defaultContent();
			driver.findElement(HomePage.menu("SEARCH")).click();
			gm.logInfo(logger, "SEARCH menu was clicked.");
			gm.waitForObjectExistence(driver, SearchPage.requestLink, "Requests link", logger);
			if(gm.elementExist(driver, SearchPage.requestLink))
			{
				gm.logInfo(logger, "Requests link is present", driver);
				//click on Submit JE button
				gm.clickLink(driver, SearchPage.requestLink, "Requests link", logger);
			}
		}
		gm.switchToFrame(driver, "iframe", logger);
		Thread.sleep(4000);
		gm.selectValueFromListByText(driver, SearchPage.searchCriteriaDropdown, criteria, "SearchCriteria", logger);
		Thread.sleep(2000);
		driver.findElement(SearchPage.RITMSearchbox).sendKeys(RITM);
		driver.findElement(SearchPage.RITMSearchbox).sendKeys(Keys.ENTER);
		
		//driver.findElement(SearchPage.RITMSearchbox).sendKeys(Keys.RETURN);
		Thread.sleep(3000);
		if(!gm.waitForAndReturnObject(driver, SearchPage.RITMLink(RITM)).equals(null))
		{
			gm.logPass(logger, "The searched incident = "+RITM+" is found !",driver);
			driver.findElement(SearchPage.RITMLink(RITM)).click();
			return true;
		}
		else
		{
			gm.logFail(logger, "Incident not found!!", driver);
			System.out.println("Failed...Incident not found");
			return false;
		}
	}
}


