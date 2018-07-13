package com.sdut_grade.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdut_grade.model.TeacherModel;
import com.sdut_grade.model.UserModel;
import com.sdut_grade.util.DBConnection;

public class UserDao {
	
	/**
	 * �û���¼login_user_type Ϊ0��ʾ����Ϊ1��ʾ��ʦ��Ϊ2��ʾѧ��
	 * @param login_user_type
	 * @param user_name ,�����ѧ����ʹ��id��¼
	 * @param password
	 * @return
	 */
	
	public boolean userLogin(int login_user_type,String user_name,String password){
		String sql = "";
		switch(login_user_type){
		case 0:
			//����Ա��¼
			sql = "select * from c_user where user_name='" + user_name+ "' and user_password='" + password + "'";
			break;
		case 1:
			//��ʦ��¼
			sql = "select * from c_teacher where teach_id=" + user_name+ " and teach_password='" + password + "'";
			break;
		case 2:
			//ѧ��ʹ��id��¼
			sql = "select * from c_student where stu_id=" + user_name+ " and stu_password='" + password + "'";
			break;
		
		}
		try {
			ResultSet rs = DBConnection.query(sql);
			if(rs.next())
			{
				rs.close();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
		public List<UserModel> getLists(boolean option,int teach_id){
			List<UserModel> lists = new ArrayList<UserModel>();
			String sql = "";
			if(option){
			sql = "select * from c_user where user_id="+teach_id;
			}else
			{sql = "select * from c_user";
			}
			
			ResultSet rs = DBConnection.query(sql);
			
			try {
				while(rs.next()){
					UserModel um = new UserModel();
					um.setUser_id(rs.getInt("user_id"));
					um.setUser_name(rs.getString("user_name"));
					um.setUser_login_ip(rs.getString("user_login_ip"));
					um.setUser_login_time(rs.getString("user_login_time"));
					um.setUser_password(rs.getString("user_password"));
					um.setUser_privilege(rs.getString("user_privilege"));
					lists.add(um);				
				}rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return lists;
		}

		/**
		 * @ɾ���û�
		 * @param user_id
		 * @return
		 */
		public boolean deleteListByUserId(int user_id){
			String sql ="delete from c_user where user_id="+user_id;
			return (DBConnection.update(sql))?true:false;
		}
		/**
		 * @����û�
		 * @param user_name
		 * @param user_password
		 * @param user_privilege
		 * @return
		 */
		public boolean addUser(String user_name,String user_password,String user_privilege){
			String sql = "insert into c_user(user_name,user_password,user_privilege) values('"+user_name+"','"+user_password+"','"+user_privilege+"')";
			return DBConnection.update(sql)==true?true:false;
		}
		/**
		 * @�޸��û�
		 * @param user_name
		 * @param user_password
		 * @param user_privilege
		 * @return
		 */
		public boolean modifyUser(String user_name,String user_password,String user_privilege){
			String sql = "update c_user set user_name='"+user_name+"',user_password='"+user_password+"',user_privilege='"+user_privilege+"'";
			return DBConnection.update(sql)==true?true:false;
		}
		/**
		 * @�޸�����
		 * @param option ֵΪ0��ʾ�޸Ĺ���Ա,1�޸���ʦ��2�޸�ѧ��
		 * @param name_or_id ѧ�������Աid
		 * @param old_password
		 * @param new_password
		 * @return
		 */
		public boolean changeUserPassword(int option,String name_or_id,String old_password,String new_password){
			String sql = "";
			if(option==0){
				sql = "update c_user set user_password = '"+new_password+"' where user_name ='"+name_or_id+"' and user_password='"+old_password+"'";
			}else if(option==2)
			{sql = "update c_student set student_password='"+new_password+"' where student_id="+name_or_id;
				
			}
			return DBConnection.update(sql)==true?true:false;
		}
		
		/**
		 * @����û��Ƿ����� 
		 * @param user_name
		 * @return ����true ��ʾ��������
		 */
		public boolean isExist(String user_name){
			String sql = "select * from c_user where user_name='"+user_name+"'";
			ResultSet rs = DBConnection.query(sql);
			try {
				if(rs.next()){
					rs.close();
					return true;

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
			
		}

		
		/**
		 * @�����û����ҳ�id
		 * @param user_name
		 * @return
		 */
		public int getUserIdByUserName(String user_name){
			int user_id=0;
			String sql = "select user_id from c_user where user_name='"+user_name+"'";
			ResultSet rs = DBConnection.query(sql);
			try {
				if(rs.next()){
					user_id = rs.getInt("user_id");
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return user_id;
		}
		
		

}
