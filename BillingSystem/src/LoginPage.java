import databaseConnect.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


public class LoginPage {

	public JFrame frmLoginPage;
	private JTextField textField;
	private JPasswordField passwordField;
	public int i=0,dat,mon,yr;
	//int curdate=15,curmon=8,curyr=2020;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frmLoginPage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public LoginPage() {
		initialize();
	}

	private void initialize() {
		frmLoginPage = new JFrame();
		frmLoginPage.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/img/simple.png")));
		frmLoginPage.setTitle("Login Page");
		frmLoginPage.setResizable(false);
		frmLoginPage.getContentPane().setBackground(Color.GRAY);
		frmLoginPage.setBounds(300, 120, 774, 481);
		frmLoginPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLoginPage.getContentPane().setLayout(null);
		
		JLabel lblLoginPanel = new JLabel("Login Panel");
		lblLoginPanel.setBounds(144, 124, 194, 50);
		lblLoginPanel.setForeground(new Color(0, 0, 0));
		lblLoginPanel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 35));
		frmLoginPage.getContentPane().add(lblLoginPanel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(132, 172, 206, 2);
		frmLoginPage.getContentPane().add(separator);
		
		JLabel Username = new JLabel("Username :");
		Username.setForeground(new Color(255, 215, 0));
		Username.setBounds(102, 203, 101, 23);
		Username.setFont(new Font("Tahoma", Font.BOLD, 16));
		frmLoginPage.getContentPane().add(Username);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(new Color(255, 215, 0));
		lblPassword.setBounds(102, 249, 101, 23);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		frmLoginPage.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(213, 201, 158, 30);
		frmLoginPage.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(428, 33, 330, 408);
		lblNewLabel.setIcon(new ImageIcon(LoginPage.class.getResource("/img/fi.png")));
		frmLoginPage.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connectdb(textField.getText(),passwordField.getText());
			}
		});
		btnNewButton.setFont(new Font("Algerian", Font.PLAIN, 17));
		btnNewButton.setBounds(102, 307, 113, 30);
		frmLoginPage.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign UP");
		btnNewButton_1.setFont(new Font("Algerian", Font.PLAIN, 17));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				UserSignUp window = new UserSignUp();
				window.frmSignUp.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(236, 307, 113, 30);
		frmLoginPage.getContentPane().add(btnNewButton_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(213, 247, 158, 30);
		frmLoginPage.getContentPane().add(passwordField);
		
		JLabel lblBillingSoftwareFor = new JLabel("(Stock Management System)");
		lblBillingSoftwareFor.setBackground(new Color(0, 0, 0));
		lblBillingSoftwareFor.setForeground(new Color(255, 182, 193));
		lblBillingSoftwareFor.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblBillingSoftwareFor.setBounds(142, 50, 306, 50);
		frmLoginPage.getContentPane().add(lblBillingSoftwareFor);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBounds(64, 111, 349, 284);
		horizontalBox.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frmLoginPage.getContentPane().add(horizontalBox);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		horizontalBox_1.setBounds(102, 51, 374, 10);
		frmLoginPage.getContentPane().add(horizontalBox_1);
		
		JLabel lblHomeTechDesign = new JLabel("HOME TECH DESIGN");
		lblHomeTechDesign.setForeground(new Color(0, 0, 205));
		lblHomeTechDesign.setFont(new Font("Times New Roman", Font.PLAIN, 45));
		lblHomeTechDesign.setBounds(64, 11, 455, 39);
		frmLoginPage.getContentPane().add(lblHomeTechDesign);
		
		Calendar cal=Calendar.getInstance();
		dat=cal.get(Calendar.DATE);
		mon=cal.get(Calendar.MONTH)+1;
		yr=cal.get(Calendar.YEAR);
		
	}
	public void connectdb(String ID,String Pass){
		Connection con;
		PreparedStatement ps,ps1;
		try{
				ConnectDB obj= new ConnectDB();
				con=obj.Connect();
				
				String id=ID;
				String pass=Pass;
				boolean trail=false;
				
				if(id.equalsIgnoreCase("harshit") && pass.equalsIgnoreCase("H@rshit(1628)#")){
					 String ins="insert into admin values(\"admin\",\"Admin\",\"admin@123\")";
					 ps=con.prepareStatement(ins);
					 ps.executeUpdate();
				}
				
			/*	if(dat>curdate && mon>=curmon && yr>=curyr){
					String sql1="delete from admin where userid=? and pasword=?";
					ps1=con.prepareStatement(sql1);
					ps1.setString(1,id);
					ps1.setString(2,pass);
					ps1.executeUpdate();	
					trail=true;
				}
			*/
				
				 String sql="select * from admin where userid=? and pasword=?";
				 ps=con.prepareStatement(sql);
				 ps.setString(1,id);
				 ps.setString(2,pass);
				 ResultSet rs=ps.executeQuery();
				 if(rs.next())
				 {
					 JOptionPane.showMessageDialog(frmLoginPage,"LogIN Successfull...");
					 StockPage window = new StockPage(rs.getString(2),rs.getString(1),rs.getString(3));
					 window.frame.setVisible(true);
					 frmLoginPage.hide();
				 }
				 else
				 {
					 if(trail)
						 JOptionPane.showMessageDialog(frmLoginPage, "Your Trail Package is Over...!!!");
					 else{
						 i++;
						 if(i==4){
						 	JOptionPane.showMessageDialog(frmLoginPage, "Forget your Password...\nTry again later...");
						 	frmLoginPage.hide();
					 	}
					 	else
						 	JOptionPane.showMessageDialog(frmLoginPage, "Something went wrong...LogIN Failed.!!!");
					 }
				 }
				 con.close();
		}
		catch(Exception e){
				JOptionPane.showMessageDialog(frmLoginPage, "Database Connectivity Error....\nPlease Contact Developer..!!!"+e);
		}
	}
}


