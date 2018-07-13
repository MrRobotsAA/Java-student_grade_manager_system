package com.sdut_grade.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdut_grade.model.CCourseModel;
import com.sdut_grade.model.CourseModel;
import com.sdut_grade.model.StudentModel;
import com.sdut_grade.util.DBConnection;

public class CourseDao {

	/**
	 * 
	 * @param option 值为true 第二个参数生效
	 * @param course_id
	 * @return return 本Bean 记录集
	 */
	public List<CourseModel> getLists(boolean option,int course_id){
		List<CourseModel> lists = new ArrayList<CourseModel>();
		String sql ="";
		if(option){
		sql = "select * from c_course where course_id="+course_id;
		}else{
		sql = "select * from c_course";
		}
		
		ResultSet rs = DBConnection.query(sql);
		TeacherDao td = new TeacherDao();
		try {
			while(rs.next()){
				CourseModel cm = new CourseModel();
				cm.setCourse_id(rs.getInt("course_id"));
				cm.setCourse_name(rs.getString("course_name"));
				cm.setCourse_info(rs.getString("course_info"));
				cm.setCourse_credit(rs.getInt("course_credit"));
				cm.setCourse_begin_time(rs.getString("course_begin_time"));
				
				cm.setCourse_info(rs.getString("course_info"));
				cm.setTeach_id(rs.getInt("teach_id"));
				cm.setTeach_name(td.getTeacherNameById(rs.getInt("teach_id")));
				
				lists.add(cm);				
			}rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
	
	
	/**
	 * 查找某位老师所教的所有课程
	 * @return
	 */
	
	public List<CourseModel> getListsByTeachId(int teach_id){
		List<CourseModel> lists = new ArrayList<CourseModel>();
		String sql = "select * from c_course where teach_id="+teach_id;
		ResultSet rs = DBConnection.query(sql);
		try {
			while(rs.next()){
				CourseModel cm = new CourseModel();
				cm.setCourse_id(rs.getInt("course_id"));
				cm.setCourse_name(rs.getString("course_name"));
				cm.setCourse_info(rs.getString("course_info"));
				cm.setCourse_credit(rs.getInt("course_credit"));
				cm.setCourse_begin_time(rs.getString("course_begin_time"));
				lists.add(cm);				
			}rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
	
	
	/**
	 * @根据id查找对于的名称
	 * @param course_id
	 * @return
	 */
	public String getCourseNameByCourseId(int course_id){
		String course_name="";
		String sql = "select course_name from c_course where course_id="+course_id;
		ResultSet rs = DBConnection.query(sql);
		try {
			if(rs.next()){
				course_name=rs.getString("course_name");
			}rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course_name;
	}
	public int getCourseNameByCourseName(String name){
		int  course_name= 0;
		String sql = "select * from c_course where course_name='"+name+"'";
		ResultSet rs = DBConnection.query(sql);
		try {
			if(rs.next()){
				course_name=rs.getInt("course_id");
			}rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course_name;
	}

	/**
	 * @根据课程id查找对于老师id
	 * @param course_id
	 * @return teach_id
	 */
	public int getTeachIdByCourseId(int course_id){
		int teach_id =0;
		String sql = "select teach_id from c_course where course_id="+course_id;
		ResultSet rs = DBConnection.query(sql);
		try {
			if(rs.next()){
				teach_id=rs.getInt("teach_id");
			}rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teach_id;
	}
	/**
	 * @删除课程
	 * @param course_id
	 * @return
	 */
	public boolean deleteListByCourseId(int course_id){
		String sql ="delete from c_course where course_id="+course_id;
		return (DBConnection.update(sql))?true:false;
	}
	
	/**
	 * @添加课程信息
	 * @param course_name
	 * @param course_credit
	 * @param course_info
	 * @param course_begin_time
	 * @param teach_id
	 * @return
	 */
	public boolean addCourse(String course_name,int course_credit,String course_info,String course_begin_time,int teach_id){
		String sql = "insert into c_course(course_name,course_credit,course_info,course_begin_time,teach_id) values('"+course_name+"',"+course_credit+",'"+course_info+"','"+course_begin_time+"',"+teach_id+")";
		return DBConnection.update(sql)==true?true:false;
	}
	public boolean modifyCourse(int course_id,String course_name,int course_credit,int teach_id,String course_info,String course_begin_time){
		String sql = "update c_course set course_name='"+course_name+"',course_credit="+course_credit+",course_info='"+course_info+"',teach_id="+teach_id + ",course_begin_time='"+course_begin_time+"' where course_id="+course_id;
		return DBConnection.update(sql)==true?true:false;
	}
}
