var EditableTableMall = function () {

    return {
        // main function to initiate the module
        init: function () {
        	function loadEparchys() {
        		clearSelect("#eparchyId");
        		clearSelect("#cityId");
        		var a = $('#provinceId').val();
        		if ($.trim(a).length == 0) {
        			return
        		}
        		$.ajax({
        			type: "POST",
        			url :  "/jude/user/registerValidate.action",
        			data : {
        				reqType : "loadEparchy",
        				addrId : a
        			},
        			success : function(b) {
        				var c = $.parseJSON(b);
        				if (c) {
        					loadSelectOptions("#eparchyId", c)
        				}
        			}
        		});
        	}
        	
        	function loadCitys() {
        		clearSelect("#cityId");
        		var a = $('#eparchyId').val();
        		if ($.trim(a).length == 0) {
        			return
        		}
        		$.ajax({
        			type: "POST",
        			url :  "/jude/user/registerValidate.action",
        			data : {
        				reqType : "loadCity",
        				addrId : a
        			},
        			success : function(b) {
        				var c = $.parseJSON(b);
        				if (c) {
        					loadSelectOptions("#cityId", c)
        				}
        			}
        		});
        	}
        	
        	function clearSelect(a) {
        		$(a).html("<option value=''>--请选择--</option>")
        	}
        	
        	function loadSelectOptions(a, c) {
        		var b = "";
        		$.each(c, function() {
        			b += "<option value='" + this.id + "'>" + this.name
        					+ "</option>"
        		});
        		$(a).append(b)
        	}
        	
        	function loadSelectRolesOptions(a, c) {
        		var b = "";
        		$.each(c, function() {
        			b += "<option value='" + this.id + "'>" + this.roleName
        					+ "</option>"
        		});
        		$(a).append(b)
        	}
        	
        	function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);

                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                    oTable.fnUpdate(aData[i], nRow, i, false);
                }
                oTable.fnDraw();
            }

            function editRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                
                jqTds[0].innerHTML = '<input type="text" id="mallIdId" readonly class="form-control small" value="' + aData[0] + '">';
                jqTds[1].innerHTML = '<input type="text" id="mallNameId" class="form-control small" value="' + aData[1] + '">';
                jqTds[2].innerHTML = '<input type="text" id="mallLinkManId"  class="form-control small" value="' + aData[2] + '">';
                jqTds[3].innerHTML = '<input type="text" id="mallPhoneId"  class="form-control small" value="' + aData[3] + '">';
                jqTds[4].innerHTML = '<input type="text" id="mallAddressId"  class="form-control small" value="' + aData[4] + '">';
                jqTds[5].innerHTML = '<input type="text" id="mallPos_lntId"  class="form-control small" value="' + aData[5] + '">';
                jqTds[6].innerHTML = '<input type="text" id="mallPos_latId"  class="form-control small" value="' + aData[6] + '">';
                jqTds[7].innerHTML = '<input type="text" id="mallSellerId"  class="form-control small" value="' + aData[7] + '">';
                jqTds[8].innerHTML = '<input type="text" id="mallProxyId"  class="form-control small" value="' + aData[8] + '">';
                jqTds[9].innerHTML = '<a class="edit" href="">保存</a>';
                jqTds[10].innerHTML = '<a class="cancel" href="">取消</a>';
            }
            
            function editNewRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);
                
                jqTds[0].innerHTML = '<input type="text" id="mallIdId" readonly class="form-control small" value="' + aData[0] + '">';
                jqTds[1].innerHTML = '<input type="text" id="mallNameId" class="form-control small" value="' + aData[1] + '">';
                jqTds[2].innerHTML = '<input type="text" id="mallLinkManId"  class="form-control small" value="' + aData[2] + '">';
                jqTds[3].innerHTML = '<input type="text" id="mallPhoneId"  class="form-control small" value="' + aData[3] + '">';
                jqTds[4].innerHTML = '<input type="text" id="mallAddressId"  class="form-control small" value="' + aData[4] + '">';
                jqTds[5].innerHTML = '<input type="text" id="mallPos_lntId"  class="form-control small" value="' + aData[5] + '">';
                jqTds[6].innerHTML = '<input type="text" id="mallPos_latId"  class="form-control small" value="' + aData[6] + '">';
                jqTds[7].innerHTML = '<input type="text" id="mallSellerId"  class="form-control small" value="' + aData[7] + '">';
                jqTds[8].innerHTML = '<input type="text" id="mallProxyId"  class="form-control small" value="' + aData[8] + '">';
                jqTds[9].innerHTML = aData[9];
                jqTds[10].innerHTML = aData[10];
               /* jqTds[11].innerHTML = aData[11];*/
                jqTds[11].innerHTML = aData[11];
            }


            function saveRow(oTable, nRow,type) {
                var jqInputs = $('input', nRow);
                var flag = false;
                var url = type==0?"/jude/mall/mallUpdateAjax.action":"/jude/mall/MallAddAjax.action";
                $.ajax({
        			type: "POST",
        			'url' :  url,
        			async:false,
        			data : {
        				id:$('#mallIdId').val(),
        				mallName:$('#mallNameId').val(),
        				mallLinkMan:$('#mallLinkManId').val(),
        				mallPhone: $('#mallPhoneId').val(),
        				mallAddress: $('#mallAddressId').val(),
        				mallPos_lat:$('#mallPos_latId').val(),
        				mallPos_lnt:$('#mallPos_lntId').val(),
        				'user.keywords': $('#mallSellerId').val(),
        				'proxyUser.keywords': $('#mallProxyId').val()
        			},
        			success : function(b) {
        				var c = $.parseJSON(b);
        				if(c.resultCode!="0"){
        					alert(c.resultMsg, {
    							icon : 5
    						});
        					flag = false;
        					return;
        				}
        				if(c.logicCode!="0"){
        					alert(c.resultMsg, {
    							icon : 5
    						});
        					flag = false;
        					return;
        				}
        				alert('操作成功', {
							icon : 5
						});
        				(type==0)?oTable.fnUpdate(jqInputs[0].value, nRow, 0, false):oTable.fnUpdate(c.resultObj.id, nRow, 0, false);
                        oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                        oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
                        oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
                        oTable.fnUpdate(jqInputs[4].value, nRow, 4, false);
                        oTable.fnUpdate(jqInputs[5].value, nRow, 5, false);
                        oTable.fnUpdate(jqInputs[6].value, nRow, 6, false);
                        oTable.fnUpdate(jqInputs[7].value, nRow, 7, false);
                        oTable.fnUpdate(jqInputs[8].value, nRow, 8, false);
                        
                        oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 9, false);
                        oTable.fnUpdate('<a class="delete" href="">删除</a>', nRow,10, false);
                        oTable.fnDraw();
                        flag = true;
        			}
        		});
                return flag;
            }

            function cancelEditRow(oTable, nRow) {
                var jqInputs = $('input', nRow);
                oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
                oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
                oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
                oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
                oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 4, false);
                oTable.fnDraw();
            }

            var oTable = $('#malltable-sample').dataTable({
                "aLengthMenu": [
                    [5, 15, 20, -1],
                    [5, 15, 20, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 5,
                "sDom": "<'row'<'col-lg-6'l><'col-lg-6'f>r>t<'row'<'col-lg-6'i><'col-lg-6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sLengthMenu": "_MENU_ records per page",
                    "oPaginate": {
                        "sPrevious": "Prev",
                        "sNext": "Next"
                    }
                },
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': [0]
                    }
                ]
            });

            jQuery('#malltable-sample_wrapper .dataTables_filter input').addClass("form-control medium"); // modify
																											// table
																											// search
																											// input
            jQuery('#malltable-sample_wrapper .dataTables_length select').addClass("form-control xsmall"); // modify
																											// table
																											// per
																											// page
																											// dropdown

            var nEditing = null;

            $('#malltable-sample_new').click(function (e) {
                e.preventDefault();
                var aiNew = oTable.fnAddData(['', '', '', '', '', '', '','','',
                        '<a class="edit" data-mode="new" href="">保存</a>', '<a class="cancel" data-mode="new" href="">取消</a>',
                        '<a class="uploadImg" href="">上传图片</a>'
                ]);
                var nRow = oTable.fnGetNodes(aiNew[0]);
                editNewRow(oTable, nRow);
                nEditing = nRow;
            });

            $('#malltable-sample a.delete').live('click', function (e) {
                e.preventDefault();

                if (confirm("确定要删除该商铺吗 ?") == false) {
                    return;
                }
                var nRow = $(this).parents('tr')[0];
                var aData = oTable.fnGetData(nRow);
                var flag = false;
                $.ajax({
        			type: "POST",
        			url :  "/jude/mall/deleteMallAjax.action",
        			async:false,
        			data : {
        				id:aData[0],
        			},
        			success : function(b) {
        				var c = $.parseJSON(b);
        				if(c.resultCode!="0" || c.logicCode!="0"){
        					alert(c.resultMsg, {
    							icon : 5
    						});
        					flag = false;
        					return;
        				}
        				alert('删除成功', {
							icon : 5
						});
        				flag = true;
        				return;
        			}
        		});
                if(flag){
                	var nRow = $(this).parents('tr')[0];
                	oTable.fnDeleteRow(nRow);
                }
            });

            $('#malltable-sample a.cancel').live('click', function (e) {
                e.preventDefault();
                if ($(this).attr("data-mode") == "new") {
                    var nRow = $(this).parents('tr')[0];
                    oTable.fnDeleteRow(nRow);
                    nEditing = null;
                } else {
                    restoreRow(oTable, nEditing);
                    nEditing = null;
                }
            });

            $('#malltable-sample a.edit').live('click', function (e) {
                e.preventDefault();
                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];

                if (nEditing !== null && nEditing != nRow) {
                    /*
					 * Currently editing - but not this row - restore the old
					 * before continuing to edit mode
					 */
                    restoreRow(oTable, nEditing);
                    editRow(oTable, nRow);
                    nEditing = nRow;
                } else if (nEditing == nRow && this.innerHTML == "保存") {
                    /* Editing this row and want to save it */
                    var flag = false;
                	if ($(this).attr("data-mode") == "new") {
                    	flag = saveRow(oTable,nEditing,1);
                	}else{
                		flag = saveRow(oTable,nEditing,0);
                	}
                    if(flag==true)
                    	nEditing = null;
                    //alert("Updated! Do not forget to do some ajax to sync with backend :)");
                } else {
                    /* No edit in progress - let's start one */
                    editRow(oTable, nRow);
                    nEditing = nRow;
                }
            });
        }

    };

}();