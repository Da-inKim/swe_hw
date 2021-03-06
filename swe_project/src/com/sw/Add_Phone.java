package com.sw;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Add_Phone {

   JFrame frame;
   private JTextField textField;
   private JTextField textField_1;
   private String addname;
   private String phonenumber;
   Connection con = null;
   PreparedStatement ps = null;
   ResultSet rs = null;
   String url = "jdbc:mysql://127.0.0.1:3306/sehw2";
   String user = "root";
   String pass = "01047670231";

   String present_id;
   String present_pw;

   public Add_Phone(String id, String pw) throws ClassNotFoundException {
      Class.forName("com.mysql.jdbc.Driver");
      present_id = id;
      present_pw = pw;
      initialize();
   }

   private void initialize() {
      try {
         con = DriverManager.getConnection(url,user,pass);
      } catch (SQLException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }
      frame = new JFrame("전화번호 추가하기");
      frame.setBounds(100, 100, 500, 500);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      
      JLabel lblNewLabel = new JLabel("이름");
      lblNewLabel.setBounds(86, 62, 62, 18);
      frame.getContentPane().add(lblNewLabel);
      
      textField = new JTextField();
      textField.setBounds(197, 59, 116, 24);
      frame.getContentPane().add(textField);
      textField.setColumns(10);
   
      
      JLabel lblNewLabel_1 = new JLabel("전화번호");
      lblNewLabel_1.setBounds(86, 109, 62, 18);
      frame.getContentPane().add(lblNewLabel_1);
      
      textField_1 = new JTextField();
      textField_1.setBounds(197, 106, 116, 24);
      frame.getContentPane().add(textField_1);
      textField_1.setColumns(10);
      
      JButton btnNewButton = new JButton("추가하기");
      btnNewButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try{
               addname = textField.getText();
               phonenumber = textField_1.getText();
               boolean checkVar = checkLength(addname, phonenumber);
               if(checkVar){ 
                  String sql1 = "insert into Phonebook(stored_id,name,phone_number) values(?,?,?)";
                  ps = con.prepareStatement(sql1);
                  ps.setString(1,"swuser");
                  ps.setString(2,addname);
                  ps.setString(3,phonenumber);
                  ps.executeUpdate();
                  JOptionPane.showMessageDialog(null, "연락처가 추가되었습니다.");      
               }
               else{
                  JOptionPane.showMessageDialog(null, "다시 입력해주세요.","error",JOptionPane.ERROR_MESSAGE);
               }
               PhoneView phoneView;
               try {
                  phoneView = new PhoneView(present_id,present_pw);
                  frame.dispose();
                  phoneView.frame.setVisible(true);
               } catch (ClassNotFoundException e1) {
                  // TODO Auto-generated catch block
                  e1.printStackTrace();
               }
               
            }catch(SQLException e1){
               e1.printStackTrace();
            }
            
         }
      });
      btnNewButton.setBounds(85, 156, 105, 27);
      frame.getContentPane().add(btnNewButton);
      
      JButton btnNewButton_1 = new JButton("취소");
      btnNewButton_1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            PhoneView returnlist;
            try {
               returnlist = new PhoneView(present_id, present_pw);
               frame.dispose();
               returnlist.frame.setVisible(true);      
            } catch (ClassNotFoundException e1) {
               // TODO Auto-generated catch block
               e1.printStackTrace();
            }
         }
      });
      btnNewButton_1.setBounds(225, 156, 105, 27);
      frame.getContentPane().add(btnNewButton_1);
      
   }
   class MyWinListener extends WindowAdapter {
      
      public void windowClosing(WindowEvent e) {
         frame.dispose();
      }
   }
   public boolean checkLength(String addname, String phonenumber) {
		if(addname.length() > 1 && addname.length() < 8)
			return true;
		else
			return false;
	}

   public void setVisible(boolean b) {
      // TODO Auto-generated method stub
      
   }
}