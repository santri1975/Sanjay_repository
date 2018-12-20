package testcase;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.swing.text.Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AboutSSI {
	WebDriver driver;
	WebElement element;
	String filepath, filename;
	String testCase;

	static ExtentReports report;
	static ExtentTest test;

	@BeforeTest
	public void createDriver() {

		//System.setProperty("webdriver.chrome.driver", "C:\\Shilpi\\Driver\\chromedriver.exe");
		//driver = new ChromeDriver();
		System.setProperty("webdriver.gecko.driver", "C:\\Venkat\\New folder\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		// Provide Test Case name for the execution as per the data sheet
		testCase = "AboutSSI";

		filepath = System.getProperty("user.dir") + "\\src\\testData\\Driver_TestData.xls";

		// Provide the environment in which this test case need to Run (Production or
		// Testing)
		String environment = "Testing";
		String url = Utilities.readExcelFile(filepath, "AppURL", environment, "TestURL");

		
		driver.get(url);
		
		driver.manage().deleteAllCookies();
	
		//report = new ExtentReports(System.getProperty("user.dir") + "\\ExtendReportResults.html");
		report = new ExtentReports(System.getProperty("user.dir") + "\\" + testCase + "_TestCasesResults.html");

		test = report.startTest(testCase + " Assignment Result");

	}
	
	@Test
	public void tc_001 () throws Exception
	{
								
		//Click About SSI link
		driver.findElement(By.xpath("//*[@id=\"ibm-content-main\"]/div/div[2]/ul/li[7]/a")).click();
		
		Thread.sleep(5000); 
				      
		    //verify page title
		    System.out.println(driver.getTitle());
			assertEquals("Sales Support Information (SSI)| About this site web page", driver.getTitle());
	    	
	    	//Verify About SSI text present in header
	    	if(driver.findElement(By.xpath("//h1[contains(text(),'About SSI')]"))!= null)
	    	{
	    		try
	    		{
	    			test.log(LogStatus.PASS, test.addScreenCapture(capture(driver)) + "About SSI page header text correct : PASSED ",
							"About SSI page header text is correct");
					System.out.println("About SSI page header text is correct");
	    		}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	} else {

				test.log(LogStatus.FAIL, test.addScreenCapture(capture(driver)) + "About SSI page header text is NOT Correct :FAILED ",
						"About SSI page header text is NOT correct");
				System.out.println("About SSI page header text is NOT correct");
	    	}
	    	
	        	   
}
	//Method to take Screenshots
	public static String capture(WebDriver driver) throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String Destfilelocation = (System.getProperty("user.dir") + System.currentTimeMillis() + ".png");
		File Dest = new File(Destfilelocation);
		String errflpath = Dest.getAbsolutePath();
		FileHandler.copy(scrFile, Dest);
		return errflpath;
	}
	
	@AfterTest
	public void afterTest() {
		// System.out.println("After test");
		driver.quit();
		report.endTest(test);
		report.flush();
	}

}
