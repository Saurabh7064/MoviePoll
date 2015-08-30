<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="taglibs.jsp"%>

<sec:authorize access="hasAnyRole('Admin', 'User')">

	<a class="btn btn-success" href="<c:url value="/addMovie"/>"> <i
		class="fa fa-plus"></i> <spring:message code="btn.addMovie" />
	</a>

</sec:authorize>
<sec:authorize access="hasRole('Admin')">
	<a class="btn btn-primary" href="<c:url value="/editMovies"/>"> <i
		class="fa fa-pencil"></i> <spring:message code="btn.editMovieList" />
	</a>
	<a class="btn btn-primary" href="<c:url value="/editUsers"/>"> <i
		class="fa fa-user"></i> <spring:message code="btn.editUsers" />
	</a>
</sec:authorize>