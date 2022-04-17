### jQuery
JavaScript框架：本质上就是一些js文件，分装了js的原生代码

自定义js框架举例
```
//HTML
<div id="div1">div1...</div>
<div id="div1">div1...</div>
```
```
<script>
//根据id获取元素对象
var div1 = document.getElementById("div1");
var div2 = document.getElementById("div2");
//获取标签体内容
alert(div1.innerHTML);
alert(div2.innerHTML);

//封装方法，根据id来获取元素对象
function get(id){
	var obj = document.getElementById(id);
	return obj;
}
</script>
```
***
#### jQuery对象和JS对象区别与转换

jQuery对象和js对象方法不通用 
```
<script>  
//通过js方式来获取名称叫div的所有html元素对象  
	var divs = document.getElementsByTagName("div");  
	alert(divs);//[object HTMLCollection]  
//可以当作数组使用如div.length。  
//对divs种所有的div让其标签体内容变为"aaa"  
  for(var i =0; i<divs.length; i++){  
     divs[i].innerHTML = "aaa";  
  }  
//jQuery与js相互转换js->jQuery：$(js对象)  
	for(var j =0; j<divs.length; j++){  
	    $(divs[j]).html("ccc");  
	}  
  
//通过jQuery方式来获取名称叫div的所有html元素对象  
	var $divs = $("div");  
	alert($divs);//[object Object]  
//可以当作数组使用如div.length。  
//对divs种所有的div让其标签体内容变为"bbb"，使用jQuery方式。  
	$divs.html("bbb");  
//js与jQuery相互转换jQuery->js：js对象[索引] 或者 jq对象.get(索引)  
	$divs[0].innerHTML = "ddd";  
	$divs.get(1).innerHTML = "eee";  
</script>
```
***
#### 选择器：
筛选具有相似特征的元素(标签)

* 基本语法学习
	* 事件绑定
	* 入口函数
	* 样式控制
```
<div id="div1"> div1... </div>  
<div id="div2" title="ShuXingValue"> div2... </div>   
<input type="button" value="点击我" id="b1">  
<script>  
  //给b1按钮添加单击事件，script在id之后。在head加script在js中需要使用window.onload=function(){...}  
	  $("#b1").click(function(){  
	        alert("abc");  
	  });  
 //jquery入口函数(dom文档加载完成之后执行该函数中的代码)  
 //jQuery不使用window.onload的语法如下: $(function(){...});  
 //window.onload和$(function)区别在于：window.onload只能定义一次，写多次后写的覆盖。$(function)可以写多次  
	  $(function(){  
	        $("#div1").css("background-color","pink");  
	        //另一种写法$("#div1").css("backgroundColor","pink");  
	  });  
</script>
```
****

* 选择器分类
	* 基本选择器
		* `$("html标签名")`获得素有匹配标签名称的元素
		* `$("id1").click(function{  $("div").css("background-color","pink"); });`
	* id选择器
		* `$("#id的属性值")`获得与指定id属性值匹配的元素
			* `$("#id2").click(function(){  $(#id2_2).css("background-color","pink");  });`
	* 类选择器
		* `$(".class的属性值")`获得与指定的class属性值匹配的元素
			* `$(".id3").click(function{  $("div").css("background-color","pink"); });`
	* 并集选择器
		* `$("选择器1，选择器2...")`获取多个选择器选中的所有元素
			* `$(".id3").click(function{  $("span,#two").css("background-color","pink"); });`
* 层级选择器
	* 后代选择器
		* `$("A B ")`选择A元素内部的所有B元素
			* `$(function(){ $("#button").click( function(){ $("body div").css("background-color","pink");}); });`
	* 子选择器
		* `$("A > B")`选择A元素内部的所有B子元素
			* `$(function(){ $("#button").click( function(){ $("body > div").css("background-color","pink");}); });`
* 属性选择器
	* 属性名称选择器
		* `$("A[属性名]")`包含指定属性的选择器
			* `$(function(){ $("#button").click(function(){ $("div[title]").css("background-color","pink");}); });`
	* 属性选择器
		* `$("A[属性名='值']")`包含指定属性等于指定值的选择器
			* `$(function(){ $("#button").click(function(){ $("div[title='test']").css("background-color","pink");}); });`
	* 复合属性选择器
		* `$("A[属性名='值'][]...")`包含多个属性条件的选择器
			* `$(function(){ $("#button").click(function(){ $("div[id][title='test']").css("background-color","pink");}); });`
* 过滤选择器
	* 首元素选择器
		* `:first`获得选择的元素中的第一个元素
			* `$(function(){ $("#button").click(function(){ $("div:last").css("background-color","pink"); }); });`
	* 尾元素选择器
		* `:last`获得选择的元素中的最后一个元素
			* `$(function(){ $("#button").click(function(){ $("div:last").css("background-color","pink"); }); });`
	* 非元素选择器
		* `:not(selector)`不包括指定内容的元素
			* `$(function(){ $("#button").click(function(){ $("div:not(.one)").css("background-color","pink"); }); });`
	* 偶数选择器
		* `:even`偶数，从0开始计算
			* `$(function(){ $("#button").click(function(){ $("div:even").css("background-color","pink"); }); });`
	* 奇数选择器
		* `:odd`奇数，从0开始计算
			* `$(function(){ $("#button").click(function(){ $("div:odd").css("background-color","pink"); }); });`
	* 等于索引选择器
		* `:eq(index)`指定索引元素
			* `$(function(){ $("#button").click(function(){ $("div:eq(2)").css("background-color","pink"); }); });`
	* 大于索引选择器
		* `:gt(index)`大于指定索引元素
			* `$(function(){ $("#button").click(function(){ $("div:gt(2)").css("background-color","pink"); }); });`
	* 小于索引选择器
		* `:lt(index)`小于指定索引元素
			* `$(function(){ $("#button").click(function(){ $("div:lt(2)").css("background-color","pink"); }); });`
	* 标题选择器
		* `:header`获得标题`[h1-h6]`元素，固定写法
			* `$(function(){ $("#button").click(function(){ $(":header").css("background-color","pink"); }); });`
* 表单过滤选择器
	* 可用元素选择器
		* `:enabled`获取可用元素
			* `$(function(){ $("#button").click(function(){ $("input[type='text']:enabled").val("aaa"); }); });`
	* 不可用元素选择器
		* `:disabled`获取不可用元素
			* `$(function(){ $("#button").click(function(){ $("input[type='text']:disabled").val("bbb"); }); });`
	* 选中选择器
		* `:checked`获得单选/复选框选中的元素
			* `$(function(){ $("#button").click(function(){ alert($("input[type='checkbox']:checked").length); }); });`
	* 选中选择器
		* `:selected`获得下拉框选中的元素
			* `$(function(){ $("#button").click(function(){ $("#job > option:selected").length; }); });`
***
### DOM操作
内容操作
* `html()`：获取/设置元素的标签体内容		`<a><font>内容</font></a>`  -->  获得 `<font>内容</font>`
* `text()`：获取/设置元素的标签体纯文本内容  	`<a><font>内容</font></a>`   -->  获得  `内容` 
* `val()`：获取/设置元素的value属性值
```
HTML
<body>
	<input id="myinput" type="text" name=username" value="张三" /><br/>
	<div id="myid"><p><a href="#">标题标签</a></p></div>
</body>

<script>  
 $(function () {  
  //获取myinput的value值  
  alert( $("#myinput").val() );  
  $("#myinput").val("李四");  
  
  //获取mydiv的标签体内容  
  alert( $("#myid").html() );  
  $("#myid").html("<p>aaa</p>");  
  
  //获取mydiv文本内容  
  alert( $("#myid").text() );  
  $("#myid").text("text content");  
 })  
</script>
```
* 属性操作
	* 通用属性操作
		* `attr()`：获取/设置元素的属性
		* `removeAttr()`：删除属性
		* `prop()`：获取/设置元素的属性
		* `removeProp()`：删除属性
	* attr和prop区别：
		* 如果操作的是元素的==固有属性==，则建议使用prop
		* 如果操作的是元素==自定义的属性==，则建议使用attr
	* 对class属性操作
		* `addClass()`：添加class属性值
		* `removeClass()`：删除class属性值
		* `toogleClass()`：切换class属性
			* `toggleClass("one")`判断如果元素对象上存在class="one"，则将属性值one删掉。如果元素对象上不存在class="one"，则添加
		* `css()`
```
<ul>  
	 <li id="bj" name="beijing" xxx="yyy">北京</li>  
	 <li id="tj" name="tianjin">天津</li>  
</ul>  
<input type="checkbox" id="hobby">  
  
<script>  
	 $(function () {  
		 alert( $("#bj").attr("name") );  
		 $("#bj").attr("name","daBJ");  
		 $("#bj").attr("descriptions","DiDu");  
		 $("#bj").removeAttr("name");  
		 alert( $("#hobby").prop("checked") );  
		 })  
</script>
```
* CRUD操作：==对象均为$("选择器")==
	* `append()`：父元素将子元素追加到末尾
		* `对象1.append(对象2)`：将对象2添加到对象1元素内部，并且在末尾
	* `prepend()`：父元素将子元素追加到开头
		* `对象1.append(对象2)`：将对象2添加到对象1元素内部，并且在开头
	* `appendTo()`：
		* `对象1.appendTo(对象2)`将对象1添加到对象2元素内部，并且在末尾
	* `prependTo()`：
		* `对象1.prependTo(对象2)`将对象1添加到对象2元素内部，并且在开头
	* ==`after()`==：添加元素到对应元素后边
		* `对象1.after(对象2)`：将对象2添加到对象1后边，对象1和对象2是兄弟关系
	* ==`before()`==：添加元素到对应元素前边
		* `对象1.before(对象2)`：将对象2添加到对象1前边，对象1和对象2是兄弟关系
	* `insertAfter()`：
		* `对象1.insertAfter(对象2)`：将对象2添加到对象1后边，对象1和对象2是兄弟关系
	* `insertBfore()`：
		* `对象1.insertBefore(对象2)`：将对象2添加到对象1前边，对象1和对象2是兄弟关系
	* `remove()`：移除元素
		* `对象.remove()`：将对象删除掉
	* `empty()`：清空元素的所有后代元素
		* `对象.empty()`：将对象的后代元素全部清空，但是保留昂前对象以及其属性节点












