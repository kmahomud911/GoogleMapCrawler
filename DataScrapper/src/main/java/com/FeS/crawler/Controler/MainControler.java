package com.FeS.crawler.Controler;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.FeS.crawler.Service.BusinessCompanyInfoScrapper;
import com.FeS.crawler.Service.DataScrapper;
//import com.FeS.crawler.Service.DataScrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MainControler {
	
	@Autowired
	private DataScrapper dataScrapper;
	
	@Autowired
	private BusinessCompanyInfoScrapper bc;
	
	
	@GetMapping("/test")
	private String testApi() {
		return "Api is Working";
	}
	
	@GetMapping("/scrapper")
	private void startScrapping() throws IOException, InterruptedException {
		log.info("Scrapper Starting");
//		dataScrapper.startScrapping();
		bc.startBusinessScrapping();
	}
}
