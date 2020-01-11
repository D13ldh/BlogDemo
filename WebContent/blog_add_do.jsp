<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"
	import="com.Haige.Myblog.VO.*"%>
<%
	Integer uid = (Integer) session.getAttribute("uid");
	if (uid == null) {
		response.sendRedirect("index.jsp");
		return;
	}

	request.setCharacterEncoding("UTF-8");

	String b_title = request.getParameter("title");
	String b_type = request.getParameter("type");
	System.out.print(b_type+"-----------type");
	String b_intro = request.getParameter("intro");
	String b_content = request.getParameter("content");
	 if(b_title!=null && !"".equals(b_title)
		        && b_type!=null && !"".equals(b_type)
		        && b_intro!=null && !"".equals(b_intro)
		        && b_content!=null && !"".equals(b_content)
		    ){
		new VO4blog_add(b_title,Integer.parseInt(b_type),b_intro,b_content);
	 }else{
	        //参数为空，跳转到index.jsp并带上参数1表示错误信息
	        System.out.print("添加文章失败");
	    }
%>