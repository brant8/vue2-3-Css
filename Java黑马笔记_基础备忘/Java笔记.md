## Java笔记

创建一个对象的时候，**至少**要调用一个构造方法。

**包** ==(Package)== 主要用来对类和接口进行分类。

==import== 命令会让编译器载入指定路径下的类、静态变量或者方法。

变量就是申请内存来储存值。

> 对象，还有所有的new出来的对象都是存放在堆里。
> 
> 方法存放在方法体中。
> 
> 基本类型的变量的数据和对象的引用存放在栈中。

### 对象内存图
![输入图片描述](https://s1.ax1x.com/2020/09/12/wUDM1x.jpg)

程序运行后，方法区先存.class内容。从main方法开始运行，方法**压栈运行**，对象进堆内存，main方法运行call方法，压栈后入。

## 内置(基本)数据类型

> 存储原理: 都是直接存储在内存中的内存栈上的，数据本身的值就是存储在==栈空间==里面

**`byte`**: -128 ~ 127 (-2^7 ~ 2^7-1 )，默认值0。占8位有符号的二进制整数，占用空间int四分之一，大型数组中代替整数。

**`short`**:占16位，有符号的二进制整数。-32768 ~ 32767 (-2 ^ 15 ~ 2^15-1)。默认值0。

**`int`**:占32位，有符号的二进制整数，-2,147,483,648 ~ 2,147,483,647(-2^31 ~ 2^31-1)。默认值0。

**`long`**:占64位，有符号二进制整数，范围很大，-2^63 ~ 2^63-1。默认0L(L与l都可以，一般用L好辨认)。

**`float`**:占32位，`单精度`符合IEEE 754标准，在大型浮点数组内省空间。默认0.0f。浮点不能用来表精确值，如货币。

**`double`**:占64位，`双精度`符合IEEE 754标准。浮点数默认double类型。不能表示精确值，如货币，默认值0.0d。

**`boolean`**:数据类型一位的信息，只取true和false。默认值false。

**`char`**:单一的16位Unicode字符，最小值`\u0000`(即为0)，最大值`\uffff`(即为65,535)。默认值`u0000`

## 引用数据类型

> 存储原理: 引用类型继承于Object类，“引用”是存储在有序的内存栈上的，而**对象**本身的**值**存储在==内存堆==上的。

对象、数组都是引用数据类型。引用型默认值都是null。
## 常量

常用final修饰常量，通常用大写字母表示。

byte、int、long、short都可以用十进制，16进制及8进制表示；使用常量时，前缀0表示8进制(如0144)，前缀0x表示16进制(如0x64)。

字符串(String)和字符(char)都可以包含Unicode字符。如`\u0001`。支持转义字符如`\n`。

### 类型转换

[低 ~ 高]自动转换：byte, short, char -> int -> long -> float -> double

自动转换和强制转换过程可能损失精度。

    int i =128;
    byte b = (byte) i;

byte类型为8位，最大值127，强制转换时，值128导致溢出。

> 局部变量时在栈上分配的，并且**没有默认值**，必须初始化后才可以使用。

>实例变量一般设为私有，通过访问修饰符使其对子类可见；实例变量**有默认值**。

>类变量(静态变量)，无论一个类创建多少对象，静态变量只有一份拷贝，储存在静态存储区。


 ### 位运算符
应用于int, long, short, char, byte。 

    例如：A= 60， b=13
    A = 0011 1100
    B = 0000 1101
    +++++++++++++++++++++++++++++++
    A & B = 0000 1100				:相对应位都是1，结果为1，否则为0
    A | B = 0011 1101				:相对应位都是0，结果为0，否则位1
    A ^ B = 0011 0001				:相对应值相同，结果为0，否则位1
    ~A = 1100 0011					:取反运算符，0为1,1为0
    还有其他位运算符，如<<, >>, >>>

### 赋值运算符
### 条件运算符
### instanceof运算符

    （Object reference variable) instanceof (class/interface type)
    例如：
    String name = "James";
    boolean result = name instanceof String;

### 增强for循环
声明语句：该变量类型必须和数组元素类型匹配，其值与此事数组元素的值相等。

表达式：要访问的数组名，或者返回值为数组的方法。

    for(type element: array){
	    System.out.println(element);
    }
    int [] numbers = {10,20,30,40,50};
    for(int x: numbers){
    	System.out.print(x);
    	System.out.print(",");
    }
    String [] names = {"James", "Larry", "Tom", "Lacy"};
    for(String name : names){
    	System.out.print(name);
    	System.out.print(",");
    }
    Result: 
    	10,20,30,40,50
    	James,Larry,Tom,Lacy


### Java Number & Math类

非内置数据类型，属于包装类的对象。

Integer, Long, Byte, Double, Float, Short均为Number的子类。以及Boolean, Character。

    Integer x = 5;
    x = x + 10;
    System.out.println(x);

当x被赋值时，由于x是一个对象，所以编译器要对x进行**装箱**。然后为了使x进行加运算，对x进行**拆箱**。

### Number & Math常用方法

| | |
|------------|--|
|xxxValue()  | 将Number对象你换位xxx数据类型值并返回  |
|compareTo() |将number对象与参数比较 |
|equals()    |判断number对象是否与参数相等 |
|valuesOf()  |返回一个Number对象指定的内置数据类型 |
|toString()  |以字符串形式返回值 |
|parselnt()  |将字符串解析为int类型 |
|abs() 		 |返回绝对值|
| ....		 |.... |

### String对象常用方法

 - byte[] getBytes()
 - byte[] getByte(String charsetName)
 - void getChars(int srcBegin, int srcEnd, charp[ dst, int dstBegin)
 - boolean equals(object anObject)
 - int indexOf(int ch)
 - toString
 - ...


### StringBuffer和StringBuilder
这两类的对象能被对此修改，并且不产生新的未使用对象。
StringBuffer是线程安全的(能同步访问)。StringBuilder有速度优势但非线程安全。

    StringBuffer sBuffer = new StringBuffer("你好世界英文：");
    sBuffer.append("Hello World");
    System.out.println(sBuffer);


### 二分查找法


### 日期
构造函数：Date()，Date(long millisec)。
	
	printf格式化日期:
    System.out.printf("全部日期和时间信息: %tc%n", date);

### 正则表达式
`java.util.regex`有Pattern类，Matcher类和PatternSyntaxException。

#### Pattern

    String content = "Hello World!";
    String pattern = ".*ello.*';
    boolean isMatch = Pattern.matches(pattern, content);

#### 捕获组 Matcher
以单词来算，比如"I need PT300 and hello 123."

## 多态

![Alt](https://s1.ax1x.com/2020/09/11/wUpRSJ.jpg)

    Animal animal = new Person();

### 规则
等号左边，对象变量的类型 决定 可以调用哪些方法。比如animal只能调用父类方法eat()。 
等号右边，对象的类型 决定 具体调用效果。
### 对象变量类型理解
animal持有Person对象，但只有Animal类型的访问权限。
animal是一个人，但我把ta暂看成动物。
### 运行时
运行时调用哪行代码 跟对象变量类型无关。
完全由 指向的对象 的类型 决定。
方法则调用规则。

    Person person = new Person()

写两遍Person是为了创建Person对象并且获得Person所有方法访问权限。

![输入图片描述](https://s1.ax1x.com/2020/09/11/wUP7kV.jpg)
同样的对象变量类型调用同样的方法，
却执行了不一样的代码。

多态使用前：
![输入图片描述](https://s1.ax1x.com/2020/09/11/wUicH1.jpg)

![输入图片描述](https://s1.ax1x.com/2020/09/11/wUFoGT.jpg)
其他讲解：
![输入图片描述](https://s1.ax1x.com/2020/09/11/wUktYV.jpg)



### 多态之【构造器】
**子类是不继承父类的构造器**（构造方法或者构造函数）的，它只是调用（隐式或显式）。如果父类的构造器带有参数，则必须在子类的构造器中显式地通过  ==super==  关键字调用父类的构造器并配以适当的参数列表。

如果父类构造器没有参数，则在子类的构造器中不需要使用  ==super==  关键字调用父类构造器，系统会自动调用父类的无参构造器。




