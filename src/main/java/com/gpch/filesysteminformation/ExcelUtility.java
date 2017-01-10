package com.gpch.filesysteminformation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUtility {
	
	private static final String[] HEADERS = {"File", "Size"}; 

	
	public static void generateReport(Map<String, Long> filesInformation) throws IOException{
		String tempFolder = System.getProperty("java.io.tmpdir");

		File file = new File(tempFolder+"file-system-information.xls");
		FileOutputStream fileOutputStream = 
				new FileOutputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook();
		workbook.createSheet("Results");
		HSSFSheet sheet = workbook.getSheet("Results");

		//Headers
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(HEADERS[0]);
		cell = row.createCell(1);
		cell.setCellValue(HEADERS[1]);
		
		//File Information
		int index = 1;
		for (Map.Entry<String, Long> entry : filesInformation.entrySet()) {
		    row = sheet.createRow(index);
			cell = row.createCell(0);
			cell.setCellValue(entry.getKey());
			cell = row.createCell(1);
			cell.setCellValue(entry.getValue());
			index++;
		}
		workbook.write(fileOutputStream);
		workbook.close();
		
	}

}
