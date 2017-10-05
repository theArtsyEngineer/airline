<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="jsp.*"%>

        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
                crossorigin="anonymous">
            <link rel="stylesheet" href="css/bootflat.min.css">
            <link rel="stylesheet" href="css/bootflat.scss">
            <link rel="stylesheet" href="css/main.css">
            <title>Sales</title>
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
                        <h1>Sales</h1>
                        <hr>
                    </div>
                </div>

                <!-- Most Valuable Customer -->
                <div class="row  panel panel-default">
                    <div class="panel-body">
                        <div class="col-xs-6">
                            <h3>Most Valuable Customer:
                                <%=Sales.topCust()%>
                            </h3>
                        </div>
                    </div>
                </div>

                <!-- Sales Report -->
                <div class="row">
                    <div class="col-xs-12 panel panel-default sales_report">
                        <div class="panel-body">
                            <h2 class="text-center">Sales Report</h2>

                            <!--Overall Report-->
                            <div class="row">
                                <div class="col-xs-12 panel panel-default">
                                    <div class="panel-body">
                                        
                                        <form class="form-inline pull-right" method="post" action="sales.jsp">
                                        	<div class="form-group">
                                            	<label for="month">Select Month:</label>
                                            	<select class="form-control" id="month" name="month">
                                            		  <option></option>
                                                      <option value="01">January</option>
                                                      <option value="02">February</option>
                                                      <option value="03">March</option>
                                                      <option value="04">April</option>
                                                      <option value="05">May</option>
                                                      <option value="06">June</option>
                                                      <option value="07">July</option>
                                                      <option value="08">August</option>
                                                      <option value="09">September</option>
                                                      <option value="10">October</option>
                                                      <option value="11">November</option>
                                                      <option value="12">December</option>
                                           		</select>
                                           		<button type="submit" class="btn btn-primary" name="choose" value="submit">Select</button>
                                        	</div>
                                        </form>
                                        
                                        <table class="table">
                                            <thead>
                                    			<%if(request.getParameter("month") != null){%>
													<h2><%= request.getParameter("month") %></h2>
												<%}%>
                                                <tr>
                                                    <th>Reservation Number</th>
                                                    <th>Booking Fee</th>
                                                    <th>Total Fare</th>
                                                    <th>Customer Rep</th>
                                                    <th>Fare Restrictions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                
                                                <%if(request.getParameter("month") != null){%>
													<%=Sales.salesReport(Integer.parseInt(request.getParameter("month"))) %>
												<%}%>
                                                

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>


                            <!--Revenue By Flight-->
                            <div class="row">
                                <div class="col-xs-12 panel panel-default">
                                    <div class="panel-body">
                                        <h3>Revenue by Flight</h3>
	                                        <form class="form-inline pull-right" method="post" action="sales.jsp">
			                                	<div class="form-group">
												<input type="text" class="form-control" id="flight" name="flight" placeholder="Enter flight number" >
											</div>
											<button type="submit" class="btn btn-primary" name="flightSubmit" value="submit3">Select</button>
                            				</form>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Reservation Number</th>
                                                    <th>Booking Fee</th>
                                                    <th>Total Fare</th>
                                                    <th>Customer Rep</th>
                                                    <th>Fare Restrictions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
												<%if(request.getParameter("flight") != null){%>
													<%=Sales.flightRev(Integer.parseInt(request.getParameter("flight"))) %>
												<%}%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
							
							<!--Revenue By City-->
                            <div class="row">
                                <div class="col-xs-12 panel panel-default">
                                    <div class="panel-body">
                                        <h3>Revenue by City</h3>
	                                        <form class="form-inline pull-right" method="post" action="sales.jsp">
			                                	<div class="form-group">
												<input type="text" class="form-control" id="city" name="city" placeholder="Enter city" >
											</div>
											<button type="submit" class="btn btn-primary" name="city" value="submit4">Select</button>
                            				</form>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Reservation Number</th>
                                                    <th>Booking Fee</th>
                                                    <th>Total Fare</th>
                                                    <th>Customer Rep</th>
                                                    <th>Fare Restrictions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
												<%if(request.getParameter("city") != null){%>
													<%=Sales.flightRev(Integer.parseInt(request.getParameter("city"))) %>
												<%}%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            
                            <!--Revenue By Customer-->
                            <div class="row">
                                <div class="col-xs-12 panel panel-default">
                                    <div class="panel-body">
                                        <h3>Revenue by Customer</h3>
	                                        <form class="form-inline pull-right" method="post" action="sales.jsp">
			                                	<div class="form-group">
												<input type="text" class="form-control" id="customer" name="customer_first" placeholder="Enter Customer First Name" >
												<input type="text" class="form-control" id="customer" name="customer_last" placeholder="Enter Customer Las Name" >
												
											</div>
											<button type="submit" class="btn btn-primary" name="customer" value="submit5">Select</button>
                            				</form>
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th>Reservation Number</th>
                                                    <th>Booking Fee</th>
                                                    <th>Total Fare</th>
                                                    <th>Customer Rep</th>
                                                    <th>Fare Restrictions</th>
                                                </tr>
                                            </thead>
                                            <tbody>
												<%if(request.getParameter("submit5") != null){%>
													<%=Sales.custRev(request.getParameter("customer_first"), request.getParameter("customer_last")) %>
												<%}%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
            </div>


        </body>

        <script src="http://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>

        </html>