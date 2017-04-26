<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String contextPath = request.getContextPath();
%>
<c:set var="contextPath" value="<%=contextPath%>"></c:set>
<c:set var="userActionUrl" value="${pageContext.request.contextPath}/loginSubmit.do" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/styles2.css" rel="stylesheet">
<title>Login System</title>
<script type="text/javascript">
	function onLoad(){
		document.getElementById("username").placeholder = "Enter username";
		document.getElementById("password").placeholder = "Enter password";
	}
</script>
</head>
<body onload="onLoad();">
	<div class="login-page">
		<div class="form">			
			<form:form cssClass="login-form" method="post" modelAttribute="userForm" action="${userActionUrl}">				
					<form:input id="username" path="userName" />
					<form:password id="password" path="password" />
					<input type="submit" />				
			</form:form>
		</div>
	</div>
</body>
</html>