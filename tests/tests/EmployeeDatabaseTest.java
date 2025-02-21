package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;


import employeeDatabase.EmployeeRecordsDatabase;

class EmployeeDatabaseTest {

	@Test
	void testDBConnectionIsWorking() {
		assertNotNull(new EmployeeRecordsDatabase().connectToDB());
	}
	
	@Test
	void testDBNewTableCreationSuccessful() {
		EmployeeRecordsDatabase database = new EmployeeRecordsDatabase();
		Connection conn = database.connectToDB();
		assertNotNull(database.createTable(conn, "records"));
	}
	
	@Test
	void testDBTableDeletionSuccessful() {
		EmployeeRecordsDatabase database = new EmployeeRecordsDatabase();
		Connection conn = database.connectToDB();
		conn = database.createTable(conn, "records");
		assertNotNull(database.deleteTable(conn, "records"));
	}

	@Test
	void testDBTableInsertionSuccessful() {
		EmployeeRecordsDatabase database = new EmployeeRecordsDatabase();
		Connection conn = database.connectToDB();
		conn = database.createTable(conn, "records");
		double d = 25000.0;
		conn = database.insertIntoRecords(conn, "Mihai", "Sauca", "0123456789", "s.mihay@gmail.com", "Firenze", (float)d);
		assertNotNull(database.deleteTable(conn, "records"));
	}
}
