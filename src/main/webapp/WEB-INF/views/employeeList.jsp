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
<script src = "/js/jquery-2.1.1.js"></script>
<script src = "/js/bootstrap.min.js"></script>
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

		<input class="form-control" id="filter" type="text"
			placeholder="Seach here..."> <br>
		<table class="table table-bordered table-hover">
		<thead class="table-dark">
			<tr scope="col">
				<th>Id</th>
				<th>Name</th>
				<th>Skills</th>
				<th>Actions</th>
				<!-- <th>Actions</th>  -->
			</tr>
			<br />
		</thead>
			<c:forEach var="employee" items="${employeeList}">
			<tbody id="test">
				<tr scope="row">
					<td>${employee.empId}</td>
					<td>${employee.empName}</td>
					<td>
						<c:forEach var="skillList" items="${employee.skillList}">
							${skillList.skillName} <br />
						</c:forEach></td>
					<td>
						<a href="/employee/edit?empId=${employee.empId}">
							<img src="/images/edit.png" alt="Edit">
						</a>
						<a href="/employee/delete/${employee.empId}">
							<img src="/images/delete.png" alt="Delete" style="margin-left: 16px;">
						</a>
					</td>
				</tr>
			</tbody>
			</c:forEach>
		</table>
				<nav aria-label="...">
		  <ul class="pagination" style="justify-content: end;">
		    <li class="page-item disabled">
		      <a class="page-link" href="#" tabindex="-1">Previous</a>
		    </li>
		    <li class="page-item"><a class="page-link" href="#">1</a></li>
		    <li class="page-item active">
		      <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
		    </li>
		    <li class="page-item"><a class="page-link" href="#">3</a></li>
		    <li class="page-item">
		      <a class="page-link" href="#">Next</a>
		    </li>
		  </ul>
		</nav>
	</div>
	<script>
		$(document).ready(function() {
			$("#filter").on("keyup",
			function() {
				var value = $(this).val().toLowerCase();
				console.log(value);
				$("#test tr").filter(
					function() {
						$(this).toggle($(this)
								.text()
							    .toLowerCase()
								.indexOf(value) > -1)
					});
			});
		});
	</script>
</body>
</html>