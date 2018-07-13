package com.sdut_grade.frame;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.sql.ResultSet;
import java.util.List;

import com.sdut_grade.dao.CCourseDao;
import com.sdut_grade.dao.CourseDao;
import com.sdut_grade.dao.TeacherDao;
import com.sdut_grade.model.CCourseModel;
import com.sdut_grade.model.CourseModel;
import com.sdut_grade.model.TeacherModel;
import com.sdut_grade.util.DBConnection;

import javax.swing.JCheckBox;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

public class CCourseManage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JButton jButton_ccourse_open = null;
	private JButton jButton_ccourse_print = null;
	private JLabel jLabel_course_counts = null;
	public int total;
	private JLabel jLabel = null;
	private JLabel jLabel3 = null;
	private JComboBox jComboBox_query_condition = null;
	private JTextField jTextField_query_word = null;
	private JButton jButton_course_query = null;
	private JLabel jLabel4 = null;
	private JComboBox jComboBox_course_condition = null;
	private JButton jButton_course_delete = null;
	private JPanel jPanel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel5 = null;
	private JComboBox jComboBox_course_name = null;
	private JButton jButton_course_modify = null;
	private JButton jButton_reflash = null;
	private JLabel jLabel_student_name = null;
	private JLabel jLabel6 = null;
	private JComboBox jComboBox_course_counts = null;
	
	private List<CourseModel> course_lists;
	private List<TeacherModel> teach_lists; 
	private List<CCourseModel> ccourse_lists;
	
	
	DefaultTableModel model = new DefaultTableModel(); 
	
	private boolean isFirstModify = true;
//	private int counts = 0;//记录数
	private JCheckBox jCheckBox_teach_filter = null;
	private JCheckBox jCheckBox_fuzzy_query = null;

	/**
	 * This is the default constructor
	 */
	public CCourseManage() {
		super();
		this.setModal(true);
		initialize();
	
		jComboBox_course_condition.addItemListener(new itemListener_course());
		jComboBox_course_counts.addItemListener(new itemListener_course());
		jButton_course_query.addActionListener(new btnListener());
		jButton_ccourse_open.addActionListener(new btnListener());
		//jButton_ccourse_print.addActionListener(new btnListener());
		jButton_course_query.addActionListener(new btnListener());
		jButton_course_delete.addActionListener(new btnListener());
		jButton_course_modify.addActionListener(new btnListener());
		jButton_reflash.addActionListener(new btnListener());
		jCheckBox_teach_filter.addItemListener(new checkListener());
		jCheckBox_fuzzy_query.addItemListener(new checkListener());
		jTable.getSelectionModel()
				.addListSelectionListener(new tableListener());// jTable选中监听！

	}

	private void initialize() {
	//  BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
		this.setSize(716, 612);
		getContentPane().setVisible(false);
		this.setContentPane(getJContentPane());
		this.setTitle("选课管理");
		this.setBackground(Color.white);
		this.setForeground(Color.white);
		this.setLocationRelativeTo(null);
	//	this.setModal(true);
		this.setLocationRelativeTo(null);
		
	   initTableDataByCombo();
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel6 = new JLabel();
			jLabel6.setBounds(new Rectangle(306, 24, 95, 24));
			jLabel6.setText("学生选课门数：");
			jLabel6.setFont(new Font("粗体",Font.BOLD,12));
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(553, 22, 56, 26));
			jLabel1.setText("记录数：");
			jLabel1.setFont(new Font("粗体",Font.BOLD,12));
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(19, 25, 105, 24));
			jLabel4.setText("按课程名称列举：");
			jLabel4.setFont(new Font("粗体",Font.BOLD,12));
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(19, 63, 65, 18));
			jLabel3.setText("查询方式：");
			jLabel3.setFont(new Font("粗体",Font.BOLD,12));
			jLabel = new JLabel();
			jLabel.setText("学生姓名：");
			jLabel.setBounds(new Rectangle(13, 21, 68, 25));
			jLabel1.setFont(new Font("粗体",Font.BOLD,12));
			
			
			jComboBox_query_condition = new JComboBox();
			jComboBox_query_condition.addItem("按ID");
			jComboBox_query_condition.addItem("按名称");
			jComboBox_query_condition.addItem("按成绩");
			
			jComboBox_query_condition.setBounds(new Rectangle(105, 56, 159, 27));
			

			
	
	jComboBox_course_name = new JComboBox();
	jComboBox_course_name.setBounds(new Rectangle(299, 21, 167, 30));
	jComboBox_course_name.setEnabled(false);


	jButton_course_modify = new JButton();
	jButton_course_modify.setBounds(new Rectangle(162, 58, 144, 27));
	jButton_course_modify.setText("修改选课");
	jButton_course_modify.setFont(new Font("粗体",Font.BOLD,12));



	jButton_reflash = new JButton();
	jButton_reflash.setBounds(new Rectangle(509, 20, 99, 26));
	jButton_reflash.setText("刷新数据");
	jButton_reflash.setFont(new Font("粗体",Font.BOLD,12));

	jComboBox_course_counts = new JComboBox();
	jComboBox_course_counts.setBounds(new Rectangle(413, 24, 122, 23));
	jComboBox_course_counts.setEnabled(false);
	jComboBox_course_counts.addItem("还没选课学生");
	jComboBox_course_counts.addItem("选一门课程");
	jComboBox_course_counts.addItem("选两门或以上");
	
	
	jComboBox_course_condition = new JComboBox();
	jComboBox_course_condition.setBounds(new Rectangle(130, 24, 134, 26));
	

	
	//初始化下拉框数据
	CourseDao cd = new CourseDao();
	course_lists = cd.getLists(false, -1);
	for(int i=0;i<course_lists.size();i++){
		jComboBox_course_condition.addItem(course_lists.get(i).getCourse_name());
	}
	
	for(int i=0;i<course_lists.size();i++){
		jComboBox_course_name.addItem(course_lists.get(i).getCourse_name());
	}
	
//	TeacherDao td = new TeacherDao();
//	teach_lists = td.getTeacherModelList();
//	for(int i =0;i<teach_lists.size();i++){
//		jComboBox_course_counts.addItem(teach_lists.get(i).getTeach_name());
//	}
	
	
	
	
	jTextField_query_word = new JTextField();
	jTextField_query_word.setBounds(new Rectangle(274, 56, 139, 28));

	jButton_course_query = new JButton();
	jButton_course_query.setBounds(new Rectangle(539, 56, 97, 28));
	jButton_course_query.setText("查询");
	jButton_course_query.setFont(new Font("粗体",Font.BOLD,12));


	jButton_course_delete = new JButton();
	jButton_course_delete.setText("删除选课");
	jButton_course_delete.setFont(new Font("粗体",Font.BOLD,12));
	jButton_course_delete.setBounds(new Rectangle(372, 58, 92, 27));
	
	if(Login.login_user_type == 2){
		
	}
	
			
	jTable = new JTable();

	jScrollPane = new JScrollPane();
	jScrollPane.setViewportView(jTable);
	jScrollPane.setBounds(new Rectangle(20, 90, 620, 300));
	
	
	String heads[] = {"课程ID","课程名称","学生ID","学生名称","教师ID","教师名称","课程成绩"};
	model = new DefaultTableModel(null, heads);	
			
			
	
	jLabel_student_name = new JLabel();
	jLabel_student_name.setBounds(new Rectangle(97, 19, 90, 25));
	jLabel_student_name.setText("");
	jLabel5 = new JLabel();
	jLabel5.setBounds(new Rectangle(209, 20, 74, 26));
	jLabel5.setText("所选课程：");
	jLabel5.setFont(new Font("粗体",Font.BOLD,12));
	jPanel = new JPanel();
	jPanel.setLayout(null);
	jPanel.setBounds(new Rectangle(17, 403, 619, 105));
	jPanel.setBorder(BorderFactory.createTitledBorder("操作面板"));
	jPanel.setBackground(Color.white);
	jPanel.setForeground(Color.white);
	getContentPane().setVisible(false);
	 this.setBackground(Color.WHITE);

	//jPanel.add(getjButton_ccourse_print(), null);
	jPanel.add(jLabel, null);
	jPanel.add(getJButton_ccourse_open(), null);
	jPanel.add(jButton_course_delete, null);
	jPanel.add(jLabel5, null);
	jPanel.add(jComboBox_course_name, null);
	jPanel.add(jButton_course_modify, null);
	jPanel.add(jButton_reflash, null);
	jPanel.add(jLabel_student_name, null);
	jPanel.setBackground(Color.white);
	
	
			jContentPane = new JPanel();
//    	    jContentPane.putClientProperty("ToolBar.isPaintPlainBackground", Boolean.TRUE);
		//	jContentPane.setBackground(Color.blue);
			jContentPane.setForeground(Color.white);
			//jContentPane.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
			jContentPane.setLayout(null);
			jContentPane.setBorder(BorderFactory.createTitledBorder("选课信息一览表"));
			jContentPane.setFont(new Font("粗体",Font.BOLD,12));
			jContentPane.add(jScrollPane, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jComboBox_query_condition, null);
			jContentPane.add(jTextField_query_word, null);
			jContentPane.add(jButton_course_query, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jComboBox_course_condition, null);
			jContentPane.add(jPanel, null);
			jContentPane.add(getJLabel_course_counts(), null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel6, null);
			jContentPane.add(jComboBox_course_counts, null);
			jContentPane.add(getJCheckBox_teach_filter(), null);
			jContentPane.add(getJCheckBox_fuzzy_query(), null);
		}
		return jContentPane;
	}
	
	/**
	 * @通过点击下拉框刷新table数据
	 */
	
	public void initTableDataByCombo() {
			int course_index = jComboBox_course_condition.getSelectedIndex();
			if(course_index==-1){
				return;	
			}	
			int course_id = course_lists.get(course_index).getCourse_id();
			CCourseDao ccd = new CCourseDao();
			ccourse_lists = ccd.getCCourseSpecificList(course_id, -1);	
			flashTableData();
	}
	/**
	 * @通过改变ccourse_lists 数据 刷新table
	 */
	public void flashTableData(){
		model.setRowCount(ccourse_lists.size());// 设置行数
		for (int i= 0; i<ccourse_lists.size(); i++) {

				model.setValueAt(ccourse_lists.get(i).getCourse_id(), i, 0);
				model.setValueAt(ccourse_lists.get(i).getCourse_name(), i, 1);
				model.setValueAt(ccourse_lists.get(i).getStu_id(), i, 2);
				model.setValueAt(ccourse_lists.get(i).getStu_name(), i, 3);
				model.setValueAt(ccourse_lists.get(i).getTeach_id(), i, 4);
				model.setValueAt(ccourse_lists.get(i).getTeach_name(), i, 5);
				model.setValueAt(ccourse_lists.get(i).getCcourse_mark(), i, 6);
		}
		jLabel_course_counts.setText(Integer.toString(ccourse_lists.size()));
		jTable.setModel(model);
	}
	
	
	private JButton getJButton_ccourse_open() {
		if (jButton_ccourse_open == null) {
			jButton_ccourse_open = new JButton();
			jButton_ccourse_open.setText("打开选课管理面板");
			jButton_ccourse_open.setBounds(new Rectangle(5, 57, 148, 26));
		}
		return jButton_ccourse_open;
	}

	private JButton getjButton_ccourse_print() {
		if (jButton_ccourse_print == null) {
			jButton_ccourse_print = new JButton();
			jButton_ccourse_print.setText("打印表单");
			jButton_ccourse_print.setBounds(new Rectangle(510, 58, 98, 25));
		}
		return jButton_ccourse_print;
	}

	private JLabel getJLabel_course_counts() {
		if (jLabel_course_counts == null) {
			jLabel_course_counts = new JLabel();
			jLabel_course_counts.setBounds(new Rectangle(608, 22, 27, 26));
		}
		return jLabel_course_counts;
	}


	//课程下拉框监听
	public class itemListener_course implements ItemListener{
		public void itemStateChanged(ItemEvent e){

			if(e.getSource()==jComboBox_course_condition){
				
				int course_index = jComboBox_course_condition.getSelectedIndex();
				int course_id = course_lists.get(course_index).getCourse_id();
				CCourseDao ccd = new CCourseDao();
				ccourse_lists=ccd.getCCourseSpecificList(course_id, -1);


				//flashDataByCombo();
				flashTableData();
				
			}else if(e.getSource()==jComboBox_course_counts){
				
//				int course_index = jComboBox_course_condition.getSelectedIndex();
//				int course_id = course_lists.get(course_index).getCourse_id();
				
				int course_counts_index = jComboBox_course_counts.getSelectedIndex();
		
				CCourseDao ccd = new CCourseDao();
				ccourse_lists=ccd.getCCourseSpecificList(-1, course_counts_index);
				flashTableData();	
				
			}
			
		}
		
	}

	/**
	 * @按钮监听
	 * @author Administrator
	 *
	 */
	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			CCourseDao ccd = new CCourseDao();
			if(e.getSource()==jButton_course_delete){
			    if (jTable.getSelectedRow() != -1) {
					int stu_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 2).toString());
					int course_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
					
					if(Login.login_user_type == 2){
						if(!Login.storeUserName.equals(stu_id+"")){
							JOptionPane.showMessageDialog(null, "只能选择删除自己的课程！");
							return;
						}
					}
					ccd.deleteCCourse(stu_id, course_id);
					
					model.removeRow(jTable.getSelectedRow());
				//	counts--;
					//flashDataByCombo();
					flashTableData();
					
				} else {
					JOptionPane.showMessageDialog(null, "请选择要删除的行！");
				}
			}else if(e.getSource()==jButton_ccourse_open){
				CCourseAdd cca = new CCourseAdd();
				cca.setVisible(true);

			}else if(e.getSource()==jButton_reflash){
				
				ccd = new CCourseDao();
				int course_index = jComboBox_course_condition.getSelectedIndex();
				int course_id = course_lists.get(course_index).getCourse_id();
				ccourse_lists=ccd.getCCourseSpecificList(course_id, -1);

				flashTableData();
				
				//flashDataByCombo();
			}else if(e.getSource()==jButton_ccourse_print){
				///////////////////////////////////////////
				///////////////////////////////////////////
				JOptionPane.showMessageDialog(null, "功能完善中...");
			
			}else if(e.getSource()==jButton_course_modify){
				int stu_id =Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 2).toString());
				if(Login.login_user_type == 2){
					if(!Login.storeUserName.equals(stu_id+"")){
						JOptionPane.showMessageDialog(null, "只能选择修改自己的课程！");
						return;
					}
				}
				//单数
				if(isFirstModify){
					
					isFirstModify=!isFirstModify;
					jButton_course_modify.setText("确认修改（慎用）");
					jComboBox_course_name.setEnabled(true);		
				}else{
				//写入数据库
				isFirstModify=!isFirstModify;
				jComboBox_course_name.setEnabled(false);
				
				int old_course_id =Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
				
				int new_course_id = course_lists.get(jComboBox_course_name.getSelectedIndex()).getCourse_id();
				if(!ccd.modifyCCourse(stu_id, old_course_id, new_course_id)){
					JOptionPane.showMessageDialog(null, "更新失败，请确保该学生未曾选修过该门课程");
					return;
				}
				flashTableData();
				JOptionPane.showMessageDialog(null, "修改成功");
				}	
			}else if(e.getSource()==jButton_course_query){
				
				int type = jComboBox_query_condition.getSelectedIndex();
				String query_word = jTextField_query_word.getText();
				if(jCheckBox_fuzzy_query.isSelected()){
					ccourse_lists = ccd.queryCCourseSpecificList(type, query_word, true);
				}else
					ccourse_lists = ccd.queryCCourseSpecificList(type, query_word, false);
					

				flashTableData();
			}
			
		}
	}

	/**
	 * 内部类监听器模块监听jTable是否被选中！
	 */

	public class tableListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (jTable.getSelectedRow() != -1) {
				int rows = jTable.getSelectedRow();
				jLabel_student_name.setText(jTable.getValueAt(rows, 3).toString());
				//匹配下拉框
				int course_id = Integer.parseInt(jTable.getValueAt(rows, 0).toString());
				
				
				for(int i=0;i<jComboBox_course_name.getItemCount();i++){
					if(course_id==course_lists.get(i).getCourse_id()){
						jComboBox_course_name.setSelectedIndex(i);break;
					}else{jComboBox_course_name.setSelectedIndex(0);}
				}			
				
			
			}
		}
	}
	
	public class checkListener implements ItemListener{
		public void itemStateChanged(ItemEvent e){
			
			if(e.getSource()==jCheckBox_teach_filter){
				if(e.getStateChange()==ItemEvent.SELECTED){
				jComboBox_course_counts.setEnabled(true);
				jComboBox_course_condition.setEnabled(false);
				
				}else{
					jComboBox_course_counts.setEnabled(false);
				    jComboBox_course_condition.setEnabled(true);
				}
				
			}else if(e.getSource()==jCheckBox_fuzzy_query){
				if(e.getStateChange()==ItemEvent.SELECTED){
					
					jComboBox_query_condition.setEnabled(false);
				//	jTextField_query_word.setEnabled(false);
				}else{
					jComboBox_query_condition.setEnabled(true);
				//	jTextField_query_word.setEnabled(true);
				}
				
			}
			
			
		}
		
		
	}
	/**
	 * This method initializes jCheckBox_teach_filter	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox_teach_filter() {
		if (jCheckBox_teach_filter == null) {
			jCheckBox_teach_filter = new JCheckBox();
			jCheckBox_teach_filter.setBounds(new Rectangle(265, 22, 23, 22));
		}
		return jCheckBox_teach_filter;
	}

	/**
	 * This method initializes jCheckBox_fuzzy_query	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getJCheckBox_fuzzy_query() {
		if (jCheckBox_fuzzy_query == null) {
			jCheckBox_fuzzy_query = new JCheckBox();
			jCheckBox_fuzzy_query.setBounds(new Rectangle(424, 59, 114, 22));
			jCheckBox_fuzzy_query.setText("是否全表模糊");
		}
		return jCheckBox_fuzzy_query;
	}

//	public static void main(String args[]){
//		CCourseManage cc = new CCourseManage();
//		cc.setVisible(true);
//		
//	}

}
