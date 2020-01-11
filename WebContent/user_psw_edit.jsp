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
				<div class="ar_title">修改登录密码</div>

				<div class="mainform_box">
					<form action="UserServlet.do?flag=changPWD" method="post">
						<label>输入旧密码：</label>
						<input type="hidden" name="name" value="<%=obj.getName()%>">
						<input type="hidden" name="id" value="<%=obj.getUseId()%>">
						<p>
							<input type="password" name="pass1" class="text" />
						</p>
						<%
						String rtn = request.getParameter("rtn");
						if(rtn!=null){
							if (rtn.equals("0")) {
						%>
						<p style="color: red;">密码错误</p>
						<%
							}}
						%>
						<label>输入新密码：</label>
						<p>
							<input type="password" name="pass2" class="text" />
						</p>

						<label>确认新密码：</label>
						<p>
							<input type="password" name="pass3" class="text" />
						</p>
						<%
						if(rtn!=null){
							if (rtn.equals("2")) {
						%>
						<p style="color: red;">两次密码不一致</p>
						<%
							}}
						%>
						<p>
							<input type="submit" value="提交" class="btn" />
						</p>
					</form>
				</div>
				<!--mainform end-->
			</div>
			<!--main-content end-->


			<!--用户中心 begin-->
			<div class="side_box">
				<div class="side_title">用户中心</div>
				<div class="ucenter_box">
					<p>
						博主<%=obj.getName()%>，欢迎您登录！
					</p>
					<p>
						<a href="user_info_edit.jsp">个人资料修改</a>
					</p>
					<p>
						<a href="user_psw_edit.jsp">登录密码修改</a>
					</p>
					<p>
						<a href="blog.jsp">博文管理</a>
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
	<div class="main-bottom">
		Copyright &copy; 2015
		<%=obj.getName()%>个人博客网站 All rights reserved
	</div>
	<!--main-bottom end-->
	</div>
	<!--main end-->
</body>
</html>