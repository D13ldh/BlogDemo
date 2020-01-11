package com.codeLib;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class MyFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
//		HttpServletRequest request = (HttpServletRequest) arg0;
//		HttpServletResponse response = (HttpServletResponse) arg1;
//		HttpSession session = request.getSession();
//
//		String path = request.getRequestURI();
//
//		Integer uid = (Integer) session.getAttribute("userid");
//
//		if (path.indexOf("/index.jsp") > -1) {// 登录页面不过滤
//			arg2.doFilter(arg0, arg1);// 递交给下一个过滤器
//			response.setCharacterEncoding("UTF-8");
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<html><head></head><body>这是一个servlet！</body></html>");
//			return;
//		}
//		// if(path.indexOf("/register.jsp")>-1){//注册页面不过滤
//		// arg2.doFilter(request, response);
//		// return;
//		// }
//
//		if (uid != null) {// 已经登录
//			arg2.doFilter(request, response);// 放行，递交给下一个过滤器
//			PrintWriter out = response.getWriter();
//			out.println("<html><head></head><body>这是一个servlet！</body></html>");
//		} else {
//			response.sendRedirect("index.jsp");
//			session.setAttribute("userid", 123);
//
//		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
//		// TODO Auto-generated method stub
//		if (path.indexOf("/index.jsp") > -1 || path.indexOf("/blog.jsp") > -1) {
//			arg2.doFilter(request, response);
//		}
//		else if (uid != null) {
//			arg2.doFilter(request, response);
//		} else {
//
//			if (path.indexOf("/index.jsp") > -1) {
//				response.sendRedirect("index.jsp");
//			} else if (path.indexOf("/blog_edit.jsp") > -1) {
//				response.sendRedirect("blog.jsp");
//			} else {
//				arg2.doFilter(request, response);
//			}
//
//		}
	}

}
