$(function() {
	rechargeComp.init()
});
var rechargeComp = {
	init : function() {
		this.bindEvent()
	},
	bindEvent : function() {
		$("#rechargeBtn").on("click", rechargeComp.rechargeSubmit);
		$("form").on("submit", rechargeComp.rechargeSubmit)
	},
	preSubmit : function() {
		var a = $("#rechargeMoney").val();
		if (!/^[1-9]{1}([0-9])*(.[0-9]{1,2})?$/.test(a)) {
			layer.tips("请输入正确的充值金额!", "#rechargeMoney", {
				tips : 3
			});
			return false
		}
		return true
	},
	rechargeSubmit : function() {
		if (rechargeComp.preSubmit()) {
			document.forms[0].submit()
		} else {
			return false
		}
	}
};