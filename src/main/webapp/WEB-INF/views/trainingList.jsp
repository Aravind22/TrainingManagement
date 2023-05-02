<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"></link>
<script src="/js/jquery-2.1.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
<link href="<c:url value='/css/style.css'/>" rel="stylesheet"></link>

<script src="/js/jquery-2.1.1.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/trainingList.js"></script>
<script>

</script>
<title>Training management</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<h1 align="center">Welcome to Training management</h1>

		<mvc:form action="search" modelAttribute="filterObj">
			<table>
				<tr>
				<tr>
					<td>

<!-- 
						<div class="btn-group" data-toggle="buttons">
							<label id="startDate"
								class="btn btn-secondary ${filterObj.filterBy == 'startDate' ? 'active' : ''}">
								<mvc:radiobutton path="filterBy" value="startDate" /> Start
								Date
							</label> <label id="endDate"
								class="btn btn-secondary ${filterObj.filterBy == 'endDate' ? 'active' : ''}">
								<mvc:radiobutton path="filterBy" value="endDate" /> End Date
							</label>
						</div>
						-->

					</td>
					<td>Filter By</td>
					<td><mvc:select path="filterBy">
							<mvc:option value="startDate" label="Start Date" />
							<mvc:option value="endDate" label="End Date" />
						</mvc:select></td>

					</div>
					<td>From</td>
					<td><mvc:input type="date" path="startDate" /></td>
					</td>
					<td>
					<td>To</td>
					<td><mvc:input type="date" path="endDate" /></td>
					</td>
					<td>
						<button type="submit">Apply Filter</button>
						<button type="reset" onclick="location.href='/training/'">Reset</button>
					</td>
				</tr>
			</table>
		</mvc:form>

		<h4 align="right">
			<a href="<c:url value="/training/add" />">Create Training</a>
		</h4>
		<table class="table table-bordered table-striped table-hover">
			<tr>
				<th>Id</th>
				<th>TrainerId - Name</th>
				<th>Skill</th>
				<th>Start Date</th>
				<th>End Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="training" items="${trainingList}">
				<tr>
					<td>${training.trainingID}</td>
					<td>${training.employee.empId}-${training.employee.empName}</td>
					<td>${training.skill.skillName}</td>

					<td>${training.startDate}</td>
					<td>${training.endDate}</td>
				
					<td>
						<a href="/training/edit?trainingId=${training.trainingID}">
							<img src="/images/edit.png" alt="Edit">
						</a>
						<a href="/training/delete/${training.trainingID}">
							<img src="/images/delete.png" alt="Delete" style="margin-left: 16px;">
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>