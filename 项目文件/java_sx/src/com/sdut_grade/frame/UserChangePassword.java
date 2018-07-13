package com.sdut_grade.frame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import com.sdut_grade.dao.UserDao;
import com.sdut_grade.util.DBConnection;

public class UserChangePassword extends JDialog{
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JPasswordField jPasswordField_old = null;
	private JPasswordField jPasswordField_new1 = null;
	private JPasswordField jPasswordField_new2 = null;
	private JButton jButton = null;
	private JButton jButton1 = null;
	
	
	ImageIcon icon0 = new ImageIcon(getClass().getResource("../images/male.gif"));
	ImageIcon icon1 = new ImageIcon(getClass().getResource("../images/female.gif"));
	/**
	 * ���캯��ģ��
	 */
	public UserChangePassword() {
		super();
		initialize();
		this.setModal(true);
	}

	private void initialize() {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(255, 229);
		this.setContentPane(getJContentPane());
		this.setModal(true);
		this.setLocationRelativeTo(null);
		this.setTitle("�޸�����");
	}

	/**
	 * ��ȡ�������ģ��
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(16, 113, 74, 29));
			jLabel2.setText("ȷ�����룺");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(17, 71, 74, 31));
			jLabel1.setText("�����룺");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(18, 27, 70, 35));
			jLabel.setText("�����룺");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(getJPasswordField_old(), null);
			jContentPane.add(getJPasswordField_new1(), null);
			jContentPane.add(getJPasswordField_new2(), null);
			jContentPane.add(getJButton(), null);
			jContentPane.add(getJButton1(), null);
			jContentPane.setBorder(BorderFactory.createTitledBorder("�޸�����"));
		}
		return jContentPane;
	}

	private JPasswordField getJPasswordField_old() {
		if (jPasswordField_old == null) {
			jPasswordField_old = new JPasswordField();
			jPasswordField_old.setBounds(new Rectangle(102, 35, 125, 24));
		}
		return jPasswordField_old;
	}

	private JPasswordField getJPasswordField_new1() {
		if (jPasswordField_new1 == null) {
			jPasswordField_new1 = new JPasswordField();
			jPasswordField_new1.setBounds(new Rectangle(101, 78, 124, 24));
		}
		return jPasswordField_new1;
	}

	private JPasswordField getJPasswordField_new2() {
		if (jPasswordField_new2 == null) {
			jPasswordField_new2 = new JPasswordField();
			jPasswordField_new2.setBounds(new Rectangle(102, 118, 124, 24));
		}
		return jPasswordField_new2;
	}

	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(15, 153, 77, 27));
			jButton.setText("ȷ��");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					UserDao ud = new UserDao();
					
					String old = new String(getJPasswordField_old().getPassword());
					String new1 = new String(getJPasswordField_new1().getPassword());
					String new2 = new String(getJPasswordField_new2().getPassword());
			
					if (old.equals(Login.storeUserPassword)) {
						if (new1.equals(new2)) {
							ud.changeUserPassword(0, Login.storeUserName, Login.storeUserPassword, new1);
							JOptionPane.showMessageDialog(null,
									"һ������Ҫ��İ����·����İ���ķ羰����İ���ĸ裬\nȻ����ĳ���������˲�䣬��ᷢ�֣�\nԭ���Ѿ��Ļ���Ҫ���ǵ�������ľ���ô�����ˡ�", "�����޸ĳɹ�",
									JOptionPane.INFORMATION_MESSAGE,icon0);
							Login.storeUserPassword = new1;//�Ѹĺõ�����д��ȥ��
							dispose();
						} else {
							JOptionPane.showMessageDialog(null,
									"��������ȷ���������������룬���������룡", "ϵͳ��ʾ",
									JOptionPane.ERROR_MESSAGE);
							return;} }	
						else {
		                    JOptionPane.showMessageDialog(null,"�����ٵ���������һ���ӣ�\n�������޷���ȴ�Ļ��䣬\n�Ұ�����ֵ�ý��յ����껪��\n�м����ʵģ������������국���ĸ��ˣ�","�����벻��ȷ",
                                    JOptionPane.INFORMATION_MESSAGE,icon1);
		                    return;
		                    }
					
					
					}
				});
	}return jButton;
	
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(147, 153, 77, 27));
			jButton1.setText("ȡ��");
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return jButton1;
	}
	
} 
