$(function() {
	orderListComp.init()
});
var orderListComp = {
	basePath: null,
	onlineCommodImgPath : null,
	listHtml: null,
	pageNo: 1,
	pageSize: 5,
	pageCount: 0,
	isTdAdded : false,
	isTdEventBinded : false,
	init: function() {
		this.basePath = basePath;
		this.onlineCommodImgPath = onlineCommodImgPath;
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
		//this.bindEvent();
	},
	bindEvent:function(){
//		$('#sendCommodity').on("click",this.sendCommodityClicked);
//		$('#undoOrder').on("click",this.undoOrderClicked);
	},
	
	sendCommodityClicked:function(orderId){
		if (confirm("确定发货 ?") == false) {
            return;
        }
		$.ajax({
			type:"POST",
			url: orderListComp.basePath + "/onlineOrder/updateOrderStatusAjax.action",
			data:{
				status : 1,
				orderId : orderId
			},
			success : function(e){
				var a = $.parseJSON(e);
				if(a.logicCode=='0'){
					$('#status_'+orderId).html("已发货");
				}else
					alert('发货失败.');
			},
		});
	},
	undoOrderClicked:function(orderId){
		if (confirm("确定撤单 ?") == false) {
            return;
        }
		$.ajax({
			type:"POST",
			url: orderListComp.basePath + "/onlineOrder/updateOrderStatusAjax.action",
			data:{
				status : -1,
				orderId : orderId
			},
			success : function(e){
				var a = $.parseJSON(e);
				if(a.logicCode=='0'){
					$('#status_'+orderId).html("已撤单");
				}else
					alert('撤单失败.');
			},
		});
	},
	loadShopList: function() {
		if(this.isTdAdded == true){
			var theadTr = $('#theadTr');
			var theadTrTd = $('#newAddedTh');
			theadTr[0].removeChild(theadTrTd[0]);
		}
		
		$.ajax({
			type: "POST",
			url : orderListComp.basePath + "/onlineOrder/getPendingOrderListAjax.action",
			data: {
				pageNo: orderListComp.pageNo,
				pageSize: orderListComp.pageSize
			},
			success: function(e) {
				var a = $.parseJSON(e);
				var c = a.resultObj;
				if (c && c.objList && c.objList.length > 0) {
					//var b = orderListComp.listHtml.process(c);
					var b = "";
					var status = "";
					$(c.objList).each(function(i,obj){
						switch(obj.status){
						case -1:
							status = '已撤销';
							break;
						case 0:
							status = '已付款';
							break;
						case 1:
							status = '已发货';
							break;
						case 2:
							status = '已确认收货';
							break;
						}
						b += '<tr class="orderTr"><td>'+obj.orderNo+'</td>'+
						'<td>' + obj.user.phone + '</td>' +
						'<td>' + obj.totalAmount + '</td>' +
						'<td>' + obj.journalTime + '</td>' + 
						/*'<td>' + obj.recvCommodityAddress.contacts + '</td>' + 
						'<td>' + obj.recvCommodityAddress.phone + '</td>' + */
						'<td>' + obj.recvCommodityAddress.province + ' ' + obj.recvCommodityAddress.city + ' ' + 
						obj.recvCommodityAddress.area + ' ' + obj.recvCommodityAddress.extra + ' ' + obj.recvCommodityAddress.contacts + ' ' + obj.recvCommodityAddress.phone + '</td>' + 
						'<td id="status_' + obj.id + '">' + status + 
						'</td>' + 
						'<td><a onclick="orderListComp.sendCommodityClicked(' + obj.id + ');" href="javascript:void(0);">发货</a>&nbsp;/&nbsp;' +
						'<a onclick="orderListComp.undoOrderClicked(' + obj.id + ');" href="javascript:void(0);">撤单</a></td>'
						'</tr>';
//						
						b += '<tr class="commodityTr" style="display:none"><td class="details" colspan="8"><table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;width:100%">';
						$(obj.items).each(function(i,item){
							    b += '<tr><td style="width:15%">商品：' + item.onlineCommodityModel.onlineCommodity.commodityName +'</td>' + 
							    '<td style="width:10%">型号：'+item.onlineCommodityModel.commodityModel+'</td>' + 
							    '<td style="width:10%">单价：'+item.onlineCommodityModel.commodityPrice+'</td>' + 
							    '<td style="width:10%">数量：'+item.num+'</td>' + 
							    '<td style="width:40%">小计：'+item.num *  item.onlineCommodityModel.commodityPrice+'</td>' + 
							    '</tr>';
						});
						b += '</table></td></tr>';
					});
					
					$("#orderList").html(b);
					orderListComp.buildPager(c.totalCount, orderListComp.pageNo, orderListComp.pageSize);
					gotoTop()
					
					var nCloneTh = document.createElement( 'th' );
				    var nCloneTd = document.createElement( 'td' );
				    $(nCloneTh).attr('width','3%');
				    $(nCloneTh).attr("id","newAddedTh");
				    nCloneTd.innerHTML = '<img class="imgClosed" src="images/details_open.png">';
				    nCloneTd.className = "center";

				    $('#hidden-table-info thead .orderTr').each( function () {
				        this.insertBefore( nCloneTh, this.childNodes[0] );
				    } );

				    $('#hidden-table-info tbody .orderTr').each( function () {
				        this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
				    } );

				    orderListComp.isTdAdded = true;
				    
				    if(orderListComp.isTdEventBinded == true)
				    	return;
				    $(document).on('click','#hidden-table-info tbody td img',function () {
				        var nTr = $(this).parents('tr')[0];
				        if ($(this).attr('class') == 'imgClosed')
				        {
				            /* This row is already open - close it */
				        	$(this).attr('class','imgOpened');
				            this.src = "images/details_close.png";
				            $(nTr).next()[0].style.display = ''; 
				        	//alert($(nTr).siblings('tr')[0]);
				        }
				        else
				        {
				            /* Open this row */
				        	$(this).attr('class','imgClosed');
					        this.src = "images/details_open.png";
					        $(nTr).next().css("display","none");
				        }
				    } );
				    orderListComp.isTdEventBinded = true;
				} else {
					$("#orderList").html("<tr><td colspan='8' style='text-align:center'>没有找到相关记录!</td></tr>");
					$(".pages").hide();
					$(".pager_div").hide();
					orderListComp.isTdAdded = false;
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