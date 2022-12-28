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







​		



























​	