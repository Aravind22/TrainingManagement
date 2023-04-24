<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
 <head>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link  href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"></link>
	<title>Training management</title>
 </head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
	<h1 align="center">Welcome to Training management</h1>
	<h4 align="right"><a href="<c:url value="/skill/add" />">Add Skill</a> </h4>
	<table class="table table-bordered table-striped table-hover">
		<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Category</th>
		<th>Edit</th>
		<th>Delete</th>
		</tr>
		<c:forEach var="skill" items="${skillList}">
			<tr><td>${skill.skillId}</td> 
			<td>${skill.skillName}</td>
			<td>${skill.category}</td>
			<td><a class="nav-link active" href="/skill/edit?skillId=${skill.skillId}">Edit</a></td>
			<td><a class="nav-link active" href="/skill/delete/${skill.skillId}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>