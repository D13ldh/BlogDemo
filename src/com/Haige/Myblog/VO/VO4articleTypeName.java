package com.Haige.Myblog.VO;

import com.Haige.Myblog.DAO.mb_blog_typeDAO;

public class VO4articleTypeName {
	public String getTypeName(int typeID) {
		return new mb_blog_typeDAO().findById(typeID).getTypeName();
	}
}
