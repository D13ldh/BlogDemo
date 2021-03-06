import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.Haige.common.exception.BaseError;
import com.Haige.common.exception.BaseException;
import com.Haige.core.constant.JdbcConst;
import com.Haige.core.db.C3P0Datasource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class GetConnetion {
	private static ComboPooledDataSource dataSource;

	private GetConnetion() {

	}

	static {

			dataSource = new ComboPooledDataSource();
			try {
				dataSource.setDriverClass(JdbcConst.DRIVER_CLASS);
			} catch (PropertyVetoException e) {
				throw new BaseError("数据库驱动类找不到："+JdbcConst.DRIVER_CLASS,"系统错误",e);
			}
			dataSource.setJdbcUrl(JdbcConst.JDBC_URL);
			dataSource.setUser(JdbcConst.USER_NAME);
		
			dataSource.setPassword(JdbcConst.PASS_WORD);
			
			dataSource.setInitialPoolSize(JdbcConst.INITIAL_POOL_SIZE);
			dataSource.setMinPoolSize(JdbcConst.MIN_POOL_SIZE);
			dataSource.setMaxPoolSize(JdbcConst.MAX_POOL_SIZE);
			dataSource.setMaxIdleTime(JdbcConst.MAX_IDLE_TIME);
			dataSource.setIdleConnectionTestPeriod(JdbcConst.IDLE_CONNECTION_TEST_PERIOD);
			dataSource.setAcquireIncrement(JdbcConst.ACQUIRE_INCREMENT);
			
	}

	public static Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new BaseException("数据库连接获取不到","系统异常",e);
		}
	}
	public static void main(String[] args) {
		Connection con =GetConnetion.getConnection();
		System.out.println(con);
	}
}
