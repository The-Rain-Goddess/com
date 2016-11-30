package com.rain.demoTests;
import java.io.*;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.*;

public class excelWrite {

	public static void main(String[] args)throws IOException, EncryptedDocumentException, InvalidFormatException{
		fillData("Panels.xlsx","HPanel");
		fillData("Panels.xlsx", "GPanel");
		fillData("Panels.xlsx", "IPanel");
		

	}
	
	public static boolean fillData(String file, String sheetName)throws IOException, EncryptedDocumentException, InvalidFormatException{
		File data = new File(file);
		Workbook panel = WorkbookFactory.create(new FileInputStream(data));
		Sheet sheet = panel.createSheet(sheetName);
		
		ran(panel, file, sheet, 0, 2);
		
		panel.close();
		System.out.println("File Complete");
		return true;
	}
	
	public static boolean ran(Workbook panel, String file, Sheet sheet, int start, int stop) throws IOException{
		
		Row temp;
		Cell temp_cell;
		for(int i = start; i<stop; i++){
			FileOutputStream fos = new FileOutputStream(file);
			for(int r = 100*i; r<100*i+100; r++){
				temp = sheet.createRow(r);
				for(int c = 0; c<4000; c++){
					temp_cell = temp.createCell(c);
					temp_cell.setCellValue("0000");
				} 
			} panel.write(fos);
			System.out.println("Part Complete");
			/////////////////////////////
			for(int r = 100*i+100; r<100*i+200; r++){
				temp = sheet.createRow(r);
				for(int c = 0; c<4000; c++){
					temp_cell = temp.createCell(c);
					temp_cell.setCellValue("0000");
				} 
			}
			panel.write(new FileOutputStream(file));
			System.out.println(i+1 + " Sections complete");
		} return true;
	}
}
