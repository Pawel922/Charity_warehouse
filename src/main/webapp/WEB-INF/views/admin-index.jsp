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

<%@ include file="header.jsp" %>

<div class="slogan container container--90">
	<table>
    	<tr><td><a href="/institution/all" class="btn btn--small btn--without-border">Instytucje</a></td></tr>
       	<tr><td><a href="/admin/all" class="btn btn--small btn--without-border">Administratorzy</a></td></tr>
       	<tr><td><a href="/user/all" class="btn btn--small btn--without-border">Użytkownicy</a></td></tr>
	</table>
</div>
    
</header>

<%@ include file="footer.jsp" %>

</body>
</html>
