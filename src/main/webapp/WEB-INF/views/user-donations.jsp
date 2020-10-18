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
              			<li><a class="btn btn--small btn--without-border" href="/user-donations">Moje zbiórki</a></li>
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
       			<th>Kategoria</th>
       			<th>Fundacja</th>
       			<th>Ilość worków</th>
       		</tr>
       		<c:forEach items="${donations}" var="donation" varStatus="theCountExt">
       			<tr>
       				<td>${theCountExt.count}</td>
       				<td>
       					<c:forEach items="${donation.categories}" var="category" varStatus="theCountInt">
       						${category.name}<c:if test="${not theCountInt.last}">,&nbsp</c:if> 
       					</c:forEach>
       				</td>
       				<td>${donation.institution.name}</td>
       				<td>${donation.quantity}</td>
       			</tr>
       		</c:forEach>
       	</table>
    </div>
</header>


<%@ include file="footer.jsp" %>
</body>
</html>
