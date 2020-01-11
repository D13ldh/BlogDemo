<%@ page language="java" pageEncoding="utf-8"
	import="java.sql.*,com.Haige.Myblog.VO.VO4login"%>
<%
	// 登录验证
	boolean isLogin = false;
	Integer uid = (Integer) session.getAttribute("uid");
	if (uid != null) {
		isLogin = true;
	}
	VO4login obj = (VO4login) session.getAttribute("obj");
	if (obj == null) {
		obj = new VO4login();
	}
%>
<!DOCTYPE html>
<html>
<head>
<!--网站标题-->
<title><%=obj.getName()%>的个人博客</title>
<!--导入css-->
<link href="css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<!--top begin-->
	<div class="top">
		<div class="top-box">
			<!--menu begin-->
			<div class="menu">
				<a href="index.html" class="current">首页</a> <a href="blog.html">我的博文</a>
			</div>
			<!--menu end-->

			<!--search begin-->
			<div class="search">
				<form action="#" method="post">
					<input type="text" name="search_content" class="text" /> <input
						type="submit" value="搜索" class="btn" />
				</form>
			</div>
			<!--search end-->
		</div>
	</div>
	<!--top end-->

	<!--head begin-->
	<div class="head">
		<div class="head-top">
			<div class="title"><%=obj.getName()%></div>
			<div class="subtitle"><%=obj.getDesc()%></div>
		</div>
		<div class="head-bottom"></div>
	</div>
	<!--head end-->

	<!--main begin-->
	<div class="main">
		<!--main-box begin-->
		<div class="main-box">
			<!--main-content begin-->
			<div class="main-content">
				<!--mainform begin-->
				<div class="ar_title">修改个人资料</div>

				<div class="mainform_box">
					<form action="UserServlet.do?flag=changInfo" method="post">
						<input type="hidden" name="uid" value="<%=uid%>"> <label>姓名：</label>
						<p>
							<input type="text" name="name" class="text"
								value="<%=obj.getName()%>" />
						</p>

						<label>简介：</label>
						<p>
							<input type="text" name="desc" class="text"
								value="<%=obj.getDesc()%>" />
						</p>

						<label>博主介绍：</label>
						<p>
							<textarea name="intro" class="textarea2"><%=obj.getIntro()%></textarea>
						</p>

						<p>
							<input type="submit" value="提交" class="btn" />
						</p>
					</form>
				</div>
				<!--mainform end-->
			</div>
			<!--main-content end-->

			<!--main-sidebar begin-->
			<div class="main-sidebar">
				<!--用户中心 begin-->
				<div class="side_box">
					<div class="side_title">用户中心</div>
					<div class="ucenter_box">
						<p>博主李远念，欢迎您登录！</p>
						<p>
							<a href="user_info_edit.jsp">个人资料修改</a>
						</p>
						<p>
							<a href="user_psw_edit.jsp">登录密码修改</a>
						</p>
						<p>
							<a href="blog.jsp">博文管理</a>
						</p>
						<p>
							<a href="UserServlet.do?flag=logout">退出</a>
						</p>
					</div>
				</div>
				<!--用户中心 end-->

				<!--形象墙 begin-->
				<div class="side_box">
					<div class="side_title">形象墙</div>
					<div class="imagewall_box">
						<img src="images/img05.png" />
					</div>
				</div>
				<!--形象墙 end-->
			</div>
			<!--main-sidebar end-->

			<div class="clear"></div>
		</div>
		<!--main-box end-->

		<!--main-bottom begin-->
		<div class="main-bottom">Copyright &copy; 2015 李远念个人博客网站 All
			rights reserved</div>
		<!--main-bottom end-->
	</div>
	<!--main end-->
</body>
</html>