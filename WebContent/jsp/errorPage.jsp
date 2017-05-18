<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/styles2.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<div class="login-page">
		<div class="form">
			<p align="center" style="color:red;">${objMessage.resultMessage }</p>
			<button style="background:red;"  onclick="location.href='${pageContext.request.contextPath}/${objMessage.linkButton }'">Back</button>
		</div>
	</div>
</body>
</html>