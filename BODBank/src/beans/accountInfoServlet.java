package beans;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;
import oracle.jdbc.driver.*;

@WebServlet("/accountInfoServlet")
public class accountInfoServlet extends HttpServlet {
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
 	 		HttpSession session = req.getSession(false);
 	 		if (session == null) {
 	 			String message = "You have been logged out due to inactivity!";
 	 			req.setAttribute("message", message);
	     		req.getRequestDispatcher("/JSPs/login.jsp").forward(req, res);
 	 		}
	     	if (req.getParameter("Logout") != null) {  
	     	    req.getSession().invalidate();
	     	    res.sendRedirect(req.getContextPath() + "/JSPs/login.jsp");
	     	}
	     	String AccountTransfersClick = req.getParameter("AccountTransfers");
	     	String BillPaymentClick = req.getParameter("BillPayment");
 	 	    String AccountTypeSelect = req.getParameter("AccountType");
 	 	    String AccountNumber = (String) session.getAttribute("AccountNumber");
 	 	    
	     	if(AccountTransfersClick != null) {
	     		res.sendRedirect("/BODBank/JSPs/transfers.jsp");
	     	}
	     	if(BillPaymentClick != null) {
	     		res.sendRedirect("/BODBank/JSPs/billPayment.jsp");
	     	}
	     	if(AccountTypeSelect.equals("bod_current_account")) {
	 	 	    stmt = con.createStatement();
	     		rs = stmt.executeQuery("SELECT * FROM BOD_CURRENT_ACCOUNT WHERE ACCOUNT_NUMBER = "+AccountNumber+" ORDER BY TRANSACTION_ID DESC"); // change account number to match person
	     		List<Accounts> Accounts = new ArrayList<Accounts>();
	     		while(rs.next()) {
	     			Accounts a = new Accounts();
	     			a.setTransactionID(rs.getString(1));
	     			a.setAccountNumber(rs.getString(2));
	     			a.setTransactionDate(rs.getString(3));
	     			a.setDetails(rs.getString(4));
	     			a.setDebit(rs.getString(5));
	     			a.setCredit(rs.getString(6));
	     			a.setBalance(rs.getString(7));
	     			Accounts.add(a);
	     		}
	     		req.setAttribute("AccountInfo", Accounts);
	     		req.getRequestDispatcher("/JSPs/currentAccount.jsp").forward(req, res);
	     	}
	     	if(AccountTypeSelect.equals("bod_savings_account")) {
	 	 	    stmt = con.createStatement();
	     		rs = stmt.executeQuery("SELECT * FROM BOD_SAVINGS_ACCOUNT WHERE ACCOUNT_NUMBER = "+AccountNumber+" ORDER BY TRANSACTION_ID DESC");
	     		List<Accounts> Accounts = new ArrayList<Accounts>();
	     		while(rs.next()) {
	     			Accounts a = new Accounts();
	     			a.setTransactionID(rs.getString(1));
	     			a.setAccountNumber(rs.getString(2));
	     			a.setTransactionDate(rs.getString(3));
	     			a.setDetails(rs.getString(4));
	     			a.setDebit(rs.getString(5));
	     			a.setCredit(rs.getString(6));
	     			a.setBalance(rs.getString(7));
	     			Accounts.add(a);
	     		}
	     		req.setAttribute("AccountInfo", Accounts);
	     		req.getRequestDispatcher("/JSPs/savingsAccount.jsp").forward(req, res);	
	     	}
	     	res.sendRedirect("/JSPs/accountInfo.jsp");	
	 	}
        catch (Exception e) {
        	e.printStackTrace();
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
