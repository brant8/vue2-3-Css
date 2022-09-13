# 黑马Node.js

## 初识node.js

1. Javascript组成：JS核心语法（变量、循环、作用域等）、WebAPI（DOM、BOM、Ajax）
2. 浏览器展示效果 = 待执行的JS代码 -> **JavaScript解析引擎**
3. 不同浏览器使用不同JavaScript引擎，与内置API构成了JavaScript的运行环境。
4. 后端开发：Java、Python、PHP、Node.js
5. Node.js是基于Chrome V8引擎的JavaScript运行环境。
   1. 内置API：fs、path、http、JS内置对象、queryString等
   2. 浏览器是JS的前端运行环境。
   3. Node.js是JS后端运行环境。

6. **Node.js无法调用DOM和BOM等浏览器内置API**。
7. Node.js作为JavaScript的运行环境，仅仅提供了基础的功能和API，基于Node.js的工具和框架可以提供多种功能。
   1. 基于Express框架，可以快速构建Web应用。
   2. 基于Electron框架，可以构架跨平台的桌面应用。
   3. 基于restify框架，可以快速构建API接口项目。
   4. 读写和操作数据库、创建实用的命令工具辅助前端开发等。

8. Node.js学习路径：JS基础、Node内置API（fs、path、http等）、第三方API模块（express、mysql等）
9. Node.js版本区别
   1. LTS：长期稳定版
   2. Current：新特性尝试版

10. 使用：`node xx.js`（当前js文件夹路径）
11. CMD旧版Windows终端，PowerShell新版Windows终端。

## fs文件系统模块

1. `fs` 模块是Node.js官方提供的、用来操作文件的模块。包含了一些列的方法和属性。*安装Node.js时`fs`模块已经自动安装*。

   1. 比如：`fs.readFile()`，用来读取指定文件中的内容。
   2. 比如：`fs.writeFile()`，用来向指定的文件中写入内容。

2. **使用fs模块**

   ```js
   //导入
   const fs = require('fs');
   ```

3. `fs.readFile(path[, options], callback)`

   1. path：必选参数，字符串，表示文件路径

   2. options：可选参数，表示以什么编码格式来读取文件

   3. callback：必选参数，文件读取完成后，通过回调函数拿到读取的结果。

      ```js
      const fs = require('fs');
      fs.readFile('./files/11.txt','utf8',function(err,dataStr){
          console.log(err); //读取成功err值为null，读取失败此err为错误对象。
          console.log('-----');
          console.log(dataStr);//读取失败dataStr值为undefined
      })
      fs.readFile('./files/11.txt','utf8',function(err,dataStr){
          if(err){
              return console.log('读取文件失败！' + err.message);
          }
          console.log("读取文件成功" + dataStr);
      })
      ```

4. `fs.writeFile(file, data[, options], callback)`

   1. file：必选参数，指定一个文件路径的字符串，表示文件的存放路径。

   2. data：必选参数，表示要写入的内容。

   3. options：可选参数，表示以什么格式写入文件内容，默认值时utf8.

   4. callback：必选参数，文件写入完成后的回调函数。

      ```js
      const fs = require('fs');
      fs.writeFile('./files/2.txt','abcd',function(err){
          console.log(err);//如果文件写入成功，err的值为null。
      })
      ```

5. 案例：考试成绩整理

   ```js
   //score.txt： A=100 B=90 C=69 D=89
   const fs = require('fs');
   fs.readFile('score.txt','utf8',function(){
       if(err){
           return console.log("读取失败"+err.message);
       }
       console.log('读取文件成功'+dataStr);
       //1. 处理数据，按照空格进行分割
       const arrOld = dataStr.split(' ');
       //2. 循环分割后的数组，每一项数据，进行字符串替换操作 等号替换成冒号
       const arrNew = [];
       arrOld.forEach(item=>{
           arrNew.push(item.replace('=',':'));
       })
       //3.把数组中的每一项，进行合并，得到一个新的字符串
       const newStr = arrNew.join('\r\n'); //每个数据一行
   })
   
   ```

6. **fs模块 - 路径动态**拼接问题

   1. 使用fs模块操作文件时，如果提供的操作路径是以`./`或`../`开头的相对路径时，很容易出现路径动态拼接错误的问题。

   2. 原因：代码在运行的时候，会执行**node命令时所处的目录**，动态拼接出被操作文件的完整路径。

      ```js
      // PS C:\Users\win10pure\Desktop\otherDoc\nodeJS> node .\05-demo.js
      // 路径拼接式，自动拼接：‘C:\Users\win10pure\Desktop\otherDoc\nodeJS\05-demo.js’
      // 文件操作目录自动拼接：‘C:\Users\win10pure\Desktop\otherDoc\nodeJS\1.txt’
      ```

   3. `__dirname`：表示**当前文件所处的目录**

7. **path路径模块**

   1. path模块式Node.js官方提供的、用来处理路径的模块。

   2. 在JavaScript中，使用path模块来处理路径，需要导入。

      ```js
      const path = require('path');
      ```

   3. `path.join()`：用来将多个路径片段拼接成一个完整的路径字符串

      1. `path.join([...paths])`：路径片段的序列
      2. 返回值：`<string>`

      ```js
      const pathStr = path.join('/a','/b/c','../','./d','e');
      console.log(pathSt);//输出：\a\b\d\e
      const pathStr2 = path.join(__dirname, './files/1.txt');
      console.log(pathStr2); //输出：当前文件所处目录\files\1.txt
      ```

   4. 注意：涉及路径拼接的操作，都要使用`path.join()`进行处理，不要直接使用`+`进行字符串的拼接。

   5. `path.basename()`：获取路径中的最后一部分，通常通过这个方法获取路径中的文件名。

      1. `path.basename(path[, ext])`
      2. path：必选参数，表示一个路径的字符串
      3. ext：可选参数，表示文件扩展名
      4. 返回值：`<string>`表示路径中的最后一部分

      ```js
      const fpath = '/a/b/c/index.html';
      var fullName = path.basename(fpath);
      console.log(fullName); //输出：index.html
      var nameWithoutExt = path.basename(fpath, '.html');
      console.log(nameWithoutExt); //输出 index
      ```

   6. `path.extname()`：获取路径中的文件扩展名

      1. `path.extname(path)`：path必选参数，表示路径字符串
      2. 返回值：得到的扩展名字符串

      ```js
      const fpath = '/a/v/c/index.html';
      const fext = path.extname(fpath);
      console.log(fext);//输出：.html
      ```

   7. 案例：拆分时钟代码

      ```js
      //要求：把style、script和div拆分到不同文件下（使用正则）
      //1.导入
      const fs = require('fs');
      const path = require('path');
      //2.匹配<style></style>标签的正则 ； \s表示空白字符；\S表示非空白字符； *表示匹配任意次
      const regStyle = /<style>[\s\S]*<\/style>/;
      //3.匹配<script></script>标签的正则 ； .匹配换行符、回车符、行分隔符和字段分隔符除外的字符
      const regScript = /<script>[\s\S]*<\/script>/;
      //4.读取需要被处理的HTML文件
      rs.readFile(path.join(__dirname,'index.html'),'utf8',(err,dataStr)=>{
          if(err){
              return console.log("读取HTML文件失败！ 错误信息：" + err.message);
          }
          //读取HTML文件成功后，调用对应的方法，拆解出css、js和html文件
          resolveCSS(dataStr);
          resolveJS(dataStr);
          resolveHTML(dataStr);
      });
      //处理css样式
      function resolveCSS(htmlStr){
          //1.使用正则提取<style>标签
          const r1 = regStyle.exec(htmlStr); //返回值为数组
          //2.将提取出来的样式字符串，做进一步处理
          const newCSS = r1[0].replace('<style>','').replace('</style>','');
          //3.将提取出来的css样式，写入到index.css文件中
          fs.writeFile(path.join(__dirname, 'index.css'),newCSS, err=>{
              if(err) return console.log('写入CSS错误',+err.message);
              console.log('写入CSS成功');
          })
      }
      //处理JS脚本
      function resolveJS(htmlStr){
          //1.使用正则提取页面<script>标签
          const r2 = regScript.exec(htmlStr);
          //2.将提取出来的脚本字符串，做进一步处理
          const newJS = r2[0].replace('<script>','').replace('</script>','');
          //3.将提取出来的js脚本，写入到index.js文件中
          fs.writeFile(path.join(__dirname,'./clock/index.js'),newJS,err=>{
              if(err) return console.log('写入 JavaScript脚本失败' + err.message);
              console.log('写入 JS脚本成功');
          })
      }
      //处理HTML
      function resolveHTML(htmlStr){
          //1.使用replace方法，提取页面style和script标签，替换为外联的style和script标签
          htmlStr.replace(regStyle,'<link rel="stylesheet" href="./index.css" />').replace(regScript,'<script src="./index.js" />');
          //2,写入index.html文件
          fs.writeFile(path.join(__dirname),'./clock/index.html'),newHTML,function(err){
              if(err) return console.log('写入HTML文件失败' +err.message);
              console.log('写入HTML文件成功');
          });
      }
      ```

      1. `RegExpObject.exec(string)`：正则
         1. 用于检索字符串中的正则表达式的匹配。
         2. 返回值：返回一个数组，其中存放匹配的结果。如果未找到匹配，则返回值为null。
         3. 返回的数组第0个元素是正则表达式相匹配的文本，第1个元素是与RegExpObject的第1个子表达式向匹配的文本（如果有的话）。

   8. 注意：

      1. `fs.writeFile()`方法只能用来创建文件，**不能用来创建路径**。
      2. 重复调用`fs.writeFile()`写入同一个文件，新写入的内容会覆盖之前的旧内容

## HTTP模块

1. http模块，是Node.js官方提供的、用来创建web 服务器的模块。通过http 模块提供的`http.createServer()`方法，可以把一台普通电脑编程一台Web服务器。

   ```js
   const http = require('http');
   ```

2. 创建Web服务器的基本步骤

   ```js
   //1.导入http模块
   const http = require('http');
   //2.创建web服务器实例
   const server = http.createServer();
   //3.为服务器实例绑定request事件，监听客户端的请求
   server.on('request', (req,res) => {
       //只要有客户端来请求服务器，就会触发request事件，从而调用这个事件处理函数
       console.log('visited the web server...');
   })
   //4.启动服务器 、 调用server.listen(端口号，cb回调)，即可启动web服务器。
   server.listen(80, () => {
       console.log('http server is running ...')
   })
   ```

3. `req`请求对象

   1. 只要服务器接收到了客户端的请求，就会调用通过`server.on()`为服务器绑定的request事件处理函数。

   2. 如果想在事件处理函数中，访问与客户端相关的数据或属性，可以使用如下方式

      ```js
      server.on('request',(req)=>{
          //req是请求对象，包含了与客户端相关的数据和属性
          //例如：req.url - 客户端请求的URL地址(比如/、/index.html)； req.method - 客户端的method请求类型
          const str = `Your request url is ${req.url} and request method is ${req.method}`;
          console.log(str);
      });
      ```

4. `res`响应对象

   1. 在服务器request事件处理函数中，访问与服务器相关的数据或属性，可以使用如下方式

      ```js
      server.on('request',(req,res)=>{
          //res是响应对象，包含了与客户端相关的数据和属性
          //例如：要发送给客户端的字符串
          const str = `Your request url is ${res.url} and request method is ${res.method}`;
          // res.end()方法作用：向客户端发送指定的内容，并结束这次请求的处理过程
          res.end(str);
      });
      ```

   2. 中文乱码问题

      ```js
      server.on('request',(req,res)=>{
          //发送内容包含中文
          const str = `您的请求url是 ${res.url} ，请求的method类型是 ${res.method}`;
          // 为了防止中文显示乱码问题，需要设置响应头Content-Type的值为text/html；charset=utf-8
          res.setHeader('Content-Type','text/html;charset=utf-8');
          res.end(str);
      })
      ```

5. 根据不同url响应不同的html内容

   1. 核心实现步骤

      ```js
      //1.获取请求的url地址
      //2.设置默认的响应内容为404 Not Found
      server.on('request',function(req,res){
          const url.req.url;
          let content = '<h1>404 Not Found</h1>';
          if(url === '/' || url === 'index.html'){
              content = '<h1>首页</h1>';
          }else if(url === 'about.html'){
              content = '<h1>关于页面</h1>';
          }
          res.setHeader('Content-type','text/html;charset=utf-8');
          res.end(content);
      })
      ```

6. 服务器：充当的角色就是一个字符串的搬运工。

   1. 在浏览器中访问地址`xxx/clock`

   2. `/index.html`

   3. 把文件的实际存放路径，作为每个资源的请求url地址；直接把请求的url地址当作读取文件的路径。

   4. 磁盘根据每个文静的存放路径获取文件比如`/clock/index.html`

   5. 将读取到的文件内容（字符串）通过`res.end()`相应给客户端（web服务器）

      ```js
      //1.导入模块
      const http = require('http');
      const fs = require('fs');
      const path = require('path');
      //2.创建web服务器
      const server = http.createServer()
      //3.监听web服务器的request事件
      server.on('request',(req,res)=>{
          
      });
      //4.启动服务器
      server.listen(80, ()=>{
          console.log('server ruunning at http://127.0.0.1');
          //5.获取到客户端请求的url地址 xxx/clock/index.html
          const url = req.url
          //6.把请求的url地址映射为具体文件的存放路径
          const fpath = path.join(__dirname, url);
          //5.2使用非完整路径 url地址 xxx/index.html
          let fpath = '';
          if(url === '/'){
              //5.3如果请求的路径是否为 / ，则手动指定问价的存放路径
              fpath = path.join(__dirname, './clock/index.html');
          }else{
              //5.4如果请求的路径不为 /， 则动态拼接文件的存放路径
              fpath = path.join(__dirname,'./clock',url);
          }
          //7.根据映射过来的文件路径读取文件内容
          fs.readFile(fpath,'utf8',(err,dataStr))=>{
             //7.1读取失败，向客户端响应固定的错误消息
              if(err) return res.end('404 Not Found...');
              //7.2读取成功，将读取成功的内容，响应给客户端
              res.end(dataStr);
          });
      })
      ```

## 模块化

1. Node.js根据模块来源不同，分为三大类。

2. **内置模块**：内置模块是由Node.js官方提供的，例如fs、path、http等。

3. **自定义模块**：用户创建的每个`.js`文件，都是自定义模块。

4. **第三方模块**：由第三方开出来的模块，并非官方提供的内置模块，也不是用户创建的自定义模块，使用前需要先下载。

5. 加载模块

   1. 使用强大的`require()`方法，可以加载需要的模块进行使用。

      ```js
      //内置模块
      const fs = require('fs')
      //自定义模块(需要路径)
      const custom = require('./custom.js');
      //第三方
      const moment = require('moment')
      ```

6. 模块作用域

   1. 和函数作用域类似，在自定义模块中定义的变量、方法等成员，只能在当前模块内被访问，这种模块级别的访问限制，叫做模块作用域。
   2. 好处：防止了全局变量污染的问题

7. 向外共享模块作用域中的成员

   1. module对象：在每个`.js`自定义模块中都有一个Module 对象，里面存储了和当前模块有关的信息。

      ```js
      console.log(module); 
      //Module{id:xx,path:xx,exports:{},filename:xx,..}
      ```

8. **`module.exports`对象**

   1. 在自定义谋爱中，可以使用module.exports对象，将模块内的成员共享出去，供外界使用。

   2. 外界用`require()`方法导入自定义模块时，得到的就是`module.exports`所指向的对象。

      ```js
      //11demo.js 当为空文件时，默认 module.exports={}
      //12demo.js 
      //外界使用require导入自定义模块得到的成员，就是哪个模块中，通过module.exports指向的那个对象
      const m = require('11demo.js');
      console.log(m); //输出：{} 空对象，即module.exports为空
      
      //11demo.js
      //向module。exports对象上挂载username属性
      module.exports.username = 'zs';
      //向module.exports对象上挂载sayHello方法
      module.exports.sayHello=function(){
          console.log("hello")
      }
      //12demo.js
      const m = require('11demo.js');
      console.log(m); //输出： {username:'zs', sayHello:[Function]}
      ```

   3. 共享成员时的注意点：

      1. 使用`require()`方法导入模块时，导入的结果，永远以`module.exports`指向的对象为准。

         ```js
         module.exports.username = 'zs';
         module.exports.sayHello=function(){
             console.log("hello");
         }
         //让module.exports指向一个全新的对象
         module.exports = {
             nickname:'小黑',
             sayHi(){
         		console.log('HI');
             }
         }
         ```

9. **`export`对象**

   1. 由于module.exports 单词写起来比较复杂，为了简化对外共享成员的代码，Node提供了`exports`对象。 默认情况下，`exports`和`module.exports`指向同一个对象。**最终共享的结果，还是以module.exports 指向的对象为准**。

10. `exports`和`module.exports`的使用误区

    1. 谨记：`require()`模块，得到的永远是`mdule.exports`指向的对象。[情景1](https://github.com/brant8/vue2-3-Css/blob/main/pictures/nodejs_exports_1.png) | [情景2](https://github.com/brant8/vue2-3-Css/blob/main/pictures/nodejs_exports_2.png) | [情景3](https://github.com/brant8/vue2-3-Css/blob/main/pictures/nodejs_exports_3.png) |  [情景4](https://github.com/brant8/vue2-3-Css/blob/main/pictures/nodejs_exports_4.png)
    2. 注意：为了防止混乱，不建议在同一个模块中同时使用`exports`和`module.exports`

11. Node.js遵循了CommonJS模块化规范，CommonJS规定了模块的特性和各模块之间如何相互依赖。

12. **CommonJS规定**

    1. 每个模块内部，module变量代表当前模块。
    2. module变量时一个对象，它的`export`属性（即`module.exports`）时对外的接口。
    3. 加载某个模块，其实是加载该模块的module.exports属性。`require()`方法用于加载模块。

## NPM与包

1. Node.js 中的第三方模块又叫做 包。

2. Node.js中的包都是免费开源的。

3. 包是基于内置模块封装出来的，提供了更高级、更方便的API，极大的提高了开发效率。

   1. 包和内置模块之间的关系，类似于jQuery和浏览器内置API之间的关系。

4. 全球最大的包共享平台：www.npmjs.com （用于搜索包）

   1. npm,Inc公司提供了地址：registry.npmjs.org的服务器（用于下载包），对外共享所有的包。

5. 包管理工具：Node Package Manager - npm包管理工具，此包*随着Node.js的安装包一起被装到了电脑上*。

6. 查看npm包版本：`npm -v`

7. npm体验案例：格式化事件

   1. 使用npm包管理工具，在项目中安装格式化时间的包 moment。

   2. `npm i moment`

   3. 使用 require() 导入格式化时间的包。

   4. 参与moment 官方API文档对事件进行格式化。

      ```js
      const moment = require('moment');
      const dt = moment().format('YYYY-MM-DD HH:mm:ss')
      ```

   5. 非引用第三方包写法

      ```js
      //15demo.js
      //格式化时间
      function dateFormat(dtStr){
          const dt = new Date(dtStr);
          const y = dt.getFullYear();
          const m = padZero(dt.getMonth() + 1); //月份
          const d = padZero(dt.getDate()); //日期
          const hh = padZero(dt.getHours());
          const mm = padZero(dt.getMinutes());
          const ss = padZero(dt.getSeconds());
          return `${y}-${m}-${d} ${hh}:${mm}:${ss}`;
      }
      //补零函数
      function padZero(n){
          return n>9 ? n : '0'+n;
      }
      module.exports = {
          dateFormat
      }
      //test.js
      const TIME = require('./15demo.js');
      //调用方法，进行时间格式化
      const dt = new Date();
      const newDT = TIME.dateFormat(dt);
      console.log(dt);
      console.log(newDT);
      ```

8. npm安装包命令：`npm install 包的完整名称`  （项目中）

   1. 或者 `npm i 包名`
   2. 安装指定包版本使用`@`：`npm i moment@2.10.0`

9. 初次装包后多的文件

   1. `node_modules`文件夹 和 `package-lock.json`配置文件。
   2. `node_modules`文件夹用来存放所有已安装到项目中的包。
   3. `require()`导入第三方包时，从`node_modules`目录中查找并加载包。
   4. `package-lock.json`配置文件用来记录 `node_modules`目录下的每一个包的下载信息。

10. 包的语义化版本规范

    1. 包的版本号以 '点分十进制' 形式进行定义，总共三位数字。
    2. 第一位数字：大版本。
    3. 第二位数字：功能版本，底层不变。
    4. 第三位数字：Bug修复版本。
    5. 版本号提升规则：前面的版本号增长了，后面的的版本号归零。

11. 包管理配置文件

    1. npm规定：在项目根目录中，**必须**提供一个叫做`package.json`的包管理配置文件，用来记录与项目有关的一些配置信息。
       1. 项目名称、版本号、描述等
       2. 项目中用到了哪些包
       3. 哪些包旨在开发期间会用到
       4. 哪些包在开发和部署时需要用到
    2. 多人协作：使用github上传时，可以在`package.json`配置包名，从而在上传github时可以提出`node_modules`目录。
    3. 注意：在项目开发中，一定要把`node_modules`文件夹，添加到`.gitignore`忽略文件中。

12. **快速创建`package.json`**

    1. npm包管理工具提供一个快捷命令，可以在执行命令时所处的目录中，快速创建配置文件
    2. 快速创建命令：`npm init -y`
    3. 注意：快速创建命令只能在英文目录下成功运行。不能出现空格。
    4. 运行 npm install 命令安装包时，npm 包管理工具会自动把包的名称和版本号，记录到package.json中。

13. 









































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







































