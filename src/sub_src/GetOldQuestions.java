package sub_src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import classes.Data;
import classes.StudentContainer;

@WebServlet("/GetOldQuestions")
public class GetOldQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		List<String> list = new ArrayList<>();
		
		if(session.getAttribute("login") != null)
		{
			StudentContainer ex = (StudentContainer) session.getAttribute("login");
			String ID = ex.GetStudentID();
			list = new Data().GetOldExamCourse(ID);
			
			String json = new Gson().toJson(list);
		
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);
		}
		else
			res.sendRedirect("/Web/index.jsp");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String class_name = req.getParameter("name");
		HttpSession session = req.getSession();
		List<String> list = new ArrayList<>();
		
		if(session.getAttribute("login") != null)
		{
			list = new Data().GetOldExamQuestions(class_name);
			
			String json = new Gson().toJson(list);
		
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			res.getWriter().write(json);
		}
		else
			res.sendRedirect("/Web/index.jsp");
	}
}
