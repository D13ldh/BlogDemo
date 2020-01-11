package com.Haige.Myblog.DAO;

import java.sql.*;

import javax.sql.rowset.CachedRowSet;

import com.Haige.Myblog.DTO.mb_userDTO;
import com.Haige.Myblog.VO.VO4ChangeInfo;
import com.Haige.common.db.DBUtil;
import com.Haige.common.exception.BaseException;
import com.Haige.core.db.C3P0Datasource;
import com.codeLib.C3p0_DataSource;

public class mb_userDAO {
	private static mb_userDAO dao = new mb_userDAO();

	private mb_userDAO() {
	}

	public static mb_userDAO getDao() {
		return dao;
	}
	public int UpDateIntro(VO4ChangeInfo tmp) {
		String sql = String.format("update mb_user set name='%s', `desc`='%s', intro='%s' where user_id='%d'", tmp.getName(), tmp.getDesc(),tmp.getIntro(),tmp.getUid());
		int rtn = DBUtil.updateSQL(C3P0Datasource.getConnection(), sql);
		return rtn;
	}
	public mb_userDTO findByName(String Name) {
		CachedRowSet rs = null;
		mb_userDTO rtn = new mb_userDTO();
		String sql = null;
		try {

			sql = "select * from mb_user where username = '" + Name + "'";
			rs = DBUtil.querySQL(C3P0Datasource.getConnection(), sql);
			while (rs.next()) {
				rtn.setDesc(rs.getString("desc"));
				rtn.setIntro(rs.getString("intro"));
				rtn.setPassWord(rs.getString("password"));
				rtn.setName(rs.getString("name"));
				rtn.setUserName(rs.getString("username"));
				rtn.setUserId(Integer.parseInt(rs.getString("user_id")));
			}
		} catch (SQLException e) {
			throw new BaseException("数据库结果集操作有误：" + sql+mb_userDAO.class, "系统异常", e);
		}
		return rtn;
		
	}
	public mb_userDTO findByBlogName(String Name) {
		CachedRowSet rs = null;
		mb_userDTO rtn = new mb_userDTO();
		String sql = null;
		try {

			sql = "select * from mb_user where name = '" + Name + "'";
			rs = DBUtil.querySQL(C3P0Datasource.getConnection(), sql);
			while (rs.next()) {
				rtn.setDesc(rs.getString("desc"));
				rtn.setIntro(rs.getString("intro"));
				rtn.setPassWord(rs.getString("password"));
				rtn.setName(rs.getString("name"));
				rtn.setUserName(rs.getString("username"));
				rtn.setUserId(Integer.parseInt(rs.getString("user_id")));
			}
		} catch (SQLException e) {
			throw new BaseException("数据库结果集操作有误：" + sql+mb_userDAO.class, "系统异常", e);
		}
		return rtn;
		
	}
	public void ChangPwd(int id,String pwd) {
		int rs ;
		String sql = String.format("update mb_user set password='%s' where user_id=%d", pwd, id);
		rs = DBUtil.updateSQL(C3P0Datasource.getConnection(), sql);
		
	}
}
