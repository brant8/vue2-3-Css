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

    2. 常用元素的属性操作

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

    3. 表单元素的属性操作

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

       3. 案例分析：登录框按钮有眼睛按钮，点击可切换明文显示密码，详细代码]()

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

       4. 











