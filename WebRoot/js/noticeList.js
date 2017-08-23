$(function() {
	commodityListComp.init()
});
var commodityListComp = {
	mallPicImgPath : null,
	basePath : null,
	onlineCommodImgPath : null,
	listHtml : null,
	pageNo : 1,
	pageSize : 5,
	pageCount : 0,
	init : function() {
		this.mallPicImgPath = mallPicImgPath;
		this.basePath = basePath;
		this.loadCommodityTypes();
		this.loadShopList();
		this.bindEvent();
	},
	loadCommodityTypes : function() {
		$.ajax({
			type : "POST",
			url : commodityListComp.basePath + "/notice/getTypeListAjax.action",
			data : {},
			success : function(e) {
				var a = $.parseJSON(e);
				var c = a.resultObj;
				if (c && c.objList && c.objList.length > 0) {
					var b = "";
					$(c.objList).each(
							function(i, obj) {
								b += '<option value=' + obj.id + '>'
										+ obj.typeName + '</option>';
							});
					$("#typeSelect").append(b);
					$("#modal_edit #noticeTypeId").append(b);
					$("#modal_add  #noticeTypeId").append(b);
				} else {
					alert(e.resultMsg);
				}
			}
		})
	},

	bindEvent : function() {
		$('#searchEndDate').on("blur", this.searchEndDateBlur);
		$('#statusSelect').on("change", this.statusSelectChange);
		$('#typeSelect').on("change", this.typeSelectChange);
		$('#timeOrderBy').on("change", this.timeOrderByChange);
		$('#searchBtn').on("click", this.searchBtnClicked);
		$('#addNewCommodityBtn').on("click", this.addNewCommodityBtnClicked);
		$('#modal_edit #submitBtn').on("click", this.editModalSubmitBtnClicked);
		$('#modal_add #submitBtn').on("click", this.addModalSubmitBtnClicked);
		$('#modal_img #submitBtn').on("click", this.imgModalSubmitBtnClicked);
		$('#modal_detail #submitBtn').on("click",this.commodityDetailModalSubmitBtnClicked);
	},

	commodityDetailModalSubmitBtnClicked : function() {
		// var formData = new FormData($("#updateDetailForm")[0]);
		for (instance in CKEDITOR.instances)
			CKEDITOR.instances[instance].updateElement();
		$.ajax({
			type : "POST",
			url : commodityListComp.basePath
					+ "/notice/noticeDetailUpdateAjax.action",
			data : {
				id : $('#id').val(),
				editor1 : escape($('#ckeditorTxArea').val()),
			},
			// data:$('#updateDetailForm').serialize(),
			success : function(e) {
				var a = $.parseJSON(e);
				if (a.logicCode != '0') {
					alert(a.resultMsg);
				} else {
					alert('公告详情修改成功');
					window.location.reload();
				}
			},
		});
	},
	editMallImgClicked : function(id, content) {
		if(content=="undefined")
			content = "";
		$('#modal_detail #id').val(id);
		var _editor = $("iframe")[0];
		if(_editor != undefined)
		{
			$(_editor).contents().find("body").html(unescape(content));//访问iframe中的body，并插入html
		}
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
	statusSelectChange : function() {
		commodityListComp.pageNo = 1;
		commodityListComp.loadShopList();
	},
	typeSelectChange : function() {
		commodityListComp.pageNo = 1;
		commodityListComp.loadShopList();
	},
	timeOrderByChange : function() {
		commodityListComp.pageNo = 1;
		commodityListComp.loadShopList();
	},
	searchBtnClicked : function() {
		commodityListComp.pageNo = 1;
		$('#statusSelect').val("0");
		$('#typeSelect').val("-2");
		$('#timeOrderBy').val("0");
		commodityListComp.loadShopList();
	},

	imgModalSubmitBtnClicked : function() {
		if (confirm("确定要修改吗？") == false)
			return;
		var formData = new FormData($("#modal_img #modalForm")[0]);

		$.ajax({
			type : "POST",
			url : commodityListComp.basePath
					+ "/notice/mallImgUploadAjax.action",
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			success : function(e) {
				var f = $.parseJSON(e);
				if (f.resultCode != '0') {
					alert("Error Request.");
					return;
				}
				if (f.logicCode != '0') {
					alert(f.resultMsg);
					return;
				}
				alert('图片上传成功');
				window.location.reload();
			},
		});
	},
	editModalSubmitBtnClicked : function() {
		if (confirm("确定要修改吗？") == false)
			return;
		var formData = new FormData($("#modal_edit #modalForm")[0]);

		$.ajax({
			type : "POST",
			url : commodityListComp.basePath
					+ "/notice/noticeUpdateAjax.action",
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			success : function(e) {
				var a = $.parseJSON(e);
				if (a.logicCode != '0') {
					alert(a.resultMsg);
				} else {
					alert('公告修改成功');
					window.location.reload();
				}
			},
		});
	},
	addModalSubmitBtnClicked : function() {
		if (confirm("确定要添加吗？") == false)
			return;
		var formData = new FormData($("#modal_add #modalForm")[0]);

		$.ajax({
			type : "POST",
			url : commodityListComp.basePath
					+ "/notice/noticeAddAjax.action",
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			success : function(e) {
				var a = $.parseJSON(e);
				if (a.logicCode != '0') {
					alert(a.resultMsg);
				} else {
					alert('公告添加成功');
					window.location.reload();
				}
			},
		});
	},

	addModalSubmitBtnClicked_model : function() {
		var formData = new FormData($("#addModal_model #addModalForm")[0]);
		$.ajax({
			type : "POST",
			url : commodityListComp.basePath
					+ "/onlineCommodity/addModelAjax.action",
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			success : function(e) {
				var a = $.parseJSON(e);
				if (a.logicCode != '0') {
					alert(a.resultMsg);
				} else {
					alert('商品添加成功');
					window.location.reload();
				}
			},
		});
	},
	editModalSubmitBtnClicked_model : function() {
		if (confirm("确定要修改吗？") == false)
			return;
		var formData = new FormData($("#editModal_model #editModalForm")[0]);

		$.ajax({
			type : "POST",
			url : commodityListComp.basePath
					+ "/onlineCommodity/updateModelAjax.action",
			data : formData,
			async : false,
			cache : false,
			contentType : false,
			processData : false,
			success : function(e) {
				var a = $.parseJSON(e);
				if (a.logicCode != '0') {
					alert(a.resultMsg);
				} else {
					alert('型号修改成功');
					window.location.reload();
				}
			},
		});
	},

	editCommodityClicked : function(id, title, typeId) {
		var modal = $('#modal_edit');
		$('#modal_edit #id').val(id);
		$('#modal_edit #noticeTitle').val(title);
		$('#modal_edit #noticeTypeId').val(typeId);
	},
	delCommodityClicked : function(commodityId) {
		if (confirm("确定删除该公告吗？") == false)
			return;
		$.ajax({
			type : "POST",
			url : commodityListComp.basePath
					+ "/notice/noticeDeleteAjax.action",
			data : {
				id : commodityId,
			},
			success : function(e) {
				var a = $.parseJSON(e);
				if (a.logicCode == '-1') {
					alert(a.resultMsg);
				} else {
					alert("删除成功!");
					window.location.reload();
				}
			}
		});
	},

	addNewModelClicked : function(commodityId) {
		$('#addModal_model #hiddenId_editModal').val(commodityId);
	},
	addNewCommodityBtnClicked : function() {
		// alert("addNewCommodityBtnClicked");
	},

	editModelClicked : function(id, commodityModel, commodityPrice,
			commodityRepertory, commodityFlag, isDefaultModel,
			commoditySmallPic1, commoditySmallPic2, commoditySmallPic3,
			commoditySmallPic4, commoditySmallPic5) {
		$('#editModal_model #hiddenId_editModal').val(id);
		$('#editModal_model #commodityModel_editModal').val(commodityModel);
		$('#editModal_model #commodityPrice_editModal').val(commodityPrice);
		$('#editModal_model #commodityRepertory_editModal').val(
				commodityRepertory);
		$('#editModal_model #commodityStatus_editModal').val(commodityFlag);
		$('#editModal_model #commodityIsDefault_editModal').val(isDefaultModel);

		if ((commoditySmallPic1) == "undefined")
			$('#commodityPic_img_editModal').attr('src', "");
		else
			$('#editModal_model #modelPic_img1_editModal').attr('src',
					commodityListComp.onlineCommodImgPath + commoditySmallPic1);
		if ((commoditySmallPic2) == "undefined")
			$('#commodityPic_img_editModal').attr('src', "");
		else
			$('#editModal_model #modelPic_img2_editModal').attr('src',
					commodityListComp.onlineCommodImgPath + commoditySmallPic2);
		if ((commoditySmallPic3) == "undefined")
			$('#commodityPic_img_editModal').attr('src', "");
		else
			$('#editModal_model #modelPic_img3_editModal').attr('src',
					commodityListComp.onlineCommodImgPath + commoditySmallPic3);
		if ((commoditySmallPic4) == "undefined")
			$('#commodityPic_img_editModal').attr('src', "");
		else
			$('#editModal_model #modelPic_img4_editModal').attr('src',
					commodityListComp.onlineCommodImgPath + commoditySmallPic4);
		if ((commoditySmallPic5) == "undefined")
			$('#commodityPic_img_editModal').attr('src', "");
		else
			$('#editModal_model #modelPic_img5_editModal').attr('src',
					commodityListComp.onlineCommodImgPath + commoditySmallPic5);
	},
	delModelClicked : function(modelId) {
		// alert("delModelClicked" + modelId );
		if (confirm("确定删除该型号吗？") == false)
			return;
		$.ajax({
			type : "POST",
			url : commodityListComp.basePath
					+ "/onlineCommodity/deleteModelAjax.action",
			data : {
				id : modelId,
			},
			success : function(e) {
				var a = $.parseJSON(e);
				if (a.logicCode == '-1') {
					alert(a.resultMsg);
				} else {
					alert("删除成功!");
					window.location.reload();
				}
			}
		});
	},

	loadShopList : function() {

		$
				.ajax({
					type : "POST",
					url : commodityListComp.basePath
							+ "/notice/getNoticeListAjax.action",
					data : {
						pageNo : commodityListComp.pageNo,
						pageSize : commodityListComp.pageSize,
						searchKey : $('#searchKey').val(),
						searchStartDate : $('#searchStartDate').val(),
						searchEndDate : $('#searchEndDate').val(),
						statusSelect : $('#statusSelect').val(),
						typeSelect : $('#typeSelect').val(),
						timeOrderBy : $('#timeOrderBy').val()
					},
					success : function(e) {
						var a = $.parseJSON(e);
						var c = a.resultObj;
						if (c && c.objList && c.objList.length > 0) {
							// var b = commodityListComp.listHtml.process(c);
							var b = "";
							var status = "";
							$(c.objList)
									.each(
											function(i, obj) {
												switch (obj.noticeFlag) {
												case 0:
													status = '正常';
													break;
												case 1:
													status = '已删除';
													break;
												case 2:
													status = '已删除';
													break;
												}
												b += '<tr class="orderTr"><td>'
														+ obj.noticeTitle
														+ '</td>'
														+ '<td>'
														+ obj.noticeAddTime
														+ '</td>'
														+ '<td>'
														+ status
														+ '</td>'
														+ '<td>'
														+ obj.noticeType.typeName
														+ '</td>'
														+ '<td><a onclick="commodityListComp.editCommodityClicked('
														+ obj.id
														+ ',\''
														+ obj.noticeTitle
														+ '\''
														+ ',\''
														+ obj.noticeType.id
														+ '\''
														+ ');" href="#modal_edit" data-toggle="modal">编辑</a>&nbsp;/&nbsp;'
														+ '<a onclick="commodityListComp.delCommodityClicked('
														+ obj.id
														+ ');" href="javascript:void(0);">删除</a>&nbsp;/&nbsp;'
														+ '<a onclick="commodityListComp.editMallImgClicked('
														+ obj.id
														+ ',\''
														+ obj.noticeFileName
														+ '\''
														+ ');" href="#modal_detail" data-toggle="modal">公告内容</a></td>'
														+ '</tr>';
											});

							$("#mallList").html(b);
							commodityListComp.buildPager(c.totalCount,
									commodityListComp.pageNo,
									commodityListComp.pageSize);
							gotoTop()
						} else {
							$("#mallList")
									.html(
											"<tr><td colspan='8' style='text-align:center'>没有找到相关记录!</td></tr>");
							$(".pages").hide();
							$(".pager_div").hide();
						}
					}
				})
	},
	buildPager : function(c, b, a) {
		var d = c / a;
		var e = (c % a == 0) ? (d) : (parseInt(d) + 1);
		commodityListComp.pageCount = e;
		$("#pager").pager({
			pagenumber : b,
			pagecount : e,
			totalRecords : c,
			buttonClickCallback : commodityListComp.pageClick
		});
		$(".pages").show()
		$(".pager_div").show();
	},
	pageClick : function(a) {
		commodityListComp.pageNo = a;
		commodityListComp.loadShopList()
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
				if (parseInt(a) > commodityListComp.pageCount) {
					alert("页数不能大于总页数，请重新输入。")
				} else {
					commodityListComp.pageNo = a;
					commodityListComp.loadShopList()
				}
			}
		}
	}
};