<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="taglibs.jsp"%>

<spring:message code="date" var="date" />

<div class="row">
	<div class="col-md-6">
		<c:if test="${username != null }">
			<spring:message code="user.logged" />
			<b><sec:authentication property="name" /></b>
			<i><sec:authentication property="authorities" /></i>
			<br>	
		</c:if>

		<spring:message code="language" />: <a href="./?language=en">English</a> | <a href="./?language=sr">Srpski</a>
	</div>	
	
	<!-- custom tag -->

	<div class="col-md-5 text-right">
		<span id="customDateTag"><date:displayDate prefix="${date}" /></span>
	</div>

	<div class="col-md-1 text-right">
		<c:choose>
			<c:when test="${username != null }">
				<a href="logout" class="btn btn-default"><spring:message code="user.logout" /></a>
			</c:when>
			<c:otherwise>
				<a href="login" class="btn btn-default"><spring:message	code="user.login" /></a>
			</c:otherwise>
		</c:choose>
	</div>
</div>