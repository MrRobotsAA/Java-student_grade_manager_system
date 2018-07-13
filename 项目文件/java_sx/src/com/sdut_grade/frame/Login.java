package com.sdut_grade.frame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.Rectangle;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.ImageIcon;

import com.sdut_grade.dao.UserDao;
import com.sdut_grade.util.DBConnection;
import com.sdut_grade.util.MusicDemo;
import javax.swing.JComboBox;
import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import com.seaglasslookandfeel.component.SeaGlassArrowButton;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton jButton21 = null;
	private JButton jButton22 = null;
	private JTextField jTextField = null;
	private JPasswordField jPasswordField = null;
	private JLabel jLabel = null;
	static int storeUserId;// ��¼�û���
	public static String storeUserName = null;// ��¼�û���
	public static String storeUserPassword = null;// ��¼����
	static boolean RELOAD = true;// ���µ�½���
	static int login_user_type;// 0��ʾ����Ա��1��ʾ��ʦ��2��ʾѧ��
	private JLabel jLabel_User = null;
	private JLabel jLabel_userName = null;
	private JLabel jLabel_password = null;
	private JLabel jLabel_privilege = null;
	private URL imgURL = null;

	private BtnListener btl = null;
	private JComboBox jComboBox = null;
	private JLabel jLabel_tips = null;

	private void initialize() {

		jLabel_tips = new JLabel();
		jLabel_tips.setBounds(new Rectangle(15, 247, 277, 24));
		jLabel_tips.setText("����Աʹ���˺ŵ�½���ǹ���Ա��ʹ��ID��½");
		this.setResizable(false);
		this.setSize(296, 356);
		// this.setSize(350, 450);
		this.setTitle("��ӭ��½");
		imgURL = this.getClass().getResource(
				"/com/sdut_grade/images/logo_0.png");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgURL));
		this.setLocationRelativeTo(null);
		// this.setUndecorated(true);//�����ޱ߿�
		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// ʹ��windows���
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jButton21 = new JButton();
		jButton21.setBounds(new Rectangle(15, 295, 78, 26));

		/*
		 * InputStream in=this.getClass().getResourceAsStream(""); Reader
		 * data=new InputStreamReader(in);
		 */

		imgURL = this.getClass().getResource("/com/sdut_grade/images/icon.png");// �������Ҳ�����Դ������

		jButton21.setText("��¼");
		jButton21.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.green));
		getRootPane().setDefaultButton(jButton21);// �س���¼

		jButton22 = new JButton();
		jButton22.setUI(new BEButtonUI()
				.setNormalColor(BEButtonUI.NormalColor.lightBlue));
		jButton22.setBounds(new Rectangle(110, 296, 78, 26));
		jButton22.setText("�˳�");

		jTextField = new JTextField(20);
		jTextField.setBounds(new Rectangle(120, 180, 124, 23));

		jPasswordField = new JPasswordField();
		jPasswordField.setBounds(new Rectangle(120, 210, 124, 23));

		jLabel = new JLabel();
		jLabel.setText("");
		jLabel.setBounds(new Rectangle(0, -1, 291, 142));
		imgURL = this.getClass()
				.getResource("/com/sdut_grade/images/login.png");
		jLabel.setIcon(new ImageIcon(imgURL));
		jLabel_password = new JLabel();
		jLabel_password.setBounds(new Rectangle(29, 210, 71, 19));
		jLabel_password.setText("�� �룺");
		jLabel_userName = new JLabel();
		jLabel_userName.setBounds(new Rectangle(29, 181, 71, 19));
		jLabel_userName.setText("�û�����");
		jLabel_User = new JLabel();
		jLabel_User.setBounds(new Rectangle(10, 147, 275, 98));

		jLabel_privilege = new JLabel();
		jLabel_privilege.setBounds(new Rectangle(18, 272, 71, 19));
		jLabel_privilege.setText("��½���ͣ�");

		jComboBox = new JComboBox();
		jComboBox.setBounds(new Rectangle(109, 272, 123, 23));
		jComboBox.addItem("�����½");
		jComboBox.addItem("��ʦ��¼");
		jComboBox.addItem("ѧ����½");

		imgURL = this.getClass().getResource("/com/sdut_grade/images/user.gif");
		jLabel_User.setIcon(new ImageIcon(imgURL));
		jLabel_User.setText("User");

		jContentPane = new JPanel();// �½�jPanel���
		jContentPane.setLayout(null);
		// jContentPane.setBackground(new Color(250,250,250));
		jContentPane.add(jLabel_userName, null);
		jContentPane.add(jLabel_password, null);
		jContentPane.add(jButton21, null);
		jContentPane.add(jButton22, null);
		jContentPane.add(jTextField, null);
		jContentPane.add(jPasswordField, null);
		jContentPane.add(jLabel, null);
		jContentPane.add(jLabel_User, null);

		jContentPane.add(jComboBox, null);
		jContentPane.add(jLabel_privilege, null);
		jContentPane.add(jLabel_tips, null);
		setContentPane(jContentPane);

		btl = new BtnListener();
		jButton21.addActionListener(btl);
		jButton22.addActionListener(btl);

	}

	/**
	 * @������
	 * @author Administrator
	 */
	public class BtnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jButton21) {
				UserDao ud = new UserDao();
				String user = jTextField.getText().trim();
				String password = new String(jPasswordField.getPassword())
						.trim();// char to String

				storeUserName = user;
				storeUserPassword = password;
				login_user_type = jComboBox.getSelectedIndex();

				if ("".equals(user)) {
					JOptionPane.showMessageDialog(null, "�û�������Ϊ��");
					return;
				}
				if ("".equals(password)) {
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
					return;
				}

				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				String dt = sdf.format(new java.util.Date());

				// ����ǹ���Ա������ֱ��ʹ���˻���¼
				if (login_user_type == 0) {

					if (ud.userLogin(login_user_type, storeUserName,
							storeUserPassword)) {

						dispose();
						try {

							UIManager.put("RootPane.setupButtonVisible", false);
							org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper
									.launchBeautyEyeLNF();

							// ��һ��������ɫͼ�������һ��
							// BeautyEyeLNFHelper.frameBorderStyle =
							// BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;

							// ʵ�ĵļ��Ϻ�����һ�ѡ�
							BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
							UIManager.put("ToolBar.isPaintPlainBackground",
									Boolean.FALSE);
							BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
							BeautyEyeLNFHelper.launchBeautyEyeLNF();
							// UIManager.put("RootPane.setupButtonVisible",
							// false);
							UIManager.put("TabbedPane.tabAreaInsets",
									new InsetsUIResource(0, 0, 0, 0));
							UIManager.put("TabbedPane.contentBorderInsets",
									new InsetsUIResource(0, 0, 2, 0));
							UIManager.put("TabbedPane.tabInsets",
									new InsetsUIResource(3, 10, 9, 10));
							Font frameTitleFont = (Font) UIManager
									.get("InternalFrame.titleFont");
							frameTitleFont = frameTitleFont
									.deriveFont(Font.PLAIN);
							UIManager.put("InternalFrame.titleFont",
									frameTitleFont);
						} catch (Exception e1) {
							// TODO exception
						}
						MainFrame mf = new MainFrame();
						mf.setVisible(true);
						JOptionPane.showMessageDialog(null, "��ӭ " + user
								+ "��½��", "����ѡ�޿γ̹���ϵͳ",
								JOptionPane.INFORMATION_MESSAGE);
						storeUserId = ud.getUserIdByUserName(storeUserName);
						String log_operate = "[" + storeUserName + "]"
								+ "����Ա��½ϵͳ";
						DBConnection
								.update("insert into c_log(login_user,log_operate,log_time) values('"
										+ storeUserName
										+ "','"
										+ log_operate
										+ "','" + dt + "')");
					} else {
						JOptionPane.showMessageDialog(null, "��¼ʧ��");
						return;
					}
					// ��ʦ��¼
				} else if (login_user_type == 1) {

					if (ud.userLogin(login_user_type, storeUserName,
							storeUserPassword)) {

						dispose();
						MainFrame mf = new MainFrame();
						mf.setVisible(true);
						JOptionPane.showMessageDialog(null, "��ӭ "
								+ storeUserName + "�Ž�ʦ��½��", "����ѡ�޿γ̹���ϵͳ",
								JOptionPane.INFORMATION_MESSAGE);

						String log_operate = "[" + storeUserName + "]�Ž�ʦ"
								+ "�û���½ϵͳ";
						DBConnection
								.update("insert into c_log(login_user,log_operate,log_time) values('"
										+ storeUserName
										+ "','"
										+ log_operate
										+ "','" + dt + "')");

					} else {
						JOptionPane.showMessageDialog(null, "��¼ʧ��");
						return;
					}
				} else if (login_user_type == 2) {

					if (ud.userLogin(login_user_type, storeUserName,
							storeUserPassword)) {
						dispose();
						MainFrame mf = new MainFrame();
						mf.setVisible(true);
						JOptionPane.showMessageDialog(null, "��ӭ " + user
								+ "��ѧ����½��", "����ѡ�޿γ̹���ϵͳ",
								JOptionPane.INFORMATION_MESSAGE);
						String log_operate = "[" + storeUserName + "]"
								+ "��ѧ����½ϵͳ";
						DBConnection
								.update("insert into c_log(login_user,log_operate,log_time) values('"
										+ storeUserName
										+ "','"
										+ log_operate
										+ "','" + dt + "')");
					} else {
						JOptionPane.showMessageDialog(null, "��¼ʧ��");
						return;
					}
				}
			}

			else if (e.getSource() == jButton22) {
				System.exit(0);
			}
		}
	}

	/**
	 * @������
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		try {
			UIManager.put("RootPane.setupButtonVisible", false);
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
			UIManager.put("ToolBar.isPaintPlainBackground", Boolean.FALSE);

		} catch (Exception e) {
			// TODO exception
		}
		BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
		BeautyEyeLNFHelper.launchBeautyEyeLNF();
		 UIManager.put("RootPane.setupButtonVisible", false);
		UIManager.put("TabbedPane.tabAreaInsets", new InsetsUIResource(0, 0, 0,
				0));
		UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(0,
				0, 2, 0));
		UIManager.put("TabbedPane.tabInsets",
				new InsetsUIResource(3, 10, 9, 10));
		Font frameTitleFont = (Font) UIManager.get("InternalFrame.titleFont");
		frameTitleFont = frameTitleFont.deriveFont(Font.PLAIN);
		UIManager.put("InternalFrame.titleFont", frameTitleFont);

		Login login = new Login(RELOAD);
		login.setVisible(true);
	}

	public Login() {
		super();
		initialize();
	}

	public Login(boolean reload) {
		super();
		initialize();
		new MusicDemo();// ��������
	}
} // @jve:decl-index=0:visual-constraint="10,10"
