<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	//response.sendRedirect("init.do");
%>
<c:set var="userActionUrl" value="${pageContext.request.contextPath}/service.do" />
<html>
<head>
<title>Free Resorts Website Template | Home :: w3layouts</title>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href='http://fonts.googleapis.com/css?family=Rochester'
	rel='stylesheet' type='text/css'>
<style type="text/css">
	#textarea {
	    border: 1px #ddd solid;
	    width: 400px;
	    height: 120px;
	    font: 9pt Consolas;
	    resize: none;
	}
</style>
</head>
<body class="hide-scrollbar">
	<jsp:include page="../header.jsp" />
	<div class="main">
		<div class="wrap">
			<div class="content-bot">
				<div class="inner-top">
					<div class="content span_1_of_2">
						<form:form cssClass="login-form" method="post" modelAttribute="serviceForm" action="${userActionUrl}">				
								<form:textarea id="textPost" path="textPost"  rows="5"/>								
								<input type="submit" style="background:#2cff2c;" value="Submit"/>				
						</form:form>
						<form:form cssClass="login-form" method="get" modelAttribute="serviceForm" action="${pageContext.request.contextPath}/fetchFacebook.do">														
								<input type="submit" style="background:#2cff2c;" value="fetch"/>				
						</form:form>
					</div>					
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>