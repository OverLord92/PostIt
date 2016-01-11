<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<spring:url value="/resources/CSS/custom.css"/>" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.core.js"/>"></script>
<script type="text/javascript"
	src="<spring:url value="/resources/js/jquery.ui.js"/>"></script>

<script>
	$(document).ready(function() {
		$('#picker').datepicker({ dateFormat: 'dd-mm-yy' });
	});
</script>
</head>
<body>

	<jsp:include page="../jspFragments/header.jsp" />

	<div class="container">
		<div class="jumbotron">
			<center><h1>Postit aplikacija</h1>
		</div>
		
		<center>
		<img src="resources/pictures/spring.jpg" />
		<img src="resources/pictures/tomcat.jpg" />
		<img src="resources/pictures/hibernate.png" />
		</center>
		
	</div>
	<br><br><br>
	<jsp:include page="../jspFragments/footer.jsp" />
</body>
</html>