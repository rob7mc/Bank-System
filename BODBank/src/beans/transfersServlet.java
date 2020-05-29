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

@WebServlet("/transfersServlet")
public class transfersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	Statement stmt = null;
	Statement stmt2 = null;
	Statement stmt3 = null;
	Statement stmt4 = null;
	ResultSet rs1 = null;
	ResultSet rs2 = null;
	ResultSet rs3 = null;
	ResultSet rs4 = null;
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
	     	String AccountTypeSelect1 = req.getParameter("ACCOUNT_TYPE1");
 	 		String AccountTypeSelect2 = req.getParameter("ACCOUNT_TYPE2");
 	 		if(AccountTypeSelect1.equals(AccountTypeSelect2)) {
 	 			AccountTypeSelect1 = null;
 	 			AccountTypeSelect2 = null;
 	 			String message = "Can't Transfer between the Same Account!";
     			req.setAttribute("message", message);
     			req.getRequestDispatcher("/JSPs/transfers.jsp").forward(req, res);
 	 		}
     		String AccountNumber = (String) session.getAttribute("AccountNumber");
      	    stmt = con.createStatement();
      	    stmt2 = con.createStatement();
      	    stmt3 = con.createStatement();
      	    stmt4 = con.createStatement();
	     	rs1 = stmt.executeQuery("select * from "+AccountTypeSelect1+" where transaction_id=(select max(transaction_id) from "+AccountTypeSelect1+")");
	     	rs2 = stmt2.executeQuery("select * from "+AccountTypeSelect2+" where transaction_id=(select max(transaction_id) from "+AccountTypeSelect2+")");
	     	rs3 = stmt3.executeQuery("select TT.* from "+AccountTypeSelect1+" TT INNER JOIN (select account_number, MAX(transaction_id) AS MaxTransactionID FROM "+AccountTypeSelect1+" where account_number = "+AccountNumber+" GROUP BY account_number) groupedTT ON TT.account_number = groupedTT.account_number AND TT.transaction_id = groupedTT.MaxTransactionID");
	     	rs4 = stmt4.executeQuery("select TT.* from "+AccountTypeSelect2+" TT INNER JOIN (select account_number, MAX(transaction_id) AS MaxTransactionID FROM "+AccountTypeSelect2+" where account_number = "+AccountNumber+" GROUP BY account_number) groupedTT ON TT.account_number = groupedTT.account_number AND TT.transaction_id = groupedTT.MaxTransactionID");
	     	
	     	while(rs1.next() && rs2.next() && rs3.next() && rs4.next()) {
	     		String TransactionID1 = rs1.getString("TRANSACTION_ID");
	     		String TransactionID2 = rs2.getString("TRANSACTION_ID");
	     		String BalanceTemp1 = rs3.getString("BALANCE");
	     		String BalanceTemp2 = rs4.getString("BALANCE");
	     	
	     		String Amount = req.getParameter("Amount");
	     		String Transfer = req.getParameter("Transfer");
	     	
	     		if(Transfer != null) {
	     			Integer i1 = Integer.parseInt(TransactionID1)+1;
	     			String Transaction_ID1 = Integer.toString(i1);
	     			Integer i2 = Integer.parseInt(TransactionID2)+1;
	     			String Transaction_ID2 = Integer.toString(i2);
	     			Integer Account_Number = Integer.parseInt(AccountNumber);
	     			Date date = new Date();  
	     			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	     			String Transaction_Date = formatter.format(date);
	     			String Details = "Account Transfer";
	     			Float Debit1 = Float.parseFloat(Amount);
	     			Float Debit2 = 0.00f;
	     			Float Credit1 = 0.00f;
	     			Float Credit2 = Float.parseFloat(Amount);
	     			Float Balance1 = Float.parseFloat(BalanceTemp1) - Float.parseFloat(Amount);
	     			Float Balance2 = Float.parseFloat(BalanceTemp2) + Float.parseFloat(Amount);
	     			
	     			if(Balance1 < 0.00 || Float.parseFloat(Amount) > Float.parseFloat(BalanceTemp1)) {
	     				String message = "Insufficient funds!";
		     			req.setAttribute("message", message);
		     			req.getRequestDispatcher("/JSPs/transfers.jsp").forward(req, res);
	     			}
	     			else {
	     				PreparedStatement pstmt1 = con.prepareStatement("INSERT INTO "+AccountTypeSelect1+" (Transaction_ID,Account_Number,Transaction_Date,Details,Debit,Credit,Balance) VALUES (?,?,?,?,?,?,?)");
	     				pstmt1.clearParameters();
	     				pstmt1.setString(1, Transaction_ID1);
	     				pstmt1.setInt(2, Account_Number);
	     				pstmt1.setString(3, Transaction_Date);
	     				pstmt1.setString(4, Details);
	     				pstmt1.setFloat(5, Debit1);
	     				pstmt1.setFloat(6, Credit1);
	     				pstmt1.setFloat(7, Balance1);
	     				pstmt1.executeUpdate();
	     			
	     				PreparedStatement pstmt2 = con.prepareStatement("INSERT INTO "+AccountTypeSelect2+" (Transaction_ID,Account_Number,Transaction_Date,Details,Debit,Credit,Balance) VALUES (?,?,?,?,?,?,?)");
	     				pstmt2.clearParameters();
	     				pstmt2.setString(1, Transaction_ID2);
	     				pstmt2.setInt(2, Account_Number);
	     				pstmt2.setString(3, Transaction_Date);
	     				pstmt2.setString(4, Details);
	     				pstmt2.setFloat(5, Debit2);
	     				pstmt2.setFloat(6, Credit2);
	     				pstmt2.setFloat(7, Balance2);
	     				pstmt2.executeUpdate();
	     		
	     				String message = "You have succesfully transferred between accounts!";
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
				if (rs1 != null) rs1.close();
				if (rs2 != null) rs2.close();
				if (rs3 != null) rs3.close();
				if (rs4 != null) rs4.close();
				if (stmt != null) stmt.close();
				if (stmt2 != null) stmt2.close();
				if (stmt3 != null) stmt3.close();
				if (stmt4 != null) stmt4.close();
				if (con != null) con.close();
			}
			catch (Exception ex) {
				out.println("<br/>An error occurred while closing down connection/statement"); 
			}
        }
		out.close();
	}
}
