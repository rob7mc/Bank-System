<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <h1>
            <img src="/BODBank/Images/DCU_Logo.png" alt="DCU Logo" width="60" height="60"/>
            Bank of DCU
            <img src="/BODBank/Images/DCU_Logo.png" alt="DCU Logo" width="60" height="60"/>
        </h1>
        <link rel="stylesheet" type="text/css" href="/BODBank/CSS/AccountInfo.css" />
    </head>
    
    <body>
        <h2>Pay a Bill:</h2>
        <form name="BillPaymentForm" method="post" action="/BODBank/billPaymentServlet">
            <table class="BillPaymentTable">
                <tr>
                    <td>Account to Pay from:</td>
                    <td>
                        <div class="CenterAlign">
                        <select name="ACCOUNT_TYPE" required>
                        	<option disabled selected value>--Please Select--</option>
                            <option value="BOD_CURRENT_ACCOUNT">Current Account</option>
                            <option value="BOD_SAVINGS_ACCOUNT">Savings Account</option>
                        </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Bill to Pay:</td>
                    <td>
                        <div class="CenterAlign">
                        <select name="BILL_TYPE" required>
                        	<option disabled selected value>--Please Select--</option>
                            <option value="Electricity">Electricity</option>
                            <option value="Gas">Gas</option>
                            <option value="BinCollection">Bin Collection</option>
                            <option value="Telephone">Telephone</option>
                            <option value="TV">TV</option>
                        </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Payment Amount:</td>
                    <td>
                        <div class="CenterAlign">
                            &euro;<input type="number" pattern="[0-9]" step="0.01" class="form-control" name="Amount" placeholder="Amount..." required>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Click to Complete Payment:</td>
                    <td>
                    	<div class="CenterAlign">
                    		<input type="submit" value="Pay" name="Pay"/>
                    	</div>
                    </td>
                </tr>
            </table>
        </form>
        
        <form name="LogoutForm" method="post" action="/BODBank/billPaymentServlet">
        	<div class="LogoutButton">
        		<input type="submit" value="Logout" name="Logout" onchange="javascript:document.LogoutForm.submit();"/>
        	</div>
        </form>
        
        <div class="Bill_Transfer">
        	<p>${message}</p>
        </div>
        
        <div class="footer">
        <pre>Phone: (01) 123456, Email: bankofdcu@bod.ie ----- This bank will rob you of all your money... T's &amp; C's Apply! ----- &copy; 2020 Bank of DCU</pre>
        </div>
    </body>
</html>