<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">

<head>
    <title>Meal</title>
    <link rel="stylesheet" href="css/style.css">
</head>

<body>
<h3><a href="${pageContext.request.contextPath}/index.html">Home</a></h3>
<hr>
<h2>${param.action == "add" ?  "Add meal" : "Edit meal"}</h2>

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