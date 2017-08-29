<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,jsp.*,java.sql.*"%>

<%

	String user = request.getParameter("user");
	String pass = request.getParameter("pass");
	String email = request.getParameter("email");
	String redir = null;
	
	if(user == null){
		user = "";
		pass = "";
		email = "";
	}else{
		
		Connection c = AWS.connect();
		
		if(c != null){
			long id = AWS.newUser(c, user, pass, email);
			
			if(id != -1){
				session.setAttribute(Template.username, id);
				AWS.close(c);
				
				response.sendRedirect("index.html");
			}
			
		}
		
	}
	
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alt Log</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="css/bootflat.min.css">
<link rel="stylesheet" href="css/bootflat.scss">
<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<%=Template.getNav()%>
    		<%=Template.getUser(session.getAttribute(Template.username))%>
		</div>
    </nav>
  
  <div class="container">

  	<form class="form" method="post" action="signup.jsp">
    	<div class="form-group">
      		<label for="user">Username:</label>
      		<input type="text" class="form-control" id="user" name="user" placeholder="Enter username" value="<%=user%>">
    	</div>
    
    	<div class="form-group">
      		<label for="pass">Password:</label>
      		<input type="password" class="form-control" id="pass" name="pass" placeholder="Enter password" value="<%=pass%>">
    	</div>
    	
    	<div class="form-group">
      		<label for="email">Email:</label>
      		<input type="text" class="form-control" id="email" name="email" placeholder="Enter email" value="<%=email%>">
    	</div>

    	<button type="submit" class="btn btn-default">Log In</button>
  	</form>
  	
  </div>
  
</body>
</html>