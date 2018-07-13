package com.javen.service;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import com.javen.db.DBhepler;
import com.javen.entity.StuEntity;
import com.struct;

public class StuService {
	/**
	 * 查询stu表中所有的数据
	 * @return 
	 */
	public static struct getAllByDb(int ID){
		List<StuEntity> list=new ArrayList<StuEntity>();
		Set<Integer> course_all = new TreeSet<Integer>();
		try {
			DBhepler db=new DBhepler();
			String sql="select * from c_student where class_id='"+ID+"'";
			ResultSet rs= db.Search(sql, null);
			while (rs.next()) {
				int id=rs.getInt("stu_id");
				String name=rs.getString("stu_name");
				String sex=rs.getString("stu_sex");
				int num=rs.getInt("class_id");
				String sql2="select * from c_ccourse where stu_id='"+id+"'";
				ResultSet rs2= db.Search(sql2, null);
				while(rs2.next())
				{
					int yy = rs2.getInt("course_id");
					course_all.add(yy);
				}
				//System.out.println(id+" "+name+" "+sex+ " "+num);
				list.add(new StuEntity(id, name, sex, num));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		struct tmp = new struct();
		tmp.list = list;
		tmp.set = course_all;
		return tmp;
	}
	
	/**
	 * 查询指定目录中电子表格中所有的数据
	 * @param file 文件完整路径
	 * @return
	 */
	public static List<StuEntity> getAllByExcel(String file){
		List<StuEntity> list=new ArrayList<StuEntity>();
		try {
			Workbook rwb=Workbook.getWorkbook(new File(file));
			Sheet rs=rwb.getSheet("Test Shee 1");//或者rwb.getSheet(0)
			int clos=rs.getColumns();//得到所有的列
			int rows=rs.getRows();//得到所有的行
			
			System.out.println(clos+" rows:"+rows);
			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < clos; j++) {
					//第一个是列数，第二个是行数
					String id=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
					String name=rs.getCell(j++, i).getContents();
					String sex=rs.getCell(j++, i).getContents();
					String num=rs.getCell(j++, i).getContents();
					
					System.out.println("id:"+id+" name:"+name+" sex:"+sex+" num:"+num);
					list.add(new StuEntity(Integer.parseInt(id), name, sex, Integer.parseInt(num)));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
		
	}
	
	/**
	 * 通过Id判断是否存在
	 * @param id
	 * @return
	 */
	public static boolean isExist(int id){
		try {
			DBhepler db=new DBhepler();
			ResultSet rs=db.Search("select * from stu where id=?", new String[]{id+""});
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		/*List<StuEntity> all=getAllByDb();
		for (StuEntity stuEntity : all) {
			System.out.println(stuEntity.toString());
		}*/
		
		System.out.println(isExist(1));
		
	}
	
}
