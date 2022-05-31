package chromedevtools;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.console.Console;
import org.openqa.selenium.devtools.v101.log.Log;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtraxtConsoleLogsTest {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		DevTools devTools = ((ChromeDriver) driver).getDevTools();
		devTools.createSession();

		devTools.send(Log.enable()); // Enable console log
		devTools.send(Console.enable());

		// Add listener to console entry logs
		devTools.addListener(Log.entryAdded(), entry -> {
			System.out.println("------------------------------------");
			System.out.println("Entry Level: " + entry.getLevel());
			System.out.println("Entry Text: " + entry.getText());
			System.out.println("Entry TimeStamp: " + entry.getTimestamp());
			System.out.println("Entry Source: " + entry.getSource());
		});
		// Add listener to console message logs
		devTools.addListener(Console.messageAdded(), message -> {
			System.out.println("------------------------------------");
			System.out.println("Entry Level: " + message.getLevel());
			System.out.println("Entry Text: " + message.getText());
			System.out.println("Entry Source: " + message.getSource());
		});

		driver.get("http://flipkart.com");

		// Add a customised log into the console
		((JavascriptExecutor) driver).executeScript("console.log('This is a sample log')");
	}
}
