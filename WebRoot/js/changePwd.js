$(function() {
	changePasswordComp.init();
});
var changePasswordComp = {
	basePath : null,
	init : function() {
		this.basePath = basePath;
	},
	check_oldPassword : function(b) {
		var a = $.trim(b.value);
		if (a.length == 0) {
			$("#oldPassword_error").text("原始密码不能为空！！")
		} else {
			$.ajax({
				type : "POST",
				url : this.basePath + "/user/changePwdValidate1.action",
				data : {
					oldPwd : a,
				},
				success : function(e) {
					var c = $.parseJSON(e);
					if (c.logicCode == "0") {
						$("#oldPassword_error").text("")
					} else {
						$("#oldPassword_error").text("原始密码填写错误！")
					}
				}
			})
		}
	},
	check_newPassword : function(d) {
		var b = $.trim(d.value);
		var c = $.trim($("#secondPassword").val());
		if (c.length == 0) {
			if (b.length == 0) {
				$("#newPassword_error").text("新密码不能为空！！")
			} else {
				var a = /^((?=.*[a-zA-Z])(?=.*[\d])|(?=.*[a-zA-Z])(?=.*[\W])|(?=.*[\d])(?=.*[\W]))[a-zA-Z\d\W]{6,16}$/;
				if (!a.test(b)) {
					$("#newPassword_error").text(
							"密码必须符合6-16个字符，需使用字母、数字或特殊符号组合")
				} else {
					$("#newPassword_error").text("")
				}
			}
		} else {
			if (b.length == 0) {
				$("#newPassword_error").text("新密码不能为空！！")
			} else {
				if (c != b) {
					$("#secondPassword_error").text("两次密码不一致，请重新填写!")
				} else {
					var a = /^((?=.*[a-zA-Z])(?=.*[\d])|(?=.*[a-zA-Z])(?=.*[\W])|(?=.*[\d])(?=.*[\W]))[a-zA-Z\d\W]{6,16}$/;
					if (!a.test(b)) {
						$("#newPassword_error").text(
								"密码必须符合6-16个字符，需使用字母、数字或特殊符号组合")
					} else {
						$("#newPassword_error").text("")
					}
				}
			}
		}
	},
	check_secondPassword : function(c) {
		var b = $.trim(c.value);
		var a = $.trim($("#newPassword").val());
		if (a.length == 0) {
			$("#newPassword_error").text("请输入新的密码！")
		} else {
			if (b.length == 0) {
				$("#secondPassword_error").text("请再次输入新的密码！")
			} else {
				if (b == a) {
					$("#secondPassword_error").text("")
				} else {
					$("#secondPassword_error").text("两次密码不一致，请重新填写!")
				}
			}
		}
	},
	reImg : function() {
		var a = document.getElementById("Img");
		a.src = this.basePath + "/user/imgVerifyCodeAjax.action?rnd=" + Math.random();
	},
	check_vailCode : function(b) {
		var a = $.trim(b.value);
		if (a.length == 0) {
			$("#vaildateCode_error").text("验证码不能为空！！")
		} else {
			$("#vaildateCode_error").text("")
		}
	},
	sub_changePassword : function() {
		var c = $.trim($("#oldPassword").val());
		var f = $.trim($("#newPassword").val());
		var e = $.trim($("#secondPassword").val());
		var b = $.trim($("#ImgCode").val());
		var d = $.trim($("#oldPassword_error").text());
		if (c.length == 0) {
			$("#oldPassword_error").text("原始密码不能为空！！")
		} else {
			if (d.length > 0) {
				$("#oldPassword_error").text("与原始密码不匹配！")
			} else {
				if (f.length == 0) {
					$("#newPassword_error").text("请输入新的密码！")
				} else {
					if (e.length == 0) {
						$("#secondPassword_error").text("请再次输入新的密码！")
					} else {
						if (b.length == 0) {
							$("#vaildateCode_error").text("请输入验证码！")
						} else {
							if (e != f) {
								$("#secondPassword_error").text(
										"两次密码不一致，请重新填写!")
							} else {
								var a = confirm("确定要修改吗？");
								if (a) {
									$
											.ajax({
												type : "POST",
												url : this.basePath
														+ "/user/changePwdValidate2.action",
												data : {
													oldPwd : c,
													newPwd : f,
													imgVerifyCode : b,
												},
												success : function(e) {
													var g = $.parseJSON(e);
													console.log(g);
													if (g.logicCode == "0") {
														alert("修改成功！");
														window.location.href = g.url;
													} else {
														alert(g.resultMsg)
													}
												}
											})
								}
							}
						}
					}
				}
			}
		}
	}
};