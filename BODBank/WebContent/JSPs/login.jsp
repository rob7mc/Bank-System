<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <h1>
            <img src="/BODBank/Images/DCU_Logo.png" alt="DCU Logo" width="60" height="60"/>
            Bank of DCU
            <img src="/BODBank/Images/DCU_Logo.png" alt="DCU Logo" width="60" height="60"/>
        </h1>
        <link rel="stylesheet" type="text/css" href="/BODBank/CSS/login.css" />
    </head>
    
    <body>  		
        <h2>Welcome to Bank of DCU...</h2>
        
        <div id="boxContainerContainer">
        <div id="boxContainer">
        <form method="POST" action="/BODBank/loginServlet" name="myform">
            <h3>Secure Login</h3>
            
            <table>		
                <tr>			
                    <td>Username:</td>			
                    <td><input type=”text” name="usernamefield" placeholder="Enter Username..."></td>	
                </tr>
                <tr>
                    <td>Password:</td>			
                    <td><input type="password" name="passwordfield" placeholder="Enter Password..."/></td>
                </tr>
            </table>
            <div class="LoginFailed">
            <p>${message}</p>
            </div>
            <input type="submit" value="Login" />
        </form>
        </div>
        </div>
        
        <div class="LeftText">
            <H4>Bank Opening Times:</H4>
            <table class="tableformat">
                <tr><td>Monday</td><td>10am - 4pm</td></tr>
                <tr><td>Tuesday</td><td>10am - 4pm</td></tr>
                <tr><td>Wednesday</td><td>10am - 4pm</td></tr>
                <tr><td>Thursday</td><td>10am - 4pm</td></tr>
                <tr><td>Friday</td><td>10am - 4pm</td></tr>
                <tr><td>Saturday</td><td>10am - 2pm</td></tr>
                <tr><td>Sunday</td><td>Closed</td></tr>
            </table>
            <marquee><h5>Banking Your Way!</h5></marquee><br/>
        </div>
        
        <div class="BankImage">
            <img src="/BODBank/Images/Bank.jpg" alt="BankImage" width="90%"/>
        </div>
        
        <div class="RightText">
            <H4>Branch Locations:</H4>
            <table class="tableformat">
                <tr><td>Glasnevin</td><td>Dublin 9</td></tr>
                <tr><td>Raheny</td><td>Dublin 5</td></tr>
                <tr><td>Sutton</td><td>Dublin 13</td></tr>
                <tr><td>Malahide</td><td>Co.Dublin</td></tr>
                <tr><td>Clontarf</td><td>Dublin 3</td></tr>
                <tr><td>Baldoyle</td><td>Dublin 13</td></tr>
                <tr><td>Portmarnock</td><td>Co.Dublin</td></tr>
            </table>
            <marquee><h5>The Bank Of You!</h5></marquee><br/>
        </div>
        
        <div class="footer">
        <pre>Phone: (01) 123456, Email: bankofdcu@bod.ie ----- This bank will rob you of all your money... T's &amp; C's Apply! ----- &copy; 2020 Bank of DCU</pre>
        </div> 
    </body>
</html>
