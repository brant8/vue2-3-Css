### Redis
概念: redis是一款高性能的NOSQL（Not Only SQL）系列的非关系型高性能键值对（Key-Value）数据库

主流NOSQL产品:
* 键值（Key-Value）存储数据库
* 列存储数据库
* 文档数据库
* 图形（Graph）数据库

redis应用场景
* 缓存（数据查询，短链接，新闻内容，商品内容等）
* 聊天室在线好友列表
* 任务列表（秒杀，抢购等）
* 应用排行榜
* 网站访问统计
* 数据过期处理（可以精确到毫秒）（过期激活码等）
* 分布式集群架构中的session分离
 ***
* redis.window .conf：配置文件
* redis-cli.exe：redis客户端
* redis-server.exe：redis服务端

redis数据结构：
* redis存储的是：key，value格式的数据，其中key都是字符串，value有5中不同的数据结构
	* value的数据结构
		* 字符串类型 string
		* 哈希类型 hash：map格式
		* 列表类型 list： linkedlist格式，允许重复元素
		* 集合类型 set：hashset格式，不允许重复元素
		* 有序集合类型 sortedset：排序，不允许重复元素
***
* 字符串类型 string
	* 存储：`set key value`
	* 获取：`get key`
	* 删除：`del key`
* 哈希类型 hash 
	* 存储：`hset key field value`
	* field可理解为另一对 “key value”，相当于[key1 - [key2 - value ]]
	* 获取：
		* `hget key field`：指定键值
		* `hgetall key`：所有键值
	* 删除：`hdel key field`
* 列表类型 list：按照插入顺序排序，左右开口，可以向左/右方向添加元素
	* 添加：
		* `lpush key value`：将元素加入列表左边
		* `rpush key value`：将元素加入列表右边
	* 获取：`lrange key start end`：返回获取
		* `lrange key 0 -1`：获取全部
	* 删除：
		* `lpop key`：删除列表最左边的元素，并将原速返回
		* `rpop key`：删除列表最右边的元素，并将原速返回
* 集合类型set：不允许重复元素，无序
	* 存储：`sadd key value`
	* 获取：`smembers key`获取列表中所有元素
	* 删除：`srem key value`删除set集合中的某个元素
* 有序集合类型 sortedset：不允许重复元素，元素有顺序
	* 存储：`zadd key score value`
	* 获取：
		* `zrange key start end`
		* `zrange key start end withscores`
	* 删除：`zrem key value`
* 通用命令
	* `keys * `：查询所有的健
	* `type key`：获取键对应的value类型
	* `del key`：删除指定的key value

#### 持久化
redi是一个内存数据库，当redis服务器重启，数据会丢失，可以将redi内存中的数据持久化保存到硬盘文件中。
redis持久化机制：
* RDB：默认方式，不需要进行配置，默认就是用这种机制
	* 在一定的时间中，检测key的变化情况，然后持久化数据
	* 编辑redis.windows.conf文件
		* `save 900 1`,  `save 300 10`,  `save 60 10000`
		* 需要cmd加配置启动命令：`redis-server.exe redis.windows.conf`
* AOF：日志记录方式（不推荐），可以记录每一条命令的操作。可以每一次命令操作后，持久化数据。
	* 编辑redis.windows.conf文件
		*  `appendonly no`（默认关闭aof）--> `appendonly yes`开启aof
		* `appendfync always`：每一次操作都进行持久或(性能低)
		* `appendfsync everysec`：每隔一秒进行持久化（开启后默认）
		* `appendfsync no`：不进行持久化
***
### Java客户端Jedis
Jedis：一款java操作redis数据库的工具。
```
//获取链接  
Jedis jedis = new Jedis("localhost",6379);  
//Jedis jedis = new Jedis();//如果使用空参构造，默认值"localhost",6379端口号

//字符串操作，存储
jedis.set("username","zhangliu");  
//获取
String username = jedis.get("username");
System.out.println(username);
//可以使用setex()方法存储指定过期时间的key value  
jedis.setex("activecode",20,"hehe");
//将activecode:hehe键值对存入redis，并且20秒后自动删除
//场景：使用注册激活码时效

//存储hash  [key1] + [ [key2]+[string] ]
jedis.hset("user","name","lisi");  
jedis.hset("user","age","23");  
jedis.hset("user","gender","male");  
//获取hash  
String name = jedis.hget("user", "name");  
System.out.println(name);  
//获取hash的所有map中的数据  
Map<String, String> userMap = jedis.hgetAll("user");  
//keyset  
Set<String> keySet = userMap.keySet();  
for(String key : keySet){  
    //获取value  
  String s = userMap.get(key);  
  System.out.println(key+ ": "+s);  
}

//存储list，支持重复元素，[key] + [strings..]
jedis.lpush("mylist","a","b","c");//从左边存
jedis.rpush("mylist","a","b","c");//从右边存
//范围获取
jedis.lrange("mylist",0,-1);
//弹出1个，弹出后list不包含弹出元素
jedis.lpop("mylist");//c
jedis.rpop("mylist");

//set存储,无序不允许重复 [key] + [members..]
jedis.sadd("myset","java","php","c++);
//获取
Set<String> myset = jedis.smembers(myset);
System.out.println(myset);

//sortedset，有序集合不允许重复 [key] + [score](次数) + [member]
jedis.zadd("mysortedset",3,","亚瑟");
jedis.zadd("mysortedset",30,","后裔");
//获取
Set<String> mysortedset = jedis.zrange("mysortedset",0,-1);
//mysortedset输出集合[亚瑟，后裔]

//关闭链接  
jedis.close();
```
***
#### jedis连接池： JedisPool
使用：
* 创建JedisPool连接池对象
* 调用方法`getResource()`方法获取Jedis链接
```
//创建Jedis连接池对象
JedisPool jedisPool = new JedisPool();
//获取链接
Jedis jedis = jedisPool.getResource();
//使用
jedis.set("hehe","haha");

//关闭，归还到连接池中
jedis.close();
```

创建JedisPool工具类JedisPoolUtils
```
//加载配置文件，配置连接池的参数，提供获取连接的方法
public class JedisPoolUtils{
	private static JedisPool jedisPool;
	//加载类的时候加载静态模块
	static{
		//读取配置文件
		InputStream is = JedisPoolUtils.class.getClassLoader.getResourceAsStream("jedis.properties");
		//创建Properties对象
		Properties pro = new Properties();
		//关联
		try{
			pro.load(is);
		}catch(Exception e){
			e.printStackTrace();
		}
		//获取数据，设置到JedisPoolConfig中
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
		config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
		
		//初始化JedisPool
		jedisPool = new JedisPool(config,pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));
	}
	
	//获取连接方法
	public static Jedis getJedis(){
		return jedisPool.getResource();
	}
}
```
jedis连接池工具类使用
```
Jedis jedis = JedisPoolUtils.getJedis();
```
