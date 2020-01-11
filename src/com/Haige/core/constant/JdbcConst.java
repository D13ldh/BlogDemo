package com.Haige.core.constant;

import java.util.Map;

import com.Haige.common.config.PropertiesUtil;



/**
 * JDBC配置信息
 * @author leeyn
 *
 */
public class JdbcConst {
	public static final String DRIVER_CLASS;
	public static final String JDBC_URL;
	public static final String USER_NAME;
	public static final String PASS_WORD;
	public static final int MIN_POOL_SIZE;
	public static final int INITIAL_POOL_SIZE;
	public static final int MAX_POOL_SIZE;
	public static final int MAX_IDLE_TIME;
	public static final int ACQUIRE_INCREMENT;
	public static final int IDLE_CONNECTION_TEST_PERIOD;
	static {
		Map<String, String> map=PropertiesUtil.getProperties("/jdbc.properties");
		DRIVER_CLASS=map.get("driverClass");
		JDBC_URL=map.get("jdbcUrl");
		USER_NAME=map.get("userName");
		PASS_WORD=map.get("passWord");
		System.out.println("PASS_WORD:"+PASS_WORD);
		MIN_POOL_SIZE=Integer.parseInt(map.get("minPoolSize"));
		INITIAL_POOL_SIZE=Integer.parseInt(map.get("initialPoolSize"));
		MAX_POOL_SIZE=Integer.parseInt(map.get("maxPoolSize"));
		MAX_IDLE_TIME=Integer.parseInt(map.get("maxIdleTime"));
		ACQUIRE_INCREMENT=Integer.parseInt(map.get("acquireIncrement"));
		IDLE_CONNECTION_TEST_PERIOD=Integer.parseInt(map.get("idleConnectionTestPeriod"));
	}	
}
