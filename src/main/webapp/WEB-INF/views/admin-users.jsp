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
       						<td><a href="/user/disable/${user.id}" style="background-color: green" class="btn">Blokuj</a></td>
       					</c:when>
       					<c:when test="${user.enabled == 0}">
       						<td ><a href="/user/enable/${user.id}" style="background-color: red" class="btn">Odblokuj</a></td>
       					</c:when>
       				</c:choose>
       				<td><a href="/user/delete/${user.id}" class="btn">Usuń</a></td>
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

<%@ include file="footer.jsp" %>
</body>
</html>
