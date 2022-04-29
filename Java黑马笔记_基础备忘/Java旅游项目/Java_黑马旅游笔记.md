## 目录
1. [标题1](#标题1)
2. 
### 标题1

### 技术选型
*  Web层
	* Servlet：前端控制器
	* html：视图
	* Filter：过滤器
	* BeanUtils：数据封装
	* Jackson：json序列化工具
*  Service层
	* Javamail：java发送邮件工具
	* Redis：nosql内存数据库
	* Jedis：java的redis客户端
*  Dao层
	* Mysql：数据库
	* Druid：数据库连接池
	* JdbcTemplate：jdbc的工具
***
前后顺序为路以及目录结构
|  
|--src/main/webapp/register.html
|--src/main/java/com/example/DemoWeb112233/web/filter/CharacterFilter.java
|--src/main/java/com/example/DemoWeb112233/web/servlet/RegistUserServlet.java
|--src/main/java/com/example/DemoWeb112233/domain/User.java
|--src/main/java/com/example/DemoWeb112233/service/impl/UserServiceImpl.java
|--src/main/java/com/example/DemoWeb112233/dao/UserDao.java
|--src/main/java/com/example/DemoWeb112233/dao/impl/UserDaoImpl.java

***
* 异步(ajax)提交表单：
	* 在此使用一部提交表单是为了获取服务器相应的数据。因为前台使用的是html作为视图。不能够直接从servlet相关的域对象获取值，只能通过Ajax获取响应。
	* 编写RegistUserServlet
	* 编写UserService以及UserSErviceImpl
	* 编写UserDao以及UserDaoImpl
	
