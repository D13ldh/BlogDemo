package com.Haige.Myblog.Servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import com.Haige.Myblog.mvc.ParseXml;
import com.Haige.Myblog.mvc.impl.InfoHandler;
import com.alibaba.fastjson.JSON;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		try {
			do_dispatcher(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void do_dispatcher(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 请求路径，对应表单的Action 属性
		String action = request.getServletPath();
		System.out.println("ServletPath------" + action);
		
		String pagePath = request.getHeader("Referer");
		System.out.println("pagePath-------"+pagePath);
		if (action != null & pagePath != null) {
			// 存在action 和来源页面
			if (pagePath.indexOf('?') > -1) {
				// 存在query string
				pagePath = pagePath.substring(pagePath.lastIndexOf('/') + 1, pagePath.lastIndexOf('?'));
			} else {
				pagePath = pagePath.substring(pagePath.lastIndexOf('/') + 1);
			}
		}
		ParseXml parseXml = new ParseXml();
		parseXml.read("E:\\java\\L5\\src\\config.xml");

		// 处理action的格式, 去掉后缀.do
		action = action.substring(1, action.indexOf(".do"));
		parseXml.setAction(action);
		parseXml.setPagePath(pagePath);
		parseXml.parse();
		String voId = parseXml.getVoId();
		String voClass = parseXml.getVoClass();
		String method = parseXml.getMethod();
		String forwardJspPath = parseXml.getForwardJspPath();
		InfoHandler infoHandler = new InfoHandler(request, response, voId, voClass);
		Class actionClass = Class.forName(parseXml.getActionClass());
		Object obj = actionClass.newInstance();
		Object rtn = null;
		if (parseXml.getVoClass() != null) {
			// xml有配置VO
			Class VoClass = Class.forName(parseXml.getVoClass());
			Method actionMethod = actionClass.getMethod(method,
					new Class[] { InfoHandler.class, HttpServletRequest.class, HttpServletResponse.class });
			rtn = actionMethod.invoke(obj, infoHandler, request, response);
			System.out.println("rtn1111111--------------" + rtn);

		} else {
			// 没有配置vo
			Method actionMethod = actionClass.getMethod(method,
					new Class[] { HttpServletRequest.class, HttpServletResponse.class });

			rtn = actionMethod.invoke(obj, request, response);

		}

		// 假如有设置forward 就包装infoOut并转发
		String forwardPath = parseXml.getForwardJspPath();
		if (forwardPath != null) {
			request.setAttribute("infoOut", rtn);
			request.getRequestDispatcher(forwardPath).forward(request, response);
		}

		String jsonRtnString = JSON.toJSONString(rtn);
		System.out.println("jsonRtnString : " + jsonRtnString);

		System.out.println("class:  " + actionClass);
		System.out.println("method:  " + method);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
