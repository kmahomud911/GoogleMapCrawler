package com.FeS.crawler.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.FeS.crawler.Model.SchoolInfo;

public class FileExport {
	
	public static void ExportFiletoExcell(String fileName, List<SchoolInfo> schoolInfoList) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		
		Sheet sheet = workbook.createSheet("SchoolInfo");
		
		Iterator<SchoolInfo> iterator = schoolInfoList.iterator();
		
		int rowIndex = 0;
		while(iterator.hasNext()){
			SchoolInfo school = iterator.next();
			Row row = sheet.createRow(rowIndex++);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(school.getSchoolName());
			Cell cell1 = row.createCell(1);
			cell1.setCellValue(school.getAddress());
			Cell cell2 = row.createCell(2);
			cell2.setCellValue(school.getInstitutionType());
			Cell cell3 = row.createCell(3);
			cell3.setCellValue(school.getRating());
			Cell cell4 = row.createCell(4);
			cell4.setCellValue(school.getPhoneNumber());
			Cell cell5 = row.createCell(5);
			cell5.setCellValue(school.getWebsite());
			Cell cell6 = row.createCell(6);
			cell6.setCellValue(school.getGoogleMapLink());
		}
		
		//lets write the excel data to file now
		FileOutputStream fos = new FileOutputStream( "E:/FeS Software Ltd/Scrapped Data/" +fileName + ".xls");
		workbook.write(fos);
		fos.close();
		System.out.println(fileName + " written successfully");
	}
}
