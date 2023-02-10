# 尚硅谷谷粒后台

## 前期准备

##### Github关联&分支的使用：

	1. 链接到Github并推送本地栈：`git push origin master`
	2. 推送主代码后，立即切换到分支进行代码的迭代。
		1. 命令：`git check -b dev` （本地）
	3. 推送分支代码到git。
		1. 命令：`git push origin dev`
	4. 若新建立空文件夹，从Github下载下来
		1. 先创建空文件夹，进入到该目录
		2. 复制远程到本地：`git clone https://xxxx.git` 
	    	1. 远程主/次分支均得到，默认只生成本地主分支
		3. 查看本地当前使用的分支：`git branch`
	5. 根据远程branch生成本地branch并且切换到该分支。
		1. `git checkout -b dev origin/dev`
		2. 此步骤不会再发请求，根据当clone时已下载的分支进行即时创建分支
	6. 获得本地分支后进行拉取更新方式。
		1. 命令：`git pull origin dev`
	7. 谷粒Github教师开源地址：[点击](https://github.com/zxfjd3g/190105_ReactAdmin)

##### 对于UI组件antd的使用

1. 安装：`npm install antd`

2. 使用（按需使用原则）

   ```jsx
   import Button from 'antd/es/button';
   import './App.css';
   import 'antd/es/button/style/css';
   
   export default function App() {
     return (
       <div className="App">
           <Button type="primary">惦记我</Button>
       </div>
     );
   }
   ```

3. 组件按需打包（教程可能用旧版，按需加载有问题查看官网3.x快速入门）

   1. 下载依赖模块：`npm i react-app-rewired customize-cra babel-plugin-import`

   2. 定义加载配置的 js 模块`config-overrides.js`

      ```js
      const {override, fixBabelImports,addLessLoader} = require('customize-cra');
      module.exports = override(
      	//针对antd实现按需打包：根据import来打包（使用babel-plugin-import）
          fixBabelImports('import',{
              libraryName:'antd',
              libraryDirectory:'es',
              style:'css',//自动打包相关的样式
          }),
          addLessLoader({
              javascriptEnabled:true,
              modifyVars:{'@primary-color':'#1DA57A'},
          })
      )
      ```

   3. 若提示缺少less支持，安装`less`和`lessloader`

      1. 命令：`npm i less less-loader`

4. 若不想按需导入CSS，可以一键导入Antd整体CSS

   ```jsx
   import 'antd/dist/antd.min.css';
   //注意非antd.css
   //否则报错：Antd source map not supported
   ```

5. 示例

   ```jsx
   import { Button, message } from 'antd';
   import React from 'react';
   import './App.css';
   import 'antd/dist/antd.min.css';
   
   export default function App() {
       const showMsg = ()=>{
           message
               .loading('Action in progress..', 2.5)
               .then(() => message.success('Loading finished', 2.5))
               .then(() => message.info('Loading finished is finished', 2.5));
       }
     return (
       <div className="App">
           	<Button type="primary" onClick={showMsg}>惦记我</Button>
       </div>
     );
   }
   ```

## 结构与样式

##### CSS

1. 使用`reset.css`对样式进行清除，

2. 对整个页面主题部分重新定义

   ```css
   html,body{
       height:100%;
       width: 100%;
   }
   #root{
       height: 100%;
       width: 100%;
   }
   ```

3. 高阶组件：

   1. 本质就是一个函数
   2. 接收一个组件（被包装组件），返回一个新的组件（包装组件），包装组件会向被包装组件传入特定属性
   3. 作用：扩展组件的功能
   4. 高阶组件也是高阶函数：接收一个组件函数，返回时一个新的组件函数。

##### 跨域问题

1. 一般跨域解决方式
   1. JSONP：解决`get`跨域请求
   2. CORS
   3. 代理服务器
      1. 仅限开发环境，在`package.json`最后添加：`"proxy":"http://localhost:5000"`

##### Promise讲解

1. Pormise对象的一般使用

   ```js
   reqLogin(username,password).then(response=>{
               console.log('成功了',response.data)
          }).catch(error=>{
       		console.log('失败了', error)
   	   })
   ```

2. Promise的不同接收方式

   ```js
   //接收Promise方式
   const promise = reqLogin(username,password) //reqLogin返回的是Promise对象
   //接收response
   async ()=>{
       try{
   		const response = await reqLogin(username,password)
      	 	console.log('请求成功',response)
       }catch(error){
           console.log('请求失败',error)
       }
   }
   ```

3. 执行器函数

   ```jsx
   new Promise((resolve,reject)=>{
       ..执行代码
       成功调用resolve(value)
       失败调用reject(reason)
   })
   ```

## Antd部分组件使用

##### 提示消息

1. 举例：对登录页面的操作

   ```jsx
   import {message} from 'antd'
   
   message.success('登录成功') //message显示在页面顶部
   // 跳转到管理界面 (不需要再回退回到登陆)
   this.props.history.replace('/')
   
   message.error('登录失败')
   ```

##### 在render中跳转

1. 当判断用户没有登录的时候，可以在admin页面中判断有无用户信息

   ```jsx
   import {Redirect} from 'react-router-dom'
   
   render(){
       const user = cookieUtils.user //自定义方法获取Cookie 中的user
       if(!user || !user._id){
           return <Redirect to='/login'/>
       }
       ...
   }
   ```

##### 保存用户到Cookie

1. 保存user、读取user、删除user到Cookie

   ```jsx
   import store from 'store' //安装库：npm install store
   const USER_KEY = 'user_key'
   export default {
     /*  保存user   */
     saveUser (user) {
       // localStorage.setItem(USER_KEY, JSON.stringify(user))
       store.set(USER_KEY, user) //store自动将对象转成JSON
     },
   
     /*  读取user   */
     getUser () {
       // return JSON.parse(localStorage.getItem(USER_KEY) || '{}')
       return store.get(USER_KEY) || {}
     },
   
     /*  删除user   */
     removeUser () {
       // localStorage.removeItem(USER_KEY)
       store.remove(USER_KEY)
     }
   }
   ```

2. 在内存中保存数据工具

   ```jsx
   /* 用来在内存保存一些数据的工具模块 */
   export default {
     user: {}, // 保存当前登陆的user
   }
   ```

3. 读取位置

   1. 一般在入口文件`src/index.jsx`读取用户
   2. 在login界面也需要使用用户登录判断，以防止用户手动输入login页面地址。

##### 使用Layout

1. 使用Antd的Layout可以快速排版后端格局
2. 注意点：若Layout不是页面100%，可以使用：`<Layout style={{height:'100%'}}>..`

##### 带有确认遮罩层的Logout

1. 适用`Modal.confirm`进行确认登出

   ```jsx
   logout = ()=>{
       Modal.confirm({
           content:'确认退出吗？',
           onOK(){
               //删除保存的user数据
               localStorage.removeItem(USER_KEY)
               memoryUtils.user={}
               //跳转到login页面
               this.props.history.replace('/login')
           }
       })
   }
   ```

## 使用JSONP

##### 第三方库 JSONP

1. json请求的接口函数，用于获取远程数据。`JSONP`j仅仅适用`GET`类型的ajax请求跨域问题。
2. `jsonp`请求不是ajax请求，而是一般的get请求。
3. 可在github查询，安装命令：`npm install jsonp`
4. 语法API：`jsonp(url, pots, fn)`
   1. url(String)：地址
   2. opts(Object)包含以下选项
      1. param（String）：回调函数的名字，默认`callback`
      2. timeout（Number）：timeout的时长，默认6000毫秒，`0`表示禁止。
      3. prefix（String）：回调函数名，用于处理jsonp的结果responses，默认`__jp`
      4. name（String）：回调函数名，用于处理sonp的结果responses，默认`prefix + incremented counter`
   3. fn：回调函数，包含err，data两个参数
5. JSONP通过浏览器的`<script>`标签发送请求，请求URL携带参数`callback=__jp0`。服务器端需要返回的数据包含该函数名的`__jp0`的执行函数。
6. 具体原理
   1. 浏览器端：
      1. 动态生成`<script>`来请求后台接口（src就是接口的url）
      2. 定义好用于接收响应数据的函数，并将函数名通过请求参数提交给后台（如：callback=fn）
   2. 服务器端：
      1. 接收到请求处理产生结果数据后，返回一个函数调用的js代码，并将结果数据作为实参传入函数调用。
   3. 浏览器端：
      1. 收到响应自动执行函数调用的js代码，也就执行了提前定义好的回调函数，得到了需要的结果数据。

##### 声明周期组件

1. `componentDidMount()`：
   1. 在第一次`render()`之后执行一次。
   2. 一般在此执行异步操作：发ajax请求 / 启动定时器。
2. 标签体内容是一个特殊的标签属性
   1. 比如`<A>退出</A>`中，‘退出’是特殊属性值，相当于`children: '退出'`









## TypeScript

##### 概念

1. TypeScript是以JavaScriot为基础构建的语言，一个JavaScript的超集。
2. TypeScript扩展了JavaScript，并添加了类型。
3. 可以在任何支持JavaScript的平台执行。
   1. TS不能被JS解析器直接执行。
4. TS需要**编译**成JS。
   1. TS可编译成任意版本JS。
5. TypeScri开发环境搭建
   1. 下载Node.js：x64 | x32。视频版本14.15.1。
   2. 安装Node.js
   3. 使用npm全局安装typescript：`npm -g typescript`
   4. 创建一个ts文件
   5. 使用tsc对ts文件进行编译：
      1. 进入ts文件所在目录
      2. 执行命令：`tsc xxx.ts`
6. 入门参考，2W文字总结版：[掘金地址](https://juejin.cn/post/7018805943710253086?share_token=c4029c41-9cc7-4e39-9e2d-c57b6617d2d8)

##### 基本类型

1. 类型声明

   1. 类型声明是TS非常重要的一个特点

   2. 通过类型声明可以指定TS中变量（参数、形参）的类型

   3. 指定类型后，当为变量赋值时，TS编译器会自动检查值是否符合类型声明，符合则赋值，否则报错

   4. 语法：

      ```typescript
      let 变量： 类型;
      //例如
      let a: number; 
      
      let 变量： 类型 = 值;
      //例如
      let a: number = 1;
      
      function fn(参数：类型，参数：类型):类型{
          ...
      }
      //例如
      function sum(a: number, b: number){
          return a + b;
      }
      sum(123,"456");//报错，但是仍可编译
      ```

   5. 注意：如果变量和赋值同时进行，TS可以自动对变量进行类型监测。

2. 类型

   | 类型    | 例子                                                         | 描述                                                         |
   | ------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
   | number  | 1、-33、2.5                                                  | 任意数字                                                     |
   | string  | 'hi'、"hi"、hi                                               | 任意字符串                                                   |
   | boolean | true、false                                                  | 布尔值true或false                                            |
   | 字面量  | 字本身<br />如：`let a: 10;`<br />或者：`let b : "male" | "female";`<br />或者：`let c: boolean | string;` (称呼联合类型) | 限制变量的值就是该字面量的值                                 |
   | any     | *<br />显示：`let a: *;`<br />隐式：`let a;`                 | 任意类型，相当于关闭了TS的类型检测                           |
   | unknow  | *                                                            | 类型安全地any                                                |
   | void    | 空值（undefined）                                            | 没有值（或undefined），包括null                              |
   | never   | 没有值                                                       | 不能是任何值，永远不会返回结果，包括不会有null，可以使用throw |
   | object  | {name:'孙悟空'}                                              | 任意的JS对象（一般不用）                                     |
   | array   | [1,2,3]，或`let e:string[]`                                  | 任意JS数组                                                   |
   | tuple   | [4,5]，或`let e:[string, number]`                            | 元素，TS新增类型，固定长度数组                               |
   | enum    | enum{A,B}                                                    | 枚举，TS中新增类型                                           |

3. 其他示例

   ```typescript
   let a; //相当于*
   let b: unknown;
   let c: string;
   b = 10;
   b = "hello";
   b = "true";
   c = a; //a的类型是any，他可以赋值给任意变量，则c此处也是类型any
   c = b; //此处报错，因为unknown不能赋值给其他变量
   
   if(typeof b === "string"){
       a = b;
   }
   //使用断言方式，告诉解析器变量的实际类型
   a = b as string;
   a = <string>b; //另外一种方式
   
   function fn(num){
       return true; //根据return返回自动定义类型
   }
   function fn(): number | string {
       ..
   }
   function fn(): void{
       ...//①没有返回值时使用void
       return null;//②如果返回null也是可以
   }
   function fn2(): never{
       throw new Error('报错了');
   }
   
   //对象的表示
   let a: object;
   a = {}; //{} 用来指定对象中可以包含哪些属性
   a = function(){};
   //通常不写object，使用下面方式表示对象
   let b: {name: string}; //表示定义对象，必须写name的属性，且不能多写属性名
   b = {name:'lisi'};
   //使用？表示此属性可选
   let b: {name:string, age?: number};
   b = {name: '孙悟空'};
   b = {name:'猪八戒', age:13};
   //[propName:string]表示任意属性名，: any表示任意值；表示可输入多个
   let e: {name: string, [propName:string]: any};
   e = {name: '孙悟空', age:18, gender:'男'};
   //函数限制输入是number
   let d: (a:number, b:number)=>number;
   //语法：（形参：类型，形参：类型 ...）=> 返回值
   
   //数组的表示
   //number[] 表示数组数值
   let e: string[];
   e = ['a','b'];
   let g: Array<number>; //数组另外一种表现形式
   g = [1,2,3];
   
   //元组：固定长度的数组
   let h: [string, string];
   h = ['hello', 'abc'];
   
   //enum 枚举
   let i: {name:string, gender: string};
   i = {
       name:'孙悟空',
       gender:'男'
   }
   let i: {name:string, gender: 0 | 1};
   i = {
       name:'孙悟空',
       gender: 1 //比写'男'效率高
   }
   //枚举方式
   enum Gender{ //枚举类
       Male = 0,
       Female = 1
   }
   let i: {name:string, gender: Gender};
   i = {
       name:'孙悟空',
       gender: Gender.Male 
   }
   
   let j: string | number;
   let i: string & number;//有 & 方式，但是不适合这样用
   let i: {name: string} & {age: number};
   i = {name:'孙悟空',age:18};
   //类型的别名
   type myType = string; //相当于类型string的别名是myType
   let k: 1| 2| 3| 4| 5;
   let l: 1| 2| 3| 4| 5;
   let m: myType;
   //用法
   type myType = 1| 2| 3| 4| 5;
   let m: myType;
   ```

4.  实时监视TS变化（单个）

   1. 命令：`tsc app.ts -w` 
   2. 可以先编译成JS，再使用`-w`实时更新ts，限单个文件。

5. 监视全部TS变化

   1. 目录下创建`tsconfig.json`文件，该文件为TS编译器的配置文件，可以根据其对代码进行编译

   2. 使用IDEA创建默认生成下面

      ```json
      {
        "compilerOptions": {
          "module": "commonjs",
          "target": "es5",
          "sourceMap": true
        },
        "exclude": [
          "node_modules"
        ]
      }
      ```

   3. 可清空`tsconfig.json`文件，只保留文件名即可

   4. 命令：`tsc`  ； 编译当前目录下所有TS文件

   5. 命令：`tsc -w` ；即监控该目录下所有TS文件，实时更新到**已**生成的JS文件中。

   6. `tsconfig.json`说明

      ```json
      {	//include 用来指定哪些ts文件需要被编译
          "include": [
              "./src/**/*"	//** 表示任意目录， * 表示任意文件
          ],
          // 表示不包含，默认值：node_modules、bower_components、jspm_packages
          "exclude":[
              
          ],
          //被编译文件的列表，只在编译文件少时才用到
          "files":[
              "core.ts",
              ..
          ]
      }
      ```

6. 编译器选项：`compilerOptions`  - tsconfig.json文件中

   ```json
   "compilerOptions": {
       //target用来指定ts被编译为ES的版本，ESNext为最新版本，可以通过故意写错来获得提示，可以输入的内容
       "target": "ES3",
       //属性模块，指定要使用的模块化的规范
       "module": "ES2015",
       //lib用来指定项目中要使用的库，默认不动。比如：document.getElement..
   //	"lib": [],  //比如["dom"]
       //用来指定编译后文件所在目录
       "outDir": "./dist",
       //输出的文件，将代码合并为一个文件；设置outFile后，所有的全局作用域的代码合并到同一个问价按
       "outFile": "./dist/app.js",
       //是否对js文件进行编译，默认是false
       "allowJs": false,
       //是否对JS检查其是否符合语法规范，默认是false
       "checkJs": false,
       //是否移除注释
       "removeComments": false,
       //不生成编译后的文件，鸡肋，当不想生成JS文件只检查编译时是否出错才会这样做
       "noEmit": true,
       //当有错误时不生成编译后的文件
       "noEmitOnError": false,
       //是否开启严格模式；使用import时为ES6，默认ES6启用严格模式
       "alwaysStrict": false,
       //对于any类型的隐式控制，默认false允许
       "noImplicitAny": false,
       //检查不明确类型的this；默认false允许不明确类型。比如函数内直接调用alert(this)，则从this默认使用any类型
       "noImplicitThis": false,
       //严格的检查控制
       "strictNullChecks": false,
       //所有严格检查的总开关，总开关需要写在所有开关之前
       "strict": false
   }
   ```

##### 使用Webpack打包

1. 使用webpack打包TS文件

2. 文件夹下初始化命令：`npm init -y`

   1. 主要作用：生成`package.json`来管理

3. 安装：`npm i -D webpack webpack-cli typescript ts-loader`

   1. `i`：安装
   2. `-D`：全局
   3. 装`webpack`和`webpack-cli`两个包
   4. 装t`ypescript` TS的和核心包和`ts-loader` TS加载器

4. 创建webpack配置文件`webpack.config.js`

   ```js
   //引入一个包，用于拼接路径
   const path = require('path');
   
   //webpack中的所有配置信息都应该写在module.exports中
   module.exports = {
       
       //指定入口文件
       entry: "./src/index.ts",
       //指定打包文件所在目录
       output:{
           //指定打包文件的目录
           path: ppath.resolve(__dirname, 'dist'),
           //打包后的文件名
           filename: "bundle.js"
       },
       //指定webpack打包时要使用的模块
       module:{
           //指定要加载的规则
           rules: [
               {
                   //test指定的时规则生效的文件
                   test: /\.ts$/，  //表示所有以ts结尾的文件
                   use: 'ts-loader', //表示用什么来（哪个loader）来处理ts结尾的文件
                   exclude: /node_modules/, //表示要排除的文件，一般排除node_modules
               }
           ],
       }
   }
   ```

5. 在`webpack.config.js`同级目录下创建`tsconfig.json`，给出下方参数即可

   ```json
   {
     "compilerOptions": {
       "module": "ES2015",
       "target": "ES2015",
       "sourceMap": true
     },
     //exclude可省略，在webpack中已移除打包
     "exclude": [
       "node_modules"
     ]
   }
   ```

6. 在`package.json`添加命令，方便打包：`npm run build`

   ```json
   "scripts":{
       "build": "webpack"
   }
   ```

7. 把打包好的JS文件引入到HTML页面两种方式

   1. 一：手动创建HTML页面，通过`<script>`标签引入

   2. 二：安装插件，自动生成

      1. 安装插件命令：`npm i -D html-webpack-plugin`

      2. `package.json`配置文档中的`devDependencies`多出`html-webpack-plugin`的行

      3. 把插件引入到`webpack.config.js`文件中：`const HTMLWebpackPlugin = require('html-webpack-plugin');`

      4. 配置插件

         ```js
         //在exports内
         plugins: [
             //自动生成HTML文件并且引入相关资源
             new HTMLWebpackPlugin({
                 title:"这是一个自定义的title",
                 //通过自定义html模板生成html文件
                 template: "./src/index.html"
             }), 
         ]
         ```

8. 通过插件实时更新内容到HTML

   1. 装插件命令：`npm i -D webpack-dev-server` 或者 `npm i --save-dev webpack-dev-server`

   2. 添加快捷命令到`package.json`

      ```json
      "scripts":{
          "start": "webpack serve --open chrome.exe"
          //弹幕： "start": "webpack serve --open --mode development"
      }
      ```

   3. 运行命令：`npm start`

   4. 注意：当前插件使用替换方式替换生成目录下的旧文件，部分情况下个别文件不一定会被替换

9. 使用删除后再编译文件，安装插件命令：`npm i -D clean-webpack-plugin`

   1. 配置到`webpack.config.js`

      ```js
      //引入clean插件
      const { CleanWebpackPlugin} = require('clean-webpack-plugin');
      
      module.exports ={
          ...
          plugins:[
              new CleanWebpackPlugin(),
              new HTMLWebpackPlugin()
          ],
          
          //用来设置引用模块
          resolve: {
              extensions: ['.ts','.js']
          }
      }
      ```

   2. 当使用引入模块时，即`import xxx from xx.ts`，由于ts默认webpack不能当作模块引入，需要设置，如上方配置。

10. 安装Babel

    1. 安装命令：`npm -i -D @babel/core @babel/preset-env babel-loader core-js`

    2. `preset-env`：预设好环境，对旧浏览器进行兼容

    3. `babel-loader`：babel与webpack结合的工具

    4. `core-js`：模拟JS运行环境，此包包含内容太多，可以按需配置

    5. 装完以后均自动配置到`package.json`

    6. 配置`webpack.config.js`

       ```js
       //指定webpack打包时要使用的模块
       module:{
           rules: [
               {
                   //test指定的时规则生效的文件
                   test: /\.ts$/，  //表示所有以ts结尾的文件
                   //注意loader执行顺序，后写的先执行
                   use: [
                   	//对babel-loader的需要一些配置信息
                   	{
                   		loader:'babel-loader', //指定加载器
                   		options:{ //设置Babel
                   			//设置预定义环境（浏览器）
                   			presets:[
                   				//指定环境的插件
                   				"@babel/preset-env"，
                   				//配置信息
                   				{
                   					targets:{
                   						"chrome":"88" //chrome兼容版本到88，表示88版本以下的都兼容
                                    	}，
               						"corejs":"3"  //根据安装的core-js判断
               						"useBuiltIns":"usage" //usage表示按需加载
              						}
                   			]
              				 }
               		},
                   	'ts-loader'
                   ], //ts-loader用来编译成js
                   exclude: /node_modules/, 
               }
           ],
       }
       ```

    7. 注意：打包后，默认webpack导出的js文件使用箭头函数为其实并使用立即执行函数，即`()=>{..}()`。其他内容可编译成兼容模式，唯独开头的箭头函数默认无法更改。

       1. 若在IE11以下，报错，因为IE不兼容箭头函数。

       2. 通过配置修复IE无法运行问题，配置`webpack.config.js`

          ```js
          module.exports = {
              entry: "./src/index.ts",
              output:{
                  path:path.resolve(__dirname,'dist'),
                  filename:"bundle.js",
                  //告诉webpack不适用箭头函数
                  environment:{
                      arrowFunction:false
                  }
              }
          }
          ```

##### 面向对象

1. 概念：一切操作都要通过对象，也就是所谓的面向对象。

   1. 操作浏览器要使用`window`对象
   2. 操作网页要使用`document`对象
   3. 操作控制台要使用`console`对象

2. **类** - `class`，定义类如下

   ```js
   class 类名{
       
       属性名：类型;
       static 属性名：类型; //不需要实例可以直接访问
       readonly 属性名：类型; //表示只读属性，无法修改
       
       constructor(参数：类型){
           this.属性名 = 参数;
       }
       
       方法名(){
           ...
       }
   }
       
   const xx = new 类名();
   ```

3. 弹幕：es6中子类的`__proto__`属性，表示构造函数的继承，总是指向父类子类`prototype`属性的`__proto__`属性表示方法的继承总是指向父类的`prototype`属性

4. `constructor(){}`：构造函数在对象创建时调用

5. `this`：

   1. 在实例方法中，this表示当前的实例；在构造函数中，this就是当前新建的那个对象
   2. 在方法中，通过this可以来表示当前调用方法的对象

6. **继承** 

   1. 继承后，子类将会继承拥有父类所有的方法和属性
   2. 如果在子类中添加了和父类相同的方法，则子类会覆盖父类的方法

   ```tsx
   class Animal{
       name: string;
       age: number;
       constructor(name: string, age: number){
           this.name = name;
           this.age = age;
       }
       sayHello(){
           console.log('动物再叫');
       }
   }
   //使Dog类继承Animal类，默认自动写了构造函数
   class Dog extends Animal{
       
   }
   ```

7. `super`关键字

   1. 如果在子类中写了构造函数，在子类构造函数中必须对父类函数进行调用，否则报错

   ```tsx
   class Dog extends Animal{
       
       sayHello(){
           //在类的方法中 super表示当前类的父类
           super.sayHello();
       }
       constructor(name: string, age: number){
           super(name,age);
           this.name = name;
           this.age = age;
       }
   }
   ```

8. `abstract`关键字

   1. 抽象类和其他类区别不大，只是不能用来创建对象
   2. 抽象类只能用来继承
   3. 抽象类中可以添加抽象方法
   4. 抽象方法只能定义在抽象类中，子类必须对抽象方法进行重写

   ```tsx
   abstract class Animal{
       name: string;
       age: number;
       constructor(name: string, age: number){
           this.name = name;
           this.age = age;
       }
       //抽象方法使用abstract开头，没有方法体
       abstract sayHello():void;
   }
   ```

9. 描述一个对象类型的方式之一如下

   ```tsx
   type myType = {
       name: string,
       age: number
   };
   const obj: myType = {
       name:'sss',
       age:111
   }
   ```

10. `interface`关键字

    1. 多个接口同个名称，则实际接口为多个接口的综合
    2. 接口可以在定义类的时候限制类的结构。
    3. 接口中的所有的属性都不能有实际的值

    ```tsx
    //接口定义一个类结构
    interface myInterface{
        name: string;
        age: number;
    }
    interface myInterface{
        gender: string;
        sayHello():void;
    //    sayHi(){}	//错误，不能写方法体
    }
    const obj: myInterface = {
        name:'sss',
        age:111,
        gender:'nan'
    }
    ```

11. 实现接口

    ```tsx
    class MyClass implements myInterface{
        name: string;
        //构造函数用于对属性的初始化来实现接口的属性
        constructor(name: string){
            this.name = name;
        }
        //初始化方法
        sayHello(){
            console.log('hi..');
        }
    }
    ```

12. 属性的封装

    1. TS可以在属性前面添加属性的修饰符
    2. public修饰符可以在任意位置访问， 默认值为public
    3. private私有属性，只能在*当前类*内部进行访问、修改，无法继承
    4. protect，受保护属性，只能在当前类和子类（继承）内使用

    ```tsx
    class Dog extends Animal{
        
        private name: string;
        private number: number;
        
        constructor(name: string, age: number){
            this.name = name;
            this.age = age;
        }
        //方式一：用方法来获取私有属性
        getName(){
            return this.name;
        }
        setName(value: string){
            this.name = value;
        }
        //方式二：TS方式
        get number(){
            return this.number;
        }
        set number(value: number){
            this.number = value;
        }
    }
    
    //调用：
    const pupy = new Dog("sunwukong",18);
    per.setName("筑坝");
    console.log(per.getName());
    //调用方式与属性一样
    console.log(per.number);
    
    //可以在类构造器直接定义属性
    class C{
        constructor(public name: string, public age: number){
            ..
        }
    }
    ```

13. 泛型

    1. 在定义函数或者类时，如果遇到类型不明确就可以使用泛型。

    ```tsx
    //方法
    function fn(参数: 类型): 类型{ //第二个类型为定义返回值类型
        return 参数;
    }
    
    function fn(a: number): number{
        return a;
    }
    function fn(a: any): any{
        return a;
    }
    //泛型
    function fn<T>(a: T): T{ //若没有定义<T>，后面的a: T会报错
        return a;
    } 
    //多个泛型
    function fn2<T,K>(a: T, b: K): T{
        return a;
    }
    //继承某个接口的泛型
    interface Inter{
        length: number;
    }
    function fn3<T extends Inter>(a: T): number{
        return a.length;
    }
    
    //调用
    //可以直接调用具有泛型的函数
    fn(10);	//不指定泛型，TS可以自动对类型进行推断
    fn<string>('hello'); //指定泛型
    fn3("123"); //不继承，此处也可以，只要有xx.length方法
    ```

14. 























​	