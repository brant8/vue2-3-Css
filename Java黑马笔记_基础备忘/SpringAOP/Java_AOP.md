### AOP
概念：AOP为Aspect Oriented Programming，面向切面编程。通过预编译方式和运行期动态代理实现程序功能的同意维护的一种技术。

动态代理优点：不修改源码的状态下对项目功能的增强(过滤器)。 
![](Java_AOP_md_files/image_20210929212001.png?v=1&type=image&token=V1:avWI9nQ3h9l7Q8VjnS6D_IczcTyc5KZsI4p-Jchxb9I)

==Java底层框架多数运用JDK代理。==

![](Java_AOP_md_files/image_20210930041736.png?v=1&type=image&token=V1:ZA3U95nO2bQQ5yvz6SHK-Dful7oRDqVUBbTyravg0Gg)

### JDK代理示例

准备1：接口类
![](Java_AOP_md_files/image_20210929220250.png?v=1&type=image&token=V1:EtbY91RdnfIi7GANePelqzGaYXXB1byMcdu66F8Y-k4)
准备2：接口实现类
![](Java_AOP_md_files/image_20210929220333.png?v=1&type=image&token=V1:nZMLU68LJlkyhC0V9Rz3YZId52s7fJh5PFuFqnyxMjU)
准备3：增强类
![](Java_AOP_md_files/image_20210929220358.png?v=1&type=image&token=V1:OW1uCOyv3rsOrKM5ltAeCBZnvHpYDDWEBLKi7jS_BJ8)
步骤4：代理测试类
![](Java_AOP_md_files/image_20210929220500.png?v=1&type=image&token=V1:-_QNoRVlxDrpHI6IVMbFRAXgmsTc-4RGQyTQPO3qj4M)

### cglib代理示例
cglib属于第三方包，所以需要导入包。(默认新版SpringMVC已把cglib包包含在内)
![](Java_AOP_md_files/image_20210930033322.png?v=1&type=image&token=V1:rxXpKXDVZSsR9quU9L0QzNtsNwXa5aONVnwSaxPhp2g)

==以上两种动态代理SpringMVC均已封装好，只需要配置即可使用。==

***
#### AOP相关概念
* 常用术语
	* Target（目标对象）：代理的目标对象
	* Proxy（代理）：一个类被AOP织入增强后，就产生一个结果代理类
	* Joinpoint（连接点）：所谓连接点是指那些被拦截到的点。在spring中，这些点值的是方法，因为spring只支持方法类型的连接点。即**可以被增强的方法叫做连接点**。
	* Pointcut（切入点）：所谓切入点是指我们要对那些Jointpoint进行拦截的定义。**即实际被增强的方法。**
	* Advice（通知/增强）：所谓通知是指拦截到Joinpoint之后要做的事情就是通知。
	* Aspect（切面）：是切入点和通知（引介）的结合。
	* Weaving（织入）：是指吧增强应用到目标对象来创建新的代理对象的过程。spring采用动态代理织入，而AspectJ采用编译期织入和类装载器织入。**即切点和增强的过程。**
	
* AOP开发的明确事项
	1.	需要编写的内容 
		* 编写核心业务代码（目标类的目标方法），即被增强的代码。
		* 编写切面类，切面类中有通知（增强功能方法）
		* 在配置文件中，配置织入关系，即将哪些通知与哪些连接点进行结合
	2.	AOP技术实现的内容
![](Java_AOP_md_files/image_20210930035601.png?v=1&type=image&token=V1:jrzKr--K5nHYteTEv3T8R2VQNZ3zfmOzNyaqcTImZMc)	 
	3. AOP底层使用哪种代理方式
		在spring中，框架会根据目标类是否实现了接口来决定采用哪种动态代理的方式。

***
### 基于XML的AOP快速入门
![](Java_AOP_md_files/image_20210930042226.png?v=1&type=image&token=V1:0Gpbmw9W53S74GEdzPDs9V8FQrpay9hNxtg57GX6cPc)

1. spring-context已包含AOP，第三方Aspect J也包含AOP还比Spring轻，一般使用Aspect J的AOP。
```
<dependency>  
 <groupId>org.aspectj</groupId>  
 <artifactId>aspectjweaver</artifactId>  
 <version>1.9.8.M1</version>  
</dependency>
```
![![](Java_AOP_md_files/image_20210930044733.png?v=1&type=image&token=V1:PtY6ZaWZ9jLfeOR4rO6MGqWVIptq0z5jgOVYl1rJBKA)](Java_AOP_md_files/image_20210930090104.png?v=1&type=image&token=V1:xrJ2giEPyJJk8rOKK72JUNzlStW-gnH_hafm54dIM90)

![](Java_AOP_md_files/image_20210930093224.png?v=1&type=image&token=V1:tr7v7HKYj2R5Fy4yUQ8KRbuuuTe9tmpC9zHk82RGrGg)

![](Java_AOP_md_files/image_20210930093443.png?v=1&type=image&token=V1:B59I40v7hFiVLK0VcjWNl1i_WdC8BMhjApNUf8gpT0A)

![](Java_AOP_md_files/image_20210930095102.png?v=1&type=image&token=V1:m2rK9GQnvIwzcDtD3HCg0cVwzW64sr6dKVh16R0yzaw)

![](Java_AOP_md_files/image_20210930095857.png?v=1&type=image&token=V1:corpw-5F0xGkunKtZC7tD2ByMiFuZqrqDsZjeRnhOzs)

![](Java_AOP_md_files/image_20210930100022.png?v=1&type=image&token=V1:jg5xl-XSDDh405x_i-vPHbRd5wB6Dps3jMJV9j5NuIE)

***
### 基于注解的AOP开发
![](Java_AOP_md_files/image_20210930100253.png?v=1&type=image&token=V1:K0jc0lhGkQH-dhrT1_Uql6YIWkfp_WFO1kr2oOzgkWI)
步骤1 & 2 略过
步骤3 &4 ： 
![](Java_AOP_md_files/image_20210930102221.png?v=1&type=image&token=V1:y7BLdU-DH80wMh6T8cLAn0UKBcMjQ3_hF5eyQ3dkGX8)
步骤5.
![](Java_AOP_md_files/image_20210930102338.png?v=1&type=image&token=V1:P3dS9B5jDScHwFzcDc3nV6MqpBIwYoV8ywt8CMcV3kk)

步骤6：
![](Java_AOP_md_files/image_20210930102413.png?v=1&type=image&token=V1:KyjCR8IzphDLKzc1Ah1zofsQB-fAjwGKApiWhpNT5GI)

![](Java_AOP_md_files/image_20210930201812.png?v=1&type=image&token=V1:8X2hRAN-B_BettM8MXpLvC2p3ZFYtmSlr4Mj1yCQ0MI)

