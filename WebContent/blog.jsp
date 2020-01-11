<%@ page language="java" pageEncoding="utf-8"
	import="java.sql.*,com.Haige.Myblog.Service.*,com.Haige.Myblog.VO.*,java.util.*"%>
<%
	// 登录验证
	boolean isLogin = false;
	Integer uid = (Integer) session.getAttribute("uid");
	if (uid != null) {
		isLogin = true;
	}
	List<VO4articleList> list = new VisitorService().getList();
	for(VO4articleList tmp:list){
		System.out.println(((VO4articleList) tmp).getIntro());
	}
	VO4login obj = (VO4login)session.getAttribute("obj");
	if(obj==null){
		obj = new VO4login();
	}
	
	//获取热门博客
		List<VO4BlogList> rank = (List<VO4BlogList>) request.getAttribute("Rank");
	if (rank == null) {
%>
	<jsp:forward page="UserServlet.do?Common=getBlogRank&from=blog.jsp"/>
<%
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
				<a href="index.jsp">首页</a> <a href="blog.jsp" class="current">我的博文</a>
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
				<!--导航 begin-->
				<div class="nav">
					<span>当前：我的博文</span>
					<%
						if (isLogin) {
					%>
					<a href="blog_add.jsp">【新建博文】</a>
					<%
						}
					%>
				</div>
				<!--导航 end-->

				<!--文章列表 begin-->
				<% for(VO4articleList tmp:list){%>
				<div class="ar_list_item">
					<div class="ar_title">
						<a href="blog_view.jsp?id=<%=tmp.getBlogId()%>"><%=tmp.getTitle()%></a>
					</div>
					<div class="ar_desc">
						<span>浏览数【<%=tmp.getViewNums()%>】 <%=tmp.getPostTime().substring(0,16)%></span> 
						<a href="#"><%=new VO4articleTypeName().getTypeName(tmp.getTypeId()) %></a> 
						<a href="blog_edit.jsp?id=<%=tmp.getBlogId()%>">【修改博文】</a> 
						<a href="UserServlet.do?id=<%=tmp.getBlogId()%>&flag=blogDel">【删除博文】</a>
					</div>
					<div class="ar_content"><%=tmp.getIntro() %></div>
				</div>

				<%} %>
				<!--文章列表 end-->
			</div>
			<!--main-content end-->

			<!--main-sidebar begin-->
			<div class="main-sidebar">
				<!--博文分类 begin-->
				<div class="side_box">
					<div class="side_title">
						博文分类 <a href="type_add.jsp" style="text-decoration: none; color: #427A24">【新建分类】</a>
					</div>
					<div class="side_list">
						<ul>
							<li><a href="#">Java</a> <a href="type_edit.jsp?id=1" class="a2">【改】</a> <a
								href="" class="a2">【删】</a></li>
							<li><a href="#">Java EE</a> <a href="#" class="a2">【改】</a> <a
								href="#" class="a2">【删】</a></li>
							<li><a href="#">WEB前端技术</a> <a href="#" class="a2">【改】</a> <a
								href="#" class="a2">【删】</a></li>
							<li><a href="#">数据库</a> <a href="#" class="a2">【改】</a> <a
								href="#" class="a2">【删】</a></li>
							<li><a href="#">服务器</a> <a href="#" class="a2">【改】</a> <a
								href="#" class="a2">【删】</a></li>
						</ul>
					</div>
				</div>
				<!--博文分类 end-->

				<!--热门博文 begin-->
				<div class="side_box">
					<div class="side_title">热门博文</div>
					<div class="side_list">
						<ul>
						<%
						
						for(VO4BlogList tmp:rank){ %>
							<li><a href="blog_view.jsp?id=<%=tmp.getBlogID()%>"><%=tmp.getBlogString() %></a></li>
						<%} %>	
						</ul>
					</div>
				</div>
				<!--热门博文 end-->
			</div>
			<!--main-sidebar end-->

			<div class="clear"></div>
		</div>
		<!--main-box end-->

		<!--main-bottom begin-->
		<div class="main-bottom">Copyright &copy; 2015 <%=obj.getName()%>个人博客网站 All
			rights reserved</div>
		<!--main-bottom end-->
	</div>
	<!--main end-->
</body>
</html>