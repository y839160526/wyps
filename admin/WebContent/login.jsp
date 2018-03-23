<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css" href="./css/demo.css">
<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" type="text/css" href="./css/animate-custom.css">
<link rel="stylesheet" href="./js/jBox/Skins2/Blue/jbox.css" />
<script type="text/javascript" src="./js/jquery.min.js"></script>
<script type="text/javascript" src="./js/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="./js/jBox/i18n/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript">
	function login() {
		$.ajax({
			url : "userAction!valiLogin.htm",
			type : 'post',
			datatype : 'json',
			data : $("#loginform").serialize(),
			timeout : 100000,
			success : function(data, status) {
				var json = JSON.parse(data);
				if (json.message == 'faile') {
					$.jBox.error('登录失败', '用户名或密码错误！');
				} else {
					localStorage.userid = json.callbackType;
					location.href = "dataManage.htm";
				}
			},
			fail : function(err, status) {
			}
		})
	}
	function register() {
		var username = $('#usernamesignup').val();
		var email = $('#emailsignup').val();
		var password = $('#passwordsignup').val();
		var repassword = $('#passwordsignup_confirm').val();
		if (username == '' || email == '' || password == '' || repassword == '') {
			return;
		}
		var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if (!myreg.test(email)) {
			alert('提示\n\n请输入有效的E_mail！');

			return false;
		}
		if (password != repassword) {
			alert('提示\n\n两次密码不正确！');

			return false;
		}
		$.ajax({
			url : "userAction!valiName.htm",
			type : 'post',
			datatype : 'json',
			data : 'user.userName=' + username,
			timeout : 100000,
			success : function(data, status) {
				var json = JSON.parse(data);
				if (json.message == 'faile') {
					$.jBox.error('注册失败', '用户名已存在！');
				} else {
					$.ajax({
						url : "userAction!register.htm",
						type : 'post',
						datatype : 'json',
						data : $("#registerform").serialize(),
						timeout : 100000,
						success : function(data, status) {
							var json = JSON.parse(data);
							if (json.message == 'success') {
								location.href = "login.jsp";
							}
						},
						fail : function(err, status) {
						}
					});
				}
			},
			fail : function(err, status) {
			}
		})

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
			<a class="logo" title=""></a><a class="nav-link" href="aboutus.jsp">关于我们</a><a
				class="nav-link" href="helper.jsp">使用教程</a><a class="nav-link"
				href="dataManage.htm" id="log">采集管理</a><a class="nav-link cur">首页</a>
		</div>
	</div>
	<div class="container" style="margin-top: 200px;">
		<section>
			<div id="container_demo">
				<!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
				<a class="hiddenanchor" id="toregister"></a> <a class="hiddenanchor"
					id="tologin"></a>
				<div id="wrapper">
					<div id="login" class="animate form">
						<form action="" id="loginform" autocomplete="on">
							<h1>登录</h1>
							<p>
								<label for="username" class="uname" data-icon="u"> 用户名 </label>
								<input id="username" name="user.userName" required="required"
									type="text" placeholder="myusername or mymail@mail.com" />
							</p>
							<p>
								<label for="password" class="youpasswd" data-icon="p">密码
								</label> <input id="password" name="user.passWord" required="required"
									type="password" placeholder="eg. X8df!90EO" />
							</p>
							<p class="keeplogin">
								<input type="checkbox" name="loginkeeping" id="loginkeeping"
									value="loginkeeping" /> <label for="loginkeeping">保存状态</label>
							</p>
							<p class="login button">
								<input type="button" onclick="login();" value="登录" />
							</p>
							<p class="change_link">
								还没有账号？ <a href="#toregister" class="to_register">注册</a>
							</p>
						</form>
					</div>

					<div id="register" class="animate form">
						<form action="" id="registerform" autocomplete="on">
							<h1>注册</h1>
							<p>
								<label for="usernamesignup" class="uname" data-icon="u">用户名</label>
								<input id="usernamesignup" name="user.userName"
									required="required" type="text"
									placeholder="mysuperusername690" />
							</p>
							<p>
								<label for="emailsignup" class="youmail" data-icon="e">
									邮箱</label> <input id="emailsignup" name="user.email"
									required="required" type="email"
									placeholder="mysupermail@mail.com" />
							</p>
							<p>
								<label for="passwordsignup" class="youpasswd" data-icon="p">密码</label>
								<input id="passwordsignup" name="user.passWord"
									required="required" type="password" placeholder="eg. X8df!90EO" />
							</p>
							<p>
								<label for="passwordsignup_confirm" class="youpasswd"
									data-icon="p">重复密码</label> <input id="passwordsignup_confirm"
									name="" required="required" type="password"
									placeholder="eg. X8df!90EO" />
							</p>
							<p class="signin button">
								<input type="button" onclick="register();" value="注册" />
							</p>
							<p class="change_link">
								已有帐号? <a href="#tologin" class="to_register">登录</a>
							</p>
						</form>
					</div>

				</div>
			</div>
		</section>
	</div>
	<!-- footer -->
	<div class="footer">花田软件&nbsp;copyright&nbsp;©&nbsp;2017</div>
</body>
</html>