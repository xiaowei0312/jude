<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String userHeadImgPath = basePath + "upload/userHeadImg/";
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="keywords"
	content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<base href="<%=basePath%>"></base>

<link rel="shortcut icon" href="#" type="image/png">
<title>AdminX</title>

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
				<h3>用户概况</h3>
				<ul class="breadcrumb">
					<li><a
						href="${pageContext.request.contextPath }/user/main.action">首页</a></li>
					<li><a href="#">用户管理</a></li>
					<li class="active">商家管理</li>
				</ul>
			</div>
			<!-- page heading end-->

			<!--body wrapper start-->
			<div class="wrapper">
				<div class="row">
					<div class="col-sm-12">
						<section class="panel">
							<header class="panel-heading">
								Dynamic Table <span class="tools pull-right"> <a
									href="javascript:;" class="fa fa-chevron-down"></a> <a
									href="javascript:;" class="fa fa-times"></a>
								</span>
							</header>
							<div class="panel-body">
								<div class="adv-table">
									<table class="display table table-bordered table-striped"
										id="dynamic-table">
										<thead>
											<tr>
												<th>用户名</th>
												<th>电话</th>
												<th>省份</th>
												<th>市</th>
												<th>区/县</th>
												<th>角色</th>
												<th>代理商</th>
												<th class="hidden-phone">状态</th>
												<th class="hidden-phone">设置</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty userList}">
												<c:forEach items="${userList }" var="user">
													<tr class="gradeA">
														<td>${user.username }</td>
														<td>${user.phone }</td>
														<td>${user.provinceObj.name }</td>
														<td>${user.cityObj.name }</td>
														<td>${user.areaObj.name }</td>
														<td>${user.role.roleName }</td>
														<td>${user.proxyUser.username }</td>
														<c:if test="${user.incomFlag ==1}">
															<td class="center hidden-phone text-danger">停用</td>
														</c:if>
														<c:if test="${user.incomFlag ==0}">
															<td class="center hidden-phone text-info">启用</td>
														</c:if>

														<td><a href="javascript:void(0);"
															onclick="setUserIncomingFlag(this,${user.id},0);">启用</a>
															&nbsp;/&nbsp; <a href="javascript:void(0);"
															onclick="setUserIncomingFlag(this,${user.id},1);">停用</a>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
										<tfoot>
											<tr>
												<th>用户名</th>
												<th>电话</th>
												<th>省份</th>
												<th>市</th>
												<th>区/县</th>
												<th>角色</th>
												<th>代理商</th>
												<th class="hidden-phone">状态</th>
												<th class="hidden-phone">设置</th>
											</tr>
										</tfoot>
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

	<!--common scripts for all pages-->
	<script src="js/scripts.js"></script>

	<!--data table-->
	<script type="text/javascript"
		src="js/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="js/data-tables/DT_bootstrap.js"></script>

	<!--script for editable table-->
	<script src="js/editable-table.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/layer.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/template.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/base.js"></script>

	<script type="text/javascript">
	function setUserIncomingFlag(a, id,n) {
		var statusElement = $(a).parent().prev();
		layer.confirm("确定执行" + (n == 0 ? "【启用】" : "【停用】") + "操作吗?", {
			btn : [ "确定", "取消" ]
		}, function() {
			//审核中
			$.ajax({
				type : "POST",
				url : "${pageContext.request.contextPath}"
						+ "/user/updateUserAjax.action",
				data : {
					'id' : id,
					'incomFlag' : n
				},
				success : function(e) {
					statusElement.removeClass("text-danger");
					statusElement.removeClass("text-info");
					var f = $.parseJSON(e);
					if (f.resultCode != '0') {
						layer.alert("Error Request!", {
							icon : 5
						});
						return;
					}
					if (f.logicCode != '0') {
						layer.alert(f.resultMsg, {
							icon : 5
						});
						return;
					}
					statusElement.addClass(n==0?"text-info":"text-danger");
					statusElement.text(n == 0 ? '启用' : '停用');
					layer.closeAll('dialog');
					return;
				}
			})
		})
	};
</script>
</body>
</html>