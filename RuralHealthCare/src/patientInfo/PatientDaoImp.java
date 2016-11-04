package patientInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbconnection.DB_Connection;
import model.Patient;





public class PatientDaoImp {
	String query;
	private Statement statement = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private Connection con;
	private DB_Connection connection ;
	
	/* write the queries*/
	 static final String INSERT = " INSERT into patient(patientid, name, age, address, email, password, phone"+ ") VALUES(?,?,?,?,?,?,?)";
	 static final String DELETE = "DELETE FROM patient WHERE patientid=?";
	 static final String UPDATE = " UPDATE patient SET name = ?, age =?, address =?, email =?, phone =? WHERE patientid=?"; ;
	 static final String FIND_ALL = "SELECT name,age,marks,address,email,phone FROM patient  ORDER BY age";
	 static final String USER_PASS = "SELECT patientid, password FROM patient WHERE name =? AND password=?";
	 public void dbConnect(){
			connection=new DB_Connection();
			connection.connectToDB();
			con=connection.getConnection();
			 
		}
	 public void insertPatient(Patient patient){
		 dbConnect();
		 //prepared statements 
		 try {
			ps=con.prepareStatement(INSERT);
			ps.setLong(1, patient.getPatientID());
			ps.setString(2, patient.getName());
			ps.setInt(3, patient.getAge());
			ps.setString(4, patient.getAddress());
			ps.setInt(5, patient.getPasword());
			ps.setString(6, patient.getEmail());
			ps.setInt(7, patient.getPhoneN());
			int rowsInserted=ps.executeUpdate();
			if(rowsInserted > 0){
				System.out.println("A new Patient has been created successfully!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 finally {
			try {
				ps.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			
		}
	 }
	 /*connect to DB and get patient list
	  * 
	  */
	 public List<Patient> getPatientList() {
		 
		dbConnect();
		List<Patient> list=new ArrayList<>();
		try {
			statement = con.createStatement();
			//get the result of the query with rs
			rs = statement.executeQuery(FIND_ALL);
			 
			int count = 0;
			
			while(rs.next()){
				Patient patient = new Patient(
						rs.getLong(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getString(4),
						rs.getInt(5),
						rs.getString(6),
						rs.getInt(7));
				 
				
				//store all student data into a List
				list.add(patient);
				//format output
				/*String output = "Student #%d: %s - %s - %s -%s" ;
				System.out.println(String.format(output, ++count, ));*/
				System.out.println(list);
			}
			rs.close();
			statement.close();
			connection.disconnectToDB();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
		
	}
	 public void removePatient(long id) {
         
			dbConnect();
			
			try {
				//remove data  
				statement = con.createStatement();

				//PreparedStatements use variables and are more efficient
				ps = con.prepareStatement(DELETE);
				 ps.setLong(1, id);
				
				int rowsDelated =ps.executeUpdate();
				if(rowsDelated > 0){
					System.out.println("An user with ID " + id + " was deleted successfully!");
				   //extract record after delete
					getPatientList();
				}
		
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					ps.close();
					statement.close();
					connection.disconnectToDB();
				} catch (SQLException e) {
					 
					e.printStackTrace();
				}
				
			}
			
		}

	 public void updateStudent(Patient patient) {
	        
			dbConnect();
			
			try {
				//statements allow me to issue SQL queries to the database
				statement = con.createStatement();
	 
				//PreparedStatements use variables and are more efficient
				ps = con.prepareStatement(UPDATE);
				ps.setString(1, patient.getName() );
				ps.setInt(2, patient.getAge());
				ps.setString(3, patient.getAddress());
				ps.setString(4, patient.getEmail());
				ps.setInt(5, patient.getPhoneN());
				int rowsUpdated = ps.executeUpdate();
				
				if(rowsUpdated > 0){
					System.out.println(rowsUpdated + " Rows updated.");
					System.out.println("An existing user with ID " +patient.getPatientID()+" was updated successfully!");
				   //extract record after updated
					getPatientList();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					ps.close();
					statement.close();
					connection.disconnectToDB();
				} catch (SQLException e) {
					 
					e.printStackTrace();
				}
				
			}
			
		}
	 public static boolean validationLogin(int id, String password) throws SQLException{
			Statement statement = null;
			ResultSet rs = null;
			PreparedStatement ps = null;
			Connection con = null;
			
			try {
			statement = con.createStatement();
			
				ps = con.prepareStatement(USER_PASS);
				ps.setInt(1, id);
				ps.setString(2,password);
				rs=ps.executeQuery();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			boolean result = false;
			try {
				if(rs.next()){
					result = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			con.close();
			return result;
		}
	 public void batchStudent(Patient patient) {
			dbConnect();
			try {
				//statements allow me to issue SQL queries to the database
				statement = con.createStatement();
				con.setAutoCommit(true);
				 
				//PreparedStatements use variables and are more efficient
				ps = con.prepareStatement(INSERT);
				//ps.setLong(1, student.getStudentID());
				ps.setLong(1, patient.getPatientID());
				ps.setString(2, patient.getName());
				ps.setInt(3, patient.getAge());
				ps.setString(4, patient.getAddress());
				ps.addBatch();
				/*
				 * To avoid SQL Injecttion
				 * Take cate of out of the memory
				 */
				int count = 0;
				final int batchsize = 1000;
				if(++count %batchsize == 0){
					/*
					 * check how we have incremented a counter
					 * once it reaches batchsize which 1000
					 * we call ps.executeBatch();
					 */
					ps.executeBatch();
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					//insert remaining records
					ps.executeBatch();
					System.out.println("A new Patient has been created successfully!");
					ps.close();
					statement.close();
					connection.disconnectToDB();
				} catch (SQLException e) {
					 
					e.printStackTrace();
				}
				
			}
			
		}
	 /*
		 * Transaction management
		 */
		
		public void transacStudent(Patient patient) {
			dbConnect();
			try {
				//statements allow me to issue SQL queries to the database
				statement = con.createStatement();
				con.setAutoCommit(false);
				 
				//PreparedStatements use variables and are more efficient
				ps = con.prepareStatement(INSERT);
				ps.setLong(1, patient.getPatientID());
				ps.setString(2, patient.getName() );
				ps.setInt(3, patient.getAge());
				ps.setString(4, patient.getAddress());
				
				ps.addBatch();
				/*
				 * To avoid SQL Injecttion
				 * Take cate of out of the memory
				 */
				int count = 0;
				final int batchsize = 1000;
				if(++count %batchsize == 0){
					/*
					 * check how we have incremented a counter
					 * once it reaches batchsize which 1000
					 * we call ps.executeBatch();
					 */
					ps.executeBatch();
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					con.rollback();
				} catch (SQLException e1) {
					 
					e1.printStackTrace();
				}
			}finally{
				try {
					//insert remaining records
					ps.executeBatch();
					con.commit();
					System.out.println("A new Student has been created successfully!");
					ps.close();
					statement.close();
					//conexion.disconnect();
				} catch (SQLException e) {
					 
					e.printStackTrace();
				}
				
			}
			
		}


		public void dbClose(){
			connection=new DB_Connection();
			connection.disconnectToDB();
			
		}

	}
