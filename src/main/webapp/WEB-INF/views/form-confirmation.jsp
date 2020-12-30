<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<header class="header--form-page">
      <nav class="container container--70">
        <ul class="nav--actions">
          <li class="logged-user">
            Witaj ${loggedUser.name}!
            <ul class="dropdown">
              <li><a class="btn btn--small btn--without-border" href="/user/edit/${loggedUser.id}/false">Profil</a></li>
              <li><a class="btn btn--small btn--without-border" href="/user/donations">Moje zbiórki</a></li>
              <li>
              		<form action="<c:url value="/logout"/>" method="post">
        				<input class="btn btn--small btn--without-border" type="submit" value="Wyloguj">
        				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        			</form>
              </li>
            </ul>
          </li>
        </ul>
        <%@ include file="header.jsp" %>
      </nav>

<div class="slogan container container--90">
	<h2>Dziękujemy za przesłanie formularza. Na maila prześlemy wszelkie<br/>informacje o odbiorze.
	</h2>
</div>

</header>

<%@ include file="footer.jsp" %>

<script src="resources/js/app.js"></script>

</body>
</html>
