### SpringBoot[网页文档链接](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started.first-application.pom)
maven工程快速创建spring boot：
```
# pom.xml
<parent>  
	<groupId>org.springframework.boot</groupId>  
	<artifactId>spring-boot-starter-parent</artifactId>
	<version>2.5.5</version>  
</parent>
<dependencies>  
	<dependency> 
		<groupId>org.springframework.boot</groupId>  
		<artifactId>spring-boot-starter-web</artifactId>  
	</dependency>
</dependencies>

# HelloController类
@RestController  
public class HelloController {  
    @RequestMapping("/hello")  
    public String hello(){  
        return "hello spring boot";  
  }  
}

# HelloApplication类,引导类一般以Application结尾并放根目录
/**  
 * 引导类：springboot项目入口  
 */  
@SpringBootApplication  
public class HelloApplication {  
    public static void main(String[] args) {  
        SpringApplication.run(HelloApplication.class,args);  
  }  
}
```
小结：
* SpringBoot在创建项目时，使用jar的打包方式。(因为使用`public static void main(String[] args)`方式开启程序)。
* SpringBoot的引导类，是项目的入口，运行main方法就可以启动项目。
* 使用SpringBoot和Spring构建的项目，业务代码编写方式完全一样。

#### 起步依赖原理分析
* spring-boot-starter-parent定义了各种技术版本信息，组合了一套最优搭配版本。
* 各种starter中，定义了该功能需要的坐标合集，大部分来自父工程。
* 新工程继承parent，引入starter后，通过依赖传递，可以简便获得所需jar包，且不会有版本冲突。

#### springboot配置文件分类及读取
默认配置名称均为application.xx。
同一级目录下相同配置参数优先级加载顺序`properties > yml > yaml`。
* properties：键值对方式。
	* 书写方式：`server.port=8080`
* yaml/yml：YML文件是以数据为核心，比传统xml更简洁。
```
# port与端口号之间有空格。
server:
	port: 8080 
```
* profile：不同环境之间动态配置切换。
* 内部配置加载顺序：
	* Springboot程序启动时，会从以下位置加载配置文件（优先级上往下）
		* `file:./config/`当前项目下的/config目录下
		* `file:./`当前项目的根目录
		* `classpath:/config/`classpath的/config目录
		* `classpath:/`classpath的根目录
* 外部配置加载顺序

#### YAML:
![](SpringBoot%E5%AD%A6%E4%B9%A0_md_files/image_20211007235415.png?v=1&type=image&token=V1:iHZslZBWqRTpqeQnTqmiXegQs4fhenMPmMJZ9INOhSw)
**YAML基本语法**：
![](SpringBoot%E5%AD%A6%E4%B9%A0_md_files/image_20211007235517.png?v=1&type=image&token=V1:j62pvtbTF3IKv4zfZOQE0D-2efKz7AkwCE4vI8Pi3LM)
YAML数据格式：
* 对象（map）：键值对的集合。
```
person:
  name: zhangsan
# 行内写法
person: {name: zhangsan}  
person2: {name: zhangsan,age: 20} 
```
* 数组：一次按次序排列的值。
```
address:
  - beijing
  - shanghai
# 行内写法
address: [beijing,shanghai]
```
* 纯量：单个的、不可再分的值。
```
msg1: 'hello \n world`  #单引忽略转义字符
msg2: "hello \n world"  #双引识别转义字符
```
 **YAML参数引用**
```
name: lisi
person:
  name: ${name}  #引用上面定义的name的值
```
**SpringBoot读取配置内容**
* @Value：
* Environment
* @ConfigurationProperties
```
@RestController  
public class HelloController {  
    @Value("${name}")   # 单一值
    private String name;  
	@Value("${person.name}")   # 对象
    private String name2;  
    @Value("${plate[0]}")  # 数组
    private String plate1; 
    
	@Autowired  # 注入一次获取所有
	private Environment env;  # 通过注入环境对象获取YML配置文件值的方法
  
    @RequestMapping("/hello2")  
    public String Hello2(){  
        System.out.println(name);  
		System.out.println(name2);  
		System.out.println(plate1); 
		System.out.println(env.getProperty("plate[0]"));  
		System.out.println(env.getProperty("person.name")); 
		return "hello " + name;  
  }
```
第一层级和第二层级都有相同参数名时，第一层级优先。
![](SpringBoot%E5%AD%A6%E4%B9%A0_md_files/image_20211008082218.png?v=1&type=image&token=V1:Rc2BfO-TzNX5QDwomK_3Ffl_6BYE9PulJ8OqJ0H4T48)
当`@ConfigurationProperties(prefix = "person")`标注了prefix后，就可以指定层级中的参数位置。
当对象有数组时：
```
# Controller代码
String[] shirt = person.getShirt();  
for(String s:shirt){  
    System.out.println(s);  
}

# Person对象类
@Component # 让Spring识别  
@ConfigurationProperties(prefix = "person")  #不配置prefix时第一层级优先
public class Person {  
     private String name;  
	 private int age;  
	 private String[] shirt;
 ...# [getter&setter]
 
# application.xml
name: lisi
person:  
  name: wangwu  
  age: 13  
  shirt:  
    - long  
    - short
```
如果Person类头部报红，需要配置其他时，添加依赖：
```
<dependency>  
 <groupId>org.springframework.boot</groupId>  
 <artifactId>spring-boot-configuration-processor</artifactId>  
 <optional>true</optional>  
</dependency>
```
**profile动态配置环境**
测试环境，开发环境，生产环境中的数据库地址，端口号的快速切换。
配置方式：
* 多profile文件方式：默认`application.properties`
	* 格式：`application-xxx.properties`如`application-test.properties`
	* 然后在默认`application.properties`添加：`spring.profiles.active=xxx`

* 多yml方式：固定名application.yml（新版本sprintboot2已弃用）
	* 使用`---`三个杠分割不同空间
```
---  
#第一部分  
server:  
  port: 8081  
#（新版本sprintboot2已弃用profiles）  
spring:  
  profiles: dev  
---  
#第二部分  
server:  
  port: 8082  
spring:  
  profiles: test  
---  
#第三部分  
server:  
  port: 8083  
spring:  
  profiles: pro  
---
spring:  
  profiles:  
    active: pro 	# 此处用于激活当中配置
```
新版本boot2的对profile中yml的格式使用新的方式，具体查看网上：
`spring:   
  config:  
    activate:  
      on-profile: test/dev/pro`
      
**profile激活方式**
* 配置文件：
```
spring:  
  profiles:  
    active: pro
```
* Run/Debug Configuration配置（JDK软件配置）
![](SpringBoot%E5%AD%A6%E4%B9%A0_md_files/image_20211008092235.png?v=1&type=image&token=V1:Cnao37kFYtYW8uEp1nraYKVswxGi6XkBq04BQdxNQVI)
	* 开头使用`-D`紧跟配置文件方式的代码
![](SpringBoot%E5%AD%A6%E4%B9%A0_md_files/image_20211008092358.png?v=1&type=image&token=V1:eSf7E2NChCWZZ8G-o1QMyQ7nubfFNCBNTS6Vfmp5FYI)
* 命令行方式：
 ![](SpringBoot%E5%AD%A6%E4%B9%A0_md_files/image_20211008092708.png?v=1&type=image&token=V1:0gwEN_q0HiKB2osSnubOyMnwBs-DIqDhVS8Io08mdvY)
 
 ### 打包jar包
 ![](SpringBoot%E5%AD%A6%E4%B9%A0_md_files/image_20211008092908.png?v=1&type=image&token=V1:X2c5n5aMf1M9M77xrwAvJSJVpWuraQ1r_QwDFCH5-QM)
打包后在该jar包目录下使用cmd/terminal命令行运行jar包：
* ` java -jar .\springboot-profiles-0.0.1-SNAPSHOT.jar`
* 使用不同环境快速切换：
	*  `java -jar .\springboot-profiles-0.0.1-SNAPSHOT.jar --spring.profiles.active=pro`

内部配置文件优先级：
![](SpringBoot%E5%AD%A6%E4%B9%A0_md_files/image_20211008204259.png?v=1&type=image&token=V1:Pa0HOTXp1V5VFI3t8uIztQ22LSlpj_xTuQ5MfMEzGuQ)
说明：每个properties都会被加载，有优先级以及互补配置关系。jar打包只限项目目录下，项目上级目录不会被打进jar包。

外部配置文件：有17处地方可以配置([文档链接](https://docs.spring.io/spring-boot/docs/2.1.9.RELEASE/reference/html/boot-features-external-config.html))。
文档优先级如文档从上到下展示。
```
# 例如：
> java -jar xx.jar --spring.config.location=e://application.properties
```

### SpringBoot整合其他框架

**整合Junit**
1. 搭建SpringBoot工程
2. 引入starter-test起步依赖
3. 编写测试类
4. 添加测试相关注解
	* @RunWith(SpringRunner.class)
	* @SpringBootTest(classes = 启动类.class）
5. 编写测试类

示例展示：
```
# 方法一：随意包名测试类
# 第一步：确保pom.xml有spring-boot-starter-test依赖
# 第二步：有需要被测试的类
@Service  
public class UserService {  
    public void add(){  
        System.out.println("test''ad...");  
  }  
}
# 第三步①：编写测试类（随意目录）
@RunWith(SpringRunner.class)  
@SpringBootTest(classes = SpringbootJunitApplication.class)  
# SpringbootJunitApplication.class是springboot快速创建时自动创建在main-java下的类
public class UserServiceTest {  
  @Autowired  
  private UserService userService;  
 
  @Test  
  public void testAdd(){  
        userService.add();  
  }  
  
}

# 第三步②：目录与main下的目录路径一致
@RunWith(SpringRunner.class)  
@SpringBootTest() # 此处省略main下的class 
public class UserServiceTest {  
  @Autowired  
  private UserService userService;  
  
  @Test  
  public void testAdd(){  
        userService.add();  
  }  
  
}

```
![](SpringBoot%E5%AD%A6%E4%B9%A0_md_files/image_20211012215029.png?v=1&type=image&token=V1:uK3XyO2YmZnDx6-nAiMOVEN6IK5zvoCrqI4saPhkN-Q)

**整合Redis**
1. 搭建SpringBoot工程
2. 引入redis起步依赖
3. 配置redis相关属性
4. 注入RedisTemplate模板
5. 编写测试方法测试

示例代码 
```
# 添加依赖spring-boot-starter-data-redis
# redis相关属性：application.yml 关键字：redis, host, port
# 第一步：注入模板
@Autowired  
private RedisTemplate redisTemplate;

# 第二步：存储和读取缓存
@Test  
public void testSet(){  
  //存入数据  
  redisTemplate.boundValueOps("name").set("zhangsan");  
}  
@Test  
public void testGet(){  
  Object name = redisTemplate.boundValueOps("name").get();  
  System.out.println(name);  
}
```
**整合Mybatis**
1. 搭建SpringBoot工程
2. 引入mybatis起步依赖，添加mysql驱动
3. 编写DataSource和MyBatis相关配置
4. 定义表和实体类
5. 编写dao和mapper文件/纯注解开发
6. 测试






