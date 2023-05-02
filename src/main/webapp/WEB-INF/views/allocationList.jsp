<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"></link>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"></link>
<script src="/js/jquery-2.1.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
<title>Allocation</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<h1 align="center">Welcome to Training management</h1>



		<h4 align="right">
			<a href="<c:url value="/allocation/add" />">Create Allocation</a>
		</h4>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Employee Name</th>
				<th>Training Id</th>
				<th>Skill Name</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Score</th>
				<th>Status</th>
				<th>Remarks</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="allocation" items="${allocationList}">
				<td>${allocation.employee.empName}</td>
				<td>${allocation.training.trainingID}</td>
				<td>${allocation.training.skill.skillName}</td>
				<td>${allocation.training.startDate}</td>
				<td>${allocation.training.endDate}</td>
				<td>${allocation.score}</td>
				<td>${allocation.status}</td>
				<td>${allocation.remarks}</td>
				<td><a
					href="/allocation/edit?allocationId=${allocation.allocationId}">
						<img src="/images/edit.png" alt="Edit" />
				</a> <a href="/allocation/delete/${allocation.allocationId}"> <img
						src="/images/delete.png" alt="Delete" />
				</a> <a
					href="/allocation/allocate/${allocation.allocationId}/${allocation.employee.empId}/${allocation.training.skill.skillId}">
						<img src="/images/complete.png" alt="Complete"
						class="complete-img" />
				</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>