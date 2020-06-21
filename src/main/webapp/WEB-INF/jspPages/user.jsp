<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
</head>
<body>
    <h1>User</h1>

    <c:set var="user" scope="request" value="${user}" />
    <tr>
        <p> <td>ID :${user.id}</td></p>
        <p> <td>Имя:${user.name}</td></p>
        <p> <td>Фамилия:${user.family}</td></p>
        <p> <td>Баланс:${user.balans}</td></p>
        <p> <td>Почта:${user.email}</td></p>
    </tr>


    <jsp:include page="logOut.jsp" />

</body>
</html>
