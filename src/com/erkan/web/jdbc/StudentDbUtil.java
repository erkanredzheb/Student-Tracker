package com.erkan.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	
	private static DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Student> getStudents() throws Exception {
		
		List<Student> students = new ArrayList<>();
		
		
		Connection myConn = null;
		Statement myStm = null;
		ResultSet myRs = null;
		
		try {
			myConn = dataSource.getConnection();
			String sql = "Select * from student order by last_name";
			myStm = myConn.createStatement();
			myRs = myStm.executeQuery(sql);
			
			while (myRs.next()) {
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				Student tempStudent = new Student(id, firstName, lastName, email);
				
				students.add(tempStudent);
			}						
			
			return students;
		}
		finally {
			close(myConn, myStm, myRs);			
		}				
	}

	private static void close(Connection myConn, Statement myStm, ResultSet myRs) {
		try {
			if(myRs != null) {
				myRs.close();
			}
			
			if(myStm != null) {
				myStm.close();
			}
			
			if(myConn != null) {
				myConn.close();
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
		
	}

	public void addStudent(Student theStudent) throws SQLException {
		
		Connection myConn = null;
		PreparedStatement myStm = null;
		
		
		try {
			myConn = dataSource.getConnection();
			
			String sql = "insert into student "
						+ "(first_name, last_name, email) "
						+ "values (?, ?, ?)";
			
			myStm = myConn.prepareStatement(sql);
			
			myStm.setString(1, theStudent.getFirstName());
			myStm.setString(2, theStudent.getLastName());
			myStm.setString(3, theStudent.getEmail());
			
			myStm.execute();
			
			
		}
		finally {
			close(myConn, myStm, null);
			
		}
	}

	public Student getStudent(String theStudentId) throws Exception {
		
		Student theStudent = null;
		
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try {
			
			studentId = Integer.parseInt(theStudentId);
			
			myConn = dataSource.getConnection();
			
			String sql = "select * from student where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, studentId);
			
			myRs = myStmt.executeQuery();
			
			if(myRs.next()) {
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				theStudent = new Student(studentId, firstName, lastName, email);
			}
			else {
				throw new Exception("Could not find the student id: " + studentId);
			}
			
			
			
		
			return theStudent;
		}	
		finally {
			close(myConn, myStmt, myRs);
		}
			
		}

	public static void updateStudent(Student theStudent) throws Exception {
		
		
		Connection myConn = null;
		PreparedStatement myStmt = null;

		
		try {
					
			myConn = dataSource.getConnection();
			
			String sql = "update student set first_name=?, last_name=?, email=? where id=?";
			
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			myStmt.execute();
			
		}
		finally {
			close(myConn, myStmt, null);
		}
			
			
		
	}

	public void deleteStudent(String theStudentId) throws Exception {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try {
			
			int studentId = Integer.parseInt(theStudentId);
			
			myConn = dataSource.getConnection();
			
			String sql = "delete from student where id=?";
			
			
			myStmt = myConn.prepareStatement(sql);
			
			myStmt.setInt(1, studentId);
			
			myStmt.execute();
			
		}
		finally {
			close(myConn, myStmt, null);
		}
	}
		
		
		

}