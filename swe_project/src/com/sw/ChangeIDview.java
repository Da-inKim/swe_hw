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



public class ChangeIDview extends JDialog implements ActionListener {
		JPanel p1,p2,p3;
		JLabel labelID1,labelID2;
		JTextField textID = new JTextField(15);//stored_id
		JTextField textID1 = new JTextField(15);
		JButton okButton, cancelButton;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		String url = "jdbc:mysql://127.0.0.1:3306/sehw2";
		String user = "root";
		String pass = "01047670231";
		
		String present_id;
		String present_pw;
		
		public ChangeIDview(String id,String pw){
			present_id = id;
			present_pw = pw;
			p1 = new JPanel();
			add(p1,"Center");
			p3 = new JPanel();
			p1.add(p3,"North");
			textID.setText(present_id);
			textID.setEditable(false);
			p3.add(textID);
			labelID1 = new JLabel(" 변경할 ID를 입력 ");
			p1.add(labelID1);
			p1.add(textID1);
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
			
			if (e.getSource() == okButton ) {			
				String id = textID.getText();
				String new_id = textID1.getText();	
				String change_id = ChangeID(new_id,present_id);
				textID.setText(change_id);	
				textID1.setText(null);	
				dispose();
				LoginView returnLoginView = new LoginView(change_id,present_pw);
				returnLoginView.setVisible(true);
			}
			
			if (e.getSource()==cancelButton) {
				
				String return_id=textID.getText();
				textID1.setText(null);	
				LoginView returnLoginView = new LoginView(return_id,present_pw);
				returnLoginView.setVisible(true);
			}
			
		}
		class MyWinListener extends WindowAdapter {
			
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		}
		
		public String ChangeID(String new_id, String id) {
			
			if ( (id != new_id) && (new_id.length() <= 15)) {
				JOptionPane.showMessageDialog(this, "아이디가 변경되었습니다.");				
				try { 			
					conn = DriverManager.getConnection(url,user,pass);
					psmt = conn.prepareStatement("UPDATE account SET stored_id='"+ new_id +"'WHERE stored_id='"+id+"'");		
					psmt.executeUpdate();
				} catch (SQLException e1) {
					System.out.println(e1);
				}
				
				return new_id;
			}
			else {
				JOptionPane.showMessageDialog(this, "다시 입력해주세요.","error",JOptionPane.ERROR_MESSAGE);
							
				return id;
			}		
			
		}
}
