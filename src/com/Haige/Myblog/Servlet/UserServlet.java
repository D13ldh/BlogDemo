package com.Haige.Myblog.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Haige.Myblog.DAO.mb_blogDAO;
import com.Haige.Myblog.DAO.mb_blog_typeDAO;
import com.Haige.Myblog.DAO.mb_userDAO;
import com.Haige.Myblog.DTO.mb_blogDTO;
import com.Haige.Myblog.DTO.mb_userDTO;
import com.Haige.Myblog.Service.UserService;
import com.Haige.Myblog.VO.VO4BlogEdit;
import com.Haige.Myblog.VO.VO4BlogList;
import com.Haige.Myblog.VO.VO4ChangeInfo;
import com.Haige.Myblog.VO.VO4blog_add;
import com.Haige.Myblog.VO.VO4login;

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VO4login rtnVO;
	private static final String METHOD_GET = "GET";
	private static final String METHOD_POST = "POST";

	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String flag = request.getParameter("flag");
		String Common =request.getParameter("Common");

		if ("getBlogRank".equals(Common)) {
			this.getBlogRank(request, response);
		}
		if ("blogDel".equals(flag)) {
			this.blogDel(request, response);
		}
		if ("logout".equals(flag)) {
			this.logout(request, response);
		}
		if ("getArticle".equals(flag)) {
			this.getArticl(request, response);
		}
	}

	private void getBlogRank(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService BlogRankService = UserService.getService();
		List<VO4BlogList> rtn = BlogRankService.getBlogRank();
		request.setAttribute("Rank", rtn);
//		if(fromURL==null) {
//			fromURL = "/BlogL4/index.jsp";
//		}
		String URL = request.getRequestURI();
		System.out.println("from-------"+request.getParameter("from"));
		request.getRequestDispatcher("/"+request.getParameter("from")).forward(request, response);
//		request.getRequestDispatcher(url);
	}

	private void getArticl(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		mb_blogDAO DAO = new mb_blogDAO();
		mb_blog_typeDAO DAO2 = new mb_blog_typeDAO();
		mb_blogDTO tmp = DAO.findBlogById(Integer.parseInt(id));
		String BlogType = DAO2.findById(tmp.getB_typeid()).getTypeName();
		request.setAttribute("TypeName", BlogType);
		DAO.bolgViewNumsAdd(Integer.parseInt(id));
		request.setAttribute("articl", tmp);
		request.getRequestDispatcher("/blog_view.jsp").forward(request, response);

	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("uid", null);// 5
		session.setAttribute("obj", null);
		response.sendRedirect("index.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String flag = request.getParameter("flag");

		System.out.println(flag);
		if ("login".equals(flag)) {
			this.login(request, response);
		}
		if ("blogAdd".equals(flag)) {
			this.blogAdd(request, response);
		}

		if ("edit".equals(flag)) {
			this.blogEdit(request, response);
		}
		if ("changPWD".equals(flag)) {
			this.changePwd(request, response);
		}
		if ("changInfo".equals(flag)) {
			this.changInfo(request, response);
		}
	}

	private void changInfo(HttpServletRequest request, HttpServletResponse response) {
		int uid = Integer.parseInt(request.getParameter("uid"));
		String name = request.getParameter("name");
		String desc = request.getParameter("desc");
		String intro = request.getParameter("intro");
		VO4ChangeInfo tmp = new VO4ChangeInfo();
		tmp.setDesc(desc);
		tmp.setName(name);
		tmp.setUid(uid);
		tmp.setIntro(intro);
		if (intro != null && desc != null && name != null) {
			UserService changInfoService = UserService.getService();
			int rtn = changInfoService.UpdateIntro(tmp);
			try {
				if (rtn > 0) {
					request.setAttribute("obj", tmp);
					response.sendRedirect("index.jsp");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private void changePwd(HttpServletRequest request, HttpServletResponse response) {
		String OldPwd = request.getParameter("pass1");
		String NewPwd = request.getParameter("pass2");
		String AgNewPwd = request.getParameter("pass3");
		if (!NewPwd.equals(AgNewPwd)) {
			try {
				response.sendRedirect("user_psw_edit.jsp?rtn=2");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}

		String name = request.getParameter("name");

		if (!OldPwd.equals(mb_userDAO.getDao().findByBlogName(name).getPassWord())) {
			try {
				response.sendRedirect("user_psw_edit.jsp?rtn=0");
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		;
		UserService ChangPwdService = UserService.getService();
		ChangPwdService.ChangePwd(Integer.parseInt(request.getParameter("id")), NewPwd);
		try {
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void blogEdit(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("b_id");
		String b_title = request.getParameter("title");
		String b_type = request.getParameter("type");
		String b_intro = request.getParameter("intro");
		String b_content = request.getParameter("content");
		if (b_title != null && !"".equals(b_title) && b_type != null && !"".equals(b_type) && b_intro != null
				&& !"".equals(b_intro) && b_content != null && !"".equals(b_content)) {
		
			VO4BlogEdit edit = new VO4BlogEdit();
			edit.setBlogId(Integer.parseInt(id));
			edit.setContent(b_content);
			edit.setIntro(b_intro);
			edit.setTitle(b_title);
			edit.setTypeId(Integer.parseInt(b_type));
			UserService us = UserService.getService();
			us.editBlog(edit);
			try {
				response.sendRedirect("blog.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			// 参数为空，跳转到index.jsp并带上参数1表示错误信息
			System.out.print("添加文章失败");
		}

	}

	private void blogDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		System.out.println("Servlet中的Id-----" + id);
		UserService delService = UserService.getService();
		int rtn = delService.delBlog(id);
		response.sendRedirect("blog.jsp");

	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String userpass = request.getParameter("userpass");
		UserService loginService = UserService.getService();
		VO4login rtn = loginService.login(username, userpass);
		// System.out.println("login_do中的rtn为(servlet)" + rtn);
		if (rtn.getCode() == 1) {
			response.sendRedirect("index.jsp?rtn=1");
		} else if (rtn.getCode() == 2) {
			// 登录错误, 跳转回去
			response.sendRedirect("index.jsp?rtn=2");// 2
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("uid", rtn.getUseId());// 5
			session.setAttribute("obj", rtn);
			response.sendRedirect("index.jsp");
		}
	}

	private void blogAdd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		Integer uid = (Integer) session.getAttribute("uid");
		System.out.println("uid----为" + uid);
		if (uid == null) {
			response.sendRedirect("index.jsp");
			return;
		}

		request.setCharacterEncoding("UTF-8");
		String b_title = request.getParameter("title");
		String b_type = request.getParameter("type");
		System.out.print(b_type + "-----------type");
		String b_intro = request.getParameter("intro");
		String b_content = request.getParameter("content");
		if (b_title != null && !"".equals(b_title) && b_type != null && !"".equals(b_type) && b_intro != null
				&& !"".equals(b_intro) && b_content != null && !"".equals(b_content)) {
			System.out.println("Servlet添加文章");
			new VO4blog_add(b_title, Integer.parseInt(b_type), b_intro, b_content);
			response.sendRedirect("blog.jsp");
		} else {
			// 参数为空，跳转到index.jsp并带上参数1表示错误信息
			System.out.print("添加文章失败");
		}
	}

}
