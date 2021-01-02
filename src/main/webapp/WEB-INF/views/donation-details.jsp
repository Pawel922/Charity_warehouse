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
	<table>
 		<tr>
  			<th>Fundacja:</th>
  			<td>${donation.institution.name}</td>
   		</tr>
    	<tr>
     	  	<th>Dary:</th>
     	  	<td>
     	  		<c:forEach items="${donation.categories}" var="category" varStatus="counter">
     	  			${category.name}<c:if test="${not counter.last}">, <br></c:if>
     	  		</c:forEach>
     	  	</td>
     	</tr>
    	<tr>
      	 	<th>Data odbioru:</th>
     	 	<td>${donation.pickUpDate}, ${donation.pickUpTime}</td>
      	 </tr>
      	 <tr>
       		<th>Data doręczenia:</th>
       		<c:choose>
       			<c:when test="${not empty donation.receiveDate}">
       				<td>
       					${donation.receiveDate}<span>&nbsp</span>
       					<button data-name='typeBtn' class="btn">Zmień</button>
       				</td>
       			</c:when>
       			<c:otherwise>
       				<td><button data-name='typeBtn' class="btn">Wprowadź</button></td>
       			</c:otherwise>
       		</c:choose>
       	</tr>
       	<tr>
       		<td><a href="/user/donations" class="btn">Wstecz</a></td>
       		<td></td>
    	</tr>
	</table>
</div>
</header>

<div id="myModal" class="modal">
 	<!-- Modal content -->
	<div class="modal-content">
  		<div class="modal-header">
  			<h2>Wprowadź datę doręczenia</h2>
    		<span class="close">&times;</span>
  		</div>
  		<div class="modal-body">
    		<form class="formTable" method="post">
    			<table>
    				<tr>
    					<td>Data doręczenia</td>
    					<td><input data-name="recDate" name="recDate" type="date"></td>
    				</tr>
    				<tr>
    					<td><input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></td>
    					<td><input type="submit" value="Wprowadź"></td>
    				</tr>
    			</table>
    		</form>
    		<div>
    			<p class="warning"></p>
    		</div>
  		</div>
  	</div>
</div>

<script src="/resources/js/app-donation-modal.js"></script>

<%@ include file="footer.jsp" %>

</body>
</html>
