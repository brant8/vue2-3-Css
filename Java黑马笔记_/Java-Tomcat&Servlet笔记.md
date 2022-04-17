***
- web服务器软件:Tomcat
- Servlet入门学习(动态资源)
***
网络通讯三要素：IP，端口，传输协议。 

### Servlet: server applet
* 概念：运行在服务器端的小程序
	* Servlet就是一个接口，定义了Java类被浏览器访问到(tomcat识别)的规则。
	* 将来自定义一个类，实现Servlet接口，复写方法。

* 快速入门：
	* 创建Java EE项目
	* 定义一个类，实现Servlet接口
		* public class ServletDemo1 implements Servlet{}
	* 实现接口中的抽象方法
	* 配置Servlet：在WEB-INF/web.xml下的</web-app>内配置
```
    <servlet>
        <servlet-name>demo1</servlet-name>
        <servlet-class>Demo.ServletDemo1</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>demo1</servlet-name>
        <url-pattern>/demo1</url-pattern>
    </servlet-mapping>
```

**执行原理：**
- 当服务器接收客户端浏览器的请求后，会解析请求URL路径，获取访问的Servlet的资源路径
- 查找web.xml文件，是否有对应的<url-pattern>标签体内容。
- 如果有，则在找到对应的<servlet-class>全类名
- tomcat会将字节码文件加载进内存，并且创建其对象
- 调用其方法

Servlet中的生命周期
- 被创建：执行init方法(加载资源)，只执行一次
	- Servlet什么时候被创建时机
		- 默认情况下，第一次被访问是，Servlet被创建
		- 可以配置执行Servlet的创建时机 
			- 第一次被访问时，创建`<load-on-startup>`的值为负数
			- 在服务器启动时，创建`<load-on-startup>`值为0或正整数
```
    <servlet>
        <servlet-name>demo1</servlet-name>
        <servlet-class>Demo.ServletDemo1</servlet-class>
        <load-on-startup>5</load-on-startup> 
    </servlet>
```
- Servlet的init方法，只执行一次，说明一个Servlet在内存中只存在一个对象，Servlet是单例(只有一个对象)
	- 多个用户同时访问是，可能存在线程安全问题
	- 解决：尽量不要再Servlet中定义成员变量(在service方法中定义局部变量并赋值，多线程锁)，即使定义了全局成员变量，也不要对其修改值。

- 提供服务：执行service方法，被执行多次
	- 每次访问Servlet时，Service方法都会被调用一次。
- 被销毁：执行destroy方法，正常关闭只执行一次
	- Servlet被销毁时执行，服务器关闭时，Servlet被销毁
	- 只有服务器正常关闭时，才会执行destroy方法
	- destroy方法在Servlet被销毁之前执行，一般用于释放资源

## Servlet 3.0以上
好处：支持注解配置，可以不需要web.xml了。

- 步骤
	- 创建Java EE项目， 选择Servlet的版本3.0以上，可以不创建web.xml
	- 定义一个类，实现Servlet接口
	- 复写方法
	- 在类上使用@Webservice注解，进行配置
		- `@WebServlet("资源路径")`
```
@webServlet(urlPatterns="/demo")
public class ServletDemo impletments Servlet{..}
//访问， http://localhost:8080/day13_servlet/demo
```
注解：
```
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WebServicelet{
	String name() default ""; //相当于<Servlet-name>
	String[] value() default {}; //代表urlPatterns()属性配置
	String[] urlPatterns() default {}; //相当于<url-pattern>
	int loadOnStartup() default -1;//相当于<load-on-startup>
	WebInitParam[] initParams() default {};
	boolean asyncSupported() default flase;
	String smallIcon() default "";
	String largeIcon() default "";
	String descirption() default "";
	String displayName() default "";
}
```


### IDEA与tomcat的相关配置
- IDEA会为每一个tomcat部署的项目单独建立一个配置文件
	- 查看控制台的log：Using CATALINA_BASE: "C:\...\(路径)"
	- 工作空间项目和tomcat部署的web项目
		- tomcat真正访问的是“tomcat部署的web项目”，“tomcat”部署的web项目“对应着”工作空间项目“的web目录下的所有资源
		- WEB-INF目录下的资源不能被浏览器直接访问
		- 断点调试：使用debug”小虫子“启动

***
### Servlet的体系结构
Servlet -- 接口
		|
GenericServlet -- 抽象类
		|
HttpServlet -- 抽象类

- GenericServlet：将Servlet接口中的其他方法做了默认空实现，只将service()方法作为抽象
	- 将来定义Servlet类时，可以继承GenericServlet，实现service()方法即可。（不常用）
	- HttpServlet（常用）：对http协议的一种封装，简化操作
		- 定义类继承HttpServlet：`extends HttpServlet`
		- 复写doGet/doPost方法
```
String method = req.getMethod();
if("GET".equals(method){
	//get方式获取数据
	doGet();
}else if{"POST".equals(method)){
	//post方式获取数据
	doPost();
}
```

## Servlet相关配置
- urlpartten：Servlet访问路径
	- 一个Servlet可以定义多个访问路径：`@WebServlet({"/d1", "/dd1", "/dd1"})`
	- 路径定义规则
		- /xxx
		- /xxx/xxx：多层路径，目录结构`/user/demo1`
		- *.do

