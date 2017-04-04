package jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class Connectionutil {

	final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	final static String DB_URL = "jdbc:mysql://localhost:3306/EatHealthy";
	final static String USER = "root";
	final static String PASS = "zapdos";
	public static  Connection con=null;
	public  static Connection getConnection()
	{
		if(con==null)
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = (Connection)DriverManager.getConnection(DB_URL, USER, PASS);
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();        //prints the curent exception ,or throwable and it; backtraces to a standard erroe stream
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return con;
	}
	
	public void closeConnection()
	{
		try {
			if(con!=null)
				con.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
}

