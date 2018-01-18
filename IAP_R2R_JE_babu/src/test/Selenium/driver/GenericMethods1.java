package driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;

import javax.naming.directory.NoSuchAttributeException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class GenericMethods1 {
	/****
	 *   This method used to log a screenshot of the particular screen
	 * @param condition - Whether the condition is Pass/ Fail / Info
	 * @param msg - Message that needs to display in the output report
	 * @param driver - WebDriver
	 */
	public void logScreenshot(String condition,String msg, WebDriver driver)
	{	
		String userDirector = System.getProperty("user.dir") + "/"; 

		String s1 = null,s2 ="";
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		if(true)
		{
			try {

				String failureImageFileName =  new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime())+ ".png"; 
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(scrFile, new File("Screenshot\\"+failureImageFileName)); 
				s2 = "<a href=\""+ userDirector +"\\Screenshot\\" + failureImageFileName +"\"><img src=\"file:///" + userDirector +"\\Screenshot\\" + failureImageFileName + "\" alt=\"\""+ "height='300' width='300' border =1/> "+"<br />";


			} catch (IOException e1) {
				e1.printStackTrace();
			}


			if (condition.equalsIgnoreCase("info"))	
			{
				s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>Info</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"

					+ "<tr>"
					+ "<td colspan=2>" 									
					+ "<table width= 100% ; rules =rows >"

												+ "<tbody>"

													+ "<tr>"
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+"Screenshot"+"</strong></td>" 
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+s2+"</strong></td>"

													+ "</tr>"
													+"</tbody>"
													+"</table>"

								+ "</td>"	
								+"</tr>"

				+ "</tbody>"
				+"</table>";

			}

			if (condition.equalsIgnoreCase("Pass"))	
			{
				s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:green;font-size:12px;font-family:verdana;\\><strong>Pass</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"

					+ "<tr>"
					+ "<td colspan=2>" 
					+ "<table width= 100% ;rules =rows >"

												+ "<tbody>"

													+ "<tr>"
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+"Screenshot"+"</strong></td>" 
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+s2+"</strong></td>"

													+ "</tr>"
													+"</tbody>"
													+"</table>"

								+ "</td>"	
								+"</tr>"

				+ "</tbody>"
				+"</table>";

			}
			if (condition.equalsIgnoreCase("Fail"))	
			{
				s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:red;font-size:12px;font-family:verdana;\\><strong>Fail</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"

					+ "<tr>"
					+ "<td colspan=2>" 
					+ "<table width= 100% ; rules =rows >"

												+ "<tbody>"

													+ "<tr>"
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+"Screenshot"+"</strong></td>" 
													+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>"+s2+"</strong></td>"

													+ "</tr>"
													+"</tbody>"
													+"</table>"
													+ "</td>"	
													+"</tr>"

				+ "</tbody>"
				+"</table>";

			}
			Reporter.log(s1);
		}
	}

	/*** This method is used to set the log message in the output report
	 * 
	 * @param condition - Whether it is Info,pass or fail
	 * @param msg - What is the message need to be passed in the output report
	 */
	public void setLogMsg(String condition, String msg)
	{
		String s1 ="";

		if (condition.equalsIgnoreCase("info"))	
		{
			s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\><strong>Info</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"									
					+ "</tbody>"
					+"</table>";

		}

		if (condition.equalsIgnoreCase("Pass"))	
		{
			s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:Green;font-size:12px;font-family:verdana;\\><strong>Pass</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"									
					+ "</tbody>"
					+"</table>";

		}
		if (condition.equalsIgnoreCase("fail"))	
		{
			s1 = "<table width= 100% ; border = 1.5; rules =rows >"

				+ "<tbody>"

					+ "<tr>"
					+ "<td> <font style=\\width:130px;text-align:left;color:red;font-size:12px;font-family:verdana;\\><strong>Fail</strong></td>" 
					+ "<td> <font style=\\width:130px;text-align:left;color:black;font-size:12px;font-family:verdana;\\>"+msg+"</td>" 
					+ "</tr>"									
					+ "</tbody>"
					+"</table>";
		}
		Reporter.log(s1);
	}

	

	/*** This method used to Wait for an object to be located in the UI
	 * 
	 * @param driver - Webdriver
	 * @param object - Object, which needs to be located
	 */
	public void waitForObject(WebDriver driver,By object)
	{
		WebDriverWait wait = new WebDriverWait(driver,60);
	//	wait.until(ExpectedConditions.presenceOfElementLocated(object));
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
							arrayExcelData[i-1][j] = row.getCell(j).toString().trim();
						}
						catch(Exception e){
							arrayExcelData[i-1][j] = "";
						}

						System.out.println("Coming here");
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
	public String getValueFromDatasheet(String SheetName,String colName,int rowNo)
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
			setLogMsg("Fail", "File not found in the path : "+ EnvironmentVariables.dataPoolPath);
		}
		catch(IOException e)
		{
			setLogMsg("Fail", "Problem in reading the File");
		}
		return null;
	}

	/**
	 *  To select the list value from the dropdown using the list value
	 * @param driver - Webdriver
	 * @param locator - Object locator
	 * @param lstValue - List value to be select
	 * @param dropdownName - Dropdown Name
	 */
	public void selectValueFromList(WebDriver driver ,By locator,String lstValue,String dropdownName)
	{
		try
		{			
			WebElement elm = driver.findElement(locator);
			if (!(elm==null))
			{
				Select s = new Select(elm);
				s.selectByValue(lstValue);
				setLogMsg("Info", "Selected the list value : "+ lstValue);
			}
			else		
				setLogMsg("Fail", "Element not found : "+ dropdownName);
		}
		catch(Exception e)
		{
			setLogMsg("Fail", "Element not found : "+ dropdownName + "Exception thrown is : " + e.getMessage());
		}
	}


	/**
	 *  To select the list value from the dropdown using the list value
	 * @param driver - Webdriver
	 * @param locator - Object locator
	 * @param lstIndex - Index of the list value to be select
	 * @param dropdownName - Dropdown Name
	 */
	public void selectValueFromList(WebDriver driver ,By locator,int lstIndex,String dropdownName)
	{
		try
		{
			WebElement elm = driver.findElement(locator);
			if (!(elm==null))
			{
				Select s = new Select(elm);
				s.selectByIndex(lstIndex);
				setLogMsg("Info", "Selected the list value at index : "+ lstIndex);
			}
			else
			{
				setLogMsg("Fail", "Element not found : "+ dropdownName);
			}
		}
		catch(Exception e)
		{
			setLogMsg("Fail", "Element not found : "+ dropdownName + "Exception thrown is : " + e.getMessage());
		}
	}
	/**
	 *  Clicks on a link
	 * @param driver - Webdriver
	 * @param locator - Object locator 
	 * @param linkName - Link Name
	 */
	public void clickLink(WebDriver driver, By locator, String linkName)
	{
		try
		{
			WebElement link = driver.findElement(locator);
			if(!(link==null))
			{
				link.click();
				setLogMsg("Info", "Clicked on link : "+ linkName);
			}
			else
				setLogMsg("Fail", linkName + " not found");
		}
		catch(Exception e)
		{
			setLogMsg("Fail", linkName + " not found. Exception thrown is : " + e.getMessage());
		}
	}

	/***
	 *  Waits for the object to be present
	 * @param driver - WebDriver 
	 * @param locator - Object locator
	 * @param objName - Object Name
	 * @return
	 */
	public boolean waitForObjectExistence(WebDriver driver, By locator, String objName)
	{
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
			setLogMsg("Info", "Object is present : "+ objName);
			return true;
		}
		catch(TimeoutException e)
		{
			setLogMsg("Fail", "Object is not present "+objName+" Timeout Exception is occured : "+ e.getMessage());
			return false;
		}
		catch(ElementNotVisibleException e)
		{
			setLogMsg("Fail", "Object is not visible "+objName+ " Exception is "+ e.getMessage());
			return false;
		}
		catch(ElementNotFoundException e)
		{
			setLogMsg("Fail", "Object is found "+objName+ " Exception is "+ e.getMessage());
			return false;
		}
		catch(Exception e)
		{
			setLogMsg("Fail", "Exception is :"+ e.getMessage());
			return false;
		}		
	}

	/***
	 *  Selects the Check box 
	 * @param driver - WebDriver
	 * @param locator - Object locator
	 * @param checkBoxName - Check box Name
	 */
	public void selectCheckBox(WebDriver driver,By locator, String checkBoxName)
	{
		try
		{
			WebElement chkBox = driver.findElement(locator);
			if (!(chkBox==null))
			{
				chkBox.click();
				setLogMsg("Info", "Selected the Checkbox : "+checkBoxName);
			}
			else
				setLogMsg("Fail", "Not sSelected the Checkbox : "+checkBoxName);
		}
		catch(ElementNotFoundException e)		
		{
			setLogMsg("Fail", "Object not found : "+ e.getMessage());
		}

		catch(Exception e)
		{
			setLogMsg("Fail", "Exception is thrown : "+ e.getMessage());
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
	public String getObjectProperty(WebDriver driver, By locator, String propertyName, String objectName) throws NoSuchAttributeException
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
				setLogMsg("Fail", "Element is not found");
				return null;
			}
		}		
		catch(ElementNotFoundException e)
		{
			setLogMsg("Fail", "Element not found : "+ objectName);
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
	public List<WebElement> getTheDropdownValues(WebDriver driver, By locator, String lstName)
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
			setLogMsg("Fail", "Element not found : "+ lstName);
			return null;
		}
		catch(Exception e)
		{
			setLogMsg("Fail", "Exception : "+ e.getMessage());
			return null;
		}
	}

	/**
	 * Switching Frames 
	 * @param driver - WebDriver 
	 * @param frameIndex - FrameIndex
	 */
	public void switchToFrame(WebDriver driver,int frameIndex)
	{
		try
		{
			driver.switchTo().frame(frameIndex);
			setLogMsg("Info", "Successfully switched to Frame. Frame Index :"+ frameIndex);
		}
		catch(Exception e)
		{
			setLogMsg("Fail", "Unable to switch Frames");			
		}
	}

	/**
	 * Switching Frames 
	 * @param driver - WebDriver 
	 * @param frameName - FrameName
	 */
	public void switchToFrame(WebDriver driver,String frameName)
	{
		try
		{
			driver.switchTo().frame(frameName);
			setLogMsg("Info", "Successfully switched to Frame. Frame Name :"+ frameName);
		}
		catch(Exception e)
		{
			setLogMsg("Fail", "Unable to switch Frames");			
		}
	}

	/**
	 * Switching Frames 
	 * @param driver - WebDriver 
	 * @param frame - WebElement Frame
	 */
	public void switchToFrame(WebDriver driver,WebElement frame)
	{
		try
		{
			driver.switchTo().frame(frame);
			setLogMsg("Info", "Successfully switched to Frame. Frame :"+ frame);
		}
		catch(Exception e)
		{
			setLogMsg("Fail", "Unable to switch Frames");			
		}
	}

	/***
	 *  Read the Table Content and prints the data in the console
	 * @param driver - WebDriver
	 * @param locator - Object locator
	 */
	/** Locater have a path of table **/
	// Table/TBODY/TR/TD

	public void displayTableContent(WebDriver driver,By locator)
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
				setLogMsg("Fail", "Table not found");
		}
		catch(NoSuchElementException e)
		{
			setLogMsg("Fail", "Exception occured : "+ e.getMessage());			
		}		
	}	

	public int getTheColumnPosition(WebDriver driver,By locator,String colName)
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
				setLogMsg("Fail", "No column found with name : "+ colName);
				return colPos; 
			}

		}
		catch(Exception e)
		{
			setLogMsg("Fail", "No colum found");			
		}
		return colPos;
	}
	
	public void performClickOnTable(WebDriver driver, By locator,String colIndex)
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

//				for (int i=0;i<rows.size();i++)
//				{
//					WebElement rowElm  = rows.get(i);
//					WebElement colRequired = rowElm.findElement(By.xpath(".//TD["+colIndex+"]"));
//					
//						String cellContent = rows.get(colIndex).getAttribute("text").toString().trim();
//						System.out.println("The content :"+ i + j + ": "+ cellContent );
//					}
//				}
			}
			else
				setLogMsg("Fail", "Table not found");

		}
		catch(Exception e)
		{
			
		}
	}
	public void verifyObejctIsdisplayed(WebDriver driver, By locator, String ObjectName)
	{		
		try
		{
			WebElement elm = driver.findElement(locator);
			if (elm.isDisplayed())
			{
				setLogMsg("Pass", ObjectName + " is displayed");
				
			}
			else
			{
				logScreenshot("Fail", ObjectName+" is not displayed", driver);
				
			}			
		}
		catch(ElementNotFoundException e)
		{
			logScreenshot("Fail", "Element not found", driver);
			
		}
		catch(Exception e)
		{
			logScreenshot("Fail", "Exception Occured : "+ e.getMessage(), driver);
			
		}
	}
}
