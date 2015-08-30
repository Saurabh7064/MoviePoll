<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/taglibs.jsp"%>
<html>
<head>
<title><spring:message code="index.title" /></title>
<%@include file="../includes/scripts.jsp"%>
<script type="text/javascript" src='<c:url value="/web-resources/js/js-movieForm.js"/>'></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<c:url var="actionUrl" value="/save" />
				<div class="centerForm">

					<c:choose>
						<c:when test="${edit eq null}">
							<h2><spring:message code="pageTitle.addMovie" /></h2>
						</c:when>
						<c:otherwise>
							<h2><spring:message code="pageTitle.editMovie" /></h2>
						</c:otherwise>
					</c:choose>

					<form:form id="movieForm" commandName="movie" method="post"	action="${actionUrl }">

						<div class="form-group">
							<label for="title"> <spring:message code="movie.title" />
							</label>
							<form:input path="title" class="form-control" />
							<form:errors path="title" class="error" />
						</div>
						<div class="form-group">
							<label for="genre"><spring:message code="movie.genre" /></label>
							<form:select path="genre" class="form-control">
								<option selected="selected" disabled="disabled"></option>
								<form:option value="Action">
									<spring:message code="genre.action" />
								</form:option>
								<form:option value="Animated">
									<spring:message code="genre.animated" />
								</form:option>
								<form:option value="Comedy">
									<spring:message code="genre.comedy" />
								</form:option>
								<form:option value="Crime">
									<spring:message code="genre.crime" />
								</form:option>
								<form:option value="Documentary">
									<spring:message code="genre.documentary" />
								</form:option>
								<form:option value="Drama">
									<spring:message code="genre.drama" />
								</form:option>
								<form:option value="Horror">
									<spring:message code="genre.horror" />
								</form:option>
								<form:option value="Musical">
									<spring:message code="genre.musical" />
								</form:option>
								<form:option value="SciFi">
									<spring:message code="genre.scifi" />
								</form:option>
								<form:option value="Thriller">
									<spring:message code="genre.thriller" />
								</form:option>
								<form:option value="War Movie">
									<spring:message code="genre.war" />
								</form:option>
								<form:option value="Western">
									<spring:message code="genre.western" />
								</form:option>
							</form:select>
							<form:errors path="genre" class="error" />
						</div>
						<div class="form-group">
							<label for="year"><spring:message code="movie.year" /></label>
							<form:input path="year" maxlength="4" class="form-control" />
							<form:errors path="year" class="error" />
						</div>
						<div class="form-group">
							<label for="actors"><spring:message code="movie.actors" /></label>
							<form:input path="actors" class="form-control" />
							<form:errors path="actors" class="error" />
						</div>
						<div class="form-group">
							<label for="imdb"><spring:message code="movie.imdb" /></label>
							<form:input path="imdb" class="form-control" />
							<form:errors path="imdb" class="error" />
						</div>
						<div class="form-group">
							<label for="publishedOn"><spring:message
									code="movie.date" /></label>
							<form:input path="publishedOn" class="datepicker form-control" />
						</div>
						<div class="btn-group " role="group">
							<button type="submit" class="btn btn-default">Save</button>
							<button type="reset" class="btn btn-default">Reset</button>
							<a href="<c:url value='/rank'/>" class="btn btn-default">Cancel</a>
						</div>			
						<form:input path="id" type="hidden" />
						<form:input path="votes" type="hidden" />
					</form:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>