<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="userActionUrl" value="${pageContext.request.contextPath}/editUserAdmin.do" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/styles3.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/styles4.css" rel="stylesheet">
<title>Login System</title>
<script type="text/javascript">
	function onLoad(){
		//document.getElementById("username").placeholder = "Enter username";
		//document.getElementById("password").placeholder = "Enter password";
	}
</script>
<title>Register User Admin</title>
</head>
<body>

	<div class="text-center" style="padding:50px 0" align="center">
		<div class="logo">Edit User</div>
		<!-- Main Form -->
		<div class="login-form-1" align="left">
			<form:form id="login-form" method="post" modelAttribute="mghtUser" action="${userActionUrl}" class="text-left">
				<div class="login-form-main-message"></div>
				<div class="main-login-form">
					<div class="login-group">
						<form:hidden path="userid"/>
						<div class="form-group">
							<label for="username" class="sr-only">Username</label> 
							<form:input id="username" name="username" path="username" readonly="true" cssClass="form-control" />
						</div>
						
						<div class="form-group">
							<label for="email" class="sr-only">Email</label>
							<form:input id="email" path="email" cssClass="form-control"  />
						</div>
						<div class="form-group">
							<label for="fullname" class="sr-only">Full Name</label>
							<form:input id="fullname" path="fullname" cssClass="form-control" />
						</div>
						
						<div class="form-group login-group-checkbox">
							<form:radiobutton path="gender" id="male" value="male" />
							<label for="male">male</label>
							
							<form:radiobutton path="gender" id="female" value="female" />							
							<label for="female">female</label>
						</div>
						<div class="form-group login-group-checkbox">
							<form:radiobutton path="role" id="admin" value="1" />
							<label for="admin">admin</label>
							
							<form:radiobutton path="role" id="user" value="2" />							
							<label for="user">user</label>
						</div>						
					</div>
					<button type="submit" class="login-button">edit</button>		
								
				</div>					
			</form:form>
			
		</div>
		<!-- end:Main Form -->
	</div>

</body>
</html>