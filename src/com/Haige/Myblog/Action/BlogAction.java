package com.Haige.Myblog.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Haige.Myblog.Service.UserService;
import com.Haige.Myblog.VO.*;
import com.Haige.Myblog.mvc.impl.InfoHandler;

public class BlogAction {
	public int addBlogType(InfoHandler info, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
		UserService ds = UserService.getService();
		VO4TypeAdd vo = (VO4TypeAdd) info.getVO();
        int rtn = ds.addBlogType(vo);
        response.sendRedirect("blog.jsp");
        return rtn;
    }
	public int editBlogType(InfoHandler info, HttpServletRequest request, HttpServletResponse response)throws Exception{
		UserService ds = UserService.getService();
		VO4TypeEdit vo = (VO4TypeEdit) info.getVO();
        int rtn = ds.editBlogType(vo);
        response.sendRedirect("blog.jsp");
        return rtn;
	}
	public VO4AllTypeName[] getAllBlogType(HttpServletRequest request, HttpServletResponse response)throws Exception{
		UserService ds = UserService.getService();
        VO4AllTypeName[] rtn = ds.getAllType();
        return rtn;
	}
}
