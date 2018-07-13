package com.sdut_grade.frame;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.sdut_grade.dao.ClassDao;
import com.sdut_grade.dao.CourseDao;
import com.sdut_grade.dao.GradeDao;
import com.sdut_grade.dao.TeacherDao;
import com.sdut_grade.model.ClassModel;
import com.sdut_grade.model.CourseModel;
import com.sdut_grade.model.GradeModel;
import com.sdut_grade.model.TeacherModel;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;


public class ClassManage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jJToolBarBar = null;
	private JButton jButton_course_add = null;
	private JButton jButton_course_query = null;
	private JButton jButton_course_delete = null;
	private JButton jButton_course_flash = null;
	private JLabel jLabel_course_counts = null;
	private ButtonGroup buttonts = null;
	private String teach_sex = "男";  //  @jve:decl-index=0:
	private int counts=0;
	
	private List<ClassModel> class_lists;  //  @jve:decl-index=0:
	List<GradeModel> gradeModelList ;
	DefaultTableModel model = new DefaultTableModel();
	private JTable jTable = null;
	private JScrollPane jScrollPane = null;
	private JLabel jLabel_teach_educatioon = null;
	private JPanel jPanel = null;
	private JLabel jLabel_teach_name = null;
	private JLabel jLabel_teach_sex = null;
	private JTextField jTextField_teach_name = null;
	private JTextField jTextField_max_num = null;
	private JComboBox jComboBox_teach_education = null;
	private JButton jButton_ok = null;
	private JButton jButton_cancel = null;
	private JRadioButton jRadioButton_boy = null;
	private JRadioButton jRadioButton_girl = null;

	public ClassManage() {
		super();
		initialize();
		initData();
		btnListener btn = new btnListener();
		jButton_course_add.addActionListener(btn);
		jButton_course_query.addActionListener(btn);
		jButton_course_delete.addActionListener(btn);
		jButton_course_flash.addActionListener(btn);
		jButton_ok.addActionListener(btn);
		jButton_cancel.addActionListener(btn);
		this.setModal(true);
		jTable.getSelectionModel().addListSelectionListener(new tableListener());
	}

	private void initialize() {
		jLabel_teach_sex = new JLabel();
		jLabel_teach_sex.setText("班级最大人数：");
		jLabel_teach_sex.setBounds(new Rectangle(23, 71, 98, 26));
		jLabel_teach_name = new JLabel();
		jLabel_teach_name.setText("班级名称：");
		jLabel_teach_name.setBounds(new Rectangle(23, 25, 88, 28));
		jLabel_teach_educatioon = new JLabel();
		jLabel_teach_educatioon.setText("所属年级：");
		jLabel_teach_educatioon.setBounds(new Rectangle(245, 25, 73, 30));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(497, 479);
		this.setTitle("班级管理");
		//this.setModal(true);
		this.setLocationRelativeTo(null);
			jButton_course_add = new JButton();
			jButton_course_add.setText("添加");
			jButton_course_query = new JButton();
			jButton_course_query.setText("修改");
			jButton_course_delete = new JButton();
			jButton_course_delete.setText("删除");
			jButton_course_flash = new JButton();
			jButton_course_flash.setText("刷新");
			

//			jRadioButton_boy = new JRadioButton("男",true);
//			jRadioButton_boy.setBounds(new Rectangle(119, 69, 59, 28));
//		
//
//			jRadioButton_girl = new JRadioButton("女",false);
//			jRadioButton_girl.setBounds(new Rectangle(179, 69, 56, 30));
//			
//			jRadioButton_boy.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					teach_sex = "男";
//				}
//			});
//			jRadioButton_girl.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					teach_sex = "女";
//				}
//			});
			
			//gettsButtonGroup();// 加入按钮组
			
			

			jButton_ok = new JButton();
			jButton_ok.setBounds(new Rectangle(250, 65, 90, 29));
			jButton_ok.setText("确定");
			
			jButton_cancel=new JButton();
			jButton_cancel.setBounds(new Rectangle(347, 65, 60, 29));
			jButton_cancel.setText("取消");
			
			
	
			jLabel_course_counts = new JLabel();
			jLabel_course_counts.setSize(20, 20);
			jJToolBarBar = new JToolBar();
			jJToolBarBar.setBounds(new Rectangle(5, 21, 714, 34));
			jJToolBarBar.add(jButton_course_add);
			jJToolBarBar.add(jButton_course_query);
			jJToolBarBar.add(jButton_course_delete);
			jJToolBarBar.add(jButton_course_flash);
			jJToolBarBar.add(jLabel_course_counts);
			
			jTable = new JTable();
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(20, 71, 450, 250));
			jScrollPane.setViewportView(jTable);
			

			jTextField_teach_name = new JTextField();
			jTextField_teach_name.setBounds(new Rectangle(116, 26, 122, 30));
			
			jTextField_max_num = new JTextField();
			jTextField_max_num.setBounds(new Rectangle(119, 69, 59, 28));

			jComboBox_teach_education = new JComboBox();
			jComboBox_teach_education.setBounds(new Rectangle(326, 26, 122, 30));


					jPanel = new JPanel();
					jPanel.setLayout(null);
					jPanel.setBounds(new Rectangle(22, 332, 456, 106));
					jPanel.setBorder(BorderFactory.createTitledBorder("班级快速添加"));
					jPanel.add(jLabel_teach_name, null);
					jPanel.add(jLabel_teach_sex, null);
					jPanel.add(jTextField_teach_name, null);
					jPanel.add(jComboBox_teach_education, null);
					jPanel.add(jLabel_teach_educatioon, null);
					jPanel.add(jTextField_max_num, null);
					jPanel.add(jButton_ok, null);
					jPanel.add(jButton_cancel, null);
//					jPanel.add(jRadioButton_boy, null);
//					jPanel.add(jRadioButton_boy, null);
//					jPanel.add(jRadioButton_boy, null);
//				
//					jPanel.add(jRadioButton_girl, null);
			
				jContentPane = new JPanel();
				jContentPane.setLayout(null);
				jContentPane.setBorder(BorderFactory.createTitledBorder("操作一体化"));
				jContentPane.add(jScrollPane, null);
				jContentPane.add(jJToolBarBar, null);
				jContentPane.add(jPanel, null);
				this.setContentPane(jContentPane);
				jContentPane.add(jScrollPane, null);
			
			this.setContentPane(jContentPane);
			

			

	}
	
	private void gettsButtonGroup() {// 按钮组
		if (buttonts == null) {
			buttonts = new ButtonGroup();
			buttonts.add(jRadioButton_boy);
			buttonts.add(jRadioButton_girl);
		}
	}
	
	/**
	 * @初始化界面
	 */
	public void initData() {

		String heads[] = { "班级ID", "班级名称" ,"所属年级" ,"最大人数"};
		model = new DefaultTableModel(null, heads);
		GradeDao gd = new GradeDao();
		gradeModelList = gd.getGradeModelList();
		for(GradeModel gm : gradeModelList){
			jComboBox_teach_education.addItem(gm);
		}
//		jComboBox_teach_education.addItem("硕士");
//		jComboBox_teach_education.addItem("博士");
//		jComboBox_teach_education.addItem("烈士");
		
		ClassDao cd = new ClassDao();
		class_lists = cd.getLists();
		
		jTextField_teach_name.setEditable(false);
		jButton_ok.setEnabled(false);
		jButton_ok.setText("确定");
		jTextField_max_num.setEditable(false);;
		flashData();
		
		
	}
	
	public void flashData(){
		counts = class_lists.size();
		model.setRowCount(class_lists.size());// 设置行数
			for(int i=0;i<counts;i++){
				model.setValueAt(class_lists.get(i).getClass_id(), i, 0);
				model.setValueAt(class_lists.get(i).getClass_name(), i, 1);
				for(GradeModel gm : gradeModelList){
					if(gm.getGrade_id() == class_lists.get(i).getGrade_id()){
						model.setValueAt(gm.getGrade_name(), i, 2);
					}
				}
				model.setValueAt(class_lists.get(i).getClass_maxnum(), i, 3);
			}
			jLabel_course_counts.setText("记录数:" + counts + "");
			jTable.setModel(model);
	}
			
	/**
	 * 内部类监听器模块
	 */
	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			GradeDao gd = new GradeDao();
			if (e.getSource() == jButton_course_add) {
				
				//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//String course_begin_time = sdf.format(new Date());
				//jTextField_teach_name.setEditable(true);
				jTextField_teach_name.setEditable(true);
				jButton_ok.setEnabled(true);
				jTextField_max_num.setEditable(true);
			}
			else if (e.getSource() == jButton_course_delete) {
				if (jTable.getSelectedRow() != -1) {	
					int class_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
					if(JOptionPane.showConfirmDialog(null, "班级下可能有多个学生，请谨慎删除，否则可能导致程序出错，是否确定删除？") == JOptionPane.OK_OPTION){
						ClassDao cd = new ClassDao();
						if(cd.deleteListByClassId(class_id)){
							JOptionPane.showMessageDialog(null, "删除成功！");
							initData();
						}else{
							JOptionPane.showMessageDialog(null, "删除失败，该班级下有学生，请先删除班级！");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "请选择要删除的行！");
				}
			}
			else if (e.getSource() == jButton_course_query) {
				if (jTable.getSelectedRow() != -1) {
					ClassDao cd = new ClassDao();
					int class_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
					String className = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
					String gradeName = jTable.getValueAt(jTable.getSelectedRow(), 2).toString();
					Integer maxNUmInteger = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 3).toString());
					if(null == className || "".equals(className)){
						JOptionPane.showMessageDialog(null, "不存在该数据，请刷新面板！");
						return;
					}
					jTextField_teach_name.setText(className);
					jTextField_teach_name.setEditable(true);
					jTextField_max_num.setText(maxNUmInteger.toString());
					jTextField_max_num.setEditable(true);
					for(int i=0;i < jComboBox_teach_education.getItemCount();i++){
						GradeModel gModel = (GradeModel) jComboBox_teach_education.getItemAt(i);
						if(gModel.getGrade_name().equals(gradeName)){
							jComboBox_teach_education.setSelectedIndex(i);
							break;
						}
					}
					jButton_ok.setText("确定修改");
					jButton_ok.setEnabled(true);
								
				} else {
					JOptionPane.showMessageDialog(null, "请选择要修改的行！");
				}
				
				//grade_lists = td.getLists(true, teach_id);
				flashData();	
			}
			else if (e.getSource() == jButton_course_flash) {
				initData();
			}
			else if(e.getSource()==jButton_ok){
				if("确定修改".equals(jButton_ok.getText())){
					int class_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
					String class_name = jTextField_teach_name.getText();
					Integer max_num ;
					try {
						max_num = Integer.parseInt(jTextField_max_num.getText());
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "最大人数请输入数字!");
						return;
					}
					GradeModel gradeModelTmp = (GradeModel) jComboBox_teach_education.getSelectedItem();
					if(gradeModelTmp != null){
						ClassDao cd = new ClassDao();
						if(cd.modifyClass(class_id, class_name, gradeModelTmp.getGrade_id(), max_num))
						{
							JOptionPane.showMessageDialog(null, "修改成功");
							initData();
						}else{
							JOptionPane.showMessageDialog(null, "修改失败");
						}
					}
					return;
				}
				String class_name = jTextField_teach_name.getText();
				if("".equals(class_name)){
					JOptionPane.showMessageDialog(null, "请输入名称！");
					return;
				}
				Integer maxNum ;
				try {
					maxNum = Integer.parseInt(jTextField_max_num.getText().toString());
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "最大人数请输入数字!");
					return;
				}
				GradeModel gradeModelTmp = (GradeModel) jComboBox_teach_education.getSelectedItem();
				if(gradeModelTmp != null){
					ClassDao cd = new ClassDao();
					if(cd.addClass(class_name, gradeModelTmp.getGrade_id(), maxNum))
					{
						JOptionPane.showMessageDialog(null, "添加成功");
						initData();
					}else{
						JOptionPane.showMessageDialog(null, "添加失败");
					}
				}
				
				//grade_lists = td.getLists(false, -1);
			}
			else if(e.getSource()==jButton_cancel){
				initData();
			}
		}
	}
	
	public class tableListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (jTable.getSelectedRow() != -1) {
			//	int index = jTable.getSelectedRow();		
						
			}
		}
	}


//	public static void main(String args[]){
//		ClassManage cm = new ClassManage();
//		cm.setVisible(true);
//	}
	
	




}  //  @jve:decl-index=0:visual-constraint="10,10"  
