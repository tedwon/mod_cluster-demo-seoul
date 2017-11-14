<html>
<head>
    <title>JSessionID test App</title>
</head>
<body>

<h1>JSessionID test App.</h1>

<%
    String JSESSIONID = "JSESSIONID";
    String JSessionID = this.getServletContext().getSessionCookieConfig().getName();
    if (JSessionID.equals(JSESSIONID)) {
        out.println("<BR>JSessionID Name is same as JSESSIONID=" + JSessionID);
    } else {
        out.println("<BR>JSessionID Name changed to=" + JSessionID);

    }

%>

</body>
</html>
