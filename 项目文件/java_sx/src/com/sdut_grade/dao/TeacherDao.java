package com.sdut_grade.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdut_grade.model.StudentModel;
import com.sdut_grade.model.TeacherModel;
import com.sdut_grade.util.DBConnection;

public class TeacherDao {
	
/**
 * @获取本Model信息
 * @param option 值为true  teach_id生效
 * @param teach_id
 * @return
 */
	public List<TeacherModel> getLists(boolean option,int teach_id){
		List<TeacherModel> lists = new ArrayList<TeacherModel>();
		String sql = "";
		if(option){
		sql = "select * from c_teacher where teach_id="+teach_id;
		}else
		{sql = "select * from c_teacher";
		}
		
		ResultSet rs = DBConnection.query(sql);
		
		try {
			while(rs.next()){
				TeacherModel tm = new TeacherModel();
				tm.setTeach_id(rs.getInt("teach_id")) ;
				tm.setTeach_name(rs.getString("teach_name"));
				tm.setTeach_sex(rs.getString("teach_sex"));
				tm.setEducation(rs.getString("teach_education"));
				lists.add(tm);				
			}rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
	/**
	 * @根据id查询对于名称
	 * @param teach_id
	 * @return
	 */
	public String getTeacherNameById(int teach_id){
		String teach_name="";
		String sql = "select teach_name from c_teacher where teach_id="+teach_id;
		ResultSet rs = DBConnection.query(sql);
		try {
			if(rs.next()){
				teach_name=rs.getString("teach_name");
			}rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return teach_name;
	}
	
	/**
	 * @根据id删除教师
	 * @param teach_id
	 * @return
	 */
	public boolean deleteListByTeachId(int teach_id){
		String sql ="delete from c_teacher where teach_id="+teach_id;
		return (DBConnection.update(sql))?true:false;
	}

	/**
	 * @添加教师
	 * @param teach_name
	 * @param teach_sex
	 * @param teach_education
	 * @return
	 */
	public boolean addTeacher(String teach_name,String teach_sex,String teach_education){
		String sql = "insert into c_teacher(teach_name,teach_sex,teach_education) values('"+teach_name+"','"+teach_sex+"','"+teach_education+"')";
		return DBConnection.update(sql)==true?true:false;
	}
	public boolean modifyTeacher(Integer teacher_id,String teacher_name,String teacher_sex,String teacher_education){
		String sql = "update c_teacher set teach_name='"+teacher_name+"',teach_sex='"+teacher_sex+"',teach_education='"+teacher_education+"' where teach_id = "+teacher_id;
		return DBConnection.update(sql)==true?true:false;
	}

}
