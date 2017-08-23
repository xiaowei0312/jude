var EditableTableSetting = function() {

	return {
		// main function to initiate the module
		init : function() {
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

				jqTds[0].innerHTML = '<input type="text"  class="form-control small" value="'
						+ aData[0] + '">';
				jqTds[1].innerHTML = '<input type="text"  class="form-control small" value="'
						+ aData[1] + '">';
				/*jqTds[2].innerHTML = '<input type="text" class="form-control small" value="'
						+ aData[2] + '">';*/
				jqTds[2].innerHTML = '<input type="text"  class="form-control small" value="'
						+ aData[2] + '">';
				jqTds[3].innerHTML = '<input type="text"  class="form-control small" value="'
						+ aData[3] + '">';
				jqTds[4].innerHTML = '<input type="text"  class="form-control small" value="'
						+ aData[4] + '">';
				jqTds[5].innerHTML = '<input type="text"  class="form-control small" value="'
						+ aData[5] + '">';
				jqTds[6].innerHTML = '<input type="text"  class="form-control small" value="'
						+ aData[6] + '">';
				
				jqTds[7].innerHTML = '<input type="text"  class="form-control small" value="'
					+ aData[7] + '">';
				jqTds[8].innerHTML = '<input type="text"  class="form-control small" value="'
						+ aData[8] + '">';
				jqTds[9].innerHTML = '<input type="text"  class="form-control small" value="'
						+ aData[9] + '">';
				jqTds[10].innerHTML = '<input type="text"  class="form-control small" value="'
						+ aData[10] + '">';
				
				jqTds[11].innerHTML = '<a class="edit" href="">保存</a>';
				jqTds[12].innerHTML = '<a class="cancel" href="">取消</a>';
			}

			function saveRow(oTable, nRow) {
				var jqInputs = $('input', nRow);
				var flag = false;
				var location = (window.location+'').split('/');  
				var basePath = location[0]+'//'+location[2]+'/'+location[3];
				var url = basePath + "/setting/updateGlobalParamsAjax.action";
				$.ajax({
					type : "POST",
					'url' : url,
					async : false,
					data : {
						cashRatio : jqInputs[0].value,
						yljRatio : jqInputs[1].value,
						/*totalReCash : jqInputs[2].value,*/
						cashByOneDlb : jqInputs[2].value,
						vipJfRatio : jqInputs[3].value,
						busJfRatio : jqInputs[4].value,
						oneDlbTotalIncom : jqInputs[5].value,
						checkLimitAmount : jqInputs[6].value,
						withdrawFlag : jqInputs[7].value,
						withdrawUpLimit : jqInputs[8].value,
						inviterSuccessGiftJf : jqInputs[9].value,
						beInviterSuccessGiftJf : jqInputs[10].value
					},
					success : function(b) {
						var c = $.parseJSON(b);
						if (c.resultCode != "0") {
							alert(c.resultMsg, {
								icon : 5
							});
							flag = false;
							return;
						}
						if (c.logicCode != "0") {
							alert(c.resultMsg, {
								icon : 5
							});
							flag = false;
							return;
						}
						alert('操作成功', {
							icon : 5
						});
						oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
						oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
						oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
						oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
						oTable.fnUpdate(jqInputs[4].value, nRow, 4, false);
						oTable.fnUpdate(jqInputs[5].value, nRow, 5, false);
						oTable.fnUpdate(jqInputs[6].value, nRow, 6, false);
						
						oTable.fnUpdate(jqInputs[7].value, nRow, 7, false);
						oTable.fnUpdate(jqInputs[8].value, nRow, 8, false);
						oTable.fnUpdate(jqInputs[9].value, nRow, 9, false);
						oTable.fnUpdate(jqInputs[10].value, nRow, 10, false);
						/*oTable.fnUpdate(jqInputs[7].value, nRow, 7, false);*/
						oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow,
								11, false);
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
				oTable.fnUpdate('<a class="edit" href="">编辑</a>', nRow, 4,
						false);
				oTable.fnDraw();
			}

			var oTable = $('#editable-sample-setting')
					.dataTable(
							{
								"aLengthMenu" : [ [ 5, 15, 20, -1 ],
										[ 5, 15, 20, "All" ] // change per
								// page values
								// here
								],
								// set the initial value
								"iDisplayLength" : 5,
								"sDom" : "<'row'<'col-lg-6'l><'col-lg-6'f>r>t<'row'<'col-lg-6'i><'col-lg-6'p>>",
								"sPaginationType" : "bootstrap",
								"oLanguage" : {
									"sLengthMenu" : "_MENU_ records per page",
									"oPaginate" : {
										"sPrevious" : "Prev",
										"sNext" : "Next"
									}
								},
								"aoColumnDefs" : [ {
									'bSortable' : false,
									'aTargets' : [ 8 ]
								} ]
							});

			jQuery('#editable-sample_wrapper .dataTables_filter input')
					.addClass("form-control medium"); // modify
			// table
			// search
			// input
			jQuery('#editable-sample_wrapper .dataTables_length select')
					.addClass("form-control xsmall"); // modify
			// table
			// per
			var nEditing = null;
			$('#editable-sample-setting a.cancel').live('click', function(e) {
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

			$('#editable-sample-setting a.edit').live('click', function(e) {
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
					flag = saveRow(oTable, nEditing);
					if (flag == true)
						nEditing = null;
					// alert("Updated! Do not forget to do some ajax to sync
					// with backend :)");
				} else {
					/* No edit in progress - let's start one */
					editRow(oTable, nRow);
					nEditing = nRow;
				}
			});
		}

	};

}();