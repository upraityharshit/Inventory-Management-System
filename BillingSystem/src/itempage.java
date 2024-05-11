import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Map;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;

import com.google.zxing.EncodeHintType;

import databaseConnect.ConnectDB;

import java.awt.Toolkit;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter; 
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javax.swing.JScrollPane;
import javax.swing.DropMode;

public class itempage {

	public JFrame frmManageItem;
	String operation;
	private JTextField textField;
	JComboBox jcb,comboBox;
	JSpinner txtquan,txtsaleprice,txtgst,txtpurprice;
	JTextArea txtremark;
	private JTextField txtsupname;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					itempage window = new itempage("update");
					window.frmManageItem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con;
	
	public itempage(String str) {
		operation=str;
		
		try{
			ConnectDB obj= new ConnectDB();
			con=obj.Connect();
		}
		catch(Exception e){}
		
		initialize();
	}

	private void initialize() {
		frmManageItem = new JFrame();
		frmManageItem.setResizable(false);
		frmManageItem.setTitle("Manage Item");
		frmManageItem.setIconImage(Toolkit.getDefaultToolkit().getImage(itempage.class.getResource("/img/simple.png")));
		frmManageItem.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmManageItem.getContentPane().setLayout(null);
		
		
		JLabel lblItemaddDelete = new JLabel("Item (ADD /DELETE /UPDATE)");
		lblItemaddDelete.setForeground(Color.BLUE);
		lblItemaddDelete.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblItemaddDelete.setBounds(118, 35, 482, 46);
		frmManageItem.getContentPane().add(lblItemaddDelete);
		
		JLabel lblItemName = new JLabel("Item Name ");
		lblItemName.setForeground(new Color(0, 0, 0));
		lblItemName.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblItemName.setBounds(89, 130, 182, 24);
		frmManageItem.getContentPane().add(lblItemName);
		
		if(operation.equals("add")){
			textField = new JTextField();
			textField.setBounds(338, 130, 181, 24);
			frmManageItem.getContentPane().add(textField);
			textField.setColumns(10);

		}
		else{
			jcb=new JComboBox();
			jcb.setBounds(338, 130, 181, 24);
			frmManageItem.getContentPane().add(jcb);
			jcb.addItem("Select One");
			additem();
			
			JButton btnViewData = new JButton("View Data");
			btnViewData.setFont(new Font("Times New Roman", Font.PLAIN, 10));
			btnViewData.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showdata();
				}
			});
			btnViewData.setBounds(536, 132, 89, 18);
			frmManageItem.getContentPane().add(btnViewData);
		}
		
		
		JLabel lblItemBrand = new JLabel("Select Item Brand");
		lblItemBrand.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemBrand.setForeground(new Color(0, 0, 0));
		lblItemBrand.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblItemBrand.setBounds(89, 170, 182, 24);
		frmManageItem.getContentPane().add(lblItemBrand);
		
		JLabel lblPriceperItem = new JLabel("Supplier Name");
		lblPriceperItem.setHorizontalAlignment(SwingConstants.LEFT);
		lblPriceperItem.setForeground(new Color(0, 0, 0));
		lblPriceperItem.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPriceperItem.setBounds(89, 210, 182, 24);
		frmManageItem.getContentPane().add(lblPriceperItem);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantity.setForeground(new Color(0, 0, 0));
		lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblQuantity.setBounds(89, 250, 182, 24);
		frmManageItem.getContentPane().add(lblQuantity);
		
		JLabel lblPriceperitem = new JLabel("Sale Price");
		lblPriceperitem.setHorizontalAlignment(SwingConstants.LEFT);
		lblPriceperitem.setForeground(new Color(0, 0, 0));
		lblPriceperitem.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPriceperitem.setBounds(89, 290, 182, 24);
		frmManageItem.getContentPane().add(lblPriceperitem);
		
		comboBox = new JComboBox();
		comboBox.setBounds(338, 170, 181, 24);
		frmManageItem.getContentPane().add(comboBox);
		comboBox.addItem("Select one");
		addbrand();
		
		txtquan = new JSpinner();
		txtquan.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		txtquan.setBounds(338, 250, 181, 24);
		frmManageItem.getContentPane().add(txtquan);
		
		txtsaleprice = new JSpinner();
		txtsaleprice.setModel(new SpinnerNumberModel(new Float(1), new Float(1), null, new Float(1)));
		txtsaleprice.setBounds(338, 293, 181, 24);
		frmManageItem.getContentPane().add(txtsaleprice);
		
		JLabel lblRemarkForThis = new JLabel("REMARK for this Item");
		lblRemarkForThis.setHorizontalAlignment(SwingConstants.LEFT);
		lblRemarkForThis.setForeground(new Color(0, 0, 0));
		lblRemarkForThis.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblRemarkForThis.setBounds(89, 410, 182, 24);
		frmManageItem.getContentPane().add(lblRemarkForThis);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(338, 410, 181, 70);
		frmManageItem.getContentPane().add(scrollPane);
		
		txtremark = new JTextArea();
		scrollPane.setViewportView(txtremark);
		
		JButton btnAddNew = new JButton("Add Item");
		btnAddNew.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itemcategorypage window = new itemcategorypage("add");
				window.itemcatframe.setVisible(true);
				frmManageItem.dispose();
			}
		});
		btnAddNew.setBounds(536, 172, 89, 18);
		frmManageItem.getContentPane().add(btnAddNew);
		
		JButton btnAddNew_1 = new JButton("Add New");
		btnAddNew_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnAddNew_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supplierpage window = new supplierpage("add");
				window.frmManageSupplier.setVisible(true);
				frmManageItem.dispose();
			}
		});
		btnAddNew_1.setBounds(537, 213, 89, 18);
		frmManageItem.getContentPane().add(btnAddNew_1);
		
		JButton btnSave;
		
		if(operation.equals("add"))
			btnSave = new JButton("SAVE");
		else if(operation.equals("update"))
			btnSave = new JButton("Update");
		else
			btnSave = new JButton("Delete");
		
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				PreparedStatement ps,ps1;
				
				Calendar cal=Calendar.getInstance();
				int dat=cal.get(Calendar.DATE);
				int mon=cal.get(Calendar.MONTH)+1;
				int yr=cal.get(Calendar.YEAR);
				
				try{
					
					String brand=comboBox.getSelectedItem().toString();
					String supplier=txtsupname.getText();;
					int quantity=(int)txtquan.getValue();
					float price=(float)txtsaleprice.getValue();
					float purprice=(float)txtpurprice.getValue();
					int gst=(int)txtgst.getValue();
					String remark=txtremark.getText();
					
						if(operation.equals("add")){
							
							String name=textField.getText();
						
							if(textField.getText().length()==0 || comboBox.getSelectedItem().toString().equalsIgnoreCase("select one") || 
									txtsupname.getText().length()==0){
								JOptionPane.showMessageDialog(frmManageItem, "Please Fill All Entry...");
							}
							else{
								
								String sq="SELECT itemid FROM item WHERE itemid=(SELECT max(itemid) FROM item)";
								ps1=con.prepareStatement(sq);
								ResultSet rss=ps1.executeQuery();
								
								int itemid=0;
								if(rss.next()){
									itemid=rss.getInt(1)+1;
								}								
								
								String str="select itemname,brandname from item where itemname=? and brandname=?";
								ps=con.prepareStatement(str);
								ps.setString(1,name);
								ps.setString(2, brand);
								ResultSet rs=ps.executeQuery();
								if(!rs.next()){
									String sql="insert into item values(itemid,?,?,?,?,?,?,?,?,?)";
									ps=con.prepareStatement(sql);
									ps.setString(1,""+itemid);
									ps.setString(2,brand);
									ps.setString(3,supplier);
									ps.setString(4,name);
									ps.setFloat(5,price);
									ps.setFloat(6,purprice);
									ps.setInt(7,quantity);
									ps.setInt(8,gst);
									ps.setString(9,remark);
									ps.executeUpdate();
									
									//Purchase History Table Saved Data
									
									String sql1="insert into itempurchase values(itempurid,?,?,?,?,?,?,?,?,?,?)";
									ps1=con.prepareStatement(sql1);
									ps1.setString(1,brand);
									ps1.setString(2,supplier);
									ps1.setString(3,name);
									ps1.setFloat(4,price);
									ps1.setFloat(5,purprice);
									ps1.setInt(6,quantity);
									ps1.setInt(7,gst);
									ps1.setString(8,dat+"-"+mon+"-"+yr);
									ps1.setString(9,"Insert");
									ps1.setString(10,remark);
									ps1.executeUpdate();
									
									textField.setText("");
									txtremark.setText("");
									comboBox.setSelectedIndex(0);
									txtsupname.setText("");
									txtquan.setValue(1);
									txtsaleprice.setValue(1);
									txtgst.setValue(0);
									txtpurprice.setValue(1);
									
									
								JOptionPane.showMessageDialog(frmManageItem, "Data Saved...QR_Code Created");
									
								}
								else{
									JOptionPane.showMessageDialog(frmManageItem, "This Item and Brand Is already exists\n\nTry another Brand");
								}
							}
						}
						else if(operation.equals("update")){
							
							if(jcb.getSelectedItem().toString().equalsIgnoreCase("Select one") || comboBox.getSelectedItem().toString().equalsIgnoreCase("select one") || 
									txtsupname.getText().length()==0)
							{
								JOptionPane.showMessageDialog(frmManageItem, "Please Fill All Entry...");
							}
							else{
								String name=jcb.getSelectedItem().toString();
								
									String str="update item set itemprice=?,PurchasePrice=?, itemquantity=itemquantity+?,GST=?,itemsupplier=?,Remark=? where itemname=? and brandname=?";
									ps=con.prepareStatement(str);
									ps.setFloat(1,price);
									ps.setFloat(2,purprice);
									ps.setInt(3,quantity);
									ps.setInt(4,gst);
									ps.setString(5,supplier);
									ps.setString(6,remark);
									ps.setString(7,name);
									ps.setString(8,brand);
									int p=ps.executeUpdate();
									JOptionPane.showMessageDialog(frmManageItem, "Data Updated...");
									
									//	Purchase History Table Saved Data
									
									String sql1="insert into itempurchase values(itempurid,?,?,?,?,?,?,?,?,?,?)";
									ps1=con.prepareStatement(sql1);
									ps1.setString(1,brand);
									ps1.setString(2,supplier);
									ps1.setString(3,name);
									ps1.setFloat(4,price);
									ps1.setFloat(5,purprice);
									ps1.setInt(6,quantity);
									ps1.setInt(7,gst);
									ps1.setString(8,dat+"-"+mon+"-"+yr);
									ps1.setString(9,"Update");
									ps1.setString(10,remark);
									ps1.executeUpdate();
									
									
									txtremark.setText("");
									comboBox.setSelectedIndex(0);
									txtsupname.setText("");
									txtquan.setValue(1);
									txtsaleprice.setValue(0);
									txtpurprice.setValue(1);
							}
						}
						else if(operation.equals("delete")){
							if(!jcb.getSelectedItem().toString().equalsIgnoreCase("Select one"))
							{
								String name=jcb.getSelectedItem().toString();
								
								String str="delete from item where itemname=? and brandname=?";
								ps=con.prepareStatement(str);
								ps.setString(1,name);
								ps.setString(2,brand);
								int p=ps.executeUpdate();
								JOptionPane.showMessageDialog(frmManageItem, "Data Deleted...");
								
//								Purchase History Table Saved Data
								
								String sql1="insert into itempurchase values(itempurid,?,?,?,?,?,?,?,?,?,?)";
								ps1=con.prepareStatement(sql1);
								ps1.setString(1,brand);
								ps1.setString(2,supplier);
								ps1.setString(3,name);
								ps1.setFloat(4,price);
								ps1.setFloat(5,purprice);
								ps1.setInt(6,quantity);
								ps1.setInt(7,gst);
								ps1.setString(8,dat+"-"+mon+"-"+yr);
								ps1.setString(9,"Delete");
								ps1.setString(10,remark);
								ps1.executeUpdate();
								
								jcb.removeItem(name);
								JOptionPane.showMessageDialog(frmManageItem, "Item Deleted");
							}
							else{
								JOptionPane.showMessageDialog(frmManageItem, "You are not Select any Item Name");
							}
						}
					}	
				catch(Exception es){
						JOptionPane.showMessageDialog(frmManageItem, "Database Connectivity Error....\nPlease Contact Developer..!!!\n"+es);
				}
			}
		});
		btnSave.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnSave.setBounds(100, 524, 164, 35);
		frmManageItem.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmManageItem.dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(341, 523, 164, 37);
		frmManageItem.getContentPane().add(btnCancel);
		
		txtsupname = new JTextField();
		txtsupname.setBounds(338, 210, 181, 24);
		frmManageItem.getContentPane().add(txtsupname);
		txtsupname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("GST %");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(89, 370, 182, 24);
		frmManageItem.getContentPane().add(lblNewLabel_1);
		
		txtgst = new JSpinner();
		txtgst.setBounds(338, 370, 181, 24);
		frmManageItem.getContentPane().add(txtgst);
		
		JLabel lblPurchasePrice = new JLabel("Purchase Price");
		lblPurchasePrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPurchasePrice.setForeground(new Color(0, 0, 0));
		lblPurchasePrice.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblPurchasePrice.setBounds(89, 330, 182, 24);
		frmManageItem.getContentPane().add(lblPurchasePrice);
		
		txtpurprice = new JSpinner();
		txtpurprice.setModel(new SpinnerNumberModel(new Float(1), new Float(1), null, new Float(1)));
		txtpurprice.setBounds(338, 330, 181, 24);
		frmManageItem.getContentPane().add(txtpurprice);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(new Color(165, 42, 42));
		separator.setBounds(10, 97, 11, 403);
		frmManageItem.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(165, 42, 42));
		separator_1.setBounds(10, 94, 677, 2);
		frmManageItem.getContentPane().add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(new Color(165, 42, 42));
		separator_2.setBounds(287, 97, 11, 403);
		frmManageItem.getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBackground(new Color(165, 42, 42));
		separator_3.setBounds(686, 97, 11, 403);
		frmManageItem.getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(165, 42, 42));
		separator_4.setBounds(10, 502, 677, 2);
		frmManageItem.getContentPane().add(separator_4);
		frmManageItem.setBounds(100, 50, 716, 625);
		
	}
	public void addbrand(){
		try{
			PreparedStatement ps;
			ResultSet rs;
			
			if(operation.equals("add")){
				String str="select DISTINCT brandname from itembrand";
				ps=con.prepareStatement(str);
				rs=ps.executeQuery();
				while(rs.next()){
					comboBox.addItem(rs.getString(1));
				}
			}
			else{
				String str="select DISTINCT brandname from item";
				ps=con.prepareStatement(str);
				rs=ps.executeQuery();
				while(rs.next()){
					comboBox.addItem(rs.getString(1));
				}
			}
		}
		catch(Exception e){}
	}
	public void additem(){
		try{
			PreparedStatement ps;
			ResultSet rs;
			
			String str="select DISTINCT itemname from item";
			ps=con.prepareStatement(str);
			rs=ps.executeQuery();
			while(rs.next()){
				jcb.addItem(rs.getString(1));
			}
		}
		catch(Exception e){}
	}
	
	public void showdata(){
		try{
			PreparedStatement ps;
			ResultSet rs;
			
			if(jcb.getSelectedItem().toString().equalsIgnoreCase("select one") || comboBox.getSelectedItem().toString().equalsIgnoreCase("select one")){
				JOptionPane.showMessageDialog(frmManageItem, "Please Select Both Item And Item Brand");
			}
			else{
				String str="select * from item where itemname=? and brandname=?";
				ps=con.prepareStatement(str);
				ps.setString(1, jcb.getSelectedItem().toString());
				ps.setString(2, comboBox.getSelectedItem().toString());
				rs=ps.executeQuery();
				if(rs.next()){
	
					txtsupname.setText(rs.getString(4));
					txtsaleprice.setValue(rs.getFloat(6));
					txtpurprice.setValue(rs.getFloat(7));
					txtquan.setValue(rs.getInt(8));
					txtgst.setValue(rs.getInt(9));
					txtremark.setText(rs.getString(10));
				}
			}
		}
		catch(Exception e){}
	}
}
