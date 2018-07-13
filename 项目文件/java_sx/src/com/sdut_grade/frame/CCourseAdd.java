package com.sdut_grade.frame;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.sdut_grade.dao.CCourseDao;
import com.sdut_grade.dao.ClassDao;
import com.sdut_grade.dao.CourseDao;
import com.sdut_grade.dao.GradeDao;
import com.sdut_grade.dao.StudentDao;
import com.sdut_grade.model.CCourseModel;
import com.sdut_grade.model.ClassModel;
import com.sdut_grade.model.CourseModel;
import com.sdut_grade.model.GradeModel;
import com.sdut_grade.model.StudentModel;



public class CCourseAdd extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JButton jButton_ok = null;
	private JButton jButton_cancel = null;
	private JComboBox jComboBox_course = null;
	private JLabel jLabel3 = null;
	private JComboBox jComboBox_stu = null;
	private JLabel jLabel = null;
	private JTextArea jTextArea_ccourse = null;

	private List<GradeModel> grade_lists;
	private List<ClassModel> class_lists;
	private List<StudentModel> student_lists;
	private List<CourseModel> course_lists;  //  @jve:decl-index=0:
	private JLabel jLabel_stu_id = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JComboBox jComboBox_grade = null;
	private JComboBox jComboBox_class = null;

	public CCourseAdd() {
		
		super();
		initialize();
		flashTextArea();
		this.setModal(true);

		jButton_ok.addActionListener(new btnListener());
		jButton_cancel.addActionListener(new btnListener());
		jComboBox_grade.addItemListener(new itemListener());
		jComboBox_class.addItemListener(new itemListener());
		jComboBox_stu.addItemListener(new itemListener());
		//jComboBox_course.addItemListener(new itemListener());
		
	}

	private void initialize() {
		
	//	JDialog.setDefaultLookAndFeelDecorated(false);
	
		jLabel2 = new JLabel();
		jLabel2.setBounds(new Rectangle(261, 33, 65, 18));
		jLabel2.setText("�༶ѡ��");
		jLabel2.setFont(new Font("����",Font.BOLD,12));
		jLabel1 = new JLabel();
		jLabel1.setBounds(new Rectangle(17, 32, 65, 18));
		jLabel1.setText("�꼶ѡ��");
		jLabel1.setFont(new Font("����",Font.BOLD,12));
		jLabel_stu_id = new JLabel();
		jLabel_stu_id.setBounds(new Rectangle(21, 124, 66, 24));
		jLabel_stu_id.setText("JLabel");
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(96, 125, 142, 26));
		jLabel.setText("��ѧ����ѡ�γ̰�����");
		jLabel.setFont(new Font("����",Font.BOLD,12));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.setSize(600, 350);
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setTitle("ѡ��¼��");
		jLabel3 = new JLabel();
		jLabel3.setBounds(new Rectangle(14, 211, 241, 26));
		jLabel3.setText("��ܰ��ʾ����ѡ���ſγ̳ɼ����������");
		jLabel3.setFont(new Font("����",Font.BOLD,12));

		jLabel5 = new JLabel();
		jLabel5.setBounds(new Rectangle(261, 73, 65, 18));
		jLabel5.setText("ѡ�޿γ̣�");
		jLabel5.setFont(new Font("����",Font.BOLD,12));
		jLabel4 = new JLabel();
		jLabel4.setBounds(new Rectangle(19, 70, 65, 18));
		jLabel4.setText("ѡ��ѧ����");
		jLabel4.setFont(new Font("����",Font.BOLD,12));

		jButton_ok = new JButton();
		jButton_ok.setBounds(new Rectangle(16, 165, 77, 26));
		jButton_ok.setText("���");
		jButton_ok.setFont(new Font("����",Font.BOLD,12));

		jButton_cancel = new JButton();
		jButton_cancel.setBounds(new Rectangle(122, 166, 77, 26));
		jButton_cancel.setText("ȡ��");
		jButton_cancel.setFont(new Font("����",Font.BOLD,12));

		jTextArea_ccourse = new JTextArea();
		jTextArea_ccourse.setBounds(new Rectangle(261, 111, 238, 131));
		jTextArea_ccourse.setBackground(SystemColor.control);
		jTextArea_ccourse.setAutoscrolls(true);
		jTextArea_ccourse.setLineWrap(true);

		jComboBox_grade = new JComboBox();
		jComboBox_grade.setBounds(new Rectangle(101, 23, 152, 31));
		GradeDao gd = new GradeDao();
		
		//���ݵ�һ�����ѡ�񣬾����ڶ������ѡ��
		jComboBox_class = new JComboBox();
		jComboBox_class.setBounds(new Rectangle(347, 21, 151, 28));
		ClassDao cd = new ClassDao();
		jComboBox_stu = new JComboBox();
		StudentDao sd = new StudentDao();		
		if(Login.login_user_type == 2){//ĳ��ѧ����½��ֻ��ѡ���Լ��Ŀγ̣�����ѡ����˵Ŀγ�
			StudentDao studentDao = new StudentDao();
			StudentModel studentModel = studentDao.getStudentByName(Login.storeUserName);
			ClassDao cdClassDao = new ClassDao();
			ClassModel classModel = cdClassDao.getListByClassId(studentModel.getClass_id());
			GradeModel gradeModel = gd.getGradeModel(classModel.getGrade_id());
			jComboBox_grade.addItem(gradeModel.getGrade_name());
			jComboBox_grade.setEditable(false);
			jComboBox_class.addItem(classModel.getClass_name());
			jComboBox_class.setEditable(false);
			jComboBox_stu.addItem(studentModel.getStu_name());
			jComboBox_stu.setEditable(false);
			grade_lists = new ArrayList<GradeModel>();
			class_lists = new ArrayList<ClassModel>();
			student_lists = new ArrayList<StudentModel>();
			grade_lists.add(gradeModel);
			class_lists.add(classModel);
			student_lists.add(studentModel);
		}else{
			grade_lists = gd.getGradeModelList();
			for (int i = 0; i < grade_lists.size(); i++) {
				jComboBox_grade.addItem(grade_lists.get(i).getGrade_name());
			}
			class_lists = cd.getListsByGradeId(grade_lists.get(jComboBox_grade.getSelectedIndex()).getGrade_id());
			for (int i = 0; i < class_lists.size(); i++) {
				jComboBox_class.addItem(class_lists.get(i).getClass_name());
			}
			student_lists = sd.getLists(false, -1);
			for (int i = 0; i < student_lists.size(); i++) {
				jComboBox_stu.addItem(student_lists.get(i).getStu_name());
			}
		}

		
		

		jComboBox_course = new JComboBox();
		jComboBox_course.setBounds(new Rectangle(348, 62, 150, 29));
		CourseDao cd1 = new CourseDao();
		course_lists = cd1.getLists(false, -1);
		for (int i = 0; i < course_lists.size(); i++) {
			jComboBox_course.addItem(course_lists.get(i).getCourse_name());
		}

		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jLabel4, null);
		jContentPane.add(jLabel5, null);
		jContentPane.add(jButton_ok, null);
		jContentPane.add(jButton_cancel, null);
		jContentPane.add(jComboBox_course, null);
		jContentPane.add(jLabel3, null);
		jContentPane.add(jComboBox_stu, null);
		jContentPane.add(jLabel, null);
		jContentPane.add(jTextArea_ccourse, null);

		jContentPane.add(jLabel_stu_id, null);
		jContentPane.add(jLabel1, null);
		jContentPane.add(jLabel2, null);
		jContentPane.add(jComboBox_grade, null);
		jContentPane.add(jComboBox_class, null);
		jContentPane.add(jTextArea_ccourse, null);
		jComboBox_stu.setBounds(new Rectangle(102, 63, 150, 30));
		this.setContentPane(jContentPane);
		
		

	}

	/**
	 * @ˢ��JTextArea��ߵ�����
	 */
	void flashTextArea() {

		int stu_id = student_lists.get(jComboBox_stu.getSelectedIndex())
				.getStu_id();
		jLabel_stu_id.setText(Integer.toString(stu_id));

		CCourseDao ccd = new CCourseDao();
		List<CCourseModel> lists = ccd.getListsByStuId(stu_id);
			jTextArea_ccourse.setText("");		
			
			for(int i=0;i<lists.size();i++){
				int course_id = lists.get(i).getCourse_id();
				//����id���Ҷ�Ӧ�Ŀγ�����
				String course_name = ccd.getNameByCourseId(course_id);
				jTextArea_ccourse.append("�γ̱�ţ�" + course_id+"   "+"�γ����ƣ�"+course_name);
				jTextArea_ccourse.append("\n");
				
			}
	}

	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jButton_ok) {
				
				int index0 = jComboBox_stu.getSelectedIndex();
				int index1 = jComboBox_course.getSelectedIndex();
				if(index0==-1||index1==-1)//���û��ѡ��
					{JOptionPane.showMessageDialog(null, "����ʧ�ܣ�������ѡ��");
					return;
					}
				int stu_id =student_lists.get(index0).getStu_id();
				int course_id = course_lists.get(index1).getCourse_id();
				int aaa = course_lists.size();
				
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String dt = sdf.format(new Date());

				CCourseDao ccd = new CCourseDao();
					if (ccd.isCCourseExist(stu_id, course_id)) {
						JOptionPane.showMessageDialog(null,
								"��Ϊ��ѧ����Ӵ�ѡ�γɼ��������ظ���ӣ�", "����δ�ɹ���",JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						
						ccd.addCCourse(stu_id, course_id,dt);
						JOptionPane.showMessageDialog(null, "�����ɹ�");
						flashTextArea();

					}
			} else if (e.getSource() == jButton_cancel) {
				dispose();
			}

		}
	}

	
	
	public class itemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				
				
				if(e.getSource()==jComboBox_grade){
							
					//�ı���һ�� ������ 
					
					jComboBox_class.removeAllItems();
					ClassDao cd = new ClassDao();
					class_lists = cd.getListsByGradeId((grade_lists.get(jComboBox_grade.getSelectedIndex()).getGrade_id()));
					for (int i = 0; i < class_lists.size(); i++) {
						jComboBox_class.addItem(class_lists.get(i).getClass_name());
					}
					
				}else if(e.getSource()==jComboBox_class){
								
					//�ı���һ�� ������ 
					
					jComboBox_stu.removeAllItems();
					StudentDao cd = new StudentDao();
					student_lists = cd.getListsByClassId(class_lists.get(jComboBox_class.getSelectedIndex()).getClass_id());
					for (int i = 0; i < student_lists.size(); i++) {
						jComboBox_stu.addItem(student_lists.get(i).getStu_name());
					}
					
				}else if(e.getSource()==jComboBox_stu){
					flashTextArea();
					
				}
				

			}

		}
		
	}
	

	
	
	
} 
