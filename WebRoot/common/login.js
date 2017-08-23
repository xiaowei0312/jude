$(function() {
	loginComp.init()
});
var loginComp = {
	init : function() {
		$("#loginBtn").on("click", function() {
			loginComp.loginSubmit()
		});
	},
	validateLogin : function(b, e, /*d,*/a) {
		if (b == null || $.trim(b).length == 0) {
			layer.alert("请输入用户名!", {
				icon : 5
			});
			return
		}
		if (e == null || $.trim(e).length == 0) {
			layer.alert("请输入登录密码!", {
				icon : 5
			});
			return
		}
//		if (d == null || $.trim(d).length != 4) {
//			layer.alert("请填写图形验证码!", {
//				icon : 5
//			});
//			return
//		}
		a()
	},
	loginSubmit : function() {
		var a = $("#username").val();
		var d = $("#password").val();
		//var c = ${"#verifyCode"}.val();
		loginComp.validateLogin(a, d, /*c,*/function() {
			$.ajax({
				type: "POST",
				url :  baseUrl+"/user/loginSubmitAjax.action",
				data : {
					username : a,
					password : d,
					//verifyCode : c,
				},
				success : function(e) {
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
						return;
					}
					window.location.href = f.url;
				}
			});
		})
	}
};