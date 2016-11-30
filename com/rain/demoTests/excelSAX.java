package com.rain.demoTests;

import java.io.BufferedReader;
//import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
//import org.junit.Assert;
import org.junit.Assert;

public class excelSAX {

    public static void main(String[] args) throws Throwable {
    	
        writeToFile("HPanel.xlsx", "HPanel");
        writeToFile("GPanel.xlsx", "GPanel");
        //writeToFile("IPanel.xlsx", "IPanel");
        
    }
    
    public static boolean writeToFile(String file, String sheet) throws Exception{
    	SXSSFWorkbook wb = new SXSSFWorkbook(100); // keep 100 rows in memory, exceeding rows will be flushed to disk
        Sheet sh = wb.createSheet(sheet);
       // StringBuilder sb = new StringBuilder();
        
        FileReader fr = new FileReader("infoDump.txt");
		BufferedReader br = new BufferedReader(fr);
		String temp_line = br.readLine();
		while(!temp_line.equals("END")){
			if(!temp_line.equals("")){
				String[] temp_store = temp_line.split(",");
				int tt = 0;
				Row trow = sh.createRow(Integer.parseInt(temp_store[0]));
				for(int i = 1; i<4000; i++){
					Cell cell1 = trow.createCell(i);
					if(tt == temp_store.length-1) tt = 0;
					else tt++;
					temp_store[tt] = lengthPlus(temp_store[tt].length()) + temp_store[tt];
					cell1.setCellValue(temp_store[tt]);
				}
			} temp_line = br.readLine();
		}
    
        for(int rownum = 1; rownum < 4000; rownum++){
        	if(sh.getRow(rownum)==null){
            Row row = sh.createRow(rownum);
	            for(int cellnum = 0; cellnum < 4000; cellnum++){
	                Cell cell = row.createCell(cellnum);
	                //String address = new CellReference(cell).formatAsString();
	                cell.setCellValue("0000");
	            }
        	}
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
        
        
        System.out.println("File Complete");
        return true;
    }

    public static String lengthPlus(int length){
    	String str = null;
    	switch(length){
	    	case 1: { 
	    		str = "000"; 
	    		break;
	    		}
	    	case 2: {
	    		str = "00";
	    		break;
	    	}
	    	case 3: {
	    		str = "0";
	    		break;
	    	}
	    	case 4:
	    	default: {
	    		str = "";
	    		break;
	    	}
	    	
    	} return str;
    }
}
