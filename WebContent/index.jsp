<%@page import="jsp.*"%>
<%@ page import="java.io.*,java.util.*,jsp.*,java.sql.*, java.text.ParseException,
java.text.SimpleDateFormat, java.util.Date
"%>

<%if(request.getParameter("submit") != null){%>
	<%
	
	//Read Form Info
	int flyNum = Integer.parseInt(request.getParameter("flightnum"));
	int pass = Integer.parseInt(request.getParameter("passengers"));
	String flyDate = request.getParameter("fDate");
	int legs = Integer.parseInt(request.getParameter("legs"));
	String pref = request.getParameter("pref");
	
	SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    Date parsed = format.parse(flyDate);
    java.sql.Date fd = new java.sql.Date(parsed.getTime());
	
	if(flyNum == 0){
		
		
	}else{
			System.out.println("Starting Creation");
			ReservationController.newRes(flyNum, pass, legs, fd, (String)session.getAttribute("email"), pref);	
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
		<title>Home</title>
	</head>

	<body>
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="container">
				<%=Template.getNav()%>
					<%=Template.getUser( session.getAttribute(Template.username) )%>
			</div>
		</nav>

		<div class="container">
			<h3 class="text-center">Welcome,
				<%= session.getAttribute(Template.username) %>
			</h3>
			<hr>
			
			<!-- Popular Flights -->
			<!-- Active Flights -->
                    <div class="row">
                        <div class="col-xs-4 col-xs-offset-4 panel panel-default">
                            <div class="panel-body active-flights">
                                <h3 class="text-center">Popular Flights</h3>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Flight</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th scope="row">1</th>
                                            <td>
                                                <%=FlightController.activeFlights(0)%>
                                            </td>
                                        </tr>

                                        <tr>
                                            <th scope="row">2</th>
                                            <td>
                                                <%=FlightController.activeFlights(1)%>
                                            </td>
                                        </tr>

                                        <tr>
                                            <th scope="row">3</th>
                                            <td>
                                                <%=FlightController.activeFlights(2)%>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
			
			
			<!-- Search Flights -->
			<h3 class="text-center">Flights Listing</h3>

			<div class="row">
				<div class="col-xs-12">
					<table class="table panel panel-default">
						<thead>
							<tr>
								<th>Flight Num</th>
								<th>Stops</th>
								<th>Seats</th>
								<th>Fare</th>
								<th>Days of Week</th>
							</tr>
						</thead>

						<tbody>
							<%= FlightController.allFlights()%>
						</tbody>
					</table>
				</div>
			</div>


			<!-- Make Reservation -->
			<h3 class="text-center">Make Reservation</h3>
			
			<div class="row">
				<div class="row">
				<div class="col-xs-12">
					<div class="btn-group btn-group-justified">
						<a href="#" class="btn btn-primary">One-Way</a>
						<a href="#" class="btn btn-primary">Round-Trip</a>
						<a href="#" class="btn btn-primary">Multi-City</a>
						<a href="#" class="btn btn-primary">Domestic or International</a>
						<a href="#" class="btn btn-primary">Flexible Date/Time</a>
					</div>
				</div>
				<hr>
			</div>
				<form class="form-inline" method="post" action="index.jsp">
							<div class="form-group">
								<label for="flightnum">Flight Number:</label>
								<input type="text" class="flightnum" id="flightnum" name="flightnum">
							</div>
							
							<div class="form-group">
								<label for="passengers">Passengers:</label>
								<input type="text" class="passengers" id="passengers" name="passengers">
							</div>
							
							<div class="form-group">
								<label for="fDate">Fly Date:</label>
								<input type="text" class="fDate" id="fDate" name="fDate">
							</div>
							
							<div class="form-group">
								<label for="Legs">Legs:</label>
								<input type="text" class="legs" id="legs" name="legs">
							</div>
							
							<div class="form-group">
								<label for="pref">Preferences:</label>
								<input type="text" class="pref" id="pref" name="pref">
							</div>
							
							<button type="submit" class="btn btn-default btn-block" value="submit" name="submit">Make Reservation</button>
						
				</form>
			</div>

		</div>


	</body>

	<script src="http://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	 crossorigin="anonymous"></script>

	</html>