package com.Haige.Myblog.DAO;

import java.sql.*;
import java.util.*;

import javax.sql.rowset.CachedRowSet;

import com.Haige.Myblog.DTO.mb_blog_typeDTO;
import com.Haige.common.db.DBUtil;
import com.Haige.common.exception.BaseException;
import com.Haige.core.db.C3P0Datasource;
import com.codeLib.C3p0_DataSource;

public class mb_blog_typeDAO {

	public mb_blog_typeDTO findById(int typeID) {
		String sql = null;
		CachedRowSet rs = null;
		mb_blog_typeDTO rtn = new mb_blog_typeDTO();
		try {

			sql = "select type_name from mb_blog_type where type_id = " + typeID;
			rs = DBUtil.querySQL(C3P0Datasource.getConnection(), sql);
			while (rs.next()) {
				rtn.setTypeName(rs.getString("type_name"));
				rtn.setTypeId(typeID);
			}
		} catch (Exception e) {
			throw new BaseException("数据库结果集操作有误：" + sql, "系统异常", e);
		}

		return rtn;
	}

	public int addType(String name) {
		String sql = null;
		int rs = 0;

		try {

			sql = String.format("insert into mb_blog_type value(null,'%s')", name);
			rs = DBUtil.updateSQL(C3P0Datasource.getConnection(), sql);
			return rs;
		} catch (Exception e) {
			throw new BaseException("数据库结果集操作有误：" + sql, "系统异常", e);
		}
	}

	public mb_blog_typeDTO findByName(String name) {
		String sql = null;
		CachedRowSet rs = null;
		mb_blog_typeDTO rtn = new mb_blog_typeDTO();
		try {

			sql = "select type_name from mb_blog_type where type_name = '" + name + "'";
			rs = DBUtil.querySQL(C3P0Datasource.getConnection(), sql);
			while (rs.next()) {
				rtn.setTypeId(rs.getInt("type_id"));
				rtn.setTypeName(name);
			}
		} catch (Exception e) {
			throw new BaseException("数据库结果集操作有误：" + sql, "系统异常", e);
		}

		return rtn;
	}

	public List<mb_blog_typeDTO> findAll() {
		CachedRowSet rs = null;
		String sql = null;
		List<mb_blog_typeDTO> tmp = new ArrayList<mb_blog_typeDTO>();
		try {

			sql = "select * from mb_blog_type";
			rs = DBUtil.querySQL(C3P0Datasource.getConnection(), sql);
			while (rs.next()) {
				mb_blog_typeDTO rtn = new mb_blog_typeDTO();
				rtn.setTypeName(rs.getString("type_name"));
				rtn.setTypeId(rs.getInt("type_id"));
				tmp.add(rtn);
			}
		} catch (Exception ex) {
			throw new BaseException("数据库结果集操作有误：" + sql, "系统异常", ex);
		}

		return tmp;
	}

	public int UpdateByName(String title ,int id) {
		int rs ;
		String sql = null;
		try {

			sql = "update mb_blog_type set type_name = '"+ title+"' where type_id = '"+id+"'";
			rs = DBUtil.updateSQL(C3P0Datasource.getConnection(), sql);
		} catch (Exception ex) {
			throw new BaseException("数据库结果集操作有误：" + sql, "系统异常", ex);
		}
		return rs;
	}

}
