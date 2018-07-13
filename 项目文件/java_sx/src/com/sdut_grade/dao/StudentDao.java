package com.sdut_grade.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sdut_grade.model.ClassModel;
import com.sdut_grade.model.StudentModel;
import com.sdut_grade.util.DBConnection;

public class StudentDao {
	
	/**
	 * @根据id查找本表所有信息
	 * @param stu_id
	 * @return
	 */
	
	public StudentModel getListsByStuId(int stu_id){
		String sql = "select * from c_student where stu_id="+stu_id;
		ResultSet rs = DBConnection.query(sql);
		StudentModel sm = new StudentModel();
		try {
			if(!rs.next()){				
				sm.setStu_id(rs.getInt("stu_id"));
				sm.setStu_name(rs.getString("stu_name"));
				sm.setStu_sex(rs.getString("stu_sex"));
				sm.setClass_id(rs.getInt("class_id"));
				sm.setStu_password(rs.getString("stu_password"));				
			}else 
			{
				rs.close();
				sm = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sm;
	}
	
	/**
	 * @查找学生信息管理模块列表所有信息
	 * @param option 值为true，第二个参数生效
	 * @param stu_id
	 * @return 一条或多条记录
	 */
	public List<StudentModel> getLists(boolean option,int stu_id){
		List<StudentModel> lists = new ArrayList<StudentModel>();
		ClassDao cd = new ClassDao();
		String sql = "";
		if(option){
			sql ="select * from c_student where stu_id="+stu_id;
		}else
			sql = "select * from c_student";
		
		try {
			ResultSet rs = DBConnection.query(sql);
			while(rs.next()){
				StudentModel sm = new StudentModel();
				sm.setStu_id(rs.getInt("stu_id"));
				sm.setStu_name(rs.getString("stu_name"));
				sm.setStu_sex(rs.getString("stu_sex"));
				sm.setStu_password(rs.getString("stu_password"));
				sm.setClass_id(rs.getInt("class_id"));
				sm.setClass_name(cd.getClassNameByClassId(rs.getInt("class_id")));
				
				lists.add(sm);				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
	
	/**
	 * @根据class_id 查找本表所有信息 
	 * @param class_id
	 * @return
	 */
	
	public List<StudentModel> getListsByClassId(int class_id){
		List<StudentModel> lists = new ArrayList<StudentModel>();
		String sql = "select * from c_student where class_id="+class_id;
		
		ResultSet rs = DBConnection.query(sql);
		
		try {
			while(rs.next()){
				StudentModel sm = new StudentModel();
				sm.setStu_id(rs.getInt("stu_id"));
				sm.setStu_name(rs.getString("stu_name"));
				sm.setStu_sex(rs.getString("stu_sex"));
				sm.setClass_id(rs.getInt("class_id"));
				sm.setStu_password(rs.getString("stu_password"));
				lists.add(sm);				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
	public List<StudentModel> getListsByCourseId(int course_id){
		List<StudentModel> lists = new ArrayList<StudentModel>();
		String sql = "select * from c_ccourse where course_id='"+course_id+"'";
		
		ResultSet rs = DBConnection.query(sql);
		
		try {
			while(rs.next()){
		
			//	StudentDao tmp = new StudentDao();
				int stu_id = rs.getInt("stu_id");
				String sql2 = "select * from c_student where stu_id='"+stu_id+"'";
				ResultSet rs2 = DBConnection.query(sql2);
				StudentModel sm = new StudentModel();
				try {
					while(rs2.next()){				
						sm.setStu_id(rs2.getInt("stu_id"));
						sm.setStu_name(rs2.getString("stu_name"));
						sm.setStu_sex(rs2.getString("stu_sex"));
						sm.setClass_id(rs2.getInt("class_id"));
						sm.setStu_password(rs2.getString("stu_password"));	
						//System.out.println(sm);
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lists.add(sm);				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
	/**
	 * @根据id找到对应名称
	 * @param stu_id
	 * @return
	 */
	
	public String getStudentNameById(int stu_id){
		String stu_name="";
		String sql = "select stu_name from c_student where stu_id="+stu_id;
		ResultSet rs = DBConnection.query(sql);
		try {
			if(rs.next()){
				stu_name=rs.getString("stu_name");
			}
			rs.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stu_name;
	}
	/**
	 * @删除学生
	 * @param stu_id
	 * @return
	 */
	
	public boolean deleteListByStuId(int stu_id){
		String sql = "delete from c_student where stu_id="+stu_id;
		return (DBConnection.update(sql)==true)?true:false;
	}
	
	public boolean addStudent(String stu_name,String stu_sex,int class_id){
		String sql="insert into c_student(stu_name,stu_sex,class_id) values('"+stu_name+"','"+stu_sex+"',"+class_id+")";
		return (DBConnection.update(sql)==true)?true:false;
		
	}
	/**
	 * 修改学生信息
	 * @param stu_id
	 * @param stu_name
	 * @param stu_sex
	 * @param class_id
	 * @return
	 */
	public boolean modifyStudentByStuId(int stu_id,String stu_name,String stu_sex,int class_id){
		String sql = "update c_student set stu_name='"+stu_name+"',stu_sex='"+stu_sex+"',class_id="+class_id+" where stu_id="+stu_id;
		return (DBConnection.update(sql)==true)?true:false;
		
	}
	
	public StudentModel getStudentByName(String stu_name){
		StudentModel stu = new StudentModel();
		String sql = "select * from c_student where stu_id="+stu_name;
		ResultSet rs = DBConnection.query(sql);
		try {
			if(rs.next()){
				stu.setClass_id(Integer.parseInt(rs.getString("class_id")));
				stu.setStu_id(Integer.parseInt(rs.getString("stu_id")));
				stu.setStu_name(rs.getString("stu_name"));
				stu.setStu_sex(rs.getString("stu_sex"));
			}
			rs.close();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stu;
	}

}
