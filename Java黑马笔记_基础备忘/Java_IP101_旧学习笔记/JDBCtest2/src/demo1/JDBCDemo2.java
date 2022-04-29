package demo1;

import javax.xml.transform.Result;
import java.sql.*;

public class JDBCDemo2 {

    public static void main(String[] args) {
        //防止关闭资源时空指针
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //1.注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql:///java","root","");
            //3.定义sql
            String sql = "select * from account";
            //4.获取执行sql对象
            stmt = conn.createStatement();
            //5.执行sql
            rs = stmt.executeQuery(sql);
            //6.处理结果
            rs.next();

            int id = rs.getInt(1);
            String name = rs.getString("name");
            String name2 = rs.getString(2);
            double balance = rs.getDouble(3);
            System.out.println(id+ "---" + name +"/"+name2+ "---" + balance);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }finally {
            //7.关闭资源
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
