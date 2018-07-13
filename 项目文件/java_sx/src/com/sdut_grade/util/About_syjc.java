package com.sdut_grade.util;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

public class About_syjc extends JFrame
{
	
	
	private JPanel jContentPane = null;
	private JButton jButton_ok = null;
	private JLabel jLabel_text1 = null;
	private JLabel jLabel_text2 = null;
//	private JLabel jLabel_text3 = null;
//	private JLabel jLabel_text4 = null;
//	private JLabel jLabel_text5 = null;
//	private JLabel jLabel_text6 = null;
//	private JLabel jLabel_text7 = null;
//	private JLabel jLabel_text8 = null;
    JTextArea wby; 
    JScrollPane gdt;
	
	
	public About_syjc()
	{
		ccourse_cxmd();
	}
	
	public void ccourse_cxmd()
	{
		
		jLabel_text2 = new JLabel();
		jLabel_text2.setBounds(new Rectangle(12, 50, 371, 23));
		//jLabel_text2.setText("1111111111111111111111111111111111111111\n6666666666");
		jLabel_text2.setFont(new Font("粗体",Font.BOLD,12));
		jLabel_text1 = new JLabel();
		jLabel_text1.setBounds(new Rectangle(240, 10, 373, 23));
		jLabel_text1.setText("姓名");
//		jLabel_text1.setFont(new Font("宋体",Font.BOLD,20));
//		jLabel_text2.setText("班级");
//		jLabel_text2.setFont(new Font("宋体",Font.BOLD,20));
//		jLabel_text3.setText("成绩");
//		jLabel_text3.setFont(new Font("宋体",Font.BOLD,20));
//		jLabel_text4.setText("");
//		jLabel_text4.setFont(new Font("宋体",Font.BOLD,20));
//		jLabel_text5.setText("使用教程");
//		jLabel_text5.setFont(new Font("宋体",Font.BOLD,20));
		
		
		jButton_ok = new JButton("确定");
		jButton_ok.setFont(new Font("粗体",Font.BOLD,12));
		jButton_ok.setBounds(new Rectangle(331, 45, 70, 26));
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.setBackground(new Color(255, 254, 254));
		jContentPane.add(jLabel_text1, null);
		//jContentPane.add(jButton_ok, null);
		jContentPane.add(jLabel_text2, null);
		
		//this.setContentPane(jContentPane);
		
		 String j="kkkkkkkkkkkkkkkk\r\n"+"jjjjjjjjj";
		 String mm="1.将项目导入到eclipse（myeclipse也可以）。\r\n"+
                    "2.导入之后做一下基本的jre配置。\r\n"+
                    "3.接下来是数据库的链接信息的配置：\r\n"+
                    "首先我们来导入数据库文件，数据库文件导入采用的是navicat工具。\r\n"+
                     "首先新建一个数据库coursechoose，然后双击打开新建的这个数据库，然后\r\n右键选择运行sql文件。导入完成后按f5刷新下就能看到导入的数据表。接下来可以运行系统了。\r\n"+
                     "4.接下来我们以管理员角色登录，管理员登录名是admin，密码是123\r\n"+
                     "5.登录后的主界面：\r\n"+
                       "可以看到有开始菜单、后台管理、选课情况、成绩管理、帮助五大主菜单。其中开始菜单有：\r\n重新登录、修改密码、用户管理、操作日志、初始化数据库等功能：\r\n"+
                      "6.后台管理里面有：\r\n学生信息管理、教师信息管理、课程信息管理、年级信息管理、班级信息管理等功能。\r\n"+
                      "7.选课情况下有选课录入、选课情况总览两个功能。\r\n"+
                       "8.成绩管理下有成绩录入、成绩统计两个功能。\r\n";
		wby=new JTextArea(mm);
		gdt=new JScrollPane(wby);
	
		this.add(gdt);

		this.setTitle("使用教程");
		this.setSize(570,370);
		this.setLocation(400,200);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		this.setVisible(true);		
	}	
}