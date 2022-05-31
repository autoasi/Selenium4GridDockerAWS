package testcases;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ElementScreenshotTest {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://gmail.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
		
		WebElement username = driver.findElement(By.id("identifierId"));
		username.sendKeys("asi@gmail.com");
		
		// Take screenshot for the username element 
		File usernameScreenshot = username.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(usernameScreenshot, new File("./screenshot/username.jpg"));
		// Take screenshot for the next button 
		WebElement nextBtn = driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/span"));
		File nextBtnScreenshot = nextBtn.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(nextBtnScreenshot, new File("./screenshot/nextBtn.jpg"));
	}

}
