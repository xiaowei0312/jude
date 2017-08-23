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
	<div class="center">
		<div class="col-lg-6 center">
			<section class="panel">
				<header class="panel-heading"> 修改密码 </header>
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="changePwdForm">
						<div class="form-group">
							<label for="inputEmail1" class="col-lg-2 col-sm-2 control-label">原始密码
							</label>
							<div class="col-lg-10">
								<input type="text" class="form-control" id="oldPassword"
									name="oldPwd" placeholder="请输入原始密码"
									onblur="changePasswordComp.check_oldPassword(this);">
								<p class="help-block" id="oldPassword_error">请输入正确的原始密码</p>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword1"
								class="col-lg-2 col-sm-2 control-label">新密码</label>
							<div class="col-lg-10">
								<input type="password" class="form-control" id="newPassword"
									name="newPwd" placeholder="请输入新密码"
									onblur="changePasswordComp.check_newPassword(this);">
								<p class="help-block" id="newPassword_error">请输入新密码</p>
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword1"
								class="col-lg-2 col-sm-2 control-label">确认新密码</label>
							<div class="col-lg-10">
								<input type="password" class="form-control" id="secondPassword"
									name="reNewPwd" placeholder="请再次输入新密码"
									onblur="changePasswordComp.check_secondPassword(this);">
								<p class="help-block" id="secondPassword_error">请再次输入新密码</p>
							</div>
						</div>
						<div class="form-group">
							<label for="exampleInputFile2"
								class="col-lg-2 col-sm-2 control-label">验证码</label>
							<div class="col-lg-4">
								<input type="text" class="form-control" id="ImgCode"
									name="imgVerifyCode"
									onblur="changePasswordComp.check_vailCode(this);" >
								<p class="help-block" id="vaildateCode_error">请输入正确的图片验证码</p>
							</div>
							<div class="col-lg-6">
								<a href="javascript:changePasswordComp.reImg();"><img src="<%=basePath%>/user/imgVerifyCodeAjax.action"
									id="Img"/></a>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-offset-2 col-lg-10">
								<button type="button" class="btn btn-primary" 
								onclick="changePasswordComp.sub_changePassword();" >确定</button>
							</div>
						</div>
					</form>
				</div>
			</section>

		</div>
	</div>

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
	<script src="js/changePwd.js"></script>
	<script type="text/javascript">
		var basePath = '<%=basePath%>';
	</script>
</body>
</html>