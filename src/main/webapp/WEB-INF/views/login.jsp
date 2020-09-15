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
   <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>
</head>
<body>
<header>
    <nav class="container container--70">
      <ul class="nav--actions">
         <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
         <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
      </ul>
      <%@ include file="header.jsp" %>
    </nav>
</header>

<section class="login-page">
   	  <h2>Zaloguj się</h2>
      <form name="login" method="post">
        <div class="form-group">
          <input type="email" name="email" placeholder="Email" />
          <p class="warning"></p>
        </div>
        <div class="form-group">
          <input type="password" name="password" placeholder="Hasło" />
          <p class="warning"></p>
          <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>
        <div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
        <div>
        	<c:if test="${not empty param.auth}">
        		<p class="warning">Nieprawidłowy email lub hasło</p>
        	</c:if>
        </div>
        <div class="form-group form-group--buttons">
          <a href="/register" class="btn btn--without-border">Załóż konto</a>      
          <button class="btn" type="submit">Zaloguj się</button> 
        </div>
      </form>
</section>

<%@ include file="footer.jsp" %>

<script src="<c:url value="/resources/js/app-login.js"/>"></script>

</body>
</html>
