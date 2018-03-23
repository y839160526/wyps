<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<!-- saved from url=(0058)http://www.17sucai.com/preview/1/2017-04-27/web/index.html -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">

<title>网页小强</title>
<meta name="description" content=" ">
<meta name="keywords" content=" ">
<link rel="Shortcut Icon"
	href="http://www.17sucai.com/preview/1/2017-04-27/web/favicon.ico">
<link rel="stylesheet" href="./index/style.css" type="text/css">
<link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
<script type="text/javascript" src="./index/jquery.js"></script>
<script type="text/javascript"
	src="http://img3.job1001.com/js/ZeroClipboard/jquery.zclip.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
        var baseUri='<%=basePath%>';
		var savePath = $('#savePath').val();
		if (savePath != '') {
			$('#progressbar').show();
			$.ajax({
				url : "uploadDocsFileToPdf.htm",
				type : 'post',
				datatype : 'json',
				data : {
					'savePath' : savePath
				},
				timeout : 100000,
				success : function(data, status) {
					var data = JSON.parse(data);
					$('#downloadurl').val(baseUri + data.downloadurl);
					$('#savePath').val('');
					$('#progressbar').hide();
					$('#downloadurl').parent().show();
				},
				fail : function(err, status) {
				}
			});
		}
		      $("#file").change(function () {
		        var filepath = $("input[name='image']").val();
		        var extStart = filepath.lastIndexOf(".");
		        var ext = filepath.substring(extStart, filepath.length).toUpperCase();
		        if (ext != ".DOC" && ext != ".DOCX" && ext != ".PPTX" && ext != ".PPT" && ext != ".ODT") {
		          alert("限于doc,docx,ppt,pptx,odt格式");
		          $("#file").val('');
		          return false;
		        } else { $("#fileType").text(ext) }
		        var file_size = 0;
		        if ($.browser.msie) {
		          var img = new Image();
		          img.src = filepath;
		          while (true) {
		            if (img.fileSize > 0) {
		              if (img.fileSize > 3 * 1024 * 1024) {
		                alert("文档不大于100MB。");
		              } else {
		                var num03 = img.fileSize / 1024;
		                num04 = num03.toFixed(2)
		               
		              }
		              break;
		            }
		          }
		        } else {
		          file_size = this.files[0].size;
		          var size = file_size / 1024;
		          if (size > 10240) {
		            alert("上传的文档大小不能超过10M！");
		          } else {
		            var num01 = file_size / 1024;
		            num02 = num01.toFixed(2);
		            $("#fileSize").text(num02 + " KB");
		          }
		        }
		        return true;
		      });
	});
	function CopyToClipboard() {
		var textToClipboard = $('#downloadurl').val();

		var success = true;
		if (window.clipboardData) { // Internet Explorer
			window.clipboardData.setData("Text", textToClipboard);
		} else {
			// create a temporary element for the execCommand method
			var forExecElement = CreateElementForExecCommand(textToClipboard);

			/* Select the contents of the element 
			    (the execCommand for 'copy' method works on the selection) */
			SelectContent(forExecElement);

			var supported = true;

			// UniversalXPConnect privilege is required for clipboard access in Firefox
			try {
				if (window.netscape && netscape.security) {
					netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
				}

				// Copy the selected content to the clipboard
				// Works in Firefox and in Safari before version 5
				success = document.execCommand("copy", false, null);
			} catch (e) {
				success = false;
			}

			// remove the temporary element
			document.body.removeChild(forExecElement);
		}

		if (success) {
			alert("复制成功！");
		} else {
			alert("浏览器不支持复制功能!");
		}
	}

	function CreateElementForExecCommand(textToClipboard) {
		var forExecElement = document.createElement("div");
		// place outside the visible area
		forExecElement.style.position = "absolute";
		forExecElement.style.left = "-10000px";
		forExecElement.style.top = "-10000px";
		// write the necessary text into the element and append to the document
		forExecElement.textContent = textToClipboard;
		document.body.appendChild(forExecElement);
		// the contentEditable mode is necessary for the  execCommand method in Firefox
		forExecElement.contentEditable = true;

		return forExecElement;
	}

	function SelectContent(element) {
		// first create a range
		var rangeToSelect = document.createRange();
		rangeToSelect.selectNodeContents(element);

		// select the contents
		var selection = window.getSelection();
		selection.removeAllRanges();
		selection.addRange(rangeToSelect);
	}
	function upload() {
		var file = $('#file').val();
		if (file != '') {
			$('#form').submit();
		}

	}
</script>
</head>

<body>
	<!-- 微信打开提示分享 -->
	<div id="wxTips">
		<img src="./index/tip_mask.png">
	</div>
	<!-- header -->
	<div class="header">
		<h1>美洲豹浏览器-手机版</h1>
		<div class="nav">
			<a class="logo" title=""></a><a class="nav-link " href="aboutus.jsp">关于我们</a><a
				class="nav-link cur">使用教程</a><a class="nav-link"
				href="dataManage.htm" id="log">采集管理</a><a class="nav-link ">首页</a>
		</div>
	</div>
	<div class="banner" id="fun-header">
		<div class="pro-info clearfix" style="font-size: larger;">
			<div class="ibox-content">
				<div class="row">
					<div class="col-sm-6 b-r">
						<h3 class="m-t-none m-b">word、ppt、ODT格式转pdf</h3>
						<p>请选择上传的文件</p>
						<form role="form" id='form' action="uploadDocsFile.htm"
							enctype="multipart/form-data" method="post">
							<div class="form-group">
								<input class="form-control" type="file" id='file' name="image">
							</div>
							<div>
								<label> <input
									class="btn btn-sm btn-primary pull-right m-t-n-xs"
									type="button" onclick='upload();' value="上传" /></label>
							</div>
						</form>
					</div>
					<div class="col-sm-6">
						<input id='savePath' value='<s:property value="savePath" />'
							type="hidden" />
						<!-- <h4>还不是会员？</h4>
                                <p>您可以注册一个新账户</p>
                                <p class="text-center">
                                    <a href="form_basic.html"><i class="fa fa-sign-in big-icon"></i></a>
                                </p> -->
					</div>
				</div>
				<div class="progress progress-striped active" style="width: 50%">
					<div style="width: 100%; display: none" id='progressbar'
						aria-valuemax="100" aria-valuemin="0" aria-valuenow="75"
						role="progressbar" class="progress-bar progress-bar-danger">
						<span class="sr-only">40% Complete (success)</span>
					</div>
				</div>

			</div>
			<div class="form-group">
				<div class="col-sm-6">
					<div class="input-group" style="display: none;">
						<input type="text" id='downloadurl' class="form-control">
						<span class="input-group-btn">
							<button type="button" onclick='CopyToClipboard();'
								class="btn btn-primary">复制</button>
						</span>
					</div>
				</div>
			</div>
		</div>
		<!-- footer -->

		<script type="text/javascript" src="./index/jquery.js"></script>
</body>
</html>