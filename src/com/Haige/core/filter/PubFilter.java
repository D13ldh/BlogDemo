package com.Haige.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

/**
 * 公共过滤器
 * 
 * @author leeyn
 *
 */
public class PubFilter implements javax.servlet.Filter {
	static Logger logger = Logger.getLogger(PubFilter.class.getName());

	/**
	 * 当请求到达时，会首先被此拦截器拦截，当数据经过获取并在V层显示完毕(响应完毕)后，
	 * 又回到此Filter内部，途中如果下层有异常抛出，在这里进行拦截捕捉，并统一处理
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpServletRequest Hrequest = (HttpServletRequest) request;
		HttpServletResponse Hresponse = (HttpServletResponse) response;
		String path = Hrequest.getRequestURI();
		HttpSession session = Hrequest.getSession();
		Integer uid = (Integer) session.getAttribute("userid");
		if (path.indexOf("/index.jsp") > -1 || path.indexOf("/blog.jsp") > -1) {
			chain.doFilter(request, response);
		}
		if (uid != null) {
			chain.doFilter(request, response);
		} else {
			if (path.indexOf("/index.jsp") > -1) {
				Hresponse.sendRedirect("index.jsp");
			} else if (path.indexOf("/blog.jsp") > -1) {
				Hresponse.sendRedirect("blog.jsp");
			} else {
				Hresponse.sendRedirect("index.jsp");
			}
		}

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	public void destroy() {

	}
}