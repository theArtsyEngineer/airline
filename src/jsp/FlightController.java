package jsp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class FlightController {
	
	//Show Flight Delays
	public static String flightDelays() {

		Connection c = AWS.connect();

		try {

			//Make a SELECT query 
			String str = "SELECT * " + 
						 "FROM flight f, stopsAt s, airport a " + 
						 "WHERE f.flyNum = s.flyNum " +
							 "AND a.apid = s.apid " + 
							 "AND s.arriveOnTime = false " + 
							 "AND s.departOnTime = false";

			//Run the query against the database.
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(str);

			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();

			String list = "";
			String colVal = "";
			String colName = "";

			while (rs.next()) {

				list = list + "<tr><td>N/A</td>";

				for (int i = 1; i <= colNum; i++) {
					colVal = rs.getString(i);

					colName = rsmd.getColumnName(i);

					if (colName.equals("advancePurchase") || colName.equals("lengthOfStay")) {

					} else {
						list = list + "<td>" + colVal + "</td>";
					}

				}

				list = list + "</tr>";
			}

			AWS.close(c);

			return list;

		} catch (Exception e) {
			throw new Error(e);
		}

	}

	//Show Most Active Flights
	public static int activeFlights(int num) {

		Connection c = AWS.connect();

		try {

			//Make a SELECT query 
			String str = "SELECT flyNum, count(*) AS num " + 
						 "FROM  madeFor " + 
						 "GROUP BY flyNum " +
						 "ORDER BY num desc";

			//Run the query against the database.
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(str);

			int active[] = new int[4];

			int i = 0;

			while (rs.next() && i < 4) {
				active[i] = rs.getInt("flyNum");
				i++;
			}

			AWS.close(c);

			if (active.length <= num) {
				return 0;
			}

			return active[num];

		} catch (Exception e) {
			throw new Error(e);
		}

	}

	//Show Flights With Most Passengers
	public static String mostPass(int num) {
		
		Connection c = AWS.connect();

		try {

			//Make a SELECT query 
			String str = "SELECT f.* " + "FROM flight f, reservation r, madeFor m " + "WHERE SUM( SELECT r.passengers"
					+ "FROM f, r, m"
					+ "WHERE f.flyNum = m.flyNum AND m.resNum = r.resNum) = MAX(SELECT SUM(rn.passengers)"
					+ "FROM flight fn, reservation rn, madeFor mn"
					+ "WHERE fn.flyNum = mn.flyNum AND mn.resNum = rn.resNum)";

			//Run the query against the database.
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(str);

			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();

			String list = "";
			String colVal = "";
			String colName = "";

			while (rs.next()) {

				list = list + "<tr><td>N/A</td>";

				for (int i = 1; i <= colNum; i++) {
					colVal = rs.getString(i);

					colName = rsmd.getColumnName(i);

					if (colName.equals("advancePurchase") || colName.equals("lengthOfStay")) {

					} else {
						list = list + "<td>" + colVal + "</td>";
					}

				}

				list = list + "</tr>";
			}

			AWS.close(c);

			return list;

		} catch (Exception e) {
			throw new Error(e);
		}

	}

	//Show All Flights
	public static String allFlights() {

		Connection c = AWS.connect();

		try {

			//Make a SELECT query 
			String str = "SELECT *" + 
						 "FROM flight";

			//Run the query against the database.
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(str);

			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();

			String list = "";
			String colVal = "";
			String colName = "";

			while (rs.next()) {

				list = list + "<tr><td>N/A</td>";

				for (int i = 1; i <= colNum; i++) {
					colVal = rs.getString(i);

					colName = rsmd.getColumnName(i);

					if (colName.equals("advancePurchase") || colName.equals("lengthOfStay")) {

					} else {
						list = list + "<td>" + colVal + "</td>";
					}

				}

				list = list + "</tr>";
			}

			AWS.close(c);

			return list;

		} catch (Exception e) {
			throw new Error(e);
		}

	}

	//Show Flights From Given Airport
	public static String flightsAt(String alid) {

		Connection c = AWS.connect();

		try {

			//Make a SELECT query 
			String str = "SELECT flight.*, operatedBy.alid " + 
						 "FROM flight, operatedBy " + 
						 "WHERE operatedBy.alid ='" + alid + "' " +
					 	 	"AND flight.flyNum = operatedBy.flyNum";

			//Run the query against the database.
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(str);

			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();

			String list = "";
			String colVal = "";
			String colName = "";

			while (rs.next()) {

				for (int i = 1; i <= colNum; i++) {
					colVal = rs.getString(i);

					colName = rsmd.getColumnName(i);

					
					list = list + "<td>" + colVal + "</td>";

				}

				list = list + "</tr>";
			}

			AWS.close(c);

			return list;

		} catch (Exception e) {
			throw new Error(e);
		}

	}

	//Show Customers On Particular Flight
	public static String custOnFlight(int flyNum) {
		
		Connection c = AWS.connect();

		try {

			//Make a SELECT query 
			String str = "SELECT c.email " + 
						 "FROM createRes c, flight f, madeFor m " + 
						 "WHERE m.flyNum = " + flyNum + " " +
							 "AND c.resNum = m.resNum " + 
							 "AND m.flyNum = f.flyNum";

			//Run the query against the database.
			Statement stmt = c.createStatement();

			ResultSet rs = stmt.executeQuery(str);

			ResultSetMetaData rsmd = rs.getMetaData();
			int colNum = rsmd.getColumnCount();

			String list = "";
			String colVal = "";
			String colName = "";

			while (rs.next()) {

				list = list + "<tr><td>N/A</td>";

				for (int i = 1; i <= colNum; i++) {
					colVal = rs.getString(i);

					colName = rsmd.getColumnName(i);

					if (colName.equals("advancePurchase") || colName.equals("lengthOfStay")) {

					} else {
						list = list + "<td>" + colVal + "</td>";
					}

				}

				list = list + "</tr>";
			}

			AWS.close(c);

			return list;

		} catch (Exception e) {
			throw new Error(e);
		}

	}
}
