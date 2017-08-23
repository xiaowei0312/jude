<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<meta name="description" content="">
<meta name="author" content="ThemeBucket">
<link rel="shortcut icon" href="#" type="${pageContext.request.contextPath }/image/png">
<link href="${pageContext.request.contextPath }/css/style.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/css/style-responsive.css"
	rel="stylesheet">
<link rel="stylesheet" href="${pageContext.request.contextPath }/common/layer.css"
	id="layui_layer_skinlayercss">
<title>Login</title>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="${pageContext.request.contextPath }/js/html5shiv.js"></script>
    <script src="${pageContext.request.contextPath }/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-body">
	<script type="text/javascript">
		var baseUrl = '${pageContext.request.contextPath}';
	</script>

	<div class="container">
		<form class="form-signin" id="loginform">
			<div class="form-signin-heading text-center">
				<h1 class="sign-title">Sign In</h1>
				<img src="${pageContext.request.contextPath }/images/login-logo.png"
					alt="" />
			</div>
			<div class="login-wrap">
				<input type="text" class="form-control" id="username"
					placeholder="User ID" autofocus> <input type="password"
					class="form-control" id="password" placeholder="Password">

				<%-- <input type="text" class="form-control" id="verifyCode" placeholder="ImgVerifyCode">
			<img alt="ImgVerifyCode" src="${pageContext.request.contextPath }/user/imageVerifyCode.action">
 --%>
				<button class="btn btn-lg btn-login btn-block" id="loginBtn" type="button">
					<i class="fa fa-check"></i>
				</button>

				<div class="registration">
					Not a member yet? <a class="" href="registration.html"> Signup
					</a>
				</div>
				<label class="checkbox"> <input type="checkbox"
					value="remember-me"> Remember me <span class="pull-right">
						<a data-toggle="modal" href="#myModal"> Forgot Password?</a>
				</span>
				</label>

			</div>

			<!-- Modal -->
			<div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog"
				tabindex="-1" id="myModal" class="modal fade">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title">Forgot Password ?</h4>
						</div>
						<div class="modal-body">
							<p>Enter your e-mail address below to reset your password.</p>
							<input type="text" name="email" placeholder="Email"
								autocomplete="off" class="form-control placeholder-no-fix">

						</div>
						<div class="modal-footer">
							<button data-dismiss="modal" class="btn btn-default"
								type="button">Cancel</button>
							<button class="btn btn-primary" type="button">Submit</button>
						</div>
					</div>
				</div>
			</div>
			<!-- modal -->

		</form>

	</div>

	<!-- Placed js at the end of the document so the pages load faster -->

	<!-- Placed js at the end of the document so the pages load faster -->
	<script
		src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/modernizr.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/layer.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/template.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/base.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/common/login.js"></script>
</body>
</html>
