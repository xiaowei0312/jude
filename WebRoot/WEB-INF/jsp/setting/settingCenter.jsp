<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String userHeadImgPath = basePath + "upload/userHeadImg/";
%>

<html>
<head>
<base href="<%=basePath%>"></base>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="keywords"
	content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<link rel="shortcut icon" href="#" type="image/png">

<title>AdminX</title>

<!--data table-->
<link rel="stylesheet" href="js/data-tables/DT_bootstrap.css" />

<!--icheck-->
<link href="js/iCheck/skins/minimal/minimal.css" rel="stylesheet">
<link href="js/iCheck/skins/square/square.css" rel="stylesheet">
<link href="js/iCheck/skins/square/red.css" rel="stylesheet">
<link href="js/iCheck/skins/square/blue.css" rel="stylesheet">

<!--dashboard calendar-->
<link href="css/clndr.css" rel="stylesheet">

<!--Morris Chart CSS -->
<link rel="stylesheet" href="js/morris-chart/morris.css">

<!--common-->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet">

<link rel="stylesheet" href="common/layer.css"
	id="layui_layer_skinlayercss">
</head>

<body class="sticky-header">
	<section>
		<!-- 左栏信息 -->
		<jsp:include page="/user1/getUserOperations.action"></jsp:include>

		<!-- main content start-->
		<div class="main-content">
			<!-- 头部信息 -->
			<jsp:include page="/user1/getLoginInfo.action"></jsp:include>

			<!-- page heading start-->
			<div class="page-heading">
				<h3>系统参数</h3>
				<ul class="breadcrumb">
					<li><a
						href="${pageContext.request.contextPath }/user/main.action">首页</a></li>
					<li><a href="#">设置中心</a></li>
					<li class="active">系统参数</li>
				</ul>
			</div>
			<!-- page heading end-->

			<!--body wrapper start-->
			<div class="wrapper">
				<div class="row">
					<div class="col-sm-12">
						<section class="panel">
							<header class="panel-heading">
								Editable Table <span class="tools pull-right"> <a
									href="javascript:;" class="fa fa-chevron-down"></a> <a
									href="javascript:;" class="fa fa-times"></a>
								</span>
							</header>
							<div class="panel-body">
								<div class="adv-table editable-table ">
									<div class="space15"></div>
									<table class="table table-striped table-hover table-bordered"
										id="editable-sample-setting">
										<thead>
											<tr>
												<th>现金比</th>
												<th>养老金比</th>
												<!-- <th>日返总额</th> -->
												<th>聚财宝日返总额</th>
												<th>会员积分比</th>
												<th>商家积分比</th>
												<th>得利宝上限</th>
												<th>报单审核阈值</th>
												<th>取现标志（1允许 0禁用）</th>
												<th>取现上限</th>
												<th>邀请人赠送积分</th>
												<th>被邀请人赠送积分</th>
												<th>编辑</th>
												<th>取消</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty globalParams}">
												<tr class="">
													<td>${globalParams.cashRatio }</td>
													<td>${globalParams.yljRatio }</td>
													<%-- <td>${globalParams.totalReCash }</td> --%>
													<td>${globalParams.cashByOneDlb }</td>
													<td>${globalParams.vipJfRatio }</td>
													<td>${globalParams.busJfRatio }</td>
													<td>${globalParams.oneDlbTotalIncom }</td>
													<td>${globalParams.checkLimitAmount }</td>
													<td>${globalParams.withdrawFlag }</td>
													<td>${globalParams.withdrawUpLimit }</td>
													<td>${globalParams.inviterSuccessGiftJf }</td>
													<td>${globalParams.beInviterSuccessGiftJf }</td>
													<td><a class="edit" href="javascript:;">编辑</a></td>
													<td><a class="" href="javascript:;">取消</a></td>
												</tr>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
						</section>
					</div>
				</div>
			</div>
			<jsp:include page="/user1/footer.action"></jsp:include>
		</div>
	</section>
	
	<!-- Placed js at the end of the document so the pages load faster -->
	<script src="js/jquery-1.10.2.min.js"></script>
	<script src="js/jquery-ui-1.9.2.custom.min.js"></script>
	<script src="js/jquery-migrate-1.2.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/modernizr.min.js"></script>
	<script src="js/jquery.nicescroll.js"></script>

	<!--data table-->
	<script type="text/javascript"
		src="js/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="js/data-tables/DT_bootstrap.js"></script>

	<!--common scripts for all pages-->
	<script src="js/scripts.js"></script>

	<!--script for editable table-->
	<script src="js/editable-table-setting1.js"></script>

	<!-- Common My script -->
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/layer.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/template.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/base.js"></script>
	<!-- END JAVASCRIPTS -->

	<script>
		jQuery(document).ready(function() {
			EditableTableSetting.init();
		});
	</script>
</body>
</html>