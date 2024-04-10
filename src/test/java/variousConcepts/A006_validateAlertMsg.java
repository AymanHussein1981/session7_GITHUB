package variousConcepts;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class A006_validateAlertMsg { 	//03/30/2024
	
	private static final String prority = null;


		
	
		// see video at 3:41:00  03/30/2024
		// see video at 4:00:50  03/30/2024
	
	
	WebDriver driver;
	
	String browser;
	String url;
	String userName;
	String password;
	
	
	//test or mock data
	String userNameAlertMsg="Please enter your user name";
	String passwordAlertMsg="Please enter your password";
	
	
	//By Type
	
	By User_Name_Field=By.xpath("//*[@id=\"user_name\"]");
	By Password_Field=By.xpath("//*[@id=\"password\"]");
	By Signin_Field=By.xpath("//*[@id=\"login_submit\"]");
	By Dashboard_Header_Field=By.xpath("/html/body/div[1]/section/div/div[2]/div/div/header/div/strong");
	By CUSTOMER_MENU_FIELD = By.xpath("/html/body/div[1]/aside[1]/div/nav/ul[2]/li[2]/a/span");
	By ADD_CUSTOMER_MENU_FIELD = By.xpath("//*[@id=\"customers\"]/li[2]/a/span");	
	By ADD_CUSTOMER_HEADER_FIELD = By.xpath("/html/body/div[1]/section/div/div[2]/div/div[1]/div[1]/div/div/header/div/strong");	
	By FUUL_NAME_FIELD = By.xpath("//*[@id=\"general_compnay\"]/div[1]/div/input");	
	By COMPANY_DROPDOWN_FIELD = By.xpath("//select[@name='company_name']");	
	
	
	@BeforeClass
	public void readingConfig() {
		
		/*in java there are 4 different classes in order to read a file
		 * InputStream
		 * BufferReader
		 * FileReader
		 * Scanner*/
		
		try {
			
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			
			Properties Prop=new Properties();
			Prop.load(input);
			
			browser = Prop.getProperty("browser");
			System.out.println("Browser used: "+ browser);
		
			url = Prop.getProperty("url");
			System.out.println("url used: "+url);
			
			userName= Prop.getProperty("userName");
			password= Prop.getProperty("password");
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	@BeforeMethod
	public void init() {
		
		if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		}
		else {
			System.out.println("Please enter valid browser name");
		}
		
		
		
		

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
	
	
	

	@Test(priority=2)
	public void loginTest() { 
			

		driver.findElement(User_Name_Field).sendKeys(userName);
		driver.findElement(Password_Field).sendKeys(password);
		driver.findElement(Signin_Field).click();
		
		Assert.assertEquals(driver.findElement(Dashboard_Header_Field).getText() , "Dashboard", "Dashboard page did not found");
		
	}
	
	
	@Test(priority=1)
	public void validateAlertMessages() throws InterruptedException {
		
		driver.findElement(Signin_Field).click();
		String actualUserNameAlertMdg = driver.switchTo().alert().getText();
		Assert.assertEquals(actualUserNameAlertMdg, userNameAlertMsg, "Alert Dosn;t match!!");
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
		
		driver.findElement(User_Name_Field).sendKeys(userName);
		driver.findElement(Signin_Field).click();
		String actualpasswordAlertMdg = driver.switchTo().alert().getText();
		Assert.assertEquals(actualpasswordAlertMdg, passwordAlertMsg, "Alert Dosn;t match!!");
		Thread.sleep(2000);
		driver.switchTo().alert().accept();

	}
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	

}
