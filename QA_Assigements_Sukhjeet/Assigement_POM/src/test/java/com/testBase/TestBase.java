package com.testBase;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.POM.PageFactory.ObjectFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {
	public static Properties prop;
	public static WebDriver driver;
    protected static ObjectFactory obj;
	
	public TestBase() {
		try {

			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "//TestData//config.properties");
			prop= new Properties();
			prop.load(ip);
	}
		
		
    catch(Exception e) {
    	e.printStackTrace();
    }
}
	public void intialization() {
		obj = new ObjectFactory();
		  WebDriverManager.chromedriver().setup();
		  driver=new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("enable-automation");
		options.addArguments("--remote-allow-origins=*");
		driver = new ChromeDriver(options);
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		  driver.manage().deleteAllCookies();driver.manage().window().maximize();
		 driver.get(prop.getProperty("url"));
	}

	 public void tearDown(){

		try{
			driver.quit();
		}catch (NullPointerException e){

			System.out.println("execptions"+e);
		}
	 }

}
