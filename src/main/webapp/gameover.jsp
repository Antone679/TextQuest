<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Игра окончена</title>
</head>
<body>
    <h1>Игра окончена!</h1>
    <p>Спасибо за участие!</p>
    <p>Ваш результат: ${sessionScope.counter} вопросов.</p>
    <form action="welcome" method="post">
                    <button type="submit">Попробовать еще раз!</button>
                </form>
</body>
</html>