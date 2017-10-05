package jsp;

import java.sql.*;
import java.util.Calendar;
import java.util.Random;

public class ReservationController {
	
    //Reservations by Flight Num
    public static String reservationsNum(int num) {

        Connection c = AWS.connect();

        try {

            //Make a SELECT query 
			String str = "SELECT r.resNum, r.passengers,r.legs, r.bookingFee, r.totalFare, r.fareRestrictions, r.customerRep, r.resDate "
				+ "FROM flight f, reservation r, madeFor m "
				+ "WHERE f.flyNum   = " + num
				+ " AND f.flyNum   =   m.flyNum"
				+ " AND m.resNum   =   r.resNum";
            
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
    
    //Reservations by Name
    public static String reservationsName(String first, String last) {

        Connection c = AWS.connect();

        try {

            //Make a SELECT query 
			String str = "SELECT r.resNum, r.passengers,r.legs, r.bookingFee, r.totalFare, r.fareRestrictions, r.customerRep, r.resDate "
						+ "FROM systemUser s, reservation   r, createRes c "
						+ "WHERE s.firstName  = " + first 
						+ " AND s.lastName   = " + last
						+ " AND s.email   = c.email"
						+ " AND c.resNum = r.resNum";

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

    //Record New Reservation(Customer)
    public static void newRes(int flyNum, int pass, int legs, 
    Date flyDate, String email, String pref) {

      Connection c = AWS.connect();
       
      String restrict = "some restrictions";
      int bookFee = 0;
      double totalFare = 0;
      
      //SELECT Flight Info
      try {
    	  String str = "SELECT fare FROM flight WHERE flight.flyNum = flyNum";
    	  Statement stmt = c.createStatement();
    	  
    	  ResultSet rs = stmt.executeQuery(str);
    	  
    	  rs.next();
    	  double fare = rs.getInt(1);
    	  
    	  totalFare = pass * fare;
    	  
    	  
    	  
      } catch(Exception e) {
    	  throw new Error(e);
      }
		
		
      //Get Reservation Creation Date
      Calendar calendar = Calendar.getInstance();
      java.util.Date currentDate = calendar.getTime();
      java.sql.Date resDate = new java.sql.Date(currentDate.getTime());
      		
      //Create Reservation Number
      Random rnd = new Random();
      int resNum = 100000 + rnd.nextInt(900000) + rnd.nextInt(900000) + rnd.nextInt(900000);

        try {

            PreparedStatement ps = c.prepareStatement(
                "INSERT INTO reservation( resNum, passengers, bookingFee, totalFare, legs, flyDate, customerRep, fareRestrictions, resDate) " + 
                "VALUES (?,?,?,?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, resNum);
            ps.setInt(2, pass);
            ps.setInt(3, bookFee);
            ps.setDouble(4, totalFare);
            ps.setInt(5, legs);
            ps.setDate(6, flyDate);
            ps.setString(7, "None");
            ps.setString(8, restrict);
            ps.setDate(9, resDate);

            ps.execute();

            ps.close();

        } catch (Exception e) {
            throw new Error(e);
        }
        
        try{
        	PreparedStatement ps = c.prepareStatement( "INSERT INTO madeFor(flyNum, resNum) VALUES (?,?)",  Statement.RETURN_GENERATED_KEYS);
        	ps.setInt(1,flyNum);
        	ps.setInt(2,resNum);
        	
        	ps.execute();
        	 ps.close();
        	 
        }catch (Exception e){
        	throw new Error(e);
        }
        
        try{
        	PreparedStatement ps = c.prepareStatement( "INSERT INTO createRes(resNum, email, preferences) VALUES (?,?,?)",  Statement.RETURN_GENERATED_KEYS);
        	ps.setInt(1,resNum);
        	ps.setString(2,email);
        	ps.setString(3, pref);
        	
        	ps.execute();
       	 	ps.close();
        	
       	 	AWS.close(c);
       	System.out.println("New Reservation Record Created");
        }catch (Exception e){
        	
        }

    }
    
    
  //Record New Reservation(Cust Rep)
    public static void newResRep(int pass, int bookFee, int totalFare, int legs, 
    Date flyDate, String custRep, String restrict) {

      Connection c = AWS.connect();
       
      
      //SELECT Flight Info
      try {
    	  String str = "SELECT fare FROM flight WHERE flight.flyNum = flyNum";
      } catch(Exception e) {
    	  throw new Error(e);
      }
		
		
      
      //Get Account Creation Date
      Calendar calendar = Calendar.getInstance();
      java.util.Date currentDate = calendar.getTime();
      java.sql.Date resDate = new java.sql.Date(currentDate.getTime());
      		
      //Create Reservation Number
      Random rnd = new Random();
      int resNum = 100000 + rnd.nextInt(900000) + rnd.nextInt(900000) + rnd.nextInt(900000);

        try {

            PreparedStatement ps = c.prepareStatement(
                "INSERT INTO Reservation( resNum, passengers, bookingFee, totalFare, legs, flyDate, customerRep, fareRestrictions, resDate) " + 
                "VALUES (?,?,?,?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
            
            ps.setInt(1, resNum);
            ps.setInt(2, pass);
            ps.setInt(3, bookFee);
            ps.setInt(4, totalFare);
            ps.setInt(5, legs);
            ps.setDate(6, flyDate);
            ps.setString(7, custRep);
            ps.setString(8, restrict);
            ps.setDate(9, resDate);

            ps.execute();

            ps.close();

            AWS.close(c);

            System.out.println("New Reservation Record Created");

        } catch (Exception e) {
            throw new Error(e);
        }
        

    }

    //Edit Reservation
    public static void updateRes(int resNum, int pass, int bookFee, int totalFare, int legs, 
    Date flyDate, String custRep, String restrict, Date resDate) {

        Connection c = AWS.connect();
        
                try {
        
                    String str = "UPDATE Reservation " + 
                                 "SET passengers=" + pass + 
                                     ",bookingFee=" + bookFee + 
                                     ",totalFare=" + totalFare +
                                     ",legs=" + legs + 
                                     ",flyDate=" + flyDate + 
                                     ",customerRep=" + custRep + 
                                     ",fareRestrictions=" + restrict + 
                                     ",resDate=" + resDate + 
                                 "WHERE resNum=" + resNum;
        
                    //Run the query against the database.
                    Statement stmt = c.createStatement();
        
                    stmt.executeQuery(str);
        
                    AWS.close(c);

                    System.out.println("Reservation Updated");
                
                } catch (Exception e) {
                    throw new Error(e);
                }
    }

    //Delete Reservation
    public static void deleteRes(int resNum) {

        Connection c = AWS.connect();
        
                try {
                	
                	//Fix Delete Reservation Query
                    String str = "DELETE FROM reservation WHERE resNum= " + resNum;
        
                    //Run the query against the database.
                    Statement stmt = c.createStatement();
        
                    stmt.executeUpdate(str);
        
                    AWS.close(c);
                    
                    System.out.println("Reservation Deleted");
                
                } catch (Exception e) {
                    throw new Error(e);
                }
    }

    //Travel Itinerary
    public static String travelI(int resNum) {
        
                Connection c = AWS.connect();
        
                try {
        
                    //Make a SELECT query 
                    String str = "SELECT r.legs " +
                                 "FROM reservation r " +
                                 "WHERE r.resNum = " + resNum;
        
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
