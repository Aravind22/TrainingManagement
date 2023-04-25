<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"></link>
<title>Training management</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<h1 align="center">Welcome to Training management</h1>
		<h4 align="right">
			<a href="<c:url value="/employee/add" />">Add Employee</a>
		</h4>
		<h2>${employeeAdded}</h2>
		<form:form action="search" modelAttribute="filterObj">
			<table>
				<tr><tr><tr>EmployeeId</tr>
					<td><form:input id="empId" path="empId"
							value="${employee.empId}" /></td></tr>
					<td><form:select path="skillIds" multiple="multiple">
							<form:option value="0" label="Please Select" />
							<c:forEach var="skillVal" items="${skillOptions}">
								<form:option value="${skillVal.skillId}"
									label="${skillVal.skillName}" />
							</c:forEach>
						</form:select></td>
					<td>
						<button type="submit" class="btn btn-default">Submit</button>
					</td>
				</tr>
			</table>
		</form:form>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Skills</th>
				<th>Edit</th>
				<th>Delete</th>
				<!-- <th>Actions</th>  -->
			</tr>
			${employee.skillList}
			<br />
			<c:forEach var="employee" items="${employeeList}">
				<tr>
					<td>${employee.empId}</td>
					<td>${employee.empName}</td>
					<td><c:forEach var="skillList" items="${employee.skillList}">
					${skillList.skillName} <br />
						</c:forEach></td>
					<td><a class="nav-link active"
						href="/employee/edit?empId=${employee.empId}">Edit</a></td>
					<td><a class="nav-link active"
						href="/employee/delete/${employee.empId}">Delete</a></td>
					<!-- 		<td><a class="nav-link active" href="/employee/train/${employee.empId}">Train</a></td>  -->
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>