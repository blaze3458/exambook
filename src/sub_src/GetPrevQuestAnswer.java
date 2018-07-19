package sub_src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.google.gson.*;

import classes.StudentContainer;

@WebServlet("/get_prev_quest")
public class GetPrevQuestAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		List<String> list = new ArrayList<>();
		if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Student")
		{
			StudentContainer ex = (StudentContainer)session.getAttribute("login");
		
			int last = ex.GetLastQuestionNum() - 1;
			if(last >= 0)
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
			
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(json);
			}
		}
		else
			response.sendRedirect("Web/index.jsp");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true); 
		
		if(session.getAttribute("login") != null && session.getAttribute("user_type") == "Student")
		{
			String myJsonData = request.getParameter("checked");//Sonuç
		
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
			response.sendRedirect("Web/index.jsp");
	}
}
