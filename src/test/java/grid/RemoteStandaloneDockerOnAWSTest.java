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

public class RemoteStandaloneDockerOnAWSTest {

	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		/* Start Docker standalone by running in CMD (only one container can run on port 4444 at the time):
		 docker run -d -p 4444:4444 -p 7900:7900 --shm-size="2g" selenium/standalone-firefox:4.1.4-20220427 
		 docker run -d -p 4444:4444 -p 7900:7900 --shm-size="2g" selenium/standalone-chrome:4.1.4-20220427 
		 docker run -d -p 4444:4444 -p 7900:7900 --shm-size="2g" selenium/standalone-edge:4.1.4-20220427 
		 
		 View the gird:           http://<public_host_ip>:4444
		 View the container:      http://localhost:7900/
		*/
		
		FirefoxOptions opt = new FirefoxOptions();
		//ChromeOptions opt = new ChromeOptions();
		//EdgeOptions opt = new EdgeOptions();
		
		// Add test metadata - will be displayed to the Grid UI
		//opt.setCapability("browserVersion", "100");
		//opt.setCapability("platformName", "Windows");
		//opt.setCapability("se:name", "My docker test"); 
		//opt.setCapability("se:sampleMetadata", "Sample metadata value"); 
		
		WebDriver driver = new RemoteWebDriver(new URL("http://13.40.62.156:4444"),opt); // the ip address is the taken from the instance id of the EC2
		
		driver.get("https://google.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//*[@id='L2AGLb']")).click();
		driver.findElement(By.name("q")).sendKeys("Selenium grid with docker on AWS");
		System.out.println(driver.getTitle());
		Thread.sleep(15000);
		driver.quit();
	}
}
