import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Add_Schedule {
	private JFrame frame;
	private JTextField textField;
	private JTextArea textArea;
	private String add_date;
	private String add_description;
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String url = "jdbc:mysql://127.0.0.1:3306/SoftwareEnginnerHw";
	String user = "root";
	String pass = "4175^^";
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Schedule window = new Add_Schedule();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Add_Schedule() {
		initialize();
	}

	private void initialize() {
		try {
			con = DriverManager.getConnection(url,user,pass);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame = new JFrame("스케줄 추가하기");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("날짜");
		lblNewLabel.setBounds(86, 62, 62, 18);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(197, 59, 116, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		add_date = textField.getText();
		
		JLabel lblNewLabel_1 = new JLabel("내용");
		lblNewLabel_1.setBounds(86, 109, 62, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		textArea = new JTextArea();
		textArea.setBounds(197, 106, 116, 24);
		frame.getContentPane().add(textArea);
		textArea.setColumns(10);
		add_description = textArea.getText();
		
		JButton btnNewButton = new JButton("추가하기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String sql1 = "insert into Schedule values(?,?)";
					ps = con.prepareStatement(sql1);
					ps.setString(3,add_date);
					ps.setString(4,add_description);
					int n = ps.executeUpdate();
					if(n>0){
						System.out.println("추가 성공");
					}else{
						System.out.println("추가 실패");
					}
				}catch(SQLException e1){
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(85, 156, 105, 27);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("취소");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScheduleView returnlist = new ScheduleView("master");
				
				
			}
		});
		btnNewButton_1.setBounds(225, 156, 105, 27);
		frame.getContentPane().add(btnNewButton_1);
		
	}
}
