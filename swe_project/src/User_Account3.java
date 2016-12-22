import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class User_Account3 extends JFrame {
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String id, pw, new_id, new_pw;	
	String db_id,db_pw;
	JPanel p;
	JButton loginButton;
	JLabel labelID, labelPW;
	JTextField textID;
	JTextField textPW;
	String url = "jdbc:mysql://127.0.0.1:3306/sehw2";
	String user = "root";
	String pass = "01047670231";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
		
				try {
					Class.forName("com.mysql.jdbc.Driver");
					User_Account3 account = new User_Account3();			
					account.setTitle("HOME");
					account.setSize(500,200);
					account.setVisible(true);
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				}
				
			}
		});
		
	}
	
	User_Account3() {
		p = new JPanel();
		add(p);		
		labelID = new JLabel(" ID ");
		labelID.setBounds(100, 80, 20, 10);
		textID = new JTextField(15);
		textID.setBounds(130,80,100,10);
		labelPW = new JLabel(" PW ");
		labelPW.setBounds(100, 100, 20, 10);
		textPW = new JTextField(15);
		textPW.setBounds(130, 100, 100, 10);
		p.add(labelID);
		p.add(textID);
		p.add(labelPW);
		p.add(textPW);
		loginButton = new JButton("로그인");
		loginButton.setBounds(200, 150, 50, 50);
		p.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { 
					id = textID.getText();
					pw = textPW.getText();
					conn = DriverManager.getConnection(url,user,pass);
					String sql = "SELECT stored_id, stored_pw FROM account";
					psmt = conn.prepareStatement(sql);
					rs = psmt.executeQuery();
					while ( rs.next() ) {
						db_id = rs.getString("stored_id");
						db_pw = rs.getString("stored_pw");						
					} 
					isLogin(id,pw);
				} catch (SQLException e1) {
					System.out.println(e1);
				}
			}
		});

	}
	
	public void isLogin(String id, String pw) {				
		
		if ( id.equals(db_id) && pw.equals(db_pw) ) {	
			dispose();
			LoginView present_id = new LoginView(id,pw);
			present_id.setVisible(true);
		}
		else {
			JOptionPane.showMessageDialog(this, "다시 입력해주세요.","error",JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	public void isLogout(String id, String pw) {
		dispose();
	}

}
	