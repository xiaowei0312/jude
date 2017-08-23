$(function() {
	nearMerchantComp.init()
});
var nearMerchantComp = {
	isLoad : false,
	page : 1,
	totalPage : 0,
	init : function() {
		nearMerchantComp.calcPageCount(s.size);
		bindScrollLoad(nearMerchantComp.scrollLoad)
	},
	scrollLoad : function() {
		if (nearMerchantComp.isLoad) {
			return
		}
		nearMerchantComp.isLoad = true;
		if (nearMerchantComp.page >= nearMerchantComp.totalPage) {
			return
		}
		nearMerchantComp.page = nearMerchantComp.page + 1;
		$(".jui_list_cells")
				.append(
						"<div id='loadMore' class='jui_list_cell loadMore'>正在加载更多...</div>");
		$.ajax({
			type: "POST",
			url :  "/wechat/user/userNearbyStoresResultPage.action",
			data : {
				page : nearMerchantComp.page,
				latitude : s.lat,
				longitude : s.lon,
				distance : 200000
			},
			success : function(e) {
				var a = $.parseJSON(e);
				$("#loadMore").remove();
				nearMerchantComp.calcPageCount(a.count);
				nearMerchantComp.buildMore(a.sets);
				nearMerchantComp.isLoad = false
			}
		})
	},
	calcPageCount : function(b) {
		var a = b / 20;
		nearMerchantComp.totalPage = (b % 20 == 0) ? (a) : (parseInt(a) + 1)
	},
	buildMore : function(b) {
		var a = "";
		$.each(b, function(c, d) {
			a += '<a href="/wechat/user/shopDetail.action?mallId=' + d.id
					+ '" class="jui_list_cell">';
			a += "	<em><img src='" + "/wechat/mall/" + d.mallMainPicUrl
					+ "?imageView2/0/w/100/h/72'></em>";
			a += '	<div class="shopInfo">';
			a += '		<p class="shopName">' + d.mallName + "</p>";
			a += '		<p class="shopRemark">' + d.mallDesc + "</p>";
			a += '		<p class="shopLP">联系电话：' + d.mallPhone + "</p>";
			a += '		<span class="shop_distance">' + d.distance + "</span>";
			a += "	</div>";
			a += "</a>"
		});
		$(".jui_list_cells").append(a);
		if (nearMerchantComp.page >= nearMerchantComp.totalPage) {
			$(".jui_list_cells")
					.append(
							"<div id='loadMore' class='jui_list_cell loadMore'>已经到最后一页</div>")
		}
	}
};