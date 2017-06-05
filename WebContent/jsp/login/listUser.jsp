<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link
	href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css"
	rel="stylesheet" media="screen">
<link href="${pageContext.request.contextPath}/css/styles4.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="main">
		<div class="row-fluid">
			<div class="span2"></div>
			<div class="span8">
				<!-- block -->
				<div class="block">
					<div class="navbar navbar-inner block-header">
						<div class="muted pull-left">Users</div>
						<div class="pull-right">
							<span class="badge badge-info">10</span>
						</div>
					</div>
					<div class="block-content collapse in">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>Username</th>
									<th>Full name</th>
									<th>Email</th>
									<th>Gender</th>
									<th>Role</th>

								</tr>
							</thead>
							<tbody>
							<%int index = 1;%>
								<c:forEach items="${listUser}" var="item">
									<tr onclick="location.href='initMgntUser.do?userid=<c:out value="${item.userId }"/>'">
										<td><%=index++%></td>
										<td><c:out value="${item.username }" /></td>
										<td><c:out value="${item.fullname }" /></td>
										<td><c:out value="${item.email }" /></td>
										<td><c:out value="${item.gender }" /></td>
										<td><c:out value="${item.role }" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<!-- /block -->
			</div>
			<div class="span2"></div>
		</div>
	</div>
</body>
</html>