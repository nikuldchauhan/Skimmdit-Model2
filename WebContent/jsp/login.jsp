<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.RequestDispatcher" %>
<%
HttpServletResponse httpResponse = (HttpServletResponse) response;
httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
httpResponse.setDateHeader("Expires", 0); // Proxies.
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String sss = (String) session.getAttribute("user");
	if(sss!=null)
	{
		response.sendRedirect("/jsp/linklist.jsp");
	}	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Subreddit</title>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h2>Login</h2>
<%
  String err = (String) request.getAttribute("err");
  if(err!=null)
  {
	  out.println("<div style='color:red;'>"+err+"</div>");
  }	  
%>
<div id="login" style="color:red;"></div>
<div id="errr" style="display:none;color:red;"></div>
<form id="fm" action="index?var=login" method="post">
		<h4>Username:</h4>	
		<input id="u" type="text" name="un" required/>
		<h4>Password</h4>
		<input id="p" type="password" name="pw" required/><br/><br/>
		<input type="submit" value="submit"/>
	</form>
	<br/>
	<h5>Don't have an account? <a style="text-decoration:none;color:blue;" href="index?var=signup">Sign up now</a></h5>
<h2>Links</h2>
<div id="main">
 There is no link to display
</div>
<script>
$( document ).ready(function() {

	 $.ajax({
		 url: "jsp/loginajax.jsp", 
		 success: function(result){
		        $("#main").html(result);
		    }});
	 });
</script>
</body>
</html>