## JavaScript笔记

👇**ECMAScript**
***
	 JavaScript = ECMAScript + JavaScript自己特有的(DOM+BOM)
Java强类型语言，在开辟变量存储空间时，定义了空间将来存储的数据类型。只能存储固定类型的数据。

JavaScript是弱类型语言，在开辟变量存储空间时，不定义空间将来的存储数据类型，可以存放任意类型的数据。

    var 变量名 = 初始化值

输出到页面上

    document.write(num + "<br>");

使用**var关键字**
 - 用：定义的变量是局部变量
 - 不用：定义的变量是全局变量
 ***
#### typeof :

    var number = 123; (NaN，boolean)
    var str = "abc";
    var obj = null;
    var obj2 = undefined;
    var obj3;
    document.write(number + typeof(number) "<br>");
    document.write(str+ typeof(str) "<br>");
    document.write(obj + typeof(obj) "<br>");
    document.write(obj2 + typeof(obj2) "<br>");
    document.write(obj3 + typeof(obj3) "<br>");
***
### 运算符
   一元运算： ++（--）在前，先自增(自减)，再运算。在后，先运算再自增（自减）。
其他类型转换成number。
 - string转number:  按照字面转换，如`var a = +"123"`如果字面值不是数字，则转为**NaN**(不是数字的数字)如`var b = +"abc"`。
 - boolean转number :  true转为1，false转为0。如`var flag = +true`。
 - `===`全等于

***
### 基本对象
**Function**: 函数(方法)对象
*	1.创建
	*	 `function 方法名称(形式参数列表){ 方法体 }`
	*	`var 方法名 = function(形式参数列表){ 方法体 }`
*	2.方法
*	3.属性 `length: 代表形参的个数`
*	4.特点 
	 * 方法定义时，形参的类型不用写，返回值类型也可以不写
	 * 方法是一个对象，如果定义名称相同的方法，会覆盖
	 * 在JS中，方法的调用只与方法的名称有关，和参数列表无关
	 * 在方法声明中有一个隐藏的内置对象(数组)，arguments，封装所有的实际参数。如下：
``` 
function add(){
	 var sum = 0;
		 for(var i = 0; i<arguments.length; i++){
			sum+= arguments[i];
		 }
	 return sum;
}
```
*	5.调用

**Array**
1. 创建:
		* var arr = new Array(元素列表);
		* var arr = new Array(默认长度);
		* var arr = [元素列表];
2. 方法: 
	*  join(参数)将数组中的元素按照指定的分隔符拼接为字符串
		* `document.write(arr.join("--"+"<br>");`
	*  push(参数)象数组末尾添加一个或多个元素，并返回新的长度。
		* `arr.push(11);` `document.write(arr.join("--"+"<br>");`
3. 属性: `length：数组的长度`
4. 特点:
	*  JS中，数组元素的类型可变的。`var arr = [1,"abc",true];`
	* 数组的长度是可变的。
	
**Boolean**

**Date**
1. 创建:
		* var date = new Date();
2. 方法: 
	*  toLocaleString()返回当前date对象对应的时间本地字符串格式。
	* getTime()获取毫秒值，到1970-01-01零点毫秒值差。
3. 属性: 
4. 特点:

**Math**
1. 创建:
		* Math对象不用创建，直接使用。 `Math.方法名();`
2. 方法: 
	*  random()返回0~1的随机数，含0不含1(0，1]。
3. 属性: `Math.PI`
4. 特点:

**Number**
**String**

**RegExp** 正则表达式对象
1. 单个字符[]；\d单个数字字符[0-9],\w单个单词字符[a-zA-Z0-9]
2. `?`,`*`,`+`,`{m,n)` ,`^`,`$`
3. 创建对象  `var reg = new RegExp("正则表达式")`，`var reg = /表达式/`

**Global** 全局对象，这个Global中封装的方法不需要对象就可以直接调用。方法名（）。
方法：
	encodeURI()：url编码
	decodeURI()：url编码
	encodeURIComponent()：url编码
	decodeURIComponent()：url解码
	parseInt()：将字符串转为数字。注意判断每一个字符是否是数字，知道不是数字为止，将前面数字部分转为number。
	isNaN()：判断一个值是否NaN。NaN六亲不认，NaN参与的都是false。
	eval()：将JavaScript字符串，并把它作为脚本代码来执行。
***
👆**ECMAScript**
👇**DOM**
***
 **功能**：控制html文档的内容(增删改查)
 **代码**：获取页面标签(元素)对象Element
	 - document.getElementById("id值")：通过元素的id获取元素对象
	 - 操作Element对象
			1. 修改属性值
				· 明确获取的对象是哪一个
			 	· 查看API文档，找其中有哪些属性可以设置
			2.  修改标签体内容：
				. 属性：innerHTML		
		
 案例：
```
 <script> //无法获取off.gif，因为script先加载
	 var light = document.getElementById("light");
	 alert(light);
	 light.src = "img/on.gif" //src为自带属性
 </script>
 <img id="light" src="img/off.gif">
```

### 事件简单学习
功能：某些组件被执行了某些操作后，触发某些代码的执行。

**如何绑定事件**
1. 直接在html标签上，指定时间的属性(操作)，属性值就是js代码。
		事件： onclick --单击时间
		
	`<img id="light" src="img/off.gif" onclick="alert('我被点击了')"> `

2. 通过js获取元素对象，指定时间属性，设置一个函数。
```
	<img id="light" src="img/off.gif" onclick="alert('你点击了我')">
	
	<script>
	fun(){
		alert('点击事件');
    }
	var light = document.getElementById("light");
	light.onclick = fun;
	</script>
```
**电灯开关案例**

    <img id = "light" src=img/off.gif">
    
    <script>
    var light = document.getElementById("light");
    var flag = false; //代表灯是灭的
    light.onclick = function(){
    	if(flag){
    		light.src = "img/off.gif"
    		flag = false;
    	}else{
    		light.src = "img/on.gif"
    		flag = true;
    	}
    }
    </script>

***
👆DOM
👇BOM
***
**概念**：Browser Object Model 浏览器对象模型
- 将浏览器的各个组成部分封装成对象。(图片文件夹目录有截图)
	* 组成：
		* Window: 窗口对象
		* Navigator: 浏览器对象
		* Screen: 显示器屏幕对象
		* History: 历史记录对象
		* Location: 地址栏对象
		
**Window**
	1. 创建
	2. 方法
- 与弹出框有关的方法
		~ alert()，显示确定按钮警告框
		~  confirm()，显示确认和取消对话框。
	    	`var flag = confirm(“确认退出吗？”);`
		    `alert(flag);  //确认返回true，取消返回false`
		~  prompt()，弹出用户输入框
- 与开启关闭有关的方法
		`var closeBtn = document.getElementById("closeBtn");`
		`closeBtn.onclick = function(){close();}`
- 与定时器有关的方法
		`setTimeout()` 如：`setTimeout("alert("hello")",3000);`
		`clearTimeout()`
		`setInterval()`
		`clearInterval()`
- 可设置轮播图。
*分析：在页面使用img标签展示图片。
定义一个方法，修改图片对象的src属性。
定义一个定时器，每隔3秒调用方法一次。*
3. 属性
- 获取其他BOM对象，如window.history。
			- history
			- location
			- Navigator
			- Screen
- 获取DOM对象，如window.document。
			- document 
4. 特点
		- Window对象不需要创建可以直接使用。 window.方法名()。
		- window可以省略，方法名()。如alert()  ->  window.alert();

***
**Location：** 地址栏对象
1. 创建（获取）
	- window.location
	- location
2. 方法：
	- reload() 重新加载当前文档。
3. 属性：
	- href：设置或返回完整的URL。
4. 案例：
	*自动跳转等待*。
	分析：显示页面效果`<p>`
			倒计时读秒效果实现。
				定义一个方法，获取span标签，修改span标签体内容，时间--。`time.innerHTML = second + "";`
				定义一个定时器，1秒执行一次该方法。
***

**History** 历史记录对象
- 创建（获取）
	- window.history
	- history
- 方法
	- back()
	- forward()
	- go(参数)：正数，前进几个。负数，后退几个。
- 属性
	- length 返回当前窗口历史列表中的URL数量。
***
**DOM**
***
概念：Document Object Model文档对象模型。
	将标记语言文档的各个组成部分，封装为对象。可以使用这些对象，对标记语言文档进行CRUD的动态操作。
- 核心DOM - 针对任何结构化文档的标准模型
	- Document*：文档对象 `window.document`获取
		- 参考XML(较HTML修改少，HTML的DOM修改多)
		- 获取Element对象方法：
			- `getElementById()`：根据i**d属性值**获取元素对象，id属性值一般唯一。`document.getElementById("id123")`
			- `getElementsByTagName()`：根据**元素名称**获取元素对象们，返回值是一个数组。`document.getElementsByTagName("div")`，`document.getElementsByTagName("a")[0]`获取`<a>`标签第一个。
			- `getElementsByClassName()`：根据**Class属性值**返回元素对象们，返回值是一个数组。`document.getElementsByClassName("cls123")`
			- `getElementsByName()`：根据**name属性值**获取元素对象们，返回值是一个数组。`document.getElementsByName("username")`
		- 创建其他DOM对象方法：
			- createAttribute(name)：
			- createComment()：
			- createElement()：
			- createTextNode()：
	- Element*：元素对象
		- 通过document来获取和创建属性
			- removeAttribute()：
			- setAttribute()：
				- `var element_a=document.getElementsByTagName("a")[0]`
				- `element_a.setAttribute("href","https://google.ca”)`
	- Attribute：属性对象
	- Text：文本对象
	- Comment：注释对象
	- Node*：节点对象，其他5个的父对象
		- 特点：所有DOM对象都可以被认为是一个节点。
		- 方法：CRUD dom树：
			- appendChild()：向节点的子节点列表的结尾添加新的节点。
			- removeChild()：删除(并返回)当前节点的指定子节点。
				- `javascript:void(0)`详情查看笔记图片目录。
		- 属性：parentNode
- XML DOM - 针对XML文档的标准模型
- HTML DOM -针对HTML文档的标准模型
	- 标签体的设置和获取：innerHTML
	- 使用html元素对象的属性
	- 控制元素样式
		- 使用元素的style属性来设置
			- `div1.styleborder="1px solid red";`
			- `div1.style.width="200px"`
			- `//font-size ->fontSize`
			- `div1.style.fontSize="20px"`
		-提前定义好类选择器的样式，通过元素的className属性来设置其class属性值。

***
### 事件
概念：某些组件被执行了某些操作后，出发某些代码的执行。
	事件：某些操作，如：单击，双击，键盘按下了，鼠标移动了
	事件源：组件，如：按钮，文本输入框
	监听器：代码
	注册监听：将事件，事件源，监听器结合在一起。当事件源上发生了某个事件，则出发执行某个监听器代码。

常见的事件
- 点击事件
	- onclick： 当用户点击某个对象是调用的事件句柄。
	- ondblclick：双击事件
- 焦点事件
	- onblur：失去焦点，一般用于表单验证（先有焦点再失去）
	- onfocus：元素获得焦点
- 加载事件 
	- onload：一张页面或一幅图像完成加载，可以让事件再页面加载完毕后发生，可以让script放在页面顶部而不用放在页面底部。
		- `window.onload= function(){document.getElementById("username").onblur = function(){alert("失去焦点了...")}}`
- 鼠标事件：
	- onmousedown
	- onmouseup
	- onmousemove
	- onmouseover
	- onmouseout
- 键盘事件
	- onkeydown
	- onkeyup
	- onkypress
- 选择和改变
	- onchange 用于选择，如城市选择`<option>`，三级联动等
		- `document.getElementById("city").onchange = function(event){ alert("改变了") }`
	- onselect
- 表单事件
	- onsubmit：可以组织表单的提交(提交前验证)
		- 方法返回false则表单被阻止提交
		- 校验用户名流程
			- 1.获取用户名的值`getElementById`.`value`
			- 2.定义正则表达式，如用户名6个以上字符
			- 3.判断值是否符合正则的规则
			- 4.提示信息
	- onreset

