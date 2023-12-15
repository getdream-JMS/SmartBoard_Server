<%@ page contentType="text/html; charset=EUC-KR" %>
<%
	String userId = (String)session.getAttribute("userId");
	String userName = (String)session.getAttribute("userName");
	String organId = (String)session.getAttribute("organId");
	String organName = (String)session.getAttribute("organName");

	out.println("userId :::: " + userId );	
	out.println("userName :::: " + userName );	
	out.println("organId :::: " + organId );	
	out.println("organName :::: " + organName );	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
 <head>
  <meta charset="euc-kr">
  <title> new document </title>
 </head>

 <body>
  <form name="form1" method="post" >
	<input type="text" name="userId" value="<%=userId%>" >
	<input type="text" name="userName" value="<%=userName%>" >
	<input type="text" name="organId" value="<%=organId%>" >
	<input type="text" name="organName" value="<%=organName%>" >
  </form>
 </body>
</html>
