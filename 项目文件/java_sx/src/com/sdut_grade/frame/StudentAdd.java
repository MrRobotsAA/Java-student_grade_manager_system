package com.sdut_grade.frame;

import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.sdut_grade.dao.ClassDao;
import com.sdut_grade.dao.StudentDao;
import com.sdut_grade.model.ClassModel;
public class StudentAdd extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel_number = null;
	private JLabel jLabel_name = null;
	private JLabel jLabel_sex = null;
	private JLabel jLabel_class = null;
	private JTextField jTextField_stu_id = null;
	private JTextField jTextField_stu_name = null;
	private JButton jButton_stu_add = null;
	private JButton jButton_cancel = null;
	private JComboBox jComboBox_class_name = null;
	private JRadioButton jRadioButton_Boy = null;
	private JRadioButton jRadioButton_Girl = null;
	private ButtonGroup buttonts = null;  //  @jve:decl-index=0:
	private String stu_sex = "��";  //  @jve:decl-index=0:

	private boolean isModify = false;//����ӻ����޸ģ�
	
	private List<ClassModel> class_lists;  //  @jve:decl-index=0:
	private int stu_id;
	/**
	 * @��ӡ��޸�ѧ��ʹ��ͬһ����
	 * @param isModify ֵΪtrue������и��²���
	 * @param stu_id Ҫ���µ�ѧ��id
	 */
	public StudentAdd(boolean isModify,int stu_id) {
		
		super();
		this.setModal(true);
		initialize();
		this.isModify = isModify;
		this.stu_id = stu_id;
		if(isModify) 
		modifyStudent(isModify, stu_id);
	}

	private void initialize() {
		this.setSize(252, 214);
		this.setModal(true);
		this.setTitle("���ѧ��");
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("../images/icon.png")));
		jLabel_class = new JLabel();
		jLabel_class.setBounds(new Rectangle(26, 116, 70, 23));

		jLabel_class.setText("�༶��");
		jLabel_sex = new JLabel();
		jLabel_sex.setBounds(new Rectangle(27, 80, 69, 25));
		
		jComboBox_class_name = new JComboBox();
		jComboBox_class_name.setBounds(new Rectangle(113, 113, 122, 26));
		ClassDao cd = new ClassDao();
		class_lists = cd.getLists();
		for(int i=0;i<class_lists.size();i++){
			String class_name = class_lists.get(i).getClass_name();
			jComboBox_class_name.addItem(class_name);
			
			
		}

		
		jLabel_sex.setText("�Ա�");
		jLabel_name = new JLabel();
		jLabel_name.setBounds(new Rectangle(26, 48, 72, 22));
		jLabel_name.setText("������");
		jLabel_number = new JLabel();
		jLabel_number.setBounds(new Rectangle(26, 9, 71, 28));
		jLabel_number.setText("ѧ�ţ�");
		jTextField_stu_id = new JTextField();
		//jTextField_stu_id.setText("��ȡ��...");
	//	jTextField_stu_id.setEditable(false);
		jTextField_stu_id.setBounds(new Rectangle(112, 14, 125, 24));
		

		jTextField_stu_name = new JTextField();
		jTextField_stu_name.setBounds(new Rectangle(112, 50, 125, 24));

		jButton_stu_add = new JButton();
		jButton_stu_add.setBounds(new Rectangle(21, 146, 78, 26));

		jButton_stu_add.setText("ȷ��");
		jButton_cancel = new JButton();
		jButton_cancel.setBounds(new Rectangle(156, 146, 78, 26));
		jButton_cancel.setText("ȡ��");

		

		jRadioButton_Boy = new JRadioButton("��", true);// Ĭ��ѡ����
		jRadioButton_Boy.setBounds(new Rectangle(107, 83, 64, 22));
		jRadioButton_Girl = new JRadioButton("Ů", false);
		jRadioButton_Girl.setBounds(new Rectangle(173, 82, 64, 28));
		
		
		jRadioButton_Boy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				stu_sex = "��";
			}
		});
		jRadioButton_Girl.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				stu_sex = "Ů";
			}
		});
		
		gettsButtonGroup();// ���밴ť��

		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jLabel_number, null);
		jContentPane.add(jLabel_name, null);
		jContentPane.add(jLabel_sex, null);
		jContentPane.add(jLabel_class, null);
		jContentPane.add(jTextField_stu_id, null);
		jContentPane.add(jTextField_stu_name, null);
		jContentPane.add(jButton_stu_add, null);
		jContentPane.add(jButton_cancel, null);
		jContentPane.add(jComboBox_class_name, null);
		jContentPane.add(jRadioButton_Boy, null);
		jContentPane.add(jRadioButton_Girl, null);

		this.setContentPane(jContentPane);
		jButton_stu_add.addActionListener(new btListener());
		jButton_cancel.addActionListener(new btListener());
	}

	private void gettsButtonGroup() {// ��ť��
		if (buttonts == null) {
			buttonts = new ButtonGroup();
			buttonts.add(jRadioButton_Boy);
			buttonts.add(jRadioButton_Girl);
		}
	}

	/**
	 * ���ܲ������޸���Ϣ
	 */
	private void modifyStudent(boolean isModify,int stu_id) {
		StudentDao sd = new StudentDao();
		ClassDao cd = new ClassDao();
		
		this.stu_id=stu_id;
		jTextField_stu_id.setText(Integer.toString(stu_id));
		
		String stu_name = sd.getLists(true, stu_id).get(0).getStu_name();
		String stu_sex = sd.getLists(true, stu_id).get(0).getStu_sex();
		jTextField_stu_name.setText(stu_name);
		
		if (stu_sex.equals("��")) {
			jRadioButton_Boy.setSelected(true);
		} else {
			jRadioButton_Girl.setSelected(true);
		}
		
		List<ClassModel> class_lists = cd.getLists();//���еİ༶��Ϣ
		
		//��Ч��
		for(int i=0;i<class_lists.size();i++){
			if(class_lists.get(i).getClass_id()==sd.getLists(true, stu_id).get(0).getClass_id()){
				jComboBox_class_name.setSelectedIndex(i);
				break;
			}else{
				jComboBox_class_name.setSelectedIndex(0);
			}
		}
		
		
		this.setTitle("�޸�ѧ��");
	}
	

	private class btListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jButton_cancel) {
				dispose();
				
			} else if (e.getSource() == jButton_stu_add) {
		
				if (!isModify) {
					StudentDao sd = new StudentDao();
					
					String stu_name  = jTextField_stu_name.getText();
					int class_id = class_lists.get(jComboBox_class_name.getSelectedIndex()).getClass_id();
					if(sd.addStudent(stu_name, stu_sex,class_id))
						JOptionPane.showMessageDialog(null, "��ӳɹ�");

				} else if (isModify) {
					StudentDao sd = new StudentDao();
					String stu_name = jTextField_stu_name.getText();
					int class_id = class_lists.get(jComboBox_class_name.getSelectedIndex()).getClass_id();
					if(sd.modifyStudentByStuId(stu_id, stu_name, stu_sex, class_id))
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
					dispose();

				} else {
					JOptionPane.showMessageDialog(null, "��������");
					dispose();
				}
			}

		}

	}
}