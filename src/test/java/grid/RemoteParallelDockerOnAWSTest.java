package grid;

import java.lang.reflect.Method;
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

public class RemoteParallelDockerOnAWSTest {
	/* ----- For Hub and nodes -----
	  docker network create grid
	  docker run -d -p 4442-4444:4442-4444 --net grid --name selenium-hub selenium/hub:4.1.4-20220427
	  docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-chrome:4.1.4-20220427
	  docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-edge:4.1.4-20220427
	  docker run -d --net grid -e SE_EVENT_BUS_HOST=selenium-hub --shm-size="2g" -e SE_EVENT_BUS_PUBLISH_PORT=4442 -e SE_EVENT_BUS_SUBSCRIBE_PORT=4443 selenium/node-firefox:4.1.4-20220427
	  
	  --- compose ---
	  Copy 'docker-compose-v3.yml' file locally and run from cmd:
	  docker-compose -f docker-compose-v3.yml up 
	  
	 ----- For Distributed -----
     Copy 'docker-compose-v3-full-grid.yml' file locally and run from cmd:
     docker-compose -f docker-compose-v3-full-grid.yml up --scale chrome=5 -d
    */
	
	// ThreadLocal always static as it has to be a unique thread for each RemoveWebDriver instance 
	protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
	
	public Capabilities cap; // super interface
	
	public WebDriver getDriver() {
		return driver.get();
	}
	
	@Parameters({"browser"}) // getting browser type from testng.xml
	@Test
	public void googleSearchTest(String browser,Method method) throws MalformedURLException, InterruptedException {
				
		if (browser.equals("firefox")) {
			cap = new FirefoxOptions();	
		} else if (browser.equals("chrome")) {
			cap = new ChromeOptions();	
		} else if (browser.equals("edge")) {
			cap = new EdgeOptions();	
		}
		
		((MutableCapabilities) cap).setCapability("se:name", method.getName()); // set test name in Grid UI
		 
		driver.set(new RemoteWebDriver(new URL("http://13.40.174.253:4444"), cap));

		getDriver().get("http://google.com");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		getDriver().findElement(By.xpath("//*[@id='L2AGLb']")).click();
		getDriver().findElement(By.name("q")).sendKeys("Selenium Grid from browser - " + browser);
		System.out.println(getDriver().getTitle()+"---on browser---" + browser);
		Thread.sleep(10000);
		getDriver().quit();
	}
}

