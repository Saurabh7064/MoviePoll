<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><spring:message code="index.title" /></title>
<%@include file="includes/scripts.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="center">
					<br>
					<%@include file="includes/header.jsp"%>

					<h1><spring:message code="index.title" /></h1>
					
					<%@include file="includes/menu.jsp"%>
					<br>
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-condensed text-center">
							<thead>
								<tr class="success">
									<th width="2%"><spring:message code="movie.rank" /></th>
									<th width="2%"><spring:message code="movie.votes" /></th>
									<th width="30$"><spring:message code="movie.title" /></th>
									<th width="7%"><spring:message code="movie.genre" /></th>
									<th width="5%"><spring:message code="movie.year" /></th>
									<th width="15%"><spring:message code="movie.actors" /></th>
									<th width="8%"><spring:message code="movie.imdb" /></th>
									<th width="11%"><spring:message code="movie.addedBy" /></th>
									<th width="10%"><spring:message code="movie.date" /></th>
									<c:if test="${username != null}">
										<th width="8%"><spring:message code="movie.vote" /></th>
									</c:if>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${movieList}" var="movie"	varStatus="loopCounter">
									<tr>
										<td><strong><c:out value="${loopCounter.count}" /></strong></td>
										<td><font color="red"><c:out value="${movie.votes}" /></font></td>
										<td><c:out value="${movie.title}" /></td>
										<td><c:out value="${movie.genre}" /></td>
										<td><c:out value="${movie.year}" /></td>
										<td><c:out value="${movie.actors}" /></td>
										<td><c:if test="${movie.imdb != ''}">
												<a href="${movie.imdb}" target="blank"> <i	class="fa fa-external-link-square"> IMDb</i></a>
											</c:if></td>
										<td><c:out value="${movie.addedBy}" /></td>
										<td><c:out value="${movie.publishedOn}" /></td>
										<c:if test="${username != null}">
											<td><form:form id="vote" action="vote" method="post">
													<button name="movieId" value="${movie.id}"
														class="btn btn-primary"
														<c:out value="${hasVoted == true ? 'disabled' : ''}"/>
														onclick="voteMovie();">
														<i class="fa fa-thumbs-up"></i>
													</button>
											</form:form></td>
										</c:if>
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