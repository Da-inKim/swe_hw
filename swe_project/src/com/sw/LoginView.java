package com.sw;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;


class LoginView extends JDialog implements ActionListener {
		
		String present_id;
		String present_pw;
		
		JPanel p1,p2,p3;
		JButton changeIDButton, changePWButton;
		JButton logoutButton,scheduleButton,phonebookButton;
		
		public LoginView(String id,String pw) {
			present_id = id;
			present_pw = pw;
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
			scheduleButton = new JButton("½ºÄÉÁì ±â´É");
			scheduleButton.addActionListener(this);
			phonebookButton = new JButton("ÆùºÏ ±â´É");
			phonebookButton.addActionListener(this);
			p2.add(scheduleButton);
			p2.add(phonebookButton); 		
			p3 = new JPanel();
			p1.add(p3,"South");
			logoutButton = new JButton("·Î±×¾Æ¿ô");
			logoutButton.addActionListener(this);
			p3.add(logoutButton);
			setSize(400,200);
			addWindowListener(new MyWinListener());
		}
			
		
		
		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == changeIDButton) {
				ChangeIDview presentchid = new ChangeIDview(present_id,present_pw);	
				dispose();
				presentchid.setVisible(true);
			}
			
			if (e.getSource() == changePWButton) {			
				ChangePWview aPWchange = new ChangePWview(present_id,present_pw);
				dispose();
				aPWchange.setVisible(true);
			
			}
			
			if (e.getSource() == phonebookButton) {
					PhoneView callphone;
					try {
						callphone = new PhoneView(present_id, present_pw);
						dispose();
						callphone.frame.setVisible(true);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}
				
			if (e.getSource() == scheduleButton) {
				ScheduleView callSchedule;
				try {
					callSchedule = new ScheduleView(present_id, present_pw);
					dispose();
					callSchedule.frame.setVisible(true);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				
			if (e.getSource() == logoutButton) {
				User_Account3 returnlogin = new User_Account3();
				dispose();
				returnlogin.setVisible(true);
				returnlogin.setSize(500,200);
				returnlogin.setTitle("HOME");
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