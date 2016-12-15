import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Add_Phone {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private String addname;
	private String phonenumber;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://127.0.0.1:3306/SoftwareEnginnerHw";
	String user = "root";
	String pass = "4175^^";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Phone window = new Add_Phone();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Add_Phone() {
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("이름");
		lblNewLabel.setBounds(86, 62, 62, 18);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(197, 59, 116, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		addname = textField.getText();
		
		JLabel lblNewLabel_1 = new JLabel("전화번호");
		lblNewLabel_1.setBounds(86, 109, 62, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(197, 106, 116, 24);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		phonenumber = textField_1.getText();
		
		JButton btnNewButton = new JButton("추가하기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql1 = "insert into Phonebook values(?,?)";
					ps = con.prepareStatement(sql1);
					ps.setString(3,addname);
					ps.setString(4,phonenumber);
					int n = ps.executeUpdate();
					if(n>0){
						System.out.println("추가 성공");
					}else{
						System.out.println("추가 실패");
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
				PhoneView returnlist = new PhoneView("master");
				
				
			}
		});
		btnNewButton_1.setBounds(225, 156, 105, 27);
		frame.getContentPane().add(btnNewButton_1);
		
	}
}
