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

<!--dashboard calendar-->
<link href="css/clndr.css" rel="stylesheet">

<!--Morris Chart CSS -->
<link rel="stylesheet" href="js/morris-chart/morris.css">

<!--dynamic table-->
<link href="js/advanced-datatable/css/demo_page.css" rel="stylesheet" />
<link href="js/advanced-datatable/css/demo_table.css" rel="stylesheet" />
<link rel="stylesheet" href="js/data-tables/DT_bootstrap.css" />

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
				<h3>报单概况</h3>
				<ul class="breadcrumb">
					<li><a
						href="${pageContext.request.contextPath }/user/main.action">首页</a></li>
					<li><a href="#">报单管理</a></li>
					<li class="active">报单概况</li>
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
												<!-- <th>订单编号</th> -->
												<th>商家信息</th>
												<th>买家信息</th>
												<!-- <th>商品类型</th> -->
												<th>商品</th>
												<th>金额</th>
												<th>优惠率 %</th>
												<th>客户积分</th>
												<th>商户积分</th>
												<th>报单时间</th>
												<th class="hidden-phone">状态</th>
												<th class="hidden-phone">审核</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty journalBookList }">
												<c:forEach items="${journalBookList }" var="book">
													<tr class="gradeA">
														<%-- <td>${book.indent.indentNo }</td> --%>
														<td>${book.business.username }</td>
														<td>${book.client.username }</td>
														<%-- <td>${book.commodityType.typeName }</td> --%>
														<td>${book.commodityName }</td>
														<td>${book.amount }</td>
														<td>${book.premiumRates }%</td>
														<td>${book.giftJf }</td>
														<td>${book.rewardJf }</td>
														<td><fmt:formatDate value="${book.journalTime }"
																pattern="yy-MM-dd HH:mm:ss" /></td>
														<c:if test="${book.flag ==0}">
															<td class="center hidden-phone text-danger">待审核</td>
														</c:if>
														<c:if test="${book.flag ==1}">
															<td class="center hidden-phone text-info">已同意</td>
														</c:if>
														<c:if test="${book.flag ==2}">
															<td class="center hidden-phone text-success">已奖励</td>
														</c:if>
														<c:if test="${book.flag ==3}">
															<td class="center hidden-phone text-warning">已拒绝</td>
														</c:if>
														<td><a href="javascript:void(0);"
															onclick="orderVerify(this,${book.id},1);">同意</a> <c:if
																test="${book.flag ==3}">	
												&nbsp;/&nbsp; <a href="javascript:void(0);"
																	style="color: #ccc">拒绝</a>
															</c:if> <c:if test="${book.flag !=3}">	
												&nbsp;/&nbsp; <a href="javascript:void(0);"
																	onclick="orderVerify(this,${book.id},3);">拒绝</a>
															</c:if></td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
										<tfoot>
											<tr>
												<!-- <th>订单编号</th> -->
												<th>商家信息</th>
												<th>买家信息</th>
												<!-- <th>商品类型</th> -->
												<th>商品</th>
												<th>金额</th>
												<th>优惠率 %</th>
												<th>客户积分</th>
												<th>商户积分</th>
												<th>报单时间</th>
												<th class="hidden-phone">状态</th>
												<th class="hidden-phone">审核</th>
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
	<script
		src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery-ui-1.9.2.custom.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery-migrate-1.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/modernizr.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/jquery.nicescroll.js"></script>

	<!--common scripts for all pages-->
	<script src="js/scripts.js"></script>

	<!--dynamic table-->
	<script type="text/javascript" language="javascript"
		src="${pageContext.request.contextPath}/js/advanced-datatable/js/jquery.dataTables.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/data-tables/DT_bootstrap.js"></script>
	<!--dynamic table initialization -->
	<script
		src="${pageContext.request.contextPath}/js/dynamic_table_init.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/layer.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/template.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/base.js"></script>
	<script type="text/javascript">
	function orderVerify(a,id,n){
		var statusElement = $(a).parent().prev();
		layer.confirm(
			"确定执行"+(n==1?"【同意】":"【拒绝】")+"操作吗?",
			{
				btn : [ "确定", "取消" ]
			},
			function() {
				$(a).parent().children('a').removeAttr('onclick');
				$(a).parent().children('a').css("color","#ccc");
				
				layer.closeAll('dialog');
				statusElement.text("");
				statusElement.removeClass("text-danger");
				statusElement.removeClass("text-warning");
				statusElement.addClass("fa fa-spinner");
				//审核中
				$.ajax({
					type: "POST",
					url : "${pageContext.request.contextPath}"+"/user/submitOrderVerifySubmitAjax.action",
					data : {
						'id':id,
						'flag':n
					},
					success : function(e) {
						statusElement.removeClass("fa fa-spinner");
						var f = $.parseJSON(e);
						if (f.resultCode!='0') {
							layer.alert("Error Request!", {
								icon : 5
							});
							return;
						}
						if (f.logicCode!='0') {
							layer.alert(f.resultMsg,{
								icon : 5
							});
							statusElement.addClass("text-danger");
							statusElement.text('待审核');
							return;
						}
						statusElement.text(n==1?'已同意':'已拒绝');
						statusElement.addClass(n==1?"text-info":"text-warning");
						return;
					}
				})
			})
	};
</script>
</body>
</html>