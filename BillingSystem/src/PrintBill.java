import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;

import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.mysql.jdbc.Connection;

import databaseConnect.ConnectDB;

import java.awt.Color;

import javax.swing.JToggleButton;

public class PrintBill implements Runnable{

	public JFrame frame;
	private JTextField txtitemname;
	private JTextField txtprice;
	private JTextField txtquantity;
	private JTextField txtitemid;
	private JTextField txttotalAmount;
	private JTextField txtgst;
	private JLabel lbl_itemid;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintBill window = new PrintBill();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	Connection con;
	
	public PrintBill() {
		initialize();
		Thread t=new Thread(this);
		t.start();
		
		try{
			ConnectDB obj= new ConnectDB();
			con=obj.Connect();
		}
		catch(Exception es){
			JOptionPane.showMessageDialog(frame, es.toString());
		}
		
		try {
			PreparedStatement pp;
			String ed="delete from saleitem2";
			pp=con.prepareStatement(ed);
			pp.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	 	DecimalFormat df=new DecimalFormat("##.##");
		
	 	Double totalAmount=0.0;
	    Double subtot=0.0;
	    Double balance=0.0;
	    Double bHeight=0.0;
	    float gst=0;
	    int tgst=0;
	    int item_id=0;
	    int quntity=1;
	    int invoiceid;
	    int invoice_id;
	    
	    ArrayList<String> itemName = new ArrayList<>();
	    ArrayList<String> quantity = new ArrayList<>();
	    ArrayList<String> itemPrice = new ArrayList<>();
	    ArrayList<String> subtotal = new ArrayList<>();
	

	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(300, 100, 378, 427);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblItemName = new JLabel("Item Name");
		lblItemName.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblItemName.setBounds(22, 85, 95, 27);
		frame.getContentPane().add(lblItemName);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblPrice.setBounds(22, 123, 95, 27);
		frame.getContentPane().add(lblPrice);
		
		JLabel lblQuantity = new JLabel("Quantity");
		lblQuantity.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblQuantity.setBounds(22, 161, 95, 27);
		frame.getContentPane().add(lblQuantity);
		
		JLabel lblAmount = new JLabel("Item Id");
		lblAmount.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblAmount.setBounds(22, 42, 95, 27);
		frame.getContentPane().add(lblAmount);
		
		txtitemname = new JTextField();
		txtitemname.setBounds(136, 85, 179, 20);
		frame.getContentPane().add(txtitemname);
		txtitemname.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(136, 123, 179, 20);
		frame.getContentPane().add(txtprice);
		
		txtquantity = new JTextField();
		txtquantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					int qun=Integer.parseInt(txtquantity.getText());
					
					PreparedStatement ps;
					String str="update saleitem2 set qun=? where id=?";
					ps=con.prepareStatement(str);
					ps.setInt(1, qun);
					ps.setInt(2, item_id);
					ps.executeUpdate();
					
					String st="select * from item where itemid=?";
					ps=con.prepareStatement(st);
					ps.setInt(1, item_id);
					ResultSet rs=ps.executeQuery();
					if(rs.next()){
						
						int a=itemName.size();
						
						for(int i=0;i<a;i++){
							if(rs.getString(5).equals(itemName.get(i))){
								
								int qun1=Integer.parseInt(quantity.get(i));
						        quantity.set(i,(qun+qun1)+"");
								
						        subtot= Double.valueOf(txtprice.getText()) * (qun+qun1);
						        subtotal.set(i,""+subtot);
						   
						        subtot= Double.valueOf(txtprice.getText()) * qun;
						        totalAmount = totalAmount+ subtot;
						        txttotalAmount.setText(totalAmount+"");
							}
						}
					}
				}
				catch(NumberFormatException nm){
					JOptionPane.showMessageDialog(frame, "Quantity is not Valid..."+nm.toString());
				}
				catch(Exception e){
					JOptionPane.showMessageDialog(frame, ""+e.toString());
				}
			}
		});
		txtquantity.setColumns(10);
		txtquantity.setBounds(136, 161, 179, 20);
		frame.getContentPane().add(txtquantity);
		
		txtitemid = new JTextField();
		txtitemid.setColumns(10);
		txtitemid.setBounds(136, 44, 179, 20);
		frame.getContentPane().add(txtitemid);
		
		JLabel lblTotalAmount = new JLabel("Total Amount");
		lblTotalAmount.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblTotalAmount.setBounds(24, 273, 95, 27);
		frame.getContentPane().add(lblTotalAmount);
		
		JLabel lblGst = new JLabel("GST %");
		lblGst.setFont(new Font("Times New Roman", Font.BOLD, 13));
		lblGst.setBounds(22, 235, 97, 27);
		frame.getContentPane().add(lblGst);
		
		txttotalAmount = new JTextField();
		txttotalAmount.setBounds(138, 273, 179, 20);
		frame.getContentPane().add(txttotalAmount);
		txttotalAmount.setColumns(10);
		
		txtgst = new JTextField();
		txtgst.setColumns(10);
		txtgst.setBounds(138, 236, 84, 20);
		frame.getContentPane().add(txtgst);
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 	
				invoice_id=createid();
				++invoice_id;
				try{
					PreparedStatement sp,sp1,sp2;
					String st="select * from saleitem2";
					sp=con.prepareStatement(st);
					ResultSet rs=sp.executeQuery();
					for(int i=0;rs.next();i++){
						String si="insert into qrinvoice value(?,?,?,?,?,?,?,?,?)";
						sp1=con.prepareStatement(si);
						sp1.setInt(1, invoice_id);
						sp1.setInt(2, rs.getInt(1));
						sp1.setString(3, itemName.get(i));
						sp1.setString(4, itemPrice.get(i));
						sp1.setString(5, quantity.get(i));
						sp1.setString(6,subtotal.get(i));
						sp1.setInt(7,tgst);
						sp1.setFloat(8,gst);
						sp1.setDouble(9, totalAmount);
						sp1.executeUpdate();	
						
						String agr="update item set itemquantity=itemquantity-? where itemid=?";
						sp2=con.prepareStatement(agr);
						sp2.setInt(1, rs.getInt(2));
						sp2.setInt(2, rs.getInt(1));
						sp2.executeUpdate();
						
						// Generate QR_Code PNG
						
						String qrCodeData = invoice_id+"";
			            String filePath = "D:\\QRCodes\\QR_Invoice\\"+invoice_id+".png";
			            String charset = "UTF-8"; // or "ISO-8859-1"
			            Map < EncodeHintType, ErrorCorrectionLevel > hintMap = new HashMap < EncodeHintType, ErrorCorrectionLevel > ();
			            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			            BitMatrix matrix = new MultiFormatWriter().encode(
			                new String(qrCodeData.getBytes(charset), charset),
			                BarcodeFormat.QR_CODE, 200, 200, hintMap);
			            MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath.lastIndexOf('.') + 1), new File(filePath));
						
					}
				}
				catch(Exception er){
					JOptionPane.showMessageDialog(frame, er.toString());
				}
				
				bHeight = Double.valueOf(itemName.size());
				 
				PrinterJob job=PrinterJob.getPrinterJob();
				job.setJobName("Print Data");
					
				job.setPrintable(new BillPrintable(),getPageFormat(job));
					
				boolean ok=job.printDialog();
				if(ok){
					try{
						job.print();
					}
					catch(PrinterException es){
						JOptionPane.showMessageDialog(frame, "Connection Problem in Printer");
					}
					catch(Exception es){
						JOptionPane.showMessageDialog(frame, "Something went Wrong....");
					}
				}	
			}
		});
		btnPrint.setBounds(161, 327, 154, 27);
		frame.getContentPane().add(btnPrint);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					tgst=Integer.parseInt(txtgst.getText());
					gst=(float) (totalAmount*((float)tgst/100));
					totalAmount+=gst;
					txttotalAmount.setText(""+df.format(totalAmount));
					txtgst.enable(false);			
					btnCalculate.setEnabled(false);
				}
				catch(NumberFormatException e){
					JOptionPane.showMessageDialog(frame, "Please type Gst in number only");
				}
			}
		});
		btnCalculate.setBounds(236, 235, 95, 23);
		frame.getContentPane().add(btnCalculate);
		
		JLabel lblInvoice = new JLabel("QR Code Invoice");
		lblInvoice.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblInvoice.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvoice.setBounds(10, 6, 342, 20);
		frame.getContentPane().add(lblInvoice);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 30, 363, 9);
		frame.getContentPane().add(separator);
		
		lbl_itemid = new JLabel("");
		lbl_itemid.setForeground(Color.RED);
		lbl_itemid.setBounds(112, 67, 250, 16);
		frame.getContentPane().add(lbl_itemid);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtitemname.setText("");
				txtquantity.setText("");
				txtprice.setText("");
				txtitemid.setText("");
				txttotalAmount.setText("");
				txtgst.setText("");
				txtgst.enable(true);
				btnCalculate.setEnabled(true);
				
				itemName.removeAll(itemName);
				itemPrice.removeAll(itemPrice);
				quantity.removeAll(quantity);
				subtotal.removeAll(subtotal);
				totalAmount=0.0;
				subtot=0.0;
				
				try {
					PreparedStatement pp;
					String ed="delete from saleitem2";
					pp=con.prepareStatement(ed);
					pp.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnReset.setBounds(44, 331, 89, 23);
		frame.getContentPane().add(btnReset);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 205, 362, 9);
		frame.getContentPane().add(separator_1);
	}

	public PageFormat getPageFormat(PrinterJob pj)
	{
	   
	   PageFormat pf = pj.defaultPage();
	   Paper paper = pf.getPaper();    

	   double bodyHeight = bHeight;  
	   double headerHeight = 5.0;                  
	   double footerHeight = 10.0;        
	   double width = cm_to_pp(8); 
	   double height = cm_to_pp(headerHeight+bodyHeight+footerHeight); 
	   paper.setSize(width, height);
	   paper.setImageableArea(0,10,width,height - cm_to_pp(1));  
	           
	   pf.setOrientation(PageFormat.PORTRAIT);  
	   pf.setPaper(paper);    

	   return pf;
	}

   protected static double cm_to_pp(double cm)
   {            
	        return toPPI(cm * 0.393600787);            
   }

   protected static double toPPI(double inch)
   {            
	        return inch * 72d;            
   }
   


public class BillPrintable implements Printable {
   
   
 public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
 throws PrinterException 
 {    
     
     int r= itemName.size();
     Toolkit t=Toolkit.getDefaultToolkit();
     Image img=t.getImage("D:\\QRCodes\\QR_Invoice\\"+invoice_id+".png"); 
     int result = NO_SUCH_PAGE;    
       if (pageIndex == 0) {                    
       
           Graphics2D g2d = (Graphics2D) graphics;                    
           double width = pageFormat.getImageableWidth();                               
           g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 
       
       try{
           int y=10;
           int yShift = 10;
           int headerRectHeight=15;
   
           g2d.setFont(new Font("Monospaced",Font.PLAIN,8));
           //g2d.drawImage(icon.getImage(), 50, 20, 90, 30, frame);y+=yShift+30;
           g2d.drawString("-------------------------------------",12,y);y+=yShift;
           g2d.drawString("            Your Shop Name           ",12,y);y+=yShift;
           g2d.drawString("      No 00000 Address Line One      ",12,y);y+=yShift;
           g2d.drawString("          Address Line 02            ",12,y);y+=yShift;
           g2d.drawString("        GSTIN : XXXXXXXXXXXXX        ",12,y);y+=yShift;
           g2d.drawString("           +91 XXXXXXXXX02           ",12,y);y+=yShift;
           g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;
           g2d.drawString("          Invoice Id : "+invoice_id,12,y);y+=headerRectHeight;
           g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;
           g2d.drawString(" Item Name                   Amount  ",10,y);y+=yShift;
           g2d.drawString("       Qty    Price                  ",10,y);y+=yShift;
           g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
    
           for(int s=0; s<r; s++)
           {
           g2d.drawString(" "+itemName.get(s)+"                 ",10,y);y+=yShift;
           g2d.drawString("        "+quantity.get(s)+"  *  "+itemPrice.get(s),10,y); g2d.drawString(" "+subtotal.get(s),150,y);y+=yShift;
           }
           
           g2d.drawString("-------------------------------------",10,y);y+=yShift;
           g2d.drawString(" GST%                        "+gst,10,y);y+=yShift;
           g2d.drawString("-------------------------------------",10,y);y+=yShift;
           g2d.drawString(" Total Amount            Rs. "+txttotalAmount.getText(),10,y);y+=yShift;
           g2d.drawString("-------------------------------------",10,y);y+=yShift;
           g2d.drawString("*************************************",10,y);y+=yShift;
           g2d.drawString("*        THANK YOU COME AGAIN       *",10,y);y+=yShift;
           g2d.drawString("*************************************",10,y);
           
           g2d.drawImage(img, 60, y, 100, 100, frame);y+=yShift+100;
           
           g2d.drawString("*************************************",10,y);y+=yShift;
           g2d.drawString("    SOFTWARE BY: Harshit Upraity     ",10,y);y+=yShift;
           g2d.drawString(" CONTACT: harshitwebdesign@gmail.com ",10,y);y+=yShift;       
           g2d.drawString("*************************************",10,y);
   }
   catch(Exception e){
   e.printStackTrace();
   }
             result = PAGE_EXISTS;    
         }    
         return result;    
     }
 }

	
	public int createid(){
		int id;
		try{
			PreparedStatement ci;
			String str="select invoiceid from qrinvoice where invoiceid=(select max(invoiceid) from qrinvoice)";
			ci=con.prepareStatement(str);
			ResultSet rs=ci.executeQuery();
			if(rs.next()){
				id= rs.getInt(1);
				return id;
			}
			else{
				return 0;
			}
		}
		catch(Exception e){
			return -1;
		}
	}
	
	public void run() {
		
		while(true){
			try {
				Thread.sleep(2000);
				
				try{
					if(txtitemid.getText().isEmpty()){
						
					}else{
						item_id=Integer.parseInt(txtitemid.getText());
						additem(item_id);
					}
				}
				catch(NumberFormatException nm){
					item_id=0;
					lbl_itemid.setText("Item ID Is Not Vaild...Only Number allowed");
				}
				
				if(item_id==0){
					txtitemid.setText("");
				}
				else{
					lbl_itemid.setText("");
					txtitemid.setText("");
				}
				
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}	
	}
	
	Boolean run=true;
	
	public void additem(int id){
		try{
			PreparedStatement ps,p,p1,p2;
			try{
				String str="select * from item where itemid=?";
				ps=con.prepareStatement(str);
				ps.setInt(1, id);
				ResultSet rs=ps.executeQuery();
				if(rs.next()){
					quntity=1;
					String st="select * from saleitem2 where id=?";
					p=con.prepareStatement(st);
					p.setInt(1, id);
					ResultSet rs1=p.executeQuery();
						if(rs1.next()){
							run=false;
							quntity+=rs1.getInt(2);
							String ins="update saleitem2 set qun=qun+1 where id=?";
							p2=con.prepareStatement(ins);
							p2.setInt(1, id);
							p2.executeUpdate();
							
							int a=itemName.size();
							
							for(int i=0;i<a;i++){
								if(rs.getString(4).equals(itemName.get(i))){
									txtitemname.setText(rs.getString(5));
									txtprice.setText(rs.getString(6));
									txtquantity.setText(quntity+"");
										       
							        subtot= Double.valueOf(txtprice.getText()) * quntity;
							        subtotal.set(i,""+subtot);
							        quantity.set(i,""+quntity);
							        
							        subtot= Double.valueOf(txtprice.getText());
							        totalAmount = totalAmount+ subtot;
							        txttotalAmount.setText(totalAmount+"");
								}
							}
							
						}
						else{
							run=true;
						}
					
					
					if(run){
						String inse="insert into saleitem2 value(?,?)";
						p1=con.prepareStatement(inse);
						p1.setInt(1, id);
						p1.setInt(2, 1);
						p1.executeUpdate();
						
						txtitemname.setText(rs.getString(5));
						txtprice.setText(rs.getString(6));
						txtquantity.setText("1");
						
						itemName.add(txtitemname.getText());
				        quantity.add(txtquantity.getText());
				        itemPrice.add(txtprice.getText());
				       
				        subtot= Double.valueOf(txtprice.getText());
				        subtotal.add(subtot+"");
				        
				        totalAmount = totalAmount+ subtot;
				        txttotalAmount.setText(totalAmount+"");
						
					}
					
				}
				else{
					JOptionPane.showMessageDialog(frame, "error...\nThis Item_ID is Not Present...");
				}
			}
			catch(Exception es){}
		}
		catch(Exception er){
			
		}
	}
}