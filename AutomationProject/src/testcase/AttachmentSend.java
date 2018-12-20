package testcase;

import java.util.List;

import org.automationtesting.excelreport.Xl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AttachmentSend {
	
 	private ChromeDriver driver;

 	@BeforeMethod
	public void createDriver() {
		System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
		driver = new ChromeDriver();
	}
	
 	@Test
	public void sendAttachment()  throws Exception {   
		driver.get("https://w3-03.ibm.com/sales/support/"
				+ "ShowDoc.wss?docid=51016051JPJA&node=&ftext=IBM&sort=date&sno=rating&showDetails=hide&hitsize=50&offset=0"
				+ "&fromdate=&todate=&filtermessage=&option=&searchin=entiredoc&isw=&sw=&swv=&l=&s=desc&campaign=");
		//driver.findElementById("smartfilter").sendKeys("ssi");

		driver.findElementByXPath("//a[@class='ibm-email-link']").click();
		Thread.sleep(10000);
		driver.findElementById("fromField").sendKeys("sanjay.k.tripathi@in.ibm.com");
		driver.findElementById("subjectField").sendKeys("Attachment done");
		driver.findElementByXPath("//span/input[@id='toField']").sendKeys("sanjay.k.tripathi@in.ibm.com");
		driver.findElementByXPath("//input[@class='ibm-btn-pri ibm-btn-blue-50']").click();
	}
	
	@Test
	public void testInputElement() {
		
		Reporter.log("Browser is maximised");
		driver.manage().window().maximize();
		
		Reporter.log("url is opened");
		driver.navigate().to("http://oitstweb.bld.dst.ibm.com/sales/support/index.html");
		
		WebElement elementA = driver.findElement(By.id("smartfilter"));
		elementA.sendKeys("ssi");		
	}
	
	@Test
	public void testHeadingText() {
		driver.navigate().to("http://oitstweb.bld.dst.ibm.com/sales/support/index.html");

		String linkText  = "Choose a link below to . . .";
		String ssiText = "My SSI saved searches";
		String whatsNew = "Whats new";
		
		System.out.println(linkText);
		
		WebElement ssiElement = driver.findElement(By.xpath("//*[@id='ibm-content-main']/div/div[2]/h4"));
		WebElement chooseElement = driver.findElement(By.xpath("//*[@id='ibm-content-main']/div/div[2]/div/h4"));
		
		List<WebElement> elementList = driver.findElements(By.xpath("//*[@id='ibm-com']"));
		
		Assert.assertTrue(linkText.equals(ssiElement.getText()), "Text not found!");
		Assert.assertTrue(ssiText.equals(chooseElement.getText()), "Text not found!");
	    
		for(WebElement element : elementList) {
			Assert.assertTrue(whatsNew.equals(element.getText()), "Text found!");
		}
	}
	
	@AfterTest
	public void quitDriver()
	{
		driver.close();
	}

	@AfterSuite
	public void generateReport() throws Exception
	{
		Xl.generateReport("Report_Excel.xlsx");
	}		
}	

