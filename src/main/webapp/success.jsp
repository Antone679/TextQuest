<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Обращение</title>
    <style>
        h1 {
            font-size: 2.5em;
        }
        h2 {
            font-size: 1.5em;
        }
        p {
            width: 33.33%;
            word-wrap: break-word;
            padding-left: 1.5em;
        }
    </style>
</head>
<body>

    <c:if test="${not empty sessionScope.username}">
        <h1>Добро пожаловать, ${sessionScope.username}!</h1>
    </c:if>
    <c:if test="${empty sessionScope.username}">
        <h1>Добро пожаловать, незнакомец!</h1>
    </c:if>

    <h2>Это заголовок второго уровня</h2>
    <p>Это пример абзаца текста, который отображается на странице.
    Вы можете добавить сюда любую информацию, которую хотите показать пользователям.</p>

</body>
</html>