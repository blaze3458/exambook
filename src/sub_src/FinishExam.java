package sub_src;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import classes.Data;
import classes.StudentContainer;


@WebServlet("/FinishExam")
public class FinishExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		
		if(session.getAttribute("login")!= null && session.getAttribute("user_type") == "Student")
		{
			StudentContainer ex = (StudentContainer)session.getAttribute("login");
			Data Ch = new Data();
		
			String class_name = ex.GetExamLesson();
			String ID = ex.GetStudentID();
				
			if(!class_name.equals(null))
			{
				Ch.SetExamFinish(ID, class_name);
				Ch.SetStudentAnswers(ex.GetStudentAnswers(), ID, class_name);
				float result = Ch.CalculateResult(ex.GetStudentAnswers(),class_name);
				Ch.SetExamResult(ID, class_name, result);
			}
		
			if(ex.HasAnotherExam())
			{
				String examClass = new Data().GetExamCourse(ID);
				ex.SetHasExam(true);
				ex.SetExamLesson(examClass);
				ex.SetQuestions(new Data().GetQuestions(ID,examClass));
				ex.SetAnswer_A(new Data().GetAnswers(ID,"A",examClass));
				ex.SetAnswer_B(new Data().GetAnswers(ID,"B",examClass));
				ex.SetAnswer_C(new Data().GetAnswers(ID,"C",examClass));
				ex.SetAnswer_D(new Data().GetAnswers(ID,"D",examClass));
				ex.SetStudentAnswers(new Data().GetStudentAnswers(ID, examClass));
				ex.SetLastQuestionNum(0);
			}
			else
				ex.SetHasExam(false);
		
			session.setAttribute("login", ex);
		}
		res.sendRedirect("/Web/index.jsp");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession(true);
		
		if(session.getAttribute("login")!= null && session.getAttribute("user_type") == "Student")
		{
			String check = req.getParameter("checked");
		
			StudentContainer ex = (StudentContainer)session.getAttribute("login");
			int last = ex.GetLastQuestionNum()+1;
			System.out.println("Answer is : "+check+" QuestNumber : "+last);
			if(check != null)
			{
				if(!ex.IsStudentAnswerContains(last))
					ex.PutStudentAnswers(last, check);
			
				else if(ex.IsStudentAnswerContains(last) && !ex.GetStudentAnswers(last).contains(check))
					ex.ReplaceStudentAnswers(last, check);
			
				session.setAttribute("login", ex);
			}
		}
	}
}
