package beans;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
import oracle.jdbc.driver.*;

@WebServlet("/ajax_javascriptServlet")
public class ajax_javascriptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	String JDBCUrl = "jdbc:oracle:thin:@ee417.c7clh2c6565n.eu-west-1.rds.amazonaws.com:1521:EE417";
	String username = "ee_user";
	String password = "ee_pass";

	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		Vector<String> dVector = new Vector<String>();
		String typed = req.getParameter("q").toLowerCase();
		String responsetext = new String("");
		
		try {
			System.out.println("\nConnecting to the SSD Database......");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(JDBCUrl, username, password);
        }
        catch (Exception e) {
        	out.println("An error has occurred during the connection phase! Did you upload your Oracle Drivers?"); 
        }
		
		try {
			stmt = con.createStatement();  
			rs = stmt.executeQuery("SELECT * FROM BOD_ATMS");
			while (rs.next()) { 
				String location = rs.getString(1);
				dVector.add(location);
			}
			for (Enumeration<String> e = dVector.elements() ; e.hasMoreElements() ;) {
				String name = ((String) e.nextElement()).toLowerCase();
				if (name.indexOf(typed)!=-1) {
					responsetext = responsetext + name + "<br/>";
				}
			}
			out.println(responsetext);
		}
		catch(Exception e) {}
		
		finally {
			try {    
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (con != null) con.close();
			}
			catch (Exception ex) {}
        }
		out.close();
	}
}
