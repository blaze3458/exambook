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

import classes.Data;
import classes.TeacherContainer;

@WebServlet("/add_question")
public class AddQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession(true);
		if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Admin")
		{
			PrintWriter out = res.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('The question has been successfully added.');");
			out.println("location='profile.jsp';");
			out.println("</script>");
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		ArrayList<String> list = new ArrayList<>();
		if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Admin")
		{	
			list.add(req.getParameter("quest"));
			list.add(req.getParameter("Ans_A"));
			list.add(req.getParameter("Ans_B"));
			list.add(req.getParameter("Ans_C"));
			list.add(req.getParameter("Ans_D"));
			list.add(req.getParameter("true_ans"));
			
			TeacherContainer ad = (TeacherContainer) session.getAttribute("login");
			
			int t_id = ad.GetAdminID();
			String course = req.getParameter("course");
			
			if(new Data().SetNewQuestion(t_id,course,list))
				doGet(req,res);
		}
	}
}
