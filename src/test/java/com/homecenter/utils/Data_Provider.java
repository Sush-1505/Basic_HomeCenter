package com.homecenter.utils;


import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import net.bytebuddy.asm.Advice.Return;

public class Data_Provider {

	@DataProvider(name="Pincodes Data")
	public Object[] [] pincodeData() throws IOException {
		Object[][] data=null;
		
		String path="D:\\Pincodes File.xlsx";
		
		
		FileInputStream fis = new FileInputStream(path);
		
		XSSFWorkbook book=new XSSFWorkbook(fis);
		XSSFSheet sheet=book.getSheet("Pincodes");
		data=new Object[sheet.getLastRowNum()][1];
		for(int i=1;i<=sheet.getLastRowNum();i++) {
			XSSFRow row=sheet.getRow(i);
			XSSFCell cell=row.getCell(1);
			
			
			switch(cell.getCellType()) {
			case STRING:
				String value=cell.getStringCellValue();        
				data[i-1][0]=value;   
				//data[0][0]
				
				break;
				
				case NUMERIC:
					int a=(int) cell.getNumericCellValue();
					data[i-1][0]=a;
					break;
					
					default:
						break;
			}
		}
	
		
	     return data;
		
	}
	
	public int lastRowNumber() throws IOException {
        String path="D:\\Pincodes File.xlsx";
		
		
		FileInputStream fis = new FileInputStream(path);
		
		XSSFWorkbook book=new XSSFWorkbook(fis);
		XSSFSheet sheet=book.getSheet("Pincodes");
		int row=sheet.getLastRowNum();
		
		return row;

	}
}
