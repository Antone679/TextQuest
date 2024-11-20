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

<h3>
  Регистрация
  <br>
  <small class="text-body-secondary">  в мир квестов</small>
</h3>

 <c:if test="${sessionScope.existLogin == true}">
 <div class="text-bg-danger p-3">Такой логин уже занят!</div>
    </c:if>

     <c:if test="${sessionScope.existPassword == true}">
     <div class="text-bg-danger p-3">Такой пароль уже используется!</div>
        </c:if>

<form action="register" method="post" class="login-form">
        <div class="mb-3">
          <label for="name" class="form-label">Name</label>
          <input type="text" class="form-control" id="name" name="name" required placeholder="name">
        </div>

         <div class="mb-3">
                  <label for="password" class="form-label">Password</label>
                  <input type="password" class="form-control" id="password" name="password" required placeholder="*">
                </div>
<div class="mb-3">
                  <label for="email" class="form-label">Email</label>
                  <input type="email" class="form-control" id="email" name="email" required placeholder="name@domain.com">
                </div>
        <div>
            <button type="submit" class="btn btn-primary">Register</button>
        </div>
    </form>

</body>
</html>