package com.sdut_grade.util;
import java.awt.event.*; 
import java.awt.*;
import javax.swing.*;
public class WindowMess extends JFrame implements ActionListener {
   JTextField inputNumber;
   JTextArea show;
   WindowMess() {
       inputNumber=new JTextField(22);
       inputNumber.addActionListener(this);
       show=new JTextArea(); 
       add(inputNumber,BorderLayout.NORTH);
       add(show,BorderLayout.CENTER); 
       setBounds(60,60,300,300);       
       setVisible(true);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   public void actionPerformed(ActionEvent e) { 
       boolean boo=false;
       if(e.getSource()==inputNumber) {
           String s=inputNumber.getText();
           for(int i=0;i<s.length();i++) {
               char c=s.charAt(i);
               if(!(Character.isDigit(c)))
                  boo=true;         
           } 
           if(boo==true) { //弹出“警告”消息对话框。
               JOptionPane.showMessageDialog(this,"您输入了非法字符","警告对话框",
                                             JOptionPane.WARNING_MESSAGE);
               inputNumber.setText(null); 
           }  
           else if(boo==false) {
               int number=Integer.parseInt(s);
               show.append("\n"+number+"平方:"+(number*number));
           }
       }
   }
}
