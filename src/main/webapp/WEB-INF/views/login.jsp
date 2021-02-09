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
	<h2>Zaloguj się</h2>
	<form name="login" method="post">
        <div class="form-group">
          <input type="text" name="email" placeholder="Email" />
          <p class="warning"><c:if test="${not empty param.auth}">Nieprawidłowy email lub hasło</c:if></p>
        </div>
        <div class="form-group">
          <input type="password" name="password" placeholder="Hasło" />
          <p class="warning"></p>
          <button data-name="repeatBtn" class="btn">Przypomnij hasło</button>
        </div>
        <div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </div>
        <div class="form-group form-group--buttons">
          <a href="/register" class="btn btn--without-border">Załóż konto</a>      
          <button class="btn" type="submit">Zaloguj się</button> 
        </div>
	</form>
</section>

</header>

<div id="myModal" class="modal">
 	<!-- Modal content -->
	<div class="modal-content">
  		<div class="modal-header">
  			<h2>Przypomnienie hasła</h2>
    		<span class="close">&times;</span>
  		</div>
  		<div class="modal-body">
  			<h1>Podaj nam swój e-mail użyty przy rejestracji</h1>
    		<form class="formTable" method="post">
    			<table>
    				<tr>
    					<td>E-mail:</td>
    					<td><input data-name="email" name="email"></td>
    					<td><button type="submit" class="btn">Wyślij</button></td>
    				</tr>
    			</table>
    		</form>
    		<div>
    			<p class="warning"></p>
    		</div>
  		</div>
  	</div>
</div>

<%@ include file="footer.jsp" %>

<script src="<c:url value="/resources/js/app-login.js"/>"></script>
<script src="<c:url value="/resources/js/app-repeat-modal.js"/>"></script>

</body>
</html>
