package com.codeLib;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class SetCommonFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String path = request.getRequestURI();
		System.out.println(path);
		HttpSession session = request.getSession();
		Integer uid = (Integer) session.getAttribute("uid");
		System.out.println(uid + ":uid");
		arg2.doFilter(request, response);
//		if (path.indexOf("/index.jsp") > -1 || path.indexOf("/blog.jsp") > -1) {
//			arg2.doFilter(request, response);
//		} else if (uid != null) {
//			arg2.doFilter(request, response);
//		} else {
//
//			if (path.indexOf("/index.jsp") > -1) {
//				response.sendRedirect("index.jsp");
//			} else if (path.indexOf("/blog_edit.jsp") > -1||path.indexOf("/UserServlet.do") > -1) {
//				response.sendRedirect("blog.jsp");
//			} else {
//				arg2.doFilter(request, response);
//			}
//
//		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
