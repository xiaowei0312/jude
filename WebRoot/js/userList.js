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
		this.loadProvinces();
		this.loadShopList();
		this.bindEvent();
	},
	
	loadProvinces : function(){
		if ($.trim(a).length == 0) {
			return
		}
		$.ajax({
			type: "POST",
			url :  commodityListComp.basePath +"/user/registerValidate.action",
			async : false,
			data : {
				reqType : "loadProvince",
			},
			success : function(b) {
				var c = $.parseJSON(b);
				if (c) {
					commodityListComp.loadSelectOptions('#modal_edit #provinceId', c);
					commodityListComp.loadSelectOptions('#modal_add #provinceId', c);
				}
			}
		});
	},
	
	loadEparchys : function(pId,eId,cId) {
		var a = $(pId).val();
		if ($.trim(a).length == 0) {
			return
		}
		this.clearSelect(eId);
		this.clearSelect(cId);
		$.ajax({
			type: "POST",
			url :  commodityListComp.basePath +"/user/registerValidate.action",
			async : false,
			data : {
				reqType : "loadEparchy",
				addrId : a
			},
			success : function(b) {
				var c = $.parseJSON(b);
				if (c) {
					commodityListComp.loadSelectOptions(eId, c)
				}
			}
		});
	},
	
	loadCitys : function(pId,eId,cId) {
		var a = $(eId).val();
		if ($.trim(a).length == 0) {
			return
		}
		this.clearSelect(cId);
		$.ajax({
			type: "POST",
			url :  commodityListComp.basePath +"/user/registerValidate.action",
			async : false,
			data : {
				reqType : "loadCity",
				addrId : a
			},
			success : function(b) {
				var c = $.parseJSON(b);
				if (c) {
					commodityListComp.loadSelectOptions(cId, c)
				}
			}
		});
	},
	
	clearSelect : function(a) {
		$(a).html("<option value=''>--请选择--</option>")
	},
	
	loadSelectOptions : function(a, c) {
		var b = "";
		$.each(c, function() {
			b += "<option value='" + this.id + "'>" + this.name
					+ "</option>"
		});
		$(a).append(b)
	},
	loadCommodityTypes : function() {
		$.ajax({
			type : "POST",
			url : commodityListComp.basePath + "/user/getRoleListAjax.action",
			data : {},
			success : function(e) {
				var a = $.parseJSON(e);
				var c = a.resultObj;
				if (c && c.objList && c.objList.length > 0) {
					var b = "";
					$(c.objList).each(
							function(i, obj) {
								b += '<option value=' + obj.id + '>'
										+ obj.roleName + '</option>';
							});
					$("#typeSelect").append(b);
					$("#modal_edit #roleId").append(b);
					$("#modal_add  #roleId").append(b);
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
	},

	commodityDetailModalSubmitBtnClicked : function() {
		// var formData = new FormData($("#updateDetailForm")[0]);
		for (instance in CKEDITOR.instances)
			CKEDITOR.instances[instance].updateElement();
		$.ajax({
			type : "POST",
			url : commodityListComp.basePath
					+ "/onlineCommodity/updateCommodityDetailAjax.action",
			data : {
				id : $('#commodityId_detail_modal').val(),
				editor1 : escape($('#ckeditorTxArea').val()),
			},
			// data:$('#updateDetailForm').serialize(),
			success : function(e) {
				var a = $.parseJSON(e);
				if (a.logicCode != '0') {
					alert(a.resultMsg);
				} else {
					alert('商品详情修改成功');
					window.location.reload();
				}
			},
		});
	},
	editMallImgClicked : function(mallId, img0,
			img1, img2, img3, img4, img5,mallDesc) {
		$('#modal_img #mallId').val(mallId);
		
		if(img0 == 'undefined')
			$('#modal_img #img0').attr('src','');
		else
			$('#modal_img #img0').attr('src',this.mallPicImgPath + img0);
		if(img1 == 'undefined')
			$('#modal_img #img1').attr('src','');
		else
			$('#modal_img #img1').attr('src',this.mallPicImgPath + img1);
		if(img2 == 'undefined')
			$('#modal_img #img2').attr('src','');
		else
			$('#modal_img #img2').attr('src',this.mallPicImgPath + img2);
		if(img3 == 'undefined')
			$('#modal_img #img3').attr('src','');
		else
			$('#modal_img #img3').attr('src',this.mallPicImgPath + img3);
		if(img4 == 'undefined')
			$('#modal_img #img4').attr('src','');
		else
			$('#modal_img #img4').attr('src',this.mallPicImgPath + img4);
		if(img5 == 'undefined')
			$('#modal_img #img5').attr('src','');
		else
			$('#modal_img #img5').attr('src',this.mallPicImgPath + img5);
		
		if(mallDesc != 'undefined')
			$('#modal_img #mallDesc').val(mallDesc);
		
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
					+ "/mall/mallImgUploadAjax.action",
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
					+ "/user/updateUserAjax.action",
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
					alert('用户信息修改成功');
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
					+ "/user/addNewUserAjax.action",
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
					alert('用户添加成功');
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

	editCommodityClicked : function(id, username, phone,
			provinceId, cityId, areaId,roleId,proxyUserKeyWords) {
		$('#modal_edit #id').val(id);
		$('#modal_edit #username').val(username);
		$('#modal_edit #phone').val(phone);
		$('#modal_edit #provinceId').val(provinceId);
		this.loadEparchys('#modal_edit #provinceId','#modal_edit #cityId','#modal_edit #areaId');
		$('#modal_edit #cityId').val(cityId);
		this.loadCitys('#modal_edit #provinceId','#modal_edit #cityId','#modal_edit #areaId');
		$('#modal_edit #areaId').val(areaId);
		$('#modal_edit #roleId').val(roleId);
		$('#modal_edit #proxyUserKeyWords').val(proxyUserKeyWords);
	},
	delCommodityClicked : function(commodityId) {
		if (confirm("确定删除该用户吗？") == false)
			return;
		$.ajax({
			type : "POST",
			url : commodityListComp.basePath
					+ "/user/deleteUserAjax.action",
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
	resetUserPwdBtnClicked : function(commodityId) {
		if (confirm("密码将被重置为【jude123】，确定执行该操作吗 ?") == false) {
	        return;
	    }
		$.ajax({
			type: "POST",
			url :  this.basePath + "/user/resetUserPwdAjax.action",
			async:false,
			data : {
				id:commodityId,
			},
			success : function(b) {
				var c = $.parseJSON(b);
				if(c.resultCode!="0" || c.logicCode!="0"){
					alert(c.resultMsg);
					return;
				}
				alert('密码被重置为【jude123】成功');
				return;
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
	
	setGiveMoneyFlag : function(userId,giveMoneyFlag){
		if (confirm("确定要修改该帐户返现功能吗？") == false)
			return;
		$.ajax({
			type : "POST",
			url : commodityListComp.basePath
					+ "/user/setGiveMoneyFlagAjax.action",
			data : {
				id : userId,
				giveMoneyFlag:giveMoneyFlag
			},
			success : function(e) {
				var a = $.parseJSON(e);
				if (a.logicCode == '-1') {
					alert(a.resultMsg);
				} else {
					alert("修改成功!");
					window.location.reload();
				}
			}
		});
	},
	
	setWithdrawLimit : function(userId,phone,d){
		var val = prompt("请输入取现额度上限");
		if(!val){
			return;
		}
		if(!/^[1-9]*[1-9][0-9]*$/.test(val)){
			alert("取现上限必须是[正整数]");
			return;
		}
		//alert(val);
		if(!confirm('用户[' + phone + ']的取现额度将被调整为[' + val + ']')){
			return;
		}
		
		$.ajax({
			type : "POST",
			url : commodityListComp.basePath
					+ "/user/setWithdrawLimit.action",
			data : {
				id : userId,
				withdrawLimit:val
			},
			success : function(e) {
				var a = $.parseJSON(e);
				if (a.logicCode == '-1') {
					alert(a.resultMsg);
				} else {
					alert("修改成功!");
					$(d).prev('span').text(val);
//					window.location.reload();
				}
			}
		});
	},

	loadShopList : function() {

		$
				.ajax({
					type : "POST",
					url : commodityListComp.basePath
							+ "/user/getUserListAjax.action",
					data : {
						pageNo : commodityListComp.pageNo,
						pageSize : commodityListComp.pageSize,
						searchKey : $('#searchKey').val(),
						searchStartDate : $('#searchStartDate').val(),
						searchEndDate : $('#searchEndDate').val(),
						statusSelect : $('#statusSelect').val(),
						roleSelect : $('#typeSelect').val(),
						timeOrderBy : $('#timeOrderBy').val()
					},
					success : function(e) {
						var a = $.parseJSON(e);
						var c = a.resultObj;
						if (c && c.objList && c.objList.length > 0) {
							// var b = commodityListComp.listHtml.process(c);
							var b = "";
							var status = "";
							var giveMoneyStatus = "";
							$(c.objList)
									.each(
											function(i, obj) {
												switch (obj.status) {
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
												switch(obj.userExtra.giveMoneyFlag){
												case 0:
													giveMoneyStatus = '正常';
													break;
												case 1:
													giveMoneyStatus = '禁用';
													break;
												}
												b += '<tr class="orderTr"><td>'
														+ obj.username + ' /<br/> ' + obj.phone
														+ '</td>'
														+ '<td>'
														+ obj.provinceObj.name + '/' +  obj.cityObj.name +'/' + obj.areaObj.name
														+ '</td>'
														/*+ '<td>'
														+ obj.cityObj.name
														+ '</td>'
														+ '<td>'
														+ obj.areaObj.name
														+ '</td>'*/
														+ '<td>'
														+ obj.role.roleName
														+ '</td>'
														+ '<td>'
														+ ((typeof obj.proxyUser == 'undefined') ? '' :  obj.proxyUser.phone )
														+ '</td>'
														+ '<td>'
														+ obj.addTime
														+ '</td>'
														+ '<td>'
														+ (
																(obj.userExtra.giveMoneyFlag == 0)
																? ('正常(' + '<a href="javascript:void(0);" onclick="commodityListComp.setGiveMoneyFlag(' + obj.id + ',1)">点击禁用</a>)') 
																: ('禁用(' + '<a href="javascript:void(0);" onclick="commodityListComp.setGiveMoneyFlag(' + obj.id + ',0)">点击启用</a>)')
															)
														+ '</td>'
														+ '<td>'
														+ '<span>' + obj.userExtra.withdrawLimit + '</span>'
														+ '(<a href="javascript:void(0);" onclick="commodityListComp.setWithdrawLimit(' + obj.id  + ',\'' + obj.phone + '\',this' + ')">设置</a>)'
														+ '</td>'
														+ '<td><a onclick="commodityListComp.editCommodityClicked('
														+ obj.id
														+ ',\''
														+ obj.username
														+ '\''
														+ ',\''
														+ obj.phone
														+ '\''
														+ ',\''
														+ obj.provinceObj.id
														+ '\''
														+ ',\''
														+ obj.cityObj.id
														+ '\''
														+ ',\''
														+ obj.areaObj.id
														+ '\''
														+ ',\''
														+ obj.role.id
														+ '\''
														+ ',\''
														+ (typeof obj.proxyUser == 'undefined' ? '' :  obj.proxyUser.phone)
														+ '\''
														+ ');" href="#modal_edit" data-toggle="modal">编辑</a>&nbsp;/&nbsp;'
														+ '<a onclick="commodityListComp.delCommodityClicked('
														+ obj.id
														+ ');" href="javascript:void(0);">删除</a>&nbsp;/&nbsp;'
														+ '<a onclick="commodityListComp.resetUserPwdBtnClicked('
														+ obj.id
														+ ');" href="javascript:void(0);">密码重置</a></td>'
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
											"<tr><td colspan='9' style='text-align:center'>没有找到相关记录!</td></tr>");
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