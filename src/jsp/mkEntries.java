package jsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class mkEntries {
	
	public static void main(String[] args){	
		
	Connection c = AWS.connect(); 
	
	ArrayList<String> firstNames = getNames("src/jsp/firstnames.txt");
	ArrayList<String> lastNames = getNames("src/jsp/lastnames.txt");
	
	Random rand = new Random();
	int numUsers = 1;
	boolean isCustomer = false; 
	
	//for(int i = 0; i < 1; i++){generateUser(c, firstNames, lastNames, numUsers, isCustomer);}
	//for(int i = 0; i < 10; i++){generateFlight(c);}
	//for(int i = 0; i < 10; i++){generateFlight(c);}
	//generateFlight(c);

	//generateUser(c, firstNames, lastNames, numUsers, isCustomer);
	//generateFlight(c);
	generateReservation(c);
	
	System.out.println("Success");
	try {
		c.close();
	} catch (SQLException e) {
	}
	}
	
	//Get Names -------------------------------
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
	
	
	
	//Generate User -------------------------------
	public static void generateUser(Connection c, ArrayList<String> firstNames, ArrayList<String> lastNames, int numUsers, boolean isCustomer){
		
		Random rand = new Random();

		String[] addresses = {"St", "Rd", "Prk", "Ave"};
		String[] states = {"CA","NY", "NJ", "VA", "TX", "WA"};
		
		//add new users
		for(int i = 0; i < numUsers; i++){
			long id = -1;

			String first = firstNames.get(rand.nextInt(firstNames.size())) ;
			String last = lastNames.get(rand.nextInt(firstNames.size())); 
			
			String user = first.charAt(0)+last;
			
			//get city 
			
			
			//create system user 
			try {
				PreparedStatement ps = c.prepareStatement("INSERT INTO systemUser(user, password, email, telephone, firstName, lastName, city, zipcode, address, state) "
														+ "VALUES (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, user); //user
				ps.setString(2, ""+rand.nextInt(9999)); //password
				ps.setString(3, first.charAt(0)+last+"@email.com"); //email
				ps.setString(4, ""+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10)+"-"+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10)+"-"+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10)); //telephone
				ps.setString(5, first); //firstName
				ps.setString(6, last); //lastName
				ps.setString(7, "randomcity"+rand.nextInt(999)); //city
				ps.setString(8, ""+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10)+rand.nextInt(10)); //zipcode
				ps.setString(9, (rand.nextInt(1000)+1)+" NewStreet " + addresses[rand.nextInt(3)]); //address
				ps.setString(10, states[rand.nextInt(6)]); //state
				
				ps.execute();
				ps.close();
			
			} catch (Exception e){
				throw new Error(e);
			}
			
			String year = ""+rand.nextInt(2020)+2017; 
			String month = ""+rand.nextInt(12)+1;
			String day = ""+rand.nextInt(28)+1; 

			if(isCustomer){	//create customer 
				try {
					PreparedStatement ps = 
							c.prepareStatement("INSERT INTO customer(creditCardNum, accountNum, accountCreationDate, preferences, email) "
							+ "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
					
					ps.setInt(1, rand.nextInt(999999999)+100000000); //creditCardNum
					ps.setInt(2, rand.nextInt(9999999)+1000000); //accountNum
					
					Random random = new Random();
					int minDay = (int) LocalDate.of(1990, 1, 1).toEpochDay();
					int maxDay = (int) LocalDate.of(2010, 1, 1).toEpochDay();
					long randomDay = minDay + random.nextInt(maxDay - minDay);
					
					LocalDate accountC = LocalDate.ofEpochDay(randomDay);
					
					Date d = Date.valueOf(accountC);
					
					ps.setDate(3, (java.sql.Date)d); //accountCreationDate
					
					ps.setString(4, "preferences" + rand.nextInt(999)); //preferences
					ps.setString(5, first.charAt(0)+last+"@email.com"); //email
					
					ps.execute();
					ps.close();
				
				} catch (Exception e){
					throw new Error(e);
				}
			}else{	//create employee
				try {
					PreparedStatement ps = 
							c.prepareStatement("INSERT INTO employee(jobRole, hourlyRate, startDate, ssn, email) "
							+ "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
					ps.setInt(1, rand.nextInt(999)); //jobRole
					ps.setFloat(2, rand.nextFloat() * (100.00f - 7.50f) + 7.50f); //hourlyRate

					Random random = new Random();
					int minDay = (int) LocalDate.of(1990, 1, 1).toEpochDay();
					int maxDay = (int) LocalDate.of(2010, 1, 1).toEpochDay();
					long randomDay = minDay + random.nextInt(maxDay - minDay);
					
					LocalDate accountC = LocalDate.ofEpochDay(randomDay);
					
					Date d = Date.valueOf(accountC);
					
					ps.setDate(3, (java.sql.Date)d); //accountCreationDate
					
					ps.setString(4, "" + rand.nextInt(999999999)); //ssn
					ps.setString(5, first.charAt(0)+last+"@email.com"); //email

					ps.execute();
					ps.close();
				
				} catch (Exception e){
					throw new Error(e);
				}
			}
		}
	}
	
	
	
	//Generate Flight ------------------
	public static void generateFlight(Connection c){
		Random rand = new Random(); 
		int flyNum = 0; 
		
		try {
			PreparedStatement ps = 
					c.prepareStatement("INSERT INTO flight(flyNum, stops, numberOfSeats, fare, advancePurchase, daysOfTheWeek, lengthOfStay)"
					+ "VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			flyNum = rand.nextInt(99999);
			ps.setInt(1, flyNum); //flyNum
			ps.setInt(2, rand.nextInt(4) + 1); //stops
			ps.setInt(3, rand.nextInt(60) + 20); //numberOfSeats	
			
			float fair = rand.nextFloat() * (1000.00f - 1.00f) + 1.00f; 
			ps.setFloat(4, fair); //fare
			ps.setFloat(5, rand.nextFloat() * (fair)); //advancePurchase
			
			String[] days = {"M", "T", "W", "Th", "F", "S", "Su"};
			String daysOfTheWeek = ""; 
			for(int i = 0; i < rand.nextInt(7); i++){
				daysOfTheWeek += days[rand.nextInt(7)]+" ";
			}
			ps.setString(6, daysOfTheWeek); //daysOfTheWeek	
			
			int hours = rand.nextInt(50);
			int minute = rand.nextInt(59);
			ps.setString(7, hours+":"+minute); //lengthOfStay
			
			ps.execute();
			ps.close();
		
		} catch (Exception e){
			throw new Error(e);
		}
		
		//select airlines in operatedBy table 
		String[] airlines = {"AM", "BJ", "DL", "UN"}; 
		try {
			PreparedStatement ps = 
					c.prepareStatement("INSERT INTO operatedBy(alid, flyNum)"
							+ "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, airlines[rand.nextInt(4)]);
			ps.setInt(2, flyNum);
			
			ps.execute();
			ps.close();
			
		} catch (Exception e){
			throw new Error(e);
		}
		
		//insert into stopsAt table '7:00:00 PM'
		String[] airports = {"LC", "YM", "ASE", "BHM", "CLD", "DAB", "EWR", "LHD"}; 
		Time arrivalTime = generateSQLTime();
		Time departureTime = generateSQLTime();
		int arriveOnTime = 1;
		int departOnTime = 1;
		int orderNum = 0;
		try {
			PreparedStatement ps = 
				c.prepareStatement("INSERT INTO stopsAt(arrivalTime, departureTime, apid, flyNum, arriveOnTime, departOnTime, orderNum)"
						+ "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		ps.setTime(1, arrivalTime);//arrivalTime
		ps.setTime(2, departureTime);//departureTime
		ps.setString(3, airports[rand.nextInt(8)]);//apid
		ps.setInt(4, flyNum);//flyNum
		ps.setInt(5, arriveOnTime);//arriveOnTime
		ps.setInt(6, departOnTime);//departOnTime
		ps.setInt(7, orderNum);//orderNum
	
		ps.execute();
		ps.close();
		
		} catch (Exception e){
		throw new Error(e);
		}
		
	}
	
	
	
	
	//Generate Time -----------------------
	public static String generateTime(){
		Random rand = new Random(); 
		
		return (rand.nextInt(12)+1) 
				+":"+rand.nextInt(5)+rand.nextInt(9)
				+":"+rand.nextInt(5)+rand.nextInt(9);
	}
	
	public static Time generateSQLTime(){
		Random rand = new Random(); 
		int millisInDay = 24*60*60*1000;
		Time time = new Time((long)rand.nextInt(millisInDay)); 
		return time; 
		}
	
	
	
	//Generate Reservation ---------------------------
	public static void generateReservation(Connection c){
		Random rand = new Random();
		int resNum = 0;
		
		int year = rand.nextInt(2020)+2017; 
		int month = rand.nextInt(12)+1;
		int day = rand.nextInt(28)+1; 
		
		int resDay = year - rand.nextInt(28); 
		if(resDay < 0){
			
		}
		
		int resMonth = rand.nextInt(12)+1;
		int resYear = rand.nextInt(2020)+2017; 
		
		try{
			PreparedStatement ps = 
					c.prepareStatement("INSERT INTO reservation(resNum, passengers, bookingFee, totalFare, legs, flyDate, customerRep, fareRestrictions, resDate, passNum)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			resNum = rand.nextInt(999999);
			ps.setInt(1, resNum); //resNum
			ps.setString(2, "passengers ok"); //passengers
			ps.setFloat(3, rand.nextFloat() * (100.00f - 7.50f) + 7.50f); //bookingFee
			ps.setFloat(4, rand.nextFloat() * (100.00f - 7.50f) + 7.50f); //totalFare
			ps.setInt(5, rand.nextInt(5)); //legs  
			
			Random random = new Random();
			int minDay = (int) LocalDate.of(1990, 1, 1).toEpochDay();
			int maxDay = (int) LocalDate.of(2010, 1, 1).toEpochDay();
			long randomDay = minDay + random.nextInt(maxDay - minDay);
			
			LocalDate accountC = LocalDate.ofEpochDay(randomDay);
			
			Date d = Date.valueOf(accountC);
			
			ps.setDate(6, (java.sql.Date)d); //flyDate
			ps.setString(7, "someguy"); //customerRep
			ps.setString(8, "some restrictions"); //fareRestrictions
			
			random = new Random();
			minDay = (int) LocalDate.of(1990, 1, 1).toEpochDay();
			maxDay = (int) LocalDate.of(2010, 1, 1).toEpochDay();
			randomDay = minDay + random.nextInt(maxDay - minDay);
			
			accountC = LocalDate.ofEpochDay(randomDay);
			
			d = Date.valueOf(accountC);
			
			ps.setDate(9, (java.sql.Date)d); //resDate
			ps.setInt(10, rand.nextInt(99999)); //passNum
			
			ps.execute();
			ps.close();
			
		} catch(Exception e){
			throw new Error(e);
		}
		
		//select flyNum for madeFor
		try{
			PreparedStatement ps = 
					c.prepareStatement("INSERT INTO madeFor(flyNum, resNum)"
							+ "VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			//PreparedStatement fs = c.prepareStatement("SELECT flyNum FROM flight ORDER BY RAND() LIMIT 1", Statement.RETURN_GENERATED_KEYS);
			Statement stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT flyNum FROM flight ORDER BY RAND() LIMIT 1"); 
			
			rs.first();
			int flyNum = rs.getInt("flyNum"); 
			
					
			ps.setInt(1, flyNum); //flyNum
			ps.setInt(2, resNum); //resNum
			
			ps.execute();
			ps.close();
			System.out.println("madeFor success");
			
		} catch(Exception e){
			throw new Error(e);
		}
		
		//select email for generating create res
		try{
			PreparedStatement ps = 
					c.prepareStatement("INSERT INTO createRes(resNum, email, preferences)"
							+ "VALUES (?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			//PreparedStatement fs = c.prepareStatement("SELECT flyNum FROM flight ORDER BY RAND() LIMIT 1", Statement.RETURN_GENERATED_KEYS);
			Statement stmt = c.createStatement(); 
			ResultSet rs = stmt.executeQuery("SELECT email FROM customer ORDER BY RAND() LIMIT 1"); 
			
			rs.first();
			String email = rs.getString("email"); 
					
			ps.setInt(1, resNum); //resNum
			ps.setString(2, email); //email
			ps.setString(3, "heres some preferences"); //preferences
			
			ps.execute();
			ps.close();
			System.out.println("createRes success");
			
		} catch(Exception e){
			throw new Error(e);
		}
	}
	
	
}
