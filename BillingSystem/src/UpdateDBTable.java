import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class UpdateDBTable {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateDBTable window = new UpdateDBTable();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public UpdateDBTable() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("RUN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con;
				PreparedStatement ps;
				try{
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection("jdbc:mysql://localhost:3307/test","root","root");
					
					String sql="ALTER TABLE test.invoice DROP CustomerName, DROP CGST, DROP SGST, DROP IGST, ADD Rateperitem MEDIUMTEXT ASCII AFTER itemsamount, ADD Qtyperitem MEDIUMTEXT ASCII, CHANGE discount HSNCode MEDIUMTEXT ASCII, CHANGE quantity TotalQuantity BIGINT(11), CHANGE TotalGST TotalGST MEDIUMTEXT ASCII, CHANGE CustomerMobileNo BuyersDeatails MEDIUMTEXT ASCII, CHANGE total GrandTotal DOUBLE, CHANGE dat dat VARCHAR(20), ADD CGST INT, ADD SGST INT, ADD IGST INT;";
					ps=con.prepareStatement(sql);
					ps.executeUpdate();
					
					String sql1="ALTER TABLE test.invoice CHANGE Qtyperitem Qtyperitem MEDIUMTEXT ASCII AFTER itemspurchase;";
					ps=con.prepareStatement(sql1);
					ps.executeUpdate();
					
					String sql2="create table apikey(akey)";
					ps=con.prepareStatement(sql2);
					ps.executeUpdate();
					
					JOptionPane.showMessageDialog(frame,"Done...Completed");
					frame.dispose();
					con.close();
					
				}
				catch(Exception es){
					JOptionPane.showMessageDialog(frame, "Error....");
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(134, 113, 138, 28);
		frame.getContentPane().add(btnNewButton);
		
	}
}
