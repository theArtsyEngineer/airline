package jsp;

import java.sql.*;
import java.util.Calendar;
import java.util.Random;

public class AcctController {

	//Create New Customer
	public static String[] newCust(Connection c, String user, String pass, String email, String telephone, String first,
			String last, String city, String zip, String address, String state, int credit) {

		String[] id = new String[2];

		//Get Account Creation Date
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date date = new java.sql.Date(currentDate.getTime());

		//Create Account Number
		Random rnd = new Random();
		int acct = 1000000 + rnd.nextInt(900000) + rnd.nextInt(900000) + rnd.nextInt(900000);
		
		try {

			//Insert Into User Table
			PreparedStatement ps = c.prepareStatement(
					"INSERT INTO systemUser(user, password, email, telephone, firstName, lastName, city, zipcode, address, state) VALUES (?,?,?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user);
			ps.setString(2, pass);
			ps.setString(3, email);
			ps.setString(4, telephone);
			ps.setString(5, first);
			ps.setString(6, last);
			ps.setString(7, city);
			ps.setString(8, zip);
			ps.setString(9, address);
			ps.setString(10, state);

			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();

			rs.close();
			ps.close();

			//Insert Into Customer Table
			ps = c.prepareStatement(
					"INSERT INTO customer(creditCardNum, accountNum, accountCreationDate, preferences, email) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, credit);
			ps.setInt(2, acct);
			ps.setDate(3, date);
			ps.setString(4, "None");
			ps.setString(5, email);

			ps.execute();

			rs = ps.getGeneratedKeys();

			id[0] = user;
			id[1] = email;

			rs.close();
			ps.close();

		} catch (Exception e) {
			throw new Error(e);
		}

		return id;
	}

	//Create New Employee
	public static long newEmp(Connection c, String user, String pass, String email, String telephone, String first,
			String last, String city, String zip, String address, String state, String role, float rate, String ssn) {

		long id = -1;

		//Get Start Date
		Calendar calendar = Calendar.getInstance();
		java.util.Date currentDate = calendar.getTime();
		java.sql.Date startDate = new java.sql.Date(currentDate.getTime());

		try {

			//Insert Into User Table
			PreparedStatement ps = c.prepareStatement(
					"INSERT INTO systemUser(user, password, email, telephone, firstName, lastName, city, zipcode, address, state) VALUES (?,?,?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user);
			ps.setString(2, pass);
			ps.setString(3, email);
			ps.setString(4, telephone);
			ps.setString(5, first);
			ps.setString(6, last);
			ps.setString(7, city);
			ps.setString(8, zip);
			ps.setString(9, address);
			ps.setString(10, state);

			ps.execute();

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getLong(user);
			}

			rs.close();
			ps.close();

			//Insert Into Employee Table
			ps = c.prepareStatement(
					"INSERT INTO employee(jobRole, hourlyRate, startDate, ssn, email) VALUES (?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, role);
			ps.setFloat(2, rate);
			ps.setDate(3, startDate);
			ps.setString(4, ssn);
			ps.setString(5, email);

			ps.execute();

			rs = ps.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getLong(user);
			}

			rs.close();
			ps.close();

		} catch (Exception e) {
			throw new Error(e);
		}

		return id;
	}

	//Update Customer Account Info
	public static void updateCust(String user, String pass, String email, String newEmail, String tel, String first,
			String last, String address, String city, String zip, String state, int credit) {

		Connection c = AWS.connect();

		try {

			String str = "UPDATE systemUser " + 
						 "SET email='" + newEmail + "'" + 
						 	",telephone='" + tel + "'" +
						 	",firstName='" + first +"'" +
						 	",lastName='" + last + "'" +
						 	",city='" + city + "'" +
						 	",zipCode='" + zip + "'" +
						 	",address='" + address + "'" +
						 	",state='" + state + "'" +
						 " WHERE email='" + email + "'";

			//Run the query against the database.
			Statement stmt = c.createStatement();

			stmt.executeUpdate(str);

			str = "UPDATE customer " + 
				  "SET email='" + newEmail + "'" +
				  	 ",creditCardNum='" + credit + "'" +
				  " WHERE email='" + email + "'";

			//Run the query against the database.
			stmt = c.createStatement();

			stmt.executeUpdate(str);

			AWS.close(c);

		} catch (Exception e) {
			throw new Error(e);
		}

	}

	//Update Employee Account Info
	public static void updateEmp(String user, String pass, String email, String newEmail, String tel, String first, String last, String address, String city, 
	int zip, String state, String role, double rate, int ssn){

		Connection c = AWS.connect();

		try {
			
			String str = "UPDATE systemUser " + 
						 "SET email='" + newEmail + "'" + 
							",telephone='" + tel + "'" +
							",firstName='" + first +"'" +
							",lastName='" + last + "'" +
							",city='" + city + "'" +
							",zipCode='" + zip + "'" +
							",address='" + address + "'" +
							",state='" + state + "'" +
						 " WHERE email='" + email + "'";
			
			//Run the query against the database.
			Statement stmt = c.createStatement();
			
			stmt.executeQuery(str);
			
			str = "UPDATE employee set email='" + newEmail + "'" +
					 ",jobRole='" + role + "'" +
					 ",hourlyRate='" + rate + "'" +
					 ",ssn='" + ssn + "'" +
				  " WHERE email='" + email + "'";

			//Run the query against the database.
			stmt = c.createStatement();

			stmt.executeQuery(str);
			
			AWS.close(c);
			
			
		} catch (Exception e){
			throw new Error(e);
		}

	}

	//Delete Customer Account
	public static void deleteCust(String email){
		
		Connection c = AWS.connect();
		
		try {

			String str = "DELETE FROM systemUser " + 
						 "WHERE email='" + email + "'";
			
			//Run the query against the database.
			Statement stmt = c.createStatement();
			
			stmt.executeUpdate(str);
			
			str = "DELETE FROM customer " + 
				  "WHERE email='" + email + "'";
			
			//Run the query against the database.
			stmt = c.createStatement();
			
			stmt.executeUpdate(str);
			
			AWS.close(c);
			
			
		} catch (Exception e){
			throw new Error(e);
		}
		
	}

	//Delete Employee Account
	public static void deleteEmp(String email){
		
		Connection c = AWS.connect();
		
		try {

			String str = "DELETE FROM systemUser " + 
						 "WHERE email='" + email + "'";
			
			//Run the query against the database.
			Statement stmt = c.createStatement();
			
			stmt.executeUpdate(str);
			
			str = "DELETE FROM employee " + 
				  "WHERE email='" + email + "'";
			
			//Run the query against the database.
			stmt = c.createStatement();
			
			stmt.executeUpdate(str);
			
			AWS.close(c);
			
			
		} catch (Exception e){
			throw new Error(e);
		}
		
	}

}
