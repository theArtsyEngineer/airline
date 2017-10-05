<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="java.io.*,java.util.*,jsp.*,java.sql.*"%>

<%if(request.getParameter("submit") != null){%>
	<%
	
	//Read Form Info
    String user = request.getParameter("user");
    String pass = request.getParameter("pass");
    String email = request.getParameter("email");
    
    String newEmail = request.getParameter("new_email");
    String telephone = request.getParameter("telephone");
    String first = request.getParameter("first");
    String last = request.getParameter("last");
    String city = request.getParameter("city");
    String zip = request.getParameter("zip");
    String address = request.getParameter("address");
    String state = request.getParameter("state");
    String credit = request.getParameter("credit");
    String redir = null;
	
        
    AcctController.updateCust(user, pass, email, newEmail, telephone, first, last, address, city, zip, state, Integer.parseInt(credit));
    response.sendRedirect("users.jsp");
		
		%>
<%}%>
        
      


            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Update Existing Customer</title>
                <!-- Bootstrap core CSS -->
                <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
                    crossorigin="anonymous">
                <link rel="stylesheet" href="css/bootflat.min.css">
                <link rel="stylesheet" href="css/bootflat.scss">
                <link rel="stylesheet" href="css/main.css">
            </head>

            <body>
                <nav class="navbar navbar-default navbar-fixed-top">
                    <div class="container">
                        <%=Template.getNav()%>
                            <%=Template.getUser( session.getAttribute(Template.username) )%>
                    </div>
                </nav>

                <div class="container">
                    <!-- Update User-->
                    <h3>Update Customer Info</h3>

                    <form class="form" method="post" action="editCust.jsp">


                        <div class="row">

                            <div class="col-xs-12 panel panel-default">
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label for="email">User's Old Email:</label>
                                        <input type="text" class="form-control" id="email" name="email" placeholder="email" >
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="new_email">New Email:</label>
                                        <input type="text" class="form-control" id="new_email" name="new_email" placeholder="New email" >
                                    </div>

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
                                                <input type="text" class="form-control" id="zip" name="zip" placeholder="Enter zip code" >
                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="phone"> Phone Number:</label>
                                        <input type="text" class="form-control" id="phone" name="phone" placeholder="Enter phone number" >
                                    </div>

                                    <div class="form-group">
                                        <label for="credit"> Credit Card Number:</label>
                                        <input type="text" class="form-control" id="credit" name="credit" placeholder="credit" >
                                    </div>

                                    <button type="submit" class="btn btn-primary btn-block" name="submit" value="submit">Update User</button>
                                </div>

                            </div>

                        </div>

                    </form>
                </div>

            </body>

            </html>