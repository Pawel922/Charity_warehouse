<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--main-page">

<%@ include file="header.jsp" %>

<div class="slogan container container--90">
    <form:form modelAttribute="user" method="post" cssClass="formTable">
    	<table>
    		<tr>
    			<th>Imię</th>
   				<td><form:input path="name"/></td>
   			</tr>
   			<tr>
   				<td></td>
    			<td><form:errors path="name" cssClass="warning"/></td>
    		</tr>
    		<tr>
   				<th>Nazwisko</th>
   				<td><form:input path="surname"/><td>
    		</tr>
    		<tr>
    			<td></td>
    			<td><form:errors path="surname" cssClass="warning"/></td>
    		</tr>
    		<tr>
    			<th>Email</th>
    			<td><form:input path="email"/><td>
    		</tr>
    		<c:if test="${not ignorableError}">
    			<tr>
    				<td></td>
    				<td><form:errors path="email" cssClass="warning"/></td>
    			</tr>
    		</c:if>
    		<tr>
    			<c:if test="${loggedUser.id == user.id}">
    				<tr>
    					<th>Hasło</th>
    					<td><button data-name="changeBtn" class="btn">Zmień</button></td>
    					<td><form:input path="password" type="hidden"/><td>
    				</tr>
    			</c:if>
    			<c:choose>
    				<c:when test="${loggedUser.id == user.id}">
    					<td><a href="/" class="btn">Wstecz</a></td>
    				</c:when>
    				<c:when test="${user.roles.stream().allMatch(r->r.name.equals('ROLE_ADMIN')).orElse(false)}">
    					<td><a href="/admin/all" class="btn">Wstecz</a></td>
    				</c:when>
    				<c:when test="${not user.roles.stream().allMatch(r->r.name.equals('ROLE_ADMIN')).orElse(false)}">
    					<td><a href="/user/all" class="btn">Wstecz</a></td>
    				</c:when>
    			</c:choose>
    			<td><button type="submit" class="btn">Zapisz</button></td>
    		</tr>
		</table>
	</form:form>
</div>
    
</header>

<div id="myModal" class="modal">
 	<!-- Modal content -->
	<div class="modal-content">
  		<div class="modal-header">
  			<h2>Zmiana hasła</h2>
    		<span class="close">&times;</span>
  		</div>
  		<div class="modal-body">
    		<form class="formTable" method="post">
    			<table>
    				<tr>
    					<td>Nowe hasło</td>
    					<td><input data-name="newPass" name="password" type="password"></td>
    				</tr>
    				<tr>
    					<td>Powtórz hasło</td>
    					<td><input data-name="repPass" type="password"></td>
    				</tr>
    				<tr>
    					<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
    					<td><input type="submit" value="Zmień"></td>
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

<script src="/resources/js/app-modal.js"></script>

</body>
</html>
