import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import databaseConnect.ConnectDB;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class itemcategorypage {

	public JFrame itemcatframe;
	String operation;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox jcb;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					itemcategorypage window = new itemcategorypage("add");
					window.itemcatframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public itemcategorypage(String str) {
		operation=str;
		initialize();
	}

	
	private void initialize() {
		itemcatframe = new JFrame();
		itemcatframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		itemcatframe.getContentPane().setBackground(new Color(128, 128, 128));
		itemcatframe.setResizable(false);
		itemcatframe.setBounds(100, 100, 640, 373);
		itemcatframe.getContentPane().setLayout(null);
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(128, 0, 0), new Color(250, 250, 210)));
		horizontalBox.setBounds(156, 8, 309, 64);
		itemcatframe.getContentPane().add(horizontalBox);
		
		JLabel lblItemCategoryPanel = new JLabel("Item Brand Panel");
		lblItemCategoryPanel.setBackground(new Color(128, 0, 0));
		lblItemCategoryPanel.setBounds(195, 11, 260, 54);
		itemcatframe.getContentPane().add(lblItemCategoryPanel);
		lblItemCategoryPanel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		
		JLabel lblNewLabel = new JLabel("Brand Name            :    ");
		lblNewLabel.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblNewLabel.setBounds(82, 101, 223, 45);
		itemcatframe.getContentPane().add(lblNewLabel);
		
		if(operation.equals("add")){
			textField = new JTextField();
			textField.setBounds(315, 101, 217, 38);
			itemcatframe.getContentPane().add(textField);
			textField.setColumns(10);
		}
		else{
			jcb=new JComboBox();
			jcb.setBounds(315, 101, 217, 38);
			itemcatframe.getContentPane().add(jcb);
			jcb.addItem("Select one");
			jcbadd();
		}
		
		JLabel lblCategoryName = new JLabel("Confirm Name         :");
		lblCategoryName.setFont(new Font("Algerian", Font.PLAIN, 20));
		lblCategoryName.setBounds(82, 164, 207, 45);
		itemcatframe.getContentPane().add(lblCategoryName);
		
		textField_1 = new JTextField();
		textField_1.setBounds(315, 170, 217, 38);
		itemcatframe.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSave = new JButton("SAVE");
		if(operation.equals("delete"))
			btnSave.setText("Delete");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection con;
				PreparedStatement ps;
				try{
					ConnectDB obj= new ConnectDB();
					con=obj.Connect();
						if(operation.equals("add")){
							String name=textField.getText();
							String name1=textField_1.getText();
							if(name.equals(name1)){
								String str="select * from itembrand where brandname=?";
								ps=con.prepareStatement(str);
								ps.setString(1,name);
								ResultSet rs=ps.executeQuery();
								if(!rs.next()){
									String sql="insert into itembrand values(id,?)";
									ps=con.prepareStatement(sql);
									ps.setString(1,name);
									ps.executeUpdate();
									JOptionPane.showMessageDialog(itemcatframe, "Brand Saved");
									textField.setText("");
									textField_1.setText("");
								}
								else{
									JOptionPane.showMessageDialog(itemcatframe, "This Brand Is already exists....\nTry another Brand");
								}
							}
							else{
								JOptionPane.showMessageDialog(itemcatframe, "Name is not Matched");
							}
						}
						else{
							textField_1.disable();
							if(!jcb.getSelectedItem().toString().equalsIgnoreCase("Select one")){
								String itemid=jcb.getSelectedItem().toString();
								String str="delete from itembrand where brandname=?";
								ps=con.prepareStatement(str);
								ps.setString(1,itemid);
								int p=ps.executeUpdate();
								JOptionPane.showMessageDialog(itemcatframe, "Data Deleted...");
								jcbadd();
								jcb.removeItem(jcb.getSelectedItem());
							}
							else{
								JOptionPane.showMessageDialog(itemcatframe, "You are not Select any Brand");
							}
						}
						con.close();
					}
				catch(Exception es){
						JOptionPane.showMessageDialog(itemcatframe, "Database Connectivity Error....\nPlease Contact Developer..!!!");
				}
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSave.setBounds(133, 245, 156, 38);
		itemcatframe.getContentPane().add(btnSave);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				itemcatframe.dispose();
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCancel.setBounds(327, 245, 156, 38);
		itemcatframe.getContentPane().add(btnCancel);
	}
	public void jcbadd(){
		try{
			Connection con;
			PreparedStatement ps;
			ResultSet rs;
			ConnectDB obj= new ConnectDB();
			con=obj.Connect();
			String str="select * from itembrand";
			ps=con.prepareStatement(str);
			rs=ps.executeQuery();
			while(rs.next()){
				jcb.addItem(rs.getString(2));
			}
			con.close();
		}
		catch(Exception e){}
	}
}
