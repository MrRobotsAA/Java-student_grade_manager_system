package com.javen.excel;

import java.io.File;
import java.sql.ResultSet;
import java.util.List;

import com.struct;
import com.javen.db.DBhepler;
import com.javen.entity.StuEntity;
import com.javen.service.StuService;

import jxl.Cell;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class TestDbToExcel {

	public TestDbToExcel(int ID_t){
		try {
			   DBhepler db=new DBhepler();
			   WritableWorkbook wwb = null;
			   // 创建可写入的Excel工作簿
			   String fileName = "D://tmp_excel.xls";
			   File file=new File(fileName);
			   if (!file.exists()) {
				   file.createNewFile();
			   }
			   //以fileName为文件名来创建一个Workbook
			   wwb = Workbook.createWorkbook(file);

			   // 创建工作表
			   WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
			   
			   //查询数据库中所有的数据
			   struct tmp =   StuService.getAllByDb(ID_t);
			   List<StuEntity> list= tmp.list;
			   //要插入到的Excel表格的行号，默认从0开始
			   Label labelId= new Label(1, 0, "学号");//
			   Label labelName= new Label(0, 0, "姓名");
			   Label labelSex= new Label(2, 0, "性别");
			  // Label labelNum= new Label(3, 0, "班级(num)");
			   ws.addCell(labelId);
			   ws.addCell(labelName);
			   ws.addCell(labelSex);
			 //  ws.addCell(labelNum);
		        int jnum = 3,top =3 ;
		        int book[] = new int[200];
		       int size = tmp.set.size();
			   for(Integer value:tmp.set)
			   {
				   System.out.println("set="+value);
				   String sql2="select * from c_course where course_id='"+value+"'";
				   System.out.println(sql2);
				   ResultSet rs2= db.Search(sql2, null);
				  while(rs2.next())
				  {
					
					   String course_name = rs2.getString("course_name");
					   Label tt = new Label(jnum,0,course_name);
					   ws.addCell(tt);
					   book[top++] = value;
					   jnum++;
				  }
				  
			   }
			   
			   for (int i = 0; i < list.size(); i++) {
				   Label labelId_i= new Label(1, i+1, list.get(i).getId()+"");
				   Label labelName_i= new Label(0, i+1, list.get(i).getName());
				   Label labelSex_i= new Label(2, i+1, list.get(i).getSex());
				//   Label labelNum_i= new Label(3, i+1, list.get(i).getNum()+"");
				   ws.addCell(labelId_i);
				   ws.addCell(labelName_i);
				   ws.addCell(labelSex_i);
				//   ws.addCell(labelNum_i);
				   top= 3;
				   for(int j=0;j<size;j++)
				   {
					   int ID = book[top++];
					   String sql3="select * from c_ccourse where course_id='"+ID+"' and stu_id='"+list.get(i).getId()+"'";
					   System.out.println(sql3);
					   ResultSet rs3= db.Search(sql3, null);
					   while(rs3.next())
					   {
						    int res = rs3.getInt("ccourse_mark");
					   Label labelNum_i= new Label(top-1, i+1,res+"");
					   ws.addCell(labelNum_i);
					   }
					  
				   }
			   }
			  int row = ws.getRows();
			  int col = ws.getColumns();
			  for(int i=0;i<row;i++)
			  {
				  for(int j=0;j<col;j++)
				  {
					  Cell c = null;
					  c = ws.getCell(j,i);
					  String tmp2= c.getContents();
					  if(tmp2.equals(""))
					  {
						  Label p = new Label(j, i, "-1");
						  ws.addCell(p);
					  }
				  }
			  }
			  //写进文档
			   wwb.write();
			  // 关闭Excel工作簿对象
			   wwb.close();
			   System.out.println("导出成功！");
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
