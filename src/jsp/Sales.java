package jsp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.text.DecimalFormat;

public class Sales {

    //Sales Report
    public static String salesReport(int num) {

        Connection c = AWS.connect();
        
        //Convert integer to 00 format
        String month = new DecimalFormat("00").format(num);       

        try {

            //Make a SELECT query 
            String str = "SELECT resNum, bookingFee, totalFare, customerRep, fareRestrictions " + 
                         "FROM reservation r " +
                         "WHERE r.resDate LIKE '%-" + month + "-%'";
        
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

    //Sales Averages
    public static String salesAvg() {
        
        Connection c = AWS.connect();

        try {

            //Make a SELECT query 
            String str = "SELECT COUNT(resNum) AS Number_of_reservations, " + 
                            "SUM() AS Number_of_passengers, " +
                            "AVG() AS Avg_number_of_passengers_flight, " + 
                            "AVG(totalFare) AS Avg_total_fare " + 
                         "SUM(totalFare) AS Sum_total_fare " + 
                         "FROM reservation " +
                         "WHERE flyDate >= startOfMonth and flyDate <= endOfMonth";
                
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

    //Revenue By Flight
    public static String flightRev(int flyNum) {

        Connection c = AWS.connect();

        try {

            //Make a SELECT query 
            String str = "SELECT madeFor.flyNum, SUM(reservation.totalFare) " +
                         "FROM madeFor, reservation, flight " +
                         "WHERE madeFor.flyNum = " + flyNum + " " +  
                            "AND madeFor.resNum = reservation.resNum GROUP BY madeFor.flyNum";

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

    //Revenue By City
    public static String cityRev(String city) {
        
        Connection c = AWS.connect();

        try {

            //Make a SELECT query 
            String str = "SELECT flight.stops, SUM(reservation.totalFare) FROM madeFor, reservation, flight WHERE flight.stops = " + city + " AND madeFor.resNum = reservation.resNum AND madeFor.flyNum = flight.flyNum GROUP BY flight.stops";
        
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

            return list;

        } catch (Exception e) {
            throw new Error(e);
        }

    }

    //Revenue By Customer
    public static String custRev(String first, String last) {

        Connection c = AWS.connect();

        try {

            //Make a SELECT query 
            String str = "SELECT systemUser.firstname, systemUser.lastname, SUM(reservation.totalFare) " + 
                         "FROM reservation, systemUser, createRes, customer " +
                         "WHERE systemUser.firstName = " + first + " " + 
                            "AND systemUser.lastName = " + last + " " + 
                            "AND systemUser.email IN (customer.email) " +
                            "AND systemUser.email IN (createRes.email) GROUP BY systemUser.lastName";

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

            return list;

        } catch (Exception e) {
            throw new Error(e);
        }

    }

    //Top Customer
    
    public static String topCust() {

        Connection c = AWS.connect();

        try {

            //Make a SELECT query 
            String str = "SELECT c.email, SUM(r.totalFare) AS total " +
                         "FROM customer c, reservation r, createRes cr " +
                         "WHERE c.email = cr.email AND cr.resNum = r.resNum GROUP BY c.email ORDER BY total desc";

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
                break;
            }

            AWS.close(c);

            return list;

        } catch (Exception e) {
            throw new Error(e);
        }

    }
    

}
