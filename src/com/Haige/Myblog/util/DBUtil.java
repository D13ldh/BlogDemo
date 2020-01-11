package com.Haige.Myblog.util;

import java.sql.*;

import com.codeLib.C3p0_DataSource;

public class DBUtil {
	private static void realseSource(ResultSet _rs, Statement _st, Connection _conn){		
		C3p0_DataSource.realseSource(_rs,_st,_conn);//使用DataSource的公共方法
	}
	public static void realseSource(DBUtil_VO _vo){	
		if(_vo!=null){
			realseSource(_vo.rs, _vo.st, _vo.conn);
		}		
	}
	/**
	 * 调用我需要realseSource(DBUtil_VO)
	 * @param _conn
	 * @param _sql
	 */
	public static DBUtil_VO executeQuery(Connection _conn,String _sql)
	{		
		DBUtil_VO vo=new DBUtil_VO();
		vo.conn = _conn;
		try{				
			vo.st = vo.conn.createStatement();			
		}catch (SQLException e){			
			realseSource(vo);//释放处理
		}		
		try{
			vo.rs = vo.st.executeQuery(_sql);
		}catch (SQLException e){			
			realseSource(vo);//释放处理
		}		
		return vo;
	}
	/**
	 * 调用我无需realseSource，已经释放
	 * @param _conn
	 * @param _sql
	 */
	public static  int executeUpdate(Connection _conn,String _sql)
	{
		int rs = 0;
		Connection conn = _conn;
		Statement st = null;
		try {
			st = conn.createStatement();
		} catch (SQLException e) {
			realseSource(null, st, conn);//释放处理			

		}
		try {
			rs = st.executeUpdate(_sql);
		} catch (SQLException e) {
			realseSource(null, st, conn);//释放处理			
		
		}
		realseSource(null, st, conn);//释放处理
		return rs;
		}
		
}
