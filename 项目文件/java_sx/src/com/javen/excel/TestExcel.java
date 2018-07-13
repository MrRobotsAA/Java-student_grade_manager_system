package com.javen.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Date;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.LabelCell;
import jxl.NumberCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;

public class TestExcel {
	public static void main(String[] args) throws  Exception {
		InputStream is=new FileInputStream("c:/book.xls");
		Workbook rwb=Workbook.getWorkbook(is);
		//获得工作薄（Workbook）中工作表（Sheet）的个数，示例：
		int sheets = rwb.getNumberOfSheets();
		//获取第一张Sheet表
		Sheet rs = rwb.getSheet(0);
		
		String sheetName = rs.getName();
		
		System.out.println(sheets+"  "+sheetName);
		
		 //获取第二行，第二列的值
		 Cell c11 = rs.getCell(1, 1);//第一个是列数，第二个是行数
		 String strc11 = c11.getContents();
		 //获取第四行，第二列的值
		 Cell c13 =  rs.getCell(1, 3);
		 String strc13 = c13.getContents();
		 //第三列 二行
		 Cell c21=  rs.getCell(2, 1);
		 String strc21 = c21.getContents();
		 System.out.println("Cell(1, 1)"+ " value : " + strc11 + "; type : " + c11.getType());
		 System.out.println("Cell(1, 3)"+ " value : " + strc13 + "; type : " + c13.getType());
		 System.out.println("Cell(2, 1)"+ " value : " + strc21 + "; type : " + c21.getType());
		
		int clos=rs.getColumns();//得到所有的列
		int rows=rs.getRows();//得到所有的行
		
		System.out.println(clos+" rows:"+rows);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < clos; j++) {
				Cell[] cells=rs.getRow(i);
				String value=cells[j].getContents();
				System.out.print(value+"\t");
			}
			System.out.println();
		}
		
		
		String strcc00 = null;
		 double strcc10 = 0.00;
		 Date strcc11 = null;

		 Cell cc00 = ((jxl.Sheet) rs).getCell(0, 0);
		 Cell cc10 = ((jxl.Sheet) rs).getCell(1, 0);
		 Cell cc11 = ((jxl.Sheet) rs).getCell(1, 1);

		 if(cc00.getType() == CellType.LABEL)
		 {
		  LabelCell labelc00 = (LabelCell)cc00;
		  strcc00 = labelc00.getString();
		 }
		 if(cc10.getType() == CellType.NUMBER)
		 {
		  NumberCell numc10 = (NumberCell)cc10;
		  strcc10 = numc10.getValue();
		 }
		 if(c11.getType() == CellType.DATE)
		 {
		  DateCell datec11 = (DateCell)cc11;
		  strcc11 = (Date) datec11.getDate();
		 }
		 
		 
		 System.out.println("Cell(0, 0)" + " value : " + strcc00 + "; type : " + cc00.getType());
		 System.out.println("Cell(1, 0)" + " value : " + strcc10 + "; type : " + cc10.getType());
		 System.out.println("Cell(1, 1)" + " value : " + strcc11 + "; type : " + cc11.getType());
		 
		 
		  
			  //利用已经创建的Excel工作薄创建新的可写入的Excel工作薄
			  jxl.write.WritableWorkbook wwb =
			  Workbook.createWorkbook(new File("C:/Book.xls"),rwb);
			 //读取第一张工作表 
			  jxl.write.WritableSheet ws = wwb.getSheet(0);
			  
			  //获取第一个单元格对象 
			  jxl.write.WritableCell wc = ws.getWritableCell(0, 0); 
			  //决断单元格的类型，做出相应的转化
			  if(wc.getType() == CellType.LABEL) { 
				  Label l = (Label) wc;
			  l.setString("The value has been modified.");
			 }
			  //写入Excel对象
			  wwb.write();
			  wwb.close();
	}

}
