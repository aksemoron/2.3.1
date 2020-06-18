<%--
  Created by IntelliJ IDEA.
  User: Святослав
  Date: 25.11.2019
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/login">
    <input name="username"/>
    <input name="password"/>
    <input type="submit"/>
</form>

</body>
</html>
