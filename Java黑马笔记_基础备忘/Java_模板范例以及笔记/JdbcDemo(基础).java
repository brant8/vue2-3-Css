import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLOutput;
import java.sql.Statement;

public class JdbcDemo {

    public static void main(String[] args) throws Exception {
        /*基础*/
        //1、导入驱动jar包
        //2、注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //3、获取数据库连接对象
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db3","root","password");
        //4、定于SQL语句
        String sql = "update account set balance = 500 where id = 1";
        //5、获取执行sql对象的语句
        Statement stmt = con.createStatement();
        //6、执行sql
        int count = stmt.executeUpdate(sql);
        //7、处理结果
        System.out.println(count);
        //8、释放资源
        stmt.close();
        con.close();
        /*
        Statement执行sql对象
        1、boolean execute(String sql)：执行任意sql
        2、int executeUpdate(String sql)：执行DML(insert,update,delete)语句，DDL(create,alter,drop)语句
        3、ResultSet executeQuery(String sql)：执行DQL(Select)语句
         */
        /*
        ResultSet结果集对象，封装查询结果
        1、next():游标向下移动一行
        2、getXxx()：获取数据
                Xxx：代表数据类型
         String sql = "select * from account";
         stmt = con.createStatement();
         ResultSet = stmt.executeQuery(sql);
         while(rs.next()){
            rs.getInt(1); //第一列的数据
            rs.getString("name");//列的名称
         }
         rs.close();
        */
    }
}
