### 学习Vue2 [其他笔记链接](https://github.com/brant8/Vue2Study)
1. webpack前端工程化具体解决方案：模块化开发，代码压缩混淆，处理浏览器端JS兼容性如ES6等。
   1. webpack基本使用：
      1. 在新建空白目录下运行：`$ npm init -y`;（`npm i`在复制public和src等其他目录结构，没有node_modules时运行，i代表install）
      2. 步骤一命令会初始化管理配置文件`package.json`
      3. 新建src源代码目录：`src->index.html`和`src->index.js`
      4. 安装jQuery：`$ npm install jquery -S`或`$ npm nstall jquery --save`命令, `-S/--save`记录到.json下的`dependencies`。webpack生成后会用到。
      5. 自行导入`<script src="xx.js">`导入；或在.js中使用ES6导入`import $ from 'jquery'`(ES6直接运行会出错)
      6. jQuery入口函数：`$(function(){...})`；如`..{ $('li:odd').css('background-color','red') }`奇数行背景色红色
      7. 安装webpack：`npm install webpack@5.42.1 webpack-cli@4.7.2 -D`, `-D`记录当前webpack版本信息到`.json`的`devDeendencies`，dev开发，webpack生成后不会用到。
      8. 官方命令：`npm install --save-dev webpack` 
      9. `--save-dev` 等同于 `-D`
   2. 项目中配置webpack
      1. 项目根目录：创建配置文件`webpack.config.js`
         1. 基本配置内容：
            1. `module.exports = { mode: 'development'}` ；mode用来指定构建模式，可选择<strong>development|production</strong>.
            2. `"script": { "dev": "webpack" } `; script节点下的脚本，可以通过npm run执行，如`npm run dev`(相当于npm run webpack)
         2. 使用：
            1. 运行`npm run dev`后生成`dist->main.js`
            2. 在页面引用`main.js`；main.js包含index.js和jquery.js两个文件。
         3. 运行流程：
            1. <strong>npm run dev</strong>中的dev找.json的webpack
            2. 然后webpack找webpack.config配置
   3. webpack默认约定
         1. 版本4.x和5.x中默认约定
            1. 默认打包入口文件为`src->index.js`
            2. 默认输出路径为`dist-main.js`
            3. 可在webpack.config.js中修改默认；修改需要使用node.js的一些代码，如`__dirname`为node中的当前文件所处目录。下方自定义生成bundle.js替代main.js。
               ```JS
               module.exports = {  
                entry: path.join(__dirname, 'src/index1.js'),  //entry:指定要处理哪个文件夹
                output: { 
                    path: path.join(__dirname, 'dist'),  //指定生成的文件放哪里，此处存放的目录
                    filename: bundle.js }  //生成的文件名
                 }
               ``` 
   4. webpack中的插件
         1. `webpack-dev-server`：类似于node.js中的nodemon工具。每当修改源代码，webpack自动对项目进行打包和构建。
            1. 安装命令：`npm install webpack-dev-server@3.11.2 -D`
            2. 配置该插件：
               1. 修改package.json -> scripts -> 添加`"dev": "webpack serve",`。
               2. 再次运行`npm run dev`，重新进行项目打包。运行命令后会<strong>热部署</strong>直到用户结束命令。
            3. 注意事项：
               1. 每次自动保存，保存在内存里（否则每次保存都会读写磁盘造成磁盘寿命减短），实际目录还未生成。与webpack.config配置有关，或默认配置。
               2. 运行命令后注意查看'output is served from /'，index.html引用地址要相应改变。
         2. `html-webpack-plugin`：webpack中的HTMl插件，类似于一个模板引擎插件。此插件可以自定制index.html页面的内容。
            1. 安装命令：`npm install html-webpack-plugin@5.3.2 -D`
            2. 配置该插件webpack.config.js：
               1. 导入HTMl插件，得到一个构造函数`const HtmlPlugin = require('html-webpack-plugin')`
               2. 创建HTML插件的实例对象
                ```JS
                const htmlPlugin = new HtmlPlugin({ 
                template: './src/index.html' , //指定源文件的存放路径
                ` filename: './index.html',  })  //指定生成的文件的存放路径
                ```  

               3. 通过plugins节点，使htmlPlugin插件生效   
   
                ```JS
                module.export = { mode: 'development', 
                plugins: [htmlPlugin], } //
                ```   

            3. 通过HTML插件负值到项根目录中的index.html也被放到了内存中。
            4. HTML插件生成的的index.html页面自动注入打包的bundle.js文件。
            5. webpack.config.js配置，devServer节点修改端口号：
                ```JS
                devServer:{ 
                    open:true,  //初次打包完成后，自动打开浏览器
                    port:80 //试试打包所使用的端口好
                    host: '127.0.0.1' // 是是打包所使用的主机地址
                    }
                ```
         3. 凡是修改了package.json/webpack.config，均需要重启。
          
   5. loader概述：webpack默认只能打包`.js`后缀结尾的模块，其他非`.js`结尾模块需要`loader`加载器才能正常打包，否则报错。
         1. `css-loader`打包.css
            1. 安装loader命令：`npm i style-loader@3.0.0 css-loader@5.2.6 -D`
            2. <strong>webpack.config.js</strong>添加load规则：
               ```JS
               module:{  //所有第三方文件模块匹配规则
                rules: [ //文件后缀名匹配规则
                    {  test: /\.css$/, use:['style-loader', 'css-loader'] }
                        ] 
                }
                // test文件类型，use表示对应要调用的loader
                //
                // 流程：
                // 1. 打包处理css文件，处理不了
                // 2. 查找webpack.config.js配置，看module.rules数组是否配置了对应loader加载器
                // 3. webpack把index.css先交给最后一个loader处理（先转交给css-loader）
                // 4. css-loader处理完后把结果转交给下一个loader（转交给style-loader）
                // 5. style-loader处理完后发现没有下一个loader就把结果转交给webpack
                // 6. webpack把style-loader处理结果合并到/dist/bundle.js中。最终生成打包好的文件。
               ``` 
         2. `less-loader`打包.less
            1. 安装命令：`npm i less-loader@10.0.1 less@4.1.1 -D`
            2. webpack.config声明规则
            ```JS
            module:{
                rules:[
                    {test: /\.less$/, use;['style-loader','css-loader','less-loader']}
                ]
            }
            //注：less@4.1.1.1为内置依赖项，不需要额外声明；less-loader依赖于less。
            ``` 
            1. <strong>rules调用顺序规则，从后往前调</strong>。
         3. `babel-loader`打包高级JS语法。
            1. 安装命令：`npm i babel-loader@8.2.2 @babel/core@7.14.6 @babel/plugin-proposal-decorators@7.14.5 -D`
            2. 配置webpack.config规则：
               1. `{ test: /\.js$/, use: 'babel-loader', exclude:/node_modules/ }`
               2. 说明：必须使用exclude指定排除想；因为node_modules目录下的第三方包不需要被打包。
            3. 在项目根目录下，创建名为`babel.config.js`的配置文件，定义Babel配置项如下
            ```JS
            module.exports = {
                //声明babel可用的插件
                plugins:[['@babel/plugin-proposal-decorators',{ legacy:true }],]
            }
            /*plugin-proposal-decorators告诉babel如何解析高级js语法
            webpack先加载plugins插件来使用再调用babel-loader*/
            ``` 
         4. 打包<strong>url路径相关</strong>的文件
            1. 安装命令：`npm i url-loader@4.1.1 file-loader@6.2.0 -D`
            2. webpack.config配置规则
            ```JS
            module:{
                rules:[
                    {test: /\.jpg|png|gif$/, use:'url-loader?limit=22229&outputPath=images'},
                ]
            }
            //?之后的使loader的参数选项
            //只有小于等于limit大小（字节bytes）的图片才会被转为base64的图片
            //&outputPath=images：指定图片在production后的存放路径，也可以像下面的一一写(步骤7优化写法)
            //案例jQuery的js设置属性：
            /* import logo from './images/logo.jpg'
            $('.box').attr('src',logo)
            */
            ```
         5. 在webpack中一切皆模块，都可以通过ES6导入语法进行导入和使用。
            1. 如果某个模块中，使用from接收到的成员为undefined，则美必要进行接收，如 `import a from './css/index.css'`直接写成`import './css/index.css'`
   6. 配置webpack的打包发布（判断文件打包状态可查看输出目录有无生成如dist）
      1. 在jackson.json添加scripts节点
         1. `"scripts": { "build": "webpack --mode production" }`
         2. --mode是一个参数项，指定webpack运行模式(webpack.config中有mode)。
         3. production代表生产环境，会生成的文件进行代码压缩和性能优化。
   7. 优化打包路径，否则打包的所有文件默认都在同一目录dist下。
   ```JS
    output:{ //output与mode同级
        path:path.join(__dirname,'dist'),
        filename:'js/bundle.js' //修改js生成路径
    }

    //图片统一生成到image目录中
    rules:[ //图片目录在规则中声明
        {
        test: /\.jpg|png|gif$/,
        use: {
            loader: 'url-loader',
            options: {
                limit:22222,
                //明确指定目录给图片
                outputPath:'image',
                }
            }
        },
    ]
   ``` 
   1. 自动清理dist目录下的旧文件插件。
      1. 安装命令`npm install clean-webpack-plugin@3.0.0 -D`
      2. 导入插件得到插件的构造函数后创建插件实例对象
         1. `const { CleanWebpackPlugin } = require('clean-webpack-plugin)`
            1. CleanWebpackPlugin首字母大写一般是构造函数
            2. `{}`表示解构赋值
         2. `const cleanPlugin = new CleanWebpackPlugin()`
      3. 把创建的cleanPlugin插件实例对象挂在到plugins节点中
         1. `plugins:[htmlPlugin, cleanPlugin]`
   2. SourceMap：信息文件，让其原文件代码行的位置转换对应的压缩后的代码行，方便纠错。
      1.  webpack.config.js中开启：`devtool: 'eval-source-map'`（开发环境时可用）
      2.  devtool与mode同级，开发环境可以开启，生产环境推荐关闭提高安全性。
      3.  只定位行数不暴露源码：`devtool: 'nosources-source-map'`
      4.  暴露行数与代码：`devtool: 'source-map'`（开发环境时可用）
   3.  使用@表示src源代码目录。如`@/msg.js`表示src/msg.js。
       1.  webpack.config配置
       ```JS
       resolve:{ //与mode平级
           alias:{
               '@':path.join(__dirname,'/src')
           }
       }
       ```  


2.  Vue入门：数据驱动视图（数据变化引起视图变化 -- 单向）、双向数据绑定（采集数据）。
    1.  MVVM：
        1.  Model-当前页面渲染时所依赖的数据源
        2.  View当前页面所渲染的DOM结构
        3.  ViewModel为vue实例，MVVM的核心

3. vue基本使用
   1. 导入vue.js
   2. 在页面中声明一个被vue所控制的DOM区域
   3. 创建vm实例对象
   ```HTML
    <div id="app">{{username}} </div>
    <script src="vue.js"></script>
    <script>
        const vm=new Vue({
            el:'#app',
            data：{
                username:'zhangsan',
            },
        })
    </script>
   ``` 

4. vue指令与过滤器
   1. 指令Directives - 模板语法，用于辅助开发者渲染页面的基本结构，如下6大类指令。
      1. 内容渲染
      2. 属性绑定
      3. 事件绑定
      4. 双向绑定
      5. 条件渲染
      6. 列表渲染
   2. 内容常用指令：
      1. `v-text`：缺点 - 会覆盖原本标签的内容,只渲染纯文本；几乎不用
      2. `{{}}`插值表达式（Mustache）：内容占位符，使用多。不能用在属性节点如~~`<input type="text" placeholder="{{tips}}"/>`~~。支持JS运算如三元表达式等简单运算，不能处理业务逻辑，如~~`if(..){document.write("启用")}`~~。
      3. `v-html`：将带有标签的字符串，渲染成真正HTML内容。
        ```HTML
        <div id="app">
            <p v-text="username"></p>
            <!-- 性别被覆盖 -->
            <p v-text="gender">性别</p>
            <!-- 可计算 -->
            <p>{{ age }}反转结果是{{ (age+"").split("").reverse().join("") }}</p>
            <p v-html="info"></p>
            <!-- 插值表达高级用法，三个input凑个rgb颜色,:style里的属于JS表达式 -->
            <div class="box" :style="{backgroundColor: 'rgb(${r}, ${g}, ${b})' }">
                {{ 'rgb(${r}, ${g}, ${b})' }}
            </div>
        </div>
        <script>
            const vm=new Vue({
            el:'#app',
            data：{
                username:'zhangsan',
                gender:'nv',
                age:17,
                info:'<h4 style="color:red">高三二班</h4>'
            }
            })
        </script>
        ``` 
    1. 属性绑定指令`v-bind`。单向动态绑定,可简写如`:placeholder`。
       1. 支持运算
        ```HTML
        <div id="app">
            <input type="text" v-bind:placeholder="username">
                <!-- 可计算 -->
            <div :title="'box' + index"></div>
        </div>
        <script>
            const vm=new Vue({
            el:'#app',
            data：{
                username:'zhangsan',
                index:2,
            }
            })
        </script>
        ```  
    2. 事件绑定`v-on`。辅助程序员DOM元素绑定事件监听。可以简写成如`@click`
       1. 常用参数：click | input | keyup | blur
       2. 事件绑定`$event`：不传参时，默认传入到方法有个`e`对象。如`add(e)`。
       3. 可以在方法内`方法(e)`查看`console.log(e)`，触发对象`target:xxx`。或者在HTML里传入`$event`
       4. 其他扩展：`$event.preventDefault()`&`@click`阻止点击跳转；Vue写法`@click.prevent="方法"`。
       5. 常用事件修饰符：
          1. `prevent`（阻止默认行为）等同于JS的event.preventDefault()；
          2. `stop`（阻止事件冒泡,类似嵌套）等同于event.stopPropagation()。
          3. `@submit.prevent=""`常用于表单提交后的默认行为(<strong>提交表单会刷新页面</strong>)。
       6. `@click="show"`与`@click="show(传参)"`以及`@click="show(3,$event)`防止e传参被覆盖。
        ```HTML
        <div id="app">
            {{ Count }}
            <button v-on:click="add">++</button>
            <button v-on:click="sub">--</button>
                <!-- 可以传参add(1) ,methods也要传add(n){..}-->
                <!-- 此处changeColor不能传e，只能在方法中放入e -->
            <button @click="changeColor">--</button>
                <!-- 若需要传入，可单独加入$event -->
            <button @click="changeColor2(1,$event)">--</button>
        </div>
        <script>
            const vm=new Vue({
            el:'#app',
            data：{
                Count:0
            },
            methods:{
                add: function(){
                    console.log(vm); //显示所有vue属性
                    vm.Count++; //不推荐用vm方式
                },
                sub(){ //简写
                    console.log(vm == this); //true
                    this.Count--;//推荐用this方式写
                },
                changeColor(e){ //奇偶改变背景颜色
                    if(this.count % 2 == 0){
                        e.target.style.backgroundColor = 'red'
                    }else{
                        e.target.style = 'backgroundColor: yellow'
                    }
                },
                changecolor2(n,e){ //用e接收$event
                    //...
                }
            }
            })
        </script>
        ``` 
        1. 按键修饰符，监听键盘事件。
        ```HTML
            <!-- 只有在key时Enter时调用submit（） -->
        <input @keyup.enter="submit">
            <!-- 只有在key时Esc时调用clearInput() -->
        <input @keyup.esc="clearInput">
        ``` 
    3. 双向绑定指令`v-model`。只有表单元素使用v-model才有意义，实现交互，否则普通div显示数据无交互意义。
       1. 表单元素：input、textarea、select
       2. v-model修饰符： 
          1. `.number` ：输入值自动转为数值类型
          2. `.trim` ：自动过滤用户输入的首位空白字符
          3. `.lazy`：在"change"时而非“input”时更新，即不同步更新过程。
    ```HTML
        <div id="app">
            <p>{{ username }}</p>
            <input type="text" v-bind:value="username">
                <!-- 不用写value，底层v-model包含 -->
            <input type="text" v-model="username">
            <div v-model="username">无意义</div>
        </div>
        <script>
            const vm=new Vue({
            el:'#app',
            data：{
                username:'zhangsan',
            }
            })
        </script>
    ```  
    1. 条件渲染指令：
       1. `v-if`：隐藏数据时HTML不显示任何内容，移除。
       2. `v-show`：隐藏时v-show实际为`display:none`；频繁切换显示状态时使用。
       3. `v-else-if` | `v-else`：需要与v-if使用。
       4. 扩展：JS里``==``操作符会先将两边的值进行强制类型转换再比较是否相等，而``===``操作符不会进行类型转换
    ```HTML
        <div id="app">
            <p v-if="flag">这是v-if元素</p>
            <p v-show="flag">这是v-show元素</p>
        </div>
        <script>
            const vm=new Vue({
            el:'#app',
            data：{
                flag:true
            }
            })
        </script>
    ```   
    1. 列表渲染指令：`v-for`，基于一个数组来循环渲染一个列表结构。常使用`item in items`结构。
       1. 支持可选第二个参数，即当前项的索引。`(item,index) in items`
       2. 官方建议，使用v-for，最好绑定一个`:key`属性，并且把id作为key值，可以提升性能，防止列表状态紊乱。
       3. 实际开发vue组件（xx.vue），若没有`:key`属性终端console会报错。key值不能重复。key值只能是字符串或数字类型。
       4. index的值当作key的值没有任何意义，因为index不具有唯一性。比如勾选框，勾选后再添加内容会错位。
       5. 扩展：JS的unshift可以追加数据到数组。
       6. `v-model`会根据表单的形式如`type="text"`或者`type="radio"`来做相对应控制。
       7. 若只能控制第一个item，可查看是否表单的`label-for`有无设置动态及`item.id`。
    ```HTML
    <script>
     ...   
    data:{
        list:[
            {id:1, name:'zs'},
            {id:2, name:'ls'}
        ],
    }
    </script>
    <ul>
        <li v-for="item in list" :key="item.id">姓名是: {{ item.name }} </li>
    </ul>

    <!-- JS代码追加内容到数组末尾，使用push -->
    ```
      
5. 过滤器(Filters)用于文本的格式化。<strong>只支持Vue2，Vue3已剔除。</strong>
   1. 用的地方：插值表达式，v-bind属性绑定
   2. 添加在JS表达式微博，由管道符进行调用。实际将方法放在filters下。必须要有返回值。
   3. `<p>{{ message | capitalize }}</p>`
   4. `<div v-bind:id = "rawId | formatId"></div>`
   5. 私有过滤去、全局过滤器->`Vue.filter()`
   6. 私有过滤器&全局过滤器名字重复，按照就近原则，调用私有过滤器。
   7. 扩展：日期格式化，[day.js](https://day.js.org/docs/en/installation/browser) (==好用==) (例子07-品牌案例)
   8. 过滤器可以连续调用`<p>{{ message | filterA(arg1,arg2) | xx | yy }}</p>`
      1. `Vue.filter('filterA',(msg,arg1,arg2)=>{})`：接收参数永远在msg后面。
   ```HTML
    <div id="app">
            <p> message 的值时{{ message | capi }}</p>
        </div>
        <script>
                //全局过滤器 - 独立于每个vm实例之外
                //Vue.filter()方法接收两个参数
                //参数1：全局过滤器的名字
                //参数2：全局过滤器的处理函数
            Vue.filter('capitalize',(str)=>{  //str用来接收前面的值
                return str.charAt(0).toUpperCase() + str.slice(1) + '~~'
            }) //全局过滤器要写在局部之前

            const vm=new Vue({
            el:'#app',
            data：{
                message:"hello vue"
            },
            filters:{ //此处为私有过滤器，仅在#app下有效
                capi:function(val){ //形参val永远都是竖线‘|’前面的内容
                    const first = val.charAt(0).toUpperCase() 
                    const other = val.splice(1)
                    return first+other
                }
            }
            })
    </script>
   ``` 

6. 侦听器：侦测数据变化`watch`并针对性操作
   1. 要监听哪个数据的变化，就把数据名作为watch方法名即可。
   2. 举例：用户注册，判断用户名是否占用（非实际操作，否则服务器交互频繁）
   3. ‘06-侦听器.html’使用`jQuery`&`Ajax`判断用户名是否占用
   4. 方法格式的侦听器：
      1. 缺点：无法在刚进入页面的时候自动触发
      2. 如果侦听的是一个对象，如果对象中属性发生变化，不会触发侦听器，需要使用对象格式
   5. 对象格式的侦听器
      1. 好处：可以通过`immediate`选项，让侦听器自动触发。
      2. 使用`handler`属性处理函数。
      3. 可以通过deep选项，深度监听对象属性的变化，如下面的info。
   6. 深度监听`deep`，仅适用于对象侦听。
   ```HTML
    <div id="app">
            <!-- 双向绑定v-model 单项v-bind:value="username"-->
        <input type="text" v-model="username"/>
        <input type="text" v-model="info.username"/>
    </div>
    <script>
        const vm=new Vue({
        el:'#app',
        data：{ 
            username:'', 
            info:{ username:''},
        }, 
        watch: {
                //方法格式
                //监听username值的变化
                //newVal 是'变化后的新值'，oldVal是'变化之前的旧值'
            username(newVal,oldVal){
                console.log(newVal,oldVal)
            }，
                //侦听器处理函数，对象形式
            username：{
                handler:function(newVal,oldVal){
                    console.log(newVal,oldVal)
                },
                immediate:true //进入页面立即触发handler函数，默认false
            },
                /*方法格式不会触发*/
            info(newVal){
                console.log(newVal)
            }，
                //对象格式可以触发
            info: {
                handler(newVal){
                console.log(newVal)
                },
                deep:true //开启深度监听，只要对象中任何一个属性变化，都会触发对象侦听器
            }
                //如果要监听的是对象的子属性，则必须包裹一层单引号
            'info.username'(newVal){ console.log(newVal) }
        }
        })
    </script>
   ```
7. 计算属性`computed`：定义计算属性的时候要定义成“方法格式”。
   1. 代码可复用：在template模板结构中可以使用，也可以在methods中使用，this.计算属性。
   2. 只要任何一个依赖的数据项发生了变化，计算属性就会重新求值
   3. 要return一个计算结果

8. `axios`是一个专注网络请求的库。
   1. axios在请求得到数据之后，在真正的数据之外，套了一层壳。
   2. axios实际返回了：{config:{}, data{<strong>真实数据</strong>}, headers:{}, status:xxx, statusText:''}
   3. 如果调用某个方法的返回值是`Promise`实例，则前面可以加`await`。
    ```JS
        //基本语法一
    const result = axios({
        method:'请求类型',
        url:'请求的URL地址'
    })
        //基本语法二
    axios({
        method:'请求类型',
        url:'请求的URL地址',
        params:{}, //可选，URL中查询的参数 如{ id: 1 }查询id为1的
        data:{}, //可选，请求体参数，GET没有请求体
    }).then((result)=>{
        //.then用来指定请求成功之后的回调函数
        //形参中的result是请求成功之后的结果
    })
        //基本语法三, await只能在被async修饰的方法中，如反调函数..('clic',async function(){ await axios({...})})
    await axios({
        method:'请求类型',
        url:'请求的URL地址'
    })
    ```

9. 单页面程序：Single Page Application。只有一个HTML页面。
    
10. Vue-cli：基于webpack创建工程化Vue项目；省去配置问题。
    1.  安装vue-cli命令：`npm install -g @vue/cli`； `-g`全局安装。
    2.  查看当前vue-cli版本：`vue -V`
    3.  创建项目：`vue create demo`
        1.  创建步骤个别提示：选择CSS-precessor的CSS预处理器。后面选择Less。
        2.  Babel（ES6兼容）配置项使用各自的config files。
    4.  vue目录src构成：
        1.  assets：文件夹，存放项目中静态资源文件，如CSS、图片。
        2.  components：文件夹，封装的、可以重复使用的vue组件。
        3.  main.js：项目的入口文件，整个项目运行，先执行main.js。
        4.  App.vue：项目的根组件。
    5.  vue项目运行流程：vue通过`src/main.js`把`src/App.vue`渲染到`public/index.html`指定的区域中。
        1.  main.js：末尾$mount('#app')可以复用，比如`vm.\$mount('#app')`
    ```JS
    //---main.js---
        //导入vue包，得到Vue构造函数
    import Vue from 'vue'
        //导入App.vue根组件，将来要把App.vue中的模板，渲染到HTML页面中
    import App from './App.vue'

    Vue.config.productionTip = false

        //创建Vue实例
    new Vue({
        // el:'#app',  => 相当于末尾的.$mount('#app') ; 二选一
            //把render函数指定的组件，渲染到HTMl页面中
        render: h => h(App), //render把App替换到index.html中#app的内容
    }).$mount('#app')
    ```

11. 组件，vue组件的三个组成部分，组件中的data不能是对象`data:{}`，必须函数`data(){}`，且必须`return{}`出去。
    1.  `<template>`：组件的模板结构，DOM标签
        1.  只能出现一个`div`根节点
    2.  `<script>`：组件的JS行为
        1.  默认导出：`export default{}`；固定写法。
        2.  export default{}：可以放入如data数据源`export default{ data(){ return{ username:'zs'}} }`
    3.  `<style>`：组件的样式。
        1.  默认使用CSS语法，`<style lang="less">`使用less语法。
    4.  在组件中的`this`是当前组件的实例对象。如`console.log(this)`
    5.  打开VSCode终端：<strong>ctrl+`</strong>。


12. 组件之间的父子关系。准备：src/App.vue、src/components/(Left.vue & Right.vue)。注册组件流程：
    1.  使用import语法在script导入需要的组件(@在webpack.config.hs的resolve里配置路径，此处默认配置好)
        ```JS
        import Left from '@/components/Left.vue'
        ```
    2.  使用components节点在script注册组件
        ```JS
        export default{
            ... 
            components:{ 
                Left 
            } 
        }
        ```
    3.  以标签形式在template使用注册的组件(父子组件注册完成)
        1.  `<Left></Left>`
    4.  扩展：VSCode路径快速提示插件Path Autocomplete。[配置视频](https://www.bilibili.com/video/BV1zq4y1p7ga?p=93&spm_id_from=pageDriver)
    5.  扩展2：快速生成vue模板插件：Vue 3 Snippets + Vuter。
    6.  通过`components`注册的是<strong>私有子组件</strong>。
    7.  注册<strong>全局组件</strong>。在`main.js`操作。
        1.  步骤1：导入需要注册的组件
            1.  `import Count from '@/components/Count.vue'`
        2.  步骤2：通过Vue.components()方法注册组件。
            1.  `Vue.component('MyCount', count)`
            2.  注：参数1字符串格式，表示注册名称；参数2，需要全局注册的组件。

13. 组件props，自定义属性，在封装通用组件的时候，合理的使用props极大的提高组件的复用性。
    1.  比如不同组件复用同一个组件允许其设置不同初始值。
    2.  全局组件设置为例，语法格式(以count为例数字加1)
        ```JS
        export default{
                //组件的自定义属性，允许使用者为当前属性初始值。
                //方式一：
                //props:['自定义属性A','自定义属性B','自定义属性C'..],
            props:['init'], //1.允许组件设置设置初始值
                //方式二：
            props: {
                init: { //用default属性定义自定义init属性的默认值
                    default: 0,
                    type:Number, //可选，设置属性值类型，否则报错
                    required: true, //可选，让用户强制填写数值，与默认值无关
                }
            }    
                //组建的私有数据
            data(){
                return{
                    //count: 0, //2. 默认初始值为0
                    count: this.init, //2.因为init不能直接修改，可以赋值给count
                }
            }，
            methods: {
                add(){
                    this.count= this.count +1
                }
            },
        }
        ``` 
    3. 在组件使用该全局组件的template内设置：`<MyCount :init="9"></MyCount>`，设置初始值为9。
       1. 使用`v-bind:init="9"`：<strong>表现为v-bind等号右边的内容为JS表达式。相当于"JS表达式"，若是字符串则:init="'9'"</strong>。或者直接init=9。
    4. props中的数据，可以直接在模板结构中被使用。<strong>props是'只读'的</strong>，程序员不能直接修改props值。可通过赋值给data(){}数据源间接修改。
    5. 可使用`default`来设置默认值。

14. 组件样式的冲突问题：默认情况下，写在.vue组件中的样式会全局生效，造成多个组件之间的样式冲突。
    1.  原因：
        1.  一：单页面应用，所有组件的DOM结构都是基于唯一index.html页面呈现。
        2.  二：每个组件中的样式，都会影响整个index.html页面中的DOM元素。
    2.  扩展：`<h2 v-data-001>`中的h2为标签，v-data-001为自定义属性，如class。CSS样式写法：`h2[v-data-001]{。。}`
    3.  解决冲突问题：在组件中的`<style>`添加`scoped`；如`<style lang="less" scoped>`
    4.  当引用组件时，如Left.vue使用Count.vue组件，在Left.vue添加了scoped设置样式去影响Count.vue内容，无法影响到Count.vue下的样式(默认全局可以影响)。
        1.  使用`/deep/`在Left.vue设置样式。如`/deep/ h5{...}`
        2.  使用场景：使用第三方组件库修改<strong>默认</strong>样式的时候要用到`/deed/`

15. package.json中devDependencies下的vue-template-complier负责把vue组件转成js解析到浏览器HTML页面中。

16. 组件生命周期及组件之间的数据共享。
    1.  生命周期：创建->运行->销毁 的整个阶段。
    2.  创建：new Vue() -> beforeCreate -> created(存在于内存) -> beforeMount(准备渲染到浏览器) -> mounted 
    3.  运行：beforeUpdate -> updated
    4.  销毁：beforeDestroy -> destroyed
    5.  大致路线：：webpack打包-> main.js发现-> App.vue发现其他组件-> Left.vue/Right.vue ..-> 编译纯JS代码（chunk-vendors.js + app.js-> 放入index.html
    6.  生命周期详细说明：[官网原图](https://v2.vuejs.org/v2/guide/instance.html?redirect=true)
        1.  创建👇（每次执行一次）
        2.  'new Vue()' - 创建组件的实例对象
        3.  'init Events & Lifecycle' - 初始化事件和生命周期
        4. `(beforeCreate)` - 组件的`props/data/methods`尚未被创建，<strong>处于不可用状态</strong>
        5.  'Init injections & reactivit'y - 初始化`props、data、methods`
        6.  `(created)` - 组件的`props/data/methods`已创建好，都<strong>处于可用</strong>的状态。但是组件的模板结构尚未生成 -> 不能操作DOM。<strong>此处一般用于Ajax请求拿数据（页面打开时显示）</strong>，调用methods方法并转存到data中给模板渲染使用。
        7.  'el options / vm.$mount(el)' -> 有无'template'选项。
        8.  'compile template into render function(有模板) / compile el's outerHTML as template(无模板)' - 基于数据和模板，在内存中编译生成HTML结构。vue-template-complier包 进行编译。
        9.  `(beforeMount)`：将要把内存中编译好的HTML结构渲染到浏览器中。此时浏览器中还没有当前组件的DOM结构。（很少用到）
        10. 'create vm.$el & replace el with it'：用内存中编译生成的HTML结构，替换掉el属性指定的DOM元素。`this.$el`。渲染出DOM结构。
        11. `mounted`：已经把内存中的HTML结构，成功渲染到浏览器中。此时浏览器已然包含了当前组件的DOM结构。要操作当前的DOM，最早只能在mounted阶段执行。
        12. 运行👇（可以执行0次~N次）
        13. `(beforeUpdate)`：<- 数据变化触发。将包根据变化过后、最新的数据，重新渲染组件的模板结构。第一次拿到数据(刷新、加载页面)自动触发一次。data数据最新如`this.message`，DOM仍是旧的（页面仍是旧数据）如`document.querySelector('#message').innerHTML`。
        14. 'Vvrtual DOM re-render & patch'：根据最新的数据，重新渲染组件的DOM结构。
        15. `(updated)`：已经根据最新的数据，完成了组件DOM结构的重新渲染。
        16. 销毁👇(执行一次)
        17. 'vm.$destroy()' is called
        18. `(beforeDestroy)`：将要销毁此组件，此时尚未销毁，组件还处于正常工作的状态
        19. 'teardown watchers,child components and event listners'：销毁当前组件的数据侦听器、子组件、事件监听。可用v-if在组件标签中取反测试。
        20. `(destroyed)`：组件已被销毁，此组件在浏览器中对应的DOM结构已被完全移除。
        

17. 组件数据共享：父子关系、兄弟关系。
    1. 父组件向子组件共享数据需要使用自定义属性。子：通过`props`来自定义属性。父：数据通过`v-bind:`绑定给子组件。
    ```JS
    //父组件  - 父把userinfo的引用（地址）传给user,message直接传值给msg
    <son :msg="message" :user="userinfo"></son>
    data(){
        return{
            message:'hello vue',
            userinfo:{ name: 'zs', age: '20'}
        }
    }
    //子组件
    <template>
        <div>
            <p>父组件传递过来的msg时：{{ msg }}</p>
            <p>父组件传递过来的msg时：{{ user }}</p>
        </div>
    </template>

    props:['msg','user']
    //子组件通过props的userinfo接收父组件传递过来的引用，地址指向父组件的user。
    //message字符串直接传值给msg
    ``` 
    1. 子向父组件传递数据需要在父组件使用自定义事件。子：通过`$emit()`触发自定义事件；参数1·字符串，表示自定义事件名称；参数2·值，要发送给父的数据。父：通过`v-on`来绑定自定义事件，并提供一个处理函数。
    ```JS
    //子组件    -> template的button加1
    data(){
        return{
            count: 0, //子组件把count值传给父组件
        }
    },
    methods:{
        add(){
            this.count += 1
            //步骤三，修改数据的同时，通过$emit()触发自定义事件，向父组件发送数据
            this.$emit('numChange', this.count) 
        }
    }
    //父组件
    <son @numChange="getNewCount"></son> //步骤一

    data(){
        return{
            countFromSon: 0, //定义来接收子组件传递过来的值
        }
    },
    methods:{
        getNewCount(val){  //步骤二，自定义事件
            this.countFromSon = val
        }
    }
     ```
    1. 兄弟组件之间的数据共享：vue2.x中使用EventBus(eventBus.js)。
       1. 步骤一：创建`eventBus.js`模块，并向外共享一个Vue的实例对象
       2. 步骤二：在数据发送方，调用`bus.$emit('事件名称'，要发送的数据)`方法触发自定义事件
       3. 步骤三：在数据接收方，调用`bus.$on('事件名称'，事件处理函数)`方法注册一个自定义事件
    ```JS
    //组件A 数据发送方
    <button @receiveMsg="sendMsg">发送</button>
        ..
    import bus from './eventBus.js'
        ..
    data(){
        return{
            msg: 'hello, this is sender.'
        }
    },
    methods:{
        sendMsg(){
            bus.$emit('share',this.msg)
        }
    }

    //组件B 数据接收方 //在components下创建
    import bus from './eventBus.js' 
        ..
    data(){
        return{
            msgFromA:'',
        }
    },
    //created考虑到生命周期
    created(){ //类似jQuery使用$('.btn').on('click',Fn)通过on绑定事件
        bus.$on('share',val=>{      //此处share形参，不放在方法下面
            this.msgFromLeft = val
        })
        
    }

    //创建eventBus.js模块
    import Vue from 'vue'
    //向外共享Vue的实例对象->new Vue()
    export default new Vue()
    ``` 

18. jQuery简化了操作DOM的过程。vue在MVVM中，不需要操作DOM。秩序维护数据（数据驱动视图）。因此在vue中不推荐安装和使用jQuery。
    1.  `ref`用来辅助在不依赖于jQuery的情况下，获取DOM元素或组件的引用。
    2.  每个vue组件实例都包含一个`$refs`对象，里面存储着对应的DOM元素或组件的引用。
    3.  默认情况下，组件的`$refs`指向一个空对象。
    4.  `methods:{ showThis(){ console.log(this) } }`；显示当前组件的vue实例对象。
    ```JS
    <h1 ref="myh1">APP组件</h1> //$refs从空变为 -> $refs: myh1:h1
    <button @click="showThis">打印this</this>

    methods:{ 
        showThis(){ 
            console.log(this)           //自身vue实例
            console.log(this.$refs.myh12) //DOM输出：<h1>APP组件</h1>
            this.$refs.myh12.style.color = 'red' //改变DOM颜色
            } 
    }
    ```
    5. 使用`ref`引用组件实例
       1. 获取实例时出错需考虑声明周期。
       2. 比如：获取不到`ref`因为数据已更新但是DOM没有更新则获取不到`ref`。
       3. `this.$nextTick(callbackFn)`：延期执行；把callback回调推迟到下一个DOM更新周期之后执行。即当渲染完毕DOM以后再执行的函数，保证callback可以操作到最新的DOM元素。
       4. 一般使用箭头函数`this.$nextTick( ()=>{ 执行逻辑 } )`
       5. 若使用`updated(){}`，数据更新后可能状态不一样，比如DOM隐藏而获取不到DOM了。
    ```JS
    <A ref="counterRef"></A> //用ref属性，为组件A添加引用名称
    <button @click="getRef">获取 $refs 引用</button>
        ...
    methods:{
        getRef(){
            console.log(this.$refs.counterRef) //通过this.$refs.引用名称，可以引用组件A的实例
            this.$refs.counterRef.add() //引用到组件A的实例之后，可以调用组件上的methods方法。
        }
    }
    ``` 
       6. 扩展JS：数组中的方法 - some循环。
          1. 找到对应项之后'item===b'，可以通过return true固定语法终止 some 循环
          2. 普通数组arr：`arr.some( (item,index)=>{ if(item === 'b'){ return true } } )`
          3. 案例场景：用于搜索当前数组数据更新项，确定后即可推出循环。
       7. 扩展JS：数组中的方法 - every循环
          1. 判断数组中，水果状态是否一致（实际操作判断是否选中的全部都是水果）
          2. 数组对象arr(对象里有status:true)：`const result = arr.every( item=>item.state )`
       8. 扩展JS：数组中的方法 - reduce基本用法
          1. 把购物车数组中，已勾选的水果，总价累加起来
          2. 数组对象arr(做法一)：
             1. 先过滤选中内容：`arr.filter( item=> item.state).forEach(..)`
             2. forEach中循环每一项：`.forEach( item=>{ total += item.price * item.count } )`；total要在循环外预先定义。
          3. 使用reduce（做法二）：
             1.  先过滤选中内容：`arr.filter( item=> item.state).reduce(..)`
             2.  reduce中循环
                 1.  语法：reduce( (累加结果,当前循环项)=>{}, 初始值)；一般情况 初始值：0 = 累加结果；若要累加结果，一般要{return 累加结果}让其在循环中一次次累加。
                 2.  写法：`const result= .reduce( (total,item)=>{ return total+= item.price*item.count}， 0)`; total经过内部累加后最后一次循环有个<strong>return</strong>把结果传给reduce再值赋给result。

19. 购物车案例
    1.  知识扩展：APP组件使用子组件Goods循环，APP需要向Goods分享数据
        1.  方法一：父组件在`<Goods>`使用:`:title="item.goods_name"`,`:pic="item.goods_img"`绑定子组件的接收传递`props:{ title , pic }`等
        2.  方法二：父组件传递整个对象到子组件`:goods="item"`, 子组件通过对象形式接收数据`props:{ goods:{ type:Object, default:{} }}`
        3.  对比方法，若普通商品列表'item.goods_title','item.goods_price'和促销商品列表'item.onsale_title','item.onsale_price'均传入Goods，Goods需要区别'goods.goods_title'和'goods.onsale_title'，造成子组件接收繁琐，非通用性。
    2.  商品勾选状态
        1.  查看，通过Vue插件在浏览器的`<Root>`查看商品列表，对数据的勾选状态`state:true`进行变更`state:false`会实时反馈到浏览器界面
        2.  通过浏览器对商品勾选框进行'勾选'和'取消勾选'，可以观察到浏览器数据并没有变更。此时不能用`v-model`，因为此时的`state`为`props`。
            1.  监听使用`e`:如`e.target.XXX`
        3.  使用自定义事件，通过子组件状态变更同步到父组件数据状态。`this.$emit（'state-change',{id, value}）`子传父。
        4.  子组件监听复选框状态变化，拿到最新的勾选状态`<input type="checkbox" @change="stateChange" />`，复选框变化则会自动触发change事件。
        5.  父组件：`<Goods @state-change="getNewState"></Goods>` & `methods:{ getNewState(e){ .. } }`,`e`为该触发对象`this.$emit(传递对象值e）`
            1.  "console.log(this.$emit)"输出： ‘ƒ (...args) {  const res = original.apply(this, args);   logEvent(this, method, args[0], args.slice(1));   return res; }’
            2.  `console.log(e)`输出：Event..等所需内容； 比如获得勾选状态：‘const newState = e.target.checked  console.log(newState)’
            3.  `console.log(this)`：获得当前Vue组件VueComponent{。。。}
        6.  父组件在methods接收数据注意：`newStateChange(val){...}`，若要从子组件传递更新数据到父组件，需要形参val来接收数据。
    3.  父传子一般从子接收开始写代码。
    4.  js让数字显示两位小数点： `num.toFixed(2)`
    5.  CSS补充笔记 , [链接参考](https://codepen.io/tianzi77/pen/aOrBdb)： 
    ```CSS
      html, body {
        height: 100%; /*让页面撑满窗口*/
      }
    ```    
    6. 组件数据的递进：孙组件Count获取数据 -> 子组件Goods获取数据 -> 父组件App，使用props递进数据传递。
       1. 孙组件Count修改数量count的值并且赋予App数据更新。
       2. 可以按照自定义事件，孙->子->父
       3. 使用eventBus（适用于多级别父子关系），注：统一数据关系需要递进id。
          1. 发送给App的数据格式为{id, value}; $emit('xx', {id,value} )
       4. 使用eventBus步骤
          1. 在components下创建eventBus.js： `import Vue from 'vue';         export default new Vue(); `
          2. 在当前需要传递数据给App的组件内导入`import bus from '@/components/eventBus.js'`
          3. 在当前组件内的方法中直接使用`bus.$emit('share', {id:this.id, value:this.num+1} )`
             1. 其中num为props，此处this.num+1并没有更改props的值
          4. 在被传递方APP下也导入同样的bus`import bus from '@/components/eventBus.js'`
          5. 在`created`函数中使用`$on`函数：`bus.$on('share',()=>{  console.log("组件接收到了counter的值")  })`
             1. `('share',(val)=>{})`：val用来接收传过来的值，案例如下
             ```JS
            bus.$on('share',(val)=>{
                // console.log("组件接收到了counter的值")
                this.list.some(item=>{
                    if(item.id === val.id){
                    item.goods_count = val.value
                    return true
                    }
                })
                })
             ``` 
20. 
21. console输入Vue.config查看vue配置。
   ```HTML
    <div id="app">
        
    </div>
    <script>
        const vm=new Vue({
        el:'#app',
        data：{
            r:'',
        }，
        computed:{//${} 模板字符串解析变量的写法
            rgb:function(){
                //return '${this.r}'
            }
        }
        })
    </script>
   ```


___


## CSS学习
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