package com.Haige.Myblog.Servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DemoServlet extends HttpServlet{

    public void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		session.setAttribute("un", "Dee");
		String s1 = (String)session.getAttribute("un");
		System.out.println("s1----"+s1);
	
		response.setCharacterEncoding("UTF-8");   
		response.setContentType("text/html; charset=UTF-8");  
		PrintWriter out = response.getWriter();
		out.println("<html><head></head><body>这是一个servlet！</body></html>");
		out.close();
		
		
		   
	

		
		
		session.invalidate();  
		String s = (String)session.getAttribute("un");
		System.out.println("s-----"+s);
	  
	  
//		response.sendRedirect("login.do");    
		return;
    }
  
    public void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException,IOException{
		doGet(request,response);
    }  
}
