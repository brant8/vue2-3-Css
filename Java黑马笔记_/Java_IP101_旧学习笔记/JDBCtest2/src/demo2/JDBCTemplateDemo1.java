package demo2;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCUtils;

public class JDBCTemplateDemo1 {
    public static void main(String[] args) {
        JdbcTemplate tp = new JdbcTemplate(JDBCUtils.getDs());
        String sql = " update account set balance = 5000 where id =?";
        int count = tp.update(sql,7);
        System.out.println(count);
    }
}
