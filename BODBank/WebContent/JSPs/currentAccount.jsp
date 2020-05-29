<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <h1>
            <img src="/BODBank/Images/DCU_Logo.png" alt="DCU Logo" width="60" height="60"/>
            Bank of DCU
            <img src="/BODBank/Images/DCU_Logo.png" alt="DCU Logo" width="60" height="60"/>
        </h1>
        <link rel="stylesheet" type="text/css" href="/BODBank/CSS/CurrentSavingsAccounts.css" />
    </head>
    
    <body> 	
    	<form name="LogoutForm" method="post" action="/BODBank/accountInfoServlet">
        	<div class="LogoutButton">
        		<input type="submit" value="Logout" name="Logout" onchange="javascript:document.LogoutForm.submit();"/>
        	</div>
        </form>
        
        <h2>Your Current Account:</h2>
            <main>
            <table class="CurrentAccountTable">
            	<tr>
            		<th>Transaction ID</th>
            		<th>Account Number</th>
            		<th>Transaction Date</th>
            		<th>Details</th>
            		<th>Debit</th>
            		<th>Credit</th>
            		<th>Balance</th>
            	</tr>
            	<c:forEach items="${AccountInfo}" var="a">
                <tr>             
                    <td><c:out value = "${a.transactionID}"/></td>
                    <td><c:out value = "${a.accountNumber}"/></td>
                    <td><c:out value = "${a.transactionDate}"/></td>
                    <td><c:out value = "${a.details}"/></td>
                    <td><c:out value = "${a.debit}"/></td>
                    <td><c:out value = "${a.credit}"/></td>
                    <td><c:out value = "${a.balance}"/></td>
                </tr>
                </c:forEach>
            </table>
            </main>
        
        <div class="footer">
        <pre>Phone: (01) 123456, Email: bankofdcu@bod.ie ----- This bank will rob you of all your money... T's &amp; C's Apply! ----- &copy; 2020 Bank of DCU</pre>
        </div> 
    </body>
</html>