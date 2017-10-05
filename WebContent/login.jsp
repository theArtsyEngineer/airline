<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ page import="java.io.*,java.util.*,jsp.*,java.sql.*"%>

		<%

	String email = request.getParameter("email");
	String pass = request.getParameter("pass");
	String redir = null;
	
	if(email == null || pass == null){
		email = "";
		pass = "";	
	}else{
		
		Connection c = AWS.connect();
		
		if(c != null){
			String[] id = AWS.selectUser(c, email, pass);
			
			if(id != null){
				session.setAttribute(Template.username, id[0]);
				session.setAttribute(Template.email, id[1]);
				
				AWS.close(c);
				response.sendRedirect("index.jsp");
			}else{
				AWS.close(c);
				response.sendRedirect("error.html");
			}
			
		}
		
	}
	
	
%>

			<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
			<html>

			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
				<title>Login</title>
				<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
				 crossorigin="anonymous">
				<link rel="stylesheet" href="css/bootflat.min.css">
				<link rel="stylesheet" href="css/bootflat.scss">
				<link rel="stylesheet" href="css/main.css">
			</head>

			<body>

				<div class="container">

					<div class="row" style="margin-top: 10%;">
						<div class="col-xs-4 col-xs-offset-4">
							<form class="form" method="post" action="login.jsp">
								<img src="img/travel-4.png" alt="" class="img-responsive center-block" style="width:50%; padding-top:10%;">
								<h3 class="text-center" style="padding-bottom:5%;">336 Flights</h3>

								<div class="form-group">


									<input type="text" class="form-control" id="email" name="email" placeholder="Email" value="<%=email%>">


								</div>

								<div class="form-group">


									<input type="password" class="form-control" id="pass" name="pass" placeholder="Password" value="<%=pass%>">


								</div>

								<button type="submit" class="btn btn-primary btn-block">Log In</button>
								<button type="button" class="btn btn-success btn-block"><a href="signup.jsp">Sign Up</a></button>
							</form>

						</div>
					</div>

				</div>

			</body>

			</html>