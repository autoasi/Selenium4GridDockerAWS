package testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IFrameTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.w3schools.com/howto/tryit.asp?filename=tryhow_js_element_iframe");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		
		driver.findElement(By.xpath("//*[@id=\"accept-choices\"]")).click(); 
		
		System.out.println("Total number of iframes: " + driver.findElements(By.tagName("iframe")).size()); // print 3
				
		// Switch to child frame by using frame name
		driver.switchTo().frame("iframeResult");
		driver.findElement(By.xpath("/html/body/button")).click(); // this button is under the 'iframeResult' frame
		
		System.out.println("Total number of iframes: " + driver.findElements(By.tagName("iframe")).size()); // print 1
		
		// Switch to child frame by using index
		driver.switchTo().frame(0); // we have only one frame so index 0
		driver.findElement(By.xpath("//*[@id=\"accept-choices\"]")).click(); // this button is under the another frame
		
		// Switch to parent frame (iframeResult)
		driver.switchTo().parentFrame(); // new in Selenium 4
		//driver.switchTo().defaultContent();
		//driver.switchTo().frame("iframeResult");
		driver.findElement(By.xpath("/html/body/button")).click(); // this button is under the 'iframeResult' frame
	}
}
