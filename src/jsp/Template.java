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
		
		String welcome = "<li><p class=\"navbar-text navbar-right\">Signed in as " + user + ":</p></li>";
		String account = "<li><a href=\"customerAccount.jsp\" class=\"navbar-link\"><span>Profile</span></a></li>";
		String reservations = "<li><a href=\"customerReservations.jsp\" class=\"navbar-link\"><span>My Reservations</span></a></li>";
		String logout = "<li><a href=\"logout.jsp\" class=\"navbar-link btn btn-primary navbar-btn\">Log Out</a></li>";
		
		if(user.equals("nate")){
			String admin = "<p class=\"navbar-text navbar-right\"><a href=\"customerAccount.jsp\" class=\"navbar-link\">Admin Dashboard</a></p>";
			return logout + admin + reservations + account + welcome;
		}
		
		return welcome + logout + reservations + account;	
		
	}

	public static final String username = "user";
}
