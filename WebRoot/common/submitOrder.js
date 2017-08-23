$(function() {
	offlineOrderSubmitComp.init()
});
var offlineOrderSubmitComp = {
	giftRate : null,
	rewardRate : null,
	rewardLimit : null,
	balance : 0,
	payType : 2,
	delayTime : 120,
	delayFlag : true,
	buyerRight : false,
	rewardRight : false,
	orderRight : false,
	init : function() {
		this.queryBalance();
		this.bindEvent();
		this.readParam()
	},
	queryBalance : function() {
		$.ajax({
			type: "POST",
			url : "/wechat/user/offLineOrderSubmit.action",
			data : {
				reqType : "getBalance"
			},
			success : function(e) {
				var a = $.parseJSON(e);
				if (a.resultCode == "0") {
					offlineOrderSubmitComp.balance = a.balance;
					$("#sellerbalance").addClass("jui_input_red").text(
							offlineOrderSubmitComp.balance.toFixed(2) + "元")
				}
			}
		})
	},
	bindEvent : function() {
		$("#orderId").blur(function() {
			offlineOrderSubmitComp.validOrderId()
		});
		$("#buyerAcct").blur(function() {
			offlineOrderSubmitComp.showBuyerAcctInfo()
		});
		$("#goodsType").change(function() {
			offlineOrderSubmitComp.loadSubTypes($(this).val())
		});
		$("#payType").change(function() {
			offlineOrderSubmitComp.changePayType(this.value)
		});
		$("#dealPrice").blur(offlineOrderSubmitComp.calcPriceJF);
		$("#serviceRate").change(function() {
			offlineOrderSubmitComp.calcPriceJF()
		});
		$("#sendMobileCode").on("click", offlineOrderSubmitComp.sendSMS);
		$("#btn_submit").on("click", offlineOrderSubmitComp.submitOrder)
	},
	validOrderId : function() {
		offlineOrderSubmitComp.orderRight = false;
		var a = $.trim($("#orderId").val());
		if (a.length == 0 || a.length != 7) {
			layer.tips("请正确输入订单号码!", "#orderId", {
				tips : 3
			});
			return
		}
		$.ajax({
			type: "POST",
			url : "/wechat/user/offLineOrderSubmit.action",
			data : {
				reqType : "validOrderId",
				indentNo : a
			},
			success : function(e) {
				var b = $.parseJSON(e);
				if (b.resultCode=="0") {
					if(b.logicCode != "0"){
						layer.tips(b.resultMsg, "#orderId", {
							tips : 3
						});
						offlineOrderSubmitComp.orderRight = false
					}else
					{
						offlineOrderSubmitComp.orderRight = true
					}
				} else {
					offlineOrderSubmitComp.orderRight = false
					layer.tips('系统繁忙，请稍候再试', "#orderId", {
						tips : 3
					});
				}
			}
		})
	},
	showBuyerAcctInfo : function() {
		offlineOrderSubmitComp.buyerRight = false;
		var a = $.trim($("#buyerAcct").val());
		if (a.length == 0) {
			layer.tips("消费者账号不能为空!", "#buyerAcct", {
				tips : 3
			});
			return
		}
		$.ajax({
			type: "POST",
			url : "/wechat/user/offLineOrderSubmit.action",
			data : {
				reqType : "checkBuyer",
				buyerAcct : a
			},
			success : function(e) {
				var b = $.parseJSON(e);
				if (b.resultCode == "0") {
					if (b.logicCode == "0") {
						offlineOrderSubmitComp.buyerRight = true;
						$("#buyerUserAcct").removeClass("jui_input_red")
								.addClass("jui_input_blue").val(
										b.username);
						$("#buyerName").removeClass("jui_input_red").addClass(
								"jui_input_blue").val(b.name)
					} else {
						offlineOrderSubmitComp.buyerRight = false;
						$("#buyerUserAcct").removeClass("jui_input_blue")
								.addClass("jui_input_red").val(
										b.resultMsg);
						$("#buyerName").removeClass("jui_input_blue").addClass(
								"jui_input_red").val(b.resultMsg)
					}
				} else {
					offlineOrderSubmitComp.buyerRight = false;
					layer.alert("系统忙，请稍后再试!")
				}
			}
		})
	},
	sendSMS : function() {
		if ($("#sendMobileCode").attr("disabled")
				|| !offlineOrderSubmitComp.delayFlag) {
			return
		}
		if (!offlineOrderSubmitComp.buyerRight) {
			layer.tips("请输入正确的消费者账号!", "#buyerAcct", {
				tips : 3
			});
			return
		}
		var b = $.trim($("#dealPrice").val());
		if (b.length == 0) {
			layer.tips("交易额不能为空!", "#dealPrice", {
				tips : 3
			});
			return
		}
		if (!/^[1-9]{1}([0-9])*(.[0-9]{1})?$/.test(b)) {
			layer.tips("交易额必须大于0并且只能有一位小数!", "#dealPrice", {
				tips : 3
			});
			return
		}
		var a = $.trim($("#buyerAcct").val());
		if (a.length == 0) {
			layer.tips("消费者账号不能为空!", "#buyerAcct", {
				tips : 3
			})
		} else {
			$("#sendMobileCode").attr("disabled", "disabled");
			$.syncReq({
				data : {
					reqUrl : "offLineOrderSubmit",
					reqType : "sendSMS",
					buyerAcct : a,
					dealPrice : b
				},
				success : function(c) {
					if (c.resultCode == "0") {
						if (c.logicCode == "0") {
							layer.alert("短信验证码已发送!", {
								icon : 1
							});
							$("#mobileBtn").html("120秒后重新获取");
							setTimeout(offlineOrderSubmitComp.countDown, 1000)
						} else {
							if (c.logicCode == "-2") {
								layer.alert("120秒内只能获取一次验证码!", {
									tips : 5
								});
								$("#sendMobileCode").removeAttr("disabled")
							} else {
								if (c.logicCode == "-3") {
									layer.alert("消费者账号不存在！", {
										tips : 5
									});
									$("#sendMobileCode").removeAttr("disabled")
								} else {
									layer.alert("短信发送失败!", {
										tips : 5
									});
									$("#sendMobileCode").removeAttr("disabled")
								}
							}
						}
					} else {
						layer.alert("短信发送失败!", {
							tips : 5
						});
						$("#sendMobileCode").removeAttr("disabled")
					}
				}
			})
		}
	},
	countDown : function() {
		offlineOrderSubmitComp.delayTime--;
		$("#mobileBtn").html(offlineOrderSubmitComp.delayTime + "秒后重新获取");
		if (offlineOrderSubmitComp.delayTime == 1) {
			offlineOrderSubmitComp.delayTime = 120;
			$("#mobileBtn").html("获取短信验证码");
			$("#sendMobileCode").removeAttr("disabled");
			offlineOrderSubmitComp.delayFlag = true
		} else {
			offlineOrderSubmitComp.delayFlag = false;
			setTimeout(offlineOrderSubmitComp.countDown, 1000)
		}
	},
	readParam : function() {
		this.giftRate = parseInt($("#giftRate").val());
		this.rewardRate = parseFloat($("#rewardRate").val());
		this.rewardLimit = parseInt($("#rewardLimit").val())
	},
	changePayType : function(a) {
		offlineOrderSubmitComp.payType = a;
		if (a == "1") {
			$(".buyerSMSCode_TR").show()
		} else {
			$(".buyerSMSCode_TR").hide()
		}
	},
	loadSubTypes : function(a) {
		$("#goodsSubType").html("<option value=''>---请选择---</option>");
		if (a.length > 0) {
			$.ajax({
				type: "POST",
				url : "/wechat/user/offLineOrderSubmit.action",
				data : {
					'commodityType.id' : a,
					reqType : "loadSubTypes"
				},
				success : function(e) {
					var b = $.parseJSON(e);
					if (b.resultCode == "0") {
						var c = "";
						$.each(b.resultObj, function() {
							c += "<option value='" + this.id + "'>"
									+ this.typeName + "</option>"
						});
						$("#goodsSubType").append(c)
					}
				}
			})
		}
	},
	calcPriceJF : function() {
		if ($.trim($("#dealPrice").val()).length > 0
				&& /^[1-9]{1}([0-9])*(.[0-9]{1})?$/.test($("#dealPrice").val())) {
			var d = parseFloat($("#dealPrice").val());
			var c = parseInt($("#serviceRate").val());
			var b = (d * c / 100).toFixed(2);
			var a = Math.round(b * offlineOrderSubmitComp.giftRate);
			if (a >= offlineOrderSubmitComp.rewardLimit) {
				$("#rewardJF").val(
						Math.round(a * offlineOrderSubmitComp.rewardRate));
				$(".rewardInfo_TR").show();
			} else {
				$(".rewardInfo_TR").hide();
			}
			$("#servicePrice").val(b);
			$("#giftJF").val(a)
		}
	},
	validateForm : function() {
		if ($.trim($("#orderId").val()).length == 0
				|| !offlineOrderSubmitComp.orderRight) {
			layer.tips("请正确输入订单号码!", "#orderId", {
				tips : 3
			});
			return false
		}
		if ($.trim($("#buyerAcct").val()).length == 0) {
			layer.tips("消费者账号不能为空!", "#buyerAcct", {
				tips : 3
			});
			return false
		}
		if (!offlineOrderSubmitComp.buyerRight) {
			layer.tips("请输入正确的消费者账号!", "#buyerAcct", {
				tips : 3
			});
			return false
		}
		if ($.trim($("#goodsType").val()).length == 0) {
			layer.tips("请选择商品大类!", "#goodsType", {
				tips : 3
			});
			return false
		}
		if ($.trim($("#goodsSubType").val()).length == 0) {
			layer.tips("请选择商品小类!", "#goodsSubType", {
				tips : 3
			});
			return false
		}
		if ($.trim($("#goodsName").val()).length == 0) {
			layer.tips("商品名称不能为空!", "#goodsName", {
				tips : 3
			});
			return false
		}
		if ($.trim($("#goodsName").val()).length > 50) {
			layer.tips("商品名称最长50个字符!", "#goodsName", {
				tips : 3
			});
			return false
		}
		if ($.trim($("#dealPrice").val()).length == 0) {
			layer.tips("交易额不能为空!", "#dealPrice", {
				tips : 3
			});
			return false
		}
		if (!/^[1-9]{1}([0-9])*(.[0-9]{1})?$/.test($("#dealPrice").val())) {
			layer.tips("交易额必须大于0并且只能有一位小数!", "#dealPrice", {
				tips : 3
			});
			return false
		}
		if (offlineOrderSubmitComp.payType == "1"
				&& $.trim($("#buyerSMSCode").val()).length == 0) {
			layer.tips("消费者短信验证码不能为空!", "#buyerSMSCode", {
				tips : 3
			});
			return false
		}
		return true
	},
	submitOrder : function() {
		if (offlineOrderSubmitComp.validateForm()) {
			layer.confirm(
							"确定提交?",
							{
								btn : [ "确定", "取消" ]
							},
							function() {
								$(".layui-layer-btn0").off("click").text("提交中");
								$("#btn_submit").off("click");
								$
										.ajax({
											type: "POST",
											url : "/wechat/user/offLineOrderSubmit.action",
											data : {
												indentNo : $.trim($("#orderId")
														.val()),
												buyerAcct : $.trim($(
														"#buyerAcct").val()),
												payType : offlineOrderSubmitComp.payType,
												'commodityType.id' : $.trim($(
														"#goodsType").val()),
												subType : $.trim($(
														"#goodsSubType").val()),
												commodityName : $.trim($(
														"#goodsName").val()),
												amount : $.trim($(
														"#dealPrice").val()),
												premiumRates : $.trim($(
														"#serviceRate").val()),
												giftJf:  $.trim($(
														"#giftJF").val()),
												rewardJf:  $.trim($(
														"#rewardJF").val()),
												buyerSMSCode : $.trim($(
														"#buyerSMSCode").val()),
												reqType : "submit"
											},
											success : function(e) {
												var a = $.parseJSON(e);
												if (a.resultCode == "0") {
													if (a.logicCode == "-1") {
														layer.alert(
																"报单失败，请稍后再试!",
																{
																	icon : 5
																});
														$("#btn_submit")
																.val("提交报单")
																.on(
																		"click",
																		offlineOrderSubmitComp.submitOrder)
													} else {
														if (a.logicCode == "-2") {
															layer
																	.alert(
																			"报单失败，错误信息："
																					+ a.resultMsg,
																			{
																				icon : 5
																			});
															$("#btn_submit")
																	.val("提交报单")
																	.on(
																			"click",
																			offlineOrderSubmitComp.submitOrder)
														} else {
															if (a.logicCode == "-3") {
																layer
																		.alert(
																				"报单失败，错误信息："
																						+ a.resultMsg,
																				{
																					icon : 5
																				});
																$("#btn_submit")
																		.val(
																				"提交报单")
																		.on(
																				"click",
																				offlineOrderSubmitComp.submitOrder)
															} else {
																layer
																		.alert(
																				"报单成功!",
																				{
																					icon : 1
																				},
																				function() {
																					window.location.href = "/wechat/user/userCenter.action"
																				})
															}
														}
													}
												} else {
													layer.alert(
															"报单失败:系统忙，请稍后再试!",
															{
																icon : 5
															});
													$("#btn_submit")
															.val("提交报单")
															.on(
																	"click",
																	offlineOrderSubmitComp.submitOrder)
												}
											}
										})
							})
		}
	}
};