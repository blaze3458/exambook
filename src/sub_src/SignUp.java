package sub_src;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import classes.Data;

@WebServlet("/sign_up")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("login")== null)
		{
			String email = req.getParameter("email");
			String name = req.getParameter("name");
			String surname = req.getParameter("surname");
			String password = req.getParameter("pass");
		
			new Data().SetNewUser(email,password,name,surname);
		}
		res.sendRedirect("/Web/index.jsp");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if(session.getAttribute("login")== null)
		{
			
			String email = req.getParameter("email");
			if(new Data().CheckEmail(email))
				doGet(req, res);
			else
				res.sendRedirect("/Web/sign_up.jsp");
		}
		else
			res.sendRedirect("/Web/index.jsp");
	}
}
