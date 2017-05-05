<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	//response.sendRedirect("init.do");
%>
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
</head>
<body class="hide-scrollbar">
	<jsp:include page="../header.jsp" />
	<div class="main">
		<div class="wrap">
			<div class="content-top">
				<%
					int loop = 0;
				%>
				<%
					while (loop < 4) {
				%>
				<div class="col_1_of_4 span_1_of_4">
					<div class="span">
						<div class="banner-wrap ">
							<div class="banner-top">
								<div>
									<figure class="thumbnail">
										<a href="#" title="gallery"><img
											src="${pageContext.request.contextPath}/images/icon4.png"
											title="gallery" alt=""></a>
									</figure>
								</div>
							</div>
							<div class="banner-text">
								<h5>Gallery</h5>
								<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit,
									sed diam nonummy nibh eu Lorem ipsum dolor sit amet,
									consectetuer adipiscing elit, sed diam nonummy nibh eu</p>
								<a href="#"><span class="italic">Read More</span></a>
							</div>
						</div>
					</div>
				</div>
				<%
					loop++;
				%>
				<%
					}
				%>
				<div class="clear"></div>
			</div>
			<div class="content-bot">
				<div class="inner-top">
					<div class="content span_1_of_2">
						<div class="grid images_3_of_2">
							<img src="${pageContext.request.contextPath}/images/pic.jpg">
						</div>
						<div class="desc span_3_of_2">
							<h3>
								Welcome</br> <span class="resort">Our Travel Resorts</span>
							</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
								sed do eiusmod tempor incididunt ut labore et dolore magna
								aliqua. Ut enim ad minim veniam, quis nostrud exercitation
								ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
								aute irure dolor in reprehenderit in voluptat.</p>


							<a href="#" class="button-1 top">&gt; read more</a>
						</div>
					</div>
					<div class="rightsidebar span_3_of_1">
						<h3>Testimonials</h3>
						<div class="comment">
							<p class="upper clr-1">Lorem ipsum dolor sit amet,
								consectetur dipiscing elit. Vivamus sed arcu dui, eu tincidunt
								sem. Vivamus hendrerit mauris ut dui gravida ut Lorem ipsum
								dolor sit ame.</p>
						</div>
						<div class="name">
							<span class="user">Mr. Lorem ipsum</span> - <span class="info">Guide</span>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp" />
</body>
</html>