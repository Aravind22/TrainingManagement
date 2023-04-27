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
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"></link>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
			<table class="table table-hover">

				<tr>
					<td>EmployeeId</td>
					<td><form:input id="empId" path="empId"
							value="${employee.empId}" /></td>
					<td><form:select path="skillIds" multiple="multiple">
							<form:option value="0" label="Please Select" />
							<c:forEach var="skillVal" items="${skillOptions}">
								<form:option value="${skillVal.skillId}"
									label="${skillVal.skillName}" />
							</c:forEach>
						</form:select></td>
					<td>
						<button type="submit">Apply Filter</button>
						<button type="reset" onclick="location.href='/employee/'">Reset</button>
					</td>
				</tr>
			</table>
		</form:form>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Skills</th>
				<th>Actions</th>
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
					<td><a href="/employee/edit?empId=${employee.empId}"><img
							src="/images/edit.png" alt="Edit"></a><a
						href="/employee/delete/${employee.empId}"><img
							src="/images/delete.png" alt="Delete" style="margin-left: 16px;"></a></td>
					<!-- 		<td><a class="nav-link active" href="/employee/train/${employee.empId}">Train</a></td>  -->
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>