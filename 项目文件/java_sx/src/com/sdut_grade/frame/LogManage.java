package com.sdut_grade.frame;

import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.sdut_grade.dao.LogDao;
import com.sdut_grade.frame.CCourseManage.btnListener;
import com.sdut_grade.frame.CCourseManage.tableListener;
import com.sdut_grade.model.LogModel;
import com.sdut_grade.util.DBConnection;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogManage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel_row = null;
	private JButton jButton_empty = null;
	private JScrollPane jScrollPane = null;
	private JTable jTable = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JTextArea jTextArea = null;
	DefaultTableModel model = new DefaultTableModel(); 
	public LogManage(){
		super();
		initialize(); 
		showTableData();
		this.setModal(true);
		jButton_empty.addActionListener(new btnListener());
		jTable.getSelectionModel().addListSelectionListener(new tableListener());// jTable选中监听！
	}

	private void initialize() {
		jLabel4 = new JLabel();
		jLabel4.setBounds(new Rectangle(359, 69, 92, 24));
		jLabel4.setText("操作内容：");
		jLabel3 = new JLabel();
		jLabel3.setBounds(new Rectangle(359, 38, 125, 24));
		
		jLabel3.setText("请选择");
		jLabel2 = new JLabel();
		jLabel2.setBounds(new Rectangle(359, 7, 71, 24));
		jLabel2.setText("操作员：");
		this.setSize(new Dimension(506, 293));
		this.setTitle("日志管理");
		this.setModal(true);
		this.setLocation(new Point(0, 0));
		this.setLocationRelativeTo(null);


			jLabel_row = new JLabel();
			jLabel_row.setBounds(new Rectangle(100, 40, 66, 21));
			jLabel_row.setText("请选择");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(9, 40, 79, 20));
			jLabel1.setText("日志记录数：");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(7, 8, 347, 20));
			jLabel.setText("为了保证日志的完整性，本系统不提供单挑日志的删功能！");

			
			jButton_empty = new JButton();
			jButton_empty.setText("清空日志");
			jButton_empty.setBounds(new Rectangle(11, 230, 344, 22));
			
			jTextArea = new JTextArea();
			jTextArea.setBounds(new Rectangle(360, 100, 131, 153));
			jTextArea.setBackground(SystemColor.control);
			jTextArea.setTabSize(6);
			jTextArea.setText("请使用鼠标点击或键盘↑↓键查看左边列表的详情！");
			jTextArea.setEditable(false);
			jTextArea.setAutoscrolls(true);
			jTextArea.setLineWrap(true);
			
			jTable = new JTable();
			
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(7, 66, 347, 154));
			jScrollPane.setViewportView(jTable);

			
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel_row, null);
			jContentPane.add(jButton_empty, null);
			jContentPane.add(jScrollPane, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jTextArea, null);
			
			
			this.setContentPane(jContentPane);
			
			
	}
	
	// 显示数据
	public void showTableData() {
		
		LogDao ld = new LogDao();
		List<LogModel> lists = ld.getLists();	
		String heads[] = { "日志ID", "操作用户ID", "操作", "操作时间" };
		model = new DefaultTableModel(null, heads);	
		model.setRowCount(lists.size());
		try {
			for(int i=0;i<lists.size();i++) {
				model.setValueAt(lists.get(i).getLog_id(), i, 0);
				model.setValueAt(lists.get(i).getLogin_user(), i, 1);
				model.setValueAt(lists.get(i).getOperate(), i, 2);
				model.setValueAt(lists.get(i).getLog_time(), i, 3);
			}
		} catch (Exception e) {
		}
		
		//jLabel_row.setText("记录数:" + counts + "");
		
		jTable.setModel(model);
		jTable.getSelectionModel().setSelectionInterval(0, 0);
		//DBConnection.close();
	}


	
	
	public class tableListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (jTable.getSelectedRow() != -1) {
				int rows = jTable.getSelectedRow();
				jLabel3.setText(jTable.getValueAt(rows, 1)
						.toString());
				jTextArea.setText(jTable.getValueAt(rows, 2)
						.toString());			}
		}
	}



	
	
	public class btnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==jButton_empty){
				Object[] options = { "确认", "取消" };
				
				if(JOptionPane.showOptionDialog(null, "警告，重要日志删除，你确定要删除吗？", "删除警告", 
				JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
				null, options, options[0])==JOptionPane.OK_OPTION)
				{
				String sql = "truncate table c_log";
				DBConnection.update(sql);
				}else return;
			    }
			}
		}





} 
