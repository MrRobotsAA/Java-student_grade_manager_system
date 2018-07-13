package com.sdut_grade.util;
import javax.swing.JPanel;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;

public class DBInit extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JProgressBar jProgressBar = null;
	private JButton jButton_Start = null;
	/**
	 * @param owner
	 */
	public DBInit() {
		initialize();
		
	}
	private void initialize() {
		this.setModal(true);
		this.setSize(312, 125);
		this.setLocationRelativeTo(null);
		jProgressBar = new JProgressBar();
		jProgressBar.setBounds(new Rectangle(6, 14, 282, 26));
		jProgressBar.setMaximum(100);
		jProgressBar.setMinimum(0);
		jProgressBar.setValue(0);
		jProgressBar.setStringPainted(true);
		jButton_Start = new JButton();
		jButton_Start.setBounds(new Rectangle(6, 46, 282, 25));
		jButton_Start.setText(" 数据库初始化");
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.add(jProgressBar, null);
		jContentPane.add(jButton_Start, null);
		
		
		this.setContentPane(jContentPane);
		jButton_Start.addActionListener(new btListener());

	}
	public class btListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == jButton_Start)
			{
/*				File fnew = new File("src/com/sdut_grade/db/BK_CourseChoose.mdb");//重命名文件
				File fold = new File("src/com/sdut_grade/db/CourseChoose.mdb");
				if(!fnew.exists()){
					JOptionPane.showMessageDialog(null,"对不起，无法恢复数据库；找不到原备份文件！");
				}
				
				else{//如果存在原备份文件
					 fold.delete();//把当前使用的数据库删除
					 int bytesum = 0; 
					 int byteread = 0; 
					 try{				//把原备份文件复制多一份，同时改名为CourseChoose
					 FileInputStream inStream = new FileInputStream("src/com/sdut_grade/db/BK_CourseChoose.mdb"); //读入原文件 
					 FileOutputStream fs = new FileOutputStream(new File("src/com/sdut_grade/db/CourseChoose.mdb")); 
					 byte[] buffer = new byte[5120]; 		 
					 while ( (byteread = inStream.read(buffer)) != -1) { 
					 bytesum += byteread; //字节数 文件大小 
					 fs.write(buffer, 0, byteread); 
					 } }catch(Exception ee){
					 }*/
				
				String sql_c_user = "truncate table c_user";
				String sql_c_student = "truncate table c_student";
				String sql_c_teacher = "truncate table c_teacher";
				String sql_c_course = "truncate table c_course";
				String sql_c_ccourse = "truncate table c_ccourse";
				String sql_c_grade = "truncate table c_grade";
				String sql_c_class = "truncate table c_class";
				
				DBConnection.query(sql_c_user);
				DBConnection.query(sql_c_student);
				DBConnection.query(sql_c_teacher);
				DBConnection.query(sql_c_course);
				DBConnection.query(sql_c_ccourse);
				DBConnection.query(sql_c_class);
				DBConnection.query(sql_c_grade);
				//////////////////插入默认数据////////////////////////
				
				DBConnection.update("insert into c_user(user_name,user_password,user_privilege) values('admin','123','管理员')");
				DBConnection.update("insert into c_grade(grade_name) values('一年级')");
				DBConnection.update("insert into c_grade(grade_name) values('二年级')");
				DBConnection.update("insert into c_class(class_name,class_maxnum,grade_id) values('核弹技术07-1',50,1)");
				DBConnection.update("insert into c_class(class_name,class_maxnum,grade_id) values('核弹技术07-2',50,1)");

				
				new Thread(new Runnable(){//开启一个线程
					public void run(){	
						for(int counter = 0;counter<=100;counter++){
							
							try {
								Thread.sleep(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							jButton_Start.setEnabled(false);
							jButton_Start.setText("正在初始化数据...");
							jProgressBar.setValue(counter);
							
							if(counter == 100){
								jButton_Start.setText("数据初始化完成！");	
								JOptionPane.showMessageDialog(null,
										"谁是谁生命中的过客,谁是谁生命的转轮,\n前世的尘,今世的风,无穷无尽的哀伤的精魂.  ", "数据初始化成功",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(this.getClass().getResource("/com/sdut_grade/images/male.gif")));
							}
						}
					}				
				}).start();
				
				}
				}			
			}	
	}
	
	


