package com.sdut_grade.frame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;

import com.sdut_grade.dao.StudentDao;
import com.sdut_grade.model.StudentModel;
import com.sdut_grade.util.DBConnection;

public class StudentManage extends JDialog{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jJToolBarBar = null;
	private JButton jButton_student_add = null;
	private JButton jButton_student_modify = null;
	private JButton jButton_student_query = null;
	private JButton jButton_student_delete = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JButton jButton_student_flash = null;
	private JLabel jLabel_Row = null;
	private int counts = 0;// 获取要查询的记录集数目
	
	private List<StudentModel> student_lists;  //  @jve:decl-index=0:
	 

	DefaultTableModel model = new DefaultTableModel();


	public StudentManage() {
		super();
		initialize();
		initData();
		this.setModal(true);
	}

	private void initialize() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(558, 318);
		this.setTitle("学生管理  支持批量删除");
		this.setLocationRelativeTo(null);
		//this.setModal(true);

		jButton_student_add = new JButton();
		jButton_student_add.setText("添加");
		jButton_student_modify = new JButton();

		jButton_student_modify.setText("修改");
		jButton_student_query = new JButton();

		jButton_student_query.setText("查询");
		jButton_student_delete = new JButton();

		jButton_student_delete.setText("删除");
		jButton_student_flash = new JButton();

		jButton_student_flash.setText("刷新");
		jTable = new JTable();
		jTable.setSelectionBackground(new Color(0, 255, 204));
		jTable.setSelectionForeground(new Color(153, 0, 0));
		jTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		jLabel_Row = new JLabel();
		jLabel_Row.setSize(20, 20);

		jJToolBarBar = new JToolBar();
		jJToolBarBar.add(jButton_student_add);
		jJToolBarBar.add(jButton_student_modify);
		jJToolBarBar.add(jButton_student_query);
		jJToolBarBar.add(jButton_student_delete);
		jJToolBarBar.add(jButton_student_flash);
		jJToolBarBar.add(jLabel_Row);

		jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jTable);
		jContentPane = new JPanel();
		jContentPane.setLayout(new BorderLayout());
		jContentPane.setBorder(BorderFactory.createTitledBorder("操作一体化"));
		jContentPane.add(jScrollPane, BorderLayout.CENTER);
		jContentPane.add(jJToolBarBar, BorderLayout.NORTH);
		this.setContentPane(jContentPane);

		btnListener btl = new btnListener();
		jButton_student_add.addActionListener(btl);
		jButton_student_modify.addActionListener(btl);
		jButton_student_query.addActionListener(btl);
		jButton_student_delete.addActionListener(btl);
		jButton_student_flash.addActionListener(btl);
	}

	/**
	 * @初始化界面
	 */
	public void initData() {

		String heads[] = { "学号", "姓名", "性别", "班级ID","班别名称" };
		model = new DefaultTableModel(null, heads);
		StudentDao sd= new StudentDao();
		student_lists = sd.getLists(false, -1);
		flashData();
		
		
	}
	
	public void flashData(){
		counts = student_lists.size();
		model.setRowCount(student_lists.size());// 设置行数
			for(int i=0;i<counts;i++){
				model.setValueAt(student_lists.get(i).getStu_id(), i, 0);
				model.setValueAt(student_lists.get(i).getStu_name(), i, 1);
				model.setValueAt(student_lists.get(i).getStu_sex(), i, 2);
				model.setValueAt(student_lists.get(i).getClass_id(), i, 3);
				model.setValueAt(student_lists.get(i).getClass_name(), i, 4);

			}
			jLabel_Row.setText("记录数:" + counts + "");
			jTable.setModel(model);
			jTable.setAutoCreateRowSorter(true);//为JTable设置排序器
	}
	
	/**
	 * 内部类监听器模块
	 */
	private class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			StudentDao sd = new StudentDao();
			if (e.getSource() == jButton_student_add) {
				
				StudentAdd sa = new StudentAdd(false,-1);
				sa.setVisible(true);
				flashData();

			} else if (e.getSource() == jButton_student_modify) {
				if (jTable.getSelectedRow() != -1) {// 选择行数如果非空
					int stu_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
					
					StudentAdd sa = new StudentAdd(true,stu_id);
					sa.setVisible(true);
					flashData();
					
				} else {
					JOptionPane.showMessageDialog(null, "请选择要修改的行!");
				}

			} else if (e.getSource() == jButton_student_delete) {// 支持多行删除
				if (jTable.getSelectedRow() != -1) {
					int stu_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
					sd.deleteListByStuId(stu_id);
					
				    JOptionPane.showMessageDialog(null, "删除成功！");
					
					model.removeRow(jTable.getSelectedRow());
					//flashData();
				} else {
					JOptionPane.showMessageDialog(null, "请选择要删除的行！");
				}
				}else if (e.getSource() == jButton_student_query) {
			
				int  stu_id ;
				try {
					stu_id = Integer.parseInt(JOptionPane.showInputDialog("按学号查询，请输入要查询的学号："));
				} catch (Exception e2) {
					// TODO: handle exception
					return;
				}
				student_lists = sd.getLists(true, stu_id);
				flashData();			
				
			} else if (e.getSource() == jButton_student_flash) {
				initData();
			}

		}
	}
	
	
//	public static void main(String args[]){
//		
//		StudentManage sm = new StudentManage();
//		sm.setVisible(true);
//		
//	}
	
}
