<%@page import="jsp.*"%>
<%@ page import="java.io.*,java.util.*,jsp.*,java.sql.*, java.text.ParseException,
java.text.SimpleDateFormat, java.util.Date
"%>

<%if(request.getParameter("submit") != null){%>
	<%
	
	//Read Form Info
	int resNum = Integer.parseInt(request.getParameter("resNum"));
	
	if(resNum == 0){
		
		
	}else{
			System.out.println("Starting Deletion");
			ReservationController.deleteRes(resNum);
			System.out.println("Success");
			response.sendRedirect("index.jsp");
	}
%>
<%}%>



	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
		 crossorigin="anonymous">
		<link rel="stylesheet" href="css/bootflat.min.css">
		<link rel="stylesheet" href="css/bootflat.scss">
		<link rel="stylesheet" href="css/main.css">
		<title>My Reservations</title>
	</head>

	<body>
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<%=Template.getNav()%>
					<%=Template.getUser( session.getAttribute(Template.username) )%>
			</div>
		</nav>

		<div class="container">
			<h2><%= session.getAttribute("user") %>'s Reservations</h2>

			<!-- Reservation List -->
			<div class="row">
				<div class="col-xs-12">
					<table class="table panel panel-default">
						<thead>
							<tr>
								<th>Reservation Num</th>
								<th>Passengers</th>
								<th>Legs</th>
								<th>Fee</th>
								<th>Total Fare</th>
								<th>Restrictions</th>
								<th>Customer Rep</th>
								<th>Date Created</th>
								<th>Fly Date</th>
							</tr>
						</thead>

						<tbody>
							<%= ReservationController.reservationsEmail((String)session.getAttribute("email"))%>
						</tbody>
					</table>
				</div>
			</div>
			
			<!-- Cancel Reservation -->
			<form class="form-inline pull-right" method="post" action="myReserv.jsp">
				<div class="form-group">
					<label for="resNum">Cancel Reservation:</label>
					<input type="text" class="resNum" id="resNum" placeholder="Reservation number" name="resNum">
				</div>
				<button type="submit" class="btn btn-xs btn-danger" value="submit" name="submit">CONFIRM CANCELLATION</button>
			</form>			

			

		</div>


	</body>

	<script src="http://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	 crossorigin="anonymous"></script>

	</html>