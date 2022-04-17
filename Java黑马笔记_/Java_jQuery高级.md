### jQuery高级
* 动画
* 遍历
* 事件绑定
* 案例
* 插件
***
#### 动画
* 三种方式显示和隐藏元素
	* 默认显示和隐藏方式(`fn`为`function(){}`可以做`alert`等)
		* `show([speed,[easing],[fn]])`
			* `speed`:动画的速度，三个预定义值`("slow", "normal", "fast")`或表示动画市场的毫秒数值(如：1000)
			* `easing`：用来指定切换效果，默认是`swing`，可用参数`liner`
				* `swing`：动画执行是效果是--先慢，中间快，最后又慢。
				* `liner`：动画执行是速度是匀速的。
				* `fn`：在动画完成时执行的函数，每个元素执行一次。
		* `hide([speed,[easing],[fn]])`
		* `toggle([speed],[easing],[fn])`
	* 华东显示和隐藏方式
		* `slideDown([speed],[easing],[fn])`
		* `slideUp([speed,[easing],[fn]])`
		* `slideToggle([speed],[easing],[fn])`
	* 淡入淡出显示和隐藏方式
		* `fadeIn([speed],[easing],[fn])`
		* `fadeOut([speed],[easing],[fn])`
		* `fadeToggle([speed,[easing],[fn]])`

#### 遍历
* js的遍历方式
	* `for(初始化值；循环结束条件；步长)`
	* jQuery的遍历方式
		* `jQuery对象.each(callback)`：
		* `jQuery对象.each(object, [callback])`或者`$.each(object, [callback])`：
		* `for..of`：
```
<ul  id="city">  
	<li>北京</li>  
	<li>上海</li>  
	<li>天津</li>  
	<li>重庆</li>  
</ul>  
<script>  
$(function () {  
	//获取所有ul下的li  
	var citys = $("#city li");  
	//方法1  js遍历方式
	//遍历  
	for(var i =0; i<citys.length; i++){  
		//获取内容  
		alert(i + ": " + citys[i].innerHTML);  
	}  
	//方法2： jq对象.each(callback)  
	citys.each(function (index,element) {  
		//获取li对象 第一种方式this  
		alert(this.innerHTML);  
		//第二种方式，在回调函数中定义参数，index(索引),element(元素对象)  
		alert(index+":"+element.innerHTML);  
		//方法三  
		alert(index+":"+$(element).html());  
		//方法四  
		alert($(this).html());  
	})  
	//方法3, $.each(object,[callback])  
	$.each(citys,function (){  
		alert($(this).html());  
	});  
	//方法4：for...of  
	for(li of citys){  
		alert($(li).html());  
	}  
})  
</script>
```

***
#### 事件绑定
* jQuery标准的绑定方式
	* `jQuery对象.事件方法(回调函数)`；
	* 如果嗲用事件方法，不传递回调函数，则会出发浏览器默认行为。
		* `表单对象.submit()`；//让表单提交
		* 回调函数：`onclick()`, `mouseover()`, `mouseout()`, `focus()`
* on绑定事件/off解除绑定
	* `jQuery对象.on("事件名称",回调函数)`
	* `jQuery对象.off("事件名称")`
* 事件切换：toggle
	* `jQuery对象.toggle(fn1,fn2..)`
	* 当单击jQuery对象对应组件后，会执行fn1,再点击执行fn2，如果只有两个方法，第三次点击重复fn1。
***
#### 案例：
当页面加载完，3秒后，自动显示广告。
广告显示5秒后，自动消失。

* 分析：
	* 使用定时器来完成。`setTimeout`执行一次定时器
	* jQuery使用隐藏和显示控制display
	* 使用show/hide完成广告的显示


抽奖按钮，点击开始->停止按钮冷却无法点击，停止按钮->点击按钮无法点击。
* 分析：
	* 开始按钮绑定单击事件
		* 定义循环定时器`setInterval(回调函数,时间)`
		* 切换小相框的src属性
			* 定义数组，存放图片资源路径
			* 生成随机数，数组索引`Math.floor(Math.random()*7)`
	* 给结束按钮绑定单击事件
		* 停止计时器`clearInterval(startID)`
		* 给大相框设置src属性`$().prop(..)`
		* 按钮可用不可用`$().prop("disabled",false/true)`
	
	***
####	插件：增强jQuery的功能
	* 实现方式
		* `jQuery.fn.extend(object)`
			* 增强通过jQuery获取的对象的功能$`("#id")`
		* `jQuery.extend(object)`
			* 增强jQuery对象自身的功能 `$/jQuery`

实现方式1：全选、取消全选案例
```
<script>  
//使用jQuery插件个jq对象'$("#id")'添加2个方法，check()选中所有复选框，uncheck()取消选中所有复选框  

//定义jQuery的对象插件  
$.fn.extend({  
	//定义一个check()方法。所有的jq对象都可以调用该方法  
	check:function(){  
		//让复选框选中  
		//this:调用该方法的jq对象  
		this.prop("checked",true);  
	},  
	uncheck:function(){  
		//让复选框不选中  
		//this:调用该方法的jq对象  
		this.prop("checked",false);  
	}  
});  
  
//调用方法  
$(function(){  
	$("#btn-check").click(function(){  
		$("input[type='checkbox']").check();  
	});  
	$("#btn-uncheck").click(function(){  
		$("input[type='checkbox']").uncheck();  
	});  
});  
</script>
```
实现方式2：
```
<script>  
//对全局'$.XX'方法扩展2个方法，扩展min方法：求2搁置的最小值；扩展max方法：求两个值最大值  
  
$.extend({  
    max:function(a,b){  
        //返回两数中的较大值  
  return a>=b ? a:b;  
  },  
  min:function (a,b) {  
        //返回两数中的较小值  
  return a<=b ? a:b;  
  }  
})  
  
//调用全局方法  
var max = $.max(4,3);  
alert(max);  
var min = $.min(3,4);  
alert(min);  
</script>
```
