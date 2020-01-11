package com.Haige.Myblog.Service;

import java.util.*;

import com.Haige.Myblog.DAO.mb_blogDAO;
import com.Haige.Myblog.DTO.mb_blogDTO;
import com.Haige.Myblog.VO.VO4articleList;

public class VisitorService {
	public List<VO4articleList> getList() {
	
		List<VO4articleList> rtn = new ArrayList<VO4articleList>();
		List<mb_blogDTO>  rs = new mb_blogDAO().findAllBlog();
		
		for(mb_blogDTO tmp:rs) {
			VO4articleList votmp = new VO4articleList();
//			System.out.println(((mb_blogDTO) tmp).getB_intro());
			votmp.setBlogId( tmp.getB_id());
			votmp.setIntro( tmp.getB_intro());
			votmp.setPostTime( tmp.getB_posttime());
			votmp.setTitle( tmp.getB_title());
			votmp.setViewNums( tmp.getB_viewnums());
			votmp.setTypeId( tmp.getB_typeid());
			rtn.add(votmp);
		}
		
		return rtn;
	}
}
