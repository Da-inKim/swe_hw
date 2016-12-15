import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

//import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PhoneView {
	
	private String user_id;
	private JFrame frame;
	private JTable table;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://localhost:3306/SoftwareEnginnerHw";
	String user = "root";
	String pass = "4175^^";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhoneView window = new PhoneView("master");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PhoneView(String id) {
		user_id=id;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() {
	
		frame = new JFrame("전화번호부");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("전화번호목록");
		lblNewLabel.setBounds(177, 12, 105, 18);
		frame.getContentPane().add(lblNewLabel);
		
		table = new JTable();
		table.setBounds(425, 210, -416, -177);
		frame.getContentPane().add(table);
		
		try{
			con = DriverManager.getConnection(url,user,pass);
			String sql = "select * from Phonebook P, User_account U where P."+user_id+"= U."+user_id;
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			//table.setModel(DbUtils.resultSetToTableModel(rs));
		}catch(SQLException e){
			e.printStackTrace();
		}
		JButton addButton = new JButton("추가");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_Phone addphone = new Add_Phone();
			}
		});
		addButton.setBounds(104, 216, 105, 27);
		frame.getContentPane().add(addButton);
		
		JButton deleteButton = new JButton("삭제");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete_Phone deletephone = new Delete_Phone();
			}
		});
		deleteButton.setBounds(223, 216, 105, 27);
		frame.getContentPane().add(deleteButton);
	}
}
