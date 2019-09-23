package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelConnect {
	@DataProvider(name="adddata")
	//public static void main(String[])throws IOException {
		//public static Object[][] main(String[] args)throws IOException {
	public static Object[][] readExcel() throws IOException {
		Object[][] arrayObject;
		//specify the excel file containing test data
		File src=new File("C:\\BusyQA\\SeleniumFramework\\src\\test\\resources\\testdata.xlsx");
		//load the excel file
		FileInputStream fis=new FileInputStream(src);
		//load the workbook from the above excel file
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		//load the sheet from above excel workbook getSheetAt(0) gets the first sheet
		XSSFSheet sheet=wb.getSheetAt(2);
		//To get the used row count use getLastRowNum() and add 1 since it returns row
		//index which starts from 0
		int rowCount=sheet.getLastRowNum();
	
		//To get used column count use row&#39;s getLastCellNum(). 1 is not added as
		//columns start from 1.
		int colCount=sheet.getRow(1).getLastCellNum();
		// cell1.setCellType(Cell.CELL_TYPE_STRING);
		// colCount.setCellType(colCount.CELL_TYPE_STRING);
		//Create a 2d Array that holds all the values in excel in a 2d array with
		//rowcount and colcount as rows and columns.
		String[][] data = new String[3][8];
		int j;
		for(int i=1; i<4; i++) {
		//Reading first column(getCell refer to Column)
		for(j=0; j<8; j++) {
		//data[i-1][j] =sheet.getRow(i).getCell(j).getStringCellValue();
			
			DataFormatter formatter = new DataFormatter();
			data[i-1][j] = formatter.formatCellValue(sheet.getRow(i).getCell(j));
			
			
		System.out.println(data[i-1][j]);
		}}
		arrayObject = data;
		return arrayObject;
		
}
	
	
	}
