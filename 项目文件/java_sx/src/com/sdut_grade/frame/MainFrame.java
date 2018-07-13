package com.sdut_grade.frame;
import java.awt.*;
import javax.swing.*;
 
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
 
import com.seaglasslookandfeel.component.SeaGlassArrowButton;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.InsetsUIResource;

import com.sdut_grade.util.About_syjc;
import com.sdut_grade.util.DBConnection;
import com.sdut_grade.util.DBInit;
import com.sdut_grade.util.Main;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JMenu jMenu_start = null;
	private JMenu jMenu_start2 = null;
	private JMenuBar jJMenuBar = null;
	private JMenuBar jJMenuBar2 = null;
	private JMenu jMenu_backstage  = null;
	private JMenu jMenu_ccourse = null;
	private JMenu jMenu_mark = null;
	private JMenu jMenu_jdk = null;
	private JMenu jMenu_help = null;
	private JMenuItem jMenuItem_relogin = null;
	public  JMenuItem jMenuItem_initDB = null;
	public  JMenu jMenuItem_hf = null;
	private JMenuItem jMenuItem_change_password = null;
	public  JMenuItem jMenuItem_user_manage = null;
	private JMenuItem jMenuItem_hang_system = null;
	private JMenuItem jMenuItem_exit = null;
	private JMenuItem jMenuItem_student_manage = null;
	private JMenuItem jMenuItem_teacher_manage = null;
	private JMenuItem jMenuItem_course_manage = null;
	private JMenuItem jMenuItem_grade_class = null;
	private JMenuItem jMenuItem_class = null;
	private JMenuItem jMenuItem_about = null;
	private JMenuItem jMenuItem_else = null;
	private JMenuItem jMenuItem_online_update = null;
	private JMenuItem jm_item0=null;
	private JMenuItem jm_item1=null;
	private JMenuItem jm_item2=null;
	private JMenuItem jm_item3=null;
	private JMenuItem jm_item4=null;
	
	private JMenuItem jMenuItem_sys_info = null;
	public JMenuItem  jMenuItem_operate_log = null;
	private JMenuItem jMenuItem_ccourse = null;
	private JMenuItem jMenuItem_ccourse_add = null;
	private JMenuItem jMenuItem_mark_add = null;
	private JMenuItem jMenuItem_bb = null;
	private JMenuItem jMenuItem_mark_statistics = null;
	private JMenuItem jMenuItem_jdk_bf = null;
	private JMenuItem jMenuItem_jdk_hf = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private URL imgURL = null;
	private URL imgURL1 = null;
	private SystemTray sysTray = SystemTray.getSystemTray();
	private int x,y;
	
	Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/com/sdut_grade/images/icon.png"));
	private TrayIcon trayicon = new TrayIcon(image, "�ɼ�����ϵͳ", createMenu());

	public MainFrame() {
		
		super();
		try
        {
	
			
			 UIManager.put("RootPane.setupButtonVisible", false);
	         org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	            
			//��һ��������ɫͼ�������һ��
			// BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
           
	        //ʵ�ĵļ��Ϻ�����һ�ѡ�
            BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
            UIManager.put("ToolBar.isPaintPlainBackground", Boolean.FALSE);
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
         //   UIManager.put("RootPane.setupButtonVisible", false);      
            UIManager.put("TabbedPane.tabAreaInsets", new InsetsUIResource(0,0,0,0));
            UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(0,0,2,0));
            UIManager.put("TabbedPane.tabInsets", new InsetsUIResource(3,10,9,10));
            Font frameTitleFont = (Font)UIManager.get("InternalFrame.titleFont");
            frameTitleFont = frameTitleFont.deriveFont(Font.PLAIN);
            UIManager.put("InternalFrame.titleFont", frameTitleFont);
        }
        catch(Exception e)
        {
            //TODO exception
        }
		initialize(0);
		initPrivilege();
	}

	private void initialize(int tt) {		
//		//this.setSize(800, 533);// �������С
//		//this.setSize(1350,755);
//		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setTitle("ѧ���ɼ�����ϵͳ V1.0");
     	imgURL = this.getClass().getResource("/com/sdut_grade/images/icon.png");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(imgURL));
//		this.setLocationRelativeTo(null);
//		this.setResizable(false);
//		this.setVisible(true);
		 Toolkit kit = Toolkit.getDefaultToolkit();
	     Dimension dimension = kit.getScreenSize();
	     this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	     this.setBounds(0, 0, dimension.width, dimension.height);
	     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     //this.setUndecorated(true);
	     this.setAlwaysOnTop(false);
	     this.setVisible(true);

		
		this.addWindowListener(new WindowAdapter()// ϵͳ�ر��¼�
		{
			public void windowClosing(WindowEvent e) {
				SystemTrayInitial();//��ʼ������ͼ��
			}
		});

		jMenuItem_relogin = new JMenuItem();
		jMenuItem_relogin.setText("���µ�¼");
		jMenuItem_operate_log = new JMenuItem();
		jMenuItem_operate_log.setText("������־");
		jMenuItem_change_password = new JMenuItem();
		jMenuItem_change_password.setText("�޸�����");
		jMenuItem_user_manage = new JMenuItem();
		jMenuItem_user_manage.setText("�û�����");
		jMenuItem_initDB = new JMenuItem();
		jMenuItem_initDB.setText("��ʼ�����ݿ�");
		jMenuItem_hf = new JMenu();
		jMenuItem_hf.setText("һ������");
		jMenuItem_hang_system = new JMenuItem();
		jMenuItem_hang_system.setText("�һ�");
		jMenuItem_exit = new JMenuItem();
		jMenuItem_exit.setText("�˳�");
		jMenuItem_student_manage = new JMenuItem();
		jMenuItem_student_manage.setText("ѧ����Ϣ����");
		jMenuItem_teacher_manage = new JMenuItem();
		jMenuItem_teacher_manage.setText("��ʦ��Ϣ����");
		jMenuItem_course_manage = new JMenuItem();
		jMenuItem_course_manage.setText("�γ���Ϣ����");
		jMenuItem_grade_class = new JMenuItem();
		jMenuItem_grade_class.setText("�꼶��Ϣ����");
		jMenuItem_class = new JMenuItem();
		jMenuItem_class.setText("�༶��Ϣ����");
		jMenuItem_ccourse_add = new JMenuItem();
		jMenuItem_ccourse_add.setText("ѡ��¼��");
		jMenuItem_ccourse = new JMenuItem();
		jMenuItem_ccourse.setText("ѡ������");
		
		jMenuItem_mark_add = new JMenuItem();
		jMenuItem_mark_add.setText("�ɼ�¼��");
		jMenuItem_mark_statistics = new JMenuItem();
		jMenuItem_mark_statistics.setText("�ɼ�ͳ��");
		jMenuItem_bb = new JMenuItem();
		jMenuItem_bb.setText("�������");
		
		jMenuItem_jdk_bf = new JMenuItem();
		jMenuItem_jdk_bf.setText("���ݿⱸ��");
		jMenuItem_jdk_hf = new JMenuItem();
		jMenuItem_jdk_hf.setText("���ݿ�ָ�");
		
		
		jMenuItem_about = new JMenuItem();
		jMenuItem_about.setText("����ϵͳ");
		jMenuItem_sys_info = new JMenuItem();
		jMenuItem_sys_info.setText("ϵͳ˵��");
		jMenuItem_else = new JMenuItem();
		jMenuItem_else.setText("ʹ�ý̳�");
		
		jMenuItem_online_update = new JMenuItem();
		jMenuItem_online_update.setText("��������");
		
		
		jMenu_start = new JMenu();
		jMenu_start.setText("��ʼ�˵�");
		jMenu_start.add(jMenuItem_relogin);
		jMenu_start.add(jMenuItem_change_password);
		jMenu_start.add(jMenuItem_user_manage);
		jMenu_start.add(jMenuItem_operate_log);
		jMenu_start.add(jMenuItem_hf);
		jMenu_start.addSeparator();// �ָ���
		//
		JMenuItem jm_item0=new JMenuItem("picture0");
		JMenuItem jm_item1=new JMenuItem("picture1");
		JMenuItem jm_item2=new JMenuItem("picture2");
		JMenuItem jm_item3=new JMenuItem("picture3");
		JMenuItem jm_item4=new JMenuItem("picture4");
		jMenuItem_hf.add(jm_item0);
		jMenuItem_hf.add(jm_item1);
		jMenuItem_hf.add(jm_item2);
		jMenuItem_hf.add(jm_item3);
		jMenuItem_hf.add(jm_item4);
		//JMenuBar jBar=new JMenuBar();
		//jBar.add(jMenu);
		//jMenuItem_hf.add(jMenu);
		//jMenu_start.add(jMenuItem_hang_system);
		jMenu_start.add(jMenuItem_exit);
		jMenu_backstage  = new JMenu();
		jMenu_backstage .setText("��̨����");
		jMenu_backstage .add(jMenuItem_student_manage);
		jMenu_backstage .add(jMenuItem_teacher_manage);
		jMenu_backstage .add(jMenuItem_course_manage);
		jMenu_backstage .add(jMenuItem_grade_class);
		jMenu_backstage .add(jMenuItem_class);
		jMenu_backstage.setForeground(Color.magenta);
		
		jMenu_ccourse = new JMenu();
		jMenu_ccourse.setText("ѡ�����");
		jMenu_ccourse.add(jMenuItem_ccourse_add);
		jMenu_ccourse.add(jMenuItem_ccourse);
		jMenu_ccourse.setForeground(Color.orange);
		
		jMenu_mark = new JMenu();
		jMenu_mark.setText("�ɼ�����");
		jMenu_mark.add(jMenuItem_mark_add);
		jMenu_mark.add(jMenuItem_mark_statistics);
		jMenu_mark.add(jMenuItem_bb);
		//jMenu_mark.setFont(new Font("����",Font.PLAIN,16));
		jMenu_mark.setForeground(Color.blue);
		
		jMenu_jdk = new JMenu();
		jMenu_jdk.setText("���ݿ����");
		jMenu_jdk.add(jMenuItem_initDB);
		jMenu_jdk.add(jMenuItem_jdk_bf);
		jMenu_jdk.add(jMenuItem_jdk_hf);
		jMenu_jdk.setForeground(Color.green);
		
		jMenu_help = new JMenu();
		jMenu_help.setText("����");
		jMenu_help.add(jMenuItem_about);
		jMenu_help.add(jMenuItem_sys_info);
		jMenu_help.add(jMenuItem_else);
		//jMenu_help.add(jMenuItem_online_update);
		jJMenuBar = new JMenuBar();
		jJMenuBar.setPreferredSize(new Dimension(10, 25));
		jJMenuBar.add(jMenu_start);
		jJMenuBar.add(jMenu_backstage );
		jJMenuBar.add(jMenu_ccourse);
		jJMenuBar.add(jMenu_mark);
		jJMenuBar.add(jMenu_jdk);
		jJMenuBar.add(jMenu_help);
		
		//jJMenuBar.setForeground(Color.red);
		jJMenuBar.setBackground(Color.getHSBColor(192,220,243));
		setJMenuBar(jJMenuBar);

		jLabel = new JLabel();
		//jLabel.setText("JLabel");
		//jLabel.setBounds(new Rectangle(1, -2, 1600, 768));
		x=dimension.width;
		y=dimension.height;
		jLabel.setBounds(0, 0, dimension.width, dimension.height);
		imgURL = this.getClass().getResource("/com/sdut_grade/images/main.jpg");
		jLabel.setIcon(new ImageIcon(imgURL));

		jContentPane = new JPanel();
		//jContentPane.add(jLabel1, null);
		jContentPane.setLayout(null);
		jContentPane.add(jLabel, null);
		setContentPane(jContentPane);
		
		btnListener btn = new btnListener();
		jMenuItem_relogin.addActionListener(btn);
		jMenuItem_change_password.addActionListener(btn);
		jMenuItem_user_manage.addActionListener(btn);
		jMenuItem_initDB.addActionListener(btn);
		jMenuItem_operate_log.addActionListener(btn);
		jMenuItem_hang_system.addActionListener(btn);
		jMenuItem_exit.addActionListener(btn);
		jMenuItem_student_manage.addActionListener(btn);
		jMenuItem_teacher_manage.addActionListener(btn);
		jMenuItem_course_manage.addActionListener(btn);
		jMenuItem_grade_class.addActionListener(btn);
		jMenuItem_class.addActionListener(btn);
		jMenuItem_ccourse_add.addActionListener(btn);
		jMenuItem_about.addActionListener(btn);
		jMenuItem_else.addActionListener(btn);
		jMenuItem_mark_add.addActionListener(btn);
		jMenuItem_mark_statistics.addActionListener(btn);
		jMenuItem_bb.addActionListener(btn);
		jMenuItem_jdk_bf.addActionListener(btn);
		jMenuItem_jdk_hf.addActionListener(btn);
		jMenuItem_ccourse.addActionListener(btn);
		jMenuItem_sys_info.addActionListener(btn);
		jm_item0.addActionListener(btn);
		jm_item1.addActionListener(btn);
		jm_item2.addActionListener(btn);
		jm_item3.addActionListener(btn);
		jm_item4.addActionListener(btn);
		jm_item0.setActionCommand("000");
		jm_item1.setActionCommand("111");
		jm_item2.setActionCommand("222");
		jm_item3.setActionCommand("333");
		jm_item4.setActionCommand("444");
		
	}
/**
 * @��ʼ������
 */
	private void SystemTrayInitial() { // ����
		if (!SystemTray.isSupported()) // �жϵ�ǰϵͳ�Ƿ�֧��ϵͳ��
			return;
				try {
					sysTray.add(trayicon);
				} catch (AWTException e1) {
					e1.printStackTrace();
				}
				setVisible(false);
				trayicon.displayMessage("ѡ�ι���ϵͳ--By axuhongbo and his friends", "ѡ�ι���ϵͳ", MessageType.INFO);// ��������ʱ����ʾ����Ϣ�Ի�
			trayicon.addActionListener(new ActionListener()// ��ͼ��ʱ��ʾ����
					{
						public void actionPerformed(ActionEvent e) {
							sysTray.remove(trayicon);
							setVisible(true);
						}
					});
	}
/**
 * @��ʼ�������Ҽ�
 * @return
 */
	private PopupMenu createMenu() { // ����ϵͳ���˵��ķ���
		PopupMenu menu = new PopupMenu();
		MenuItem exitItem = new MenuItem("�˳���ϵͳ");
		exitItem.addActionListener(new ActionListener() { // ϵͳ���˳��¼�
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
		MenuItem openItem = new MenuItem("��������");
		openItem.addActionListener(new ActionListener() {// ϵͳ���򿪲˵����¼�
					public void actionPerformed(ActionEvent e) {
						if (!isVisible()) {
							setVisible(true);
							sysTray.remove(trayicon);
						}
					}
				});
		
		MenuItem viewItem = new MenuItem("���");
		viewItem.addActionListener(new ActionListener() {// ϵͳ���򿪲˵����¼�
					public void actionPerformed(ActionEvent e) {
						try {
							Runtime.getRuntime().exec("explorer http://axuhongbo.top");
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					}
				});
		
		
		menu.add(openItem);
		menu.add(viewItem);
		menu.addSeparator();
		menu.add(exitItem);
		return menu;
	}

	public void initPrivilege(){
		if(Login.login_user_type==1){
			jMenuItem_change_password.setEnabled(false);
			jMenuItem_operate_log.setEnabled(false);
			jMenuItem_initDB.setEnabled(false);
			jMenuItem_user_manage.setEnabled(false);
			jMenu_backstage .setEnabled(false);
			jMenu_jdk.setEnabled(false);
			
		}else if(Login.login_user_type==0){			
		}else if(Login.login_user_type==2){
			
			jMenuItem_change_password.setEnabled(false);
			jMenuItem_operate_log.setEnabled(false);
			jMenuItem_initDB.setEnabled(false);
			jMenuItem_user_manage.setEnabled(false);
			jMenuItem_sys_info.setEnabled(false);
					
			jMenu_mark.setEnabled(false);
			jMenu_backstage .setEnabled(false);
			jMenu_jdk.setEnabled(false);
			
		}else{
			jMenuItem_change_password.setEnabled(false);
			jMenuItem_operate_log.setEnabled(false);
			jMenuItem_initDB.setEnabled(false);
			jMenuItem_user_manage.setEnabled(false);
			jMenuItem_sys_info.setEnabled(false);
					
			jMenu_mark.setEnabled(false);
			jMenu_backstage .setEnabled(false);
			jMenu_jdk.setEnabled(false);
			
		}
		
	}

	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == jMenuItem_relogin) {
				dispose();
				Login login = new Login();// �����޲������췽�������������ظ�����
				login.setVisible(true);
			} else if (e.getSource() == jMenuItem_change_password) {
				if(Login.login_user_type!=0)
					{JOptionPane.showMessageDialog(null, "�Բ��𣬷Ǻ�̨����Ա�ݲ��ṩ�޸����빦��");
					return;
					}
			
				UserChangePassword cp = new UserChangePassword();
				cp.setVisible(true);
			} else if (e.getSource() == jMenuItem_user_manage) {
			
				UserManage um = new UserManage();
				um.setVisible(true);
			} else if (e.getSource() == jMenuItem_operate_log) {
				LogManage lm = new LogManage();
				lm.setVisible(true);
			} 
			else if (e.getActionCommand().equals("000")) {
				jLabel.setBounds(0, 0, x, y);
			    imgURL = this.getClass().getResource("/com/sdut_grade/images/main.jpg");
				jLabel.setIcon(new ImageIcon(imgURL));
				try
		        {
		            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
		            BeautyEyeLNFHelper.launchBeautyEyeLNF();
		            BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
		            UIManager.put("ToolBar.isPaintPlainBackground", Boolean.FALSE);
		         //   UIManager.put("RootPane.setupButtonVisible", false);      
		            UIManager.put("TabbedPane.tabAreaInsets", new InsetsUIResource(0,0,0,0));
		            UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(0,0,2,0));
		            UIManager.put("TabbedPane.tabInsets", new InsetsUIResource(3,10,9,10));
		            Font frameTitleFont = (Font)UIManager.get("InternalFrame.titleFont");
		            frameTitleFont = frameTitleFont.deriveFont(Font.PLAIN);
		            UIManager.put("InternalFrame.titleFont", frameTitleFont);
		        }
		        catch(Exception ee)
		        {
		            //TODO exception
		        }
			} 
			else if (e.getActionCommand().equals("111")) {
				jLabel.setBounds(0, 0, x, y);
			    imgURL = this.getClass().getResource("/com/sdut_grade/images/main1.jpg");
				jLabel.setIcon(new ImageIcon(imgURL));
				
				try
		        {
		            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
		            BeautyEyeLNFHelper.launchBeautyEyeLNF();
		            BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
		            UIManager.put("ToolBar.isPaintPlainBackground", Boolean.FALSE);
		         //   UIManager.put("RootPane.setupButtonVisible", false);      
		            UIManager.put("TabbedPane.tabAreaInsets", new InsetsUIResource(0,0,0,0));
		            UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(0,0,2,0));
		            UIManager.put("TabbedPane.tabInsets", new InsetsUIResource(3,10,9,10));
		            Font frameTitleFont = (Font)UIManager.get("InternalFrame.titleFont");
		            frameTitleFont = frameTitleFont.deriveFont(Font.PLAIN);
		            UIManager.put("InternalFrame.titleFont", frameTitleFont);
		        }
		        catch(Exception ee)
		        {
		            //TODO exception
		        }
				
				
			} 
			else if (e.getActionCommand().equals("222")) {
				jLabel.setBounds(0, 0, x, y);
			    imgURL = this.getClass().getResource("/com/sdut_grade/images/main2.jpg");
				jLabel.setIcon(new ImageIcon(imgURL));
				 BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
		          try {
					BeautyEyeLNFHelper.launchBeautyEyeLNF();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		          BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
		            UIManager.put("ToolBar.isPaintPlainBackground", Boolean.FALSE);
		         //   UIManager.put("RootPane.setupButtonVisible", false);      
		            UIManager.put("TabbedPane.tabAreaInsets", new InsetsUIResource(0,0,0,0));
		            UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(0,0,2,0));
		            UIManager.put("TabbedPane.tabInsets", new InsetsUIResource(3,10,9,10));
		            Font frameTitleFont = (Font)UIManager.get("InternalFrame.titleFont");
		            frameTitleFont = frameTitleFont.deriveFont(Font.PLAIN);
		            UIManager.put("InternalFrame.titleFont", frameTitleFont);
				
			} 
			else if (e.getActionCommand().equals("333")) {
				jLabel.setBounds(0, 0, x, y);
			    imgURL = this.getClass().getResource("/com/sdut_grade/images/main3.jpg");
				jLabel.setIcon(new ImageIcon(imgURL));
				try
		        {
		            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
		            BeautyEyeLNFHelper.launchBeautyEyeLNF();
		            BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
		            UIManager.put("ToolBar.isPaintPlainBackground", Boolean.FALSE);
		         //   UIManager.put("RootPane.setupButtonVisible", false);      
		            UIManager.put("TabbedPane.tabAreaInsets", new InsetsUIResource(0,0,0,0));
		            UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(0,0,2,0));
		            UIManager.put("TabbedPane.tabInsets", new InsetsUIResource(3,10,9,10));
		            Font frameTitleFont = (Font)UIManager.get("InternalFrame.titleFont");
		            frameTitleFont = frameTitleFont.deriveFont(Font.PLAIN);
		            UIManager.put("InternalFrame.titleFont", frameTitleFont);
		        }
		        catch(Exception ee)
		        {
		            //TODO exception
		        }
				
			} 
			else if (e.getActionCommand().equals("444")) {
				jLabel.setBounds(0, 0, x, y);
			    imgURL = this.getClass().getResource("/com/sdut_grade/images/main4.jpg");
				jLabel.setIcon(new ImageIcon(imgURL));
				 BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
		          try {
					BeautyEyeLNFHelper.launchBeautyEyeLNF();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		          BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
		            UIManager.put("ToolBar.isPaintPlainBackground", Boolean.FALSE);
		         //   UIManager.put("RootPane.setupButtonVisible", false);      
		            UIManager.put("TabbedPane.tabAreaInsets", new InsetsUIResource(0,0,0,0));
		            UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(0,0,2,0));
		            UIManager.put("TabbedPane.tabInsets", new InsetsUIResource(3,10,9,10));
		            Font frameTitleFont = (Font)UIManager.get("InternalFrame.titleFont");
		            frameTitleFont = frameTitleFont.deriveFont(Font.PLAIN);
		            UIManager.put("InternalFrame.titleFont", frameTitleFont);
				
			} 
			else if (e.getSource() == jMenuItem_hang_system) {
				DBConnection.update(Login.storeUserName + "   ִ�йһ�������");
				JOptionPane.showMessageDialog(null, "��δ���Ǽ���һ����ܣ�");
			} else if (e.getSource() == jMenuItem_initDB) {

		
				DBInit di = new DBInit();
				di.setVisible(true);
			} else if (e.getSource() == jMenuItem_exit) {

				System.exit(0);
			} else if (e.getSource() == jMenuItem_student_manage) {
				StudentManage sm = new StudentManage();
				sm.setVisible(true);
			} else if (e.getSource() == jMenuItem_teacher_manage) {
		
				TeacherManage tm = new TeacherManage();
				tm.setVisible(true);
			}else if (e.getSource() == jMenuItem_grade_class) {
				//JOptionPane.showMessageDialog(null, "�����Ż���...�����ڴ���");
				GradeManage gm = new GradeManage();
				gm.setVisible(true);
			} else if(e.getSource() == jMenuItem_class){
				ClassManage cm= new ClassManage();
				cm.setVisible(true);
				//JOptionPane.showMessageDialog(null, "�����Ż���...�����ڴ���");
			}else if (e.getSource() == jMenuItem_course_manage) {
			
				CourseManage cm = new CourseManage();
				cm.setVisible(true);
			} else if (e.getSource() == jMenuItem_ccourse_add) {
			
				CCourseAdd cm = new CCourseAdd();
				cm.setVisible(true);
			} else if (e.getSource() == jMenuItem_ccourse) {
				CCourseManage cci = new CCourseManage();
				cci.setVisible(true);
			}else if (e.getSource() == jMenuItem_mark_add) {
				
				CCourseMarkAdd ccma = new CCourseMarkAdd();
				ccma.setVisible(true);
			}else if (e.getSource() == jMenuItem_mark_statistics) {
				CCourseMarkStatistic ccs = new CCourseMarkStatistic();
				ccs.setVisible(true);
			} 
			else if (e.getSource() == jMenuItem_bb) {
				CCourse_bb ds = new CCourse_bb();
				ds.setVisible(true);
			} 
			
			else if (e.getSource() == jMenuItem_jdk_bf) {
				try {
					Main sjk = new Main(1);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//ffff.setVisible(true);
				
			} 
             else if (e.getSource() == jMenuItem_jdk_hf) {
				
            	 try {
					Main ffff = new Main(2);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
			else if (e.getSource() == jMenuItem_about) {
				About ab = new About();
				ab.setVisible(true);
			} 
			else if(e.getSource() == jMenuItem_else)
			{
				
				About_syjc abs= new About_syjc(); 
				abs.setVisible(true);
			}
//			else if (e.getSource() == jMenuItem_else) {
//				About ab = new About();
//				ab.setVisible(true);
//			} 
			else if (e.getSource() == jMenuItem_sys_info) {
				try {
					String fp = System.getProperty("user.dir") + "\\readme.txt";
					File f = new File(fp);
					if (f.exists()) {
						Runtime.getRuntime().exec(" notepad.exe " + fp);
					} else {
						JOptionPane.showMessageDialog(null, "sdut�������---" + "\n"
								+ "http://jwch.sdut.edu.cn/");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		}

	}
	
	
	public static void main(String[] args) {
		try
        {
	
			
			 UIManager.put("RootPane.setupButtonVisible", false);
	         org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	            
			//��һ��������ɫͼ�������һ��
			// BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencyAppleLike;
           
	        //ʵ�ĵļ��Ϻ�����һ�ѡ�
            BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
            UIManager.put("ToolBar.isPaintPlainBackground", Boolean.FALSE);
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
         //   UIManager.put("RootPane.setupButtonVisible", false);      
            UIManager.put("TabbedPane.tabAreaInsets", new InsetsUIResource(0,0,0,0));
            UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(0,0,2,0));
            UIManager.put("TabbedPane.tabInsets", new InsetsUIResource(3,10,9,10));
            Font frameTitleFont = (Font)UIManager.get("InternalFrame.titleFont");
            frameTitleFont = frameTitleFont.deriveFont(Font.PLAIN);
            UIManager.put("InternalFrame.titleFont", frameTitleFont);
        }
        catch(Exception e)
        {
            //TODO exception
        }
		MainFrame login = new MainFrame();
		login.setVisible(true);
	}

}
