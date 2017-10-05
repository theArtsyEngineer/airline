<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,jsp.*,java.sql.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>User Information</title>
    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="css/bootflat.min.css">
	<link rel="stylesheet" href="css/bootflat.scss">
    <link rel="stylesheet" href="css/main.css">
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
                <h3 class="text-center">User Information</h3>
                <hr>
            </div>
        </div>
        
        <!-- Customer List -->
        
        <div class="row ">
        	<div class="btn-group btn-group-justified">
                    	<a href="addEmp.jsp" class="btn btn-success">Add Employee</a>
                        <a href="editEmp.jsp" class="btn btn-success">Edit Employee</a>
                        <a href="delEmp.jsp" class="btn btn-success">Delete Employee</a>
            </div>
        	
        	<div class="btn-group btn-group-justified">
                    	<a href="addCust.jsp" class="btn btn-primary">Add Customer</a>
                        <a href="editCust.jsp" class="btn btn-primary">Edit Customer</a>
                        <a href="delCust.jsp" class="btn btn-primary">Delete Customer</a>
            </div>
                 <hr>
            <div class="col-xs-12 panel panel-default">
                <div class="panel-body">
                	
                    <table class="table">
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Telephone</th>
                                <th>Email</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <%=AWS.allUsers()%>
                        </tbody>
                    </table>
                 
                </div>
                
            </div>
        </div>



    </div>
        
    <!-- /container -->
</body>

</html>
