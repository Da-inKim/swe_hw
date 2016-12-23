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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Delete_Phone {

	JFrame frame;
	JTextField textField;
	String deletePhoneKey;
	Connection con = null;
	PreparedStatement ps = null; 
	ResultSet rs = null;
	
	String url = "jdbc:mysql://localhost:3306/sehw2";
	String user = "root";
	String pass = "01047670231";
	
	String present_id;
	String present_pw;

	public Delete_Phone(String id, String pw) throws ClassNotFoundException {
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
		frame = new JFrame("삭제하기");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PhoneKey");
		lblNewLabel.setBounds(102, 71, 62, 18);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(201, 68, 116, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
	
		
		JButton btnNewButton = new JButton("삭제");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{          
					deletePhoneKey = textField.getText();
					String sql1="DELETE FROM Phonebook WHERE phone_key='"+deletePhoneKey+"'";
					ps = con.prepareStatement(sql1); 
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "연락처가 삭제되었습니다.");							
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
		btnNewButton.setBounds(78, 129, 105, 27);
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
		btnNewButton_1.setBounds(217, 129, 105, 27);
		frame.getContentPane().add(btnNewButton_1);
	}
	
	class MyWinListener extends WindowAdapter {
		
		public void windowClosing(WindowEvent e) {
			frame.dispose();
		}
	}


}
