package methods;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.naming.directory.NoSuchAttributeException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.*;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import driver.EnvironmentVariables;
import driver.GenericMethods2;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.LoginPageObjects;



public class IAPMethods {

	/**	 Prints the custom message in the Extend reports output - Pass 
	 * @param logger - ExtendTest Logger
	 * @param msg - Message
	 */
	GenericMethods2 gm = new GenericMethods2();
	public void logPass(ExtentTest logger,String msg)
	{
		logger.log(LogStatus.PASS, msg);
	}

	/**
	 * Prints the custom message in the Extend reports output with screenshot - Pass
	 * @param logger- Extend logger
	 * @param msg  - Message
	 * @param driver - WebDriver
	 */
	public void logPass(ExtentTest logger,String msg,WebDriver driver)
	{
		String screeshotPath = captureScreenshot(driver);
		String image = logger.addScreenCapture(screeshotPath);
		logger.log(LogStatus.PASS, msg,image);
	}

	/**	 Prints the custom message in the Extend reports output - Info 
	 * @param logger - ExtendTest Logger
	 * @param msg - Message
	 */
	public void logInfo(ExtentTest logger,String msg)
	{
		logger.log(LogStatus.INFO, msg);
	}

	/**
	 * Prints the custom message in the Extend reports output with screenshot - Info
	 * @param logger- Extend logger
	 * @param msg  - Message
	 * @param driver - WebDriver
	 */
	public void logInfo(ExtentTest logger,String msg,WebDriver driver)
	{
		String screeshotPath = captureScreenshot(driver);
		String image = logger.addScreenCapture(screeshotPath);
		logger.log(LogStatus.INFO, msg,image);
	}

	/**
	 * Prints the custom message in the Extend reports output with screenshot - Fail
	 * @param logger- Extend logger
	 * @param msg  - Message
	 * @param driver - WebDriver
	 */
	public void logFail(ExtentTest logger,String msg, WebDriver driver)
	{
		String screeshotPath = captureScreenshot(driver);
		String image = logger.addScreenCapture(screeshotPath);		
		logger.log(LogStatus.FAIL, msg,image);		
	}		
	/***
	 *  click and select Value In LookUp
	 * @param driver - WebDriver
	 * @param locator - Object locator
	 * @param LookupfieldName - lookup field Name
	 * @param ValueToSearch - Value to search in lookup
	 */
	public void clickSelectValueInLookup(WebDriver driver,String LookupfieldName, String ValueToSearch ,ExtentTest logger)
	{
		try
		{
			//Checking lookup button in header information section & Advance search section
			if(verifyObjectExistenceWithoutFailMessage(driver, By.xpath("//*[contains(text(),'"+LookupfieldName+"')]/../following-sibling::td//div//input[@class='LookupButton']"), LookupfieldName, logger))
			{
				driver.findElement(By.xpath("//*[contains(text(),'"+LookupfieldName+"')]/../following-sibling::td//div//input[@class='LookupButton']")).click();
				driver.switchTo().frame("lookup_dialog_content");
				/*enterTextInTextbox(driver,LookuptableObjs.lookupsearchText , ValueToSearch, "Lookup textbox field", logger);
				clickLink_button(driver, LookuptableObjs.lookupsearchButton, "Lookup Search button", logger);
				clickLink_button(driver, LookuptableObjs.lookupsearchselection, "Search value", logger);
				*/
			}
			//Checking lookup button in Amend field section in work queue
			else if(verifyObjectExistenceWithoutFailMessage(driver,By.xpath("//*[contains(text(),'"+LookupfieldName+"')] /..//input[@class='LookupButton']"), LookupfieldName, logger))
			{
				driver.findElement(By.xpath("//*[contains(text(),'"+LookupfieldName+"')] /..//input[@class='LookupButton']")).click();
				driver.switchTo().frame("lookup_dialog_content");
				/*enterTextInTextbox(driver,LookuptableObjs.lookupsearchText , ValueToSearch, "Lookup textbox field", logger);
				clickLink_button(driver, LookuptableObjs.lookupsearchButton, "Lookup Search button", logger);
				clickLink_button(driver, LookuptableObjs.lookupsearchselection, "Search value", logger);
				*/
			}
			//Checking lookup button in Flex field section in work queue
			/*else if()
			{
				driver.findElement(By.xpath("")).click();
				driver.switchTo().frame("lookup_dialog_content");
				enterTextInTextbox(driver,LookuptableObjs.lookupsearchText , ValueToSearch, "Lookup textbox field", logger);
				clickLink_button(driver, LookuptableObjs.lookupsearchButton, "Lookup Search button", logger);
				clickLink_button(driver, LookuptableObjs.lookupsearchselection, "Search value", logger);
			}
			// Checking lookup button in Line field section in work queue
			else if()
			{
				driver.findElement(By.xpath("")).click();
				driver.switchTo().frame("lookup_dialog_content");
				enterTextInTextbox(driver,LookuptableObjs.lookupsearchText , ValueToSearch, "Lookup textbox field", logger);
				clickLink_button(driver, LookuptableObjs.lookupsearchButton, "Lookup Search button", logger);
				clickLink_button(driver, LookuptableObjs.lookupsearchselection, "Search value", logger);
			}*/
			//log fail lookup button is not found 
			else
			{
				logFail(logger,LookupfieldName+" Element is not found:  ",driver);
			}
		}
		catch(ElementNotFoundException e)		
		{
			logFail(logger, "Object not found : "+ e.getMessage(),driver);
		}
		catch(Exception e)
		{
			logFail(logger, "Exception is thrown : "+ e.getMessage(),driver);
		}
	}


	/*** This method used to Wait for an object to be located in the UI
	 * 
	 * @param driver - Webdriver
	 * @param object - Object, which needs to be located
	 */
	public void waitForObject(WebDriver driver,By object)
	{
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.presenceOfElementLocated(object));
		wait.until(ExpectedConditions.visibilityOfElementLocated(object));
	}

	/** This method used to read the excel data and store it two dimensional Array
	 * 
	 * @param fileName - Where the file located in project
	 * @param sheetName - Sheet, where data needs to be extracted
	 * @return
	 */
	public String[][] getExcelData(String fileName, String sheetName) 
	{
		String[][] arrayExcelData = null;
		org.apache.poi.ss.usermodel.Workbook tempWB;

		try {

			if(fileName.contains(".xlsx")){
				tempWB = new XSSFWorkbook(fileName);
			}
			else{				
				InputStream inp = new FileInputStream(fileName);
				tempWB = (org.apache.poi.ss.usermodel.Workbook) new HSSFWorkbook(new POIFSFileSystem(inp));					
			}

			org.apache.poi.ss.usermodel.Sheet sheet = tempWB.getSheet(sheetName);

			// Total rows counts the top heading row
			int totalNoOfRows = sheet.getLastRowNum();
			System.out.println("The total rows are : " + totalNoOfRows);
			Row row = sheet.getRow(0);
			int totalNoOfCols = row.getLastCellNum();
			System.out.println("The total colums are : " + totalNoOfCols);

			arrayExcelData = new String[totalNoOfRows][totalNoOfCols];

			try {
				for (int i= 1 ; i < totalNoOfRows+1; i++) {

					for (int j=0; j < totalNoOfCols; j++) 
					{
						row = sheet.getRow(i);
						try{
							System.out.println(row.getCell(j).toString().trim());
							System.out.println("The cell values are : " +i +"," +j+": "+ row.getCell(j).toString().trim() );
							arrayExcelData[i-1][j] = row.getCell(j).toString().trim();
						}
						catch(Exception e){
							arrayExcelData[i-1][j] = "";
						}
					}
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		return arrayExcelData;
	}

	public String NameToId(String name)
    {
           String Id = "Could not be derived!";
           if(name.contains("Approver"))
           {
                  String no = new Integer(gm.getNoFromString(name)).toString();
                  Id = "test."+"approver"+no;
                  System.out.println(Id);
           }
           else if(name.contains("Requestor"))
           {
                  String no = new Integer(gm.getNoFromString(name)).toString();
                  Id = "test."+"requestor"+no;
                  System.out.println(Id);
           }
           else
           {
                  System.out.println("Only test Acceptor/Requestor Id can be derived by this method.");
           }
           return Id;
    }


	/**
	 *  Enters the text in the text field
	 * @param driver - Webdriver
	 * @param locator - Object locator
	 * @param text - String that needs to be entered
	 * @param objName - Object Name
	 */
	public void enterText(WebDriver driver, By locator, String text,String objName,ExtentTest logger)
	{
		try
		{
			WebElement elm = driver.findElement(locator);
			if (!(elm==null))
			{
			
				elm.sendKeys(text);
				logInfo(logger, "Successfully entered the text " + text+ " in the field :"+ objName);
			}
			else
				logFail(logger, "Element not found", driver);				
		}
		catch(ElementNotFoundException e)
		{
			logFail(logger, "Element not found", driver);						
		}
		catch(InvalidElementStateException e)
		{			
			logFail(logger, "Element is not proper state to Enter.Element is disabled", driver);
		}
		catch(Exception e)
		{
			logFail(logger, "Other Exceptions : "+ e.getMessage(), driver);
		}
	}
	
	
	/** This method used to find the Column Index in excel sheet, depends on the column name
	 * 
	 * @param sheet - Excel sheet, where needs to find the column
	 * @param ColName - Col Name
	 * @return
	 */
	public int findCol(Sheet sheet,String ColName)
	{
		Row row = null;
		int colCount = 0;

		row = sheet.getRow(0);
		if (!(row==null))
		{
			colCount = row.getLastCellNum();
		}
		else
			colCount = 0;

		for (int j=0;j<colCount;j++)
		{
			if(!( row.getCell(j)==null)){
				if (row.getCell(j).toString().trim().equalsIgnoreCase(ColName)|| row.getCell(j).toString().trim().equalsIgnoreCase((ColName+"[][String]"))){
					return j;
				}
			}
		}
		return -1;
	}

	/*** This method used to get the value from the excel sheet
	 * 
	 * @param SheetName - Sheet, where data needs to extracted
	 * @param colName - Column Name
	 * @param rowNo - Row number, at which data needs to extracted
	 * @return
	 */
	public String getValueFromDatasheet(String SheetName,String colName,int rowNo,ExtentTest logger,WebDriver driver)
	{
		try
		{
			Workbook tempWB;
			String value ="";
			if (EnvironmentVariables.dataPoolPath.contains(".xlsx"))
				tempWB = new XSSFWorkbook(EnvironmentVariables.dataPoolPath);

			else
			{
				FileInputStream inp = new FileInputStream(EnvironmentVariables.dataPoolPath);
				tempWB = (Workbook) new HSSFWorkbook(new POIFSFileSystem(inp));	
			}

			Sheet sheet = tempWB.getSheet(SheetName);
			Row row = sheet.getRow(rowNo);

			if(row == null){
				return null;
			}
			try{
				value = row.getCell(findCol(sheet, colName)).toString().trim();
				return value;
			}
			finally {}
		}
		catch(FileNotFoundException e)
		{
			logFail(logger, "File not found in the path : "+ EnvironmentVariables.dataPoolPath,driver);
		}
		catch(IOException e)
		{
			logFail(logger, "Problem in reading the File",driver);
		}
		return null;
	}

	/**
	 * Method id - "i"
	 *  To select the list value from the dropdown using the list value
	 * @param driver - Webdriver
	 * @param locator - Object locator
	 * @param lstValue - List value to be select
	 * @param dropdownName - Dropdown Name
	 * 
	 */
	public void selectValueFromDropdown(WebDriver driver ,By locator,String valueToSelect,String dropdownName,ExtentTest logger)
	{
		try
		{			
			WebElement elm = driver.findElement(locator);
			if (!(elm==null))
			{
				Select s = new Select(elm);
				s.selectByValue(valueToSelect);
				logInfo(logger, "Selected the list value : "+ valueToSelect);
			}
			else				
				logFail(logger, "Element not found : "+ dropdownName,driver);

		}
		catch(Exception e)
		{
			logFail(logger, "Element not found : "+ dropdownName + "Exception thrown is : " + e.getMessage(),driver);
		}
	}


	/**
	 *  To select the list value from the dropdown using the list value
	 * @param driver - Webdriver
	 * @param locator - Object locator
	 * @param lstIndex - Index of the list value to be select
	 * @param dropdownName - Dropdown Name
	 */
	public void selectValueFromDropdownUsingIndex(WebDriver driver ,By locator,int index,String dropdownName,ExtentTest logger)
	{
		try
		{
			WebElement elm = driver.findElement(locator);
			if (!(elm==null))
			{
				Select s = new Select(elm);
				s.selectByIndex(index);
				logInfo(logger, "Selected the list value at index : "+ index);
			}
			else
			{
				logFail(logger, "Element not found : "+ dropdownName,driver);
			}
		}
		catch(Exception e)
		{
			logFail(logger, "Element not found : "+ dropdownName + "Exception thrown is : " + e.getMessage(),driver);
		}
	}

	public void selectTextFromDropdown(WebDriver driver ,By locator,String textToSelect,String dropdownName,ExtentTest logger)
	{
		try
		{			
			WebElement elm = driver.findElement(locator);
			if (!(elm==null))
			{
				Select s = new Select(elm);
				s.selectByVisibleText(textToSelect);
				logInfo(logger, "Selected the list value : "+ textToSelect);
			}
			else				
				logFail(logger, "Element not found : "+ dropdownName,driver);

		}
		catch(Exception e)
		{
			logFail(logger, "Element not found : "+ dropdownName + "Exception thrown is : " + e.getMessage(),driver);
		}
	}



	/**
	 * Method id= "f"
	 *  Enters the text in the text field
	 * @param driver - Webdriver
	 * @param locator - Object locator
	 * @param text - String that needs to be entered
	 * @param objName - Object Name
	 */
	public void enterTextInTextbox(WebDriver driver, By locator, String text,String objName,ExtentTest logger)
	{
		try
		{
			WebElement elm = driver.findElement(locator);
			if (!(elm==null))
			{
				elm.sendKeys(text);
				logInfo(logger, "Successfully entered the text " + text+ " in the field :"+ objName);
			}
			else
				logFail(logger, "Element not found", driver);				
		}
		catch(ElementNotFoundException e)
		{
			logFail(logger, "Element not found", driver);						
		}
		catch(InvalidElementStateException e)
		{			
			logFail(logger, "Element is not proper state to Enter.Element is disabled", driver);
		}
		catch(Exception e)
		{
			logFail(logger, "Other Exceptions : "+ e.getMessage(), driver);
		}
	}
	/**
	 * Method id= "h"
	 *  Clicks on a link or a button as per locator
	 * @param driver - Webdriver
	 * @param locator - Object locator 
	 * @param linkName - Link Name
	 */
	public void clickLink_button(WebDriver driver, By locator, String linkName,ExtentTest logger)
	{
		try
		{
			WebElement link = driver.findElement(locator);
			if(!(link==null))
			{
				link.click();
				logInfo(logger, "Clicked on link : "+ linkName);
			}
			else
				logFail(logger, linkName + " not found",driver);
		}
		catch(Exception e)
		{
			logFail(logger, linkName + " not found. Exception thrown is : " + e.getMessage(),driver);
		}
	}

	
	
	/**
	 *  Verifies that element is present or not 
	 * @param driver - WebDriver 
	 * @param locator - Locator
	 * @param ObjectName - Object Name 
	 * @param logger - ExtentTest Logger
	 * @return
	 */
	
	
	/***
	 *  Waits for the object to be present
	 * @param driver - WebDriver 
	 * @param locator - Object locator
	 * @param objName - Object Name
	 * @return
	 */
	public boolean waitForObjectExistence(WebDriver driver, By locator, String objName,ExtentTest logger)
	{

		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			Thread.sleep(5000);
			logInfo(logger, "Object is present : "+ objName);			
			return true;
		}
		catch(TimeoutException e)
		{
			logFail(logger, "Object is not present "+objName+" Timeout Exception is occured : "+ e.getMessage(),driver);
			return false;
		}
		catch(ElementNotVisibleException e)
		{
			logFail(logger, "Object is not visible "+objName+ " Exception is "+ e.getMessage(),driver);
			return false;
		}
		catch(ElementNotFoundException e)
		{
			logFail(logger, "Object is found "+objName+ " Exception is "+ e.getMessage(),driver);
			return false;
		}
		catch(Exception e)
		{
			logFail(logger, "Exception is :"+ e.getMessage(),driver);
			return false;
		}		
	}

	/***
	 *  Selects the Check box 
	 * @param driver - WebDriver
	 * @param locator - Object locator
	 * @param checkBoxName - Check box Name
	 */
	public void selectCheckBox(WebDriver driver,By locator, String checkBoxName,ExtentTest logger)
	{
		try
		{
			WebElement chkBox = driver.findElement(locator);
			if (!(chkBox==null))
			{
				chkBox.click();
				logInfo(logger, "Selected the Checkbox : "+checkBoxName);
			}
			else
				logFail(logger, "Not sSelected the Checkbox : "+checkBoxName,driver);
		}
		catch(ElementNotFoundException e)		
		{
			logFail(logger, "Object not found : "+ e.getMessage(),driver);
		}

		catch(Exception e)
		{
			logFail(logger, "Exception is thrown : "+ e.getMessage(),driver);
		}
	}

	/**
	 *  To retrieve the element Property
	 * @param driver - WebDrvier
	 * @param locator - Object locator
	 * @param propertyName - Element Property
	 * @param objectName - Element Name 
	 * @return 
	 * @throws NoSuchAttributeException
	 */
	public String getObjectProperty(WebDriver driver, By locator, String propertyName, String objectName,ExtentTest logger) throws NoSuchAttributeException
	{
		try
		{
			WebElement elm = driver.findElement(locator);
			if (!(elm==null))
			{
				String propertyValue = elm.getAttribute(propertyName).toString().trim();
				return propertyValue;
			}
			else
			{
				logFail(logger, "Element is not found",driver);
				return null;
			}
		}		
		catch(ElementNotFoundException e)
		{
			logFail(logger, "Element not found : "+ objectName,driver);
			return null;
		}
	}	

	/**
	 *  Get the list values present in the drodown
	 * @param driver - Webdriver 
	 * @param locator -  Object locator
	 * @param lstName - Dropdown name
	 * @return
	 */
	public List<WebElement> getTheDropdownValues(WebDriver driver, By locator, String lstName,ExtentTest logger)
	{
		try
		{
			WebElement elm = driver.findElement(locator);
			Select s = new Select(elm);
			List<WebElement> options = s.getOptions();
			return options;
		}
		catch(ElementNotFoundException e)
		{
			logFail(logger, "Element not found : "+ lstName,driver);
			return null;
		}
		catch(Exception e)
		{
			logFail(logger, "Exception : "+ e.getMessage(),driver);
			return null;
		}
	}


	/**
	 * Switching Frames 
	 * @param driver - WebDriver 
	 * @param frameName - FrameName
	 */
	public void switchToFrame(WebDriver driver,String frameName,ExtentTest logger)
	{
		try
		{
			driver.switchTo().frame(frameName);
			logInfo(logger, "Successfully switched to Frame. Frame Name :"+ frameName);
		}
		catch(Exception e)
		{
			logFail(logger, "Unable to switch Frames",driver);			
		}
	}


	/***
	 *  Read the Table Content and prints the data in the console
	 * @param driver - WebDriver
	 * @param locator - Object locator
	 */
	/** Locater have a path of table **/
	// Table/TBODY/TR/TD

	public void displayTableContent(WebDriver driver,By locator,ExtentTest logger)
	{
		try
		{		
			WebElement tblMain = driver.findElement(locator);
			if (!(tblMain==null))
			{
				WebElement tblbody = tblMain.findElement(By.tagName("TBODY"));

				// Get the Total number of Rows - TR
				List<WebElement> rows = tblbody.findElements(By.tagName("TR"));
				List<WebElement> cols = rows.get(0).findElements(By.tagName("TD"));

				for (int i=0;i<rows.size();i++)
				{
					for (int j=0;j<cols.size();j++)
					{
						String cellContent = cols.get(j).getAttribute("text").toString().trim();
						System.out.println("The content :"+ i + j + ": "+ cellContent );
					}
				}
			}
			else
				logFail(logger, "Table not found",driver);
		}
		catch(NoSuchElementException e)
		{
			logFail(logger, "Exception occured : "+ e.getMessage(),driver);			
		}		
	}	

	public int getTheColumnPosition(WebDriver driver,By locator,String colName,ExtentTest logger)
	{
		int colPos = -1;
		try
		{
			WebElement tblMain = driver.findElement(locator);
			if (!(tblMain==null))
			{
				WebElement tblbody = tblMain.findElement(By.tagName("TBODY"));

				// Get the Total number of Rows - TR
				List<WebElement> heading = tblbody.findElements(By.tagName("TH"));				

				for (int i=0;i<heading.size();i++)
				{				
					String columnName = heading.get(i).getText();
					System.out.println("The column name is : "+ columnName );
					if (columnName.equalsIgnoreCase(colName))
					{
						colPos = i;
						break;
					}
				}
			}

			if (colPos==-1){
				logFail(logger, "No column found with name : "+ colName,driver);
				return colPos; 
			}

		}
		catch(Exception e)
		{
			logFail(logger, "No colum found",driver);			
		}
		return colPos;
	}

	/**
	 * Clicks on Table column based on the column Index and Searching text 
	 * @param driver - WebDriver
	 * @param locator - Object locator
	 * @param searchText - Searching text
	 * @param colIndex - Column Index 
	 */
	public void clickOnTableColumn(WebDriver driver, By locator,String searchText,String colIndex,ExtentTest logger)
	{
		try
		{
			WebElement tblMain = driver.findElement(locator);
			if (!(tblMain==null))
			{
				WebElement tblbody = tblMain.findElement(By.tagName("TBODY"));

				// Get the Total number of Rows - TR
				List<WebElement> rows = tblbody.findElements(By.tagName("TR"));
				List<WebElement> cols = rows.get(0).findElements(By.tagName("TD"));

				for (int i=0;i<rows.size();i++)
				{
					WebElement rowElm  = rows.get(i);
					WebElement colRequired = rowElm.findElement(By.xpath(".//TD["+colIndex+"]"));

					String cellContent = colRequired.getAttribute("text").toString().trim();
					if (cellContent.equalsIgnoreCase(searchText))
					{
						colRequired.click();						
						logPass(logger, "Clicked on the Row number : "+ i+" with the column : "+ colIndex,driver );
						break;
					}
					System.out.println("The content at row : "+ i +" columm index :" +colIndex +". CellContent is : "+ cellContent);
				}
			}		
			else
				logFail(logger, "Table not found",driver);

		}
		catch(ElementNotFoundException e)
		{
			logFail(logger, "No Element found",driver);
		}
		catch(Exception e)
		{
			logFail(logger, "The exception is :" +e.getMessage(),driver);
		}
	}

	/**
	 *  Return the current window handles 
	 * @param driver
	 * @return
	 */

	public String getTheCurrentWindowHandle(WebDriver driver,ExtentTest logger)
	{
		try
		{
			String currentWindowName = driver.getWindowHandle();
			return currentWindowName;
		}
		catch(NoSuchWindowException e)
		{
			logFail(logger, "No window found",driver);
			return null;
		}
	}

	/**
	 *  Switch to other window handles
	 * @param driver - WebDriver 
	 * @param currentWindow  - Window handle, where drive has to switch
	 * @return
	 */
	public boolean switchToNewlyOpenedWindow(WebDriver driver,String currentWindow,ExtentTest logger)
	{
		try
		{			
			Set<String> totalwindows = driver.getWindowHandles();
			for (String window : totalwindows)
			{
				if (window.equalsIgnoreCase(currentWindow))
				{
					driver.switchTo().window(window);
					logInfo(logger, "Successfully navigated to Newly opened window");
					break;
				}
			}			
		}
		catch(NoSuchWindowException e){
			logFail(logger, "No window found with the Window handle",driver);
			return false;
		}		
		return true;		
	}	

	public boolean verifyObjectExistence(WebDriver driver, By locator, String ObjectName,ExtentTest logger)
	{		
		try
		{
			WebElement elm = driver.findElement(locator);
			if (elm.isDisplayed())
			{
				logPass(logger,  ObjectName + " is displayed");
				return true;
			}
			else
			{
				logFail(logger, ObjectName+" is not displayed", driver);
				return false;
			}			
		}
		catch(ElementNotFoundException e)
		{
			logFail(logger, "Element not found", driver);
			return false;
		}
		catch(Exception e)
		{
			logFail(logger, "Exception Occured : "+ e.getMessage(), driver);
			return false;
		}
	}
	public boolean verifyObjectExistenceWithoutFailMessage(WebDriver driver, By locator, String ObjectName,ExtentTest logger)
	{		
		try
		{
			WebElement elm = driver.findElement(locator);
			if (elm.isDisplayed())
			{
				logPass(logger,  ObjectName + " is displayed");
				return true;
			}
			else
			{
				//logFail(logger, ObjectName+" is not displayed", driver);
				return false;
			}			
		}
		catch(ElementNotFoundException e)
		{
			//logFail(logger, "Element not found", driver);
			return false;
		}
		catch(Exception e)
		{
			//logFail(logger, "Exception Occured : "+ e.getMessage(), driver);
			return false;
		}
	}


	public String getCurrentDate()
	{				
		java.util.Calendar cal=java.util.Calendar.getInstance();//calculating current date
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(java.util.Calendar.getInstance().getTime());
		System.out.println("Date1 is "+timeStamp);
		return timeStamp;		
	}

	public String months(int m)
	{
		String month;
		if(m==1){
			month="January";return month;
		}
		if(m==2){
			month="February";return month;
		}
		if(m==3){
			month="March";return month;
		}
		if(m==4){
			month="April";return month;
		}
		if(m==5){
			month="May";return month;
		}
		if(m==6){
			month="June";return month;
		}
		if(m==7){
			month="July";return month;
		}
		if(m==8){
			month="August";return month;
		}
		if(m==9){
			month="September";return month;
		}
		if(m==10){
			month="October";return month;
		}
		if(m==11){
			month="November";return month;
		}
		if(m==12){
			month="December";
			return month;
		}
		return "";
	}

	public ArrayList<String> getTheListValues(WebDriver driver,By locator,ExtentTest logger)
	{
		ArrayList<String> a = new ArrayList<>();
		try
		{
			Select n = new Select(driver.findElement(locator));
			List<WebElement> listValues = n.getOptions();

			for (int i=0;i<listValues.size();i++)
			{
				String value = listValues.get(i).getAttribute("text").toString().trim();
				a.add(value);
			}
		}
		catch(ElementNotFoundException e)
		{
			logFail(logger, "Element not found", driver);
			return null;
		}	
		catch(Exception e)
		{
			logFail(logger, "Exception occured is : "+ e.getMessage(), driver);
			return null;
		}
		return a;
	}

	/**	 Prints the custom message in the Extend reports output - Fail 
	 * @param logger - ExtendTest Logger
	 * @param msg - Message
	 */
	public void logfail(ExtentTest logger,String msg)
	{
		logger.log(LogStatus.FAIL, msg);
	}
	
	public String captureScreenshot(WebDriver driver)
	{	
		String userDirector = System.getProperty("user.dir");
	//	String userDirector = System.getProperty("user.dir") + "/";

		String s1 = null,s2 ="";
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		if(true)
		{
			try {

				String failureImageFileName =  new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime())+ ".png"; 
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("Screenshot\\"+failureImageFileName));
				s1 =  userDirector +"\\Screenshot\\" + failureImageFileName ;							

			} catch (IOException e1) {
				e1.printStackTrace();
			}		
		}
		return s1;
	}
	public void login(WebDriver driver,String userName, String passWord, ExtentTest logger)throws InterruptedException
	{
		//gm.waitForObject(driver, LoginPage.userName);
		Thread.sleep(8000);
		switchToFrame(driver, "gsft_main", logger);
		driver.findElement(LoginPage.userName).sendKeys(userName);
		driver.findElement(LoginPage.password).sendKeys(passWord);
		driver.findElement(LoginPage.loginButton).click();
		Thread.sleep(4500);
		if(verifyObjectExistence(driver, HomePage.topRightUserName, "User Name at the top-right", logger))
			gm.logPass(logger, "Navigated to the IAP Portal");
		else
			gm.logFail(logger, "Not Navigated to IAP Portal");			
		
	}
	public void login2(WebDriver driver, String usrName, String usrPswd, ExtentTest logger) {
		// Check for login Page
		if (gm.waitForObjectExistence(driver, LoginPageObjects.loginPage, "login Page", logger)) {
			gm.enterText(driver, LoginPageObjects.userName_txt, usrName, "UserName Field", logger);
			gm.enterText(driver, LoginPageObjects.password_txt, usrPswd, "UserPassword Field", logger);
			//gm.clickButton(driver, LoginPageObjects.LogIn_Btn, "Login", logger);
			
			if (gm.waitForObjectExistence(driver, LoginPageObjects.headerDisplayed, "Home Page Header", logger)) {
				gm.logPass(logger, "User navigated to Client Service Portal");
			} else
				gm.logFail(logger, "User not Navigated to Client Service Portal page On clicking Login Button", driver);

		} else
			gm.logFail(logger, "Login Page is not Displayed", driver);
//code profile  name
		// JOptionPane.showMessageDialog(null, "Click on Ok");

	}
	
	public void logoutAndLogin(WebDriver driver, String username, String pwd, ExtentTest logger)throws InterruptedException
	{
		driver.switchTo().defaultContent();
		Thread.sleep(1500);
		driver.findElement(HomePage.topRightUserName).click();
		if(gm.isAlertPresent(driver))
		{
			System.out.println("Alert found before clicking!");
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
		gm.selectValueFromListByText(driver, HomePage.topRightDropdown("LOGOUT"), "LOGOUT", "User name dropdown", logger);
		if(gm.isAlertPresent(driver))
		{
			System.out.println("Alert found!");
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
		Thread.sleep(3000);
		login(driver,username,pwd,logger);
	}
	
}
