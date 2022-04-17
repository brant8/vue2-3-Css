### Spring 快速入门


Spring使用步骤
* 导入坐标
```
<dependency>  
	<groupId>org.springframework</groupId>  
	<artifactId>spring-context</artifactId>  
	<version>5.3.9</version>  
</dependency>
```
* 创建Bean，如`UserDao`（接口），`UserDaoImpl`（继承）
* 创建`applicationContext.xml`（可以取任意名字）,并进行配置
```
<bean id="userDao" class="ca.winbo.branty.dao.impl.UserDaoImpl"></bean>
```
* 创建`ApplicationContext`对象获取`getBean`
```
ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");  
UserDao userDao = (UserDao) app.getBean("userDao");  
userDao.save();
```
***

### Spring配置文件
基本属性
* id：Bean实例在Spring容器中的唯一标识
* class：Bean的全限定名称
***
Bean实例化三种方式：
* Bean创造时默认使用无参构造方法实例化。
```
#配置文件使用默认无参构造
class="ca.winbo.branty.dao.impl.UserDaoImpl"
```
* 工厂静态方法实例化
```
#创建工厂类
public class StaticFactory {  
    public static UserDao getUserDao(){  
        return new UserDaoImpl();  
  }  
}
#下一步
<bean id="userDao" class="ca.winbo.branty.factory.StaticFactory" factory-method="getUserDao"></bean>
```
* 工厂实例方法实例化
```
#创建非静态类方法
public class DynamicFactory {  
        public UserDao getUserDao(){  
            return new UserDaoImpl();  
  }    
}
#下一步，配置xml文件
<bean id="factory" class="ca.winbo.branty.factory.DynamicFactory"></bean>  
<bean id="userDao" factory-bean="factory" factory-method="getUserDao"></bean>
```

***

Bean标签范围配置
* scope：对象作用范围
	* singleton：默认值，单例的
		* 创建多个对象，引用同一个地址值
		* 导入xml文件时就创建对象
	* prototype：多例的
		* 创建多个对象，不同地址值
		* 导入xml文件时不创建对象，`getBean`时创建对象
	* request：WEB项目中，Spring创建一个Bean对象，将对象存入到request域中
	* session：WEB项目中，Spring创建一个Bean对象，将对象存入到session域中
	* global session：web项目种，应用在Portlet环境，如果没有Portlet环境那么globalSession相当于session。
```
<bean id="userDao" class="ca.winbo.branty.dao.impl.UserDaoImpl" scope="singleton"></bean>
```
***

Bean生命周期配置
* 测试生命周期：
	* 在`UserDaoImple`类中加`init()`和`destroy()`
	* 在xml配置文件中添加`init-method="init"` 和`destroy-method="destroy"`
```
<bean id="userDao" class="ca.winbo.branty.dao.impl.UserDaoImpl" init-method="init" destroy-method="destroy"></bean>

//测试类种强转类型，手动关闭destroy()才能显示
((ClassPathXmlApplicationContext)app).close();
```
***
### Spring的依赖注入
场景：在`UserServiceImpl`调用`UserDao`不需要再额外创建`new ClassPathXmlApplicationContext("applicationContext.xml")`对象。直接使用userDao。
```
#UserServiceImple调用UserDao，UserDao已创建new ClassPathXmlApplicationContext("applicationContext.xml")
public class UserServiceImpl implements UserService {  
	private UserDao userDao;   
	public UserDao getUserDao() {  
		return userDao;  
	}  
	public void setUserDao(UserDao userDao) {  
		this.userDao = userDao;  
	}  
	@Override  
	public void save() {  
		System.out.println("service...");  
		userDao.save();
	}  
}

#依赖注入需要在xml配置，属于set方式，如setUserDao
<bean id="userDao" class="ca.winbo.branty.dao.impl.UserDaoImpl"></bean>  
<bean id="userService" class="ca.winbo.branty.service.impl.UserServiceImpl">  
	 <property name="userDao" ref="userDao"></property>  
</bean>
# property说明：
# name的userDao是来自UserServiceImpl的setUserDao
# ref的userDao是来自xml的id，属于对象引用，如果是数据类型则不需要
```
其他注入方式：
```
#命名空间set方式
<?xml version="1.0" encoding="UTF-8"?>  
<beans xmlns="http://www.springframework.org/schema/beans"  
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
  xmlns:p="http://www.springframework.org/schema/p"   #此处为命名空间
  xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<bean id="userDao" class="ca.winbo.branty.dao.impl.UserDaoImpl"></bean>  
<bean id="userService" class="ca.winbo.branty.service.impl.UserServiceImpl" p:userDao-ref="userDao">
```
```
#UserServiceImpl的构造方法
private UserDao userDao;  
public UserServiceImpl(UserDao userDao) {  
    this.userDao = userDao;  
}    
public UserServiceImpl() {  
}
 
#构造方法注入，XML配置
<bean id="userDao" class="ca.winbo.branty.dao.impl.UserDaoImpl"></bean>
<bean id="userService" class="ca.winbo.branty.service.impl.UserServiceImpl">  
	 <constructor-arg name="userDao" ref="userDao"></constructor-arg>  
</bean>

# constructor-arg讲解
# name的userDao为构造方法参数名
# ref的userDao为容器bean的id名
```

***
引入配置文件（分模块开发）
* 主`applicationContext.xml`文件导入其他文件
	* `<import resource="applicationContext-xxx.xml"/>`
* 其他配置文件如`applicationContext-user.xml`

### Spring相关API

`ApplicationContext`实现类：
* `ClassPathXmlApplicationContext`：xml都放至在resource目录下
* `FileSystemXmlApplicationContext`：使用系统磁盘路径
* `AnnotationConfigApplicationContext`：使用注解配置容器

getBean()方法使用：
* 源码
```
public Object getBean(String name) throws BeansException{
	assertBeanFactoryActive();
	return getBeanFactory().getBean(name);
}
public <T> T getBean(Class<T> requiredType) throws BeansException{
	assertBeanFactoryActive();
	return getBeanFactory().getBean(requiredType);
}
```
* 使用
	* `app.getBean("id")`
	* `app.getBean(Class)`
```
ApplicationContext app = new FileSystemXmlApplicationContext("C:\\User\\apple\\Idea\\..\\resources\applicationContext.xml");
#方法一：
(UserService) userService = (UserService) app.getBean("userService");
方法二：
UserService userService = app.getBean(UserService.class);

#当容器xml中同个类型(class="..")的对象存在多个情况下，如
#<bean id="userService" class="ca.winbo.branty.service.impl.UserServiceImpl">
#则只能使用id，方法一。
```

***
### Spring配置数据源
第一种格式，直接配置xml
```
# <bean id="ID名称" class="数据对象类型">
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
# <proprety name="setXXX" value="具体值"></property>
#property的name就是setDriverClass
<property name="driverClass" value="com.mysql.jdbc.Driver"></property>
...
</bean>

@Test  
public void test4() throws Exception {  
	ApplicationContext app = new ClassPathXmlApplicationContext("ApplicationContext.xml");  
	DataSource dataSource = app.getBean(DataSource.class);  
	Connection connection = dataSource.getConnection();  
	System.out.println(connection);  
	connection.close();  
}
```

第二种格式，配置XML以及properties文件
```
<?xml version="1.0" encoding="UTF-8"?>  
	<beans xmlns="http://www.springframework.org/schema/beans"  
			xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
			xmlns:context="http://www.springframework.org/schema/context"  
	xsi:schemaLocation= 
			"http://www.springframework.org/schema/beans 
			http://www.springframework.org/schema/beans/spring-beans.xsd 
			http://www.springframework.org/schema/context 
			http://www.springframework.org/schema/context/spring-context.xsd">  
	  
	  
	<!--加载外部的配置文件-->  
	<context:property-placeholder location="classpath:jdbc.properties"/>  
	#classpath为resource目录
	
	<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">  
		<!--property的name为DataSourceTest.java里面的ComboPooledDataSource对象即dataSource的setXXX方法-->  
		<property name="driverClass" value="${jdbc.driver}"></property>  
		<property name="jdbcUrl" value="${jdbc.url}"></property>  
		<property name="user" value="${jdbc.username}"></property>  
		<property name="password" value="${jdbc.password}"></property>  
	</bean>
</beans>
```

***

#### Spring原始注解

Spring原视注解主要是替代`<Bean>`的配置
| 注解 | 说明 |
|:--------:| -------------:|
| @Component| 使用在类上用，于实例化Bean |
| @ControllerComponent| 使用在web层类上，用于实例化Bean |
| @Service| 使用在servce层类上，用于实例化Bean |
| @Respository| 使用在dao层类上，用于实例化Bean |
| @Autowired| 使用在字段上，用于根据类型依赖注入 |
| @Qualifier| 结合@Autowired一起使用，用于根据名称进行依赖注入 |
| @Resource| 相当于@Autowired + @Qualifier，按照名称进行注入 |
| @Value| 注入普通属性 |
| @Scope| 标注Bean的作用范围 |
| @PostConstruct| 使用在方法上，标注该方法是Bean的初始化方法 |
| @PreDestory| 使用在方法，上标注该方法是Bean的销毁方法 |

举例：
```
//注册组件
//<bean id="userDao" class="ca.winbo.branty.test.dao.daoImpl.UserDaoImpl"></bean>  
@Component("userDao")  
public class UserDaoImpl implements UserDao {

//注册组件
//<bean id="userService" class="ca.winbo.branty.test.service.impl.UserServiceImpl">  
@Component("userService")  
public class UserServiceImpl implements UserService {

//注册引用
//<property name="userDao" ref="userDao"></property>  
@Autowired  #按照数据类型从Spring容器中进行匹配Qualifier可以不写（只限一一对应，多个bean不行）
@Qualifier("userDao")  #按照id值从容其中进行匹配，但是主要此处@Qualifier结合@Autowired一起使用。
//@Resource(name="userDao")  #@Resource相当于@Qualifier + @Autowired
private UserDao userDao;
public void setUserDao(UserDao userDao){  
    this.userDao=userDao;  
}
#如果通过注解，为反射机制，可以不写setUserDao方法。

<!--配置组件扫描-->  
<context:component-scan base-package="ca.winbo.branty.test"/>


@Service("userService")  
public class UserServiceImpl implements UserService {  
    @Value("${jdbc.driver}")  #value的其他用法
    private String driver;
}
System.out.println(driver);
#value且需要配置文件
```
#### Spring新注解
| 注解 | 说明 |
|:--------:| -------------:|
| @Configuration | 用于指定当前类是一个Spring配置类，当创建容器时会从该类上加载注解 |
| @ComponentScan | 用于指定Spring在初始化容器时要扫描的包。 作用和在Spring的xml配置文件中的`<content:component-scan base-package="com.iteheima" />`一样 |
| @Bean | 使用在方法上，标注将该方法的返回值存储到Spring容器中 |
| @PropertySource | 用于加载properties文件中的配置 |
| @Import | 用于导入其他配置类 |



*新注解使用范例：*
```
#创建Spring配置类
//标志该类是Spring的核心配置类  
@Configuration  
//<context:component-scan base-package="ca.winbo.branty.test"/>  
@ComponentScan("ca.winbo.branty.test")  
//<import resource="其他xml配置"/>  
@Import({DataSourceConfiguration.class})//（{XXX,XX}）数组可添加多个  
public class SpringConfiguration {  
		
}

#其他配置辅助类
//<context:property-placeholder location="classpath:jdbc.properties"/>  
@PropertySource("classpath:jdbc.properties")  
public class DataSourceConfiguration {  
  
	@Value("${jdbc.driver}")  
	private String driver;  
	@Value("${jdbc.url}")  
	private String url;  
	@Value("${jdbc.username}")  
	private String username;  
	@Value("${jdbc.password}")  
	private String password;  
  
	@Bean //或者加参数 @Bean(dataSource)   ->Spring会将当前方法的返回值以指定名称存储到容器中  
	public DataSource getDataSource() throws PropertyVetoException {  
		ComboPooledDataSource dataSource = new ComboPooledDataSource();  
		dataSource.setDriverClass(driver);  
		dataSource.setJdbcUrl(url);  
		dataSource.setUser(username);  
		dataSource.setPassword(password);  
		return dataSource;  
  }  
}

#调用配置类
ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfiguration.class);  
UserService userService = app.getBean(UserService.class);  
userService.save();

```

***
**原视Junit测试Spring**
Spring集成Junit步骤
1. 导入spring继承Junit的坐标
2. 使用@Runwith注解替换原来的运行期
3. 使用@ContextConfiguration指定配置文件或配置类
4. 使用@Autowired诸如需要测试的对象
5. 创建测试方法进行测试

示例：
```
@RunWith(SpringJUnit4ClassRunner.class)  //固定写法
@ContextConfiguration("classpath:ApplicationContext.xml") //加载配置文件方式，默认value="xx"省略value
//@ContextConfiguration(classes = {SpringConfiguration.class}) //通过全注解方式 
public class SpringJunitTest {  
  
  @Autowired  
  private UserService userService;  
  @Autowired  
  private DataSource dataSource;  
  
  @Test  
  public void test1() throws SQLException {  
      userService.save();  
	  System.out.println(dataSource.getConnection());  
  }  
}
```

