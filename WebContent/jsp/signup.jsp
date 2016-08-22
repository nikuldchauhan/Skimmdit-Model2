<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
HttpServletResponse httpResponse = (HttpServletResponse) response;
httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
httpResponse.setDateHeader("Expires", 0); // Proxies.
%>
<%
	String sss = (String) session.getAttribute("user");
	if(sss!=null)
	{
		response.sendRedirect("linklist.jsp");
	}	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Subreddit</title>
<script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<h2>Sign Up</h2>
<%
  String err = (String) request.getAttribute("err");
  if(err!=null)
  {
	  out.println("<div id='e' style='color:red;'>"+err+"</div>");
  }	  
%>
<div id="errr" style="display:none;color:red;"></div>
<form id="fm" action="index?var=signup" method="post">
		<h4>Username:</h4>	
		<input id="u" type="text" name="un" required/>
		<h4>Password</h4>
		<input id="p" type="password" name="pw" required/>
		<h4>Retype-Password</h4>
		<input id="p1" type="password" required/><br/><br/>
		<input type="submit" value="submit"/>
</form>
<br/>
<a style="text-decoration:none;" href="index?var=back"><input type="button" value="Back"/></a>
<script>
$("#fm").submit(function(event) {
	$("#e").html("");
	$("#errr").html("");
	$("#errr").css("display","none");
	$("#p").removeClass('textboxred');
	$("#p1").removeClass('textboxred');
	err="";
	p = $("#p").val();
	p1 = $("#p1").val();
	if(p=="")
	{
		bb=true;
		if(b)
		{
			err+=" and Password";
		}
		else
		{
			err+="Password";
		}	
		$("#p").addClass("textboxred");
	}	
	if(p1=="")
	{
		$("#p1").addClass("textboxred");
	}	
	if(p!=p1)
	{
		$("#p1").addClass("textboxred");
		err+="<br/>Password does not match";
	}	
	if(err!="")
	{
		$("#errr").html(err);
		$("#errr").css("display","block");
		return false;
	}	
	return true;
});
</script>
</body>
</html>