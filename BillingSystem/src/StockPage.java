import java.awt.*;
import java.awt.print.*;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JScrollPane;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JTable;
import javax.swing.JRadioButton;

import messageSend.ByEmail;
import net.proteanit.sql.DbUtils;

import javax.swing.table.DefaultTableModel;

import databaseConnect.ConnectDB;


public class StockPage implements Runnable{

	public JFrame frame;
	JLayeredPane layeredPane;
	private JTextField textField_2;
	private JTextField textField_5;
	private JTextArea textArea,textArea_1,textArea_2,textArea_3,Qty_item,textArea_6,gstarea,textArea_7,textArea_8,textArea_10,ratearea,hsnarea,textArea_4  ; 
	private JTextPane textPane;
	JPanel panel_1;
	private JLabel Price,lblQuantity_1, invoice,date,cgstrate,sgstrate,igstrate,Lamounttotal,setqun,home_date,home_time,lblItemid,text_itemid,lblinvoiceid,lblgst,lblgstamount,lbltotprice,lbltotquan;
	private JLabel label_0,label_1,label_2,label_3,label_4,label_5,label_7;
	private JComboBox comboBox,comboBox_1,quickshow,comboBox_2,comboBox_3,comboBox_4;
	JTextPane textPane_1;
	private JTextField txtinvoicecode;
	private JLabel lblinvoicecode;
	
	
	float totalprice,totalamount=0;
	int totalquantity,totalgst,numto=0,disc=0;
	private JSpinner igst,cgst,sgst;
	private JTextField cusnamefield;
	private JTextField cusphonefield;
	private JTable table;
	String whr="date";
	String erate,equn,ebrand,eitem;
	
	JRadioButton rbitemname,rbbrandname,rbDate,rb1item,rb1brand,rb1quantity;
	ButtonGroup bg,bg1;
	private JTable table_1;
	private JTable table_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table_3;
	private JTable table_4;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	
	int sno=0,forint=0,forint1=0;
	Double invoiceid,item_id;
	int[] qunarr=new int[100];
	float[] pricearr=new float[100];
	int totqun=0,totgst=0;
	float totprice=0;
	String[] sitem=new String[100];
	String[] sbrand=new String[100]; 
	
	private JTextField cusgstfield;
	private JTextField cusaddfield;
	
	private JPanel bar_item_detail,barcodeinvoicedetails;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StockPage window = new StockPage("Admin","","");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void switchpanel(JPanel panel){
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	String Name,id,pass;
	Connection con;
	private JTextField textField_itemid;
	private JTable table_5;
	
	public StockPage(String name,String Id,String Pass) {
		Name=name;
		id=Id;
		pass=Pass;
		Thread t,t1;
		
		t=new Thread(this);
		t.start();
		
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
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(StockPage.class.getResource("/img/simple.png")));
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(128, 128, 128));
		frame.setTitle("Stock Control Panel");
		frame.setBounds(-8, 1, 1400, 740);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		layeredPane = new JLayeredPane();
		layeredPane.setBorder(new TitledBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, new Color(240, 240, 240), new Color(255, 255, 255), new Color(105, 105, 105), new Color(160, 160, 160)), new LineBorder(new Color(180, 180, 180), 5)), "HOME TECH DESIGN", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 255, 255)));
		layeredPane.setBounds(0, 94, 1370, 607);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		layeredPane.add(panel, "name_84013684881100");
		panel.setLayout(null);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBackground(new Color(255, 255, 240));
		separator_3.setBounds(429, 284, 490, 12);
		panel.add(separator_3);
		
		JLabel lblNewLabel_6 = new JLabel("Your Shop Title");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Algerian", Font.PLAIN, 60));
		lblNewLabel_6.setForeground(new Color(250, 240, 230));
		lblNewLabel_6.setBounds(302, 216, 735, 80);
		panel.add(lblNewLabel_6);
		
		JLabel lblAllSportsWear = new JLabel("{ Description }");
		lblAllSportsWear.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllSportsWear.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblAllSportsWear.setForeground(new Color(255, 255, 255));
		lblAllSportsWear.setBounds(448, 297, 471, 36);
		panel.add(lblAllSportsWear);
		
		home_date = new JLabel("New label");
		home_date.setBounds(962, 40, 380, 31);
		panel.add(home_date);
		home_date.setForeground(Color.GREEN);
		home_date.setFont(new Font("Algerian", Font.PLAIN, 30));
		home_date.setHorizontalAlignment(SwingConstants.CENTER);
		
		home_time = new JLabel("New label");
		home_time.setBounds(0, 40, 261, 29);
		panel.add(home_time);
		home_time.setFont(new Font("Algerian", Font.PLAIN, 30));
		home_time.setHorizontalAlignment(SwingConstants.CENTER);
		home_time.setForeground(Color.GREEN);
		
		Calendar cal1=Calendar.getInstance();
		int dat=cal1.get(Calendar.DATE);
		int mon=cal1.get(Calendar.MONTH)+1;
		int yr=cal1.get(Calendar.YEAR);
		int day1=cal1.get(Calendar.DAY_OF_WEEK);
		String day;
		
		if(day1==1)
			day="Sunday";
		else if(day1==2)
			day="Monday";
		else if(day1==3)
			day="Tuesday";
		else if(day1==4)
			day="Wednesday";
		else if(day1==5)
			day="Thursday";
		else if(day1==6)
			day="Friday";
		else 
			day="Saturday";
		
		if(mon==1)
			home_date.setText(day+" , "+dat+" "+"JAN"+" "+yr);
		else if(mon==2)
			home_date.setText(day+" , "+dat+" "+"FEB"+" "+yr);
		else if(mon==3)
			home_date.setText(day+" , "+dat+" "+"MAR"+" "+yr);
		else if(mon==4)
			home_date.setText(day+" , "+dat+" "+"APR"+" "+yr);
		else if(mon==5)
			home_date.setText(day+" , "+dat+" "+"MAY"+" "+yr);
		else if(mon==6)
			home_date.setText(day+" , "+dat+" "+"JUNE"+" "+yr);
		else if(mon==7)
			home_date.setText(day+" , "+dat+" "+"JULY"+" "+yr);
		else if(mon==8)
			home_date.setText(day+" , "+dat+" "+"AUG"+" "+yr);
		else if(mon==9)
			home_date.setText(day+" , "+dat+" "+"SEP"+" "+yr);
		else if(mon==10)
			home_date.setText(day+" , "+dat+" "+"OCT"+" "+yr);
		else if(mon==11)
			home_date.setText(day+" , "+dat+" "+"NOV"+" "+yr);
		else if(mon==12)
			home_date.setText(day+" , "+dat+" "+"DEC"+" "+yr);
		
		JLabel lblTime = new JLabel("Time :");
		lblTime.setForeground(Color.YELLOW);
		lblTime.setFont(new Font("Algerian", Font.PLAIN, 25));
		lblTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblTime.setBounds(53, 3, 154, 30);
		panel.add(lblTime);
		
		JLabel lblNewLabel_11 = new JLabel("Date :");
		lblNewLabel_11.setForeground(Color.YELLOW);
		lblNewLabel_11.setFont(new Font("Algerian", Font.PLAIN, 25));
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setBounds(1071, 5, 153, 27);
		panel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon(StockPage.class.getResource("/img/background_20.wide.jpg")));
		lblNewLabel_3.setBounds(0, 0, 1348, 580);
		panel.add(lblNewLabel_3);
		
		/*
		JLabel lblQuickShow = new JLabel("Quick View");
		lblQuickShow.setForeground(new Color(128, 0, 0));
		lblQuickShow.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblQuickShow.setBounds(128, 90, 95, 25);
		panel.add(lblQuickShow);
		
		quickshow = new JComboBox();
		quickshow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (quickshow.getSelectedIndex()!=0) {	          
					
				}
			}
		});		quickshow.setBounds(291, 90, 164, 25);
		panel.add(quickshow);
		quickshow.addItem("Today");
		quickshow.addItem("Weekly");
		quickshow.addItem("Monthly");
		quickshow.addItem("Yearly");
		
		JLabel lblGrossSale = new JLabel("Gross Sale");
		lblGrossSale.setForeground(Color.RED);
		lblGrossSale.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGrossSale.setBounds(128, 144, 76, 25);
		panel.add(lblGrossSale);
		
		JLabel lblAmountReceived = new JLabel("Amount Received");
		lblAmountReceived.setForeground(new Color(0, 0, 255));
		lblAmountReceived.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAmountReceived.setBounds(225, 144, 123, 25);
		panel.add(lblAmountReceived);
		
		JLabel lblAmountDue = new JLabel("Amount Due");
		lblAmountDue.setForeground(new Color(128, 0, 128));
		lblAmountDue.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAmountDue.setBounds(369, 144, 103, 25);
		panel.add(lblAmountDue);
		
		JLabel lblRs = new JLabel("Rs.");
		lblRs.setBounds(128, 177, 21, 19);
		panel.add(lblRs);
		
		JLabel label_1 = new JLabel("Rs.");
		label_1.setBounds(233, 177, 21, 19);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Rs.");
		label_2.setBounds(366, 177, 21, 19);
		panel.add(label_2);
		
		JLabel grosssale = new JLabel("100000");
		grosssale.setBounds(149, 177, 66, 16);
		panel.add(grosssale);
		
		JLabel amountreceived = new JLabel("100000");
		amountreceived.setBounds(257, 177, 85, 16);
		panel.add(amountreceived);
		
		JLabel amountdue = new JLabel("100000");
		amountdue.setBounds(389, 177, 85, 16);
		panel.add(amountdue);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(128, 130, 331, 4);
		panel.add(separator_3);
		
		Box horizontalBox_6 = Box.createHorizontalBox();
		horizontalBox_6.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		horizontalBox_6.setBounds(80, 61, 419, 169);
		panel.add(horizontalBox_6);
		
		*/
		
		JPanel panel1 = new JPanel();
		layeredPane.add(panel1, "name_82436627474300");
		panel1.setLayout(null);
		
		JLabel lblPanel = new JLabel("Search By :");
		lblPanel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPanel.setBounds(10, 16, 101, 36);
		panel1.add(lblPanel);
		
		bg=new ButtonGroup();
		
		rbitemname = new JRadioButton("Item Name");
		rbitemname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					PreparedStatement ps;
					ResultSet rs;
					comboBox_2.removeAllItems();
					whr="itemname";
					
					String str="select DISTINCT itemname from itempurchase";
					ps=con.prepareStatement(str);
					rs=ps.executeQuery();
					while(rs.next()){
						comboBox_2.addItem(rs.getString(1));
					}
					
				}
				catch(Exception e){}
			}
		});
		rbitemname.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rbitemname.setBounds(120, 23, 101, 23);
		panel1.add(rbitemname);
		bg.add(rbitemname);
		
		rbbrandname = new JRadioButton("Brand Name");
		rbbrandname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				
					PreparedStatement ps;
					ResultSet rs;
					comboBox_2.removeAllItems();
					whr="brandname";
					
					String str="select DISTINCT brandname from itempurchase";
					ps=con.prepareStatement(str);
					rs=ps.executeQuery();
					while(rs.next()){
						comboBox_2.addItem(rs.getString(1));
					}
					con.close();
				}
				catch(Exception e){}
			}
		});
		rbbrandname.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rbbrandname.setBounds(223, 23, 109, 23);
		panel1.add(rbbrandname);
		bg.add(rbbrandname);
		
		rbDate = new JRadioButton("Date");
		rbDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					//Connection con;
					PreparedStatement ps;
					ResultSet rs;
					comboBox_2.removeAllItems();
					whr="date";
					
					
					String str="select DISTINCT date from itempurchase";
					ps=con.prepareStatement(str);
					rs=ps.executeQuery();
					while(rs.next()){
						comboBox_2.addItem(rs.getString(1));
					}
					//con.close();
				}
				catch(Exception e){}
			}
		});
		rbDate.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rbDate.setBounds(334, 23, 66, 23);
		panel1.add(rbDate);
		bg.add(rbDate);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 73, 1348, 507);
		panel1.add(scrollPane_2);
		
		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane_2.setViewportView(table);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(whr.equalsIgnoreCase("itemname")){
					try{
						//Connection con;
						PreparedStatement ps;
						ResultSet rs;
						
						String str="select * from itempurchase where itemname=?";
						ps=con.prepareStatement(str);
						ps.setString(1, comboBox_2.getSelectedItem().toString());
						rs=ps.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						//con.close();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(frame, e.toString());
					}
				}
				else if(whr.equalsIgnoreCase("brandname")){
					try{
						//Connection con;
						PreparedStatement ps;
						ResultSet rs;
						
						String str="select * from itempurchase where brandname=?";
						ps=con.prepareStatement(str);
						ps.setString(1, comboBox_2.getSelectedItem().toString());
						rs=ps.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						//con.close();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(frame, e.toString());
					}
				}
				else if(whr.equalsIgnoreCase("date")){
					try{
						//Connection con;
						PreparedStatement ps;
						ResultSet rs;
						
						String str="select * from itempurchase where date=? order by date DESC";
						ps=con.prepareStatement(str);
						ps.setString(1, comboBox_2.getSelectedItem().toString());
						rs=ps.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						//con.close();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(frame, e.toString());
					}
				}
			}
		});
		
		comboBox_2 = new JComboBox();
		comboBox_2.setBounds(422, 16, 201, 36);
		panel1.add(comboBox_2);
		
		JLabel lblItemPurchaseHistory = new JLabel("Item Purchase History");
		lblItemPurchaseHistory.setForeground(Color.BLUE);
		lblItemPurchaseHistory.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblItemPurchaseHistory.setBounds(1057, 11, 281, 36);
		panel1.add(lblItemPurchaseHistory);
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnSearch.setBounds(639, 21, 89, 23);
		panel1.add(btnSearch);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(1052, 57, 275, 2);
		panel1.add(separator_8);
		
		JPanel panel2 = new JPanel();
		layeredPane.add(panel2, "name_82443075050800");
		panel2.setLayout(null);
		
		JLabel label = new JLabel("Search By :");
		label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label.setBounds(10, 12, 101, 36);
		panel2.add(label);
		
		bg1=new ButtonGroup();
		
		rb1item = new JRadioButton("Item Name");
		rb1item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					//Connection con;
					PreparedStatement ps;
					ResultSet rs;
					comboBox_3.removeAllItems();
					whr="itemname";
					
					String str="select DISTINCT itemname from item";
					ps=con.prepareStatement(str);
					rs=ps.executeQuery();
					while(rs.next()){
						comboBox_3.addItem(rs.getString(1));
					}
					//con.close();
				}
				catch(Exception e){}
			}
		});
		rb1item.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rb1item.setBounds(120, 21, 101, 23);
		panel2.add(rb1item);
		bg1.add(rb1item);
		
		rb1brand = new JRadioButton("Brand Name");
		rb1brand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					//Connection con;
					PreparedStatement ps;
					ResultSet rs;
					comboBox_3.removeAllItems();
					whr="brandname";
					
					String str="select DISTINCT brandname from itembrand";
					ps=con.prepareStatement(str);
					rs=ps.executeQuery();
					while(rs.next()){
						comboBox_3.addItem(rs.getString(1));
					}
					//con.close();
				}
				catch(Exception e){}
			}
		});
		rb1brand.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rb1brand.setBounds(223, 21, 109, 23);
		panel2.add(rb1brand);
		bg1.add(rb1brand);
		
		rb1quantity = new JRadioButton("Quantity");
		rb1quantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				whr="quantity";
				comboBox_3.removeAllItems();
				comboBox_3.addItem("Less Than <5");
				comboBox_3.addItem("Less Than <10");
				comboBox_3.addItem("Less Than <15");
				comboBox_3.addItem("Less Than <20");
				comboBox_3.addItem("Less Than <25");
				comboBox_3.addItem("Less Than <30");
				comboBox_3.addItem("Less Than <35");
				comboBox_3.addItem("Less Than <40");
				comboBox_3.addItem("Less Than <45");
				comboBox_3.addItem("Less Than <50");
				comboBox_3.addItem("Less Than <60");
				comboBox_3.addItem("Less Than <70");
				comboBox_3.addItem("Less Than <80");
				comboBox_3.addItem("Less Than <90");
				comboBox_3.addItem("Less Than <100");
				comboBox_3.addItem("Less Than <200");
				comboBox_3.addItem("Less Than <300");
				comboBox_3.addItem("Less Than <400");
				comboBox_3.addItem("Less Than <500");
				comboBox_3.addItem("Less Than <1000");
				comboBox_3.addItem("Less Than <1500");
				comboBox_3.addItem("Less Than <2000");
			}
		});
		rb1quantity.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		rb1quantity.setBounds(334, 21, 101, 23);
		panel2.add(rb1quantity);
		bg1.add(rb1quantity);
		
		comboBox_3 = new JComboBox();
		comboBox_3.setBounds(454, 14, 201, 36);
		panel2.add(comboBox_3);
		
		JLabel lblCurrentItemStock = new JLabel("Current Item Stock");
		lblCurrentItemStock.setForeground(Color.BLUE);
		lblCurrentItemStock.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblCurrentItemStock.setBounds(1057, 12, 281, 36);
		panel2.add(lblCurrentItemStock);
		
		JButton button = new JButton("Search");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(whr.equalsIgnoreCase("itemname")){
					try{
						
						PreparedStatement ps;
						ResultSet rs;
						
						String str="select * from item where itemname=?";
						ps=con.prepareStatement(str);
						ps.setString(1, comboBox_3.getSelectedItem().toString());
						rs=ps.executeQuery();
						table_1.setModel(DbUtils.resultSetToTableModel(rs));
						
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(frame, e.toString());
					}
				}
				else if(whr.equalsIgnoreCase("brandname")){
					try{
						//Connection con;
						PreparedStatement ps;
						ResultSet rs;
						
						String str="select * from item where brandname=?";
						ps=con.prepareStatement(str);
						ps.setString(1, comboBox_3.getSelectedItem().toString());
						rs=ps.executeQuery();
						table_1.setModel(DbUtils.resultSetToTableModel(rs));
						//con.close();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(frame, e.toString());
					}
				}
				else if(whr.equalsIgnoreCase("quantity")){
					try{
						//Connection con;
						PreparedStatement ps;
						ResultSet rs;
						
						String qunt=comboBox_3.getSelectedItem().toString();
						int qun1= Integer.parseInt(qunt.substring(qunt.indexOf("<")+1));
	
						String str="select * from item where itemquantity<=?";
						ps=con.prepareStatement(str);
						ps.setInt(1, qun1);
						rs=ps.executeQuery();
						table_1.setModel(DbUtils.resultSetToTableModel(rs));
						//con.close();
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(frame, e.toString());
					}
				}
			
			}
		});
		button.setFont(new Font("Times New Roman", Font.BOLD, 15));
		button.setBounds(681, 20, 89, 23);
		panel2.add(button);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 67, 1348, 513);
		panel2.add(scrollPane_3);
		
		table_1 = new JTable();
		table_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		table_1.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		scrollPane_3.setViewportView(table_1);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setBounds(1057, 53, 237, 8);
		panel2.add(separator_9);
		
		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(255, 228, 181));
		layeredPane.add(panel3, "name_82447065160300");
		panel3.setLayout(null);
		
		
		JButton btnPrint = new JButton("(Sell / Print / Save) Bill ");
		btnPrint.setFont(new Font("Times New Roman", Font.BOLD, 11));
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int a=JOptionPane.showConfirmDialog(frame, "If you Want to Print or Save It to PDF. Press Ok Button, If Not Press NO");
				if(a==0){
					PrinterJob job=PrinterJob.getPrinterJob();
					job.setJobName("Print Data");
					
					job.setPrintable(new Printable(){
						public int print(Graphics pg,PageFormat pf,int pageNum){
								pf.setOrientation(PageFormat.LANDSCAPE);
							if(pageNum>0)
								return Printable.NO_SUCH_PAGE;
						
						
							java.awt.Graphics2D g2=(java.awt.Graphics2D)pg;
							g2.translate(pf.getImageableX(),pf.getImageableY());
							g2.scale(1.27,1.345);
							
							panel_1.paint(g2);
							
							return Printable.PAGE_EXISTS;
						}
						
					});
					
						boolean ok=job.printDialog();
						if(ok){
							try{
								job.print();
							}
							catch(PrinterException es){
								JOptionPane.showMessageDialog(panel3, "Connection Problem in Printer");
							}
							catch(Exception es){
								JOptionPane.showMessageDialog(panel3, "Something went Wrong....");
							}
						}
					}
				
				
				String dat=date.getText().toString();
				//Connection con;
				PreparedStatement ps,ps1,ps2;
				try{
					
					String str="select * from saleitem";
					ps=con.prepareStatement(str);
					ResultSet rs=ps.executeQuery();
					int CGST=(int) cgst.getValue();
					int SGST=(int) sgst.getValue();
					int IGST=(int) igst.getValue();
					try{
						while(rs.next()){
							String str2="update item set itemquantity=itemquantity-? where itemname=? and brandname=?";
							ps2=con.prepareStatement(str2);
							ps2.setInt(1, rs.getInt(3));
							ps2.setString(2, rs.getString(1));
							ps2.setString(3, rs.getString(2));
							ps2.executeUpdate();
						}
							String str1="insert into invoice values(invoiceid,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							ps1=con.prepareStatement(str1);
							ps1.setString(1, textArea.getText().toString().replace('\n',','));
							ps1.setString(2, Qty_item.getText().toString().replace('\n',','));
							ps1.setString(3, textArea_1.getText().toString().replace('\n',','));
							ps1.setString(4, ratearea.getText().toString().replace('\n',','));
							ps1.setInt(5, totalquantity);
							ps1.setInt(6, totalgst);
							ps1.setString(7, hsnarea.getText().toString().replace('\n',','));
							ps1.setDouble(8, totalprice);
							ps1.setString(9, textArea_8.getText().toString().replace('\n',','));
							ps1.setString(10, dat);
							ps1.setInt(11, CGST);
							ps1.setInt(12, SGST);
							ps1.setInt(13, IGST);
							ps1.executeUpdate();
					}
					catch(SQLException ed){
						JOptionPane.showMessageDialog(frame, "Item Quantity Is greater to our Stock\nPlease Check Stock First");
					}
					String last="delete from saleitem";
					ps2=con.prepareStatement(last);
					ps2.executeUpdate();
					
					JOptionPane.showMessageDialog(frame, "Stock Updated...");
				//con.close();
				}
				catch(Exception es){
					JOptionPane.showMessageDialog(frame, es.toString());
				}
				
			}
		});
		btnPrint.setBounds(596, 508, 166, 44);
		panel3.add(btnPrint);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(457, 45, 197, 8);
		panel3.add(separator_2);
		
		JLabel lblItemName = new JLabel("Brand");
		lblItemName.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblItemName.setBounds(77, 220, 53, 14);
		panel3.add(lblItemName);
		
		JLabel lblTotal = new JLabel("Total Price                :");
		lblTotal.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblTotal.setBounds(164, 455, 176, 34);
		panel3.add(lblTotal);
		
		JLabel lblBillPanel = new JLabel("Bill Panel");
		lblBillPanel.setHorizontalAlignment(SwingConstants.CENTER);
		lblBillPanel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 40));
		lblBillPanel.setBounds(447, 7, 219, 34);
		panel3.add(lblBillPanel);
		
		JLabel lblItemCategory = new JLabel("Select Item's   :");
		lblItemCategory.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblItemCategory.setBounds(43, 120, 115, 34);
		panel3.add(lblItemCategory);
		
		JLabel lblPrize = new JLabel("Prize / item   :");
		lblPrize.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblPrize.setBounds(43, 165, 115, 34);
		panel3.add(lblPrize);
		
		JLabel lblQuantity = new JLabel("Total Quantity           :");
		lblQuantity.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblQuantity.setBounds(164, 417, 176, 34);
		panel3.add(lblQuantity);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frame.repaint();
				textArea_1.setText("");
				DecimalFormat df=new DecimalFormat("##.##");
				
				int cgst1=(int) cgst.getValue();
				int sgst1=(int) sgst.getValue();
				int igst1=(int) igst.getValue();
				totalgst=cgst1+sgst1+igst1;
				
				erate=textArea_3.getText().toString().trim();
				equn=textArea_10.getText().toString().trim();
				eitem=textArea_2.getText().toString().trim();
				ebrand=textArea_4.getText().toString().trim();
				
				char c[]=erate.toCharArray();
				char c1[]=equn.toCharArray();
				char c2[]=eitem.toCharArray();
				char c3[]=ebrand.toCharArray();
				
				if(c[c.length-1]!='\n'){
					erate+="\n";
				}
				if(c1[c1.length-1]!='\n'){
					equn+="\n";
				}
				if(c2[c2.length-1]!='\n'){
					eitem+="\n";
				}
				if(c3[c3.length-1]!='\n'){
					ebrand+="\n";
				}
			
				char ch[]=erate.toCharArray();
				char ch1[]=equn.toCharArray();
				char ch2[]=eitem.toCharArray();
				char ch3[]=ebrand.toCharArray();
				
				int g=-1;
				for(int i=0;i<ch.length;i++)
				{
					if(ch[i]=='\n'){
						pricearr[forint]=Float.parseFloat(erate.substring(g+1, i));
						++forint;
						g=i;
					}
				}
				forint=0;
				g=-1;
				for(int i=0;i<ch1.length;i++)
				{
					if(ch1[i]=='\n'){
						qunarr[forint]=Integer.parseInt(equn.substring(g+1, i));
						totqun+=qunarr[forint];
						++forint;
						g=i;
					}
				}
				
				forint=0;
				g=-1;
				for(int i=0;i<ch2.length;i++)
				{
					if(ch2[i]=='\n'){
						sitem[forint]=eitem.substring(g+1, i);
						++forint;
						g=i;
					}
				}
				
				forint=0;
				g=-1;
				for(int i=0;i<ch3.length;i++)
				{
					if(ch3[i]=='\n'){
						sbrand[forint]=ebrand.substring(g+1, i);
						++forint;
						g=i;
					}
				}
				
				
				        // Store Data into saleitem table
				
				//Connection con;
				PreparedStatement ps,ps1,ps2;
				try{
					
					String last="delete from saleitem";
					ps=con.prepareStatement(last);
					ps.executeUpdate();
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(frame, e);
				}
				for(int i=0;i<forint;i++)
				{
					String litem=sitem[i].trim();
					String lbrand=sbrand[i].trim();
					int lqun=qunarr[i];
					try{
						
						String str1="select * from saleitem where itemname=? and brandname=?";
						ps2=con.prepareStatement(str1);
						ps2.setString(1, litem);
						ps2.setString(2, lbrand);
						ResultSet rs1=ps2.executeQuery();
						if(rs1.next()){
							String sr="update saleitem set qun=qun+? where itemname=? and brandname=?";
							ps1=con.prepareStatement(sr);
							ps1.setInt(1,lqun);
							ps1.setString(2, litem);
							ps1.setString(3, lbrand);
							ps1.executeUpdate();
						}
						else{
							String sr="insert into saleitem values(?,?,?)";
							ps1=con.prepareStatement(sr);
							ps1.setString(1, litem);
							ps1.setString(2, lbrand);
							ps1.setInt(3, lqun);
							ps1.executeUpdate();
						}
					}
					catch(Exception e){
						JOptionPane.showMessageDialog(frame, "Error..."+e);
					}
				}
				
				String amo="";
				for(int i=0;i<forint;i++)
				{
					float totp=(float)pricearr[i]*qunarr[i];
					totprice+=totp;
					amo=textArea_1.getText().toString();
					if(amo.equals(""))
						textArea_1.setText(df.format(totp));
					else if(totp==0)
						textArea_1.setText(amo+"\n");
					else
						textArea_1.setText(amo+"\n"+df.format(totp));
					
					totp=0;
				}
				totalamount=totprice;
				forint=0;
				g=-1;
				
				textField_2.setText(" "+totqun);
				
				float cgstprice=totprice*((float)cgst1/100);
				cgstrate.setText(""+df.format(cgstprice));
				
				float sgstprice=totprice*((float)sgst1/100);
				sgstrate.setText(""+df.format(sgstprice));
				
				float igstprice=totprice*((float)igst1/100);
				igstrate.setText(""+df.format(igstprice));
				
				totprice+=cgstprice+sgstprice+igstprice;
				
				textField_5.setText(" "+df.format(totprice));
				
				totalquantity=totqun;
				totalprice=totprice;
				
				totprice=0;
				totgst=0;
				totqun=0;
			}
		});
		btnCalculate.setBounds(141, 508, 154, 44);
		panel3.add(btnCalculate);
		
		textField_2 = new JTextField();
		textField_2.setBounds(368, 417, 211, 34);
		panel3.add(textField_2);
		textField_2.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(368, 457, 213, 34);
		panel3.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnPreviewReceipt = new JButton("Preview Receipt");
		btnPreviewReceipt.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnPreviewReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ratearea.setText(textArea_3.getText().toString());
				Qty_item.setText(""+textArea_10.getText().toString());
				gstarea.setText("CGST  "+cgst.getValue().toString()+"%\nSGST  "+sgst.getValue().toString()+"%\nIGST  "+igst.getValue().toString()+"%");
				textArea.setText(textArea_2.getText());
				
				DecimalFormat df=new DecimalFormat("##.##");
				
				Price.setText("‎₹‎ "+df.format(totalprice));
				Lamounttotal.setText(""+totalamount);
				setqun.setText(textField_2.getText().toString());
				textArea_8.setText(cusnamefield.getText().toString()+"\n"+cusaddfield.getText().toString()+"\n"+cusphonefield.getText().toString()+"\nGSTIN : "+cusgstfield.getText().toString());
				
			}
		});
		btnPreviewReceipt.setBounds(358, 508, 176, 44);
		panel3.add(btnPreviewReceipt);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(857, 0, 481, 580);
		panel3.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Your Shop Name");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(10, 11, 231, 25);
		panel_1.add(lblNewLabel_2);
		
		JTextPane txtpnAddress = new JTextPane();
		txtpnAddress.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		txtpnAddress.setText("Shop Address");
		txtpnAddress.setBounds(40, 40, 181, 30);
		panel_1.add(txtpnAddress);
		
		JLabel lblDate = new JLabel("Date       :");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDate.setBounds(297, 113, 64, 20);
		panel_1.add(lblDate);
		
		invoice = new JLabel("New label");
		invoice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		invoice.setBounds(370, 147, 68, 14);
		panel_1.add(invoice);
		addinvoiceid();
		
		date = new JLabel("20/07/2020");
		date.setFont(new Font("Tahoma", Font.PLAIN, 12));
		date.setBounds(369, 116, 87, 16);
		panel_1.add(date);

		date.setText(dat+"-"+mon+"-"+yr);
		
		JLabel Invoice_1 = new JLabel("Invoice #  :");
		Invoice_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		Invoice_1.setBounds(296, 147, 68, 14);
		panel_1.add(Invoice_1);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblAmount.setBounds(405, 194, 55, 20);
		panel_1.add(lblAmount);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblDescription.setBounds(90, 196, 87, 18);
		panel_1.add(lblDescription);
		
		JLabel lblTotal_1 = new JLabel("Grand Total ");
		lblTotal_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTotal_1.setBounds(248, 464, 85, 20);
		panel_1.add(lblTotal_1);
		
		textArea = new JTextArea();
		textArea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textArea.setBounds(75, 221, 114, 167);
		panel_1.add(textArea);
		
		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textArea_1.setBounds(395, 221, 56, 167);
		panel_1.add(textArea_1);
		
		Price = new JLabel("Price");
		Price.setHorizontalAlignment(SwingConstants.RIGHT);
		Price.setFont(new Font("Times New Roman", Font.BOLD, 15));
		Price.setBounds(347, 464, 103, 20);
		panel_1.add(Price);
		
		JLabel lblNewLabel_10 = new JLabel("GSTIN - Your GST No.");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblNewLabel_10.setBounds(40, 69, 181, 17);
		panel_1.add(lblNewLabel_10);
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(5, 90, 466, 2);
		panel_1.add(separator_1);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setBounds(5, 186, 466, 2);
		panel_1.add(separator_4);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setOrientation(SwingConstants.VERTICAL);
		separator_5.setBounds(275, 90, 8, 96);
		panel_1.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(5, 90, 8, 96);
		panel_1.add(separator_6);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(470, 90, 8, 98);
		panel_1.add(separator_7);
		
		JLabel lblSignature = new JLabel("Authorized  Signature");
		lblSignature.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblSignature.setBounds(334, 557, 140, 20);
		panel_1.add(lblSignature);
		
		JSeparator separator_15 = new JSeparator();
		separator_15.setBounds(368, 557, 103, 2);
		panel_1.add(separator_15);
		
		Qty_item = new JTextArea();
		Qty_item.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		Qty_item.setBounds(275, 221, 53, 167);
		panel_1.add(Qty_item);
		
		textArea_7 = new JTextArea();
		textArea_7.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		textArea_7.setBounds(42, 222, 18, 168);
		panel_1.add(textArea_7);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setOrientation(SwingConstants.VERTICAL);
		separator_13.setBounds(66, 193, 3, 198);
		panel_1.add(separator_13);
		
		JSeparator separator_14 = new JSeparator();
		separator_14.setOrientation(SwingConstants.VERTICAL);
		separator_14.setBounds(266, 193, 3, 212);
		panel_1.add(separator_14);
		
		JSeparator separator_16 = new JSeparator();
		separator_16.setBounds(40, 391, 411, 2);
		panel_1.add(separator_16);
		
		gstarea = new JTextArea();
		gstarea.setText("CGST\r\nSGST \r\n IGST  ");
		gstarea.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		gstarea.setBounds(238, 409, 90, 48);
		panel_1.add(gstarea);
		
		cgstrate = new JLabel("CGST");
		cgstrate.setHorizontalAlignment(SwingConstants.RIGHT);
		cgstrate.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		cgstrate.setBounds(380, 409, 64, 18);
		panel_1.add(cgstrate);
		
		sgstrate = new JLabel("SGST");
		sgstrate.setHorizontalAlignment(SwingConstants.RIGHT);
		sgstrate.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		sgstrate.setBounds(380, 425, 64, 20);
		panel_1.add(sgstrate);
		
		igstrate = new JLabel("IGST");
		igstrate.setHorizontalAlignment(SwingConstants.RIGHT);
		igstrate.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		igstrate.setBounds(385, 442, 59, 20);
		panel_1.add(igstrate);
		
		JLabel lblQtyitem = new JLabel("Qty /item");
		lblQtyitem.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblQtyitem.setBounds(271, 195, 57, 18);
		panel_1.add(lblQtyitem);
		
		JLabel lblSno = new JLabel("S.No");
		lblSno.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		lblSno.setBounds(41, 196, 31, 18);
		panel_1.add(lblSno);
		
		JSeparator separator_17 = new JSeparator();
		separator_17.setOrientation(SwingConstants.VERTICAL);
		separator_17.setBounds(387, 193, 8, 212);
		panel_1.add(separator_17);
		
		JLabel lblRate = new JLabel("Rate");
		lblRate.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblRate.setBounds(347, 197, 40, 14);
		panel_1.add(lblRate);
		
		JSeparator separator_18 = new JSeparator();
		separator_18.setOrientation(SwingConstants.VERTICAL);
		separator_18.setBounds(331, 193, 6, 293);
		panel_1.add(separator_18);
		
		JLabel lblHsnCode = new JLabel("HSN Code");
		lblHsnCode.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		lblHsnCode.setBounds(207, 196, 56, 18);
		panel_1.add(lblHsnCode);
		
		JSeparator separator_19 = new JSeparator();
		separator_19.setOrientation(SwingConstants.VERTICAL);
		separator_19.setBounds(199, 193, 5, 198);
		panel_1.add(separator_19);
		
		JSeparator separator_20 = new JSeparator();
		separator_20.setBounds(40, 215, 411, 2);
		panel_1.add(separator_20);
		
		JSeparator separator_21 = new JSeparator();
		separator_21.setBounds(40, 460, 411, 2);
		panel_1.add(separator_21);
		
		hsnarea = new JTextArea();
		hsnarea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		hsnarea.setBounds(204, 221, 58, 167);
		panel_1.add(hsnarea);
		
		ratearea = new JTextArea();
		ratearea.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		ratearea.setBounds(335, 221, 51, 165);
		panel_1.add(ratearea);
		
		JSeparator separator_22 = new JSeparator();
		separator_22.setBounds(40, 405, 411, 3);
		panel_1.add(separator_22);
		
		JLabel lblTotal_2 = new JLabel("Total  ");
		lblTotal_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTotal_2.setBounds(220, 391, 40, 14);
		panel_1.add(lblTotal_2);
		
		Lamounttotal = new JLabel("Price");
		Lamounttotal.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		Lamounttotal.setHorizontalAlignment(SwingConstants.RIGHT);
		Lamounttotal.setBounds(396, 391, 46, 14);
		panel_1.add(Lamounttotal);
		
		JLabel lblBuyersDetails = new JLabel("Buyer's Details :");
		lblBuyersDetails.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblBuyersDetails.setBounds(20, 95, 103, 14);
		panel_1.add(lblBuyersDetails);
		
		JSeparator separator_23 = new JSeparator();
		separator_23.setBounds(80, 38, 78, 3);
		panel_1.add(separator_23);
		
		textArea_8 = new JTextArea();
		textArea_8.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		textArea_8.setBounds(18, 111, 253, 72);
		panel_1.add(textArea_8);
		
		JLabel lblInvoice = new JLabel("INVOICE");
		lblInvoice.setBounds(297, 26, 156, 37);
		panel_1.add(lblInvoice);
		lblInvoice.setFont(new Font("Castellar", Font.PLAIN, 30));
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		horizontalBox.setBounds(280, 20, 175, 48);
		panel_1.add(horizontalBox);
		
		Box horizontalBox_8 = Box.createHorizontalBox();
		horizontalBox_8.setBounds(6, 6, 244, 82);
		panel_1.add(horizontalBox_8);
		horizontalBox_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		setqun = new JLabel("Quantity");
		setqun.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		setqun.setHorizontalAlignment(SwingConstants.RIGHT);
		setqun.setBounds(275, 392, 50, 13);
		panel_1.add(setqun);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setBounds(38, 191, 415, 297);
		panel_1.add(horizontalBox_2);
		horizontalBox_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedIndex()!=0){
					//Connection con;
					PreparedStatement ps;
					try{
						
						String str="select * from item where itemname=? and brandname=?";
						ps=con.prepareStatement(str);
						ps.setString(1, comboBox.getSelectedItem().toString());
						ps.setString(2, comboBox_1.getSelectedItem().toString());
						ResultSet rs=ps.executeQuery();
						while(rs.next()){
							lblQuantity_1.setText("Quantity of this Item = "+rs.getInt(7));
						}
						//con.close();
					}
					catch(Exception es){}
					
				}
			}
		});
		comboBox.setBounds(168, 120, 150, 34);
		panel3.add(comboBox);
		comboBox.addItem("Select One");
		
		comboBox_1 = new JComboBox();
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_1.getSelectedIndex()!=0){
					comboBox.removeAllItems();
					comboBox.repaint();
					comboBox.addItem("Select One");
					additembrand(comboBox_1.getSelectedItem().toString());
				}
			}
		});
		comboBox_1.setBounds(168, 80, 151, 34);
		panel3.add(comboBox_1);
		comboBox_1.addItem("Select One");
		
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinner.setBounds(332, 120, 46, 34);
		panel3.add(spinner);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(new Float(0), null, null, new Float(1)));
		spinner_2.setBounds(168, 165, 78, 29);
		panel3.add(spinner_2);
		
		JLabel lblX = new JLabel("X");
		lblX.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblX.setForeground(Color.DARK_GRAY);
		lblX.setBounds(317, 119, 21, 34);
		panel3.add(lblX);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		horizontalBox_3.setBounds(129, 410, 487, 88);
		panel3.add(horizontalBox_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(149, 242, 129, 138);
		panel3.add(scrollPane);
		
		textArea_2 = new JTextArea();
		scrollPane.setViewportView(textArea_2);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(342, 242, 90, 138);
		panel3.add(scrollPane_1);
		
		textArea_3 = new JTextArea();
		scrollPane_1.setViewportView(textArea_3);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(434, 242, 90, 138);
		panel3.add(scrollPane_7);
		
		textPane = new JTextPane();
		scrollPane_7.setViewportView(textPane);
		
		JScrollPane scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(280, 243, 60, 137);
		panel3.add(scrollPane_10);
		
		textArea_10 = new JTextArea();
		scrollPane_10.setViewportView(textArea_10);
		
		JScrollPane scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(56, 242, 90, 138);
		panel3.add(scrollPane_11);
		
		textArea_4 = new JTextArea();
		scrollPane_11.setViewportView(textArea_4);
		
		Box horizontalBox_4 = Box.createHorizontalBox();
		horizontalBox_4.setEnabled(false);
		horizontalBox_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		horizontalBox_4.setBounds(20, 215, 548, 186);
		panel3.add(horizontalBox_4);
		
		JLabel lblNewLabel_4 = new JLabel("Select Brand   :");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.ITALIC, 18));
		lblNewLabel_4.setBounds(43, 80, 115, 29);
		panel3.add(lblNewLabel_4);
		
		JLabel lblNoOfItems = new JLabel("(No. Of Item's)");
		lblNoOfItems.setBounds(385, 132, 90, 14);
		panel3.add(lblNoOfItems);
		
	
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PreparedStatement ps,ps1,ps2;
				String brand=comboBox_1.getSelectedItem().toString();
				String item=comboBox.getSelectedItem().toString();
				float price=(float)spinner_2.getValue();
				int num=(int)spinner.getValue();
				
				try{
					
					String str="select * from item where itemname=? and brandname=?";
					ps=con.prepareStatement(str);
					ps.setString(1, item);
					ps.setString(2, brand);
					ResultSet rs=ps.executeQuery();
					if(rs.next()){
						if(num<=rs.getInt(8)){

							int gst=rs.getInt(9);
							
							String Sno=textArea_7.getText().toString();
							++sno;
							textArea_7.setText(Sno+sno+"\n");
							
							String n=textArea_10.getText();
							textArea_10.setText(num+"\n"+n);
							
							String rst=textArea_2.getText();
							String pr=textArea_3.getText().toString();
							String gs=textPane.getText();
							String amo=textArea_1.getText();
							String br=textArea_4.getText().toString();
							
							float np=num*price;
							DecimalFormat df=new DecimalFormat("##.##");
							
							textArea_4.setText(""+brand+"\n"+br);
							textArea_2.setText(" "+item+"\n"+rst);
							textArea_3.setText(df.format(price)+"\n"+pr);
							textPane.setText("GST "+gst+"%\n"+gs);
							comboBox.removeItem(item);
							
						}
						else{
							JOptionPane.showMessageDialog(frame, "Quantity is Greater than to your Stock...");
						}
					}
					//con.close();
				}
				catch(Exception es){
					JOptionPane.showMessageDialog(frame, es.toString()+" add");
				}
			}
		});
		btnAdd.setBounds(291, 168, 113, 31);
		panel3.add(btnAdd);
		
		lblQuantity_1 = new JLabel("");
		lblQuantity_1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblQuantity_1.setBounds(329, 90, 146, 20);
		panel3.add(lblQuantity_1);
		
		Box horizontalBox_5 = Box.createHorizontalBox();
		horizontalBox_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		horizontalBox_5.setBounds(10, 64, 471, 145);
		panel3.add(horizontalBox_5);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sno=0;
				forint=0;
				totalprice=0;
				totalquantity=0;
				totalgst=0;
				textArea_2.setText("");
				textField_2.setText("");
				textField_4.setText("");
				textField_5.setText("");
				textArea_1.setText("");
				textArea_3.setText("");
				textArea_4.setText("");
				Qty_item.setText("");
				textArea.setText("");
				textArea_7.setText("");
				textPane.setText("");
				Price.setText("");
				textArea_10.setText("");
				ratearea.setText("");
				cusgstfield.setText("");
				cusnamefield.setText("");
				cusphonefield.setText("");
				cusaddfield.setText("");
				addinvoiceid();
				
				try{
					//Connection con;
					PreparedStatement ps;
					
					String last="delete from saleitem";
					ps=con.prepareStatement(last);
					ps.executeUpdate();
					//con.close();
				}
				catch(Exception es){
					JOptionPane.showMessageDialog(frame, es.toString());
				}
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnReset.setBounds(628, 429, 166, 30);
		panel3.add(btnReset);
		
		JLabel lblCustomerName = new JLabel("Customer Name    :");
		lblCustomerName.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblCustomerName.setBounds(511, 75, 122, 25);
		panel3.add(lblCustomerName);
		
		cusnamefield = new JTextField();
		cusnamefield.setBounds(643, 75, 176, 25);
		panel3.add(cusnamefield);
		cusnamefield.setColumns(10);
		
		JLabel lblPhoneNo = new JLabel("Phone No.        :");
		lblPhoneNo.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblPhoneNo.setBounds(511, 145, 122, 25);
		panel3.add(lblPhoneNo);
		
		cusphonefield = new JTextField();
		cusphonefield.setBounds(643, 149, 176, 25);
		panel3.add(cusphonefield);
		cusphonefield.setColumns(10);
		
		cusgstfield = new JTextField();
		cusgstfield.setBounds(643, 181, 176, 25);
		panel3.add(cusgstfield);
		cusgstfield.setColumns(10);
		
		cusaddfield = new JTextField();
		cusaddfield.setBounds(643, 111, 176, 28);
		panel3.add(cusaddfield);
		cusaddfield.setColumns(10);
		
		Box horizontalBox_7 = Box.createHorizontalBox();
		horizontalBox_7.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		horizontalBox_7.setBounds(488, 64, 348, 145);
		panel3.add(horizontalBox_7);
		
		JLabel lblCgst = new JLabel("CGST :");
		lblCgst.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCgst.setBounds(632, 282, 78, 22);
		panel3.add(lblCgst);
		
		JLabel lblSgst = new JLabel("SGST :");
		lblSgst.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSgst.setBounds(632, 315, 78, 29);
		panel3.add(lblSgst);
		
		JLabel lblIgst = new JLabel("IGST :");
		lblIgst.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIgst.setBounds(632, 355, 78, 22);
		panel3.add(lblIgst);
		
		cgst = new JSpinner();
		cgst.setBounds(699, 285, 67, 20);
		panel3.add(cgst);
		
		sgst = new JSpinner();
		sgst.setBounds(699, 321, 67, 20);
		panel3.add(sgst);
		
		igst = new JSpinner();
		igst.setBounds(700, 355, 68, 22);
		panel3.add(igst);
		
		Box horizontalBox_9 = Box.createHorizontalBox();
		horizontalBox_9.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		horizontalBox_9.setBounds(618, 229, 176, 159);
		panel3.add(horizontalBox_9);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setBounds(189, 218, 46, 14);
		panel3.add(lblItems);
		
		JLabel lblQty = new JLabel("Qty");
		lblQty.setBounds(292, 218, 46, 14);
		panel3.add(lblQty);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(348, 218, 46, 14);
		panel3.add(lblPrice);
		
		JLabel lblGstitem = new JLabel("Gst/item");
		lblGstitem.setBounds(446, 218, 60, 14);
		panel3.add(lblGstitem);
		
		JLabel lblAddress = new JLabel("Address           :");
		lblAddress.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblAddress.setBounds(511, 112, 113, 25);
		panel3.add(lblAddress);
		
		JLabel lblBuyerGstin = new JLabel("Buyer GSTIN   :");
		lblBuyerGstin.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblBuyerGstin.setBounds(511, 180, 115, 22);
		panel3.add(lblBuyerGstin);
		
		JLabel lblSelectGst = new JLabel("Select GST");
		lblSelectGst.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectGst.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSelectGst.setBounds(618, 242, 174, 23);
		panel3.add(lblSelectGst);
		
		JPanel panel4 = new JPanel();
		layeredPane.add(panel4, "name_28531958925900");
		panel4.setLayout(null);
		
		JLabel lblSearchBillReceipt = new JLabel("Search Bill Receipt By :");
		lblSearchBillReceipt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblSearchBillReceipt.setBounds(10, 11, 194, 38);
		panel4.add(lblSearchBillReceipt);
		
		JRadioButton rdbtnByInvoiceId = new JRadioButton("Invoice Id");
		rdbtnByInvoiceId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					PreparedStatement ps;
					ResultSet rs;
					comboBox_4.removeAllItems();
					whr="invoiceid";
					
					String str="select invoiceid from invoice order by invoiceid DESC";
					ps=con.prepareStatement(str);
					rs=ps.executeQuery();
					while(rs.next()){
						comboBox_4.addItem(rs.getString(1));
					}
					
				}
				catch(Exception es){}
			}
		});
		buttonGroup.add(rdbtnByInvoiceId);
		rdbtnByInvoiceId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnByInvoiceId.setBounds(210, 17, 88, 27);
		panel4.add(rdbtnByInvoiceId);
		
		JRadioButton rdbtnByDate = new JRadioButton("Date");
		rdbtnByDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					
					PreparedStatement ps;
					ResultSet rs;
					comboBox_4.removeAllItems();
					whr="date";
					
					String str="select DISTINCT dat from invoice order by dat DESC";
					ps=con.prepareStatement(str);
					rs=ps.executeQuery();
					while(rs.next()){
						comboBox_4.addItem(rs.getString(1));
					}
					
				}
				catch(Exception es){}
			}
		});
		buttonGroup.add(rdbtnByDate);
		rdbtnByDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnByDate.setBounds(300, 19, 58, 23);
		panel4.add(rdbtnByDate);
		
		comboBox_4 = new JComboBox();
		comboBox_4.setBounds(377, 16, 180, 30);
		panel4.add(comboBox_4);
		
		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(whr.equalsIgnoreCase("invoiceid")){
					try{
						//Connection con;
						PreparedStatement ps;
						ResultSet rs;
						
						String str="select * from invoice where invoiceid=?";
						ps=con.prepareStatement(str);
						ps.setString(1, comboBox_4.getSelectedItem().toString());
						rs=ps.executeQuery();
						table_2.setModel(DbUtils.resultSetToTableModel(rs));
						//con.close();
					}
					catch(Exception es){
						JOptionPane.showMessageDialog(frame, es.toString());
					}
				}
				else if(whr.equalsIgnoreCase("date")){
					try{
						//Connection con;
						PreparedStatement ps;
						ResultSet rs;
						
						String str="select * from invoice where dat=?";
						ps=con.prepareStatement(str);
						ps.setString(1, comboBox_4.getSelectedItem().toString());
						rs=ps.executeQuery();
						table_2.setModel(DbUtils.resultSetToTableModel(rs));
						//con.close();
					}
					catch(Exception es){
						JOptionPane.showMessageDialog(frame, es.toString());
					}
				}
				
			}
		});
		btnSearch_1.setBounds(585, 22, 75, 23);
		panel4.add(btnSearch_1);
		
		JLabel lblBillReceipt = new JLabel("BILL");
		lblBillReceipt.setForeground(Color.BLUE);
		lblBillReceipt.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblBillReceipt.setBounds(1238, 11, 88, 44);
		panel4.add(lblBillReceipt);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setBounds(1226, 53, 100, 2);
		panel4.add(separator_10);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(0, 72, 1348, 508);
		panel4.add(scrollPane_4);
		
		table_2 = new JTable();
		table_2.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		table_2.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		scrollPane_4.setViewportView(table_2);
		
		JPanel panel5 = new JPanel();
		panel5.setBackground(new Color(72, 209, 204));
		layeredPane.add(panel5, "name_102475607398600");
		panel5.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Customer List");
		lblNewLabel_7.setForeground(Color.RED);
		lblNewLabel_7.setFont(new Font("Algerian", Font.PLAIN, 35));
		lblNewLabel_7.setBounds(545, 11, 267, 43);
		panel5.add(lblNewLabel_7);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setForeground(Color.ORANGE);
		separator_11.setBounds(535, 55, 278, 2);
		panel5.add(separator_11);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(1, 73, 1347, 507);
		panel5.add(scrollPane_5);
		
		table_3 = new JTable();
		table_3.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		table_3.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		scrollPane_5.setViewportView(table_3);
		
		JPanel panel6 = new JPanel();
		panel6.setBackground(new Color(72, 209, 204));
		layeredPane.add(panel6, "name_102527774129300");
		panel6.setLayout(null);
		
		JLabel lblSupplierList = new JLabel("Supplier List");
		lblSupplierList.setForeground(new Color(255, 0, 0));
		lblSupplierList.setFont(new Font("Algerian", Font.PLAIN, 35));
		lblSupplierList.setBounds(556, 13, 260, 43);
		panel6.add(lblSupplierList);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setForeground(Color.ORANGE);
		separator_12.setBounds(551, 58, 250, 3);
		panel6.add(separator_12);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(1, 81, 1347, 498);
		panel6.add(scrollPane_6);
		
		table_4 = new JTable();
		table_4.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		table_4.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		scrollPane_6.setViewportView(table_4);
		
		JPanel SendMailPanel = new JPanel();
		SendMailPanel.setBackground(new Color(255, 182, 193));
		layeredPane.add(SendMailPanel, "name_82011291291800");
		SendMailPanel.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setIcon(new ImageIcon(StockPage.class.getResource("/img/Send-Email-Button-Transparent-PNG.png")));
		lblNewLabel_8.setBounds(179, 0, 521, 86);
		SendMailPanel.add(lblNewLabel_8);
		
		JLabel lblFrom = new JLabel("From    :");
		lblFrom.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblFrom.setBounds(201, 142, 99, 34);
		SendMailPanel.add(lblFrom);
		
		JLabel lblNewLabel_9 = new JLabel("TO        :");
		lblNewLabel_9.setFont(new Font("Times New Roman", Font.PLAIN, 23));
		lblNewLabel_9.setBounds(200, 191, 99, 28);
		SendMailPanel.add(lblNewLabel_9);
		
		JLabel lblHarshitwebdesigngmailcom = new JLabel("hubsports97@gmail.com");
		lblHarshitwebdesigngmailcom.setForeground(new Color(65, 105, 225));
		lblHarshitwebdesigngmailcom.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblHarshitwebdesigngmailcom.setBounds(364, 142, 280, 34);
		SendMailPanel.add(lblHarshitwebdesigngmailcom);
		
		textField = new JTextField();
		textField.setBounds(363, 186, 281, 34);
		SendMailPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblSubject = new JLabel("Subject   :");
		lblSubject.setFont(new Font("Times New Roman", Font.PLAIN, 21));
		lblSubject.setBounds(200, 232, 113, 34);
		SendMailPanel.add(lblSubject);
		
		textField_1 = new JTextField();
		textField_1.setBounds(363, 231, 280, 34);
		SendMailPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblBody = new JLabel("Body     :");
		lblBody.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblBody.setBounds(200, 283, 113, 34);
		SendMailPanel.add(lblBody);
		
		JScrollPane scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(363, 283, 364, 140);
		SendMailPanel.add(scrollPane_8);
		
		JTextArea textArea_5 = new JTextArea();
		scrollPane_8.setViewportView(textArea_5);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					ByEmail obj=new ByEmail();
					String ope=obj.SendEmail(textField.getText().toString(),textField_1.getText().toString(),textArea_5.getText().toString());
					if(ope.equalsIgnoreCase("ok"))
						JOptionPane.showMessageDialog(frame, "Sent Sucessfully...");
					else
						JOptionPane.showMessageDialog(frame,"There Was Some Problem...\nCheck Your Internet Connection");
			}
		});
		btnSend.setFont(new Font("Algerian", Font.PLAIN, 22));
		btnSend.setBounds(233, 459, 136, 36);
		SendMailPanel.add(btnSend);
		
		JButton btnReset_1 = new JButton("Reset");
		btnReset_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textArea_5.setText("");
			}
		});
		btnReset_1.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		btnReset_1.setBounds(423, 459, 136, 36);
		SendMailPanel.add(btnReset_1);
		
		Box horizontalBox_6 = Box.createHorizontalBox();
		horizontalBox_6.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(255, 0, 0), new Color(0, 206, 209)));
		horizontalBox_6.setBounds(125, 115, 663, 405);
		SendMailPanel.add(horizontalBox_6);
		
		JPanel SendSmsPanel = new JPanel();
		SendSmsPanel.setBackground(new Color(255, 182, 193));
		layeredPane.add(SendSmsPanel, "name_138165434948400");
		SendSmsPanel.setLayout(null);
		
		JLabel lblNumber = new JLabel("Receiver Number    :");
		lblNumber.setForeground(new Color(0, 128, 128));
		lblNumber.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNumber.setBounds(189, 193, 166, 35);
		SendSmsPanel.add(lblNumber);
		
		JLabel lblMessage = new JLabel("Message                  :");
		lblMessage.setForeground(new Color(46, 139, 87));
		lblMessage.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblMessage.setBounds(189, 260, 186, 35);
		SendSmsPanel.add(lblMessage);
		
		textField_4 = new JTextField();
		textField_4.setBounds(406, 193, 226, 35);
		SendSmsPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JScrollPane scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(406, 250, 338, 155);
		SendSmsPanel.add(scrollPane_9);
		
		textArea_6 = new JTextArea();
		scrollPane_9.setViewportView(textArea_6);
		
		JButton btnSend_1 = new JButton("Send");
		btnSend_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSend_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendSms();
			}
		});
		btnSend_1.setBounds(270, 454, 123, 35);
		SendSmsPanel.add(btnSend_1);
		
		JLabel lblSendAMessage = new JLabel("Send a Message");
		lblSendAMessage.setForeground(new Color(0, 0, 205));
		lblSendAMessage.setFont(new Font("Algerian", Font.PLAIN, 40));
		lblSendAMessage.setBounds(271, 48, 343, 48);
		SendSmsPanel.add(lblSendAMessage);
		
		JButton btnReset_2 = new JButton("Reset");
		btnReset_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_4.setText("");
				textArea_6.setText("");
			}
		});
		btnReset_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReset_2.setBounds(468, 452, 159, 35);
		SendSmsPanel.add(btnReset_2);
		
		Box horizontalBox_10 = Box.createHorizontalBox();
		horizontalBox_10.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		horizontalBox_10.setBounds(233, 34, 409, 72);
		SendSmsPanel.add(horizontalBox_10);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(128, 0, 0), new Color(128, 128, 128)));
		horizontalBox_1.setBounds(114, 149, 681, 368);
		SendSmsPanel.add(horizontalBox_1);
		
		JButton btnChangeApikey = new JButton("Change Api-Key");
		btnChangeApikey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangeApiKey window = new ChangeApiKey(id,pass);
				window.frmManageApiKey.setVisible(true);
			}
		});
		btnChangeApikey.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		btnChangeApikey.setBounds(1170, 24, 168, 35);
		SendSmsPanel.add(btnChangeApikey);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(188, 143, 143));
		menuBar.setFont(new Font("Times New Roman", Font.BOLD, 15));
		menuBar.setMargin(new Insets(4, 0, 0, 0));
		menuBar.setBounds(0, 0, 1380, 39);
		frame.getContentPane().add(menuBar);
		
		JMenu mnHome = new JMenu("Home");
		mnHome.setFont(new Font("Times New Roman", Font.BOLD, 15));
		menuBar.add(mnHome);
		
		JMenuItem mntmHome = new JMenuItem("Home");
		mntmHome.setFont(new Font("Segoe UI Black", Font.BOLD, 12));
		mntmHome.setHorizontalAlignment(SwingConstants.LEFT);
		mntmHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchpanel(panel);
			}
		});
		mnHome.add(mntmHome);
		
		JMenu mnItemCategory = new JMenu("Item Brand");
		mnItemCategory.setFont(new Font("Times New Roman", Font.BOLD, 15));
		menuBar.add(mnItemCategory);
		
		JMenuItem mntmAddNewCategory = new JMenuItem("Add New Brand");
		mntmAddNewCategory.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmAddNewCategory.setForeground(Color.BLUE);
		mntmAddNewCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemcategorypage window = new itemcategorypage("add");
				window.itemcatframe.setVisible(true);
				
			}
		});
		mnItemCategory.add(mntmAddNewCategory);
		
		JMenuItem mntmDeleteCategory = new JMenuItem("Delete Brand");
		mntmDeleteCategory.setForeground(new Color(255, 0, 0));
		mntmDeleteCategory.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmDeleteCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemcategorypage window = new itemcategorypage("delete");
				window.itemcatframe.setVisible(true);
			}
		});
		
		JSeparator separator_27 = new JSeparator();
		mnItemCategory.add(separator_27);
		mnItemCategory.add(mntmDeleteCategory);
		
		JMenu mnItem = new JMenu("Item");
		mnItem.setFont(new Font("Times New Roman", Font.BOLD, 15));
		menuBar.add(mnItem);
		
		JMenu mnNewMenu = new JMenu("Add Item/Product");
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.setForeground(Color.BLUE);
		mnItem.add(mnNewMenu);
		
		JMenuItem mntmAddNewItem = new JMenuItem("Without QR/Barcode");
		mntmAddNewItem.setForeground(new Color(128, 0, 0));
		mntmAddNewItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itempage window = new itempage("add");
				window.frmManageItem.setVisible(true);
			}
		});
		mnNewMenu.add(mntmAddNewItem);
		
		JMenuItem mntmWithItemQr = new JMenuItem("With Item Barcode");
		mntmWithItemQr.setForeground(new Color(107, 142, 35));
		mntmWithItemQr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				QRItemPage window = new QRItemPage("add");
				window.frame.setVisible(true);
			}
		});
		mnNewMenu.add(mntmWithItemQr);
		
		
		JMenuItem mntmDeleteItem = new JMenuItem("Delete Item");
		mntmDeleteItem.setForeground(new Color(255, 0, 0));
		mntmDeleteItem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmDeleteItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itempage window = new itempage("delete");
				window.frmManageItem.setVisible(true);
			}
		});
		
		JSeparator separator = new JSeparator();
		mnItem.add(separator);
		
		JMenu mnUpdateItemproduct = new JMenu("Update Item/Product");
		mnUpdateItemproduct.setForeground(new Color(186, 85, 211));
		mnUpdateItemproduct.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnItem.add(mnUpdateItemproduct);
		
		JMenuItem mntmUpdateItem = new JMenuItem("Without Code");
		mntmUpdateItem.setForeground(new Color(139, 0, 0));
		mnUpdateItemproduct.add(mntmUpdateItem);
		
		JMenuItem mntmWithCode = new JMenuItem("With Code");
		mntmWithCode.setForeground(new Color(60, 179, 113));
		mntmWithCode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				QRItemPage window = new QRItemPage("update");
				window.frame.setVisible(true);
			}
		});
		mnUpdateItemproduct.add(mntmWithCode);
		mntmUpdateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itempage window = new itempage("update");
				window.frmManageItem.setVisible(true);
			}
		});
		
		JSeparator separator_26 = new JSeparator();
		mnItem.add(separator_26);
		mnItem.add(mntmDeleteItem);
		
		JMenu mnSupplier = new JMenu("Supplier");
		mnSupplier.setFont(new Font("Times New Roman", Font.BOLD, 15));
		menuBar.add(mnSupplier);
		
		JMenuItem mntmAdd = new JMenuItem("Add");
		mntmAdd.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmAdd.setForeground(Color.BLUE);
		mntmAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supplierpage window = new supplierpage("add");
				window.frmManageSupplier.setVisible(true);
			}
		});
		mnSupplier.add(mntmAdd);
		
		JMenuItem mntmUpdate = new JMenuItem("Update");
		mntmUpdate.setForeground(new Color(186, 85, 211));
		mntmUpdate.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supplierpage window = new supplierpage("update");
				window.frmManageSupplier.setVisible(true);
			}
		});
		
		JSeparator separator_28 = new JSeparator();
		mnSupplier.add(separator_28);
		mnSupplier.add(mntmUpdate);
		
		JMenuItem mntmDelete = new JMenuItem("Delete");
		mntmDelete.setForeground(new Color(255, 0, 0));
		mntmDelete.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supplierpage window = new supplierpage("delete");
				window.frmManageSupplier.setVisible(true);
			}
		});
		
		JSeparator separator_29 = new JSeparator();
		mnSupplier.add(separator_29);
		mnSupplier.add(mntmDelete);
		
		JMenu mnCustomer = new JMenu("Customer");
		mnCustomer.setFont(new Font("Times New Roman", Font.BOLD, 15));
		mnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerpage window = new customerpage("add");
				window.frmManageCustomer.setVisible(true);
			}
		});
		menuBar.add(mnCustomer);
		
		JMenuItem mntmAdd_1 = new JMenuItem("Add");
		mntmAdd_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmAdd_1.setForeground(Color.BLUE);
		mntmAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerpage window = new customerpage("add");
				window.frmManageCustomer.setVisible(true);
			}
		});
		mnCustomer.add(mntmAdd_1);
		
		JMenuItem mntmUpdate_1 = new JMenuItem("Update");
		mntmUpdate_1.setForeground(new Color(186, 85, 211));
		mntmUpdate_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmUpdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerpage window = new customerpage("update");
				window.frmManageCustomer.setVisible(true);
			}
		});
		
		JSeparator separator_30 = new JSeparator();
		mnCustomer.add(separator_30);
		mnCustomer.add(mntmUpdate_1);
		
		JMenuItem mntmDelete_1 = new JMenuItem("Delete");
		mntmDelete_1.setForeground(new Color(255, 0, 0));
		mntmDelete_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				customerpage window = new customerpage("delete");
				window.frmManageCustomer.setVisible(true);
			}
		});
		
		JSeparator separator_31 = new JSeparator();
		mnCustomer.add(separator_31);
		mnCustomer.add(mntmDelete_1);
		
		JMenu mnContactUs = new JMenu("Contact Us");
		mnContactUs.setFont(new Font("Times New Roman", Font.BOLD, 15));
		menuBar.add(mnContactUs);
		
		JMenuItem menuItem = new JMenuItem("Via Email");
		menuItem.setForeground(new Color(255, 0, 255));
		menuItem.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(frame, "This Feature is Not Available...");
				switchpanel(SendMailPanel);
			}
		});
		mnContactUs.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("Via Message");
		menuItem_1.setForeground(new Color(0, 0, 255));
		menuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(frame, "This Feature is Not Available...");
				switchpanel(SendSmsPanel);
			}
		});
		
		JSeparator separator_32 = new JSeparator();
		mnContactUs.add(separator_32);
		mnContactUs.add(menuItem_1);
		
		JMenu mnFeatures = new JMenu("Search");
		mnFeatures.setFont(new Font("Times New Roman", Font.BOLD, 15));
		menuBar.add(mnFeatures);
		
		JMenuItem mntmPurchaseHistory = new JMenuItem("Item's Purchase History");
		mntmPurchaseHistory.setForeground(new Color(30, 144, 255));
		mntmPurchaseHistory.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmPurchaseHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchpanel(panel1);
				try{
					PreparedStatement ps;
					ResultSet rs;
					
					String str="select * from itempurchase";
					ps=con.prepareStatement(str);
					rs=ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(frame, e.toString());
				}
			}
		});
		mnFeatures.add(mntmPurchaseHistory);
		
		JMenuItem mntmCheckStock = new JMenuItem("Check Stock");
		mntmCheckStock.setForeground(new Color(30, 144, 255));
		mntmCheckStock.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmCheckStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchpanel(panel2);
				try{
					PreparedStatement ps;
					ResultSet rs;
					
					String str="select * from item";
					ps=con.prepareStatement(str);
					rs=ps.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					
				}
				catch(Exception es){
					JOptionPane.showMessageDialog(frame, es.toString());
				}

			}
		});
		
		JSeparator separator_33 = new JSeparator();
		mnFeatures.add(separator_33);
		mnFeatures.add(mntmCheckStock);
		
		JMenuItem mntmCheckBills = new JMenuItem("Check Bills");
		mntmCheckBills.setForeground(new Color(30, 144, 255));
		mntmCheckBills.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmCheckBills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchpanel(panel4);
				try{
					//Connection con;
					PreparedStatement ps;
					ResultSet rs;
					
					String str="select * from invoice";
					ps=con.prepareStatement(str);
					rs=ps.executeQuery();
					table_2.setModel(DbUtils.resultSetToTableModel(rs));
					//con.close();
				}
				catch(Exception es){
					JOptionPane.showMessageDialog(frame, es.toString());
				}
			}
		});
		
		JSeparator separator_34 = new JSeparator();
		mnFeatures.add(separator_34);
		mnFeatures.add(mntmCheckBills);
		
		JMenuItem mntmAllCustomerList = new JMenuItem("All Customer List");
		mntmAllCustomerList.setForeground(new Color(30, 144, 255));
		mntmAllCustomerList.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmAllCustomerList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchpanel(panel5);
				try{
					//Connection con;
					PreparedStatement ps;
					ResultSet rs;
					
					String str="select * from customer";
					ps=con.prepareStatement(str);
					rs=ps.executeQuery();
					table_3.setModel(DbUtils.resultSetToTableModel(rs));
					//con.close();
				}
				catch(Exception es){
					JOptionPane.showMessageDialog(frame, es.toString());
				}
			}
		});
		
		JSeparator separator_35 = new JSeparator();
		mnFeatures.add(separator_35);
		mnFeatures.add(mntmAllCustomerList);
		
		JMenuItem mntmAllSupplierList = new JMenuItem("All Supplier List");
		mntmAllSupplierList.setForeground(new Color(30, 144, 255));
		mntmAllSupplierList.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmAllSupplierList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchpanel(panel6);
				try{
					PreparedStatement ps;
					ResultSet rs;
					
					String str="select * from supplier";
					ps=con.prepareStatement(str);
					rs=ps.executeQuery();
					table_4.setModel(DbUtils.resultSetToTableModel(rs));
			
				}
				catch(Exception es){
					JOptionPane.showMessageDialog(frame, es.toString());
				}
			}
		});
		
		JSeparator separator_36 = new JSeparator();
		mnFeatures.add(separator_36);
		mnFeatures.add(mntmAllSupplierList);
		
		JMenu mnGenerateBill = new JMenu("Generate_Invoice");
		mnGenerateBill.setFont(new Font("Times New Roman", Font.BOLD, 15));
		menuBar.add(mnGenerateBill);
		
		JMenuItem mntmGenerateBillReceipt = new JMenuItem("Manually Generate");
		mntmGenerateBillReceipt.setForeground(new Color(128, 0, 128));
		mntmGenerateBillReceipt.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmGenerateBillReceipt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchpanel(panel3);
				addcomboBox_1();
			}
		});
		mnGenerateBill.add(mntmGenerateBillReceipt);
		
		JSeparator separator_37 = new JSeparator();
		mnGenerateBill.add(separator_37);
		
		JMenuItem mntmGenerateInvoice = new JMenuItem("BarCode Invoice");
		mntmGenerateInvoice.setForeground(new Color(128, 0, 128));
		mntmGenerateInvoice.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnGenerateBill.add(mntmGenerateInvoice);
		mntmGenerateInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PrintBill window = new PrintBill();
				window.frame.setVisible(true);
			}
		});
		
		JMenu mnBarcode = new JMenu("Bar_Code");
		mnBarcode.setFont(new Font("Times New Roman", Font.BOLD, 15));
		menuBar.add(mnBarcode);
		
		JMenuItem mntmShowItemDetails = new JMenuItem("Show Item Details");
		mntmShowItemDetails.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmShowItemDetails.setForeground(new Color(148, 0, 211));
		mntmShowItemDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchpanel(bar_item_detail);
			}
		});
		mnBarcode.add(mntmShowItemDetails);
		
		JMenuItem mntmShowInvoiceDetails = new JMenuItem("Show Invoice Details");
		mntmShowInvoiceDetails.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmShowInvoiceDetails.setForeground(new Color(148, 0, 211));
		mntmShowInvoiceDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switchpanel(barcodeinvoicedetails);
			}
		});
		
		JSeparator separator_38 = new JSeparator();
		mnBarcode.add(separator_38);
		mnBarcode.add(mntmShowInvoiceDetails);
		
		JMenu mnSetting = new JMenu("Setting");
		mnSetting.setFont(new Font("Times New Roman", Font.BOLD, 15));
		menuBar.add(mnSetting);
		
		JMenuItem mntmLogOut = new JMenuItem("Log Out");
		mntmLogOut.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mntmLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage window = new LoginPage();
				window.frmLoginPage.setVisible(true);
				frame.dispose();
			}
		});
		
		JMenuItem mntmChangePassword = new JMenuItem("Change Password");
		mntmChangePassword.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnSetting.add(mntmChangePassword);
		
		JSeparator separator_39 = new JSeparator();
		mnSetting.add(separator_39);
		mnSetting.add(mntmLogOut);
		
		
		JLabel lblNewLabel = new JLabel("DASHBOARD");
		lblNewLabel.setBackground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblNewLabel.setBounds(108, 50, 203, 41);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon(StockPage.class.getResource("/img/stock-management-words-next-to-boxes-inventory-white-background-31736981.jpg")));
		lblNewLabel_1.setBounds(0, 42, 100, 54);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(1215, 33, 155, 63);
		frame.getContentPane().add(lblNewLabel_5);
		lblNewLabel_5.setIcon(new ImageIcon(StockPage.class.getResource("/img/MyLOgo1.png")));
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setForeground(new Color(0, 191, 255));
		lblWelcome.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblWelcome.setBounds(851, 52, 125, 41);
		frame.getContentPane().add(lblWelcome);
		
		JLabel aname = new JLabel("New label");
		aname.setForeground(new Color(0, 206, 209));
		aname.setFont(new Font("Times New Roman", Font.PLAIN, 28));
		aname.setBounds(976, 53, 243, 41);
		frame.getContentPane().add(aname);
		
		aname.setText(Name);
		
		bar_item_detail = new JPanel();
		bar_item_detail.setBackground(new Color(176, 196, 222));
		layeredPane.add(bar_item_detail, "name_906334715145300");
		bar_item_detail.setLayout(null);
		
		lblItemid = new JLabel("Item ID :");
		lblItemid.setForeground(new Color(0, 0, 255));
		lblItemid.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblItemid.setBounds(268, 33, 96, 24);
		bar_item_detail.add(lblItemid);
		
		text_itemid = new JLabel("");
		text_itemid.setBounds(590, 33, 411, 24);
		bar_item_detail.add(text_itemid);
		
		textField_itemid = new JTextField();
		textField_itemid.setBounds(374, 33, 206, 27);
		bar_item_detail.add(textField_itemid);
		textField_itemid.setColumns(10);
		
		JLabel lblItemDetails = new JLabel("Item Details");
		lblItemDetails.setForeground(new Color(255, 0, 0));
		lblItemDetails.setFont(new Font("Algerian", Font.PLAIN, 30));
		lblItemDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemDetails.setBounds(1011, 19, 327, 41);
		bar_item_detail.add(lblItemDetails);
		
		JSeparator separator_24 = new JSeparator();
		separator_24.setBounds(0, 75, 1348, 9);
		bar_item_detail.add(separator_24);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(310, 112, 728, 391);
		bar_item_detail.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblItemName_1 = new JLabel("Item Name  :");
		lblItemName_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblItemName_1.setForeground(new Color(139, 0, 0));
		lblItemName_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblItemName_1.setBounds(24, 34, 109, 29);
		panel_2.add(lblItemName_1);
		
		JLabel lblNewLabel_12 = new JLabel("Sale Price  :");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_12.setForeground(new Color(139, 0, 0));
		lblNewLabel_12.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_12.setBounds(24, 96, 109, 29);
		panel_2.add(lblNewLabel_12);
		
		JLabel lblNewLabel_13 = new JLabel("Quantity  :");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_13.setForeground(new Color(139, 0, 0));
		lblNewLabel_13.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_13.setBounds(402, 151, 109, 29);
		panel_2.add(lblNewLabel_13);
		
		JLabel lblGst = new JLabel("GST %  :");
		lblGst.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGst.setForeground(new Color(139, 0, 0));
		lblGst.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblGst.setBounds(27, 207, 109, 29);
		panel_2.add(lblGst);
		
		JLabel lblNewLabel_14 = new JLabel("Brand Name  :");
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_14.setForeground(new Color(139, 0, 0));
		lblNewLabel_14.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_14.setBounds(402, 34, 109, 29);
		panel_2.add(lblNewLabel_14);
		
		JLabel lblSupplier = new JLabel("Supplier :");
		lblSupplier.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSupplier.setForeground(new Color(139, 0, 0));
		lblSupplier.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblSupplier.setBounds(400, 96, 109, 29);
		panel_2.add(lblSupplier);
		
		JLabel lblRemark = new JLabel("Remark  :");
		lblRemark.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRemark.setForeground(new Color(139, 0, 0));
		lblRemark.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblRemark.setBounds(399, 208, 109, 29);
		panel_2.add(lblRemark);
		
		label_0 = new JLabel("");
		label_0.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_0.setForeground(Color.BLUE);
		label_0.setBounds(148, 34, 248, 29);
		panel_2.add(label_0);
		
		label_1 = new JLabel("");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_1.setForeground(Color.BLUE);
		label_1.setBounds(148, 96, 211, 29);
		panel_2.add(label_1);
		
		label_2 = new JLabel("");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_2.setForeground(Color.BLUE);
		label_2.setBounds(526, 151, 192, 29);
		panel_2.add(label_2);
		
		label_3 = new JLabel("");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_3.setForeground(Color.BLUE);
		label_3.setBounds(151, 207, 211, 29);
		panel_2.add(label_3);
		
		label_4 = new JLabel("");
		label_4.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_4.setForeground(Color.BLUE);
		label_4.setBounds(521, 34, 194, 29);
		panel_2.add(label_4);
		
		label_5 = new JLabel("");
		label_5.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_5.setForeground(Color.BLUE);
		label_5.setBounds(521, 96, 194, 29);
		panel_2.add(label_5);
		
		JScrollPane scrollPane_12 = new JScrollPane();
		scrollPane_12.setBounds(518, 210, 197, 95);
		panel_2.add(scrollPane_12);
		
		textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		scrollPane_12.setViewportView(textPane_1);
		
		JButton btnUpdate = new JButton("Update Item");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				itempage window = new itempage("update");
				window.frmManageItem.setVisible(true);
			}
		});
		btnUpdate.setForeground(new Color(205, 92, 92));
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnUpdate.setBounds(277, 316, 155, 29);
		panel_2.add(btnUpdate);
		
		JLabel lblPurchasePrice = new JLabel("Purchase Price  :");
		lblPurchasePrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPurchasePrice.setForeground(new Color(139, 0, 0));
		lblPurchasePrice.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblPurchasePrice.setBounds(24, 149, 111, 29);
		panel_2.add(lblPurchasePrice);
		
		label_7 = new JLabel("");
		label_7.setForeground(Color.BLUE);
		label_7.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_7.setBounds(150, 149, 211, 29);
		panel_2.add(label_7);
		
		barcodeinvoicedetails = new JPanel();
		layeredPane.add(barcodeinvoicedetails, "name_1674868918479300");
		barcodeinvoicedetails.setLayout(null);
		
		JLabel lblInvoiceIdcode = new JLabel("Invoice Id/Code :");
		lblInvoiceIdcode.setForeground(Color.BLUE);
		lblInvoiceIdcode.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblInvoiceIdcode.setBounds(199, 28, 164, 24);
		barcodeinvoicedetails.add(lblInvoiceIdcode);
		
		txtinvoicecode = new JTextField();
		txtinvoicecode.setColumns(10);
		txtinvoicecode.setBounds(373, 28, 206, 27);
		barcodeinvoicedetails.add(txtinvoicecode);
		
		JSeparator separator_25 = new JSeparator();
		separator_25.setBounds(0, 79, 1348, 9);
		barcodeinvoicedetails.add(separator_25);
		
		JLabel lblItemInvoiceDetails = new JLabel("Item Invoice Details");
		lblItemInvoiceDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblItemInvoiceDetails.setForeground(Color.RED);
		lblItemInvoiceDetails.setFont(new Font("Algerian", Font.PLAIN, 30));
		lblItemInvoiceDetails.setBounds(1004, 13, 334, 41);
		barcodeinvoicedetails.add(lblItemInvoiceDetails);
		
		lblinvoicecode = new JLabel("");
		lblinvoicecode.setBounds(589, 31, 327, 20);
		barcodeinvoicedetails.add(lblinvoicecode);
		
		JLabel lblNumberOfItems = new JLabel("Number Of Item's in this Bill's");
		lblNumberOfItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberOfItems.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNumberOfItems.setBounds(629, 87, 300, 33);
		barcodeinvoicedetails.add(lblNumberOfItems);
		
		JLabel lblGstAmount = new JLabel("Invoice Id                :");
		lblGstAmount.setHorizontalAlignment(SwingConstants.LEFT);
		lblGstAmount.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblGstAmount.setBounds(73, 156, 164, 33);
		barcodeinvoicedetails.add(lblGstAmount);
		
		JLabel lblNewLabel_15 = new JLabel("Total Quantity         :");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_15.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNewLabel_15.setBounds(73, 211, 164, 33);
		barcodeinvoicedetails.add(lblNewLabel_15);
		
		JLabel lblGst_1 = new JLabel("GST %                   :");
		lblGst_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGst_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblGst_1.setBounds(73, 269, 164, 27);
		barcodeinvoicedetails.add(lblGst_1);
		
		JLabel lblGstAmount_1 = new JLabel("GST Amount          :");
		lblGstAmount_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblGstAmount_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblGstAmount_1.setBounds(73, 322, 164, 27);
		barcodeinvoicedetails.add(lblGstAmount_1);
		
		JLabel lblTotalPrice = new JLabel("Total Price :");
		lblTotalPrice.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblTotalPrice.setBounds(137, 443, 142, 33);
		barcodeinvoicedetails.add(lblTotalPrice);
		
		lblinvoiceid = new JLabel("");
		lblinvoiceid.setForeground(Color.BLUE);
		lblinvoiceid.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblinvoiceid.setBounds(261, 156, 319, 33);
		barcodeinvoicedetails.add(lblinvoiceid);
		
		lbltotquan = new JLabel("");
		lbltotquan.setForeground(Color.BLUE);
		lbltotquan.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbltotquan.setBounds(261, 209, 319, 33);
		barcodeinvoicedetails.add(lbltotquan);
		
		lblgst = new JLabel("");
		lblgst.setForeground(Color.BLUE);
		lblgst.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblgst.setBounds(261, 265, 319, 33);
		barcodeinvoicedetails.add(lblgst);
		
		lblgstamount = new JLabel("");
		lblgstamount.setForeground(Color.BLUE);
		lblgstamount.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblgstamount.setBounds(261, 319, 319, 33);
		barcodeinvoicedetails.add(lblgstamount);
		
		lbltotprice = new JLabel("");
		lbltotprice.setForeground(Color.BLUE);
		lbltotprice.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lbltotprice.setBounds(278, 443, 237, 33);
		barcodeinvoicedetails.add(lbltotprice);
		
		Box horizontalBox_11 = Box.createHorizontalBox();
		horizontalBox_11.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 0, 0), Color.BLUE));
		horizontalBox_11.setBounds(56, 412, 534, 100);
		barcodeinvoicedetails.add(horizontalBox_11);
		
		JScrollPane scrollPane_13 = new JScrollPane();
		scrollPane_13.setBounds(639, 119, 699, 450);
		barcodeinvoicedetails.add(scrollPane_13);
		
		table_5 = new JTable();
		scrollPane_13.setViewportView(table_5);
		
		
	}
	
	private void addinvoiceid() {
		//Connection con;
		PreparedStatement ps;
		invoice.setText(" ");
		try{
			
			String str="select invoiceid from invoice where invoiceid=(select max(invoiceid) from invoice)";
			ps=con.prepareStatement(str);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				int in=rs.getInt(1)+1;
				invoice.setText(" "+in);
			}
			else{
				invoice.setText(" 1");
			}
			
		}
		catch(Exception es){}
		
	}

	public void addcomboBox_1(){
		//Connection con;
		PreparedStatement ps;
		ResultSet rs;
		try{
			
			String str="select DISTINCT brandname from itembrand";
			ps=con.prepareStatement(str);
			rs=ps.executeQuery();
			while(rs.next()){
				comboBox_1.addItem(rs.getString(1));
			}
			//con.close();
		}
		catch(Exception es){
			JOptionPane.showMessageDialog(frame, es.toString());
		}
	}
	
	public void additembrand(String brand){
		//Connection con;
		PreparedStatement ps;
		ResultSet rs;
		try{
			
			String str="select DISTINCT itemname from item where brandname=?";
			ps=con.prepareStatement(str);
			ps.setString(1, brand);
			rs=ps.executeQuery();
			while(rs.next()){
				comboBox.addItem(rs.getString(1));
			}		
			//con.close();
		}
		catch(Exception es){}
	}
	public void sendSms(){
		String apiKey="";
		try {
			//Connection con;
			PreparedStatement ps;
			ResultSet rs;
			
			String str="select * from apikey";
			ps=con.prepareStatement(str);
			rs=ps.executeQuery();
			if(rs.next()){
				apiKey = rs.getString(1);
			}		
			//con.close();
			
			// Construct data
			String message = textArea_6.getText().toString();
			String sender = "SMSIND";
			String numbers = textField_4.getText().toString();
			
			// Send data
			message=URLEncoder.encode(message,"UTF-8");
			String language="english";
			String route="p";
			
			String myUrl="https://www.fast2sms.com/dev/bulk?authorization="+apiKey+"&sender_id="+sender+"&language=english&route=p&numbers="+numbers+"&message="+message;
			
			URL url=new URL(myUrl);
			
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("cache-control", "no-cache");
			conn.setRequestProperty("User-Agent", "Mozilla/5.0");
			
			//conn.getOutputStream().write(data.getBytes("UTF-8"));
			int code=conn.getResponseCode();
			
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				//stringBuffer.append(line);
				JOptionPane.showMessageDialog(frame, "message "+line);
			}
			rd.close();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,"Error SMS "+e);
		}
	}

	public void run() {
		while(true){
			try{
		         
				try{
					if(con.isClosed()){
						ConnectDB obj= new ConnectDB();
						con=obj.Connect();
					}
				}
				catch(Exception es){
					//JOptionPane.showMessageDialog(frame, "error..."+es.toString());
				}
				
				Thread.sleep(1000);
				
				Calendar cal=Calendar.getInstance();
				int hour=cal.get(Calendar.HOUR_OF_DAY);
				if(hour>12)
					hour=hour-12;
				int min=cal.get(Calendar.MINUTE);
				int sec=cal.get(Calendar.SECOND);
				SimpleDateFormat df=new SimpleDateFormat("hh : mm : ss aa");
				Date da=cal.getTime();
				String tim=df.format(da);
				home_time.setText(tim);
	
				
				try{
					item_id= Double.parseDouble(textField_itemid.getText());
					showitemdetails(item_id);
				}
				catch(NumberFormatException nm){
					item_id=0.0;
					textField_itemid.setText("");
				}
				
				if(item_id==0){
					textField_itemid.setText("");
				}
				else{
					text_itemid.setText("Item Id = "+item_id);
					textField_itemid.setText("");
				}
				
				try{
					invoiceid=  Double.parseDouble(txtinvoicecode.getText());
					showinvoicedetails(invoiceid);
				}
				catch(NumberFormatException er){
					invoiceid=0.0;
					txtinvoicecode.setText("");
				}
				
				if(invoiceid==0){
					txtinvoicecode.setText("");
				}
				else{
					lblinvoicecode.setText("Invoice Code = "+invoiceid);
					txtinvoicecode.setText("");
				}
				
				
			}catch(Exception e){
				//e.printStackTrace();
			}
		}
		
	}

	private void showinvoicedetails(Double id) {
				
		try{
			PreparedStatement ps,ps1,ps2;
			
			String str="select itemid,itemname,price,quantity,amount from qrinvoice where invoiceid=?";
			ps=con.prepareStatement(str);
			ps.setDouble(1, id);
			
			ps2=con.prepareStatement(str);
			ps2.setDouble(1, id);
			
			ResultSet rs=ps.executeQuery();
			ResultSet r=ps2.executeQuery();
			
			table_5.setModel(DbUtils.resultSetToTableModel(rs));
			
			if(!r.next()){
				lbltotquan.setText("");
				lblinvoiceid.setText("");
				lblgst.setText("");
				lblgstamount.setText("");
				lbltotprice.setText("");
				lblinvoicecode.setText("Id is not Present in DataBase...");
				JOptionPane.showMessageDialog(frame, "          Error...\nThis Invoice Id is not found...");
			}
			
			String st="select invoiceid,quantity,gst,gstamount,total from qrinvoice where invoiceid=?";
			ps1=con.prepareStatement(st);
			ps1.setDouble(1, id);
			ResultSet rs1=ps1.executeQuery();
			int qun=0;
			while(rs1.next()){
				qun+=Integer.parseInt(rs1.getString(2));
				
				lbltotquan.setText(""+qun);
				lblinvoiceid.setText(rs1.getString(1));
				lblgst.setText(rs1.getString(3));
				lblgstamount.setText(rs1.getString(4));
				lbltotprice.setText("‎₹ "+rs1.getString(5));
			}
	
		}
		catch(SQLException er){
			JOptionPane.showMessageDialog(frame, er.toString());
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(frame, e.toString());
		}
		
	}

	public void showitemdetails(Double id){
			PreparedStatement ps;
			try{
				String str="select * from item where itemid=?";
				ps=con.prepareStatement(str);
				ps.setDouble(1, id);
				ResultSet rs=ps.executeQuery();
				if(rs.next()){
					label_0.setText(rs.getString(5));
					label_1.setText("‎₹ "+rs.getFloat(6));
					label_2.setText(rs.getInt(8)+"");
					label_3.setText(rs.getInt(9)+" %");
					label_4.setText(rs.getString(3));
					label_5.setText(rs.getString(4));
					label_7.setText("‎₹ "+rs.getFloat(7));
					textPane_1.setText(rs.getString(10));
				}
				else{
					JOptionPane.showMessageDialog(frame, "error...\nThis Item_ID is Not Present...");
				}
			}
			catch(Exception es){}
	}
}