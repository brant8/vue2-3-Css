package test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.PropertyVetoException;

public class JdbcTemplateTest {

    @Test
    //测试JdbcTemplate开发步骤
    public void test1() throws PropertyVetoException {
        //创建数据源对象, C3P0方式
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/javatest");
        dataSource.setUser("root");
        dataSource.setPassword("");


        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        //设置数据源对象，数据库地址等信息
        jdbcTemplate.setDataSource(dataSource);
        //执行操作
        int row = jdbcTemplate.update("insert into account values(?,?)", "tom2", 5000);
        System.out.println(row);
    }

    @Test
    //测试Spring产生jdbcTemplate模板对象
    public void test2() throws PropertyVetoException {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate bean = app.getBean(JdbcTemplate.class);
        int row = bean.update("insert into account values(?,?)", "Time", 250);
        System.out.println(row);
    }
    /**
     * applicationContext.xml
     *
     #数据源对象
     <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
         <property name="driverClass" value="com.mysql.cj.jdbc.Driver"/>
         <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/javatest"/>
         <property name="user" value="root"/>
         <property name="password" value=""/>
     </bean>

     #模板对象
     <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
         <property name="dataSource" ref="dataSource"/>
     </bean>
     */

    /**
     *  #解除耦合使用文件配置
     #加载jdbc.propertiies， 需要context命名空间-->
     <context:property-placeholder location="classpath:jdbc.properties"/>

     #数据源对象
     <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
     <property name="driverClass" value="${driver}"/>
     <property name="jdbcUrl" value="${url}"/>
     <property name="user" value="${username}"/>
     <property name="password" value="${password}"/>
     </bean>
     */


}
