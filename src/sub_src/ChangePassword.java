package sub_src;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Data;
import classes.StudentContainer;
import classes.TeacherContainer;


@WebServlet("/change_password")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		
		if(session.getAttribute("login") != null)
		{
			String pass = request.getParameter("pass");
			String pass_confirm = request.getParameter("pass_confirm");
			if(!pass.equals(pass_confirm))
			{
				request.setAttribute("TextValue","Alert");
				response.setCharacterEncoding("ISO-8859-9");
				RequestDispatcher reDis = request.getRequestDispatcher("/change_password.jsp");
				reDis.forward(request, response);
			}
			else
			{
				String ID = null;
				
				if(session.getAttribute("user_type") == "Admin")
				{
					TeacherContainer ad = (TeacherContainer) session.getAttribute("login");
					ID = ad.GetAdminEmail();
				}
				else
				{
					StudentContainer ex = (StudentContainer) session.getAttribute("login");
					ID = ex.GetStudentID();
				}
				new Data().UpdatePassword(ID, pass_confirm);
				request.removeAttribute("TextValue");
				response.sendRedirect("/Web/profile.jsp");	
			}
		}
		else
			response.sendRedirect("/Web/index.jsp");
	}
}
