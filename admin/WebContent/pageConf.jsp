<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
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
<link rel="stylesheet" href="./index/style.css" type="text/css">
<link href="index/css/bootstrap.min.css" rel="stylesheet">
<link href="index/css/font-awesome.min93e3.css" rel="stylesheet">
<!-- jqGrid组件基础样式包-必要 -->
<link rel="stylesheet" href="jqgrid/css/ui.jqgrid.css" />

<!-- jqGrid主题包-非必要 -->
<!-- 在jqgrid/css/css这个目录下还有其他的主题包，可以尝试更换看效果 -->
<link rel="stylesheet"
	href="jqgrid/css/css/redmond/jquery-ui-1.8.16.custom.css" />
<link rel="stylesheet" href="./js/jBox/Skins2/Blue/jbox.css" />
<script type="text/javascript" src="./index/jquery.js"></script>
<!-- jqGrid插件包-必要 -->
<script type="text/javascript" src="jqgrid/js/jquery.jqGrid.js"></script>
<script type="text/javascript" src="jqgrid/js/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="./js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="./js/jBox/i18n/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="./index/main.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#confform').find('input').live('blur', function() {
			savedata();
			pageInit();
		});
		pageInit();
	});
	function pageInit() {
		var colModel = ${colNames};
		var coltitle = ${colTitle};
		var id = ${id};
		var title='${pageConf.title}';
		//创建jqGrid组件
		jQuery("#editable").jqGrid({
			url : 'pageData.htm?id=' + id,//组件创建完成之后请求数据的url
			datatype : "json",//请求数据返回的类型。可选json,xml,txt
			colNames : coltitle,//jqGrid的列显示名字
			colModel : colModel,
			rowNum : 10,//一页显示多少条
			rowList : [ 10, 20, 30 ],//可供用户选择一页显示多少条
			pager : '#pager2',//表格页脚的占位符(一般是div)的id
			sortname : 'id',//初始化的时候排序的字段
			sortorder : "desc",//排序方式,可选desc,asc
			mtype : "post",//向后台请求数据的ajax的类型。可选post,get
			viewrecords : true,
			multiselect : true,
			shrinkToFit : true,
			editurl : "navGrid.htm",
			caption : title,//表格的标题名字
			height : 200
		});
		/*创建jqGrid的操作按钮容器*/
		/*可以控制界面上增删改查的按钮是否显示*/
		jQuery("#editable").jqGrid('navGrid', '#pager2', {
			edit : false,
			add : false,
			del : false
		});

	}

	function confpanel(obj) {
		if ($('#confpanel').is(':hidden')) {
			$('#confpanel').show();
			$(obj).text('隐藏配置');
		} else {
			$('#confpanel').hide();
			$(obj).text('展开配置');
		}

	}
	function deletelinne(obj) {
		if ($(obj).parent().parent().find('.fa-minus-square').length > 1) {
			if ($(obj).parent().find('i').length == 2) {
				$(obj).parent().prev().prev().prev().append("<i onclick='addline(this);' style='color: #18ade8; cursor: pointer;' class='fa fa-plus-square'></i>");
			}
			$(obj).parent().prev().prev().remove();
			$(obj).parent().prev().remove();
			$(obj).parent().remove();
			savedata();
		}

	}
	function addline(obj) {
		var html = "<div class='col-sm-3'><input name='fieldname' type='text' value='' class='form-control'></div>";
		html = html + "<div class='col-sm-8' style='padding-right: inherit;'>";
		html = html + "<input name='fieldselector' type='text'	value='' class='form-control'></div>";
		html = html + "<div class='col-sm-1'><i onclick='deletelinne(this);'  style='color: red; margin-right: 10px; cursor: pointer;' class='fa fa-minus-square'></i>";
		html = html + "<i onclick='addline(this);' style='color: #18ade8; cursor: pointer;' class='fa fa-plus-square'></i>";
		$(obj).parent().parent().append(html);
		$(obj).remove();
	}
	function savedata() {
		var id = $.trim($('#startcollectpagedataprocess').val());
		var url = $.trim($('#url').val());
		var listSelector = $.trim($('#listSelector').val());
		var fenye = $('#fenye').val();

		var fenyeselector = $.trim($('#fenyeselector').val());
		if (fenyeselector != '') {
			fenye = fenye + '{{{{{}}}}}' + fenyeselector;
		}
		var fieldname = $("input[name='fieldname']");
		var fieldnamestr = '';
		for (var i = 0; i < fieldname.length; i++) {
			fieldnamestr = fieldnamestr + $.trim($(fieldname[i]).val()) + '{{{{{}}}}}';
		}
		fieldnamestr = fieldnamestr.substring(0, fieldnamestr.lastIndexOf('{{{{{}}}}}'));
		var fieldselectorstr = '';
		var fieldselector = $("input[name='fieldselector']");
		for (var i = 0; i < fieldselector.length; i++) {
			fieldselectorstr = fieldselectorstr + $.trim($(fieldselector[i]).val()) + '{{{{{}}}}}';
		}
		fieldselectorstr = fieldselectorstr.substring(0, fieldselectorstr.lastIndexOf('{{{{{}}}}}'));
		$.ajax({
			url : "updateConf.htm",
			type : 'post',
			datatype : 'json',
			data : {
				'pageConf.id' : id,
				'pageConf.targetUrl' : url,
				'pageConf.listSelector' : listSelector,
				'pageConf.nextSelector' : fenye,
				'pageConf.fieldsName' : fieldnamestr,
				'pageConf.fieldsSelector' : fieldselectorstr
			},
			timeout : 100000,
			success : function(data, status) {
			},
			fail : function(err, status) {
			}
		});
	}
	function refresh() {
		location.reload();
	}
	function deleteData() {
		var submit = function(v, h, f) {
			if (v == 'ok') {
				var ids = $('#editable').jqGrid('getGridParam', 'selarrrow');
				var id = '';
				for (var i = 0; i < ids.length; i++) {
					var rowData = $('#editable').jqGrid('getRowData', ids[i]);
					id =id+ rowData.id + ',';
				}
				id = id.substring(0, id.lastIndexOf(','));
				$.ajax({
					url : "navGrid.htm",
					type : 'post',
					datatype : 'json',
					data : {
						'id' : id
					},
					timeout : 100000,
					success : function(data, status) {
						refresh();
					},
					fail : function(err, status) {
					}
				});
			} else if (v == 'cancel') {

			}
			return true; //close
		};
		$.jBox.confirm("确定删除吗？", "提示", submit);

	}
	function exportExcel() {
		var id = $.trim($('#startcollectpagedataprocess').val());
		location.href = 'exportExcel.htm?id=' + id;
	}
</script>
<style type="text/css">
DIV.viciao {
	MARGIN-TOP: 20px;
	MARGIN-BOTTOM: 10px
}

DIV.viciao A {
	BORDER-RIGHT: #8db5d7 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #8db5d7 1px solid;
	PADDING-LEFT: 5px;
	PADDING-BOTTOM: 2px;
	BORDER-LEFT: #8db5d7 1px solid;
	COLOR: #000;
	MARGIN-RIGHT: 2px;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #8db5d7 1px solid;
	TEXT-DECORATION: none
}

DIV.viciao A:hover {
	BORDER-RIGHT: red 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: red 1px solid;
	PADDING-LEFT: 5px;
	PADDING-BOTTOM: 2px;
	BORDER-LEFT: red 1px solid;
	MARGIN-RIGHT: 2px;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: red 1px solid
}

DIV.viciao A:active {
	BORDER-RIGHT: red 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: red 1px solid;
	PADDING-LEFT: 5px;
	PADDING-BOTTOM: 2px;
	BORDER-LEFT: red 1px solid;
	MARGIN-RIGHT: 2px;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: red 1px solid
}

DIV.viciao SPAN.current {
	BORDER-RIGHT: #e89954 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #e89954 1px solid;
	PADDING-LEFT: 5px;
	FONT-WEIGHT: bold;
	PADDING-BOTTOM: 2px;
	BORDER-LEFT: #e89954 1px solid;
	COLOR: #000;
	MARGIN-RIGHT: 2px;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #e89954 1px solid;
	BACKGROUND-COLOR: #ffca7d
}

DIV.viciao SPAN.disabled {
	BORDER-RIGHT: #ccc 1px solid;
	PADDING-RIGHT: 5px;
	BORDER-TOP: #ccc 1px solid;
	PADDING-LEFT: 5px;
	PADDING-BOTTOM: 2px;
	BORDER-LEFT: #ccc 1px solid;
	COLOR: #ccc;
	MARGIN-RIGHT: 2px;
	PADDING-TOP: 2px;
	BORDER-BOTTOM: #ccc 1px solid
}
</style>
</head>

<body style="background: #fff url(images/bg.jpg) repeat top left;">
	<!-- 微信打开提示分享 -->
	<div id="wxTips">
		<img src="./index/tip_mask.png">
	</div>
	<!-- header -->
	<div class="header">
		<h1></h1>
		<div class="nav">
			<a class="logo" title=""></a><a class="nav-link"  href="aboutus.jsp" >关于我们</a><a
				class="nav-link" href="helper.jsp">使用教程</a><a class="nav-link cur"
				id="log" href='dataManage.htm'>采集管理</a><a href="index.jsp"
				class="nav-link ">首页</a>
		</div>
	</div>
	<!-- banner -->
	<div class="banner" id="fun-header" style="overflow:auto; ">
		<div class="pro-info clearfix" >
			<div class="row">
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<p>
							<button type="button" class="btn btn-outline btn-info"
								id='restartcollectpagedataprocess'>重新配置</button>
							<button type="button" class="btn btn-outline btn-info"
								onclick='confpanel(this);'>隐藏配置</button>
						</p>

						<div class="ibox-content" id='confpanel'
							style="display: none; margin-bottom: 5px; border-style: solid; border-color: #d8d8d8; padding: 10px;">
							<form id='confform' method="get" class="form-horizontal">
								<div class="form-group">
									<label class="col-sm-3 control-label">采集入口地址</label>

									<div class="col-sm-9">
										<input type="text" id='url'
											value="<s:property value="pageConf.targetUrl" />"
											class="form-control">
									</div>
									<label class="col-sm-3 control-label">列表页规则</label>

									<div class="col-sm-9">
										<input type="text" id='listSelector'
											value="<s:property value="pageConf.listSelector" />"
											class="form-control">
									</div>
									<label class="col-sm-3 control-label">分页规则</label>
									<div class="col-sm-2">
										<select onchange="savedata();" class="form-control m-b"
											id="fenye">
											<option value="下一页"
												<s:if test='pageConfDto.fenye==0'>selected='selected'</s:if>>下一页</option>
											<option value='>'
												<s:if test="pageConfDto.fenye==1">selected='selected'</s:if>>></option>
											<option value=">>"
												<s:if test="pageConfDto.fenye==2">selected='selected'</s:if>>>></option>
										</select>
									</div>
									<div class="col-sm-7">
										<input type="text" id='fenyeselector'
											placeholder="分页的cssselector,一般情况不需要填写"
											value="<s:property value="pageConfDto.nextSelector" />"
											class="form-control">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">字段名</label>

									<div class="col-sm-9">
										<label class="col-sm-3 control-label">字段配置</label>
									</div>
									<s:iterator value="pageConfDto.fieldsName" var="nameListArr"
										status="status">
										<div class="col-sm-3">
											<input type="text" name="fieldname"
												value="<s:property value="#nameListArr" />"
												class="form-control">
										</div>
										<div class="col-sm-8" style="padding-right: inherit;">
											<input type="text" name="fieldselector"
												value="<s:property value="pageConfDto.fieldsSelector[#status.index]"/>"
												class="form-control">
										</div>
										<div class="col-sm-1">
											<i onclick="deletelinne(this);"
												style="color: red; margin-right: 10px; cursor: pointer;"
												class="fa fa-minus-square"></i>
											<s:if test="#status.last">
												<i onclick="addline(this);"
													style="color: #18ade8; cursor: pointer;"
													class="fa fa-plus-square"></i>
											</s:if>

										</div>
									</s:iterator>

								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
			<p>
				<button type="button" id='startcollectpagedataprocess'
					value='<s:property value="pageConf.id" />'
					class="btn btn-outline btn-primary">开始采集</button>
				<button type="button" id='stopcollectpagedataprocess'
					value='<s:property value="pageConf.id" />'
					class="btn btn-outline btn-primary disabled">停止采集</button>
				<button type="button" class="btn btn-outline btn-success"
					onclick="refresh();">刷新</button>
				<button type="button" class="btn btn-outline btn-info"
					onclick="exportExcel();">导出</button>
				<button type="button" class="btn btn-outline btn-danger"
					onclick="deleteData();">删除</button>
			</p>
			<table class="table table-striped table-bordered table-hover "
				id="editable">
				<thead>
					<tr>
						<th><input type="checkbox" /></th>
						<s:iterator value="pageConfDto.fieldsName" var="nameListArr">
							<th><s:property value="#nameListArr" /></th>
						</s:iterator>

					</tr>
				</thead>
				<tbody>
					<s:iterator value="valueList" var="valueList" status="status">
						<tr class="gradeX">
							<td><input type="checkbox" /></td>
							<s:iterator value="#valueList" var="list">
								<td><s:property value="#list" /></td>
							</s:iterator>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<div id="pager2"></div>
		</div>
	</div>
</body>
</html>