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
 * ������
 * ����JTable��ֱ�Ӵ������
 * �������õ�����ͳ����ʽ֮һ����swing���� JTable��ʵ�ֱ��
 * �������
 * JTable���г���Ĭ�ϵĹ��췽��֮�⣬���ṩ������ָ�������������ͱ���������鴴�����Ĺ��췽��
 * 
 * JTable(Object[][] rowData,Object[] columnNames)
 * rowData:��װ������ݵ�����
 * columnNames:��װ�������������
 * ��ʹ�ñ��ʱ��ͨ��������ӵ���������У�Ȼ�󽫹��������ӵ���Ӧ��λ��
 */
public class demo extends JFrame {

	public List<StudentModel> tmp;

	public demo(int yy) {
		super();
		CourseDao tools  = new CourseDao();
		ClassDao tool2 = new ClassDao();
		setTitle(tools.getCourseNameByCourseId(yy)+"����ѧ������");
		setBounds(300, 160, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		StudentDao stu  = new StudentDao();
		CCourseDao tool = new CCourseDao();
		tmp = stu.getListsByCourseId(yy);
	
		
		// ��������������
				String[][] tableValues = new String [200][200];
				//	{ { "A1", "B1" }, { "A2", "B2" }, { "A3", "B3" }, { "A4", "B4" }, { "A5", "B5" } };
		// ��������������
		String[] columnNames = { "����", "ѧ��","�Ա�","�༶","�ɼ�" };
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
		
		// ����ָ�����������ݵı��
		JTable table = new JTable(tableValues, columnNames);
		// ������ʾ���Ĺ������
		JScrollPane scrollpane = new JScrollPane(table);
		// �����������ӵ��߽粼�ֵ��м�
		getContentPane().add(scrollpane, BorderLayout.CENTER);
		//this.setResizable(false);
		//this.setSize(296, 356);
		//this.setSize(350, 450);
		this.setTitle("��ӭ��½");
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
 * ���н�� ��С����߶�ʱ�����ֹ�����
 */