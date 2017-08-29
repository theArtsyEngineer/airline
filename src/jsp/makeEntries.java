package jsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

//MYSQL database connection management

public class AWS {
	public static void main(String[]args){
		Connection con = connect(); 
		ArrayList<String> firstNames = getNames("first-names.txt");
		ArrayList<String> lastNames = getNames("last-names.txt");
		
		Random rand = new Random();
		int numUsers = 0;
		int numEmployees = 0; 
		
		//add new users
		for(int i = 0; i < numUsers; i++){
			String first = firstNames.get(rand.nextInt(firstNames.size())) ;
			String last = lastNames.get(rand.nextInt(firstNames.size())); 
			newUser(con, 
					first+"_"+last, 
					""+first.charAt(0)+last.charAt(0), 
					first.charAt(0)+last+"@email.com");
		}
		
		//add new employees
		for(int i = 0; i < numEmployees; i++){
			String first = firstNames.get(rand.nextInt(firstNames.size())) ;
			String last = lastNames.get(rand.nextInt(firstNames.size())); 
			newUser(con, 
					first+"_"+last, 
					""+first.charAt(0)+last.charAt(0), 
					first.charAt(0)+last+"@email.com");
		}
		
	}
	
	public static ArrayList getNames(String fileName)
	{
		ArrayList<String> names = new ArrayList(); 
		BufferedReader br = null;
		FileReader fr = null; 
		
		try{
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			
			String line;
			while((line = br.readLine()) != null){
				names.add(line);
			}
			
			br.close();
			fr.close();
			return names; 
		} catch(IOException e){
			return null; 
		}
		
	}
	
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
		
		//Most Valuable Customer
				public static int valuableCust(int num){
					
					Connection c = AWS.connect();
					
					try {
						
						//Make a SELECT query 
						String str = "select c.* "
								+ "from customer c, reservation r, createRes cr "
								+ "where 	sum(select r.totalFare "
								+ "from  r, cr, c "
								+ "where c.email = cr.email and cr.resNum = r.resNum) "
								+ "= max(select sum(rn.totalFare) "
								+ "from  reservation rn, createRes crn, customer cn "
								+ "where cn.email = crn.email and crn.resNum = rn.resNum);";
						
						//Run the query against the database.
						Statement stmt = c.createStatement();
						
						ResultSet rs = stmt.executeQuery(str);
						
						int i = 0;
						int active[] = new int[4];
						
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
		
		
		
		
}