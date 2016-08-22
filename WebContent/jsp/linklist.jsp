<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.cpsc476A1.bean.LinkBean" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.cpsc476A1.bean.linkUser" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
HttpServletResponse httpResponse = (HttpServletResponse) response;
httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
httpResponse.setHeader("Pragma", "no-cache"); // HTTP 1.0
httpResponse.setDateHeader("Expires", 0); // Proxies.
%>
<%
	String sss = (String) session.getAttribute("user");
	if(sss==null)
	{
		response.sendRedirect("login.jsp");
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
<a style="text-decoration: none;" href="link?var=signout"><input type="button" value="sign out" /></a>
<div id="errr" style="display:none;color:red;"></div>
 <form id="fm" action="link?var=new" method="post">
   <h4>Link Descriptin</h4>
   <input id="ln" type="text" name="ln" />
   <h4>Link Address</h4>
   <input id="la" type="text" name="la" /><br/><br/>
   <input type="submit" value="submit"/>
 </form>
 <br/>
 <h2>Links</h2>
 <div id="main" style="width:500px;">
 There is no link to display
 </div>
 <script>
 $("#fm").submit(function(event) {
	 $("#errr").css("display","none");
	 $("#ln").removeClass('textboxred');
	 $("#la").removeClass('textboxred');
	 ln = $("#ln").val();
     la = $("#la").val();
     err="";
     if(ln=="")
     {
    	 $("#ln").addClass("textboxred");
         err+="Link Name";
     } 
     if(la=="")
     {
    	 $("#la").addClass("textboxred");
    	 err+=" Link Address";
     }
     
     if(err!="")
     {
    	 err="Please enter "+err;
    	 $("#errr").html(err);
 		 $("#errr").css("display","block");
 		 return false;
     }	 
     return true;
 });
 $( document ).ready(function() {

	 $.ajax({
		 url: "jsp/ajaxlink.jsp", 
		 success: function(result){
		        $("#main").html(result);
		    }});

	 });
 </script>
</body>
</html>