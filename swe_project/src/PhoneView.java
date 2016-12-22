import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PhoneView {
	
	JFrame frame;
	JTable table;
	JScrollPane scrollpane;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://127.0.0.1:3306/sehw2";
	String user = "root";
	String pass = "01047670231";


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					PhoneView window = new PhoneView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PhoneView() {
		initialize();
	}

	private void initialize() {
	
		frame = new JFrame("전화번호부");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("전화번호목록");
		lblNewLabel.setBounds(200, 30, 105, 15);
		frame.getContentPane().add(lblNewLabel);
		
		/*JLabel tableLabel = new JLabel("이름       전화번호");
		lblNewLabel.setBounds(177,30, 105, 20);
		frame.getContentPane().add(tableLabel);*/
		
		String header[] = {"PhoneKey","이름","전화번호"};
		DefaultTableModel model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		table.setBounds(90,60,300,250);
		scrollpane = new JScrollPane(table);
		frame.add(scrollpane);
		frame.add(table);
		model.addRow(header);
		
		try{
			con = DriverManager.getConnection(url,user,pass);
			String sql = "SELECT phone_key,name,phone_number FROM Phonebook";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String[] row = new String[3];
				row[0] = rs.getString("phone_key");
				row[1] = rs.getString("name");
				row[2] = rs.getString("phone_number");
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
		addButton.setBounds(100, 350, 105, 27);
		frame.getContentPane().add(addButton);
		
		JButton deleteButton = new JButton("삭제");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete_Phone deletephone = new Delete_Phone();
				deletephone.frame.setVisible(true);
			}
		});
		deleteButton.setBounds(260, 350, 105, 27);
		frame.getContentPane().add(deleteButton);
	}
	

}
