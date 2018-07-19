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

@WebServlet("/assign_course")
public class AssignCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static ArrayList<String> stu_id = new ArrayList<>();
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(!stu_id.isEmpty())
			stu_id.clear();
		
		if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Admin")
		{
			String course = req.getParameter("val");
			TeacherContainer ad = (TeacherContainer) session.getAttribute("login");
			int t_id = ad.GetAdminID();
			
			ArrayList<String> list = new Data().GetNoExamStudents(t_id,course);
			
			if(list.size() > 0)
			{
				for(int i = 0; i< list.size(); i++)
				{
					String id = list.get(i).substring(0, 12);
					stu_id.add(id);
				}
				
				String json = new Gson().toJson(list);
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);
			}
		}
		else
			res.sendRedirect("/Web/index.jsp");		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		req.setCharacterEncoding("ISO-8859-9");
		if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Admin")
		{
			ArrayList<String> list = new ArrayList<>();
			
			PrintWriter out = res.getWriter();
			String course = req.getParameter("course");
			String[] stu = req.getParameterValues("stu_id");
			
			for(int i = 0; i<stu.length; i++)
				list.add(stu_id.get(Integer.parseInt(stu[i])));
		
			if(new Data().CheckHasExamQuestion(course))
			{
				new Data().SetNoExamStudents(course,list);
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Student assignment is successful.');");
				out.println("location='profile.jsp';");
				out.println("</script>");
			}
			else
			{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Add questions before assigning students to this course.');");
				out.println("location='profile.jsp';");
				out.println("</script>");
			}
		}		
	}
}
