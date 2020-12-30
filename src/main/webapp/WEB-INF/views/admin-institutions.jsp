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
       	<tr>
       		<th>Lp.</th>
       		<th>Name</th>
       		<th>Opis</th>
       		<th></th>
   			<th></th>
       	</tr>
       	<c:forEach items="${institutions}" var="institution" varStatus="theCount">
       		<tr>
       			<td>${theCount.count}</td>
       			<td>${institution.name}</td>
       			<td>${institution.description}</td>
       			<td><a href="/institution/edit/${institution.id}" class="btn">Edytuj</a></td>
       			<td><a href="/institution/delete/${institution.id}" class="btn">Usuń</a></td>
       		</tr>
       	</c:forEach>
       	<tr>
       		<td></td>
       		<td></td>
       		<td></td>
       		<td><a href="/admin" class="btn">Wstecz</a></td>
       		<td><a href="/institution/add" class="btn">Dodaj</a></td>
       	</tr>
	</table>
</div>

</header>

<%@ include file="footer.jsp" %>

</body>
</html>
