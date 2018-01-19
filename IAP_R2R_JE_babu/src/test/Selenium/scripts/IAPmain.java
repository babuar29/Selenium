package scripts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import driver.EnvironmentVariables;
import driver.GenericMethods;
import driver.GenericMethods2;
import methods.ExceptionMethod;
import methods.Home;
import methods.IAPReoprting;
import methods.LoginAndNavigate;
import methods.OutOffOffice;
import methods.PortalHelpSection;
import methods.SubmitFormMethods;

public class IAPmain {

	GenericMethods2 gm = new GenericMethods2();

	LoginAndNavigate login = new LoginAndNavigate();
	Home home = new Home();
	IAPReoprting report = new IAPReoprting();
	OutOffOffice ooo = new OutOffOffice();
	PortalHelpSection help = new PortalHelpSection();
	SubmitFormMethods sm = new SubmitFormMethods();
	ExceptionMethod em = new ExceptionMethod();
	WebDriver driver;
	SoftAssert sa = new SoftAssert();
	ExtentReports reports;
	ExtentTest logger;
	String RITM;

	
	
	ArrayList<String> CTask;

	@BeforeClass()
	public void Open() throws InterruptedException {

		
		
		//reports = new ExtentReports(EnvironmentVariables.extendReportsPath("IAPmain"));
		 String timeStamp =  new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) ; 
        // String dir=System.getProperty("user.dir");
         String extentReportsPath="./Extent_Reports/"+"IAPmain"+ timeStamp+".html";
         reports = new ExtentReports(extentReportsPath);
		logger = reports.startTest("IAP - R2R");

	}

	// @Parameters("sheetName")
	@Test(priority = 0, description = "IAP - R2R", dataProvider = "LoginDetails")
	public void iapR2RApplicationTest(String flag, String module, String browser, String comments,
			String userName, String passWord, String profileName, String menu, String subMenu, String subSectionMenus,
			String Reports,  String delegate, String helpTabs, String filePath, String Criteria,String Exception_Rejection,String Reason,String Exception_comments)
			throws InterruptedException {
		if (flag.equalsIgnoreCase("y")) {
			if (module.equalsIgnoreCase("login")) {
				if (browser.equalsIgnoreCase("IE")) {
					// Launches IE Driver
					System.setProperty(EnvironmentVariables.IEdriverType, EnvironmentVariables.IEdriverPath);
					driver = new InternetExplorerDriver();
					gm.logInfo(logger, "Successfully launched the Internet Explorer 11 browser");
				} else if (browser.equalsIgnoreCase("Chrome")) {
					// Launches Chrome Driver
					System.setProperty(EnvironmentVariables.chromeDriverType, EnvironmentVariables.chromeDriverPath);
					driver = new ChromeDriver();
					gm.logInfo(logger, "Successfully launched the Google Chrome browser");
				}
				/*
				 * else if (browser.equalsIgnoreCase("Firefox")) { //Launches
				 * FireFox Driver driver = new FirefoxDriver();
				 * gm.logInfo(logger,
				 * "Successfully launched the Mozilla Firefox browser"); }
				 */
				else {
					gm.logFail(logger, "Please Select Valid Browser Type", driver);
				}
				// to navigate to the URL
				driver.get(EnvironmentVariables.URL);
				System.out.println("driver initialised");
				// driver.manage().timeouts().pageLoadTimeout(40,
				// TimeUnit.SECONDS);
				driver.manage().window().maximize();
				// To Enter the user name and password in the login screen
				login.login(driver, userName, passWord, profileName, logger);

			}
			else if (module.equalsIgnoreCase("logoutAndRelogin")) {
				System.out.println("In logoutAndRelogin");
				login.logOutAndRelogin(driver, userName, passWord, logger);
			}
			
			else if (module.equalsIgnoreCase("Navigate")) {
				System.out.println("In Navigate");
				login.Navigate(driver, menu, subMenu, logger);
			}
			else if (module.equalsIgnoreCase("Home")) {
				System.out.println("In homePageVerification");
				gm.logInfo(logger, "Home Page Verification");
				home.homePageVerification(driver, menu, subMenu, subSectionMenus, logger);
			} 
			else if (module.equalsIgnoreCase("report")) {
				gm.logInfo(logger, "IAP Reports Verification");
				System.out.println("In verifyIAPReports");
				report.verifyIAPReports(driver, Reports, RITM, logger);
			} 
			
			else if(module.equalsIgnoreCase("Exception"))
			{
				CTask = em.createException(driver, userName,passWord,RITM,Exception_Rejection,Reason,Exception_comments,logger);
				
			}
			else if (module.equalsIgnoreCase("addDelegate")) {
				gm.logInfo(logger, "Adding Delegate");
				
					
					login.logOutAndRelogin(driver, CTask.get(0), passWord, logger);
				
				System.out.println("In addDelegate");
				ooo.addDelegate(driver,"PROFILE","My Profile", delegate, logger);
			}

			else if (module.equalsIgnoreCase("searchRIT")) {
				System.out.println("In searchRITNum");
				
				ooo.searchRITNum(driver, "Pending Response", CTask.get(1), logger);
			} 
			else if (module.equalsIgnoreCase("help")) {
				System.out.println("In VerifyHelpSection");
				gm.logInfo(logger, "Help Section Verification");
				help.VerifyHelpSection(driver, helpTabs, logger);
			}
			else if (module.equalsIgnoreCase("Submit")) {
				System.out.println("In verifySubmission");
				gm.logInfo(logger, "Form Submission Verification");
				RITM = sm.verifySubmission(driver, filePath, "SubmissionForm", logger);
			}
			else if (module.equalsIgnoreCase("Search")) {
				System.out.println("In search");
				sm.search(driver, "","",menu, RITM, Criteria, logger);
			}
		}
	}

	@DataProvider(name = "LoginDetails")
	public Object[][] getExcelData(ITestContext context) {
		Object[][] c = gm.getExcelData(EnvironmentVariables.dataPoolPath, "IAP_R2R_Babu");
		return c;

	}

	@AfterClass
	public void closeTheApplication() {

		reports.endTest(logger);
		reports.flush();
		// driver.quit();
	}

}