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
import java.sql.ResultSet;

import javax.swing.JPasswordField;

import com.mysql.jdbc.Connection;

import databaseConnect.ConnectDB;


public class ChangeApiKey {

	public JFrame frmManageApiKey;
	private JTextField textField;
	private JTextField textField_2;
	private JPasswordField passwordField;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeApiKey window = new ChangeApiKey("","");
					window.frmManageApiKey.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	String upass,id;
	public ChangeApiKey(String ID,String Pass) {
		upass=Pass;
		id=ID;
		initialize();
	}

	private void initialize() {
		frmManageApiKey = new JFrame();
		frmManageApiKey.getContentPane().setBackground(new Color(255, 240, 245));
		frmManageApiKey.setTitle("Manage API Key");
		frmManageApiKey.setIconImage(Toolkit.getDefaultToolkit().getImage(UserSignUp.class.getResource("/img/simple.png")));
		frmManageApiKey.setBounds(200, 100, 902, 430);
		frmManageApiKey.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmManageApiKey.getContentPane().setLayout(null);
		
		JLabel lblChangeApikey = new JLabel("Change API-KEY");
		lblChangeApikey.setForeground(new Color(0, 0, 255));
		lblChangeApikey.setFont(new Font("Algerian", Font.PLAIN, 35));
		lblChangeApikey.setBounds(249, 25, 299, 52);
		frmManageApiKey.getContentPane().add(lblChangeApikey);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(236, 73, 318, 4);
		frmManageApiKey.getContentPane().add(separator);
		
		JLabel lblEnterPassword = new JLabel("Enter Global Password   :");
		lblEnterPassword.setForeground(new Color(30, 144, 255));
		lblEnterPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterPassword.setBounds(123, 132, 163, 30);
		frmManageApiKey.getContentPane().add(lblEnterPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password        :");
		lblConfirmPassword.setForeground(new Color(30, 144, 255));
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblConfirmPassword.setBounds(123, 180, 171, 30);
		frmManageApiKey.getContentPane().add(lblConfirmPassword);
		
		JLabel lblEnterNewApikey = new JLabel("Enter New API-KEY     :");
		lblEnterNewApikey.setForeground(new Color(30, 144, 255));
		lblEnterNewApikey.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEnterNewApikey.setBounds(123, 227, 163, 30);
		frmManageApiKey.getContentPane().add(lblEnterNewApikey);
		
		textField = new JTextField();
		textField.setBounds(310, 132, 269, 30);
		frmManageApiKey.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(310, 228, 470, 30);
		frmManageApiKey.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnChnage = new JButton("Change");
		btnChnage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String gpass="Home%Tech%Design@2020#";
				String pass=textField.getText().toString();
				String cpass=passwordField.getText().toString();
				String key=textField_2.getText().toString();
				if(gpass.length()==0 || key.length()==0){
					JOptionPane.showMessageDialog(frmManageApiKey, "Enter All Fields...");
				}
				else{
						Connection con;
						PreparedStatement ps;
						try{
							if(pass.equals(gpass)){
								ConnectDB obj= new ConnectDB();
								con=obj.Connect();
								
								if(cpass.equals(upass)){
									String st="delete from apikey";
									ps=con.prepareStatement(st);
									ps.executeUpdate();
									
									String st1="insert into apikey value(?)";
									ps=con.prepareStatement(st1);
									ps.setString(1, key);
									ps.executeUpdate();
									
									JOptionPane.showMessageDialog(frmManageApiKey, "New API-Key Is Set Successfully !!!");
									con.close();
									frmManageApiKey.dispose();
								}
								else{
									JOptionPane.showMessageDialog(frmManageApiKey, "Your Password Is Wrong!!!");
								}
							}
							else{
								JOptionPane.showMessageDialog(frmManageApiKey, "Global Password is Wrong....");
							}
						}
						catch(Exception es){
							
						}
					
				}
				
			}
		});
		btnChnage.setForeground(new Color(138, 43, 226));
		btnChnage.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		btnChnage.setBounds(258, 302, 239, 30);
		frmManageApiKey.getContentPane().add(btnChnage);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(310, 181, 269, 30);
		frmManageApiKey.getContentPane().add(passwordField);
	}
}
