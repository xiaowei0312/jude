<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String userHeadImgPath = basePath + "upload/userHeadImg/";
	String mallPicImgPath = "/mallImgs/";
	String onlineCommodImgPath = "/onlineCommodImgs/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<base href="<%=basePath%>"></base>
<!-- <script type="text/javascript" src="common/common.js"></script> -->
</head>
<body class="sticky-header">
	<section>
		<!-- left side start-->
		<div class="left-side sticky-left-side">

			<!--logo and iconic logo start-->
			<div class="logo">
				<a href="<%=basePath%>/user/main.action"><img
					src="images/logo.png" alt=""></a>
			</div>

			<div class="logo-icon text-center">
				<a href="<%=basePath%>/user/main.action"><img
					src="images/logo_icon.png" alt=""></a>
			</div>
			<!--logo and iconic logo end-->

			<div class="left-side-inner">

				<!-- visible to small devices only -->
				<div class="visible-xs hidden-sm hidden-md hidden-lg">
					<div class="media logged-user">
						<img alt="" src="images/photos/user-avatar.png"
							class="media-object">
						<div class="media-body">
							<h4>
								<a href="#">${user.username }</a>
							</h4>
							<span>"Hello There..."</span>
						</div>
					</div>

					<h5 class="left-nav-title">Account Information</h5>
					<ul class="nav nav-pills nav-stacked custom-nav">
						<li><a href="#"><i class="fa fa-user"></i> <span>Profile</span></a></li>
						<li><a href="#"><i class="fa fa-cog"></i> <span>Settings</span></a></li>
						<li><a href="#"><i class="fa fa-sign-out"></i> <span>Sign
									Out</span></a></li>
					</ul>
				</div>

				<!--sidebar nav start-->
				<ul class="nav nav-pills nav-stacked custom-nav">
					<%-- <c:forEach items="${operations }" var="operation">
						<c:if test="${operation.active==true}">
							<c:if test="${empty operation.childOperations}">
								<li class="active"><a href="<%=basePath %>${operation.url}">
										<i class="fa ${operation.style }"></i> <span>${operation.name }</span>
								</a></li>
							</c:if>
							<c:if test="${not empty operation.childOperations}">
								<li class="active menu-list"><a href="javascript:void(0);"><i
										class="fa ${operation.style }"></i> <span>${operation.name }</span></a>
									<ul class="sub-menu-list">
										<c:forEach items="${operation.childOperations }"
											var="childOperation">
											<li><a href="<%=basePath %>${childOperation.url}">${childOperation.name }</a></li>
										</c:forEach>
									</ul></li>
							</c:if>
						</c:if>
						<c:if test="${operation.active==false}">
							<c:if test="${empty operation.childOperations}">
								<li"><a href="<%=basePath %>${operation.url}"><i
										class="fa ${operation.style }"></i> <span>${operation.name }</span></a></li>
							</c:if>
							<c:if test="${not empty operation.childOperations}">
								<li class="menu-list"><a href="javascript:void(0);"><i
										class="fa ${operation.style }"></i> <span>${operation.name }</span></a>
									<ul class="sub-menu-list">
										<c:forEach items="${operation.childOperations }"
											var="childOperation">
											<li><a href="<%=basePath %>${childOperation.url}">
													${childOperation.name }</a></li>
										</c:forEach>
									</ul></li>
							</c:if>
						</c:if>
					</c:forEach> --%>
					<c:forEach items="${operations }" var="operation">
						<c:if test="${empty operation.childOperations}">
							<li id="li_${operation.id }"><a
								href="<%=basePath %>${operation.url}?id=${operation.id}"><i
									class="fa ${operation.style }"></i> <span>${operation.name }</span></a></li>
						</c:if>
						<c:if test="${not empty operation.childOperations}">
							<li id="li_${operation.id }" class="menu-list"><a
								href="javascript:void(0);"><i class="fa ${operation.style }"></i>
									<span>${operation.name }</span></a>
								<ul class="sub-menu-list">
									<c:forEach items="${operation.childOperations }"
										var="childOperation">
										<li id="li_${childOperation.id }"><a
											href="<%=basePath %>${childOperation.url}?id=${childOperation.id}">
												${childOperation.name }</a></li>
									</c:forEach>
								</ul></li>
						</c:if>
					</c:forEach>
				</ul>
				<!--sidebar nav end-->
			</div>
		</div>
		<!-- left side end-->
	</section>
	<script src="js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript">
		var sActiveId = "${subActiveId}";
		$(function() {
			$('#li_'+sActiveId).parent().parent().addClass('nav-active');
			$('#li_'+sActiveId).addClass('active');
		});
		$('.toggle-btn').click();
	</script>
</body>
</html>
