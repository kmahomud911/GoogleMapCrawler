package com.FeS.crawler.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v106.emulation.Emulation;
import org.springframework.stereotype.Service;

import com.FeS.crawler.Model.SchoolInfo;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DataScrapper extends AbstractScraper {

	private static final String keyword = "school in Vatara";
	private static final String URL = "https://www.google.com/search" + "?q=" + keyword;
	private static final String notAvailable = "is not available";
	private static ChromeDriver driver;

	public void startScrapping() throws IOException, InterruptedException {
		driver = getChromeDriver();
		DevTools devTools = driver.getDevTools();
		devTools.createSession();
		devTools.send(Emulation.setGeolocationOverride(Optional.of(52.5043), Optional.of(13.4501), Optional.of(1)));

		if (isAvailable(URL)) {
			log.info("Website Found");
		} else
			log.info("WebPage not Found");
		List<SchoolInfo> list = new ArrayList<>();
		List<String> linkList = new ArrayList<>();

		scrollUntillPageEnd(driver);
		List<WebElement> eList = new WebDriverWait(driver, Duration.ofSeconds(70))
				.until(ExpectedConditions.numberOfElementsToBeMoreThan(
						By.cssSelector("div.m6QErb.DxyBCb.kA9KIf.dS8AEf.ecceSd > div:nth-child(n) > div > a"), 0));
		log.info(eList.toString());

		try {
			for (WebElement it : eList) {
				String link = it.getAttribute("href");
				linkList.add(link);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (String link : linkList) {

			SchoolInfo schoolInfo = new SchoolInfo();
			String schedule = "", website1 = "", website2 = "", website3 = "", phone1 = "", phone2 = "", phone3 = "",
					schoolName = "", rating = "", address = "", type = "";
			driver.get(link);

			try {
				schoolName = driver
						.findElement(By.cssSelector("div.lMbq3e > div:nth-child(1) > h1 > span:nth-child(1)"))
						.getText();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				rating = driver
						.findElement(By.cssSelector("div.F7nice.mmu3tf > span:nth-child(1) > span > span:nth-child(1)"))
						.getText();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				address = driver.findElement(By.cssSelector(
						"#QA0Szd > div > div > div.w6VYqd > div.bJzME.tTVLSc > div > div.e07Vkf.kA9KIf > div > div > div:nth-child(7) > div:nth-child(3) > button > div.AeaXub > div.rogA2c > div.Io6YTe.fontBodyMedium"))
						.getText();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				type = driver
						.findElement(By.cssSelector(
								"div.LBgpqf > div > div:nth-child(2) > span:nth-child(1) > span:nth-child(1) > button"))
						.getText();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			schoolInfo.setSchoolName(schoolName);
			schoolInfo.setRating(rating);
			schoolInfo.setGoogleMapLink(link);
			schoolInfo.setAddress(getAddress(address));
			schoolInfo.setInstitutionType(type);

			try {
				schedule = driver.findElement(By.cssSelector(
						"#QA0Szd > div > div > div.w6VYqd > div.bJzME.tTVLSc > div > div.e07Vkf.kA9KIf > div > div > div:nth-child(7) > div.OqCZI.fontBodyMedium.WVXvdc > div.OMl5r.hH0dDd.jBYmhd > div.MkV9 > div.o0Svhf > span.ZDu9vd > span:nth-child(1) > span:nth-child(2)"))
						.getText();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				website1 = driver.findElement(By.cssSelector(
						"#QA0Szd > div > div > div.w6VYqd > div.bJzME.tTVLSc > div > div.e07Vkf.kA9KIf > div > div > div:nth-child(7) > div:nth-child(5)"))
						.getText();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				website2 = driver.findElement(By.cssSelector(
						"#QA0Szd > div > div > div.w6VYqd > div.bJzME.tTVLSc > div > div.e07Vkf.kA9KIf > div > div > div:nth-child(7) > div:nth-child(6)"))
						.getText();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				phone1 = driver.findElement(By.cssSelector(
						"#QA0Szd > div > div > div.w6VYqd > div.bJzME.tTVLSc > div > div.e07Vkf.kA9KIf > div > div > div:nth-child(7) > div:nth-child(6)"))
						.getText();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				phone2 = driver.findElement(By.cssSelector(
						"#QA0Szd > div > div > div.w6VYqd > div.bJzME.tTVLSc > div > div.e07Vkf.kA9KIf > div > div > div:nth-child(7) > div:nth-child(7)"))
						.getText();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				website3 = driver.findElement(By.cssSelector(
						"#QA0Szd > div > div > div.w6VYqd > div.bJzME.tTVLSc > div > div.e07Vkf.kA9KIf > div > div > div:nth-child(7) > div:nth-child(4)"))
						.getText();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				phone3 = driver.findElement(By.cssSelector(
						"#QA0Szd > div > div > div.w6VYqd > div.bJzME.tTVLSc > div > div.e07Vkf.kA9KIf > div > div > div:nth-child(7) > div:nth-child(5)"))
						.getText();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!schedule.isEmpty()) {
				if (isWebsite(website1)) {
					schoolInfo.setWebsite(website1);
				}

				if (isWebsite(website2)) {
					schoolInfo.setWebsite(website2);
				}

				if (isPhoneNumber(phone1)) {
					schoolInfo.setPhoneNumber(phone1);
				}

				if (isPhoneNumber(phone2)) {
					schoolInfo.setPhoneNumber(phone2);
				}

				if (isPhoneNumber(website1)) {
					schoolInfo.setPhoneNumber(website1);
				}

				if (isPhoneNumber(website2)) {
					schoolInfo.setPhoneNumber(website2);
				}
			}

			if (schedule == "") {
				if (isWebsite(website3)) {
					schoolInfo.setWebsite(website3);
				}
				if (isPhoneNumber(website3)) {
					schoolInfo.setPhoneNumber(website3);
				}
				if (isPhoneNumber(phone3)) {
					schoolInfo.setPhoneNumber(phone3);
				}
			}
			list.add(schoolInfo);
		}

		log.info(list.toString());
		FileExport.ExportFiletoExcell(keyword + convertLocalDateTimeToString(LocalDateTime.now()), list);
		driver.close();
	}

	private boolean isPhoneNumber(String data) {
		if (data.contains("+88") || data.contains("01")) {
			return true;
		} else
			return false;
	}

	private boolean isWebsite(String data) {
		if (data.contains(".com") || data.contains(".edu") || data.contains(".bd") || data.contains(".net")
				|| data.contains(".org")) {
			return true;
		} else
			return false;
	}

	private void scrollUntillPageEnd(ChromeDriver driver2) {

		JavascriptExecutor js = (JavascriptExecutor) driver2;
		try {
			String doc = "document.querySelector('div.m6QErb.DxyBCb.kA9KIf.dS8AEf.ecceSd > div:nth-child(n)')";
			long lastHeight = (long) js.executeScript("return " + doc + ".scrollHeight");
			while (true) {
				js.executeScript(doc + ".scrollBy(0, (" + doc + ".scrollHeight - 1))");
				Thread.sleep(3000);

				long newHeight = (long) js.executeScript("return " + doc + ".scrollHeight");
				if (newHeight == lastHeight) {
					break;
				}
				lastHeight = newHeight;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("Page End");
	}

	private String getAddress(String address) {

		try {
			if (!address.isEmpty() && address.contains("+")) {
				String[] newAdrress = address.split(",");
				if (!newAdrress[2].isEmpty()) {
					return newAdrress[1] + newAdrress[2];
				} else
					return newAdrress[1];
			} else if (!address.isEmpty()) {
				return address;
			} else
				return "Address " + notAvailable;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Address " + notAvailable;

	}

	private boolean isAvailable(String input) throws InterruptedException {
		driver.get(input);
		try {
			List<WebElement> mapButton = driver.findElements(By.cssSelector("div.KP7LCb > div > div > div > a:nth-child(n)"));
			for (WebElement it : mapButton) {
				if(it.getAttribute("href").contains("maps.google.com")) {
					it.click();
					break;
				} else log.info("Can not find map button.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement card = new WebDriverWait(driver, Duration.ofSeconds(70)).until(ExpectedConditions
				.presenceOfElementLocated(By.cssSelector("div.m6QErb.DxyBCb.kA9KIf.dS8AEf.ecceSd > div:nth-child(1)")));

		if (card.isDisplayed()) {
			return true;
		} else
			return false;
	}

	private String convertLocalDateTimeToString(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		return localDateTime.format(formatter); // "1986-04-08 12:30"
	}
}