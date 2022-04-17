import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo {

    @Test
    public void druidTest() throws Exception {
        //1.导入jar包
        //2. 定义配置文件
        //3.加载配置文件
        Properties pro = new Properties();
        ClassLoader classLoader = DruidDemo.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("druid.properties");
        pro.load(is);
        //4. 获取连接池对象
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        //5.获取连接
        Connection con = ds.getConnection();

        System.out.println(con);

    }
}
