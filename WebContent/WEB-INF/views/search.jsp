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
	href="<spring:url value="/resources/CSS/jquery-ui.min.css"/>"
	type="text/css" />
<link rel="stylesheet"
	href="<spring:url value="/resources/CSS/jquery-ui.structure.css"/>"
	type="text/css" />
<link rel="stylesheet"
	href="<spring:url value="/resources/CSS/custom.css"/>" type="text/css" />
<title>Add new postits:</title>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.core.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.validate.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/search.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.ui.js"/>"></script>

</head>
<body>
	<jsp:include page="../jspFragments/header.jsp" />

	<div class="container">

		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<form action="searchPostits" method="POST" id="searchForm">

					<div class="form-group">
						<label for="date">Date:</label> <input class="form-control"
							id="date" name="date" />
					</div>

					<div class="radio">
						<label> <input type="radio" name="searchType"
							id="byDay" value="byDay" checked> Search by day
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" name="searchType"
							id="ByWeek" value="byWeek"> Search by week
						</label><br>
					</div>
					<div class="radio">
						<label> <input type="radio" name="searchType"
							id="byMonth" value="byMonth"> Search by month
						</label>
					</div>
					
					<div class="form-group">
						<center><button class="btn btn-default" id="submitForm">Search</button></center>
					</div>

				</form>
			</div>
		</div>
	</div>
	<br>
	<br>





	<div class="container" id="showPostits">
		<h1>Search Results:</h1>
		<table class="table table-hover" id="postitsTable">
			<tr>
				<th class="col-xs-2">Subject:</th>
				<th class="col-xs-7">Text:</th>
				<th class="col-xs-2">Date:</th>
				<th class="col-xs-1"></th>
			</tr>
			<c:forEach items="${postits}" var="postit">
				<c:if test="${postit.active}">
					<tr>
						<td>${postit.subject}</td>
						<td>${postit.text}</td>
						<td>${postit.date}</td>
						<td></td>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</div>
	<br>
	<br>
	<br>
	<jsp:include page="../jspFragments/footer.jsp" />
</body>
</html>