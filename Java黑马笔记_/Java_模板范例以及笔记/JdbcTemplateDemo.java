import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JdbcTemplateDemo {
    /**
     * update(),执行DML的增删改
     * queryForMap(),查询结果封装map集合，封装一个map集合，查询结果长度只能是1个（一条记录）
     * queryForList(),查询结果封装为list集合,将每个记录封装为Map集合,再将Map集合装载到List集合中
     * query()查询结果封装为JavaBean对象
     * queryForObject,查询结果封装为对象
     */
    @Test
    public void testTemplate(){
        //1.导入jar包
        //2.创建JdbcTemplate对象
        JdbcTemplate template = new JdbcTemplate(JdbcUtilsAdvanced.getDataSource());
        //3.调用方法
        String sql = "update account set balance = 5000 where id = ?";
        int count = template.update(sql, 3);//3表示id=3，多个参数加逗号逐一添加
        System.out.println(count);
        //template不需要额外写close，JdbcTemplate自动归还连接到连接池

        //查询记录，queryForMap()封装结果为Map集合
        String sql2 = "select * from account where id = ?";
        Map<String, Object> map = template.queryForMap(sql2,1);//queryForMap只能搜索一条记录
        System.out.println(map);
        //{id=1, name=lisi, balance=2000.0}

        String sql3 = "select * from account";
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map<String,Object> stringObjectMap :   list) {
            System.out.println(stringObjectMap);
        }

        //非简化版
        //RowMapper()为接口
        List<Account> query = template.query(sql3, new RowMapper<Account>() { //自定义实现接口，封装成JavaBean对象，Account为自定义类
            @Override
            public Account mapRow(ResultSet rs, int i) throws SQLException {
                Account act = new Account();
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double balance = rs.getDouble("balance");

                act.setBalance(balance);
                act.setId(id);
                act.setName(name);
                return act;
            }
        });

        for(Account act : query){
            System.out.println(act);
        }

        //注意事项：Account类的balance若为double，则double balance =null会报错，优选Double balance则Double balance = null不会报错，int与Integer类似
        //  简化版封装JavaBean类型
        List<Account> query1 = template.query(sql3, new BeanPropertyRowMapper<Account>(Account.class));
        for(Account act : query1){
            System.out.println(act);
        }

        Long total = template.queryForObject(sql3, Long.class);
        System.out.println(total);

        /**连接池对象获取连接DataSource，封装了DriverManager，connection可以被复用
         *DataSource ds = new ComboPooledDataSource(); //C3P0
         * DataSource ds = DruidDataSourceFactory.createDataSource(pro)
         * Connection con = ds.getConnection()
         *
         *连接数据库，connection不可以复用
         * Connection con = DriverManager.getConnection(url,user,password)
         */
    }
}
