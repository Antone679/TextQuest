<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Login</title>
      <style>
            body {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .login-form {
                text-align: center;
                margin-top: 20px;
            }
            .error-message {
                color: red;
                margin-bottom: 20px;
                text-align: center;
            }
        </style>
</head>
<body>
   <c:if test="${sessionScope.auth == false}">
 <h3 class="error-message">Неверный логин или пароль!</h3>
    </c:if>
    <form action="login" method="post" class="login-form">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div>
            <button type="submit">Login</button>
        </div>
    </form>
</body>
</html>