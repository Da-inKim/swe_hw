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

public class ScheduleView {
	
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
					ScheduleView window = new ScheduleView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ScheduleView() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame("스케줄");
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("스케줄 목록");
		lblNewLabel.setBounds(200, 30, 105, 15);
		frame.getContentPane().add(lblNewLabel);
		
		String header[] = {"날짜","내용"};
		DefaultTableModel model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		table.setBounds(90,60,300,250);
		scrollpane = new JScrollPane(table);
		frame.add(scrollpane);
		frame.add(table);
		
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sehw2","root","4175^^");
			String sql = "SELECT date,description FROM schedule";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String[] row = new String[2];
				row[0] = rs.getString("date");
				row[1] = rs.getString("description");
				model.addRow(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		JButton addButton = new JButton("추가");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_Schedule addschedule = new Add_Schedule();
				addschedule.frame.setVisible(true);
			}
		});
		addButton.setBounds(100, 350, 105, 27);
		frame.getContentPane().add(addButton);
		
		JButton deleteButton = new JButton("삭제");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete_Schedule deleteschedule = new Delete_Schedule();
				deleteschedule.frame.setVisible(true);
			}
		});
		deleteButton.setBounds(260, 350, 105, 27);
		frame.getContentPane().add(deleteButton);
	}
}
