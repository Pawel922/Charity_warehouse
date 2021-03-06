<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="pl">
<head>
   <meta charset="UTF-8" />
   <meta name="viewport" content="width=device-width, initial-scale=1.0" />
   <meta http-equiv="X-UA-Compatible" content="ie=edge" />
   <title>Document</title>
   <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header>

<%@ include file="header.jsp" %>

</header>

<section class="login-page">
	<h2>Załóż konto</h2>
    <form:form name="registration" method="post" modelAttribute="userToRegister">
      	<div class="form-group">
          <form:input path="name" placeholder="Imię"/>
          <p class="warning"></p>
        </div>
        <div class="form-group">
          <form:input path="surname" placeholder="Nazwisko"/>
          <p class="warning"></p>
        </div>
        <div class="form-group">
          <form:input path="email" placeholder="Email"/>
          <form:errors cssClass="warning" path="email" element="p"/>
          <p class="warning"></p>
        </div>
        <div class="form-group">
          <form:password path="password" name="password" placeholder="Hasło"/>
          <p class="warning"></p>
        </div>
        <div class="form-group">
          <input type="password" name="password2" placeholder="Powtórz hasło"/>
          <p class="warning"></p>
        </div>
        <div class="form-group form-group--buttons">
          <a href="/login" class="btn btn--without-border">Zaloguj się</a>
          <button class="btn" type="submit">Załóż konto</button>
        </div>
	</form:form>
</section>

<%@ include file="footer.jsp" %>

<script src="<c:url value="/resources/js/app-register.js"/>"></script>

</body>
</html>
