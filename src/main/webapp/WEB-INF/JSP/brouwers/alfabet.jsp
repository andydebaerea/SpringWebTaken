<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<title>Brouwers op alfabet</title>
<link
	rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/default.css">
</head>
<body>
	<a href="<c:url value='/'/>">Menu</a>
	<h1>Brouwers op alfabet</h1>
	<c:forEach var="letter" items="${alfabet}">
	<c:url value="/brouwers/alfabet" var="letterURL">
	<c:param name="letter" value="${letter}"></c:param></c:url>
<a href="${letterURL}">${letter}</a>
</c:forEach>
<c:if test="${not empty brouwers}">
	<ul>
	<c:forEach items="${brouwers}" var="brouwer">
	<li>${brouwer.naam} (${brouwer.adres.gemeente})
	</c:forEach>
	</ul>
	</c:if>
</body>
</html>