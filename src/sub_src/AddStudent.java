package sub_src;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import classes.Data;
import classes.TeacherContainer;

@WebServlet("/add_student")
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		HttpSession session = req.getSession();
		
		if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Admin")
		{
			TeacherContainer ad = (TeacherContainer) session.getAttribute("login");
			ArrayList<String> list = new ArrayList<String>();
			int id = ad.GetAdminID();
			list = new Data().GetTeacherStudents(id);
			
			String json = new Gson().toJson(list);
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);
		}
		else
			res.sendRedirect("/Web/index.jsp");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		req.setCharacterEncoding("ISO-8859-9");
		
		if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Admin")
		{
			TeacherContainer ad = (TeacherContainer) session.getAttribute("login");
			PrintWriter out = res.getWriter();
			int T_ID = ad.GetAdminID();
			
			String S_ID = req.getParameter("ID");
			String name = req.getParameter("name");
			String surname = req.getParameter("surname");
			String pass = req.getParameter("pass");
			if(new Data().SetNewStudent(S_ID, name, surname, pass, T_ID))
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('The student has been successfully added.');");
				out.println("location='profile.jsp';");
				out.println("</script>");
			}
			else
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Failed.This id has been used.');");
				out.println("location='profile.jsp';");
				out.println("</script>");
			}
		}
		
		else
			res.sendRedirect("/Web/index.jsp");
	}
}
