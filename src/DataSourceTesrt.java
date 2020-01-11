
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceTesrt {
	public static void getConnection(){	//测试1：普通创建连接与用连接池获取连接的差别
	    
        int count=0;
		  for(int i = 1;i<=1000;i++){
			    Connection connection = C3p0_DataSource.getConnection();
			    System.out.println(i+"-----"+count+"-----"+connection);
			    count++;
			    C3p0_DataSource.realseSource(null, null, connection);	//必须释放					    					   
		  }
		  System.out.println(count); 	
			  
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getConnection();
	}

}
