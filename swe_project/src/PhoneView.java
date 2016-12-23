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
	
	String present_id;
	String present_pw;


	public PhoneView(String id, String pw) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		present_id = id;
		present_pw = pw;
		initialize();
	}

	private void initialize() {
	
		frame = new JFrame("��ȭ��ȣ��");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("��ȭ��ȣ���");
		lblNewLabel.setBounds(200, 30, 105, 15);
		frame.getContentPane().add(lblNewLabel);
		
		/*JLabel tableLabel = new JLabel("�̸�       ��ȭ��ȣ");
		lblNewLabel.setBounds(177,30, 105, 20);
		frame.getContentPane().add(tableLabel);*/
		
		String header[] = {"PhoneKey","�̸�","��ȭ��ȣ"};
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
		JButton addButton = new JButton("�߰�");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_Phone addphone;
				try {
					addphone = new Add_Phone(present_id,present_pw);
					addphone.frame.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addButton.setBounds(70, 350, 105, 27);
		frame.getContentPane().add(addButton);
		
		JButton deleteButton = new JButton("����");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete_Phone deletephone;
				try {
					deletephone = new Delete_Phone(present_id,present_pw);
					frame.dispose();
					deletephone.frame.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		deleteButton.setBounds(190, 350, 105, 27);
		frame.getContentPane().add(deleteButton);
		
		JButton returnButton = new JButton("��������");
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				LoginView returnlogin = new LoginView(present_id, present_pw);
				returnlogin.setVisible(true);
			}
		});
		returnButton.setBounds(310, 350, 105, 27);
		frame.getContentPane().add(returnButton);

	}
	class MyWinListener extends WindowAdapter {
		
		public void windowClosing(WindowEvent e) {
			frame.dispose();
		}
	}


	

}
