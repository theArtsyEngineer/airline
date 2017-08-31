package jsp;

import java.sql.*;

//MYSQL database connection management

public class AWS {
	public static Connection connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://adgroup11cs336.cflhalajqv6b.us-east-2.rds.amazonaws.com:3306/travel_reservation";
			
			return DriverManager.getConnection(url, "ad829cs336", "entity336");
		} catch (Exception e) {
			throw new Error(e);
		}
	}

	public static boolean close(Connection c) {
		try {
			c.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	//Create account for new user
	public static long newUser(Connection c, String user, String pass, String email){
		
		long id = -1;
		
		try {
			PreparedStatement ps = c.prepareStatement("INSERT INTO systemUser(user, password, email) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user);
			ps.setString(2, pass);
			ps.setString(3, email);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()){
				id = rs.getLong(user);
			}
			
			rs.close();
			ps.close();
			
		} catch (Exception e){
			throw new Error(e);
		}
		
		return id;
	}
	
	//Create account for new employee
		public static long newEmployee(Connection c, String user, String pass, String email){
			
			long id = -1;
			
			try {
				PreparedStatement ps = c.prepareStatement("INSERT INTO systemUser(user, password, email) VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user);
				ps.setString(2, pass);
				ps.setString(3, email);
				
				ps.execute();
				
				ResultSet rs = ps.getGeneratedKeys();
				
				if(rs.next()){
					id = rs.getLong(user);
				}
				
				rs.close();
				ps.close();
				
			} catch (Exception e){
				throw new Error(e);
			}
			
			return id;
		}
		
		//Update Customer Account Info****
		public static void updateCust(String user, String pass, String email, String newEmail, String tel, String first, String last, String address, String city, 
				int zip, String state, int credit, int AccNum, String createDate, String pref){
			
			Connection c = AWS.connect();
			
			try {
				
				String str = "UPDATE systemUser set email=" + newEmail
							+"set telephone=" + tel
							+ ",firstName=" + first
							+ ",lastName=" + last
							+ ",city=" + city
							+ ",zipCode=" + zip
							+ ",address=" + address
							+ ",state=" + state
							+ ",WHERE email=" + email;
				
				//Run the query against the database.
				Statement stmt = c.createStatement();
				
				stmt.executeQuery(str);
				
				str = "UPDATE customer set email=" + email
						+"set creditCardNum=" + credit
						+ ",accountNum=" + AccNum
						+ ",accountCreationDate=" + createDate
						+ ",preferences=" + pref
						+ ",WHERE email=" + email;
			
				//Run the query against the database.
				stmt = c.createStatement();
			
				stmt.executeQuery(str);
				
				AWS.close(c);
				
				
			} catch (Exception e){
				throw new Error(e);
			}
			
		}
		
		//Update Employee Account Info****
				public static void updateEmp(String user, String pass, String email, String tel, String first, String last, String address, String city, 
						int zip, String state, String role, double rate, String startD, int ssn){
					
					Connection c = AWS.connect();
					
					try {
						
						String str = "UPDATE systemUser set email=" + email
									+"set telephone=" + tel
									+ ",firstName=" + first
									+ ",lastName=" + last
									+ ",city=" + city
									+ ",zipCode=" + zip
									+ ",address=" + address
									+ ",state=" + state
									+ ",WHERE email=" + email;
						
						//Run the query against the database.
						Statement stmt = c.createStatement();
						
						stmt.executeQuery(str);
						
						str = "UPDATE employee set email=" + email
								+"set jobRole=" + role
								+ ",hourlyRate=" + rate
								+ ",startDate=" + startD
								+ ",ssn=" + ssn
								+ ",WHERE email=" + email;
					
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
	
				String str = "DELETE FROM systemUser WHERE email=" + email;
				
				//Run the query against the database.
				Statement stmt = c.createStatement();
				
				stmt.executeQuery(str);
				
				str = "DELETE FROM customer WHERE email=" + email;
				
				//Run the query against the database.
				stmt = c.createStatement();
				
				stmt.executeQuery(str);
				
				AWS.close(c);
				
				
			} catch (Exception e){
				throw new Error(e);
			}
			
		}
		
		//Delete Employee Account
				public static void deleteEmp(String email){
					
					Connection c = AWS.connect();
					
					try {
			
						String str = "DELETE FROM systemUser WHERE email=" + email;
						
						//Run the query against the database.
						Statement stmt = c.createStatement();
						
						stmt.executeQuery(str);
						
						str = "DELETE FROM employee WHERE email=" + email;
						
						//Run the query against the database.
						stmt = c.createStatement();
						
						stmt.executeQuery(str);
						
						AWS.close(c);
						
						
					} catch (Exception e){
						throw new Error(e);
					}
					
				}
	
	//Check to see if user account exists
		public static String selectUser(Connection c, String email, String pass){
			
			String id = "";
			
			try {
				
				//Make a SELECT query 
				String str = "SELECT user FROM systemUser WHERE email=? AND password=?";
				
				PreparedStatement ps = c.prepareStatement(str);
				ps.setString(1, email);
				ps.setString(2, pass);

				
				//Run the query against the database.
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()){
					id = rs.getString("user");
				}
				
				return id;
				
			} catch (Exception e){
				throw new Error(e);
			}
			
		}
			
		
	//ADMIN DASHBOARD INFO (In Progress)
		
		
	//Sales Report
	public static String[][] salesReport(int month){
			
			Connection c = AWS.connect();
			
			try {
				
				String str = "SELECT resNum, bookingFee, totalFare, customerRep, fareRestrictions"
						+ "FROM reservation r "
						+ "WHERE r.resDate LIKE " + month;
				
				//Run the query against the database.
				Statement stmt = c.createStatement();
				
				ResultSet rs = stmt.executeQuery(str);
				
				AWS.close(c);
				
				return null;
				
			} catch (Exception e){
				throw new Error(e);
			}
			
		}

	//All Flights
	public static String[][] allFlights(){
		
		Connection c = AWS.connect();
		
		try {
			
			//Make a SELECT query 
			String str = "SELECT *"
					+ "FROM flight";
			
			//Run the query against the database.
			Statement stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery(str);
			
			AWS.close(c);
			
			return null;
			
		} catch (Exception e){
			throw new Error(e);
		}
		
	}
	
	//Reservations by Num
		public static String[][] reservationsNum(int num){
			
			Connection c = AWS.connect();
			
			try {
				
				//Make a SELECT query 
				String str = "SELECT r.resNum, r.passengers,r.legs, r.bookingFee, r.totalFare, r.fareRestrictions, r.customerRep, r.resDate "
						+ "FROM flight   f, reservation   r, madeFor   m "
						+ "WHERE f.flyNum   = " + num 
						+ "AND f.flyNum   =   m.flyNum "
						+ "AND m.resNum   =   r.resNum;";
				
				//Run the query against the database.
				Statement stmt = c.createStatement();
				
				ResultSet rs = stmt.executeQuery(str);
				
				AWS.close(c);
				
				return null;
				
			} catch (Exception e){
				throw new Error(e);
			}
			
		}
		
		//Reservations by Name
				public static String[][] reservationsName(String first, String last, String email){
					
					Connection c = AWS.connect();
					
					try {
						
						//Make a SELECT query 
						String str = "SELECT r.resNum, r.passengers,r.legs, r.bookingFee, r.totalFare, r.fareRestrictions, r.customerRep, r.resDate "
								+ "FROM systemUser s, reservation   r, createRes c "
								+ "WHERE s.firstName   = " + first 
								+ "AND s.lastName   = " + last
								+ "AND s.email   = c.email"
								+ "AND c.resNum = r.resNum";
						
						//Run the query against the database.
						Statement stmt = c.createStatement();
						
						ResultSet rs = stmt.executeQuery(str);
						
						AWS.close(c);
						
						return null;
						
					} catch (Exception e){
						throw new Error(e);
					}
					
				}
		
	//Most Active Flights
		public static int activeFlights(int num){
			
			Connection c = AWS.connect();
			
			try {
				
				//Make a SELECT query 
				String str = "select flyNum, count(*) as num "
								+ "from  madeFor "
								+ "group by flyNum "
								+ "order by num desc";
				
				//Run the query against the database.
				Statement stmt = c.createStatement();
				
				ResultSet rs = stmt.executeQuery(str);
				
				int active[] = new int[4];
				
				int i = 0;
				
				while(rs.next() && i < 5){
					active[i] = rs.getInt("flyNum");
					i++;
				}
				
				AWS.close(c);
				
				if(active.length <= num){
					return 0;
				}
				
				return active[num];
				
			} catch (Exception e){
				throw new Error(e);
			}
			
		}
		
		//Get all customers
		public static ResultSet allCustomers(){
			
			Connection c = AWS.connect();
			
			try {
				
				//Make a SELECT query 
				String str = "SELECT c.accountNum, s.firstName, s.lastName, s.email, s.telephone"
						+ "FROM systemUser s, customer c";
				
				//Run the query against the database.
				Statement stmt = c.createStatement();
				
				ResultSet rs = stmt.executeQuery(str);
				
				AWS.close(c);
				
				return rs;
				
			} catch (Exception e){
				throw new Error(e);
			}
			
		}
		
}