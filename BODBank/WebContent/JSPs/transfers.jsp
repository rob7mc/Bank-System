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
        <h2>Transfer Money Between Your Accounts:</h2>
        <form name="TransferForm" method="post" action="/BODBank/transfersServlet">
            <table class="TransferTable">
                <tr>
                    <td>Account to Transfer from:</td>
                    <td>
                        <div class="CenterAlign">
                        <select name="ACCOUNT_TYPE1" required>
                            <option disabled selected value>--Please Select--</option>
                            <option value="BOD_CURRENT_ACCOUNT">Current Account</option>
                            <option value="BOD_SAVINGS_ACCOUNT">Savings Account</option>
                        </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Account to Transfer to:</td>
                    <td>
                        <div class="CenterAlign">
                        <select name="ACCOUNT_TYPE2" required>
                            <option disabled selected value>--Please Select--</option>
                            <option value="BOD_CURRENT_ACCOUNT">Current Account</option>
                            <option value="BOD_SAVINGS_ACCOUNT">Savings Account</option>
                        </select>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Transfer Amount:</td>
                    <td>
                    	<div class="CenterAlign">
                    		&euro;<input type="number" pattern="[0-9]" step="0.01" class="form-control" name="Amount" placeholder="Amount..." required>
                    	</div>
                    </td>
                </tr>
                <tr>
                    <td>Click to Complete Transfer:</td>
                    <td>
                    	<div class="CenterAlign">
                    		<input type="submit" value="Transfer" name="Transfer"/>
                    	</div>
                    </td>
                </tr>
            </table>
        </form>
        
        <form name="LogoutForm" method="post" action="/BODBank/transfersServlet">
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