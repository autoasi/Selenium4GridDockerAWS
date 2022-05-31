package grid;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class RemoteParallelTest {
	/* ----- For Standalone -----
	  Run on VM terminal:
	  java -jar selenium-server-4.1.4.jar standalone
	
	
	 -----  For Hub and nodes ------ need to 
	  Run on VM terminal to set up Hub
	  java -jar selenium-server-4.1.4.jar hub
	    
	  Run on VM terminal to set up Node:
	  java -jar selenium-server-4.1.4.jar node --detect-drivers true --publish-events tcp://192.168.1.16:4442 --subscribe-events tcp://192.168.1.24:4443
	
	
	 ----- For Distributed -----
      Run on VM terminal all the commands below:
	  java -jar selenium-server-4.1.4.jar event-bus
	  java -jar selenium-server-4.1.4.jar sessions
	  java -jar selenium-server-4.1.4.jar sessionqueue
	  java -jar selenium-server-4.1.4.jar distributor --sessions http://localhost:5556 --sessionqueue http://localhost:5559 --bind-bus false
	  java -jar selenium-server-4.1.4.jar router --sessions http://localhost:5556 --distributor http://localhost:5553 --sessionqueue http://localhost:5559	
    */
	
	// ThreadLocal always static as it has to be a unique thread for each RemoveWebDriver instance 
	protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	
	public Capabilities cap; // super interface
	
	public WebDriver getDriver() {
		return driver.get();
	}
	
	@Parameters({"browser"}) // getting browser type from testng.xml
	@Test
	public void googleSearchTest(String browser) throws MalformedURLException, InterruptedException {
				
		if (browser.equals("firefox")) {
			cap = new FirefoxOptions();	
		} else if (browser.equals("chrome")) {
		    cap = new ChromeOptions();	
		} else if (browser.equals("edge")) {
			cap = new EdgeOptions();	
		}
				
		driver.set(new RemoteWebDriver(new URL("http://192.168.0.33:4444"), cap));

		getDriver().get("http://google.com");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().findElement(By.xpath("//*[@id='L2AGLb']")).click();
		getDriver().findElement(By.name("q")).sendKeys("Selenium Grid from browser - " + browser);
		System.out.println(getDriver().getTitle()+"---on browser---" + browser);
		Thread.sleep(10000);
		getDriver().quit();
	}
}
