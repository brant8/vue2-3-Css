# 黑马Node.js

## 初始node.js

1. Javascript组成：JS核心语法（变量、循环、作用域等）、WebAPI（DOM、BOM、Ajax）
2. 浏览器展示效果 = 待执行的JS代码 -> **JavaScript解析引擎**
3. 不同浏览器使用不同JavaScript引擎。
4. 

















# 尚硅谷Ajax

##  Ajax介绍

1.  Ajax - Asynchronous JavaScript And XML, 即异步的`JS`和`XML`。

2.  通过**Ajax**可以在浏览器中发送异步请求，**优势**：无刷新获取数据。

3.  **XML** 可扩展标记语言，被设计用来传输和存储数据。

   1. XML和HTML类似，不同的是HTML中都是预定义标签，而XML中没有预定义标签，全都是自定义标签，用来表示一些数据。
   2. **Ajax初期**设计出来**数据交换**所使用的格式就是**XML**。
   3. 服务器端给浏览器返回的XML格式字符串。前端JS对数据进行解析，提取数据。
   4. 如今已经**被JSON取代**。

4.  Ajax优点

   1. 可以无需刷新页面与服务器端进行通信。
   2. 允许根据用户**事件**来更新部分页面内容。

5.  Ajax缺点

   1. 没有浏览历史，不能回退。
   2. 存在跨域问题（同源）。跨域：不同网站地址请求内容。
   3. SEO不友好。异步请求来的数据，在HTML源码中无法显示，JS动态创建，爬虫爬不到。

6.  HTTP

   1. Hypertext transport protocol，超文本传输协议，协议详细规定了浏览器和万维网服务器之间互相通信的规则。

   2. **请求报文**：重点是格式与参数

      ```js
      行：	GET（请求类型） /s?ie=utf-8（url路径） HTTP/1.1（http版本）
      头：	Host：atguigu.com
      	 Cookie：name=guigu
      	 Content-type：application/x-www-form-urlencoded
      	 User-Agent: chrome 83
      空行：
      体：	username=admin&password=admin
      	 GET（此处为空）
      	 POST（此处可以不为空）
      ```

   3. **响应报文**：

      ```js
      行：	HTTP/1.1（协议版本）	200（响应状态码）	OK（相应状态字符串）
      头：	Content-Type：text/html;charset=utf-8
      	 Content-length:2048
      	 Content-encoding: gzip
      空行：
      体：	（返回结果）
      	 <html>
          	<head>
          	</head>
      	 	<body>
          		<h1>尚硅谷</h1>
          	</body>
           </html>
      ```

   ## 网络控制台 Network

7.  浏览器（console同级别） Network界面内容梗概

   1. 进入到 Network界面，刷新页面，加载所有发送的请求。默认查看第一个资源内容。
   2. 菜单项：Headers、Preview、Response、Initiator、Timing、Cookies
   3. **Headers**：
      1. General：
      2. Reponse Headers：
         1. 响应头信息
         2. 点击'view source'可查看原始报文，包含相应行和响应头。
         3. 响应体在*Headers同级别菜单Response*
         4. 若响应结果为页面跳转，则当前页的响应体为空。
      3. Request Headers：
         1. 刷新浏览器时向服务器发送的*请求头*内容
         2. 点击'view source'可查看当前*请求行*。
      4. Query String Parameters：
         1. 对 请求url路径 参数进行解析。
      5. Form Data：
         1. 登录账号后（案例）出现的**原始请求体内容**
   4. **Response**：
      1. 响应体 HTML内容

8.  Ajax常使用浏览器的Network下的Query String Parameters检查参数。

   ## NodeJS

9.  官网下载安装包

   1. CMD查看Node.js版本：`node -v`

10.  ExpressJS：简单服务器框架，基于node.js。

     1. `npm`是Node.js包平台下的管理工具。

     2. 指定目录下开始npm管理：`npm init --yes`

     3. 安装ExpressJS：`npm i express`

     4. 在该目录下创建Express启动文件：express.js

        ```js
        //1.引入express
        const express = require('express');
        
        //创建应用对象
        const app = express();
        
        //3.创建路由规则
        // request - 对请求报文的封装； response - 对响应报文的封装
        app.get('/',(request,response)=>{
            //设置响应
            response.send('Hello Express');
        });
        
        //监听端口启动五福
        app.listen(8000,()=>{
            console.log("服务已经启动，8000端口监听中")
        })
        ```

     5. 启动Express：`node express.js`

     6. 浏览器打开地址即可：`127.0.0.1:8000`

11.  **GET- AJAX案例及讲解**：

     1.  Network窗口栏有一小选项：`Fetch/XHR` ，即表示AJAX请求。

     2.  ExpressJS的服务器， 启动命令：`node 1-server.js`

         ```js
         //1-server.js
         //1.引入express
         const express = require('express');
         
         //创建应用对象
         const app = express();
         
         //3.创建路由规则
         // /server为服务器路由
         app.get('/server',(request,response)=>{
             //设置响应头 设置允许跨域
             response.setHeader('Access-Control-Allow-Origin','*');
             //设置响应体
             response.send('Hello Ajax GET');
         });
         
         app.post('/server',(request,response)=>{
             //设置响应头 设置允许跨域
             response.setHeader('Access-Control-Allow-Origin','*');
             //响应头 （若不设置，则前端自定义请求头会报错，但此处还会报错，需要用下面app.all()形式）
             response.setHeader('Access-Control-Allow-Headers','*');
             //设置响应体
             response.send('Hello Ajax POST');
         });
         
         app.all('/server',(request,response)=>{
             response.setHeader('Access-Control-Allow-Origin','*');
             response.setHeader('Access-Control-Allow-Headers','*');
             response.send('Hello Ajax POST');
         });
         //JSON
         app.all('/json-server',(request,response)=>{
             response.setHeader('Access-Control-Allow-Origin','*');
             response.setHeader('Access-Control-Allow-Headers','*');
             response.send('Hello Ajax JSON');
         });
         
         //监听端口启动五福
         app.listen(8000,()=>{
             console.log("服务已经启动，8000端口监听中")
         })
         ```

     3.  HTML测试文件

         ```html
         <!DOCTYPE html>
         <html lang="en" xmlns="http://www.w3.org/1999/html">
         <head>
             <meta charset="UTF-8">
             <title>Title</title>
             <style>
                 #result{
                     width: 200px;
                     height: 200px;
                     border:solid 1px deeppink;
                 }
             </style>
         </head>
         <body>
         
         <button>点击发送请求</button>
         <div id="result"></div>
         <script>
             const btn = document.getElementsByTagName('button')[0];
             const result = document.getElementById("result");
             btn.onclick=function(){
                 // console.log('test');
                 //1.创建对象
                 const xhr = new XMLHttpRequest();
                 //2.初始化，设置请求方法和url
                 xhr.open('GET','http://127.0.0.1:8000/server?a=100&b=200');
                 //3.发送
                 xhr.send();
                 //4.事件绑定，作用：处理服务端返回的结果
                 //on when 当...时候 ；
                 //readystate 是 xhr对象中的属性，表示状态值： 
                 //	0-初始值，
                 //	1-open方法调用完毕，
                 //	2-send方法调用完毕，
                 //	3-服务端返回部分结果，
                 //	4-服务端返回所有结果
                 //change 改变的时候，即改变一次触发一次。
                 xhr.onreadystatechange = function(){
                     //判断（服务端返回了所有结果）
                     if(xhr.readyState === 4){
                         //判断响应状态码 200 404 403 401 500
                         //2xx表示成功
                         if(xhr.status === 200 && xhr.status < 300){
                             //处理结果； 行头，空行，体
                             //1.响应行
                             console.log(xhr.status);//状态码
                             console.log(xhr.statusText);//状态字符串
                             console.log(xhr.getAllResponseHeaders())//所有响应头
                             console.log(xhr.response);
                             //无刷新在HTML设置result文本
                             result.innerHTML = xhr.response;
                         }
                     }
                 }
             }
         </script>
         
         </body>
         </html>
         ```

     4.  部分讲解：`xhr.open('GET','http://127.0.0.1:8000/server?a=100&b=200');`

         1.  传参在`open`处传参
         2.  当中的URL传参可在Network下的文件名查看
         3.  在Headers（有时候在Payload选项Tab）里的Query String Parameters可以查看传递的参数

12.  **POSt - Ajax案例讲解**

     1.  注意在ExpressJS中添加`app.post(..)`（参考上面Express配置），否则会报以下错误（没有对应POST入口）

         ```js
         Access to XMLHttpRequest at 'http://127.0.0.1:8000/server' from origin 'http://localhost:63342' has been blocked by CORS policy: No 'Access-Control-Allow-Origin' header is present on the requested resource.
         
         2-POST.html?_ijt=adigt4e28qraa92uk7ef48ju16&_ij_reload=RELOAD_ON_SAVE:28          POST http://127.0.0.1:8000/server net::ERR_FAILED 404
         ```

     2.  对应js让其在页面显示response内容

         ```js
         //获取元素
         const result = document.getElementById("result");
         //绑定事件
         result.addEventListener("mouseover",function(){
             // console.log("test");
             //1.创建对象
             const xhr = new XMLHttpRequest();
             //初始化 设置类型与URL
             xhr.open('POST','http://127.0.0.1:8000/server');
             //3.发送(带参数有不同格式)
             xhr.send('a=100&b=200');
             //xhr.send('a:100&b:200');
             //4.事件绑定
             xhr.onreadystatechange = function(){
                 //判断
                 if(xhr.readyState ==4 ){
                     if(xhr.status >=200 && xhr.status<300){
                         //处理服务端返回的结果
                         result.innerHTML=xhr.response;
                     }
                 }
             }
         })
         ```

     3.  部分讲解 POST参数

         1.  POST参数在`xhr.send()`中传参，且有不同传参方式

     4.  设置预定义请求头，如 `xhr.setRequestHeader('Content-Type','application/x-www-form-urluncoded');`

         1.  `Content-Type`：设置请求体内容的类型
         2.  比如请求体内容类型：`xhr.send('a=100&b=200');`
         3.  `application/x-www-form-urluncoded`：固定写法，请求体字符串查询类型

     5.  设置自定义请求头：

         1.  需要在服务端允许自定义 (查看上面配置文件),注意，一般要在`app.all()`设置

             ```
             response.setHeader('Access-Control-Allow-Headers','*');
             ```

         2.  应用场景：对身份进行认证

13.  **JSON -AJAX案例**

     1.  服务端，使用ExpressJS

         ```js
         //1.引入express
         const express = require('express');
         
         //创建应用对象
         const app = express();
         
         //3.创建路由规则
         // request - 对请求报文的封装； response - 对响应报文的封装
         app.all('/json-server',(request,response)=>{
             response.setHeader('Access-Control-Allow-Origin','*');
             response.setHeader('Access-Control-Allow-Headers','*');
             //响应数据
             const data={
                 name:'atguigu'
             };
             //对对象进行字符串转换
             let str = JSON.stringify(data);
             //response.send('Hello Ajax JSON'); //send()只能接收字符串或者buffer类型，不能接收对象
             response.send(str);
         });
         
         //监听端口启动五福
         app.listen(8000,()=>{
             console.log("服务已经启动，8000端口监听中")
         })
         ```

     2.  html代码

         ```js
         const result = document.getElementById('result');
         //绑定按键盘按下事件
         window.onkeydown = function() {
             //发送请求
             const xhr = new XMLHttpRequest();
             //设置响应体数据的类型
             xhr.responseType = 'json';
             //初始化
             xhr.open('GET','http://127.0.0.1:8000/json-server');
             //发送
             xhr.send();
             //事件绑定
             xhr.onreadystatechange=function(){
                 if(xhr.readyState===4){
                     if(xhr.status>=200 & xhr.status<300){
                         // result.innerHTML = xhr.response;
                         //数据接收的是字符串形式
                         //1. 手动转换方式 没有前面的xhr.responseType
                         let data = JSON.parse(xhr.response);
                         console.log(data);
                         //2. 自动转换，通过设置xhr.responseType='json'
                         result.innerHTML=xhr.response.name;
                         //result.innerHTML=xhr.response; //得到[object Object]
                     }
                 }
             }
         }
         ```

14.  **nodemon**

     1.  一款针对node.js开发的自动重启软件（包）。当有文件变化时自动检测。
         1.  比如ExpressJS更改配置时需要手动重启，使用nodemon其可自动重启。

     2.  安装命令：`npm install -g nodemon`
     3.  通过nodemon运行服务器：`nodemon server.js`

15.  **IE缓存问题**

     1.  IE会对Ajax返回结果进行缓存。

     2.  当有新的请求时，会优先从缓存读取。

         ```js
         <const btn = document.getElementsByTagName('button')[0];
         const result =document.querySelector('#result');
         btn.addEventListener('click',function(){
             const xhr = new XMLHttpRequest();
             xhr.open("GET",'http://127.0.0.1:8000/ie');
             //解决缓存办法
             xhr.open("GET",'http://127.0.0.1:8000/ie?t='+Date.now());
             xhr.send();
             xhr.onreadystatechange = function(){
                 if(xhr.readystate === 4){
                     if(xhr.status >= 200 && xhr.status<300){
                         result.innerHTML = xhr.response;
                     }
                 }
             }
         })
         ```

16.  **请求超时与网络异常**

     1.  项目遇到网络超时请求时，即服务器无法及时响应。

         ```js
         //ExpressJS - server.js
         app.get('/delay',(request,response)=>{
             //设置响应头，设置允许跨域
             response.setHeader('Access-Control-Allow-Origin','*');
             setTimeout(()=>{ //延时响应
                 response.send('延时响应')
             },3000);
         })
         ```

     2.  html页面

         ```js
         <const btn = document.getElementsByTagName('button')[0];
         const result =document.querySelector('#result');
         btn.addEventListener('click',function(){
             const xhr = new XMLHttpRequest();
             //超时设置2s 设置
             xhr.timeout = 2000; //Network下请求的delay路径的'Status'会从'(pending)'变成'canceled'
             //超时回调 
             xhr.ontimeout = function(){
                 alert('网络异常，请稍后重试!');//实际应用一般用div或者遮罩层提示
             }
             //网络异常回调
             xhr.onerror= function(){
                 alert('你的网络似乎出现了一些问题！'); //测试可以在Network下的'no throttling'模拟成'offline'
             }
             xhr.open("GET",'http://127.0.0.1:8000/delay');
             xhr.send();
             xhr.onreadystatechange = function(){
                 if(xhr.readystate === 4){
                     if(xhr.status >= 200 && xhr.status<300){
                         result.innerHTML = xhr.response;
                     }
                 }
             }
         })
         ```

17.  **Ajax的取消请求**

     1.  代码

         ```js
         const btns = document.querySelectorAll('button');
         let x=null;
         btns[0].onclick = function(){
             x = new XMLHttpRequest();
             x.open('GET','http://127.0.0.1:8000/delay');
             x.send();
         }
         //abort取消请求
         btns[1].onclick =function(){
             x.abort();//不能直接使用x，因为x没有在btns[0]函数中，需要在全局声明变量
         }
         ```

18.  **Ajax请求重复发送问题**

     1.  每次点击时都会创建一次请求

         ```js
         const btns = document.querySelectorAll('button');
         let x=null;
         //标识变量
         let isSending = false; //标识是否正在发送AJAX请求。
         
         btns[0].onclick = function(){
             if(isSending) x.abort(); //如果正在发送，则取消该请求，创建一个新请求
             x = new XMLHttpRequest();
             //修改标识变量的值
             isSending=true;
             x.open('GET','http://127.0.0.1:8000/delay');
             x.send();
             x.onreadystatechange = function(){
                 if(x.readyState ===4){ //发送请求成功
                     //修改标识变量
                     isSending =false;//此处不需要再加状态码判断，无论请求成功失败
                 }
             }
         }
         ```

19.  引入说明

     ```js
     <script crossorigin="anonymous" src='...'>
     <!--anonymous意思是，向src中的js发送请求时，不会携带域名中的cookie。通常CDN中使用。-->
     ```

     1.  

## jQuery中的Ajax

1. get请求

   ```js
   $.get(url,[data],[callback],[type])
   ```

   1. url：请求的URL地址

   2. data：请求携带的参数。

   3. callback：载入成功时回调函数。

   4. type：设置返回内容格式，xml，html，script，json，text，_default。

      ```js
      $('button').eq(0).click(function(){
          $.get('http://127.0.0.1:8000/jq-server',{a:100,b:200},function(data){
              console.log(data);//data为响应体  
              //此处输出对象
          }，'json'); //json为响应体类型
      })
      //Network下的Name：jq-server?a=100&b=200
      ```

   5. 

2. post请求

   ```js
   $.post(url,[data],[callback],[type]);
   ```

   1. url：请求的URL地址

   2. data：请求携带的参数

   3. callback：载入成功时回调函数。

      ```js
      //多个btn时
      $('button').eq(1).click(function(){
          $.post('http://127.0.0.1:8000/jq-server',{a:100,b:200},function(data){
              console.log(data);//data为响应体 
              //没有设置响应体类型，此处响应体为字符串
          })
      })
      //Network下的Name：jq-server
      //携带的参数在Form Data中
      ```

3. 通用ajax请求

   ```js
   $('button').eq(2).click(function(){
       $.post({
           //url
           url:'http://127.0.0.1:8000/jq-server',
           //[参数]
           data:{a:100,b:200},
           //请求类型
           type:'GET',
           //[响应体设置]
           dataType:'json',
           //[成功的回调]
           success:function(data){
               console.log(data);//响应体
           },
           //[超时时间]
           timeout:2000,
           //[失败的回调]
           error:function(){
               console.log("出粗了");
           },
           //头部信息
           headers:{
               c:300, //自定义，需要在服务器设置
               d:400
           }
      })
   })
   ```

## Axios

1. 使用方式一：npm安装或者其他安装方式（bower,yarn）, `npm install axios`

2. 使用方式二：CDN使用。

3. **Axios的GET和POST**方式发送请求

   ```js
   const btns= document.querySelectorAll('button');
   //[配置 baseURL]
   axios.defaults.baseURL = 'http://127.0.0.1:8000';
   
   btns[0].onclick=function(){
       //Axios GET请求： axios#get(url[,config])
       axios.get('/axios-serer',{
           //[url参数]
           params:{
               id:100,
               vip:8
           },
           //[请求头信息]
           headers:{
               name:'atguigu',
               age:20
           }
       }).then(value=>{ //axios返回的时promise对象
           console.log(value); //包含data数据，headers，request，响应代码，响应字符串等
       });
   }
   btns[1].onclick = function(){
       //Axios post请求：axios#post(url[,data[,config]])
       axios.post('/axios-server',{
           //请求体
           username:'admin',
           password:'admin'
       },{
           //url
           params:{
               id:200,
               vip:9
           },
           //请求头参数
           headers:{
               height:180,
               weight:180
           }
       })
   }
   ```

4. **Axios通用方法**

   ```js
   btns[2].onclick=function(){
               //axios通用方法
               axios({
                   //请求方法
                   method:'POST',
                   //url
                   url:'/axios-server',
                   //url参数
                   params:{
                       vip:10,
                       level:30
                   },//头部信息
                   headers:{
                       a:100,
                       b:100
                   },
                   //请求体参数
                   data:{
                       username:'admin',
                       password:'admin'
                   }
               }).then(response=>{
                   console.log(response);
               })
           }
   ```

## Fetch函数发送Ajax请求

1. `fetch()` 是全局对象，用于发起获取资源的请求，返回的是一个promise，这个promise会在请求响应后被resolve，并传回Response对象。

   ```js
   const btn=document.querySelector('button');
   btn.onclick=function(){
       //Promise<Response> fetch(input[, init]);
       fetch('http://127.0.0.1:8000/server?vip=10',{
           //请求方法
           method:'POST',
           //请求头
           headers:{
               abc:100
           },
           //请求体
           body: 'username=admin&password=123',//可以不同形式参数
       }).then(response=>{
           return response.text(); //若服务端返回的是字符串
           return response.json(); //若服务端返回的是json
       }).then(response=>{
           console.log(response);
       });
       //
       fetch(..).then(response=>{
           console.log(response); //不能直接获取响应体信息，需要用上面两次调取
       })
   }
   ```

2. `fetch()`较少使用。

## 同源策略

1. 同源策略 Same-Origin Policy，是浏览器的一种安全策略。

2. **同源**：**协议、域名、端口号**，必须完全相同。

3. 违背同源策略的就是**跨域**。

4. **Ajax必须满足同源策略，才能发送请求**。

5. 同源策略案例：

   1. 服务器Express： server-page.js

      ```js
      const express = require('express');
      const app = express();
      
      app.get('/home',(request,response)=>{
          //响应一个页面
          response.sendFile(__dirname + '/6-index.html')
      });
      app.get('/data',(request, response)=>{
          response.send('用户数据');
      })
      
      app.listen(9000, ()=>{
          console.log("服务器已经启动");
      })
      ```

   2. 用户根据地址访问：`127.0.0.1/home`获取到的页面`6-index.html`来自同个服务器

      ```html
      <h1>同源与跨域</h1>
      <button>点击获取用户</button>
      <script>
          //页面中的Ajax
          const btn = document.querySelector('button');
          btn.onclick=function(){
              const x = new XMLHttpRequest();
              //这里满足同源策略，即页面资源为6-index.html是来自server-page.js本地服务器的同端口9000,且用户数据也要来自同服务器，同端口号
              // 所以url可以简写
              x.open('GET','/data');
              //发送
              x.send();
              x.onreadystatechange=function(){
                  if(x.readyState===4){
                      if(x.status>=200 && x.status<300) {
                          console.log(x.response); //得到的数据 
                      }
                  }
              }
          }
      </script>
      ```

6. **解决跨域**：

   1. JSONP - JSON with Padding，是一个**非官方**的跨域解决问题，只支持`get`请求。

   2. 网页中有些标签天生具有跨域能力，如：`img`，`link`，`iframe`，`script`

   3. JSONP就是利用script标签的跨域能力来发送请求的。

      ```js
      // JSONP 使用
      //1. 动态的创建一个script标签
      var script = document.createElement("script");
      //2. 设置script的src，设置回调函数
      //说明：Express服务器返回的是函数调用
      script.src = "http://localhost:3000/checkusername";
      //3. 将script插入到文档中
      document.body.appendChild(script);
      ```

   4. JSONP理解：动态添加`<script>`标签

      ```js
      //jsonp服务
      app.all('/jsonp-server',(request,response)=>{
           response.send('hello jsonp-server'); //输出字符串 
          //前端使用<script src='localhost/jsonp-server'>无法识别返回的内容（非JS代码），报错
          
          response.send('console.log("hello jsonp")');   //返回js代码可在console输出
          
          const data = {
              name:'尚硅谷'
          };
          //将数据转化为字符串
          let str = JSON.stringify(data);
          //返回结果
          response.end(`handle(${str})`);
              //返回结果为函数调用：`handle(${str})`， 函数参数str为实际返回给客户端的数据（前端必须提前声明该函数）
              // end()不会增加特殊响应头
      });
      ```

   5. CTRL + F5： 强制刷新。

7. jQuery 发送 JSONP请求（跨域请求）

   1. 前端代码

      ```js
      $('button').eq(0).click(function(){
          //$.getJSON('[url]?callback=?',Fn)
          //此处的?会默认生成一个值（随机函数名），只需要后端返回相同值并且传入参数即可,XX(xx)
          $.getJSON('http://127.0.0.1:8000/jquery-json-server?callback=?',function(data){
             //console.log(data); 
              $('#result').html(`
              	名称：${data.name}<br>
              	小区：${data.city}
              `)
          });
      })
      ```

   2. 后端服务器代码

      ```js
      app.all('/jquery-jsonp-server',(request,response)=>{
          const data = {
              name:'尚硅谷',
              city:['北京','上海']
          };
          //将数据转化为字符串
          let str = JSON.stringify(data);
          //接收callback参数 , cb即jquery的问好？的值
          let cb = requuest.query.callback;
          //返回结果（方法调用并传参）
          response.end(`${cb}(${str})`)
      });
      ```

8. **跨域请求另一种解决方案 CORS**

   1. CORS - Cross-Origin Resource Sharing， 跨域资源共享。

   2. CORS 是**官方**的跨域解决方案，其特点是不需要在客户端做任何特殊的操作，完全在服务器中进行处理，支持`get`和`post`请求。

   3. 跨域资源共享标准新增了一组HTTP首部字段，允许服务器声明哪些源站通过浏览器有权限访问哪些资源。

   4. CORS 如何工作的：通过设置一个响应头`Access-Control-Allow-Origin`来告诉浏览器，该请求允许跨域，浏览器接收到该响应以后就会对响应放行。

   5. CORS 的使用：（参考火狐说明，关键字Access-Control-CORS）

      1. 主要是服务器端的设置（常默认允许三个，Origin、Methods、Headers）

         ```js
         router.get("/testAJAX",function(req,res){
             response.setHeader("Access-Control-Allow-Origin","http://127.0.0.1:5000");//限制网页和端口
             response.setHeader("Access-Control-Allow-Origin","*");//允许所有URL进行跨域
             response.setHeader("Access-Control-Allow-Methods","*");//默认允许HTTP的get和post，若需要put等其他，需要允许
             response.setHeader("Access-Control-Allow-Headers","*");//自定义头信息参数
             response.send('hello CORS');
         })
         ```

      2. 前端AJAX正常写法







































