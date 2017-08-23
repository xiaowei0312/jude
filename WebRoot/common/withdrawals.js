$(function() {
	withdrawalsComp.init()
});
var withdrawalsComp = {
	balance : 0,
	txMoney : 0,
	txCredit : 0,
	serviceMoney : 0,
	init : function() {
		this.balance = parseFloat($(".canTxMoney").text());
		this.txCredit = parseFloat($(".credit").text());
		this.bindEvent()
	},
	bindEvent : function() {
		$("#txMoney").blur(withdrawalsComp.validAndCalcMoney);
		$("#rechargeBtn").on("click", withdrawalsComp.submit)
	},
	validAndCalcMoney : function() {
		var a = $("#txMoney").val();
		if (a.length == 0) {
			layer.tips("请输入提现金额!", "#txMoney", {
				tips : 3
			});
			return false
		}
		if (!/^\d*$/.test(a)) {
			layer.tips("金额错误，必须为整数!", "#txMoney", {
				tips : 3
			});
			return false
		}
		if (a > withdrawalsComp.balance) {
			layer.tips("您可提现余额不足，请重新输入!", "#txMoney", {
				tips : 3
			});
			return false
		}
		if (a < 100) {
			layer.tips("提现金额必须大于等于100元!", "#txMoney", {
				tips : 3
			});
			return false
		}
		if (a % 100 > 0) {
			layer.tips("提现金额必须为100的整数倍!", "#txMoney", {
				tips : 3
			});
			return false
		}
		if (a > withdrawalsComp.txCredit) {
			layer.tips("您信用额度不足，无法完成本次提现!", "#txMoney", {
				tips : 3
			});
			return false
		}
		withdrawalsComp.txMoney = a;
		withdrawalsComp.serviceMoney = a <= 1000 ? 2
				: (a > 1000 && a <= 10000) ? a * 0.005 : a * 0.003;
		$("#serviceMoney").val(withdrawalsComp.serviceMoney);
		$("#actualMoney").val(a - withdrawalsComp.serviceMoney);
		return true
	},
	submit : function() {
		if (withdrawalsComp.validAndCalcMoney()) {
			$("#rechargeBtn").off("click");
			layer.confirm("您申请的提现信息如下：<br/>提现金额：" + withdrawalsComp.txMoney
					+ "元<br/>&#12288;手续费：" + withdrawalsComp.serviceMoney
					+ "元<br/>实际到帐："
					+ (withdrawalsComp.txMoney - withdrawalsComp.serviceMoney)
					+ "元<br/>确定提现吗？", {
				btn : [ "提交", "取消" ]
			}, function() {
				$.ajax({
					type: "POST",
					url :  "/wechat/user/withdrawsSubmit.action",
					data : {
						txMoney : withdrawalsComp.txMoney
					},
					success : function(e) {
						var a = $.parseJSON(e);
						if (a.resultCode == "0") {
							if (a.logicCode == "0") {
								layer.alert("提现申请成功!", {
									icon : 6
								}, function() {
									window.location.reload(true)
								})
							} else {
								layer.msg("提现失败：" + a.resultMsg, {
									icon : 5
								});
								$("#rechargeBtn").on("click",
										withdrawalsComp.submit)
							}
						} else {
							layer.alert("系统忙，请稍后再试!", {
								icon : 5
							})
						}
					}
				})
			}, function() {
				$("#rechargeBtn").on("click", withdrawalsComp.submit)
			})
		}
	}
};