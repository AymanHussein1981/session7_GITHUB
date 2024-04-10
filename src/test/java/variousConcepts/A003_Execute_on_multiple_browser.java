package variousConcepts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class A003_Execute_on_multiple_browser { //03/30/2024
		
	
		// see video at 2:39:31 03/30/2024
	
	
	WebDriver driver;
	String browser="asxc";
	
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
		driver.get("https://codefios.com/ebilling/login");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

	@Test
	public void loginTest() {
			

		driver.findElement(User_Name_Field).sendKeys("demo@codefios.com");
		driver.findElement(Password_Field).sendKeys("abc123");
		driver.findElement(Signin_Field).click();
		
		Assert.assertEquals(driver.findElement(Dashboard_Header_Field).getText() , "Dashboard", "Dashboard page did not found");
		
	}
	
	
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}
	

}
