package testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ElementCoordinatesTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.gmail.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement logoImage = driver.findElement(By.xpath("//*[@id=\"logo\"]"));
		
		// Before Seleniunm 4.0 - Without using Rectangle class
		System.out.println("Height = " + logoImage.getSize().getHeight());
		System.out.println("Width = " + logoImage.getSize().getWidth());
		System.out.println("Location = (" + logoImage.getLocation().getX() + "," + + logoImage.getLocation().getY() + ")");
		
		// From Selenium 4.0 - Using Rectangle class
		Rectangle rectangle = logoImage.getRect();
		System.out.println("Height = " + rectangle.getHeight());
		System.out.println("Width = " + rectangle.getWidth());
		System.out.println("Location = (" + rectangle.getX() + "," + + rectangle.getY() + ")");
	}
}
