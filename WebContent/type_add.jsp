<%@ page language="java" pageEncoding="utf-8"
	import="java.sql.*,com.Haige.Myblog.Service.*,com.Haige.Myblog.VO.*,java.util.*"%>
<!DOCTYPE html>
<html>
	<head>
		<!--网站标题-->
		<title>李远念的个人博客</title>
		<!--导入css-->
		<link href="css/style.css" rel="stylesheet" type="text/css" />
	</head>
	
	<body>
		<!--top begin-->
		<div class="top">
			<div class="top-box">
				<!--menu begin-->
				<div class="menu">
					<a href="index.html">首页</a>
					<a href="blog.html" class="current">我的博文</a>
				</div>
				<!--menu end-->
				
				<!--search begin-->
				<div class="search">
					<form action="#" method="post">
						<input type="text" name="search_content" class="text"/>
						<input type="submit" value="搜索" class="btn"/>
					</form>
				</div>
				<!--search end-->
			</div>
		</div>
		<!--top end-->
		
		<!--head begin-->
		<div class="head">
			<div class="head-top">
				<div class="title">李远念</div>
				<div class="subtitle">广州海阁信息科技有限公司 海阁软件学院 首席讲师 CEO</div>
			</div>
			<div class="head-bottom">
			
			</div>
		</div>
		<!--head end-->
		
		<!--main begin-->
		<div class="main">
			<!--main-box begin-->
			<div class="main-box">
				<!--main-content begin-->
				<div class="main-content">
					<!--mainform begin-->
					<div class="ar_title">新建分类</div>
					
					<div class="mainform_box">
						<form action="addType.do" method="post">
							<label>分类标题：</label>
							<p><input type="text" name="title_addInfo" class="text"/></p>
							
							<p><input type="submit" value="提交" class="btn"/></p>
						</form>
					</div>
					<!--mainform end-->
				</div>
				<!--main-content end-->
				
				<!--main-sidebar begin-->
				<div class="main-sidebar">
					<!--博文分类 begin-->
					<div class="side_box">
						<div class="side_title">
							博文分类
							<a href="#" style="text-decoration:none;
							color:#427A24">【新建分类】</a>
						</div>
						<div class="side_list">
							<ul>
								<li>
									<a href="#">Java</a> 
									<a href="#" class="a2">【改】</a>
									<a href="#" class="a2">【删】</a>
								</li>
								<li>
									<a href="#">Java EE</a>
									<a href="#" class="a2">【改】</a>
									<a href="#" class="a2">【删】</a>
								</li>
								<li>
									<a href="#">WEB前端技术</a>
									<a href="#" class="a2">【改】</a>
									<a href="#" class="a2">【删】</a>
								</li>
								<li>
									<a href="#">数据库</a>
									<a href="#" class="a2">【改】</a>
									<a href="#" class="a2">【删】</a>
								</li>
								<li>
									<a href="#">服务器</a>
									<a href="#" class="a2">【改】</a>
									<a href="#" class="a2">【删】</a>
								</li>
							</ul>
						</div>
					</div>
					<!--博文分类 end-->
					
					<!--热门博文 begin-->
					<div class="side_box">
						<div class="side_title">
							热门博文
						</div>
						<div class="side_list">
							<ul>
								<li><a href="#">15个前卫的 HTML5 & CSS3 网页设计作品</a></li>
								<li><a href="#">Javascript基础</a></li>
								<li><a href="#">Java GUI的学习心得</a></li>
								<li><a href="#">HTML元素基础学习</a></li>
								<li><a href="#">Tomcat服务器的配置</a></li>
								<li><a href="#">MySQL基本使用</a></li>
								<li><a href="#">Java集合</a></li>
								<li><a href="#">Java多线程</a></li>
								<li><a href="#">Java网络编程</a></li>
								<li><a href="#">Java数据类型</a></li>
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
			<div class="main-bottom">
				Copyright &copy; 2015 李远念个人博客网站 All rights reserved
			</div>
			<!--main-bottom end-->
		</div>
		<!--main end-->
	</body>
</html>