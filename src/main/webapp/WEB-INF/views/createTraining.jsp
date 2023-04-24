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
<title>Create Training</title>
</head>
<body>
	<div class="container">
	<h2 align="center">Add Training</h2>
		<mvc:form cssClass="form-horizontal" modelAttribute="training"
			action="add" method="post">
			<table class="table table-striped">
				<tr>
					<td>
					<td>Skills</td>
					<td>
							<mvc:select path="skill">
							<mvc:option value="-" label="Please Select"/>
								<c:forEach var="skillVal" items="${skillOptions}">
									<mvc:option value="${skillVal.skillName}"
										label="${skillVal.skillName}" />
								</c:forEach>
							</mvc:select>
					</td>

				</tr>
				<tr>
					<td>
					<td>EmpLoyee Id</td>
					<td><mvc:input path="empId" /></td>
					</td>
				</tr>
				<tr>
					<td>
					<td>Start Date</td>
					<td><mvc:input type="date" path="startDate" /></td>
					</td>
				</tr>
				<tr>
					<td>
					<td>End date</td>
					<td><mvc:input type="date" path="endDate" /></td>
					</td>
				</tr>
				<tr>
					<td>
					<td>Status</td>
					<td><mvc:input path="status" /></td>
					</td>
				</tr>
				<tr hidden>
					<td>
					<td>Id</td>
					<td><mvc:input type="hidden" path="trainingID" /></td>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<button type="submit" class="btn btn-default">Submit</button>
						<button type="reset" class="btn btn-default">Clear</button>
					</td>
					<td></td>
				</tr>
			</table>
		</mvc:form>
	</div>
</body>
</html>