package driver;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class EnvironmentVariables {

	// Update the Work Book Name 
	public final static String workbookName = "Selenium_DataSheet.xlsx";
	public final static String driverPath = "C:\\Users\\babu.ramareddy\\workspace\\DFM_BABU\\IEDriverServer.exe";
	public final static String driverType = "webdriver.chrome.driver";	
	public final static String IEdriverType = "webdriver.ie.driver";	
	public final static String chromeDriverType = "webdriver.chrome.driver";
	public final static String IEdriverPath =  "C:\\Users\\babu.ramareddy\\workspace\\DFM_BABU\\IEDriverServer.exe";
	public final static String chromeDriverPath =  "C:\\Users\\babu.ramareddy\\workspace\\IAP_R2R_Babu\\chromedriver.exe";			
	
	public final static String dataPoolPath = System.getProperty("user.dir")+"\\src\\test\\Selenium\\resources\\"+workbookName;
	//public final static String URL = "https://preproddocumentflowmanagerus.accenture.com/DFM411Instance1/Pages/HomePage.aspx?";

	public final static String URL = "http://emeops01test.service-now.com/";
	public final static String reportPath = "C:\\Users\\babu.ramareddy\\workspace\\DFM_BABU\\Extent_Reports\\Report.html";
	public static String extendReportsPath(String className)
    {
          String timeStamp =  new SimpleDateFormat("MM-dd-yyyy_HH-ss").format(new GregorianCalendar().getTime()) ; 
          String dir=System.getProperty("user.dir");
          String extendReportsPathh="./Extent_Reports/"+className+ timeStamp+".html";
          return extendReportsPathh;
    }

}

