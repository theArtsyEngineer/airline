package jsp;

public class Template {
	public static final String[] visible = new String[]{ "Home" };
	public static final String[] link = new String[]{ "index.jsp" };

	public static String getNav(){
		
		String s = "";
		
		for(int i = 0; i < visible.length; i++){
			s += "<p class=\"navbar-text\"><a href=\"" + link[i] + "\" class=\"navbar-link\">" + visible[i] + "</a></p>";
		}
		
		return s;
	}

	public static String getUser(Object user){
		
		//user = "nate";
		
		if(user == null){
			return "<a href=\"login.jsp\" role=\"button\" class=\"navbar-link btn btn-primary navbar-btn navbar-right\">Log In/Register</a>";
		}
		
		//Navbar Links
		String account = "<li><a href=\"customerAccount.jsp\">Profile</a></li>";
		String reservations = "<li><a href=\"myReserv.jsp\">My Reservations</a></li>";
		
		String signedIn = "<li><p class=\"navbar-text\">Signed in as " + user + "</p></li>";
		String logout = "<li><button type=\"button\" class=\"btn btn-success navbar-btn\"><a href=\"logout.jsp\">Log Out</a></button></li>";
		
		if(user.equals("natelad") || user.equals("Anthony")){
			String sales = "<li><a href=\"sales.jsp\" class=\"navbar-link\">Sales</a></li>";
			String reserve = "<li><a href=\"reservations.jsp\" class=\"navbar-link\">Reservations</a></li>";
			String flights = "<li><a href=\"flights.jsp\" class=\"navbar-link\">Flights</a></li>";
			String users = "<li><a href=\"users.jsp\" class=\"navbar-link\">Users</a></li>";
			return "<ul class=\"nav navbar-nav\">" + reservations + account + sales + reserve + flights + users + "</ul>" + "<ul class=\"nav navbar-nav navbar-right\">" + signedIn + logout + "</ul>";
		}
		
		return "<ul class=\"nav navbar-nav\">" + reservations + account + "</ul>" + "<ul class=\"nav navbar-nav navbar-right\">" + signedIn + logout + "</ul>";	
		
	}

	public static final String username = "user";
	public static final String email = "email";
}
