<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,jsp.*,java.sql.*"%>

<%



%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="css/bootflat.min.css">
	<link rel="stylesheet" href="css/bootflat.scss">
	<link rel="stylesheet" href="css/main.css">
	<title>Dashboard</title>
</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top">
  		<div class="container">
    		<%=Template.getNav()%>
    		<%=Template.getUser( session.getAttribute(Template.username) )%>
  		</div>
	</nav>
	
	<div class="container">
        <div class="row">
            <div class="col-xs-12">
                <h1>Admin Dashboard</h1>
                <hr>
            </div>
        </div>
        
        <!-- Time and Most Valuable Customer -->
        <div class="row">
            <div class="col-xs-6 panel panel-default">
               <div class="panel-body">
                   <h2>Time</h2>
               </div>
            </div>
            <div class="col-xs-6 panel panel-default">
               <div class="panel-body">
                    <div class="row">
                    <div class="col-xs-12">
                        <h3>Top Customer</h3>
                        <h2>John Doe</h2>
                    </div>
                </div>
               </div>
            </div>
        </div>
        
        <!-- Sales Report -->
        <div class="row">
            <div class="col-xs-8 panel panel-default sales_report">
               <div class="panel-body">
                   <h2>Sales Report</h2>
               </div>
            </div>
            <div class="col-xs-4 panel panel-default active_flights">
               <div class="panel-body">
                <h2>Most Active Flights</h2>
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
                            <td><%=AWS.activeFlights(0)%></td>
                        </tr>
                        
                        <tr>
                            <th scope="row">2</th>
                            <td><%=AWS.activeFlights(1)%></td>
                        </tr>
                        
                        <tr>
                            <th scope="row">3</th>
                            <td><%=AWS.activeFlights(2)%></td>
                        </tr>
                        
                        <tr>
                            <th scope="row">4</th>
                            <td><%=AWS.activeFlights(3)%></td>
                        </tr>
                        
                        <tr>
                            <th scope="row">5</th>
                            <td><%=AWS.activeFlights(4)%></td>
                        </tr>
                    </tbody>
                </table> 
               </div>
            </div>
        </div>
    </div>
    </div>
	
	
</body>

<script  src="http://code.jquery.com/jquery-3.2.1.min.js"   integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="   crossorigin="anonymous"></script>

</html>