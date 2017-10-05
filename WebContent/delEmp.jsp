<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%@ page import="java.io.*,java.util.*,jsp.*,java.sql.*"%>

<%if(request.getParameter("submit") != null){%>
	<%
	
	//Read Form Info
    String email = request.getParameter("email");
        
    AcctController.deleteEmp(email);
    response.sendRedirect("users.jsp");
		
		%>
<%}%>

            <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>Delete Employee</title>
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
                    <h3>Delete Employee Info</h3>

                    <form class="form" method="post" action="delCust.jsp">

                        <div class="row">

                            <div class="col-xs-12 panel panel-default">
                                <div class="panel-body">
                                    <div class="form-group">
                                        <label for="email">Employee's Email:</label>
                                        <input type="text" class="form-control" id="email" name="email" placeholder="email" >
                                    </div>
                                    <button type="submit" class="btn btn-danger btn-block" name="submit" value="submit">CONFIRM DELETE Employee</button>
                                </div>
								
                            </div>

                        </div>

                    </form>
                </div>

            </body>

            </html>