import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JList;

import databaseConnect.ConnectDB;

import java.awt.Toolkit;
import javax.swing.JScrollPane;


public class customerpage {

	public JFrame frmManageCustomer;
	String operation;
	private JTextField textField;
	JComboBox jcb;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea,textArea_1;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customerpage window = new customerpage("add");
					window.frmManageCustomer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public customerpage(String str) {
		operation=str;
		initialize();
	}

	private void initialize() {
		frmManageCustomer = new JFrame();
		frmManageCustomer.setTitle("Manage Customer");
		frmManageCustomer.setIconImage(Toolkit.getDefaultToolkit().getImage(customerpage.class.getResource("/img/simple.png")));
		frmManageCustomer.setBounds(100, 100, 450, 300);
		frmManageCustomer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmManageCustomer.getContentPane().setLayout(null);
		
		JLabel lblItemaddDelete = new JLabel("Customer (ADD /DELETE /UPDATE)");
		lblItemaddDelete.setBounds(101, 25, 555, 46);
		lblItemaddDelete.setForeground(Color.BLUE);
		lblItemaddDelete.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		frmManageCustomer.getContentPane().add(lblItemaddDelete);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(111, 82, 522, 2);
		frmManageCustomer.getContentPane().add(separator);
		
		JLabel lblItemName = new JLabel("Customer Name ");
		lblItemName.setBounds(91, 137, 175, 24);
		lblItemName.setForeground(Color.CYAN);
		lblItemName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frmManageCustomer.getContentPane().add(lblItemName);
		
		if(operation.equals("add")){
			textField = new JTextField();
			textField.setBounds(374, 130, 206, 37);
			frmManageCustomer.getContentPane().add(textField);
			textField.setColumns(10);
		
		}
		else{
			jcb=new JComboBox();
			jcb.setBounds(374, 130, 206, 37);
			frmManageCustomer.getContentPane().add(jcb);
			jcb.addItem("Select One");
			addcus();
			
			JButton btnViewData = new JButton("View Data");
			btnViewData.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showdata();
				}
			});
			btnViewData.setBounds(610, 130, 95, 23);
			frmManageCustomer.getContentPane().add(btnViewData);
		}
		
		JLabel lblQuantity = new JLabel("Contact No.");
		lblQuantity.setBounds(91, 192, 175, 24);
		lblQuantity.setForeground(Color.CYAN);
		lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frmManageCustomer.getContentPane().add(lblQuantity);
		
		JLabel lblPriceperitem = new JLabel("Email ID");
		lblPriceperitem.setBounds(91, 251, 175, 24);
		lblPriceperitem.setForeground(Color.CYAN);
		lblPriceperitem.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frmManageCustomer.getContentPane().add(lblPriceperitem);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(91, 307, 175, 24);
		lblAddress.setForeground(Color.CYAN);
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 20));
		frmManageCustomer.getContentPane().add(lblAddress);
		
		JLabel lblRemarkForThis = new JLabel("REMARK for this Item");
		lblRemarkForThis.setBounds(91, 405, 175, 24);
		lblRemarkForThis.setForeground(Color.CYAN);
		lblRemarkForThis.setFont(new Font("Times New Roman", Font.BOLD, 15));
		frmManageCustomer.getContentPane().add(lblRemarkForThis);
		
		textField_1 = new JTextField();
		textField_1.setBounds(373, 189, 206, 37);
		frmManageCustomer.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(373, 248, 206, 37);
		frmManageCustomer.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(373, 307, 206, 75);
		frmManageCustomer.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(373, 405, 206, 67);
		frmManageCustomer.getContentPane().add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(287, 137, 11, 335);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		frmManageCustomer.getContentPane().add(separator_1);
		
		JButton btnSave;
		
		if(operation.equals("add"))
			btnSave = new JButton("SAVE");
		else if(operation.equals("update"))
			btnSave = new JButton("Update");
		else
			btnSave = new JButton("Delete");
			
			btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con;
				PreparedStatement ps,ps1;
				try{
					ConnectDB obj= new ConnectDB();
					con=obj.Connect();
					
						if(operation.equals("add")){
							String name=textField.getText();
							String Phone=textField_1.getText();
							String email=textField_2.getText();
							String address=textArea.getText();
							String remark=textArea_1.getText();
							
							if(textField.getText().length()==0 || textField_1.getText().length()==0 || textField_2.getText().length()==0 || 
									textArea.getText().length()==0 || textArea_1.getText().length()==0){
								JOptionPane.showMessageDialog(frmManageCustomer, "Please Fill All Entry...");
							}
							else{
								if(checknum() && checkemail())
								{
								String str="select cusname from customer where cusname=?";
								ps=con.prepareStatement(str);
								ps.setString(1,name);
								ResultSet rs=ps.executeQuery();
									if(!rs.next()){
										String sql="insert into customer values(cusid,?,?,?,?,?)";
										ps=con.prepareStatement(sql);
										ps.setString(1,name);
										ps.setString(2,Phone);
										ps.setString(3,email);
										ps.setString(4,address);
										ps.setString(5,remark);
										ps.executeUpdate();
										JOptionPane.showMessageDialog(frmManageCustomer, "Data Saved");
										textField.setText("");
										textField_1.setText("");
										textField_2.setText("");
										textArea.setText("");
										textArea_1.setText("");
									}
									else{
										JOptionPane.showMessageDialog(frmManageCustomer, "This Customer Is already exists...");
									}
								}
								else{
									JOptionPane.showMessageDialog(frmManageCustomer, "Please Check Phone Number And Email");
								}
								
							}
						}
						else if(operation.equals("update")){
							
							if(textField_1.getText().length()==0 || textField_2.getText().length()==0 ||
									textArea.getText().length()==0 || textArea_1.getText().length()==0 || 
									jcb.getSelectedItem().toString().equalsIgnoreCase("select one")){
								JOptionPane.showMessageDialog(frmManageCustomer, "Please Fill All Entry...");
							}
							else{
								String cusname=jcb.getSelectedItem().toString();
								String Phone=textField_1.getText();
								String email=textField_2.getText();
								String address=textArea.getText();
								String remark=textArea_1.getText();
								
									String str="update customer set phone_no=?,email_id=?,address=?,Remark=? where cusname=?";
									ps=con.prepareStatement(str);
									ps.setString(1, Phone);
									ps.setString(2, email);
									ps.setString(3, address);
									ps.setString(4, remark);
									ps.setString(5, cusname);
									int p=ps.executeUpdate();
									JOptionPane.showMessageDialog(frmManageCustomer, "Data Updated...");
							}
						}
						else if(operation.equals("delete")){
							if(!jcb.getSelectedItem().toString().equalsIgnoreCase("Select one")){
								String cusname=jcb.getSelectedItem().toString();
								String str="delete from customer where cusname=?";
								ps=con.prepareStatement(str);
								ps.setString(1,cusname);
								int p=ps.executeUpdate();
								JOptionPane.showMessageDialog(frmManageCustomer, "Data Deleted...");
								textField_1.setText("");
								textField_2.setText("");
								textArea.setText("");
								textArea_1.setText("");
								jcb.removeItem(jcb.getSelectedItem());
								
							}
							else{
								JOptionPane.showMessageDialog(frmManageCustomer, "You are not Select any Customer Name");
							}
						}
					}	
				catch(Exception es){
						JOptionPane.showMessageDialog(frmManageCustomer, "Database Connectivity Error....\nPlease Contact Developer..!!!\n"+es);
				}
			}
		});
		btnSave.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnSave.setBounds(134, 500, 170, 37);
		frmManageCustomer.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(362, 500, 164, 37);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmManageCustomer.dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		frmManageCustomer.getContentPane().add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(0, 0, 750, 584);
		lblNewLabel.setIcon(new ImageIcon(customerpage.class.getResource("/img/blur-blurred-highway-1240.jpg")));
		frmManageCustomer.getContentPane().add(lblNewLabel);
		frmManageCustomer.setBounds(100, 50, 766, 623);
		frmManageCustomer.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	public void addcus(){
		try{
			Connection con;
			PreparedStatement ps;
			ResultSet rs;
			ConnectDB obj= new ConnectDB();
			con=obj.Connect();
			
				String str="select * from customer";
				ps=con.prepareStatement(str);
				rs=ps.executeQuery();
				while(rs.next()){
					jcb.addItem(rs.getString(2));
				}
		}
		catch(Exception e){}
	}
	
	public boolean checknum(){
		  String phoneNumber = textField_1.getText();
	      String regex = "(0/91)?[7-9][0-9]{9}";
	      return phoneNumber.matches(regex);
	}
	public boolean checkemail(){
		String email=textField_2.getText();
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	    return email.matches(regex);
	}
	
	public void showdata(){
		try{
			Connection con;
			PreparedStatement ps;
			ResultSet rs;
			ConnectDB obj= new ConnectDB();
			con=obj.Connect();
			
				String str="select * from customer where cusname=?";
				ps=con.prepareStatement(str);
				ps.setString(1, jcb.getSelectedItem().toString());
				rs=ps.executeQuery();
				if(rs.next()){
					textField_1.setText(rs.getString(3));
					textField_2.setText(rs.getString(4));
					textArea.setText(rs.getString(5));
					textArea_1.setText(rs.getString(6));
				}
		}
		catch(Exception e){}
	}
}


