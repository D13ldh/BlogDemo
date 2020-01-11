package com.Haige.Myblog.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.Haige.Myblog.DTO.mb_blogDTO;
import com.Haige.common.db.DBUtil;
import com.Haige.common.exception.BaseException;
import com.Haige.core.db.C3P0Datasource;
import com.codeLib.C3p0_DataSource;

public class mb_blogDAO {

	public List<mb_blogDTO> findAllBlog() {
		CachedRowSet rs = null;
		String sql = null;
		List<mb_blogDTO> tmp = new ArrayList<mb_blogDTO>();
		try {
			sql = "select * from mb_blog order by b_posttime desc";
			rs = DBUtil.querySQL(C3P0Datasource.getConnection(), sql);
			while (rs.next()) {
				mb_blogDTO rtn = new mb_blogDTO();
				rtn.setB_content(rs.getString("b_content"));
				rtn.setB_intro(rs.getString("b_intro"));
				rtn.setB_id(rs.getInt("b_id"));
				rtn.setB_viewnums(rs.getInt("b_viewnums"));
				rtn.setB_posttime(rs.getString("b_posttime"));
				rtn.setB_typeid(rs.getInt("b_typeid"));
				rtn.setB_title(rs.getString("b_title"));
				tmp.add(rtn);

			}
			// mb_blogDTO tmp2=null;
			// for(int x = 0; x < tmp.size(); x++) {
			// System.out.println(tmp.get(x).getB_intro());
			// }

		} catch (Exception e) {
			throw new BaseException("数据库结果集操作有误：" + sql, "系统异常", e);
		}
		return tmp;

	}

	public List<mb_blogDTO> OrderAllBlog() {
		CachedRowSet rs = null;
		String sql = null;
		List<mb_blogDTO> tmp = new ArrayList<mb_blogDTO>();
		try {
			sql = "select * from mb_blog order by b_viewnums desc";
			rs = DBUtil.querySQL(C3P0Datasource.getConnection(), sql);
			while (rs.next()) {
				mb_blogDTO rtn = new mb_blogDTO();
				rtn.setB_content(rs.getString("b_content"));
				rtn.setB_intro(rs.getString("b_intro"));
				rtn.setB_id(rs.getInt("b_id"));
				rtn.setB_viewnums(rs.getInt("b_viewnums"));
				rtn.setB_posttime(rs.getString("b_posttime"));
				rtn.setB_typeid(rs.getInt("b_typeid"));
				rtn.setB_title(rs.getString("b_title"));
				tmp.add(rtn);

			}
			// mb_blogDTO tmp2=null;
			// for(int x = 0; x < tmp.size(); x++) {
			// System.out.println(tmp.get(x).getB_intro());
			// }

		} catch (Exception e) {
			throw new BaseException("数据库结果集操作有误：" + sql, "系统异常", e);
		}
		return tmp;

	}
	public Boolean addBlog(mb_blogDTO blog) {
		String sql =null;
		try {

			sql = "insert into mb_blog(b_typeid,b_title,b_intro,b_content)" + "value(" + blog.getB_typeid()
					+ ",'" + blog.getB_title() + "','" + blog.getB_intro() + "','" + blog.getB_content() + "')";
			DBUtil.updateSQL(C3P0Datasource.getConnection(), sql);
		} catch (Exception e) {
			throw new BaseException("数据库结果集操作有误：" + sql, "系统异常", e);
		}
		return true;
	}

	public mb_blogDTO findBlogById(int id) {
		String sql = null;
		CachedRowSet rs = null;
		mb_blogDTO rtn = new mb_blogDTO();
		try {
			
			sql = "select * from mb_blog where b_id = " + String.valueOf(id);
			rs = DBUtil.querySQL(C3P0Datasource.getConnection(), sql);
			while (rs.next()) {
				rtn.setB_content(rs.getString("b_content"));
				rtn.setB_intro(rs.getString("b_intro"));
				rtn.setB_id(rs.getInt("b_id"));
				rtn.setB_viewnums(rs.getInt("b_viewnums"));
				rtn.setB_posttime(rs.getString("b_posttime"));
				rtn.setB_typeid(rs.getInt("b_typeid"));
				rtn.setB_title(rs.getString("b_title"));
			}
		} catch (Exception e) {
			throw new BaseException("数据库结果集操作有误：" + sql, "系统异常", e);
		} 

		return rtn;

	}

	public int delBlogById(String id) {
		String sql = null;
		int rs = 0;
		
		try {

		
			 sql = String.format("delete from mb_blog where b_id=%s", id);
			 rs = DBUtil.updateSQL(C3P0Datasource.getConnection(), sql);
		} catch (Exception e) {

			System.out.println(e);
			return 0;
		} 
		return 1;
	}

	public int blogEdit(mb_blogDTO blog) {
		try {
			String sql = String.format("update mb_blog SET b_typeid ="+"'"+blog.getB_typeid()+"',"+
			"b_title ="+"'"+blog.getB_title()+"',"+"b_intro ="+"'"+blog.getB_intro()+"',"+
			"b_content ="+"'"+blog.getB_content()+"'"+"WHERE b_id ="+blog.getB_id());
			DBUtil.updateSQL(C3P0Datasource.getConnection(), sql);
		} catch (Exception e) {

			System.out.println(e);
			return 0;
		} 
		return 1;
	}
	public int bolgViewNumsAdd(int id) {
		String sql = "update mb_blog SET b_viewnums = b_viewnums +1 WHERE b_id="+id;
		int rtn = DBUtil.updateSQL(C3P0Datasource.getConnection(), sql);
		return rtn;
	}
}
