package beans;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.sql.*;
import oracle.jdbc.driver.*;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String JDBCUrl = "jdbc:oracle:thin:@ee417.c7clh2c6565n.eu-west-1.rds.amazonaws.com:1521:EE417";
	String username = "ee_user";
	String password = "ee_pass";
       
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		try {
			System.out.println("\nConnecting to the SSD Database......");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(JDBCUrl, username, password);
        }
        catch (Exception e) {
        	out.println("An error has occurred during the connection phase! Did you upload your Oracle Drivers?"); 
        }  

 	 	try {
 	 		out.println("Connection Successful..... creating statement....");
      	    stmt = con.createStatement();
	     	rs = stmt.executeQuery("SELECT * FROM BOD_USERS");
	     	String Username = req.getParameter("usernamefield");
	     	String Password = req.getParameter("passwordfield");

	     	while (rs.next()) {
	     		if(Username.equals(rs.getString("USERNAME")) && Password.equals(rs.getString("PASSWORD"))) {
	    			String firstName = rs.getString("FIRSTNAME");
	    			String surname = rs.getString("SURNAME");
	    			String AccountHolder =  firstName + " " + surname;
	    			String AccountNumber = rs.getString("ACCOUNT_NUMBER");
	    			String Email = rs.getString("EMAIL");
	    			String Branch = rs.getString("BRANCH");
	    			
	    			HttpSession session = req.getSession();
	    			session.setAttribute("AccountHolder", AccountHolder);
	    			session.setAttribute("AccountNumber",AccountNumber);
	    			session.setAttribute("Email", Email);
	    			session.setAttribute("Branch",Branch);
	    			session.setMaxInactiveInterval(120); // Session Timeout after 2 Minutes
	     			res.sendRedirect("/BODBank/JSPs/accountInfo.jsp");
	     		}
	     	}
 			String message = "Login Failed, Incorrect Username or Password!";
 			req.setAttribute("message", message);
 			req.getRequestDispatcher("/JSPs/login.jsp").forward(req, res);
	 	}
        catch (Exception e) {
        	out.println("<br/>An error has occurred during the Statement/ResultSet phase!");
	    }   
         	
		finally {
			try {    
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			}
			catch (Exception ex) {
				out.println("<br/>An error occurred while closing down connection/statement"); 
			}
        }
		out.close();
	}
}