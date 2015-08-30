<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/taglibs.jsp"%>
<html>
<head>
<title><spring:message code="movieEditPage.title" /></title>
<%@include file="../includes/scripts.jsp"%>
<script type="text/javascript" src='<c:url value="/web-resources/js/js-movieForm.js"/>'></script>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="center">
					<br>

					<%@include file="../includes/header.jsp"%>
					<h1><spring:message code="movieEditPage.title" /></h1>

					<a class="btn btn-success" href="<c:url value="/addMovie"/>"> <i
						class="fa fa-plus"></i> <spring:message code="btn.addMovie" />
					</a> <a class="btn btn-primary" href="<c:url value="/rank"/>"> <i
						class="fa fa-list"> </i> <spring:message code="btn.ranking" />
					</a> <br>
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-condensed text-center">
							<thead>
								<tr class="success">
									<th width="30%"><spring:message code="movie.title" /></th>
									<th width="7%"><spring:message code="movie.genre" /></th>
									<th width="5%"><spring:message code="movie.year" /></th>
									<th width="15%"><spring:message code="movie.actors" /></th>
									<th width="8%"><spring:message code="movie.imdb" /></th>
									<th width="11%"><spring:message code="movie.addedBy" /></th>
									<th width="10%"><spring:message code="movie.date" /></th>
									<th width="10%"><spring:message code="movie.manage" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${movieList}" var="movie"	varStatus="loopCounter">
									<tr>
										<td><c:out value="${movie.title}" /></td>
										<td><c:out value="${movie.genre}" /></td>
										<td><c:out value="${movie.year}" /></td>
										<td><c:out value="${movie.actors}" /></td>
										<td><c:if test="${movie.imdb != '' }">
												<a href="${movie.imdb}" target="blank"> <i	class="fa fa-external-link-square"> IMDb</i></a>
											</c:if></td>
										<td><c:out value="${movie.addedBy}" /></td>
										<td><c:out value="${movie.publishedOn}" /></td>
										<td><nobr>
												<a class="btn btn-primary"
													href="<c:url value="/getMovie/${movie.id}"/>"> <i
													class="fa fa-pencil"></i> <spring:message code="btn.movieEdit" />
												</a> <a class="btn btn-danger" onclick="return confirm('OK to delete ${movie.title} ?');"
													href="deleteMovie/${movie.id}"> <i class="fa fa-times"></i>
													<spring:message code="btn.movieDelete" />
												</a>
										</nobr></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<br>
				</div>
			</div>
		</div>
	</div>
</body>
</html>