<%@ page language="java" contentType="text/html;" pageEncoding="UTF-8"
	import="com.Haige.Myblog.Service.*,com.Haige.Myblog.VO.VO4articleList,java.util.*"%>
<%
	List<VO4articleList> obj = new VisitorService().getList();
%>
