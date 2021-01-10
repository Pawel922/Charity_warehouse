<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
        	<sec:authorize access="isAuthenticated()">Witaj ${loggedUser.name}!
            	<ul class="dropdown">
              		<li><a class="btn btn--small btn--without-border" href="/user/edit/${loggedUser.id}/false">Profil</a></li>
              		<li><a class="btn btn--small btn--without-border" href="/user/donations?sortBy=all">Moje zbiórki</a></li>
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
    <ul>
    	<li><a href="/donation" class="btn btn--without-border active">Start</a></li>
    	<li><a href="#steps" class="btn btn--without-border">O co chodzi?</a></li>
    	<li><a href="#about-us" class="btn btn--without-border">O nas</a></li>
    	<li><a href="#help" class="btn btn--without-border">Fundacje i organizacje</a></li>
    	<li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
	</ul>    
</nav>
