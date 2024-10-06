<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="../../index.html">Home</a></h3>
<hr>
<section>
    <h2>Meals</h2>
    <a href="">Add meal</a>
    <table border="1" cellpadding="8" cellspacing="0">
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        <c:forEach items="${storage}" var="meal">
            <jsp:useBean id="meal" type="ru.javawebinar.topjava.model.MealTo"/>
            <tr>
                <td>${meal.dateTime}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?id=${meal.id}&action=update">Update</a></td>
                <td><a href="meals?id=${meal.id}&action=delete">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>