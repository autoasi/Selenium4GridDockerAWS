package testcases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenNewTabsAndWindowsTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.bbc.co.uk/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// From Selenium 4 - open new tab
		driver.switchTo().newWindow(WindowType.TAB); 
		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys("Selenium 4");
		
		// From Selenium 4 - open new window
		driver.switchTo().newWindow(WindowType.WINDOW); 
		driver.get("https://www.gmail.com/");
		System.out.println(driver.getTitle());
		
		// Switch between open windows
		System.out.println("Total open windows: " + driver.getWindowHandles().size());
		 
		Set<String> winids = driver.getWindowHandles();
		Iterator<String> iterator = winids.iterator();
 
		List<String> winIndex = new ArrayList<String>();
		while (iterator.hasNext()) {
			winIndex.add(iterator.next());
		}
 
		driver.switchTo().window(winIndex.get(0));
		System.out.println("First window title is :" + driver.getTitle());
		driver.close();
 
		driver.switchTo().window(winIndex.get(1));
		System.out.println("second window title is :" + driver.getTitle());
		driver.close();
		
		driver.switchTo().window(winIndex.get(2));
		System.out.println("third window title is :" + driver.getTitle());
		driver.quit();
	}
}


