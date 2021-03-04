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

<section class="login-page">
	<h2>Utwórz nowe hasło</h2>
	<form id="resetPass" action="/password/reset/${email}" method="post">
        <div class="form-group">
          <input type="password" name="password" placeholder="Hasło"/>
          <p class="warning"></p>
        </div>
        <div class="form-group">
          <input type="password" name="password2" placeholder="Powtórz hasło"/>
          <p class="warning"></p>
        </div>
        <div class="form-group form-group--buttons">     
          <input type="submit" class="btn" value="Zapisz"/> 
        </div>
        <div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
	</form>
</section>

</header>

<%@ include file="footer.jsp" %>

<script src="/resources/js/app-reset.js"></script>

</body>
</html>
