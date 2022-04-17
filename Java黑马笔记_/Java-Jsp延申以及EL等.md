### JSP
指令
* 作用：用于配置JSP页面，导入资源文件
* 格式：`<%@ 指令名称 属性名1=属性值1 属性名2=属性值2 ... %>`
* 如：`<%@ page contentType="text/html;charset=UTF-8" errorPage="500.jsp" pageEncoding="GBL" language="java" %>`
* 分类：
	* page：配置JSP页面
		* `contentType`：等同于`response.setContentType()`
			* 设置响应体的mime类型以及字符集
			* 设置当前jsp页面的编码(只能是高级IDE才能生效，如果使用低级开发软件，则需要设置`pageEncoding`属性设置当前页面的字符集)
		* `import`：导包
		* `errorPage`：当前页面发生异常后，会自动跳转到指定的错误页面
		* `isErrorPage`：表示当前是否错误页面，`true`,`false`
	* include：页面包含的，导入页面的资源文件
		* `<%include file="top.jsp"%>`
	* taglib：导入资源
		* `<% taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>`
* 注释：HTML`<!-- -->`，JSP`<%-- --%>`
* 内置对象：在jsp页面中不需要创建，直接使用的对象，一共9个。
    | 变量名 | 真实类型 | 作用 |
    | :--------------:| ------------------ | ---------------- |
	 |`pageContext`  | `PageContent`	|	当前页面共享数据，还可以获取其他8个内置对象			
	 | `request` |`HttpServletRequest` | 一次请求访问的多个资源(转发)
	 |`session` |`HttpSession`| 一次绘画的多个请求间
	 |`application` |`ServletContext`| 所有用户间的共享数据，服务器开启共享，关闭销毁
	 | `response` |`HttpServletResponse`| 响应对象
	 | `page` |`Object`| 当前页面(Servlet)的对象`this`
	 | `out` |`JspWriter`| 输出对象，数据输出到页面上
	 |`config` |`ServletConfig`| Servlet的配置对象
	 | `exception` |`Throwable`| 异常对象，只有声明`isErrorPage`才能使用
***
### MVC开发模式
M：完成具体的业务操作，如：查询数据库，封装对象(`JavaBean`)
V：展示数据(`JSP`)
C：获取用户的输入，调用模型，将数据交给视图展示(`Servlet`)

#### EL表达式
概念：Expression Language表达式语言
作用：替换和简化jsp页面中Java代码的编写`${表达式}`如`${ 3>4 }`
* jsp默认支持el表达式
	* 取消单个el表达式：`\${ 3 > 4 }`
	* 取消全部el表达式：设置jsp中page指令`isELIgnored="true"`
* 使用：
	* 运算：算数、比较、逻辑、空运算符`empty`->用于判断字符串，集合，数组对象是否为null并且长度是否为0`${empty list}`
	* 获取值
		* el表达式只能从域对象中获取值
		* 语法：
			* `${域名称.键名}`：从指定域中获取指定键的值
				* 域名称：
					* `pageScope`->`pageContext`
					* `requstScope`->`request`
					* `sessionScope`->`session`
					* `applicationScope`->`application`(`ServletContext`)
				* 例如：在`rquest`域中存储了`name=张三`
			* `${键名}`：表示一次从最小的域中查找是否有该键值对应的值，直到找到位置。
			* 获取对象、List集合、Map集合的值
				* 对象：`${域名称.键名.属性名}`(本质调用对象的getter方法)
				* List集合：`${域名称.键名[索引]}`
				* Map集合：`${域名称.键名[索引]}`
```
el.jsp:
	<%//在域中存储数据
	request.setAttribute("name","张三");
	session.setAttribute("age","23");
	%>
	//获取值
	${requestScope.name}
	${sessionScope.age}
```
获取域中存储的对象值
```
//User.java
public class User{
	...(setter, getter)
}
//el3.jsp
<%
User user= new User();
user.setName("张三");
user.setAge(23);
user.setBirthday(new Date());
request.setAttribute("u",user);
%>

<h3>el获取对象中的值</h3>
${requestScope.u} //输出内存地址值

//通过的是对象的属性来获取(JavaBean属性)
/***setter或getter方法，去掉set或get，剩余部分首字母变小写***/
//setName->Name->name
${requestScope.u.name} //输出具体字符串值'张三'
${u.birthday} //显示英文日期格式
${u.birthday.month} //通过java.util.Date自带getMonth()得来
${u.birStr} //在User.java中建立方法getBirStr()自定义格式化日期

//集合
<%
List list = new ArrayList();
list.add("aaa");
list.add("bbb");
list.add(user);
request.setAttribute("list",list); 
%>
<h3>list值</h3>
${list}
${list[0]}
${list[1]}
${list[2].name}

//Map集合
<%
Map map = new HashMap();
map.put("sname","李四");
map.put("gender","男");
map.put("user",user); //放对象
request.setAttribute("map",map);
%>
<h3>获取值</h3>
${map.gender}
${map["gender"]}
${map.user.name}//获取对象的属性值

//empty运算符
<%
String str= "abc";
request.setAttribute("str",str);
List list = new ArrayList();
request.setAttribute("list",list);
%>
${empty str} //显示false
${not empty list} //显示false
```
***
EL隐士对象：
* el表达式中有11个隐式对象
	* 例如：`pageContext`，获取jsp其他八个内置对象。
		* `${pageContext.request.contextPath}`：动态获取虚拟目录
***
### JSTL
概念：JavaServer Pages Tag Library， jsp标准标签库。
作用：用于简化和替换jsp页面上的java代码。
使用步骤：
* 导入jstl相关jar包
* 引入标签库：taglib指令：`<%@ taglib%>`
* 使用标签
常用JSTL标签
* if：相当于if语句
* choose：相当于switch语句
* foreach：相当于for语句
```
//xx.jsp
<% taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
//body
<%--
c:if标签
	1.属性：test 必须属性，接受boolean表达式
	如果表达式为true则显示if标签体内容，如果为false不显示标签体内容。
	没有else表达，再写一次if标签。
--%>
<c:if test="true">
	<h1>我是真..</h1>
</c:if>
<c:if test="${not empty list}">
遍历集合
</c:if>

<%--
完成数字编号对应星期几
1.域中存储数字
2.使用choose标签去除数字		相当于switch声明
3.使用when标签做数字判断		相当于case
4.otherwise标签做其他状况声明	相当于default
--%>
<%
request.setAttribute("number",3);
%>
<c:choose>
	<c:when test="${number == 1}">星期一</c:when>
	<c:when test="${number == 2}">星期二</c:when>
	<c:when test="${number == 3}">星期三</c:when>
	<c:when test="${number == 4}">星期四</c:when>
	<c:when test="${number == 5}">星期五</c:when>
	<c:when test="${number == 6}">星期六</c:when>
	<c:when test="${number == 7}">星期日</c:when>
	<c:otherwise>输入数字有错误</c:otherwise>
</c:choose>

<%--
foreach：两种for循环，for(int i; ;)和增强for(:)
普通for：begin开始值(包含)，end结束值(包含)，var变量，step不长(var一次增长多少)，varStatus循环状态对象
	varStatus: index容器中元素的索引，从0开始。count循环次数，从1开始。
增强for：items容器对象。var容器元素的临时变量。varStatus循环状态对象。
	varStatus: index容器中元素的索引，从0开始。count循环次数，从1开始。
--%>
<c:forEach begin="1" end="10" var="i" step="1" varStatus="s">
	${i} ${s.index} ${s.count} <br>
<c:forEach>
<%
List list = new ArrayList();
list.add("aaa");
list.add("bbb);
list.add("ccc;);
request.setAttribute("list",list);
%>
<c:forEach items="${list}" var="str" varStatus="s">
	${s.index} ${s.count} ${str}<br>
</c:forEach>
```
#### MVC实例 
* 用户信息列表展示
* 用户信息的增删改查操作
* 设计：
	* 技术选择：Servlet+JSP+MySql+JDBCTemplate+Druid+BeanUtils
	* 数据库设计：
```
create database day17; 
use 17; 
create table user(
	id int primary key auto_increment,
	name varchar(20) not null,
	gender varchar(5),
	age int,
	address varchar(32),
	qq varchar(20),
	email varchar(50)
);
```
开发：
* 环境搭建 
* 创建项目
* 导入需要jar包
* 编码设置
* 测试
* 部署运维


