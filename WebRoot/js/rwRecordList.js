$(function() {
	orderListComp.init()
});
var orderListComp = {
	basePath: null,
	listHtml: null,
	pageNo: 1,
	pageSize: 5,
	pageCount: 0,
	init: function() {
		this.basePath = basePath;
		//this.listHtml = '{for shop in objList}<li><a target="_blank" href="' + this.basePath + '/mall/item/${shop.id}"><div class="product-img"><img class="lazy" data-original="' + this.mallPicImgPath + '${shop.mallMainPicUrl}" alt="${shop.mallName }"/><span class="shopName">${shop.mallName }</span></div></a><p>电话：${shop.mallPhone }</p><p class="address">地址：${shop.mallAddress }</p></li>{/for}';
		this.listHtml = '{for obj in objList}<tr><td>${obj.orderNo}</td>'+
			'<td>${obj.user.phone}</td><td>${obj.totalAmount}</td>'+
			'<td>${obj.journalTime}</td>' + 
			'<td>${obj.recvCommodityAddress.contacts}</td><td>${obj.recvCommodityAddress.phone}</td>' + 
			'<td>${obj.recvCommodityAddress.province} ${obj.recvCommodityAddress.city} ' + 
			'${obj.recvCommodityAddress.area} ${obj.recvCommodityAddress.extra}</td>' + 
			'<td>${obj.status}</td>' + 
			'</tr>{/for}';
		this.loadShopList();
		this.bindEvent();
	},
	bindEvent: function(){
		$('#searchEndDate').on("blur",this.searchEndDateBlur);
		$('#statusSelect').on("change",this.statusSelectChange);
		$('#typeSelect').on("change",this.typeSelectChange);
		$('#addTimeOrderBy').on("change",this.addTimeOrderByChange);
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
	statusSelectChange:function(){
		orderListComp.pageNo = 1;
		orderListComp.loadShopList();
	},
	typeSelectChange:function(){
		orderListComp.pageNo = 1;
		orderListComp.loadShopList();
	},
	addTimeOrderByChange:function(){
		orderListComp.pageNo = 1;
		orderListComp.loadShopList();
	},
	searchBtnClicked:function(){
		orderListComp.pageNo = 1;
		$('#typeSelect').val("1");
		$('#statusSelect').val("2");
		$('#addTimeOrderBy').val("0");
		orderListComp.loadShopList();
	},
	
	loadShopList: function() {
		$.ajax({
			type: "POST",
			url : orderListComp.basePath + "/rwRecord/getRwRecordListAjax.action",
			data: {
				pageNo: orderListComp.pageNo,
				pageSize: orderListComp.pageSize,
				searchKey : $('#searchKey').val(),
				searchStartDate : $('#searchStartDate').val(),
				searchEndDate : $('#searchEndDate').val(),
				statusSelect : $('#statusSelect').val(),
				typeSelect : $('#typeSelect').val(),
				addTimeOrderBy : $('#addTimeOrderBy').val()
			},
			success: function(e) {
				var a = $.parseJSON(e);
				var c = a.resultObj;
				if (c && c.objList && c.objList.length > 0) {
					//var b = orderListComp.listHtml.process(c);
					var b = "";
					var status = "";
					$(c.objList).each(function(i,obj){
						//alert(obj.operateState);
						b += '<tr class="orderTr"><td>'+obj.user.username + ' / ' + obj.user.phone +'</td>'+
						'<td>' + ((obj.operateType=="RECHARGE")?'充值':'提现') + '</td>' +
						'<td>' + obj.operateNum + '</td>' +
						'<td>' + obj.serviceNum + '</td>' + 
						'<td>' + ((obj.operateState=="PAYSUCCESS")?'成功':'失败') + '</td>' + 
						'<td>' + obj.operateTime + '</td>' + 
						'</tr>';
					});
					
					$("#orderList").html(b);
					orderListComp.buildPager(c.totalCount, orderListComp.pageNo, orderListComp.pageSize);
					gotoTop()
				} else {
					$("#orderList").html("<tr><td colspan='6' style='text-align:center'>没有找到相关记录!</td></tr>");
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