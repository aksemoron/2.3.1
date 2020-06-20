<%--
  Created by IntelliJ IDEA.
  User: aks
  Date: 17.06.2020
  Time: 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" modelAttribute="user" action="${pageContext.request.contextPath}/registration">
    <legend>Регистрация</legend>
    <p> <label>Почта:<br><input type="email" name="email"></label></p>
    <p> <label>Пароль:<br><input type="password" name="password"></label></p>
    <p> <label>Имя:<br><input type="text" name="name"></label></p>
    <p><label>Фамилия:<br><input type="text" name="family"></label></p>
    <p> <label>Баланс:<br><input type="text" name="balans"></label></p>
    <p> <input type="submit"></p>
</form>

<jsp:include page="logOut.jsp" />
</body>
</html>
