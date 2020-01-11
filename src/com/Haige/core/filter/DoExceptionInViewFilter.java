package com.Haige.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;

import com.Haige.common.exception.BaseError;
import com.Haige.common.exception.BaseException;

/**
 * Exception统一捕捉处理过滤器
 * @author leeyn
 *
 */
public class DoExceptionInViewFilter implements javax.servlet.Filter {
	static Logger logger = Logger.getLogger(DoExceptionInViewFilter.class);
	/**
	 *当请求到达时，会首先被此拦截器拦截，当数据经过获取并在V层显示完毕(响应完毕)后，
	 *又回到此Filter内部，途中如果下层有异常抛出，在这里进行拦截捕捉，并统一处理
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String error=null;
		try{
			chain.doFilter(request, response);	
		}catch(BaseError err){				
			if(err.getAdminMessage()!=null) {
				logger.error(err.getAdminMessage());
			}
			if(err.getUserMessage()!=null) {
				error = err.getUserMessage();	
			}
		}catch(BaseException err){
			if(err.getAdminMessage()!=null) {
				logger.error(err.getAdminMessage());
			}
			if(err.getUserMessage()!=null) {
				error = err.getUserMessage();	
			}
		}catch(Exception err){					//某些还未能考虑到的异常	
			error = "系统异常"+err;
		}catch(Error err){						//某些还未能考虑到的错误
			error = "系统错误"+err;
		}
		
		//带上error信息跳转到error.jsp
		//...
		if(error!=null) {
			request.setAttribute("error", error);
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	
	}

	public void destroy() {
	
	}
}