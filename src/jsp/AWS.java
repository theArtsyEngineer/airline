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
	
	//Check to see if user account exists
	public static String[] selectUser(Connection c, String email, String pass) {

		String[] id = new String[2];

		try {

			//Make a SELECT query 
			String str = "SELECT user FROM systemUser WHERE email=? AND password=?";

			PreparedStatement ps = c.prepareStatement(str);
			ps.setString(1, email);
			ps.setString(2, pass);

			//Run the query against the database.
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				id[0] = rs.getString("user");
				id[1] = email;
			}

			return id;

		} catch (Exception e) {
			throw new Error(e);
		}

	}
		
	//Get All Users
	public static String allUsers() {

		Connection c = AWS.connect();

		try {

			//Make a SELECT query 
			String str = "SELECT *" + "FROM systemUser";

			//Run the query against the database.
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(str);

			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();

			String list = "";
			String colVal = "";
			String colName = "";
			String telephone = "";
			String email = "";
			String viewProf = "";

			String listPart = "";

			while (rs.next()) {

				list = list + "<tr>";
				listPart = "";
				
				for (int i = 1; i <= colNum; i++) {

					colVal = rs.getString(i);

					colName = rsmd.getColumnName(i);

					if (colName.equals("firstName") || colName.equals("lastName")) {
						listPart = listPart + "<td>" + colVal + "</td>";
					} else if (colName.equals("telephone")) {
						
					} else if (colName.equals("email")) {
						email = "<td>" + colVal + "</td>";
					}

				}

				list = list + listPart + telephone + email + "</tr>";
			}

			AWS.close(c);

			return list;

		} catch (Exception e) {
			throw new Error(e);
		}

	}

	//Get All Customers
	public static ResultSet allCustomers() {

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

		} catch (Exception e) {
			throw new Error(e);
		}

	}
	
	//Mailing List
	public static ResultSet mailList() {

		Connection c = AWS.connect();

		try {

			//Make a SELECT query 
			String str = "SELECT customer.email FROM customer;";

			//Run the query against the database.
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(str);

			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();

			String list = "";
			String colVal = "";

			while (rs.next()) {

				for (int i = 1; i <= colNum; i++) {
					colVal = rs.getString(i);
					list = list + "<td>" + colVal + "</td>";
				}

				list = list + "</tr>";
			}

			AWS.close(c);

			return null;

		} catch (Exception e) {
			throw new Error(e);
		}
	}
}