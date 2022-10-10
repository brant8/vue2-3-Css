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

    1. 对象是由属性和方法组成的

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

    3. **JavaScript推荐API地址**，[火狐](https://developer.mozilla.org/en-US/) 。API中的`[]`表示参数可以省略

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
          //也可以用来判断是否伪数组
          ```

       3. 添加或删除数组元素

          | 方法名            | 说明                                                        | 返回值               | 举例             |
          | ----------------- | ----------------------------------------------------------- | -------------------- | ---------------- |
          | push(参数1...)    | 末尾添加一个或多个元素<br/>注意修改原数组                   | 并返回新的长度       | arr.push(345)    |
          | pop()             | 删除数组最后一个元素，把数组长度减1 <br/>无参数、修改原数组 | 返回它删除的元素的值 | arr.pop()        |
          | unshift(参数1...) | 向数组的开头添加一个或多个更多元素<br/>注意修改原数组       | 并返回新的长度       | arr.unshift(123) |
          | shift()           | 删除数组的第一个元素，数组长度减1 <br/>无参数、修改原数组   | 并返回第一个元素的值 | arr.shift()      |

       4. 数组排序

          | 方法名    | 说明                             | 是否修改原数组                    | 举例          |
          | --------- | -------------------------------- | --------------------------------- | ------------- |
          | reverse() | 颠倒数组中元素的顺序，无参数     | 该方法会改变原来的数组 返回新数组 | arr.reverse() |
          | sort()    | 对数组的元素进行排序（冒泡排序） | 该方法会改变原来的数组 返回新数组 | arr.sort()    |

       5. 特别说明：

          ```js
          //sort()冒泡排序会有问题，比如下面无法正常排序
          var arr = [13,4,77,1,7]
          console.log(arr.sort()) //输出 [1, 13, 4, 7, 77]； 因为1和13按字典ASCII顺序排序，即字符串的字符规则排序
          
          //解决方法
          var arr[13,4,77,1,7];
          arr.sort(function(a,b){
              return a-b;//升序排序, b-a降序
          });
          console.log(arr);
          ```

       6. 数组索引方法

          | 方法名        | 说明                           | 返回值                                       | 举例                |
          | ------------- | ------------------------------ | -------------------------------------------- | ------------------- |
          | indexOf()     | 数组中查找给定元素的第一个元素 | 如果存在返回索引号<br/> 如果不存在，则返回-1 | arr.indexOf('blue') |
          | lastIndexOf() | 在数组中的最后一个索引         | 如果存在返回索引号 <br/>如果不存在，则返回-1 |                     |

       7. 案例：数组去重

          1. 核心算法：遍历旧数组，然后拿着旧数组元素取查询新数组。如果元素在新数组没有出现过就添加。

       8. 数组传唤为字符串

          | 方法名         | 说明                                                       | 返回值         | 举例           |
          | -------------- | ---------------------------------------------------------- | -------------- | -------------- |
          | toString()     | 把数组传换成字符串，逗号分隔每一项                         | 返回一个字符串 | arr.toString() |
          | join('分隔符') | 方法用于数组中的所有元素转换为一个字符串(默认使用逗号分隔) | 返回一个字符串 | arr.join('&')  |

       9. 其他方法

          | 方法名   | 说明                                   | 返回值                               |
          | -------- | -------------------------------------- | ------------------------------------ |
          | concat() | 连接两个或多个数组，不影响原数组       | 返回一个新的数组                     |
          | slice()  | 数组截取slice(begin, end)              | 返回被截取项目的新数组               |
          | splice() | 数组删除splice(第几个开始，要删除个数) | 返回被删除项目的新数组，会影响原数组 |

12. ## 基本类型包装

    1. 包装：对象才有属性和方法，或者复杂数据类型才有数据和方法

       1. `var str='hello';	console.log(str.length);`
       2. 简单数据类型为何有length属性

    2. 基本包装类型：把简单数据类型 包装成为了 复杂数据类型。包装步骤如下

       1. 把简单数据类型包装成为复杂数据类型
       2. `var temp = new String('hello')`
       3. 把临时变量的值给str `str = temp`
       4. 销毁这个临时变量 `temp = null`

    3. 为了方便操作基本数据类型，JS提供了三个特殊的引用类型：String、Number和Boolean

    4. **字符串String**不可变

       1. 值得是里面的值不可变，看上去内容改变，其实是地址变了，内存中新开辟了一个内存空间

       2. 因为字符串的不可变，所以不要大量的拼接字符串

       3. 字符串的所有方法，都不会修改字符串本身，操作完会返回一个新的字符串

          | 方法名                              | 说明                                                         |
          | ----------------------------------- | ------------------------------------------------------------ |
          | indexOf('要查找的字符', 开始的位置) | 返回指定内容在元字符串中的位置 <br />如果找不到就返回-1，开始的位置是index索引号 <br /> *如果多个相同元素，查找后从找到的位置作为开始位置* |
          | lastIndexOf()                       | 从后往前找，只找第一个匹配的                                 |

       4. 案例：判断字符出现位置和次数

          ```js
          //查找某个字符出现的位置以及次数
          var str = "19231091";
          var index = str.indexOf('9');
          var num = 0;
          while (index != -1) {
              console.log("---" + index + "----");
              num++;
              index = str.indexOf('9', index + 1);
          }
          ```

       5. 根据位置返回字符

          | 方法名            | 说明                                                         | 使用                                 |
          | ----------------- | ------------------------------------------------------------ | ------------------------------------ |
          | charAt(index)     | 返回指定位置的字符（index字符串的索引号）                    | str.charAt(0)                        |
          | charCodeAt(index) | 获取指定位置处字符的ASCII码（index索引号）<br/>目的：判断用户按下了哪个键(游戏开发) | str.charCodeAt(0)                    |
          | str[index]        | 获取指定位置处字符                                           | HTML5，IE8+支持 <br/>和chartAt()等效 |

       6. 案例：判断字符出现次数最多的字符，并统计其次数

          ```js
          //对象：{name:'zhangsan', age:18}
          var str = "19231091";
          var o = {}; //把每个字符都存储给对象，如果对象没有该属性就为1，如果存在了就+1
          for (var i = 0; i < str.length; i++) {
              var chars = str.charAt(i);
              if (o[chars]) { //o[chars]得到的是属性值
                  o[chars]++;
              } else {
                  o[chars] = 1; //给对象设置属性和赋值
              }
          }
          console.log(o);
          //遍历对象
          var max= 0;
          for(var k in o){ //k是属性名
              if(o[k] > max){
                  max=o[k];
              }
          }
          ```

       7. 字符串操作方法

          | 方法名                           | 说明                                                         |
          | -------------------------------- | ------------------------------------------------------------ |
          | concatenate(str1, str2, str3...) | contact()方法用于连接两个或多个字符串。拼接字符串，等效于+，+更常用 |
          | **substr(start, length)**        | 从start位置开始（索引号），length取的个数 **重点**           |
          | slice(start, end)                | 从start位置开始，截取到end位置，end娶不到（她两都是索引号）  |
          | substring(start, end)            | 从start位置开始，截取到end位置，end娶不到。基本和slice相同，但是不接受负值。 |

       8. 替换字符串

          | 方法名                                  | 说明                                         |
          | --------------------------------------- | -------------------------------------------- |
          | replace('被替换的字符', '替换为的字符') | 替换字符，只替换第一个字符                   |
          | split('分隔符')                         | 字符转换为数组，与join(把数组转为字符串)相反 |

       9. 其他方法：`toUpperCase()`， `toLowerCase()`

13. ## 数据类型内存分配

    1. 简单数据类型又称作基本数据类型/值类型，复杂类型又叫做引用类型。
    2. **基本类型**：在存储时变量中存储的是值本身，因此叫做值类型。
       1. string, number, boolean, undefined, null
       2. `console.log(null)`返回的是空的对象， 即空object
       3. **小知识**：若以后又打算用变量存储为对象，没想好放啥，使用null。如`var ob=null`
    3. **引用类型**：在存储变量中存储的仅仅是地址（引用），因此叫做引用数据类型。
       1. 通过new 关键字创建的对象（系统对象、自定义对象），如Object、Array、Date等。
    4. **堆栈空间分配区别**
       1. 栈（操作系统）：又操作系统自动分配释放存放函数的参数值、局部变量的值等。其操作方式类似数据结构中的栈；**简单数据类型存放到栈里面**。
       2. 堆（操作系统）：存储复杂类型（对象），一般由程序员分配释放，若程序员不是放，由垃圾回收机制回收；**复杂数据类型存放到堆里面**。
    5. Javascript时机是没有堆栈概念，知识便于理解代码执行方式以及学习其他语言。
       1. 栈：存放简单数据类型的值、引用类型的地址值
       2. 堆：存放引用类型的值
    6. 简单类型传参：
       1. 函数的形参也可以看作是一个变量，当把一个值类型变量作为参数传给函数的形参时，其实是把变量在栈空间里的值复制了一份给形参，那么在方法内部堆形参做任何修改，都不会影响到外部变量。
    7. 复杂类型传参
       1. 函数的形参也可以看作是一个变量，当我们把引用类型变量传给形参时，其实是把变量在栈空间里保存的堆地址复制给了形参，形参和实参其实保存的是同一个地址，所以操作的是同一个对象。

14. ## Web APIs

    1. JS 基础阶段 和 Web APIs 阶段	
       1. JS基础阶段
          1. 学习ECMAScript标准规定的基本语法
          2. 掌握JS基础语法
          3. 基础语法无法做到与网页交互
       2. Web APIs阶段
          1. 是W3C组织的标准
          2. 学习DOM 和 BOM
          3. Web APIs是JS所独有的部分
          4. 学习页面交互功能

15. ## DOM

    1. Document Object Model：文档对象模型，是W3C组织推荐的处理可扩展标记语言HTML/XML的标准编程接口。

    2. DOM可以改变页面的内容、结构、样式。

    3. DOM树

       1. **文档 Document**：一个页面就是一个文档，DOM中使用**document**表示。

          ![DOM树图](https://github.com/brant8/vue2-3-Css/blob/main/pictures/dom_tree.png)

       2. **元素 Element**：页面中所有的标签都是元素，DOM中使用**element**表示。

       3. **节点 Node**：网页中那个所有内容都是节点（标签、属性、文本、注释等），DOM中使用**node**表示。

       4. DOM把以上内容都看作是对象

    4. DOM主要是用来操作元素的。[API可查看火狐](https://developer.mozilla.org/zh-CN/docs/Web/API/Document/getElementById)。

    5. **根据ID**获取 `getElementById()`

       1. ```html
          <div id="time">我是帅哥哥</div>
          <script>
          	var timer = document.getElementById('time');
              console.log(timer); //<div id="time">我是帅哥哥</div>
              console.log(typeof timer); // object
              console.dir(timer); //打印返回的元素对象，更好的查看里面的属性和方法
          </script>
          ```

       2. 注意：文档页面从上往下加载，所以`<script>`要写在标签之后

       3. 返回结果：是一个元素对象。

       4. 使用`console.dir()`查看对象属性和方法

    6. **根据标签名**获取 `getElementsByTagName()`

       1. ```html
          <ul>
              <li>哇啦啦1</li>
              <li>哇啦啦2</li>
              <li>哇啦啦3</li>
              <li>哇啦啦4</li>
          </ul>
          <ol>
              <li>1</li>
              <li>2</li>
          </ol>
          <script>
              var lis = document.getElementsByTagName('li');
              console.log(lis);
              for (var i = 0; i < lis.length; i++) { //可便利伪数组
                  console.log(lis[i]);
              }
              
              var ol = document.getElementsByTagName('ol');//得到 -> [ol]
              //element.getElementsByTagName()形式
              console.log(ol[0].getElementsByTagName('li'));
          </script>
          ```

       2. 返回结果：获得元素对象的集合，以伪数组的形式存储的。若没有该元素，返回空伪数组`[]`

       3. 可以使用索引方式遍历伪数组

       4. 得到的元素是动态的，即HTML内容变化， JS代码不变。

       5. 可以通过`element.getElementsByTagName()`获取某个父元素（单个对象）内部的子元素，获取的时候不包括父元素自己。

    7. 通过 HTML5 新增的方法获取

       1. **类名**：`document.getElementsByClassName('类名')` 根据类名返回元素对象集合

       2. **选择器（通用）**：`document.querySelector('')` 返回指定选择器（id、class、标签）的*第一个*元素对象。`document.querySelectorAll('')`获取全部

          ```js
          //返回第一个元素对象
          var _id = document.querySelector('#box');
          var _class = document.querySelector('.nav');
          var _tag = document.querySelector('li');
          //返回指定选择器，返回所有元素对象
          var _allTag = document.querySelectorAll('li'); 
          ```

    8. **特殊元素**获取

       1. 获取body标签元素对象：`document.body`
       2. 获取html标签元素对象：`document.documentElement`

16. ## 事件基础

    1. JavaScript使我们有能耐创建动态页面，事件是可以被JavaScript侦测到的行为。网页中的每个元素都可以产生某些可以触发JS的事件。

    2. 事件由三部分组成：

       1. 事件源：事件被触发的对象。
          1. 使用获取元素方式获取事件源，如`getElementById('btn')`。
       2. 事件类型：以何种方式触发，比如鼠标点击onclick、鼠标经过、键盘按下等。
       3. 事件处理程序：通过一个函数赋值的方式完成
          1. 比如 `btn.onclick=function(){...}`

    3. 常见鼠标事件

       | 鼠标事件   | 触发事件         | 鼠标事件    | 触发事件         |
       | ---------- | ---------------- | ----------- | ---------------- |
       | onclick    | 鼠标点击左键触发 | onmouseover | 鼠标经过触发     |
       | onmouseout | 鼠标离开触发     | onfocus     | 获得鼠标焦点触发 |
       | onblur     | 失去鼠标焦点触发 | onmousemove | 鼠标移动触发     |
       | onmouseup  | 鼠标弹起触发     | onmousedown | 鼠标按下触发     |

17. ## 操作元素

    1. **改变元素内容**

       1. `element.innerText`：从起始位置到终止位置的内容，但它去除html标签，同时空格和换行也会去掉。
       2. `element.innerHTML`：其实位置到终止位置的全部内容，包括html标签，同时保留空格和换行。
       3. innerText与innerHTML的区别：
          1. innerText 不能识别html标签，并且读取后去除了空格和换行，非标准。
          2. innerHTML 识别html标签，读取后保留空格和换行，是W3C标准。
       4. innerText 与innerHTML 两个属性都是可**读写**的，可以获得元素里面的内容。

       ```html
       <button>显示当前系统时间</button>
       <div class="content">某个时间</div>
       <p>我是p标签</p>
       <script>
           //1.获取元素
           var btn = document.querySelector('button');
           var div = document.querySelector('.content');
           console.log(div);
           //2.注册事件
           btn.onclick = function() {
               //div.innerText = '被更换的内容：就是酱紫';
               div.innerText = getDate();
           }
       
           function getDate() {
               var date = new Date();
               var year = date.getFullYear();
               var month = date.getMonth() + 1;
               var dates = date.getDate();
               var arr = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
               var day = date.getDay();
               return "今天是： " + year + "年" + month + "月" + dates + "日  " + arr[day];
           }
           //元素可以不用添加事件 ，页面加载完直接修改
           var p = document.querySelector('p');
           p.innerText = getDate(); 
       </script>
       <p>
           我是文字
           <span>123</span>
       </p>
       <script>
       	var div = document.querySelector('div');
           div.innerHTML = '<strong>今天是：</strong> 2019';
           var p = document.querySelector('p');
           console.log(p.innerText);//读取
           console.log(p.innerHTML);
       </script>
       ```

    2. **常用元素的属性操作**

       1. innerText、innerHTML：改变元素内容

       2. src、href、id、alt、title：属性操作

          ```html
          <button id="ldh">刘德华</button>
          <button id="zxy">张学友</button>
          <img src="images/ldh.jpg" alt="">
          <script>
              //修改元素 属性 src ... 切换图片
              //1.获得元素
              var ldh = document.getElementById('ldh');
              var zxy = document.getElementById('zxy');
              var img = document.querySelector('img');
              //2.注册事件 处理程序
              zxy.onclick = function() {
                  img.src = 'images/zxy.jpg'
              }
          </script>
          ```

    3. **表单元素的属性操作**

       1. 利用DOM可以操作如下表单元素的属性

          1. type、value、checked、selected、disabled

       2. 表单里面的值 文字内容是通过 value 来修改的，innerHTML不可改。

          ```html
          <button id="ldh">刘德华</button>
          <input type="text" value="输入内容">
          <script>
              //1.获取元素
              var btn = document.querySelector('button');
              var input = document.querySelector('input');
              //2.注册事件 处理程序
              btn.onclick = function() {
                  //input.innerHTML = '点击了我';  -> 这个是普通盒子才有的，比如div标签里面的内容才能更改
                  //表单里面的值 文字内容是通过value来修改的
                  input.value = "被点击了";
                  btn.disabled = true; //点击后 禁用
                  this.disabled = true; //与上面btn.disabled等效
                  //this 指向的是事件函数的调用者 即btn自身 ->[btn].onclick调用了函数
              }
          </script>
          ```

       3. 案例分析：登录框按钮有眼睛按钮，点击可切换明文显示密码，[详细代码](https://github.com/brant8/vue2-3-Css/blob/main/js%E9%BB%91%E9%A9%AC%E4%BB%A3%E7%A0%81/004Demo_login1.html)

          ```js
          //1.获取元素
          var eye = document.getElementById('eye');
          var pwd = document.getElementById('pwd');
          //2.注册事件 处理程序
          var flag=0;
          eye.onclick = function () {
              if(flag==0) {
                  pwd.type = 'text';
                  eye.src='https://via.placeholder.com/15'; //打开眼睛的图片
                  flag=1;
              }else{
                  pwd.type='password';
                  eye.src='https://via.placeholder.com/15';//闭眼的图片
                  flag=0;
              }
          }
          ```

    4. **样式属性操作**

       1. 通过 js 修改元素的大小、颜色、位置等样式

       2. 行内样式操作：`element.style` (当修改的样式较少时使用)

          ```js
          //1.获取元素
          var div = document.querySelector('div');
          //2. 注册事件 处理程序
          div.onclick = function(){
              //div.style 里面的属性 采取驼峰命名法
              this.style.backgroundColor = 'purple';
              this.style.width = '250px';
          }
          /*
          JS修改style样式操作，产生的时行内样式，CSS权重比较高
          */
          ```

       3. 类名样式操作：`element.className`

          1. 现在css中预设要更改成什么样的样式，然后js点击事件进行类切换

          2. `class`因为是个保留字，因此使用`className`来操作元素类名属性。

          3. `className`会直接更改元素的类名，会覆盖原先的类名。

          4. 适用于样式较多或者功能复杂的情况

             ```html
             <style>
                 div{
                     height: 50px;
                     width:50px;
                     background-color: red;
                 }
                 .change{
                     background-color: purple;
                     color:#ffffff;
                     font-size:25px;
                 }
             </style>
             
             <div>文本</div>
             <!--如果原来有class，则js会覆盖原来的class名-->
             <div class='first'>文本2</div> 
             <script>
                 var test = document.querySelector('div');
                 test.onclick = function () {
                     //相当于<div class='change'>..
                     this.className = 'change'; //会覆盖原来的类名
                     this.className = 'first change';//保留原来的first
                 }
             </script>
             ```

18. ## 排他思想（操作元素）

    1. 排他思想案例

       ```js
       <button>按钮1</button>
       <button>按钮2</button>
       <button>按钮3</button>
       <button>按钮4</button>
       <button>按钮5</button>
       
       <script>
           //1.获取所有按钮元素
           var btns = document.getElementsByTagName('button');
       //btns得到的时伪数组
       for (let i = 0; i < btns.length; i++) {
           btns[i].onclick = function(){
               console.log(this.innerHTML);
               //2.每次切换后的背景颜色会变，所以先把所有的按钮背景颜色去掉
               for (let j = 0; j < btns.length; j++) {
                   btns[j].style.backgroundColor='';
               }
               //3.然后才让当前的背景颜色变色为pink
               this.style.backgroundColor='pink';
           }
       }
       </script>
       ```

    2. 如果有同一组元素，我们想要某一个元素实现某种样式，需要用到循环的**排他思想**：

       1. 所有元素全部清除样式（干掉其他人）
       2. 给当前元素设置样式（留下我自己）
       3. 注意顺序不能颠倒，首先干掉其他人，再设置自己。

    3. **排他思想**：首先排除他人，然后才设置自己的样式。

    4. 其他案例：换皮肤

    5. ```html
       <ul>
           <li><img src="images/1.jpg"></li>
           <li><img src="images/2.jpg"></li>
           <li><img src="images/3.jpg"></li>
           <li><img src="images/4.jpg"></li>
       </ul>
       <script>
           //1.获取标签内所有元素
           var imgs = document.querySelector('.baidu').querySelectorAll('img');
           //console.log(imgs);
           for(var i=0; i<imgs.length;i++){
               imgs[i].onclick=function(){
                   document.body.style.backgroundImage = 'url('+this.src+')';
               }
           }
       </script>
       ```

    6. 案例：选择框的全选

       ```html
       <div class="wrap">
           <table>
               <thead>
                   <tr>
                       <th>
                           <input type="checkbox" id="j_cbAll">
                       </th>
                       <th>商品</th>
                       <th>价格</th>
                   </tr>
               </thead>
               <tbody id="j_tb">
                   <tr>
                       <td>
                           <input type="checkbox">
                       </td>
                       <td>Iphone3</td>
                       <td>5000</td>
                   </tr>
                   <tr>
                       <td>
                           <input type="checkbox">
                       </td>
                       <td>Iphone4</td>
                       <td>8000</td>
                   </tr>
                   <tr>
                       <td>
                           <input type="checkbox">
                       </td>
                       <td>Iphone5</td>
                       <td>12000</td>
                   </tr>
                   <tr>
                       <td>
                           <input type="checkbox">
                       </td>
                       <td>Iphone6</td>
                       <td>14000</td>
                   </tr>
               </tbody>
           </table>
       </div>
       <script>
           //1.全选和取消全选做法：让下面所有复选框的checked属性（选中状态） 跟随全选按钮即可
           var j_cbAll = document.getElementById('j_cbAll');//全选按钮
           var j_tbs = document.getElementById('j_tb').getElementsByTagName('input');
           console.log(j_cbAll);
           console.log(j_tbs);
           //2.注册事件
           j_cbAll.onclick = function(){
               console.log(this.checked);
               for(var i=0; i<j_tbs.length;i++){
                   j_tbs[i].checked=this.checked;//总勾选框 - 取消全选和全选
               }
           }
           //下面复选框需要全部选中，上面的全选才能选中
           for (let i = 0; i < j_tbs.length; i++) {
               j_tbs[i].onclick = function(){
                   var flag = true; //控制全选按钮是否选中
                   //每次点击下面的复选框都要循环检查4个小按钮是否被选中
                   for (let j = 0; j < j_tbs.length; j++) {
                       if(!j_tbs[j].checked){  //取反
                          //如果点击了取反就是false，没有点击默认是false取反就是true，全选就会赋值下面的为false。
                           flag = false;
                           break;//退出for循环，提高执行效率，因为只要有一个没有选中，剩下的就是无意义循环
                       }
                   }
                   j_cbAll.checked = flag;
               }
           }
       </script>
       ```

19. ## 自定义属性的操作

    1. 获取属性值

       1. `element.属性` ：获取属性值。获取**内置属性值**（元素本身自带的属性）。

       2. `element.getAttribute('属性')`。获得**自定义的属性**（标准）。

       3. ```html
          <div id="demo" index="1" class="test"></div>
          <script>
            //方式一
            var div=document.querySelector('div');
            console.log(div.valueOf()) //获得 <div id="demo"></div>
            console.log(div.id); //获得 demo
            //方式二
            console.log(div.getAttribute('id'));//获得 demo
            console.log(div.getAttribute('index'));//获得自定义属性的值 
          </script>
          ```

    2. 设置属性值

       1. `element.属性 = '值'`：设置内置属性值

       2. `element.setAttribute('属性','值')`：主要针对自定义属性值

       3. ```html
          <script>
          div.id='test';
          div.className='navs';
          //
          div.setAttribute('index',2);
          div.setAttribte('class', 'footer'); //class比较特殊，不是className了。
          </script>
          ```

    3. 移除属性

       1. `element.removeAttribute('属性')`

    4. 案例：tab栏切换

       1. 当鼠标点击上面相应的选项卡tab，下面内容跟随变化。
       2. Tab大模块分两个模块：`<li>`导航模块，和`<div>`内容模块
       3. for循环绑定点击事件，排他思想 -> 双层循环，内层循环清空，外层循环赋值
       4. 核心思路：循环内用 js 给tab_list即`<li>`添加自定义属性，属性值从0开始编号，setAttribute

    5. H5自定义属性

       1. 目的：为了保存并使用数据，有些数据可以保存到页面中而不用保存到数据库中。

       2. 自定义获取是通过`getAttribute('属性')`,但是有时候无法判断是否是内置属性还是自定义属性。

       3. 规范：H5规定自定义属性`data-`开头作为属性名并且赋值

          1. 比如：`<div data-index='1'></div>`

       4. H5新增`element.dataset.属性`或者`element.dataset['属性']`获取自定义属性值

       5. ```html
          <div getTime="20" data-index="2" data-list-name="andy"></div>
          <script>
          	var div = document.querySelector('div');
              console.log(div.getTime); //与内置方法getTime冲突，实际调用内置方法
              console.log(div.getAttribute('getTime')) //可以正常显示
              div.setAttribute('data-time',20);
              console.log(div.getAttribute('data-index'));
              //h5新增获取自定义方法（IE10+） dataset是一个集合，存放了所有以data开头的自定义属性
              console.log(div.datadset.index);//不需要用data-index
              console.log(div.dataset['index']);//与上面结果一样
              console.log(div.dataset['listName'])//多个需要驼峰
          </script>
          ```

20. ## 节点操作

    1. 利用DOM提供方法获取元素

       1. `document.getElementById()`、`document.getElementByTagName()`、`document.querySelectior()`等
       2. 缺点：逻辑性不强，繁琐。多数情况下需要先获取父级等才能获得子级别

    2. 利用节点层级关系获取元素

       1. 利用父子兄节点关系获取元素，比如`<div><img></div>`
       2. 逻辑性强，但是兼容性稍差

    3. **节点**：网页中的所有内容都是节点（标签、属性、文本、注释等），再DOM中，节点使用node来表示。

    4. HTML DOM树中的所有节点均可通过JavaScript进行访问，所有HTML（节点）均可被修改，也可以创建或删除。

    5. **节点 三个基本属性**：

       1. `nodeType`节点类型：1，主要针对标签
       2. `nodeName`节点名称：2
       3. `nodeValue`节点值 ：3（文本节点包含文字、空格、换行等）

    6. 节点操作主要操作的是元素节点，即 nodeType

    7. **父级节点**：`node.parentNode`

       1. parentNode属性可返回某节点的父节点，注意是最近的一个父节点
       2. 如果指定的节点没有父节点则返回null

       ```html
       <div class="box">
           <span class="QRcode"></span>
       </div>
       <script>
       	//1.父节点 parentNode
           var qrcode=document.querySelector('.QRcode');
           var box = document.querySelector('.box');
           //得到的是离元素最近的父级节点，如果找不到父节点就返回null
           console.log(qrcode.parentNode);
       </script>
       ```

    8. **子节点**：`node.childNodes` (标准)

       1. childNodes返回包含指定节点的子节点的集合，该集合为即时更新的集合。
       2. 如果只想要获得里面的元素节点，需要专门处理，所以一般不提倡使用 childNodes。

       ```html
       <ul>
           <li>1</li>
           <li>2</li>
           <li>3</li>
       </ul>
       <script>
           //DOM方式
       	var ul=document.querySelector('ul');
       	var lis = ul.querySelectorAll('li');
           //子节点 所有的子节点，包含 元素节点 文本节点等 
           console.log(ul.childNodes); //结果是[text, li, text, li, text, li, text]
           console.log(ul.childNodes[0].nodeType);//输出：3
           console.log(ul.childNodes[1].nodeType);//输出：1
           //获得元素节点
           for(var i=0;i<ul.childNodes.length; i++){
               if(ul.childNodes[i].nodeType ==1){
                   //ul.childNodes[i]是元素节点
                   console.log(ul.childNodes[i]);
               }
           }
       </script>
       ```

    9. **子节点**：`node.children` (非标准)

       1. children是一个只读属性，返回所有的子元素节点。只返回子元素节点，其余节点不返回。
       2. 虽然是非标准，但是得到了各个浏览器的支持。

       ```js
       console.log(ul.children); //返回结果集（伪数组）: [li, li, li]
       ////解决firstElementChild兼容性问题
       console.log(ul.children[0]);//第一个元素
       console.log(ul.children[ul.children.length-1]);//最后一个元素
       ```

    10. **其他子节点**：

        1. `node.firstChild`：返回第一个节点，找不到返回null。同样，包含所有节点。
        2. `node.lastChild`：返回最后一个节点，找不到返回null。同样，包含所有节点。
        3. `node.firstElementChild`：返回第一个子元素节点。IE9+才支持。
        4. `node.lastElementChild`：返回最后一个元素节点。IE9+才支持。

    11. 案例分析：导航栏显示二级导航

        1. 导航代码`<nav><ul><li><ul><li></li></ul></li></ul>`其中外层`<ul>`为导航，内层`<ul>`为二级导航。
        2. 先获取外层导航的元素集合，可使用`lis = nav.children`获得，再遍历每个元素，当`onmouseover`时的触发事件。

    12. **兄弟节点**：`node.nextSibling`

        1. 返回当前元素的下一个兄弟节点，找不到返回null。包含元素节点、文本节点等。
        2. 若返回结果时`#text`表示换行的文本节点

    13. **兄弟节点**：`node.previousSibling`

        1. 返回当前元素的上一个兄弟节点，找不到返回null。包含元素节点、文本几点等。

    14. **兄弟节点**：`node.nextElementSibling` 。**IE9+才支持**。

        1. 返回当前元素下一个兄弟元素节点，找不到则返回null。

    15. **兄弟节点**：`node.previousElementSibling`。**IE9+才支持**。

        1. 返回当前元素上一个兄弟元素节点，找不到则返回null。

    16. 解决兼容性问题：

        ```html
        <script>
            //自行封装一个方法
        	function getNextElementSibling(element){
                var el = element;
                while(el = el.nextSibling){//获取所有节点然后循环
                    if(el.nodeType ===1){
                        return el;
                    }
                }
                return null;
            }
        </script>
        ```

    17. **创建节点**：`document.createElement('tagName')`

        1. 比如文章评论，在没有评论情况下进行评论，产生页面新节点。

    18. **添加节点**：`node.appendChild(child)`

        1. 将一个节点添加到指定父节点的子节点列表**末尾**。类似于css里面的**after伪元素**。

           ```js
           //1.创建节点元素节点
           var li = document.createElement('li');
           //2.添加创建好的节点
           var ul = document.querySelector('ul');//父节点
           ul.appendChild(li);
           ```

    19. **添加节点**：`node.insertBefore(child,指定元素)`

    20. 案例：添加评论/留言发布

        ```html
        <textarea name="" id="">123</textarea>
        <button>发布</button>
        <ul>
        
        </ul>
        <script>
            //1.获取元素
            var btn = document.querySelector('button');
            var text=document.querySelector('textarea');
            var ul=document.querySelector('ul');
            //2.注册事件
            btn.onclick=function() {
                if (text.value == '') {
                    console.log("没有输入东西");
                    return false;
                } else {
                    console.log(text.value);//获得输入的内容
                    //1.创建元素
                    var li = document.createElement('li');
                    //先有li才赋值
                    li.innerHTML=text.value + "<a href='javascript:void(0)'>删除</a>";
                    //2.1添加元素到末尾
                    //ul.append(li);
                    //2.2添加元素到开头，即原先第一个的前面
                    ul.insertBefore(li,ul.children[0]);
                    //3.添加删除键后删除元素，删除的时当前链接的li 其父元素
                    var as = document.querySelectorAll('a');
                    for (let i = 0; i < as.length; i++) {
                        as[i].onclick = function(){
                            //node.removeChild(child)删除的是li， 当前a所在的li。<li><a></li>
                            ul.removeChild(this.parentNode);
                        }
                    }
                }
            }
        </script>
        ```

    21. **删除节点**：`node.removeChild(child)`

        1. 从DOM中删除一个子节点，返回删除的节点。

    22. **阻止链接跳转**：（链接跳转表现在地址栏有变化比如点击完后xxx#）

        1. `<a href=''>`添加`javascript:void(0)`或者`javascript:;`

    23. **复制节点**：`node.cloneNode()`

        1. 返回调用该方法的节点的一个副本，也成为克隆节点。
        2. 如果括号参数为*空或者false*，则是浅拷贝，值克隆复制节点本身，不克隆里面的子节点（内容）。
        3. 比如`ul.children[0].cloneNode(true)` 为深度拷贝。
        4. 克隆完后与创建节点一样，需要添加节点。

21. ## 动态创建元素

    1. `document.write()`：是直接将内容写入页面的内容流，但是文档流执行完毕，则会导致页面全部重绘。
       1. 比如：`document.write(<div>123</div>)`
       2. 页面重绘：相当于创建了一个新的页面，旧页面DOM的标签都会消失。
       3. `window.onload = function(){}` 表示页面加载完毕后运行
    2. `element.innerHTML`
       1. 添加多个内容使用 '+' 拼接时，耗时久
       2. 使用数组循环`push`添加内容，再使用数组方法`join()`合并成字符串一次性复制给innerHTML，耗时更少。
       3. 将内容写入某个DOM节点，不会导致页面全部重绘。
    3. `document.createElement()`
       1. 创建多个元素时，耗时稍少，innerHTML使用数组时最佳。
    4. 总结：不同浏览器下，innerHTML效率比createElement高。

22. ## DOM操作推荐（总结）

    1. 查：获取查询dom元素
       1. DOM提供API方法：getElementById、getElementsByTagName。 古老用法 不推荐
       2. H5提供的新方法：querySelector、querySelectorAll 。 提倡
       3. 利用节点操作获取元素：父 parentNode、 子 children、兄previousElementSibling/nextElementSibling。 提倡
    2. 属性操作：主要针对自定义属性
       1. setAttribute：设置dom属性值
       2. getAttribute：得到dom的属性值
       3. removeAttribute 移除属性
    3. 事件操作：
       1. 给元素注册事件，事件源.事件类型 = 事件处理程序
       2. onclick、onmouseover等。

23. ## 事件高级

    1. **注册事件**

       1. 给元素添加事件，成为注册事件或者绑定事件。
       2. 注册事件有两种方式：*传统方式和方法监听方式*

    2. **传统注册方式**

       1. 利用on开头的事件 onclick
       2. `<button onclick = "alert('hi~')"></button>`
       3. `btn.onclick=function(){}`
       4. **特点**：注册事件的唯一性
       5. 同一个元素同一个事件只能设置一个处理函数，最后注册的处理函数将会**覆盖**前面注册的处理函数。

    3. **方法监听注册方式**

       1. w3c标准，推荐方式
       2. `addEventListener()` 是一个方法
       3. IE9之前不支持此方法，可使用`attachEvent()`替代
       4. **特点**：同一个元素同一个事件可以注册多个监听器。
       5. 按注册顺序依次执行

    4. **addEventListener** 注册事件*/*绑定事件

       1. ```js
          eventTarget.addEventListener(type, listener[, useCapture])
          ```

       2. **参数 type**：事件类型字符串，比如 click、mouseover，注意这里不要带 on。

       3. **参数 listener**：事件处理函数，事件发生时，会调用该监听函数

       4. **参数 useCapture**：可选参数，是一个布尔值，默认时false。

       5. `eventTarget.addEventListener()`方法将指定的监听器注册到eventTarget(目标对象)上，当该对象触发指定的事件时，就会执行事件处理函数。

       6. ```js
          var btns=document.querySelectorAll('button');
          btns[1].addEventListener('click',function(){
            alert(22);  
          }) 
          //同一个元素 同一个事件可以添加多个监听器（事件处理程序）
          btns[1].addEventListener('click',function(){
            alert(23);  
          })
          ```

    5. **attachEvent** 事件监听方式**(**IE9以前的识别，非标准，尽量不使用**)**

       1. ```js
          eventTarget.attachEvent(eventNameWithOn, callback)
          ```

       2. **参数 eventNameWithOn**：事件类型字符串，比如 onclick、onmouseover，这里要**带on**。

       3. **参数 callback**：事件处理函数，当目标触发事件时回调函数被调用。

       4. `eventTarget.attachEvent()`方法将指定的监听器注册到eventTarget(目标对象)上，当该对象触发指定的事件时，指定的回调函数就会被执行。

       5. ```js
          btns[2].attachEvent('onclick',function(){
              alert(11)
          })
          ```

    6. 注册事件兼容性解决不同浏览器

       ```js
       function addEventListener(element, eventName, fn){
           //判断当前浏览器是否支持 addEventListener方法
           if(element.addEventListener){
               element.addEventListener(eventName, fn);//第三个参数 默认是false
           }else if(element.attachEvent){
               element.attachEvent('on'+eventName,fn);
           }else{
               //相当于element.onclick = fn;
               element['on' + eventName] = fn;
           }
       }//兼容性原则：先照顾大多数浏览器，再处理特殊浏览器
       ```

    7. **删除事件的方式**

       1. 传统注册方式：

          ```js
          var divs = document.querySelectorAll('div');
          divs[0].onclick=function(){
              alert(11);
              //点击一次后不想让其点击后再弹出窗口，解除事件绑定
              //eventTarget.onclick = null;
              divs[0].onclick=null;
          }
          ```

       2. 方法监听注册方式

          ```js
          var divs = document.querySelectorAll('div');
          /**
          divs[1].addEventListener('click',function(){ //若要解除绑定，此处不能用匿名函数
              alert(22);
              divs[1].removeEventListener('click', /*失败*/)
          })
          */
          //正确做法 点击后再次点击不弹窗
          divs[1].addEventListener('click',fn) //里面的fn不需要调用 加小括号
          function fn(){
              alert(22);
              divs[1].removeEventListener('click',fn); //解除事件绑定
          }
          divs[2].attachEvent('onclick',fn1);
          function fn1(){
              alert(33);
              divs[2].detachEvent('onclick',fn1);
          }
          ```

24. ## DOM事件流

    1. 事件流描述的是从页面中接收事件的顺序。

    2. 事件发生时会在元素节点之间按照特定的顺序传播，这个传播过程即DOM事件流。

    3. DOM事件流三个阶段 [图示](https://github.com/brant8/vue2-3-Css/blob/main/pictures/javascript_eventflow.png)：

       1. 捕获阶段： document -> html -> body -> 当前阶段
       2. 当前目标阶段
       3. 冒泡阶段： 当前阶段 -> body -> html -> document

    4. **注意事项**：

       1. JS 代码中只能执行捕获或者冒泡其中的一个阶段
       2. onclick 和 attachEvent 只能得到冒泡阶段
       3. addEventListener(type, listener[, userCapture]) 第三个参数如果是true，表示在事件捕获阶段调用事件处理程序；如果是false（不写默认就是false），表示在事件冒泡阶段用事件处理函数。

    5. ```html
       <div class="father">
           <div class="son">son盒子</div>
       </div>
       <script>
       	//捕获阶段，如果第三个参数是true， 
           //那么捕获阶段 弹框顺序： document -> html -> body -> father -> son
           var son = document.querySelector('.son');
           son.addEventListener('click',function(){
               alert('son');
               e.stopPropagation();//阻止冒泡事件(标准)
               e.cancelBubble = true;//阻止冒泡事件（非标准）
           },true);
          // 捕获阶段 第三个参数是true，
           //document -> html -> body -> father,  点击son也会有弹窗
           var father = document.querySelector('.father');
           father.addEventListener('click',function(){
               alert('father');
           },true);
           //冒泡阶段， addEventListener 第三个参数是false 或者省略，那么处于冒泡阶段
           //弹框顺序：son -> father -> body -> html -> document
           var son = document.querySelector('.son');
           son.addEventListener('click',function(){ //点击son father也会一起弹出来
               alert('son');
           },false);
           var father = document.querySelector('.father');
           father.addEventListener('click',function(){
               alert('father');
           },false);
       </script>
       ```

    6. 主要关注：**事件冒泡 Bubbling**

    7. 有些事件是没有冒泡的：onblue、onfocus、onmouseenter、onmouseleave。

25. ## 事件对象

    1. ```js
       var div = document.querySelector('div');
       div.onclick=function(event){}
       ```

    2. **event就是一个事件对象**，写到侦听函数的小括号里面，当做形参来看。

    3. 事件对象只有有了事件才会存在，它是系统自动创建的，不需要传递参数。

    4. 事件对象，是事件一系列相关数据的集合，跟事件相关的，比如鼠标点击了鼠标的相关信息，鼠标坐标。也包含键盘信息，比如用户按了什么键。

    5. ```js
       div.addEventListener('click',function(event){}) //此处event可以自定义名字
       ```

    6. 事件对象也有兼容性问题，ie678通过`window.event`获取。

       1. 兼容性解决：`e = e || window.event`

    7. 常见事件对象的属性和方法：

       ```html
       <div>123</div>
       <ul>
           <li>123</li>
           <li>123</li>
       </ul>
       <script>
           //1. e.target返回的是触发事件的对象（元素）
           //2. this返回的是绑定事件的对象（元素）
           var div=document.querySelector('div');
           div.addEventListener('click',function(e){
               console.log(e.target); //输出：<div>123</div>
               console.log(this);//输出：<div>123</div>
           });
           var ul = document.querySelector('ul');
           ul.addEventListener('click',function(e){ //实际点击的是ul中的li
               console.log(this); //给ul绑定了事件，那么 this 指向ul
               //<ul>...</ul>
               console.log(e.target);
               //<li>123</li>
           })
           //了解即可：跟this有个非常相似的属性 currentTarget  ie678不认识
       </script>
       ```

    8. | 事件对象属性方法    | 说明                                                         |
       | ------------------- | ------------------------------------------------------------ |
       | e.target            | 返回触发事件的对象   -  标准                                 |
       | e.srcElement        | 返回触发事件的对象   -  非标准 ie 6-8使用                    |
       | e.type              | 返回事件的类型 比如 click mouseover 不带 on                  |
       | e.cancelBubble      | 该属性阻止冒泡  非标准  ie6-8使用                            |
       | e.returnValue       | 该属性 阻止默认事件（默认行为） 非标准 ie6-8 使用， 比如不让链接跳转 |
       | e.preventDefault()  | 该方法 阻止默认事件（默认行为） 标准，比如不让链接跳转       |
       | return false        | 该返回可阻止默认行为，并且无兼容性问题，特点：return后面代码不执行，且只限制于传统注册方式 |
       | e.stopPropagation() | 阻止冒泡  标准                                               |

26. ## 阻止事件冒泡

    1. 事件冒泡：开始时由最具体的元素接收，然后主机向上传播到DOM最顶层节点。
    2. 标准写法：利用事件对象里面的`e.stopPropagation()`方法，*演示笔记 24 - 5代码*。
    3. 非标准写法：IE 6-8 利用事件对象cancelBubble属性。`e.cancelBubble = true`

27. ## 事件委托（代理）

    1. 比如一个班100个学生，由100个快递，一个个送耗费时间长，每个学生领取也需要排队。快递员把100个快递，委托给班主任，下课后学生自行领取即可。又比如`<ul><li><li>..`

    2. 事件委托**原理**：不是给每个子节点单独设置事件监听器，而是事件监听器设置在其父节点上，然后利用冒泡原理印象设置每个子节点。

    3. 作用：只操作了一次DOM，提高了程序的性能。

    4. ```html
       <ul>
           <li>知否知否</li>
           <li>知否知否</li>
           <li>知否知否</li>
           <li>知否知否</li>
           <li>知否知否</li>
       </ul>
       <script>
           var ul = document.querySelector('ul');
           ul.addEventListener('click',function(e){
               alert('点击了ul弹出了 li标签中的知否');
               //e.target可以得到点击的对象 获得li标签，更改某一个li的背景颜色
               e.target.style.backgroundColor = 'pink';
           })
       </script>
       ```

28. ## 常用鼠标事件

    1. **禁止**鼠标右键菜单

       1. `contextmenu`主要控制应该何时显示上下文菜单，主要用于程序员取消默认的上下文菜单。

          ```js
          document.addEventListener('contextmenu',function(e){
              e.preventDefault();
          })
          ```

       2. 禁止鼠标选中（selectstart开始选中）

          ```js
          document.addEventListener('selectstart',function(e){ //禁止鼠标选择文字
              e.preventDefault();
          })
          ```

    2. **event对象**代表事件的状态，跟事件相关的一系列信息的集合。比如 MouseEvent、KeyboardEvent等。

    3. | 鼠标事件对象 | 说明                                      |
       | ------------ | ----------------------------------------- |
       | e.clientX    | 返回鼠标相对于浏览器窗口可视区的 X 坐标   |
       | e.clientY    | 返回鼠标相对于浏览器窗口可视区的 Y 坐标   |
       | e.pageX      | 返回鼠标相对于文档页面的 X 坐标 IE9+ 支持 |
       | e.pageY      | 返回鼠标相对于文档页面的 Y 坐标 IE9+ 支持 |
       | e.screenX    | 返回鼠标相对于电脑屏幕的 X 坐标           |
       | e.screenY    | 返回鼠标相对于电脑屏幕的 Y 坐标           |

    4. 案例：鼠标移动图片跟随

       1. 使用鼠标移动事件：mousemove
       2. 页面中移动，给document注册事件
       3. 图片要移动举例，而且不占位置，使用绝对定位即可。
       4. 核心原理：每次鼠标移动，都会获得新的鼠标坐标，把这个x和y坐标作为图片的top和left值就可以移动图片。注意值加'px'.

29. ## 常用键盘事件

    1. | 键盘事件                | 触发条件                                                     |
       | ----------------------- | ------------------------------------------------------------ |
       | onkeyup<br/>keyup       | 某个键盘按键被松开时触发                                     |
       | onkeydown<br/>keydown   | 某个键盘按键被按下时触发                                     |
       | onkeypress<br/>keypress | 某个键盘按键被按下时触发，但是它**不识别功能键**，比如ctrl、shift、箭头等 |

    2. 传统方式：

       1. `document.onkeyup = function(){..}`

    3. 监听方式：

       1. `document.addEventListener('keyup',function(){...})`

    4. 执行顺序：keydown  -- keypress -- keyup

    5. 键盘事件对象 KeyboardEvent：

       1. 键盘对象包含多个属性，比如 `e.keyCode`遵循ASCII表格
       2. keyup、keydown 事件不区分字母大小写
       3. keypress 事件区分字母大小写

    6. ```js
       document.addEventListener('keyup',function(e){
           console.log('up:' + e.keyCode);
           if(e.keyCode ===65){
               alert("你按下了a键")
           }
       })
       ```

    7. 案例分析：

       1. 核心思路：检测用户是否在页面按下了s键，若按下s键，就把光标定位到搜索框里面
       2. 使用键盘事件对象里的keyCode 判断用户按下的是否s键
       3. 搜索框获得焦点：使用 js 里面的 `element.focus()` 方法
       4. 提示：使用keydown会在搜索框里面有s字母，使用keyup可以解决。

    8. 案例：搜索框放大镜

       1. 快递单号里面的值 value 获取过来给 con 盒子 innerText作为内容
       2. 注意表单获取的值使用value，div的盒子使用innerText或者innerHTML。
       3. 注意：keydown 和 keypress在文本框里面的特点：事件触发的时候，文字还没有落入文本框中。
       4. keypress触发事件对于盒子删除键无效。keyup触发事件的时候，文字已经落入文本框了。
       5. 失去焦点：`element.addEventListener('blur',function(){..})`
       6. 获得焦点：`element.addEventListener('focus',function(){..})`

30. ## BOM浏览器对象模型

    1. BOM - Browser Object Model 即浏览器对象模型，提供了独立于内容而与 浏览器窗口进行交互的对象，其核心是对象window。

       1. 比如浏览器刷新、滚动条、窗口大小等

    2. BOM由一系列相关的对象构成，并且每个对象都提供了很多方法与属性。[BOM与DOM对比图](https://github.com/brant8/vue2-3-Css/blob/main/pictures/javascript_bomdom.png)。

    3. BOM比DOM更大，包含DOM

       1. **window**包含：document、location、navigation、screen、history。
       2. `document.querySelector('..')` 等同于 `window.document.querySelector('..')`

    4. window对象时浏览器顶级对象，具有双重角色：

       1. 是JS访问浏览器窗口的一个接口。

       2. 是一个**全局对象**。定义在全局作用域中的变量、函数都会变成window对象的属性和方法。

       3. ```js
          var num = 10;
          console.log(num);
          console.log(window.num);
          ```

       4. window下的一个特殊属性`window.name`，变量尽量不取`var name`

31. ## window对象常见事件

    1. 一般DOM执行是从上往下执行，js代码必须放在html元素之后方能操作该元素。

    2. **窗口加载事件**：

       1. ```js
          window.onload = function(){..}
          //或者
          window.addEventListener("load",function(){..});
          ```

       2. window.onload是窗口（页面）加载事件，当**文档内容**完全加载完成会触发该事件（包括图像、脚本文件、css文件等），就调用的处理函数。

    3. 有了`window.onload`就可以把JS代码写道元素的上方，因为onload是等页面全部加载完毕再去处理函数。

    4. `window.onload`传统注册方式**只能写一次**，如果有多个，会以最后一个`window.onload`为准。

    5. 如果使用`addEventListener`则**没有限制**。

    6. `DOMContentLoaded`事件触发时，仅当DOM加载完成，不包括样式表、图片、flash等。

       1. `document.addEventListener('DOMContentLoaded',function(){..})`
       2. IE 9 + 才支持。
       3. 若页面图片很多，用户访问到 onload 触发可能需要较长事件，交互效果不能实现，影响用户体验，此时用 DOMContentLoaded 事件比较合适。

    7. **调整窗口大小事件**

       1. ```js
          window.onresize = function(){..}
          //或者
          window.addEventListener("resize",function(){..});
          ```

       2. `window.onresize`是调整窗口大小加载事件，当触发时就调用的处理函数。

    8. 只要窗口大小发生变化，就会触发这个事件。

    9. 经常利用这个事件完成响应式布局。`window.innerWidth`当前屏幕的宽度。

    10. **定时器**

        1. 两种定时器：setTimeout() 、 setInterval()

        2. **SetTimeout() ：**

           ```js
           //该定时器在定时器到期后执行调用函数。	
           //一、其中window可省略。
           window.setTimeout(调用函数, [延迟的毫秒数]);//二、毫秒可省略，默认值0.
           setTimeout(function(){},2000); //2秒后执行匿名函数function，也可以调用外层函数
           //
           function callback(){
               console.log('爆炸了');
           }
           setTimeout(callback,2000); //2秒后输出，不加括号表示的函数本身整个代码
           setTimeout(callback(),2000); //立刻输出，加括号表示调用函数输出的结果
           setTimeout('callback()',2000);//三、2秒后输出，但是不提倡此写法
           //四、页面中可能有很多定时器，经常给定时器加标识符（名字）
           var timer1 = setTimeout(callback,3000);
           var timer2 = setTimeout(callback,5000); //无需调用timerX，直接运行
           ```

        3. 回调函数：setTimeout()调用的函数也称为回调函数 callback。

           1. 普通函数是按照代码顺序直接调用。
           2. callback函数，需要等待事件，时间到了采取调用这个函数，因此称为回调函数。
           3. `element.onclick=function(){}`和`element.addEventListener("click",fn)`也是回调函数

        4. 案例1：定时5s后关闭广告。

        5. **停止**`setTimeout()`定时器

           1. `window.clearTimeout(timeoutID)`
           2. window可省略。

        6. **setInterval()**

           ```js
           window.setInterval(回调函数, [间隔的秒数]);
           ```

        7. setInterval() 方法重复调用一个函数，每隔这个事件，就去调用一次回调函数。

        8. 案例：倒计时

           1. 使用setInterval设置事件自动变化
           2. 三个盒子分别存放时分秒
           3. 使用innerHTML放入计算的值
           4. 封装倒计时函数，使用定时器调用时先调用一次，否则刷新页面会慢1秒/空白。

        9. 其他案例提示：

           1. 定义变量不赋值时，尽量赋值`null`值，否则会`undefined`，容易出错

           2. button修改按钮上的字，使用innerHTML修改其内容。

           3. 清除timer时注意如下

              ```js
              var timer = setInterval(function(){
                  if(time ==0 ){
                      clearInterval(timer);//此处可以清除timer定时器，因在其内部
                      ...
                  }
              })
              ```

32. ## this指向问题

    1. 一般情况下this的最终指向是哪个调用它的对象

    2. 全局作用域或者普通函数中this指向全局对象window（注意定时器里面的this指向window）

       ```js
       console.log(this); //window对象
       function fn(){
           console.log(this); //window对象
       }
       fn(); //实际window.fn()
       setTimeout(function(){ //相当于window.setTimeout...
           console.log(this);
       },1000);
       ```

    3. 方法调用中谁调用this指向谁

       ```js
       var o = {
           sayHi: function(){
               console.log(this); //指向的是o这个对象
           }
       }
       o.sayHi(); //输出 {saHi:f}
       o.sayHi;//输出 ƒ (){console.log(this); }
       var btn = document.querySelector('button');
       btn.onclick=function(){
           console.log(this);//指向btn这个按钮对象， <button>点击</button>
       }
       btn.addEventListener('click',function(){
           console.log(this);//指向btn这个按钮对象
       })
       ```

    4. 构造函数中this指向构造函数的实例

       ```js
       function Fun(){
           console.log(this); //指得是fun 实例对象
       }
       var fun = new Fun();
       ```

33. ## JS执行机制

    1. JS是单线程：

       1. JavaScript语言的一大特点就是单线程。同一时间只能做一件事。
       2. 比如堆某个DOM元素进行添加和删除操作，不能同时进行。因该先进行添加，之后再删除。
       3. HTML5提出Web Worker标准，允许JavaScript脚本创建多个线程。于是JS有了*同步*和*异步*。

    2. 同步：前一个任务结束后再执行后一个任务，程序的执行顺序与任务的排列顺序是一致的、同步的。

    3. 异步：在当前任务处理中可以处理其他任务。

    4. 本质区别：流水线上各个流程的执行顺序不同。

    5. 同步任务：同步任务都在主线程上执行，形成一个执行栈。

       ```js
       console.log(1); //同步1
       var timer = setTimeout(function(){  //回调函数为异步1
           console.log(3);
       },1000); //timer为同步2
       console.log(2); //同步3
       //输出结果：1 2 3
       ```

    6. 异步任务：JS的异步是通过回调函数实现的。[如图示](https://github.com/brant8/vue2-3-Css/blob/main/pictures/javascript_sync.png)

    7. 常见异步任务：

       1. 普通事件：如click、resize等
       2. 资源加载：如load、error等
       3. 定时器，包括setInterval、setTimeout等

    8. **JS执行机制**：

       1. 先执行*执行栈*中的同步任务
       2. 异步任务（回调函数）放入任务队列中。
       3. 一旦执行栈中的所有同步任务执行完毕，系统就会按次序读取任务队列中的异步任务，于是被读取的异步任务结束等待状态，进行执行栈，开始执行。[如图示](https://github.com/brant8/vue2-3-Css/blob/main/pictures/javascript_sync2.png)

    9. **事件循环 event loop**：由于主线程不断地重复获得任务、执行任务、再获得任务、再执行，这种机制称为事件循环。[如图示](https://github.com/brant8/vue2-3-Css/blob/main/pictures/javascript_eventloop.png)

34. ## location对象

    1. window对象给我们提供了一个location属性，用于获得或设置窗体的URL，并且可以用于解析URL。因为这个属性返回的示一个对象，所以将这个属性也成为location对象。

    2. URL一般语法格式

       ```js
       protocol://host[:port]/path/[?query]#fragment
       //比如：
       http://www.itcast.cn/index.html?name=andy&age=18#link
       //query 参数，以键值对的形式，通过 & 符号分隔开来
       //fragment 片段 #后面内容 常见于链接 锚点
       ```

    3. location对象的属性

       | location对象属性  | 返回值                           |
       | ----------------- | -------------------------------- |
       | location.href     | 获取或设置整个URL                |
       | location.host     | 返回主机（域名）                 |
       | location.port     | 返回端口号，如果未写返回空字符串 |
       | location.pathname | 返回路径                         |
       | location.search   | 返回参数，比如`?name=andy&..`    |
       | location.hash     | 返回片段 ，常见链接、锚点        |

    4. 案例分析：

       1. 第一个登录页面有提交表单(登录)，action提交到index.html页面
       2. 第二个页面，可以使用第一个页面的参数，实现了一个数据不同页面之间的传递效果
       3. 第二个页面之所以可以使用第一个页面的数据，是利用了URL里面的**location.search**参数。
       4. 在第二个页面中，需要把这个参数提取
       5. 第一步，去掉`？`利用`substr`
       6. 第二部，利用 =号分割 键 和 值，` split('=')`

    5. location对象的方法

       | location对象方法   | 返回值                                                       |
       | ------------------ | ------------------------------------------------------------ |
       | location.assign()  | 跟href一样，可以跳转页面（也成为重定向页面）                 |
       | location.replace() | 替换当前页面，因为不记录历史，所以不能后退页面               |
       | location.reload()  | 重新加载页面，相当于刷新按钮或者 f5，如果参数为true，**强制刷新ctrl + f5** |

35. ## navigator对象

    1. navigator对象包含有关浏览器的信息，有很多属性，如常用的 userAgent，该属性可以返回有客户机发送服务器的user-agent头部的值。

       1. 如手机浏览网站，切换手机版和电脑版

       2. ```js
           //前端判断用户用那个终端打开页面
          if((navigator.userAgent.match(/(phone|pad|pod|iPhone|iPod|ios|iPad|Android|Mobile|BlackBerry|IEMobile|MQQBrowser|JUC|Fennec|wOSBrowser|BrowserNG|WebOS|Symbian|Windows Phone/i))){
                  window.location.href="";//手机
              }else{
                  window.location.href="";//电脑
              }
          ```

36. ## history对象

    1. window对象提供了history对象，与浏览器历史记录进行交互。该对象包含用户访问过的URL。

       | history对象方法 | 作用                                                         |
       | --------------- | ------------------------------------------------------------ |
       | back()          | 可以后退功能                                                 |
       | forward()       | 前进功能                                                     |
       | go(参数)        | 前进后退功能 <br/> 参数如果是1，前进1个页面<br/>如果是-1，后退1个页面 |

    2. history对象一般在开发中比较少用，但是会在一些OA办公系统中见到。

37. ## offset偏移量

    1. offset翻译过来就是偏移量，使用offset系列相关属性可以*动态的*得到该元素的位置（偏移）、大小等。

       1. 获得元素举例带有定位父元素的位置
       2. 获得元素自身的大小（高度宽度）
       3. 注意：返回的数值都不带单位

    2. offset系列常用属性

       | offset系列属性       | 作用                                                         |
       | -------------------- | ------------------------------------------------------------ |
       | element.offsetParent | 返回作为该元素带有定位的父级元素，如果父级都没有定位则返回body |
       | element.offsetTop    | 返回元素相对带有定位父元素上方的偏移                         |
       | element.offsetLeft   | 返回元素相对带有定位父元素左边框的偏移                       |
       | element.offsetWidth  | 返回自身**包括**padding、边框、内容区的宽度，返回数值不带单位 |
       | element.offsetHeight | 返回自身**包括**padding、边框、内容去的高度，返回数值不带单位 |

    3. offsetParent与parentNode

       1. parentNode节点：返回父亲元素，最近一级的父亲，不管父亲有没有定位
       2. offsetParent：返回带有定位的父亲元素，否则返回的是body。

    4. offset与style区别

       | offset                                        | style                                       |
       | --------------------------------------------- | ------------------------------------------- |
       | offset可以得到任意样式中的样式值              | style只能得到行内样式表中的样式值           |
       | offset获得数值是没有单位的                    | style.width获得的是带有单位的字符串         |
       | offsetWidth包含padding+border+width           | style.width获得不包含padding和border的值    |
       | offsetWidth等属性是只读属性，只能获取不能赋值 | style.width是可读写属性，可以获取也可以赋值 |
       | 比如：box.offsetWidth                         | 比如：box.style.width                       |
       | *想要获取元素大小位置，offset更适合*          | *想要给元素更改值，使用style*               |

    5. 案例分析：获取在盒子内的鼠标坐标（以后的图片放大镜）

       ```html
       <div class="box"></div>
       <script>
           //在盒子内点击，得到鼠标举例盒子左右的距离
           let box = document.querySelector('.box');
           box.addEventListener('mousemove',function (e) {
               //鼠标在页面中的坐标e.pageX， e.pageY
               // console.log(e.pageX);
               // console.log(e.pageY);
               //盒子在页面中的距离（box.offsetLeft, box.offsetTop)
               // console.log(box.offsetLeft);
               var x = e.pageX - this.offsetLeft;
               var y = e.pageY - this.offsetTop;
               this.innerHTML = 'x坐标是：' + x + '<br>y的坐标是：'+ y ;
           })
       </script>
       ```

    6. 案例分析：模态框拖拽 [html细节链接](https://github.com/brant8/vue2-3-Css/blob/main/js%E9%BB%91%E9%A9%AC%E4%BB%A3%E7%A0%81/013demo_moveframe.html)

       ```html
       <script>
           var login = document.querySelector('.login');
           var mask = document.querySelector('.login-bg');
           var link = document.querySelector('#link')
           var closeBtn = document.querySelector('#closeBtn');
           var title = document.querySelector('#title');
           link.addEventListener('click',function(){
               mask.style.display='block';
               login.style.display='block';
           })
           closeBtn.addEventListener('click',function(){
               mask.style.display='none';
               login.style.display='none';
           })
           //开始拖拽
           title.addEventListener('mousedown',function(e){
               var x = e.pageX - login.offsetLeft;
               var y = e.pageY - login.offsetTop;
               //鼠标移动的时候，把鼠标在页面中的坐标，减去鼠标在盒子内的坐标就是模态框的坐标
               document.addEventListener('mousemove',move);//为了后续移除让盒子固定位置
               function move(e) {
                   login.style.left = e.pageX - x + 'px';
                   login.style.top = e.pageY - y + 'px';
               }
               //鼠标弹起，就让鼠标移动事件移除
               document.addEventListener('mouseup',function(){
                   document.removeEventListener('mousemove',move);
               })
           })
       </script>
       ```

38. ## 元素可视区client

    1. 使用client系列的相关属性来获取元素可视区的相关信息，即动态的得到该元素大小、边框大小等。不计较内容溢出部分。

       | client系列属性       | 作用                                                         |
       | -------------------- | ------------------------------------------------------------ |
       | element.clientTop    | 返回元素上边框的大小                                         |
       | element.clientLeft   | 返回元素左边框的大小                                         |
       | element.clientWidth  | 返回自身包括padding、内容区的宽度，**不含**边框，返回数值不带单位 |
       | element.clientHeight | 返回自身包括padding、内容区的高度，**不含**边框，返回数值不带单位 |

    2. 立即执行函数：`(function(){})()`

       1. 主要作用：创建一个独立的作用域
       2. 里面所有的变量都是局部变量，不会有命名冲突的情况

       ```js
       function fn(){
           console.log(1);
       }
       fn();
       //立即执行函数：不需要调用，立马能够自己执行的函数，多个立即执行函数需要逗号隔开
       //写法一：(function{})() ->相当于 （匿名函数)（） 第二个括号相当于调用
       //写法二：(function(){}())
       (function(a){
           console.log(2);
           console.log(a); //输出1
       })(1); //第二个小括号可以看作是调用函数
       ```

    3. 阿里flexible.js分析

       1. 物理像素比：`var dpr = window.devicePixelRatio || 1`，有像素比则获取，没有则取值1.
       2. 三种情况都会刷新页面触发 addEventListener('load',fn) 事件。
          1. a标签的超链接
          2. F5或者刷新按钮（强制刷新）
          3. 前进后退按钮

       3. 在火狐中，有个特点，有个“往返缓存”，这个缓存中不仅保存着页面数据，还保存了DOM和JavaScript的状态；实际上是将整个页面都保存在了内存里。此时后退不能刷新页面。
       4. 此时可以用pageshow事件来触发，这个事件在页面显示时触发，无论页面是否来自缓存。在重新加载页面中，pageshow会在load事件触发后触发；根据事件对象中的persisted来判断是否缓存中的页面触发的pageshow事件，注意这个事件给window添加。
          1. addEventListener('pageshow',fn())
          2. persisted有缓存返回true，无缓存返回false。

39. ## 元素滚动scroll

    1. 使用scroll可以动态的得到该元素的大小、滚动距离等。若有内容溢出，以溢出为主的实际大小。

       | scroll系列属性       | 作用                                           |
       | -------------------- | ---------------------------------------------- |
       | element.scrollTop    | 返回被卷去的上侧距离，返回数值不带单位         |
       | element.scrollLeft   | 返回被卷去的左侧距离，返回数值不带单位         |
       | element.scrollWidth  | 返回自身实际的宽度，不含边框，返回数值不带单位 |
       | element.scrollHeight | 返回自身实际的高度，不含边框，返回数值不带单位 |

    2. 盒子文字内容溢出情况：

       1. `overflow：auto`后内容有滚动条，向下滚动时，scrollTop为第一行（被卷去的头部）到内容可视区的高度。

    3. 页面被卷去的头部：

       1. 如果浏览器的高（或宽）度不足以显示整个页面时，会自动出现滚动条。当滚动条向下滚动时，页面上面被隐藏掉的高度，我们就成为页面被卷去的头部。滚动条在滚动时会触发onscroll事件。

       2. ```js
          div.addEventListener('scroll',function(){
              console.log(div.scrollTop); //滚动时被卷掉的高度
          })
          ```

    4. 案例：固定右侧侧边栏，返回顶部，头部固定 [代码示例](https://github.com/brant8/vue2-3-Css/blob/main/js%E9%BB%91%E9%A9%AC%E4%BB%A3%E7%A0%81/014demo_moveframescrolltop.html)

       1. 滚动事件scroll，事件源document

       2. 滚动到某个位置(头部卷去时)，判断页面被卷去的上部值

       3. 页面被卷去的头部：通过`window.pageYOffset`获得，如果时被卷去的左侧`window.pageXOffset`

       4. 注意：**元素**被卷去的头部时`element.scrollTop`，如果是**页面**被卷去的头部则是`window.pageYOffset`

       5. ```html
          <script>
              var sliderbar = document.querySelector('.slider-bar');
              var banner = document.querySelector('.banner');
              console.log(banner.offsetTop);
              var bannerTop = banner.offsetTop;
              //当侧边栏固定之后应该变化的数值，否则位置跳动幅度很大
              var sliderbarTop = sliderbar.offsetTop - bannerTop;
              //获得main主体元素
              var main = document.querySelector('.main');
              var goBack = document.querySelector('.goBack');
              var mainTop = main.offsetTop;
              document.addEventListener('scroll',function(){
                  //console.log(window.pageYOffset);//页面被卷去的头部
                  //当页面被卷去的头部大于等于某个值，侧边栏就改为固定定位
                  if(window.pageYOffset >= bannerTop){
                      sliderbar.style.position = 'fixed';
                      sliderbar.style.top = sliderbarTop + 'px';
                  }else{
                      //低于头部值，恢复其值
                      sliderbar.style.position = 'absolute';
                      sliderbar.style.top = '300px';
                  }
                  //当页面滚动到main盒子，就滚动goback模块
                  if(window.pageYOffset >= mainTop){
                      goBack.style.display='block';
                  }else{
                      goBack.style.display='none';
                  }
              })
          </script>
          ```

       6. 需要注意的时，页面被卷去的头部，有兼容性问题，因此被卷去的头部通常有如下写法

          1. 声明了DTD，使用document.documentElement.scrollTop

          2. 未声明DTD，使用document.bodyscrollTop

          3. 新方法window.pageYOffset和window.pageXOffset，IE9开始支持

          4. ```js
             function getScroll(){
                 return {
                     left:window.pageXOffset || document.documentElement.scrollLeft || document.body.scrollLeft || 0,
                     top: window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0
                 };
             }
             //使用的时候 getScroll().left
             ```

40. ## 三大系列总结

    1. | 三大系列大小对比    | 作用                                                         |
       | ------------------- | ------------------------------------------------------------ |
       | element.offsetWidth | 返回自身**包括**padding、**边框**、内容区的宽度，返回数值不带单位 |
       | element.clientWidth | 返回自身包括padding、内容区的宽度，**不包含边框**，返回数值不带单位 |
       | element.scrollWidth | 返回自身实际的宽度，**不包含边框**，返回数值不带单位  (超出内容宽度使用这个) |

    2. ![总结](https://github.com/brant8/vue2-3-Css/blob/main/pictures/javascript_offsetscroll.png)

    3. 主要用法：

       1. offset系列主要用于获得元素位置 offsetLeft、offsetTop
       2. client经常用于获得元素大小 clientWidth、clientHeight
       3. scroll经常用于获取滚动距离 scrollTop、scrollLeft
       4. 注意页面滚动的距离通过window.pageXOffset获得

41. ## 其他鼠标事件

    1. 当鼠标移动到元素上时就会触发mouseenter事件
    2. 类似mouseover经过自身盒子会触发，经过子盒子还会触发。mouseenter指挥经过自身盒子触发。
    3. mouseenter不会冒泡
    4. mouseleave同样不会冒泡

42. ## 动画函数封装

    1. 核心原理：通过定时器`setInterval()`不断移动盒子位置

    2. 实现步骤：

       1. 获得盒子当前位置 `div.offsetLeft`

       2. 让盒子在当前位置加上1个移动距离 `div.style.left = div.offsetLeft + 1 + 'px'`

       3. 利用定时器不断重复这个操作 `setInterval(function(){..},30)`

       4. 加一个结束定时器的操作 if->`div.offsetLeft >=400`，`clearInterval(timer)`

       5. 注意此**元素需要添加定位**，才能使用`element.style.left`

          1. 比如定位：`position:absolute`

          ```html
          <div></div> <!--场景：幻灯片移动-->
          <script>
          	var div = document.querySelector('div'); //有var表示要在内存开辟空间
              var timer = setInterval(function(){
                  if(div.offsetLeft >= 400){
                      clearInterval(timer);
                  }
                  div.style.left = div.offsetLeft +1 + 'px';
              },30);
          </script>
          ```

    3. 简单动画封装函数： **动画元素必须要有定位**

       ```js
       function animate(obj,target){
           var div = document.querySelector('div'); 
           var timer = setInterval(function(){
               if(obj.offsetLeft >= target){
                   clearInterval(timer);
               }
               obj.style.left = obj.offsetLeft +1 + 'px';
           },30);
       }
       var div = document.querySelector('div');
       //调用函数
       animate(div,300);
       ```

    4. 动画函数给不同元素记录不同定时器

       1. 如果多个原始都使用这个动画函数，每次都要var声明定时器。我们可以给不同元素使用不同的定时器（自己还专门用自己的定时器）。

       2. 核心原理：利用JS时一门动态语言，可以很方便的给当前对象添加属性。

          ```js
          var obj = {};
          obj.name = 'andy';
          ```

       3. ```js
          function animate(obj,target){
              var div = document.querySelector('div'); 
              obj.timer = setInterval(function(){ //obj设定属性timer
                  if(obj.offsetLeft >= target){
                      clearInterval(timer);
                  }
                  obj.style.left = obj.offsetLeft +1 + 'px';
              },30);
          }
          ```

    5. BUG：当不断点击按钮，元素的速度会越来越快，因为i开启了太多的定时器。

       1. 解决方案：让元素只有一个定时器执行

          ```js
          function animate(obj,target){
              clearInterval(obj.timer);//清除以前的定时器，以防用户多次点击给元素加速
              var div = document.querySelector('div'); 
              obj.timer = setInterval(function(){ //obj设定属性timer
                  if(obj.offsetLeft >= target){
                      clearInterval(timer);
                  }
                  obj.style.left = obj.offsetLeft +1 + 'px';
              },30);
          }
          ```

    6. **缓动效果原理**：

       1. 让元素运动速度有所变化，最常见的时让速度慢慢停下来。

       2. 思路：

          1. 让盒子每次移动的距离慢慢变小，速度就会慢慢落下来。
          2. 核心算法：（目标值-现在的位置）/10，作为每次移动的距离步长
          3. 停止的条件：让当前盒子位置等于目标位置就停止定时器
          4. 注意步长值取整。

          ```js
          function animate(obj,target){
              clearInterval(obj.timer);//清除以前的定时器，以防用户多次点击给元素加速
              var div = document.querySelector('div'); 
              obj.timer = setInterval(function(){ //obj设定属性timer
                  //把步长值改为整数，不要出现小数的问题即可让步长满足移动距离
                  //var step = Math.ceil((target - obj.offsetLeft) /10);
                  var step = (target - obj.offsetLeft) /10;
                  //往正方向走时向上取整，往回走时向下取整，否则步长不等于期望的移动距离
                  step = step > 0 ? Math.ceil(step) : Math.floor(step);
                  if(obj.offsetLeft >= target){
                      clearInterval(timer);
                  }
                  obj.style.left = obj.offsetLeft + step + 'px';
              },15);
          }
          ```

       3. 匀速动画：盒子当前位置+固定值

       4. 缓动动画：盒子当前位置+变化值

    7. **动画函数添加回调函数**

       1. 函数作为一个参数，将这个函数作为参数传到另一个函数里面，当那个函数执行完之后，再执行传进去的这个函数，这个过程就叫做回调。

       2. ```html
              <style>
                  .move{
                      width: 300px;
                      height: 300px;
                      background: #009dfd;
                      position: absolute; /*必须定位*/
                  }
              </style>
          <button>点击移动</button>
          <div class="move"></div>
          <script>
          function animate(obj,target,callback){ //callback回调函数
          //function animate(obj,target){ 
              	console.log(callback);// 调用的时候 callback()
                  clearInterval(obj.timer);
                  obj.timer = setInterval(function(){
                      if(obj.offsetLeft >= target){
                          clearInterval(obj.timer);
                          //回调函数写到定时器里面
                          if(callback){
                              callback();
                          }
                      }
                      console.log(obj.offsetLeft);
                      obj.style.left = obj.offsetLeft + 10 + 'px';
                  },15);
              }
              var div = document.querySelector('.move');
              var btn = document.querySelector('button');
              btn.addEventListener('click',function (e) {
                  console.log("btn clicked")
                  animate(div,800,function (){
                      //当盒子位置抵达800后背景颜色变为红色
                      div.style.backgroundColor = 'red';
                  });
                  //相当于 animate(div,800,fn) fn当参数传入
              })
          </script>
          ```

    8. **动画函数封装到单独JS文件里面**

       1. 若经常使用这个动画函数，可以单独封装到一个js文件里面，使用的时候直接引用js文件即可。

    9. 案例：鼠标经过展示动画，显示菜单名字。

       1. 鼠标经过和离开的事件监听
       2. 效果执行完毕，使用回调函数更改图标方向

43. ## 常见网页特效案例

    1. **网页轮播图**

       1. 按钮显示

          1. 鼠标经过轮播图模块，左右显示按钮，离开隐藏按钮。
          2. 点击左侧按钮一次，图片往左播放一张，以此类推
          3. 图片播放的是同时，下面圆圈模块跟随一起变化
          4. 点击小圆圈，可以播放相应图片
          5. 鼠标不经过轮播图，轮播图也会自动播放
          6. 鼠标经过，轮播图模块，自动停止播放

       2. 动态生成小圆圈

          1. 核心思路：圆圈个数跟图片张数一致
          2. 首先得到ul里面图片张数（图片放入li里面，就是li的个数）
          3. 利用循环动态生成小圆圈（小圆圈放入ol里面）
          4. 创建节点createElement('li')
          5. 插入节点ol.append Child(li)

       3. 小圆圈的排他思想

          1. 点击当前小圆圈，就添加current类，更换当前样式
          2. 其余的小圆圈，就移除这个current类

       4. 点击小圆圈滚动图片

          1. 用到animate动画函数，将js文件引入，注意引入先后顺序

          2. 使用动画函数的前提，该元素必须有定位

          3. **注意**是 ul 移动，而不是 li 移动。如 'div - ul - li' 是ul整体移动，否则 li 移动会造成排版问题

          4. 滚动图片的核心算法：点击某个小圆圈，让图片滚动，小圆圈的索引号乘以图片的宽度，作为ul移动距离。

          5. 此时需要直到小圆圈的索引号，可以再生成小圆圈的时候，设定一个自定义属性，点击的时候获取这个自定义属性即可。

          6. ```js
             window.addEventListener('load',function(){
                 //1.获取元素
                 var arrow_l = document.querySelector('.arrow-l');
                 var arrow_r = document.querySelector('.arrow-r');
                 var focus = document.querySelector('.focus');
                 //2.鼠标经过focus 显示隐藏的按钮
                 focus.addEventListener('mouseenter',function(){
                     arrow_l.style.display='block';
                     arrow_r.style.display='block';
                 })
                 //3.鼠标经过focus 隐藏按钮
                 focus.addEventListener('mouseleave',function(){
                     arrow_l.style.display='none';
                     arrow_r.style.display='none';
                 })
                 //4.生成动态小圆圈，有几张图片就生成几个
                 var ul = focus.querySelector('ul');
                 console.log(ul.children.length);
                 var ol = focus.querySelector('ol');
                 for (let i = 0; i < ul.children.length; i++) {
                     //5.创建一个li
                     var li = document.createElement('li');
                     //13.记录当前小圆圈的索引号，通过自定义属性来做
                     li.setAttribute('index',i);
                     //6.把li插入到ol里面
                     ol.appendChild(li);
                     //8.小圆圈的排他思想，可以直接再生成小圆圈的同时直接绑定点击事件
                     li.addEventListener('click',function(){
                         //9.干掉所有人，把所有li清除current类名
                         for (let j = 0; j < ol.children.length; j++) {
                             ol.children[j].className = '';
                         }
                         //10.留下自己，当前li设置current
                         this.className='current';
                         //11.点击小圆圈，移动图片 移动的是ul
                         //12.ul的移动距离，小圆圈的索引号，乘以图片的宽度
                         var focusWidth = focus.offsetWidth;
                         //14.当点击了某个li就获取当前li的索引号
                         var index= this.getAttribute('index');
                         //15.让其移动ul，，注意是负值
                         animate(ul,-index * focusWidth, callback);
                     })
                 }
                 //7.把ol里面的第一个li设置类名为current
                 ol.children[0].className = 'current';
             ```

       5. 点击右侧按钮一次，就让图片滚动一张

          1. 声明一个变量num。点击一次，自增1，让这个变量乘以图片宽度，就是ul的滚动距离。
          2. 图片滚动无缝原理
          3. 把ul 第一个li 复制一份，放到ul 最后面
          4. 当图片滚动到克隆的最后一张图片时，让ul 快速的、不做动画的跳到最左侧，left 为0.
          5. 同时num赋值为0，可以从新开始滚动图片

       6. 克隆第一张图片

          1. 克隆ul第一个li cloneNode()，加 true 深克隆赋值里面的子节点，false 浅克隆。

          2. ```js
             //7.把ol里面的第一个li设置类名为current
             ol.children[0].className = 'current';
             //克隆第一张图片li 到ul最后面（过渡效果）
             var first = ul.children[0].cloneNode(true);
             ul.appendChild(first);
             //点击右侧按钮，图片滚动一张
             var num = 0;
             var focusWidth = focus.offsetWidth;
             arrow_r.addEventListener('click',function(){
                 if(num ==ul.children.length-1){
                     ul.style.left=0;
                     num=0;
                 }
                 num++;
                 animate(ul, -num * focusWidth, callback);
             })
             ```

       7. 点击右侧按钮，小圆圈跟随变化

          1. 最简单做法，再声明一个变量circle，每次点击自增1。左侧按钮也需要这个变量，因此要声明全局变量。
          2. 图片有5张，小圆圈只有4个少一个，必须加一个判断条件
          3. 如果circle == 4，就重新复原为0.

       8. 自动播放功能

          1. 添加一个定时器
          2. 自动播放轮播图，实现类似与点击了右侧按钮
          3. 使用手动调用右侧按钮点击事件 arrow_r.click()
          4. 鼠标经过focus就停止定时器
          5. 鼠标离开focus就开启定时器

    2. **节流阀** [例子](https://github.com/brant8/vue2-3-Css/blob/main/js%E9%BB%91%E9%A9%AC%E4%BB%A3%E7%A0%81/016demo_slider.html)

       1. 放置轮播图按钮连续点击造成播放过快

       2. 节流阀目的：当上一个函数动画内容执行完毕，再去执行下一个函数动画，让事件无法连续触发。

       3. 核心思路：利用回调函数，添加一个变量来控制，锁住函数和解锁函数。

       4. 开始设置一个变量 var flag = true

          1. if(flag){ flag = false; do sth. } ， 关闭水龙头
          2. 利用回调函数，动画执行完毕，flag = true， 打开水龙头

       5. ```js
          if(callback){
              callback();
          }
          //等同于
          callback && callback(); //短路运算
          ```

    3. **返回顶部特效**

       1. 滚动窗口值文档中的特定位置

       2. `window.scroll(x,y)`， 比如顶部window.scroll(0,0)。其中的x、y不加单位。

       3. 带有动画的返回顶部，使用封装的动画函数

       4. 把所有left相关的值改为跟页面垂直滚动距离相关即可。

       5. 页面滚动了多少，可以通过`window.pageYOffset`得到

       6. ```js
          window.scroll（0，window.pgeYOffset + step);
          ```

       7. 窗口滚动，所以对象是window：`animate(window, 0, callback)`

    4. **筋斗云案例**

       1. 鼠标经过某个li，筋斗云跟着到当前li的位置

       2. 鼠标离开li，筋斗云复原为原来的位置

       3. 鼠标点击了某个li，筋斗云留在点击这个li的位置

       4. 利用动画函数做动画效果

       5. 原先筋斗云的起始位置是0

       6. 经过某个li，把当前li的offsetLeft位置作为目标值即可

       7. 鼠标离开li，把目标值设置为0

       8. ```html
          <script>
              window.addEventListener('load',function(){
                  var cloud = document.querySelector('.cloud');
                  var c_nav =document.querySelector('.c-nav');
                  var lis = c_nav.querySelectorAll('li');
                  var current = 0;//筋斗云的起始位置
                  //给所有li绑定事件
                  for (let i = 0; i < lis.length; i++) {
                      lis[i].addEventListener('mouseenter',function () {
                          animate(cloud, this.offsetLeft);
                      });
                      lis[i].addEventListener('mouseleave',function () {
                          animate(cloud, current);
                      });
                      lis[i].addEventListener('click',function () {
                          current=this.offsetLeft
                      })
                  }
              })
          </script>
          <div class="c-nav" id="c_nav">
              <span class="cloud"></span>
              <ul>
                  <li class="current"><a href="#">首页新闻</a></li>
                  <li><a href="#">师资力量</a> </li>
                  <li><a href="#">活动策划</a> </li>
                  <li><a href="#">企业文化</a> </li>
                  <li><a href="#">招聘信息</a> </li>
                  <li><a href="#">公司简介</a> </li>
              </ul>
          </div>
          ```

44. ## 移动端 触屏事件

    1. 移动端浏览器兼容性较好，不需要考虑以前JS的兼容性问题，可以放心使用原生JS书写效果。

    2. 移动端特有：触屏事件`touch`（触摸事件），Android和IOS都有。

    3. **touch 对象 代表一个触摸点**。触摸点可能是一根手指，也可能是一根触摸笔。触屏事件可以相应用户手指（或触控笔）堆屏幕或者触控板操作。

       | 触屏touch事件 | 说明                          |
       | ------------- | ----------------------------- |
       | touchstart    | 手指触摸到一个DOM元素的触发   |
       | touchmove     | 手指在一个DOM元素上滑动时触发 |
       | touchend      | 手指从一个DOM元素上移开时触发 |

    4. 触摸事件对象 TouchEvent

       1. 描述手指在触摸屏面的状态变化的事件。

       2. 这类事件用于描述一个或多个触电，使开发者可以检测触电的移动，触点的增加和减少。

       3. 

          ```js
          元素.addEventListener('touchstart',function(e){
              console.log(e); //TouchEvent{...}
          })
          ```

       4. 触摸事件对象常见对象列表（`console.log(e)`）

          | 触摸列表       | 说明                                             | 举例                                           |
          | -------------- | ------------------------------------------------ | ---------------------------------------------- |
          | touches        | 正在触摸**屏幕**的所有手指的一个列表             | TouchEvent{ touches:{{0:Touch{..}, length:1} } |
          | targetTouches  | 正在触摸当前**DOM元素**上的手指的一个列表        |                                                |
          | changedTouches | 手指状态发生了改变的列表，从无到有，从有到无变化 |                                                |

       5. 如果侦听的是一个DOM元素，touches和targetTouches是一样的。

       6. 平时都是给元素注册触摸事件，重点记住targetTouches

          ```js
          console.log(e.targetTouches[0])//获取正在触摸dom元素的第一个手指信息，比如手指的坐标
          ```

    5. 移动端拖动元素

       1. touchstart、touchmove、touchend可以实现拖动元素
       2. 拖动元素需要当前手指的坐标值，可以使用`targetTouches[0]`里面的`pageX`和`pageY`
       3. 移动端拖动原理：手指移动中，计算出手指移动举例。然后用原来的盒子的位置 + 手指移动的距离。
       4. 手指移动的距离：手指滑动中的位置减去 手指刚开始触摸的位置

    6. 拖动元素三部曲：

       1. 触摸元素touchstart：获取手指初始坐标，同时获得盒子原来的位置
       2. 移动手指touchmove：计算手指的滑动距离，并移动盒子
       3. 离开手指touchend

    7. 注意：**手指移动也会触发滚动屏幕**所以这里要阻止默认的屏幕滚动`e.preventDefault();`

       ```html
       <style>
           div{
               position:absolute;
               left: 0;
               width: 100px;
               height: 100px;
               background-color: pink;
           }
       </style>
       <div>   </div>
       <script>
           //touchstart, touchmove, touchend
           var div =document.querySelector('div');
           var startX = 0;//获得手指初始位置
           var startY = 0;
           var x = 0;//获得盒子原来的位置
           var y = 0;
           div.addEventListener('touchstart',function(e){
               startX = e.targetTouches[0].pageX;
               startY = e.targetTouches[0].pageY;
               x = this.offsetLeft;
               y = this.offsetTop;
           });
           div.addEventListener('touchmove',function(e) {
               //计算手指的移动距离：手指移动之后的坐标减去手指初始的坐标
               var moveX = e.targetTouches[0].pageX - startX;
               var moveY = e.targetTouches[0].pageY - startY;
               //移动盒子
               this.style.left = x + moveX + 'px';
               this.style.top = y + moveY + 'px'; 
               e.preventDefault(); //阻止默认移动屏幕
           })
       </script>
       ```

    8. 轮播图使用CSS3的过渡，判断：

       1. CSS3使用过渡效果，比如 `ul.style.transition='all .3s';` `ul.style.transform='translateX(' + translatex + 'px)';`
       2. 等CSS效果过渡完成之后，在判断监听过渡完成的事件 transitionend，比如`ul.addEventListener('transitionend',function(){...})`
       3. 过渡效果要在监听事件之外

    9. **classList属性，HTML5新增属性，返回元素的类名 伪数组**。IE10+支持。

       1. ```html
          <div class="one two"></div>
          <script>
          	//元素.classList 或者具体当中的class名 元素.classList[0]
              //可以在元素中添加、移除、切换CSS类名
              element.classList.add('类名'); //追加class类名、不会覆盖以前类名
              element.classList.remove('类名');//移除类名
              element.classList.toggle('类名');//切换类名
              btn.addEventListener('click',function(){
                  document.body.classList.toggle('bg');//一般使用button切换，比如开关等切换黑色背景
                  //点一次有bg类，再点一次移除bg类
              })
          </script>
          ```

       2. 使用场景：轮播图小圆点跟随变化

          1. 把ol里面li带有current类名的选出来去掉类名： remove

          2. 让当前索引号的小li加上current： add

          3. 但是：要等着CSS3过渡结束之后变化，要写到transitionend事件里面

          4. ```js
             //不使用排他思想的做法
             ol.querySelector('.current').classList.remove('current');
             ol.children[index].classList.add('current');
             ```

    10. **手指滑动轮播图**

        1. 本质就是ul跟随手指移动，简单说就是移动端拖动元素

        2. 触摸元素touchstart：获取手指初始坐标

        3. 移动手指touchmove：计算手指的滑动距离，并且移动盒子

           ```js
           var startX = 0; //获取手指初始坐标
           var moveX = 0; //移动距离，后面会使用，
           ul.addEventListener('touchstart', function(e){
               startX = e.targetTouches[0].pageX;
           });
           ul.addEventListener('touchmove', function(e){
               moveX = e.targetTouches[0].pageX-startX;//计算移动距离
               //移动盒子：盒子原来的位置 + 手指移动的距离
               var translatex = -index * w + moveX;
               //手指拖动的时候，不需要动画效果，要取消过渡效果
               ul.style.transition = 'none';
               ul.style.transform = 'translateX('+ translatex + 'px)';
           });
           ```

        4. 离开手指touchend：根据滑动距离分不同情况

           1. 如果移动距离小于某个像素，就回弹原来位置
           2. 如果移动距离大于某个像素，就上一张或下一张图片滑动
           3. 若用来判断是否拖动，可单独给一个变量更改true/false。（点击链接非滑动时可用）

           ```js
           ul.addEventListener('touchend', function(e){
              //如果移动距离大于50像素就播放上一张或者下一张
               if(Math.abs(moveX) > 50){
                   //如果右滑：播放上一张 moveX是正值
                   if(moveX > 0){
                       index--;
                   }else{//如果作画：播放下一张 moveX是负值
                       index++;
                   }
                   var translatex = -index *w;
                   ul.style.transition = 'all .3s';
               	ul.style.transform = 'translateX('+ translatex + 'px)';
               }else{
                   //小于50像素就回弹
                   var translatex = -index * w;
                   ul.style.transform = 'translateX('+ translatex + 'px)';
               }
           });
           ```

    11. **click延时**解决方案

        1. 移动端click事件会有300ms的演示，原因是移动端屏幕等待 双击会缩放(double tap to zoom)页面。

        2. 解决方案：

           1. 禁止缩放。浏览器禁用默认的双击缩放行为并且去掉300ms的点击延迟

              ```html
              <meta name = "viewport" content="user-scalable=no"
              ```

           2. 利用touch事件自己封装这个事件解决300ms延迟。

              1. 当手指触摸屏幕，记录当前触摸时间。
              2. 当手指离开屏幕，用离开的时间减去触摸的时间。
              3. 如果时间小于150ms，并且没有滑动屏幕，那么就定义为点击。

              ```js
              function tap(obj,callback){
                  var isMove = false;
                  var startTime = 0;//记录触摸的时候的时间变量
                  obj.adEventListener('touchstart',function(e){
                      startTime = Date.now();//记录触摸时间
                  });
                  obj.addEventListener('touchmove',function(e){
                      isMove=true;//看看是否有滑动，有滑动算拖拽，不算点击
                  });
                  obj.addEventListener('touchend',function(e){
                      if(!isMove && (Date.now - startTime) < 150){
                          //如果手指触摸和离开时间小于150ms算点击
                          callback && callback();//执行回调函数
                      }
                      isMove = false; //取反 重置
                      startTime=0;
                  });
              }
              //调用
              tap(div,function(){ 
                  //执行代码 
              })；
              ```

           3. 使用插件。fastclick插件解决300ms延迟。（js第三方文件）

              ```js
              if('addEventListener' in document){//查看document是否有该事件
                  document.addEventListener('DOMContentLoaded',function(){ //等DOM元素全部加载完毕
                      FastClick.attach(document.body);//FastClick为导入改js文件后
                  },false);
              }
              //原FastClick以 ;function(){。。。} 开头是因为方法之间用 ； 隔开，以防导入时其他方法没有加分号
              //剩余代码按照正常写法即可
              ```

45. ## Swiper插件 轮播图手机端

    1. 引入插件相关文件（中英版） [下载地址](https://www.swiper.com.cn/download/index.html)

    2. 使用大概：

       1. 使用在线demo查看所需样式，[点击查看地址](https://www.swiper.com.cn/demo/index.html) ，含有PC和移动端

       2. 下载文件包中有demo目录

       3. 目录中的样式按编号与在线样式编号一致即可。

       4. 具体代码与CSS、HTML、JS代码匹配 以及 swiper.min.css和 swiper.min.js文件

       5. 注意：最好使用`load`添加轮播方式 ，具体自定义[案例地址](https://github.com/brant8/vue2-3-Css/blob/main/js%E9%BB%91%E9%A9%AC%E4%BB%A3%E7%A0%81/019demo_swiper.html) .

          ```js
          window.addEventListener('load',function(){
              //轮播图主要播放方式的选择
              var swiper = new Swiper(".mySwiper", {
                  pagination: {
                      el: ".swiper-pagination",
                  },
              });
          })
          ```

       6. 官方参考使用方法： [地址](https://www.swiper.com.cn/usage/index.html) , [官方AP](https://www.swiper.com.cn/api/pagination/69.html) .

       7. 样式若需要修改，查找相对应的元素，在定义CSS/style中修改覆盖即可。（引用顺序）

       8. 其他常见移动端插件,移动端一般叫做TouchSlide

          1. superslide
          2. iscroll

46. ## 移动端视频插件

    1. H5提供了video标签，但是浏览器支持情况不同，不同的视频格式文件，可以通过source解决。

    2. 外观样式、暂停、播放、全屏功能只能自己写代码解决，此时可以用其他插件解决。

       ```html
       <!--比如视频插件zy.media.js-->
       <div class="playvideo">
           <div class="zy_media">
               <video data-config='{"mediaTitle": "测试视频---视频“}' controls>
                   <source src="C:\Users\win10pure\Downloads\20210305-093530_F.MOV" type="video/mp4">
                   您的浏览器不支持HTML5视频
               </video>
               <!-- 默认HTML5代码
               <video src="xx.mp4" controls></video>   -->
           </div>
           <div id="modelView">&nbsp;</div>
       </div>
       ```

47. ## Bootstrap轮播图

    1. [原生复制](https://getbootstrap.com/docs/3.4/javascript/#carousel-methods)遇到的问题：轮播图与自定义`.focus`类尺寸不协调。
       1. `<div class=focus><轮播图代码></div>`
       2. 查看首页代码，boostrap的`carousel`只有`relative`没有给高度宽度，而父类`.focus`给了高宽。
       3. 赋值高度与宽度给`.carousel`和`.carousel img`
       4. 若无法生效，强制`!important`高宽度。

    2. Methods为控制速度等其他特效
    3. [案例](https://github.com/brant8/vue2-3-Css/blob/main/js%E9%BB%91%E9%A9%AC%E4%BB%A3%E7%A0%81/020demo_bootstrap.html)

48. ## 本地存储

    1. 数据存储在用户浏览器中

    2. 设置、读取方便、甚至页面刷新不丢失数据

    3. 容量较大，`sessionStorage`约 5M（几百万字）、`localStorage`约 20M。

    4. 只能存储字符串，可以将对象`JSON.stringify()`编码后存储

    5. **window.sessionStorage**

       1. 生命周期为关闭浏览器窗口

       2. 在同一个窗口(页面)下数据可以共享

       3. 以键值对的形式存储使用

       4. 数据操作

          ```js
          //存储数据
          sessionStorage.setItem(key, value) //省略window
          //获取数据
          sessionStorage.getItem(key)
          //删除数据
          sessionStorage.removeItem(key)
          //删除所有数据
          sessionSotrage.clear()
          ```

       5. 浏览器查看存储路径信息：Application - Storage - Session Storage

    6. **window.localStorage**

       1. 声明周期永久生效，除非手动删除， 否则关闭页面也会存在

       2. 可以多窗口（页面）共享（统一浏览器可以共享）

       3. 以键值对的形式存储使用

          ```js
          //存储数据
          localStorage.setItem(key, value)
          //获取数据
          localStorage.getItem(key)
          //删除数据
          localStorage.removeItem(key)
          //删除所有数据
          localStorage.clear()
          ```

       4. 浏览器查看存储路径信息：Application - Storage - Local Storage

    7. 案例：记住用户名

       ```html
       <input type="text" id="username"><input type="checkbox" name="" id="remember">记住用户名
       <script>
           var username = document.querySelector('#username');
           var remember = document.querySelector('#remember');
           if(localStorage.getItem('username')){
               username.value = localStorage.getItem('username');//文本框的值
               remember.checked = true;
           }
           remember.addEventListener('change',function(){//复选框 状态发生改变
               if(this.checked){
                   localStorage.setItem('username',username.value);
               }else{
                   localStorage.removeItem('username');
               }
           })
       </script>
       ```

49. ## jQuery

    1. JavaScript库 概述：一个封装好的特定的集合（方法和函数）。jQuery中j就是JavaScript，Query查询，就是查询js，把 js 中的 DOM 操作做了封装，可以快速查询使用里面的功能。

    2. jQuery版本

       1. 1x：兼容IE678等低版本浏览器，不再更新
       2. 2x：不兼容IE678等低版本浏览器，不再更新
       3. 3x：不兼容IE678等低版本浏览器，主要更新维护版本

    3. 基本用法之 等页面DOM加载完毕再执行js代码

       ```js
       //方式一：
       $(document).ready(function(){
           ...
       })
       //方式二：
       $(function(){
           ...
       })
       //相当于原生js中的DOMContentLoaded（比load更快）
       //不同于原生js中的load事件，时等页面文档、外部js文件、css文件、图片加载完毕才执行内部代码
       ```

    4. `$`是jQuery的别称，代码中可以使用`jQuery`代替`$`，为了方便，通常直接使用`$`。

    5. `$`是jQuery中的顶级对象，相当于原生JavaScript中的window。把元素利用`$`包装成jQuery对象，就可以使用jQuery的方法

    6. **jQuery对象和DOM对象**

       1. 用原生JS获取来的对象就是DOM对象。
          1. 比如`var myDiv = document.querySelector('div')`;

       2. jQuery伙房获取的元素就是jQuery对象。
          1. `$('div');`

       3. `console.log(myDiv)` 与 `console.log($('div')) `获取的对象不一样
          1. jQuery获取以伪数组形式得到对象，有索引值`0:div`和长度`length: 1`
          2. DOM获取的有很多的属性和方法。

       4. **jQuery对象只能使用jQuery方法，DOM对象使用原生的JavaScript属性和方法**。

    7. **jQuery对象和DOM对象相互转换**

       1. DOM对象**转换为jQuery对象**：
          1. `$(DOM对象)` 或 `$(DOM标签)`

       2. jQuery对象**转换为DOM对象**：
          1. `$(DOM)[index]` ：index是索引号，一般取0即可。
          2. `$(DOM).get(index)`：index是索引号

50. ## jQuery常用API-选择器

    1. jQuery选择器：`$('选择器')` 。里面选择器**直接写CSS选择器**即可， 但是要加引号。

       | 名称       | 用法            | 描述                     |
       | ---------- | --------------- | ------------------------ |
       | ID选择器   | $("#id")        | 获取指定ID的元素         |
       | 全选择器   | $('*')          | 匹配所有元素             |
       | 类选择器   | $('.class')     | 获取同一类class的元素    |
       | 标签选择器 | $("div")        | 获取同一类标签的所有元素 |
       | 并集选择器 | $("div, p, li") | 选取多个元素             |
       | 交集选择器 | $("li.current") | 交集元素                 |

    2. jQuery层级选择器

       | 名称       | 用法       | 描述                                                         |
       | ---------- | ---------- | ------------------------------------------------------------ |
       | 子代选择器 | $("ul>li") | 使用>号，湖区亲儿子层级的元素；注意，并不会获得孙子层级的元素 |
       | 后代选择器 | $("ul li") | 使用空格，代表后代选择器，获取ul下的所有li元素，包括孙子等   |

    3. **jQuery隐式迭代**

       1. jQuery设置样式：$('DOM').css('属性'，'值')
       2. 页面多个DOM标签时，一次性获取并且可以使用`.css()`一次性给所有赋值
       3. **遍历内部DOM元素**（伪数组形式存储）的过程叫做隐式迭代。
       4. 比如`$('div').css('background-color','pink');`，把每个div都设置背景颜色，相当于原生JS 的 for循环

    4. **jQuery筛选选择器**

       | 语法       | 用法          | 描述                                                        |
       | ---------- | ------------- | ----------------------------------------------------------- |
       | :first     | $('li:first') | 获取第一个li元素                                            |
       | :last      | $('li:last')  | 获取最后一个li元素                                          |
       | :eq(index) | $("li:eq(2)") | 获取到的li元素中，选择索引号伪2的元素，索引号index从0开始。 |
       | :odd       | $("li:odd")   | 获取到的li元素中，选择索引号伪奇数的元素                    |
       | :even      | $("li:even")  | 获取到的li元素中，选择索引号伪偶数的元素                    |

    5. **jQuery筛选方法**

       | 语法/均为方法        | 用法                             | 说明                                                   |
       | -------------------- | -------------------------------- | ------------------------------------------------------ |
       | `parent()`           | `$("li").parent()`               | 查找父级                                               |
       | `children(selector)` | `$("ul").children("li")`         | 相当于`$("ul>li")`，最近一级（亲儿子）                 |
       | `find(selector)`     | `$("ul").find("li")`             | 相当于`$("ul li")`，后代选择器                         |
       | `siblings(selector)` | `$(".first").siblings("li")`     | 查找兄弟节点，不包括自己本身                           |
       | `nextAll([expr])`    | `$(".first").nextAll()`          | 查找当前元素之后的所有同辈元素                         |
       | `prevtAll([expr])`   | `$(".last").prevAll()`           | 查找当前元素之前的所有同辈元素                         |
       | `hasClass(class)`    | `$('div').hasClass("protected")` | 检查当前的元素是否含有某个特定的类，如果有，则返回true |
       | `eq(index)`          | `$("li").eq(2)`                  | 相当于`$("li:eq(2)")`,index从0开始                     |

    6. **jQuery排他思想**

       1. 想要多选一的效果，排他思想：当前元素设置样式，其余的兄弟元素清除样式。

          ```js
          $(function(){
              //1.隐式迭代 给所有的按钮都绑定了点击事件
              $("button").click(function(){
              //2.当前元素变化背景颜色
              $(this).css("background","pink");
              //3.其余兄弟去掉背景颜色
              $(this).siblings("button").css("background","");
          })
          })
          ```

    7. 案例：tab分栏 [案例地址](https://github.com/brant8/vue2-3-Css/blob/main/js%E9%BB%91%E9%A9%AC%E4%BB%A3%E7%A0%81/023demo_jquery_tab.html)

       1. 核心原理：鼠标经过左侧某个li，就让内容区盒子对应图片显示，其余图片隐藏。
       2. 需要得到当前li的索引号，就可以显示对应索引号的图片
       3. jQuery得到当前元素索引号`$(this).index()`
       4. 中间对应的图片，可以通过`eq(index)`方法取选择
       5. 显示元素show()，隐藏元素hide()
       6. 链式编程：$(元素).css(..).$(其他元素).css(..)
          1. 比如：`$(this).css("color","red).siblings().css('color',"")`

51. ## jQuery常用API-操作CSS方法

    1. jQuery可以使用css方法来修改简单元素样式，也可以操作类，修改多个样式

    2. 操作一：参数只写属性名，则是返回属性值。

       ```js
       $(this).css("color");
       ```

    3. 操作二：参数是属性名，属性值，逗号分隔，是设置一组样式；*属性必须加引号*，值如果是数字可以不用跟单位和引号。

       ```js
       $(this).css("color","red");
       ```

    4. 操作三：参数可以是对象形式，方便设置多组样式。属性名和属性值用冒号隔开，属性可以不用加引号。

       ```js
       $(this).css({
           "color":"white", 
           "font-size":"20px",
           "background-color":"red"
       });
       //或者
       $(this).css({
           color:"white", 
           font-size:"20px",
           backgroundColor:"red" //不加引号用驼峰
       });
       ```

    5. 设置类样式方法

       1. 作用等同于以前的classList，可以操作类样式，注意操作类里面的参数不要加点。

       2. 添加类

          ```js
          $("div").click(function(){
          	$(this).addClass("current");
          })
          ```

       3. 删除类

          ```js
          $("this").removeClass("current");
          ```

       4. 切换类

          ```js
          //原生js：使用变量点击取反
          $("this").toggleClass("current");
          ```

    6. 案例：tab栏切换

       1. 点击上部的li，当前li添加current类，其余兄弟移除类

       2. 点击的同时，得到当前 li 的索引号

       3. 让下部里面相应索引号的item显示，其余的item隐藏

          ```js
          $(function(){
              $(".tab_list li").click(function(){
                  //链式
                  $(this).addClass('current').siblings().removeClass('current');
                  var index = $(this).index();
                  $(".tab_con .item").eq(index).show().siblings().hide();
              })
          })
          ```

       4. 原生 js写法

          ```js
          //tab原生JS写法
          var tab_list = document.querySelector(".tab_list");
          var lis = tab_list.querySelectorAll('li');
          var items = document.querySelectorAll('item');
          //for循环绑定点击事件
          for(var i =0;i<lis.length;i++){
              //开始给5个li 设置索引号
              lis[i].setAttribute('index',i);
              lis[i].onclick=function(){
                  //上面模块选项卡，点击某一个，当前这个底色会变红，其余不变（排他思想），修改类名的方式
                  //干掉所有人，其余的li清除 class
                  for(var i =-;i<lis.length;i++){
                      lis[i].className="";
                  }
                  //留下自己
                  this.className = 'current';
                  //下面的显示内容模块
                  var index = this.getAttribute('index');
                  console.log(index);
                  //干掉所有人，让其余的item这些div 隐藏
                  for(var i=0;i<items.length;i++){
                      items[i].style.display='none';
                  }
                  //留下自己，让对应的item显示出来
                  items[index].style.display='block';
              }
          }
          ```

    7. **类操作与className区别**

       1. 原生JS 中 className会覆盖元素原先里面的类名。

       2. jQuery里面类操作知识堆指定类进行操作，不影响原先的类名。

          ```html
          <div class="one"></div>
          <script>
          	var one = document.querySelector('.one');
              one.className = "two";//覆盖原先的one
              //既有one也有two，追加类名
              $(".one").addClass("two");
          </script>
          ```

52. ## jQuery效果

    1. **显示与隐藏**：`show()`、`hide()`、`toggle()`

       1. 语法规范：`show([speed,[easing],[fn]])`
          1. 显示参数，参数都可以省略，无动画直接显示
          2. speed：三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画市场的毫秒数值，如1000。
          3. easing：(Optional)用来指定切换效果，默认是"swing"，可用参数"linear"。
          4. fn：回调函数，再动画完成时执行的函数，每个元素执行一次。

       2. 语法规范：`hide([speed,[easing],[fn]])`
       3. 语法规范：`toggle([speed,[easing],[fn]])`

    2. **滑动**：`slideDown()`、`slideUp()`、`slideToggle()`

       1. 语法规范：`slideDown([speed,[easing],[fn]])`

    3. **淡入淡出**：`fadeIn()`、`fadeOut()`、`fadeToggle()`、`fadeTo()`

       1. 语法规范：`slideIn([speed],[easing],[fn]])`
       2. 语法规范：`slideOut([speed],[easing],[fn]])`
       3. 语法规范：`slideTo([speed], opacity, [easing],[fn]])`; 渐进方式调整到指定的不透明度（修改）
          1. opacity：透明度必须且，取值 0~1 之间。
          2. speed：必须写。
          3. easing、fn：可选

       4. 语法规范：`slideToggle([speed,[easing],[fn]])`

    4. **自定义动画**：`animate()`

       1. 语法规范：`animate(params,[speed],[easing],[fn])`

       2. params：想要更改的样式属性，以对向行驶传递，必须写。属性名可以不用带引号，入股偶时符合属性则需要采取驼峰命名法borderLeft。*其余参数都可以省略*。

       3. speed：三种预定速度之一的字符串("slow", "normal", or "fast")或表示动画市场的毫秒数值，如1000。

       4. easing：(Optional)用来指定切换效果，默认是"swing"，可用参数"linear"。

       5. fn：回调函数，在动画完成时执行的函数，每个元素执行一次。

          ```html
          <button>动起来</button>
          <script>
          $(function(){
          	$("button").click(function(){
                  $("div").animate({
                      left:500,
                      top:300,
                      opacity: .4,
                      width:600,
                  },500);
              })
          }
          </script>
          ```

       6. 

    5. **事件切换**：`hover([over,] out)`

       1. over：鼠标移到元素上要触发的函数（相当于mouseenter）

       2. out：鼠标移出元素要触发的函数（相当于mouse leave）

          ```js
          $(".nav>li").hover(function(){..}, function(){..});
          //场景 导航效果
          $(".nav>li").hover(function(){ //over
              $(this).children("ul").slideDown(200);
          }, function(){//out
              $(this).children("ul").slideUp(200);
          });
          ```

       3. hover(fn)：只写一个函数，在鼠标移动到该元素和离开该元素时都会触发该函数。

          ```js
          $(".nav>li").hover(function(){ //只写一个函数通常配合Toggle
              $(this).children("ul").slideToggle(200);
          });
          ```

    6. **jQuery动画对立即其停止排队方法**

    7. 动画或效果队列：动画或者效果一旦触发就会执行，如果多次触发，就会造成多个动画或者效果队列执行。

       1. 比如导航栏`nav li`来回移动hover出现下拉菜单，会全部触发，造成所有动画一起出现。

    8. 停止排队：`stop()`

       1. stop()：用于停止动画或效果

       2. 注意：stop()写到动画或者效果的前面，相当于停止结束上一次的动画。

          ```js
          $(".nav>li").hover(function(){ 
              //stop()必须写到动画的前面，结束上一次的动画 比如：多次打开下拉导航，结束上次导航
              $(this).children("ul").stop().slideToggle(200);
          });
          ```

    9. 案例：突出当前图片 [案例链接](https://github.com/brant8/vue2-3-Css/blob/main/js%E9%BB%91%E9%A9%AC%E4%BB%A3%E7%A0%81/025demo_jquery_fade.html)

       ```js
       $(function(){
           $(".wrap li").hover(function(){
               //鼠标进入的时候，其他的li标签透明度：0.5
               //当前元素的其他兄弟元素，400ms时候更改透明度为0.5
               $(this).siblings().stop().fadeTo(400,0.5); //注意stop()，没有stop突出想过来回摆动
           },function(){
               //鼠标离开，其他li 透明度改为1
               $(this).siblings().stop().fadeTo(400,1);
           });
       })
       ```

    10. 案例：王者荣耀英雄，手风琴特效 [案例地址](https://github.com/brant8/vue2-3-Css/blob/main/js%E9%BB%91%E9%A9%AC%E4%BB%A3%E7%A0%81/026demo_jquery_king.html)

        1. 手风琴每个位置2张图片，头像和人物，头像压在人物介绍上层。

        2. 小图片可看到，大图片不可见display:none。动画需要定位，absolute

        3. 当前current默认可见大图，小图不可见

        4. 鼠标经过当前li实现两个步骤

        5. 当前li宽度变为224px，同时里面的小图片淡出，大图片淡入。

        6. 其余兄弟li宽度变为69px，小图片淡入，大图片淡出

           ```js
           $(function(){
               $(".king li").mouseenter(function(){
                   //每个 效果之前都要注意 对象.stop()
                   $(this).stop().animate({
                       width:224,
                   }).find(".small").stop().fadeOut().siblings(".big").stop().fadeIn();
                   $(this).siblings("li").stop().animate({
                       width:69
                   }).find(".small").stop().fadeIn().siblings(".big").stop().fadeOut();
               })
           })
           ```

53. ## jQuery属性操作

    1. 设置或获取元素**固有属性值** `element.prop()`

       1. 获取： `$("a").prop("href")`

       2. 设置：`$("a").prop("title","我还可以")`

       3. 复选框：

          ```js
          $(function(){
            $("input").change(function(){ //change相当于vue的watch事件
                console.log($(this).prop("checked"));
            })  
          })
          ```

       4. 获取不到结果：undefined

    2. 设置或获取元素**自定义属性值** `element.attr()`

       1. 获取：`attr("属性")` ；类似原生`getAttribute()`
       2. 设置：`attr("属性","属性值")`；类似原生`setAttribute()`；*设置完后可在DOM（HTML页面）看到该值*
       3. 也可获取 H5自定义属性 `<div class="one" data-index="4">` 中的`data-index` (`data-`开头)

    3. **数据缓存** `data()`

       1. 可以在指定元素上存取数据，并不会修改DOM元素结构（*HTML页面看不到该元素和值*）。一旦页面刷新，之前存放的数据都将被移除。
       2. data() 里面的数据是存放在元素的内存里面。
       3. 设置：`$("span").data("uname","andy")`
       4. 获取：`$("span").data("uname")`
       5. 可获取HTML5自定义属性 `data-index`，得到的是数字型。
          1. 获取不用写`data-`：`$("div").data("index")`

    4. 案例：购物车案例 - 全选分析

       1. 思路：里面3个小的复选框按钮（j-checkbox）选中状态（checked）跟着全选按钮（checkall）走。

       2. 因为checked是复选框的固有属性，此时需要利用prop()方法获取和设置该属性

       3. 把全选按钮状态赋给3个小复选框即可

          ```js
          $(function(){
              $(".checkall").change(function(){
                  //$(this).prop("checked");
                  //多个全选按钮，头部一个，尾部一个；$(".j-checkbox, .checkall")表示不同位置的两个class
                  $(".j-checkbox, .checkall").prop("checked",$(this).prop("checked"));
              })
          })
          ```

       4. 单击每个选项框，当每个选项框都勾选，全选也自动勾选

       5. `:checked`选择器； 查找被选中的表单元素

          ```js
          $(function(){
          	$(".j-checkbox").change(function(){
                  //console.log( $(".jcheckbox:checked") ); 得到伪数组，包含length
                  if( $(".jcheckbox:checked").length === $('.j-checkbox').length ){ //被选中的小的复选框个数
                      $(".checkall").prop("checked", true);
                  }else{
                      $(".checkall").prop("checked", false);
                  }
              })
          })
          ```

54. ## jQuery内容文本值

    1. 主要针对 *元素的内容* 还有 *表单的值* 的操作。

    2. 普通元素内容： `html()` ，相当于原生`innerHTML`

       1. 获取：`$(element).html()`
       2. 设置：`html('内容')`

    3. 普通元素文本内容：`text()`，相当于原生`innerText`

       1. 获取：`$(element).text()`
       2. 设置：`$(element).text('内容')`

    4. 表格元素的值：`val()` ,input等表格元素

       1. 获取：`$(element).val()`
       2. 设置：`$(element).val('内容')`

    5. 案例：购物车商品数量赋值

       1. 思路：首先声明一个变量，当我们点击+号increment，就让这个值++，然后赋值给文本框。

       2. 注意：只能增加本商品的数量，就是当前+号的兄弟文本框itxt 的值

       3. 修改表单的值是val() 方法

       4. 注意：这个变量初始值应该是这个文本框的值，在这个值的基础上++，要获取表单的值。

          ```js
          // - 文本框input +
          $(".increment").click(function(){
              var n = $(this).siblings(".itxt").val();
              console.log(n);
              n++;
              $(this).siblings(".itxt").val(n); //文本框、表单获取值val()
          })
          ```

       5. 减号 decrement思路同理，但是如果文本框的值是1，就不能再减了。

          ```js
          $(".decrement").click(function(){
              var n = $(this).siblings(".itxt").val();
              if(n == 1){
                  return false;
              }
              n--;
              $(this).siblings(".itxt").val(n);
          })
          ```

       6. 多层级父级

          ```js
          $('.four').parent().parent().parent(); //伪数组.substr(index) 可以得到其值
          $('.four').parents(); //得到直至html的元素 的伪数组
          $('.four').parents(".one"); //得到.one的父级元素
          ```

       7. 保留两位小数：`数字.toFixed(2)`

       8. 用户直接修改表单里面的值，同样可以计算价格。表单用`change`事件

55. ## jQuery元素操作

    1. 主要遍历、创建、添加、删除元素操作。

    2. **遍历元素**

       1. jQuery隐式迭代时堆同一类元素做了同样的操作。如果想要给同一类元素做不同操作，就需要用到遍历。
       2. 语法一：`$("div").each(function(index, domEle){ ... })`
          1. `each()`方法遍历匹配的每一个元素。*主要用DOM处理，each每一个*。
          2. 里面的回调函数有2个参数：`index`是每个元素的索引号，`demEle`是每个*DOM元素对象*，不是jquery对象。
          3. 想要使用jquery方法，需要给这个dom元素转换为jquery对象`$(domEle)`

       3. 语法二：`$.each(object, function(index, element){ ... })`
          1. `$.each()`可以遍历任何对象。*主要用于数据处理，比如数组、对象*。
          2. 里面有两个参数：index是每个元素的索引号；element 遍历内容
          
     ```js
       $(function(){
           //同一类元素同样操作
           $("div").css("color","red"); //隐式遍历
           //.each()遍历不同操作
           var arr = ["red","green","blue"];
           $("div").each(function(index,domEle){
               //回调函数第一个参数一定是索引号，此处形参
               console.log(index);//每次循环自动加1
               //第二个参数是DOM元素对象，比如获取的结果：<div>2</div> ,DOM没有css方法，jQuery有
               $(domEle).css("color","blue"); //举例
               $(domEle).css("color",arr[i]); 
           })
           //$.each()遍历任何对象 , jQuery对象
           $.each( $("div"),function(index,element){
               console.log(index + " : " + element);
           } );
           //数组对象
           $.each(arr, function(index, element){
               console.log(index + " : " + element);
           });
           //对象
           $.each({
               name:"andy",
               age:18
           },function(index,ele){
               console.log(index);//输出的是 name ,age 属性名
               console.log(ele); // 输出的是 andy 18 属性值
           })
       })
     ```

    3. 案例：购物车不同商品数量相加

       1. 注意场景应用，`$(domEle).text()`获取到的数字不是Int类型，需要转换`parseInt(值)`

    4. **创建元素**

       1. `$("创建元素内容")`

          ```js
          var li = $("<li>我是新创建的</li>")
          ```

       2. 需要添加后才能显示

    5. **添加元素**

       1. 内部添加：`.append("内容")`、`.prepend("内容")`

          1. 把内容放入匹配元素内部最后面，类似原生`appendChild`

          ```js
          $("ul").append(li);  //li添加到ul内li的最后
          $("ul").prepend(li); //li添加到ul内li的前面
          ```

       2. 外部添加：`.after("内容")`、`.before("内容")`

          ```js
          var div=$("<div>新生div</div>");
          $(".test").after(div);  //把内容放入目标元素后面
          $(".test").before(div); //把内容放入目标元素前面
          ```

       3. 内部添加元素，生成之后，是父子关系。

       4. 外部添加元素，生成之后，是兄弟关系。

    6. **删除元素**

       1. `$(element).remove()`：删除匹配的元素（本身）
       2. `$(element).empty()`：删除匹配的元素集合中所有的子节点及其内容、孩子
       3. `$(element).html("")`：删除匹配的元素集合中所有的子节点及其内容、孩子

    7. 案例：删除商品模块

       1. 商品后面的删除按钮

          ```js
          $(".p-action a").click(function(){
              //删除当前商品
          	$(this).parents(".cart-item").remove();
          })
          ```

       2. 删除选中的商品

          ```js
          $(".remove-batch").click(function(){
              //删除小的复选框中的商品
              $(".j-checkbox:checked").parents(".cart-item").remove();//隐式迭代
          })
          ```

       3. 清空购物车，删除全部商品

          ```js
          $(".clear-all").click(function(){
              //删除小的复选框中的商品
              $(".cart-item").remove();//隐式迭代
          })
          ```

56. ## jQuery尺寸、位置操作

    1. **jQuery尺寸**

       | 语法                                | 用法                                                  |
       | ----------------------------------- | ----------------------------------------------------- |
       | width() 、 height()                 | 取得匹配元素宽度和高度值，只算width、height           |
       | innerWidth()、innerHeight()         | 取得匹配元素宽度和高度值，包含padding                 |
       | outerWidth()、outerHeight()         | 取得匹配元素宽度和高度值，包含padding、border         |
       | outerWidth(true)、outerHeight(true) | 取得匹配元素宽度和高度值，包含padding、border、margin |

    2. 以上参数为空，则是获取相应值，返回的是数字型。

    3. 如果参数为数字，则是修改相应值。

    4. 参数可以不必写单位。

    5. **jQuery位置**

       1. 主要有：`offset()`、`position()`、`scrollTop()`、` scrollLeft()`

    6. `offset()`：设置或获取元素偏移

       1. 设置或返回被选元素 *相对于文档*  的偏移坐标，跟父级没有关系。
       2. 偏移属性为：top、left；表示举例文档顶部和左侧的距离。
          1. 比如`console.log( $(".son").offset().top )`

       3. 设置元素的偏移：`.offset({ top:10, left:30 })`

    7. `position()`：获取元素偏移（只能获取不能设置）

       1. 获取距离带有定位父级位置（偏移），如果没有带有定位的父级，则以文档为准。

    8. `scrollTop()`、` scrollLeft()`：设置或获取元素被卷去的头部和左侧

       1. `scrollTop()`：设置或返回被选元素被卷去的头部。

       ```js
       $(function(){
           //页面滚动事件
           $(window).scroll(function(){
               console.log($(document).scrollTop());
           })
       })
       //带有动画的返回顶部
       $(".back").click(function(){
           $("body,html").stop().animate({ //animate只有元素才能做动画
        // $(document).stop().animate({.. //文档无法做动画
               scrollTop:0
           })
       })
       ```

    9. 案例：电梯导航

       1. 侧边栏悬浮导航，点击快速定位到对应内容区域。

       2. 核心算法：电梯导航模块和内容区模块一一对应。

       3. 当点击电梯导航某个小模块，就可以拿到当前小模块的索引号。

       4. 把animate移动的距离求出来：当前索引号内容区模块它的`offset().top`

       5. 然后执行动画

          ```js
          $(".fixedtool li").click(function(){
              console.log($(this).index());
              //当每次点击li，就需要计算出页面要去往的位置
              //选出对应索引号的内容区的盒子，计算他的offset().top
              var current = $(".floor .w").eq($(this).index()).offset().top;
              //页面动画滚动效果
              $("body,html").stop().animate(function(){
                  scrollTop:current
              });
              //点击之后，当让钱li添加current类名让其高亮，兄弟类移除current
              $(this).addClass("current").siblings().removeClass();
              //.removeClass()当只有一个类名时，无需添加removeClass("current")
          })
          ```

       6. 使用滚动条页面滚动时，电梯导航做出相应变化，此功能要写道页面滚动事件中区。

       7. 用到each，遍历内容区域大模块。each能拿到内容区域每一个模块元素和索引号

       8. 判断条件：被卷去的头部大于等于 内容区域里面每个模块的`offset().top`

          ```js
          $(window).scroll(function(){
              //页面滚动到某个区域内，左侧电梯导航li相应添加和删除current类
              $(".floor .w").each(function(index,element){
                  if(($(document).scrollTop() >= $(element).offset().top){
                  	console.log(i);
                  	$(".fixedtool li").eq(i).addClass("current").siblings().removeClass();
                  }
              })
          })
          ```

       9. 当既有滚动和点击切换时，点击电梯导航时滚动条也会走动，造成电梯导航中的li会有其他非必要高亮显示，如不按顺序点击时。

       10. 节流阀/互斥锁：点击li时，把页面滚动事件的li添加current行为锁住。

           ```js
           var flag=true;
           if(flag){
               //点击或滚动各自的事件
               //动画执行完毕后，使用回调函数解锁，比如animate({..},function(){ flag = true; })
           }
           ```

57. ## jQuery事件

    1. 单个事件注册
       1. 语法：`element.事件(function(){})`
       2. 比如：`$("div").click(function(){ 事件处理程序 })`
       3. 其他事件基本和原生一致：mouseover、mouseout、blur、focus、change、keydown、keyup、resize、scroll等

    2. **事件处理`on()`绑定事件及优势一**

       1. `on()`在匹配元素上绑定一个或多个事件事件处理函数

       2. 语法：`element.on(events, [selector], fn)`

       3. events：一个或多个用空格分隔的事件类型，如"click"或"keydown"

       4. selector：元素的子元素选择器

       5. fn：回调函数，即绑定在元素身上的侦听函数

          ```js
          //不同事件不同处理函数
          $("div").on({
              mouseenter:function(){
                  $(this).css("background","skyblue");
              },
              click:function(){
                  $(this).css("background","purple");
              },
              mouseleave:function(){
                  $(this).css("background","blue");
              }
          })
          //不同事件，如果事件处理程序相同
          $("div").on({"mouseenter mouseout", function(){
              $(this).toggleClass("current");
           	},
          })
          ```

    3. **`on()`方法优势二**：

       1. 可以事件委派操作。事件委派定义就是，把原来加给子元素身上的事件绑定在父元素身上，就是把事件委派给父元素。

          ```js
          //jQuery隐式迭代，给ul和li分别绑定了点击事件
          $("ul li").click();
          //把点击事件委派给子元素 li；理论：点击li，li冒泡传递给ul的点击触发事件函数
          $("ul").on("click","li",function(){
              alert("hello world");
          })
          ```

       2. 旧版本：bind()、live()、delegate()等方法来处理时间绑定或者事件委派，最新版本用on替代她们。

    4. **`on()`方法优势3**：

       1. 动态创建元素，`click()`没有办法绑定事件，`on()`可以给动态生成的元素绑定事件

          ```js
          $("ul li").click(function(){
              alert(11);
          });
          var li = $("<li>我是后创建的</li>");
          $("ol").append(li); //追加后点击后没有点击事件
          //on方法绑定事件 动态
          $("ol").on("click","li",function(){
              alert(11);
          });
          var li = $("<li>我是后创建的</li>");
          $("ol").append(li);  //追加后点击有绑定事件
          ```

    5. 案例：发布微博案例 [地址](https://github.com/brant8/vue2-3-Css/blob/main/js%E9%BB%91%E9%A9%AC%E4%BB%A3%E7%A0%81/027demo_jquery_on.html)

       1. 点击发布按钮，动态创建一个li，放入文本框的内容和删除按钮，并且添加到ul中。

       2. 点击的删除按钮，可以删除当前的微博留言。

          ```js
          $(function(){
              //添加动态元素
              $(".btn").on("click",function(){
                  var li=$("<li></li>");
                  li.html($(".txt").val() + "<a href='javascript:;'>删除</a>");
                  $("ul").prepend(li);
                  li.slideDown();
                  $(".txt").val("");
              });
              //删除动态元素
              $("ul").on("click","a",function(){
                  $(this).parent().slideUp(function(){ //slideUp向上拉，但是HTML的DOM元素还在，只是display:none
                      $(this).remove(); //删除该DOM元素
                  });
              })
          })
          ```

    6. **`off()`解绑事件**

       1. `off()`可以移除通过`on()`方法添加的事件处理程序

          ```js
          $(function(){
              $("div").on({
                  click:function(){
                      console.log("click me");
                  },
                  mouseover:function(){
                      console.log("hover over me");
                  }
              });
              $("div").off(); //解除了div身上所有的事件
              $("div").off("click");//解除了div身上的click事件
              $("ul").off("click","li"); //解除事件委托
          })
          ```

    7. **`one()`：事件只想触发一次**，可以使用`one()`来绑定事件。

    8. **`trigger()`自动触发事件**

       1. 方式一：`element.event()`

          ```js
          $(function(){
              div("div").on("click",function(){
                  alert(10);
              });
              //1.默认触发事件，不需要点击
              $("div").click(); 
              //2.自动触发事件
              $("div").trigger("click");
          })
          
          ```

       2. 方式二：`element.trigger("事件名")`

          1. 事件自动触发，比如轮播图，可以利用定时器自动触发右侧按钮点击事件，不必鼠标点击触发。

       3. 方式三：`element.triggerHandler("事件名")`

          1. 不会触发元素的默认行为。

          2. ```js
             $("input").on("focus",function(){//鼠标点击文本框
                 $(this).val("你好");//没有自动事件时，手动点击文本框后自动赋值，默认情况，鼠标光标会在文本框闪烁
             });
             $("input").triggerHandler("focus"); //有自动事件后，文本框自动赋值，但是不会有光标
             ```

    9. **事件对象**

       1. 事件被触发，就会有事件对象的产生。

       2. `element.on(events,[selector],function(event){})`

       3. 阻止默认行为：`event.preventDefault()` 或者 `return false`

       4. 阻止冒泡：`event.stopPropagation()`

          ```js
          $(function(){
              $(document).on("click",function(){
                  console.log("click document");
              });
              $("div").on("click",function(event){
                  console.log(event);
                  event.stopPropagation();//阻止冒泡
              });
          })
          ```

58. ## jQuery其他方法

    1. 对象拷贝：把某个对象拷贝（合并）给另外一个对象使用，可以使用`$.extend()`

       1. 语法：`$.extend([deep], target, object1, [objectN])`

       2. deep：如果设为true为深拷贝，默认为false浅拷贝。

       3. target：要拷贝的目标对象

       4. object1：待拷贝到第一个对象的对象

       5. 浅拷贝是把被拷贝的对象  *复杂数据类型中的地址拷贝*  给目标对象，修改目标对象会影响被拷贝对象。

          ```js
          $(function(){
              //目标有数据情况下，若冲突，会被覆盖（浅拷贝）
              var targetObj = {};
              var obj = {
                  id;1,
                  name:"andy",
                  msg:{	//复杂对象，该msg会新开辟空间，并使用地址指指向msg的地址
                  	age:18
              	}
              };
              $.extend(targetObj,obj); //复制的时候，targetObj得到msg地址值，若更改其msg指，其他复制的和原数据也会受影响
          })
          ```

       6. 深拷贝，前面加true，完全克隆（拷贝的对象，而不是地址），*修改目标对象不会影响被拷贝对象*。

          ```js
          $(function(){
              var targetObj = {
                  id:0,
                  msg:{
                      gender:male
                  }
              };
              var obj = {
                  id;1,
                  name:"andy",
                  msg:{	
                  	age:18
              	}
              };
              $.extend(true,targetObj,obj); //复制的时候，targetObj得到全部数据值，并且若目标对象原来有值，则会都保留。如msg，会新开辟空间保留msg:{gender:male, age:18}
          })
          ```

59. ## jQuery多库共存

    1. jQuery使用`$`作为标识符,suizhe jQuery的流行，其他js库也会用这`$`作为标识符，这样一起使用会引起冲突。

       ```js
       //$标识符的简单封装
       $(function(){
           function $(ele){
               return document.querySelector(ele);
           }
           //调用
           console.log($("div"));//此时$ 作为函数，非jQuery
       })
       ```

    2. 客观需求：

       1. 需要一个解决方案，让jQuery和其他的js库不存在冲突，可以同时存在，就叫做多库共存。

    3. jQuery解决方案：

       1. 把里面的`$`统一改为jQuery，比如`jQuery("div")`
       2. jQuery使用自定义标识符， `var xx = $.noConflict()`

60. ## jQuery插件

    1. jQuery功能比较优先，想要更复杂的特效效果，可以借助于jQuery插件完成。

    2. jQuery常用插件网站：

       1. jQuery插件库：www.jq22.com
       2. jQuery之家：www.htmleaf.com

    3. 图片懒加载 - LazyLoad

    4. 全屏滚动，类似幻灯片，fullPage.js

    5. Bootstrap，引入bootstrap.js后可以在使用用看到特效

    6. 【重点】案例：**toDoList** [案例地址](https://github.com/brant8/vue2-3-Css/blob/main/js%E9%BB%91%E9%A9%AC%E4%BB%A3%E7%A0%81/028demo_jquery_todoList.html)

       1. 刷新页面不会丢失数据，因此需要用到本地存储localStorage

       2. 思路：不管按下回车，还是点击复选框，都是把本地存储的数据加载到页面中，这样保证刷新关闭页面不会丢失数据

       3. 存储的数据格式：`var todolist = { {title:'xxx', done:false},{...} }`

       4. 本地存储只能存储字符串的数据格式，不能存数组`[ {},{} ]`。

       5. **把数组对象转换为字符串格式**：`JSON.stringify(XX)`

       6. `localStorage.setItem("todo", JSON.stringify(todolist))`；若保存的是非字符串，LocalStorage显示`Key: XX   Value: [object Object]`

       7. 获取本地存储数据：`var data = localStorage.getItem("todo")`；得到字符串

       8. **把字符串数据转换为 对象格式**：`JSON.parse(data)`，用于追加数据

       9. 判断是否回车： ’keydown事件‘ - ’函数的event参数’  -  ‘event.keyCode === 13‘

       10. 读取本地存储数据（数据转换成对象格式），遍历此数据`$.each()`，生成 li，添加到ol里。

       11. 每次渲染/遍历之前，要先把里面的ol内容清空，`$("ol").empty()`，然后渲染加载最新的数据，否则会出现多次渲染重复输出。

       12. 点击里面的a链接，不是删除li，而是删除本地鵆对应的数据。

       13. 原理：先u后去本地存储数据，删除对应的数据，保存给本地存储，重新渲染列表li。

       14. 给链接自定义属性记录当前索引号。根据索引号删除相关数据，数组的`splice(i,1)`方法

           ```js
           //div - a - a - a -div
           //ul - li-a - li-a -li-a -ul
           $("div a").click(function(){
               console.log($(this).index()); //亲兄弟时 得到索引号0、1、2
           })
           $("ul a").click(function(){
               console.log($(this).index());//非亲兄弟时 得到索引号0、0、0
           })
           //数组删除元素，arr.splice(开始删除的位置，1) 删除1个
           ```

       15. 动态创建元素使用on事件

           ```html
           <header>
               <section>
                   <label for="title">ToDoList</label>
                   <input type="text" id="title" name="title" placeholder="添加ToDo" required="required" autocomplete="off" />
               </section>
           </header>
           <section>
               <h2>正在进行 <span id="todocount"></span></h2>
               <ol id="todolist" class="demo-box">
           
               </ol>
               <h2>已经完成 <span id="donecount"></span></h2>
               <ul id="donelist">
           
               </ul>
           </section>
           <footer>
               Copyright &copy; 2014 todolist.cn
           </footer>
           
           <script>
               $(function(){
                   console.log("第一..");
                   $("#title").on("keydown",function(event){
                       console.log("第二.." + "event.key: "+ event.key + ", event.keyCode: "+ event.keyCode+", event.key: " + event.code);
                       if(event.keyCode === 13){
                           console.log("第三..")
                           //先读取本地存储原来的数据（经过转换后的得到的数组）
                           var local = getData();
                           console.log(local);
                           // 把local数组进行更新数据，把最新的数据追加给local数组
                           local.push({ title:$(this).val(), done:false });
                           saveData(local);
                           load();
                           $(this).val("");
                       }
                   });
                   load();
                   //删除操作
                   $("ol,ul").on("click","a",function () {
                       //获取本地存储
                       var data = getData();
                       console.log(data);
                       //修改数据
                       var index=$(this).attr("id");
                       data.splice(index,1);
                       //保存到本地存储
                       saveData(data);
                       //重新渲染页面
                       load();
                   });
                   //完成和未完成选项操作
                   $("ol,ul").on("click","input",function(){
                       //先获取本地存储
                       var data = getData();
                       //修改数据
                       console.log("修改状态是："+$(this));
                       var index=$(this).siblings("a").attr("id");
                       console.log(index);
                       data[index].done = $(this).prop("checked");
                       console.log(data);
                       //保存数据到存储
                       saveData(data);
                       //重新渲染页面
                       load();
                   })
           
                   //读取本地存储的数据
                   function getData(){
                       var data = localStorage.getItem("todolist");
                       if(data !== null){
                           //本地存储的数据是字符串格式，
                           return JSON.parse(data);
                       }else{
                           return []; //数据使用数组存储
                       }
                   }
                   function saveData(data){
                       localStorage.setItem("todolist", JSON.stringify(data));
                   }
                   //加载渲染数据
                   function load(){
                       var data = getData();
                       console.log(data);
                       $("ol,ul").empty();
                       var todoCount = 0;
                       var doneCount = 0;
                       $.each(data,function(i,n){ //n元素内容
                           console.log(n);
                           if(n.done){
                               $("ul").prepend("<li><input type='checkbox' checked='checked'><p>"+n.title+"</p><a href='javascript:;' id=" + i +"></a></li>");
                               doneCount++;
                           }else{
                               $("ol").prepend("<li><input type='checkbox'><p>"+n.title+"</p><a href='javascript:;' id=" + i +"></a></li>");
                               todoCount++;
                           }
                       });
                       $('#todocount').text(todoCount);
                       $('#donecount').text(doneCount);
                   }
           
               })
           </script>
           ```

61. ## 数据可视化

    1. 应用场景：通用报表、移动端图表、大屏可视化、图编辑&图分析、地理可视化

    2. 常见数据可视化库

       1. D3.js，目前Web端评价最高的Javascript可视化工具库（入手难）
       2. **ECharts.js**百度出品的一个开源Javascript数据可视化库（WPS）
       3. **Highcharts.js**国外的前端数据可视化库，非商用免费，国外许多大公司使用（Office）
       4. AntV蚂蚁金服全新一代数据可视化解决方案

    3. ECharts基本使用

       1. 下载并引入echarts.js文件
       2. 准备一个具备大小的DOM容器
       3. 初始化echarts实例（每个图标都要有自己的实例）
       4. 指定配置项和数据(option)
       5. 将配置项设置给echarts实例对象

       ```html
       <div class="box"></div>
       <script>
           // 1.基于准备好的dom，初始化echarts实例
           var myChart = echarts.init(document.querySelector('.box'));
           // 2.指定图表的配置项和数据
           var option = {
               title: {
                   text: 'ECharts 入门示例'
               },
               tooltip: {},
               legend: {
                   data: ['销量']
               },
               xAxis: {
                   data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
               },
               yAxis: {},
               series: [
                   {
                       name: '销量',
                       type: 'bar',
                       data: [5, 20, 36, 10, 10, 20]
                   }
               ]
           };
           // 3.使用刚指定的配置项和数据显示图表。
           myChart.setOption(option);
       </script>
       ```

    4. 相关基础配置讲解：

       1. title：标题组件
       2. tooltip：提示框组件
       3. legend：图例组件
       4. toolbox：工具栏
       5. grid：直角坐标系内绘图网格
       6. xAxis：直角坐标系grid中的x轴
       7. yAxis：直角坐标系grid中的y轴
       8. series：系列列表。每个系列通过type决定自己的图表类型（什么类型的图标）
          1. stack：数据堆叠，在前面数据基础上累加（非单例数据，实为累加数据），若不需要，可以给其不同值或者删掉即可。

       9. color：调色盘颜色列表

    5. 案例：数据可视化项目

       1. 适配方案一：（采用）

          1. **flexible.js**：检测浏览器宽度，修改html文字大小

             1. 屏幕分为24等分、在setRemUnit()更改值，10 -> 24.
             2. PC端效果图1920px

          2. **rem单位**：页面元素根据rem适配大小，配合VSCode的cssrem插件（更改Root Font Size 为80）。

             1. 基准值 1920/24=80px、rem值自动生成（正常输入XXXpx值，插件切换rem值供选择） 

          3. flex布局：页面快速布局

          4. PC端约束屏幕在1024 - 1920之间适配

             ```css
             @media screen and (max-width:1024px){
                 html{
                     font-size:42.66px!important;
                 }
             }
             @media screen and (min-width:1920px){
                 html{
                     font-size:80px!important;
                 }
             }
             ```

          5. 

       2. 适配方案二：

          1. @media媒体查询

       3. 背景图片缩放并且显示全部：`background-size: contain`；其他参数`cover`

          1. 或者：`background:url(../xx.jpg) no-repeat 0 0 /contain`

       4. flex均等分

          ```css
          .fat{
              display:flex;
          }
          .fat .son{ /*注意权重*/
              flex: 3;
          }
          .fat .son:nth-child(2) {
              flex:4;
          }
          ```

       5. 边框图片：盒子大小不一，但是边框样式相同，此时可以用边框图片来完成。

          1. CSS3中，新增`border-image`属性，允许一副图像作为元素的边框。

          2. 边框图片切图原理：[图](https://github.com/brant8/vue2-3-Css/blob/main/pictures/javascript_border_image.png)

             1. 把四个角切出去（九宫格的由来），中间部分可以铺排、拉伸或者环绕。
             2. 四个角：top-left、top-right、bottom-right、bottom-left
             3. 方位：top、right、left、bottom
             4. 中间：内容区

             | 属性                | 描述                                                         | 举例                                            |
             | ------------------- | ------------------------------------------------------------ | ----------------------------------------------- |
             | border-image-source | 用在边框的图片的路径（哪个图片）                             | `border-image-source: url(images/border.png) ;` |
             | border-image-slice  | 图片边框向内偏移（切割）。（剪裁的尺寸，一定不加单位，上右下左顺序） | `border-image-slice: 30 30 30 30;`              |
             | border-image-width  | 图片边框的宽度（需要加单位）（不是边框的宽度是边框图片的宽度），*大小不会挤压文字* | `border-image-width:30px；`                     |
             | border-image-repeat | 图像边框是否应平铺（repeat）、铺满（round）或拉伸（stretch），默认拉伸 | `border-image-repeat:repeat`                    |

          3. 注：平铺时，图片无法铺满则只会显示一半；铺满表示完整显示该图平铺。

          4. 图片边框宽度` border-image-width` 默认和边框宽度`border-width`一样。

             1. 只需设置`border-width`或者`border-image-width`其中一个即可。

          5. 被切割的内容区，会在盒子中间，若切割不是对称则错位。[图片](https://github.com/brant8/vue2-3-Css/blob/main/pictures/javascript_border_image2.png)

             1. 解决思路：在内容区设立一个盒子，因为绝对定位也无法影响其位置；让其拉伸与父盒子一样宽高即可，使用top、left定位。

             ```html
             <div class="viewport"> <!--大容器盒子-->
                 <div class="column"> <!--flex排列-->
                     <div class="panel"> <!--盒子切割部分-->
                         <div class="inner"> <!--内部拉伸部分-->
                             123
                         </div>
                     </div>
                 </div>
             </div>
             <style>
                 .panel{
                     position:relative;
                     border:15px solid transparent;/*透明色*/
                     border-width: 0.6375rem .475rem .25rem 1.65rem;
                     border-image-source:url(../images/border.png);
                     border-image-slice:51 38 20 132;
                 }
                 .inner{ /*内部盒子没有设置宽高，进行了拉伸； 相当于切割的“＃”中间内容区扩展到整个“#”字*/
                     position:absolute;
                     top: -0.6375rem;
                     left: -1.65rem;
                     right: -0.475rem;
                     bottom: -0.25rem;
                     background-red:red;
                     padding: .3rem .45rem;/*让盒子内的内容不会贴着边输出*/
                 }
             </style>
             ```

          6. flex平均分配的布局

             ```css
             .overview{
                 height: 1.375rem;
             } 
             overview ul{
                 display:flex;
                 justify-content: space-between;
             }
             ```

          7. 立即执行函数策略：

             1. JS文件中会有大量的变量名，特别是Echarts使用，需要大量初始化Echarts对象。
             2. 为了放置变量名冲突（污染），采用立即执行函数策略：`(function(){...})()`；（注意：需要入口函数，或者引入的js库放在html最底）

          8. 无缝滚动原理：

             1. 先克隆 marquee 里面所有的行（row）

                ```js
                $(".marquee-view .marquee").each(function(){ //tab有多个选项
                    var rows=$(this).children().clone();
                    $(this).append(rows);
                })
                ```

             2. 通过CSS3动画滚动

                ```CSS
                .marquee-view .marquee{
                    animation:move 15s linear infinite; /*匀速运动，无限*/
                }
                @keyframes move{
                    0%{}
                    100%{
                        transform: translateY(-50%); /*经过自身高度的一半（克隆后的高度）*/
                    }
                }
                /*鼠标经过marquee 就停止动画*/
                .marquee-view .marquee:hover{
                    animation-play-state: paused;
                }
                ```

62. ## JavaScript高级

    1. 类和对象

       1. 创建类`class A{}`
       2. 创建对象：`new A()`

    2. **类constructor构造函数**

       1. `constructor()`方法是类的构造函数（默认方法），用于传递参数，返回实例对象，通过new 命令生成对象实例时，自动调用该方法。如果没有显示定义，类内部会自动给我们创建一个`constructor()`

          ```js
          class Star{
              constructor(uname){
                  this.uname = uname; //this指向创建的实例
              }
          }
          var fj = new Star('李四');
          console.log(fj.uname);
          ```

       2. 通过class 关键字创建类，类名习惯性定义首字母大写

       3. 类里面的constructor函数，可以接收传递过来的参数，同时返回实例对象

       4. constructor函数只要new 生成实例，就会自动调用这个函数，如果不写，类也会自动生成这个函数

       5. 生成实例new  不能省略

       6. 语法规范：创建类，类名后面不要加小括号，生成实例，类名后面加小括号，构造函数不需要加function。

       7. 类中所有的函数不需要写function；多个方法之间，不需要用逗号分隔。

    3. **类的继承**

       1. 子类可以继承父类的属性和方法。

          ```js
          class Father{
              constructor(x,y){
                  this.x=x;
                  this.y=y;
              }
              sum(){
                  console.log(this.x+this.y); //此处的this指向构造器中传过来的参数
              }
          }
          class Son extends Father{
              constructor(x,y){
                  this.x=x; //此处需要使用super，方可以调用父类的sum方法传参计算
                  this.y=y;
                  //正确写法
                  super(x,y);//super必须在子类this之前调用
              }
          }
          var son = new Son(1,2); //此处子类的this指向子类的构造器参数
          son.sum(); //没有用super会出错，
          ```

    4. **`super`关键字**

       1. 用于访问和调用对象父类上的函数。可以调用父类的构造函数，也可以调用父类的普通函数。

    5. 继承中，如果实例化子类输出一个方法，先看子类有没有这个方法，如有有就先执行子类的。

    6. 继承中，如果子类里面没有，就去查找父类有没有这个方法，如果有，就执行父类的这个方法（就近原则）。

    7. `super()`必须在子类this之前调用

    8. ES6中，类没有变量提升，所以**必须先定义类**，才能通过类实例化对象。

    9. 类里面的共有的属性和方法一定要加`this`使用。

       ```js
       var that;
       var _that;
       class Star{
           constructor(uname,age){ //this指向实例对象
               that = this;
               this.uname = uname;
               this.age = age;
               this.sing();
               this.btn=document.querySelector('button');
               this.btn.onnclick=this.sing; //使用括号sing()表示立即调用；不加括号表示点击后调用
           }
           sing(){
               console.log(this);
               console.log(this.uname);
               console.log(that.uname);
           }
           dance(){
               _that = this;
               console.log(this);
           }
       }
       var ldh = new Star('刘德华');
       console.log(that === ldh); //true
       console.log(_that === ldh);//true
       ```

    10. **`this`指向问题**

        1. constructor里面的this 指向的时 创建的实例对象。
        2. dance方法中的this 指向的实例例对象
        3. sing方法中的this指向的是btn 这个按钮，因为是这个按钮调用了这个函数
           1. sing方法中的 `this.uname` 输出是`undefined`
           2. 若要使用实例对象的this可以使用赋值方法that 

    11. 案例：[面向对象版tab栏切换](https://github.com/brant8/vue2-3-Css/blob/main/js%E9%BB%91%E9%A9%AC%E4%BB%A3%E7%A0%81/030demo_obj_tab.html)

        1. 点击tab蓝，可以切换效果

        2. 点击+号，可以添加tab项和内容

        3. 点击x号，可以删除当前tab项和内容项

        4. 双击tab项文字或者内容项文字，可以修改里面的文字内容

        5. var和let对于for循环以及function使用的疑难解答，[地址](https://stackoverflow.com/questions/73408209/javascript-object-and-non-object-inside-for-loop-after-click)

        6. 添加元素

           1. 以前做法：动态创建元素createElement,但是元素内容较多，需要`innerHTML`赋值，在`appendChild`追加到父元素里面。
           2. **现在高级做法**：利用`insertAdjacentHTML()`可以直接把字符串格式元素**添加到父元素中**。
           3. 语法：`element.insertAdjacentHTML(position, text);`
           4. position是相对于元素的位置，必须使用以下字符串之一
              1. `beforebegin`：元素自身的前面
              2. `afterbegin`：插入元素内部的第一个子节点之前
              3. `beforeend`：插入元素内部的最后一个子节点之后
              4. afterend`：元素自身的后面。

           5. `appendChild`不支持追加字符串的子元素，`insertAdjacentHTML`支持追加字符串的元素。

        7. 添加元素后的问题，页面展示有bug，比如后加的元素没有动画效果

           1. 解决：类似套娃，产生新的元素后，重新调用获取全部元素，querySelectorAll，再重新进行事件绑定（封装成方法方便调用）

        8. 删除功能：关闭按钮没有索引号，但是其父亲`li`有索引号。

        9. 编辑选项卡li和section里面的文字，实现修改功能

           1. 双击事件：`ondbclick`

           2. 如果双击文字，默认选定文字，此时需要双击禁止选中文字。

              ```js
              window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
              ```

           3. 双击文字的时候，在里面生成一个文本框，当失去焦点或者按下回车后把文本框输入的值给原先的元素即可

63. ## 构造函数和原型

    1. ES6之前，JS中并没有引入类的概念。

    2. ES6，ECMAScript 6.0,2016.-6发版。目前浏览器主流ES5版本，多数高级版本浏览器支持ES6，不过只实现了ES6的部分特性功能。现已支持。

    3. **创建对象三种形式**

       1. 利用`new Object()`创建对象

          ```js
          var obj1 = new Object();
          ```

       2. 利用对象**字面量**创建对象

          ```js
          var obj2 = {};
          ```

       3. 利用构造函数创建对象

          ```js
          function Star(uname){  //构造函数构造对象 ES6之前的使用方式
              this.uname = uname;
              this.sing = function(){
                  console.log('嫦娥');
              }
          }
          var ldh = new Star('刘德华');
          ldh.sing();
          ```

    4. 构造函数：一种特殊的韩式，用来初始化对象，即对象成员变量赋初始值，与new 一起使用。可以把对象中一些公共的属性和方法抽取出来，封装到此函数中。

       1. 注意点一：**构造函数**用于创建某一类对象，**其首字母要大写**。
       2. 构造函数要和new 一起使用才有意义。

    5. **new** 在执行时会做四件事

       1. 在内存中创建一个新的空对象
       2. 让this 指向这个新的对象
       3. 执行构造函数里面的代码，给这个新对象添加属性和方法。
       4. 返回这个新对象（所以构造函数里面不需要return）。

    6. 构造函数中的属性和方法称为成员，成员可以添加。

       1. **实例成员**：
          1. 构造函数内部通过`this`添加的成员。
          2. 实例成员只能通过实例化的对象来访问。
          3. 如 `ldh.uname`
          4. 不可以通过构造函数来访问实例成员

       2. **静态成员：**
          1. 在构造函数本上上添加的成员
          2. 如 `Star.sex = '男'`
          3. 静态成员只能通过构造函数来访问
          4. 不能通过对象来访问

    7. 构造函数的问题：

       1. 构造函数方法`var sing = function(){}`很好用，但是其复杂数据类型会存在浪费内存问题。[图示](https://github.com/brant8/vue2-3-Css/blob/main/pictures/javascript_construct.png)
       2. 复杂类型另辟内存空间：`ldh.sing === zxy.sing`为false

    8. **构造函数原型 prototype**

       1. 构造函数通过原型分配的函数是所有对象所共享的。

       2. Javascript规定，**每一个构造函数都有一个prototype属性**，指向另一个对象。

       3. 注意：这个prototype就是一个对象，这个对象的所有属性和方法，都会被构造函数所拥有。

          ```js
          console.dir(Star);
          //输出：ƒ Star(uname,age)
          //		prototype: {constructor: ƒ}  	//花括号为一个对象
          ```

       4. 把那些不变的方法，直接定义在prototype 对象上，这样所有对象的实例就可以共享这些方法。

          ```js
          function Star(uname,age){
              this.uname = uname;
              this.age = age;
              this.sing = function(){
                  console.log('我唱歌');
              }
          }
          //原型对象 共享方法 ，所有实例共享同一个方法
          Star.prototype.singing = function(){
              console.log("我唱歌");
          }
          var ldh = new Star('刘德华', 18);
          ldh.sing();
          ldh.singing();
          ```

    9. **对象原型`__proto__`**

       1. 对象都会有一个属性`__proto__`：指向构造函数的prototype原型对象，之所以我们对象可以使用构造函数，prototype原型对象的属性和方法，就是因为对象有`__proto__`原型的存在。

       2. 对象身上系统自动添加`__proto__`： 

          ```js
          console.log(ldh);
          //结果包含：[[Prototype]]: Object
          //指向构造函数的原型对象
          console.log(ldh.__proto__ === Star.prototype); //结果为true 
          ```

       3. `__proto__`对象原型和原型对象prototype 是等价的。

       4. 方法的查找规则：

          1. 首先看实例对象 ldh 身上是否有 sing方法，如果有就执行这个对象的sing
          2. 如果没有sing 方法，因为有`__proto__`的存在，就去构造函数原型对象prototype身上查找sing这个方法。

       5. `__proto__`对象原型的意义就在于为对象的查找机制提供一个方向，或者说一条路线，但是它是一个非标准属性，因此实际开发中，不可以使用这个属性，它只是内部指向原型对象prototype。[图](https://github.com/brant8/vue2-3-Css/blob/main/pictures/javascript_proto.png)

    10. **`constructor`构造函数**

        1. 对象原型`__proto__`和构造函数`prototype`原型对象 里面都有一个属性constructor，称为构造函数，因为它指回构造函数本身。

           ```js
           console.log(Star.prototype.constructor);
           console.log(ldh.__proto__.constructor);
           //均指向构造函数如下输出
           ƒ Star(uname,age){
                   this.uname = uname;
                   this.age = age;
                   this.sing = function(){
                       console.log('我唱歌');
                   }
               }
           ```

        2. constructor主要记录该对象应用哪个构造函数，它可以让原型对象重新只想原来的构造函数。

        3. 很多情况下，我们需要手动利用constructor 这个属性指回 原来的构造函数

           ```js
           Star.prototype.sing = function(){
               console.log('我会唱歌');
           };
           Star.prototype.movie = function(){
               console.log('我会');
           }
           //对象形式写法，失去constructor的指向
           Star.prototype ={
               sing: function(){..},
               movie:function(){..}
           };
           //指向均为： ƒ Object() { [native code] }，需要手动重新添加指向
           console.log(Star.prototype.constructor);
           console.log(ldh.__proto__.constructor);
           //
           Star.prototype ={
               constructor: Star, //手动重新添加指向
               sing: function(){..},
               movie:function(){..}
           };
           ```

        4. 如果修改了原来的原型对象，如上例子，以对象形式给原型对象赋值，则必须手动的利用constructor指回原来的构造函数。

    11. 构造函数、实例、原型对象

        1. Star构造函数 ->  (`Star.prototype`) ->Star原型对象prototype
        2. Star原型对象prototype -> (`Star.prototype.constructor`) -> Star构造函数
        3. Star构造函数 -> ldh对象实例`ldh.__proto__.constructor` -> (`ldh.__proto__`)  ->Star原型对象prototype

    12. **原型链**

        1. `Star.prototype.__proto__ === Object.prototype`结果为true
        2. `console.log(Object.prototype.__proto__)`结果为null
        3. ![图](https://github.com/brant8/vue2-3-Css/blob/main/pictures/javascript_protochain.png)

    13. Javascript成员查找机制（规则）

        1. 当访问一个对象的属性（包括方法）时，首先查找这个对象自身有没有该属性。

        2. 如果没有就查找它的原型（也就是`__proto__`指向的prototype原型对象）。

        3. 如果还没有就查找原型对象的原型（Object的原型对象）。

        4. 以此类推一致找到Object位置（null）

           ```js
           function Star(uname,age){
               this.uname=uname;
               this.age=age;
           }
           Star.prototype.sing = function(){
               console.log('我会爬山');
           }
           //Star.prototype.sex = '女';
           //Object.prototype.sex = '男';
           var ldh = new Star('刘德华',18);
           //ldh.sex='男';
           console.log(ldh.sex);
           //Object.prototype有一个默认的toString方法: ƒ toString()
           console.log(Object.prototype);
           console.log(ldh); //没有toString，但是可以跟Object查找
           console.log(Star.prototype;)//没有toString，但是可以跟Object查找
           ```

    14. 原型对象this指向 [地址](https://github.com/brant8/vue2-3-Css/blob/main/js%E9%BB%91%E9%A9%AC%E4%BB%A3%E7%A0%81/033demo_proto_this.html)

        1. 构造函数中，里面的this指向的时对象实例ldh
        2. 原型对象函数里面的this，指向的时实例对象ldh

    15. 扩展内置对象

        1. 可以通过原型对象，对原来的内置对象进行扩展自定义的方法。比如给数组增加自定义求偶数和的功能。

           ```js
           console.log(Array.prototype);
           Array.prototype.sum=function(){
               var sum=0;
               for( var i = 0; i <this.length; i++){
                   sum +=this[i];
               }
               return sum;
           }
           var arr = [1,2,3];
           console.log(arr.sum());
           ```

        2. 对象式写法：覆盖（不可取）

           ```js
           Array.prototype={
               sum: function(){
                   var sum=0;
               	for( var i = 0; i <this.length; i++){
                   	sum +=this[i];
               	}
               	return sum;
               }
           }
           console.log(Array.prototype);
           var arr = [1,2,3];
           console.log(arr.sum());//报错，相当于把Array的prototype对象覆盖掉了
           ```

        3. 数组和字符串内置对象不能给原型对象覆盖操作`Array.prototype={}`，只能是`Array.prototype.xxx = function(){}`的方式。

64. ## 继承

    1. ES6之前并没有提供`extends`继承。可以通过构造函数+原型对象模拟实现继承，被称为组合继承。

    2. ES6之前通过 构造函数+ 原型实现面向对象编程。

    3. `call()`

       1. 调用这个函数，并且修改函数运行时的this 指向。

          ```js
          fun.call(thisArg, arg1, arg2,...)
          ```

       2. thisArg： 当前调用函数this的指向对象

       3. arg1，arg2：传递的其他参数

          ```js
          function fn(x,y){
              console.log("喝咖啡");
              console.log(this);//默认指向window
          }
          //以前调用方式：
          fun();
          //使用call调用
          fn.call(); //相当于window.fn.call()
          var o = {
              name='andy';
          }
          fn.call(o,1,2); //this指向对象o；并且fn可以接收参数1，2
          ```

    4. 借用**构造函数继承父类型属性** [示例讲解图](https://github.com/brant8/vue2-3-Css/blob/main/pictures/javascript_protochain2.png)

       1. 原理：通过`call()`把父类型的 this指向子类型的 this，这样就可以实现子类型继承夫类型的属性。

          ```js
          //父构造函数
          function Father(uname,age){
              //this指向父构造函数的对象实例
              this.uname=uname;
              this.age=age;
          }
          Father.prototype.money = function()[
              console.log(10000);
          ]
          //子构造函数
          function Son(uname,age){
              //this指向子构造函数的对象实例
              Father.call(this,uname,age); //修改父中的this，让其指向子，即可使用父构造函数的属性
          }
          //Son.prototype=Father.prototype; //Ⅰ.若赋值原型对象，当修改了子原型对象，父原型对象也会跟着一起变化
          ----
          //Ⅲ.Father原型对象Father.prototype 与 Father 实例对象new Father() 非同一个对象
          //Ⅳ.Father实例对象可以通过__proto__访问Father原型对象
          Son.prototype = new Father();
          //Ⅴ.如果利用对象的形式修改了原型对象，别忘了利用constructor指回原来的构造函数
          Son.prototype.constructor = Son;
          ----
          Son.prototype.exam = function(){
              console.log('考试'); 
          }
          var son = new Son('刘德华',18);
          console.log(son);
          console.log(Father.prototype); //Ⅱ.此时Father原型对象也有了exam()
          console.log(Son.prototype.constructor);//
          ```

65. ## 类

    1. ES6通过类实现面向对象编程

       ```js
       class Star{}
       console.log(typeof Star); //输出：function
       console.log(Star.prototype); //{constructor: ƒ}
       console.log(Star.prototype.constructor); //constructor指回类本身
       Star.prototype.sing = function(){
           console.log('并于');
       }
       ```

    2. 类的本质其实还是一个函数function，可以认为 类就是构造函数的另一种写法。

    3. 类的所有方法都定义在类的prototype属性上

    4. 类的创建的实例，里面也有`__proto__`指向类的prototype原型对象

    5. ES6的类的绝大部分功能，ES5都可以做到，新的class写法只是让对象原型的写法更加清晰、更像面向对象编程的语法。ES6的类其实就是语法糖。

    6. 语法糖：一种便携写法，简单理解，即两种方法可以实现同样的功能，但是一种写法更加清晰、方便，那么这个方法就是语法糖。

    7. **ES5新增方法**

       1. **数组方法**	

          1. 迭代遍历方法：`forEach()`、`map()`、`filter()`、`some()`、`every()`

             ```0
             array.forEach(function(currentValue, index, arr))
             ```

          2. currentValue：数组当前项的值

          3. index：数组当前项的索引

          4. arr：数组对象本身

          5. `forEach()`例子

             ```js
             var arr = [1,2,3];
             var sum=0;
             arr.forEach(function(value,index, array){
                 console.log('每个元素' + value);
                 console.log('每个数组的索引号' + index);
                 console.log('数组本身' + array);
                 sum+= value;
             })
             ```

          6. `filter()`例子

             ```js
             array.filter(function(currentValue,index,arr))
             //filter()方法创建一个新的数组，新数组中的元素是通过检查指定数组中符合条件的所有元素，主要用于筛选数组
             //他直接返回一个新数组（所有满足条件的元素）
             var arr = [12,66,4,88];
             var newArr=arr.filter(function(value,index){
                 return value >=20;
             })
             ```

          7. `some()`例子

             ```js
             //some()方法用于检测数组中的元素是否满足指定条件，通俗点：查找数组中是否有满足条件的元素
             //返回值是布尔值，如果查找到这个元素，就返回true，如果查找不到就返回false。
             //如果找到第一个满足条件的元素，则终止循环，不再继续查找
             array.some(function(currentValue,index,arr))
             ```

          8. `map()`与`forEach()`相似，`every()`与`some()`相似

          9. 案例：筛选查询数据表格中的数据

             1. 使用innerHTML动态添加数据。并且使用appendChild追加元素到DOM。

                ```js
                //1.获取相应的元素
                var tbody=document.querySelector('tbody');
                var search_price = document.querySelector('.search-price');
                var start = document.querySelector('.start');
                var end = document.querySelector('.end');
                setData(data);
                //2.把数据渲染到页面中
                function setData(mydata){
                    //渲染前先清空tbody数据
                    tbody.innerHTML='';
                    mydata.forEach(function (value) {
                        // console.log(value);
                        var tr=document.createElement('tr');
                        tr.innerHTML = '<td>'+ value.id+'</td><td>'+value.pname+'</td><td>'+value.price+'</td>';
                        tbody.appendChild(tr);
                    });
                }
                ```

             2. 按价格条件查询数据。单击了按钮，可以根据商品价格筛选数组里面的对象。

                ```js
                search_price.addEventListener('click',function(){
                    var newData = data.filter(function(data){
                        return data.price >= start.value && data.price <= end.value;
                    });
                    //渲染筛选玩后的数据到页面中
                    setData(newData);
                });
                ```

             3. 根据商品名称（唯一值）查找商品，使用`some()`查找到后布尔值判断，并且追加到一个新数组。

                ```js
                search_pro.addEventListener('click',function(){
                    var arr=[];
                    data.some(function(value){
                        if(value.pname === product.value){
                            arr.push(value);//追加到新数组
                            return true;
                        }
                    });
                    setData(arr);
                })
                ```

             4. 在`some()`里面遇到`return true`就会终止遍历，迭代效率更高。在`forEach()`和`filter()`使用`return true`不会终止遍历。

       2. **字符串方法**

          1. `trim()`方法回从一个字符换的两端删除空白字符

             ```js
             str.trim();
             var input = document.querySelector('input');
             console.log(input.value.trim());
             ```

             1. `trim()`方法并不会影响原字符串本身，它返回的是一个新的字符串。

       3. **对象方法**

          1. `Object.keys()`用于获取对象自身所有的属性

             ```js
             Object.keys(obj)
             ```

          2. 效果类似`for...in`； 返回一个由属性名组成的数组。

             ```js
             var obj = {
                 id: 1,
                 pname: '小米',
                 price: 1999,
                 num:2000
             }
             var arr = Object.keys(obj);
             arr.forEach(function(value){
                 console.log(value);
             })
             ```

          3. `Object.defineProperty()`定义新属性或修改原有的属性。

             ```js
             Object.defineProperty(obj, prop, descriptor)
             ```

             1. obj：必须，目标对象

             2. prop：必须：需要定义或修改的属性名字。若原来没有的属性，写完后自动添加进去。

             3. `descriptor`： 必须。目标属性所拥有的特性。以对象形式`{}`书写

                1. value：设置属性的值，默认为undefined
                2. writable：值是否可以重写。`true | false`默认为false
                3. enumerable：目标属性是否可以被枚举（能否遍历）。`true | false`默认false
                4. configurable：目标属性是否可以被删除或是否可以再次修改特性`true | false`默认为false

                ```js
                var obj = {
                    id: 1,
                    pname: '小米',
                    price: 1999,
                    num:2000
                };
                //以前修改方式：
                obj.num=1000;
                Object.defineProperty(obj, 'num', {
                    value:1000
                }); //相当于obj.num=1000
                Object.defineProperty(obj, 'id', {
                    writable:false, //不允许修改这个属性值
                    enumerable:false,//不允许被遍历显示
                    configurable:false//不允许被修改特性，比如删除；如果再定义一次id，修改其特性为true会报错。
                }); 
                obj.id=2;//不能修改，值不变还是1
                delete obj.id; //删除对象属性，此处无法删除，因设定
                ```

66. ## 函数进阶

    1. [函数的定义方式](https://github.com/brant8/vue2-3-Css/blob/main/pictures/javascript_Function.png) 

       ```js
       //1. 函数声明方式function关键字（命名函数）
       function fn(){};
       //2. 函数表达式（匿名函数）
       var fun = function(){};
       //3. new Function('参数1','参数2','函数体') ； 
       var f = new Function()
       
       var demo = new Function('console.log(123)');//函数体
       var demo2 = new Function('a', 'b', 'console.log(a+b)');
       demo2(1,2);
       ```

    2. **Function里面参数必须是字符串格式**

       1. new Function执行效率低，不方便书写，较少使用。
       2. 所有函数都是Function的实例对象。
       3. **只要是对象，都有原型**`__proto__`。
       4. 函数也属于对象  `console.log(demo2 instanceof Object)`

    3. **函数的调用方式**

       ```js
       //1.普通函数
       function fn(){
           console.log('人生的巅峰' + this);
       }
       fn(); //相当于window.fn() this指向window, [object Window]
       fn.call();
       
       //2.对象的方法
       var o = {
           sayHi:function(){
               console.log('人生的巅峰');
           }
       }
       o.sayHi(); //this指向o对象 [object Object]
       
       //3.构造函数
       function Star(){}
       Star.prototype.sing = function(){}
       var ldh = new Star(); //构造函数this 指向 ldh这个实例对象，原型对象里面的this 也指向ldh实例对象
       
       //4.绑定事件函数
       btn.onclick = function(){
           console.log('...'+ this);//点击即调用函数 [object HTMLButtonElement]
       }; 
       
       //5.定时器函数
       setInterval(function(){},1000);//定时器 自动调用
       window.setTimtout(function(){
           console.log(this); //this指向Window， [object Window]
       })
       
       //6.立即执行函数
       (function(){
           console.log('人生的巅峰' + this); //this指向 Window
       })(); //自动调用立即执行
       ```

    4. **函数内this的指向**

       1. this 的指向，是当调用函数的时候确定的。调用方式不同决定了this 的指向不同，一半指向我们的调用者。

          | 调用方式     | this指向                                   |
          | ------------ | ------------------------------------------ |
          | 普通函数调用 | window                                     |
          | 构造函数调用 | 实例对象，原型对象里面的方法也指向实例对象 |
          | 对象方法调用 | 该方法所属对象                             |
          | 事件绑定方法 | 绑定事件对象                               |
          | 定时器函数   | window                                     |
          | 立即执行函数 | window                                     |

    5. **改变函数内部this指向**

       1. JavaScript提供了一些函数方法来更优雅的处理函数内部 this 的指向问题，常用的`bind()`、`call()`、`apply()`。

       2. **call方法**

          ```js
          //调用一个对象。 既是调用函数的方式，也是可以改变函数的this指向
          fun.call(thisArg, arg1, arg2,...)
          var o = { name:'andy' };
          function fn(a,b){
              console.log(this);
              console.log(a+b);
          };
          fn.call(o,1,2);
          //call第一个可以调用函数，第二个可以改变函数内的this指向。
          //call的主要作用可以实现继承
          function Father(uname){
              this.uname=uname;
          }
          function Son(){
              Fatjer.call(this,uname);
          }
          var son = new Son('刘德华');
          ```

       3. **apply方法** 与数组操作

          ```js
          //apply()方法调用一个函数。可以调用函数，也可以改变函数的this指向
          fun.apply(thisArg, [argArray])
          ```

          1. thisArg：在fun函数运行时指定的this值

          2. argsArray：传递的值，必须包含在数组里面`[arr]`

          3. 返回值就是函数的返回值，因为它就是调用函数。

             ```js
             var o = {
                 name: 'andy'
             };
             function fn(arr){
                 console.log(this);
                 console.log(arr);
             };
             fn.apply(o,['pink']);
             //参数必须是数组形式（伪数组）
             //apply的主要应用：利用apply， 借助于数学内置对象求最大值
             //Math.max();
             var arr = [1,66,3,99];
             var max = Math.max.apply(null, arr);//null表示不需要改变this指向，求最大值
             //最合适写法：, Math调用
             var max = Math.max.apply(Math, arr);
             ```

       4. **bind方法**

          ```js
          //不会调用函数。但是能改变函数内部this指向
          fun.bind(thisArg, arg1, arg2,...)
          ```

          1. thisArg：在fun函数运行时指定的this值。

          2. arg1,arg2：传递的其他参数

          3. 返回由指定的this 值和初始化参数改造的原函数拷贝。

             ```js
             var o = {
                 name:'andy'
             };
             function fn(a,b){
                 console.log(this);
                 console.log(a+b)
             }
             var f = fn.bind(o, 1,2); //拷贝函数，不会调用原来的函数
             f();
             //应用场景：如果有的函数不需要立即调用，但又想改变这个函数内部的this时使用。
             //比如：点击按钮，就禁用这个按钮，3秒之后开启这个按钮。
             var btn = document.querySelector('button');
             btn.onclick = function(){
                 this.disabled = true; //这个this指向的时btn这个按钮
                 setTimeout(function(){
                     this.disabled = false;//定时器的this指向window对象，window没有disabled属性
                 },3000);
                 //正确写法
                 setTimeout(function(){ //相当于setTimeout(ƒ.bind(btn),3000)，改变函数this指向为btn
                     this.disabled = false; 
                 }.bind(this),3000)//不能用apply或者call，会立即调用，只能用bind 
             }
             //其他使用场景
             for(var i =0;i<this.lis.length;i++){
                 this.lis[i].onclick = this.toggleTab.bind(this.lis[i],this);
                 //toggleTab为函数 toggleTab(that){..}  ,this.lis[i]让其指向其自己，this（构造器的this）作为参数传参
             }
             ```

67. ## 严格模式 Strict Mode

    1. JavaScript除了提供正常模式外，还提供了严格模式。ES5 的严格模式采用具有限制性JavaScript变体的一种方式，即在严格的条件下运行JS代码。

    2. 严格模式在IE10 以上版本的浏览器才支持。

       1. 消除了JS语法的一些不合理、不严谨支持，如不声明变量。
       2. 消除代码运行的不安全之处，提高编译器效率。
       3. 仅用了ECMAScript未来本本可能定义的一些语法。比如关键字：class，enum，export，extends，import，super不能做变量名。

    3. 严格模式可以应用到整个脚本或个别函数中。

       1. 为这个脚本文件开启严格模，在所有语句之前放一个特定语句：`"use strict";`或`'use strict';`

          ```html
          <div></div>
          <script>
          'use strict'; //下面的代码就会按照严格模式执行代码
          //...
          </script>
          <script>
          (function(){ //独立作用域
              'use strict';
              //...
          })();
          </script>
          <script>
          function fn1(){
              'use strict'; //仅限当前函数内的代码按照严格模式执行
          }
          function fn2(){  //冈普通式
          }
          </script>
          ```

    4. 严格模式中的变化：语法和行为的改变

       1. **变量规定**

          1. 在正常模式中，如果一个变量没有声明就赋值，默认是全局变量。严格模式禁止这种模式，变量都必须先用`var`命令声明，然后再用。
          2. 严禁删除已经声明变量。例如：`delete x；`语法是错误的。

       2. **this指向问题**

          1. 以前全局作用域中的`this`指向 `window` 对象

          2. 严格模式下全局作用域中函数中的`this`是`undefined`。

          3. 以前构造函数是不加`new`也可以调用，当普通函数，`this`指向全局对象。

          4. 严格模式下，如果构造函数不加new调用，`this`会报错。

             ```js
             function Star(){
                 this.sex='male'; 
             }
             Star(); //不加new当作普通函数，对象即window
             console.log(window.sex);
             ```

          5. new实例化的构造函数指向创建的对象实例。

          6. 定时器的`this`还是指向`window`

          7. 事件、对象还是指向调用者

       3. **函数变化**

          1. 函数不能有重名的参数

             ```js
             function fn(a,a){
                 console.log(a+a);
             }
             fn(1,2); //结果为4 相当于a=1; a=2然后前一个被覆盖掉
             ```

          2. 函数必须声明在顶层。新版本的JavaScript会引入“块级作用域”（ES6中引入）。为了与新版本接轨，不允许在非函数的代码块内声明函数。

             ```js
             function fn(){ 
             	function f(){} //合法
             }
             if(true){
                 function f(){} //语法错误
                 f();
             }
             for(var i = 0; i<5;i++){
                 function f2(){}//语法错误
                 f2();
             }
             ```

68. ## 高阶函数

    1. 高阶函数是对其他函数进行操作的函数。它接收函数作为参数或将函数作为返回值输出。

       ```js
       function fn(callback){
           callback && callback();
       }
       fn(function(){
       	console.log('hello');
       })
       //或者
       function fn(){
           return function(){}
       }
       fn();
       ```

69. ## 闭包

    1. 变量作用域：全局变量和局部变量。

    2. 函数内部可以使用全局变量。函数外部不可以使用局部变量。

    3. 当函数执行完毕，本作用域内的局部变量会销毁。

    4. **闭包 closure** 指有权*访问*另一个函数作用域中*变量*的*函数*。

       1. 即：一个作用域可以访问另外一个函数内部的局部变量。

          ```js
          function fn(){
              var num = 10;	
              function fun(){//fun()为闭包函数
                  console.log(num); //即在fun()函数内可以访问fn()内的num的变量，此时产生了闭包
              }
              fun();
          }
          fn(); //chrome浏览器，在fn()打断点，刷新浏览器。右侧菜单↓一步步走完显示闭包Closure(fn)
          ```

       2. 在`fn()`外使用`num`

          ```js
          function fn(){
              var num = 10;	
              function fun(){
                  console.log(num); 
              }
              return fun; //使用return返回函数
          }
          var f = fn();//此时可以在fn()外部作用域访问num局部变量，即产生了闭包
          ```

       3. 闭包的主要作用：延伸了变量的作用范围

       4. 案例：点击li输出当前li的索引号

          ```html
          <ul class="nav">
              <li>榴莲</li>
              <li>臭豆腐</li>
              <li>鲱鱼罐头</li>
              <li>大猪蹄子</li>
          </ul>
          <script>
              //利用动态添加属性方式
          	var lis = document.querySelector('.nav').querySelectorAll('li');
              for(var i = 0; i< lis.length; i++){//同步任务
                  lis[i].index = i;
                  lis[i].onclick = function(){//异步任务
                      console.log(i); //每次输出都是4
                      console.log(this.index); //正常输出
                  }
              }
              //利用闭包的方式得到当前li的索引号
              for (var i = 0; i < lis.length; i++) {
                //利用for循环创建了4个立即执行函数
                (function(i){//接收外面(下面)括号的i传递进来的
                    console.log(i);
                })(i);//把i作为参数传递
              }
          </script>
          ```

       5. 立即执行函数，也称为小闭包；立即函数里面的任何一个函数都可以使用外面括号传递进去的变量。

       6. 立即执行函数里面的`this`指向`window`。

       7. 异步任务只有触发时才会执行。

70. ## 递归

    1. 如果一个函数在内部可以调用其本身，那么这个函数就是递归函数。递归函数的作用和循环效果一样。

    2. 由于递归函数很容易发生“栈溢出”错误（stack overflow），所以必须要加退出条件return。

    3. 斐波那契数列（兔子序列）：

       1. 前两个数字之和等于第三个数：1、2、3、5、8...

          ```js
          function fb(n){
              if(n ===1 || n===2){
                  return 1;
              }
              return fb(n-1) + fb(n-2);
          }
          ```

    4. 案例：利用递归遍历数据

       1. 模拟json数据，输入任意id获得数据对象（有子数据目录结构形式）

          ```js
          var data = [{
              id:1,
              name:'家电',
              goods:[{
                  id:11,
                  gname:'冰箱'
              },{
                  id:12,
                  gname:'洗衣机'
              }],
          },{
              id:2,
              name:'服饰'
          }];
          //输入id号，就可以返回数据对象
          //1.利用forEach 遍历里面的每一个对象
          function getID(json,id){
              var obj = {};
              json.forEach(function(item){ //forEach自身遍历完毕自动结束递归，不需要再加退出条件
                  console.log(item);//当前例子输出2个元素对象
                  if(item.id == id){
                      console.log(item);
                      obj = item;
                      //2.想要得到里层的数据，比如id为11，可以利用递归函数
                      //需要确保里面有goods这个数组并且长度不为0
                  }else if(item.goods && item.goods.length>0){
                      obj = getID(item.goods,id);
                  }
              })
              return obj;
          }
          
          console.log(getID(data,1));
          getID(data,2);
          getID(data,11);
          ```

    5. **浅拷贝**：

       1. 只是拷贝最外面一层，更深层次对象级别的只拷贝引用。

          ```js
          var obj = {
          	id:1,
              name:"andy",
              msg:{
                  age:18
              }
          };
          var o={};
          //以前拷贝方式
          for(var k in obj){
              //k是属性名， obj[k]属性值
              o[k] = obj[k];
          }
          //复杂数据，msg拷贝过来的是地址
          o.msg.age=20;//验证是地址，修改值后影响原数据
          ```

       2. ES6 浅拷贝 语法糖：`Object.assign(target, ...sources)`

          ```js
          Object.assign(o, obj);
          ```

    6. **深拷贝**：

       1. 拷贝多层，每一级别的数据都会拷贝。

          ```js
          //函数递归深层拷贝
          var obj = {
          	id:1,
              name:"andy",
              msg:{
                  age:18
              },
              color:['pink','red']
          };
          var o={};
          function deepCopy(newobj, oldobj){
              for(var k in oldobj){
                  //判断属性值属于哪种数据类型（一层还是多层）
                  //1.获取属性值 oldobj[k]
                  var item = oldobj[k];
                  //2.判断这个指是否是数组
                  if(item instanceof Array){	//注意先写Array再写Object
                      newobj[k] = [];//相当于 o.color = []
                      deepCopy(newobj[k], item);
                  }else if(item instanceof Object){
                  //3.判断这个值是否是对象
                      newobj[k]={};
                      deepCopy(newobj[k], item);
                  }else{
                  //4.属于简单数据类型
                      newobj[k]=item;
                  }
              }
          }
          deepCopy(o, obj);
          ```

71. ## 正则表达式

    1. Regular Expression 用于匹配字符串中字符组合的模式。在JavaScript中，正则表达式也是对象。

    2. 场景使用：匹配、替换、提取、验证。

    3. **创建正则表达式**：

       1. 通过调用`RegExp`对象的构造函数创建

          ```js
          var regexp = new RegExp(/表达式/);
          ```

       2. 通过字面量创建

          ```js
          var regexp = /表达式/;
          ```

    4. **测试正则表达式**

       1. `test()`正则对象方法，用于检测字符串是否符合该规则，该对象会返回`true`或`false`，其参数是测试字符串。

          ```js
          //regexObj.test(str)
          var rg=/abc/;
          rg.test('abcde');
          ```

       2. regexObj：写的正则表达式

       3. str：要测试的文本

       4. 检测str文本是否符合写的正则表达式规范。

    5. 正则表达式的组成

       1. 元字符：在正则中具有特殊意义的专用符号，比如`^`,`$`,`+`等， [参考火狐](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Guide/Regular_Expressions)

       2. 正则表达式里面不需要加用引号，不管是数字型还是字符串型。

          ```js
          var rg=/abc/; //表示要包含abc这个字符的
          ```

    6. **边界符**：提示字符所处的位置

       | 边界符 | 说明                           | 举例     |
       | ------ | ------------------------------ | -------- |
       | `^`    | 表示匹配行首的文本（以谁开始） | `/^abc/` |
       | `$`    | 表示匹配行尾的文本（以谁结束） | `/abc$/` |

    7. **字符类**：

       | 字符类 | 说明                                                         | 举例           |
       | ------ | ------------------------------------------------------------ | -------------- |
       | `[]`   | 表示有一些列字符可供选择，只需要匹配其中一个就可以了（多选一） | `/[abc]/`      |
       | `[-]`  | 方括号内部范围符`-`；字符组合`/[a-zA-Z/`                     | `/[a-z]/`      |
       | `[^]`  | 表示取反；如例子表示不要字母开头的                           | `/^[^a-zA-Z]/` |

    8. **量词符**

       | 量词    | 说明                           | 举例          |
       | ------- | ------------------------------ | ------------- |
       | `*`     | 重复零次或更多次               | `/^a*$/`      |
       | `+`     | 重复一次或更多次               | `/^a+$/`      |
       | `?`     | 重复零次或一次                 | `/^a?$/`      |
       | `{n}`   | 重复n次                        | `/^a{3}$/`    |
       | `{n,}`  | 重复n次或更多次                | `/^a{3,}$/`   |
       | `{n,m}` | 重复n到m次（逗号后不能有空格） | `/^a{3,16}$/` |

       ```js
       var reg = /^a$/ //以^和$开头和结尾，限制只能有一个
       var reg = /^abc{3}$/ //表示abccc，让c重复3次
       var reg = /^（abc）{3}$/ //abc重复3次
       var reg = /^[abc]$/ //a或b或c且限1个
       ```

    9. **预定义类**

       | 预定类 | 说明                                                         | 例子 |
       | ------ | ------------------------------------------------------------ | ---- |
       | `\d`   | 匹配0-9之间的任一数组，相当于`[0-9]`                         |      |
       | `\D`   | 匹配所有0-9以外的字符，相当于`[^0-9]`                        |      |
       | `\w`   | 匹配任意的字母、数字和下划线，相当于`[A-Za-z0-9]`            |      |
       | `\W`   | 出所有字母、数字和下划线以外的字符，相当于`[^A-Za-z0-9]`     |      |
       | `\s`   | 匹配空格（包括换行符、制表符、空格符等），相当于`[\t\r\n\v\f]` |      |
       | `\S`   | 匹配非空格符的字符，相当于`[^\t\r\n\v\f]`                    |      |

    10. **符号**

       | 符号 | 说明                                                     | 例子                          |
       | ---- | -------------------------------------------------------- | ----------------------------- |
       | `|`  | 表示或者的意思；比如电话号码010-12345678或者0530-1234567 | `/^\d{3}-\d{8}|\d{4}-\d{7}$/` |

       ```js
       //中文
       var reg = /[\u4e00-\u9fa5]/; //从一开始的中尉unicode
       ```

    11. **replace**替换

        1. 可以实现替换字符串操作，用来替换的参数可以是一个字符串或是一个正则表达式

           ```js
           stringObject.replace(regexp/substr, replacement)
           ```

        2. 第一个参数：被替换的字符串 或者 正则表达式

        3. 第二个参数：替换为的字符串

        4. 返回值是一个替换完毕的新字符串

           ```js
           var str = 'andy和red,andy和red';
           var newStr = str.replace('andy','baby');//只替换第一个成baby
           var newStr = str.replace(/andy/,'baby'); //只替换第一个成baby
           var newStr = str.replace(/andy|candy/g,'baby'); //多个敏感词，全部换成baby
           ```

        5. `/正则表达式/[switch]`：正则表达式参数

           1. `g`：全局匹配
           2. `i`：忽略大小写
           3. `gi`：全局匹配且忽略大小写

72. ## ES6

    1. `let`：ES6新增的用于声明变量的关键字。

       1. `let` 声明的变量旨在所处于的块级`{}`有效。

       2. `var`关键字不具备块级作用域。

       3. **防止循环变量变成全局变量**。比如`fori`循环。

          ```js
          for(let i=0; i<2;i++){
              //()括号内let变量与{}绑定，{}外无法访问
          }
          ```

       4. 不存在变量提升

          ```js
          console.log(a);//a is not defined
          let a = 20;
          ```

       5. 暂时性死区

          ```js
          var tmp=123;
          if(true){
              console.log(tmp);//报错
              tmp = 'abc';
              let tmp;
          }
          ```

    2. 分析([黑马讲解](https://www.bilibili.com/video/BV1DY41177dM?p=96&spm_id_from=pageDriver&vd_source=31dc1543590614dbc49f7bf7cfc36195))

       ```js
       var arr=[];
       for(var i=0; i<2; i++){ //var为全局
           arr[i] = function(){//函数需要调用才能执行，因此循环的时候函数内的 i 值为循环结束后的
               console.log(i);
           }
       }
       arr[0]();
       arr[1]();
       //同步与异步
       for(letvar i=0; i<2; i++){
           arr[i] = function(){//let为块级，所以循环后根据 i 的值生成多个块级
               console.log(i);
           }
       }
       arr[0]();
       arr[1]();
       ```

    3. `const`：声明常量，即值（内存地址）不能变化的量。

       1. 具有块级作用域
       2. 声明常量时必须赋值
       3. 赋值后值不能修改
       4. 数组 或 对象赋值后，地址值不变，通过索引内容可改。但不能直接使用变量赋值。

    4. let、const、var区别

       | var          | let            | const          |
       | ------------ | -------------- | -------------- |
       | 函数级作用域 | 块级作用域     | 块级作用域     |
       | 变量提升     | 不存在变量提升 | 不存在变量提升 |
       | 值可更改     | 值可更改       | 值不可更改     |

73. ## 解构赋值

    1. es6中允许从数组中提取值，按照对应位置，对变量赋值。对象也可以实现解构。

       ```js
       let ary = [1,2,3];
       let [a,b,c] = ary;
       console.log(a);
       console.log(b);
       console.log(c);
       ```

    2. 如果解构不成功，变量的值为`undefined`。

       ```js
       let [foo] = [];
       let [bar,foo] = [1];
       ```

    3. **解构对象**

       1. 按照一定模式，从数组或对象中提取值，将提取出来的值赋值给另外的变量。

          ```js
          let person = {name:'张三', age:20};
          let {name, age} = person;
          console.log(name);
          console.log(age);
          
          let {name:myName, age:myAge} = person; //myName myAge属于别名
          console.log(myName);
          console.log(myAge);
          ```

    4. **箭头函数**

       1. ES6中新增的定义函数方式

          ```js
          ()=>{}
          const fn = ()=>{
              console.log(123);
          }
          fn();
          ```

       2. 函数体中只有一句代码，且代码的执行结果就是返回值，可以省略大括号。

          ```js
          function sum(num1, num2){
              return num1 + num2;
          }
          const sum = (num1,num2) => num1 + num2;
          ```

       3. 如果参数只有一个，可以省略小括号

          ```js
          function fn(v){
              return v;
          }
          const fn = v => v;
          ```

       4. 箭头函数**不绑定**`this`关键字，箭头函数中的`this`，指向的时函数定义位置的上下文`this`。

          ```js
          const obj = {name:'张三'};
          function fn(){
              console.log(this); //调用时，this指向obj对象
              return ()=>{ //箭头函数没有自己的this
                  console.log(this); //this指向定义箭头函数区域的位置，即fn函数区的this，而obj调用fn，则指向obj对象
              }
          }
          const resFn = fn.call(obj);
          resFn();
          ```

       5. 分析：**对象不能产生作用域**

          ```js
          var obj = { //2.obj对象不能产生作用域
              age: 20,
              say: () => {
                  alert(this.age); //3.this实际被定义在全局区域下，即window区域
              }
          }
          obj.say(); //1.输出undefined
          ```

    5. **剩余参数**

       1. 剩余参数语法允许我们将一个不定数量的参数表示为一个数组。

          ```js
          function sum(first, ...args){
              console.log(first);//10
              console.log(args);//[20,30]
          }
          sum(10,20,30);
          const sum = (...args) => {
              let total = 0;
              args.forEach( (item)=>{
                  total += item;
              });
              return total;
          };
          sum(10,20);
          ```

       2. 剩余参数和解构配合

          ```js
          let students = ['wangwu','zhangsan','lisi'];
          let [s1, ...s2] = students; //让s2接收剩余数组数据
          console.log(s1); //'wangwu'
          console.log(s2); //['zhangsan','lisi']
          ```

74. ## ES6的内置对象扩展

    1. **扩展运算符**

       1. 可以将数组或者对象转为用逗号分隔的参数序列。

          ```js
          let arr = [1,2,3];
          ...arr; //1,2,3
          console.log(...arr); //拆分成1 2 3, console.log把逗号省略
          console.log(1,2,3); //等同上面
          ```

       2. 扩展运算符还可以用于合并数组。

          ```js
          let arr1 = [1,2,3];
          let arr2 = [3,4,5];
          let arr3 = [...arr1, ...arr2];
          //或者
          arr1.push(...arr2);
          ```

       3. **将类数组（伪数组）或可遍历对象转换为真正的数组**

          ```js
          let oDivs = document.getElementsByTagName('div');
          oDivs = [...oDivs];
          //伪数组不能使用数组的方法
          ```

    2. **Array的扩展方法** [火狐Array.prototype.filter() 等其他参考](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Array/filter)

       1. `Array.from()`： 构造函数方法

       2. 将类数组或可遍历对象转换为真正的数组

          ```js
          let arrayLike = {
              '0': 'a',
              '1': 'b',
              '2': 'c',
              length: 3
          };
          let arr2 = Array.from(arrayLike); // ['a','b','c']
          ```

       3. 方法还可以接收第二个参数，作用类似于数组的map方法，用来对每个元素进行处理，将处理后的值放入返回的数组。

          ```js
          let arrayLike = {
              '0': '1',
              '1': '2',
              length: 2
          };
          let newArr = Array.from(arrayLike, item => item*2);
          ```

       4. `find()`：用于找出**第一个**符合条件的数组成员，如果没有找到返回`undefined`

          ```js
          let arr = [{
              id:1,
              name:'张三'
          },{
              id:2,
              name:'李四'
          }];
          let target = arr.find((item,index) => item.id == 2);
          ```

       5. `findIndex()`：实例方法，用于找出**第一个**符合条件的数组成员的位置，如果没有找到返回`-1`

          ```js
          let arr = [1,5,10,15];
          let index = arr.findIndex((value,index) => value > 9);
          console.log(index); //2
          ```

       6. `includes()`：实例方法，表示某个数组是否包含给定的值，返回布尔值。

          ```js
          [1,2,3].includes(2); //true
          [1,2,3].includes(4);//false;
          ```

    3. **String的扩展方法**

       1. 模板字符串：可以解析变量

          ```js
          let name = '张三';
          let sayHello = `hello, my name is ${name}`; //hello, my name is 张三
          ```

       2. 模板字符串：可以换行

          ```js
          let result = {
              name: 'zhangsan',
              age:20,
              gender: '男'
          };
          let html = `<div>
          	<span>${result.name}</span>
          	<span>${result.age}</span>
          	<span>${result.gender}</span>
          </div>`
          ```

       3. 模板字符串：可以调用函数

          ```js
          const sayHello = function(){
              return 'halo呀';
          };
          let greet = `${sayHello()} hah`;
          console.log(greet);
          ```

       4. `startsWith()`和`endsWith()`

          1. `startsWith()`：表示参数字符串是否在原字符串的头部，返回布尔值

          2. `endsWith()`：表示参数字符串是否在原字符串的尾部，返回布尔值

             ```js
             let str = "hello world!";
             str.startsWith("hello");
             str.endsWith('!')；
             ```

       5. `repeat()`：实例方法，表示将源字符串重复n次，返回一个新字符串

          ```js
          'x'.repeat(3); //"xxx"
          'hello'.repeat(2); //"hellohello"
          ```

    4. **Set数据结构**

       1. ES6提供了新的数据解构Set。**Set类似于数组**，但是成员的值都是唯一的，没有重复的值。

       2. Set本身时一个构造函数，用来生成Set数据结构。

          ```js
          const s = new Set();
          console.log(s.size); // 0
          //Set函数可以接收一个数组作为参数，用来初始化
          const set = new Set(['a','b','b']); 
          console.log(set.size); // 2
          const arr = [...set]; //转成数组
          ```

       3. `add(value)`：添加某个值，返回Set结构本身

       4. `delete(value)`：删除某个值，返回一个布尔值，表示删除是否成功

       5. `has(value)`：返回一个布尔值，表示改制是否为Set的成员

       6. `clear()`：清除所有成员，没有返回值

          ```js
          const s = new Set();
          s.add(1).add(2).add(3); //向set结构添加值
          s.delete(2); //删除set结构中的2
          s.has(1); //表示set结构中是否有1这个值，返回布尔值
          s.clear();  //清除set结构中的所有值
          ```

       7. `forEach`()：Set结构的实例与数组一样，也有forEach方法，对于每个成员执行某个操作，没有返回值。

          ```js
          s.forEach(value => console.log(value))
          ```

       8. 



















1. # ES6尚硅谷

2. ## ECMAScript：

   1. Ecma国际通过ECMA-262标准化的脚本程序设计语言。
   2. TC39委员会进行维护，成员都是浏览器大厂公司。
   3. ES6本本变动内容最多，具有里程碑意义，许多新的语法特性。 ES6于2015年。
   4. ES6对于各个浏览器的兼容性： [地址](http://kangax.github.io/compat-table/es6/)

3. ## `let`声明与特性

   1. 声明方式

      ```js
      let a;
      let a,b,c,d;
      let e=100;
      let f=512,g="ivyou",h=[];
      ```

   2. `let`变量不能重复声明；`var`可以重复声明

   3. `let`块级作用域：全局、函数、eval。 `var`没有全局作用域。

   4. `let`不存在变量提升。

      ```js
      //比如var, console输出在声明之前，会显示undefined。代码执行之前会先收集
      console.log(song);
      var song="恋爱达人";
      //相当于先声明，未赋值然后再console输出与赋值
      var song;
      //let此处会报错
      console.log(song);
      let song="恋爱达人";
      ```

   5. 不影响作用域链。

      ```js
      {
          let school="尚硅谷";
          function(){
              console.log(school); //顺着作用域链往上找，不是向外找
          }
          fn();
      }
      ```

   6. 案例：

      ```js
      //获取div元素对象
      let items = document.getElementsByClassName('item');
      //遍历并绑定事件
      for(var i=0;i<items.length;i++){ //items.length=3 3个div
          items[i].onclick=function(){
              //修改当前元素的背景颜色
              this.style.background = 'pink'; //可以成功
              items[i].style.background = 'pink';//报错失败
          }
      }
      //循环相当于如下
      { var i = 0; }
      { var i = 1; }
      { var i = 2; }
      //若循环后使用输出得到结果i=3，越界异常
      console.log(window.i)
      for(let i=0;i<items.length;i++){ 
          items[i].onclick=function(){
              items[i].style.background = 'pink';//成功
          }
      }
      ```

4. ## `const`：

   1. 一般**常量**使用大写，一定要赋初始值，常量值不能修改。
   2. 块级作用域
   3. 对于数组和对象的元素修改，不算做对常量的修改，不会报错。

   ```js
   const TEAM = ['red','blue'];
   TEAM.push('yellow');
   ```

5. ## 解构赋值

   1. ES6允许按照一定模式从数组和对象中提取值，对变量进行赋值，这被称为**解构赋值**。

   ```js
   //数组解构
   const TEAM = ['red','blue'];
   let [hong, lan] = TEAM;
   console.log(hong);
   //对象解构
   const zhao = {
       name:'zhao',
       age:'unknown',
       xiaopin:function(){ //方法解构用的比较多
           console.log('show');
       }
   };
   let {name,age,xiaopin} = zhao;
   console.log(name);
   console.log(xiaopin);
   xiaopin();
   //
   zhao.xiaopin();
   let {xiaopin} = zhao; //同名赋值
   xiaopin();
   ```

6. ## **模板字符串**：反引号

   1. 内容中可以直接出现换行符

   ```js
   let str = `字符串`;
   console.log(str, typeof str);
   let out = `${str} 是可以换行的`;
   ```

7. ES6允许在**大括号里面**，直接写入变量和函数，作为对象的属性和方法。

   ```js
   let name = '尚硅谷';
   let change = function(){
       console.log('我可以改变你！');
   };
   const school = {
       name, 
       change,
       improve:function(){
           console.log("函数书写方式之一");
       },
       other(){
           console.log("函数简写方式");
       }
   }
   ```

8. ## ES6允许使用**箭头定义函数**`=>`

   1. 箭头函数特性：**`this`是静态的**，this始终指向函数声明时所在作用域下的this值。
   2. 不能作为构造函数实例化对象。
   3. 不能使用`arguments`变量
   4. 箭头函数的简写：
      1. 省略小括号：当形参有且只有一个的时候
      2. 省略花括号：当代码体只有一条语句的时候，此时return必须省略，而且语句的执行结果就是函数的返回值


   ```js
   let fn = function(){
   }
   let fn = (a,b)=>{
       return a+b;
   }
   //1.this指向
   function getName(){
       console.log(this.name);
   }
   let getName2 = ()=>{
       console.log(this.name);
   };
   window.name = '尚硅谷';
   const school = {
       name: "shangguigu",
   }
   //直接调用
   getName(); //尚硅谷
   getName2();//尚硅谷
   //call方法调用
   getName.call(school);//shangguigu
   getName2.call(school);//尚硅谷
   //2.不能作为构造实例化对象
   let Person = (name,age)=>{
       this.name = name;
       this.age = age;
   }
   let met = new Person('xiao',30);
   console.log(me); //error：Person is not a constructor
   //3.不能使用arguments变量（函数内部有一个特殊变量保存实参）
   let fn = ()=>{
       console.log(arguments);
   }
   fn(1,2,3); //arguments is not defined
   //4.省略花括号
   let pow = n => n*n;
   ```

   1. 案例：箭头函数的使用场景

   ```js
   //需求，点击div 2s后变粉色
   let ad = document.querySelector('div');
   ad.addEventListener('click',function(){
       //非箭头函数时
       let _this = this;
       setTimeout(function () {
           console.log(this);//window
           _this.style.background = 'pink';
       },2000);
       //使用箭头函数
       setTimeout(()=> {
           console.log(this);//<div id="ad" style="background: skyblue;"></div>
           this.style.background = 'skyblue';
       },4000)
   })
   ```

   1. 案例：需求，从数组中返回偶数的元素

   ```js
   const arr = [1,5,6,7,30];
   //普通写法
   const result = arr.filter(function(item){
       if(item % 2 === 0){
           return true;
       }else{
           return false;
       }
   });
   console.log(result);
   //箭头函数普通写法
   const result2 = arr.filter( item => {
       if(item % 2 === 0){
           return true;
       }else{
           return false;
       }
   });
   console.log(result2);
   //箭头函数简洁写法
   const result3 = arr.filter( item => item % 2 === 0);
   console.log(result3)
   ```

   1. 箭头函数总结：

      1. 箭头函数适合与`this`无关的回调，如 定时器、数组的方法回调。
      2. 箭头函数不适合与`this`有关的回调，如 事件回调、对象的方法

      ```js
      { //不适合对象的方法
          name:"尚硅谷",
              getName:()=>{
                  this.name; //指向外层
              }
      }
      ```

   2. ## ES6允许给函数参数赋值**初始值**

   3. 形参初始值：有默认值的参数，一般位置要靠后（潜规则）

      ```js
      function add(a,b,c=10){
          return a+b+c;
      }
      let result = add(1,2);
      ```

   4. 与解构赋值结合

      ```js
      //普通写法
      function connect(options){
          let host = options.host;
          let username = options.username;
      }
      //解构赋值写法，且有默认值
      function connect({host="127.0.0.1", username,password,port}){
          console.log(host);
          console.log(username);
      }
      connect({
          host:'localhost',
          username:'root',
          password:'root',
          port:3306
      })
      ```

   5. ## Rest

      1. ES6引入`rest`参数，用于获取函数的实参，用来代替 `arguments`
      2. rest参数必须要放到参数最后。

      ```js
      //ES5获取实参的方式
      function data(){
          console.log(arguments);
      }
      data('白芷','阿娇','思慧');
      //Arguments(3) ['白芷', '阿娇', '思慧', callee: ƒ, Symbol(Symbol.iterator): ƒ]
      
      //ES6获取实参的方式
      function data(...args){
          console.log(args);
      }
      data('白芷','阿娇','思慧');
      //['白芷', '阿娇', '思慧']
      ```

   6. ## 扩展运算符

      1. `...`扩展预算夫能将数组转换为逗号分隔的参数序列。

         ```js
         const tfboys = ['red','blue','pink'];
         function fn(){
             console.log(arguments);
         }
         fn(tfboys);//一个数组3个元素
         //Arguments [Array(3), callee: ƒ, Symbol(Symbol.iterator):
         fn(...tfboys);//相当于fn('red','blue','pink')；单个元素逐一放进去
         //Arguments(3) ['red', 'blue', 'pink', callee: ƒ, Symbol(Symbol.iterator): ƒ]
         ```

      2. 案例：数组合并

         ```js
         const a = [1,2,3];
         const b = [5,6,7];
         const c = a.concat(b); //以前合并数组方式
         const d = [...a, ...b];
         ```

      3. 案例：数组克隆

         ```js
         const a = [1,2,3];
         const b = [...a]; //注意浅拷贝、深拷贝问题
         ```

      4. 案例：伪数组转为真数组

         ```js
         const divs = document.querySelectorAll('div');
         const divArr = [...divs];
         ```

   7. ## Symbol

      1. ES6引入新的原始数据类型Symbol，表示独一无二的值。

      2. 是JavaScript语言的第七种数据类型，是一种类似于字符串的数据类型

      3. Symbol特点

         1. 值是唯一的，用来解决命名冲突的问题。唯一性外部不可见，内部定义。

         2. Symbol的值不能与其他数据进行运算。

         3. Symbol定义的对象属性不能使用`for...in`循环遍历。但是可以使用`Reflect.ownKeys`来获取对象的所有键名。

            ```js
            //创建Symbol
            let s1 = Symbol();
            console.log(s1, typeof s1);
            //添加表示的Symbol
            let s2 = Symbol('尚硅谷'); //描述性字符串
            let s3 = Symbol('尚硅谷');
            console.log(s2 === s3); //false
            let s4 = Symbol.for('尚硅谷');
            let s5 = Symbol.for('尚硅谷');
            console.log(s4, typeof s4);
            console.log(s4 === s5 //true
            //不能运算
            let result = s+100;//err
            let result = s>100;//err
            let result = s+s;//err
            ```

      4. 数据类型：USONB - you are so niubility

         1. u：undefined
         2. s：string、symbol
         3. o：object
         4. n：null、number
         5. b：boolean

      5. **Symbol的主要作用：向对象添加属性值**。

         ```js
         //向对象中添加方法 up down
         let game = {...};//很多个方法
         //声明一个对象
         let methods = {
             up:Symbol(),
             down:Symbol()
         }
         //安全的向对象扩展方法一
         game[methods.up] = function(){
             console.log("向上");
         }
         game[methods.down] = function(){
             console.log("下降");
         }
         //向对象添加方法
         let youxi = {
             name:"狼人杀",
             say:function(){},
             Symbol():function(){}//err;Symbol()是一个动态值，不是固定属性
             [Symbol('say')]:function(){ //正确做法，相当于一个普通的属性名
             }
         }
         
         ```

      6. **Symbol内置值**：除了定义自己使用的Symbol的值以外，ES6还提供了11个内置的Symbol值，指向语言内部使用的方法。（了解即可）

         | 内置值                    | 说明                                                         | 举例 |
         | ------------------------- | ------------------------------------------------------------ | ---- |
         | Symbol.hasInstance        | 当其他对象使用instanceof运算符，判断是否为该对象的实例时，会调用此方法 |      |
         | Symbol.isConcatSpreadable | 对象的Symbol.isConcatSpreadable属性等于的时一个布尔值，表示该对象用于Array.prototype.concat()时，是否可以展开。 |      |
         | Symbol.unscopables        | 该对象制定了使用with关键字时，哪些属性会被with环境排除。     |      |
         | Symbol.match              | 当执行str.match(myObject)时，如果该属性存在，会调用它，返回该属性的返回值。 |      |
         | Symbol.replace            | 当该对象被str.replace(myObject)方法调用时，会返回该方法的返回值 |      |
         | Symbol.search             | 当该对象被str.search(myObject)方法调用时，会返回该方法的返回值。 |      |
         | Symbol.split              | 当该对象被str.split(myObject)方法调用时，会返回该方法的返回值。 |      |
         | Symbol.iterator           | 对象进行for...of循环时，会调用Symbol.iterator方法，返回该对象的默认遍历器 |      |
         | Symbol.toPrimitive        | 该对象被转为原始类型的值时，会调用这个方法，返回该对象对应的原始类型值 |      |
         | Symbol.toStringTag        | 在对象上调用toString方法时，返回该方法的返回值               |      |
         | Symbol.species            | 创建衍生对象时，会使用改该属性                               |      |

         ```js
         class Person{
             static [Symbol.hasInstance](param){ //自动执行场景
                 console.log(param); //[可选 来自o的参数]  返回：{}
                 console.log("我被用来检测类型了"); //返回：我被用来检测类型了
                 return true; //[可选] 返回：true
             }
         }
         let o = {};
         console.log(o instanceof Person);
         ```

   8. ## 迭代器Iterator

      1. 迭代器是一种接口，为各种不同的数据解构提供统一的访问机制。任何数据结构只要部署Iterator接口，就可以完成遍历操作。

      2. ES6创造了一种新的遍历命令`for...of`循环，Iterator接口主要供`for...of`消费。

      3. **原生具备iterator接口的数据**（可以用`for of`遍历）

         1. Array、Arguments、Set、Map、String、TypedArray、NodeList
         2. iterator接口：实际就是对象里面的属性，`Symbol.iterator`

         ```js
         const xiyou = ['唐僧','孙悟空','猪八戒','沙僧'];
         for(let v of xiyou){
             console.log(v); // 唐僧 。。
         }
         for(let v in xiyou){
             console.log(v); // 0 1 2 3 保存键名
         }
         ```

      4. 工作原理：

         1. 创建一个指针**对象**，指向当前数据解构的起始位置。
         2. 第一次调用对象的next方法，指针指向数据结构的第一个成员。
         3. 接下来不断调用next方法，指针一直往后移动，直到指向最后一个成员。
         4. 每调用next方法返回一个包含value和done属性的对象。
         5. 注：需要自定义遍历数据的时候，要想到迭代器。

         ```js
         //1.
         let iterator = xiyou[Symbol.iterator]();
         console.log(iterator);// //Array Iterator {..next: ƒ next()..}
         //2.
         console.log(iterator.next());//{value: '唐僧', done: false}
         //3.
         console.log(iterator.next());//{value: undefined, done: true} 遍历第五次(沙僧后)，done表示遍历结束
         
         ```

      5. **自定义遍历数据**案例

         ```js
         const banji = {
               name:"终极",
               stus:[
                   'zhangsan',
                   'lisi',
                   'wangwu'
               ],
               [Symbol.iterator](){ 
                   let index = 0;
                   let _this = this;
                   return {//1.返回一个对象 {}为一个对象
                     next:function(){ //2. 要有next方法
                         //return {value:'abc', done: false}; // 3、4.返回对象要包含done和value属性
                         if(index < _this.stus.length) {
                             const result = {value: _this.stus[index], done: false};
                             index++;
                             return result;
                         }else{
                             return {value: undefined, done: true};
                         }
                     }
                   };
               }
           };
         
           //遍历这个对象
           for(let v of banji){
               console.log(v); //若没有Symbol时：err, banji is not iterable
           }
         //也可以用banji.stus.forEach遍历
         ```

   9. ## 生成器

         1. 生成器是ES6提供的一种异步编程解决方案，语法行为与传统函数完全不同。

         2. 生成器其实就是一个特殊的函数。异步编程： 纯回调函数

            ```js
            function * gen(){
                console.log("hello nihao ");
            }
            let iterator = gen();
            console.log(iterator); // 没有立刻执行输出， 结果： gen {<suspended>}
            //gen {<suspended>}
            //[[GeneratorLocation]]: VM349:1
            //	[[Prototype]]: Generator
            //        [[Prototype]]: Generator
            //            next: ƒ next()
            iterator.next(); //使用next方法才能执行
            ```

            ```js
            //函数代码的分隔符：yield
            function * gen(){
                //第一段
                console.log(111);
                yield '一只没有耳朵';
                //第二段
                console.log(222);
                yield '一只没有尾巴';
                //第三段
                console.log(333);
                yield '真奇怪';
                //第四段 {value: undefined, done: true}
                console.log(444);
            }
            let iterator = gen(); //带参数传入
            iterator.next(); 
            //可以使用 for..of循环遍历
            for(let v of gen()){
                console.log(v);
            }
            ```

            ```js
            //带参数传入
            function * gen(arg){
                console.log(arg);
                let one = yield '一只没有耳朵';
                console.log(one);
                yield '一只没有尾巴';
                yield '真奇怪';
            }
            let iterator = gen('AAA'); //带参数传入
            iterator.next(); //第一次遍历传入
            //next可以传入实参
            iterator.next('BBB'); //此次（第二次）传入的实参，将作为第一个yield 调用后 的返回结果
            ```

         3. 案例：生成器函数实例

               1. 需求：1s 后控制台输出 111， 2s后输出222，3s后输出333.

            ```js
            //异步编程： 文件操作、网络操作（ajax、request）、数据库操作
            //生成器函数完成异步任务
            function one(){
                setTimeout(()=>{
                    console.log(111);
                    iterator.next();//若不在函数添加遍历器，则只遍历one()，后续不遍历
                },1000);
            }
            function two(){
                setTimeout(()=>{
                    console.log(222);
                    iterator.next();
                },2000);
            }
            function three(){
                setTimeout(()=>{
                    console.log(333);
                    iterator.next();
                },3000);
            }
            function * gen(){
                yield one();
                yield two();
                yield three();
            }
            //调用生成器函数
            let iterator = gen();
            iterator.next();
            ```

         4. **案例：先获取用户数据、再获取订单数据、再获取商品数据**

            ```js
            //解决回调地狱问题
            function getUsers(){
                setTimeout(()=>{
                    let data = '用户数据';
                    //调用next方法，并且将数据传入
                    iterator.next(data);
                },1000);
            }
            function getOrders(){
                setTimeout(()=>{
                    let data = '订单数据';
                    iterator.next(data);
                },1000);
            }
            function getGoods(){
                setTimeout(()=>{
                    let data = '商品数据';
                    iterator.next(data);
                },1000);
            }
            //不适用单独调用方法，因为数据之间有关联
            /*
              getUsers();
              getOrders();
              getGoods();
             */
            function * gen(){
                let users = yield getUsers();
                console.log(users);
                let orders = yield getOrders();
                console.log(orders);
                let goods = yield getGoods();
                console.log(goods);
            }
            //调用生成器函数
            let iterator = gen();
            iterator.next();
            ```

   10. ## Promise

         1. Promise是ES6引入的异步编程的新解决方案。语法上Promise是一个构造函数，用来封装异步操作并可以获取其成功或失败的结果。

               1. Promise 构造函数：`Promise(excutor){}`
               2. `Promise.prototype.then`方法
               3. `Promise.prototype.catch`方法

         2. **异步编程：主要指一些文件IO操作、数据库IO、网络请求**。

         3. 作用：解决回调套娃。

            ```js
            //1. 实例化Promise对象
            const p = new Promise(function(resolve, reject){  //异步操作
                setTimeout(function(){
                    let data = '数据库中的用户数据';
                    //2. 得到数据后，可以调用resolve、reject两个函数改变const p的状态
                    resolve(data); //3. p结果显示成功状态
                    //7. 若得到的结果失败，不用resolve，使用reject
                    let err = '数据读取失败';
                },1000);
            });
            //4. 得到resolve状态成功后，可以调用promise对象的then方法（前一个），失败调用后一个方法
            //p.then(function(){},function(){}); then参数里面都是函数
            p.then(function(value){
                //5. resolve成功后，调用此函数（第一个）
                console.log(value); //6. 数据库中的用户数据
            },function(reason){
                //8. resolve失败，使用reject后调用此函数（第二个）
                console.err(reason); //9. 数据读取失败
            });
            ```

   11. ## Set

          1. ES6提供了新的数据解构Set（集合）。它类似于数组，但成员的值都是唯一的，

                 2. 集合实现了iterator接口，所以可以使用 '扩展运算符' 和 `for...of...` 进行遍历。

                 3. 集合的属性和方法：

                  1. size：返回集合的元素个数
                  2. add：增加一个新元素，返回当前集合
                  3. delete：删除元素，返回boolean值
                  4. has：检测集合中是否包含某个元素，返回boolean值。
                  5. clear： 清空，返回当前集合

               ```js
               //声明一个set
               let s = new Set();
               console.log(s, typeof s); //set(0) {}, "object"
               let s2 = new Set(['大事','小事','坏事','好事','小事']); //参数为可迭代数组，自动去重
               console.log(s2.size);//返回个数
               console.log(s2.add('稀释')); //返回更新后的集合
               console.log(s2.delete('小事')); //返回boolean
               console.log(s2.has('大事')); //返回Boolean
               ```

                 4. 遍历Set

               ```js
               for(let v of s2){
                   console.log(s2);
               }
               ```

                 5. 数组去重

               ```js
               let arr = [1,2,3,4,5,4,3,2,1];
               let result = [...new Set(arr)]; //去重后再转为数组
               ```

                 6. 交集

               ```js
               let arr2 = [4,5,6,7];
               let result = [...new Set(arr)].filter(item => {
                   let s2 = new Set(arr2);
                   if(s2.has(item)){
                       return true;
                   }else{
                       return false;
                   }
               });
               ```

                 7. 并集

               ```js
               let result = [...newSet([...arr,...arr2])]; //先合并数组，然后用集合去重后再转为数组
               ```

                 8. 差集

               ```js
               //差集根据主体不一样的结果
               let diff = [...new Set(arr)].filter(item => !(new Set(arr2).has(item)));
               ```

   12. ## Map

               1. ES6提供了Map数据解构。它类似于对象，也是键值对的集合。但是“键”的范围不限于字符串，各种类型的值（包括对象）都可以当作键。
              
                      2. Map也实现了iterator接口，所以可以使用 '扩展运算符' 和 `for...of...` 进行遍历。
              
                      3. Map的属性和方法：
              
                       1. size：返回Map 的元素个数
                       2. set：增加一个新元素，返回当前Map
                       3. get：返回键名对象的键值
                       4. delete：删除
                       5. has：检测Map 中是否包含某个元素，返回boolean值。
                       6. clear： 清空，返回undefined


          ​          
                    ```js
                    //创建一个Map
                    let m = new Map();
                    //添加元素
                    m.set('name','尚硅谷'); //"name" => "尚硅谷"
                    m.set('change',function(){ //"change" => f（）{。。}
                        console.log("改变你我他");
                    })
                    let key = {
                        school: 'ATGUIGU'
                    };
                    me.set(key, ['北京','上海']); //{Object => Array(2)}
                    //大小
                    console.log(m.size);
                    //删除
                    m.delete('name');
                    //获取
                    console.log(m.get(key));
                    console.log(m.get('change'));
                    ```

   13. ## class

         1. ES6提供了更接近传统语言的写法，引入了Class（类）的概念，作为对象模板。通过class关键字，可以定义类。

                2. 基本上，ES6的class可以看作只是一个语法糖，它的绝大部分功能，ES5都可以做大，新的class写法只是让对象原型的写法更加清晰、更像面对对象编程的语法。

                3. 声明类

              ```js
              //==== ES 5 通过构造函数 ====
              function Phone(brand, price){
                  this.brand = brand;
                  this.price = price;
              }
              //添加方法
              Phone.prototype.call = function(){
                  console.log("我可以打电话");
              }
              //实例化对象
              let Huawei = new Phone('华为',999);
              Huawei.call(); //调用原型对象公共方法
              console.log(Huawei);
              //===== ES 6 =====
              class Phone{
                  //构造方法，名字不能修改
                  constructor(brand, price){
                      this.brand = brand;
                      this.price = price;
                  }
                  //方法必须使用该语法，不能使用ES5 的对象完整形式， 如 call:function(){} 不能在ES6
                  call(){
                      console.log("我可以打电话");
                  }
              }
              let onePlus = new Phone("1+",1999);
              console.log(onePlus);
              ```

            4. constructor 定义构造器函数初始化

            5. extends  继承父类

              ```js
              //====ES5 继承=====
              function SmartPhone(brand, price, color, size){
                 Phone.call(this, brand, price);//继承Phone，改变其this指向即可
                  this.color = color;
                  this.size = size;
              }
              //设置自己构造函数的原型 (校正)
              SmartPhone.prototype = new Phone;
              SmartPhone.prototype.constructor = SmartPhone;
              //声明子类的方法
              SmartPhone.prototype.photo = function(){
                  console.log("我可以拍照");
              }
              const chuizi = new SmartPhone('锤子',2499,'黑色','5.5');
              //====ES6 继承=====
              class Phone{
                  constructor(brand, price){
                      this.brand = brand;
                      this.price = price;
                  }
                  call(){
                      console.log("我能打电话");
                  }
              }
              class SmartPhone extends Phone{
                  constructor(brand,price,color,size){
                      super(brand, price); //相当于 Phone.call(this, brand, price)
                      this.color = color;
                      this.size = size;
                  }
                  call()}{ //覆盖重写
                      console.log("我可以视频通话");
                      //此处不能调用super()，普通成员方法不能调用super
                  }
              }
              ```

            6. super 调用父级构造器方法

            7. static 定义静态方法和属性

              ```js
              function Phone(){
                  
              }
              Phone.name = '手机';
              Phone.change = function(){
                  console.log("我可以改变世界");
              }
              ```

               8. set和get，对属性的设置和获取

              ```js
              class Phone{
                  get price(){
                      console.log("读取价格");
                      return 'iloveyou';
                  }
                  set price(newVal){ //设置必须要有一个参数
                      console.log('价格属性修改了');
                  }
              }
              let s = new Phone();
              console.log(s.price);
              s.price = 'free';
              ```

   14. ## ES6数值的扩展

         1. `Number.EPSILON`： 是Javascript表示的最小精度。

               1. 值：接近于`2.22* 10E-16`(2.22乘以10的负16次方)

                  ```js
                  console.log（0.1 + 0.2 ===0.3）; //false. 浮点精度相加不一样
                  //用法
                  if( Math.abs(a-b) < Number.EPSILON){
                      return true;
                  }else{
                      return false;
                  }
                  ```

                2. `Number.isFinite`：检测一个数值是否为有限数。

              ```js
              console.log(Number.isFinite(100)); //true
              console.log(Number.isFinite(100/0));//false
              console.log(Number.isFinite(Infinity)); //false
              ```

            3. `Number.parseInt`、`Number.parseFloat`：字符串转整数

            4. `Number.isInteger`：判断一个数是否为整数

            5. `Number.isNaN`：检测一个属是否为NaN

            6. `Math.trunc`：将数字的小数部分抹掉

              ```js
              console.log(Math.trunc(3.5));
              ```

               7. `Math.sign`：判断一个数到底是正数、负数、还是零。

              ```js
              console.log(Math.sign(100)); //1
              console.log(Math.sign(0)); //0
              console.log(Math.sign(-20000)); //-1
              ```

   15. ## ES6对象方法扩展

         1. `Object.is：判断两个值是否完全相等`

               ```js
               console.log(Object.is(120,120)); //类似于===全等号，又不完全一样
               console.log(Object.is(NaN,NaN)); //true
               console.log(NaN === NaN); //false ,NaN与非数值比较之外，其余的都是false
               ```

         2. `Object.assign`：对象的合并

               ```js
               const config1 = {
                   host:'localhost',
                   port:3306
               }
               const config2 = {
                   host:'http://google.ca',
                   port:33060
               }
               console.log(Object.assign(config1, config2)); //相同属性，后面覆盖前面，非重复属性名合并
               ```

         3. `Object.setPrototypeOf`、`Object.getPrototypeof` ：原型对象操作

               ```js
               const school = {
                   name: '尚硅谷'
               }
               const city = {
                   xiaoqu: ['beijing','shanghai']
               }
               Object.setPrototypeOf(school, city);
               console.log(Object.getPrototypeOf(school));
               console.log(school);
               ```

   16. ## 模块化

            1. 模块化指将一个大的程序文件，拆分成许多小的文件，然后将小文件组合起来。

            1. 模块化优点

                   1. 防止命名冲突
                   2. 代码复用
                   3. 高维护性
                 
            3. 模块化规范产品，ES6之前的模块化规范及产品有：

                   1. CommonJS => NodeJS、Browserify
                   2. AMD => requireJS
                   3. CMD => seaJS

            4. ES6模块化语法

                 1. **export**命令用于规定模块的对外接口

                       ```js
                       //m1.js
                       //1.分别暴露
                       export let school = '尚硅谷';
                       export function teach(){
                           console.log("教你东西");
                       }
                       //2.统一暴露 m2.js
                       export {school, teach}; 
                       //3.默认暴露
                       export default{ //任意类型，对象居多
                           school:'AIGUIGU',
                           change:function(){
                               console.log("尚硅谷");
                           },
                       }
                       ```

                 2. **import**命令用于输入其他模块提供的功能

                       ```html
                       <script type="module">
                           //1.通用导入方式
                           import * as m1 from './m1.js'; //m1的所有标记export 暴露的导入
                           console.log(m1); //Module {Symbol(Symbol.toStringTag):"Module"}..
                           import * as m3 from 'm3.js';
                           m3.default.change();
                           //2.解构赋值形式(统一暴露)
                           import {school,teach} from 'm2.js'; //相当于分别暴露
                           console.log(school);
                           console.log(teach);
                           import {school as guigu, findJob} from 'm2.js'; //重名用as重命名
                           console.log(guigu);
                           import {default as m3} from "m3.js";//对应默认暴露（观看行对象形式友好）
                           //3.简便形式，针对默认暴露
                           import m3 from "m3.js";
                       </script>
                       ```

            5. 在HTML中的引入

          ```html
          <!--HTML页面 引入一个js入口文件-->
          <script src="app.js" type="Module">
          </script>
          <!--JS文件-->
          <script>
              import * as m1 from "m1.js";
          </script>
          ```

              6. **实际项目使用方式**
                   1. 因兼容性考虑，使用Babel编译方式（JavaScript编译器），将ES6编译成ES5语法。
                   2. HTML引入编译后的文件。

               7. **Babel使用方式**
                  1. 安装工具：babel-cli（命令行工具）、babel-preset-env（预设包，转换）、browserify（打包工具，也可用webpack打包但是需要配置环境）
                  2. 安装命令：`npm i babel cli babel-preset-env browserify -D`； 
                     1. -D开发以来

                  3. 非全局安装**编译**使用命令：`npx babel src/js -d dist/js --preset=babel-preset-env`
                     1. -d：目标目录
                     2. --preset：预设环境

                  4. **打包**入口文件：`npx browserify dist/js/app.js -o dist/bundle.js`

               8. 额外包实例 - jQuery
                  1. 安装jQuery：`npm i jquery`
                  2. 入口函数`app.js`导入：`import $ from 'jquery'`; 
                     1. 通过npm安装的不需要路径
                     2. 等同于`const $ = require("jquery");`

                  3. 修改内容后，重新打包
                     1. `$('body').css('background','pink')`

   17. ## ES7新特性

             1. `Array.prototype.includes`：includes方法用来检测数组中是否包含某个元素，返回布尔类型值

          ```js
           const mingzhu = ['西游记','红楼梦','三国演义','水浒传'];
           console.log(mingzhu.includes('金瓶梅'));
           //.indexOf('西游记') ,若没有返回 -1
          ```

          1. `[**]`：指数操作符，用来实现幂运算，功能与`Math.pow`结果相同。

          ```js
            console.log(2 ** 10); // 1024  (2的10侧方)
            console.log(Math.pow(2, 10)); //1024
          ```


   18. ## ES8新特性

       1. `async`和`await`两种语法结合可以让异步代码像同步代码一样。

          1. 异步编程解决方案

         2. `async`函数

         3. `async`函数的返回值为`promise`对象

         4. `promise`对象的结果由`async`函数执行的返回值决定

            ```js
            async function fn(){
                return '尚硅谷'; //即便是字符串，返回结果也是promise对象； Promise {<fulfilled>: '尚硅谷'}
                /*只要不是自定义返回Promise对象( return new Promise())的其他任何值，则返回结果就是 成功的promise对象 否则如下：
                return new Promise(function(resolve, reject){
                        // resolve("成功的");
                        reject("失败的");
                    }); //Promise {<pending>} */
                /*throw new Error('出错啦！'); //Promise:"rejected"
                */
            }
            const result = fn();
            console.log(result); //Promise {<fulfilled>: '尚硅谷'}
            ```

         5. `await`表达式

               1. `await`**必须**写在`async`**函数中**

               2. `await`右侧的表达式一般为`promise`对象

               3. `await`返回的是`promise`成功的值

               4. `await`的`promise`失败了，就会抛出异常，需要通过`try...catch`捕获处理

                  ```js
                  //创建promise对象 - 成功resolved
                  const p =new Promise(((resolve, reject) => {
                      resolve("用户数据"); //返回的有 状态和值
                      /*
                          [[Prototype]]: Promise
                          [[PromiseState]]: "resolved" 或者 "fullfiled"
                          [[PromiseResult]]: "用户数据"
                          * */
                  }))
                  //await放在async函数中
                  async function main(){
                      let result = await p; //得到promise成功的对象的值 即：用户数据
                      console.log(result);
                  }
                  // - 失败 rejected
                  const p = new Promise(((resolve, reject) => {
                      reject("失败啦");
                  }));
                  async function main(){
                      try {
                          let result = await p;
                          console.log(result);
                      }catch (e){
                          console.log(e);//失败啦
                      }
                  }
                  //调用函数
                  main();
                  ```

         6. 案例：await和async结合使用 - 读取本地文件内容

               ```js
               const fs = require("fs");
               //读取文件，返回的是一个promise对象
               function readWeiXue(){
                   return new Promise((resolve, reject) => {
                       fs.readFile("./weixue.md",(err,data)=>{
                           //如果失败
                           if(err) reject(err);
                           //如果成功
                           resolve(data);
                       })
                   })
               }
               //声明一个async函数
               async function main(){
                   //获取为学的内容
                   let weixue = await readWeiXue(); //await获取的promise的对象的返回值
                   console.log(weixue.toString());
                   //以上用法与同步用法很类似
                   
                   //多个await时，最好用try catch捕获，若某个await的promise失败，整个async函数会终止，后面的语句不再执行。
                   
                   //若不用async和await，返回值为Promise对象，需要用.then链式调用获取值。
               }
               ```

         7. 案例：

               ```js
               //发送AJAX请求，返回结果时Promise对象
               function sendAjax(){
                   return new Promise(((resolve, reject) => {
                       //1.创建对象
                       const x = new XMLHttpRequest();
                       //2.初始化
                       x.open('GET', url);
                       //3.发送
                       x.send();
                       //4.事件绑定
                       x.onreadystatechange = function () {
                           if(x.readyState ===4 ){
                               if(x.status>=200 && x.status<300){
                                   //return xxx;
                                   /*
                                       此处使用Promise的目的时因为，要返回结果return xx给函数SendAjax，
                                       在函数内部的返回结果返回给事件绑定，获取的结果无法'快速'传递到主函数作为返回值
                                       */
                                   //如果成功
                                   resolve(x.response);
                               }else{
                                   //如果失败
                                   reject(x.response);
                               }
                           }
                       }
                   }));
               }
               //=====promise then方法测试=====
               sendAjax("https://api.apiopen.top/getJoke").then(value => {
                   console.log(value);//获取到的数据
               },reason => {});
               //=====async与await测试=====
               async function main(){
                   let result = await sendAjax("https://api.apiopen.top/getJoke");
                   console.log(result);
               }
               //axios的npm包，返回值就是promise对象。
               ```

         8. **ES8对象方法扩展**

               ```js
               const school = {
                   name:"尚硅谷",
                   city:['北京','上海'],
                   course:['前端','java','大数据']
               };
               //获取对象的所有键
               console.log(Object.keys(school));
               //获取对象所有的值
               console.log(Object.values(school));
               //entries，获取键值形式数组
               console.log(Object.entries(school))
               //创建Map
               const m = new Map(Object.entries(school));
               console.log(m);//m集合{’name'=>'..'}
               console.log(m.get('name')); //获取键的值
               //获得对象属性的描述
               console.log(Object.getOwnPropertyDescriptors(school));
               ```

               ```js
               //对象属性
               const obj = Object.create(null,{
                   name:{ //getOwnPropertyDescriptors（）获得以下属性
                       //设置值
                       value：'尚硅谷',
                       //属性特性
                       writable: true,
                       configurable:true,
                       enumerable:true
                   }
               })
               ```

   19. ## ES9扩展运算符与rest参数

          1. `rest`参数与`spread`扩展运算符在ES6中i经引入，不过ES6中只针对数组，在ES6中为对象提供了像数组一样的rest参数和扩展运算符。

             ```js
             //function connect({host, port, username, password}){
             function connect({host, port, ...user}){
                 console.log(host);
                 console.log(port);
                 //console.log(username);
                 //consoel.log(password);
                 console.log(user); //多余参数自动归入...user : rest支持
             }
             connect({
                 host:'127.0.0.1',
                 port:3306,
                 username:'root',
                 password:'root'
             });
             const skillOne = {
                 q:'天音波',
                 w:'金钟罩'
             }
             // ...skillOne => q:'天音波'， w:'金钟罩'
             const skillTwo = {
                 e:'天龙破'
             }
             const mangseng = {...skillOne, ...skillTwo}; //对象合并
             ```

          2. **命名捕获分组**

               ```js
               //声明一个字符串
               let str = '<a href="http://www.atguigu.com">尚硅谷</a>';
               //提取url与 标签文本
               const reg = /<a href="(.*)">(.*)<\/a>/; //当中的正则通过小括号分别捕获
               //执行
               const result = reg.exec(str); 
               console.log(result); //得到三个数组（有索引号），第一个原数组，第二个捕获的第一括号，第二个捕获的第二个括号
               //使用 ?<参数名> 设置正则提取别名，以适应代码变化（更改正则，其他代码不变）
               const reg2 = /<a href="(?<url>.*)">(?<text>.*)<\/a>/;
               const result2 = reg2.exec(str); 
               console.log(result2); //多了名为groups的对象，并且捕获的数据分别对应其变量
               console.log(result2.groups.url);
               ```

          3. **正则扩展 - 反向断言**

             1. ```js
                //声明字符串
                let str = 'JS123121你知道么555啦啦啦';
                // 正向断言
                //'?='表示判断
                const reg = /\d+(?=啦)/; //只想匹配数字 本例语法：、d+表示数字；(?=啦)匹配的数字后面必须要有'啦'才符合要求
                const result = reg.exec(str);
                console.log(result);
                // 反向断言
                let str = 'JS123121你知道么555啦啦啦';
                const reg = /\d+/; //普通匹配：匹配"123121"  
                const reg = /(?<=么)\d+/; //反向断言 匹配"555"  
                const result2 = reg.exec(str);
                console.log(result2);
                ```

          4. **正则扩展 - dotAll模式**

                1. dot `.` 元字符：除换行符以外的任意单个字符。

                   ```js
                   //目标：提取<a>和<p>标签内容分别赋予变量
                   let str = `
                   	<ul>
                   		<li>
                   			<a>肖申克的救赎</a>
                   			<p>上映日期：1994-09-10</p>
                           </li>
                           <li>
                   			<a>阿甘正传</a>
                   			<p>上映日期：1994-07-6</p>
                           </li>
                   	</ul>`;
                   const reg = /<li>\s+<a>(.*?)<\/a>\s+<p>/; //测试匹配
                   const reg2 = /<li>\s+<a>(.*?)<\/a>\s+<p>(.*?)<\/p>/; //普通模式匹配
                   const reg3 = /<li>.*?<a>(.*?)<\/a>.*?<p>(.*?)<\/p>/gs; //修正符 '/s'，让'.'匹配任意字符（用于换行/多个换行）
                   const result = reg.exec(str);
                   console.log(result); //[[Prototype]]: Array(0)
                   let data = [];
                   let result2;
                   while(result2 = reg3.exec(str)){ //此处时=号不是==号
                       console.log(result2);
                       data.push({title:result2[1], time:result2[2]});
                   }
                   //输出结果
                   console.log(data);
                   ```

   20. ## ES10


          1. `Object.fromEntries()` 转为对象
    
             ```js
             //二维数组
             const result = Object.fromEntries([
                 ['name','尚硅谷'],
                 ['学科','大数据']
             ]);
             console.log(result); //[[Prototype]]: Object
             //Map
             const m = new Map();
             m.set('name','ATGUIGU');
             const result2 = Object.fromEntries(m);
             console.log(result2); //[[Prototype]]: Object
             ```
    
          2. `Object.entries`ES8，把对象转为二维数组。
    
             ```js
             const arr = Object.entries({
                 name:"尚硅谷"
             });
             console.log(arr); //[Array(2)]
             ```
    
          3. `trimStart()`与`trimEnd()`：清除左侧或者右侧空格
    
             ```js
             let str = "    ilove you   ";
             console.log(str.trimStart());
             console.log(str.trimEnd());
             ```
    
          4. `flap`和`flapMap`：将多维数组转为低位数组、降低Map维度
    
             ```js
             const arr = [1,2,3,4,[5,6,7]];
             const arr2 = [1,2,3,4,[5,[6,7]]];
             console.log(arr.flat()); //[1, 2, 3, 4, 5, 6, 7]
             console.log(arr2.flat(2)); //[1, 2, 3, 4, 5, 6, 7] ,参数2表示深度
             //Map
             const arr = [1,2,3,4];
             const result = arr.map(item => item * 10); //(4) [10, 20, 30, 40]
             const result2 = arr.map(item => [item * 10]); //二维数组， (4) [Array(1), Array(1), Array(1), Array(1)]
             const result2 = arr.flatMap(item => [item * 10]); //(4) [10, 20, 30, 40]
             ```
    
          5. `Symbol.prototype.description`：用来获取Symbol的字符串描述
    
             ```js
             let s = Symbol('尚硅谷');
             console.log(s.description);
             ```

   21. ## ES11


          1. 私有属性
    
             ```js
             class Person{
                 //共有属性
                 name;
                 //私有属性
                 #age;
                 #weight;
                 //构造方法
                 constructor(name, age, weight){
                     this.name = name;
                     this.#age = age;
                     this.#weight = weight;
                 }
                 intro(){
                     console.log(this.#age);
                 }
             }
             const girl = new Person('小红',18,'45kg');
             console.log(girl.name);
             console.log(girl.#age); //出错，外部无法访问私有属性
             console.log(girl.intro());
             ```
    
          2. `Promise.allSettled([数组参数])`：返回一个始终成功的Promise对象
    
             ```js
             const p1 = new Promise((resolve, reject)=>{
                 setTimeout(()=>{
                     resolve('商品数据 - 1');
                 },1000);
             });
             const p2 = new Promise((resolve, reject)=>{
                 setTimeout(()=>{
                     //resolve('商品数据 - 2');
                     reject('出错啦！');
                 },1000);
             });
             //调用 allsettled方法 ; 数组参数
             const result = Promise.allSettled([p1, p2]);
             /*
             [[Prototype]]: Promise
             [[PromiseState]]: "fulfilled"  --状态始终都是成功--
             [[PromiseResult]]: Array(2)
              ----当两个都是resolve--
                 0: {status: 'fulfilled', value: '商品数据 - 1'}
                 1: {status: 'fulfilled', value: '商品数据 - 2'}
                 length: 2
                 [[Prototype]]: Array(0)
             ---当其中一个是reject时候---    
             0: {status: 'fulfilled', value: '商品数据 - 1'}
             1: {status: 'rejected', reason: '出错啦！'}
             */
             ```
    
          3. `Promise.all([数组参数])`：返回结果根据参数的返回值决定。若参数有一个是reject，则返回结果是reject。必须都resolve其返回结果才是resolve。
    
          4. `String.prototype.matchAll`：正则批量匹配结果。
    
             ```js
             //爬虫类使用率高的方法
             let str = `
             	<ul>
             		<li>
             			<a>肖申克的救赎</a>
             			<p>上映日期：1994-09-10</p>
                     </li>
                     <li>
             			<a>阿甘正传</a>
             			<p>上映日期：1994-07-6</p>
                     </li>
             	</ul>`;
             const reg = /<li>.*?<a>(.*?)<\/a>.*?<p>(.*?)<\/p>/gs; //s修正模式，批量匹配，需要用g（全文/字符串）
             //调用方法
             const result = str.matchAll(reg);
             console.log(result);
             //for...of （个人测试 不能与 扩展运算符 共存）
             for(let v of result){
                 console.log(v); 
             }
             //扩展运算符
             const arr = [...result];
             console.log(arr);
             ```
    
          5. console返回内容里面：
    
             ```js
             /*
             RegExpStringIterator {}
             	[[Prototype]]: RegExp String Iterator
             		next: ƒ next()	 //此处ƒ next()表示可迭代对象
             */
             //可迭代对象可以使用 for...of迭代或者 扩展运算符展开
             ```
    
          6. 可选链操作符`?.`
    
             ```js
             function main(config){
                 const dbHost = config && config.db && config.db.host; //使用逻辑与，判断是否为空值，若为空console会报错
                 const dbHost = config?.db?.host; //查看config?.是否有传入，有传入则db?.查看db有无输入；若都没有不会报错，返回undefined
                 console.log(dbHost);
             }
             main({
                 db:{
                     host:'192.168.1.100',
                     username:'root'
                 },
                 cache:{
                     host:'192.168.1.200',
                     username:'admin'
                 }
             });
             ```
    
          7. 动态import：按需加载
    
             ```js
             //app.js入口函数
             //import * as m1 from "./hello.js"; //静态导入
             //获取元素
             const btn = document.getElementById('btn');
             btn.onclick = function(){
                 import('./hello.js').then(module => {//动态导入
                     console.log(module);
                     module.hello();
                 }); 
             }
             //hello.js
             export function hello(){
                 alert('hello');
             }
             
             ```
    
          8. `BigInt` 数据类型：大数值运算
    
             ```js
             //大整形
             let n = 521n;
             console.log(n, typeof(n));
             let n = 123;
             console.log(BigInt(n));
             console.log(BigInt(1.2));//报错，非整形
             //大数值运算
             let max = Number.MAX_SAFE_INTEGER;
             console.log(max);
             console.log(max+1);
             console.log(BigInt(max)+1); //报错，不能跟普通整型运算
             ```
    
          9. `globalThis` 全局对象 ：对全局参数的操作
    
             ```js
             console.log(globalThis);
             ```



























































