package com.sw;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ChangePWview extends JDialog implements ActionListener {	
		JPanel p1,p2,p3;
		JLabel labelPW1,labelPW2;
		JTextField textPW = new JTextField(15);
		JTextField textPW1 = new JTextField(15);	
		JButton okButton, cancelButton;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:mysql://127.0.0.1:3306/sehw2";
		String user = "root";
		String pass = "01047670231";
		
		String present_id;
		String present_pw;
		
		public ChangePWview(String id, String pw){
			present_id = id;
			present_pw = pw;
			p1 = new JPanel();
			add(p1,"Center");
			p3 = new JPanel();
			p1.add(p3,"North");
			textPW.setText(present_pw);
			textPW.setEditable(false);
			p3.add(textPW);		
			labelPW1 = new JLabel(" 변경할 PW를 입력 ");
			p1.add(labelPW1);
			p1.add(textPW1);
			p2 = new JPanel();
			p1.add(p2,"South");
			okButton = new JButton("ok");
			okButton.addActionListener(this);
			p2.add(okButton);
			cancelButton = new JButton("cancel");
			cancelButton.addActionListener(this);
			p2.add(cancelButton);		
			setSize(500,200);
			addWindowListener(new MyWinListener());
		}
		

		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource()==okButton ) {
				String pw = textPW.getText();	
				String new_pw = textPW1.getText();	
				String change_pw = ChangePW(new_pw,present_pw);
				textPW.setText(change_pw);
				textPW1.setText(null);	
				dispose();
				LoginView returnLoginView = new LoginView(present_id,change_pw);
				returnLoginView.setVisible(true);
			}
			
			if (e.getSource()==cancelButton) {
				String return_pw = textPW.getText();
				textPW1.setText(null);			
				LoginView returnLoginView = new LoginView(present_id,return_pw);
				dispose();
				returnLoginView.setVisible(true);
			}
		}
		class MyWinListener extends WindowAdapter {
			
			public void windowClosing(WindowEvent e) {
				dispose();
			}
			
		}
		
		
		public String ChangePW(String new_pw, String pw) {		
			
			if ( (pw != new_pw) && (new_pw.length() <= 15)  ) {
				JOptionPane.showMessageDialog(this, "비밀번호가 변경되었습니다.");			
				try {							
					conn = DriverManager.getConnection(url,user,pass);
					psmt = conn.prepareStatement("UPDATE account SET stored_pw='"+new_pw+"'WHERE stored_pw='"+pw+"'");
					psmt.executeUpdate();
				} catch (SQLException e1) {
					System.out.println(e1);
				}
				
				return new_pw;
			}
			else {
				JOptionPane.showMessageDialog(this, "다시 입력해주세요.","error",JOptionPane.ERROR_MESSAGE);
			
				return pw;
			}		
			
			
	}

	
}