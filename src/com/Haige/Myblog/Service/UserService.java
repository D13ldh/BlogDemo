package com.Haige.Myblog.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Haige.Myblog.DAO.mb_blogDAO;
import com.Haige.Myblog.DAO.mb_blog_typeDAO;
import com.Haige.Myblog.DAO.mb_userDAO;
import com.Haige.Myblog.DTO.mb_blogDTO;
import com.Haige.Myblog.DTO.mb_blog_typeDTO;
import com.Haige.Myblog.DTO.mb_userDTO;
import com.Haige.Myblog.VO.VO4AllTypeName;
import com.Haige.Myblog.VO.VO4BlogEdit;
import com.Haige.Myblog.VO.VO4BlogList;
import com.Haige.Myblog.VO.VO4ChangeInfo;
import com.Haige.Myblog.VO.VO4TypeAdd;
import com.Haige.Myblog.VO.VO4TypeEdit;
import com.Haige.Myblog.VO.VO4login;

public class UserService {
	private VO4login rtnVO;
	private static UserService service=new UserService();
	private UserService() {}
	public static UserService getService() {
		return service;
	}
	/**
	 * @param username
	 * @param userpass
	 * @return 1:参数为空，2:参数错误，3：登录成功
	 */
	public VO4login login(String username, String userpass) {
		rtnVO = new VO4login();
		if (username != null && userpass != null && !"".equals(username) && !"".equals(userpass)) {
			mb_userDTO rtn = mb_userDAO.getDao().findByName(username);
			if (rtn == null) {
				rtnVO.setCode(2);
				return rtnVO;
			}
			if (userpass.equals(rtn.getPassWord())) {
				rtnVO.setName(rtn.getName());
				rtnVO.setDesc(rtn.getDesc());
				rtnVO.setIntro(rtn.getIntro());
				rtnVO.setUseId(rtn.getUserId());
				return rtnVO;
			}
			rtnVO.setCode(2);
			return rtnVO;
		} else {
			rtnVO.setCode(1);
			return rtnVO;
		}
	}
	
	public int UpdateIntro(VO4ChangeInfo tmp) {
		mb_userDAO DAO = mb_userDAO.getDao();
		int rtn = DAO.UpDateIntro(tmp);
		return rtn;
	}
	/**
	 * @return 博文所属类型的VO对象
	 */
	public VO4AllTypeName[] getAllType() {

		mb_blog_typeDAO DAO = new mb_blog_typeDAO();
		List<mb_blog_typeDTO> tmp = DAO.findAll();
		VO4AllTypeName[] rtn = new VO4AllTypeName[tmp.size()];
		int i = 0;
		for (mb_blog_typeDTO DTO : tmp) {
			rtn[i] = new VO4AllTypeName();
			rtn[i].setName( DTO.getTypeName()); 
			rtn[i].setTypeId(DTO.getTypeId());
			i++;
		}
		i = 0;
		return rtn;
	}

	public VO4BlogEdit getArticleMsg(int id){
		mb_blogDAO DAO = new mb_blogDAO();
		mb_blogDTO tmp = DAO.findBlogById(id);
//		System.out.println("UserService--"+tmp.getB_content());
		VO4BlogEdit rtn = new VO4BlogEdit();
		rtn.setContent(tmp.getB_content());
		rtn.setIntro(tmp.getB_intro());
		rtn.setTitle(tmp.getB_title());
		rtn.setTypeId(tmp.getB_typeid());
		rtn.setBlogId(tmp.getB_id());
		VO4AllTypeName[] nameList = getAllType();
		String name = null;
		for(VO4AllTypeName tmp2 : nameList) {
			if(tmp2.getTypeId()==id) {
				name = tmp2.getName();
			}
		}
 		rtn.setTypeName(name);
		return rtn;
	}
	public  int delBlog(String id) {
		mb_blogDAO Dao = new mb_blogDAO();
		Dao.delBlogById(id);
		return 0;
	}
	public int editBlog(VO4BlogEdit edit) {
		mb_blogDAO Dao = new mb_blogDAO();
		mb_blogDTO DTO = new mb_blogDTO();
		DTO.setB_content(edit.getContent());
		DTO.setB_id(edit.getBlogId());
		DTO.setB_title(edit.getTitle());
		DTO.setB_typeid(edit.getTypeId());
		DTO.setB_viewnums(0);
		DTO.setB_posttime(String.valueOf(new Date()));
		DTO.setB_intro(edit.getIntro());
		Dao.blogEdit(DTO);
		return 1;
	}
	public int ChangePwd(int id,String pwd) {
		mb_userDAO.getDao().ChangPwd(id, pwd);
		return 1;
	}
	public List<VO4BlogList> getBlogRank(){
		mb_blogDAO DAO = new mb_blogDAO();
		List<mb_blogDTO> DTOList = new ArrayList<mb_blogDTO>();
		List<VO4BlogList> rtn = new ArrayList<VO4BlogList>();
		DTOList = DAO.OrderAllBlog();
		for(mb_blogDTO tmp :DTOList) {
			VO4BlogList VOtmp = new VO4BlogList();
			VOtmp.setBlogID(tmp.getB_id());
			VOtmp.setBlogString(tmp.getB_title());
			rtn.add(VOtmp);
		}
		return rtn;
	}
	public int addBlogType(VO4TypeAdd vo) {
		// TODO Auto-generated method stub
		mb_blog_typeDAO DAO = new mb_blog_typeDAO();
		mb_blog_typeDTO DTO = DAO.findByName(vo.getTitle());
		if(DTO.getTypeName()==vo.getTitle()) {
			return 2;//重名
		}
		int rtn = DAO.addType(vo.getTitle());
		return rtn;
		
	}
	public int editBlogType(VO4TypeEdit vo) {
		System.out.println("修改博文的VO----"+vo.getTitle());
		mb_blog_typeDAO DAO = new mb_blog_typeDAO();
		int rtn =  DAO.UpdateByName(vo.getTitle(),vo.getId());
		return 0;
	}

}
