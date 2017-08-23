<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String userHeadImgPath = basePath + "upload/userHeadImg/";
	//String mallPicImgPath = basePath + "upload/mallPicture/";
	String mallPicImgPath = "/mallPicture/";
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
					<li><a href="#">商铺管理</a></li>
					<li class="active">商铺概况</li>
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
											<button id="malltable-sample_new" class="btn btn-primary">
												添加新商铺 <i class="fa fa-plus"></i>
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
									<div class="space15"></div>
									<table class="table table-striped table-hover table-bordered"
										id="malltable-sample">
										<thead>
											<tr>
												<th>编号</th>
												<th>名称</th>
												<th>联系人</th>
												<th>电话</th>
												<!-- <th>描述</th> -->
												<th>位置</th>
												<th>经度</th>
												<th>纬度</th>
												<th>商家</th>
												<th>代理商</th>
												<th>编辑</th>
												<th>删除</th>
												<!-- <th>设置位置</th> -->
												<th>图片管理</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${not empty mallList}">
												<c:forEach items="${mallList }" var="mall"
													varStatus="status">
													<tr class="">
														<td>${mall.id }</td>
														<td>${mall.mallName }</td>
														<td>${mall.mallLinkMan }</td>
														<td>${mall.mallPhone }</td>
														<%-- <td>${mall.mallDesc }</td> --%>
														<td>${mall.mallAddress }</td>
														<td>${mall.mallPos_lnt }</td>
														<td>${mall.mallPos_lat }</td>
														<td>${mall.user.username }</td>
														<td>${mall.user.proxyUser.username }</td>
														<td><a class="edit" href="javascript:;">编辑</a></td>
														<td><a class="delete" href="javascript:;">删除</a></td>
														<!-- <td><a class="setPos" id="setPos" href="javascript:;">修改位置</a></td> -->
														<td><a class="setPic" id="setPic"
															href="#myModal_${mall.id }" data-toggle="modal">上传图片</a></td>
													</tr>
												</c:forEach>
											</c:if>
										</tbody>
									</table>
								</div>
							</div>
							<div class="model-frame">
								<c:if test="${not empty mallList}">
									<c:forEach items="${mallList }" var="mall" varStatus="status">
										<div aria-hidden="true" aria-labelledby="myModalLabel"
											role="dialog" tabindex="-1" id="myModal_${mall.id }"
											class="modal fade">

											<div class="modal-dialog">
												<div class="modal-content">
													<div class="modal-header">
														<button aria-hidden="true" data-dismiss="modal"
															class="close" type="button">×</button>
														<h4 class="modal-title">Modal Tittle</h4>
													</div>

													<div class="modal-body">
														<form id="uploadImgForm_${mall.id }"
															action="mall/mallImgUploadAjax.action" method="post"
															enctype="multipart/form-data">
															<input type="hidden" name="mallId" value="${mall.id }">
															<div class="mainPic"
																style="border-bottom: 1px solid #96c2f1">
																<div class="form-group">
																	<label for="main_${id }">选择图片</label> <input
																		type="file" id="main_${id }" name="img0">
																</div>
															</div>
															<div class="mainPic"
																style="border-bottom: 1px solid #ccc">
																<div class="form-group">
																	<label for="pic1_${id }">选择图片</label> <input
																		type="file" id="pic1_${id }" name="img1">
																</div>
															</div>
															<div class="mainPic"
																style="border-bottom: 1px solid #ccc">
																<div class="form-group">
																	<label for="pic2_${id }">选择图片</label> <input
																		type="file" id="pic2_${id }" name="img2">
																</div>
															</div>
															<div class="mainPic"
																style="border-bottom: 1px solid #ccc">
																<div class="form-group">
																	<label for="pic3_${id }">选择图片</label> <input
																		type="file" id="pic3_${id }" name="img3">
																</div>
															</div>
															<div class="mainPic"
																style="border-bottom: 1px solid #ccc">
																<div class="form-group">
																	<label for="pic4_${id }">选择图片</label> <input
																		type="file" id="pic4_${id }" name="img4">
																</div>
															</div>
															<div class="mainPic"
																style="border-bottom: 1px solid #ccc">
																<div class="form-group">
																	<label for="pic5_${id }">选择图片</label> <input
																		type="file" id="pic5_${id }" name="img5">
																</div>
															</div>

															<%-- <div class="mainPic"
													style="border-bottom: 2px solid #96c2f1">
													<c:if test="${not empty mall.mallMainPicUrl}">
														<div class="form-group">
														<img src="<%=mallPicImgPath %>${mall.mallMainPicUrl}"
															width="100" height="80" />
															<label for="mall_${id }">修改</label> 
															<input
																type="file" id="main_${id }" name="img0">
														</div>
													</c:if>
													<c:if test="${empty mall.mallMainPicUrl}">
														<div class="form-group">
															<label for="mall_${id }">选择图片</label> <input
																type="file" id="main_${id }" name="img0">
														</div>
														<!-- <section class="panel">
															<header class="panel-heading">
																Dropzone File Upload <span class="tools pull-right">
																	<a class="fa fa-chevron-down" href="javascript:;"></a>
																	<a class="fa fa-times" href="javascript:;"></a>
																</span>
															</header>
															<div class="panel-body">
																<form action="js/dropzone/upload.php" class="dropzone"
																	id="my-awesome-dropzone"></form>
															</div>
														</section> -->
													</c:if>
												</div>
												<div class="mainPic" style="border-bottom: 1px solid #ccc">
													<c:if test="${not empty mall.mallPicUrl1}">
														<img src="<%=mallPicImgPath %>${mall.mallPicUrl1}"
															width="100" height="80" />
														<div class="form-group">
															<label for="pic1_${id }">修改</label> <input
																type="file" id="pic1_${id }" name="img1"">
														</div>
													</c:if>
													<c:if test="${empty mall.mallPicUrl1}">
														<!-- 暂无图片，点我上传 -->
														<div class="form-group">
															<label for="pic1_${id }">选择图片</label> <input
																type="file" id="pic1_${id }" name="img1">
														</div>
													</c:if>
												</div>
												<div class="mainPic" style="border-bottom: 1px solid #ccc">
													<c:if test="${not empty mall.mallPicUrl2}">
														<img src="<%=mallPicImgPath %>${mall.mallPicUrl2}"
															width="100" height="80" />
														<div class="form-group">
															<label for="pic2_${id }">修改</label> <input
																type="file" id="pic2_${id }" name="img2">
														</div>
													</c:if>
													<c:if test="${empty mall.mallPicUrl2}">
														<!-- 暂无图片，点我上传 -->
														<div class="form-group">
															<label for="pic2_${id }">选择图片</label> <input
																type="file" id="pic2_${id }" name="img2">
														</div>
													</c:if>
												</div>
												<div class="mainPic" style="border-bottom: 1px solid #ccc">
													<c:if test="${not empty mall.mallPicUrl3}">
														<img src="<%=mallPicImgPath %>${mall.mallPicUrl3}"
															width="100" height="80" />
														<div class="form-group">
															<label for="pic3_${id }">修改</label> <input
																type="file" id="pic3_${id }" name="img3">
														</div>
													</c:if>
													<c:if test="${empty mall.mallPicUrl3}">
														<!-- 暂无图片，点我上传 -->
														<div class="form-group">
															<label for="pic3_${id }">选择图片</label> <input
																type="file" id="pic3_${id }" name="img3">
														</div>
													</c:if>
												</div>
												<div class="mainPic" style="border-bottom: 1px solid #ccc">
													<c:if test="${not empty mall.mallPicUrl4}">
														<img src="<%=mallPicImgPath %>${mall.mallPicUrl4}"
															width="100" height="80" />
														<div class="form-group">
															<label for="pic4_${id }">修改</label> <input
																type="file" id="pic4_${id }" name="img4">
														</div>
													</c:if>
													<c:if test="${empty mall.mallPicUrl4}">
														<!-- 暂无图片，点我上传 -->
														<div class="form-group">
															<label for="pic4_${id }">选择图片</label> <input
																type="file" id="pic4_${id }" name="img4">
														</div>
													</c:if>
												</div>
												<div class="mainPic" style="border-bottom: 1px solid #ccc">
													<c:if test="${not empty mall.mallPicUrl5}">
														<img src="<%=mallPicImgPath %>${mall.mallPicUrl5}"
															width="100" height="80" />
														<div class="form-group">
															<label for="pic5_${id }">修改</label> <input
																type="file" id="pic5_${id }" name="img5">
														</div>
													</c:if>
													<c:if test="${empty mall.mallPicUrl5}">
														<!-- 暂无图片，点我上传 -->
														<div class="form-group">
															<label for="pic5_${id }">选择图片</label> <input
																type="file" id="pic5_${id }" name="img5">
														</div>
													</c:if>
												</div> --%>
														</form>
													</div>

													<div class="modal-footer">
														<button type="button" class="btn btn-default"
															data-dismiss="modal" id="modalCloseBtn_${mall.id }">Close</button>
														<button type="button" class="btn btn-success"
															onclick="uploadSubmit('${mall.id}')">Save
															changes</button>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:if>
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
	<!--dropzone-->
	<script src="js/dropzone/dropzone.js"></script>
	<!--common scripts for all pages-->
	<!-- <script src="js/scripts.js"></script> -->

	<!--common scripts for all pages-->
	<script src="js/scripts.js"></script>

	<!--data table-->
	<script type="text/javascript"
		src="js/data-tables/jquery.dataTables.js"></script>
	<script type="text/javascript" src="js/data-tables/DT_bootstrap.js"></script>

	<!--script for editable table-->
	<script src="js/editable-table-mall.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/layer.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/template.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/base.js"></script>
	<!-- END JAVASCRIPTS -->
	<script>
		jQuery(document).ready(function() {
			EditableTableMall.init();
		});

		function jsSetPos(m, a, b, c, d, e) {
		}

		function uploadSubmit(a) {
			var id = "uploadImgForm_" + a;
			var f = document.getElementById(id);
			var closeBtnId = "modalCloseBtn_" + a;
			var closeBtn = document.getElementById(closeBtnId);
			//alert($(f).serialize());
			//f.submit();
			//alert($(id).innerHtml());
			//a.submit();
			//var formData = new FormData($(f));
			//formData.append();
			var formData = new FormData(f);
			$
					.ajax({
						url : "${pageContext.request.contextPath}/mall/mallImgUploadAjax.action",
						type : "POST",
						data : formData,
						cache : false,
						contentType : false,
						processData : false,
						success : function(e) {
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
							layer.alert("图片上传成功", {
								icon : 5
							});
							//$(closeBtn).click();
						}
					});
		}
	</script>
</body>
</html>