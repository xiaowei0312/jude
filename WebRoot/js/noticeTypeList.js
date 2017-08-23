$(function() {
	typeListComp.init()
});
var typeListComp = {
	basePath : null,
	listHtml : null,
	pageNo : 1,
	pageSize : 5,
	pageCount : 0,
	init : function() {
		this.basePath = basePath;
		this.loadShopList();
		this.bindEvent();
	},
	bindEvent : function() {
		$('#typeSeqOrderBy').on("change", this.typeSeqOrderByChange);
		$('#searchBtn').on("click", this.searchBtnClicked);
		$('#editModalSubmitBtn').on("click",this.editModalSubmitBtnClicked);
		$('#addModalSubmitBtn').on("click",this.addModalSubmitBtnClicked);
	},

	typeSeqOrderByChange : function() {
		typeListComp.pageNo = 1;
		typeListComp.loadShopList();
	},
	
	searchBtnClicked : function() {
		typeListComp.pageNo = 1;
		$('#orderTimeOrderBy').val("0");
		typeListComp.loadShopList();
	},
	
	addModalSubmitBtnClicked : function(){
		if(confirm("确定要添加吗？") == false)
			return;
		$.ajax({
			type: "POST",
			url : typeListComp.basePath
				+ "/notice/addTypeAjax.action",
			data: $('#addModalForm').serialize(),
			success : function(e){
				var a = $.parseJSON(e);
				if(a.logicCode != '0'){
					alert(a.resultMsg);
				}else{
					alert('商品类型添加成功');
					window.location.reload();
				}
			},
		});
	},
	
	editModalSubmitBtnClicked : function(){
		if(confirm("确定要修改吗？") == false)
			return;
		$.ajax({
			type: "POST",
			url : typeListComp.basePath
				+ "/notice/updateTypeAjax.action",
			data: $('#editModalForm').serialize(),
			success : function(e){
				var a = $.parseJSON(e);
				if(a.logicCode != '0'){
					alert(a.resultMsg);
				}else{
					alert('商品类型修改成功');
					window.location.reload();
				}
			},
		});
	},

	editClicked:function(typeName,typeSeq,typeId){
		var model = $('#editModal');
		$('#typeName').val(typeName);
		$('#typeSeq').val(typeSeq);
		$('#typeId').val(typeId);
	},
	
	delType : function(typeId){
		$.ajax({
			type: "POST",
			url : typeListComp.basePath
				+ "/notice/deleteTypeAjax.action",
			data:{
				id : typeId,
				reqType : 2,
			},
			success : function(e){
				var a = $.parseJSON(e);
				if(a.logicCode == '-1'){
					alert(a.resultMsg);
				}else{
					alert("删除成功!");
					window.location.reload();
				}
			}
		});
	},
	
	delClicked:function(typeId){
		$.ajax({
			type: "POST",
			url : typeListComp.basePath
				+ "/notice/deleteTypeAjax.action",
			data:{
				id : typeId,
				reqType : 0,
			},
			success : function(e){
				var a = $.parseJSON(e);
				if(a.logicCode == '-1'){
					alert(a.resultMsg);
				}else{
					var totalCommodityInThisType = a.resultObj;
					console.log(totalCommodityInThisType);
					if(totalCommodityInThisType == 0){
						if(confirm("该类型下目前包含商品数量为：【"+totalCommodityInThisType+"】可以放心删除，确定删除该类型吗？")==false)
							return;
						typeListComp.delType(typeId);
					}else{
						alert("该类型下目前包含商品数量为：【" + totalCommodityInThisType+"】，无法删除!" );
					}
				}
			}
		});
	},

	loadShopList : function() {
		$
				.ajax({
					type : "POST",
					url : typeListComp.basePath
							+ "/notice/getTypeListAjax.action",
					data : {
						pageNo : typeListComp.pageNo,
						pageSize : typeListComp.pageSize,
						searchKey : $('#searchKey').val(),
						typeSeqOrderBy : $('#typeSeqOrderBy').val(),
					},
					success : function(e) {
						var a = $.parseJSON(e);
						var c = a.resultObj;
						if (c && c.objList && c.objList.length > 0) {
							// var b = typeListComp.listHtml.process(c);
							var b = "";
							var status = "";
							$(c.objList)
									.each(
											function(i, obj) {
												b += '<tr><td>'
														+ obj.typeName
														+ '</td>'
														+ '<td>'
														+ '顶层分类'
														+ '</td>'
														+ '<td>'
														+ obj.sequence
														+ '</td>'
														+ '<td><a onclick="typeListComp.editClicked(\'' + obj.typeName + '\',' + obj.sequence + ',' + obj.id + ');" href="#editModal" data-toggle="modal">编辑</a>' + 
														' / ' + 
														'<a onclick="typeListComp.delClicked(' + obj.id + ');" href="javascript:void(0);">删除</a></td>'
														+ '</tr>';
											});

							$("#typeList").html(b);
							typeListComp.buildPager(c.totalCount,
									typeListComp.pageNo, typeListComp.pageSize);
							gotoTop()
						} else {
							$("#typeList")
									.html(
											"<div style='text-align:center;font-size:14px;'>没有找到相关记录!</div>");
							$(".pages").hide();
							$(".pager_div").hide();
						}
						// $("#totalShopNum").text(c.totalCount);
						// $("img.lazy").lazyload({
						// effect: "fadeIn",
						// placeholder: "/userfiles/images/placeholder.gif"
						// })
					}
				})
	},
	buildPager : function(c, b, a) {
		var d = c / a;
		var e = (c % a == 0) ? (d) : (parseInt(d) + 1);
		typeListComp.pageCount = e;
		$("#pager").pager({
			pagenumber : b,
			pagecount : e,
			totalRecords : c,
			buttonClickCallback : typeListComp.pageClick
		});
		$(".pages").show()
	},
	pageClick : function(a) {
		typeListComp.pageNo = a;
		typeListComp.loadShopList()
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
				if (parseInt(a) > typeListComp.pageCount) {
					alert("页数不能大于总页数，请重新输入。")
				} else {
					typeListComp.pageNo = a;
					typeListComp.loadShopList()
				}
			}
		}
	}
};