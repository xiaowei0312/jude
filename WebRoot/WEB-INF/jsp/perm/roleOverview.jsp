<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String mallPicImgPath = "/mallImgs/";
	String onlineCommodImgPath = "/onlineCommodImgs/";
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

<!--pickers css-->
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-datepicker/css/datepicker-custom.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-timepicker/css/timepicker.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-colorpicker/css/colorpicker.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" type="text/css"
	href="js/bootstrap-datetimepicker/css/datetimepicker-custom.css" />


<!--dashboard calendar-->
<link href="css/clndr.css" rel="stylesheet">

<!--Morris Chart CSS -->
<link rel="stylesheet" href="js/morris-chart/morris.css">

<!--common-->
<link href="css/style.css" rel="stylesheet">
<link href="css/style-responsive.css" rel="stylesheet">
<link href="css/Pager.css" rel="stylesheet" type="text/css" />
<link href="css/doc2.css" rel="stylesheet">


<link href="js/advanced-datatable/css/demo_page.css" rel="stylesheet" />
<link href="js/advanced-datatable/css/demo_table.css" rel="stylesheet" />
<link rel="stylesheet" href="js/data-tables/DT_bootstrap.css" />
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
				<h3>权限管理</h3>
				<ul class="breadcrumb">
					<li><a
						href="${pageContext.request.contextPath }/user/main.action">首页</a></li>
					<li><a href="#">权限管理</a></li>
					<li class="active">角色管理</li>
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
									<div class="clearfix">
										<div class="btn-group">
											<a href="#modal_add" data-toggle="modal"><button type="button" id="addMallBtn"
														class="btn btn-primary">添加新角色</button></a>
											</button>
										</div>
										<div class="btn-group pull-right">
											<button class="btn btn-default dropdown-toggle"
												data-toggle="dropdown">
												Tools <i class="fa fa-angle-down"></i>
											</button>
											<ul class="dropdown-menu pull-right">
												<li><a href="#">Print</a></li>
												<li><a href="#">Save as PDF</a></li>
												<li><a href="#">Export to Excel</a></li>
											</ul>
										</div>
									</div>

									<br />
									<div id="search-condition" class="search-condition row">
										<div class="col-md-8">
											<form class="form-inline" role="form">
												<div class="form-group col-md-3" style="padding: 0px">
													<input type="text" class="form-control" id="searchKey"
														placeholder="角色名">
												</div>
												<div class="form-group col-md-7">
													<div class="input-group input-large custom-date-range"
														data-date="" data-date-format="yyyy/mm/dd">
														<input type="text" class="form-control dpd1"
															name="startDate" id="searchStartDate" placeholder="起始日期">
														<span class="input-group-addon">To</span> <input
															type="text" id="searchEndDate" class="form-control dpd2"
															name="to" placeholder="结束日期">
													</div>
												</div>
												<div class="form-group col-md-2">
													<button type="button" id="searchBtn"
														class="btn btn-primary">搜索</button>
												</div>
											</form>
										</div>
										<div class="col-md-4">
											<div class="form-group col-md-6">
												<select class="form-control" name="roleSelect"
													id="typeSelect">
													<option value="-2" checked="checked">全部类型</option>
												</select>
											</div>
											<div class="form-group col-md-6">
												<select class="form-control" name="timeOrderBy"
													id="timeOrderBy">
													<option value="0" checked="checked">时间降序</option>
													<option value="1">时间升序</option>
												</select>
											</div>
										</div>
									</div>
									<br />

									<div class="space15"></div>
									<table class="table table-striped table-hover table-bordered"
										id="malltable-sample">
										<thead>
											<tr>
												<!-- <th>编号</th> -->
												<th>角色名</th>
												<th>角色级别</th>
												<th>显示序号</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody id="mallList">
										</tbody>
									</table>
								</div>
								<div id="pager_div" class="pager_div">
									<div id="pager"></div>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 到第<input type="text"
										class="text-box" id="numberOfPages"> 页 <input
										type="button" value="GO" class="sub-btn"
										onclick="javascript:commodityListComp.goToPage();">
								</div>
							</div>
						</section>
						<section class="modal-section">
							<!-- 添加 模态框 -->
							<div aria-hidden="true" aria-labelledby="myModalLabel"
								role="dialog" tabindex="-1" id="modal_add" class="modal fade">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-body">
											<section class="panel">
												<header class="panel-heading"> 新增角色</header>
												<div class="panel-body">
													<form class="form-horizontal" role="form"
														id="modalForm">
														<div class="form-group">
															<label for="commodityName_editModal"
																class="col-lg-2 col-sm-2 control-label">角色名</label>
															<div class="col-lg-10">
																<input type="text" class="form-control"
																	name="roleName" id="roleName"
																	placeholder="角色名">
																<!-- <p class="help-block">商铺名称会显示在前台网站上.</p> -->
															</div>
														</div>
														<div class="form-group">
															<label for="commoditySeq_editModal"
																class="col-lg-2 col-sm-2 control-label">显示序号</label>
															<div class="col-lg-10">
																<input type="text" class="form-control"
																	name="seqNum" id="seqNum"
																	placeholder="显示序号">
																<!-- <p class="help-block">联系人代表该商铺的常用联系人.</p> -->
															</div>
														</div>
														<div class="form-group">
															<div class="col-lg-offset-2 col-lg-2">
																<button type="button" id="submitBtn"
																	class="btn btn-primary">确定</button>
															</div>
															<div class="col-lg-8">
																<button type="button" class="btn btn-default"
																	data-dismiss="modal"">关闭</button>
															</div>
														</div>
													</form>
												</div>
											</section>
										</div>
									</div>
								</div>
							</div>
							<!-- 修改 模态框 -->
							<div aria-hidden="true" aria-labelledby="myModalLabel"
								role="dialog" tabindex="-1" id="modal_edit" class="modal fade">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-body">
											<section class="panel">
												<header class="panel-heading"> 修改角色信息 </header>
												<div class="panel-body">
													<form class="form-horizontal" role="form"
														id="modalForm">
														<div class="form-group">
															<label for="commodityName_editModal"
																class="col-lg-2 col-sm-2 control-label">角色名</label>
															<div class="col-lg-10">
																<input type="hidden" class="form-control" name="id"
																	id="id" placeholder="用户ID">
																<input type="text" class="form-control"
																	name="roleName" id="roleName"
																	placeholder="角色名">
																<!-- <p class="help-block">商铺名称会显示在前台网站上.</p> -->
															</div>
														</div>
														<div class="form-group">
															<label for="commoditySeq_editModal"
																class="col-lg-2 col-sm-2 control-label">显示序号</label>
															<div class="col-lg-10">
																<input type="text" class="form-control"
																	name="seqNum" id="seqNum"
																	placeholder="显示序号">
																<!-- <p class="help-block">联系人代表该商铺的常用联系人.</p> -->
															</div>
														</div>
														<div class="form-group">
															<div class="col-lg-offset-2 col-lg-2">
																<button type="button" id="submitBtn"
																	class="btn btn-primary">确定</button>
															</div>
															<div class="col-lg-8">
																<button type="button" class="btn btn-default"
																	data-dismiss="modal"">关闭</button>
															</div>
														</div>
													</form>
												</div>
											</section>
										</div>
									</div>
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
	<script src="js/jquery.pager1.js"></script>
	<!-- <script src="js/jquery_002.js"></script> -->
	<!--common scripts for all pages-->
	<script src="js/scripts.js"></script>


	<!--pickers plugins-->
	<script type="text/javascript"
		src="js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript"
		src="js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript"
		src="js/bootstrap-daterangepicker/moment.min.js"></script>
	<script type="text/javascript"
		src="js/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script type="text/javascript"
		src="js/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
	<script type="text/javascript"
		src="js/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>

	<!--pickers initialization-->
	<script src="js/pickers-init.js"></script>

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
	<script type="text/javascript" src="js/roleList.js"></script>
	<!-- END JAVASCRIPTS -->

	<script>
 		var basePath = '<%=basePath%>';
 		var mallPicImgPath = '<%=mallPicImgPath%>';
	</script>
</body>
</html>