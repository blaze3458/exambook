package sub_src;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import classes.Data;
import classes.StudentContainer;

import java.util.Map;

public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	HttpSession session = req.getSession(true);
		if(session.getAttribute("login") != null )
		{ 
			if(session.getAttribute("user_type").equals("Admin"))
			{
				
			}
			else
			{
				StudentContainer ex = (StudentContainer)session.getAttribute("login");
				
				if(ex.HasExam())
				{
					String ID = ex.GetStudentID();
					Map<Integer,String> stu_ans = ex.GetStudentAnswers();
					String examClass = ex.GetExamLesson();
					new Data().SetStudentAnswers(stu_ans,ID,examClass);
				}
			}
			session.invalidate();
		}
		res.sendRedirect("/Web/index.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
