import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JdbcUtilsDemo {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static{//静态随着类的加载而执行，只执行一次，放在方法之外
        try {
            //1.创建Properties集合类
            Properties pro = new Properties();

            //获取src路径下的文件的方式 -->ClassLoader类加载器，加载字节码文件进内存(随便一个文件都可以)，可以获取src下资源文件的路径
            ClassLoader classLoader = JdbcUtilsDemo.class.getClassLoader();
            //传一个文件名就可以获取其在src目录下的资源路径
            URL res = classLoader.getResource("jdbc.properties");//URL 统一资源定位符，定位文件的绝对路径
            String path = res.getPath();
            //2.加载文件
            pro.load(new FileReader(path));
            //绝对路径可以成功：pro.load(new FileReader("D:\\project\\src\\jdbc.properties"));

            //3.获取数据，赋值
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //注册驱动
            Class.forName(driver); //Class.forName()初始化给定的类
        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取连接
     * @return 连接对象
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,user,password); //try-catch后的return无效？只能抛出异常

        //固定的数据库db3，不实用
        //DriverManager.getConnection("jdbc:mysql://localhost:3306/db3","root","password");
        /**配置文件.properties
         *url=jdbc:mysql///db3
         * user=root
         * password=root
         * driver=com.mysql.jdbc.Driver
         */
    }

    /**
     * 释放资源
     * @param stmt
     * @param con
     */
    public static void close(Statement stmt, Connection con){
        if(stmt != null){
            try{
                stmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(con != null){
            try{
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 重载
     * @param rs 结果集
     * @param stmt
     * @param con
     */
    public static void close(ResultSet rs, Statement stmt, Connection con){
        if(rs != null){
            try{
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(stmt != null){
            try{
                stmt.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(con != null){
            try{
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
