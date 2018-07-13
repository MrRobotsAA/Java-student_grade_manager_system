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
		//��ù�������Workbook���й�����Sheet���ĸ�����ʾ����
		int sheets = rwb.getNumberOfSheets();
		//��ȡ��һ��Sheet��
		Sheet rs = rwb.getSheet(0);
		
		String sheetName = rs.getName();
		
		System.out.println(sheets+"  "+sheetName);
		
		 //��ȡ�ڶ��У��ڶ��е�ֵ
		 Cell c11 = rs.getCell(1, 1);//��һ�����������ڶ���������
		 String strc11 = c11.getContents();
		 //��ȡ�����У��ڶ��е�ֵ
		 Cell c13 =  rs.getCell(1, 3);
		 String strc13 = c13.getContents();
		 //������ ����
		 Cell c21=  rs.getCell(2, 1);
		 String strc21 = c21.getContents();
		 System.out.println("Cell(1, 1)"+ " value : " + strc11 + "; type : " + c11.getType());
		 System.out.println("Cell(1, 3)"+ " value : " + strc13 + "; type : " + c13.getType());
		 System.out.println("Cell(2, 1)"+ " value : " + strc21 + "; type : " + c21.getType());
		
		int clos=rs.getColumns();//�õ����е���
		int rows=rs.getRows();//�õ����е���
		
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
		 
		 
		  
			  //�����Ѿ�������Excel�����������µĿ�д���Excel������
			  jxl.write.WritableWorkbook wwb =
			  Workbook.createWorkbook(new File("C:/Book.xls"),rwb);
			 //��ȡ��һ�Ź����� 
			  jxl.write.WritableSheet ws = wwb.getSheet(0);
			  
			  //��ȡ��һ����Ԫ����� 
			  jxl.write.WritableCell wc = ws.getWritableCell(0, 0); 
			  //���ϵ�Ԫ������ͣ�������Ӧ��ת��
			  if(wc.getType() == CellType.LABEL) { 
				  Label l = (Label) wc;
			  l.setString("The value has been modified.");
			 }
			  //д��Excel����
			  wwb.write();
			  wwb.close();
	}

}
