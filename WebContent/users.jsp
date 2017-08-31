<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,jsp.*,java.sql.*"%>

<%

	String email = request.getParameter("email");
	String pass = request.getParameter("pass");
	String redir = null;
	
	if(email == null || pass == null){
		email = "";
		pass = "";	
	}else{
		
		Connection c = AWS.connect();
		
		if(c != null){
			String id = AWS.selectUser(c, email, pass);
			
			if(id != ""){
				session.setAttribute(Template.username, id);
				AWS.close(c);
				response.sendRedirect("index.jsp");
			}else{
				AWS.close(c);
				response.sendRedirect("error.html");
			}
			
		}
		
	}
	
	
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Users</title>
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="https://maxcdn.bootstrapcdn.com/bootswatch/3.3.7/paper/bootstrap.min.css" rel="stylesheet" integrity="sha384-awusxf8AUojygHf2+joICySzB780jVvQaVCAt1clU3QsyAitLGul28Qxb2r1e5g+" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css">
<body>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <h3>User Information</h3>
                <hr>
            </div>
        </div>
        
        <!-- Customer and Employee List -->
        <div class="row">
            <div class="col-xs-4">
                <button type="button" class="btn btn-default btn-lg">Customer</button>
                <button type="button" class="btn btn-default btn-lg">Employee</button>  
            </div>
        </div>
        
        <div class="row ">
            <div class="col-xs-8 panel panel-default">
                <div class="panel-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Account #</th>
                                <th>Last Name</th>
                                <th>First Name</th>
                                <th>Email</th>
                                <th>Telephone</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                    </table>
                </div>
            </div>
            
            <div class="col-xs-3 col-xs-offset-1">
                    <h2>Add User</h2>
                    <form action="post">
                        <div class="form-group">
                            <label for="inputFirst">First Name</label>
                            <input type="text" class="form-control" id="inputFirst" placeholder="First Name">
                        </div>
                        <div class="form-group">
                            <label for="inputFirst">Last Name</label>
                            <input type="text" class="form-control" id="inputFirst" placeholder="First Name">
                        </div>
                        <div class="form-group">
                            <label for="inputEmail">Email Address</label>
                            <input type="email" class="form-control" id="inputEmail" placeholder="Email">
                        </div>
                        <div class="form-group">
                            <label for="inputPass">Password</label>
                            <input type="password" class="form-control" id="inputPass" placeholder="Password">
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Add User</button>
                    </form>
            </div>
        </div>
    </div>
        
    <!-- /container -->
</body>

</html>
