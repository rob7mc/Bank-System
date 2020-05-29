var xmlHttp

function showHint(str) {
	if (str.length >= 1) {
		var url="/BODBank/ajax_javascriptServlet?sid=" + Math.random() + "&q=" + str
        xmlHttp=GetXmlHttpObject(stateChanged)
        xmlHttp.open("GET", url , true)
        xmlHttp.send(null)
    } 
    else { 
    	document.getElementById("txtHint").innerHTML="Search for a Valid ATM Location!";
    } 
} 

function stateChanged() { 
    if (xmlHttp.readyState==4 || xmlHttp.readyState=="complete") { 
    	document.getElementById("txtHint").innerHTML=xmlHttp.responseText 
    } 
} 

function GetXmlHttpObject(handler) { 
    var objXmlHttp=null
    if (navigator.userAgent.indexOf("MSIE")>=0) { 
        var strName="Msxml2.XMLHTTP"
        if (navigator.appVersion.indexOf("MSIE 5.5")>=0) {
            strName="Microsoft.XMLHTTP"
        } 
        try { 
            objXmlHttp=new ActiveXObject(strName)
            objXmlHttp.onreadystatechange=handler 
            return objXmlHttp
        } 
        catch(e) { 
            return 
        } 
    }

    if (navigator.userAgent.indexOf("Mozilla")>=0) {
        objXmlHttp=new XMLHttpRequest()
        objXmlHttp.onload=handler
        objXmlHttp.onerror=handler 
        return objXmlHttp
    }
}