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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import databaseConnect.ConnectDB;

import java.awt.Toolkit;
import javax.swing.JScrollPane;


public class supplierpage {

	public JFrame frmManageSupplier;
	String operation;
	private JTextField textField;
	JComboBox jcb,comboBox,comboBox_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextArea textArea,textArea_1;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					supplierpage window = new supplierpage("add");
					window.frmManageSupplier.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public supplierpage(String str) {
		operation=str;
		initialize();
	}

	
	private void initialize() {
		frmManageSupplier = new JFrame();
		frmManageSupplier.setIconImage(Toolkit.getDefaultToolkit().getImage(supplierpage.class.getResource("/img/simple.png")));
		frmManageSupplier.setTitle("Manage Supplier");
		frmManageSupplier.setBounds(100, 100, 450, 300);
		frmManageSupplier.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmManageSupplier.getContentPane().setLayout(null);
		
		JLabel lblItemaddDelete = new JLabel("Supplier (ADD /DELETE /UPDATE)");
		lblItemaddDelete.setForeground(Color.BLUE);
		lblItemaddDelete.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblItemaddDelete.setBounds(118, 25, 538, 46);
		frmManageSupplier.getContentPane().add(lblItemaddDelete);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(118, 82, 515, 2);
		frmManageSupplier.getContentPane().add(separator);
		
		JLabel lblItemName = new JLabel("Supplier Name ");
		lblItemName.setForeground(Color.CYAN);
		lblItemName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblItemName.setBounds(90, 118, 175, 24);
		frmManageSupplier.getContentPane().add(lblItemName);
		
		if(operation.equals("add")){
			textField = new JTextField();
			textField.setBounds(374, 105, 206, 37);
			frmManageSupplier.getContentPane().add(textField);
			textField.setColumns(10);
		
		}
		else{
			jcb=new JComboBox();
			jcb.setBounds(374, 105, 206, 37);
			frmManageSupplier.getContentPane().add(jcb);
			jcb.addItem("Select One");
			addsup();
			
			JButton btnViewData = new JButton("View Data");
			btnViewData.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showdata();
				}
			});
			btnViewData.setBounds(610, 112, 95, 23);
			frmManageSupplier.getContentPane().add(btnViewData);
		}
		
		JLabel lblItemBrand = new JLabel("Select Item Brand");
		lblItemBrand.setForeground(Color.CYAN);
		lblItemBrand.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblItemBrand.setBounds(91, 168, 174, 24);
		frmManageSupplier.getContentPane().add(lblItemBrand);
		
		JLabel lblPriceperItem = new JLabel("Select Item Name");
		lblPriceperItem.setForeground(Color.CYAN);
		lblPriceperItem.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPriceperItem.setBounds(90, 212, 175, 24);
		frmManageSupplier.getContentPane().add(lblPriceperItem);
		
		JLabel lblQuantity = new JLabel("Contact No.");
		lblQuantity.setForeground(Color.CYAN);
		lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblQuantity.setBounds(90, 258, 175, 24);
		frmManageSupplier.getContentPane().add(lblQuantity);
		
		JLabel lblPriceperitem = new JLabel("Email ID");
		lblPriceperitem.setForeground(Color.CYAN);
		lblPriceperitem.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPriceperitem.setBounds(90, 301, 175, 24);
		frmManageSupplier.getContentPane().add(lblPriceperitem);
		
		comboBox = new JComboBox();
		comboBox.setBounds(374, 153, 206, 37);
		frmManageSupplier.getContentPane().add(comboBox);
		comboBox.addItem("Select one");
		addbrand();
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(374, 201, 206, 37);
		frmManageSupplier.getContentPane().add(comboBox_1);
		comboBox_1.addItem("Select one");
		additem();
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setForeground(Color.CYAN);
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAddress.setBounds(90, 354, 175, 24);
		frmManageSupplier.getContentPane().add(lblAddress);
		
		JLabel lblRemarkForThis = new JLabel("REMARK for this Item");
		lblRemarkForThis.setForeground(Color.CYAN);
		lblRemarkForThis.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblRemarkForThis.setBounds(90, 419, 175, 24);
		frmManageSupplier.getContentPane().add(lblRemarkForThis);
		
		textField_1 = new JTextField();
		textField_1.setBounds(374, 249, 206, 37);
		frmManageSupplier.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(374, 293, 206, 37);
		frmManageSupplier.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(374, 340, 206, 69);
		frmManageSupplier.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(374, 420, 206, 59);
		frmManageSupplier.getContentPane().add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(287, 105, 11, 381);
		frmManageSupplier.getContentPane().add(separator_1);
		
		JButton btnAddNew = new JButton("Add New");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itemcategorypage window = new itemcategorypage("add");
				window.itemcatframe.setVisible(true);
				frmManageSupplier.dispose();
			}
		});
		btnAddNew.setBounds(610, 160, 89, 23);
		frmManageSupplier.getContentPane().add(btnAddNew);
		
		JButton btnAddNew_1 = new JButton("Add New");
		btnAddNew_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itempage window = new itempage("add");
				window.frmManageItem.setVisible(true);
				frmManageSupplier.dispose();
			}
		});
		btnAddNew_1.setBounds(610, 208, 89, 23);
		frmManageSupplier.getContentPane().add(btnAddNew_1);
		
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
							String brand=comboBox.getSelectedItem().toString();
							String itemname=comboBox_1.getSelectedItem().toString();
							String Phone=textField_1.getText();
							String email=textField_2.getText();
							String address=textArea.getText();
							String remark=textArea_1.getText();
							
							if(textField.getText().length()==0 || textField_1.getText().length()==0 || textField_2.getText().length()==0 || 
									comboBox.getSelectedItem().toString().equalsIgnoreCase("select one") || 
										comboBox_1.getSelectedItem().toString().equalsIgnoreCase("select one")){
								JOptionPane.showMessageDialog(frmManageSupplier, "Please Fill All Entry...");
							}
							else{
								String str="select supname from supplier where supname=?";
								ps=con.prepareStatement(str);
								ps.setString(1,name);
								ResultSet rs=ps.executeQuery();
								if(!rs.next()){
									String sql="insert into supplier values(id,?,?,?,?,?,?,?)";
									ps=con.prepareStatement(sql);
									ps.setString(1,name);
									ps.setString(2,brand);
									ps.setString(3,itemname);
									ps.setString(4,Phone);
									ps.setString(5,email);
									ps.setString(6,address);
									ps.setString(7,remark);
									ps.executeUpdate();
									JOptionPane.showMessageDialog(frmManageSupplier, "Data Saved");
									textField.setText("");
									textField_1.setText("");
									textField_2.setText("");
									textArea.setText("");
									textArea_1.setText("");
									comboBox.setSelectedIndex(0);
									comboBox_1.setSelectedIndex(0);
									
								}
								else{
									JOptionPane.showMessageDialog(frmManageSupplier, "This Supplier Is already exists...");
								}
							}
						}
						else if(operation.equals("update")){
							
							if(jcb.getSelectedItem().toString().equalsIgnoreCase("select one") || textField_1.getText().length()==0 || textField_2.getText().length()==0 || 
									comboBox.getSelectedItem().toString().equalsIgnoreCase("select one") || 
										comboBox_1.getSelectedItem().toString().equalsIgnoreCase("select one")){
								JOptionPane.showMessageDialog(frmManageSupplier, "Please Fill All Entry...");
							}
							else{
								String supname=jcb.getSelectedItem().toString();
								String brandname=comboBox.getSelectedItem().toString();
								String itemname=comboBox_1.getSelectedItem().toString();
								String Phone=textField_1.getText();
								String email=textField_2.getText();
								String address=textArea.getText();
								String remark=textArea_1.getText();
								
									String str="update supplier set brandname=?,itemname=?,phone_no=?,email_id=?,address=?,Remark=? where supname=?";
									ps=con.prepareStatement(str);
									ps.setString(1, brandname);
									ps.setString(2, itemname);
									ps.setString(3, Phone);
									ps.setString(4, email);
									ps.setString(5, address);
									ps.setString(6, remark);
									ps.setString(7, supname);
									int p=ps.executeUpdate();
									JOptionPane.showMessageDialog(frmManageSupplier, "Data Updated...");
							}
						}
						else if(operation.equals("delete")){
							if(!jcb.getSelectedItem().toString().equalsIgnoreCase("Select one")){
								String supname=jcb.getSelectedItem().toString();
								String str="delete from supplier where supname=?";
								ps=con.prepareStatement(str);
								ps.setString(1,supname);
								int p=ps.executeUpdate();
								JOptionPane.showMessageDialog(frmManageSupplier, "Data Deleted...");
								textField_1.setText("");
								textField_2.setText("");
								textArea.setText("");
								textArea_1.setText("");
								comboBox.setSelectedIndex(0);
								comboBox_1.setSelectedIndex(0);
								jcb.removeItem(jcb.getSelectedItem());
								
							}
							else{
								JOptionPane.showMessageDialog(frmManageSupplier, "You are not Select any supplier Name");
							}
						}
						con.close();
					}	
				catch(Exception es){
						JOptionPane.showMessageDialog(frmManageSupplier, "Database Connectivity Error....\nPlease Contact Developer..!!!\n"+es);
				}
			}
		});
		btnSave.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnSave.setBounds(134, 521, 170, 37);
		frmManageSupplier.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmManageSupplier.dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(364, 522, 164, 37);
		frmManageSupplier.getContentPane().add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(supplierpage.class.getResource("/img/blur-blurred-highway-1240.jpg")));
		lblNewLabel.setBounds(0, 0, 750, 584);
		frmManageSupplier.getContentPane().add(lblNewLabel);
		frmManageSupplier.setBounds(100, 50, 766, 623);
		frmManageSupplier.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	}
	public void addsup(){
		try{
			Connection con;
			PreparedStatement ps;
			ResultSet rs;
			ConnectDB obj= new ConnectDB();
			con=obj.Connect();
			
				String str="select * from supplier";
				ps=con.prepareStatement(str);
				rs=ps.executeQuery();
				while(rs.next()){
					jcb.addItem(rs.getString(2));
				}
			con.close();
		}
		catch(Exception e){}
	}
	
	public void addbrand(){
		try{
			Connection con;
			PreparedStatement ps;
			ResultSet rs;
			ConnectDB obj= new ConnectDB();
			con=obj.Connect();
			
				String str="select DISTINCT brandname from itembrand";
				ps=con.prepareStatement(str);
				rs=ps.executeQuery();
				while(rs.next()){
					comboBox.addItem(rs.getString(1));
				}
			con.close();
		}
		catch(Exception e){}
	}
	public void additem(){
		try{
			Connection con;
			PreparedStatement ps;
			ResultSet rs;
			ConnectDB obj= new ConnectDB();
			con=obj.Connect();
			
			String str="select DISTINCT itemname from item";
			ps=con.prepareStatement(str);
			rs=ps.executeQuery();
			while(rs.next()){
				comboBox_1.addItem(rs.getString(1));
			}
			con.close();
		}
		catch(Exception e){}
	}
	public boolean checknum(){
		return true;
	}
	public boolean checkemail(){
		return true;
	}
	
	public void showdata(){
		try{
			Connection con;
			PreparedStatement ps;
			ResultSet rs;
			ConnectDB obj= new ConnectDB();
			con=obj.Connect();
			
				String str="select * from supplier where supname=?";
				ps=con.prepareStatement(str);
				ps.setString(1, jcb.getSelectedItem().toString());
				rs=ps.executeQuery();
				if(rs.next()){
					comboBox.setSelectedItem(rs.getString(3));
					comboBox_1.setSelectedItem(rs.getString(4));
					textField_1.setText(rs.getString(5));
					textField_2.setText(rs.getString(6));
					textArea.setText(rs.getString(7));
					textArea_1.setText(rs.getString(8));
				}
			con.close();
		}
		catch(Exception e){}
	}
		
}

