<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"></link>


<link rel="stylesheet" href="/css/choices.min.css" />

<script type="text/javascript" src="/js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="/js/choices.min.js"></script>


<link href="<c:url value='/css/style.css'/>" rel="stylesheet"></link>


<script>
$(document).ready(function(){
    
    var multipleCancelButton = new Choices('#skillIds', {
       removeItemButton: true,
       maxItemCount:15,
       searchResultLimit:15,
       renderChoiceLimit:15
     }); 
    
    
});
</script>

<title>Add Employee</title>

</head>
<body>
	<jsp:include page="header.jsp" />
	<form:form action="addEmployee" modelAttribute="employee">
		<c:set var="pageTitle" value="Add Employee" />
		<c:if test="${employee.empId != 0 && not empty employee.empId}">
			<c:set var="pageTitle" value="Update Employee" />
		</c:if>
		<h2 align="center">${pageTitle}</h2>
		<h3>${message}</h3>
		<table>
			<tr>
				<td>EmployeeId</td>
				<td><form:input id="empId" path="empId"
						value="${employee.empId}"
						disabled="${employee.empId ne 0 && not empty employee.empId}" /></td>
				<td><form:errors path="empId" cssClass="error" /></td>
			</tr>
			<tr hidden>
				<td>EmployeeId</td>
				<td><form:input id="empId" path="empId"
						value="${employee.empId}" disabled="${employee.empId eq 0}" /></td>
				<td><form:errors path="empId" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Employee Name</td>
				<td><form:input id="empName" path="empName"
						value="${employee.empName}" /></td>
				<td><form:errors path="empName" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Skills</td>
				<td><form:select path="skillIds" multiple="multiple">
						<c:forEach var="skillVal" items="${skillOptions}">
							<form:option value="${skillVal.skillId}"
								label="${skillVal.skillName}" selected="${skillVal.selected}" />
						</c:forEach>
					</form:select></td>
			</tr>
			
			<tr hidden>
				<td>Disabled</td>
				<td><form:input id="disabled" path="disabled"
						value="${employee.disabled}" /></td>
				<td><form:errors path="disabled" cssClass="error" /></td>
			</tr>
			<tr>
				<c:if test="${employee.empId != 0 && not empty employee.empId}">
					<td><input id="updateBtn" type="submit"
						value="Update Employee" /></td>
				</c:if>
				<c:if test="${employee.empId == 0 || empty employee.empId}">
					<td><input class="customButton" type="submit" value="Add Employee" /></td>
				</c:if>
			</tr>
		</table>
	</form:form>
</body>
</html>