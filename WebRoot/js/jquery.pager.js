(function(c){c.fn.pager=function(d){var e=c.extend({},c.fn.pager.defaults,d);return this.each(function(){c(this).empty().append(b(parseInt(d.pagenumber),parseInt(d.pagecount),parseInt(d.totalRecords),d.buttonClickCallback));c(this).find('input[type="text"]').val(d.pagenumber);c(this).find('input[type="text"]').keydown(function(f){if(f.keyCode==13){var g=c(this).val();if(!checkNumByValue(g)||parseInt(g)>d.pagecount){c(this).val("1");g=1}d.buttonClickCallback(g)}})})};function b(d,f,h,e){var g=c('<div class="s-pages"></div>');g.append(a("首页",d,f,e)).append(a("上一页",d,f,e));if(h&&h>0){g.append('<strong class="pageNum totalRecord f-14 f-orange1">'+h+'</strong>&nbsp;&nbsp;条记录，&nbsp;&nbsp;<span class="pageNum currPage">'+d+'</span>/<span class="pageNum totalPage">'+f+"</span>&nbsp;&nbsp;")}g.append(a("下一页",d,f,e)).append(a("末页",d,f,e));g.append('跳转至<input type="text" class="pageNum currPage text-box" value="1" maxlength="8" onKeyDown="javascript:return checkNumByEvent(event);">页');return g}function a(i,d,h,f){var g=null;switch(i){case"首页":g="btn first";break;case"上一页":g="btn previous";break;case"下一页":g="btn next";break;case"末页":g="btn last";break}var e=c('<a href="javascript:void(0);" title="'+i+'"></a>');if(g){e.addClass(g)}var j=1;switch(i){case"首页":j=1;break;case"上一页":j=d-1;break;case"下一页":j=d+1;break;case"末页":j=h;break}if(i=="首页"||i=="上一页"){d<=1?e.unbind("click"):e.click(function(){f(j)})}else{d>=h?e.unbind("click"):e.click(function(){f(j)})}return e}c.fn.pager.defaults={pagenumber:1,pagecount:1}})(jQuery);function checkNumByEvent(b){var a=0;if(window.event){a=b.keyCode}else{if(b.which){a=b.which}}if(a==8||a==13||a==37||a==39||a==46||(a>=48&&a<=57)||(a>=96&&a<=105)){return true}return false}function checkNumByValue(b){var a=/^[1-9][0-9]*$/;return a.test(b)};