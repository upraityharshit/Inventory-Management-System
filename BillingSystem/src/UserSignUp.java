import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Toolkit;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JPasswordField;

import com.mysql.jdbc.Connection;

import databaseConnect.ConnectDB;

import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class UserSignUp {

	public JFrame frmSignUp;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_1;
	private JTextField textField_3;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserSignUp window = new UserSignUp();
					window.frmSignUp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UserSignUp() {
		initialize();
	}

	
	private void initialize() {
		frmSignUp = new JFrame();
		frmSignUp.getContentPane().setBackground(new Color(255, 240, 245));
		frmSignUp.setTitle("Sign Up");
		frmSignUp.setIconImage(Toolkit.getDefaultToolkit().getImage(UserSignUp.class.getResource("/img/simple.png")));
		frmSignUp.setBounds(200, 100, 792, 430);
		frmSignUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignUp.getContentPane().setLayout(null);
		
		JLabel lblChangeApikey = new JLabel("New User Sign-Up");
		lblChangeApikey.setForeground(new Color(0, 0, 255));
		lblChangeApikey.setFont(new Font("Algerian", Font.PLAIN, 35));
		lblChangeApikey.setBounds(225, 11, 341, 47);
		frmSignUp.getContentPane().add(lblChangeApikey);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(217, 60, 327, 2);
		frmSignUp.getContentPane().add(separator);
		
		JLabel lblEnterPassword = new JLabel("Enter Global Password      :");
		lblEnterPassword.setForeground(new Color(30, 144, 255));
		lblEnterPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterPassword.setBounds(101, 86, 184, 30);
		frmSignUp.getContentPane().add(lblEnterPassword);
		
		JLabel lblConfirmPassword = new JLabel("Create ID             :");
		lblConfirmPassword.setForeground(new Color(30, 144, 255));
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfirmPassword.setBounds(135, 132, 150, 30);
		frmSignUp.getContentPane().add(lblConfirmPassword);
		
		JLabel lblEnterNewApikey = new JLabel("Enter Name          :");
		lblEnterNewApikey.setForeground(new Color(30, 144, 255));
		lblEnterNewApikey.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterNewApikey.setBounds(135, 180, 150, 30);
		frmSignUp.getContentPane().add(lblEnterNewApikey);
		
		textField = new JTextField();
		textField.setBounds(310, 87, 269, 30);
		frmSignUp.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(310, 183, 270, 30);
		frmSignUp.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnChnage = new JButton("Sign Up");
		btnChnage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gpass="Home%Tech%Design@2020#";
				String mypass=textField.getText().toString();
				String id=textField_1.getText().toString();
				String name=textField_2.getText().toString();
				String pass=textField_3.getText().toString();
				if(mypass.length()==0 || id.length()==0 || name.length()==0 || pass.length()==0){
					JOptionPane.showMessageDialog(frmSignUp, "Enter All Fields...");
				}
				else{
					Connection con;
					PreparedStatement ps;
					try{
						if(mypass.equals(gpass)){
							ConnectDB obj= new ConnectDB();
							con=obj.Connect();
							
							String str="insert into admin value(?,?,?)";
							ps=con.prepareStatement(str);
							ps.setString(1, id);
							ps.setString(2, name);
							ps.setString(3, pass);
							ps.executeUpdate();
							JOptionPane.showMessageDialog(frmSignUp, "Saved....");
							frmSignUp.dispose();
							con.close();
						}
						else{
							JOptionPane.showMessageDialog(frmSignUp, "Global Password Wrong....");
						}
					}
					catch(Exception es){
						
					}
				}
				
			}
		});
		btnChnage.setForeground(new Color(138, 43, 226));
		btnChnage.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnChnage.setBounds(283, 305, 150, 30);
		frmSignUp.getContentPane().add(btnChnage);
		
		textField_1 = new JTextField();
		textField_1.setBounds(310, 134, 269, 30);
		frmSignUp.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEnterPassword_1 = new JLabel("Enter Password      :");
		lblEnterPassword_1.setForeground(new Color(30, 144, 255));
		lblEnterPassword_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterPassword_1.setBounds(135, 234, 156, 30);
		frmSignUp.getContentPane().add(lblEnterPassword_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(310, 236, 270, 30);
		frmSignUp.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblLogIn = new JLabel("Log In...");
		lblLogIn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				LoginPage window = new LoginPage();
				window.frmLoginPage.setVisible(true);
				frmSignUp.hide();
			}
		});
		lblLogIn.setForeground(Color.BLUE);
		lblLogIn.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblLogIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogIn.setBounds(293, 355, 128, 25);
		frmSignUp.getContentPane().add(lblLogIn);
	}
}
