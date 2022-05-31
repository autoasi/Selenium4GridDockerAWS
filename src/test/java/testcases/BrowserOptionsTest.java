package testcases;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserOptionsTest {

	public static void main(String[] args) {
		
		ChromeOptions opt = new ChromeOptions();
		//opt.addArguments("--headless"); // Headless, means a Web Browser without User Interface.
		opt.setAcceptInsecureCerts(true); // Handles Bad SSL Certificate message
		//opt.addArguments("disable-infobars"); // Disable info bar - does not work
		opt.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation")); // Disable info bar
		//opt.addArguments("window-size=1400,1000"); // Adjust window size
		opt.addArguments("incognito"); // open incognito browser
		
		// Open browser in mobile mode
		Map<String,String> mobileEm = new HashMap<String,String>();
		mobileEm.put("deviceName", "iPhone X");
		opt.setExperimentalOption("mobileEmulation",mobileEm);
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(opt);
		
		driver.get("https://selenium.dev");
		//driver.get("https://expired.badssl.com/");
		System.out.println(driver.getTitle());		
	}
}


