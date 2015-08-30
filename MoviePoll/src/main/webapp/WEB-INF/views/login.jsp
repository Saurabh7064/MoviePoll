<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="includes/taglibs.jsp"%>
<html>
<head>
<title><spring:message code="login.title" /></title>
<link rel="stylesheet" href='<c:url value="/web-resources/css/bootstrap.min.css"/>'>
</head>
<body>
	<div style="margin: 10% auto; width: 300px;">
		<h2><spring:message code="login" /></h2>
		<form action="j_spring_security_check" method="POST">

			<div class="form-group">
				<label><spring:message code="login.username" /></label>
				<input type="text" name="j_username" class="form-control" />
			</div>
			<div class="form-group">
				<label><spring:message code="login.password" /></label> 
				<input type="password" name="j_password" class="form-control" />
			</div>
			<button type="submit" value="login" class="btn btn-primary">
				<spring:message code="btn.login" />
			</button>
		</form>
		<br> <font color="red"> <span>${ sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message }</span>
		</font>
	</div>
</body>
</html>