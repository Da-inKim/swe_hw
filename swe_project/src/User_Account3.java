import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	JTextField textPW = new JTextField(10);
	JTextField textPW1 = new JTextField(10);
	JTextField textPW2 = new JTextField(10);
	JButton okButton, cancelButton;
	
	ChangePWview(LoginView loginView, String str) {
		super(loginView, str, true);
		p1 = new JPanel();
		add(p1);
		p3 = new JPanel();
		p1.add(p3,"North");
		//textPW.setText(id);
		textPW.setEditable(false);
		p3.add(textPW);		
		labelPW1 = new JLabel(" 변경할 PW를 입력 ");
		p1.add(labelPW1);
		p1.add(textPW1);
		textPW1.addActionListener(this);
		labelPW2 = new JLabel(" PW 재입력 ");
		p1.add(labelPW2);
		p1.add(textPW2);
		p2 = new JPanel();
		p1.add(p2,"South");
		textPW2.addActionListener(this);
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
			String new_id1 = textPW1.getText();
			String new_id2 = textPW2.getText();			
			isChangePW(new_id1, new_id2);			
		}
		
		if (e.getSource() == cancelButton) {
			textPW1.setText(null);
			textPW2.setText(null);
			dispose();
		}
		
	}
	class MyWinListener extends WindowAdapter {
		
		public void windowClosing(WindowEvent e) {
			dispose();
		}
		
	}
	
	public void isChangePW(String new_id1, String new_id2) {
		if ( new_id1 == new_id2 ){//textPW1.getText()==textPW2.getText()			
			//String new_id = textPW1.getText();			
			JOptionPane.showMessageDialog(this, "아이디가 변경되었습니다.");			
		}
		else {
			JOptionPane.showMessageDialog(this, "다시 시도하세요.","error",JOptionPane.ERROR_MESSAGE);
		}
		textPW1.setText(null);
		textPW2.setText(null);
	}
	
	
}

class ChangeIDview extends JDialog implements ActionListener {
	JPanel p1,p2,p3;
	JLabel labelID1,labelID2;
	JTextField textID = new JTextField(10);
	JTextField textID1 = new JTextField(10);
	JTextField textID2 = new JTextField(10);
	JButton okButton, cancelButton;
	
	ChangeIDview(LoginView loginView, String str) {
		super(loginView,str, true);
		p1 = new JPanel();
		add(p1,"Center");
		p3 = new JPanel();
		p1.add(p3,"North");
		//textID.setText(id);
		textID.setEditable(false);
		p3.add(textID);
		labelID1 = new JLabel(" 변경할 ID를 입력 ");
		p1.add(labelID1);
		p1.add(textID1);
		textID1.addActionListener(this);
		labelID2 = new JLabel(" ID 재입력 ");
		p1.add(labelID2);
		p1.add(textID2);
		p2 = new JPanel();
		p1.add(p2,"South");
		textID2.addActionListener(this);
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
			String new_id1 = textID1.getText();
			String new_id2 = textID2.getText();		
			
			isChangeID(new_id1,new_id2);
			
		}
		
		if (e.getSource()==cancelButton) {
			textID1.setText(null);
			textID2.setText(null);
			dispose();
		}
		
	}
	class MyWinListener extends WindowAdapter {
		
		public void windowClosing(WindowEvent e) {
			dispose();
		}
	}
	
	public void isChangeID(String new_id1,String new_id2) {//사용하지 않음!
		
		if (new_id1 == new_id2){
			JOptionPane.showMessageDialog(this, "아이디가 변경되었습니다.");
		}
		else {
			JOptionPane.showMessageDialog(this, "다시 입력해주세요.","error",JOptionPane.ERROR_MESSAGE);
		}
		//masterID=id;//user접근 필요!
		textID1.setText(null);
		textID2.setText(null);	
	}
}


class LoginView extends JDialog implements ActionListener {
	ChangeIDview aIDchange;
	ChangePWview aPWchange;
	
	JPanel p1,p2,p3;
	JButton changeIDButton, changePWButton;
	JButton logoutButton,scheduleButton,phonebookButton;

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
		scheduleButton = new JButton("스케쥴 기능");
		scheduleButton.addActionListener(this);
		phonebookButton = new JButton("폰북 기능");
		phonebookButton.addActionListener(this);
		p2.add(scheduleButton);
		p2.add(phonebookButton); 		
		p3 = new JPanel();
		p1.add(p3,"South");
		logoutButton = new JButton("로그아웃");
		logoutButton.addActionListener(this);
		p3.add(logoutButton);
		setSize(400,200);
		addWindowListener(new MyWinListener());
	}
	
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == changeIDButton){
			
			if (aIDchange == null){
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
			;
		}
			
		if (e.getSource() == scheduleButton) {
			;
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

class User_Account3 extends JFrame implements ActionListener{
	LoginView aLogin;
	ChangeIDview aIDchange;
	ChangePWview aPWchange;	
	Connection conn = null;
	PreparedStatement pstmt = null;
	String url = "jdbc:mysql://localhost:3306/database";
	//String url = "jdbc:mysql://127.0.0.1:3306/SoftwareEnginnerHw";
	String user = "root";
	String pass = "4175^^";
/*	String userId = request.getParameter("user");
	String userPass = request.getParameter("pass");
	conn = DriverManager.getConnection(url, user, pass);
	//
	String sql = "SELECT * FROM member WHERE id=?"; 
	pstmt = conn.prepareStatement(sql); 
	pstmt.setString(1, userId); 
	ResultSet rs = pstmt.executeQuery(); 
*/	
	String masterID=user;
	String masterPW=pass;
	String id, pw, new_id, new_pw;	
	JPanel p;
	JButton loginButton;
	JLabel labelID, labelPW;
	JTextField textID = new JTextField(10);
	JTextField textPW = new JTextField(10);
	
	User_Account3() {
		p = new JPanel();
		add(p);		
		labelID = new JLabel(" ID ");
		labelPW = new JLabel(" PW ");
		p.add(labelID);
		p.add(textID);
		textID.addActionListener(this);
		p.add(labelPW);
		p.add(textPW);
		textPW.addActionListener(this);
		loginButton = new JButton("로그인");
		p.add(loginButton);
		loginButton.addActionListener((ActionListener) this);

	}
	
	public void isLogin(String id, String pw){
		
		if ( id.equals(masterID) && pw.equals(masterPW) ) {
			if ( aLogin == null ) {
				aLogin = new LoginView(this, "LOGIN");
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "다시 입력해주세요.","error",JOptionPane.ERROR_MESSAGE);
		}	
	}
	
	public void isLogout(String id, String pw) {
		dispose();
	}
/*	
	public void isChangeID(String new_id) {//사용하지 않음!
		
		id = new_id;
		masterID=id;//user접근 필요!
		
		JOptionPane.showMessageDialog(this, "아이디가 변경되었습니다.");
		//JOptionPane.showMessageDialog(this, "다시 입력해주세요.","error",JOptionPane.ERROR_MESSAGE);
	}
*/
	public void isChangePW(String new_pw, String pw) {//사용하지 않음!
		pw = new_pw;
		masterPW = pw;//user접근 필요!
		
		JOptionPane.showMessageDialog(this, "비밀번호가 변경되었습니다.");
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
		User_Account3 account = new User_Account3();
		
		account.setTitle("HOME");
		account.setSize(400,200);
		account.setVisible(true);

	}

}


