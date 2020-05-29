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
        <script src="/BODBank/Javascript/ajax_javascript.js"></script>
    </head>
      
    <body>
        <h2>Your Account Information:</h2>
           <form name="myform2" method="POST" action="/BODBank/accountInfoServlet"> 
	       <table class="AccountInfoTable">
               <tr>
               		<td>Account Holder Name:</td>
               		<td>
               		<div class="CenterAlign">
               			${AccountHolder}
               		</div>
               		</td>
			   </tr>
               <tr>
                   <td>Account Number:</td>
                   <td>
                   <div class="CenterAlign">
               	   		${AccountNumber}
          		   </div>
               	   </td>
               </tr>
               <tr>
                   <td>Email:</td>
                   <td>
                   <div class="CenterAlign">
               	   		${Email}
          		   </div>
               	   </td>
               </tr>
               <tr>
                   <td>Branch:</td>
                   <td>
                   <div class="CenterAlign">
               	   		${Branch}
          		   </div>
               	   </td>
               </tr>
               <tr>
                   <td>View Your Accounts:</td>
                   <td>
                       <div class="CenterAlign">
                           <select name="AccountType" onchange="javascript:document.myform2.submit();">
                           		<option selected disabled>--Please Select--</option> 
                               	<option value="bod_current_account">Current Account</option>
                               	<option value="bod_savings_account">Savings Account</option>
                           </select>
                       </div>
                   </td>
               </tr>
               <tr>
                   <td>Transfer Money Between Accounts:</td>
                   <td>
                   <div class="CenterAlign">
                   <input type="submit" value="Account Transfers" name="AccountTransfers"/>
                   </div>
                   </td>
               </tr>
               <tr>
                   <td>Pay A Bill:</td>
                   <td>
                   <div class="CenterAlign">
                   <input type="submit" value="Bill Payment" name="BillPayment"/>
                   </div>
                   </td>
               </tr>
           </table>
           </form>
           
        <form name="LogoutForm" method="post" action="/BODBank/accountInfoServlet">
        	<div class="LogoutButton">
        		<input type="submit" value="Logout" name="Logout" onchange="javascript:document.LogoutForm.submit();"/>
        	</div>
        </form>
        
        <div class="Bill_Transfer">
        	<p>${message}</p>
        </div>
           
		<h2>Search for an ATM:</h2>
		<div class="CenterAlign">
			<p><b>Enter a Location in the Box Below:</b></p>
			<form id="searchNames" method="GET" action="">
				<b>Location:</b>
				<input type="text" autocomplete="off" onkeyup="showHint(this.value)" name="searchString" size="20" maxlength="30"/>
			</form><br/>
			<div class="box" id="autodiv1">
				<span id="txtHint">Search for a Valid ATM Location!</span>
			</div>
		</div>
        
        <div class="footer">
        <pre>Phone: (01) 123456, Email: bankofdcu@bod.ie ----- This bank will rob you of all your money... T's &amp; C's Apply! ----- &copy; 2020 Bank of DCU</pre>
        </div>
    </body>
</html>