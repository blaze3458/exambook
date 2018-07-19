<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
    pageEncoding="ISO-8859-9"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-9">
<title>Exambook - Home</title>
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
<% 
StudentContainer ex = new StudentContainer();
TeacherContainer ad = new TeacherContainer();
int last_quest = 0;
if(session.getAttribute("login") != null  && session.getAttribute("user_type") == "Student")
{
	ex = (StudentContainer)session.getAttribute("login");
	last_quest = ex.GetLastQuestionNum();
}

else if(session.getAttribute("login") != null  && session.getAttribute("user_type") == "Admin")
{
	ad = (TeacherContainer)session.getAttribute("login");
	last_quest = ex.GetLastQuestionNum();
}

%>
	<div class="limiter">
		<div class="topnav">
			<div class="top_inner wrap-login100">
				<a class ="top_anchor" href = "index.jsp">exambook</a>
			<%if(session.getAttribute("login") == null) /*ÖĞRENCİYLE ADMİN GİRİŞ YAPMADIĞI ZAMAN*/ { %>
				<div class="top-button-right container-login100-form-btn">
					<div class="wrap-login100-form-btn">
						<div class="login100-form-bgbtn"></div>
							<form action = "student_login.jsp">
							<button class="login100-form-btn">
								Student Login
							</button>
							</form>
					</div>
				</div>
				<div class="top-button-right container-login100-form-btn">
					<div class="wrap-login100-form-btn">
						<div class="login100-form-bgbtn"></div>
							<form action = "admin_login.jsp">
							<button class="login100-form-btn">
								Admin Login
							</button>
							</form>
					</div>
				</div>
				<%}else if(session.getAttribute("login") != null  && session.getAttribute("user_type") == "Student"){ %>
				<a class ="profile" href = "profile.jsp"><%= ex.GetStudentSurname()%>,<%= ex.GetStudentName()%></a>
				<%}
				else if(session.getAttribute("login") != null  && session.getAttribute("user_type") == "Admin"){%>
				<a class ="profile" href = "profile.jsp"><%= ad.GetAdminSurname()%>,<%= ad.GetAdminName()%></a>
				<%} %>
			</div>
		</div>
		<div class = "inner-content">
		<%if(session.getAttribute("login") != null  && session.getAttribute("user_type") == "Student" && ex.HasExam()) {%>
			<div class = "quest-content">
				<div class= "quest">
					<span id="quest-number"><%=ex.GetLastQuestionNum()+1 %></span><span>/<%= ex.GetQuestSize() %></span>
					<span id=quest style="margin-left:10px"><%=ex.GetQuestions(last_quest) %></span>
				</div>
			</div>
			<div class = "quest-content">
				<form>
					<label class="container"><span id= "button_1"><%=ex.GetAnswer_A(last_quest) %></span>
  						<input type="radio" name="radio" value="A">
  						<span class="checkmark"></span>
					</label>
					<label class="container"><span id= "button_2"><%=ex.GetAnswer_B(last_quest) %></span>
 					 	<input type="radio" name="radio" value="B">
 						 <span class="checkmark"></span>
					</label>
					<label class="container"><span id= "button_3"><%=ex.GetAnswer_C(last_quest) %></span>
  						<input type="radio" name="radio" value="C">
  						<span class="checkmark"></span>
					</label>
					<label class="container"><span id= "button_4"><%=ex.GetAnswer_D(last_quest) %></span>
  						<input type="radio" name="radio"value="D">
 				 		<span class="checkmark"></span>
					</label>
				</form>
			</div>
			<div class = "quest-content">
				<div class = "quest-btn-content">
						<div class="quest-btn-bottom container-login100-form-btn">
							<div class="wrap-login100-form-btn">
								<div class="login100-form-bgbtn"></div>
								<button id="prev_button" class="login100-form-btn">
								Prev
								</button>
							</div>
						</div>
						<div class="quest-btn-bottom container-login100-form-btn">
							<div class="wrap-login100-form-btn">
								<div class="login100-form-bgbtn"></div>
								<button id="next_button" class="login100-form-btn">
								Next
								</button>
							</div>
						</div>
						
						<div class="quest-btn-bottom container-login100-form-btn">
							<div class="wrap-login100-form-btn">
								<div class="login100-form-bgbtn"></div>
								<button id="finish_button" class="login100-form-btn">
								Finish Exam
								</button>
							</div>
						</div>
						<form id="finish" action = "FinishExam" method = "GET"></form>
				</div>
			</div>
		<%}//Login - Student
		else if(session.getAttribute("login") != null  && session.getAttribute("user_type") == "Student" && !ex.HasExam()){%>
		<div>You do not have an exam right now. Go to 
			<a class ="profile" href = "profile.jsp">profile.</a>
		</div>
		<%}
		else if(session.getAttribute("login") != null  && session.getAttribute("user_type") == "Admin"){ %>
		<div>You do not have a new message.</div>
		<%} %>
		</div><!--inner-content -->
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
	<script src="MainLayer.js"></script>
<!--===============================================================================================-->
</body>
</html>