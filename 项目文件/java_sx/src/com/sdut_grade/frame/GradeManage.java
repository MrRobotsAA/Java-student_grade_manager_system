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

import com.sdut_grade.dao.CourseDao;
import com.sdut_grade.dao.GradeDao;
import com.sdut_grade.dao.TeacherDao;
import com.sdut_grade.model.CourseModel;
import com.sdut_grade.model.GradeModel;
import com.sdut_grade.model.TeacherModel;

import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;


public class GradeManage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JToolBar jJToolBarBar = null;
	private JButton jButton_course_add = null;
	private JButton jButton_course_query = null;
	private JButton jButton_course_delete = null;
	private JButton jButton_course_flash = null;
	private JLabel jLabel_course_counts = null;
	private ButtonGroup buttonts = null;
	private String teach_sex = "��";  //  @jve:decl-index=0:
	private int counts=0;
	
	private List<GradeModel> grade_lists;  //  @jve:decl-index=0:
	
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

	public GradeManage() {
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
//		jLabel_teach_sex = new JLabel();
//		jLabel_teach_sex.setText("��ʦ�Ա�");
//		jLabel_teach_sex.setBounds(new Rectangle(23, 71, 88, 26));
		jLabel_teach_name = new JLabel();
		jLabel_teach_name.setText("�꼶���ƣ�");
		jLabel_teach_name.setBounds(new Rectangle(23, 25, 88, 28));
//		jLabel_teach_educatioon = new JLabel();
//		jLabel_teach_educatioon.setText("��ʦѧ����");
//		jLabel_teach_educatioon.setBounds(new Rectangle(245, 20, 73, 30));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(497, 479);
		this.setTitle("�꼶����");
		//this.setModal(true);
		this.setLocationRelativeTo(null);
			jButton_course_add = new JButton();
			jButton_course_add.setText("���");
			jButton_course_query = new JButton();
			jButton_course_query.setText("�޸�");
			jButton_course_delete = new JButton();
			jButton_course_delete.setText("ɾ��");
			jButton_course_flash = new JButton();
			jButton_course_flash.setText("ˢ��");
			

//			jRadioButton_boy = new JRadioButton("��",true);
//			jRadioButton_boy.setBounds(new Rectangle(119, 69, 59, 28));
//		
//
//			jRadioButton_girl = new JRadioButton("Ů",false);
//			jRadioButton_girl.setBounds(new Rectangle(179, 69, 56, 30));
//			
//			jRadioButton_boy.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					teach_sex = "��";
//				}
//			});
//			jRadioButton_girl.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					teach_sex = "Ů";
//				}
//			});
			
			//gettsButtonGroup();// ���밴ť��
			
			

			jButton_ok = new JButton();
			jButton_ok.setBounds(new Rectangle(100, 65, 94, 29));
			jButton_ok.setText("ȷ��");
			
			jButton_cancel=new JButton();
			jButton_cancel.setBounds(new Rectangle(247, 65, 94, 29));
			jButton_cancel.setText("ȡ��");
			
			
	
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
					jPanel.setBorder(BorderFactory.createTitledBorder("�꼶�������"));
					jPanel.add(jLabel_teach_name, null);
//					jPanel.add(jLabel_teach_sex, null);
					jPanel.add(jTextField_teach_name, null);
//					jPanel.add(jComboBox_teach_education, null);
//					jPanel.add(jLabel_teach_educatioon, null);
					jPanel.add(jButton_ok, null);
					jPanel.add(jButton_cancel, null);
//					jPanel.add(jRadioButton_boy, null);
//					jPanel.add(jRadioButton_boy, null);
//					jPanel.add(jRadioButton_boy, null);
//				
//					jPanel.add(jRadioButton_girl, null);
			
				jContentPane = new JPanel();
				jContentPane.setLayout(null);
				jContentPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
				jContentPane.add(jScrollPane, null);
				jContentPane.add(jJToolBarBar, null);
				jContentPane.add(jPanel, null);
				this.setContentPane(jContentPane);
				jContentPane.add(jScrollPane, null);
			
			this.setContentPane(jContentPane);
			

			

	}
	
	private void gettsButtonGroup() {// ��ť��
		if (buttonts == null) {
			buttonts = new ButtonGroup();
			buttonts.add(jRadioButton_boy);
			buttonts.add(jRadioButton_girl);
		}
	}
	
	/**
	 * @��ʼ������
	 */
	public void initData() {

		String heads[] = { "�꼶ID", "�꼶����"};
		model = new DefaultTableModel(null, heads);
//		jComboBox_teach_education.addItem("ѧʿ");
//		jComboBox_teach_education.addItem("˶ʿ");
//		jComboBox_teach_education.addItem("��ʿ");
//		jComboBox_teach_education.addItem("��ʿ");
		
		GradeDao gd = new GradeDao();
		grade_lists = gd.getGradeModelList();
		
		jTextField_teach_name.setEditable(false);
		jButton_ok.setEnabled(false);
		flashData();
		
		
	}
	
	public void flashData(){
		counts = grade_lists.size();
		model.setRowCount(grade_lists.size());// ��������
			for(int i=0;i<counts;i++){
				model.setValueAt(grade_lists.get(i).getGrade_id(), i, 0);
				model.setValueAt(grade_lists.get(i).getGrade_name(), i, 1);

			}
			jLabel_course_counts.setText("��¼��:" + counts + "");
			jTable.setModel(model);
	}
			
	/**
	 * �ڲ��������ģ��
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
				
			}
			else if (e.getSource() == jButton_course_delete) {
				if (jTable.getSelectedRow() != -1) {	
					int grade_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
					if(JOptionPane.showConfirmDialog(null, "�꼶�¿����ж���༶�������ɾ����������ܵ��³�������Ƿ�ȷ��ɾ����") == JOptionPane.OK_OPTION){
						if(gd.deleteListByGradeId(grade_id)){
							JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
							initData();
						}else{
							JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ����꼶���а༶������ɾ���༶��");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ�����У�");
				}
			}
			else if (e.getSource() == jButton_course_query) {
				if (jTable.getSelectedRow() != -1) {	
					int grade_id = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(), 0).toString());
					GradeModel gradeModelTmp = gd.getGradeModel(grade_id);
					if(null == gradeModelTmp){
						JOptionPane.showMessageDialog(null, "�����ڸ����ݣ���ˢ����壡"+grade_id);
						return;
					}
					Object input_objObject = JOptionPane.showInputDialog(null,"���������ƣ�\n","�޸��꼶����",JOptionPane.PLAIN_MESSAGE,null,null,gradeModelTmp.getGrade_name());
					if(input_objObject != null){
						String  grade_name = input_objObject.toString();
						if("".equals(grade_name)){
							return;
						}
						if(grade_name.equals(gradeModelTmp.getGrade_name()))return;
						if(gd.modifyGrade(grade_id, grade_name)){
							JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
							initData();
							return;
						}
					}
					JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");				
					//model.removeRow(jTable.getSelectedRow());
					//���������Ǻܶ࣬�о�����ˢ��һ�κõ�
					//counts = td.getLists(false, -1).size();
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ��У�");
				}
				
				//grade_lists = td.getLists(true, teach_id);
				flashData();	
			}
			else if (e.getSource() == jButton_course_flash) {
				initData();
			}
			else if(e.getSource()==jButton_ok){
				String grade_name = jTextField_teach_name.getText();
				if("".equals(grade_name)){
					JOptionPane.showMessageDialog(null, "���������ƣ�");
					return;
				}
				//String teach_education = teacher_lists.get(jComboBox_teach_education.getSelectedIndex()).getEducation();
				//String teach_education = jComboBox_teach_education.getSelectedItem().toString();
				if(gd.addGrade(grade_name))
				{
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
				}else{
					JOptionPane.showMessageDialog(null, "���ʧ��");
				}
				//grade_lists = td.getLists(false, -1);
				initData();
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


	public static void main(String args[]){
		GradeManage cm = new GradeManage();
		cm.setVisible(true);
	}
	
	




}  //  @jve:decl-index=0:visual-constraint="10,10"  
