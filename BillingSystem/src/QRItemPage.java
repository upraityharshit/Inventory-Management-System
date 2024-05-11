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

public class QRItemPage {

	public JFrame frame;
	String operation;
	private JTextField textField;
	JComboBox jcb,comboBox;
	JSpinner txtquan,txtsaleprice,txtgst,txtpurprice;
	JTextArea txtremark;
	private JTextField txtsupname;
	private JTextField txtitemcode;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QRItemPage window = new QRItemPage("add");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con;
	
	public QRItemPage(String str) {
		operation=str;
		
		try{
			ConnectDB obj= new ConnectDB();
			con=obj.Connect();
		}
		catch(Exception e){
			
		}
		
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Manage Item");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(itempage.class.getResource("/img/simple.png")));
		frame.getContentPane().setBackground(new Color(211, 211, 211));
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblItemaddDelete = new JLabel("Item (Add using Item Official QR)");
		lblItemaddDelete.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemaddDelete.setForeground(Color.BLUE);
		lblItemaddDelete.setFont(new Font("Times New Roman", Font.PLAIN, 35));
		lblItemaddDelete.setBounds(89, 11, 532, 37);
		frame.getContentPane().add(lblItemaddDelete);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(165, 42, 42));
		separator.setBounds(10, 59, 677, 2);
		frame.getContentPane().add(separator);
		
		JLabel lblItemName = new JLabel("Item Name ");
		lblItemName.setForeground(new Color(0, 0, 0));
		lblItemName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblItemName.setBounds(89, 130, 182, 24);
		frame.getContentPane().add(lblItemName);
		
		if(operation.equals("add")){
			textField = new JTextField();
			textField.setBounds(338, 130, 181, 24);
			frame.getContentPane().add(textField);
			textField.setColumns(10);

		}
		else{
			jcb=new JComboBox();
			jcb.setBounds(338, 130, 181, 24);
			frame.getContentPane().add(jcb);
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
			frame.getContentPane().add(btnViewData);
		}
		
		
		JLabel lblItemBrand = new JLabel("Select Item Brand");
		lblItemBrand.setHorizontalAlignment(SwingConstants.LEFT);
		lblItemBrand.setForeground(new Color(0, 0, 0));
		lblItemBrand.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblItemBrand.setBounds(89, 170, 182, 24);
		frame.getContentPane().add(lblItemBrand);
		
		JLabel lblPriceperItem = new JLabel("Supplier Name");
		lblPriceperItem.setHorizontalAlignment(SwingConstants.LEFT);
		lblPriceperItem.setForeground(new Color(0, 0, 0));
		lblPriceperItem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPriceperItem.setBounds(89, 210, 182, 24);
		frame.getContentPane().add(lblPriceperItem);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setHorizontalAlignment(SwingConstants.LEFT);
		lblQuantity.setForeground(new Color(0, 0, 0));
		lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblQuantity.setBounds(89, 250, 182, 24);
		frame.getContentPane().add(lblQuantity);
		
		JLabel lblPriceperitem = new JLabel("Sale Price");
		lblPriceperitem.setHorizontalAlignment(SwingConstants.LEFT);
		lblPriceperitem.setForeground(new Color(0, 0, 0));
		lblPriceperitem.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPriceperitem.setBounds(89, 290, 182, 24);
		frame.getContentPane().add(lblPriceperitem);
		
		comboBox = new JComboBox();
		comboBox.setBounds(338, 170, 181, 24);
		frame.getContentPane().add(comboBox);
		comboBox.addItem("Select one");
		addbrand();
		
		txtquan = new JSpinner();
		txtquan.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		txtquan.setBounds(338, 250, 181, 24);
		frame.getContentPane().add(txtquan);
		
		txtsaleprice = new JSpinner();
		txtsaleprice.setModel(new SpinnerNumberModel(new Float(1), new Float(1), null, new Float(1)));
		txtsaleprice.setBounds(338, 290, 181, 24);
		frame.getContentPane().add(txtsaleprice);
		
		JLabel lblRemarkForThis = new JLabel("REMARK for this Item");
		lblRemarkForThis.setHorizontalAlignment(SwingConstants.LEFT);
		lblRemarkForThis.setForeground(new Color(0, 0, 0));
		lblRemarkForThis.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblRemarkForThis.setBounds(89, 410, 182, 24);
		frame.getContentPane().add(lblRemarkForThis);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(338, 410, 181, 70);
		frame.getContentPane().add(scrollPane);
		
		txtremark = new JTextArea();
		scrollPane.setViewportView(txtremark);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(165, 42, 42));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(287, 59, 11, 430);
		frame.getContentPane().add(separator_1);
		
		JButton btnAddNew = new JButton("Add Item");
		btnAddNew.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itemcategorypage window = new itemcategorypage("add");
				window.itemcatframe.setVisible(true);
				frame.dispose();
			}
		});
		btnAddNew.setBounds(536, 172, 89, 18);
		frame.getContentPane().add(btnAddNew);
		
		JButton btnAddNew_1 = new JButton("Add New");
		btnAddNew_1.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		btnAddNew_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supplierpage window = new supplierpage("add");
				window.frmManageSupplier.setVisible(true);
				frame.dispose();
			}
		});
		btnAddNew_1.setBounds(537, 213, 89, 18);
		frame.getContentPane().add(btnAddNew_1);
		
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
					String supplier=txtsupname.getText();
					int quantity=(int)txtquan.getValue();
					float price=(float)txtsaleprice.getValue();
					float purprice=(float)txtpurprice.getValue();
					int gst=(int)txtgst.getValue();
					String remark=txtremark.getText();
					String code=txtitemcode.getText().toString();
					
						if(operation.equals("add")){
							
							String name=textField.getText();
							
							if(textField.getText().length()==0 || comboBox.getSelectedItem().toString().equalsIgnoreCase("select one") || 
									txtsupname.getText().length()==0 || txtitemcode.getText().length()==0){
								JOptionPane.showMessageDialog(frame, "Please Fill All Entry...");
							}
							else{
								
								
								String sq="SELECT itemid FROM item WHERE itemid=(SELECT max(itemid) FROM item)";
								ps1=con.prepareStatement(sq);
								ResultSet rss=ps1.executeQuery();
								
								int itemid=0;
								if(rss.next()){
									itemid=rss.getInt(1)+1;
								}
								
								try {
						            String qrCodeData = itemid+"";
						            String filePath = "D:\\QRCodes\\Items\\"+name+"_"+brand+"_"+itemid+".png";
						            String charset = "UTF-8"; // or "ISO-8859-1"
						            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
						            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
						            BitMatrix matrix = new MultiFormatWriter().encode(
						                new String(qrCodeData.getBytes(charset), charset),
						                BarcodeFormat.QR_CODE, 200, 200, hintMap);
						            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
						                .lastIndexOf('.') + 1), new File(filePath));
						            //System.out.println("QR Code image created successfully!");
								} catch (Exception e) {
						            System.err.println(e);
								}
								
								
								
								String str="select itemname,brandname from item where itemname=? and brandname=?";
								ps=con.prepareStatement(str);
								ps.setString(1,name);
								ps.setString(2, brand);
								ResultSet rs=ps.executeQuery();
								if(!rs.next()){
									String sql="insert into item values(itemid,?,?,?,?,?,?,?,?,?)";
									ps=con.prepareStatement(sql);
									ps.setString(1,code);
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
									
									
									
								JOptionPane.showMessageDialog(frame, "Data Saved...QR_Code Created");
									
								}
								else{
									JOptionPane.showMessageDialog(frame, "This Item and Brand Is already exists\n\nTry another Brand");
								}
							}
						}
						else if(operation.equals("update")){
							
							if(jcb.getSelectedItem().toString().equalsIgnoreCase("Select one") || comboBox.getSelectedItem().toString().equalsIgnoreCase("select one") || 
									txtsupname.getText().length()==0 || txtitemcode.getText().length()==0)
							{
								JOptionPane.showMessageDialog(frame, "Please Fill All Entry...");
							}
							else{
								String name=jcb.getSelectedItem().toString();
								
									String str="update item set itemprice=?,PurchasePrice=?, itemquantity=itemquantity+?,GST=?,itemsupplier=?,Remark=?,barcode=? where itemname=? and brandname=?";
									ps=con.prepareStatement(str);
									ps.setFloat(1,price);
									ps.setFloat(2,purprice);
									ps.setInt(3,quantity);
									ps.setInt(4,gst);
									ps.setString(5,supplier);
									ps.setString(6,remark);
									ps.setString(7,code);
									ps.setString(8,name);
									ps.setString(9,brand);
									int p=ps.executeUpdate();
									JOptionPane.showMessageDialog(frame, "Data Updated...");
									
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
								JOptionPane.showMessageDialog(frame, "Data Deleted...");
								
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
								JOptionPane.showMessageDialog(frame, "Item Deleted");
							}
							else{
								JOptionPane.showMessageDialog(frame, "You are not Select any Item Name");
							}
						}
					}	
				catch(Exception es){
						JOptionPane.showMessageDialog(frame, "Database Connectivity Error....\nPlease Contact Developer..!!!\n"+es);
				}
			}
		});
		btnSave.setFont(new Font("Algerian", Font.PLAIN, 20));
		btnSave.setBounds(100, 515, 164, 37);
		frame.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.setBounds(338, 515, 164, 37);
		frame.getContentPane().add(btnCancel);
		
		txtsupname = new JTextField();
		txtsupname.setBounds(338, 210, 181, 24);
		frame.getContentPane().add(txtsupname);
		txtsupname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("GST %");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(89, 370, 182, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtgst = new JSpinner();
		txtgst.setBounds(338, 370, 181, 24);
		frame.getContentPane().add(txtgst);
		
		JLabel lblPurchasePrice = new JLabel("Purchase Price");
		lblPurchasePrice.setHorizontalAlignment(SwingConstants.LEFT);
		lblPurchasePrice.setForeground(new Color(0, 0, 0));
		lblPurchasePrice.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPurchasePrice.setBounds(89, 330, 182, 24);
		frame.getContentPane().add(lblPurchasePrice);
		
		txtpurprice = new JSpinner();
		txtpurprice.setBounds(338, 330, 181, 24);
		frame.getContentPane().add(txtpurprice);
		
		JLabel lblItemBarcode = new JLabel("Item BarCode");
		lblItemBarcode.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblItemBarcode.setBounds(89, 90, 182, 25);
		frame.getContentPane().add(lblItemBarcode);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(new Color(165, 42, 42));
		separator_2.setBounds(10, 491, 677, 2);
		frame.getContentPane().add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(165, 42, 42));
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(10, 59, 11, 430);
		frame.getContentPane().add(separator_3);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBackground(new Color(165, 42, 42));
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(686, 59, 11, 430);
		frame.getContentPane().add(separator_4);
		
		txtitemcode = new JTextField();
		txtitemcode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(operation.equalsIgnoreCase("add")){
					
				}
				else
					showdata1();
			}
		});
		txtitemcode.setBounds(338, 90, 181, 24);
		frame.getContentPane().add(txtitemcode);
		txtitemcode.setColumns(10);
		frame.setBounds(100, 50, 712, 623);
		
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
				JOptionPane.showMessageDialog(frame, "Please Select Both Item And Item Brand");
			}
			else{
				String str="select * from item where itemname=? and brandname=?";
				ps=con.prepareStatement(str);
				ps.setString(1, jcb.getSelectedItem().toString());
				ps.setString(2, comboBox.getSelectedItem().toString());
				rs=ps.executeQuery();
				if(rs.next()){
					txtitemcode.setText(rs.getString(2));
					txtsupname.setText(rs.getString(4));
					txtsaleprice.setValue(rs.getFloat(6));
					txtpurprice.setValue(rs.getFloat(7));
					txtquan.setValue(rs.getInt(8));
					txtgst.setValue(rs.getInt(9));
					txtremark.setText(rs.getString(10));
				}
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	}
	
	public void showdata1(){
		try{	
			PreparedStatement ps;
			ResultSet rs;
			
			if(txtitemcode.getText().length()==0){
				JOptionPane.showMessageDialog(frame, "Please Enter/Scan Item BarCode");
			}
			else{
				String str="select * from item where barcode=?";
				ps=con.prepareStatement(str);
				ps.setString(1, txtitemcode.getText().toString());
				rs=ps.executeQuery();
				if(rs.next()){
					comboBox.setSelectedItem(rs.getString(3));
					jcb.setSelectedItem(rs.getString(5));
					txtsupname.setText(rs.getString(4));
					txtsaleprice.setValue(rs.getFloat(6));
					txtpurprice.setValue(rs.getFloat(7));
					txtquan.setValue(rs.getInt(8));
					txtgst.setValue(rs.getInt(9));
					txtremark.setText(rs.getString(10));
				}
				else
					JOptionPane.showMessageDialog(frame, "Invalid  BarCode...!!");
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	}
}
