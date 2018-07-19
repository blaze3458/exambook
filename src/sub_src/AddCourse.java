package sub_src;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Data;
import classes.TeacherContainer;

@WebServlet("/add_course")
public class AddCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Admin")
		{
			TeacherContainer ad = (TeacherContainer)session.getAttribute("login");
			
			int ID = ad.GetAdminID();
			String class_name = req.getParameter("course");
			
			if(new Data().SetNewCourse(class_name,ID))
			{
				ad.SetNewCourse(class_name);
				session.setAttribute("login", ad);
				PrintWriter out = res.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('The course has been successfully added.');");
				out.println("location='profile.jsp';");
				out.println("</script>");		
			}	
		}
		else
			res.sendRedirect("/Web/index.jsp");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Admin")
			doGet(req, res);
		
		else
			res.sendRedirect("/Web/index.jsp");
	}
}
