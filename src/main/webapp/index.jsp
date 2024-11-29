<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*" %>
<%@ page import="com.quest.entity.Unit" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Квест</title>
     <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        h1 {
            font-size: 2.5em;
            padding-left: 1.5em;
        }
        h2 {
            font-size: 1.5em;
            padding-left: 1.5em;
        }
        h3 {
            font-size: 1.0em;
            padding-left: 1.5em;
        }
        p {
            width: 33%;
            word-wrap: break-word;
            padding-left: 1.5em;
        }
        #footer {
            position: fixed;
            bottom: 10px;
            left: 10px;
            font-size: 14px;
            color: white;
                }
        #footer2 {
            position: fixed;
            bottom: 30px;
            left: 10px;
            font-size: 14px;
            color: white;
                }
        #footer3 {
             position: fixed;
             bottom: 45px;
             left: 10px;
             color: white;
             width: 33%;
                 }
        hr {
             border: none;
             height: 2px;
             background-color: #555;
             margin: 20px 0;
               width: 33%;
                 }
                 body {

                 height: 100vh;
                  margin: 0;
                  background-image: url("<%= request.getContextPath() %>/images/green_blue_back.jpg");
                  background-size: cover; /* Масштабирование изображения */
                  background-position: center; /* Центрирование изображения */
                  background-repeat: no-repeat; /* Без повторения изображения */
                  height: 100vh; /* Занимает всю высоту экрана */
                  color: white;
                             }
                  .black-text {
                  color: black;
                  }
    </style>
</head>
<body style="background-color: #f0f8ff;">
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Стартовая страница</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarScroll">
      <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#">О нас</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Квесты
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="#">Необитаемый остров</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Ведьмак</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">Космическая одиссея</a></li>
          </ul>
        </li>
 </ul>

  <div class="d-flex align-items-center"> <!-- Обернуть форму и выход в div -->
          <form class="d-flex me-2" role="search"> <!-- Добавить класс me-2 для отступа -->
            <input class="form-control" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>

          <a class="nav-link black-text" href="#">Выход</a>
        </div>

    </div>
  </div>
</nav>

<%
 Boolean auth = (Boolean) session.getAttribute("auth");

    if (auth == null || auth == false) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

 <div id="footer3">
               <hr>
        </div>

<div id="footer">
        <c:if test="${not empty sessionScope.timesPlayed}">
            Игр сыграно: ${sessionScope.timesPlayed}
        </c:if>
    </div>

<div id="footer2">
        <c:if test="${not empty sessionScope.ipAddress}">
            IP: : ${sessionScope.ipAddress}
        </c:if>

    </div>

    <h1>Предыстория</h1>
    <p>Предлагаем Вам пройти текстовый квест на 10 вопросов. В каждом вопросе есть правильные и неправильные ответы. В случае выбора
    неправильного ответа игра заканчивается. Постарайтесь пройти его, выбирая только верные ответы. Удачи!</p>
    <h2>Необитаемый остров</h2>

<hr>

   <c:if test="${empty sessionScope.username}">
       <p>Вы оказались на необитаемом острове после кораблекрушения.</p>
   </c:if>

    <c:if test="${not empty sessionScope.username}">
        <c:if test="${sessionScope.counter == 0}">
            <h2>Добро пожаловать, ${sessionScope.username}!</h2>
        </c:if>

        <c:if test="${sessionScope.isCorrect != null && sessionScope.isCorrect == false}">
            <h2>Игра окончена!</h2>
            <p>${sessionScope.failure}</p>
             <p>Количество правильных ответов: ${sessionScope.correctAnswers != null ? sessionScope.correctAnswers : 0}</p>
             <form action="reset" method="post">
                            <button type="submit">Сыграть еще раз под тем же именем!</button>
                        </form>
            <form action="restart" method="post">
                <button type="submit">Попробовать под другим именем!</button>
            </form>
        </c:if>

        <c:if test="${sessionScope.gameWon == true}">
            <h2>Поздравляем! Вы ответили на все вопросы правильно!</h2>
            <p>Вы успешно преодолели все трудности на необитаемом острове! Ваша находчивость и смелость помогли вам выжить.</p>
            <p>Количество правильных ответов: ${sessionScope.correctAnswers}</p>
             <form action="reset" method="post">
                                        <button type="submit">Сыграть еще раз под тем же именем!</button>
                                    </form>
                        <form action="restart" method="post">
                            <button type="submit">Попробовать под другим именем!</button>
                        </form>
        </c:if>

        <c:if test="${sessionScope.isCorrect == null || sessionScope.isCorrect == true}">
            <c:if test="${not empty sessionScope.questions}">
                <c:set var="counter" value="${sessionScope.counter}" />
                <c:set var="questionKeys" value="${sessionScope.questions.keySet().toArray()}" />

                <c:if test="${sessionScope.gameWon == false}">
                    <c:set var="currentKey" value="${questionKeys[counter]}" />
                    <c:set var="currentQuestion" value="${sessionScope.questions[currentKey]}" />
                    <p>${currentQuestion.question}</p>

                    <br/>

                    <c:forEach var="answer" items="${sessionScope.answers}">
                        <form action="answer" method="post">
                            <input type="hidden" name="questionId" value="${currentQuestion.id}"/>
                            <button type="submit" name="answer" value="${answer}">${answer}</button>
                        </form>
                        <br>
                    </c:forEach>
                </c:if>
            </c:if>
        </c:if>
    </c:if>

    <c:if test="${empty sessionScope.username}">
        <form action="welcome" method="post">
            <label for="username">Ваше имя:</label>
            <input type="text" id="username" name="username" required>
            <button type="submit">Отправить</button>
        </form>
    </c:if>

</body>
</html>