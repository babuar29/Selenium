package methods;

import java.util.ArrayList;
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
import pageObjects.LoginPage;
import pageObjects.SearchPage;
import pageObjects.SubmitFormPage;

public class ExceptionMethod
{
	GenericMethods2 gm = new GenericMethods2();
	IAPMethods im = new IAPMethods();
	SubmitFormMethods sm = new SubmitFormMethods();
	LoginAndNavigate login = new LoginAndNavigate();
	public ArrayList<String> createException(WebDriver driver, String userName,String password, String RITM, 
			String Exception_Rejection, String reason, String comments, ExtentTest logger) throws InterruptedException 
	{
		String RITnum ="", TaskID="",state, requestedFor="";
		boolean found;
		
		ArrayList<String> RequestorAndRITMNum = new ArrayList<String>();

		String x = Exception_Rejection.toLowerCase();
		/*if(gm.elementExist(driver,ExceptionPage.searchBox_left))
		{

		}
		else
		{
			im.logoutAndLogin(driver, userName, password, logger);
		}*/
		
		login.logOutAndRelogin(driver, userName, password, logger);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		Actions act = new Actions(driver);
		Thread.sleep(5000);
		WebElement assigned = driver.findElement(ExceptionPage.listItem("Assigned To Me"));
		//js.executeScript("arguments[0].click;", assigned);
		assigned.click();
		Thread.sleep(10000);
		//sm.search(driver, "", RITM, "Number", logger);
		gm.switchToFrame(driver, "gsft_main", logger);
		driver.findElement(ExceptionPage.firstRITM).click();
		Thread.sleep(7000);
		if(x.equalsIgnoreCase("Exception"))
		{
			System.out.println("Exception...");
			
			state = driver.findElement(ExceptionPage.stateRITM).getText();
			System.out.println("RITM state ---> "+state);
			gm.logInfo(logger, "The state of the RITM is ---> "+state);
			WebElement exception = driver.findElement(ExceptionPage.exceptionTab);
			//js.executeScript("arguments[0].click;", exception);
			act.moveToElement(exception).click().build().perform();
			//exception.click();
			Thread.sleep(3000);
			WebElement check = driver.findElement(ExceptionPage.checkBox(x));
			act.moveToElement(check).click().build().perform();
			//js.executeScript("arguments[0].click;", check);
			//check.click();
			Thread.sleep(3000);
			//driver.findElement(ExceptionPage.reason(x)).click();
			gm.selectValueFromListByText(driver, ExceptionPage.reason(x), reason, "Exception/Rejection Reason", logger);
			WebElement comment = driver.findElement(ExceptionPage.comments(x));
			Thread.sleep(3000);
			js.executeScript("arguments[0].click;", comment);
			comment.sendKeys(comments);
			Thread.sleep(2500);
			driver.findElement(ExceptionPage.updateButton).click();
			Thread.sleep(8000);
			//driver.findElement(ExceptionPage.customerTaskTab).click();
			TaskID = driver.findElement(ExceptionPage.taskId).getAttribute("value");
			System.out.println("Customer task = "+TaskID);
			gm.logInfo(logger, "Customer task created - "+TaskID);
			driver.findElement(ExceptionPage.submitButton).click();
			Thread.sleep(4000);
			state = driver.findElement(ExceptionPage.stateRITM).getText();
			System.out.println("RITM State = "+state);
			gm.logInfo(logger, "The state of the RITM is ---> "+state);
			driver.findElement(ExceptionPage.customerTaskTab).click();
			if(gm.elementExist(driver, ExceptionPage.customerTaskId(TaskID)))
			{
				gm.logPass(logger, "Customer task "+TaskID+" created.");
			}
			else
			{
				gm.logFail(logger, "Customer task was not created!!", driver);
			}
			RITnum = driver.findElement(ExceptionPage.RITM).getAttribute("value");
			gm.logInfo(logger, "The concerned RITM is - "+RITnum);
			
			driver.findElement(ExceptionPage.requestedForIdLookup).click();
			Thread.sleep(5000);
			requestedFor = driver.findElement(ExceptionPage.requestdForID).getAttribute("value");
			System.out.println("Requested for = "+requestedFor);
			driver.findElement(ExceptionPage.backButton).click();
			Thread.sleep(5500);
		}
		if(x.equalsIgnoreCase("Rejection"))
		{
			System.out.println("Rejection...");
			RITnum = driver.findElement(ExceptionPage.RITM).getAttribute("value");
			gm.logInfo(logger, "The concerned RITM is - "+RITnum);
			WebElement exception = driver.findElement(ExceptionPage.exceptionTab);
			//js.executeScript("arguments[0].click;", exception);
			act.moveToElement(exception).click().build().perform();
			//exception.click();
			Thread.sleep(3500);
			WebElement check = driver.findElement(ExceptionPage.checkBox(x));
			act.moveToElement(check).click().build().perform();
			//js.executeScript("arguments[0].click;", check);
			//check.click();
			Thread.sleep(4000);
			//driver.findElement(ExceptionPage.reason(x)).click();
			gm.selectValueFromListByText(driver, ExceptionPage.reason(x), reason, "Exception/Rejection Reason", logger);
			WebElement comment = driver.findElement(ExceptionPage.comments(x));
			Thread.sleep(3000);
			js.executeScript("arguments[0].click;", comment);
			comment.sendKeys(comments);
			Thread.sleep(5000);
			driver.findElement(ExceptionPage.updateButton).click();
			Thread.sleep(8000);
			//requestedFor = driver.findElement(ExceptionPage.requestdForID).getAttribute("value");
			if(gm.verifyObejctExistence(driver, ExceptionPage.rejectionConfirm, "Rejection confirmation message", logger))
			{
				gm.logPass(logger,driver.findElement(ExceptionPage.rejectionConfirm).getText(), driver);
			}
			else
			{
				gm.logFail(logger, "Rejection confirmation message was not displayed.", driver);
			}
			driver.switchTo().defaultContent();
			driver.findElement(ExceptionPage.listItem("RTR Journal Entry All Requested Items")).click();
			Thread.sleep(8000);
			found = sm.search(driver, "", "", "", RITnum, "Number", logger);
			if(found)
			{
				if((driver.findElement(By.xpath("//tbody/tr/td[6]")).getText()).equals("Cancelled"))
				{
					gm.logPass(logger, "The state of the rejected RITM is - Cancelled", driver);
				}
				else
				{
					gm.logFail(logger, "The state of the rejected RITM is not as expected", driver);
				}
			}			
		}
		driver.switchTo().defaultContent();
		/*driver.findElement(ExceptionPage.userNameDropdown).click();
		Thread.sleep(2500);
		JOptionPane.showMessageDialog(null, "Click LogOut button manually");
		WebElement logout = driver.findElement(ExceptionPage.logout);
		act.moveToElement(logout).click().build().perform();
		//js.executeScript("arguments[0].click;", logout);
		if(gm.waitForObjectExistence(driver, LoginPage.loginButton, "Login Button", logger))
		{
			im.login(driver, requestedFor, password, logger);
			driver.findElement(HomePage.subLinks("Pending Response")).click();
			driver.findElement(HomePage.viewAllButton).click();
			Thread.sleep(6000);
			sm.search(driver, "", "", "", RITnum, "Number", logger);
			if(x.equalsIgnoreCase("Exception"))
			{
				if(gm.verifyObejctExistence(driver, HomePage.RITM(RITnum), "RITM list in table", logger))
				{
					gm.logPass(logger, "RITM for Exception found in Pending Response section", driver);
				}
				else
				{
					gm.logFail(logger, "RITM for Exception found in Pending Response section", driver);
				}
			}
			if(x.equalsIgnoreCase("Rejection"))
			{
				if(!gm.verifyObejctExistence(driver, HomePage.RITM(RITnum), "RITM list in table", logger))
				{
					gm.logPass(logger, "Rejected RITM is not present in Pending Response section", driver);
				}
				else
				{
					gm.logFail(logger, "Rejected RITM is still displayed in Pending Response section", driver);
				}
			}

		}
		else
		{
			gm.logFail(logger, "Login page did not load.", driver);
		}*/
		RequestorAndRITMNum.add(requestedFor);
		RequestorAndRITMNum.add(RITnum);
	
		
		return RequestorAndRITMNum;
	}
}


