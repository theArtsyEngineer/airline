<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="jsp.*"%>

    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Reservations</title>
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
            crossorigin="anonymous">
        <link rel="stylesheet" href="css/bootflat.min.css">
        <link rel="stylesheet" href="css/bootflat.scss">
        <link rel="stylesheet" href="css/main.css">

        <body>

            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="container">
                    <%=Template.getNav()%>
                    <%=Template.getUser(session.getAttribute(Template.username))%>
                </div>
            </nav>

            <div class="container">
                <div class="row">
                    <div class="col-xs-12">
                        <h2>Reservations</h2>
                        <hr>
                    </div>
                </div>

                <!-- Reservations By Flight -->
                <div class="row">
                    <div class="col-xs-12 panel panel-default">
                        <div class="panel-body active-flights">
                            <h2 class="text-center">Reservations By Flight</h2>
                            <form class="form-inline pull-right" method="post" action="reservations.jsp">
                                <div class="form-group">
									<input type="text" class="form-control" id="flightNum" name="flightNum" placeholder="Enter flight number" >
								</div>
								
								<button type="submit" class="btn btn-primary" name="submit" value="submit">Select</button>
                            </form>
                            <table class="table">
                            	<thead>
							<tr>
								<th>Airline</th>
								<th>Reservation Num</th>
								<th>Passengers</th>
								<th>Legs</th>
								<th>Fee</th>
								<th>Total Fare</th>
								<th>Restrictions</th>
								<th>Customer Rep</th>
								<th>Date Created</th>
							</tr>
						</thead>
                                <tbody>
                                    <%if(request.getParameter("flightNum") != null){%>
										<%=ReservationController.reservationsNum(Integer.parseInt(request.getParameter("flightNum"))) %>
									<%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                
                 <!-- Reservations Per Customer -->
                <div class="row">
                    <div class="col-xs-12 panel panel-default">
                        <div class="panel-body active-flights">
                            <h2 class="text-center">Reservations By Customer</h2>
                            <form class="form-inline pull-right" method="post" action="reservations.jsp">
                                <div class="form-group">
									<input type="text" class="form-control" id="email" name="email" placeholder="Enter customer email" >
								</div>
								
								<button type="submit" class="btn btn-primary" name="submit" value="submit2">Select</button>
                            </form>
                            
                            <table class="table">
                            <thead>
                            <%if(request.getParameter("email") != null){%>
								<h2><%= request.getParameter("email") %>'s Reservations</h2>
							<%}%>
							<tr>
								<th>Airline</th>
								<th>Reservation Num</th>
								<th>Passengers</th>
								<th>Legs</th>
								<th>Fee</th>
								<th>Total Fare</th>
								<th>Restrictions</th>
								<th>Customer Rep</th>
								<th>Date Created</th>
							</tr>
						</thead>
                                <tbody>
                                    <%if(request.getParameter("email") != null){%>
										<%=ReservationController.reservationsEmail(request.getParameter("email")) %>
									<%}%>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                
                </div>
                <!-- /container -->
        </body>

    </html>
