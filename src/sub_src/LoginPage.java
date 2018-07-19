package sub_src;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import classes.Data;
import classes.StudentContainer;
import classes.TeacherContainer;

public class LoginPage extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	public boolean IsAdmin(String name)
	{
		boolean check = false;
		Pattern VALID_EMAIL_ADDRESS_REGEX = 
			    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher m = VALID_EMAIL_ADDRESS_REGEX.matcher(name);
		if(m.find())
		{
			System.out.println("Admin : "+name);
			check = true;
		}	
		return check;
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String name = req.getParameter("email");

		HttpSession session = req.getSession(true);
		//HttpSession session = request.getSession(); Var olan sessionlarý alýr.
		//session.setAttribute("Session_ismi",obje);Obje(class,String,Integer,Float...)
		//session.getAttribute("Session_ismi")Obje çaðrýlýr.
		//session.invalidate();Var olan bütün sessionlarý kaldýrýr.
		//HttpSession session;
		
		if(session.getAttribute("login") == null)
		{
			if(IsAdmin(name))
			{
				session.setAttribute("user_type", "Admin");
				TeacherContainer ad = new TeacherContainer();
				ad.SetAdminEmail(name);
				
				int ID = new Data().GetTeacherID(name);
				ad.SetAdminID(ID);
				ad.SetAdminName(new Data().GetName(name));
				ad.SetAdminSurname(new Data().GetSurName(name));
				ad.SetCourses(new Data().GetTeacherCourses(ID));
				
				session.setAttribute("login", ad);
			}
			else
			{
				session.setAttribute("user_type", "Student");
				StudentContainer ex = new StudentContainer();
				ex.SetStudentName(new Data().GetName(name));
				ex.SetStudentSurname(new Data().GetSurName(name));
				ex.SetStudentID(name);
				if(new Data().CheckExam(name))
				{
					String examClass = new Data().GetExamCourse(name);
					ex.SetHasExam(true);
					ex.SetExamLesson(examClass);
					ex.SetQuestions(new Data().GetQuestions(name,examClass));
					ex.SetAnswer_A(new Data().GetAnswers(name,"A",examClass));
					ex.SetAnswer_B(new Data().GetAnswers(name,"B",examClass));
					ex.SetAnswer_C(new Data().GetAnswers(name,"C",examClass));
					ex.SetAnswer_D(new Data().GetAnswers(name,"D",examClass));
					ex.SetStudentAnswers(new Data().GetStudentAnswers(name, examClass));
					ex.SetLastQuestionNum(0);
				}
				else
					ex.SetHasExam(false);
				
				session.setAttribute("login", ex);
			}
		}	
		res.sendRedirect("/Web/index.jsp");
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
	{
		String name = req.getParameter("email");
		String password = req.getParameter("pass");
		
		HttpSession session = req.getSession(true);
		if(session.getAttribute("login") == null)
		{
			System.out.println(name+":"+password);
			try {
					if(new Data().CheckUserNamePassword(name,password))
						doGet(req,res);
					else
					{
						if(IsAdmin(name))
							res.sendRedirect("/Web/admin_login.jsp");
						else
							res.sendRedirect("/Web/student_login.jsp");
					}
				} 
			catch (SQLException e) {e.printStackTrace();}
		}
		else
			res.sendRedirect("/Web/index.jsp");
	}
}
