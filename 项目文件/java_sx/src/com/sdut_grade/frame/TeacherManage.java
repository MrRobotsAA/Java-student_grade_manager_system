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
import com.sdut_grade.dao.TeacherDao;
import com.sdut_grade.model.CourseModel;
import com.sdut_grade.model.GradeModel;
import com.sdut_grade.model.TeacherModel;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;


public class TeacherManage extends JDialog {

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
	
	private List<TeacherModel> teacher_lists;  //  @jve:decl-index=0:
	
	DefaultTableModel model = new DefaultTableModel();
	private JTable jTable = null;
	private JScrollPane jScrollPane = null;
	private JLabel jLabel_teach_educatioon = null;
	private JPanel jPanel = null;
	private JLabel jLabel_teach_name = null;
	private JLabel jLabel_teach_sex = null;
	private JTextField jTextField_teach_name = null;
	private JComboBox jComboBox_teach_education = null;
	private JButton jButton_ok = null;
	private JButton jButton_cancel = null;
	private JRadioButton jRadioButton_boy = null;
	private JRadioButton jRadioButton_girl = null;

	public TeacherManage() {
		super();
		initialize();
		initData();
		this.setModal(true);
		btnListener btn = new btnListener();
		jButton_course_add.addActionListener(btn);
		jButton_course_query.addActionListener(btn);
		jButton_course_delete.addActionListener(btn);
		jButton_course_flash.addActionListener(btn);
		jButton_ok.addActionListener(btn);
		jButton_cancel.addActionListener(btn);
		
		jTable.getSelectionModel().addListSelectionListener(new tableListener());
	}

	private void initialize() {
		jLabel_teach_sex = new JLabel();
		jLabel_teach_sex.setText("教师性别：");
		jLabel_teach_sex.setBounds(new Rectangle(23, 71, 88, 26));
		jLabel_teach_name = new JLabel();
		jLabel_teach_name.setText("教师名称：");
		jLabel_teach_name.setBounds(new Rectangle(23, 25, 88, 28));
		jLabel_teach_educatioon = new JLabel();
		jLabel_teach_educatioon.setText("教师学历：");
		jLabel_teach_educatioon.setBounds(new Rectangle(245, 20, 73, 30));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(497, 479);
		this.setTitle("教师管理");
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
			

			jRadioButton_boy = new JRadioButton("男",true);
			jRadioButton_boy.setBounds(new Rectangle(119, 69, 59, 28));
		

			jRadioButton_girl = new JRadioButton("女",false);
			jRadioButton_girl.setBounds(new Rectangle(179, 69, 56, 30));
			
			jRadioButton_boy.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					teach_sex = "男";
				}
			});
			jRadioButton_girl.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					teach_sex = "女";
				}
			});
			
			gettsButtonGroup();// 加入按钮组
			
			

			jButton_ok = new JButton();
			jButton_ok.setBounds(new Rectangle(242, 65, 94, 29));
			jButton_ok.setText("确定");
			
			jButton_cancel=new JButton();
			jButton_cancel.setBounds(new Rectangle(347, 66, 88, 27));
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
			jTextField_teach_name.setBounds(new Rectangle(116, 26, 122, 26));

			jComboBox_teach_education = new JComboBox();
			jComboBox_teach_education.setBounds(new Rectangle(326, 18, 122, 30));


					jPanel = new JPanel();
					jPanel.setLayout(null);
					jPanel.setBounds(new Rectangle(22, 332, 456, 106));
					jPanel.setBorder(BorderFactory.createTitledBorder("教师快速添加"));
					jPanel.add(jLabel_teach_name, null);
					jPanel.add(jLabel_teach_sex, null);
					jPanel.add(jTextField_teach_name, null);
					jPanel.add(jComboBox_teach_education, null);
					jPanel.add(jLabel_teach_educatioon, null);
					jPanel.add(jButton_ok, null);
					jPanel.add(jButton_cancel, null);
					jPanel.add(jRadioButton_boy, null);
					jPanel.add(jRadioButton_boy, null);
					jPanel.add(jRadioButton_boy, null);
				
					jPanel.add(jRadioButton_girl, null);
			
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

		String heads[] = { "授课教师ID", "授课教师姓名", "教师性别", "教师学历"};
		model = new DefaultTableModel(null, heads);
		jComboBox_teach_education.addItem("学士");
		jComboBox_teach_education.addItem("硕士");
		jComboBox_teach_education.addItem("博士");
		jComboBox_teach_education.addItem("教授");
		
		TeacherDao td = new TeacherDao();
		teacher_lists = td.getLists(false, -1);
		
		jTextField_teach_name.setEditable(false);
		jButton_ok.setEnabled(false);
		jButton_ok.setText("确定");
		flashData();
		
		
	}
	
	public void flashData(){
		counts = teacher_lists.size();
		model.setRowCount(teacher_lists.size());// 设置行数
			for(int i=0;i<counts;i++){
				model.setValueAt(teacher_lists.get(i).getTeach_id(), i, 0);
				model.setValueAt(teacher_lists.get(i).getTeach_name(), i, 1);
				model.setValueAt(teacher_lists.get(i).getTeach_sex(), i, 2);
				model.setValueAt(teacher_lists.get(i).getEducation(), i, 3);

			}
			jLabel_course_counts.setText("记录数:" + counts + "");
			jTable.setModel(model);
	}
			
	/**
	 * 内部类监听器模块
	 */
	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			TeacherDao td = new TeacherDao();
			if (e.getSource() == jButton_course_add) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String course_begin_time = sdf.format(new Date());
				jTextField_teach_name.setEditable(true);
				jTextField_teach_name.setEditable(true);
				jButton_ok.setEnabled(true);
				
			}
			else if (e.getSource() == jButton_course_delete) {
				if (jTable.getSelectedRow() != -1) {	
					int teach_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
					td.deleteListByTeachId(teach_id);
					JOptionPane.showMessageDialog(null, "删除成功！");				
					//model.removeRow(jTable.getSelectedRow());
					//数据量不是很多，感觉重新刷新一次好点
					counts = td.getLists(false, -1).size();
					initData();
				} else {
					JOptionPane.showMessageDialog(null, "请选择要删除的行！");
				}
			}
			else if (e.getSource() == jButton_course_query) {
				//int  teach_id = Integer.parseInt(JOptionPane.showInputDialog("按学号查询，请输入要查询的学号："));
				//teacher_lists = td.getLists(true, teach_id);
				//flashData();
				if (jTable.getSelectedRow() != -1) {
					String teachName = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
					String teachSex = jTable.getValueAt(jTable.getSelectedRow(), 2).toString();
					String teachEducation = jTable.getValueAt(jTable.getSelectedRow(), 3).toString();
					if(null == teachName || "".equals(teachName)){
						JOptionPane.showMessageDialog(null, "不存在该数据，请刷新面板！");
						return;
					}
					jTextField_teach_name.setText(teachName);
					jTextField_teach_name.setEditable(true);
					for(int i=0;i < jComboBox_teach_education.getItemCount();i++){
						String ted = (String) jComboBox_teach_education.getItemAt(i);
						if(ted.equals(teachEducation)){
							jComboBox_teach_education.setSelectedIndex(i);
							break;
						}
					}
					if ("男".equals(teachSex)) {
						jRadioButton_boy.setSelected(true);
					} else {
						jRadioButton_girl.setSelected(true);
					}
					jButton_ok.setText("确定修改");
					jButton_ok.setEnabled(true);
				}else {
					JOptionPane.showMessageDialog(null, "请选择要修改的行！");
				}
			}
			else if (e.getSource() == jButton_course_flash) {
				initData();
			}
			else if(e.getSource()==jButton_ok){
				String teach_name = jTextField_teach_name.getText();
				if("确定修改".equals(jButton_ok.getText())){
					int teacher_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
					String teach_sex = jRadioButton_boy.isSelected() ? "男" : "女";
					String teach_education = jComboBox_teach_education.getSelectedItem().toString();
					if(teach_education != null){
						if(td.modifyTeacher(teacher_id, teach_name, teach_sex, teach_education))
						{
							JOptionPane.showMessageDialog(null, "修改成功");
							initData();
						}else{
							JOptionPane.showMessageDialog(null, "修改失败");
						}
					}
					return;
				}
				//String teach_education = teacher_lists.get(jComboBox_teach_education.getSelectedIndex()).getEducation();
				String teach_education = jComboBox_teach_education.getSelectedItem().toString();
				if(td.addTeacher(teach_name, teach_sex, teach_education))
				{JOptionPane.showMessageDialog(null, "添加成功");
				}else
					JOptionPane.showMessageDialog(null, "添加失败");
				teacher_lists = td.getLists(false, -1);
				flashData();
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

//
	public static void main(String args[]){
		TeacherManage cm = new TeacherManage();
		cm.setVisible(true);
	}
	
	




}  //  @jve:decl-index=0:visual-constraint="10,10"  
