<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">

<head>
    <title>Meal</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<h3><a href="${pageContext.request.contextPath}/index.html">Home</a></h3>
<hr>

<c:choose>
    <c:when test="${param.action=='add'}">
        <h2>Add meal</h2>
    </c:when>
    <c:when test="${param.action=='update'}">
        <h2>Edit meal</h2>
    </c:when>
</c:choose>

<jsp:useBean id="meal" type="ru.javawebinar.topjava.model.Meal" scope="request"/>
<form method="post" action="meals">
    <input type="hidden" name="id" value="${meal.id}"/>
    <dl>
        <dt>DateTime</dt>
        <dd><input type="datetime-local" name="dateTime" value="${meal.dateTime}" required/></dd>
    </dl>
    <dl>
        <dt>Description</dt>
        <dd><input type="text" name="description" value="${meal.description}" required/></dd>
    </dl>
    <dl>
        <dt>Calories</dt>
        <dd><input type="number" name="calories" value="${meal.calories}" required/></dd>
    </dl>
    <br>
    <button type="submit">Save</button>
    <button type="button" onclick="window.history.back()">Cancel</button>
</form>
</body>
</html>