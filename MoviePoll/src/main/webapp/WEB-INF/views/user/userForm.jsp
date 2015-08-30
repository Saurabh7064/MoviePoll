<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/taglibs.jsp"%>
<html>
<head>
<title><spring:message code="index.title" /></title>
<%@include file="../includes/scripts.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<c:url var="actionUrl" value="/saveUser" />
				<div class="centerForm">

					<c:choose>
						<c:when test="${edit eq null}">
							<h2><spring:message code="pageTitle.addUser" /></h2>
						</c:when>
						<c:otherwise>
							<h2><spring:message code="pageTitle.editUser" /></h2>
						</c:otherwise>
					</c:choose>
					<br>
					<form:form id="userForm" commandName="user" method="post" action="${actionUrl}">

						<div class="form-group">
							<label for="username"><spring:message code="user.username" /></label>
							<form:input path="username" class="form-control" />
							<form:errors path="username" class="error" />
						</div>
						<div class="form-group">
							<label for="password"><spring:message code="user.password" /></label>
							<form:password path="password" maxlength="10"
								class="form-control" />
							<form:errors path="password" class="error" />
						</div>
						<div class="form-group">
							<label for="status"><spring:message code="user.status" /></label>

							<form:radiobutton path="status" value="ACTIVE" />
							<spring:message code="user.active" />
							<form:radiobutton path="status" value="INACTIVE" />
							<spring:message code="user.inactive" />
							<form:errors path="status" class="error" />
						</div>
						<div class="form-group">
							<label for="roles"><spring:message code="roles" /></label>
							<form:select path="roles" multiple="true" class="form-control">
								<form:options items="${roleList}" itemValue="id" itemLabel="roleName" />
							</form:select>
							<form:errors path="roles" class="error" />
						</div>
						<div class="btn-group " role="group">
							<button type="submit" class="btn btn-default">Save</button>
							<button type="reset" class="btn btn-default">Reset</button>
							<a href="<c:url value='/editUsers'/>" class="btn btn-default">Cancel</a>
						</div>
						<form:input path="id" type="hidden" />
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>