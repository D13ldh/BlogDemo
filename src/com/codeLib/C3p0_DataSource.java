package com.codeLib;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class C3p0_DataSource {
	private static ComboPooledDataSource dataSource;

	private C3p0_DataSource() {

	}

	static {

			dataSource = new ComboPooledDataSource();
			Properties properties = new Properties();
			InputStream is = C3p0_DataSource.class.getClassLoader()
					.getResourceAsStream("jdbc.properties");
			try {
				properties.load(is);
			} catch (IOException e) {
				e.printStackTrace();
			}
			dataSource.setProperties(properties);
			//这一句注释掉了，我感觉他提供这个方法应该就是直接获取配置文件的，不过一直测试不通过，不知道什么原因.	   
			dataSource.setUser(properties.getProperty("user"));
			dataSource.setPassword(properties.getProperty("passWord"));
			dataSource.setJdbcUrl(properties.getProperty("jdbcUrl"));
			System.out.println(properties.getProperty("jdbcUrl")+"---------------datasource");
			try {
				dataSource.setDriverClass(properties.getProperty("driverClass"));
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}

			dataSource.setInitialPoolSize(Integer.parseInt(properties.getProperty("initialPoolSize")));
			dataSource.setMinPoolSize(Integer.parseInt(properties.getProperty("minPoolSize")));
			dataSource.setMaxPoolSize(Integer.parseInt(properties.getProperty("maxPoolSize")));
			dataSource.setMaxIdleTime(Integer.parseInt(properties.getProperty("maxIdleTime")));
			dataSource.setIdleConnectionTestPeriod(Integer.parseInt(properties.getProperty("idleConnectionTestPeriod")));
			
	}

	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void realseSource(ResultSet rs, Statement st, Connection conn){
		try{
			try {
				if (rs != null)
					rs.close();
			} finally {
				try {
					if (st != null)
						st.close();
				} finally {
					if (conn != null)
						conn.close();//不是真正的销毁，而是放弃使用权，放回到池中（空闲）
				}
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
}