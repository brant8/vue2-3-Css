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
    4.  `ul{ overflow:hidden }`&`li{ float:left }`使用`overflow:hidden`，因为ul的子级使用float属性会产生浮动，所以使用清除子级使用float对父级产生不能撑开问题；



56. CSS3盒子模型可以通过`box-sizing`来指定盒子模型。
    1.  `box-sizing: content-box`（默认样式）： 盒子大小为 <strong>width + pading + border</strong> 
    2.  `box-sizing`：盒子最终大小为`width`宽度，不会撑大盒子（前提：padding和border值不大于宽度情况）。
    3.  推荐样式：`* { padding:0; margin:0; box-sizing:border-box}`
    4.  其他说明[mozillaCSS说明](https://developer.mozilla.org/zh-CN/docs/Web/CSS/box-sizing) 

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

59. 项目搭建：基础总结知识
    1. `ico`小图标：`<link rel="shorcut icon" href="images/favicon.ico" />
    2. SEO搜索引擎优化三大标签：`title`, `description`, `keyword`
       1. `Title`：网页入口对网页主题归属大的最佳判断点。
          1. 建议：网站名（产品名） - 网站的介绍（尽量不超过30个汉字）
       2. `Description`：网站的总体业务和主题概括。采用：我们是..我们提供..之类。
       3. `Keywords`：最好6-8个关键词，用英文逗号隔开。
    3. 模板设置父元素设置高度`height`，在相同元素内设置`line-height`则内部字体居中。
    4. 导航之间相互用竖线`|`隔开，可以使用奇偶的偶数`li`内加个`width:1px`以及设置`height：12px`即可（具体可以测量）。
    5. 设置宽度`w元素`，然后在`class`重复添加该宽度`w`即可。
    6. LOGO SEO优化：
       1. logo先放`h1`标签，提权，告诉搜索引擎这个地方很重要。
       2. `h1`里再放一个链接，可以返回首页的，logo的背景图片给链接即可。
       3. 为了搜索引擎收录，链接里面放文字（网站名称），但是文字不要显示出来。
          1. 方法一：`text-indent`一道盒子外面`text-indent:-9999px`，然后`overflow：hidden`，淘宝的做法。
          2. 方法二：给`font-size:0`就看不到文字了，京东的做法。
       4. 最后给链接一个`title`属性，这样鼠标放logo就可以看到提示文字了。
    7. 图片被文字包裹效果，让图片所在的元素浮动，文字另起同级别标准流元素。为了SEO优化，让其图片使用`<h5>`包裹。
    8. 盒子设置高度与不设置高度
       1. 如果不给高度，浮动的元素时脱标的，不会将盒子撑开，所以要清除浮动(设置高度)。
       2. 父盒子没有给高低压，子盒子可以随扩大，高度撑开，但是子盒子时浮动的，父盒子实际高度为0。要清除浮动，是的大盒子高度随子盒子高度改变。
    9. 设置文本字数多出部分单行`<li>`为例：
       1. 步骤一：` overflow: hidden; `，步骤二：`white-space: nowrap;`，步骤三：`text-overflow: ellipsis;`
    10. 楼层区，左侧栏固定导航。
    11. 如果标题`h1`,`h2`等字体过粗，可以使用`font-weight:400`降到普通文字粗细。
    12. 注册表格使用`<form>`和`<li>`元素时，使用左侧`<label>`时设置一个宽度，可以让其对右对齐。
        1.  `<li><label for="">手机号：</label> <input type="text"> <span class="error"> <i class="icon"></i>手机号码格式不对，请重新输入</span>`
        2.  `label text-align:right; display:inline-block; width:88px;}`
    13. 验证安全强度写法：
        1.  `<li class="safe">安全强度 <em>弱</em> <em>中</em> <em>强</em> </li>`

60. 2D转换：`transform`，实现了元素的位移、旋转、缩放等效果。优点：<strong>不会影响到其他元素的位置</strong>。对行内标签没有效果。
    1.  `translate`：移动，类似定位。
        1.  `transform:translate(x,y);` 或者分开写。
        2.  `transform:translateX(n);`
        3.  `transform:translateY(n);`
        4.  `translate`中的百分比单位时相对于自身元素的`translate(50%,50%)`，移动的距离为自身长度的50%。
    2.  `rotate(度数)`：旋转。
        1.  旋转单位`deg`如`rotate（45deg)`
        2.  角度为正时，顺时针；负时，逆时针。
        3.  默认旋转的中心点为元素的中心点。
        4.  操作案例1：旋转`360度`，使用过渡动画`transition`能让其旋转。
        5.  操作案例2：`>`三角使用正方形`border`旋转操作
        6.  案例：产品图片`:hover`放大。常跟`overflow:hidden`和`transition:all 0.4s`配合使用。
    3.  `scale（x,y）`：缩放，倍数不跟数值单位。不会影响其他盒子。
        1.  x和y使用逗号分隔。可以设置转换中心点缩放，默认以中心点缩放。
        2.  `transform:scale(1,1)`：宽和高都放大一倍，相当于没放大。
        3.  `transform:scale(2,2)`: 宽和高都放大了2倍。
        4.  `transform:scale(2)`:相当于scale(2,2)
        5.  `transform:scale(0.5,0.5)`：缩小
        6.  其他方式放大缩小如`:hover`设置宽度高度，以`top`水平固定向其他方向放大。
    4.  `transform-origin: x y`：旋转中心点。空格隔开。
        1.  x与y默认转换的中心点时元素的中心点(50% 50%)
        2.  还可以给xy设置`像素`或者`方位名词`（top bottom left right center）
    5.  2D转换综合写法
        1.  格式：`transform:translate() rotate() scale()..`等。
        2.  其顺序会影响转换效果。先旋转会改变坐标轴方向。
        3.  当同时有位移和其他属性的时候，记得要<strong>将位移放到最前面</strong>。

 1.  动画`animation`：通过设置多个节点来精确控制一个或一组动画，常用来实现复杂的动画效果，如连续播放。   
     1.  步骤一：先定义动画
     2.  步骤二：调用动画
```CSS
        /*打开一个网页，一个盒子从左边走到右边*/
        
        @keyframes move {
            0% {
                transform: translateX(0px);
            }
            100% {
                transform: translateX(1000px)
            }
        }
        
        div {
            width: 300px;
            height: 300px;
            background-color: pink;
            /*调用动画*/
            animation-name: move;
            /*持续时间*/
            animation-duration: 10s;
        }
```
62. 动画讲解
    1.  动画序列：
    2.  `0%`时动画的开始，`100%`时动画的完成，这样的规则就是动画序列。
    3.  `@keyframes`创建当前样式逐渐改为新样式的动画效果。
    4.  可以任意多样式，任意多的次数。
    5.  使用百分比规定变化发生的时间。或者用`from`和`to`等同。
    6.  动画简写属性
        1.  `animation: 动画名称 持续时间 运动曲线 何时开始 播放次数 是否反方向 动画其实或者结束的状态`
        2.  如：`animation: myfirst 5s liner 2s infinite alternate;`
        3.  `animation-timing-function`可以用来做动画，如一张PNG的熊跑起来，类似定格动画。[b站教程](https://www.bilibili.com/video/BV14J4114768?p=374&spm_id_from=pageDriver)
        4.  多个动画效果使用逗号隔开，如`animation:move1 1s forwards, move2 2s backwards`

动画常用属性 | 描述
---- | ---
@keyframes | 规定动画
animation |  所有动画属性的简写属性，除了animation-play-state属性
animation-name | 规定@keyframes动画的名称（必须的）
animation-duration | 规定动画完成一个周期所花费的秒或毫秒，默认时0.（必须的）
animation-timing-function | 规定动画的速度曲线，默认是ease。其他参数：linear,ease-in,ease-out,ease-in-out,steps()
animation-delay | 规定动画何时开始，默认是0。
animation-iteration-count | 规定动画被播放的次数，默认时1，还有infinite。
animation-direction | 规定动画是否在下一周逆向播放，默认时"normal",alternate逆播放
animation-play-state | 规定动画是否正在运行或暂停。默认是“running”，还有“pause”。
animation-fill-mode | 规定动画结束后状态，保持在结束状态forwards，默认回到起始backwards。

63. 3D转换：近大远小，物体后面遮挡不可见。
    1.  x轴，水平向右，左边负值，右边正值
    2.  y轴，垂直向下，y上负值，y下正值
    3.  z轴，垂直屏幕，<strog>往里负值，往外正值</strog>
64. 3D移动`translate3d`  
    1.  `transform:translateZ(100px)`仅在Z轴上移动，translateZ一般用px单位。
    2.  `transform:translate3d(x,y,z)`：xyz是不能省略的，如果没有就写0.
    3.  透视：`perspective`,<strog>透视写在被观察元素的父盒子上面</strog>。
        1.  `d`：视距，视距就是一个距离人的眼睛到屏幕的距离
        2.  `z轴`：物理距离屏幕的距离，z轴越大（正值），物体越大。  
        3.  `perspective:(10px)`比`perspective:(100px)`看到的大。相当于`d距离`更近看到的更大。
        4.  `d`与`z轴`作用元素不同。

65. 3D旋转`rotate3d`：
    1.  transform:rotateX(45deg)：沿着x轴正方向旋转45度，旋转方向，左手准则，大拇指X轴正方向（右），其余手指弯曲方向为该元素沿x轴旋转的方向。
    2.  transform:rotateY(45deg)：沿着y轴正方向旋转45deg，左手准则，大拇指Y轴正方向（下），其余手指弯曲方向为该元素沿Y轴旋转的方向
    3.  transform:rotateZ(45deg)：沿着Z轴正方向旋转45读，类似于2D旋转
    4.  tramsform:rotate3d(x,yx,z,deg)：沿着自定义轴旋转deg角度。矢量方向。（了解即可）
66. 3D呈现`transform-style`,多个子元素保持3D立体空间环境。
    1.  控制子元素是否开启三维立体环境
    2.  `transform-style:flat`子元素不开启3d立体空间，默认参数，最后一个3d其他变成2d。
    3.  `transform-style:preserve-3d`子元素开启立体空间
    4.  代码写给父级，但是影响的是子盒子

67. 私有前缀:兼容老版本
    1.  `-moz-`：firefox,`-moz-border-radius:10px`
    2.  `-ms-`：ie,`-ms-border-radius:10px`
    3.  `-webkit-`：safari，chrome,`-webkit-border-radius:10px`
    4.  `-o-`：Opera, `-o-border-radius:10px;`

68. 移动端, 视口`viewport`：浏览器显示页面内容的屏幕区域。视口可以分为布局视口，视觉视口，理想视口。
    1.  布局视口`layout viewport`（缩小）
        1.  一般移动设备浏览器都默认设置了一个布局视口，用于解决早期pc页面在手机显示问题
        2.  iOS和Android都将这个视口分辨率设置为`980px`。多数PC端网页在手机也可以看，只不过很小需要缩放。
    2.  视觉视口`visual viewport`,用户正在看的网页区域（不缩小）
    3.  理想视口`ideal viewport`
    4. 标准移动端`meta`视口标签如下：
    5. `<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">`
    6. 移动端常用`normalize.css`清除默认样式

69.  二倍图：手机显示`50*50`，在电脑端会模糊。实际应用为`100*100`，给图片盒子设计宽高`50px*50px`压缩图片比，电脑端就会显示清晰。
    7.  物理像素&物理像素比
        1.  物理像素是屏幕显示的最小颗粒，物理真是存在，出厂时就设置好了的。
        2.  开发时1px不一定等于一个物理像素。
        3.  PC端页面，1个px等于一个物理像素，移动端不尽相同。
        4.  一个px能显示的物理像素点的个数为物理像素比或屏幕像素比。
        5.  Retina（视网膜屏幕）显示技术，可以将更多的物理像素点压缩至一块屏幕里，从而达到高分辨率。
    8.  `background-size：背景图片宽度  背景图片高度`：设置背景图片宽高（背景图没有填充盒子的宽高时可设置）
        1.  单位：长度，百分比，cover，contain（整张图）
        2.  适用场景：图片过大，或者图片过小
70.  CSS3盒子模型`box-sizing`
    9.  传统模式宽度计算，<strong>盒子宽度 = CSS中设置的width + border + pading</strong>
        1.  默认传统盒子：`box-sizing:content-box`
    10. CSS3盒子模型，<strong>盒子宽度 = CSS中设置的宽度width里面包含了 border 和 padding</strong>
        1.  转变成CSS3盒子：`box-sizing:border-box`
        2.  移动端考虑使用CSS3盒子模型
    11. 特殊样式：移动端技术解决方案
```CSS
/*CSS3 盒子模型 */
box-sizing:border-box;
-webkit-box-sizing:border-box;
/* 点击高亮我们需要清楚 设置transparent完成透明 */
-webkit-tap-highlight-color:transparent;
/* 在移动端默认浏览器的外观在ios上加上这个属性才能给按钮和输入框自定义样式 */
-webkit-apperance:none;
/* 禁用长安页面时弹出的菜单 */　　
img,a{-webkit-touch-callout:none;}
```

71. 京东案例大致笔记：流式布局（全部百分比布局）
    1.  设置根元素100%，并且设置`max-width:640px`和`min-width:320px`.
    2.  图片居中两种方式：`line-height`,`vertical-align:middle`
    3.  搜索栏左右两端菜单固定，中间搜索栏可伸缩
        1.  处理方法：两边小菜单和登录框使用 绝对定位，指定尺寸大小。
        2.  中间搜索栏使用自动宽（100%或不指定宽度）再使用margin左右。
    4.  二倍精灵图做法
        1.  在`firework`把精灵图等比例缩放为原来的一半
        2.  之后根据大小测量坐标，然后写在`background`的图片坐标
        3.  在`background-size`写：精灵图原来宽度的一半
    5. 在盒子里面切图圆角方式：
       1. 在父级盒子切完盒子圆角`border-radius`后，使用`overflow:hidden`隐藏溢出的图片直角
       2. 子级元素`img`宽度`100%`
       3. 若图片距离盒子或者盒子距离前(上)一个盒子有距离/顶部有空隙，尝试使用`vertical-align:top`
    6. 左/右浮动之后，可以设置宽度等长度单位，浮动之后元素变成行内块元素。
    7. 按尺寸左右调整距离后再加竖线`border-left:1px solid #ccc`撑开盒子造成最后一个元素溢出，可以使用`box-sizing:border-box`
       1. 原因：多添加了一个border的宽度撑出盒子，使用border-box可以把border宽度计算在内。

72. 传统布局与flex布局
    1.  传统布局：兼容性好，布局繁琐，局限性，不能再移动端很好的布局
    2.  flex弹性布局：布局极为简单，移动端应用广泛，PC端浏览器支持较差，IE11或更低版本不支持。
    3.  父元素使用`display:flex`
        1.  注意：子元素的float, clear和vertical-align属性将失效
        2.  子元素`<span>`等可以设置宽高，并且有效
        3.  伸缩布局 = 弹性布局 = 伸缩盒布局 =弹性盒布局 = flex布局
    4.  `flex`属性:定义子项目分配<strong>剩余空间</strong>，flex表示占多少份数
        1.  语法：`.item{ flex: <number>; }`默认值为0. 
          
6个属性对父元素设置的 | 描述
---- | ---
flex-direction | 设置主轴（x轴）方向，参数`row`等
justify-content | 设置主轴上的子元素排列方式
flex-wrap | 设置子元素是否换行，默认`nowrap`不换行并且缩小子元素宽度，其他参数`wrap`
align-content | 设置侧轴上（y轴）的子元素的排列方式（多行）
align-items | 设置侧轴上的子元素排列方式（单行）
flex-flow | 复合属性，相当于同时设置了flex-direction和flex-wrap，语法`flex-flow:row wrap`
flex | 分到的份数
align-self | 控制子项自己在侧轴上的排列方式

flex-direction(属性值) | 可设置主轴，余下为侧轴
---- | ---
row | 默认值从左到右(相当于主轴为x轴)
row-reverse | 从右到左
column | 从上到下
column-reverse | 从下到上

justify-content(属性值) | 可设置主轴上子元素排列/对齐方式
---- | ---
flex-start | 默认值，从头部开始，如果主轴x轴，左到右
flex-end | 从尾部开始排列
center | 在主轴居中对齐（如果主轴是x轴则水平居中）
space-around | 评分剩余空间
space-between | 先两边贴边，再平分剩余空间（重要）

align-items/align-content(属性值) | 可设置侧轴上子元素排列方式，再子项为单项（单行）时使用(items)  | 只能用与子项出现换行的情况（多行，可通过flex-wrap设置wrap换行），单行效果无效(content)
---- | --- | ----
flex-start | 默认值，从上到下  | 默认值在侧轴的头部开始排列
flex-end | 从下到上  |  在侧轴的尾部开始排列
center | 挤再一起居中（垂直居中） |  在侧轴中间显示
stretch | 拉伸(items) | 设置子元素高度评分父元素高度（contents多行）
space-around |  --  |  子项在侧轴平分剩余空间
space-between | --  |  子项在侧轴先分布在两头，再平分剩余空间

73. 常见flex布局思路
    1. 图片居于文字正下方使用大盒包两个小盒，小盒常用`<span>`，[b站教程](https://www.bilibili.com/video/BV14J4114768?p=432&spm_id_from=pageDriver)
       1. 默认主轴X轴，`<a><span1><span2></a>`为横向span1和span2处于左右
       2. 把主轴设为Y轴，则span1和span2上下分布
       3. 把侧轴设为居中对齐，则span1和span2位于盒子中间对齐
    2. 快速设置子元素样式，前提设置通用class名。
       1. 先通过属性选择器设置通用样式及图片`url`，如`.local-nav li [class^="local-nav-icon"] {。。}`([]前右空格)
       2. 通过背景位置对图标进行校正`.local-nav li .local-nav-icon-ico2{ background-position: 0 -32px;} `
    3. 给相邻盒子之间加竖线，比如三个盒子相邻，中间加两条竖线
       1. `.nav-items:nth-child(-n+2)`选择为前面两个元素
       2. 样式`{border-right:1px solid #fff}`
       3. 选择flex后，边框加`1px`不会影响，因为flex包含边框计算在内为盒子尺寸
    4. 背景颜色渐变色,<strong>背景渐变必须添加浏览器私有前缀</strong>。
       1. 背景线性渐变
          1. `background:linear-gradient(起始方向，颜色1，颜色2)`;
          2. `background:-webkit-liner-gradient(left, red, blue)`;
          3. `background:-webkit-linear-gradient(left top, red, blue)`;

74. 移动端之`rem`适配布局及Less语法
    1.  目的：解决不同尺寸移动端根据比例适配文字、图片、行高的大小
    2.  rem（root em）是一个相对单位，类似于em，em是父元素字体大小。
    3.  rem的基准是相对于html元素的字体大小。
    4.  如：根元素`html`设置`font-size=12px`，非根元素设置`width:2rem`；则换算`px`就是`24px`。
75. 媒体查询`Media Query`为CSS3新语法
    1.  `@media`可以设置不同尺寸不同样式
    2.  格式：`@media mediaType and|not|only (media feature){css code}`
        1.  mediaType查询类型：`all` - 所有设备； `print` - 打印机和打印预览； `screen` - 电脑屏幕，平板手机等。<strong>必须写</strong>。
        2.  关键字将媒体类型或多个媒体特性连接到一起作为媒体查询的条件。
            1.  `and` - 将多个媒体特性链接一起，<strong>必须写</strong>； `not` - 排除某个媒体类型，可省略； `only` - 指定某个特定媒体类型，可省略。
        3.  媒体特性，目前只需要了解三个：`max-width | min-width | width`
        4.  如`@media screen and (max-width:800px){..}`：在屏幕上并且最大宽度是800像素的样式。
        5.  可以使用`and`使用多个条件如配合max-width和min-width。
    3.  `@media`一般设置高度不设置宽度，且加`transition`可以让效果拉满。
    4.  通过`<link>`方式添加不同尺寸的`media`的CSS文件格式如下
        1.  `<link rel="stylesheet" media="mediatype and|not|only (media feature)" href="mystylesheet.css">`
        2.  推荐媒体查询方式：从小到大

76. Less基础
    1.  CSS是一门非程序式语言，没有变量、函数、SCOPE（作用域）等概念。
    2.  Less(learner Style Sheets)为CSS扩展语言，成为CSS预处理器。常见CSS预处理器：Sass、Less、Stylus。
        1.  Less变量：`@变量：值；`，常用于CSS中的一些颜色和数值。
            1.  必须@前缀、不能包含特殊字符、不能以数字开头、大小写敏感
        2.  Less编译：Less包含一套自定义语法及解析器，根据自定义样式规则，通过解析器，编译生成对应的CSS文件。
            1.  如vscode中的easy-less插件；保存后自动生成css文件。
        3.  Less嵌套：原CSS中的标签选择器如`.div a{ .. }`
            1.  Less使用：`.div{ a{..} }`直接嵌套
            2.  若遇到（交集|伪类|伪类选择器）
                1.  内层选择器的前面没有 & 符号，则被解析为夫选择器的后代；如`a{ :hover{..} }` => `a :hover{..}`为a的后代某个hover
                2.  如果有 & 符号，则被解析为父元素自身或父元素的伪类；如`a{ &:hover{..} }` => `a:hover{..}`自身hover
        4.  Less运算：任何颜色、数字或者变量都可以参与运算。运算符号：加、减、乘、除。
            1.  注意：运算符号需要空格隔开。除号多单位运算时额外注意。如(10rem / 50)
            2.  两个参数运算，只有一个数有单位，结果以这个单位为准。
            3.  两个参数运算，且2个数都有单位，且不一样的单位，最后的结果以第一个单位为准。

77. rem实际开发适配方案：rem + 媒体查询 + less技术
    1.  一般移动端使用`750px`为准。
    2.  假设把整个屏幕划分`15`等份（也可以是20、10等份），每一份作为html字体大小，这里`750px/15`就是`50px`
    3.  那么在`320px`设备时，字体大小为`320px/15`就是`21.33px`
    4.  页面元素的大小初一不同html字体大小，他们的比例还是相同的
    5.  设计一个`100*100像素div`的页面元素在750屏幕下，就是`100/50`转换为rem时宽高就是`2rem * 2rem`比例
    6.  320屏幕下，html字体大小为21.33，则2rem = 42.66px，此时宽和高都是42.66

78. 苏宁模板 - rem适配方案- 手动计算
    1.  注意：做less运算除法时，需要用括号括起来如：`font-size: (360px / @no);`
    2.  固定定位，必须要有宽度，如导航
    3.  页面元素rem计算公式： 页面元素的px / html 字体的大小 50.
        1.  注意：若要得到rem则要在计算里至少有一个rem。
    4.  `<a>`图片会缩放，使用背景图片，以及背景图片的`background-size`进行背景图片缩放，宽高让其与自身宽高对等即可

79. rem适配方案 - flexible.js简洁高效版
    1.  手机淘宝出的简介搞笑移动端适配库，不需要写不同媒体查询，因为里面做了js处理。
    2.  其把当前设备划分`10等份`。只需设置当前`html`文字大小即可。
    3.  比如设计稿时`750px`，只需要把html文字设置为`75px`（750px/10）即可，页面元素`rem值`：`页面元素的px值/75`，余下`flexible.js`计算。
    4.  使用插件让用户输入px自动转换成rem。
        1.  VSCode 插件： `cssrem`。
        2.  注意：cssrem插件默认使用html文字大小为16px。可在VSCode设置搜cssrem设置大小。
    5.  若超出750px可以参考使用`！Important`进行提权。
    6.  避免使用id选择器，权重太高。
    7.  `display:flex`多个子元素让其换行使用`flex-wrap:wrap`
    8.  图片自动缩放：使用`img{ width: XXrem }`用等比例方式缩放
    9.  水平居中显示；使用flex方式。`{ flex-direction:column; align-items:center }`

80. 移动端响应式布局：使用媒体查询
    1.  响应式屏幕常用分档：
        1.  超小屏幕（手机）< 768px ：设置宽度100%
        2.  小屏设备（平板） >= 768px ~ < 992px ： 设置宽度750px
        3.  中等屏幕（桌面显示器） >= 992px ~ < 1200px ： 宽度设置为970px 
        4.  宽屏设备（大桌面显示器） >= 1200px ： 宽度设置为1170px
    2.  使用父级布局容器，来配合子元素实现效果
    3.  要求当前网页使用IE浏览器最高版本内核来渲染： `<meta http-equiv="X-UA-Compatible" content="IE=edge">`

81. Bootstrap框架 <strong>v3版本</strong>
    1.  布局容器 `.container`响应式布局、`container-fliud`流式布局（适合于单独做移动端开发）。所有`container`左右各有15px空隙
    2.  一般用来分成几等份的才会用到container。
    3.  container下包裹1行`row`和列`column`12等份：格式： `container -> row（去除15px） -> col`
        1.  手机100% 小于768px - `col-xs-`
        2.  平板 大等于768px - `col-sm-`
        3.  中等屏幕 大等于992px - `col-md-`
        4.  大屏幕 大等于1200px - `col-lg`
        5.  注意：行使用`row`可以去除父容器作用`15px`的边距； 每一列默认有左右`15px`的`padding`。
    4.  列嵌套：`container -> row1 -> col -> row2 -> col`: row2可以取消父元素的padding值，而且高度自动和父级一样高。
    5.  列偏移：`col-md-offset-*`：让盒子靠左右中间空。列偏移写在后面想要偏移的盒子。row -> col还是正常写。
        1.  可作用于一个row一个盒子居中对齐。
    6.  列排序：使用`col-md-push-*`和`col-md-pull-*`让左侧右侧对调更改顺序。
        1.  作用于整体布局，如中屏幕菜单左侧，内容居中，右侧侧边栏，小屏幕切换位置。
    7.  `.hidden-xs`等隐藏。`visible-xs`显示。

82. 阿里百秀案例[bootstrap 3 参考手册](https://getbootstrap.com/docs/3.3/css/#helper-classes)
    1.  策略：先布局md以上的pc端布局，最后根据实际需求在修改小屏幕和嘲笑屏幕的特殊布局样式。
    2.  本案例实际开发z最大宽度`1280px`。bootstrap的`container`宽度为`1170px`。可以手动修改container宽度。
    3.  消除`container`里面的`padding值`，加`row`。
    4.  `row`里面的列`col-xx`也有默认左右`padding值`，消除方法：使用选择器如自身标签选择器`<header>`或者添加元素选择器`.header`控制样式。
        1.  注：若标签/元素选择器自定义样式不起作用，尝试权重更改。
    5.  注意`ul`清除`padding`和`margin`以及`list-style`，也可以设置`<a>`的显示样式如`color:#666; text-decoration:none;`以及`a:hover{text-decoration:none}`
    6.  bootstrap的icon位置错位，可以通过`XX::before{vertical-align: middle;}`修正。
        1.  注意：官方推荐利用`<span>`添加图标。
    7.  子元素浮动，为防止影响父元素的下一个盒子位置，需要在父元素清除浮动，可以直接在`class`添加`clearfix`。
    8.  图片缩放问题：若子元素图片没有按父元素一样进行缩放如`col-sm`，设置子元素`img{width：100%}`即可(为区分可添加一个`pic`元素在父元素)。
        1.  注意：若图片宽度`width:100%`后小屏幕会撑大盒子
        2.  此时可以用`img{ max-width:100% }`。
    9.  测量像素软件，[像素大厨](https://www.fancynode.com.cn/pxcook)


83. `vw`和`vh`：是一个相对单位（类似em和rem相对单位）
    1.  vw：viewport width 视口宽度单位； 1vw = 1/100视口高度
    2.  vh：viewport height 视口高度单位； 1vh = 1/100视口高度
    3.  如：视口时375px，则1vw时3.75像素。
        1.  注意：和百分比区别，百分比相对于父元素，vw和vh总是针对于当前视口。
    4.  视口时375px，如果需要一个`50px * 50px`的盒子，则需要`50px / 3.75 = 13.333vw`，然后其按尺寸大小等比缩放
    5.  注意使用2x模式。
    6.  vh做滚动高度时会使用
    7.  插件自动转换：`px2vw`,可设置宽度转换