package com.Haige.Myblog.Servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Haige.Myblog.mvc.ParseXml;
import com.Haige.Myblog.mvc.impl.InfoHandler;

public class InitServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			this.initPage(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void initPage(HttpServletRequest request, HttpServletResponse response) throws Exception, SecurityException {
		ParseXml parseXml = new ParseXml();
		parseXml.read("E:\\java\\L5\\src\\config.xml");
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI------"+RequestURI.substring(RequestURI.lastIndexOf("/")));
		String path = RequestURI.substring(RequestURI.lastIndexOf("/")+1);
		parseXml.setInitPath(path);
		parseXml.parse();
		String method = parseXml.getInitmethod();
		Class InitactionClass = Class.forName(parseXml.getInitActionClass());
		Object obj = InitactionClass.newInstance();
		Method actionMethod = InitactionClass.getMethod(method,
				new Class[] { HttpServletRequest.class, HttpServletResponse.class});
		Object rtn = actionMethod.invoke(obj,request, response);
		HttpSession session = request.getSession();
		session.setAttribute(parseXml.getResource(), rtn);
		request.getRequestDispatcher("/WEB-INF/"+path).forward(request, response);
		

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
