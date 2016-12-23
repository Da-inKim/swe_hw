import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Delete_Schedule {
	
	JFrame frame;
	private JTextField textField;
	private String deleteschedulenum;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	String url = "jdbc:mysql://127.0.0.1:3306/sehw2";
	String user = "root";
	String pass = "01047670231";
	
	String present_id;
	String present_pw;

	public Delete_Schedule(String id, String pw) throws ClassNotFoundException {
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ScheduleKey");
		lblNewLabel.setBounds(82, 71, 100, 18);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(201, 68, 116, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("삭제");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{          
					deleteschedulenum = textField.getText();
					String sql = "DELETE FROM Schedule WHERE Schedule_key='"+deleteschedulenum+"'";        
					ps = con.prepareStatement(sql); 
					ps.executeUpdate();
					JOptionPane.showMessageDialog(null, "스케쥴이 삭제되었습니다.");							
					ScheduleView scheduleView;
					try {
						scheduleView = new ScheduleView(present_id,present_pw);
						frame.dispose();
						scheduleView.frame.setVisible(true);
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
				ScheduleView scheduleView_1;
				try {
					scheduleView_1 = new ScheduleView(present_id,present_pw);
					frame.dispose();
					scheduleView_1.frame.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(217, 129, 105, 27);
		frame.getContentPane().add(btnNewButton_1);
	}

}
