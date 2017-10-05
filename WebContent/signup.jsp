<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,jsp.*,java.sql.*"%>

<%if(request.getParameter("submit") != null){%>
<%
	
	//Read Form Info
	String user = request.getParameter("user");
	String pass = request.getParameter("pass");
	String email = request.getParameter("email");
	
	String telephone = request.getParameter("telephone");
	String first = request.getParameter("first");
	String last = request.getParameter("last");
	String city = request.getParameter("city");
	String zip = request.getParameter("zip");
	String address = request.getParameter("address");
	String state = request.getParameter("state");
	int credit = Integer.parseInt(request.getParameter("credit"));
	String redir = null;
		
	if(user == null){
		
	}else{
		Connection c = AWS.connect();
		
		
		System.out.println("Starting Creation");
		String[] id = AcctController.newCust(c, user, pass, email, telephone, first, last, city, zip, address, state, credit);
			
		
		session.setAttribute(Template.username, id[0]);
		session.setAttribute(Template.email, id[1]);
		AWS.close(c);
			
		System.out.println("Success");
		response.sendRedirect("index.jsp");
	}
	
%>
<%}%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Log In</title>
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
		
    	
    	<div class="row">
		
			<div class="col-xs-6 col-xs-offset-3">

				<div class="panel panel-default">
					<div class="panel-body">
							<h3 class="text-center">Let's Get Started</h3>
							<hr>
							<h4 class="text-center">Personal Information</h4>
							<div class="form-group">
								<label for="first">First Name:</label>
								<input type="text" class="form-control" id="first" name="first" placeholder="Enter first name" >
							</div>
			
							<div class="form-group">
								<label for="last">Last Name:</label>
								<input type="text" class="form-control" id="last" name="last" placeholder="Enter last name" >
							</div>
			
							<div class="form-group">
								<label for="address">Address:</label>
								<input type="text" class="form-control" id="address" name="address" placeholder="Enter address" >
							</div>
			
							<div class="row">
								<div class="col-xs-7">
									<div class="form-group">
										<label for="city">City:</label>
										<input type="text" class="form-control" id="city" name="city" placeholder="Enter city" >
									</div>
								</div>
								<div class="col-xs-2">
									<div class="form-group">
										<label for="state">State:</label>
										<input type="text" class="form-control" id="state" name="state" placeholder="state" >
									</div>
								</div>
								<div class="col-xs-3">
									<div class="form-group">
										<label for="zip">Zip Code:</label>
										<input type="text" class="form-control" id="zip" name="zip" placeholder="000000" >
									</div>
								</div>
							</div>
			
							<div class="form-group">
								<label for="phone"> Phone Number:</label>
								<input type="text" class="form-control" id="phone" name="phone" placeholder="Enter phone number" >
							</div>
			
							<div class="form-group">
								<label for="credit"> Credit Card Number:</label>
								<input type="text" class="form-control" id="credit" name="credit" placeholder="credit">
							</div>
			
							<hr>
							<h4 class="text-center">Login Info</h4>

							<div class="form-group">
								<label for="email">Enter Your Email:</label>
								<input type="text" class="form-control" id="email" name="email" placeholder="email">
							  </div>
			
							<div class="form-group">
								<label for="user">Choose a Username:</label>
								<input type="text" class="form-control" id="user" name="user" placeholder="username">
							  </div>
				  
							  <div class="form-group">
								<label for="pass">Choose a Password:</label>
								<input type="password" class="form-control" id="pass" name="pass" placeholder="password" >
							  </div>	
							
							<button type="submit" class="btn btn-primary btn-block" name="submit" value="submit">Sign Up</button>		
					</div>
					
				</div>
				
				
				
			</div>
    		
		</div>
		
  	</form>
  	
  </div>
  
</body>
</html>