<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Users</h1>
<table>
    <c:forEach var="users" items="${users}">
        <tr>
            <td>${users.id}</td>
            <td>${users.name}</td>
            <td>${users.family}</td>
            <td>${users.balans}</td>
            <td>${users.email}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/delete?idToDelete=${users.id}">Delete</a>
                <a href="${pageContext.request.contextPath}/admin/update?idToUpdate=${users.id}">Update</a>
            </td>
        </tr>

    </c:forEach>

</table>
<form action="${pageContext.request.contextPath}/admin/add" method="POST">
    <legend>Add user</legend>
    <p> <label>Почта:<br><input type="email" name="email"></label></p>
    <p> <label>Пароль:<br><input type="password" name="password"></label></p>
    <p> <label>Имя:<br><input type="text" name="name"></label></p>
    <p><label>Фамилия:<br><input type="text" name="family"></label></p>
    <p> <label>Баланс:<br><input type="text" name="balans"></label></p>
    <input type="hidden" name="balans" value="0">
    <input type="submit">
</form>

<jsp:include page="logOut.jsp" />
</body>
</html>
