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
                <div class="row">s
                    <div class="col-xs-12">
                        <h1>Sales</h1>
                        <hr>
                    </div>
                </div>

        
                            
                            <!--Revenue By Customer-->
                            <div class="row">
                                <div class="col-xs-12 panel panel-default">
                                    <div class="panel-body">
                                        <h3>Revenue by Customer</h3>
	                                        <form class="form-inline pull-right" method="post" action="salesCustomer.jsp">
			                                	<div class="form-group">
													<input type="text" class="form-control" id="customer" name="customer_first" placeholder="First Name" >
													<input type="text" class="form-control" id="customer" name="customer_last" placeholder="Last Name" >
												</div>
											<button type="submit" class="btn btn-primary" name="customerSubmit" value="submit9">Select</button>
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
												<%if(request.getParameter("customerSubmit") != null){%>
													<%=Sales.custRev(request.getParameter("customer_first"), request.getParameter("customer_last"))%>
												<%}%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>


                        </div>
                    


        </body>

        <script src="http://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>

        </html>