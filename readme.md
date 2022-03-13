### 学习Vue2 
1. console输入Vue.config查看vue配置。



___

### CSS学习
1. 选择器：选择标签。
    1. 标签（元素）选择器如`<div>`,`<p>`。
    2. class类选择器。`.class`
    3. 一个标签多类名：`<div class="class1 class2">`
    4. id选择器，`#class`

2. 字体复合写法属性(强制顺序)
   1. `font: font-style font-weight(加粗) font-size/line-height font-family`;
   2. 文本默认`font-height:400;`
   3. 如：`font:12px/24px 'Microsoft Yahei'`，24px为行高。

3. `text-decoration`装饰文本，下划线，删除线，上划线等。
4. 行间距：`line-height`
   1. 文本缩进：`text-indent: 2em;` => 可用于链接导航

5. Emmet语法快速生成HTML标签。
   1. tab键快速生成
   2.多标签`*`。如`<div>*3`生成3个div。
   1. 父子关系标签`>`。如`ul > li`。扩展`ul > li#two`中li的id为two。
   2. 兄弟关系标签`+`。如`div+p`。
   3. 带有类名或者id名。如`.demo`,`#demo`然后tab。
   4. 带有顺序关系的`$`，如`.demo$*5`。生成demo1,demo2等。
   5. 快速填充内容`{}`。如`div{我是大哥}`为`<div>我是大哥</div>`。

6. 后代选择器（包含选择器）为后代元素关系。
    格式：元素1  元素2  {样式声明}
7. 子选择器只能选择作为某元素的最近一级子元素(亲儿子)。
    格式：元素1 > 元素2  {样式声明}
8. 并集选择器：元素1，元素2 {样式声明}

9.  伪类选择器用于向某些选择器添加特殊的效果，比如给链接添加特殊效果，或选择第1个，第n个元素。
   1. 格式：用`:`表示，比如`:hover`，`:first-child`
   2. 链接伪类选择器，如`a:link`,`a:visited`,`a:hover`,`a:active`，a标签最好按照LVHA顺序确保生效。

10. `：focus`伪类选择器获得焦点的表单元素，一般用于`input:focus{}`。
11. 元素显示模式：
    1.  块元素，`div`,`<h1>~<h6>`等。<mark>(重点)</mark>
        1.  独占一行
        2.  高度，宽度，外编剧，内边距都可以控制
        3.  宽度默认是容器(父级宽度如body)的100%
        4.  是一个容器及盒子，里面可以放行内或者块级元素
        5.  文字类如<p>标签内不能放块级元素</p>
    2.  行内元素，`<a>`,`<i>`,`<span>`等<mark>(重点)</mark>
        1.  相邻行内元素在一行上，一行可以显示多个
        2.  高、款直接设置是无效的
        3.  默认宽度是本身内容的宽度
        4.  行内元素只能容纳文本或其他行内元素
        5.  特殊情况`<a>`可以放块级元素
    3.  行内块元素，同时具有块元素和行内元素特点。
        1.  `<img>`,`<input>`,`<td>`<mark>(重点)</mark>
        2.  和相邻行内元素(行内块)在一行上，之间会有空白缝隙，一行可显示多个
        3.  默认宽度就是它本身内容的宽度（行内元素特点）
        4.  高度，行高，外边距以及内边距都可以控制（块级元素特点）

12. 元素显示模式转换
    1.  比如增加链接`<a>`的触发范围
    2.  转换为块级元素：`display:block` <mark>(重点)</mark>
    3.  转换为行内元素: `display: inline`
    4.  转换为行内块元素: `display: inline-block` <mark>(重点)</mark>

13. 文字居中案例：
    1.  行内元素`<a>`的<mark>文本要垂直居中</mark>的方法：
        1.  `a { height:40px; line-height:40px }` 
        2.  方法：让其行高line-height与盒子高度相等即垂直居中 <mark>(重点)</mark>
        3.  行高 = 上空隙 + 文字本身高度 + 下空隙
![avatar](https://i.postimg.cc/Pq3HwXyY/1.png)    

14. 背景颜色`background-color`，默认透明`transparent`
15. 背景平铺`background-repeat`，默认情况repeat。
    1.  repeat
    2.  no-repeat：不平铺
    3.  repeat-x：横向平铺
    4.  repeat-y：纵向平铺
16. 背景图片位置：`background-position：x轴  y轴`
    1.  参数值：length 
        1.  子参数 百分比 | 浮点数 | 单位标识
    2.  参数值：position(方位名词前后顺序无影响)
        1.  子参数 top | center | bottom | left | center | right
17. 背景图片固定：`background-attachment： scroll | fixed`
    1.  scroll：默认。背景图片随对象内容滚动
    2.  fixed：背景图片固定
18. 背景复合写法(非强制顺序)
    1.  background: 背景颜色 背景图片地址 背景平铺 背景图片滚动 背景图片位置
19. 背景颜色半透明：`background: rgba(0,0,0,0.3)`。CSS3新增属性，IE9+才支持。
    1.  rgba: red , green , blue , Alpha(透明度)
    2.  盒子背景色半透明，内容不影响

20. CSS三特性之继承性：子标签会继承父标签的某些样式
    1.  如使用body让其子元素继承字体统一
    2.  可继承元素样式：`text-`,`font-`,`line-`,`color`
    3.  如父元素：`body{ font: 12px/1.5 'Microsoft Yahei'}`
    4.  如子元素：`div{ font-size: 14px}`，其中子元素行高继承自父的`12px/1.5`中的`1.5`乘以自身字体大小`14px`为子元素的行高。(常用)
21. CSS三特性之层叠性：谁靠近使用谁的样式
22. CSS三特性之优先级：
    1.  选择器相同，执行层叠性
    2.  选择器不同，根据选择器权重执行
    3.  如：`div{ color: pink!important;}`  
 
选择器 | 选择器权重
---- | ---
继承 或者 * | 0，0，0，0
元素选择器 |  0，0，0，1
类选择器，伪类选择器 |  0，0，1，0
ID选择器 |  0，1，0，0
行内样式style="" |  1，0，0，0
!important 重要的 |  无穷大

23. 权重叠加:复合选择器的权重。权重虽然是叠加，但是永远不会有进位。

24. 页面布局三大核心：盒子模型，浮动，定位。布局过程如下：
    1.  定义盒子
    2.  给盒子css样式，摆放到相应位置。
    3.  往盒子装内容

25. 盒子模型 - 从外到里： 外边距`margin`、边框`border`、内边距`padding`、实际内容`content`。== 重点 ==  
    
26. 边框border三部分组成：宽度(粗细)`border-width`、边框样式`border-style`、边框颜色`border-color`。
    1.  `border-style`：
        1.  `solid`  
        2.  `dashed`(虚线) 
        3.  | `dotted`(点线) 等
    2.  例子：`border:1px solid red;` 没有顺序
    3.  `border-top`,`border-bottom`,`border-left`,`border-right`.
        1.  单边不同样式时，先设置四边，再设置单独一边的样式。
        2.  例子：`table, td {..}` 表格内部也有border。
    4. `border-collapse:collapse`：两个相邻边框合并成一个边框，常用在table上。

27. 边框`border`会影响盒子的大小。
    1.  例子：盒子`div{ width:200px; height:200px; border-width:10px }`
    2.  例子中的盒子整体变为`220px`。实际盒子内容大小不变`200px`。整体上大了`20px`。

28. 内边距`padding`内部为填充内容。
    1.  `padding-left`等
    2.  内边距复写组合规范
        1.  padding：5px；所有内边距(顺时针方向)
        2.  padding：5px 10px，上下内边距5像素，左右内边距10像素。
        3.  padding：5px 10px 20px， 上边5像素，左右10像素，下边20像素
    3.  例子：`div{ width:200px; height:200px; padding:5px }`
    4.  例子中的内边距影响了块，最终大小为`210*210`.
    5.  导航栏利用padding给导航标题设置到左右距离的padding相等。
    6.  未给盒子设置`width/height`的情况时，pading不会撑开盒子(未设置区)的大小。
    7.  给盒子设置`100%`再加`padding`后会有左右/上下滚轴出现。
    8.  嵌套情况下，父级设置宽度高度，子级padding小于父级宽度高度不会偏移/溢出。

29. 外边距，控制盒子与盒子之间的距离。
    1.  margin-top等。
    2.  块级盒子`水平居中`条件：
        1.  盒子必须制定了宽度`width`。
        2.  盒子的`左右外边距`都设置为`auto`。3种常用写法如下：
            1.  `margin-left:auto; margin-right:auto`
            2.  `margin: auto`
            3.  `margin：0 auto`
        3.  盒子内的行内元素居中对齐可以使用`text-align:center`
    3.  相邻块元素垂直外边距的合并：
        1.  上下两个盒子外边距各有距离时，取两个值中较大者的值。
    4.  嵌套元素垂直外边距的塌陷：
        1.  两个嵌套(父子)关系的块元素，父元素有上外边距同时，子元素也有上外边距，此时父元素会塌陷较大的外边距值。
        2.  即：父与子一起向下移动，移动的数值为其中移动的较大值。
            1.  解决一起移动的方法一：父级添加上边框
            2.  解决一起移动的方法二：父级添加上内边距
            3.  解决一起移动的方法三：父元素添加`overflow: hidden;`
    5.  浏览器默认有内外边距。
        1.  消除浏览器默认边距： `*｛padding:0; margin:0 ｝`
        2.  行内元素为了照顾兼容性，尽量只设置左右内外边距，不要设置上下内外边距。但是转换为块级和行内块元素就可以。
        3.  文字溢出部分：`text-overflow: ellipsis` 输出效果让其溢出部分省略号。
        4.  倾斜标签 `<em> | </em>`输出斜线，并用css让其变成竖线：`font-style:normal`
        5.  去掉`ul li`的圆点，`li{ list-style: none}`

30. Photoshop基本操作。用来测量和切图。
    1.  快捷键： 
        1.  标尺->`Ctrl + R`
        2.  取消选区：`Ctrl + D`
        3.  取色：Eyedropper Tool工具（吸取）

31. CSS3新增的圆角边框
    1.  `border-radius: length`。如`border-radisu:10px`相当于在矩形的四个角放四个10px的圆。
    2.  做一个圆形，先做一个正方形然后CSS：`border-radius： 矩形的一半长度`，相当于圆放在正方形中心。也可以`border-radius： 50%`.
    3.  圆角矩形（横向）：设置成高度的一半。
    4.  不规则弧度：`border-radius: 左上角 右上角 右下角 左下角`

32.  CSS3新增的盒子阴影 ==(重点)==
     1. 格式`box-shadow: h-shadow v-shadow blur spread color inset;`
        1. h-shadow：必须，水平阴影的位置，允许负值。
        2. v-shadow：必须，垂直阴影的位置，允许负值。
        3. blur：可选，模糊距离。
        4. spread：可选，阴影的尺寸。
        5. color：可选，阴影的颜色。
        6. inset：可选，将外部阴影(outset)改为内部阴影。
        7. 例如：`box-shadow: 10px 10px 10px 10px black inset` 其中`inset`不写默认`outset`但是`outset`不能写在css中。

33.  CSS3新增的文字阴影，了解即可。
     1. 格式`text-shadow： h-shadow v-shadow blur color`














