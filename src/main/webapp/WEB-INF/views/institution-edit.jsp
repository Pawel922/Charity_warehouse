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
	<form:form modelAttribute="institution" method="post" cssClass="formTable">
    	<table>
    		<tr>
    			<th>Nazwa</th>
    			<td><form:input path="name"/></td>
    		</tr>
    		<tr>
   				<td></td>
   				<td><form:errors path="name" cssClass="warning"/></td>
   			</tr>
   			<tr>
    			<th>Opis</th>
    			<td><form:input path="description"/><td>
    		</tr>
    		<tr>
    			<td></td>
    			<td><form:errors path="description" cssClass="warning"/></td>
    		</tr>
    		<tr>
   				<td><a href="/institution/all" class="btn">Wstecz</a></td>
   				<td><button type="submit" class="btn">Zapisz</button></td>
   			</tr>
   		</table>
	</form:form>
</div>

</header>

<%@ include file="footer.jsp" %>

</body>
</html>
