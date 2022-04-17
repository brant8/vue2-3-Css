### 在Servlet配置容器

目的：让Servlet在服务器开启时候就对容器进行创建app。
```
#UserServlet
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
	ServletContext servletContext = req.getServletContext();//或者 this.getServletContext()  
	ApplicationContext app = (ApplicationContext)servletContext.getAttribute("app"); //默认local variable是Object，在知道类型情况下强转 
	UserService bean = app.getBean(UserService.class);  
	bean.save();  
}

#创建监听器，在Servlet开启时监听
public class ContextLoaderListener implements ServletContextListener {  
	@Override  
	public void contextInitialized(ServletContextEvent sce) {  
		#创建容器
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml"); 
		 
		//将Spring的应用上下文对象存储到ServletContext域中  
		ServletContext servletContext = sce.getServletContext();  
		servletContext.setAttribute("app",app);  
		
		System.out.println("spring容器创建完毕");  
		
/**  优化设置application.xml为动态名称（也需要在web.xml配置）
 *         ServletContext servletContext = sce.getServletContext();          //读取web.xml中的全局参数  
 *         String contextConfiguration = servletContext.getInitParameter("contextConfiguration");  
 * *       ApplicationContext app = new ClassPathXmlApplicationContext(contextConfiguration); */
}

#WEB-INF下的web.xml配置或者直接@WebServlet("..")
<servlet>  
	<servlet-name>UserServlet</servlet-name>  
	<servlet-class>com.example.spring_mvc.web.UserServlet</servlet-class>  
</servlet> 

<servlet-mapping> 
	<servlet-name>UserServlet</servlet-name>  
	<url-pattern>/userServlet</url-pattern>  
</servlet-mapping> 
 
<!-- 配置监听器-->  
<listener>  
	<listener-class>com.example.spring_mvc.listener.ContextLoaderListener</listener-class>  
</listener>

/**<!-- 优化设置 web.xml配置 --> 
 * <!--    全局初始化参数 让Servlet读取applicationContext的配置文件名称-->  
 * <context-param>  
 * <param-name>contextConfigLocation</param-name>  
 * <param-value>applicationContext.xml</param-value>  
 * </context-param>  
 */
```

解除耦合
```
#创建Utils
public class WebApplicationContextUtils {  
	public static ApplicationContext getWebApplicationContext(ServletContext servletContext){  
		return (ApplicationContext)servletContext.getAttribute("app");  
  }  
}

#在Servlet调用
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
    ServletContext servletContext = req.getServletContext();//或者 this.getServletContext() 
	//ApplicationContext app = (ApplicationContext)servletContext.getAttribute("app");
	#解除耦合，在代码中消除"app"变量名称
	ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(servletContext);  
	  UserService bean = app.getBean(UserService.class);  
	  bean.save();  
}
```
使用Spring解耦，集成web环境步骤
```
<!-- 全局初始化参数-->  
<context-param>  
	<param-name>contextConfigLocation</param-name>  
	<param-value>classpath:applicationContext.xml</param-value>  
</context-param>
 
<!-- 配置监听器-->  
 <listener>  
	 <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>  
 </listener>

<!--###########################################-->
在Servlet调用
ApplicationContext app = WebApplicationContextUtils.getWebApplicationContext(servletContext);
```

***
### SpringMVC
快速使用：访问localhost/quick自动跳转到success.jsp页面。
实现代码：需要导包spring-webmvc
说明：
* `forward:/WEB-INF/views/index.jsp`转发，`WEB-INF`为受保护文件夹
* `redirect:/index.jsp`重定向，客户端再次访问/请求，需要放在公共文件夹，无法访问`WEB-INF`文件夹
* SpringMVC内置代码，有：`return "redirect：/success.jsp"`，重定向，地址转变`localhost/success.jsp`
* 还有另外一个参数(默认)：`forward:/success.jsp`，转发，地址不变为`localhost/user/quik?username=xxx`
```
#加载配置：WEB-INF/web.xml
<!--第一步： 配置SpringMVC的前端控制器-->  
<servlet>  
	<servlet-name>DispatcherServlet</servlet-name>  
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class> 
	
	<!--第五步：启动时加载spring-mvc配置文件-->  
	<init-param>  
		<param-name>contextConfigLocation</param-name>  
		<param-value>classpath:spring-mvc.xml</param-value>  
	</init-param> 

	<load-on-startup>1</load-on-startup>  
</servlet> 
<servlet-mapping> 
	<servlet-name>DispatcherServlet</servlet-name>  <!--主控制器-->
	<url-pattern>/</url-pattern>  <!--斜杠表示缺省（默认），客户端发送请求找对应servlet，如找不到则找缺省的-->
	<!--，使用/为任何请求都得走该Servlet。如果是*.xx则为当文件属于某个xx格式时使用该servlet-->  

</servlet-mapping>


#第二步，创建类并放至容器
@Controller //放至容器当中,还需要配置文件扫一下该注解  
@RequestMapping("/user")
public class UserController {  
	//请求地址http://localhost:8080/user/quick
	@RequestMapping(value = "/quick",method = RequestMethod.GET,params = {"username"}) //请求映射 //请求映射 地址为localhost/quick
	public String save(){  
		System.out.println("Controller save running..");  
		return "/success.jsp";   //跳转到jsp页面
  }  
}

#第三步创建配置文件，并扫描
<?xml version="1.0" encoding="UTF-8"?>  
<beans xmlns="http://www.springframework.org/schema/beans"  
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"  
	xmlns:context="http://www.springframework.org/schema/context"  
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
	http://www.springframework.org/schema/beans/spring-beans.xsd  
	http://www.springframework.org/schema/context 
	http://www.springframework.org/schema/context/spring-context.xsd">  

	<!--    Controller的组件扫描, 非beans,需要设置context命名空间-->  
	<context:component-scan base-package="com.example.spring_mvc.controller"/>  
	

	
	<!-- 配置内部资源视图解析器-->  
	<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">  
		<!-- ## servlet使用return "success"，实际拿到的是/jsp/success.jsp --> 
		 
		<property name="prefix" value="/jsp/"></property>  
		<property name="suffix" value=".jsp"></property>  
		
		<!--#InternalResourceViewResolver下有getPrefix(),getSuffix()-->
	</bean>
	

	<!--其他方式的组件扫描
		<context:component-scan base-package="com.example.spring_mvc">  
			<context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>  
			#该controller为xx.java的注解复制的路径
			#context:include-filter 反之有exclude-filter
		</context:component-scan>
	-->

#第四步
编写jsp页面。	
</beans>

```
`<servlet-name>DispatcherServlet</servlet-name>`的DispatcherServlet进行虚拟化路径匹配，去匹配`@RequestMapping`，含有js文件时，浏览器F12的Network可以观察到，jsp请求时也会寻找jquery.js路径，当时MVC会把jquery.js当作RequestMapping去匹配，但是找不到路径。
在`spring_mvc.xml`添加`<mvc:resources mapping="/js/**" location="/js/" />  `为在那些路径下开放资源访问权限。
注：mvc要自行添加命名空间
* mapping：前端访问的地址  
* location：后端的具体目录

***
### SpringMVC相关组件

* 前端控制器：DispatcherServlet
* 处理器映射器：HandlerMapping
* 处理器适配器：HandlerAdapter
* 处理器：Handler
* 视图解析器：View Resolver
* 视图：View

#### SpringMVC的注解和配置
* 请求映射注解：@RequestMapping
* 视图解析器配置
```
REQIRECT_URL_PREFIX="redirect:"
FORWARD_URL_PREFIX="forward:"
prefix="";
suffix="";
```
#### SpringMVC的数据相应方式
* 页面跳转
	* 直接返回字符串：web层相当于`request.getDispatcherXX`和`response.send(redirect:XX)`
	* 通过ModelAndView对象返回
* 回写数据
	* 直接返回字符串：web层相当于`response.getWriter()`
	* 返回对象或集合

##### 乱码问题：
```
# 在WEB-INF/web.xml配置
<!-- 配置全局过滤的filter 解决乱码问题-->  
<filter>  
	<filter-name>CharacterEncodingFilter</filter-name>  
		<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>  
	<init-param> 
		<param-name>encoding</param-name>  
		<param-value>UTF-8</param-value>  
	</init-param> 
</filter> 

<filter-mapping> 
	<filter-name>CharacterEncodingFilter</filter-name>  
	<url-pattern>/*</url-pattern>  
</filter-mapping>
```

#### 自定义类型转换器
客户端提交的字符串转换成int型进行参数设置。
不是所有类型都提供了转换器，有的需要自定义转换器。
转换器开发步骤：
* 定义转换器类实现Converter接口
* 在配置文件中声明转换器
* 在`<annotation-driver>`中引用转换器

```
#第一步，继承接口
import org.springframework.core.convert.converter.Converter;  
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
  
public class DateConverter implements Converter<String, Date> { //String转成Date  
  
  @Override  
  public Date convert(String s) {  
        //将日期字符串转换成日期对象 返回  
	  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
	  Date date = null;  
	 try {  
            date = format.parse(s);  
		  } catch (ParseException e) {  
            e.printStackTrace();  
		  }  
        return null;  
	  }  
}

#第二步，在mvc_spring.xml中声明转换器
<!-- 声明转换器-->  
 <bean id="ConversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">  
	 <property name="converters">  
		 <list> 
			 <bean class="com.example.spring_mvc.converter.DateConverter"></bean>  
		 </list> 
	 </property> 
 </bean>

#第三步，mvc_spring.xml引用转换器
<!--mvc的注解驱动-->  
<mvc:annotation-driven conversion-service="ConversionService"/>
``` 
