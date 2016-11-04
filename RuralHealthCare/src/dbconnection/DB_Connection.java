package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {
private static Connection connection=null;
public  Connection getConnection() {
	return connection;
}
public  void setConnection(Connection connection) {
	DB_Connection.connection = connection;
}
public void connectToDB(){
	//load the driver class
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/patient","root","7417899737");
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
}
public void disconnectToDB(){
	try {
		Class.forName("com.mysql.jdbc.Driver");
		connection.close();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	
}
}
