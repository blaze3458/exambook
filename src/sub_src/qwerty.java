package sub_src;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes.Data;

@WebServlet("/qwerty")
public class qwerty extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String val = req.getParameter("text");
		
		new Data().InsertData(val);
		
		//....
		//sql = SELECT ...
		
		ResultSet result = new Data().GetData(); //conn.createStatement().executeQuery(sql);//conn.prepareStatement(sql).executeQuery(sql);
		
		//req.getSession().setAttribute("cat", result);
		//res.sendRedirect("test2.jsp");
		req.setAttribute("cat", result);// session.setAttribute("cat",result);
		req.getRequestDispatcher("test2.jsp").forward(req, res); //res.SendRedirect("result.jsp");
		
		
	}
}
