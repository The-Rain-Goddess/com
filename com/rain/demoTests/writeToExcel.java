package com.rain.demoTests;

//import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Assert;

/*
					int k = j;
						if(temp_store.length == 1){
							String tango = temp_store[0];
							Cell cell = row.createCell(j); cell.setCellValue(tango);
						} else{
							if(j!=0){
								if((temp_store.length)==k/b){
									a = b*temp_store.length; b++;
								} Cell cell = row.createCell(j);
							cell.setCellValue(temp_store[j-a]);
							}
						}
						
						
						if(!temp_store[0].equals("")){
					Row row = sh.createRow(Integer.parseInt(temp_store[0])); int take = 0;
					for(int j = 1; j<=4000; j++){ 
						if(j%temp_store.length==0)
							take = 0;
						//System.out.println("Mod:" +temp_store.length%j);
						Cell cell = row.createCell(j); 
						cell.setCellValue(temp_store[take]);
						//System.out.println(cell.toString());
						take++;
					} temp_line = br.readLine();
				}
*/

public class writeToExcel {

	public static void main(String[] args) {
		writeToFiles("HPanel.xlsx", "HPanel");
	}
	
	@SuppressWarnings({ "unused", "finally" })
	public static boolean writeToFiles(String file, String sheet){
		try{
			SXSSFWorkbook wb = new SXSSFWorkbook();
			Sheet sh = wb.createSheet(sheet);
			FileReader fr = new FileReader("infoDump.txt");
			BufferedReader br = new BufferedReader(fr);
			String temp_line = br.readLine();
			while(!temp_line.equals("END")){
				if(!temp_line.equals("")){
					String[] temp_store = temp_line.split(","); int a = 0; int b = 1;
					//System.out.println(temp_line);
					int tt = 0;
					Row row = sh.getRow(Integer.parseInt(temp_store[0]));
					sh.removeRow(row);
					Row real  = sh.createRow(Integer.parseInt(temp_store[0]));
					for(int i = 1; i<=4000; i++){
						
						Cell cell = real.createCell(i);
						if(tt == temp_store.length-1)
							tt = 0;
						else
							tt++;
						cell.setCellValue(temp_store[tt]);
					}
					
					
				}temp_line = br.readLine();
			}
			
			// Rows with rownum < 3900 are flushed and not accessible
	        for(int rownum = 0; rownum < 3900; rownum++){
	          Assert.assertNull(sh.getRow(rownum));
	        }
	
	        // the last 100 rows are still in memory
	        for(int rownum = 3900; rownum < 4000; rownum++){
	           Assert.assertNotNull(sh.getRow(rownum));
	        } 
	        
	        FileOutputStream out = new FileOutputStream(file);
	        wb.write(out);
	        out.close();
	        br.close();
	
	        // dispose of temporary files backing this workbook on disk
	        wb.dispose();
	        wb.close();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
		System.out.println("File Complete");
		return true;
		}
	}

}
