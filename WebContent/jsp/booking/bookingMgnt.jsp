<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	//response.sendRedirect("init.do");
%>
<c:set var="userActionUrl"
	value="${pageContext.request.contextPath}/bookingSubmit.do" />

<html>
<head>
<title>Free Resorts Website Template | Home :: w3layouts</title>
<link href="${pageContext.request.contextPath}/css/mgntUser.css"
	rel="stylesheet" media="all">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>   
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>


<script  type="text/javascript">
$(document).ready(function() {
    $(function () {
    	
        $('#datetimepicker6').datetimepicker();
        $('#datetimepicker7').datetimepicker({
            useCurrent: false //Important! See issue #1075
            
        });
        $("#datetimepicker6").on("dp.change", function (e) {
            $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
        });
        $("#datetimepicker7").on("dp.change", function (e) {
            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
        });
    });
});
</script>
</head>
<body class="hide-scrollbar">
	<div class="text-center" style="padding:50px 100px;" align="center" >
		
		<!-- Main Form -->
		<div class="login-form-1" align="center" style="width:500px">
		<div class="logo">Booking</div>
			<form:form id="login-form" method="post" modelAttribute="bookingForm" action="${userActionUrl}" class="text-left">
				<div class="login-form-main-message"></div>
				<div class="main-login-form">
					<div class="login-group">
						<div class="form-group">
							<label for="title" class="sr-only">Title</label> 
							<form:input id="title" name="title" path="title" cssClass="form-control" />
						</div>
						<div class="form-group">
							<label for="name" class="sr-only">Name</label>
							<form:input id="name" path="name" cssClass="form-control"  />
						</div>						
						<div class="form-group">
							<label for="phone" class="sr-only">Phone</label>
							<form:input id="phone" path="phone" cssClass="form-control"  />
						</div>
						<div class="form-group">
							<label for="fullname" class="sr-only">StartDate</label>
							<div class='input-group date' id='datetimepicker6'>
				                <form:input  path="startDate" cssClass="form-control" />	
				                <span class="input-group-addon">
				                    <span class="glyphicon glyphicon-calendar"></span>
				                </span>
				            </div>							
						</div>	
						<div class="form-group">
							<label for="endDate" class="sr-only">EndDate</label>							
							<div class='input-group date' id='datetimepicker7'>
				                <form:input path="endDate" cssClass="form-control" />	
				                <span class="input-group-addon">
				                    <span class="glyphicon glyphicon-calendar"></span>
				                </span>
				            </div>	
						</div>	
					</div>
					<button type="submit"  class="login-button">Booking</button>
				</div>				
			</form:form>
		</div>
		<!-- end:Main Form -->
	</div>    

</body>
</html>