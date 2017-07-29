<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.booking.Object.ObjBooking" %>
<%
	//response.sendRedirect("init.do");
%>
<c:set var="userActionUrl" value="${pageContext.request.contextPath}/service.do" />
<c:set var="objBookings" value="${objBookings}"/>
<head>
<title>Free Resorts Website Template | Home :: w3layouts</title>
<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="${pageContext.request.contextPath}/css/styles4.css"
	rel="stylesheet">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href='http://fonts.googleapis.com/css?family=Rochester'
	rel='stylesheet' type='text/css'>
<link
	href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css"
	rel="stylesheet" >
<link href="${pageContext.request.contextPath}/css/fullcalendar.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/fullcalendar.print.min.css" rel="stylesheet" media="print" />
<script src="${pageContext.request.contextPath}/js/moment.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/fullcalendar.min.js"></script>

<style type="text/css">
	#textarea {
	    border: 1px #ddd solid;
	    width: 400px;
	    height: 120px;
	    font: 9pt Consolas;
	    resize: none;
	}
</style>

<script>

	$(document).ready(function() {
		var tmp = {};
		tmp[0] = 
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				//listDay,listWeek,month
				right: 'month' 
			},

			// customize the button names,
			// otherwise they'd all just say "list"
			views: {
				listDay: { buttonText: 'list day' },
				listWeek: { buttonText: 'list week' }
			},

			defaultView: 'month',
			defaultDate: '2017-07-05',
			navLinks: true, // can click day/week names to navigate views
			editable: true,
			eventLimit: true, // allow "more" link when too many events
			events: [
			<% ArrayList<ObjBooking> objBookings = (ArrayList<ObjBooking>) request.getAttribute("objBookings");%>
			<% for(ObjBooking obj : objBookings){%>
				{
					title: '<%=obj.getTitle()%>',
					start: '<%=obj.getStartDate()+"T12:00:00"%>',
					end: '<%=obj.getEndDate()+"T12:00:00"%>'
				},
			<% }%>
				{
					title: 'test',
					start:'2011-01-01',
					end: '2011-01-02'
				}
			],
			tmp : tmp
		});
		
	});

</script>
<style>

	body {
		margin: 40px 10px;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}

	#calendar {
		max-width: 900px;
		margin: 0 auto;
	}

</style>
</head>
<body class="hide-scrollbar">
	<jsp:include page="../header.jsp" />
	<!-- <div class="main">
		<div class="row-fluid">
			<div class="span2"></div>
				<div class="span8">
					<form:form cssClass="login-form" method="post" modelAttribute="serviceForm" action="${userActionUrl}">				
							<form:textarea id="textPost" path="textPost"  rows="5"/>								
							<input type="submit" value="Submit"/>				
					</form:form>
					<form:form cssClass="login-form" method="get" modelAttribute="serviceForm" action="${pageContext.request.contextPath}/fetchFacebook.do">														
							<input type="submit" value="fetch"/>				
					</form:form>
				</div>
				<div class="span2"></div>
			</div>
		</div>
	</div> -->

	<div id='calendar'></div>
	

	<jsp:include page="../footer.jsp" />
</body>
</html>