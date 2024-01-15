package in.sp.backend;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import javax.servlet.*;
import java.sql.*;

@WebServlet("/regForm")
public class Register extends HttpServlet
{
	private static final long serialVersionUID = 8763787433732355374L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		PrintWriter out = resp.getWriter();
		
		String Username = req.getParameter("username");
		String Password = req.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Reguser");
			PreparedStatement ps = con.prepareStatement("insert into register values(?,?)");
			ps.setString(1, Username);
			ps.setNString(2, Password);
			
			int count = ps.executeUpdate();
			if (count>0)
			{
				resp.setContentType("text/html");
				out.print("<h3 style='color:green'> success </h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
				rd.include(req, resp);
			}
			else
			{
				resp.setContentType("text/html");
				out.print("<h3 style='color:green'> fail; </h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
				rd.include(req, resp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resp.setContentType("text/html");
			out.print("<h3 style='color:green'> fail; </h3>");
			
			RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
			rd.include(req, resp);
		}
		
	}
   
}
