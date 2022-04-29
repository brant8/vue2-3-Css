package com.example.spring_mvc.controller;

import com.example.spring_mvc.domain.User;
import com.example.spring_mvc.domain.VO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller //放至容器当中,还需要配置文件扫一下该注解
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/quick",method = RequestMethod.GET,params = {"username"}) //请求映射
    public String save(){
        System.out.println("Controller save running..");
        return "success.jsp";
    }

    @RequestMapping(value = "/quick2") //请求映射
    public ModelAndView save2(){
        /**
         * Model:模型，用于封装数据
         * View:视图，用于展示数据
         */
        ModelAndView modelAndView = new ModelAndView();
        //设置模型数据
        modelAndView.addObject("username","itcaset");//相当于放到域当中
        //设置试图
        modelAndView.setViewName("success");//相当于jsp目录下的success.jsp（xml配置视频解析器）
        return modelAndView;
    }

    @RequestMapping(value = "/quick3") //请求映射
    public ModelAndView save3(ModelAndView modelAndView){//SpringMVC自动注入ModelAndView
        modelAndView.addObject("username","itcaset");
        modelAndView.setViewName("success");
        return modelAndView;
    }

    @RequestMapping(value = "/quick4") //请求映射
    public ModelAndView save4(ModelAndView modelAndView){
        modelAndView.addObject("username","itcaset");
        modelAndView.setViewName("success");
        return modelAndView;
    }


    @RequestMapping(value = "/quick5") //注入模型
    public String save5(Model model){//Model和View拆开使用,Model为SpringMVC封装的对象
        model.addAttribute("username","boxuegu");
        return "success";//相当于/jsp/success.jsp，在xml配置视图解析器
    }

    @RequestMapping(value = "/quick6")
    public String save6(HttpServletRequest request){ //HttpServletRequest为JavaWeb自带，不常用，用框架就用框架带的（方便解耦）
        //一般情况使用HttpServletRequest需要继承HttpServlet才能用，SpringMVC集成可做参数
        request.setAttribute("username","ladingyu");
        return "success";//相当于/jsp/success.jsp，在xml配置视图解析器
    }


    /**
     * 回写数据
     */
    @RequestMapping(value = "/quick7")
    public void save7(HttpServletResponse response) throws IOException {
        response.getWriter().print("hello itcase");
        //return "success";
    }

    //普通字符串
    @RequestMapping(value = "/quick8")
    @ResponseBody  //与save1()比较，告知SpringMVC框架，方法返回的是字符串不是跳转。直接数据响应。
    public String save8() throws IOException {
        return "hello iteheima";
    }

    //Json手动版
    @RequestMapping(value = "/quick9")
    @ResponseBody
    public String save9() throws IOException {
        return "{\"username\":\"zhangsan\",\"age\":\"15\"}";
    }

    //Json导包版
    @RequestMapping(value = "/quick10")
    @ResponseBody
    public String save10() throws IOException {
        User user= new User();
        user.setUsername("lisi");
        user.setAge(30);
        //使用json的转换工具，将对象转换成json格式字符串再返回
        //pom.xml需要导报json,一般三个,jackson-core,jackson-databind,jackson-annotations
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        return json;
    }

    //Json SpringMVC版
    /**
     * 需要在spring-mvc.xml配置干预让其数据返回Json
     *     <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter">
     *         <property name="messageConverters"> //property相当于类中的方法
     *             <list>
     *                 <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter"></bean>
     *             </list>
     *         </property>
     *     </bean>
     */
    @RequestMapping(value = "/quick11")
    @ResponseBody
    //期望SpringMVC自动将User转换成json格式的字符串
    public User save11() throws IOException {
        User user= new User();
        user.setUsername("lisi2");
        user.setAge(32);

        return user;
    }
    /**    //配置spring_mvc.xml
     *    //mvc的注解驱动,则不需要quick11繁琐配置，但还是要配置命名空间，其他json等springmvc自动加载
     *           xmlns:mvc="http://www.springframework.org/schema/mvc"
     *           http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd
     *      //mvc的注解驱动
     *     <mvc:annotation-driven/>
     */

    //基本数据类型参数的获取，前端数据传到后端都是字符串形式，int类型自动类型转换
    @RequestMapping(value = "/quick12")
    @ResponseBody
    public void save12(String username, int age) throws IOException {//此处测试用void，返回体范围空,控制台有数据
        System.out.println(username);
        System.out.println(age);
        //测试连接localhost:8080/user/quick11?username=zhangsan&age=18
    }

    //POJO(Plain Old Java Object)类型参数的获取
    //需要有个User类以及setter和getter，页面获取参数与User类参数一致则SpringMVC自动封装
    @RequestMapping(value = "/quick13")
    @ResponseBody
    public void save13(User user) throws IOException {
        System.out.println(user);
    }

    //获取数组类型参数
    //比如连接：localhost/iteheima/quick14?strs=11&strs=222&strs=333
    @RequestMapping(value = "/quick14")
    @ResponseBody
    public void save14(String[] sts) throws IOException {
        System.out.println(sts); //默认打印数组地址
        System.out.println(Arrays.asList(sts));//作为集合打印
    }

    //获得集合类型参数，需要将集合包装到POJO中
    /**
     *  public class VO {
     *     private List<User> userList;
     *     ..}
     */
    @RequestMapping(value = "/quick15")
    @ResponseBody
    public void save15(VO vo) throws IOException {
        System.out.println(vo);
    }
    /** webapp/form.jsp
     * <form action="${pageContext.request.contextPath}/user/quick14" method="post"> #一般用post
     *      #表明第一个User对象的username age,而Save15()方法的vo为->VO类的参数userList是个数组
     *      所以name="userLst[0]",为第一个元素。userList[0]为List<User>的User对象，User对象需要封装username&age
     *      则需要User.username
     *      <input type="text" name="userList[0].username"><br/>
     *      <input type="text" name="userList[0].age"><br/>
     *      <input type="text" name="userList[1].username"><br/>
     *      <input type="text" name="userList[1].age"><br/>
     *      <input type="submit" value="提交">
     * </form>
     */

    //获得集合类型参数第二种情况
    //当使用ajax提交时，可以指定contentType为json形式，
    //那么在方法参数位置使用@RequestBody可以直接收集几何数据二无需使用POJO进行包装。
    @RequestMapping(value = "/quick16")
    @ResponseBody
    public void save16(@RequestBody List<User> userList) throws IOException {
        System.out.println(userList);
    }
    /** #导入jquery，可用EL：src=${pageContext.request.contextPath}/js/jquery.js
     *    <script>
     *         var userList = new Array();
     *         userList.push({username:"zhangsan",age:18});
     *         userList.push({username:"lisi",age:28});
     *         $.ajax({
     *             type:"POST",
     *             url:"${pageContext.request.contextPath}/user/quick16",
     *             data:JSON.stringify(userList),
     *             contentType:"application/json:charset=utf-8"
     *         })
     *     </script>
     *
     *     #如果无法获取jquery.js，可尝试重新deploy Maven
     *     #或者在spring_mvc.xml添加<mvc:resources mapping="/js/**" location="/js/" />
     *     #注：mvc要自行添加命名空间，开放资源的访问
     *     #mapping：前端访问的地址（用以映射）
     *     #location：后端的具体目录
     *     映射->servlet里面的地址->name匹配->真实地址
     *
     *     或者默认一句代码不需要mapping：<mvc:default-servlet-handler/>
     *     如客户端访问不到@RequestMapping对应的地址，则交给原始容器Tomcat，Tomcat可以找到静态资源
     */

    @RequestMapping(value = "/quick17")
    @ResponseBody
    //value="name"当参数只有一个的时候也可以省去value，只填写参数
    public void save17(@RequestParam(value="name",required = true,defaultValue = "itcast") String username) throws IOException {
        System.out.println(username);
    }
    /**
     * @RequestPara三个参数：value 请求参数名称。required请求参数是否包括，默认时true；提交时没有参数404报错。
     * defaultValue没有指定请求参数时，则使用指定的默认值赋值。
     * # 绑定前端参数和后端参数（前后参数名称不一致时）
     * <form action="${pageContext.request.contextPath}/quick17" method="post">
     *     <input type="text" name="name"><br>
     *     <input type="submit" value="提交">
     * </form>
     */

    //restful风格传递参数，{username}为占位符,占位符必须与value参数名称一致，String参数可不一样
    //localhost/user/quick18/zhangsan
    @RequestMapping(value = "/quick18/{username}")
    //@RequestMapping(value = "/quick18/{username}"，method="post"), 默认值get
    @ResponseBody
    //value="name"当参数只有一个的时候也可以省去value，只填写参数
    public void save18(@PathVariable(value="username") String username) throws IOException {
        System.out.println(username);
    }

    //数据类型转换，自定义转换器
    @RequestMapping(value = "/quick19")
    @ResponseBody
    public void save19(Date date) throws IOException {
        System.out.println(date);
    }

    //获取请求Servlet相关的API
    @RequestMapping(value = "/quick20")
    @ResponseBody
    public void save20(HttpServletRequest req, HttpServletResponse rep, HttpSession session) throws IOException {
        System.out.println(req);
        System.out.println(rep);
        System.out.println(session);
    }


    /** 获取请求头 @RequestHeader相当于request.getHeader(name)，获取Header下的各种标题对应值，如Cookie对应值SessionID=xxx
     * @RequestHeader参数：
     * value，请求头的名称
     * required，是否必须携带此请求头, required = true必须携带信息去访问save21()
     * 浏览器F12查看信息， User-Agent：<..浏览器信息等..>
     */
    @RequestMapping(value = "/quick21")
    @ResponseBody
    public void save21(@RequestHeader(value="User-Agent",required = false) String user_agent) throws IOException {
        //获取Request Header下面的User-Agent并复制给user_agent
        System.out.println(user_agent);
    }

    /** 获得请求头@CookieValue, 获取Cookie里的sessionID值xxx,sessionID=xxx
     * value:指定cookie的名称
     * required：是否必须携带此cookie
     * 浏览器F12显示： Cookie：JESSIONID=xxxxxx
     */
    @RequestMapping(value = "/quick22")
    @ResponseBody
    public void save22(@CookieValue(value="JSESSIONID") String jsessionID) throws IOException {
        System.out.println(jsessionID); //显示xxxx
    }


    /**     单个文件上传步骤
     * 1.导入fileupload和io坐标：
     *         <dependency>
     *             <groupId>commons-fileupload</groupId>
     *             <artifactId>commons-fileupload</artifactId>
     *             <version>1.4</version>
     *         </dependency>
     *         <dependency>
     *             <groupId>commons-io</groupId>
     *             <artifactId>commons-io</artifactId>
     *             <version>2.6</version>
     *         </dependency>
     * 2. 配置文件上传解析器spring_mvc.xml
     *      #文件上传解析器
     * <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
     *      #上传文件总大小
     *     <property name="maxUploadSize" value="5242800" />
     *      #上传单个文件的大小
     *     <property name="maxUploadSizePerFile" value="5242800" />
     *      #上传文件的编码类型
     *     <property name="defaultEncoding" value="UTF-8"/>
     * </bean>
     * 3.文件代码
     */
    @RequestMapping(value = "/quick23")
    @ResponseBody
    public void save23(String username, MultipartFile upload) throws IOException {
    //public void save23(String username, MultipartFile upload, MultipartFile upload2) throws IOException {
    //public void save23(String username, MultipartFile[]  upload) throws IOException { #多个文件同name值
        //参数名必须与HTML表单的name取值一致，MVC才能自动传参
        System.out.println(username);
        System.out.println(upload);
        //获得上传文件的名称
        String originalFilename = upload.getOriginalFilename();
        upload.transferTo(new File("C:\\uploads\\"+originalFilename));
        // for(MultipartFile multipartFile : upload){...}
    }
    /** HTML表格
     *<form action="${pageContent.request.contextPath}/user/quick23" method="post" enctype="multipart/form-data">
     *     名称<input type="text" name="username"><br/>
     *     文件<input type="file" name="upload"><br/>  #如果多个文件同name值，方法用数组接受循环遍历
     *     文件2<input type="file" name="upload2"><br/> #多文件上传
     *     <input type="submit" name="提交"><br/>
     * </form>
     */


    /**
     *
     */
}

