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
		jTable.getSelectionModel().addListSelectionListener(new tableListener());// jTableѡ�м�����
	}

	private void initialize() {
		jLabel4 = new JLabel();
		jLabel4.setBounds(new Rectangle(359, 69, 92, 24));
		jLabel4.setText("�������ݣ�");
		jLabel3 = new JLabel();
		jLabel3.setBounds(new Rectangle(359, 38, 125, 24));
		
		jLabel3.setText("��ѡ��");
		jLabel2 = new JLabel();
		jLabel2.setBounds(new Rectangle(359, 7, 71, 24));
		jLabel2.setText("����Ա��");
		this.setSize(new Dimension(506, 293));
		this.setTitle("��־����");
		this.setModal(true);
		this.setLocation(new Point(0, 0));
		this.setLocationRelativeTo(null);


			jLabel_row = new JLabel();
			jLabel_row.setBounds(new Rectangle(100, 40, 66, 21));
			jLabel_row.setText("��ѡ��");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(9, 40, 79, 20));
			jLabel1.setText("��־��¼����");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(7, 8, 347, 20));
			jLabel.setText("Ϊ�˱�֤��־�������ԣ���ϵͳ���ṩ������־��ɾ���ܣ�");

			
			jButton_empty = new JButton();
			jButton_empty.setText("�����־");
			jButton_empty.setBounds(new Rectangle(11, 230, 344, 22));
			
			jTextArea = new JTextArea();
			jTextArea.setBounds(new Rectangle(360, 100, 131, 153));
			jTextArea.setBackground(SystemColor.control);
			jTextArea.setTabSize(6);
			jTextArea.setText("��ʹ�����������̡������鿴����б�����飡");
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
	
	// ��ʾ����
	public void showTableData() {
		
		LogDao ld = new LogDao();
		List<LogModel> lists = ld.getLists();	
		String heads[] = { "��־ID", "�����û�ID", "����", "����ʱ��" };
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
		
		//jLabel_row.setText("��¼��:" + counts + "");
		
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
				Object[] options = { "ȷ��", "ȡ��" };
				
				if(JOptionPane.showOptionDialog(null, "���棬��Ҫ��־ɾ������ȷ��Ҫɾ����", "ɾ������", 
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
