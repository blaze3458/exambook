<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
    pageEncoding="ISO-8859-9"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-9">
<title>Exambook - Profile</title>
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
	<form id=change_pass action="change_password.jsp"></form>
	<div class="limiter">
		<div class="topnav">
			<div class="top_inner wrap-login100">
				<a class ="top_anchor" href = "index.jsp">exambook</a>
				<%if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Student"){ %>
					<p class = "parg">| <%= ex.GetStudentSurname()%>,<%= ex.GetStudentName()%></p>
				<%} 
				else if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Admin"){ %>
					<p class = "parg">| <%= ad.GetAdminSurname()%>,<%= ad.GetAdminName()%></p>
				<%} %>
				<div style="left:70%;" class="top-button-right container-login100-form-btn">
					<div class="wrap-login100-form-btn">
						<div class="login100-form-bgbtn"></div>
							<form action = "log_out">
							<button class="login100-form-btn">
								Log Out
							</button>
							</form>
					</div>
				</div>
			</div>
		</div>
		<div class="sidenav"> 
			<div class="left-menu wrap-login100">
				<ul>
				<%if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Student"){ %>
					<li><button id="exam_results" class = left-menu-button>Exam Results</button></li>
					<li><button id="show_old_questions" class = left-menu-button>Old Questions</button></li>
					<li><button id="change_password" class = left-menu-button>Change Password</button></li>
				<%} 
				else if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Admin"){ %>
					<li><button id="new_course" class = left-menu-button>Add Course</button></li>
					<li><button id="new_quest" class = left-menu-button>Add and Show Quest</button></li>
					<li><button id="new_student" class = left-menu-button>Add and Show Student</button></li> 
					<li><button id="assign_course" class = left-menu-button>Assign Course</button></li>
					<li><button id="change_password" class = left-menu-button>Change Password</button></li>
				<%} %>
				</ul>
			</div>
		</div>
		<div class="profile-content">
			<div id="content" class="profile-content-inner wrap-login100"> 
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
<!--===============================================================================================-->
	<script src="MainLayer.js"></script>
</body>
</html>