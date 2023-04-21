<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="mvc" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link  href="<c:url value='/css/bootstrap.min.css'/>" rel="stylesheet"></link>
<title>Add Employee</title>

</head>
<body>
<form:form action="addEmployee" modelAttribute="employee">
<h2 align="center">Add Employee</h2>
<table>
<tr>
<td>EmployeeId</td>
<td><form:input id="empId" path="empId" value="${employee.empId}" /></td>
<td><form:errors path="empId" cssClass="error" /></td>
</tr>
<tr>
<td>Employee Name</td>
<td><form:input id="empName" path="empName" value="${employee.empName}" /></td>
<td><form:errors path="empName" cssClass="error" /></td>
</tr>
<tr>
<td><input type="submit" value="Add Employee" /></td>
</tr>
</table>
</form:form>
</body>
</html>