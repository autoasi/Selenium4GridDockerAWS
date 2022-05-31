package grid;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteStandaloneTest {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		/* Need to run on VM terminal:
		 *  java -jar selenium-server-4.1.4.jar standalone */
		
		//FirefoxOptions opt = new FirefoxOptions();
		ChromeOptions opt = new ChromeOptions();
		//EdgeOptions opt = new EdgeOptions();
		
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.33:4444"),opt);
		
		driver.get("https://google.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//*[@id='L2AGLb']")).click();
		driver.findElement(By.name("q")).sendKeys("Selenium grid");
		Thread.sleep(5000);
		driver.quit();
	}
}
