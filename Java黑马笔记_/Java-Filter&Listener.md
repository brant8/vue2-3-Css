### JavaWeb的Filter
* Servlet
* Filter
* Listener

Filter过滤器：一般用于通用的操作比如登录操作，统一编码处理，敏感字过滤(脏话)..
快速入门：
* 定义一个类，实现接口Filter
* 复写方法
* 配置拦截路径2种方法
	* web.xml
	* 注解
```
@WebFilter("/*") //配置，访问所有资源之前，都会执行该过滤器
public class FilterDemo implements Filter{
	//服务器启动后，会创建Filter对象，然后调用init方法，只执行一次。用于加载资源。
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.print("init...");
	}
	//每一次请求被拦截资源时，会执行
	public void doFilter(ServletRequest servletRequest, ServletResponse servletReponse, FilterChain filterChain) throws ServletException {
		//对request对象请求消息增强
		System.out.println("filterDemo1被执行了");
		//放行，拦截后如不放行，页面显示为空
		FilterChain.doFilter(servletRequest,servletResponse);
		//对response对象的影响消息增强
		System.out.println("filterDemo1回来了");
	}
	//在服务器关闭后，Filter对象被销毁，如果服务器是正常关闭，则会执行destroy方法
	public void destroy() {
		System.out.println("destroy...");
	}
}
```
***
过滤器细节：
* web.xml配置
```
...
<filter>
	<filter0name>demo1</filter-name>
	<filter-class>cn.itecase.web.filter.FilterDemo1</filter-class>
</filter>
<filter-mapping>
	<filter-name>demo1</filter-name>
	<!--拦截路径-->
	<url-pattern>/*</url-pattern>
</filter-mapping>
```
* 过滤器执行流程
* 过滤器生命周期方法
	* `init()`
	* `doFilter()`
	* `destroy()`
* 过滤器配置详解
	* 拦截路径配置
		* 具体资源路径：`/index.jsp`只有访问`index.jsp`资源时，过滤器才会被执行。
		* 拦截方式配置：`/user/*` 访问`/user`下的所有资源时，过滤器都会被执行。
		* 后缀名拦截：`*.jsp`访问所有jsp资源时，过滤器都会被执行。
		* 拦截所有资源：`/*` 访问所有资源时，过滤器都会被执行。
	* 拦截方式配置：资源被访问的方式
		* 注解配置
			* 设置`dispatcherTypes`属性
				* `REQUEST`：默认值，浏览器直接请求资源。
				* `FORWARD`：转发访问资源
				* `INCLUDE`：包含访问资源
				* `EROR`：错误跳转资源
				* `ASYNC`：异步访问资源
		* web.xml
			* 设置`<dispatcher></dispatcher>`即可
```
//浏览器直接请求index.jsp资源时，该过滤器会被执行,转发请求不会被执行
//如ServletDemo转发到index.jsp不会被执行
//request.getRequestDispatcher("/index.jsp").forward(request,response);
//DispatcherType是一个数组，如dispatcherTypes={DispatcherType.Forward,DispatcherType.REQUEST}

@WebFilter(value="/index.jsp",dispatcherTypes = DispatcherType.REQUEST)
public class FilterDemo5 implements Filter{
	public void doFilter(..) throws ..{
	System.out.println("filterDemo5);
	chain.doFilter(req,resp);
	}
}
```
* 过滤器链(配置多个过滤器)
	* 执行顺序和返回顺序
	* 先后顺序
		* 注解配置
			* 按照类名的字符串比较规则，较小的先执行
			* 如`AFilter`和`BFilter`
		* web.xml配置
			* 根据`filter-mapping`配置顺序，先配先执行。
***
### Listener:监听器
概念：web的三大组件之一
* 事件监听机制
	* 事件：一件事情
	* 事件源：事件发生的地方
	* 监听器：一个对象
	* 注册监听：将事件、事件源、监听器绑定在一起。当事件源上发生某个事件后，执行监听器代码。
* `ServletContextListener`监听ServletContext对象的创建和销毁
	* `void contextDestroyed(SErvletContextEvent sce)`
	* `void contextInitialized(ServletContextEvent sce)`
* 实现步骤
	* 定义一个类，实现ServletContextListener接口
	* 复写方法
	* xml配置监听器`WEB-INF/web.xml` 或者注解配置`@Weblistener`
		* `<listener>`
		* `<listener-class>cn.itcast.web.listener.ContextLoaderListener</listener-class>`
		* `</listener>`
	

***
案例：登录资源过滤 & 敏感字过滤
### 登录
```
LoginFilter
    
```
判断Session种是否有user
```
LoginServlet.java
...
if(loginUser != null){
	session.setAttribute("user",loginUser);
	response.sendRedirect(request.getContextPath()+"/index.jsp");
}
...
```
**登录验证**
需求：
* 访问案例资源，验证其是否登录
*  如果登录，则直接放行，
* 如果没有登录，则跳转到登录页面，提示”您尚未登陆"

**敏感词汇过滤**
需求：
* 对案例录入的数据进行敏感词汇过滤
* 敏感词汇参考<敏感词汇.txt>
* 如果是敏感词汇，替换为***

分析：
* 对request对象进行增强

增强对象的功能：
* 设计模式：一些通用的解决固定问题的方式
* 装饰模式
* 代理模式
	* 概念： 
		* 真实对象：被代理的对象
		* 代理对象：
		* 代理模式：代理对象代理真实对象，达到增强真是对象功能的目的
	* 实现方式：
		* 静态代理：有一个类文件描述代理模式
		* 动态代理：在内存中形成代理类 
			* 实现步骤：
				* 代理对象和真实对象实现相同的接口
				* 代理对象 = `Proxy.newProxyInstance()`
				* 使用代理对象调用方法
				* 增强方法
		* 增强方式
			* 增强参数列表
			* 增强返回值类型
			* 增强方法体执行逻辑
***
**代理模式案例**：
* 客户 - 代理商 - 厂商(真实对象)

SaleComputer接口（代理商）
```
public Interface SaleComputer{
	public String sale(double money);
	publi void show();
}
```
Lenovo类（真实对象）
```
public class Lenovo implements SaleComputer{
	@Override
	public String sale(double money){
		System.out.println("客户花了"+ money +"元买了一台联想电脑...");
		 return "联想电脑";
	 }
 	@Override
	public void show(){
		System.out.print("展示电脑...");
	}
}
```
测试类
```
public class ProxyTest{
	public static void main(String[] args){
		//创建真实对象
		Lenovo lenovo = new Lenovo();
		
		//一般情况下的调用方法
		String computer = lonovo.sale(8000);//对sale()方法增强用代理
		System.out.println(computer);
		
		//动态代理增强lenovo对象
		//三个固定参数：
		//1.类加载器：真实对象.getClass().getClassLoader()
		//2.接口数组：真实对象.getClass().getInterfaces()
		//3.处理器：new InvocationHandler()
		Proxy.newProxyInstance(lenovo.getClass().getClassLoader(),lenovo.getClass().getInterfaces(), new InvocationHandler(){
		
			//代理逻辑编写的方法：代理对象调用的所有方法都会触发该invoke()方法执行
			//参数： 
			//  proxy - 代理对象，
			//  method - 代理对象调用的方法，被封装为的对象
			//  args - 代理对象调用的方法是，传递的实际参数
			@Override
			Object proxy_lenovo = public Object invoke(Object proxy, Method method, Object[] args)throws Throwable{
				System.out.println("该方法执行了");
				System.out.println(method.getName());
				System.out.println(args[0]);
				//使用真实对象调用该方法
				Object obj = method.invoke(lenovo, args);//
				return obj;
				
				//案例卖电脑实际用法
				//判断是否sale方法
				if(method.getName().equals("sale")){
					//增强参数
					double money = (double) args[0];
					money = money * 0.85;
					//使用真实对象调用该方法
					String obj = method.invoke(lenovo, money);
					//增强返回值
					return obj+"_送：鼠标垫";
				}else{
					Object obj = method.invoke(lenovo, args);
					return obj;
				}
			}
		});
		//调用方法使用代理对象
		String computer = proxy_lenovo.sale(8000);//invoke()方法被执行
		System.out.println(computer);
		//或者调用show
		proxy_lenovo.show()//invoke()方法被执行

	}
}
```
