## HTTP
概念：Hyper Text Transfer Protocol超文本传输协议
- 传输协议：定义了客户端和服务端通信时，发送数据的格式
- 特点：基于TCP/IP的高级协议、默认端口号80、基于请求/响应模型(一次请求对应一次响应)、无状态的(每次请求之间相互独立，不能交互数据)
- 历史版本
	- 1.0：每一次请求响应都会建立新的连接
	- 1.1：复用连接
	
请求消息数据格式
- 请求行
	- 请求方式 		请求url 			请求协议/版本
	- GET			/login/html			HTTP/1.1
		- 请求方式：HTTP有7种请求方式，常用2种
			- GET：请求参数在请求行中，在url后、请求的url长度有限制、不太安全。
			- POST：请求参数在请求体中、请求的url长度没有限制、相对安全
- 请求头：客户端浏览器告诉服务器一些信息
	- 请求头名称：请求头值
	- 常见的请求头：
		- User-Agent：浏览器告诉服务器，我访问你使用的浏览器版本信息(可以在服务器端获取该头的信息，解决浏览器的兼容性问题)
		- Referer：http://localhost/login.html，告诉服务器，我(当前请求)从哪里来
			- 作用：防盗链、统计工作
- 请求空行(分隔正文)
- 请求体(正文)：封装POST请求消息的请求参数


***

### Request：
request对象和response对象原理
1. request和response对象时由服务器创建的，我们来使用它们。
2. request对象时来获取请求消息，response对象是来设置响应消息。

Request：获取请求消息
 ~ ServletRequest  - 接口
 | ->继承
 HttpServletRequest - 接口
 | ->实现
 `HeetServletRequest req` 打印对象后->`org.apache.catalina.connector.RequestFacade@74cb396c` `RequestFacade`类继承`HTTPServletRequest`(tomcat)
***
Request功能：
* 获取请求行数据
	GET /day14/demo?name=zhangsan HTTP/1.1
	* 方法：获取请求方式：GET
		`String getMethod()`
	*	获取虚拟目录：/day14
		==`String getContextPath()`==
	*	获取Servlet路径：/demo1
		`String getServletPath()`
	* 获取get方式请求参数：name=zhangsan
	  `String getQueryString()`
	*  获取请求的URI：/day14/demo
	  ==`String getRequestURI()`==:	/day14/demo1
	  ==`StringBuffer getRequestURL()`==: 	http://localhost/day14/demo1
	  * 获取协议及版本：HTTP/1.1
		`String getProtocol()`
	 * 获取客户机的IP地址：
	  `String getRemoteAddr()`
* 获取请求头数据
	 方法：
	* ==`String getHeader(String name)`==：通过请求头的名称获取请求头的值如`request.getHeader("user-agent")`
	* `Enumeration<String> getHeaderNames()`：获取所有请求头的名称
	```
	 String referer = request.getHeader("referer");
	 System.out.println(referer);
	 //防盗链
	 if(referer != null){
		 //正常访问如源自点击login超链接过来的
		 if(referer.contains("/day14")){
			 //情景1 console显示内容
			 System.out.println("播放电源");
			 //情景2 浏览器显示内容
			 response.setContentType("text/html;charset=utf-8");
			 response.getWriter().write("播放电影");
		}else{
		//盗链
		System.out.print("想看电影来优酷");
		}
	}
	```

* 获取请求体数据：
获取请求体数据只有POST请求方式才有请求体，在请求体中封装了POST请求的请求参数。
步骤：
	* 获取流对象：如视频字节流，文本字符流
		* `BUfferedReader getReader()`：获取字符输入流，只能操作字符数据
		* `ServletInputStream getInputStream()`：获取字节输入流，可以操作所有类型数据
	* 再从流对象中拿数据
```
//获取请求消息体(在doPost方法内)
//获取字符流
BufferedReader br = request.getReader();
//读取数据
String line = null;
while((line = br.readLine()) != null){
	System.out.println(line);
}
```
其他功能：
* 获取请求参数通用方式：不论POST还是GET方法都可以使用以下请求方式
	* ==`String getParameter(String name)`==：根据参数名称获取参数值  `username=sz&password=123`
	* `String[] getparameterValues(String name)`：根据参数名称获取参数值的数组 `hobby=xx&hobby=game`(勾选款checkbox等)
	* `Enumration<String> getParameterNames()`：获取所有请求的参数名称
	* ==`Map<String,String[]> getParameterMap()`==：获取所有参数的map集合 
	* 中文乱码问题
		* get方式：tomcat 8 已经将get方式乱码问题解决了
		* post方式：会乱码。解决：在获取参数前，设置request的编码		`request.setCharacterEncoding("utf-8")`
* 请求转发：一种在服务器内的资源跳转方式。
	* 步骤：
		* 通过request对象获取请求转发器对象：`RequestDispatcher getRequestDispatcher(String path)`
		* 使用Requestdispatcher对象来进行转发：`foward(ServletRequest request, ServletResponse response)`
	* 特点：
		* 浏览器地址栏路径不发生变化
		* 只能转发到当前服务器内部资源中，如`http://...`无法转发
		* 转发时一次性请求
* 共享数据
	* 域对象：一个有作用范围的对象，可以在范围内共享数据
	* request域：代表一次请求的范围，一般用于请求转发的多个资源中共享数据
	* 方法：
		* `void setAttribute(String name, Object obj)`：存储数据
		* `Object getAttribute(String name)`：通过键获取值
		* `void removeAttribute(String name)`：通过键移除键值对
* 获取ServletContext 
	* `ServletContext getServletContext()`

### BeanUtils工具类，简化数据封装
用于封装JavaBean的
* JavaBean：**标准的Java类**
* 要求：
	* 类必须被public修饰
	* 必须提供空参的构造器
	* 成员变量必须使用private修饰
	* 提供公共setter和getter方法
* 功能：封装数据
* 概念：
	* 成员变量
	* 属性：setter和getter方法截取后的产物
		* 例如：getUsername()-->Username->username
	*方法：
		* `setProperty()`：`BeanUtils.setProperty(user,name:"hehe",value:"male")`
		* `getProperty()`：`String gender = BeanUtils.getProperty(user,name:"hehe")`
		* populate(Object obj, Map map)：将map集合的键值对信息，封装到对应的

**User.java部分代码示例**：
```
public void setHehe(String gender)P
	this.gender = gender;
}
public String getHehe(){
	return gender;
}
```

### 案例
[B站视频地址](https://www.bilibili.com/video/BV1uJ411k7wy?p=711&spm_id_from=pageDriver)
* 用户登录案例需求：
	* 编写login.html登录页面， username & password两个输入框
	* 使用Druid数据库连接池技术，操作mysql，day14数据库中的user表
	* 使用JdbcTemplate技术封装JDBC
	* 登陆成功跳转到SuccessServlet展示：登录成功，用户名，欢迎你
	* 登陆失败跳转到FailServlet展示：登陆失败，用户名或密码错误

login.html
```
<head>
	<meta charset="UTF-8">
	<title>...</title>
</head>
<bgody>
	<!--虚拟目录+Servlet的资源路径-->
	<form action="/day14-test/loginServlet" method="post">
	用户名：<input type="text" name="username"><br>
	密码：<input type="password" name="password"><br>
	<input type="submit" value="登录">
	</form>
</body>
```
Druid.properties连接池配置(需要导入jar包)
```
driverClassName=com.mysql.jdbc.Driver
url=jdbc:mysql:///day14
username=root
password=root
initialSize=5
maxActive=10
maxWait=3000
```
user数据库
```
create database day14;
user day14;
create table user(
	id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(32) unique not null,
	password varchar(32) not null
);
```
用户的实体类User.java
```
Public class User（
	private int id;
	private String username;
	private String password;
	...
	//Getter & Setter
```
操作数据库中User表的类UserDao(D.A.O)，登录操作
```
public class UserDao{
	//声明JDBCTemplate对象共用
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
	
	public User login(User loginUser){
		try{
			//编写sql
			String sql = "select * from user where username = ? and password = ?";
			
			//调用query方法
			template.queryForObject(sql, 
				new BeanPropertyRowMapper<User>(User.class),
				loginUser.getUsername(), loginUser.getPassword());
				//如果查询到，返回user其包含全部数据
				return user;
			}catch(DataAccessException e){
				e.printStackTrace();//后期记录日志不打印
				//如果没有查询到，返回null
				return null;
			}
	}
```
JDBC工具类，使用Durid连接池
```
public class JDBCUtils{
	private static DataSource ds;
	//静态连接池
	static{
		try{
			//加载配置文件
			Properties pro = new Properties();//键值对集合
			//使用ClassLoader加载配置文件，获取字节输入流
			InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
			pro.load(is); //异常抛出
			
			//初始化连接池对象
			ds = DruidDataSourceFactory.careateDataSource(pro);//异常抛出
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	//获取连接池对象
	public static DataSource getDataSource(){
		return ds;
	}
	//获取连接Connection对象
	public static Connection getConnection(){
		return ds.getConnection();
	}
}
```
Servlet包(目录需要注意)
```
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		//设置编码
		req.setCharacterEncoding("utf-8");
		//获取请求参数
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		//封装user对象
		User loginUser= new User();
		loginUser.setUsername(username);
		loginUser.setPassword(password);
		/*//上面一个个封装遇到多参操作繁琐，用BeanUtils可以解决
		*//获取所有请求参数 
		*	Map<String,String[]> map = req.getParameterMap();
		*//创建User对象
		*	User loginUser = new User();
		*//使用BeanUtils封装获取User对象
		*try{
		*	BeanUtils.populate(loginUser,map);
		*	}catch{IllegalAccessException e){
		*	e.printStackTrace();
		*	}catch(InvocationTargetException e){
		*	e.printStackTrace();
		*	}
		*/
		
		//调用UserDao的login方法 
		UserDao dao = new UserDao();
		User user = dao.login(loginUser);
		
		//判断user
		if(user == null){
			//登录失败
			req.getRequestDispatcher("/failServlet").farward(req,resp);
		}else{
		//登录成功
		//存储数据
		req.setAttrribute("user",user);
		//转发
		req.getRequestDispatcher("/successServlet").farward(req,resp);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		this.doGet(req,resp);//同样代码直接调用doPost()
	}

}
```
FailServlet
```
@WebServlet("/failServlet");
public class FailServlet extends HttpServlet{
	proceted void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//给页面写一句话

		//设置编码
		response.setContentType("text/html;charset="utf-8");
		//输出
		response.getWriter().write("登录失败，用户名密码错误");
	}
	
	proceted void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	this.doPost(request,response);
	}
}
```
SuccessServlet
```
@WebServlet("/successServlet");
public class SuccessServletextends HttpServlet{
	proceted void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//获取request域中共享的user对象
		User user = (User)request.getAttribute("user");
		if(user !=null){
			//给页面写一句话
			//设置编码
			response.setContentType("text/html;charset="utf-8");
			//输出
			response.getWriter().write("登录成功，“+ user.getUsername() +”，欢迎你");
		}
	}
	
	proceted void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	}
}
```




