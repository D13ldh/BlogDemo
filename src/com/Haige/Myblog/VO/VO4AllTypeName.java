package com.Haige.Myblog.VO;
 
import java.util.*;

import com.Haige.Myblog.DAO.mb_blog_typeDAO;
import com.Haige.Myblog.DTO.mb_blog_typeDTO;

public class VO4AllTypeName {
	private String Name;
	private int typeId;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
}
