<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix ="fmt" uri ="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<header class="header--main-page">
    <nav class="container container--70">
        <ul class="nav--actions">
        	<li>
        		<sec:authorize access="!isAuthenticated()">
        			<a href="/login" class="btn btn--small btn--without-border">Zaloguj</a>
        		</sec:authorize>
        	</li>
        	<li>
        		<sec:authorize access="!isAuthenticated()">
        			<a href="/register" class="btn btn--small btn--without-border">Załóż konto</a>
        		</sec:authorize>
        	</li>
        	<li class="logged-user">
        		<sec:authorize access="isAuthenticated()">
            		Witaj ${loggedUser.name}!
            		<ul class="dropdown">
              			<li><a class="btn btn--small btn--without-border" href="/profile">Profil</a></li>
              			<li>
              				<form action="<c:url value="/logout"/>" method="post">
        						<input class="btn btn--small btn--without-border" type="submit" value="Wyloguj">
        						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        					</form>
              			</li>
            		</ul>
        		</sec:authorize>
        	</li>
        </ul>
        <%@ include file="header.jsp" %>
    </nav>

    <div class="slogan container container--90">
		<table>
       		<tr>
       			<th>Lp.</th>
       			<th>Imię</th>
       			<th>Nazwisko</th>
       			<th>Email</th>
       			<th></th>
       			<th></th>
       			<th></th>
       		</tr>
       		<c:forEach items="${users}" var="user" varStatus="theCount">
       			<tr>
       				<td>${theCount.count}</td>
       				<td>${user.name}</td>
       				<td>${user.surname}</td>
       				<td>${user.email}</td>
       				<td><a href="/user/edit/${user.id}/false" class="btn">Edytuj</a></td>
       				<c:choose>
       					<c:when test="${user.enabled == 1}">
       						<td><button data-status="disable" data-href="/user/disable/${user.id}" style="background-color: green" class="btn">Blokuj</button></td>
       					</c:when>
       					<c:when test="${user.enabled == 0}">
       						<td ><button data-status="enable" data-href="/user/enable/${user.id}" style="background-color: red" class="btn">Odblokuj</button></td>
       					</c:when>
       				</c:choose>
       				<td><button data-name="removeBtn" data-href="/user/delete/${user.id}" class="btn">Usuń</button></td>
       			</tr>
       		</c:forEach>
       		<tr>
       			<td></td>
       			<td></td>
       			<td></td>
       			<td></td>
       			<td></td>
       			<td></td>
       			<td><a href="/admin" class="btn">Wstecz</a></td>
       		</tr>
       	</table>
    </div>
</header>

<div id="confirmModal" class="modal">
 	<!-- Modal content -->
	<div class="modal-content">
  		<div class="modal-header">
  			<h2>Potwierdzenie</h2>
    		<span class="close">&times;</span>
  		</div>
  		<div class="modal-body">
    		<p>Jesteś pewien, że chcesz usunąć tego użytkownika?</p>
    		<a href="/">Nie</a>
    		<a href="/">Tak</a>
  		</div>
  	</div>
</div>

<%@ include file="footer.jsp" %>

<script src="/resources/js/app-status.js"></script>

</body>
</html>
