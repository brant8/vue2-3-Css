### 两种方式处理异常
* 使用SpringMVC提供的简单异常处理器SimpleMappingExceptionResolver
	* 也可以创建自定义异常
![](SpringMVC%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86_md_files/image_20210929040553.png?v=1&type=image&token=V1:V4aufH9EUj5KehEEUuC58fSth4MRPvr8G1SDWT9fQPs)

* 实现Spring的异常处理接口HandlerExceptionResolver自定义自己的异常处理器
 ![](SpringMVC%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86_md_files/image_20210929042524.png?v=1&type=image&token=V1:b3F1xzA8TZCw1xKTzxz2aUAdBwL4ONWh0KdPZz1QwiY)

步骤1：创建异常实现类
![](SpringMVC%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86_md_files/image_20210929043549.png?v=1&type=image&token=V1:15sXHWEVRCbAtF9MJVfpdf02lY3BVApXnMjexx6SreU)

步骤2：spring-mvc.xml配置
![](SpringMVC%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86_md_files/image_20210929043625.png?v=1&type=image&token=V1:CW8M_Mc8RfkRoU2HuToaZLWQT2LyhJt5Lh3Tlwm19OI)

步骤3：错误页面
![](SpringMVC%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86_md_files/image_20210929043710.png?v=1&type=image&token=V1:a_NPETSmEhvmX1S87hRdf1oiXqDZi2Yd5E4SFnCoXw4)


