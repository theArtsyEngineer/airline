package jsp;
import java.sql.*;

public class peek {

	public static void main(String[] args) {
		
		Connection c = AWS.connect();
			
		try{
			
			String str = "SELECT * FROM airport";
			
			Statement stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery(str);
			
			ResultSetMetaData rsmd = rs.getMetaData();
			
			int columnsNumber = rsmd.getColumnCount();
			
			while (rs.next()) {
				for (int i = 1; i <= columnsNumber; i++) {
					
					if (i > 1){ 
						System.out.print(",  ");
					}
				    
					String columnValue = rs.getString(i);
					
				    System.out.print(columnValue + " " + rsmd.getColumnName(i));
					System.out.println("");
				}
			}
			
			AWS.close(c);
			
		} catch (Exception e){
			throw new Error(e);
		}
		
		
		

	}
}
