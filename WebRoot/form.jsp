<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String mallPicImgPath = "/mallImgs/";
	String onlineCommodImgPath = "/onlineCommodImgs/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<base href="<%=basePath%>"></base>
<link rel="shortcut icon" href="#" type="image/png">
<title>Editors</title>
</head>

<body class="sticky-header">

	<div>
		<img src=""
			style="width: 300px; height: 300px" />
		<form id="uploadForm">
			<input type="file" name="upload" /> <input type="button"
				value="submit" onclick="btnClicked();" />
		</form>
	</div>

	<!-- Placed js at the end of the document so the pages load faster -->
	<script src="js/jquery-1.10.2.min.js"></script>

	<script>
		function btnClicked() {
			var formData = new FormData($("#uploadForm")[0]);
			$.ajax({
				type : "POST",
				url : "<%=basePath%>" 
						+ "/ckeditor/imgUploadTest.action",
				data : formData,
				async : true,
				cache : false,
				contentType : false,
				processData : false,
				success : function(e) {
					var a = $.parseJSON(e);
					if (a.logicCode != '0') {
						alert(a.resultMsg);
					} else {
						$("img")[0].src = "<%=onlineCommodImgPath%>"
										+ a.resultMsg;
					}
				},
			});
		}
	</script>
</body>
</html>
