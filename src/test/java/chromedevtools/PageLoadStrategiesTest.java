package chromedevtools;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PageLoadStrategiesTest {

	public static void main(String[] args) {
		ChromeOptions opt = new ChromeOptions();
		//opt.setPageLoadStrategy(PageLoadStrategy.NORMAL); // 18670
		//opt.setPageLoadStrategy(PageLoadStrategy.EAGER); // 10893
		opt.setPageLoadStrategy(PageLoadStrategy.NONE); // 11234
		
		long start = System.currentTimeMillis(); // Get start time in milliseconds 
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(opt);
		
		driver.get("http://flipkart.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("/html/body/div[2]/div/div/button")).click();
		driver.quit();
		
		long stop = System.currentTimeMillis(); // Get end time in milliseconds 
		System.out.println("Run time in milliseconds: " + String.valueOf(stop-start));
	}
}
