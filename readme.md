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
        5.  默认行内块元素相邻之间有空隙，若需要去除空隙可以设置成浮动
        6.  添加`text-align: center`文字可以自动居中

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

34. 浮动：float。(行内块之间默认有空隙。)
    1.  float： none | left | right
    2.  浮动为脱标，相当于标准盒子的外层覆盖。
    3.  浮动的盒子不在保留原先的位置（其他标准流盒子会顶上）
    4.  浮动元素具有行内块元素特性。
        1.  `span1{ width:200px; height:100px; backgroundcolor-:pink }`：没有效果，看不到任何东西，行内元素无法设置有效高度宽度。
        2.  `span2{ width:200px; height:100px; backgroundcolor-:pink； float:left }`：有效果。可以看到方块背景颜色。
        3.  行内元素有了浮动不需要转换成块级或者行内块直接可以设置高端宽度。
    5.  如果块级盒子没有设置宽度，默认宽度和父级一样宽。但是添加浮动后，它的大小根据内容动态决定。
    6.  浮动的盒子指挥影响浮动盒子`后面`的`标准流`，不会影响`前面`的`标准流`。
35. 设置完marging,padding时，对于子元素，清楚继承时注意权重问题，否则清除失败。
    
36. 为什么要清除浮动？
    1.  由于父级盒子很多情况下，不方便给高度，但是子盒子浮动又不占有位置，最后父级盒子高度为0时，就会影响西面的标准流盒子。
    2.  如果父盒子本身有高度，则不需要清除浮动。
    3.  清除浮动之后，父级会根据浮动的子盒子自行检测高度。父级有了高度就不会影响下面的标准流了。
        1.  `选择器{clear:属性值}`。实际使用`clear:both`。
37. 清除浮动方法
    1.  额外标签法，W3C推荐的做法。
        1.  在浮动的子元素的最后一个添加标签`clear:both`
        2.  注意事项，最后一个元素必须是块级元素，行内元素无效。
        3.  其他人做法：浮动末尾添加一个空标签，并且添加`clear:both`。
    2.  父级添加`overflow`属性。(浮动错位：子不教父之过)
        1.  参数：`hidden` | `auto` | `scroll`
        2.  常用`hidden` & `auto`
    3.  父级添加双伪元素。
    4.  父级添加`after`伪元素。
```css
/*添加afer*/
选择器:after{ /*通常选择器单独一个命名为.clearfix*/
    content:"";
    display:block;
    height:0;
    clear:both;
    visibility:hidden;
}
选择器{ /*IE6、7专有*/
    *zoom:1;
}

/*添加双伪元素*/
/*before在前面也插入盒子，after在后面也插入盒子。双门堵。*/
.clearfix:before, .clearfix:after{
    content:"";
    display:table;
}
.clearfix:after{
    clear:both;
}
.clearfix{
    *zoom:1;
}
```

38. 盒子CSS属性建议书写顺序：
 ![avatar](https://i.postimg.cc/jqhCJt0j/2.png)  

39. 制作案例注意点：
    1.  导航
        1.  实际开发中，使用`li`+`a`作为导航。若直接用a，搜索引擎容易辨别为堆砌关键字嫌疑降权风险。
        2.  盒子里面有一个子元素浮动的话，其他子元素也需要浮动，否则浮动效果非期望值。
        3.  `li`里的`a`要做到与父级同高度时，需要给与同高度的参数，以及转换为块级`display: block`
        4.  导航栏文字不一样多时，最好给`a`左右padding撑开盒子，而不是指定宽度。
        5.  垂直居中`line-height：高度值px`
        6.  计算高度与宽度后若效果出了父元素，可尝试设置`border: 0`
    2.  内容部分
        1.  背景图片一般配置 `background-image:url(link) no-repeat top center;`
        2.  盒子填充背景半透明 `background:rgba(0,0,0,0 0.3)`
        3.  浮动的盒子不会有外边距合并问题
    3.  页脚内容
        1.  网站地图使用自定义列表`<dt>`大哥，`<dl>`小弟(垂直列表)。

40. <strong>定位 = 定位模式 + 边偏移</strong>。
    1.  定位模式用于指定一个元素在文档中的定位方式。通过`position`属性设置
        1.  `static` - 静态，按照标准流特性摆放位置，<strong>没有边偏移</strong>。（少用）
        2.  `relative` - 相对，相对定位是根据<strong>自身原来的定位</strong>来说的，自恋型。位置挪走了但是保留，其他盒子挤不上去。（常用）  
            1.  `position: relative; top:100px; left:100px`
            2.  表示<strong>原来的位置</strong>在目前位置的上面100px，左边100px。
            3.  <strong>相对定位并没有脱标。典型的应用是给绝对定位当爹的。</strong>
        3.  `absolute` - 绝对，移动位置时，时相对于它祖先元素来说的(拼爹型，看父亲脸色行事)。
            1.  如果没有祖先元素或者祖先元素没有定位，则以浏览器为准定位(Document文档)。
            2.  如果祖先元素右定位(相对、绝对、固定)，则以最近一级的有定位祖先元素为参考点移动位置。
            3.  <strong>绝对定位不再占有原先的位置。</strong>
        4.  `fixed` - 固定，元素固定于浏览器可视区的位置，在浏览器滚动时元素的位置不变。
            1.  以浏览器的可是窗口为参照点移动元素。
            2.  跟父元素没有关系，不随滚动条滚动。
            3.  固定定位不占有原先的位置。
            4.  案例：到顶部在主体页面的右下角，固定在`left:50%`，然后主题页面一半的右边的宽度。
        5.  `sticky` - 粘性，被认为是相对定位和固定定位的混合。
            1.  以浏览器的可视窗口为参照点移动元素
            2.  粘性定位占有原先的位置。
            3.  必须添加`top`，`left`， `right`， `bottom`其中一个才有效。
            4.  兼容性差，IE不支持。
            5.  案例：导航栏往下滚动时，`top: 0`则会固定在顶部。
    2.  边偏移则决定了该元素的最终位置。
        1.  `top` - 顶部偏移量，定义元素相对于其父元素上边线的距离。
        2.  `bottom` - 底部偏移量，定义元素相对于其父元素下边线的距离。
        3.  `left` - 左侧偏移量，定义元素相对于其父元素左边线的距离。
        4.  `right` - 右侧偏移量，定义元素相对于其父元素右边线的距离。

41. 定位叠放次序 `z-index`，控制和自动前后次序。x轴，y轴，z轴。
    1.  数值可以是正数，负数，0，默认是auto。数值越大，盒子越靠上。
    2.  如果属性值相同，则按照书写顺序，后来居上
    3.  数字后面不能加单位
    4.  只有<strong>定位的盒子</strong>才有`z-index`属性
    5.  加了<strong>绝对定位的盒子</strong>不能通过`margin: 0 auto`水平居中。但是可以通过计算方法实现水平和垂直居中。
        1.  水平居中：父容器宽度的一半，`left: 50% `，`margin-left：负自身长度/2`
        2.  垂直居中：父容器高度的一半，`top: 50% `，`margin-top：负自身长度/2`

42. 定位特殊特性：和浮动类似
    1.  行内元素添加绝对或者固定定位，可以直接设置高度和宽度。
    2.  块级元素添加绝对或者固定定位，如果不给宽度或者高度，默认大小时内容的大小。（脱离标准流）
    3.  脱标的盒子不会触发外边距塌陷。
43. 浮动和定位的区别：
    1.  绝对定位(固定定位)会完全压住盒子，并且会压住标准流下面所有的内容。
    2.  浮动不同，只会压住它下面标准流的盒子，但是不会压住西面标准流盒子里面的文字(图片)。
        1.  浮动之所以不会压住文字，因为浮动产生的目的就是为了做文字环绕效果，文字围绕漂浮元素。
    3.  浮动可以让多个块级元素一行显示或者左右对齐盒子，多个会计盒子水平显示就用浮动布局。
    4.  定位的最大的特点是有层叠概念，可以让多个盒子前后叠压显示。如果元素自由在某个盒子内移动就用定位布局。

44. 元素的显示和隐藏
    1.  `display`
        1.  block : 转换成块级元素；显示元素
        2.  none ： 隐藏对象，并且位置也没有了。（用处多）
        3.  inline ：类似于`<span>`
    2.  `visibility`
        1.  inherit
        2.  visible：可见
        3.  collapse
        4.  hidden : 隐藏并且占有着原来的位置。
    3.  `overflow`溢出，如果有定位的盒子，慎用hidden，会隐藏多余的部分。比如Hot热点标签样式。
        1.  visible： 默认设置
        2.  auto: 允许溢出，溢出部分用滚动条显示
        3.  hidden：隐藏溢出部分
        4.  scroll：溢出部分用滚动条

45. CSS高级技巧的使用

46. 精灵图`sprites`：为了有效地减少服务器接收和发送请求的次数，提高网页的加载速度。
    1.  主要针对背景图片的使用。
    2.  移动背景图片位置，此时可以用`background-position`
    3.  移动距离就是图片的x和y坐标。注意：网页中的坐标有所不同。
    4.  一般情况都是往上往左移动（起始位置左上角），所以数值都是负值。
    5.  需要精确测量每个小背景图片的大小和位置。

```css
.ico{
    width:60px;
    height:60px;
    margin:100px auto;
    background:url("xxx.jpg");
    repeat:no-repeat;
    background-position: -120px 0;
    /*background:url("xxx.jpg") no-repeat -120px 0; */
}
```

47. 字体图标`iconfont`:展示的是图标，本质属于字体。注意：不能替代精灵技术，只能对图标部分技术提升和优化。
    1.  字体图库：
        1.  icomoon、iconfont
    2. 选择图标，然后使用css导入方式导入到页面，进行字体声明，具体使用步骤查看[视频链接教程](https://www.bilibili.com/video/BV14J4114768?p=257&spm_id_from=pageDriver)。
    3. 在当前图标中添加新的图标，使用selection.json方式导入网站再添加字体。

48. CSS三角制作
49. CSS用户界面样式
    1.  鼠标样式`li{cursor:pointer}`
    2.  轮廓线`outline`，常用输入框、表单`outline:0`，或者`outline：none`可以去掉默认蓝色边框
    3.  防止拖拽文本框变换尺寸`textarea{resize:none}`

50. `vertical-align`：经常用于设置图片或者表单（行内块元素）和文字垂直对齐。
    1.  有效范围：行内元素，行内块元素
    2.  `baseline`：默认，元素放在父元素的基线上。
    3.  `top`：把元素的顶端与行中最高元素的顶部对齐。
    4.  `middle`：把此元素防止再父元素的中部。
    5.  `bottom`：把父元素的顶端与行中最低的元素的顶端对齐。
    6.  场景：一般情况下图片和盒子底部有一段距离，若加上文字可以看到图片的水平是在基线上。
        1.  解决方法：使用`vertical-align`的除基线外的其他三个。(提倡做法)
        2.  把图片转换为块级元素`display:block`

51. 溢出的文字用<strong>省略号显示</strong>
    1.  单行文本溢出省略号需要三个条件。
        1.  `white-space: nowrap;` - 先强制一行内显示文本(默认normal自动换行)
        2.  `overflow: hidden;` - 超出的部分隐藏
        3.  `text-overflow: ellipsis;` - 文字用省略号替代超出的部分
    2.  多行文本溢出显示省略号。 注意点：多行显示有兼容性问题，适合于webKit浏览器或移动端。
        1.  `overflow: hidden;`
        2.  `text-overflow: ellipsis;`
        3.  `display: -webkit-bot;` - 单行伸缩盒子模型显示
        4.  `-webkit-line-clamp: 2;` - 限制在一个块元素显示的文本行数
        5.  `-webkit-bot-orient: vertical;` - 设置或检索伸缩盒对象的子元素排列方式，如垂直居中
        6.  推荐：多行推荐在后端直接限制字数再传递到前端来。

52. CSS初始化，即清空CSS默认样式。

53. HTML5新特性，IE9+才支持。语义化标签对搜索引擎更好。
    1.  `<header>`（头部标签）,`<nav>`（导航，与头部分开）,`<article<>`,`<section>`(定义文档某个区域) ,`<aside>`（侧边栏） ,`<footer>`
    2.  `<audio>`, `<video>`多媒体标签
        ![3.png](https://i.postimg.cc/T1sXCFs3/3.png)
    3. `<video src="文件地址" controls="controls"></video>`，详细参考[w3c school](https://www.w3schools.com/html/html5_video.asp)
    4. `<input>`属性值，`type="email"`等

54. CSS3新增选择器，下列三种选择器 <strong>权重 = 10</strong>
    1.  属性选择器：可以根据元素特定的属性来选择元素，可以不用借助于类或者id选择器。
        1.  `div[class^=icon]` - 权重div=1,icon=10,总权重=11   
        2.  `input[value]`， `section[class$=data]`，`section[class*=icon]`
    2.  结构伪类选择器
        1.  `ul :first-child`(空格表示后代选择器)或者`ul li:first-child`。
        2.  `ul li:nth-child(2)`选择第2个子元素，2为可变值，`nth-child`对所有子元素排序。
            1.  `nth-child(n)`：n可以是数字，关键字和公式。
            2.  如：`nth-child(2)`,n从1开始。n关键字：`even`,`odd`，奇偶数，如`nth-child(even)`。
            3.  公式时，n从0开始： `nth-child(-n+5)`，选择前5个子元素；`nth-child(n+2)`，规避第1个从第2个子元素开始； `nth-child(2n)`，等价于even偶数
            4.  场景应用：输出多个列表，每行5个，共2行。用5n消除每行最后不想要的样式。
            5.  `nth`会把所有孩子都排序列号，混合标签时会有问题
        3.  `nth-of-type`方式对指定元素排序。
            1.  `nth-of-type`会把<strong>指定的盒子</strong>排列序号，文档例子中执行的时候首先看`:nth-child(1)`之后， 会回去看前面的`div`
    3.  伪元素选择器：利用CSS创建新标签元素，而不需要HTML标签，简化HTML结构。
        1.  如`:hover`遮罩原来在盒子上再加`div`再进行定位等，此后用为元素即可。
        2.  `before`和`after`创建一个元素，但是属于行内元素。
        3.  新创建的这个元素再文档树中找不到的我们成为伪元素。语法：`element::before{}`。
        4.  `before`和`after`必须有<strong>content属性</strong>。
        5.  `before`在父元素内容前面创建元素，`after`在父元素内容的后面插入元素。
        6.  为元素选择器和标签选择器一样，<strong>权重为1</strong>
            1.  `::before`
            2.  `::after`

55. 伪元素清除浮动。
    1.  父级添加`after`伪元素，`after`使用`display:block`，如方法三。
        1.  具体：`element::after{content:""; display:block; height:0; clear:both; visibility:hidden;}`
        2.  其中`clear:both`为核心
    2.  父级添加双伪元素
        1.  具体：`element::before,element::after{ content:""; display:table;}  &  element::after{ clear:both; }`
    3.  其他清除浮动方法
        1.  额外标签法：盒子【】中有`【 [浮动1] [浮动2] [clear:both] 】`，其中末尾新的空标签必须是块元素。

56. CSS3盒子模型可以通过`box-sizing`来指定盒子模型。
    1.  `box-sizing: content-box`（默认样式）： 盒子大小为 <strong>width + pading + border</strong> 
    2.  `box-sizing`：盒子最终大小为`width`宽度，不会撑大盒子（前提：padding和border值不大于宽度情况）。
    3.  推荐样式：`* { padding:0; margin:0; box-sizing:border-box}`

57. CSS3其他特性：
    1.  滤镜`filter`：将模糊或颜色偏移等图形效果应用于元素。
        1.  `filter: 函数()`：如`filter:blur(5px); `，blue模糊处理，数值越大越模糊。
    2.  `calc（）`函数：在声明CSS属性值时执行一些计算。  
        1.  `width:calc（100%-80px）`（放在子元素）：子盒子永远比父盒子小80px。 注意：减号要空格。

58. CSS3的过渡动画`transition`，元素从一种状态渐渐过渡到另外一种状态。经常和`:hover`一起使用。
    1.  `transition：要过渡的属性 花费时间 运动曲线 何时开始`
        1.  属性：想要变化的CSS属性，宽度高度背景颜色，内外边距都可以。所有属性都要变化过渡使用`all`。
        2.  花费时间：单位是秒，如`5s`。必须写时间。
        3.  运动曲线：默认是ease，可以省略。还有匀速等其他。
        4.  何时开始：单位是秒，必须写单位，可以设置延迟出发，默认是`0s`，可以省略。
        5.  多个变化使用逗号`,`隔开。
        6.  <strong>谁做过渡给谁加</strong>

59. 项目搭建：总结知识
    1. `ico`小图标：`<link rel="shorcut icon" href="images/favicon.ico" />
    2. SEO搜索引擎优化三大标签：`title`, `description`, `keyword`
       1. `Title`：网页入口对网页主题归属大的最佳判断点。
          1. 建议：网站名（产品名） - 网站的介绍（尽量不超过30个汉字）
       2. `Description`：网站的总体业务和主题概括。采用：我们是..我们提供..之类。
       3. `Keywords`：最好6-8个关键词，用英文逗号隔开。
`


























