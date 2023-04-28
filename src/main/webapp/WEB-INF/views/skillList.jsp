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
<title>Training management</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<h1 align="center">Welcome to Training management</h1>
		<h4 align="right">
			<a href="<c:url value="/skill/add" />">Add Skill</a>
		</h4>
		<mvc:form cssClass="form-horizontal" modelAttribute="skillSearch"
			action="search" method="post">
			<mvc:input path="skillName"  placeholder="skillname"/>
			<mvc:input path="skillCategory"  placeholder="skillcategory"/>
			<button type="submit" style="align-items: center;">Submit</button>
		</mvc:form>
		<br />
		<table class="table table-bordered table-hover">
			<thead class="table-dark">
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Actions</th>
				</tr>
			</thead>
			<c:forEach var="skill" items="${skillList}">
				<tbody id="test">
					<tr scope="row">
						<td>${skill.skillId}</td>
						<td>${skill.skillName}</td>
						<td>${skill.category}</td>
						<td><a href="/skill/edit?skillId=${skill.skillId}"> <img
								src="/images/edit.png" alt="Edit">
						</a> <a href="/skill/delete/${skill.skillId}"> <img
								src="/images/delete.png" alt="Delete" style="margin-left: 16px;">
						</a></td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
		<nav aria-label="...">
			<ul class="pagination" style="justify-content: end;">
				<li class="page-item disabled"><a class="page-link" href="#"
					tabindex="-1">Previous</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item active"><a class="page-link" href="#">2
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">Next</a></li>
			</ul>
		</nav>
	</div>
</body>
</html>