package chromedevtools;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.network.Network;
import org.openqa.selenium.devtools.v101.network.model.Headers;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RequestAndResponseHeadersTest {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
 
		DevTools devTools = ((ChromeDriver) driver).getDevTools();
		devTools.createSession();
 
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		// Add listener to request headers
		devTools.addListener(Network.requestWillBeSent(), request -> {
			Headers headers = request.getRequest().getHeaders();
			if (!headers.isEmpty()) {
				System.out.println("Request Headers: ");
				System.out.println("-------------------------------------------");
				headers.forEach((key, value) -> {
					System.out.println("  " + key + " = " + value);
				});
			}
		});
		
		// Add listener to response headers
		devTools.addListener(Network.responseReceived(), response -> {		 
			Headers headers = response.getResponse().getHeaders();
			if (!headers.isEmpty()) {
				System.out.println("Response Headers: ");
				System.out.println("-------------------------------------------");
				headers.forEach((key, value) -> {
					System.out.println("  " + key + " = " + value);
				});
			}		
			// Get response URL and status code 
			System.out.println("Response URL is : " + response.getResponse().getUrl());
			System.out.println("Status code is : " + response.getResponse().getStatus());
		});
		
		// Add custom header request
		Map<String, Object> headers = new HashMap<String,Object>();
		headers.put("customHeaderName", "customHeaderValue");
		headers.put("Asi", "Automation");
		Headers head = new Headers(headers);
		devTools.send(Network.setExtraHTTPHeaders(head));
		
		driver.get("https://bbc.com");
	}
}
