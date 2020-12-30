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
       		<th>Status</th>
       		<th>Data odbioru</th>
       		<th>Data doręczenia</th>
  			<th>Szczegóły</th>
  		</tr>
       	<c:forEach items="${donations}" var="donation" varStatus="theCountExt">
       		<tr>
       			<td>${theCountExt.count}</td>
       			<td></td>
       			<td>${donation.pickUpDate}, ${donation.pickUpTime}</td>
       			<td></td>
       			<td><a href="/donation/details/${donation.id}" class="btn">Szczegóły</a></td>
       		</tr>
       	</c:forEach>
	</table>
</div>
    
</header>


<%@ include file="footer.jsp" %>
</body>
</html>
