package com.sdut_grade.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdut_grade.model.ClassModel;
import com.sdut_grade.util.DBConnection;



public class ClassDao {

	/**
	 * @����class_id ���ұ���������Ϣ
	 * @param class_id
	 * @return ���ݿ���ĳһ����Ϣ
	 */
	
	public ClassModel getListByClassId(int class_id){
		String sql="select * from c_class where class_id="+class_id;
		ClassModel cm = new ClassModel();
		ResultSet rs = DBConnection.query(sql);
		
		try {
			if(rs.next()){
				cm.setClass_id(rs.getInt("class_id"));    
				cm.setClass_name(rs.getString("class_name"));
				cm.setClass_maxnum(rs.getInt("class_maxnum"));
				cm.setGrade_id(rs.getInt("grade_id"));
			}else
				cm = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cm;
		
	}
	
	/**
	 * @��ȡ�������м�¼
	 * @return ��������
	 */
	public List<ClassModel> getLists(){
		String sql = "select * from c_class";
		ResultSet rs = DBConnection.query(sql);
		List<ClassModel> lists = new ArrayList<ClassModel>();

		try {
			while(rs.next()){
				ClassModel cm = new ClassModel();
				cm.setClass_id(rs.getInt("class_id"));    
				cm.setClass_name(rs.getString("class_name"));
				cm.setClass_maxnum(rs.getInt("class_maxnum"));
				cm.setGrade_id(rs.getInt("grade_id"));
				lists.add(cm);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lists;
	}
	
	/**
	 * @ ����grade_id ���ұ������м�¼
	 * @param grade_id
	 * @return ���м�¼
	 */
	
	public List<ClassModel> getListsByGradeId(int grade_id){
		String sql = "select * from c_class where grade_id="+grade_id;
		ResultSet rs = DBConnection.query(sql);
		List<ClassModel> lists = new ArrayList<ClassModel>();

		try {
			while(rs.next()){
				ClassModel cm = new ClassModel();
				cm.setClass_id(rs.getInt("class_id"));    
				cm.setClass_name(rs.getString("class_name"));
				cm.setClass_maxnum(rs.getInt("class_maxnum"));
				cm.setGrade_id(rs.getInt("grade_id"));
				lists.add(cm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lists;
	}
	
	/**
	 * @����class_id ���Ҷ��ڵ�class_name
	 * @param class_id
	 * @return �༶����
	 */
	public String getClassNameByClassId(int class_id){
		String class_name ="";
		
		String sql = "select class_name from c_class where class_id="+class_id;
		ResultSet rs = DBConnection.query(sql);
		try {
			if(rs.next()){
				class_name=rs.getString("class_name");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return class_name;
		
	}
	public int getClassNameByClassName(String name){
		int class_name = 0 ;
		String sql = "select * from c_class where class_name='"+name+"'";
		//System.out.println(sql);
		ResultSet rs = DBConnection.query(sql);
		try {
			if(rs.next()){
				class_name=rs.getInt("class_id");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return class_name;
		
	}
	/**
	 * @����ѧ��id����ѯѧ�����ڰ༶id
	 * @param stu_id
	 * @return
	 */
	public List<String> getListByStuId(int stu_id){
		List<String> list = new ArrayList<String>();
		String sql = "select * from c_student where stu_id ="+stu_id;
		ResultSet rs = DBConnection.query(sql);
		try {
			if(rs.next()){
				list.add(Integer.toString(rs.getInt("class_id")));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean addClass(String class_name,Integer grade_id,Integer max_num){
		String sql = "insert into c_class(class_name,class_maxnum,grade_id) values('"+class_name+"',"+max_num+","+grade_id+")";
		return DBConnection.update(sql)==true?true:false;
	}
	
	public boolean modifyClass(Integer class_id,String class_name,Integer grade_id,Integer max_num){
		String sql = "update c_class set class_name='"+class_name+"',class_maxnum = "+max_num+",grade_id = "+grade_id+" where class_id = "+class_id;
		return DBConnection.update(sql)==true?true:false;
	}
	public boolean deleteListByClassId(int class_id){
		String sql ="delete from c_class where class_id="+class_id;
		return (DBConnection.update(sql))?true:false;
	}
}
