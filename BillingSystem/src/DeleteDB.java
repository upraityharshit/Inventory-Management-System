import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import databaseConnect.ConnectDB;


public class DeleteDB {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteDB window = new DeleteDB();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DeleteDB() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try{
			Connection con;
			PreparedStatement ps;
			ConnectDB obj= new ConnectDB();
			con=obj.Connect();
				
			String sql="DROP TABLE `test`.`admin`";
			ps=con.prepareStatement(sql);
			ps.executeUpdate();
			
			String sql1="DROP TABLE `test`.`customer`;";
			ps=con.prepareStatement(sql1);
			ps.executeUpdate();
			
			String sql2="DROP TABLE `test`.`supplier`;";
			ps=con.prepareStatement(sql2);
			ps.executeUpdate();
			
			String sql3="DROP TABLE `test`.`item`;";
			ps=con.prepareStatement(sql3);
			ps.executeUpdate();
			
			String sql4="DROP TABLE `test`.`itempurchase`;";
			ps=con.prepareStatement(sql4);
			ps.executeUpdate();
			
			String sql5="DROP TABLE `test`.`saleitem`;";
			ps=con.prepareStatement(sql5);
			ps.executeUpdate();
			
			String sql6="DROP TABLE `test`.`itembrand`;";
			ps=con.prepareStatement(sql6);
			ps.executeUpdate();
			
			String sql7="DROP TABLE `test`.`invoice`;";
			ps=con.prepareStatement(sql7);
			ps.executeUpdate();
			
			String sql8="DROP TABLE `test`.`apikey`;";
			ps=con.prepareStatement(sql8);
			ps.executeUpdate();
			
			con.close();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(frame, e.toString());
		}
	}

}
