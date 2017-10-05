<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@page import="jsp.*"%>

        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html lang="en">

        <head>
            <meta charset="utf-8">
            <meta name="description" content="">
            <meta name="author" content="">
            <title>Flights</title>
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
                            <h2>Flights</h2>
                            <hr>
                        </div>
                    </div>

                    <!-- Delayed Flights -->
                    <div class="row">
                        <div class="col-xs-12 panel panel-default">
                            <div class="panel-body active-flights">
                                <h2>Flight Delays</h2>
                                <table class="table">
                                    <tbody>
                                        <%=FlightController.flightDelays()%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <!-- Active Flights -->
                    <div class="row">
                        <div class="col-xs-12 panel panel-default">
                            <div class="panel-body active-flights">
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

                                        <tr>
                                            <th scope="row">4</th>
                                            <td>
                                                <%=FlightController.activeFlights(3)%>
                                            </td>
                                        </tr>

                                        <tr>
                                            <th scope="row">5</th>
                                            <td>
                                                <%=FlightController.activeFlights(4)%>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <!-- All Flights -->
                    <div class="row">
                        <div class="col-xs-12 panel panel-default">
                            <div class="panel-body flights">
                                <div class="row">
                                    <div class="col-xs-2">
                                        <button class="btn btn-primary btn-large btn-block" type="submit">ALL FLIGHTS</button>
                                    </div>
                                    <div class="col-xs-2">
                                        <button class="btn btn-primary btn-large btn-block" type="submit">JFK</button>
                                    </div>
                                    <div class="col-xs-2">
                                        <button class="btn btn-primary btn-large btn-block" type="submit">LAX</button>
                                    </div>
                                    <div class="col-xs-2">
                                        <button class="btn btn-primary btn-large btn-block" type="submit">ONT</button>
                                    </div>
                                    <div class="col-xs-2">
                                        <button class="btn btn-primary btn-large btn-block" type="submit">MIA</button>
                                    </div>
                                    <div class="col-xs-2">
                                        <button class="btn btn-primary btn-large btn-block" type="submit">PHL</button>
                                    </div>
                                </div>
                                <hr>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Flight Num</th>
                                            <th>Stops</th>
                                            <th>Seats</th>
                                            <th>Fare</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%= FlightController.allFlights()%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <!-- LGA, PSA, NWA, TBA-->
                    <div class="row">
                        <div class="col-xs-12 panel panel-default">
                            <div class="panel-body active-flights">
                                <h2>DL</h2>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Flight Num</th>
                                            <th>Stops</th>
                                            <th>Seats</th>
                                            <th>Fare</th>
                                            <th>Adv Purchase</th>
                                            <th>Days of Week</th>
                                  			<th>Stay Time</th>
                                  			<th>Airline</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%=FlightController.flightsAt("DL")%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-12 panel panel-default">
                            <div class="panel-body active-flights">
                                <h2>AM</h2>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Flight Num</th>
                                            <th>Stops</th>
                                            <th>Seats</th>
                                            <th>Fare</th>
                                            <th>Adv Purchase</th>
                                            <th>Days of Week</th>
                                  			<th>Stay Time</th>
                                  			<th>Airline</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%=FlightController.flightsAt("AM")%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-12 panel panel-default">
                            <div class="panel-body active-flights">
                                <h2>UN</h2>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Flight Num</th>
                                            <th>Stops</th>
                                            <th>Seats</th>
                                            <th>Fare</th>
                                            <th>Adv Purchase</th>
                                            <th>Days of Week</th>
                                  			<th>Stay Time</th>
                                  			<th>Airline</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%=FlightController.flightsAt("UN")%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-12 panel panel-default">
                            <div class="panel-body active-flights">
                                <h2>BJ</h2>
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Flight Num</th>
                                            <th>Stops</th>
                                            <th>Seats</th>
                                            <th>Fare</th>
                                            <th>Adv Purchase</th>
                                            <th>Days of Week</th>
                                  			<th>Stay Time</th>
                                  			<th>Airline</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%=FlightController.flightsAt("BJ")%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    
                   <div class="row">
                        <div class="col-xs-12 panel panel-default">
                            <div class="panel-body active-flights">
                                <h2>Customers on Flight</h2>
                                <form class="form-inline pull-right" method="post" action="flights.jsp">
                                <div class="form-group">
									<input type="text" class="form-control" id="num" name="num" placeholder="Enter Flight Num" >
								</div>
								
								<button type="submit" class="btn btn-primary" name="submit" value="submit7">Select</button>
                            </form>
                                <table class="table">
                                    
                                    <tbody>
                                    	<%if(request.getParameter("num") != null){%>
											<%=FlightController.custOnFlight(Integer.parseInt(request.getParameter("num")))%>
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
