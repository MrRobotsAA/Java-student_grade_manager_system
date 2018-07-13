package com.sdut_grade.frame;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Rectangle;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Label;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

import com.sdut_grade.dao.StudentDao;
import com.sdut_grade.dao.UserDao;
import com.sdut_grade.model.UserModel;
import com.sdut_grade.util.DBConnection;
/**
 * ѧ���ɼ�����ϵͳ ɽ������ѧ
 * @author ��첩 ����
 * @version 1.0
 * @data 01/09/2018
 * @last update 09/11/2018
 * @Email 910374559@qq.com
 */
public class UserManage extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButton_add = null;
	private Label label_user_name = null;
	private Label label_user_password = null;
	private Label label_user_privilege = null;
	private JTextField jTextField_user_name = null;
	private JPasswordField jPasswordField_user_password = null;
	private JComboBox jComboBox_user_privilege = null;

	private JButton jButton_delete = null;
	private JTable jTable = null;
	private DefaultTableModel model;
	private List<UserModel> user_lists;  //  @jve:decl-index=0:
	private int counts =0 ;
	private JScrollPane jScrollPane = null;
	/**
	 * ���캯��ģ��
	 */
	public UserManage() {
		super();
		initialize();
		this.setModal(true);
		initData();
	}

	private void initialize() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("�û�����");
		this.setModal(true);
		this.setSize(new Dimension(436, 327));
		this.setLocation(new Point(0, 0));
		this.setLocationRelativeTo(null);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("../images/icon.png")));

		jButton_add = new JButton();
		jButton_add.setBounds(new Rectangle(311, 183, 78, 26));
		jButton_add.setText("���");
		jTextField_user_name = new JTextField();
		jTextField_user_name.setBounds(new Rectangle(98, 183, 165, 28));
		jPasswordField_user_password = new JPasswordField();
		jPasswordField_user_password.setBounds(new Rectangle(98, 222, 165, 26));
		jComboBox_user_privilege = new JComboBox();// ��Ҫ©����仰
		jComboBox_user_privilege.addItem("����Ա");
		jComboBox_user_privilege.addItem("��ͨ�û�");
		jComboBox_user_privilege.setBounds(new Rectangle(99, 256, 163, 27));
		jButton_delete = new JButton();
		jButton_delete.setBounds(new Rectangle(314, 228, 78, 26));

		jButton_delete.setText("ɾ��");
		label_user_privilege = new Label();
		label_user_privilege.setBounds(new Rectangle(16, 261, 76, 20));
		label_user_privilege.setText("Ȩ   �ޣ�");
		label_user_password = new Label();
		label_user_password.setBounds(new Rectangle(17, 213, 76, 31));
		label_user_password.setText("��   �룺");
		label_user_name = new Label();
		label_user_name.setBounds(new Rectangle(17, 183, 76, 26));
		label_user_name.setText("�� �� ����");
		
	
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jButton_add, null);
		jContentPane.add(label_user_name, null);
		jContentPane.add(label_user_password, null);
		jContentPane.add(label_user_privilege, null);
		jContentPane.add(jTextField_user_name, null);
		jContentPane.add(jPasswordField_user_password, null);
		jContentPane.add(jComboBox_user_privilege, null);
		jContentPane.add(jButton_delete, null);
		jContentPane.setBorder(BorderFactory.createTitledBorder("����һ�廯"));
		this.setContentPane(jContentPane);
		

		
		jTable = new JTable();
		jScrollPane = new JScrollPane();
		jScrollPane.setBounds(new Rectangle(15, 24, 378, 149));
		jScrollPane.setViewportView(jTable);
		
		jContentPane.add(jScrollPane, null);
		setContentPane(jContentPane);
		
		jButton_add.addActionListener(new btnListener());
		jButton_delete.addActionListener(new btnListener());
	}
	
	

	/**
	 * @��ʼ������
	 */
	public void initData() {

		String heads[] = { "�û�ID", "�û�����", "����¼ʱ��", "����¼ip","�û�Ȩ��" };
		model = new DefaultTableModel(null, heads);
		UserDao ud= new UserDao();
		user_lists = ud.getLists(false, -1);
		flashData();
		
		
	}
	
	public void flashData(){
		counts = user_lists.size();
		model.setRowCount(user_lists.size());// ��������
			for(int i=0;i<counts;i++){
				model.setValueAt(user_lists.get(i).getUser_id(), i, 0);
				model.setValueAt(user_lists.get(i).getUser_name(), i, 1);
				model.setValueAt(user_lists.get(i).getUser_login_time(), i, 2);
				model.setValueAt(user_lists.get(i).getUser_login_ip(), i, 3);
				model.setValueAt(user_lists.get(i).getUser_privilege(), i, 4);

			}
			jTable.setModel(model);
			//jTable.setAutoCreateRowSorter(true);//ΪJTable����������
	}

	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			UserDao ud = new UserDao();
			if (e.getSource() == jButton_delete) {
				int id  = Integer.parseInt(jTable.getValueAt(jTable.getSelectedRow(),0).toString());
					if (Login.storeUserId == id) {
						JOptionPane.showMessageDialog(null, "�ҿ�ѽ���Լ�����ɾ���Լ���");
					} else {
						ud.deleteListByUserId(id);
						user_lists = ud.getLists(false, -1);
						JOptionPane.showMessageDialog(null, "ɾ���û��ɹ���");
						flashData();
					}
				} else if (e.getSource() == jButton_add) {
					
					String user_name = jTextField_user_name.getText();
					String user_password = jPasswordField_user_password.getText().toString();
					String user_privilege = jComboBox_user_privilege.getSelectedItem().toString();
					if("".equals(user_name)){
						JOptionPane.showMessageDialog(null, "�û�������Ϊ�գ�");
						return;
					}
					if("".equals(user_password)){
						JOptionPane.showMessageDialog(null, "���벻��Ϊ�գ�");
						return;
					}
					if(ud.isExist(user_name)){
						JOptionPane.showMessageDialog(null, "�û����Ѿ����ڣ�");
						return;
					}
					if(ud.addUser(user_name, user_password, user_privilege))
						JOptionPane.showMessageDialog(null, "��ӳɹ�");
					user_lists = ud.getLists(false, -1);
					flashData();
				
				}

			}
		}
//	public static void main(String args[]){
//		UserManage um = new UserManage();
//		um.setVisible(true);
//		
//	}
	
}
