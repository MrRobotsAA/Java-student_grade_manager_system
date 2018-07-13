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
	 * 用户登录login_user_type 为0表示管理，为1表示教师，为2表示学生
	 * @param login_user_type
	 * @param user_name ,如果是学生，使用id登录
	 * @param password
	 * @return
	 */
	
	public boolean userLogin(int login_user_type,String user_name,String password){
		String sql = "";
		switch(login_user_type){
		case 0:
			//管理员登录
			sql = "select * from c_user where user_name='" + user_name+ "' and user_password='" + password + "'";
			break;
		case 1:
			//教师登录
			sql = "select * from c_teacher where teach_id=" + user_name+ " and teach_password='" + password + "'";
			break;
		case 2:
			//学生使用id登录
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
		 * @删除用户
		 * @param user_id
		 * @return
		 */
		public boolean deleteListByUserId(int user_id){
			String sql ="delete from c_user where user_id="+user_id;
			return (DBConnection.update(sql))?true:false;
		}
		/**
		 * @添加用户
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
		 * @修改用户
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
		 * @修改密码
		 * @param option 值为0表示修改管理员,1修改老师，2修改学生
		 * @param name_or_id 学生或管理员id
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
		 * @检查用户是否重名 
		 * @param user_name
		 * @return 返回true 表示存在重名
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
		 * @根据用户名找出id
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
