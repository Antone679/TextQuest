<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="ru">
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
      <style>
            body {
                display: flex;
                flex-direction: column;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                background-image: url("<%= request.getContextPath() %>/images/auth_back.png");
                background-size: cover; /* Масштабирование изображения */
                background-position: center; /* Центрирование изображения */
                background-repeat: no-repeat; /* Без повторения изображения */
                height: 100vh; /* Занимает всю высоту экрана */
            }
            .login-form {
                text-align: center;
                margin-top: 20px;
            }

        </style>
</head>
<body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<p class="fs-2">Авторизация</p>

   <c:if test="${sessionScope.auth == false}">
 <div class="text-bg-danger p-3">Неверный логин или пароль!</div>
    </c:if>
    <form action="login" method="post" class="login-form">


        <div class="mb-3">
          <label for="username" class="form-label">Username</label>
          <input type="text" class="form-control" id="username" name="username" required placeholder="user">
        </div>

         <div class="mb-3">
                  <label for="password" class="form-label">Password</label>
                  <input type="password" class="form-control" id="password" name="password" required placeholder="****">
                </div>

        <div>
            <button type="submit" class="btn btn-primary">Login</button>
        </div>
    </form>
</body>
</html>