<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"></link>
<title>Failure</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<h2>${msg}</h2>
	<h3>Error in creating Training</h3>
</body>
</html>