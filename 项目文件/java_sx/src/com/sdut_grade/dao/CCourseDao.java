package com.sdut_grade.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sdut_grade.model.CCourseModel;
import com.sdut_grade.util.DBConnection;

public class CCourseDao {
	private int counts = 0;

	public boolean addCCourse(int stu_id, int course_id, String ccourse_time) {

		String sql = "insert into c_ccourse(stu_id,course_id,ccourse_time) values("
				+ stu_id + "," + course_id + ",'" + ccourse_time + "');";
		return DBConnection.update(sql)==true?true:false;
	}
	
	public boolean modifyCCourse(int stu_id, int old_course_id,int new_course_id) {

		String sql = "update  c_ccourse set course_id="+new_course_id+" where stu_id="+stu_id+" and course_id="+old_course_id ;
		return DBConnection.update(sql)==true?true:false;
	}
	
	
	//获取查询中 的记录集总函数
	public int getCounts(){
		return counts;
	}
	
	/**
	 * 
	 * @根据stu_id,course_id 删除选课
	 * @param stu_id
	 * @param course_id
	 */
	public boolean deleteCCourse(int stu_id,int course_id){
		String sql = "delete from c_ccourse where stu_id ="+stu_id+" and course_id = "+course_id;
		return DBConnection.update(sql)==true?true:false;
	}
	
	/**
	 * @添加/修改成绩
	 * @param stu_id
	 * @param course_id
	 * @param ccourse_mark
	 */
	public boolean modifyCCourseAndMark(int stu_id,int course_id,int ccourse_mark){
		String sql ="update  c_ccourse set ccourse_mark="+ccourse_mark+" where stu_id ="+stu_id+" and course_id ="+course_id;
		return DBConnection.update(sql)==true?true:false;
		
	}

	/**
	 * @是否重复选课
	 * @param stu_id
	 * @param course_id
	 * @return true 代表重复
	 */
	public boolean isCCourseExist(int stu_id, int course_id) {
		String sql = "select * from c_ccourse where stu_id =" + stu_id
				+ " and course_id =" + course_id;
		ResultSet rs = DBConnection.query(sql);
		try {
			if (rs.next())
				return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	/**
	 * @根据学号获取选课管理模块数据
	 * @param stu_id
	 * @param isNull true 表示 查询成绩为空的列表
	 * @return 多行记录
	 */
	public List<CCourseModel> getListsByStuId(int stu_id,boolean isNull){
		
		List<CCourseModel> lists = new ArrayList<CCourseModel>();
		String sql ="";
		
		if(isNull){
			sql = "select stu_id,c_course.course_id as course_id,course_name,ccourse_mark from c_course,c_ccourse where c_course.course_id=c_ccourse.course_id and  stu_id ="+stu_id +" and ccourse_mark is null";
		}else{
			sql = "select stu_id,c_course.course_id as course_id,course_name,ccourse_mark from c_course,c_ccourse where c_course.course_id=c_ccourse.course_id and  stu_id="+stu_id;
			
		}
		ResultSet rs = DBConnection.query(sql);
		try {
			while(rs.next()){
				CCourseModel ccm = new CCourseModel();
				ccm.setStu_id(stu_id);
				ccm.setCourse_id(rs.getInt("course_id"));
				ccm.setCourse_name(rs.getString("course_name"));
				ccm.setCcourse_mark(rs.getInt("ccourse_mark"));

				lists.add(ccm);				
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}
	


	/**
	 * @根据id查找对应的名称
	 * @param course_id
	 * @return 课程名称
	 */

	public String getNameByCourseId(int course_id) {
		String course_name = "";

		String sql = "select course_name from c_course where course_id="
				+ course_id;
		ResultSet rs = DBConnection.query(sql);
		try {
			if (rs.next()) {
				course_name = rs.getString("course_name");
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course_name;

	}
	
	
	public List<CCourseModel> getListsByStuId(int stu_id){
		String sql = "select * from c_ccourse where stu_id="+stu_id;
		CourseDao cd = new CourseDao();
		
		List<CCourseModel> lists = new ArrayList<CCourseModel>();
		
	
		try {
			ResultSet rs= DBConnection.query(sql);
			while(rs.next()){
				CCourseModel ccm=new CCourseModel();
				ccm.setCourse_id(rs.getInt("course_id"));
				ccm.setCourse_name(cd.getCourseNameByCourseId(rs.getInt("course_id")));
				lists.add(ccm);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
		
	}
	


	/**
	 * @某门课程中，最高最低平均分
	 * @param course_id
	 * @return
	 */
	public List<String> getThreeMark(int course_id) {
		List<String> lists = new ArrayList<String>();

		String sql = "select max(ccourse_mark) as max_mark,min(ccourse_mark) as min_mark,avg(ccourse_mark) as avg_mark from c_ccourse where course_id="
				+ course_id;
		ResultSet rs = DBConnection.query(sql);
		try {
			if (rs.next()) {
				lists.add(rs.getString("max_mark"));
				lists.add(rs.getString("min_mark"));
				lists.add(rs.getString("avg_mark"));
			}rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;

	}

	/**
	 * @ 某门课程中，优秀率，及格率 以及 男生，女生各多少名
	 * @param course_id
	 * @return 单行数据，List<String> 封装
	 */

	public List<String> getGoodRadio(int course_id) {
		List<String> lists = new ArrayList<String>();
		    // 总人数
		String sql = "select count(*) as counts from c_ccourse where course_id="+ course_id;
		ResultSet rs = DBConnection.query(sql);
		try {
			if (rs.next()) {
				lists.add(rs.getString("counts"));
			}else 
				lists.add("0");
			//大于 90
			
			sql = "select count(ccourse_mark) as counts from c_student,c_ccourse where c_student.stu_id=c_ccourse.stu_id and ccourse_mark>=90 and course_id="+course_id;
			rs = DBConnection.query(sql);
			
			if (rs.next()) {
				lists.add(rs.getString("counts"));
			}else lists.add("0");
			
			// 大于60分人数
			sql = "select count(ccourse_mark) as counts from c_student,c_ccourse where c_student.stu_id=c_ccourse.stu_id and ccourse_mark>=60 and course_id="
					+ course_id;
			rs = DBConnection.query(sql);
			if (rs.next()) {
				lists.add(rs.getString("counts"));
			}else lists.add("0");
			// 此门课程男生人数
			sql = "select count(stu_sex) as counts from c_student,c_ccourse where c_student.stu_id=c_ccourse.stu_id and stu_sex='男' and course_id="+course_id;
			rs = DBConnection.query(sql);

			if (rs.next()) {
				lists.add(rs.getString("counts"));
			}else lists.add("0");
			rs.close();
		} catch (Exception e) {
		}

		return lists;
	}
	
	
	public List<CCourseModel> getCCourseSpecificList(int course_id,int course_counts_index){
		CourseDao cd = new CourseDao();
		StudentDao sd = new StudentDao();
		TeacherDao td = new TeacherDao();
		CCourseDao ccd = new CCourseDao();
		List<CCourseModel> lists = new ArrayList<CCourseModel>();
		String sql = "";
		if(course_counts_index==-1){
			sql = "select * from c_ccourse where course_id="+course_id;
			
			try {
				ResultSet rs = DBConnection.query(sql);
				while(rs.next()){
					counts++;	
					CCourseModel ccm = new CCourseModel();
					int course_id_i = rs.getInt("course_id");
					int stu_id_i = rs.getInt("stu_id");
					ccm.setCcourse_mark(rs.getInt("ccourse_mark"));
					int teach_id_i = cd.getTeachIdByCourseId(course_id_i);//老师id
					
					ccm.setCourse_id(course_id_i);
					ccm.setCourse_name(cd.getCourseNameByCourseId(course_id_i));
					ccm.setStu_id(stu_id_i);
					ccm.setStu_name(sd.getStudentNameById(stu_id_i));
					ccm.setTeach_id(teach_id_i);
					ccm.setTeach_name(td.getTeacherNameById(teach_id_i));
					lists.add(ccm);
				}rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}else if(course_id==-1){
			
			switch(course_counts_index){
			case 0:		
				sql = "select stu_id  from c_student where stu_id not in  (select stu_id from c_ccourse)";	
				try {
						ResultSet rs = DBConnection.query(sql);
						while(rs.next()){
							CCourseModel ccm = new CCourseModel();
							ccm.setStu_id(rs.getInt("stu_id"));
							ccm.setStu_name("-1");
							ccm.setCourse_id(-1);
							ccm.setCourse_name("-1");
							ccm.setTeach_id(-1);
							ccm.setTeach_name("-1");
							ccm.setCcourse_mark(-1);
							lists.add(ccm);
						}rs.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	

				
				break;
			case 1:
				
				sql = "select temp.stu_id,course_id from ((select stu_id,count(*) as counts  from c_ccourse group by stu_id having counts=1) as temp),c_ccourse where c_ccourse.stu_id = temp.stu_id";

					try {
						ResultSet rs1 = DBConnection.query(sql);
						while(rs1.next()){
							CCourseModel ccm = new CCourseModel();
							
							int course_id_i = rs1.getInt("course_id");
							int stu_id_i = rs1.getInt("stu_id");
							
							ccm.setCourse_id(course_id_i);
							ccm.setCourse_name(cd.getCourseNameByCourseId(course_id_i));
							ccm.setStu_id(stu_id_i);
							ccm.setStu_name(sd.getStudentNameById(stu_id_i));
							ccm.setCcourse_mark(ccd.getCCourseMarkByStuIdCourseId(stu_id_i, course_id_i));
							//根据课程id 查找对于教师id和姓名	
							ccm.setTeach_id(cd.getTeachIdByCourseId(course_id_i));
							ccm.setTeach_name(td.getTeacherNameById(cd.getTeachIdByCourseId(course_id_i)));	

							lists.add(ccm);
						}rs1.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				
				break;
			case 2:
				sql = "select temp.stu_id,course_id from ((select stu_id,count(*) as counts  from c_ccourse group by stu_id having counts>=2) as temp),c_ccourse where c_ccourse.stu_id = temp.stu_id";
				
					try {
						ResultSet rs2 = DBConnection.query(sql);
						while(rs2.next()){
							CCourseModel ccm = new CCourseModel();
							
							int course_id_i = rs2.getInt("course_id");
							int stu_id_i = rs2.getInt("stu_id");		
							ccm.setCourse_id(course_id_i);
							ccm.setCourse_name(cd.getCourseNameByCourseId(course_id_i));
							ccm.setStu_id(stu_id_i);
							ccm.setStu_name(sd.getStudentNameById(stu_id_i));
							ccm.setCcourse_mark(ccd.getCCourseMarkByStuIdCourseId(stu_id_i, course_id_i));
							//根据课程id 查找对于教师id和姓名	
							ccm.setTeach_id(cd.getTeachIdByCourseId(course_id_i));
							ccm.setTeach_name(td.getTeacherNameById(cd.getTeachIdByCourseId(course_id_i)));	
							lists.add(ccm);
						}rs2.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				break;

				default:

			}	
			
		}
		return lists;
	}
	
	
	/**
	 * @根据条件，全表模糊查询
	 * @param type 类型
	 * @param query_word 关键字
	 * @param fuzzy_query 是否模糊查询
	 * @return 多行数据，选课模块列表
	 */
	public List<CCourseModel> queryCCourseSpecificList(int type,String query_word,boolean fuzzy_query){
		CourseDao cd = new CourseDao();
		StudentDao sd = new StudentDao();
		TeacherDao td = new TeacherDao();
		List<CCourseModel> lists = new ArrayList<CCourseModel>();
		String sql = "";
		//如果模糊查询，四表连接查询！慎用。
		if(fuzzy_query){
		sql ="select c_course.course_id,course_name,c_ccourse.stu_id as stu_id,stu_name,c_course.teach_id as teach_id,teach_name,ccourse_mark " +
					"from c_student,c_course,c_ccourse,c_teacher " +
					"where   c_student.stu_id = c_ccourse.stu_id and c_ccourse.course_id=c_course.course_id and c_course.teach_id = c_teacher.teach_id " +
					"and (c_course.course_id like '%"+query_word+"%' or course_name like '%"+query_word+"%' or c_ccourse.stu_id like '%"+query_word+"%' or stu_name like '%"+query_word+"%'  or c_course.teach_id like '%"+query_word+"%' or teach_name like '%"+query_word+"%' or ccourse_mark like '%"+query_word+"%')";
		}else{
			
			switch(type){
			case 0:
				//按编号
				sql ="select c_course.course_id,course_name,c_ccourse.stu_id as stu_id,stu_name,c_course.teach_id as teach_id,teach_name,ccourse_mark " +
				"from c_student,c_course,c_ccourse,c_teacher " +
				"where   c_student.stu_id = c_ccourse.stu_id and c_ccourse.course_id=c_course.course_id and c_course.teach_id = c_teacher.teach_id " +
				"and (c_course.course_id like '%"+query_word+"%' or c_ccourse.stu_id like '%"+query_word+"%')";
				
				break;
			case 1:
				sql ="select c_course.course_id,course_name,c_ccourse.stu_id as stu_id,stu_name,c_course.teach_id as teach_id,teach_name,ccourse_mark " +
				"from c_student,c_course,c_ccourse,c_teacher " +
				"where   c_student.stu_id = c_ccourse.stu_id and c_ccourse.course_id=c_course.course_id and c_course.teach_id = c_teacher.teach_id " +
				"and (course_name like '%"+query_word+"%' or stu_name like '%"+query_word+"%' or teach_name like '%"+query_word+"%')";
				break;
			case 2:
				sql ="select c_course.course_id,course_name,c_ccourse.stu_id as stu_id,stu_name,c_course.teach_id as teach_id,teach_name,ccourse_mark " +
				"from c_student,c_course,c_ccourse,c_teacher " +
				"where   c_student.stu_id = c_ccourse.stu_id and c_ccourse.course_id=c_course.course_id and c_course.teach_id = c_teacher.teach_id " +
				"and ccourse_mark like "+query_word+"";
				break;
				default:
					break;
			
			}
			
		}
		try {
			ResultSet rs = DBConnection.query(sql);
			while(rs.next()){
				CCourseModel ccm = new CCourseModel();
				counts++;	
				int course_id_i = rs.getInt("course_id");
				int stu_id_i = rs.getInt("stu_id");
				ccm.setCcourse_mark(rs.getInt("ccourse_mark"));
				int teach_id_i = cd.getTeachIdByCourseId(course_id_i);
				
				ccm.setCourse_id(course_id_i);
				ccm.setCourse_name(cd.getCourseNameByCourseId(course_id_i));
				ccm.setStu_id(stu_id_i);
				ccm.setStu_name(sd.getStudentNameById(stu_id_i));
				ccm.setTeach_id(teach_id_i);
				ccm.setTeach_name(td.getTeacherNameById(teach_id_i));
				lists.add(ccm);
			}rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
		
	}
	
	
	/**
	 * @根据stu_id和course_id获取成绩
	 * @param stu_id
	 * @param course_id
	 * @return 成绩
	 */
	
	public int getCCourseMarkByStuIdCourseId(int stu_id,int course_id){
		int ccourse_mark = 0;
		String sql = "select ccourse_mark from c_ccourse where stu_id="+stu_id+" and course_id="+course_id;
		ResultSet rs = DBConnection.query(sql);
		try {
			if(rs.next()){
				
				ccourse_mark=rs.getInt("ccourse_mark");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ccourse_mark;
	}

	

}
