package com.sdut_grade.util;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


public class Main
{
	private static final Component JFrame = null;
	private static int returnVal;
	/**
	 * Java代码实现MySQL数据库导出
	 * 
	 * @author 徐红博 李威
	 * @param hostIP MySQL数据库所在服务器地址IP
	 * @param userName 进入数据库所需要的用户名
	 * @param password 进入数据库所需要的密码
	 * @param savePath 数据库导出文件保存路径
	 * @param fileName 数据库导出文件文件名
	 * @param databaseName 要导出的数据库名
	 * @return 返回true表示导出成功，否则返回false。
	 * @throws IOException 
	 */
	public static boolean out_mysql(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) throws InterruptedException, IOException {

		
			Runtime rt = Runtime.getRuntime();
			  String path = "c:\\mysqlq";
			  StringBuffer sb = new StringBuffer();
			    sb.append("cmd /c ");
			    sb.append(path);
			    sb.append("\\mysqldump -hlocalhost  -uroot ");
			    sb.append(" -proot ");
			    sb.append("--opt test > ");
			    sb.append(savePath);
			    sb.append("\\save.sql");
			    String javaExecute = sb.toString();// 在CMD中执行的字符串
			    System.out.println(javaExecute);
		        rt.exec(javaExecute);
			return true;
	
	}
	public static boolean to_mysql(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) throws InterruptedException, IOException {

		
		Runtime rt = Runtime.getRuntime();
		  String path = "c:\\mysqlq";
		  StringBuffer sb = new StringBuffer();
		    sb.append("cmd /c ");
		    sb.append(path);
		    sb.append("\\mysqldump -hlocalhost  -uroot ");
		    sb.append(" -proot ");
		    sb.append("--opt test < ");
		    sb.append(savePath);
		    String javaExecute = sb.toString();// 在CMD中执行的字符串
		    System.out.println(javaExecute);
	        rt.exec(javaExecute);
		return true;

}
	
	public static void save_to_mysql() throws IOException
	{
		 JFileChooser fileChooser = new JFileChooser("c://"); 
		 fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); 
		 returnVal = fileChooser.showOpenDialog(fileChooser); 
	     String filePath= fileChooser.getSelectedFile().getAbsolutePath();//这个就是你选择的文件夹的
		 System.out.println(filePath);
		try {
			if (out_mysql("localhost", "root", "root", filePath.toString(), "save.sql", "test")) {
				JOptionPane.showMessageDialog(null,"数据库备份成功","确认对话框",
                        JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(JFrame,"数据库备份成功","警告对话框",
                        JOptionPane.WARNING_MESSAGE);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void save_from_mysql() throws IOException
	{
		 JFileChooser fileChooser = new JFileChooser(); 
		 fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY); 
		 returnVal = fileChooser.showOpenDialog(fileChooser); 
		    File filePath= fileChooser.getSelectedFile();
		    String hh = filePath.toString();
		 if (hh.endsWith("sql"))
		 {
			 System.out.println(filePath);
			 try {
					if (to_mysql("localhost", "root", "root", filePath.toString(), "save.sql", "test")) {
						JOptionPane.showMessageDialog(JFrame,"数据库恢复成功","确认对话框",
                                JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(JFrame,"数据库恢复失败","警告对话框",
                                JOptionPane.WARNING_MESSAGE);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		 }
		 else 
		 {
			 JOptionPane.showMessageDialog(JFrame,"您选择了错误文件","警告对话框",
                     JOptionPane.WARNING_MESSAGE);
		 }
	}

	public Main(int flag) throws IOException
	{
		if(flag==1)
		{
			save_to_mysql() ;
		}
		else if(flag == 2)
		{
			 save_from_mysql();
		}
       
	}

}