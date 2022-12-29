package com.FeS.crawler.Service;

import java.io.File;
import java.time.Duration;
import java.util.Locale;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;


public abstract class AbstractScraper {
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";

	

	
	protected ChromeDriver getChromeDriver() {
		return getChromeDriver(false);
	}

	protected ChromeDriver getChromeDriver(boolean isHeadless) {
		ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("E:/Java/ChromeDriver/chromedriver.exe")).usingAnyFreePort().build();

		ChromeOptions opts = new ChromeOptions().addArguments("--no-sandbox")
				.addArguments("start-maximized")
				.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"})
				.addArguments("--disable-blink-features")
				.addArguments("--lang=" + Locale.ENGLISH)
				.addArguments("user-agent="+ USER_AGENT)
				.addArguments("--disable-blink-features=AutomationControlled")
				.addArguments("--disable-dev-shm-usage", "--disable-extensions", "disable-infobars")
				.setHeadless(isHeadless).setExperimentalOption("useAutomationExtension", false);
		ChromeDriver driver = new ChromeDriver(service, opts);
		/*
		 * Developer should increase page load timeout in their scraper class when
		 * needed
		 */
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(40));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
		return driver;
	}
}
