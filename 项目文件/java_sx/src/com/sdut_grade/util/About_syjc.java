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
		jLabel_text2.setFont(new Font("����",Font.BOLD,12));
		jLabel_text1 = new JLabel();
		jLabel_text1.setBounds(new Rectangle(240, 10, 373, 23));
		jLabel_text1.setText("����");
//		jLabel_text1.setFont(new Font("����",Font.BOLD,20));
//		jLabel_text2.setText("�༶");
//		jLabel_text2.setFont(new Font("����",Font.BOLD,20));
//		jLabel_text3.setText("�ɼ�");
//		jLabel_text3.setFont(new Font("����",Font.BOLD,20));
//		jLabel_text4.setText("");
//		jLabel_text4.setFont(new Font("����",Font.BOLD,20));
//		jLabel_text5.setText("ʹ�ý̳�");
//		jLabel_text5.setFont(new Font("����",Font.BOLD,20));
		
		
		jButton_ok = new JButton("ȷ��");
		jButton_ok.setFont(new Font("����",Font.BOLD,12));
		jButton_ok.setBounds(new Rectangle(331, 45, 70, 26));
		jContentPane = new JPanel();
		jContentPane.setLayout(null);
		jContentPane.setBackground(new Color(255, 254, 254));
		jContentPane.add(jLabel_text1, null);
		//jContentPane.add(jButton_ok, null);
		jContentPane.add(jLabel_text2, null);
		
		//this.setContentPane(jContentPane);
		
		 String j="kkkkkkkkkkkkkkkk\r\n"+"jjjjjjjjj";
		 String mm="1.����Ŀ���뵽eclipse��myeclipseҲ���ԣ���\r\n"+
                    "2.����֮����һ�»�����jre���á�\r\n"+
                    "3.�����������ݿ��������Ϣ�����ã�\r\n"+
                    "�����������������ݿ��ļ������ݿ��ļ�������õ���navicat���ߡ�\r\n"+
                     "�����½�һ�����ݿ�coursechoose��Ȼ��˫�����½���������ݿ⣬Ȼ��\r\n�Ҽ�ѡ������sql�ļ���������ɺ�f5ˢ���¾��ܿ�����������ݱ���������������ϵͳ�ˡ�\r\n"+
                     "4.�����������Թ���Ա��ɫ��¼������Ա��¼����admin��������123\r\n"+
                     "5.��¼��������棺\r\n"+
                       "���Կ����п�ʼ�˵�����̨����ѡ��������ɼ���������������˵������п�ʼ�˵��У�\r\n���µ�¼���޸����롢�û�����������־����ʼ�����ݿ�ȹ��ܣ�\r\n"+
                      "6.��̨���������У�\r\nѧ����Ϣ������ʦ��Ϣ�����γ���Ϣ�����꼶��Ϣ�����༶��Ϣ����ȹ��ܡ�\r\n"+
                      "7.ѡ���������ѡ��¼�롢ѡ����������������ܡ�\r\n"+
                       "8.�ɼ��������гɼ�¼�롢�ɼ�ͳ���������ܡ�\r\n";
		wby=new JTextArea(mm);
		gdt=new JScrollPane(wby);
	
		this.add(gdt);

		this.setTitle("ʹ�ý̳�");
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