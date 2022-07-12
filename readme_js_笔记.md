# JavaScript

1. ## 概述

   1. Javascript是一种运行在客户端的*脚本语言*。
   2. 不需要编译，运行过程由js解释器（引擎）逐行进行解释并执行（转为机器语言）
   3. 现在也可以基于Node.js技术进行服务器端编程
   4. 浏览器执行JS，浏览器分为两部分
      1. 渲染引擎：用来解析HTML与CSS，俗称内核。
      2. JS引擎：也称为JS解释器。
   5. Javascript组成
      1. ECMAScript：js语法
      2. DOM：Document Object Model，通过DOM提供的接口可以对页面上的各种元素进行操作。
      3. BOM：Browser Object Model，浏览器对象模型，提供了独立于内容的、可以与浏览器窗口进行互动的结构对象。通过BOM可以操作浏览器窗口。

2. ## 变量

   1. 本质：在程序内存中申请的一块用来存放数据的空间。
   2. 声明变量：`var 变量名`
   3. 输入：`prompt("请输入")`
   4. 弹窗：`alert("警告")`
   5. 变量重新赋值后，原有的值会被覆盖。
   6. 变量只声明不赋值，结果是`undefined`
   7. 不声明不赋值，直接报错，并且显示`not defined`
   8. 不声明，直接赋值使用，不报错但是不推荐。

3. ## 数据类型

   1. JS是一种弱类型语言。不用提前声明变量类型，在程序运行过程中，类型会自动确定。

   2. 在JS中，以特定符号或格式规定的，创建指定类型变量的，不能被修改的便捷表达式。因为是表达式，字面量都有返回值。

      1. 比如：`null`等

   3. 数据类型是由JS引擎`根据 = 右边变量值的数据类型判断`。变量的类型是可变的（重新赋值）。

   4. JS整数共有四种字面量格式：十进制、二进制、八进制、十六进制。

   5. 具体类型

      1. 数字型
      2. 八进制：`0 ~ 7`数字前面加`0`，表示八进制。输出时自动转为十进制
      3. 十六进制：数字的前面加`0x`。`0 ~ 9 a ~ f`

   6. 数字型 

      1. 最大值与最小值：`Number.MAX_VALUE`、`Number.MIN_VALUE`
      2. 特殊值：`Infinity`无穷大、`-Infinity`无穷小、`NaN`非数值

   7. `isNaN`：判断非数字，如果时数字 返回false。

   8. `String`：字符串使用双引号或单引号。因为HTML使用双引号，JS推荐使用单引号。

   9. 转义字符：以`\`开头。

      1. 比如：`\n`，`\\`，`\'`，`\"`，`\t`，`\b`空格blank

   10. 字符串：

       1. 长度`length`：比如`'字符串长度'.length`
       2. 拼接：使用`+`。字符串 + 任何类型 = 拼接之后的新字符串。

       ```js
       var variable = undefined;
       console.log(variable + 'pink'); //underfinedpink
       console.log(variable + 1);//NaN
       var v = null;
       console.log(v + 'pink');//nullpink
       console.log(v + 1);//1
       console.log(v + true);//1
       ```

   11. Boolean：布尔型

       1. true的值为1，false的值为0.

       ```js
       var falg = true;
       console.log(flag + 1); //true 参与加法运算当1来看
       var v = underfined;
       console.log(v + flag);//NaN
       ```

   12. `typeof`：查看数据类型

   13. ```js
       var num = 10;
       console.log(typeof num); //number
       var str = 'pink';
       console.log(typeof str);//string
       var un = undefined;
       console.log(typeof un);//undefined
       var nu = null;
       console.log(typeof nu);//object
       ```

   14. 注意Console的输出字体颜色：

       1. 黑色表示字符型
       2. prompt获取到的值都是String
       3. 数字型蓝色
       4. undefined和null浅灰色

   15. 类型转换：**转为字符串**

       | 方式              | 说明                                             | 案例                                              |
       | ----------------- | ------------------------------------------------ | ------------------------------------------------- |
       | toString()        | 转成字符串                                       | var num=1; <br/>console.log( num.toString() );    |
       | String() 强制转换 | 转成字符串                                       | var num=1; <br/>console.log( String(num) )        |
       | 加号拼接字符串    | 和字符串拼接的结果都是字符串（也称呼为隐式转换） | var num=1; <br/>console.log( num + "我是字符串" ) |

   16. 类型转换：**转为数字型**

       | 方式                    | 说明                             | 案例                                   |
       | ----------------------- | -------------------------------- | -------------------------------------- |
       | parseInt(string) 函数   | 将String类型转成整数数值型       | parseInt('78')  <br/>parseInt('120px') |
       | parseFloat(string) 函数 | 将string类型转成浮点数数值型     | parseFloat('78.21')                    |
       | Number() 强制转换函数   | 将string类型转换为数值型         | Number('12')                           |
       | js隐式转换（- * /）     | 利用算数**运算**隐式转换为数值型 | '12' - 0                               |

   17. 类型转换：转为布尔型

       | 方式           | 说明                                                        | 案例             |
       | -------------- | ----------------------------------------------------------- | ---------------- |
       | Boolean() 函数 | 其他类型转成布尔值<br/>代表空、否定的值以及0会被转换为false | Boolean('ture'); |

4. ## 运算符

   1. 加、减、乘、除、取余`%`

      1. 取余：3%5 = 3； 5%3 = 2

   2. 浮点数相加，会有运算问题

      1. 比如：`consolelog(0.1+0.2)`为`0.3000004`
      2. 比如：`console.log((0.1+0.2) == 0.3)`为`false`

   3. 递增递减

      1. 递增和递减运算符必须和变量配合使用
      2. 前置：先加1，后返回值
         1. `++var` 类似 `var = var + 1`
      3. 后置：先返回原值，后加1
         1. `var a =10`然后，`console.log(age++ + 10)`得到20，`a=11`
      4. 运算符号
         1. `==`：判等号（会转型）
         2. `===`&`!==`：全等， 要求值和数据类型都一致
         3. `&&`：并且
         4. `||`：或
         5. `!`：非

   4. 逻辑与短路运算

      1. 如果表达式1 结果为真 则返回表达式2
         1. `console.log(123 && 456)`：456
         2. `console.log(0 && 456)`：0
      2. 如果表达式1为假 那么返回表达式1
         1. 如果有空或者否定的为假，其余的是真的

   5. 逻辑或短路运算

      1. 如果表达式1结果为真 则返回的是表达式1 如果表达式1结果为假 则返回表达式2

         1. `console.log(123 || 456)`： 123

         2. `console.log(0 || 456 || 456 + 123)`：456

         3. ```js
            var num = 0;
            console.log(123 || num++);
            console.log(num); //结果是0，因为逻辑中断，Num++没有运行
            ```

   6. 运算符优先级

      | 优先级 | 运算符     | 顺序          |
      | ------ | ---------- | ------------- |
      | 1      | 小括号     | ()            |
      | 2      | 一元运算符 | ++ -- !       |
      | 3      | 算术运算符 | 先* % 后+ -   |
      | 4      | 关系运算符 | > >= < <=     |
      | 5      | 相等运算符 | == != === !== |
      | 6      | 逻辑运算符 | 先&& 后\|\|   |
      | 7      | 赋值运算符 | =             |
      | 8      | 逗号运算符 | ,             |

5. ## 流程判断

   1. if..else...等
   2. 三元表达式
   3. switch
   4. 断点调试
      1. 浏览器F13 -> sources -> 找到需要调试的文件 -> 设置某一行断点
      2. Watch：监视，通过watch可以监视变量的值的变化
      3. F11：程序但不执行，让程序一行一行的执行，可以观察watch中变量的值的变化
   5. 关键字
      1. continue：跳出本次循环，继续下一次循环
      2. break：跳出整个循环

6. ## 数组Array

   1. 数组可以存放任意数据类型的元素
   2. 创建数组的方式
      1. 使用 new 创建空数组：`var arr = new Array();`
      2. 使用数组字面量创建数组：`var arr = ['小白','小黑',110];`
   3. 获取数组元素
      1. 索引 下标：索引值从0开始。
      2. 超出索引值：结果为 undefined
   4. 数组长度：`arr.length`
   5. 可以修改数组长度length：` arr.length = 5`
      1. 未使用的默认使用 undefined
   6. **冒泡排序 Bubble Sort**：
      1. 重复的走访要排序的数列，一次比较两个元素，如果顺序错误就交换过来。
      2. 两个for循环

7. ## 函数

   1. 声明函数 & 调用函数。

      ```js
      function 函数名(形参1, 形参2...){
          ...
          return xxx;//可选
      }
      ```

   2. 实参个数小于形参，该形参就是`underfined`。

   3. `return`既可以做 函数返回值，也可以做 中止函数。return只能返回一个值。

   4. `arguments`使用：

      1. 不确定多少个参数传递的时候使用

      2. 所有函数都内置一个arguments对象，对象中存储了传递的所有实参

      3. ```js
         function fn(){
             console.log(arguments);
         }
         fn(1,2,3);
         ```

      4. arguments展现形式是一个伪数组，因此可以遍历

         1. 具有length属性
         2. 按索引方式存储数据，可遍历
         3. **不具有**数组的push，pop等方法

8. ## 作用域

   1. 代码变量名在某个范围内起作用和效果
   2. 全局作用域：整个script标签或者是一个单独的js文件
   3. 局部作用域：只在函数内部起效果和作用
   4. 块级作用域（es6）：`{}`

9. ## 预解析

   1. 案例

      ```js
      //场景1
      console.log(abc); //输出错误 报红
      //场景2
      console.log(num); //输出Undefined，相当于下面的预解析--变量解析
      var num = 10; 
      //场景3
      fn();	//可以输出
      function fn(){ //相当于下面的函数预解析（提升）
          console.log(11);
      }
      //场景4
      fun2(); //输出错误 报红， 相当于下面的预解析--变量解析
      var fun2 = function(){
          console.log(22);
      }
      ```

   2. JavaScript解析器在运行JS代码的时候分为两步：预解析和代码执行。

      1. 预解析：js引擎会把js里面所有的var还有function提升到当前作用域的最前面
         1. 变量解析（变量提升）：
            1. 把所有的变量声明提升到当前的作用域最前面，不提升赋值操作。
            2. 函数内与变量解析情况一样，函数范围内 声明变量做预提升
         2. 函数预解析（函数提升）：与变量一样做提升。
      2. 代码执行：按照代码书写的顺序从上往下执行

   3. 特别案例：

      ```js
      var a = b = c = 9;
      //相当于 
      var a = 9; b = 9; c = 9; //b和c直接赋值,没有var 声明，当全局变量看
      
      //集体声明
      var a = 9, b = 9, c = 9;
      ```

10. ## JS对象

    1. 对象时由属性和方法组成的

    2. 创建对象

       1. 利用new Object创建空对象：

          ```js
          var obj = new Object();
          obj.name='张三丰';//属于追加方式添加属性
          obj.age=18;
          obj.sayHi= function(){
              console.log("hi");
          }
          ```

       2. 利用对象字面`{}`创建：

          ``` js
          var obj = {
              name:'张三丰',
              age:18,
              sayHi:function(){
                  console.log('hi');
              }
          };
          ```

    3. 使用对象

       1. `对象名.属性名`：比如 `obj.name`
       2. `对象名['属性名']`：比如 `obj['age']`
       3. `对象.方法名()`：比如 `obj.sayHi()`

    4. **构造函数**

       1. 利用函数的方法，重复相同的代码，就把这个函数称为 构造函数，又因为这个函数不一样，里面封装的是对象。构造函数 就是把对象里面一些相同的属性和方法抽象出来封装到函数里面。

       2. 构造函数语法

          ```js
          function 构造函数名(){
              this.属性 = 值;
              this.方法 = function(){}
          }
          new 构造函数名();
          //比如
          function Star(name, age, sex){ //1.构造函数名必须是大写
              this.name = name;
              this.age = age;
              this.sex = sex;
          }
          var ldh = new Star('刘德华',18, '男');//2.构造函数不需要return也可以返回结果
          console.log(ldh.name);		//3. 调用构造函数必须使用new 
          ```

       3. new关键字执行过程

          1. new构造函数可以在内存中创建一个空的对象
          2. this机会指向刚才创建的空对象
          3. 执行构造函数里面的代码，给这个空对象添加属性和方法
          4. 返回这个对象（相当于return）

    5. 遍历对象

       1. `for...in`：用于对数组或者对象的属性进行循环操作

          ```js
          for(var k in obj){
              console.log(k.name);
          }
          ```

11. ## JS内置对象

    1. JavaScript中分为3中对象：自定义对象、内置对象、浏览器对象。前两种js基础内容，属于ECMAScript，浏览器对象属于 JS独有。

    2. 内置对象：js语言自带的一些对象，提供了一些常用的或者是最基本的必要功能（属性和方法）。

    3. **JavaScript推荐API地址**，[火狐](https://developer.mozilla.org/en-US/) 。

    4. **Math**对象

       1. 不是一个函数对象即不是一个构造函数。不需要通过`new`来调用。

       2. 其所有的属性和方法都是**静态**的，直接使用。

       3. `Math.max(1,5,9)`, `Math.PI`, `Math(abs(-1))`

       4.  `Math.floor(2.2)`, `Math.ceil(3.2)`, `Math.round(4.4)`

       5. `Math.random()`随机浮点数`[0,1)` [火狐官方例子参考](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math/random)

          ```js
          function getRandomIntInclusive(min, max) {//取两个数之间一个随机整数，包含这两个数
            min = Math.ceil(min);
            max = Math.floor(max);
            return Math.floor(Math.random() * (max - min + 1) + min); //The maximum is inclusive and the minimum is inclusive
          }
          ```

    5. **Date**对象

       1. 只能通过调用 **Date构造函数** 来实例化日期对象。若常规调用（不加new 操作符）将会返回一个字符串，而不是日期对象。

       2. Date 对象没有字面量格式。

          ```js
          var date = new Date(); //当前日期
          var date = new Date(2019,11,29); //数字型用逗号隔开,返回的是10月而不是数字的11月。
          var date = new Date('2019-10-1 8:8:8')//字符串型
          ```

       3.  日期格式化：获取日期指定部分

          | 方法名        | 说明                                                         | 代码               |
          | ------------- | ------------------------------------------------------------ | ------------------ |
          | getFullYear() | 获取当年                                                     | dObj.getFullYear() |
          | getMonth()    | 获取当月0-11                                                 | dObj.getMonth()    |
          | getDate()     | 获取当天日期                                                 | dObj.getDate()     |
          | getDay()      | 获取星期几（周日0，到周六6）<br/>*数字切换到星期三这种使用自定义数组* | dObj.getDay()      |
          | getHours()    | 获取当前小时                                                 | dObj.getHours()    |
          | getMinutes()  | 获取当前分钟                                                 | dObj.getMinutes()  |
          | getSeconds()  | 获取当前秒钟                                                 | dObj.getSeconds()  |
          | ..            | ..                                                           | ..                 |

       4. 获得Date 的毫秒数（距离1970-1-1）

          | 方法名    | 说明       | 代码           |
          | --------- | ---------- | -------------- |
          | valueOf() | 获取毫秒值 | date.valueOf() |
          | getTime() | 获取毫秒值 | date.getTime() |

       5. 获得Date 的毫秒数另一种写法（常用写法）

          1. `var date = +new Date()`

       6. H5 获得Date 的毫秒数另一种写法

          1. `Date.now()`

       7. 案例：倒计时

          ```js
          //思路：使用时间戳相减，获得的毫秒数按公式除以小时/分钟/秒钟，使用parseInt转成整数
          function countDown(time) {
              var nowT = +new Date(); //获取当前时间
              var inputTime = +new Date(time); //获取要倒计时的时间
              var times = (inputTime - nowT) / 1000; //获取秒数
              var day = parseInt(times / 60 / 60 / 24); //天
              //一天是24小时,%24是为了去除整个天数,留下余的小时数
              var hours = parseInt(times / 60 / 60 % 24); //获取小时
              var minutes = parseInt(times / 60 % 60);//获取分钟数
              var s = parseInt(times % 60); //获取秒数
              return day + '天' + hours + '时' + minutes + '分' + s + '秒';
          }
          console.log(countDown('2022-07-13 18:00:00'));
          ```

    6. **Array**数组对象

       1. 数组对象的两种创建方式

          1. 字面量方式：`var arr = [1,2,3]`

          2. 构造函数 `new Array()`：

             ```js
             var arr = new Array() //空数组
             var arr = new Array(2) //表示初始化数组长度为2的空的数组
             var arr = new Array(2,3)//等价于[2,3]
             
             ```

       2. 检测是否为数组：`参数 instanceof Array` 和 `Array.isArray(参数)`

          ```js
          var arr = [];
          console.log(arr instanceof Array);
          //Array.isArray(XX) 是H5新增方法，ie9以上版本支持
          console.log(Array.isArray(arr));
          ```

       3. 添加或删除数组元素

          | 方法名            | 说明                                                   | 返回值               |
          | ----------------- | ------------------------------------------------------ | -------------------- |
          | push(参数1...)    | 末尾添加一个或多个元素，注意修改原数组                 | 并返回新的长度       |
          | pop()             | 删除数组最后一个元素，把数组长度减1 无参数、修改原数组 | 返回它删除的元素的值 |
          | unshift(参数1...) | 向数组的开头添加一个或多个更多元素，注意修改原数组     | 并返回新的长度       |
          | shift()           | 删除数组的第一个元素，数组长度减1 无参数、修改原数组   | 并返回第一个元素的值 |

       4. 

















