### 事务控制
编程式事务控制：Java代码操作
声明式事务控制：XML、注解配置方式

### 编程式事务控制相关对象
![](Spring%E4%BA%8B%E5%8A%A1%E6%8E%A7%E5%88%B6_md_files/image_20210930204715.png?v=1&type=image&token=V1:ZzVkyH2PiHbLv_LVra6osTiZYoxxuw-LhPVqvIM9Uhc)
![](Spring%E4%BA%8B%E5%8A%A1%E6%8E%A7%E5%88%B6_md_files/image_20210930205301.png?v=1&type=image&token=V1:l9bIBoeaz4X4J1ikCUeHCacgwR0MoR90auGuwCcmx7A)
1. 事务隔离级别：解决事务并发产生的问题，如脏读，不可重复读和虚读。
* ISOLATION_DEFAULT
* ISOLATION_READ_UNCOMMITTED
* ISOLATION_READ_COMMITTED
* ISOLATION_REPEATABLE_READ
* ISOLATION_SERIALIZABLE

![](Spring%E4%BA%8B%E5%8A%A1%E6%8E%A7%E5%88%B6_md_files/image_20210930210908.png?v=1&type=image&token=V1:iMzkff2dPPHpjse8MBkzXyKMChAHFP8SJNCyHweuwxw)
![](Spring%E4%BA%8B%E5%8A%A1%E6%8E%A7%E5%88%B6_md_files/image_20210930211547.png?v=1&type=image&token=V1:ogarothQa-f2V0H5jfcN8Sskj0wMjN-4n8Zd7-2JV40)

编程事务控制三大对象：
* `PlatformTransactionManager`实现 
* `TransactionDefinition`
* `TransactionStatus`

***
### 基于XML声明式事务控制
![](Spring%E4%BA%8B%E5%8A%A1%E6%8E%A7%E5%88%B6_md_files/image_20211001080311.png?v=1&type=image&token=V1:b33qVp4CQ16SBdgl7manueu4QB3Rk7H1P0kP-zPpvG8)

### 示例
转账，A转B500元，出错后A少了500但是B没有增加500。
```
# AccountServiceImpl
@Override  
public void transfer(String outMan, String inMan, double money) {  
    //开启事务  # 使用AOP思想剥离，可以让其他方法也实现事务控制
	accountDao.out(outMan,money);  
	int a = 1/0;  
	accountDao.in(inMan,money);//报错后钱丢了500  
	//提交事务  
}
```
略去接口及实现类，Controller参考代码：
![](Spring%E4%BA%8B%E5%8A%A1%E6%8E%A7%E5%88%B6_md_files/image_20211001044935.png?v=1&type=image&token=V1:jcrUKJeDAu3qSJhv_WxWy-Nb8mNRLnvBez4nC70ie_g)
事务配置详细：
![](Spring%E4%BA%8B%E5%8A%A1%E6%8E%A7%E5%88%B6_md_files/image_20211001045052.png?v=1&type=image&token=V1:gN4UDgGq6IWoTeUtn9Vu4vr27HdRRJc8BuE7DY8S9gY)
平台事务管理器配置根据不同的数据源进行不一样的配置，如C3P0的`datasource`。
配置事务的aop织入使用`advisor`，而非`aspect`。

细节说明：
![](Spring%E4%BA%8B%E5%8A%A1%E6%8E%A7%E5%88%B6_md_files/image_20211001080157.png?v=1&type=image&token=V1:ssK7PIguz6TChu-uCZejJjZLx3ZPmk6LdANokaY5MIU)

### 注解方式的事务声明方式
示例：
![](Spring%E4%BA%8B%E5%8A%A1%E6%8E%A7%E5%88%B6_md_files/image_20211001082311.png?v=1&type=image&token=V1:rWIomlD9AgQLjaOWMF-Z0Y2bVCVnTaFune4nYkrtcK0)
XML的配置：
![](Spring%E4%BA%8B%E5%8A%A1%E6%8E%A7%E5%88%B6_md_files/image_20211001082331.png?v=1&type=image&token=V1:H5qd_F6COVsuwQ-lNSOrb8NykYBoXBbljqitNb3BuaQ)

![](Spring%E4%BA%8B%E5%8A%A1%E6%8E%A7%E5%88%B6_md_files/image_20211001082743.png?v=1&type=image&token=V1:jXl_pyxBE1dVancZKTnukKBtyUQNWrwIaFBZZkeFD98)
![](Spring%E4%BA%8B%E5%8A%A1%E6%8E%A7%E5%88%B6_md_files/image_20211001082807.png?v=1&type=image&token=V1:MEtGI0mhoL-JivSA7w3haPuFyvX2w3O1Vjoe6ZOZOOw)
