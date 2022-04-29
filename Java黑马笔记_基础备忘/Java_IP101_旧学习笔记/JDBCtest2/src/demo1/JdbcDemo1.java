package demo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JdbcDemo1 {
    public static void main(String[] args) throws Exception {
        //1.导入驱动jar包,添加到library
        //2.加载字节码文件（反射）。Driver类含有静态块，注册驱动，
        Class.forName("com.mysql.jdbc.Driver");
        //获取数据库连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
        //4.定于sql语句
        String sql="update account set balance = 5000 where id = 5";
        //获取执行sql的对象Statement
        Statement stmt = conn.createStatement();
        //执行sql
        //int executeUpdate(String sql):执行DML(insert, update, delete)语句，DDL(create, alter, drop)语句(不常用)
        int count = stmt.executeUpdate(sql);
        //处理结果
        System.out.println(count);
        //关闭资源
        stmt.close();
        conn.close();
    }
}
