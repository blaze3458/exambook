package sub_src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.*;

import classes.StudentContainer;

@WebServlet("/get_next_quest")
public class GetNextQuestAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		List<String> list = new ArrayList<>();
		
		if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Student")
		{
			StudentContainer ex = (StudentContainer)session.getAttribute("login");
			
			int last = ex.GetLastQuestionNum() +1;
			
			if(last< ex.GetQuestSize())
			{
				list.add(ex.GetQuestions(last));
				list.add(ex.GetAnswer_A(last));
				list.add(ex.GetAnswer_B(last));
				list.add(ex.GetAnswer_C(last));
				list.add(ex.GetAnswer_D(last));
				list.add(Integer.toString(last+1));
				if(ex.IsStudentAnswerContains(last+1))
					list.add(ex.GetStudentAnswers(last+1));
						
				String json = new Gson().toJson(list);
		
				ex.SetLastQuestionNum(last);
				session.setAttribute("login", ex);
			
				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().write(json);
			}		
		}
		else
			res.sendRedirect("/Web/index.jsp");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		HttpSession session = req.getSession(true);
		
		if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Student")
		{
			String myJsonData = req.getParameter("checked");
			StudentContainer ex = (StudentContainer)session.getAttribute("login");
		
			int last = ex.GetLastQuestionNum()+1;
		
			System.out.println("Answer is : "+myJsonData+" QuestNumber : "+last);
		
			if(!ex.IsStudentAnswerContains(last))
				ex.PutStudentAnswers(last, myJsonData);
		
			else if(ex.IsStudentAnswerContains(last) && !ex.GetStudentAnswers(last).contains(myJsonData)) 
				ex.ReplaceStudentAnswers(last, myJsonData);
		
			session.setAttribute("login", ex);
		}
		else
			res.sendRedirect("/Web/index.jsp");
	}
}
