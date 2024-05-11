package databaseConnect;

import java.sql.*;
import javax.swing.JOptionPane;
import com.mysql.jdbc.Connection;

public class ConnectDB {
	Connection con;
	public Connection Connect(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/IMSBARCODE","root","root");
			//con=(Connection) DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12360365","sql12360365","8wkg58jYam");		
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,e.toString());
		}
		return con;
	}
}
