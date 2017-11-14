<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="US-ASCII" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
    <title>Login Success Page</title>
</head>
<body>
<%
    final String serverName = "Server " + System.getProperty("server.name") + " ";
    //allow access only if session exists1
    String user = null;
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.html");
    } else user = (String) session.getAttribute("user");
    String userName = null;
    String sessionID = null;
    String sessionCookieName = this.getServletContext().getSessionCookieConfig().getName();
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) userName = cookie.getValue();
            if (cookie.getName().equals(sessionCookieName)) sessionID = cookie.getValue();
        }
    }
    final String sessionIdWithCookieName = sessionCookieName + "=" + sessionID;
    System.out.println("[LoginSuccess.jsp] " + sessionIdWithCookieName);
%>
<h3>
    Hi <%=userName %>, Login successful.
    <br/>
    <%=serverName%>: <%=sessionIdWithCookieName%>
</h3>
<br>
User=<%=user %>
<br>
<a href="CheckoutPage.jsp">Checkout Page</a>
<form action="LogoutServlet" method="post">
    <input type="submit" value="Logout">
</form>
</body>
</html>