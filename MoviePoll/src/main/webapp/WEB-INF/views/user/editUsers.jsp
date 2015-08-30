<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../includes/taglibs.jsp"%>

<html>
<head>
<title><spring:message code="user.list" /></title>
<%@include file="../includes/scripts.jsp"%>
</head>
<body>
	<div class="container-fluid">
	<!-- display active users using angular -->
	
		<div class="row">		
			<div class="col-md-12">
				<div class="center">
					<br>
					<%@include file="../includes/header.jsp"%>

					<h1><spring:message code="user.list" /></h1>
					
						<div class="row" data-ng-app="myApp">
						
							<c:if test="${username != null }">
							
							<div class="pull-left" data-ng-controller="myController">
        						<a class="btn btn-success" href="<c:url value="/addUser"/>"> <i
								class="fa fa-plus"></i> <spring:message code="btn.addUser" /></a> 
								<a class="btn btn-primary" href="<c:url value="/rank"/>"> <i
								class="fa fa-list-ol"></i> <spring:message code="btn.ranking" /></a>
            					<button type="button" class="btn btn-default" onclick="openModal();" data-ng-click="getUserDataFromServer()"><spring:message code="btn.loggedInUsers" /></button>
			             		<div id="myModal" class="modal fade">
			    					<div class="modal-dialog">
			        					<div class="modal-content">
			            					<div class="modal-header">
			                					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			                					<h4 class="modal-title"><spring:message code="btn.loggedInUsers" /></h4>
			            					</div>
			            					<div class="modal-body">
			                					<ol >
			    									<li data-ng-repeat="user in users">{{ user.username }}</li>
												</ol>
			            					</div>
			            					<div class="modal-footer">
			                					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			               					</div>
			        					</div>
			   					 	</div>
								</div>
							</div>								
						</c:if>							
					</div>
					<div class="table-responsive row">
						<table class="table table-striped table-bordered table-condensed text-center">
							<thead>
								<tr class="success">
									<th width="30%"><spring:message code="user.username" /></th>
									<th width="15%"><spring:message code="user.roles" /></th>
									<th width="10%"><spring:message code="user.status" /></th>
									<th width="10%"><spring:message code="user.hasVoted" /></th>
									<th width="30%"><spring:message code="user.manage" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${userList}" var="user" varStatus="loopCounter">
									<tr>
										<td><c:out value="${user.username}" /></td>
										<td><c:forEach items="${user.roles}" var="role">
												<c:out value="${role.roleName} " />
											</c:forEach>
										<td><c:out value="${user.status}" /></td>
										<td><c:out value="${user.hasVoted}" /></td>
										<td><nobr>
												<a class="btn btn-primary"	href="<c:url value="/getUser/${user.id}"/>"> 
													<i class="fa fa-pencil"></i> <spring:message code="user.edit" />
												</a> <a class="btn btn-danger" onclick="return confirm('OK to delete ${user.username} ?');"
													href="deleteUser/${user.id}"> <i class="fa fa-times"></i>
													<spring:message code="user.delete" />
												</a> <a class="btn btn-default" href="resetVote/${user.id}">
													<i class="fa fa-reset"></i> <spring:message	code="user.resetVote" />
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