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
	
	<div class="container">
	<h1 align="center">Welcome to Training management</h1>
	<h4 align="right"><a href="<c:url value="/training/add" />">Create Training</a> </h4>
	<table class="table table-bordered table-striped table-hover">
		<tr>
		<th>Id</th>
		<th>TrainerId</th>
		<th>Skill</th>
		<th>Start Date</th>
		<th>End Date</th>
		<th>Status</th>
		<th>Edit</th>
		<th>Delete</th>
		</tr>
		<c:forEach var="training" items="${trainingList}">
			<tr><td>${training.trainingID}</td>
			<td>${training.employee}</td> 
			<td>${training.skill.skillName}</td>
			<td>${training.startDate}</td>
			<td>${training.endDate}</td>
			<td>${training.status}</td>
			<td><a class="nav-link active" href="/training/edit?trainingId=${training.trainingID}">Edit</a></td>
			<td><a class="nav-link active" href="/training/delete/${training.trainingID}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>