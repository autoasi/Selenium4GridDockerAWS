package chromedevtools;

import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v99.emulation.Emulation;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MockGeoLocationTest {

	public static void main(String[] args) {
		// https://chromedevtools.github.io/devtools-protocol/tot/Emulation/
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		DevTools devTools = ((ChromeDriver) driver).getDevTools();
		devTools.createSession();
		// Change Geo location to Covent Garden		
		devTools.send(Emulation.setGeolocationOverride(Optional.of((Number)51.509865), 
				Optional.of((Number)(double)-0.118092), Optional.of((Number)100)));
				
		driver.get("https://mycurrentlocation.net/");
	}
}
