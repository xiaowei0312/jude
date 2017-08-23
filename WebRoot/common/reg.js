$(function() {
	regComp.init()
});
var regComp = {
	oldPhone : null,
	oldAcct : null,
	acctExists : false,
	phoneExists : false,
	delayFlag : true,
	delayTime : 120,
	width : 0,
	height : 0,
	init : function() {
		this.width = parseInt(document.documentElement.clientWidth) - 40 + "px";
		this.height = parseInt(document.documentElement.clientHeight) - 120
				+ "px";
		this.bindSelectEvent();
		$("#verifyImg").click(function() {
			$(this).attr("src", "/wechat/user/imageVerifyCode.action?t=" + new Date().getTime())
		});
		$("#smsBtn").on("click", this.sendSMS);
		$("#reg_btn").on("click", this.reg);
		$(".readme_yhxy").on("click", function() {
			$.syncReq({
				data : {
					reqUrl : "register",
					reqType : "queryYHXY",
				},
				success : function(a) {
					layer.open({
						type : 1,
						title : "用户协议",
						skin : "layui-layer-rim",
						area : [ regComp.width, regComp.height ],
						content : a.resultObj[0].HELP_CONTENT
					})
				}
			})
		});
		$(".readme_zsgz").on("click", function() {
			$.syncReq({
				data : {
					reqUrl : "register",
					reqType : "queryZSGZ",
				},
				success : function(a) {
					layer.open({
						type : 1,
						title : "赠送规则",
						skin : "layui-layer-rim",
						area : [ regComp.width, regComp.height ],
						content : a.resultObj[0].HELP_CONTENT
					})
				}
			})
		})
	},
	bindSelectEvent : function() {
		$("#provinceId").on("change", this.loadEparchys);
		$("#eparchyId").on("change", this.loadCitys);
		$("#cityId").on("change", this.changeCity)
	},
	bindValidEvent : function() {
		this.regValid("#userAcct", this.regUserAcct);
		this.regValid("#userPhone", this.regUserPhone);
		this.regValid("#password", this.regPassword);
		this.regValid("#repassword", this.regRepassword);
		this.regValid("#realName", this.regRealName)
	},
	loadEparchys : function() {
		regComp.clearSelect("#eparchyId");
		regComp.clearSelect("#cityId");
		var a = $(this).val();
		if ($.trim(a).length == 0) {
			return
		}
		$.ajax({
			type: "POST",
			url :  "/wechat/user/registerValidate.action",
			data : {
				reqType : "loadEparchy",
				addrId : a
			},
			success : function(b) {
				var c = $.parseJSON(b);
				if (c) {
					regComp.loadSelectOptions("#eparchyId", c)
				}
			}
		})
	},
	loadCitys : function() {
		regComp.clearSelect("#cityId");
		var a = $(this).val();
		if ($.trim(a).length == 0) {
			return
		}
		$.ajax({
			type: "POST",
			url :  "/wechat/user/registerValidate.action",
			data : {
				reqType : "loadCity",
				addrId : a
			},
			success : function(b) {
				var c = $.parseJSON(b);
				if (c) {
					regComp.loadSelectOptions("#cityId", c)
				}
			}
		})
	},
	clearSelect : function(a) {
		$(a).html("<option value=''>--请选择--</option>")
	},
	loadSelectOptions : function(a, c) {
		var b = "";
		$.each(c, function() {
			b += "<option value='" + this.id + "'>" + this.name
					+ "</option>"
		});
		$(a).append(b)
	},
	regUserAcct : function(b) {
		var a = $("#userAcct").val();
		if ($.trim(a).length == 0) {
			layer.alert("会员名不能为空!", {
				icon : 5
			});
			return
		}
		if (/^\d*$/.test(a)) {
			layer.alert("会员名不能使用纯数字!", {
				icon : 5
			});
			return
		}
		if (!/^[a-zA-z]{1}[A-Za-z0-9_]{5,15}$/.test(a)) {
			layer.alert("用户名必须以字母开头并且长度在6-16位之间!", {
				icon : 5
			});
			return
		}
		if (regComp.acctExists && a == regComp.oldAcct) {
			layer.alert("会员号已被注册!", {
				icon : 5
			});
			return
		}
		if (a != regComp.oldAcct) {
			$.ajax({
				type: "POST",
				url :  "/wechat/user/registerValidate.action",
				data : {
					reqType : "validAcct",
					username : a
				},
				success : function(e) {
					regComp.oldAcct = a;
					var c = $.parseJSON(e);
					if (c.resultCode == "0") {
						if (c.logicCode != "0") {
							regComp.acctExists = true;
							$("#userAcct").attr("state", "1");
							layer.alert(c.resultMsg, {
								icon : 5
							})
						} else {
							regComp.acctExists = false;
							$("#userAcct").attr("state", "0");
							b()
						}
					} else {
						layer.alert("系统忙,请稍后再试!", {
							icon : 5
						})
					}
				}
			})
		} else {
			b()
		}
	},
	regUserPhone : function(b) {
		var a = $("#userPhone").val();
		if ($.trim(a).length == 0) {
			layer.alert("手机号码不能为空!", {
				icon : 5
			});
			return
		}
		if (!/^0?(13|15|18|14|17)[0-9]{9}$/.test(a)) {
			layer.alert("请输入正确的手机号码!", {
				icon : 5
			});
			return
		}
		if (regComp.phoneExists && a == regComp.oldPhone) {
			layer.alert("手机号已被注册!", {
				icon : 5
			});
			return
		}
		if (a != regComp.oldPhone) {
			$.ajax({
				type: "POST",
				url :  "/wechat/user/registerValidate.action",
				data : {
					reqType : "validPhone",
					phone : a
				},
				success : function(e) {
					var c = $.parseJSON(e);
					if (c.resultCode == "0") {
						regComp.oldPhone = a;
						if (c.logicCode != "0") {
							regComp.phoneExists = true;
							$("#userPhone").attr("state", "1");
							layer.alert(c.resultMsg, {
								icon : 5
							})
						} else {
							regComp.phoneExists = false;
							$("#userPhone").attr("state", "0");
							b()
						}
					} else {
						layer.alert("系统忙,请稍后再试!", {
							icon : 5
						})
					}
				}
			})
		} else {
			b()
		}
	},
	regPassword : function(b) {
		var a = $("#password").val();
		if ($.trim(a).length == 0) {
			layer.alert("密码不能为空!", {
				icon : 5
			});
			return
		}
		if (!/^((?=.*[a-zA-Z])(?=.*[\d])|(?=.*[a-zA-Z])(?=.*[\W])|(?=.*[\d])(?=.*[\W]))[a-zA-Z\d\W]{6,16}$/
				.test(a)) {
			layer.alert("密码需使用字母、数字或特殊符号组合6-16位，不能使用纯数字、纯字母、纯符号", {
				icon : 5
			});
			return
		}
		b()
	},
	regRepassword : function(b) {
		var a = $("#repassword").val();
		if ($.trim(a).length == 0) {
			layer.alert("确认密码不能为空!", {
				icon : 5
			});
			return
		}
		if (a != $("#password").val()) {
			layer.alert("两次密码不一致!", {
				icon : 5
			});
			return
		}
		b()
	},
	validImgCode : function() {
		var a = $.trim($("#verifyCode").val()).length > 0;
		if (!a) {
			layer.alert("请填写图形验证码!", {
				icon : 5
			})
		}
		return a
	},
	validSMSCode : function() {
		var a = $.trim($("#smsCode").val()).length > 0;
		if (!a) {
			layer.alert("请填写短信验证码!", {
				icon : 5
			})
		}
		return a
	},
	sendSMS : function() {
		if ($("#smsBtn").attr("disabled") || !regComp.delayFlag) {
			return
		}
		regComp.regUserPhone(function() {
			if ($("#userPhone").attr("state") == "1") {
				return
			}
			if (regComp.validImgCode()) {
				$.ajax({
					type: "POST",
					url :  "/wechat/user/registerValidate.action",
					data : {
						reqType : "validMsg",
						phone : $("#userPhone").val(),
						verifyCode : $("#verifyCode").val()
					},
					success : function(e) {
						var a = $.parseJSON(e);
						if (a.resultCode == "0") {
							if (a.logicCode == "0") {
								layer.alert("短信验证码已发送!", {
									icon : 1
								});
								$("#smsBtn_text").html("120秒后重新获取");
								setTimeout(regComp.countDown, 1000)
							} else {
								if (a.logicCode == "-1" || a.logicCode == "-2"
										|| a.logicCode == "-3") {
									layer.alert(a.resultMsg, {
										icon : 5
									})
								} else {
									if (a.logicCode == "-4") {
										layer.alert("120秒内只能获取一次验证码!", {
											icon : 5
										})
									} else {
										layer.alert("短信发送失败!", {
											icon : 5
										})
									}
								}
							}
						} else {
							layer.alert("短信发送失败!", {
								icon : 5
							})
						}
					}
				})
			}
		})
	},
	countDown : function() {
		regComp.delayTime--;
		$("#smsBtn").attr("disabled", "disabled");
		$("#smsBtn_text").html(regComp.delayTime + "秒后重新获取");
		if (regComp.delayTime == 1) {
			regComp.delayTime = 120;
			$("#smsBtn_text").html("获取短信验证码");
			$("#smsBtn").removeAttr("disabled");
			regComp.delayFlag = true
		} else {
			regComp.delayFlag = false;
			setTimeout(regComp.countDown, 1000)
		}
	},
	reg : function() {
		regComp.regUserAcct(function() {
			regComp.regPassword(function() {
				regComp.regRepassword(function() {
					regComp.regUserPhone(function() {
						var a = true;
						if ($("#cityId").val() == "") {
							a = false;
							layer.alert("请选择常住地区!", {
								icon : 5
							})
							return;
						}
						if (!regComp.validSMSCode()|| !regComp.validImgCode()) {
							a = false
							return;
						}
						if ($("#readme:checked").length == 0) {
							layer.alert("请勾选注册协议!",{
								icon : 5
							});
							return
						}
						$("#reg_btn").off("click").addClass("disable_btn").text("提交中，请稍后");
						$.ajax({
							type: "POST",
							url :  "/wechat/user/regist.action",
							data : $("#personRegForm").serialize(),
							success : function(e) {
								var b = $.parseJSON(e);
								if (b.resultCode == "0") {
									if (b.logicCode == "0") {
										layer.alert("恭喜您，注册成功，点确定跳转!",{	icon : 1},function() {
											window.location.href="/wechat/user/userCenter.action";	
										})
									} else {
										$("#reg_btn").on("click",regComp.reg).removeClass("disable_btn").text("立即注册");
										layer.alert(b.resultMsg,{icon : 5})
									}
								} else {
									$("#reg_btn").on("click",regComp.reg).removeClass("disable_btn").text("立即注册");
									layer.alert("系统忙，请稍后再试!",{icon : 5})
								}
							}
						})
					})
				})
			})
		})
	}
};