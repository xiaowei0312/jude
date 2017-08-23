(function(c) {
	c.fn.pager = function(d) {
		var e = c.extend({}, c.fn.pager.defaults, d);
		return this.each(function() {
			if (d == null) {
				return
			}
			c(this).empty().append(b(parseInt(d.pagenumber), parseInt(d.pagecount), parseInt(d.totalRecords), d.buttonClickCallback))
		})
	};

	function b(g, f, i, l) {
		var e = c('<div class="mod_body list_page"></div>');
		e.append(a("首页", g, f, l)).append(a("上一页", g, f, l));
		var d = 1;
		var j = 9;
		if (g > 4) {
			d = g - 4;
			j = g + 4
		}
		if (j > f) {
			d = f - 8;
			j = f
		}
		if (d < 1) {
			d = 1
		}
		for (var h = d; h <= j; h++) {
			var k;
			if (h == g) {
				k = c("<span>" + (h) + "</span>")
			} else {
				k = c('<a href="javascript:void(0)">' + (h) + "</a>");
				k.click(function() {
					l(this.firstChild.data)
				})
			}
			k.appendTo(e)
		}
		e.append(a("下一页", g, f, l)).append(a("末页", g, f, l));
		if (i && i > 0) {
			e.append("共<i style='font-weight: bold;font-style: normal;'>" + f + "</i>页")
		}
		return e
	}
	function a(h, d, g, f) {
		var e = c('<a href="javascript:void(0)">' + h + "</a>");
		var i = 1;
		switch (h) {
		case "首页":
			i = 1;
			break;
		case "上一页":
			i = d - 1;
			break;
		case "下一页":
			i = d + 1;
			break;
		case "末页":
			i = g;
			break
		}
		if (h == "首页" || h == "上一页") {
			d <= 1 ? e.addClass("pgEmpty") : e.click(function() {
				f(i)
			})
		} else {
			d >= g ? e.addClass("pgEmpty") : e.click(function() {
				f(i)
			})
		}
		return e
	}
	c.fn.pager.defaults = {
		pagenumber: 1,
		pagecount: 1
	}
})(jQuery);