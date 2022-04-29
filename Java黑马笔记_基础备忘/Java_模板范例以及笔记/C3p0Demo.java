import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3p0Demo {

    @Test
    public void test() throws SQLException {
        //1.创建数据库连接池对象，使用默认配置，配置文件名称固定，放置目录在src下任意目录
        DataSource ds = new ComboPooledDataSource();
        /**指定配置名称
         * DataSource ds = new ComboPooledDataSource("otherc3p0");         */
        //2.获取连接对象
        /**Connection con = ds.getConnection(); */
        for (int i = 1; i <= 11; i++) { //超过连接数报错
            Connection con = ds.getConnection();
            System.out.println(i+": "+con);

            if(i == 5){
                con.close(); //归还连接到连接池中
                /**第五个连接只用了短时间就归还，第六个人使用的同个连接对象
                 * 5: com.mchange.v2.c3p0.impl.NewProxyConnection@3108bc [wrapping: com.mysql.cj.jdbc.ConnectionImpl@370736d9]
                 * 6: com.mchange.v2.c3p0.impl.NewProxyConnection@6536e911 [wrapping: com.mysql.cj.jdbc.ConnectionImpl@370736d9]
                 */
            }
        }
        /**打印
        System.out.println(con);                */

        /**C3P0加载需要mysql驱动mysql-connector-java
         *         <dependency>
         *             <groupId>com.mchange</groupId>
         *             <artifactId>c3p0</artifactId>
         *             <version>0.9.5.5</version>
         *         </dependency>
         *         <dependency>
         *             <groupId>mysql</groupId>
         *             <artifactId>mysql-connector-java</artifactId>
         *             <version>8.0.26</version>
         *         </dependency>
         */
    }
}




