import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import databaseConnect.ConnectDB;


public class demo {

	public JFrame frame;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					demo window = new demo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public demo() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 495, 331);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
				
		JButton btnLogin = new JButton("Create DB");
		btnLogin.setBounds(145, 114, 158, 30);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con;
				PreparedStatement ps;
				try{
					ConnectDB obj= new ConnectDB();
					con=obj.Connect();
					
					String sql="CREATE TABLE `admin` (`userid` varchar(20) NOT NULL,  `name` varchar(20) DEFAULT NULL,  `pasword` varchar(20) DEFAULT NULL,  PRIMARY KEY (`userid`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
					ps=con.prepareStatement(sql);
					ps.executeUpdate();
					
					String sq="insert into admin values('admin','USER','admin@123')";
					ps=con.prepareStatement(sq);
					ps.executeUpdate();
					
					String sql1="CREATE TABLE `customer` (  `cusid` bigint(20) NOT NULL AUTO_INCREMENT,  `cusname` varchar(30) DEFAULT NULL,  `phone_no` varchar(10) DEFAULT NULL,  `email_id` varchar(30) DEFAULT NULL,  `address` mediumtext CHARACTER SET latin1,  `Remark` mediumtext CHARACTER SET latin1,  PRIMARY KEY (`cusid`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
					ps=con.prepareStatement(sql1);
					ps.executeUpdate();
					
					String sql2="CREATE TABLE `invoice` (  `invoiceid` bigint(20) NOT NULL AUTO_INCREMENT, `itemspurchase` mediumtext CHARACTER SET latin1,  `Qtyperitem` mediumtext CHARACTER SET latin1,  `itemsamount` mediumtext CHARACTER SET latin1,  `Rateperitem` mediumtext CHARACTER SET latin1,  `TotalQuantity` bigint(11) DEFAULT NULL,  `TotalGST` mediumtext CHARACTER SET latin1,  `HSNCode` mediumtext CHARACTER SET latin1,  `GrandTotal` double DEFAULT NULL,  `BuyersDeatails` mediumtext CHARACTER SET latin1,  `dat` varchar(20) DEFAULT NULL,  `CGST` int(11) DEFAULT NULL,  `SGST` int(11) DEFAULT NULL,  `IGST` int(11) DEFAULT NULL,  PRIMARY KEY (`invoiceid`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
					ps=con.prepareStatement(sql2);
					ps.executeUpdate();
					
					String sql3="CREATE TABLE `item` (  `itemid` bigint(20) NOT NULL AUTO_INCREMENT,  `barcode` varchar(20) DEFAULT NULL,  `brandname` varchar(20) DEFAULT NULL,  `itemsupplier` varchar(30) DEFAULT NULL,  `itemname` varchar(30) DEFAULT NULL,  `itemprice` float DEFAULT NULL,  `PurchasePrice` float DEFAULT NULL,  `itemquantity` bigint(11) DEFAULT NULL,  `GST` int(11) DEFAULT NULL,  `Remark` mediumtext CHARACTER SET latin1,  PRIMARY KEY (`itemid`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
					ps=con.prepareStatement(sql3);
					ps.executeUpdate();
					
					String sql4="CREATE TABLE `itembrand` (`id` bigint(11) NOT NULL AUTO_INCREMENT,`brandname` varchar(20) DEFAULT NULL,PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
					ps=con.prepareStatement(sql4);
					ps.executeUpdate();
					
					String sql5="CREATE TABLE `itempurchase` (  `itempurid` bigint(20) NOT NULL AUTO_INCREMENT,  `brandname` varchar(20) DEFAULT NULL,  `itemsupplier` varchar(30) DEFAULT NULL,  `itemname` varchar(30) DEFAULT NULL,  `itemprice` float DEFAULT NULL,  `PurchasePrice` float DEFAULT NULL,  `itemquantity` bigint(11) unsigned DEFAULT NULL,  `GST` int(11) DEFAULT NULL,  `date` varchar(12) DEFAULT NULL,  `Action` varchar(10) DEFAULT NULL,  `Remark` mediumtext CHARACTER SET latin1,  PRIMARY KEY (`itempurid`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
					ps=con.prepareStatement(sql5);
					ps.executeUpdate();
					
					String sql6="CREATE TABLE `saleitem` (`itemname` varchar(20) DEFAULT NULL,`brandname` varchar(20) DEFAULT NULL,`qun` int(11) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
					ps=con.prepareStatement(sql6);
					ps.executeUpdate();
					
					String sql7="CREATE TABLE `supplier` (  `id` bigint(20) NOT NULL AUTO_INCREMENT,  `supname` varchar(30) DEFAULT NULL,  `brandname` varchar(30) DEFAULT NULL,  `itemname` varchar(20) DEFAULT NULL,  `phone_no` varchar(10) DEFAULT NULL,  `email_id` varchar(30) DEFAULT NULL,  `address` mediumtext CHARACTER SET latin1,  `Remark` mediumtext CHARACTER SET latin1,  PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
					ps=con.prepareStatement(sql7);
					ps.executeUpdate();
					
					String sql8="create table apikey(akey varchar(100))";
					ps=con.prepareStatement(sql8);
					ps.executeUpdate();
					
					String sql9="CREATE TABLE `qrinvoice` (  `invoiceid` bigint(20) DEFAULT NULL,  `itemid` int(11) DEFAULT NULL,  `itemname` varchar(20) DEFAULT NULL,  `price` varchar(11) DEFAULT NULL,  `quantity` varchar(11) DEFAULT NULL,  `amount` varchar(20) DEFAULT NULL,  `gst` int(11) DEFAULT NULL,  `gstamount` float DEFAULT NULL,  `total` double DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
					ps=con.prepareStatement(sql9);
					ps.executeUpdate();
					
					String sql10="CREATE TABLE `saleitem2` (  `id` int(11) DEFAULT NULL,  `qun` int(11) DEFAULT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
					ps=con.prepareStatement(sql10);
					ps.executeUpdate();
					
					JOptionPane.showMessageDialog(frame, "Table Created... ");
					con.close();
					 
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(frame, e.toString());
				}
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnLogin);
	}
}
