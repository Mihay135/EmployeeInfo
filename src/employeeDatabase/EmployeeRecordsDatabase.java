package employeeDatabase;

import java.sql.*;

public class EmployeeRecordsDatabase {
	public Connection connectToDB() {
		Connection conn = null;
		String databaseUrl = "jdbc:sqlite:employeeDB";
		try {
			conn = DriverManager.getConnection(databaseUrl);
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return conn;
	}
	
	public Connection createTable(Connection conn, String tableName) {
		try {
			//deleteTable(conn, tableName);
			
			String createTableSQL = "" +
					"CREATE TABLE " +
					"IF NOT EXISTS " +
					tableName +
					"(" +
					"id integer primary key, " +
					"first_Name varchar(255), " +
					"last_Name varchar(255), " +
					"phone varchar(11), " +
					"email varchar(45), " +
					"city varchar(50), " +
					"annual_salary double(11)" +
					");";
			
			Statement stmt = conn.createStatement();
			stmt.execute(createTableSQL);
			System.out.println();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return conn;
	}
	
	public Connection updateTableRecords(Connection conn, String firstName, String lastName, String phoneNumber, String email, String city, double annualSalary, int id) {
		String updateSQL = "UPDATE records "+
						"SET first_Name=?, last_Name=?, phone=?, email=?, city=?, annual_salary=? "+
						"WHERE id = ? ;";
		try {
			PreparedStatement pstmt = conn.prepareStatement(updateSQL);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, phoneNumber);
			pstmt.setString(4, email);
			pstmt.setString(5, city);	
			pstmt.setDouble(6, annualSalary);
			pstmt.setInt(7,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public Connection deleteFromRecordsTable(Connection conn,  int id) {
		String deleteSQL = "DELETE FROM records "+
						"WHERE id = ? ;";
		try {
			PreparedStatement pstmt = conn.prepareStatement(deleteSQL);
			pstmt.setInt(1,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public Connection deleteTable(Connection conn, String tableName) {
		try {
			String deleteTableSQL = "" +
					"DROP TABLE " +
					tableName +
					";";	
			
			Statement stmt = conn.createStatement();
			stmt.execute(deleteTableSQL);
			System.out.println();
		} catch (SQLException e) {
			//Ignore exception - table doesn't exist probably
		}
		return conn;
	}
	
	public Connection insertIntoRecords(Connection conn, String firstName, String lastName, String phoneNumber, String email, String city, double annualSalary) {
		String insertSQL = ""
				+ "INSERT INTO records"
				+ "(first_Name, last_Name, phone, email, city, annual_salary) "
				+ "VALUES(?,?,?,?,?,?);";
		try {
			PreparedStatement pstmt = conn.prepareStatement(insertSQL);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, phoneNumber);
			pstmt.setString(4, email);
			pstmt.setString(5, city);	
			pstmt.setDouble(6, annualSalary);
			pstmt.executeUpdate();
			System.out.println("Record succesfully inserted" + String.format("%.2f",annualSalary));
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return conn;
	}
	
	public void displayDatabase(Connection conn, String tableName) {
		String selectSQL = "SELECT * FROM " + tableName;
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(selectSQL);
			System.out.println("--------- " + tableName + " -----------");
			while(result.next()) {
				System.out.print("ID: " + result.getInt("id") + ", ");
				System.out.print("Name: " + result.getString("first_name") + ", ");
				System.out.print("Surname: " + result.getString("last_name") + ", ");
				System.out.print("Annual Salary: " + String.format("%, .2f", result.getDouble("annual_salary")));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("-------------------------------------");
	}
	
	public ResultSet getResultSetFromQuery(PreparedStatement pst ) throws SQLException{
		return pst.executeQuery();
		
	}
	
	public void closeConnection(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	
}
