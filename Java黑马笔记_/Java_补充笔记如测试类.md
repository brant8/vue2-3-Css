### Junit4
单元测试编码规范:
* 类名： 
	* 定义测试类，类名是由被测试类名Test构成。例如：CalculatorTest
* 名：
	*  定义的测试类需要放在`xxx.xxx.xxx.test`包中。
	*  `package com.mylifes1110.test`;
* 方法名：
	*  测试方法的方法名有两种定义方式test测试方法和测试方法。
	*  `testAdd`和`add`
* 返回值：
	*  方法只在类中测试，可以独立运行，所以不需要处理任何返回值，所以这里使用`void`。
	* `public void add()`;
* 参数列表：
	*  因为我们的方法是用来测试的，至于参数列表的传入是没有必要的。我们在测试的时候自行传入需要的参数测试即可。所以在此参数列表为空。
	* `public void add()`;
* `@Test`注解：
	*  测试是需要运行来完成的。如果我们==只有一个main==方法，显然在结构上还是需要我们去注释掉测试过的。在测试方法上方加`@Test`注解来完成测试。
* `@Test`注解jar包Junit4、5： 
	* `@Test`注解是需要我们导入jar包才能使用的。jar包有两个分别是：`junit-4.13-rc-2`和`hamcrest-core-1.3`。一般使用`Junit4`，单元测试还有`Junit5`。
* IDEA快捷导入Junit4、5： 
	* 先创建测试类和方法，然后在测试方法上方加入@Test注解，此时IDEA显示的@Test注解是飘红的，这时候我们使用Alt + Enter组合键来打开导入Junit单元测试列表，然后再选择Junit4或者Junit5确定。

* `assertNull(java.lang.Object object)`：检查对象是否为空
* `assertNotNull(java.lang.Object object)`：检查对象是否不为空
* `assertEquals(long expected, long actual)`：检查long类型的值是否相等
* `assertEquals(double expected, double actual, double delta)`：检查指定精度的double值是否相等
* `assertFalse(boolean condition)`：检查条件是否为假
* `assertTrue(boolean condition)`：检查条件是否为真
* `assertSame(java.lang.Object expected, java.lang.Object actual)`：检查两个对象引用是否引用同一对象（即对象是否相等）
* `assertNotSame(java.lang.Object unexpected, java.lang.Object actual)`：检查两个对象引用是否不引用统一对象(即对象不等)

```
@Test
public void add(){
	int result = calculator.add(10,10);
	Assert.assertEquals(1,result);//测试预期值是否与结果一致
}
```
