$(function() {
	inviterListComp.init()
});
var inviterListComp = {
	basePath : null,
	listHtml : null,
	pageNo : 1,
	pageSize : 10,
	pageCount : 0,
	init : function() {
		this.basePath = basePath;
		this.listHtml = '{for obj in objList}<tr><td>${obj.orderNo}</td>'
				+ '<td>${obj.user.phone}</td><td>${obj.totalAmount}</td>'
				+ '<td>${obj.journalTime}</td>'
				+ '<td>${obj.recvCommodityAddress.contacts}</td><td>${obj.recvCommodityAddress.phone}</td>'
				+ '<td>${obj.recvCommodityAddress.province} ${obj.recvCommodityAddress.city} '
				+ '${obj.recvCommodityAddress.area} ${obj.recvCommodityAddress.extra}</td>'
				+ '<td>${obj.status}</td>' + '</tr>{/for}';
		this.loadInviterList();
		this.bindEvent();
	},

	bindEvent : function() {
		$('#searchEndDate').on("blur", this.searchEndDateBlur);
		$('#sendPushMsgFlag').on("change", this.sendPushMsgFlagChange);
		$('#inviterRewardFlag').on("change", this.inviterRewardFlagChange);
		$('#addTimeOrderBy').on("change", this.addTimeOrderByChange);
		$('#searchBtn').on("click", this.searchBtnClicked);
	},

	searchEndDateBlur : function() {
		var startDate = $('#searchStartDate').val();
		var endDate = $('#searchEndDate').val();
		if (startDate == "" || endDate == "") {
			return;
		}
		var arr = startDate.split('/');
		var startDateObj = new Date(arr[2], arr[0], arr[1]);
		arr = endDate.split('/');
		var endDateObj = new Date(arr[2], arr[0], arr[1]);
		if (startDateObj.getTime() > endDateObj.getTime()) {
			alert('结束日期不能早于起始日期');
			$('#searchEndDate').val("");
		}
	},
	sendPushMsgFlagChange : function() {
		inviterListComp.pageNo = 1;
		inviterListComp.loadInviterList();
	},
	inviterRewardFlagChange : function() {
		inviterListComp.pageNo = 1;
		inviterListComp.loadInviterList();
	},
	addTimeOrderByChange : function() {
		inviterListComp.pageNo = 1;
		inviterListComp.loadInviterList();
	},
	searchBtnClicked : function() {
		inviterListComp.pageNo = 1;
		$('#sendPushMsgFlag').val("-2");
		$('#inviterRewardFlag').val("-2");
		$('#addTimeOrderBy').val("-2");
		inviterListComp.loadInviterList();
	},
	loadInviterList : function() {

		$
				.ajax({
					type : "POST",
					url : inviterListComp.basePath
							+ "/inviter/getInviterListAjax.action",
					data : {
						pageNo : inviterListComp.pageNo,
						pageSize : inviterListComp.pageSize,
						searchKey : $('#searchKey').val(),
						searchStartDate : $('#searchStartDate').val(),
						searchEndDate : $('#searchEndDate').val(),
						sendPushMsgFlag : $('#sendPushMsgFlag').val(),
						inviterRewardFlag : $('#inviterRewardFlag').val(),
						addTimeOrderBy : $('#addTimeOrderBy').val()
					},
					success : function(e) {
						var a = $.parseJSON(e);
						var c = a.resultObj;
						if (c && c.objList && c.objList.length > 0) {
							// var b = inviterListComp.listHtml.process(c);
							var b = "";
							var status = "";
							$(c.objList)
									.each(
											function(i, obj) {
												b += '<tr class="orderTr"><td>'
														+ obj.inviterUser.phone
														+ '</td>'
														+ '<td>'
														+ obj.user.phone
														+ '</td>'
														+ '<td>'
														+ ((obj.sendPushMsgFlag == 0) ? '未登录' : '已登录')
														+ '</td>'
														+ '<td>'
														+ ((obj.inviterRewardFlag == 0) ? '未产生' : '已产生')
														+ '</td>'
														+ '<td>'
														+ obj.addTime
														+ '</td>'
														+ '</tr>';
											});
							$("#commodityList").html(b);
							inviterListComp.buildPager(c.totalCount,
									inviterListComp.pageNo,
									inviterListComp.pageSize);
							gotoTop()
						} else {
							$("#commodityList")
									.html(
											"<tr><td colspan='5' style='text-align:center'>没有找到相关记录!</td></tr>");
							$(".pages").hide();
							$(".pager_div").hide();
							inviterListComp.isTdAdded = false;
						}
					}
				})
	},
	buildPager : function(c, b, a) {
		var d = c / a;
		var e = (c % a == 0) ? (d) : (parseInt(d) + 1);
		inviterListComp.pageCount = e;
		$("#pager").pager({
			pagenumber : b,
			pagecount : e,
			totalRecords : c,
			buttonClickCallback : inviterListComp.pageClick
		});
		$(".pages").show()
		$(".pager_div").show();
	},
	pageClick : function(a) {
		inviterListComp.pageNo = a;
		inviterListComp.loadInviterList()
	},
	goToPage : function() {
		var a = "";
		var c = "^[0-9]*$";
		var b = new RegExp(c);
		a = $.trim($("#numberOfPages").val());
		if (a == "" || !a.match(b)) {
			alert("页数不能为空，且必须是数字，请重新输入。")
		} else {
			if (parseInt(a) <= 0) {
				alert("页数不能小于零，请重新输入。")
			} else {
				if (parseInt(a) > inviterListComp.pageCount) {
					alert("页数不能大于总页数，请重新输入。")
				} else {
					inviterListComp.pageNo = a;
					inviterListComp.loadInviterList()
				}
			}
		}
	}
};