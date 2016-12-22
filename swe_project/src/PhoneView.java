import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PhoneView {
	
	private String user_id;
	JFrame frame;
	JTable table;
	JScrollPane scrollpane;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					PhoneView window = new PhoneView("Lee11365");
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

	private void initialize() {
	
		frame = new JFrame("전화번호부");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("전화번호목록");
		lblNewLabel.setBounds(177, 12, 105, 18);
		frame.getContentPane().add(lblNewLabel);
		
		String header[] = {"이름","전화번호"};
		DefaultTableModel model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		scrollpane = new JScrollPane(table);
		frame.add(scrollpane);
		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sehw2","root","4175^^");
			String sql = "SELECT name,phone_number FROM Phonebook";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String[] row = new String[2];
				row[0] = rs.getString("name");
				row[1] = rs.getString("phone_number");
				model.addRow(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		JButton addButton = new JButton("추가");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_Phone addphone = new Add_Phone();
				addphone.frame.setVisible(true);
			}
		});
		addButton.setBounds(104, 216, 105, 27);
		frame.getContentPane().add(addButton);
		
		JButton deleteButton = new JButton("삭제");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete_Phone deletephone = new Delete_Phone();
				deletephone.frame.setVisible(true);
			}
		});
		deleteButton.setBounds(223, 216, 105, 27);
		frame.getContentPane().add(deleteButton);
	}

}
