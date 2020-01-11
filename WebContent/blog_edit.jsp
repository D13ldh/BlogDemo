<%@ page language="java" pageEncoding="utf-8"
	import="java.sql.*,com.Haige.Myblog.Service.*,com.Haige.Myblog.VO.*,java.util.*"%>
<%
	Integer uid = (Integer) session.getAttribute("uid");
	if (uid == null) {
		response.sendRedirect("index.jsp");
		return;
	}
	VO4login obj = (VO4login) session.getAttribute("obj");
	UserService us = UserService.getService();
	int BlogId = Integer.parseInt(request.getParameter("id"));
%>
<!DOCTYPE html>
<html>

<head>
<!--网站标题-->
<title><%=obj.getName()%>的个人博客</title>
<meta charset="utf-8">
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
				<form action="blog_search.jsp" method="post">
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
			<!--mian-content begin-->
			<div class="main-content">
				<!-- mainform begin -->
				<div class="ar_title">修改博文</div>

				<div class="mainform_box">
					<%
						VO4BlogEdit tmp = us.getArticleMsg(BlogId);
					%>
					<form action="UserServlet.do?b_id=<%=BlogId%>&flag=edit" method="post">
					
						<label>标题： </label>
						<p>
							<input type="text" name="title" class="text"
								value="<%=tmp.getTitle()%>" />
						</p>

						<label>分类： </label>
						<p>
							<select name="type" class="select">

								<%
									VO4AllTypeName[] list = UserService.getService().getAllType();
									for (VO4AllTypeName type : list) {
										if (type.getTypeId() == tmp.getTypeId()) {%>
										<option value="<%=type.getTypeId()%>" selected>
									<%=type.getName()%></option>
									<% 	
										}else{
								%>

								<option value="<%=type.getTypeId()%>"><%=type.getName()%></option>

								<%
									}
									}
								%>

							</select>
						</p>
						<label>摘要： </label>
						<p>
							<textarea name="intro" class="textarea1"><%=tmp.getIntro()%></textarea>
						</p>

						<label>内容： </label>
						<p>
							<textarea name="content" class="textarea2"><%=tmp.getContent()%></textarea>
						</p>

						<p>
							<input type="submit" value="提交" class="btn" />
						</p>
					</form>

				</div>
				<!-- mainform end -->
			</div>
			<!--main-content end-->

			<!--main sidebar begin-->
			<div class="main-sidebar">
				<!-- 博文分类 begin -->
				<div class="side_box">
					<div class="side_title">
						博文分类 <a href="type_add.jsp"
							style="text-decoration: none; color: #427A24">【新建分类】</a>

					</div>
					<div class="side_list"></div>
				</div>
				<!-- 博文分类 end -->

				<!-- 热门博文 begin -->
				<div class="side_box">
					<div class="side_title">热门博文</div>
					<div class="side_list"></div>
				</div>
				<!-- 热门博文 end -->
			</div>
			<!--main-sidebar end-->

			<div class="clear"></div>
		</div>
		<!--main-box end-->

		<!--main-bottom begin-->
		<div class="main-bottom">Copyright &copy; <%=obj.getName()%>个人博客网站 All
			rights reserved</div>
		<!--mian-bottom end-->
	</div>
	<!--main end-->
</body>

</html>
