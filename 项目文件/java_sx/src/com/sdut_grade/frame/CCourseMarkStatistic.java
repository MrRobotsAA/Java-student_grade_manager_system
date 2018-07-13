package com.sdut_grade.frame;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.demo;
import com.sdut_grade.dao.CCourseDao;
import com.sdut_grade.dao.CourseDao;
import com.sdut_grade.frame.MainFrame.btnListener;
import com.sdut_grade.model.CourseModel;
import com.sdut_grade.util.About_syjc;



public class CCourseMarkStatistic extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel3 = null;
	private JComboBox jComboBox_course = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel_course_max = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel_course_min = null;
	private JLabel jLabel6 = null;
	private JLabel jLabel_course_avg = null;
	private JLabel jLabel7 = null;
	private JLabel jLabel_course_good = null;
	private JLabel jLabel8 = null;
	private JLabel jLabel_course_ok = null;
	private JLabel jLabel_course_counts = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel11 = null;
	private JLabel jLabel_course_boy = null;
	private JLabel jLabel_course_girl = null;
	private JPanel jPanel = null;
	private JPanel jPanel1 = null;
	private JButton jButton_export = null;

	private List<CourseModel> course_lists; 
	
	private List<String> course_threeMark;  
	private List<String> course_goodRadio;
	
	
	private void initialize() {
		this.setSize(522, 250);
		
		this.setTitle("成绩统计");
		this.setModal(true);
		this.setLocationRelativeTo(null);
		
		jLabel_course_girl = new JLabel();
		jLabel_course_girl.setText("");
		jLabel_course_girl.setBounds(new Rectangle(240, 88, 43, 25));
		jLabel_course_boy = new JLabel();
		jLabel_course_boy.setText("");
		jLabel_course_boy.setBounds(new Rectangle(239, 60, 42, 25));
		jLabel11 = new JLabel();
		jLabel11.setText("女生：");
		jLabel11.setBounds(new Rectangle(191, 89, 45, 25));
		jLabel10 = new JLabel();
		jLabel10.setText("男生：");
		jLabel10.setBounds(new Rectangle(192, 60, 45, 25));
		jLabel_course_counts = new JLabel();
		jLabel_course_counts.setText("JLabel");
		jLabel_course_counts.setBounds(new Rectangle(224, 23, 55, 25));
		jLabel_course_ok = new JLabel();
		jLabel_course_ok.setText("");
		jLabel_course_ok.setBounds(new Rectangle(148, 92, 40, 21));
		jLabel8 = new JLabel();
		jLabel8.setText("及格率（60分以上）：");
		jLabel8.setBounds(new Rectangle(10, 89, 136, 24));
		jLabel_course_good = new JLabel();
		jLabel_course_good.setText("");
		jLabel_course_good.setBounds(new Rectangle(147, 58, 44, 26));
		jLabel7 = new JLabel();
		jLabel7.setText("优秀率（90分以上）：");
		jLabel7.setBounds(new Rectangle(10, 57, 137, 25));
		jLabel_course_avg = new JLabel();
		jLabel_course_avg.setText("");
		jLabel_course_avg.setBounds(new Rectangle(66, 89, 107, 25));
		jLabel6 = new JLabel();
		jLabel6.setText("平均分：");
		jLabel6.setBounds(new Rectangle(4, 89, 56, 27));
		jLabel_course_min = new JLabel();
		jLabel_course_min.setText("");
		jLabel_course_min.setBounds(new Rectangle(68, 58, 103, 26));
		jLabel5 = new JLabel();
		jLabel5.setText("最低分：");
		jLabel5.setBounds(new Rectangle(3, 55, 58, 29));
		jLabel_course_max = new JLabel();
		jLabel_course_max.setText("");
		jLabel_course_max.setBounds(new Rectangle(65, 28, 105, 23));
		jLabel4 = new JLabel();
		jLabel4.setText("最高分：");
		jLabel4.setBounds(new Rectangle(3, 29, 52, 18));
		jLabel3 = new JLabel();
		jLabel3.setText("课程选择：");
		jLabel3.setBounds(new Rectangle(12, 25, 71, 21));
		jLabel = new JLabel();
		jLabel.setText("不同班级年级的学生可以选择同一门课程，只能按课程统计。");
		jLabel.setFont(new Font("粗体",Font.BOLD , 12));
		jLabel.setBounds(new Rectangle(8, 8, 360, 22));
		


		
		jComboBox_course = new JComboBox();
		jComboBox_course.setBounds(new Rectangle(85, 24, 137, 24));
		
		CourseDao cd = new CourseDao();
		course_lists = cd.getLists(false, -1);
		for(int i=0;i<course_lists.size();i++){
			
			jComboBox_course.addItem(course_lists.get(i).getCourse_name());
		}	
		
		
		
		jButton_export = new JButton();
		jButton_export.setText("重修学生名单");
		jButton_export.setBounds(new Rectangle(371, 8, 97, 22));
		
		jPanel = new JPanel();
		jPanel.setLayout(null);
		jPanel.setBounds(new Rectangle(293, 32, 177, 120));
		jPanel.add(jLabel4, null);
		jPanel.add(jLabel_course_max, null);
		jPanel.add(jLabel5, null);
		jPanel.add(jLabel_course_min, null);
		jPanel.add(jLabel6, null);
		jPanel.add(jLabel_course_avg, null);
		jPanel.setBorder(BorderFactory.createTitledBorder("高低分统计"));

		jPanel1 = new JPanel();
		jPanel1.setLayout(null);
		jPanel1.setBounds(new Rectangle(-2, 29, 296, 124));
		jPanel1.setBorder(BorderFactory.createTitledBorder("课程选择"));
		jPanel1.add(jLabel7, null);
		jPanel1.add(jLabel_course_good, null);
		jPanel1.add(jLabel10, null);
		jPanel1.add(jLabel_course_boy, null);
		jPanel1.add(jLabel_course_girl, null);
		jPanel1.add(jLabel11, null);
		jPanel1.add(jLabel_course_ok, null);
		jPanel1.add(jLabel8, null);
		jPanel1.add(jLabel_course_counts, null);
		jPanel1.add(jComboBox_course, null);
		jPanel1.add(jLabel3, null);
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jLabel, null);
		jContentPane.add(jPanel, null);
		jContentPane.add(jPanel1, null);
		btnListener btn = new btnListener();
		jButton_export.addActionListener(btn);
		jContentPane.add(jButton_export, null);
		this.setContentPane(jContentPane);
	}
	
	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jButton_export) {
				dispose();
				//System.out.println("成功");
				 String course_name = (String) jComboBox_course.getSelectedItem();
			  CourseDao exp = new CourseDao();
				int uu = exp.getCourseNameByCourseName(course_name);
				 new demo(uu).setVisible(true);
			}
		}
	}


	public void flashData(){
		
		
		if(jComboBox_course.getSelectedIndex()==-1)
			return;
		
		int index = jComboBox_course.getSelectedIndex();
		int course_id = course_lists.get(index).getCourse_id();
		
		jLabel_course_counts.setText("编号："+Integer.toString(course_id));
		
		CCourseDao ccd = new CCourseDao();
		course_threeMark = ccd.getThreeMark(course_id);
		jLabel_course_max.setText(course_threeMark.get(0));
		jLabel_course_min.setText(course_threeMark.get(1));
		jLabel_course_avg.setText(course_threeMark.get(2));
		
		course_goodRadio = ccd.getGoodRadio(course_id);

		
		int totalCounts = Integer.parseInt(course_goodRadio.get(0));
		if(totalCounts==0)
			return;
		DecimalFormat df = new DecimalFormat("#,##0.00");//保留两位小数且不用科学计数法
		jLabel_course_good.setText(df.format(Integer.parseInt(course_goodRadio.get(1))*1.0/totalCounts*100)+"%");
		jLabel_course_ok.setText(df.format(Integer.parseInt(course_goodRadio.get(2))*1.0/totalCounts*100)+"%");
		
		jLabel_course_boy.setText(course_goodRadio.get(3)+"个");
		jLabel_course_girl.setText(totalCounts-Integer.parseInt(course_goodRadio.get(3))+"个");
		
		
	}
	
	public CCourseMarkStatistic() {
		super();
		initialize();
		flashData();
		jComboBox_course.addItemListener(new itemListener_course());
	}
	
	
	public class itemListener_course implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				flashData();
				}
			}

		}

	

} 
