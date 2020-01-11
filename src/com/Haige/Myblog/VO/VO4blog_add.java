package com.Haige.Myblog.VO;

import com.Haige.Myblog.DAO.mb_blogDAO;
import com.Haige.Myblog.DTO.mb_blogDTO;

public class VO4blog_add {
	public VO4blog_add(String title,int typeId,String blogIntro,String blogContent) {
		mb_blogDTO tmp = new mb_blogDTO();
		tmp.setB_title(title);tmp.setB_typeid(typeId);tmp.setB_intro(blogIntro);
		tmp.setB_content(blogContent);
		new mb_blogDAO().addBlog(tmp);
	}
}
