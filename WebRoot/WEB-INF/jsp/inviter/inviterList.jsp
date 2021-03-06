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

<style type="text/css">
	.uploadFile_div
	{
	    position: relative;
	}
	.uploadFile_div input
	{
	    opacity:0;
	    filter:alpha(opacity=0);
	    height: 95px;
	    width: 100px;
	    position: absolute;
	    top: 0;
	    left: 0;
	    z-index: 9;
	}
</style>

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
				<h3>邀请注册管理</h3>
				<ul class="breadcrumb">
					<li><a
						href="${pageContext.request.contextPath }/user/main.action">首页</a></li>
					<li><a href="#">邀请注册管理</a></li>
					<li class="active">邀请注册</li>
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
									<div class="col-md-6">
										<form class="form-inline" role="form">
											<div class="form-group col-md-4" style="padding-left:0">
												<input type="text" class="form-control"
													id="searchKey" placeholder="邀请人、被邀请人">
											</div>
											<div class="form-group col-md-6">
												<div class="input-group input-large custom-date-range"
													data-date="" data-date-format="yyyy/mm/dd">
													<input type="text" class="form-control dpd1" name="startDate"
														id = "searchStartDate" placeholder="起始日期"> <span
														class="input-group-addon">To</span> <input type="text"
														id = "searchEndDate" class="form-control dpd2" name="to" placeholder="结束日期">
												</div>
											</div>
											<div class="form-group col-md-1">
												<button type="button" id="searchBtn" class="btn btn-primary">搜索</button>
											</div>
										</form>
									</div>
									<!-- <div class="col-md-1">
									</div> -->
									<div class="col-md-6" style="padding-left:0">
										<div class="col-md-4" style="padding-left:0">
										<select class="form-control" name="sendPushMsgFlag" id="sendPushMsgFlag">
											<option value="-2">是否登录过用户中心</option>
											<option value="0">未登录</option>
											<option value="1">已登录</option>
										</select> 
										</div>
										<div class="col-md-4">
										<select class="form-control" name="inviterRewardFlag" id="inviterRewardFlag">
											<option value="-2">是否产生消费</option>
											<option value="0">未产生</option>
											<option value="1">已产生</option>
										</select> 
										</div>
										<div class="col-md-4">
										<select class="form-control" name="addTimeOrderBy" id="addTimeOrderBy">
											<option value="0" checked="checked">时间降序</option>
											<option value="1">时间升序</option>
										</select>
										</div>
									</div>
									<!-- <div class="col-md-1" style="padding-left:0;margin-left:0">
										<a href="#addModal" data-toggle="modal"><button type="button" id="addNewCommodityBtn" class="btn btn-primary">添加商品</button></a>
									</div>  -->
								</div>
								<br/>
								<div class="adv-table">
									<table class="display table table-bordered" 
										id="hidden-table-info">
										<thead>
											<tr class="orderTr" id="theadTr">
												<th>邀请人</th>
												<th>被邀请人</th>
												<th>是否登录过</th>
												<th>是否产生消费</th>
												<th>注册时间</th>
												<!-- <th style="width:10%">详情页</th> -->
												<!-- <th style="width:6%">状态</th> -->
												<!-- <th style="width:10%">类型</th>
												<th style="width:24%">操作</th> -->
											</tr>
										</thead>
										<tbody id="commodityList">
										</tbody>
									</table>
								</div>
								<div id="pager_div" class="pager_div">
									<div id="pager"></div>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 到第<input type="text"
										class="text-box" id="numberOfPages"> 页 <input
										type="button" value="GO" class="sub-btn"
										onclick="javascript:orderListComp.goToPage();">
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
	<script type="text/javascript" src="js/ckeditor/ckeditor.js"></script>
	<script src="js/inviterList.js"></script>
	<script type="text/javascript">
		var basePath = '<%=basePath%>';
	</script>
</body>
</html>