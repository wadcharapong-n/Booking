<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="userActionUrl" value="${pageContext.request.contextPath}/createUserAdmin.do" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/styles3.css" rel="stylesheet">
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
		<div class="logo">register</div>
		<!-- Main Form -->
		<div class="login-form-1" align="left">
			<form:form id="login-form" method="post" modelAttribute="createForm" action="${userActionUrl}" class="text-left">
				<div class="login-form-main-message"></div>
				<div class="main-login-form">
					<div class="login-group">
						<div class="form-group">
							<label for="username" class="sr-only">Username</label> 
							<form:input id="username" name="username" path="username" cssClass="form-control" />
						</div>
						<div class="form-group">
							<label for="password" class="sr-only">Password</label>
							<form:password id="password" path="password" cssClass="form-control"  />
						</div>
						<div class="form-group">
							<label for="reg_password_confirm" class="sr-only">Password Confirm</label>
							<input type="password" class="form-control" id="reg_password_confirm" name="reg_password_confirm" >
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
							<input type="checkbox" class="" id="reg_agree" name="reg_agree">
							<label for="reg_agree">i agree with <a href="#">terms</a></label>
						</div>
					</div>
					<button type="submit" class="login-button">Submit</i></button>
				</div>
				<div class="etc-login-form">
					<p>already have an account? <a href="${pageContext.request.contextPath}/init.do">login here</a></p>
				</div>
			</form:form>
		</div>
		<!-- end:Main Form -->
	</div>

</body>
</html>