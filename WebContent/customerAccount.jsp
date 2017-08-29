<%@page import="jsp.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
	<link rel="stylesheet" href="css/bootflat.min.css">
	<link rel="stylesheet" href="css/bootflat.scss">
	<link rel="stylesheet" href="css/main.css">
	<title>My Account</title>
</head>

<body>
	<nav class="navbar navbar-default navbar-fixed-top">
  		<div class="container">
    		<%=Template.getNav()%>
    		<%=Template.getUser( session.getAttribute(Template.username) )%>
  		</div>
	</nav>
	
	
	
</body>

<script  src="http://code.jquery.com/jquery-3.2.1.min.js"   integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="   crossorigin="anonymous"></script>

</html>