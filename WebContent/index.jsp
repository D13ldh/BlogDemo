<%@ page language="java" pageEncoding="utf-8"
	import="java.sql.*,com.Haige.Myblog.VO.*,java.util.*"%>
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
				<a href="index.jsp" class="current">首页</a> <a href="blog.jsp">我的博文</a>
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
				<div class="ar_title">博主介绍</div>
				<div class="ar_content"><%=obj.getIntro()%></div>
			</div>
			<!--main-content end-->

			<!--main-sidebar begin-->
			<div class="main-sidebar">
				<%
					if (!isLogin) {
				%>
				<!--博主登录 begin-->
				<div class="side_box">
					<div class="side_title">博主登录</div>
					<div class="login_box">
						<form action="UserServlet.do" method="post">
							<input name="flag" value="login" type="hidden" /> <label>用户名：</label>
							<input type="text" name="username" class="text" /> <br /> <label>密&nbsp;码：</label>
							<input type="password" name="userpass" class="text" /> <br /> <input
								type="submit" value="登录" class="btn" /> <input type="reset"
								value="重置" class="btn" />

							<%
								String rtn = String.valueOf(request.getParameter("rtn"));
									if ("1".equals(rtn)) {
							%>
							<p>
								<span style="color: red;">用户名和密码不能为空!</span>
							</p>
							<%
								} else if ("2".equals(rtn)) {
							%>
							<p>
								<span style="color: red;">用户名或密码错误!</span>
							</p>
							<%
								}
							%>
						</form>
					</div>
				</div>
				<!--博主登录 end-->
				<%
					} else {
				%>
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
						<p>
							<a href="UserServlet.do?flag=logout">退出</a>
						</p>
					</div>
				</div>
				<!--用户中心 end-->
				<%
					}
				%>
				<!--形象墙 begin-->
				<div class="side_box">
					<div class="side_title">形象墙</div>
					<div class="imagewall_box">
						<img src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1578759160332&di=4e067c13bda5a52c647483594d402139&imgtype=jpg&src=http%3A%2F%2Ft-1.tuzhan.com%2Fcf1419e3145a%2Fc-1%2Fl%2F2012%2F12%2F10%2F04%2F5d9c8229d01a47e8b44b6cca66f59574.jpg" />
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
			Copyright &copy; 2019
			<%=obj.getName()%>个人博客网站 All rights reserved
		</div>
		<!--main-bottom end-->
	</div>
	<!--main end-->
</body>
</html>
<%
	
%>