### 作用
Spring MVC的拦截器类似于Servlet的过滤器Filter，用于对处理器进行预处理和后处理。
![](SpringMVC%E6%8B%A6%E6%88%AA%E5%99%A8_md_files/image_20210928083022.png?v=1&type=image&token=V1:JVG-L6v_SYQPLu4sedueBc2IA1PgFMEevx5vPpcfw0A)

#### 拦截器快速入门
1. 创建拦截器类实现HandlerInterceptor
```
public class MyInterceptor implements HandlerInterceptor {  
  
    //在目标方法执行之前执行  
  @Override  
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
        System.out.println("preHandle...");  
	  //return HandlerInterceptor.super.preHandle(request, response, handler);  
  
		return false;
		//如果返回false，后续的postHandle和afterCompletion都不会执行 
		//返回true，代表放行 
  }  
  
    //在目标方法执行之后，视图对象返回之前执行  
  @Override  
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {  
        System.out.println("postHandle..");  
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);  
  }  
  
    //整个流程都执行完毕后执行  
  @Override  
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {  
        System.out.println("afterHandle...");  
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);  
  }  
}
```
2. 配置拦截器spring-mvc.xml
```
<!-- 配置拦截器-->  
<mvc:interceptors>  
	<mvc:interceptor>  
		<!-- 对哪些资源执行拦截操作-->  
		<mvc:mapping path="/**"/>  
		<bean class="com.example.demo1.test.MyInterceptor" />  
	</mvc:interceptor>  
	
		<!--可以放多个拦截器--> 
	<mvc:interceptor>  
		<mvc:mapping path="/**"/>  
		<bean class="com.example.demo1.test.MyInterceptor2" />  
	</mvc:interceptor>  
</mvc:interceptors>  
```
3. 测试拦截器的拦截效果
```
//测试类
@Controller  
public class TargetController {  
    @RequestMapping("/target")  
    public ModelAndView show(){  
		System.out.printf("目标资源执行");  
		ModelAndView modelAndView = new ModelAndView();  
		modelAndView.addObject("name","itcast");  
		modelAndView.setViewName("index2");  
		return modelAndView;  
  }  
}

//index2.jsp:
<body>  
${name}  
</body>

```

***

其他示例
![](SpringMVC%E6%8B%A6%E6%88%AA%E5%99%A8_md_files/image_20210928094805.png?v=1&type=image&token=V1:eilmJ3Duf6PoczpMb50LnNoOHdsWp2hpaNGW2RNIlHo)
两个拦截器的流程
![](SpringMVC%E6%8B%A6%E6%88%AA%E5%99%A8_md_files/image_20210928094941.png?v=1&type=image&token=V1:eC5Cbavgx3rFD1yKHQffzAX7TbBD-tg4AixoUd92BnA)

***
### 拦截器：用户登录
1. 继承HandlerInteceptor
![](SpringMVC%E6%8B%A6%E6%88%AA%E5%99%A8_md_files/image_20210928222837.png?v=1&type=image&token=V1:hbfBB_mtshkEH6931q3_O_C-cAXhPep8LcoYasr19Hs)
2. 配置文件
![](SpringMVC%E6%8B%A6%E6%88%AA%E5%99%A8_md_files/image_20210928222922.png?v=1&type=image&token=V1:yg7r9b29texRbhVEcicHaASCZLWjMj7SHgKn4RUN-HE)

3. 被拦截后实现用户登录。
 login.jsp
![](SpringMVC%E6%8B%A6%E6%88%AA%E5%99%A8_md_files/image_20210928231620.png?v=1&type=image&token=V1:M46Ji4eh13vYmnw9KbL5RbLtbT3u861xaf_wOf-Qgy8)
4. 控制器登录方法
![](SpringMVC%E6%8B%A6%E6%88%AA%E5%99%A8_md_files/image_20210928231719.png?v=1&type=image&token=V1:PirgvMraNHw0D5rt4nK2L4OEFrTeGVhNjgJ9pvQRnjQ)
5. 对应Service及Dao方法，此处只显示DaoImpl
![](SpringMVC%E6%8B%A6%E6%88%AA%E5%99%A8_md_files/image_20210928231825.png?v=1&type=image&token=V1:itkbRT-pHTJr2Mtm5BLPpnQu78fxEngPHt4kqX7XYoQ)
6. 配置拦截器排除login操作。否则死循环：要查看页面必须登录，登录页面禁止操作，要求登录。
![](SpringMVC%E6%8B%A6%E6%88%AA%E5%99%A8_md_files/image_20210928231904.png?v=1&type=image&token=V1:QRAn0ridvrgsom33AhKqHbrC3Gaool8OX1PsQJB3OTo)

7. 小Bug：当用户输入用户名密码错误，在Dao层会抛异常造成页面`# HTTP状态 500 - 内部服务器错误` ![](SpringMVC%E6%8B%A6%E6%88%AA%E5%99%A8_md_files/image_20210928232820.png?v=1&type=image&token=V1:GksoD-bL12xqPGq-_ab-VLKnMcKAr3mcu4YfNiwuNa8)
8. 修复Bug：在DaoImpl层抛出异常
![](SpringMVC%E6%8B%A6%E6%88%AA%E5%99%A8_md_files/image_20210928232852.png?v=1&type=image&token=V1:KoxuQoASL7qhRhx-s4Gq45KhyoM1Hr6iKv01Zb5Qqbg)
然后在Service返回空。
![](SpringMVC%E6%8B%A6%E6%88%AA%E5%99%A8_md_files/image_20210928233132.png?v=1&type=image&token=V1:kr5HPiGCq7S-2JtP2dEht1cJQwIcJo74vQHoZqJShQA)
修复后，当用户输入用户名密码错误，页面不会转向HTTP500页面，自动跳转页面。


