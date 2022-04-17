import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DruidUseDemo {

    @Test
    public void druidUse() {
        /**
         * 完成添加操作：给account表添加一条记录
         */
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            //1.获取连接
            con = JdbcUtilsAdvanced.getConnection();
            //2.定义sql
            String sql = "insert into account values(null,?,?)";
            //3.获取pstmt对象
            pstmt = con.prepareStatement(sql);
            //4.给?问好赋值
            pstmt.setString(1,"wangwu"); //中文需要额外配置
            pstmt.setDouble(2,3000);
            //5.执行sql
            int rs = pstmt.executeUpdate();
            System.out.println(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //6.释放资源
            JdbcUtilsAdvanced.close(pstmt,con);
        }
    }
}
