### 会话技术
* 绘画：一次会话中包含多次请求和响应。
	* 一次会话：浏览器第一次给服务器资源发送请求，会话建立，直到有一方断开为止。
* 功能：在一次会话的范围内多次请求间，共享数据。
* 方式：
	* 客户端会话技术：Cookie
	* 服务器端会话技术：Session

***
### Cookie
快速入门：
* 创建Cookie对象，绑定数据
	* `new Cookie(String name, String value)`
* 发送Cookie对象
	* `response.addCookie(Cookie cookie)`
* 获取Cookie，拿到数据
	* `Cookie[] request.getCookies()`

示例：
```
@WebServlet("/cookieDemo1")
public class CookieDemo1 extends HttpServlet{
	protected void doPost(..){
		//创建Cookie对象
		Cookie c = new Cookie("msg“，”hello");
		//设置cookie的存活时间
		c.setMaxAge(30);//将cookie持久化到硬盘，30秒后会自动删除cookie文件
		//发送Cookie
		response.addCookie(c);
	}
}

@WebServlet("/cookieDemo2")
public class CookieDemo2 extends HttpServlet{
	protected void doPost(..){
		//获取Cookie对象
		Cookie[] cs = request.getCookies();
		//获取数据，遍历Cookies
		if(cs != null){
			for(Cookie c : cs){
				String name = c.getName();
				String value = c.getValue();
				System.out.println(name+": "+value);
			}
		}	
	}
}
```
***
Cookie实现原理
* 基于响应头set-cookie和请求头cookie实现

Cookie细节：
*	一次可不可以发送多个cookie
	*	可以
	*	可以创建多个Cookie对象，使用response调用多次addCookie方法发送cookie即可。
*	cookie在浏览器中保存多长时间
	*	默认情况下，当浏览器关闭后，Cookie数据被销毁
	*	持久化存储
		*	`setMaxAge(int seconds)`
			*	正数：将Cookie数据写道硬盘的文件中，持久化存储，Cookie存活时间，浏览器关掉还有存活时间。
			*	负数：默认值，存在浏览器中，浏览器关掉删除cookie。
			*	零：删除cookie信息。
*	cookie能不能存中文
	*	在tomcat8之前cookie中不能直接存储中文数据
		*	需要将中文数据转码，一般采用URL编码(%E3)
	*	tomcat8之后cookie可以存中文数据
*	cookie获取范围多大(共享问题)
	*	假设在一个tomcat服务器中，部署了多个web项目，那么在这些web项目中cookie能不能共享？
		*	默认情况下cookie不能共享
			*	`setPath(String path)`：设置cookie的获取范围。默认情况下，设置当前的虚拟目录。如果要共享，将`path`设置成`/`。
		`cookie c1 = new Cookie("msg","hello");`	
		`c1.setPath("/");`	
	* 不同的tomcat服务器间cookie共享问题
		* `setDomain(String path)`：如果设置一级域名相同，那么多个服务器之间cookie可以共享
		* `setDomain(".baidu.com")`，那么`tieba.baidu.com`和`news.baidu.com`中cookie可以共享。
***
cookie特点和作用
* cookie存储数据在客户端浏览器(安全性容易被篡改)
* 浏览器对于单个cookie的大小限制(4kb)以及对同一个域名下的总cookie数量有限制(20个)
* cookie一般用于存储少量的不太敏感的数据
* 在不登陆的情况下，完成服务器对客户端的身份识别
***
案例：记住上一次访问时间
需求：
* 访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问。
* 如果不是第一次访问，则提示：欢迎回来，您上次访问事件为：显示时间字符串。
```
protected void doPost(..){
	//设置响应的消息体的数据格式以及编码
	response.setContentType("text/html;charset=utf-8");
	//获取所有Cookie
	Cookie[] cookies = request.getCookies();
	boolean flag = false;//没有cookie为lastTime
	if(cookie != null && cookies.length > 0){
		for(Cookie cookie : cookies){
			//获取cookie名称
			String name = cookie.getName();
			if("lastTime".equals(name)){
				//有该Cookie，不是第一次访问
				
				//设置Cookie的value
				//获取当前时间的字符串，重新设置Cookie的值，重新发送cookie
				Date date = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");//不支持空格，需要URL编码
				String str_date = sdf.formate(date);
				System.out.println("编码前"+str_date);
				//URL编码
				str_date = URLEncoder.encode(str_date,"utf-8");
				System.out.println("编码后",+str_date);
				cookie.setValue(str_date);
				//设置cookie存活时间
				cookie.setMaxAge(60*60*24*30);//1个月
				response.addCookie(cookie);
				
				//响应数据
				//获取Cookie的value时间
				String value = cookie.getValue();
				System.out.println("解码前:"+value);
				//URL解码
				value = URLDecoder.decode(value,"utf-8");
				System.out.println("解码后:"+value);
				response.getWriter().write("<1>欢迎回来，您上次访问时间为："+value+"<h1>");
				break;
			}
		}
	}
	if(cookies == null || cookies.length == 0 || flag == false){
		//没有第一次访问
		//设置Cookie的value
		//获取当前时间的字符串，重新设置Cookie的值，重新发送cookie
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String str_date = sdf.formate(date);
		cookie.setValue(str_date);
		//设置cookie存活时间
		cookie.setMaxAge(60*60*24*30);//1个月
		response.addCookie(cookie);
		response.getWriter().write("<h1>您好，首次访问<h1>");
	}
}
```
***
### JSP入门学习
概念：Java Server Pages： java服务器端页面
可以理解为：一个特殊的页面，其中既可以指定定义html标签，又可以定义java代码，用于简化书写。
原理：JSP本质上就是一个Servlet。
JSP脚本：JSP定义Java代码的方式
* `<% 代码 %>`：定义的java代码在service()方法中。
* `<%! 代码%>`：定义的java代码，在jsp转换后的java类的成员位置。(不推荐，容易引发线程安全问题)
* `<%= 代码%>`：定义的java代码会输出到页面上。

JSP的内置对象：jsp页面中不需要获取和创建，可以直接使用的对象。一共有9个内置对象。
* `request`、`response`、`out`(与`response.getWriter()`类似但有区别，先于out输出)、
`<% String contextPath = request.getContextPath(); %>`
`<% out.print(contextPath); %>`
***
### Session
概念：服务器端会话技术，再一次会话的多次请求间共享数据，将数据保存在服务器端的对象中。`HttpSession`
原理：==Session的实现是依赖于Cookie的==。
快速入门：
* HttpSession对象
	* `Object getAtrribute(String name)`
	*  `void setAttribute(String name, Object value)`
	* `void removeAttribute(String name)`

```
@WebServlet("/sessionDemo1")
public class SessionDemo1 extends HttpServlet{
	protected void doPost(..){
	//使用session共享数据
	//获取session
	HttpSession session = request.getSession();
	//存储数据
	session.setAttribute("msg","hello session");
	}
}

@WebServlet("/sessionDemo2")
public class SessionDemo2 extends HttpServlet{
	protected void doPost(..){
	//使用session共享数据
	//获取session
	HttpSession session = request.getSession();
	Object msg = session.getAttribute("msg");
	System.out.println(msg);
	}
}
```
细节：
* 当客户端关闭后，服务器不关闭，两次获取session是否同一个
	* 默认情况下，不是。
 ```
 //获取Session
 HttpSession session = request.getSession();
 //期望客户端关闭后，session也能相同
 Cookie c = new Cookie(JSESSIONID",session.getId());
 c.setMaxAge(60*60); `
 response.addCookie(c);
```
* 客户端不关闭，服务器关闭后，两侧获取的session是同一个吗
	* 不是同一个，但是要确保数据不丢失(如购物车的浏览器关闭)
		* session的钝化：在服务器正常关闭之前，将session对象系列化到硬盘上。
		* session的活化：在服务器启动后，将session文件转化为内存中的session对象即可。
* session的失效时间
	* 服务器关闭
	* session对象调用`invalidate()`
	* session默认失效时间30分钟(浏览器打开情况下)
		*  `<session-config>`
		*  `<session-timeout>30</session-timeout>`
		*  `<session-config>`

Session的特点
* session用于存储一次会话的多次请求的数据，存在服务器端
* session可以存储任意类型，任意大小的数据
* 区别：
	* session存储数据在服务器端，cookie在客户端
	* session没有数据大小限制，cookie有
	* session数据安全，cookie相对其不安全
	 
***
Login.jsp
```
<form action="/day16/loginServlet" method="post">
	<table>
		<td><tr>..用户名+密码+验证码</tr></td>
	</table>	
```		
LoginServlet
```
@WebSErvlet("loginServlet")
public class LoginSErvlet extends HttpServlet{
	protected void doPost(..){
		//设置request编码
		request.setCharacterEncoding("utf-8");
		//获取参数Map,集合获取需要导包等操作
		//Map<String,String[]> parameterMap = request.getParameterMap();
		String username = request.getparameter("username");
		String password = request.getparameter("password");
		String checkCode = request.getparameter("checkCode");
		
		//两次请求，一次请求当前login页，一次请求验证码，验证码传入session共享setAttribute(）
		//获取生成的验证码
		HttpSession session = request.getSession();
		//验证码需要稍作修改传入session
		String checkCode_session = session.getAttribute("checkCode_session")
		//删除session中存储的验证码，否则用户后退页面验证码一样，可重复使用不安全
		session.removeAttribute("checkCode_session");
		//先判断验证码是否正确
		if(checkCode_session != null && checkCode_session.equalsIgnoreCase(checkCode)){
			//忽略大小写
			//判断用户名和密码是否一致
			if("zhangsan".equals(username) && "123".equals(password)){
				//登陆成功
				//存储信息
				session。色图Attribute（“user",username);
				//重定向到success
				response.sendRedirect(request.getContextPath()+"/success.jsp");
			}else{
				//登陆失败
				request.setAttribute("cc_error","验证码错误");
				//转发到登录页面
				request.getRequestDispatcher("login.jsp").forward(request,response);
			}
		}else{
			//验证码不一致
			//存储提示信息到request
			request.setAttribute("cc_error","验证码错误");
			//转发到登录页面
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
	}
}
```

