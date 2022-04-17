### 原始jdbc操作(查询数据)
![](MyBatis_md_files/image_20211001083429.png?v=1&type=image&token=V1:xfHKpS3qdEYk_iJ9a7G2-2CWxEX9KIXawm3uXFX_PY0)
![](MyBatis_md_files/image_20211001083710.png?v=1&type=image&token=V1:IYXgWbj_q-MJnCOE9Tf1rvE0TS9wQvTtRsDS-D8t-sg)

**Mybatis**是一款基于java的持久层(Dao层)框架。
#### Mybatis快速入门：
![](MyBatis_md_files/image_20211001092143.png?v=1&type=image&token=V1:5QlZ2xhwsQY0EXK2cBtTh7Kf9RUHYdm_YQYzZxerGZ0)

步骤4：resources/mapper/UserMapper.xml
```
<?xml version="1.0" encoding="UTF-8" ?>  
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  
 "http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
<mapper namespace="userMapper">  
  <!-- SQL语句 -->  
  <select id="findAll" resultType="com.example.mybatis.domain.User">  
	  select * from user  
  </select>  
</mapper>
```
步骤4图片版
![](MyBatis_md_files/image_20211001094412.png?v=1&type=image&token=V1:XAjx-whUgWjZZ7LUAgRhmwgTnXdq8bfjTMiljPzzGuU)
步骤5：resources/SqlMapConfig.xml
```
<?xml version="1.0" encoding="UTF-8" ?>  
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"  
 "http://mybatis.org/dtd/mybatis-3-config.dtd"> 
  
<configuration>  
	# 配置数据源环境 
	<environments default="development"> # default值来自下一行的id(只有一个环境的时候),先配置id，再复制到default 
		<environment id="development"> # 自定义id名称   
			<transactionManager type="JDBC"></transactionManager>  
			<dataSource type="POOLED">  
				<property name="driver" value="com.mysql.cj.jdbc.Driver"/>  
				<property name="url" value="jdbc:mysql://localhost:3306/test"/>  
				<property name="username" value="root"/>  
				<property name="password" value=""/>  
			</dataSource> 
		</environment> 
	</environments>
	  
	# 加载映射文件  
	<mappers>  
		<mapper resource="mapper/UserMapper.xml"></mapper>  
	</mappers> 
	 
</configuration>
```
步骤5图片版： ![](MyBatis_md_files/image_20211001094337.png?v=1&type=image&token=V1:hACZMssDnpyWOXQXcJ-zrQXzjKroEd-p5Aq93xFugoc)
步骤6： 测试类
![](MyBatis_md_files/image_20211001094234.png?v=1&type=image&token=V1:lY29siu1SSB7Ez1Mibz1MnmxGNd2mwmMmYespNwHtz8)
注：查询操作不会对数据表有影响，默认事务自动提交。

#### 讲解
映射文件：
![](MyBatis_md_files/image_20211001202633.png?v=1&type=image&token=V1:D8XqfH7vzHJkLaQLjf_nHfKbnkkfse3TYiny_y7nJWw)

Mybatis执行更新操作需要提交事务。

#### 插入操作
UserMapper.xml配置
![](MyBatis_md_files/image_20211001204604.png?v=1&type=image&token=V1:xcC6cO3h8niJNNYeIzag-GAsJYUqK7dI5B0zvVt_NBg)
![](MyBatis_md_files/image_20211001204535.png?v=1&type=image&token=V1:sPJpAo8CrO2Wa2ZlV6VZIZPjcaFpcHDpEjmTazhoWZ8)
![](MyBatis_md_files/image_20211001205118.png?v=1&type=image&token=V1:YumZOBtW1tWTKYA_eP4dPreyacSCY9X9OMkkmGpyfnk)

**MyBatis核心配置文件层级关系**
* configuration配置
	* properties属性
	* settings设置
	* typeAliases类型别名
	* typeHandlers类型处理器
	* objectFactory对象工厂
	* plugins插件
	* environments环境
		* environment环境变量
		* transactionManager事务管理器
		* dataSource数据源
	* databaseIdProvider数据库厂商表示
	* mappers映射器

![](MyBatis_md_files/image_20211001222158.png?v=1&type=image&token=V1:_62tFNR__vn8AKSgenMDQMPpWAu-ffyLpLI76gDDJYE)

![](MyBatis_md_files/image_20211001222407.png?v=1&type=image&token=V1:fJIGTC1emeKmTaHDJHITLlD1KcJoFLJVKVfX0eXjsI8)
![](MyBatis_md_files/image_20211001233404.png?v=1&type=image&token=V1:EKQCUQlSCoU2cvFAKzNsGnQH0tWTNQ8qsEsu9dTQP8k)
![](MyBatis_md_files/image_20211001233832.png?v=1&type=image&token=V1:wDhFn2GbOtAvq13CeGfpwdbbFg6aL8P_sVTXI_93Caw)
![](MyBatis_md_files/image_20211001234606.png?v=1&type=image&token=V1:g-MeqHLP1yoERUxQ3Xpp6PuTy1tr-uN-zwrIcfVoCro)
注：typeAliases在xml中有先后顺序，如在`<environments>`之前
![](MyBatis_md_files/image_20211002040108.png?v=1&type=image&token=V1:L72JmoyN_kDFxUsSKv5ry9WJntDM4_BElRUWfLV0D1Q)

### MyBatis相应API
![](MyBatis_md_files/image_20211002040319.png?v=1&type=image&token=V1:t3nps1UWXLI0_Y7fYyXycOuGqCOSdUHSNlYpgI8Cstc)

1. 传统Dao设置：
![](MyBatis_md_files/image_20211003034519.png?v=1&type=image&token=V1:pe3MdBiYNVpR-WxUtX-ZIHqgInKpQVwRdRlaUlJRO6U)
2. 接口代理方式
![](MyBatis_md_files/image_20211003031235.png?v=1&type=image&token=V1:la3eMGwRC4-dQbnV9_nWdhXxdZZ4NQa-VmqNh2-PuBI)

***
### MyBatis映射文件深入
#### Log4j.properties配置
![](MyBatis_md_files/image_20211003040845.png?v=1&type=image&token=V1:dp9l1CQHztzskKBhMIT6Z8HURvDpH8GVGv9-T6ONuIE)
配置rootLogger到debug级别，可以在控制台输出下面SQL动态生成语句。
![](MyBatis_md_files/image_20211003040736.png?v=1&type=image&token=V1:ZR243yNEeOd1vTKqbw7PTN1L4VCh-Ljl43eqliNGxf4)
#### 动态sql语句
`<select>`:查询,一般用resultType接收对象，参数用paramenterType
```
<select id="findById" resultType="user" parameterType="int">  
  delete from user where id=#{id}  
</select>
```
`<insert>`:parameterType插入的对象，插入实体属性名称`#{参数值}`
```
<insert id="save" parameterType="com.example.mybatis.domain.User">  
  insert into user values(#{id},#{username},#{password})  
</insert>
```
`<update>`:修改
```
<update id="update" parameterType="com.example.mybatis.domain.User">  
  update user set username=#{username}, password=#{password} where id=#{id}  
</update>
```
`<delete>`:parameterType是实体的话需要传入对象，也可以`int`单个值等。
```
<delete id="delete" parameterType="java.lang.Integer">  
  delete from user where id=#{id}  
</delete>
```
`<where>`:动态判断是否有where条件
`<if>`:拼接条件
```
<select id="findByCondition" parameterType="user" resultType="user">  
  <!--select * from user where id=#{id} and username = #{username} and password=#{password}-->  
		 select * from user where 1=1  
	  <if test="id != 0">  
		  and id=#{id}  
	  </if>  
	  <if test="username != null">  
		  and username=#{username}  
	  </if>  
	  <if test="password != null">  
		  and password=#{password}  
	  </if>  
	<!--或者使用<where></where>包裹<if>条件,即可省略where 1=1，看起来更优雅。但是输出结果与where 1=1还是稍有不同，如空参-->  
</select>
```
`<foreach>`:循环拼接
```
<select id="findByIds" parameterType="list" resultType="user">  
  <!-- select * from user where id in (1,2,3) -->  
  select * from user  
  <where>  
	  <foreach collection="list" open="id in(" close=")" item="id" separator=",">  <!--collection="array"-->  
		  #{id}  
	  </foreach>  
  </where>  
</select>
```
`<sql>`:sql片段的抽取
```
<!--sql语句的抽取-->  
<sql id="selectUser">select * from user</sql>  
<select id="findId" parameterType="int" resultType="user">  
  <include refid="selectUser"></include>  <!--select * from user where id=#{id}-->  
  <where>  
	  id=#{id}  
  </where>  
</select>
```
![](MyBatis_md_files/image_20211003085541.png?v=1&type=image&token=V1:IK9cVvSiMnv7EvZhuV7Tn4UGnKcYWd0QQUS23PwWr6I)

![](MyBatis_md_files/image_20211003091439.png?v=1&type=image&token=V1:m_ZrMP1IfyYSp5AxD12HmNMy412dqAmmLl-6Y9CCCQ4)
注： `<T>`为java数据类型

![](MyBatis_md_files/image_20211004034829.png?v=1&type=image&token=V1:Hg6g_JcytaLIWHwS6EmDUMWyLwQOimwY0u35ZBSAUwI)
***
### MyBatis多表操作
#### 一个订单对一个用户：

![](MyBatis_md_files/image_20211004041626.png?v=1&type=image&token=V1:HTF8ewmEYEUyg0PJwfmeQLpDgkM5b-TKCt7UG7Lk2C4)
Order类：
![](MyBatis_md_files/image_20211004223250.png?v=1&type=image&token=V1:F8k7SZ_8MeFVSRg6HwW6ifRbPWHDuQItf7rikF9hjNw)
OrderMapper第一种方式:
![](MyBatis_md_files/image_20211004223214.png?v=1&type=image&token=V1:hhTkS-Suo21dI_iIv4Trxkd86U7l7Jlr6U2RTECydvM)
OrderMapper第二种方式：
![](MyBatis_md_files/image_20211004224332.png?v=1&type=image&token=V1:waq9yY0lBjmjcwqfnChshDcfSKK3XHz_hbdwVfrUQoU)
测试类：
![](MyBatis_md_files/image_20211004223335.png?v=1&type=image&token=V1:7HUlGzJsgCIka5IMU3RI8qlgwwB_HK5MJmoE_t8tVNk)
输出结果：
![](MyBatis_md_files/image_20211004223356.png?v=1&type=image&token=V1:KG3gCXunbMLMEj3cJkSVQXIZXJNgvXXigV1opNEwPU8)

#### 一个用户对多个订单：
User类：
![](MyBatis_md_files/image_20211004232138.png?v=1&type=image&token=V1:3pr-5R64a0XyqCI1WSzNX5Z7QppQE-qM6ybpYhVIrls)

UserMapper.xml配置
![](MyBatis_md_files/image_20211004232251.png?v=1&type=image&token=V1:5MQqNMnmgl7wYxazhEs0UzTPxKJLwnECxxVrrMRew-w)

测试类：
![](MyBatis_md_files/image_20211004232333.png?v=1&type=image&token=V1:E3-5et_6Kg5aqcAjYrYKLn-4krW3MeBvyIDxU34hSqc)

#### 多对多模型
![](MyBatis_md_files/image_20211004232612.png?v=1&type=image&token=V1:axGT9000r2Wxe44pZfcQqkp0ceciKl6k9WdjJeLyhhk)
提示：做查询右查询结果不一样
Role角色类：
![](MyBatis_md_files/image_20211005025956.png?v=1&type=image&token=V1:NT9JkUPo7dAQg_9WeACakTeLLJ44OA96gjG19onVRk0)
用户类：![](MyBatis_md_files/image_20211005030021.png?v=1&type=image&token=V1:_BaeLZ6DNJBjM8yvGNC7AeESySJRTAymbIBJCUO0ve0)
中间一张表uer_role链接
![](MyBatis_md_files/image_20211005030121.png?v=1&type=image&token=V1:8gyXyp45o2hBUiwAvEvxxF_XJfahYeBiKdYnm9ccZzg)
测试类：
![](MyBatis_md_files/image_20211005030307.png?v=1&type=image&token=V1:DP62KnwWBgaRwb2q7cL8T7Sq9c7mvKe7njNauktumUc)
![](MyBatis_md_files/image_20211005044644.png?v=1&type=image&token=V1:GQvOyyabaxqnlXCPu0VJpNHA571ww8xJt2sT-eSJhfI)














