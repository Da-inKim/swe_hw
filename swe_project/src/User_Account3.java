import java.awt.EventQueue;
import java.awt.Frame;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



class ChangePWview extends JDialog implements ActionListener {
	JPanel p1,p2,p3;
	JLabel labelPW1,labelPW2;
	JTextField textPW = new JTextField(15);//stored_pw
	JTextField textPW1 = new JTextField(15);	
	JButton okButton, cancelButton;
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String sql = "SELECT stored_id, stored_pw FROM account";
	
	ChangePWview(LoginView loginView, String str) {
		super(loginView, str, true);
		p1 = new JPanel();
		add(p1);
		p3 = new JPanel();
		p1.add(p3,"North");
		//textPW.setText(stored_pw);
		textPW.setEditable(false);
		p3.add(textPW);		
		labelPW1 = new JLabel(" ������ PW�� �Է� ");
		p1.add(labelPW1);
		p1.add(textPW1);
		textPW1.addActionListener(this);
		p2 = new JPanel();
		p1.add(p2,"South");
		okButton = new JButton("ok");
		okButton.addActionListener(this);
		p2.add(okButton);
		cancelButton = new JButton("cancel");
		cancelButton.addActionListener(this);
		p2.add(cancelButton);		
		setSize(500,200);
		addWindowListener(new MyWinListener());
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource()==okButton ) {
			String pw = textPW.getText();	
			String new_pw = textPW1.getText();				
			
			if ( isChangePW(new_pw, pw) == pw) {
				textPW.setText(pw);				
			}
			else if ( isChangePW(pw,new_pw) == new_pw ) {
				textPW.setText(new_pw);				
			}		
		}
		
		if (e.getSource() == cancelButton) {
			dispose();
		}
		
		textPW1.setText(null);
		
	}
	class MyWinListener extends WindowAdapter {
		
		public void windowClosing(WindowEvent e) {
			dispose();
		}
		
	}
	
	public String isChangePW(String new_pw, String pw) {		
		
		if ( (new_pw != pw) && (new_pw.length() <= 15) && (new_pw.length() >= 8) ){
			JOptionPane.showMessageDialog(this, "��й�ȣ�� ����Ǿ����ϴ�.");
			
			try {
				psmt = conn.prepareStatement(sql);
				psmt.executeUpdate("UPDATE accounts SET stored_pw=new_pw WHERE stored_pw=pw");
			} catch (SQLException e1) {
				System.out.println(e1);
			}
			
			return new_pw;
		}
		else {
			JOptionPane.showMessageDialog(this, "�ٽ� �Է����ּ���.","error",JOptionPane.ERROR_MESSAGE);
			
			try {
				psmt = conn.prepareStatement(sql);
				psmt.executeUpdate("UPDATE accounts SET stored_pw=pw WHERE stored_pw=pw");
			} catch (SQLException e1) {
				System.out.println(e1);
			}
			
			return pw;
		}		
		
	}	
}

class ChangeIDview extends JDialog implements ActionListener {
	JPanel p1,p2,p3;
	JLabel labelID1,labelID2;
	JTextField textID = new JTextField(15);//stored_id
	JTextField textID1 = new JTextField(15);
	JButton okButton, cancelButton;
	
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	String sql = "SELECT stored_id, stored_pw FROM account";
	String present_id;
	
	ChangeIDview(String id){
		present_id = id;
	}
	
	ChangeIDview(LoginView loginView, String str) {
		super(loginView,str, true);
		p1 = new JPanel();
		add(p1,"Center");
		p3 = new JPanel();
		p1.add(p3,"North");
		//textID.setText(id);
		textID.setEditable(false);
		p3.add(textID);
		labelID1 = new JLabel(" ������ ID�� �Է� ");
		p1.add(labelID1);
		p1.add(textID1);
		textID1.addActionListener(this);
		p2 = new JPanel();
		p1.add(p2,"South");
		okButton = new JButton("ok");
		okButton.addActionListener(this);
		p2.add(okButton);
		cancelButton = new JButton("cancel");
		cancelButton.addActionListener(this);
		p2.add(cancelButton);		
		setSize(500,200);
		addWindowListener(new MyWinListener());
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == okButton ) {			
			String id = textID.getText();
			String new_id = textID1.getText();		
			
			if ( isChangeID(new_id, id) == id) {
				textID.setText(id);				
			}
			else if ( isChangeID(new_id, id) == new_id ) {
				textID.setText(new_id);				
			}
			
			textID1.setText(null);	
		}
		
		if (e.getSource()==cancelButton) {
			
			textID1.setText(null);			
			dispose();
		}
		
	}
	class MyWinListener extends WindowAdapter {
		
		public void windowClosing(WindowEvent e) {
			dispose();
		}
	}
	
	public String isChangeID(String new_id, String id) {
		
		if ( (id != new_id) && (new_id.length() <= 15) && (new_id.length() >= 8) ) {
			JOptionPane.showMessageDialog(this, "���̵� ����Ǿ����ϴ�.");
			
			try { // jdbc driver �ε�
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				System.out.println(e1);
			}
		
			try { // db�� connection ����
				String jdbc_url = "http:mysql://localhost:3306/sehw2";				
				conn = DriverManager.getConnection(jdbc_url,"swuser","user0000");
			} catch (SQLException e1) {
				System.out.println(e1);
			}

			try {
				psmt.executeUpdate("UPDATE account SET stored_id=new_id WHERE stored_id=id");
			} catch (SQLException e1) {
				System.out.println(e1);
			}
			
			return new_id;
		}
		else {
			JOptionPane.showMessageDialog(this, "�ٽ� �Է����ּ���.","error",JOptionPane.ERROR_MESSAGE);
			
			try { // jdbc driver �ε�
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e1) {
				System.out.println(e1);
			}
			
			try { // db�� connection ����
				String jdbc_url = "http:mysql://localhost:3306/sehw2";					
				conn = DriverManager.getConnection(jdbc_url,"root","4175^^");
			} catch (SQLException e1) {
				System.out.println(e1);
			}

			try {
				psmt = conn.prepareStatement(sql);
				psmt.executeUpdate("UPDATE account SET stored_id=id WHERE stored_id=id");
			} catch (SQLException e1) {
				System.out.println(e1);
			}		
			
			return id;
		}		
		
	}
}


class LoginView extends JDialog implements ActionListener {
	ChangeIDview aIDchange;
	ChangePWview aPWchange;
	String present_id;
	
	JPanel p1,p2,p3;
	JButton changeIDButton, changePWButton;
	JButton logoutButton,scheduleButton,phonebookButton;
	
	LoginView(String id){
		present_id = id;
	}

	LoginView(Frame parent,String str) {
		super(parent,str,true);		
		p1 = new JPanel();
		add(p1);		
		changeIDButton = new JButton(" Change ID ");		
		changeIDButton.addActionListener(this);
		changePWButton = new JButton(" Change PW ");
		changePWButton.addActionListener(this);
		p1.add(changeIDButton, "Center");
		p1.add(changePWButton, "Center");		
		p2 = new JPanel();
		p1.add(p2);
		scheduleButton = new JButton("������ ���");
		scheduleButton.addActionListener(this);
		phonebookButton = new JButton("���� ���");
		phonebookButton.addActionListener(this);
		p2.add(scheduleButton);
		p2.add(phonebookButton); 		
		p3 = new JPanel();
		p1.add(p3,"South");
		logoutButton = new JButton("�α׾ƿ�");
		logoutButton.addActionListener(this);
		p3.add(logoutButton);
		setSize(400,200);
		addWindowListener(new MyWinListener());
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == changeIDButton) {
			
			if (aIDchange == null) {
				aIDchange = new ChangeIDview(this, "CHANGE ID");
			}
			
			aIDchange.setVisible(true);
		}
		
		if (e.getSource() == changePWButton) {
			
			if (aPWchange == null) {
				aPWchange = new ChangePWview(this, "CHANGE ID");
			}
			
			aPWchange.setVisible(true);
		
		}
		
		if (e.getSource() == phonebookButton) {
			PhoneView callphone = new PhoneView() ;
			callphone.frame.setVisible(true);
			//aPhoneView; 
			
		}
			
		if (e.getSource() == scheduleButton) {
			ScheduleView callSchedule = new ScheduleView();
			callSchedule.frame.setVisible(true);
			//aScheduleView;
		}
			
		if (e.getSource() == logoutButton) {
			dispose();			
		}
		
		changeIDButton.requestFocus();
		changePWButton.requestFocus();
		phonebookButton.requestFocus();
		scheduleButton.requestFocus();
		logoutButton.requestFocus();
	}

	
	class MyWinListener extends WindowAdapter {
		
		public void windowClosing(WindowEvent e) {
			dispose();
		}
	}

	
}



public class User_Account3 extends JFrame implements ActionListener {
	LoginView aLogin;
	ChangeIDview aIDchange;
	ChangePWview aPWchange;	
	
	//String stored_id = "swuser";	//String stored_pw = "user0000";
	
	static Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	String id, pw, new_id, new_pw;	
	String db_id,db_pw;
	JPanel p;
	JButton loginButton;
	JLabel labelID, labelPW;
	JTextField textID = new JTextField(15);
	JTextField textPW = new JTextField(15);
	
	User_Account3() {
		p = new JPanel();
		add(p);		
		labelID = new JLabel(" ID ");
		labelPW = new JLabel(" PW ");
		p.add(labelID);
		p.add(textID);
		p.add(labelPW);
		p.add(textPW);
		loginButton = new JButton("�α���");
		p.add(loginButton);
		loginButton.addActionListener((ActionListener) this);

	}
	
	public void isLogin(String id, String pw){				

		try { // db�� connection ����			
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sehw2","root","4175^^");
			String sql = "SELECT stored_id, stored_pw FROM account";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while ( rs.next() ) {
				db_id = rs.getString("stored_id");
				db_pw = rs.getString("stored_pw");
				boolean r1 = id.equals(db_id);
				boolean r2 = pw.equals(db_pw);
				System.out.println(r1);
				System.out.println(r2);
			} 
		} catch (SQLException e1) {
			System.out.println(e1);
		}
		
		
		if ( id.equals(db_id) && pw.equals(db_pw) ) {
			
			LoginView present_id = new LoginView(id);
			aLogin = new LoginView(this, "LOGIN");
			
		}
		else {
			JOptionPane.showMessageDialog(this, "�ٽ� �Է����ּ���.","error",JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	public void isLogout(String id, String pw) {
		dispose();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if ( e.getSource()==loginButton ) {			
			id = textID.getText();
			pw = textPW.getText();
			
			isLogin(id,pw);

			
			textID.setText(null);
			textPW.setText(null);			
		}
		
		aLogin.setVisible(true);
		loginButton.requestFocus();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
		
				try { // jdbc driver �ε�
					Class.forName("com.mysql.jdbc.Driver");
					User_Account3 account = new User_Account3();			
					account.setTitle("HOME");
					account.setSize(400,200);
					account.setVisible(true);
				} catch (ClassNotFoundException e) {
					System.out.println(e);
				}
				
			}
		});
		
	}	
	
}



