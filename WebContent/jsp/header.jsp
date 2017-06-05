<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="header-bg">
	<div class="wrap">
		<div class="header">
			<div class="logo">
				<h1><a href="main.do">Resorts</a></h1>
			</div>
		</div>
		<div class="header-bot">
	   		<div class="menu">
		   		<ul>				   			
					<c:forEach items="${menu}" var="item">		
					<li class=""><a href="<c:out value="${item.menuAction }"/>"><c:out value="${item.menuName }" /></a></li>
					
					<!-- <li class="home"><a href="init.do">Login</a></li>					
					<li class=""><a href="serviceInit.do">Services</a></li>
					<li class=""><a href="">Contact</a></li>
					<li class=""></li>  -->
					</c:forEach>
				</ul>
				<div class="clear"></div>
	   		</div>
	   	</div>
	</div>
	<div class="banner">
		<div class="wrap">
			<span class="banner-img">
				<img src="${pageContext.request.contextPath}/images/banner2.jpg" alt=""/>
			</span>
		</div>
	</div>
</div>