import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TestLogError {
	public static final Logger LOGGER = LogManager.getLogger(TestLogError.class);
	 
    public static void main(String[] args) {
        try{
            // 模拟空指针异常
            //Integer nullInt = Integer.valueOf(null);
            int[] array = {1,2,3,4,5};
            int outBoundInt = array[5];
        }catch (Exception e){
            // 直接打印，则只输出异常类型
            LOGGER.error(e);
            // 使用字符串拼接
            LOGGER.error("使用 + 号连接直接输出 e : " + e);
            LOGGER.error("使用 + 号连接直接输出 e.getMessage() : " + e.getMessage());
            LOGGER.error("使用 + 号连接直接输出 e.toString() : " + e.toString());
            // 使用逗号分隔，调用两个参数的error方法
            LOGGER.error("使用 , 号 使第二个参数作为Throwable : ", e);
            // 尝试使用分隔符,第二个参数为Throwable,会发现分隔符没有起作用，第二个参数的不同据，调用不同的重载方法
            LOGGER.error("第二个参数为Throwable，使用分隔符打印 {} : ", e);
            // 尝试使用分隔符，第二个参数为Object,会发现分隔符起作用了，根据第二个参数的不同类型，调用不同的重载方法
            LOGGER.error("第二个参数为Object，使用分隔符打印 {} ");
        }
        System.out.println("我是Try catch 后面的");
    }
}
