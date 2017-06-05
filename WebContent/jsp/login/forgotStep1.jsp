<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String contextPath = request.getContextPath();
%>
<c:set var="contextPath" value="<%=contextPath%>"></c:set>
<c:set var="userActionUrl" value="${pageContext.request.contextPath}/forgetStep1.do" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/styles3.css" rel="stylesheet">
<title>Login System</title>
<script type="text/javascript">
	function onLoad(){
		document.getElementById("username").placeholder = "Enter username";
	}
</script>
</head>
<body onload="onLoad();">
<center>
	<div class="text-center" style="padding:50px 0">
		<div class="logo">Forgot Password</div>
		<!-- Main Form -->
		<div class="login-form-1">
			<form:form id="login-form" cssClass="text-left" method="post" modelAttribute="userForm" action="${userActionUrl}" >
				<div class="login-form-main-message"></div>
				<div class="main-login-form">
					<div class="login-group">
						<div class="form-group">
							<label for="lg_username" class="sr-only">Username</label>
							<form:input id="username" path="userName" cssClass="form-control" name="lg_username" placeholder="username" />
						</div>						
					</div>
					<button type="submit" class="login-button">send</button>
				</div>				
			</form:form>
		</div>
		<!-- end:Main Form -->
	</div>
	<!-- <div class="login-page">
		<div class="form">			
			<form:form cssClass="login-form" method="post" modelAttribute="userForm" action="${userActionUrl}">				
					<form:input id="username" path="userName" />
					<form:password id="password" path="password" />
					<input type="submit" style="background:#2cff2c;" value="Login"/>				
			</form:form>
		</div>
	</div> -->
</center>
</body>
</html>