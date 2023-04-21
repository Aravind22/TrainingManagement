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
	<h4 align="right"><a href="<c:url value="/employee/add" />">Add Employee</a> </h4>
	<h2>${employeeAdded}</h2>
	<table class="table table-bordered table-striped table-hover">
		<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Edit</th>
		<th>Delete</th>
		<th>Actions</th>
		</tr>
		<c:forEach var="employee" items="${employeeList}">
			<tr><td>${employee.empId}</td> 
			<td>${employee.empName}</td>
			<td><a class="nav-link active" href="/employee/edit?empId=${employee.empId}">Edit</a></td>
			<td><a class="nav-link active" href="/employee/delete/${employee.empId}">Delete</a></td>
			<td><a class="nav-link active" href="/employee/train/${employee.empId}">Train</a></td>
			</tr>
		</c:forEach>
	</table>
	</div>
</body>
</html>