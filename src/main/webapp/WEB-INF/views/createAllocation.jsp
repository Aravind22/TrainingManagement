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
<title>Allocation</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="container">
		<h2 align="center">Add a new Allocation</h2>
		<mvc:form cssClass="form-horizontal" modelAttribute="allocation"
			action="createAllocation" method="post">
			<table class="table table-striped">
				<tr hidden>
					<td>
					<td>Allocation ID</td>
					<td><mvc:input path="allocationId" /></td>
					</td>
				</tr>
				<tr>
					<td>
					<td>Employee ID</td>
					<td><mvc:input path="empId" /></td>
					</td>
				</tr>
				<tr>
					<td>
					<td>Training Id</td>
					<td><mvc:select path="trainingId">
							<mvc:option value="0" label="Please Select" />
							<c:forEach var="trainingVal" items="${trainingOptions}">
								<mvc:option value="${trainingVal.trainingID}"
									label="${trainingVal.trainingName}" />
							</c:forEach>
						</mvc:select></td>
					</td>
				</tr>
				
				<tr>
					<td>
					<td>Score</td>
					<td><mvc:input path="score" /></td>
					</td>
				</tr>
				<tr>
					<td>
					<td>Status</td>
					<td><mvc:input path="status" /></td>
					</td>
				</tr>
				<tr>
					<td>
					<td>Remarks</td>
					<td><mvc:input path="remarks" /></td>
					</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<button class="customButton" type="submit" style="align-items: center;">Submit</button>
						<button class="customButton" type="reset">Clear</button>
					</td>
					<td></td>
				</tr>
			</table>
		</mvc:form>
	</div>
</body>
</html>