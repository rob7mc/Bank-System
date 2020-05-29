package beans;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date; 
import oracle.jdbc.driver.*;

@WebServlet("/billPaymentServlet")
public class billPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	Statement stmt = null;
	Statement stmt2 = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
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
 	 		String AccountTypeSelect = req.getParameter("ACCOUNT_TYPE");
     		String AccountNumber = (String) session.getAttribute("AccountNumber");
      	    stmt = con.createStatement();
      	    stmt2 = con.createStatement();
	     	rs = stmt.executeQuery("select * from "+AccountTypeSelect+" where transaction_id=(select max(transaction_id) from "+AccountTypeSelect+")");
	     	rs2 = stmt2.executeQuery("select TT.* from "+AccountTypeSelect+" TT INNER JOIN (select account_number, MAX(transaction_id) AS MaxTransactionID FROM "+AccountTypeSelect+" where account_number = "+AccountNumber+" GROUP BY account_number) groupedTT ON TT.account_number = groupedTT.account_number AND TT.transaction_id = groupedTT.MaxTransactionID");
	     	
	     	while(rs.next() && rs2.next()) {
	     		String TransactionID = rs.getString("TRANSACTION_ID");
	     		String BalanceTemp = rs2.getString("BALANCE");
	     	
	     		String BillTypeSelect = req.getParameter("BILL_TYPE");
	     		String Amount = req.getParameter("Amount");
	     		String Pay = req.getParameter("Pay");
	     	
	     		if(Pay != null) {
	     			Integer i = Integer.parseInt(TransactionID)+1;
	     			String Transaction_ID = Integer.toString(i);
	     			Integer Account_Number = Integer.parseInt(AccountNumber);
	     			Date date = new Date();  
	     			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	     			String Transaction_Date = formatter.format(date);
	     			String Details = BillTypeSelect;
	     			Float Debit = Float.parseFloat(Amount);
	     			Float Credit = 0.00f;
	     			Float Balance = Float.parseFloat(BalanceTemp) - Float.parseFloat(Amount);
	     			
	     			if(Balance < 0.00 || Float.parseFloat(Amount) > Float.parseFloat(BalanceTemp)) {
	     				String message = "Insufficient funds!";
		     			req.setAttribute("message", message);
		     			req.getRequestDispatcher("/JSPs/billPayment.jsp").forward(req, res);
	     			}
	     			else {
	     				PreparedStatement pstmt = con.prepareStatement("INSERT INTO "+AccountTypeSelect+" (Transaction_ID,Account_Number,Transaction_Date,Details,Debit,Credit,Balance) VALUES (?,?,?,?,?,?,?)");
	     				pstmt.clearParameters();
	     				pstmt.setString(1, Transaction_ID);
	     				pstmt.setInt(2, Account_Number);
	     				pstmt.setString(3, Transaction_Date);
	     				pstmt.setString(4, Details);
	     				pstmt.setFloat(5, Debit);
	     				pstmt.setFloat(6, Credit);
	     				pstmt.setFloat(7, Balance);
	     				pstmt.executeUpdate();
	     		
	     				String message = "You have succesfully paid your bill!";
	     				req.setAttribute("message", message);
	     				req.getRequestDispatcher("/JSPs/accountInfo.jsp").forward(req, res);
	     			}
	     		}
	     	}
 	 	}
        catch (Exception e) {
        	out.println("<br/>An error has occurred during the Statement/ResultSet phase!");
	    }   
         	
		finally {
			try {    
				if (rs != null) rs.close();
				if (rs2 != null) rs2.close();
				if (stmt != null) stmt.close();
				if (stmt2 != null) stmt2.close();
				if (con != null) con.close();
			}
			catch (Exception ex) {
				out.println("<br/>An error occurred while closing down connection/statement"); 
			}
        }
		out.close();
	}
}