package com.sdut_grade.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
/**
 * @数据链接模块
 * @author 徐红博 李威 
 * @version 1.0
 * @data 07/01/2018
 * @last update 07/11/2018
 * @Email 910374559@qq.com
 */
public class DBConnection {
	private static Connection conn ;
	private static Statement stmt ;
    private static final String drivername="org.gjt.mm.mysql.Driver";
    private static final String url="jdbc:mysql://localhost:3306/coursechoose?characterEncoding=utf8";
    private static final String username="root";
    private static final String password="root";
    
    public DBConnection(){
    	
    }
    
    static {

			try {
				Class.forName(drivername);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, "加载数据库驱动出错，请查明原因！");
			}
			try {
				conn = DriverManager.getConnection(url,username,password);
				stmt = conn.createStatement();
			
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "数据库驱动出错，用户名或密码错误！");
			}

    }    	
    /**
     * @创建rs游标；调用此方法后请养成良好的习惯调用rs.close()方法释放所获得的rs
     * @param sql
     * @return 每次调用此方法可以返回不同的rs记录集，避免不能嵌套查询的问题
     */
	public static ResultSet query(String sql) {
		    ResultSet rs = null;
			try {
			rs = conn.createStatement().executeQuery(sql);
			} catch (SQLException e) {
			}
		return rs;
	}
	/**
	 * 数据库执行、更新、删除模块
	 */
	public static boolean update(String sql){
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			return false;
			
		}
		return true;
	}
	/**
	 * @关闭stmt和conn
	 */
	public static void close() {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException ac) {
			ac.printStackTrace();
		}
	}
	/**
	 * @ 根据 表名获取数据库记录集
	 */
	public static int getTotalRow(String tableName){//获取记录集总数
		ResultSet rsline = query("select count(*) as counts from "+tableName);
		
		int counts=0;
		try{
		if(rsline.next())
		{
			counts = Integer.parseInt(rsline.getString("counts"));
		}
		}catch(Exception er){
		}
		return counts;
	}
}


