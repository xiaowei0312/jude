$(function() {
	orderListComp.init()
});
var orderListComp = {
	basePath: null,
	pageNo: 1,
	pageSize: 5,
	pageCount: 0,
	init: function() {
		this.basePath = basePath;
		this.loadShopList();
		this.bindEvent();
	},
	bindEvent: function(){
		$('#searchEndDate').on("blur",this.searchEndDateBlur);
		$('#orderStatusSelect').on("change",this.orderStatusSelectChange);
		$('#orderAmountOrderBy').on("change",this.orderAmountOrderByChange);
		$('#orderTimeOrderBy').on("change",this.orderTimeOrderByChange);
		$('#searchBtn').on("click",this.searchBtnClicked);
	},
	
	searchEndDateBlur:function(){
		var startDate = $('#searchStartDate').val();
		var endDate = $('#searchEndDate').val();
		if(startDate == "" || endDate == ""){
			return;
		}
		var arr = startDate.split('/');
		var startDateObj = new Date(arr[2],arr[0],arr[1]);
		arr = endDate.split('/');
		var endDateObj = new Date(arr[2],arr[0],arr[1]);
		if(startDateObj.getTime() > endDateObj.getTime()){
			alert('结束日期不能早于起始日期');
			$('#searchEndDate').val("");
		}
	},
	orderStatusSelectChange:function(){
		orderListComp.pageNo = 1;
		orderListComp.loadShopList();
	},
	orderAmountOrderByChange:function(){
		orderListComp.pageNo = 1;
		orderListComp.loadShopList();
	},
	orderTimeOrderByChange:function(){
		orderListComp.pageNo = 1;
		orderListComp.loadShopList();
	},
	searchBtnClicked:function(){
		orderListComp.pageNo = 1;
		$('#orderStatusSelect').val("-2");
		$('#orderAmountOrderBy').val("0");
		$('#orderTimeOrderBy').val("0");
		orderListComp.loadShopList();
	},
	
	loadShopList: function() {
		$.ajax({
			type: "POST",
			url : orderListComp.basePath + "/offlineOrder/getOfflineOrderListAjax.action",
			data: {
				pageNo: orderListComp.pageNo,
				pageSize: orderListComp.pageSize,
				searchKey : $('#searchKey').val(),
				searchStartDate : $('#searchStartDate').val(),
				searchEndDate : $('#searchEndDate').val(),
				compareOp : $('#compareOp').val(),
				compareAmount : $('#compareAmount').val(),
				orderStatusSelect : $('#orderStatusSelect').val(),
				orderAmountOrderBy : $('#orderAmountOrderBy').val(),
				orderTimeOrderBy : $('#orderTimeOrderBy').val()
			},
			success: function(e) {
				var a = $.parseJSON(e);
				var c = a.resultObj;
				if (c && c.objList && c.objList.length > 0) {
					//var b = orderListComp.listHtml.process(c);
					var b = "";
					var status = "";
					var statusClass = "";
					$(c.objList).each(function(i,obj){
						switch(obj.flag){
						case 0:
							status = '待审核';
							statusClass = 'center hidden-phone text-danger';
							break;
						case 1:
							status = '已同意';
							statusClass = 'center hidden-phone text-info';
							break;
						case 2:
							status = '已奖励';
							statusClass = 'center hidden-phone text-success';
							break;
						case 3:
							status = '已拒绝';
							statusClass = 'center hidden-phone text-warning';
							break;
						}
						b += '<tr class="orderTr">'+ 
						'<td>' + obj.business.phone+'</td>'+
						'<td>' + obj.client.phone + '</td>' +
						'<td>' + obj.commodityName + '</td>' +
						'<td>' + obj.amount + '</td>' + 
						'<td>' + obj.premiumRates + '</td>' +
						'<td>' + obj.giftJf + '</td>' +
						'<td>' + obj.rewardJf + '</td>' +
						'<td>' + obj.journalTime + '</td>' +
						'<td id="status_' + obj.id + '" class="' + statusClass + '">' + status + '</td>' + 
						'</tr>';
					});
					
					$("#orderList").html(b);
					orderListComp.buildPager(c.totalCount, orderListComp.pageNo, orderListComp.pageSize);
					//gotoTop()
				} else {
					$("#orderList").html("<tr><td colspan='9' style='text-align:center'>没有找到相关记录!</td></tr>");
					$(".pages").hide();
					$(".pager_div").hide();
				}
			}
		})
	},
	buildPager: function(c, b, a) {
		var d = c / a;
		var e = (c % a == 0) ? (d) : (parseInt(d) + 1);
		orderListComp.pageCount = e;
		$("#pager").pager({
			pagenumber: b,
			pagecount: e,
			totalRecords: c,
			buttonClickCallback: orderListComp.pageClick
		});
		$(".pages").show()
		$(".pager_div").show();
	},
	pageClick: function(a) {
		orderListComp.pageNo = a;
		orderListComp.loadShopList()
	},
	goToPage: function() {
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
				if (parseInt(a) > orderListComp.pageCount) {
					alert("页数不能大于总页数，请重新输入。")
				} else {
					orderListComp.pageNo = a;
					orderListComp.loadShopList()
				}
			}
		}
	}
};