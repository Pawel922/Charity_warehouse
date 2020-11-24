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
    			<c:choose>
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

<%@ include file="footer.jsp" %>

</body>
</html>
