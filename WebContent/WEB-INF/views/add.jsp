<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="<spring:url value="/resources/CSS/jquery-ui.min.css"/>" type="text/css" />
<link rel="stylesheet"
	href="<spring:url value="/resources/CSS/jquery-ui.structure.css"/>" type="text/css" />
<link rel="stylesheet"
	href="<spring:url value="/resources/CSS/custom.css"/>" type="text/css" />
<title>Add new postits:</title>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.core.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.validate.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/add.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.ui.js"/>"></script>
	
</head>
<body>
	<jsp:include page="../jspFragments/header.jsp" />

	<div class="container">
	
		<table class="table">
		<tr>
			<th class="col-xs-2">Subject:</th>
			<th class="col-xs-7">Text:</th>
			<th class="col-xs-2">Date:</th>
			<th class="col-xs-1"></th>
		</tr>
		</table>

		<c:url value="/addPost" var="formSubmitUrl" />
		<form:form action="${formSubmitUrl}" method="POST" modelAttribute="postit" class="form-inline" id="addForm">
			<div class="form-group col-xs-2">
				<form:input path="subject" class="form-control" id="subject" name="subject" />
				<form:errors class="help-block" path="subject" />
			</div>
			<div class="form-group col-xs-7">
				<form:textarea path="text" class="form-control col-xs-7" id="text" name="text" rows="5" cols="95" />
				<form:errors class="help-block" path="text" />
			</div>
			<div class="form-group col-xs-2">	
				<form:input path="dateString" class="form-control" id="date" name="date" />
				<form:errors class="help-block" path="dateString" />
			</div>
			<div class="form-group col-xs-1">	
				<input type="submit" class="btn btn-primary" id="submit" value="Add Postit" />
			</div>
		</form:form>
	</div>
	<br>
	<br>





	<div class="container" id="showPostits">
		<table class="table table-hover" id="postitsTable">
			<tr>
				<th class="col-xs-2">Subject:</th>
				<th class="col-xs-7">Text:</th>
				<th class="col-xs-2">Date:</th>
				<th class="col-xs-1"></th>
			</tr>
			<c:forEach items="${postits}" var="postit">
				<tr>
					<td>${postit.subject}</td>
					<td>${postit.text}</td>
					<td>${postit.date}</td>
					<td><button class="btn btn-default done" id="${postit.id}">Done</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br>
	<br>
	<br>
	<jsp:include page="../jspFragments/footer.jsp" />
</body>
</html>