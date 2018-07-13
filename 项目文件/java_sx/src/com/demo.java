package com;

import java.awt.BorderLayout;
import java.awt.Dialog.ModalityType;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import com.sdut_grade.dao.CCourseDao;
import com.sdut_grade.dao.ClassDao;
import com.sdut_grade.dao.CourseDao;
import com.sdut_grade.dao.StudentDao;
import com.sdut_grade.model.StudentModel;

/*
 * 表格组件
 * 利用JTable类直接创建表格
 * 表格是最常用的数据统计形式之一，在swing中由 JTable类实现表格。
 * 创建表格
 * JTable类中除了默认的构造方法之外，还提供了利用指定表格列名数组和表格数据数组创建表格的构造方法
 * 
 * JTable(Object[][] rowData,Object[] columnNames)
 * rowData:封装表格数据的数组
 * columnNames:封装表格列名的数组
 * 在使用表格时，通常将其添加到滚动面板中，然后将滚动面板添加到相应的位置
 */
public class demo extends JFrame {

	public List<StudentModel> tmp;

	public demo(int yy) {
		super();
		CourseDao tools  = new CourseDao();
		ClassDao tool2 = new ClassDao();
		setTitle(tools.getCourseNameByCourseId(yy)+"重修学生名单");
		setBounds(300, 160, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StudentDao stu  = new StudentDao();
		CCourseDao tool = new CCourseDao();
		tmp = stu.getListsByCourseId(yy);
	
		
		// 定义表格数据数组
				String[][] tableValues = new String [200][200];
				//	{ { "A1", "B1" }, { "A2", "B2" }, { "A3", "B3" }, { "A4", "B4" }, { "A5", "B5" } };
		// 定义表格列名数组
		String[] columnNames = { "姓名", "学号","性别","班级","成绩" };
		int size = tmp.size();
		System.out.println(size);
		for(StudentModel sty:tmp)
		{
			System.out.println(sty);
		}
		for(int i=0;i<size;i++)
		{
			System.out.println(tmp.get(i));
			int tmp_id = tmp.get(i).getStu_id();
		    int hh = tool.getCCourseMarkByStuIdCourseId(tmp_id, yy);
		    if(hh>=60) { 
		    	tmp.remove(i);
		    	size--;
                i--;		    
		    }
		}
	   
		size = tmp.size();
		for(int i=0;i<size;i++)
		{
			    int tmp_id = tmp.get(i).getStu_id();
			    int hh = tool.getCCourseMarkByStuIdCourseId(tmp_id, yy);
				tableValues[i][0] = tmp.get(i).getStu_name();
				tableValues[i][1] = tmp.get(i).getStu_id().toString();
				tableValues[i][2] = tmp.get(i).getStu_sex();
			    int tmp_d = tmp.get(i).getClass_id();
				tableValues[i][3] = tool2.getClassNameByClassId(tmp_d); 
				tableValues[i][4] = Integer.toString(hh);
				
		}
		
		// 创建指定列名和数据的表格
		JTable table = new JTable(tableValues, columnNames);
		// 创建显示表格的滚动面板
		JScrollPane scrollpane = new JScrollPane(table);
		// 将滚动面板添加到边界布局的中间
		getContentPane().add(scrollpane, BorderLayout.CENTER);
		//this.setResizable(false);
		//this.setSize(296, 356);
		//this.setSize(350, 450);
		this.setTitle("欢迎登陆");
		//imgURL = this.getClass().getResource("/com/sdut_grade/images/logo_0.png");
		//this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgURL));
		//this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

	}

}
/*
 * 运行结果 调小窗体高度时，出现滚动条
 */