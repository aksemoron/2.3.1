<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aks
  Date: 04.06.2020
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cars</title>
</head>
<body>
<h1>${anotherHeader}</h1>
<table>

    <c:forEach var="cars" items="${cars}">
        <tr>
            <td>${cars.model}</td>
            <td>${cars.price}</td>
            <td>${cars.brand}</td>
        </tr>

    </c:forEach>

</table>
</body>
</html>
