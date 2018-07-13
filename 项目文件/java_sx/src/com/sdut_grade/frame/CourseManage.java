package com.sdut_grade.frame;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
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

import com.sdut_grade.dao.CourseDao;
import com.sdut_grade.dao.TeacherDao;
import com.sdut_grade.model.CourseModel;
import com.sdut_grade.model.TeacherModel;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;


public class CourseManage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jJToolBarBar = null;
	private JButton jButton_course_add = null;
	private JButton jButton_course_edit = null;
	private JButton jButton_course_query = null;
	private JButton jButton_course_delete = null;
	private JButton jButton_course_flash = null;
	private JLabel jLabel_course_counts = null;
	private int counts=0;
	
	private List<CourseModel> course_lists ;  

	private List<TeacherModel> teacher_lists;  //  @jve:decl-index=0:
	
	DefaultTableModel model = new DefaultTableModel();
	private JScrollPane jScrollPane1 = null;
	private JTable jTable = null;
	private JScrollPane jScrollPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextArea jTextField_course_info = null;
	private JPanel jPanel = null;
	private JLabel jLabel_course_name = null;
	private JLabel jLabel_course_credit = null;
	private JLabel jLabel_teach_name = null;
	private JTextField jTextField_course_name = null;
	private JTextField jTextField_course_credit = null;
	private JComboBox jComboBox_teach_name = null;
	private JTextField jTextField_course_begin_time = null;
	private JButton jButton_ok = null;
	private JButton jButton_cancel = null;
	public CourseManage() {
		super();
		initialize();
		initData();
		btnListener btn = new btnListener();
		jButton_course_add.addActionListener(btn);
		jButton_course_edit.addActionListener(btn);
		jButton_course_query.addActionListener(btn);
		jButton_course_delete.addActionListener(btn);
		jButton_course_flash.addActionListener(btn);
		jButton_ok.addActionListener(btn);
		jButton_cancel.addActionListener(btn);
		this.setModal(true);
		jTable.getSelectionModel().addListSelectionListener(new tableListener());
	}

	private void initialize() {
		jLabel_teach_name = new JLabel();
		jLabel_teach_name.setText("授课老师：");
		jLabel_teach_name.setBounds(new Rectangle(253, 18, 69, 29));
		jLabel_course_credit = new JLabel();
		jLabel_course_credit.setText("课程学分：");
		jLabel_course_credit.setBounds(new Rectangle(23, 71, 88, 26));
		jLabel_course_name = new JLabel();
		jLabel_course_name.setText("课程名称：");
		jLabel_course_name.setBounds(new Rectangle(23, 25, 88, 28));
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(478, 71, 96, 31));
		jLabel1.setText("课程介绍：");
		jLabel = new JLabel();
		jLabel.setText("开课时间：");
		jLabel.setBounds(new Rectangle(250, 63, 73, 30));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(680, 479);
		this.setTitle("课程管理");
		//this.setModal(true);
		this.setLocationRelativeTo(null);
			jButton_course_add = new JButton();
			jButton_course_add.setText("添加");
			jButton_course_edit = new JButton();
			jButton_course_edit.setText("修改");
			jButton_course_query = new JButton();
			jButton_course_query.setText("查询");
			jButton_course_delete = new JButton();
			jButton_course_delete.setText("删除");
			jButton_course_flash = new JButton();
			jButton_course_flash.setText("刷新");
			

			jButton_ok = new JButton();
			jButton_ok.setBounds(new Rectangle(496, 17, 114, 29));
			jButton_ok.setText("确定");
			
			jButton_cancel=new JButton();
			jButton_cancel.setBounds(new Rectangle(496, 63, 115, 27));
			jButton_cancel.setText("取消");
			
			
	
			jLabel_course_counts = new JLabel();
			jLabel_course_counts.setSize(20, 20);
			jJToolBarBar = new JToolBar();
			jJToolBarBar.setBounds(new Rectangle(5, 21, 714, 34));
			jJToolBarBar.add(jButton_course_add);
			jJToolBarBar.add(jButton_course_edit);
			jJToolBarBar.add(jButton_course_query);
			jJToolBarBar.add(jButton_course_delete);
			jJToolBarBar.add(jButton_course_flash);
			jJToolBarBar.add(jLabel_course_counts);
			
			jTable = new JTable();
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(20, 71, 450, 250));
			jScrollPane.setViewportView(jTable);
			

			jTextField_course_info = new JTextArea();
			jTextField_course_info.setBounds(new Rectangle(478, 108, 167, 214));


			jTextField_course_name = new JTextField();
			jTextField_course_name.setBounds(new Rectangle(116, 26, 122, 26));

			jTextField_course_credit = new JTextField();
			jTextField_course_credit.setBounds(new Rectangle(117, 71, 123, 26));

			jComboBox_teach_name = new JComboBox();
			jComboBox_teach_name.setBounds(new Rectangle(326, 18, 148, 30));


			jTextField_course_begin_time = new JTextField();
			jTextField_course_begin_time.setBounds(new Rectangle(330, 64, 144, 28));

					jPanel = new JPanel();
					jPanel.setLayout(null);
					jPanel.setBounds(new Rectangle(22, 332, 626, 106));
					jPanel.setBorder(BorderFactory.createTitledBorder("课程快速开设"));
					jPanel.add(jLabel_course_name, null);
					jPanel.add(jLabel_course_credit, null);
					jPanel.add(jTextField_course_name, null);
					jPanel.add(jTextField_course_credit, null);
					jPanel.add(jLabel_teach_name, null);
					jPanel.add(jComboBox_teach_name, null);
					jPanel.add(jLabel, null);
					jPanel.add(jTextField_course_begin_time, null);
					jPanel.add(jButton_ok, null);
					jPanel.add(jButton_cancel, null);
			
				jContentPane = new JPanel();
				jContentPane.setLayout(null);
				jContentPane.setBorder(BorderFactory.createTitledBorder("操作一体化"));
				jContentPane.add(jScrollPane, null);
				jContentPane.add(jJToolBarBar, null);
				jContentPane.add(jLabel1, null);
				jContentPane.add(jTextField_course_info, null);
				jContentPane.add(jPanel, null);
				this.setContentPane(jContentPane);
				jContentPane.add(jScrollPane, null);
			
			this.setContentPane(jContentPane);
			

			

	}
	
	/**
	 * @初始化界面
	 */
	public void initData() {

		String heads[] = { "课程ID", "课程名称", "课程学分", "授课教师","开课时间"};
		model = new DefaultTableModel(null, heads);
		CourseDao cd = new CourseDao();
		course_lists = cd.getLists(false, -1);
		
		jTextField_course_begin_time.setEditable(false);
		jTextField_course_credit.setEditable(false);
		jTextField_course_info.setEditable(false);
		jTextField_course_name.setEditable(false);
		jButton_ok.setEnabled(false);
		jButton_ok.setText("确定");
		TeacherDao td = new TeacherDao();
		

		
		teacher_lists = td.getLists(false, -1);
		for(int i=0;i<teacher_lists.size();i++){
			jComboBox_teach_name.addItem(teacher_lists.get(i).getTeach_name());
		}
		
		flashData();
		
		
	}
	
	public void flashData(){
		counts = course_lists.size();
		model.setRowCount(course_lists.size());// 设置行数
			for(int i=0;i<counts;i++){
				model.setValueAt(course_lists.get(i).getCourse_id(), i, 0);
				model.setValueAt(course_lists.get(i).getCourse_name(), i, 1);
				model.setValueAt(course_lists.get(i).getCourse_credit(), i, 2);
				model.setValueAt(course_lists.get(i).getTeach_name(), i, 3);
				model.setValueAt(course_lists.get(i).getCourse_begin_time(), i, 4);

			}
			jLabel_course_counts.setText("记录数:" + counts + "");
			jTable.setModel(model);
			jTable.setAutoCreateRowSorter(true);//为JTable设置排序器
	}
			
	/**
	 * 内部类监听器模块
	 */
	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CourseDao cd = new CourseDao();
			
			if (e.getSource() == jButton_course_add) {
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String course_begin_time = sdf.format(new Date());
				jTextField_course_name.setEditable(true);
				jTextField_course_begin_time.setEditable(true);
				jTextField_course_begin_time.setText(course_begin_time);
				jTextField_course_credit.setEditable(true);
				jTextField_course_info.setEditable(true);
				jButton_ok.setEnabled(true);
				jButton_ok.setText("确定");
			}else if(e.getSource() == jButton_course_edit){
				if (jTable.getSelectedRow() != -1) {
					String course_name = jTable.getValueAt(jTable.getSelectedRow(), 1).toString();
					int course_credit = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 2).toString());
					String teach_name = jTable.getValueAt(jTable.getSelectedRow(), 3).toString();
					String course_begin_time = jTable.getValueAt(jTable.getSelectedRow(), 4).toString();
					jTextField_course_name.setText(course_name);
					jTextField_course_name.setEditable(true);
					jTextField_course_credit.setText(course_credit+"");
					jTextField_course_credit.setEditable(true);
					jTextField_course_begin_time.setText(course_begin_time);
					jTextField_course_begin_time.setEditable(true);
					jButton_ok.setText("确认修改");
					jButton_ok.setEnabled(true);
					jTextField_course_info.setEditable(true);
					for(int i = 0;i < jComboBox_teach_name.getItemCount();i++){
						if(teach_name.equals(jComboBox_teach_name.getItemAt(i).toString())){
							jComboBox_teach_name.setSelectedIndex(i);
							break;
						}
					}
				}else{
					JOptionPane.showMessageDialog(null, "请选择要修改的行！");
				}
			}
			else if (e.getSource() == jButton_course_delete) {
				if (jTable.getSelectedRow() != -1) {	
					int course_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
					cd.deleteListByCourseId(course_id);
					JOptionPane.showMessageDialog(null, "删除成功！");				
					model.removeRow(jTable.getSelectedRow());
				} else {
					JOptionPane.showMessageDialog(null, "请选择要删除的行！");
				}
			}
			else if (e.getSource() == jButton_course_query) {
				int  course_id;
				try {
					course_id = Integer.parseInt(JOptionPane.showInputDialog("按学号查询，请输入要查询的学号："));
				} catch (Exception e2) {
					// TODO: handle exception
					return;
				}
				course_lists = cd.getLists(true, course_id);
				flashData();	
			}
			else if (e.getSource() == jButton_course_flash) {
				initData();
			}
			else if(e.getSource()==jButton_ok){
				if("确认修改".equals(jButton_ok.getText())){
					int course_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
					int course_credit = Integer.parseInt(jTextField_course_credit.getText().toString());
					int teach_id = teacher_lists.get(jComboBox_teach_name.getSelectedIndex()).getTeach_id();
					String course_name = jTextField_course_name.getText();
					String course_begin_time = jTextField_course_begin_time.getText();
					String course_info = jTextField_course_info.getText();
					if(cd.modifyCourse(course_id, course_name, course_credit, teach_id, course_info,course_begin_time)){
						JOptionPane.showMessageDialog(null, "修改成功！");
						initData();
					}else{
						JOptionPane.showMessageDialog(null, "修改失败！");
					}
					return;
				}
				String course_name = jTextField_course_name.getText();
				int course_credit = Integer.parseInt(jTextField_course_credit.getText());
				String course_begin_time = jTextField_course_begin_time.getText();
				String course_info = jTextField_course_info.getText();
				int teach_id = teacher_lists.get(jComboBox_teach_name.getSelectedIndex()).getTeach_id();
				if(cd.addCourse(course_name, course_credit, course_info, course_begin_time, teach_id))
				{JOptionPane.showMessageDialog(null, "添加成功");
				}else
					JOptionPane.showMessageDialog(null, "添加失败");
				
				course_lists = cd.getLists(false, -1);
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
				int index = jTable.getSelectedRow();		
				jTextField_course_info.setText(course_lists.get(index).getCourse_info());
						
			}
		}
	}







//	public static void main(String args[]){
//		CourseManage cm = new CourseManage();
//		cm.setVisible(true);
//	}
	
	




}  //  @jve:decl-index=0:visual-constraint="10,10"  
