package com.sw;
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

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

public class ScheduleView {
	
	JFrame frame;
	JTable table;
	JScrollPane scrollpane;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String present_id;
	String present_pw;
	
	String url = "jdbc:mysql://127.0.0.1:3306/sehw2";
	String user = "root";
	String pass = "01047670231";
	


	public ScheduleView(String id, String pw) throws ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		present_id = id;
		present_pw = pw;
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
		
		String header[] = {"번호","날짜","내용"};
		DefaultTableModel model = new DefaultTableModel(header, 0);
		table = new JTable(model);
		table.setBounds(90,60,300,250);
		scrollpane = new JScrollPane(table);
		frame.add(scrollpane);
		frame.add(table);
		model.addRow(header);
		
		try{
			con = DriverManager.getConnection(url,user,pass);
			String sql = "SELECT schedule_key,date,description FROM schedule";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				String[] row = new String[3];
				row[0] = rs.getString("schedule_key");
				row[1] = rs.getString("date");
				row[2] = rs.getString("description");
				model.addRow(row);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		JButton addButton = new JButton("추가");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_Schedule addschedule;
				try {
					addschedule = new Add_Schedule(present_id,present_pw);
					frame.dispose();
					addschedule.frame.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		addButton.setBounds(70, 350, 105, 27);
		frame.getContentPane().add(addButton);
		
		JButton deleteButton = new JButton("삭제");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete_Schedule deleteschedule;
				try {
					deleteschedule = new Delete_Schedule(present_id,present_pw);
					frame.dispose();
					deleteschedule.frame.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		deleteButton.setBounds(190, 350, 105, 27);
		frame.getContentPane().add(deleteButton);
		
		JButton returnButton = new JButton("이전으로");
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
