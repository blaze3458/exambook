<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test_jsp</title>

</head>
<body>
<% String name = request.getParameter("your_name");
	String email = request.getParameter("e_mail"); %>
<p>Your name : <%= name %><br/> Email : <%= email %></p>

</body>
</html>