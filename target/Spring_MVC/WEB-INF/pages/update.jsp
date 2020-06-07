<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: aks
  Date: 07.05.2020
  Time: 1:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateUser</title>

    <c:set var="user" scope="request" value="${user}" />

</head>
<body>
<form action="${pageContext.request.contextPath}/update" method="POST">

    <legend>Введите новые данные:</legend>
    <p> <label>Имя:<br><input type="text" name="name" value="${user.name}"></label></p>
    <p><label>Фамилия:<br><input type="text" name="family" value="${user.family}"></label></p>
    <p> <label>Баланс:<br><input type="text" name="balans" value="${user.balans}"></label></p>
    <p> <input type="hidden" name="id" value="${user.id}">
    <p> <input type="submit"></p>
</form>
</body>
</html>
