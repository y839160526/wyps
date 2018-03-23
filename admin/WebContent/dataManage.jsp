<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
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
<link href="index/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript">
	function logout(){
		location.href='logout.htm';
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
			<a class="logo" title="美洲豹浏览器">美洲豹浏览器</a><a class="nav-link"
				href="aboutus.jsp">关于我们</a><a class="nav-link " href="helper.jsp">使用教程</a><a
				class="nav-link cur" id="log">采集管理</a><a class="nav-link"
				href="index.jsp">首页</a>
		</div>
	</div>
	<!-- banner -->
	<div class="banner" id="fun-header">
		<div class="pro-info clearfix">
			<p style="float: right;">
				<button type="button" class="btn btn-outline btn-info" onclick='logout()'>退出登录</button>

			</p>
			<table class="table table-striped table-bordered table-hover "
				id="editable" style="table-layout: fixed;">
				<thead>
					<tr>
						<th>标题</th>
						<th>路径</th>
						<th>字段</th>
						<th>数据</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="pageConfList" var="list">
						<tr class="gradeX">
							<td><s:property value="#list.title" /></td>
							<td style="word-break: break-all;"><s:property
									value="#list.targetUrl" /></td>
							<td class="center"><s:property
									value="#list.fieldsName.replace(\"{{{{{}}}}}\", \",\")" /></td>
							<td class="center"><s:property value="#list.dataCount" /></td>
							<td class="center"><a type="button"
								href='pageConf.htm?id=<s:property value="#list.id" />'
								class="btn btn-primary">进入</a></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
	<!-- main -->
	<!-- footer -->

	<script type="text/javascript" src="./index/jquery.js"></script>

</body>
</html>