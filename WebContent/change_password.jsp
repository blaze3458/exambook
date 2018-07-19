<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-9">
<title>Exambook - Change Password</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<link rel="stylesheet" type="text/css" href="MainLayer.css">
<!--===============================================================================================-->
</head>
<body>
<%@page import= "javax.servlet.http.HttpSession"%>
<%@page import= "classes.StudentContainer"%>
<%@page import= "classes.TeacherContainer"%>

<%StudentContainer ex = new StudentContainer(); %>
<%TeacherContainer ad = new TeacherContainer(); %>

<% if(session.getAttribute("login") == null)
		response.sendRedirect("/Web/index.jsp");
else if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Student")
	ex = (StudentContainer)session.getAttribute("login");

else if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Admin")
	ad = (TeacherContainer) session.getAttribute("login");
%>

	<div class="limiter">
	<div class="topnav">
			<div class="top_inner wrap-login100">
				<a class ="top_anchor" href = "index.jsp">exambook</a>
				<%if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Student"){ %>
					<a class ="profile" href = "profile.jsp"><%= ex.GetStudentSurname()%>,<%= ex.GetStudentName()%></a>
				<%} 
				else if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Admin"){ %>
					<a class ="profile" href = "profile.jsp"><%= ad.GetAdminSurname()%>,<%= ad.GetAdminName()%></a>
				<%} %>
			</div>
		</div>
		<div class="container-login100">
		
			<div class="wrap-login100">
			
				<form class="login100-form validate-form" action="change_password" method="POST">
					
					<span class="login100-form-title p-b-26">
						Change Password
					</span>
					<span class="login100-form-title p-b-48">
						<i class="zmdi zmdi-font"></i>
					</span>
					<%if(request.getAttribute("TextValue")!=null){%>
						<p class = alert-wrong-password>Password does not match!</p>
 					<% } %>
					<div class="wrap-input100 validate-input" data-validate="Enter password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
						<input class="input100" type="password" name="pass">
						<span class="focus-input100" data-placeholder="New Password"></span>
					</div>
					
					<div id = "input" class="wrap-input100 validate-input" data-validate="Enter password">
						<span class="btn-show-pass">
							<i class="zmdi zmdi-eye"></i>
						</span>
						<input class="input100" type="password" name="pass_confirm">
						<span class="focus-input100" data-placeholder="New Password Again"></span>
					</div>

					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn">
								Change
							</button>
						</div>
					</div>

					
				</form>
			</div>
		</div>
	</div>
	
<!--===============================================================================================-->
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/daterangepicker/moment.min.js"></script>
	<script src="vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>
	
</body>
</html>