<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
				<h3>通知分类管理</h3>
				<ul class="breadcrumb">
					<li><a
						href="${pageContext.request.contextPath }/user/main.action">首页</a></li>
					<li><a href="#">通知管理</a></li>
					<li class="active">通知分类管理</li>
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
								<div id="search-condition" class="search-condition row">
									<div class="col-md-4">
										<form class="form-inline" role="form">
											<div class="form-group col-md-8">
												<input type="text" class="form-control" id="searchKey"
													placeholder="分类名称">
											</div>
											<!-- <div class="form-group col-md-7">
												<div class="input-group input-large custom-date-range"
													data-date="" data-date-format="yyyy/mm/dd">
													<input type="text" class="form-control dpd1" name="startDate"
														id = "searchStartDate" placeholder="起始日期"> <span
														class="input-group-addon">To</span> <input type="text"
														id = "searchEndDate" class="form-control dpd2" name="to" placeholder="结束日期">
												</div>
											</div> -->
											<div class="form-group col-md-3">
												<button type="button" id="searchBtn" class="btn btn-primary">搜索</button>
											</div>
										</form>
									</div>
									<!-- <div class="col-md-1">
									</div> -->
									<div class="col-md-5">
										<!-- <div class="col-md-4">
											<select class="form-control" name="orderStatusSelect" id="orderStatusSelect">
												<option value="-2" checked="checked">全部状态</option>
												<option value="0">已付款</option>
												<option value="-1">已撤单</option>
												<option value="1">已发货</option>
												<option value="2">已收货</option>
											</select> 
										</div>
										<div class="col-md-4">
										<select class="form-control" name="orderAmountOrderBy" id="orderAmountOrderBy">
											<option value="0" checked="checked">金额降序</option>
											<option value="1">金额升序</option>
										</select>
										</div> -->
										<div class="col-md-9">
											<select class="form-control" name="typeSeqOrderBy"
												id="typeSeqOrderBy">
												<option value="0" checked="checked">序号降序</option>
												<option value="1">序号升序</option>
											</select>
										</div>
									</div>
									<div class="col-md-2">
										<div class="col-md-12">
											<a href="#addModal" data-toggle="modal"><button type="button" class="btn btn-primary">添加新类型</button></a>
										</div>
									</div>
								</div>
								<br />
								<div class="adv-table">
									<table class="display table table-bordered table-striped"
										id="dynamic-table">
										<thead>
											<tr>
												<th>类别名称</th>
												<th>父类别名称</th>
												<th>显示序号</th>
												<th>操作</th>
												<!-- <th>下单时间</th>
												<th>联系人</th>
												<th>联系电话</th>
												<th>收获地址</th>
												<th>订单状态</th> -->
											</tr>
										</thead>
										<tbody id="typeList">
										</tbody>
									</table>
								</div>
								<div id="pager_div" class="pager_div">
									<div id="pager"></div>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 到第<input type="text"
										class="text-box" id="numberOfPages"> 页 <input
										type="button" value="GO" class="sub-btn"
										onclick="javascript:typeListComp.goToPage();">
								</div>
							</div>
						</section>
						<section class="model">
							<!-- 修改商品类别 模态框 -->
							<div aria-hidden="true" aria-labelledby="myModalLabel"
								role="dialog" tabindex="-1" id="editModal" class="modal fade">
								<div class="modal-dialog">
									<div class="modal-content">
										<!-- <div class="modal-header">
											<button aria-hidden="true" data-dismiss="modal"
												class="close" type="button">×</button>
											<h4 class="modal-title">Update the Commodity Type</h4>
										</div> -->
										<div class="modal-body">
											<section class="panel">
												<header class="panel-heading"> 修改商品类别信息 </header>
												<div class="panel-body">
													<form class="form-horizontal" role="form" id="editModalForm">
														<div class="form-group">
															<label for="typeName"
																class="col-lg-2 col-sm-2 control-label">类别名称</label>
															<div class="col-lg-10">
																<input type="hidden" class="form-control"
																	id="typeId" name="id">
																<input type="text" class="form-control" name="typeName"
																	id="typeName" placeholder="类别名称">
																<p class="help-block">类别名称会显示在前台网站上.</p>
															</div>
														</div>
														<div class="form-group">
															<label for="typeSeq"
																class="col-lg-2 col-sm-2 control-label">显示序号</label>
															<div class="col-lg-10">
																<input type="text" class="form-control" name="typeSeq"
																	id="typeSeq" placeholder="显示序号">
																<p class="help-block">显示序号代表该类别在网站上显示的顺序，序号越小越靠前显示.</p>
															</div>
														</div>
														<!-- <div class="form-group">
															<label for="exampleInputFile2"
																class="col-lg-2 col-sm-2 control-label">File
																input</label>
															<div class="col-lg-10">
																<input type="file" id="exampleInputFile2">
																<p class="help-block">Example block-level help text
																	here.</p>
															</div>
														</div> 
														<div class="form-group">
															<div class="col-lg-offset-2 col-lg-10">
																<div class="checkbox">
																	<label> <input type="checkbox">
																		Remember me
																	</label>
																</div>
															</div>
														</div>-->
														<div class="form-group">
															<div class="col-lg-offset-2 col-lg-2">
																<button type="button" id="editModalSubmitBtn" class="btn btn-primary">确定</button>
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
							<!-- 添加商品类别 模态框 -->
							<div aria-hidden="true" aria-labelledby="myModalLabel"
								role="dialog" tabindex="-1" id="addModal" class="modal fade">
								<div class="modal-dialog">
									<div class="modal-content">
										<!-- <div class="modal-header">
											<button aria-hidden="true" data-dismiss="modal"
												class="close" type="button">×</button>
											<h4 class="modal-title">Update the Commodity Type</h4>
										</div> -->
										<div class="modal-body">
											<section class="panel">
												<header class="panel-heading"> 添加商品类别 </header>
												<div class="panel-body">
													<form class="form-horizontal" role="form" id="addModalForm">
														<div class="form-group">
															<label for="typeName"
																class="col-lg-2 col-sm-2 control-label">类别名称</label>
															<div class="col-lg-10">
																<input type="hidden" class="form-control"
																	id="typeId_addModal" name="id">
																<input type="text" class="form-control" name="typeName"
																	id="typeName_addModal" placeholder="类别名称">
																<p class="help-block">类别名称会显示在前台网站上.</p>
															</div>
														</div>
														<div class="form-group">
															<label for="typeSeq"
																class="col-lg-2 col-sm-2 control-label">显示序号</label>
															<div class="col-lg-10">
																<input type="text" class="form-control" name="typeSeq"
																	id="typeSeq_addModal" placeholder="显示序号">
																<p class="help-block">显示序号代表该类别在网站上显示的顺序，序号越小越靠前显示.</p>
															</div>
														</div>
														<!-- <div class="form-group">
															<label for="exampleInputFile2"
																class="col-lg-2 col-sm-2 control-label">File
																input</label>
															<div class="col-lg-10">
																<input type="file" id="exampleInputFile2">
																<p class="help-block">Example block-level help text
																	here.</p>
															</div>
														</div> 
														<div class="form-group">
															<div class="col-lg-offset-2 col-lg-10">
																<div class="checkbox">
																	<label> <input type="checkbox">
																		Remember me
																	</label>
																</div>
															</div>
														</div>-->
														<div class="form-group">
															<div class="col-lg-offset-2 col-lg-2">
																<button type="button" id="addModalSubmitBtn" class="btn btn-primary">确定</button>
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
			<!--body wrapper end-->
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
	<script src="js/noticeTypeList.js"></script>
	<script type="text/javascript">
		var basePath = '<%=basePath%>';
	</script>
</body>
</html>