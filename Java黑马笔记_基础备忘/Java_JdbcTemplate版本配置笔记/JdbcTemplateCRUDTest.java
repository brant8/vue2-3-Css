package test;

import domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

//导入spring test包
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class JdbcTemplateCRUDTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    public void test(){
        jdbcTemplate.update("insert into account values (?,?)","sb2",250);
    }

    @Test
    public void testUpdate(){
        jdbcTemplate.update("update account set money = ? where name = ?",1000,"tom");
    }

    @Test
    public void testDelete(){

        jdbcTemplate.update("delete from account where name = ?", "tom");
    }

    @Test
    public void testQueryAll(){
        List<Account> accountList = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
        /**jdbcTemplate.query(String sql, RowMapper<T> rowMapper                            List<T>
         *                               使用接口的实现类(鼠标放放类名，快捷键Ctrl+Alt+B查看实现类)   返回类型
         *  BeanPropertyRowMapper<Account>(Account.class)
         *                      <要封装的实体泛型>(对应的字节码类型对象)
         */
        System.out.println(accountList);
    }

    @Test
    public void testQueryOne(){
        Account account = jdbcTemplate.queryForObject("select * from account where name = ?", new BeanPropertyRowMapper<Account>(Account.class), "tom");
        System.out.println(account);
    }

    @Test
    public void testQueryCount(){
        Long aLong = jdbcTemplate.queryForObject("select count(*) from account", Long.class);
        /**<T> T queryForObject(String sql, Class<T> requiredType)
         *                                   requiredType基本类型
         */
        System.out.println(aLong);
    }


}
