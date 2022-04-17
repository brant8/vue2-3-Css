### AJAX & JSON

### AJAX
概念：Asynchronous JavaScript And XML。异步的JavaScript和XML
* 异步和同步：
	* 同步：客户端必须等待服务器端的响应，再等待的期间客户端不能做其他操作。
	* 异步：客户端不需要等待服务器端的响应，再服务器处理请求的过程中，客户端可以进行其他的操作。

实现方式：
* 原生JS实现方式
```
<input type="button" value="发送异步请求" onclick="fun();">
<script>  
	function fun(){  
		//发送异步请求  
		//1.创建核心对象  
		var xmlhttp;  
		if(window.XMLHttpRequest){  
			//IE7+,Chrome...  
			xmlhttp = new XMLHttpRequest();  
		}else{  
			//IE5,IE6  
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");  
		}  
		
		//2.建立连接  
		/*参数  
		请求方式：GET/POST；  
		- get方式：请求参数再URL后边拼接，send方法为空参  
		- post方式：请求参数在send方法中定义  
		请求的URL；  
		同步或异步请求：true异步或false同步。  
		*/  
		xmlhttp.open("GET","ajaxServlet?username=tom",true);  
		
		//3.发送请求  
		xmlhttp.send();  
		//post方式:xmlhttp.send("username=tom");  
		
		//4.接受并处理来自服务器的响应结果  
		//获取方式  
		//什么时候获取:当服务器相应成功后再获取  
		//当xmlhttp对象的就绪状态改变时,触发事件onreadystatechange  
		xmlhttp.onreadystatechange=function(){  
			//就绪状态完成代号为4  
			if(xmlhttp.readyState==4 &&xmlhttp.status==200){  
			document.getElementById("myDiv").innerHTML=xmlhttp.responseText;  
			}  
		}  
	}  
</script>
```
* jQuery实现方式
	* `$.ajax()`：
	* `$.get()`：
		* 语法:`$.get(url,[data],[callback],[type])`
	* `$.post()`：
```
<input type="button" value="发送异步请求" onclick="fun();">
<script>  
	function fun(){  
		//使用$.ajax()方式  
		$.ajax({  
			url:"ajaxServlet",//路径  
			type:"POST",//请求方式  
			data:"username=jack&age=23",//请求参数  
			//第二种date格式  
			//data:{"username":"jack","age":23},  
			success:function(data){  
				alert(data);  
			},//响应成功后的回调函数 
			error:function(){
				alert("出错了..");
			},
			dataType:"text"//设置接收到的响应的数据格式
		});  
	}  
</script>

//$.get方法
function fun(){
	$.get("ajaxServlet",{username:"rose"},function(){
		alert(data);
	},"text");
}

//$.post方法
function fun(){
	$.post("ajaxServlet",{username:"rose"},function(){
		alert(data);
	},"text");
}
```

***
### JSON
概念: JavaScript Object Notation, JavaScript对象表示法
```
//Java对象表示法
Person p = new Person();
p.setName("zhang san");
p.setAge(23);

//JSON对象表示法
var p = {"name":"zhang san", "age":23};
alert(p);//返回[object Object]
```
JSON**现在**多用于存储和交换信息的语法，类似XML。JSON比XML更小,更快更易解析。
语法:
* 基本规则:
	* 数据在名称/值对中：json数据是由键值对构成的
		* 键用引号(单双都行)引起来，也可以不使用引号
		* 值得取值类型:
			* 数字(整数或浮点数)
			* 字符串(在双引号中)
			* 逻辑值(true或者false)
			* 数组(在方括号中)` {"persons":[{},{}]}`
			* 对象(在花括号中)：`{"address":{"province":"陕西"...}}`
		* 数据用逗号分隔：多个键值对逗号分隔
		* 花括号保存对象：使用{}定义json格式
```
<script>
	var person = {"name":"zhang san",age:23,'gender:true};
	//数组[]套嵌{}
	var ps = [{"name":"zhang san",age:23,'gender:true},
	{"name":"zhang san",age:23,'gender:true},
	{"name":"zhang san",age:23,'gender:true}];
	alert(ps[1].name);
	
	//获取person对象中所有的键和值
	//for in循环
	for(var key in person){
		//下行的获取方式不行,相当于person."name"
		//alert(key + ":" + person.key);
		alert(key + ":" + person[key]);
	}
	//获取ps中的所有值
	for(var i = 0; i<ps.length; i++){
		var p = ps[i];
		for(var key in p){
			alert(key + ":" + person[key]);
		}
	}
</script>
```

JSON数据和Java对象的相互转换
JSON解析器: 常见的解析器:Jsonlib, Gson, fastjson, jakson
* JSON转为Java对象
* Java对象转换JSON
	* 导入jakson相关的jar包
	* 创建jakson核心对象ObjectMapper
	* 调用ObjectMapper的相关方法进行转换
```
public class JacksonTest{
	//Java对象转为JSON字符串
	@Test
	public void test1(){
		//1.创建对象
		Person p = new Person();
		p.setName("张三");	
		
		//2.创建Jackson的核心对象ObjectMapper
		ObjectMapper mapper = new ObjectMapper();
		
		//3.转换
		/*writeValue(参数1,obj)
		*参数1:
		***File: 将obj对象转换为JSON字符串,并保存到指定的文件中
		***Writer: 将obj对象转换为JSON字符串,并将json数据填充到字符输出流中
		***OutputStream: 将obj对象转换为JSON字符串,并将json数据填充到字节输出流中
		* writeValueAsString(obj):将对象转为json字符串
		*/
		String s = mapper.writerValueAsString(p); //有异常需要抛出去
		
		//writeValue将数据写到d://a.txt文件中
		mapper.writeValue(new File("d://a.txt",),p);
		
		mapper.writerValue(new FileWriter("d://a.txt"),p);
	}
}
```
* 注解(在对象类中属性声明中使用,如Person类的出生日期`private Date birthday`)
	* @JasonIgnore: 排除属性
	* @JsonFormat: 属性值得格式化
		* `@JsonFormat(pattern="yyy-MM-dd")`
* 复杂java对象转换
	* List:数组
	* Map:对象格式一致
* JSON字符串转为Java对象
```
@Test
public void test5() throws Exception{
	//初始化JSON字符串
	String json = "{\"gender\":\"男",\"name\":\"张\",\"age\":23}";
	//创建ObjectMapper对象
	ObjectMapper mapper = new ObjectMapper();
	//转换为Java对象 Person对象
	Person person = mapper.readValue(json,Person.class);
	System.out.println(person);
}
```
案例
* 校验用户名是否存在
	* 服务器相应的数据,在客户端使用是,想要当作json数据格式使用
		* `$.get(type)`: 将最后一个参数type指定为`json`
	* 在服务器段设置MIME类型
		* `response.setContentType("application/json;charset=utf-8");`
		
