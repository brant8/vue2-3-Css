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

## NPM

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
    2. **快速创建命令：`npm init -y`**
    3. *注意*：快速创建命令只能在英文目录下成功运行。不能出现空格。
    4. 运行 npm install 命令安装包时，npm 包管理工具会自动把包的名称和版本号，记录到package.json中。

13. **`dependencies`节点**

    1. 专门用来记录使用`npm install`命令装过哪些包及版本号。
    2. 装多个包：`npm i jquery art-template` 使用空格

14. 一次性安装所有的包

    1. 当剔除了node_module文件夹以后，需要下载所有的包才能运行起来。
    2. **安装所有包命令：`npm install`**
    3. 该命令执行时，npm包管理工具会先读取package.json中的dependencies节点，读取依赖包和版本后，npm会一次性下载到项目中。

15. 卸载包

    1. 运行命令：`npm uninstall moment`
    2. 卸载成功后，会自动从package.json中的dependencies删掉。

16. **`devDependencies`节点**

    1. 如果某些包旨在项目开发阶段会用到，在项目上线之后不会用到，则建议把这些包记录到devDependencies节点中。
    2. 某些包在开发和项目上线之后都需要用到的，把其记录到dependencies节点中。
    3. **安装命令：`npm i 包名 -D`** ；安装包到devDependencies节点中
    4. 完整形式：`npm install 包名 --save-dev`
    5. 如何判断是否安装在开发阶段：查看 npmjs.com 该包的安装命令，通常安装命令直接给出是否`-D`

17. 切换npm的下包**镜像源**

    1. 查看当前的下包镜像源：`npm config get registry`
    2. 将下包的尽享元切换为淘宝镜像源：`npm config set registry=https://registry.npm.taobao.org/`
    3. 检查镜像源是否下载成功：`npm config get registry`

18. nrm工具

    1. 为了方便切换下包镜像源，可以安装`nrm`小工具，利用nrm提供的终端命令，可以快速查看和切换下包镜像源。
    2. 安装命令：`npm i nrm -g` ；
    3. 查看所有可用的镜像源：`nrm ls`
    4. 将下载包的镜像源切换为淘宝镜像：`nrm use taobao`

## 包

1. 包的分类：项目包、全局包。

2. 项目包：被安装到项目`node_modules`目录中的包。

3. 项目包分两类
   1. 开发依赖包：记录到devDependencies节点中的包。关键字 `-D`
   2. 核心依赖包：记录到dependencies节点的包。一直会用到。

4. 全局包：`npm install 包 -g` ；参数 `-g`
   1. 全局包安装到`C:\Users\用户目录\AppData\Roaming\npm\node_modules`目录下
   2. 安装全局包：`npm i 包名 -g`
   3. 卸载全局包：`npm uninstall 包名 -g`

5. 只有**工具性质的包**，才有全局安装的必要性，因为其提供了好用的终端命令。
   1. 判断某个包是否需要全局安装后才能使用，可以参考官方提供的说明即可。
   2. 官方安装命令提示是否有 `-g`

6. 工具包之 `i5ting_toc`：
   1. `i5ting_toc`可以把md文档转为html页面的小工具
   2. 安装为全局包命令：`npm install -g i5ting_toc`
   3. 调用实现转换功能：`i5ting_toc -f 要转换的md文件路径 -o`

7. 规范的包结构
   1. 包必须以单独的目录而存在。
   2. 包的顶级目录下必须包含package.json这个包管理配置文件。
   3. package.json中必须包含name、version、main这三个属性，分别代表包的名字、版本号、包的入口。
   4. main属性：require指向加载的文件，比如`'main':'./moment.js'`

8. **案例：开发自己的包** 用于转义HTML标签

   1. 创建一个文件夹（文件夹名字无所谓）包含index.js、package.json、README.md三个文件。

   2. 初始化package.json（注意包名称需要唯一，可查看npmjs库搜一下创建对应的名字），[开源协议参考](https://www.jianshu.com/p/86251523e898)

      ```json
      {
        "name": "itheima-tools",
        "version": "1.0.0",
        "main": "index.js",
        "description": "提供了格式化时间，HTML Escape的功能 -- bbyo",
        "keywords": ["itheima","dataFormat","escape"],
        "license": "ISC"
      }
      ```

   3. 在index.js中定义格式化时间的方法

      ```js
      function dateFormat(dataStr){
          /**代码*/
      }
      //补零方法
      function padZero(n){ /**代码*/ } 
      //定义转义HTML字符函数
      function htmlEscape(htmlStr){
          return htmlStr.replace(/<|>|"|&/g,(match)=>{ //g表示全局匹配，即匹配多次/全部
              switch(match){
                  case '<':
                      return '&lt;';
                  case '>':
                      return '&gt;';
                  case '"':
                      return '&quot;';
                  case '&':
                      return '&amp;';
              }
           });
      }
      //定义还原HTML字符串
      function htmlUnEscape(str){
          return str.replace(/&lt;|&gt;|&quot;|&amp;/g,(match)=>{
              switch (match){
                  case '&lt;':
                      return '<';
                  case '&gt;':
                      return '>';
                  case '&quot;':
                      return '"';
                  case '&amp;':
                      return '&';
              }
          });
      }
      module.exports = {
          dateFormat,
          htmlEscape
      }
      ```

   4. 对代码进行测试，文件夹外创建 test.js，使用`node ./test.js`测试代码运行清空

      ```js
      const itheima = require('./itheima-tools/index');
      //const itheima = require('./itheima-tools'); //使用目录形式，默认指向index.js
      const dtStr = itheima.dateFormat(new Date());
      console.log(dtStr);
      
      const htmlStr = '<h1 title="abc">这是h1标签<span>123&nbsp;</span></h1>';
      const str = itheima.htmlEscape(htmlStr);
      console.log(str);
      
      let str2 = itheima.htmlUnEscape(str);
      console.log(str2);
      ```

   5. 模块拆分：

      1. 将时间格式化功能拆分到 src->dataFormat.js 中

      2. 将处理HTML字符串的功能，拆分到 src->htmlEscape.js 中

      3. 在index.js中，导入两个模块，得到需要向外共享的方法

      4. 在index.js中，使用module.exports把对应的方法共享出去

         ```js
         //index.js
         const date = require('./src/ddateFormat');
         const escape = requrie('./src/htmlEscape');
         //向外暴露需要的成员
         module.exports = {
             ...date, //es8中的展开对象、数组，date中多个到处的函数
             ...escape
         }
         ```

   6. 发布npm包

      1. 注册npm账号
      2. 在终端执行账号登陆命令：`npm login` ； 后输入登录信息
      3. npm登录注意：需要把下包的服务器地址切换为npm官方，否则发布失败。
      4. 切换目录到包的根目录，运行：`npm publish` ；即可发布到npm上。
      5. 注意：包名唯一，且包名不能大写。

   7. 删除已发布的包

      1. 运行：`npm unpublish 包名 --force` ； 从npm删除已发布的包
      2. `npm unpublish`命令只能删除72小时以内发布的包
      3. `npm unpublish`删除的包，在24小时内不允许重复发布

9. 模块：优先从缓存内加载

   1. 模块在第一次加载后会被缓存。这页意味着多次调用`require()`方法不会导致模块的代码被执行多次。
   2. 注意：不论是内置模块、用户自定义模块、还是第三方模块，都会优先从缓存中加载，从而提高模块的加载效率。

10. 内置模块加载机制

    1. 内置模块由Node.js 官方提供的模块，内置模块的加载优先级**最高**。
    2. 例如：`require('fs')`始终返回内置的fs模块，即使在node_modules目录下由名字相同的包也叫做fs。

11. 自定义模块加载机制

    1. 使用`require()`加载自定义模块时，必须指定以`./`或`../`开头的路径标识符。在加载自定义模块时，如果没有指定`./`或`../`这样的路径标识符，则node 会把它当作内置模块或第三方模块进行加载。
    2. 使用`require()`导入自定义模块时，如果省略了文件的扩展名，Node.js 会 按顺序 分别尝试加载以下文件
       1. 按照确切的文件名进行加载
       2. 补全`.js`扩展名进行加载
       3. 补全`.json`扩展名进行加载
       4. 补全`.node`扩展名进行加载
       5. 加载失败，终端报错

12. 第三方模块的加载机制

    1. 如果传递给`require()`的模块标识符不是一个内置模块，也没有以`./`或`../`开头，则Node.js会从当前模块的父目录开始，尝试从`/node_modules`文件夹中加载第三方模块。
    2. 如果没有找到对应的第三方模块，则移动到再上一层父目录中，进行加载，直到文件系统的根目录。
    3. 如果 在`C:\Users\itheima\project\foo.js`调用了`require('tools')`，则Node.js会按以下顺序查找
       1. `C:\Users\itheima\project\node_modules\tools`
       2. `C:\Users\itheima\node_modules\tools`
       3. `C:\Users\node_modules\tools`
       4. `C:\node_modules\tools`
       5. 找不到，报错

13. 目录作为模块

    1. 把目录作为模块标识符，传递给`require()`进行加载的时候，有三种加载方式
       1. 在被加载的目录下查找一个叫做package.json的文件，并寻找`main 属性`，作为`require()`加载入口
       2. 如果目录中没有`package.json`文件，或者`main`入口不存在或无法解析，则Node.js 将会视图加载目录下的index.js文件。
       3. 如果上述都失败，则Node.js会在终端打印错误消息，报告模块的缺失。`Error:Cannot Find module 'xxx'`


## Express

1. Express是基于Node.js平台，快速、开放、极简的Web开发框架。

2. Express的作用和Node.js内置的http模块类似，是专门用来创建Web服务器的。

3. http 内置模块用起来复杂，开发效率低；Express是基于内置的http模块进一步封装出来的，能够极大的提高开发效率。

4. 使用Express，前端服务器常见的：

   1. Web网站服务器：专门对外提供Web网页资源的服务器。
   2. API接口服务器：专门对外提供API接口的服务器。

5. 安装Express：`npm i express@4.17.1`

6. 创建基本Web服务器

   ```js
   //1.导入express
   const express = require('express');
   //2.创建web服务器
   const app = express();
   //3.其他操作，比如挂载路由
   //4.调用app.listen（端口号，启动成功后的回调函数），启动服务器
   app.listen(80, ()=>{
       console.log('express server running at http://127.0.0.1');
   })
   ```

7. 监听**GET请求**

   1. 通过`app.get()`方法，可以监听客户端的GET请求，具体如下

      ```js
      //参数1：客户端请求的URL地址
      //参数2：请求对应的处理函数
      //	req：请求对象（包含了与请求相关的属性和方法）
      //	res：响应对象（包含了与响应相关的属性与方法）
      app.get('请求URL',function(req,res){ /*处理函数*/ })
      ```

8. 监听**POST请求**

   1. 通过`app.post()`方法，可以监听客户端的GET请求，具体如下

      ```js
      //参数1：客户端请求的URL地址
      //参数2：请求对应的处理函数
      //	req：请求对象（包含了与请求相关的属性和方法）
      //	res：响应对象（包含了与响应相关的属性与方法）
      app.post('请求URL',function(req,res){ /*处理函数*/ })
      ```

9. 把内容**响应**给客户端

   1. 通过`res.send()`方法，可以把处理好的内容，发送给客户端。

      ```js
      app.get('/user',(req, res)=>{
          //向客户端发送JSON对象
          res.send({
              name:'zs',
              age:20
          });
      })
      app.post('/user',(res,req)=>{
          //向客户端发送文本内容
          res.send('请求成功');
      })
      ```

10. 获取URL中携带的查询参数

    1. 通过`req.query`对象，可以访问到客户端通过查询字符串的形式，发送到服务器的参数。

       ```js
       app.get('/',(req,res)=>{
           //通过req.query可以获取到客户端发送过来的 查询参数
           console.log(req.query);
           res.send(req.query);
       })
       //前端访问传递参数方式：http://127.0.0.1/?name=zs&age=20
       ```

11. 获取URL中的动态参数

    1. 通过req.params对象，可以访问到URL中，通过`:`匹配到的动态参数。

       ```js
       //URL地址中，可以通过:参数名的形式，匹配动态参数值
       app.get('/user/:id',(req,res)=>{ //此处的:id 是一个动态参数
          //req.params默认是一个空对象
          //里面存放着通过 ： 动态匹配到的参数值
          console.log(req.params);
       })
       //比如： http://127.0.0.1/user/1 
       //结果: { "id":"1" }
       ```

12. 托管静态资源 - `expess.static()`

    1. 通过`express.static()`，可以非常方便的创建一个*静态资源服务器*。

    2. 例如：通过下面代码可以将public目录下的图片、CSS文件、JavaScript文件对外开放

       ```js
       app.use(express.static('public')); 
       //即可以访问public目录中所有文件
       //访问： http://localhost:3000/images/bg.jpg
       ```

    3. 注意：Express在指定的静态目录中查找文件，并对外提供资源的访问路径，因此，存放静态文件的目录名不会出现在URL中。

    4. 若要托管多个静态资源目录，多次调用`express.static()`函数即可。

    5. 注意：多个静态托管时，同个名称文件，先托管的优先级高。

13. 挂载路径前缀 

    1. 若在托管的资源访问路径之前，挂载路径前缀，可以使用如下

       ```js
       app.use('/public',express.static('public'));
       //访问： http://localhost:3000/public/images/bg.jpg
       ```

14. 工具 - nodemon：能够监听项目的改动自动重启

    1. 安装命令：`npm install -g nodemon`
    2. 运行命令：`nodemon server.js`

15. Express - 路由

    1. Express中，路由指的是客户端的请求与服务器处理函数之间的映射关系。

    2. Express中的路由分3部分组成，分别是请求的类型、请求的URL地址、处理函数。

       ```js
       //格式
       app.METHOD(PATH, HANDLER)
       //比如
       app.get('/',function(req,res){
           res.send('hello World');
       })
       ```

    3. 每个请求到大服务器之后，需要先经过路由的匹配，只有匹配成功之后，才会调用对应的函数处理。

       1. 匹配时，会按照*路由的顺序*进行匹配，如果请求类型和请求的URL同时匹配成功，则Express会将这次请求，转交给对应的function函数进行处理。
       2. 注意路由顺序：即为代码`app.get('/',fn)`的先后顺序，先声明的代码先匹配。

16. 模块化路由

    1. 为了方便对路由进行模块化的管理，Express不建议将路由直接挂载到app上，而推荐将路由抽离为单独的模块。

    2. 步骤

       1. 创建路由模块对应的 .js文件
       2. 调用`express.Router()`函数创建路由对象
       3. 向路由对象上挂载具体的路由
       4. 使用`module.exports`向外共享路由对象
       5. 使用`app.use()`函数注册路由模块

       ```js
       //创建路由模块user.js
       var express = require('express'); //1.导入express
       var router = express.Router(); //2.路由对象
       
       router.get('/user/list',function(req,res){ //3.挂载具体的路由
           res.send('Get user list.');
       })
       router.post('/user/add',function(req,res){
           res.send('Add new user..');
       })
       
       module.exports = router //4.导出路由对象
       ```

    3. 注册路由模块

       ```js
       //app.js
       //..
       //1.导入路由模块
       const userRouter = require('./router/user.js');
       //2.使用app.user()注册路由模块
       app.use(userRouter);
       //..
       ```

    4. 注意：`app.use()`的作用，就是用来注册全局中间件的。

    5. 为路由模块添加前缀

       1. 类似于托管静态资源，为挂载访问前缀一样。

          ```js
          //导入路由模块
          const userRouter = require('./router/user.js');
          //挂载路由前缀
          app.use('/api',userRouter);
          //访问 http://127.0.0.1/api/user/add
          ```

17. Express中间件：Middleware，特指业务流程的中间处理环节。

    1. Express中间件调用流程

       1. 当一个请求到大Express服务器之后，可以连续调用多个中间件，从而对这次请求进行预处理。

    2. 格式：本质上是一个function处理函数

       ```js
       app.get('/',function(req,res,next){
           next(); //表示把流转关系转交给下一个中间件或路由
       })
       /*
       .get: 	 	HTTP method
       '/': 	 	Path(route) for which middleware function applies
       function: 	middleware function
       req:		http request to middleware function
       res:		http response to middleware function
       next:		callback argument to middlefunction
       */
       ```

    3. 注意：**中间件函数**的形参列表中，**必须包含next 参数**。而路由处理函数中只包含req和res。

18. **全局生效中间件**：客户端发起的任何请求，到达服务器之后，都会触发的中间件，叫做全局生效的中间件。

    1. 通过调用`app.use(中间件函数)`，即可定义一个全局生效的中间件

       ```js
       const mw = function (req,res,next){
           console.log('简单的中间件函数');
           next();
       }
       //全局生效的中间件
       app.use(mw);
       app.get('/'(req,res)=>{
           res.send('homepage');
       })
       ..
       ```

    2. 定义全局中间件的简化形式

       ```js
       app.use(function (req,res,next){
           console.log('简单的中间件函数');
           next();
       })
       ```

19. 中间件的作用

    1. 多个中间件之间，共享同一份`req`和`res`。基于这样的特性，可以在上游的中间件中，统一为req或res对象添加自定义的属性或方法，供下游的中间件或路由进行使用。

20. 定义多个全局中间件

    1. 可以使用`app.use()`连续定义多个全局中间件。

    2. 客户端请求达到服务器之后，会按照中间件的**先后顺序**依次进行调用。

       ```js
       app.use(function (req,res,next){
           console.log('调用第一个简单的中间件函数');
           next();
       })
       app.use(function (req,res,next){
           console.log('调用第二个简单的中间件函数');
           next();
       })
       ```

21. 局部生效中间件

    1. 不使用`app.use()`定义的中间件，叫做局部生效的中间件。

       ```js
       const mw1 = function(req,res,next){
           next();
       }
       //此中间件mw1 只在当前路由中生效，属于局部
       app.get('/',mw1,function(req,res){
           res.send('Home page');
       })
       //mw1不会影响下面的路由
       app.get('/user',function(req,res){ res.send('User page'); })
       ```

22. 定义多个局部中间件

    1. 可以在路由中，通过下面两种等价的方式，使用多个局部中间件。

       ```js
       //以下两种写法是完全等价的，可以根据喜好任意选择 ; 注意调用顺序
       app.get('/', mw1, mw2, (req,res) => { res.send('Home page'); })
       app.get('/',[mw1, mw2], (req,res) =>{ res.send('Home page'); })
       ```

23. 中间件使用注意事项

    1. 一定要在路由之前注册中间件
    2. 客户端发送过来的请求，，可以连续调用多个中间件进行处理
    3. 执行完中间件的业务代码之后，不要忘记调用`next()`函数
    4. 为了防止代码逻辑混乱，调用`next()`函数后不要再写额外的代码
    5. 连续调用多个中间件时，多个中间件之间，共享req和res对象。

24. 中间件的分类 - 5大类

    1. 应用级别的中间件

       1. 通过`app.use()`或`app.get()`或`app.post()`，绑定到app实例上的中间件。

    2. 路由级别的中间件

       1. 绑定到`express.Router()`实力实例上的中间件。用于与应用级别中间件没有区别，只不过，应用级别中间件时绑定到app实例上，路由级别中间件绑定到router实例上。

          ```js
          var app = express()
          var router = express.Router()
          router.use(function(req,res,next){
              next();
          })
          ```

    3. 错误级别的中间件

       1. 作用：用来捕获整个项目中发生的异常错误，从而方式项目异常崩溃的问题。

       2. 格式：`(err,req,res,next)`

          ```js
          app.get('/',function(req,res){
              throw new Error('服务器内部发生了错误！');//1.抛出一个自定义的错误
              res.send('Home Page'); //若没有下面捕获，前端页面显示‘错误页面’（类似404）
          });
          app.use(function(err,req,res,next){  //错误级别中间件
              console.log('发生了错误: ' + err.message) //2.在服务器打印‘错误消息’
              res.send('Error! ' + err.message); //3.向客户端响应‘错误相关的内容’而非错误页面
          })
          ```

       3. 注意：错误级别的中间件，必须注册在所有路由之后。

    4. Express内置的中间件

       1. 从Express 4.16.0版本开始，Express内置了3个常用的中间件，极大的提高了Express项目的开发效率和体验。

       2. `express.static`：快速托管静态资源的内置中间件，如HTML、图片、CSS等。（无兼容性问题，任何版本均可使用）

       3. `express.json`：解析JSON格式的请求体数据（有兼容性，4.16.0+版本）

       4. `express.urlencoded`解析URL-encoded格式的请求体数据（有兼容性，尽在4.16.0+版本）

          ```js
          /*
          Postman向服务器发送指定内容：
          选择'POST'方式，输入'URL'，选择'Body'的'raw'中的'JSON'格式
          内容区输入：{ "name":"zs", "age":13}
          服务器接收：req.body 属性接收客户端发送过来的请求体数据，此处JSON格式
          默认情况下，如果不配置表单数据的中间件，则 req.body 默认等于'undefined'
          */
          //配置解析 appication/json 格式数据的内置中间件
          app.use(express.json());
          /*
          Postman向服务器发送指定内容：
          选择'POST'方式，输入'URL'，选择'Body'的'x-www-form-urlencoded'中的键值对形式
          内容区输入：Key【bookname】 - Value【水谷转】 - Description【】
          服务器接收：req.body 属性接收客户端发送过来的请求体数据，此处url-encoded格式
          默认情况下，如果不配置表单数据的中间件，则 req.body 默认等于'{}' 空对象
          */
          //配置解析 application/x-www-form-urlencoded 格式数据的内置中间件
          app.use(express.urlencoded( {extended:false} ))
          ```

       5. 除了*错误级别的中间件*，其他的中间件，必须在路由之前进行配置

    5. 第三方中间件

       1. 非Express官方内置，有第三方开发出来的，按需下载。
       2. 在Express 4.16.0之前，经常使用'body-parser'这个第三方中间件，来解析请求体的数据。
       3. 安装：`npm install body-parser`； 
       4. 然后导入、调用`app.user(parser.urlencoded({ extended:false }))`注册中间件。

25. 自定义中间件 - 案例

    1. 手动模拟一个类似于express.urlencoded这样的中间件，来解析POST提交到服务器表单的数据。

       1. 定义中间件
       2. 监听req的data事件
       3. 监听req的end事件
       4. 使用querystring模块解析请求体数据
       5. 将解析出来的数据对象挂载为req.body

       ```js
       //导入内置模块
       const qs = require('querystring')
       //解析表单数据的中间件
       app.use(function(req,res,next){
           //定义中间件具体的业务逻辑
           //1.定义变量，用来存储客户端发送过来的请求体数据
           let str = '';
           //2.监听req对象的data事件（客户端发送过来的新的请求体数据）
           req.on('data',(chunk)=>{
           	//拼接请求体数据，隐式转换为字符串
           	str += chunk;
       	});
           //3.监听req对象的end事件（请求体发送完毕后自动触发）
           req.on('end',()=>{
               //打印完整的请求体数据
               console.log(str)
               //TODO：把字符串格式的请求体数据，解析成对象格式
               //4.调用qs.parse()方法，把查询字符串解析为对象
           	const body = qs.parse(str);
               console.log(body);
               //5.将解析出来的请求体对象，挂在为 req.body 属性
               req.body = body;
               next(); //交给其他或路由使用
           })
       })
       
       ```

    2. 监听 req 的 data 事件：获取客户端发送到服务端的数据。

       1. 如果数据量比较大，无法一次性发送完毕，则客户端会数据切割后，分批发送到服务器。所以data事件可能会触发多次，每一次触发data事件时，获取到的数据只是完整数据的一部分，需要手动对接收到的数据进行拼接。

    3. 监听 req 的 end 事件：请求体数据接收完毕之后，会自动触发req的end事件。

       1. 可以在req的end事件中，拿到并处理完整的请求体数据。

    4. 使用 querystring 模块解析请求体数据

       1. Node.js内置了一个 querystring 模块，专门用来处理查询字符串。通过这个模块提供的`parse()`函数，可以轻松查询字符串，解析成对象的格式。

    5. 将解析出来的数据对象挂载为 req.body

       1. 上游的中间件和下游的中间件及路由之间，共享同一份req 和 res。因此，可以将解析出来的数据，挂载为req的自定义属性，命名为`req.body`，供下游使用。

    6. 将自定义中间件封装为模块

       1. 为了优化代码的结构，我们可以把自定义的中间件函数，封装为独立的模块

          ```js
          //custom-body-parse.js 代码
          const qs = require('querystring');
          function bodyParser(req, res, next){ /*逻辑代码*/}
          module.exports = bodyParser //向外导出解析请求体数据的中间件函数
          //--------分割----
          //1.导入自定义的中间件模块
          const myBBodyParser = require('custom-body-parser');
          //2.注册自定义的中间件模块，注册为全局可用的中间件
          app.use(myBodyParser);
          app.post(...)
          ```

26. 编写接口

    1. 先创建一个基本服务器

       ```js
       //app.js
       const express = require('express');
       const app = express();
       //代码...
       app.listen(80,function(){
           console.log("expressserver running ..")
       })
       ```

    2. 创建API路由模块

       ```js
       //apiRouter.js 路由模块
       const express = require('express');
       const apiRouter = express.Router();
       //代码...GET/POST等
       module.exports = apiRouter
       
       //app.js
       const apiRouter = require('./apiRouter.js');
       app.use('/api',apiRouter);//注册全局中间件
       ```

    3. 编写GET接口

       ```js
       apiRouter.get('/get',(req,res)=>{
           //1.获取到客户端通过查询字符串，发送到服务器的数据
           const query = req.query;
           //2.调用res.send()方法，把数据响应给客户端
           res.send({
               status:0, //状态码，0表示成功，1表示失败
               msg:'GET请求成功！', //状态描述
               data:query //响应给客户端的具体数据
           })
       })
       //访问地址：127.0.0.1/api/get
       ```

    4. 编写POST接口

       ```js
       apiRouter.post('/post',(req,res)=>{
           //1.获取到客户端通过请求体，发送到服务器的URL-encoded数据
           const body = req.body;
           //2.调用res.send()方法，把数据响应给客户端
           res.send({
               status:0, //状态码，0表示成功，1表示失败
               msg:'GET请求成功！', //状态描述
               data:body //响应给客户端的具体数据
           })
       })
       //注意：如果要获取URL-encoded格式的请求体数据，必须配置中间件app.use(express.urlencoded({extended:false}))
       //访问地址：127.0.0.1/api/post?
       ```

    5. 接口跨域问题

       1. 以上编写的GET和POST接口，存在一个严重的问题：**不支持跨域请求**。

    6. 解决跨域问题：

       1. CORS：主流解决方案，推荐使用。
       2. JSONP：有缺陷的解决方案，只支持GET请求

27. CORS跨域资源共享

    1. CORS - Cross-Origin Resource Sharing，跨域资源共享， 由一些列HTTP响应头组成，**这些HTTP响应头决定浏览器是否阻止前端JS代码跨域获取资源**。

    2. 浏览器的同源安全策列默认会阻止网页“跨域”获取资源。如果接口服务器配置了CORS相关的HTTP响应头，就可以解除浏览器端的跨域访问限制。

    3. CORS注意事项

       1. CORS主要在服务器端进行配置。客户端浏览器无需做任何额外的配置，即可请求开启了CORS的接口。
       2. CORS在浏览器中有兼容性。只有支持 *XMLHttpRequest Level2* 的浏览器，才能正常访问开启了CORS的服务端接口。如 IE10+、CHrome4+、FireFox3.5+。

    4. 使用 **cors中间件** 解决跨域问题：

       1. 通过安装和配置 Express的第三方 cors中间件，可以方便的解决跨域问题。
       2. 步骤一：安装中间件 `npm install cors`
       3. 步骤二：使用 `const cors = require('cors')`
       4. 步骤三： 在路由之前调用（全局）`app.use(cors())`

    5. CORS响应头部 - *Access-Control-Allow-**Origin***

       1. 响应头部中可以携带一个 Access-Control-Allow-Origin 字段

          ```js
          Access-Control-Allow-Origin: <origin> | *
          ```

       2. 其中，origin 参数的值指定了允许访问该资源的外域URL。

          ```js
          //只允许来自http://itcase.cn的请求
          res.setHeader('Access-Control-Allow-Origin','http://itcast.cn');
          //通配符*， 允许来自任何域的请求
          res.setHeader('Access-COntrol-Allow-Origin','*');
          ```

    6. CORS响应头部 -  *Access-Control-Allow-**Headers***

       1. 默认情况下，CORS仅支持客户端向服务器发送**如下9个请求头**。

       2. Accept、Accept-Language、Content-Language、DPR、Downlink、Save-Data、Viewport-Width、Width、Content-Type（值仅限于 text/plain、multipart/form-data、application/x-www-form-urlencoded三者之一）

       3. 如果客户端向服务器发送了额外的请求头信息，则需要在服务器端，通过Access-Control-Allow-Headers对额外的请求头进行声明，否则这次请求会失败。

          ```js
          //比如：允许客户端额外向服务器发送Content-Type请求头和X-Custom-Header请求头
          //注意：多个请求头之间使用英文的逗号进行分割
          res.setHeader('Access-Control-Allow-Headers','Content-Type,X-Custom-Header')
          ```

    7. CORS响应头部 -  *Access-Control-Allow-**Methods***

       1. 默认情况下，CORS仅支持客户端发起GET、POST、HEAD请求。

       2. 如果客户端希望通过PUT、DELETE等方式请求服务器的资源，则需要在服务器端，通过 Access-Control-Allow-Headers来指明实际请求所允许使用的HTTP方法

          ```js
          //只允许POST、GET、DELETE、HEAD请求方法
          res.setHeader('Access-Control-Allow-Methods','POST,GET,DELETE,HEAD');
          //允许所有的HTTP请求方法
          res.setHeader('Access-Control-Allow-Methods','*');
          ```

    8. CORS请求的分类

       1. 客户端在请求CORS接口时，根据请求方式和请求头的不同，可以将CORS的请求分为两大类
       2. **简单请求**：**同时**满足以下两大条件
          1. 请求方式：GET、POST、HEAD三者之一
          2. HTTP头部信息不超过以下几种字段：**无自定义头部字段**、Accept、Accept-Language、Content-Language、DPR、Downlink、Save-Data、Viewport-Width、Width、Content-Type（值仅限于 text/plain、multipart/form-data、application/x-www-form-urlencoded三者之一）
       3. **预检请求**：满足以下任何条件之一
          1. 请求方式：GET、POST、HEAD之外的请求Method类型
          2. 请求头部包含自定义头部字段
          3. 向服务器发送了 application/json 格式的数据。
       4. 预检请求的说明：在浏览器与服务器正式通信之前，浏览器会先发送OPTION请求进行预检，以获知服务器是否允许该实际请求，所以这一次的OPTION请求称为“预检请求”。服务器成功响应预检请求后，才会发送真正的请求，并携带真是数据。

    9. 简单请求与预检请求的区别

       1. 简单请求特点：客户端与服务器之间只会发生一次请求。
       2. 预检请求的特点：客户端与服务器之间会发生两次请求，OPTION预检请求成功之后，才会发起真正的请求。
       3. 测试：使用DELETE请求，使用jQuery发送DELETE请求，Express设置`.delete()`，可在浏览器的'网络'中看到点击DeleteBtn会发生两次请求。

    10. JSONP概念与特点

        1. 概念：
           1. 浏览器通过`<script>`标签的src属性，请求服务器上的数据，同时，服务器返回一个函数的调用。这种请求叫做JSONP。
        2. 特点：
           1. JSONP不属于真正的Ajax请求，因为它没有使用XMLHttpRequest这个对象。
           2. JSONP仅支持GET请求，不支持POST、PUT、DELETE请求。

    11. JSONP接口：

        1. 如果项目中已经配置了CORS跨域资源共享，为了防止冲突，必须在配置CORS中间件之前声明JSONP的接口。否则JSONP接口会被处理成开启了CORS的接口。

           ```js
           //1.优先创建JSONP 接口，【这个接口不会被处理成CORS接口】 先后顺序
           app.get('/api/jsonp',(req,res)=>{ /*..*/});
           //2.再配置CORS中间件 【后续的所有接口，都会被处理成CORS接口】
           app.use(cors());
           //3.这是一个开启了CORS的接口
           app.get('/api/get',(req,res)=>{ /*...*/})
           ```

        2. 实现JSONP接口步骤

           1. 获取客户端发送过来的回调函数的名字
           2. 得到要通过JSONP形式发送给客户端的数据
           3. 根据前两部得到的数据，拼接处一个函数调用的字符串
           4. 把上一步拼接得到的字符串，响应给客户端的`<script>`标签进行解析执行

           ```js
           app.get('/api/jsonp',(req,res)=>{
               //1.获取客户端发送来的回调函数的名字
               const funcName = req.query.callback
               //2. 得到要通过JSONP形式发送给客户端的数据
               const data = { name:'zs', age:22 }
               //3. 根据前两部得到的数据，拼接处一个函数调用的字符串
               const scriptStr = `${funcName}(${JSON.stringify(data)})`; //JSON.stringify将data对象转成字符串
               //4.把上一步拼接得到的字符串，响应给客户端的<script>标签进行解析执行
               res.send(scriptStr)
           })
           ```

        3. 网页端代码

           ```html
           <button id="btnJSONP">JSONP</button>
           <script>
           //调用$.ajax()，提供JSONP的配置选项，从而发起JSONP请求，代码如下
           $('#btnJSONP').on('click'，function(){
               $.ajax({
                   method:'GET',
                   url:'http://127.0.0.1/api/jsonp',
                   dataType:'jsonp',//表示要发起JSONP的请求
                   success:function(res){
                       console.log(res)
                   }
               })
           })
           </script>
           ```

## 数据库

1. MySQL、Oracle、SQL Server属于传统型数据库（又叫：关系型数据库或SQL数据库），这三者设计理念相同，用法比较类似。

2. Mongodb属于新型数据库（又叫：非关系型数据库或NoSQL数据库），弥补了传统数据库的缺陷。

3. Excel的数据组织结构：

   1. 每个Excel中，数据的阻止结构分别为工作簿（整个excel）、工作表（下标的不同表名）、数据行、列 这四大部分组成。

4. 传统型数据库的数据组织结构：

   1. 传统型数据库，数据的阻止结构分为数据库（database）、数据表（table）、数据行（row）、字段（field） 这四大部分组成。

5. MySQL Server：专门用来提供数据存储和服务的软件。

   1. 安装注意事项：若提示错误，需要安装NDP452环境 - KB2901907

6. MySQL Workbench：可视化MySQL管理工具。

7. Express 项目中操作数据库

   1. npm安装MySQL数据库的第三方模块（mysql）

      1. 安装：`npm install mysql`

      2. 使用之前需要对MySQL模块进行必要的配置 

         ```js
         //1.导入mysql模块
         const mysql = require('mysql');
         //2.建立与Mysql数据库的链接
         const db = mysql.createPool({
             host:'127.0.0.1',
             user:'root',
             password:'',
             database:'my_db_01'
         })
         ```

   2. 通过mysql模块连接到MySQL数据库

   3. 通过mysql模块执行SQL语句

      ```js
      //检测mysql模块能否正常工作
      db.query('select 1',(err, results)=>{
          if(err) return console.log(err.message);
          //只要能打印出 [RowDataPacket {'1':1} ]的结果，就证明数据库连接正常
          console.log(results);
      })
      ```

8. 查询

   ```js
   const sqlStr = 'select * from users';
   db.query(sqlStr, (err,results)=>{
       //查询失败
       if(err) return console.log(err.message);
       //查询成功 如果执行的是select语句，则执行的结果是数组。
       console.log(results);
   })
   ```

9. 插入

   ```js
   //1.要插入到users表中的数据对象
   const user = {username:'spider',password:'pcc333'};
   //2.待执行的sql语句，其中英文的？表示占位符
   const sqlStr = 'insert into users (username, password) values (?,?)';
   //3.使用数组形式，依次为？占位符指定具体的值
   db.query(sqlStr, [user.username, user.password], (err, results)=>{
       if(err) return console.log(err.message) //失败
       //如果执行的是 insert into 插入语句， 则results是一个对象
       if(results.affectedRows === 1) {console.log('插入数据成功'); }
   })
   ```

10. 插入 - 便捷方式 set

    ```js
    //1.要插入到users表中的数据对象
    const user = {username:'spider',password:'pcc333'};
    //2.待执行的sql语句，其中英文的？表示占位符
    const sqlStr = 'insert into users set ?';
    //3.使用数组形式，依次为？占位符指定具体的值
    db.query(sqlStr, user, (err, results)=>{
        if(err) return console.log(err.message) //失败
        //如果执行的是 insert into 插入语句， 则results是一个对象
        if(results.affectedRows === 1) {console.log('插入数据成功'); }
    })
    ```

11. 更新

    ```js
    //1.要更新的数据对象
    const user = {id:7, username:'aaa', password:'000'};
    //2.要执行的SQL语句
    const sqlStr = 'update users set username=?,password=? where id=?'
    /*便捷方式
    const sqlStr = 'update users set ? where id=?'
    */
    //3.调用db.query()执行SQL语句的同时，使用数组依次为占位符指定具体的值
    db.query(sqlStr,[user.username,user.password,user.id],(err,results)=>{
    /*便捷方式
    db.query(sqlStr,[user,user.id],(err,results)=>{
    */    
        if(err) return console.log(err.message);
        if(results.affectedRows === 1){console.log('更新数据成功!');}
    })
    ```

12. 删除

    ```js
    //要执行的SQL语句
    const sqlStr = 'delete from users where id=?';
    //调用db.query()执行SQL语句的同时，为占位符指定具体的值
    //注意：如果SQL语句中有多个占位符，必须使用数组为每个占位符指定具体的值
    //	如果SQL语句中只有一个占位符，则可以省略数组
    db.query(sqlStr,7,(err,results)=>{
        if(err) return console.log(err.message);
        if(results.affectedRows === 1){console.log('删除数据成功!');}
    })
    ```

13. **标记删除**

    1. 使用DELETE语句，会把真正的数据从表中删除掉。为了保险起见，推荐使用标记删除的形式，来模拟删除的动作。

    2. 所谓的标记删除，就是在表中设置类似于**status**这样的状态字段，来标记当前这条数据是否被删除。

    3. 当用户执行了删除的动作时，并没有执行DELETE语句把数据删除掉，而是执行了UPDATE语句，将这条数据对应的status字段标记为删除即可。

       ```js
       //标记删除：使用UPDATE语句替代DELETE语句；只更新数据的状态，并没有真正的删除
       db.query('update users set status=1 where id=?',6,(err,results)=>{
           if(err) return console.log(err.message);
           if(results.affectedRows === 1){console.log('删除数据成功!');}
       })
       ```

## 前后端身份认证

1. Web开发模式

   1. 基于服务端渲染的传统Web
   2. 基于前后端分离的新型Web

2. 服务端渲染的Web开发

   1. 概念：*服务器*发送给客户端的HTML页面，是在服务器通过字符串的拼接，动态生成的。因此，客户端不需要使用Ajax这样的技术额外请求页面的数据。如下代码

      ```js
      app.get('/index.html',(req,res)=>{
          //1.要渲染的数据
          const user = {name:'zs', age:20};
          //2.服务器端通过字符串的拼接，动态生成HTML内容
          const html = `<h1>姓名：${user.name},年龄：${user.age}</h1>`
          //3.把生成好的页面内容响应给客户端。因此客户端拿到的是带有真实数据的HTML页面
          res.send(html);
      })
      ```

   2. 优点：

      1. 前端耗时少：服务器端负责动态生成HTML内容，浏览器只需要直接渲染页面即可。尤其是移动端，更省电。
      2. 有利于SEO：因为服务端响应的是完整的HTML页面内容，所以爬虫更容易爬取获得信息，更有利于SEO。

   3. 缺点：

      1. 占用服务器端资源：即服务器端完成HTML页面内容的拼接，如果请求较多，会对服务器造成一定的访问压力。
      2. 不利于前后端分离，开发效率低：使用服务器端渲染，则无法进行分工合作，尤其对于前端复杂度高的项目，不利于项目高效开发。

3. 前后端分离的Web开发模式

   1. 概念：前后端分离的开发模式，依赖于Ajax技术的广泛应用。简而言之，前后端分离的Web开发模式，就是后端只负责提供API接口，前端使用Ajax调用接口的开发模式。
   2. 优点：
      1. 开发体验好：前端专注于UI页面的开发，后端专注于api的开发，且前端有更多的选择性。
      2. 用户体验好：Ajax技术的广泛应用，极大的提高了用户的体验，可以轻松实现页面的局部刷新。
      3. 减轻了服务器端的渲染压力：因为页面最终是在每个用户的浏览器中生成的。
   3. 缺点：
      1. 不利于SEO：因为完整的HTML页面需要在客户端动态拼接完成，所以爬虫对无法爬取页面的有效信息。（解决方案：利用Vue、React等前端框架的SSR - server side render 技术能够很好的解决SEO问题）

4. 实际：根据业务场景

   1. 企业级网站：主要功能，展示，没有复杂的交互，并且有良好的SEO，使用服务器端渲染。
   2. 后台管理项目：交互性比较强，不需要考虑SEO，那么使用前后端分离的开发模式。
   3. 同时兼容首页的渲染速度和前后端分离的开发效率，一些网站采用了首屏服务器端渲染 + 其他页面前后端分离的开发模式。

5. 身份认证

   1. Authentication，身份验证、鉴权，是指通过一定的手段，完成对用户身份的确认。
   2. 不同开发模式下的身份认证
      1. 服务端渲染：推荐使用 Session 认证机制
      2. 前后端分离：推荐使用 JWT 认证机制

6. Session认证机制

   1. **HTTP协议的无状态性**
      1. 指的是，客户端的每次HTTP请求都是独立的，连续多个请求之间没有直接的关系，服务器不会主动保留每次HTTP请求的状态。
   2. Cookie
      1. Cookie是存储在用户浏览器中的一段**不超过4KB的字符串**。由一个名称Name、一个值Value和其他几个用于空值Cookie 有效期、安全性、使用范围的可选属性组成。
      2. 浏览器保存的位置：F12 - Application - Storage - Cookies
      3. 不同域名下的Cookie各自独立，每当客户端发起请求时，会自动把当前域名下所有未过期的Cookie一同发送到服务器。
   3. Cookie几大特性
      1. 自动发送：发起请求时自动发送
      2. 域名独立：不同域名不同cookie
      3. 过期时限：有效期内的Cookie 才会被发送
      4. 4KB限制：键值对形式

7. Cookie在身份认证中的作用

   1. 客户端**第一次**请求服务器时，服务器通过响应头的形式，向客户端发送一个身份认证的Cookie，客户端会自动将Cookie保存在浏览器中。
   2. 随后，当客户端浏览器*每次*请求服务器的时候，浏览器会**自动**将身份认证相关的Cookie，通过请求头的形式发送给服务器，服务器即可验明客户端的身份。

8. Cookie**不具有**安全性

   1. 由于Cookie时存储在浏览器中的，而且浏览器页提供了读写Cookie的API，因此Cookie很容易被伪造，不具有安全性。因此**不建议**服务器将**重要的隐私数据**，通过Cookie的形式发送给浏览器。

9. Session工作原理

   ```js
   浏览器								服务器
   |->客户端登录：提交账号密码				|
       					 	       | 验证账号和密码
    服务器响应：将生成的Cookie响应给客户端<-| 将登陆成功后的用户信息存储在服务器内存中，同时生成对应的Cookie字符串
   | 浏览器自动把Cookie存储在当前域名下
   |->客户端再次发起请求时，通过请求头自动把当前域名下所有可用的Cookie发送给服务器
   									|服务器根据请求头携带的Cookie，从内存中查找对应的用户信息
    服务器响应：把当前用户对应的页面内容响应给浏览器<-|用户的身份认证成功后，服务器针对当前用户生成特定的相应内容
   
   ```

10. 安装express-session中间件

    1. 安装命令：`npm install express-session`

    2. 配置express-session中间件：

       ```js
       //app.js
       //1.导入session中间件
       var session = require('express-session');
       //2.配置Session中间件（全局）
       app.use(session({
           secret:'keyboard cat', //secret属性的值可以为任意字符串
           resave: false, //固定写法
           saveUninitialized: true //固定写法
       }))
       ```

    3. 向session中存数据：

       1. 当express-session中间件配置成功后，才可以通过`req.session`来访问和使用session对象，从而存储用户的关键信息。

          ```js
          app.post('/api/login',(req,res)=>{
              //判断用户提交的登录信息是否正确
              if(req.body.username !== 'admin' || req.body.password !== '000000'){
                  return res.send({status:1, msg:'登陆失败'});
              }
              req.session.user = req.body //追加自定义属性，将用户的信息，存储到Session中
              req.session.islogin = true //将用户的登陆状态，存储到Session中
              res.send({status:0,msg:'登录成功'});
          })
          ```

    4. 从session中取数据

       ```js
       app.get('/api/username',(req,res)=>{
           //判断用户是否登录
           if(!req.session.islogin){
               return res.send({ status:1, msg:'fail'});
           }
           res.send({ status:0, msg:'success',username:req.session.user.username })
       ```

    5. 清空session

       ```js
       //退出登录时
       app.post('/api/logout',(req,res)=>{
           req.session.destroy();
           res.send({
               status:0,
               msg:'退出登录'
           });
       })
       ```

11. **JWT认证机制**

    1. Session认证的局限性：

       1. Session认证机制需要配合Cookie才能实现。由于Cookie默认不支持跨域访问，所以，当设计到前端跨域请求后端接口的时候，需要做很多额外的配置，才能实现跨域Session认证。
       2. 注意：
          1. 当前端请求后端接口不存在跨域问题的时候，推荐使用Session身份认证机制。
          2. 当前端需要跨域请求后端接口的时候，不推荐使用Session身份认证机制，推荐使用 JWT 认证机制。

    2. JWT：JSON Web Token，目前最流行的跨域认证解决方案。

    3. JWT工作原理

       ```js
       浏览器								服务器
       |->客户端登录：提交账号密码				|
           					 	       | 验证账号和密码
        服务器响应：将生成的Token响应给客户端<-| 将登陆成功后的用户信息存储在服务器内存中，同时生成对应的Token字符串
       | 讲Token存储到LocalStorage或SessionStorage
       |->客户端再次发起请求时，通过请求头Authorization字段，将Token发送给服务器
       									|服务器把Token字符串还原成用户的信息对象
        服务器响应：把当前用户对应的页面内容响应给浏览器<-|用户的身份认证成功后，服务器针对当前用户生成特定的相应内容
       
       ```

    4. JWT工作原理总结：用户的信息通过Token字符串的形式，保存在客户端浏览器中。服务器通过还原Token字符串的形式来认证用户的身份。

    5. **JWT组成部分**：Header头部、Payload有效荷载、Signature签名。

       1. 三者使用英文“.”分隔：`Header.Payload.Signature`

          ```js
          //示例
          afasdfasdfasdf.adsfasdfasdf.adsfadsfa
          ```

       2. Payload：才是真正的用户信息，它是用户信息经过加密之后生成的字符串。

       3. Header、Signature：是安全性相关的部分，只是为了保证Token的安全性。

    6. JWT使用方式

       1. 客户端收到服务器返回的JWT之后，通常会将它存储在`localStorage`或`sessionStorage`中。
       2. 此后，客户端每次于服务器通信，都要带上这个JWT的字符串，从而进行身份认证。
       3. **推荐做法**是把JWT放在HTTP请求头的Authorization字段中

       ```js
       /*  Authorization:Bearer <token>  */
       ```

12. JWT相关的包

    1. 安装命令：`npm install jsonwebtoken express-jwt`

    2. jsonwebtoken包：用于生成 JWT 字符串

    3. express-jwt包：用于将JWT字符串解析还原成 JSON 对象

    4. 导入相关包

       ```js
       //导入
       const jwt = require('jsonwebtoken');
       const expressJWT = require('express-jwt');
       //新版本可能需要如下导入方式
       const { expressjwt } = require("express-jwt");  
       ```

    5. 定义Secret密钥：为了保证JWT字符串的安全性，防止JWT字符在网络传输过程中被别人破解，需要定义一个加密和解密的secret密钥

       1. 当生成JWT字符串的时候，需要使用secret密钥对用户的信息进行加密，最终得到加密好的JWT字符串。
       2. 当把JWT字符串解析还原成JSON对象的时候，需要使用secret密钥进行解密。

       ```js
       const secretKey = 'itheima No.123 ^_^';
       ```

    6. 登录成功后生成JWT字符串：调用 jsonwebtoken包提供的`sign()`方法，将用户的信息加密成JWT字符串，响应给客户端。

       ```js
       //登录接口
       app.post('/api/login',function(req,res){
           //... 省略登录失败情况下代码
           //用户登录成功之后，生成JWT字符串，
           //注意：不要加密密码这种隐私的信息到token发送给前端
           res.send({
               status:200,
               message:'登陆成功',
               //调用 jwt.sign() 生成JWT字符串，三个参数分别是：用户信息对象、加密密钥、配置对象
               token: jwt.sign({username:userinfo.username},secretKey,{expiresIn:'30'})
           })
       })
       ```

    7. 将JWT字符串还原为JSON对象

       1. 客户端每次在访问那些有权限接口的时候，都需要主动通过请求头中的Authorization字段，将Token字段串发送到服务器进行身份认证。

       2. 此时，服务器端可以通过express-jwt这个中间件，自动将客户端发送过来的Token解析还原成JSON对象

          ```js
          // 使用app.use()注册中间件
          // expressJWT({secret:secretKey}) 用来解析Token的中间件
          // .unless({path:[/^\/api\//]})用来指定哪些接口不需要访问权限 即/api，其他/admin开头需要权限
          app.use(expressJWT( {secret:secretKey} ).unless( {path:[/^\/api\//]} ))
          /*新版本，若出错，尝试以下：
          app.use(expressJWT({secret:secretKey, algorithms: ['HS256'],}).unless({path:[/^\/api\//]}))
          */
          ```

    8. 使用`req.user`获取用户信息（最新版使用`req.auth`获取）

       1. 只要配置成功了 express-jwt这个中间件，就可以把解析出来的用户信息，挂载到req.user 属性上。

       ```js
       //这是 一个有权限的API接口
       app.get('/admin/getinfo',function(req,res){
           console.log(req.user);
           res.send({
               status:200,
               message:'获取用户信息成功！',
               data:req.user
           })
       })
       ```

    9. 使用Postman测试

       1. POST发送（用于登录）：使用POST方式，地址栏使用`http://127.0.0.1:888/api/login`，并且在`Body`中选择`x-wwww-form-urlencoded`添加 `[Key - Value]`等登录用户米密码
       2. GET发送（已登录）：使用GET方式登录，地址栏使用`http://127.0.0.1:888/admin/getinfo`，并且在`Headers`下添加 `[Key - Value]`，[ Authorization - 'Bearer `Token`']。注意格式，必须加Bearer和空格再添加Token字符串才能解析。

    10. 捕获解析JWT失败后产生的错误

        1. 当使用express-jwt解析Token字符串时，客户端发送过来的Token字符串过期或不合法，会产生一个解析失败的错误，影响项目的正常运行。

        2. 可以通过Express的错误中间件，捕获这个错误并进行相关的处理

           ```js
           app.use((err,req,res,next)=>{
               //token解析失败导致的错误
               if(err.name === 'UnauthorizedError'){
                   return res.send( {status:401,message:'无效的token'} );
               }
               //其他原因导致的错误
               res.send( {status:500,message:'未知的错误'} );
           })
           ```

    11. 密码加密模块：

        1. 安装：`npm i bcryptjs`
        2. 使用前导入：`bcrypt.hashSync(明文密码，随机盐的长度)`

13. 其他人整理完整笔记（包括案例）：[地址](https://brucecai55520.gitee.io/bruceblog/notes/nodejs/node.html) | 黑马案例老师笔记：[地址](http://escook.cn:8088/#/mds/1.init)

14. 案例个别注意：

    1. 在处理函数，多次调用`res.send()`向客户端响应处理失败的结果，为了简化代码，可以手动封装一个`res.cc()`函数。 

       ```js
       //全局响应数据的中间件(放在路由之前)
       app.use(function(req,res,next){
           //status=0为成功，status=1为失败；默认将status的值设置为1，方便处理失败的清空
           res.cc = function(err, status=1){
               res.send({
                   //状态
                   status，
                   //状态描述，判断err时错误对象还是字符串
                   message: err instanceof Error ? err.message : err,
               })
           }
           next();
       })
       //其他代码
       db.query(sqlStr, userinfo.username, (err, results)=>{
           if(err){
               //return res.send({status:1, message:err.message})
               return res.cc(err);
               //或者其他return
               return res.cc('登陆失败，稍后再试！');
               return res.cc('登录成功',0);
           }
       })
       ```

    2. 注意报错：可能出现两次`res.send()`

​	









































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







# 尚硅谷 React

## 概述

1. 采用组件化模式、声明式编码，提高开发效率及组件复用率。

2. 在React Native可以使用React用法进行**移动端开发** ios/android。

3. 使用**虚拟DOM**+优秀的Diffing算法，尽量减少与真实DOM的交互。

   1. 真实数据 -  虚拟DOM - 真实DOM

4. 相关库使用

   1. `react.js`：React核心库
   2. `react-dom.js`：提供操作DOM的react扩展库
   3. `babel.min.js`：解析 JSX 转成 JS的代码库，即ES6转为ES5。

   ```html
   <!--容器-->
   <div id="test"></div>
   
   <!--注意引入顺序-->
   <!--引入react核心库-->
   <script type="text/javascript" src="new/react.development.js"></script>
   <!--引入react-dom，用于支持react操作DOM-->
   <script type="text/javascript" src="new/react-dom.development.js"></script>
   <!--引入babel，用于将jsx转为js-->
   <script type="text/javascript" src="new/babel.min.js"></script>
   
   <script type="text/babel">/*此处一定要写Babel(即使用的JSX语言)，默认是JS*/
           //1.创建虚拟DOM
           const VDOM = <h1>hello,React</h1>; /*此处一定不要写引号，因为不是字符串*/
           //2.渲染虚拟DOM到页面
           // 格式：ReactDOM.render(虚拟DOM，容器)
           ReactDOM.render(VDOM,document.getElementById('test'));
   </script>
   ```

5. 虚拟DOM创建两种方式

   1. JSX方式

      ```html
      ...js库引用
      <div id="test"></div>
      <script type="text/babel">/*此处一定要写Babel(即使用的JSX语言)，默认是JS*/
          //1.创建虚拟DOM
          const VDOM = <h1 id="title">hello,React</h1>;
          /* 嵌套
          const VDOM = <h1 id="title"><span>hello,React</span></h1>; 
          输出：<div id="test"><h1 id="title"><span>hello,React</span></h1></div>*/
          //2.渲染虚拟DOM到页面
          ReactDOM.render(VDOM,document.getElementById('test'));
      </script>
      ```

   2. JS方式（一般不用）

      ```html
      ...js库引用
      <div id="test2"></div>
      <script type="text/javascript">/*此处是JS*/
          //1.创建虚拟DOM
          // 格式： React.createElement(标签名，标签属性，标签体内容)；
          const VDOM2 = React.createElement('h1',{id:'title2',class:'title2'},'Hello React - JS');
          /* 嵌套
          const VDOM2 = React.createElement('h1',{id:'title2',class:'title2'},React.createElement('span',{},'Hello React - JS'));
          输出：<div id="test"><h1 id="title"><span>hello,React</span></h1></div> */
          //2.渲染虚拟DOM到页面
          ReactDOM.render(VDOM2,document.getElementById('test2'));
      </script>
      ```

6. 虚拟DOM

   1. 虚拟DOM是Object类型

      ```js
      console.log(VDOM);
      //{$$typeof: Symbol(react.element), type: 'h1', key: null, ref: null, props: {…}, …}
      console.log(VDOM instanceof Object);
      //true
      ```

   2. 查看真实DOM的属性

      1. 真实DOM使用 console.log() 默认输出HTML元素

      2. 使用`debugger`查看真实DOM属性

         ```js
         const TDOM = document.getElementById('#test2');
         console.log("真实DOM",TDOM); //默认：<div id="test2">...</div>
         debugger //鼠标移动到TDOM自动显示属性信息
         ```

   3. 虚拟DOM的属性少，真实DOM属性多。虚拟DOM是React内部再用，无需真实DOM那么多的属性。

   4. 虚拟DOM最终会被React转化为真实DOM，呈现在页面上。

7. JSX - JavaScript XML

   1. react定义的一种类似XML的**JS扩展语法**。
   2. XML早期用于存储和传输数据。后续使用JSON，JS内置JSON，常用`JSON.parse()`和`JSON.stringify()`。

## JSX语法规则

1. 定义虚拟DOM时，不需要写引号。

2. 标签中混入**JS表达式**（非JS语句、代码）时要用`{}`

   1. JS表达式：一个表达式会产生一个值，可以放在任何一个需要值的地方
      1. 判定，使用`const x = 变量/运算/函数/函数调用/数组方法等`，能获得到值即为表达式

      2. `const x = 函数`其返回值即是函数本身

   2. JS语句/代码：多数为流程控制，比如 if判断、for循环、switch、

3. 样式的类名不要用`class`，要使用`className='xxx'` (class是ES6定义的关键字，react使用className避免冲突)

4. 内联样式，要用`style={{key:value}}`的形式去写，且用驼峰形式书写

5. 虚拟DOM必须只有一个根标签，比如只能有一个`<div>`或者`<h1>`

6. 标签必须闭合 比如`</div>`

7. 自定义标签中，

   1. 若是小写字母开头，JSX自动根据小写字母转成HTML同名元素标签（小写标签一般使用HTML标准标签）
   2. 若html中无同名元素，则报错
   3. 若大写字母开头，react就去渲染对应的组件，若组件没有定义，则报错。

   ```html
       <style>
           .title{
               background-color: orange;
           }
       </style>
   <div id="test"></div>
   
   <script type="text/javascript" src="new/react.development.js"></script>
   <script type="text/javascript" src="new/react-dom.development.js"></script>
   <script type="text/javascript" src="new/babel.min.js"></script>
   
   <script type="text/babel">
           const myId = 'aTgUiGu';
           const myData = 'HelLo,rEaCt';
           //1.创建虚拟DOM
           const vDOM= (
               <h2 className="title" id={myId.toLowerCase()}>
                   <span style={{color:'white',fontSize:'20px'}}>{myData.toLowerCase()}</span>
                   <good>小写开头自定义html标签，报错能显示</good>
                   <Good>不显示且报错，需要定义组件Good</Good>
       </h2>
               // <div>错误，只能有一个根标签</div>
           );
           //渲染虚拟DOM到页面
           ReactDOM.render(vDOM,document.getElementById('test'));
   </script>
   ```


1. 练习与延申

   ```jsx
   //模拟数据
   const data = ['Angular','React','Vue'];
   //1.创建虚拟DOM
   const vDom = (
   	<div>
       	<h1>前端JS框架列表</h1>
           <ul>
             {
           	data.map((item,index)={ //注意此处只能使用JS表达式，不能使用JS（流程控制）语句，即必须要有返回值
                   return <li key={index}>{item}</li //变量使用{}
               })
             }
           </ul>
       </div>
   );
   //2.渲染虚拟DOM到页面
   ReactDOM.render(vDom, document.getElementById('test'));
   ```

## 模块与组件

1. 模块：向外提供特定功能的js程序，一般就是一个js文件。

2. 组件：用来实现局部功能效果的代码和资源的集合（html/css/js/image等）

3. 模块化：当应用的js都以模块来编写，这个应用就是模块化的应用

4. 组件化：当应用以多组件的方式实现，这个应用就是一个组件化。

5. 使用谷歌插件React Developer Tools可以查看当前网页是否使用React。

6. React分为两种组件方式

   1. 函数式组件

      ```html
      <script type="text/babel">
      	//1.创建函数式组件
          function Demo(){ //注意函数名大写，JSX语法规则
              console.log(this);
              /*Babel官网使用Try it out复制整段函数代码:
              	'use strict'严格模式，this不能指向window undefined*/
              return <h2>我是用函数定义的组件（适用于【简单组件】的定义）</h2>
          }
          //2.渲染组件到页面 - 函数类型不能作为React节点（React child）
          ReactDOM.render(<Demo/>,document.getElementById('test'));
          //错误：ReactDOM.render(Demo,document.getElementById('test'));
      /*
      执行了ReactDOM.render(<Demo/>....之后，发生了什么
      	1.React解析组件标签，找到了Demo组件
      	2.发现组件是使用函数定义的，随后调用该函数，将返回的虚拟DOM转为真实DOM，随后呈现在页面中。
      */
      </script>
      ```

   2. 类式组件

      ```html
      <script type="text/javascript">
          //复习： 创建一个Person类
          class Person{
              //构造器方法
              constructor(name,age){
                  this.name=name;
                  this.age=age;
              }
              speak(){ console.log(`我叫${this.name}`) }
              //普通方法，放在类的原型对象上，供实例使用
          }
          //创建一个Person的实例对象
          const p1 = new Person()
          console.log(p1);
          class Student extends Person{
              constructor(name,age,grade){
                  /*this.name = name
                  super规则：一个类，继承另一个类，且重写构造器这三个因素，必须调用super。*/
                  super(name,age);
                  this.grade=grade;
              }
          }
          const s1 = new Student('校长',26,'高层');
          s1.speak();//没有重写speak方法，找的是原型链__proto__的__proto__的speak
      </script>
      <script type="text/babel">
      	//1.创建类式组件
      	class MyComponent extends React.Component {
      		render(){
      			//render是放在哪里的？  --- 类的原型对象上，供实例使用
      			
      			//render中的this是谁？ --- MyComponent的实例对象 = MyComponent组件实例对象
      			console.log('render中的this' , this);
      			/*输出包括，其中组件实例（表示类组件）重要的三大属性，prop:{},refs:{},state:null*/
      			
      			return (
      				<div>我是用类定义的组件（适用于【复杂组件】的定义） </div>
      			)
      		}
      	}
      	//2.渲染组件到页面
      	ReactDom.render(<MyComponent/>,document.getElementById('test'));
      	/*此处的render与类中的render毫无关系
      	执行了ReactDOM.render(<Demo/>....之后，发生了什么
      		1.React解析组件标签，找到了MyComponent组件
      		2.发现组件时使用类定义的，随后new出来该类的实例，并通过该实例调用到原型上的render方法。
      		3.将render返回的虚拟DOM转为真实DOM，随后呈现在页面中。
      	*/
      </script>
      ```

7. **组件实例三大核心属性之**：**state**（类组件的实例对象才会有，只有类才有实例对象，函数组件没有，未来通过hooks可以让函数也有）

   1. 理解：

      1. state是组件对象最重要的属性，值是对象（可以包含多个key-value的组合）
      2. 组件被称为“状态机”，通过更新组件的state来更新对应的页面显示（重新渲染组件）
      3. state表示有状态，复杂组件会有（实例对象）

   2. 注意：

      1. 组件中render方法中的this为组件实例对象
      2. 组件自定义的方法中this为undefined，如何解读
         1. 强制绑定this：通过函数对象的bind()
         2. 箭头函数
      3. 状态数据，不能直接修改或更新。

   3. 巩固知识

      ```js
        function demo1(){
            console.log(this);
        }
        demo1(); //Window{..}
        function demo2(){
            'use strict';
            console.log(this);
        }
        demo2(); //undefined
      ```

   4. 扩展知识

      ```js
        console.log(demo1.bind({a:1,b:2})) //.bind()返回的是新函数（未调用）
        /*ƒ demo1(){
            console.log(this);
        }*/
        const b = demo1.bind({a:1,b:2});
        b() //调用新函数 {a: 1, b: 2}
      ```

   5. 讲解state [未整理说明示例](https://github.com/brant8/vue2-3-Css/blob/main/React%E7%AC%94%E8%AE%B0%E8%B5%84%E6%96%99/demo5_class_instance_component.html)：构造器写法

      ```js
      //1.创建函数式组件
      class Weather extends React.Component{
          constructor(props) {
              super(props);
              this.state = {isHot:false,wind:'大风'};//因为React有内置setState，所以此处使用state
              this.demo = this.changeWeather.bind(this);
              /*this.changeWeather.bind(this)的解析
              this - 原型
              .changeWeather - 原型对象上的changeWeather
              .bind(this) - .bind()生成新的函数，并且把this改成Weather的实例对象
              this.demo - 挂载的地方
              */
          }
          render(){
              console.log(this);
              const {isHot} = this.state;
              const {isHot, windd} = this.state;
              return <h1 onClick={this.demo}>今天天气很{isHot ? '炎热':'凉爽'}，今天有大风</h1>
          }
          changeWeather(){
              console.log(this);//默认this是undefined，需要bind()重新指向
              /*严重注意：状态state不可以直接更改，要借助一个内置API去更改
              this.state.isHot = !isHot; 是错误的*/
              const isHot = this.state.isHot;
              this.setState({isHot:!isHot});
          }
      }
      //2.渲染虚拟DOM到页面
      ReactDOM.render(<Weather/>,document.getElementById('test'));
      ```

      1. `changeWeather()`在原型对象上，供实例使用
      2. React把在HTML上的事件从`onclick`改成`onClick`
      3. `state`不能直接赋()值，若render中有多个状态需要更改，单个值无法实现
      4. 类中的方法默认开启了局部的严格模式，[参考](https://github.com/brant8/vue2-3-Css/blob/main/React%E7%AC%94%E8%AE%B0%E8%B5%84%E6%96%99/demo6_this_relearn.html)
      5. 状态state不可以直接更改，要借助一个内置API`.setState({})`去更改
      6. `.setState({})`是合并的行为，非覆盖式

   6. 基础巩固

      ```js
      class Car{
          constructor(name,price){
              this.name=name;
              this.price=price
              this.wheel=4
          }
          //类中可以直接写赋值语句，下面代码的含义：给Car的示例对象添加一个属性，名为a，值为1；不通过构造器
          a=1;
          /*
          类 非函数体，不能随意定义变量、console.log()、alert，也不能直接声明变量
          */
          static demo = 100;
      }
      const c1 = new Car('宝马',299); 
      console.log(c1);//实例中带有 a:1
      console.log(Car.demo); //输出100
      ```

   7. state写法二：无构造器写法

      ```js
      //1.创建函数式组件
      class Weather extends React.Component{
          render(){
              const {isHot} = this.state;
              const {isHot, windd} = this.state;
              return <h1 onClick={this.demo}>今天天气很{isHot ? '炎热':'凉爽'}，今天有大风</h1>
          }
          state = {isHot:false,wind:'微风'};
          /* 报错失败
          changeWeather=function(){
              const isHot = this.state.isHot;
              this.setState({isHot:!isHot});
          }*/
          //箭头函数可以成功；因为箭头函数没有自己的this，找上一层的this
          changeWeather=()=>{
              const isHot = this.state.isHot;
              this.setState({isHot:!isHot});
          }
          //自定义方法 ---要用赋值语句的形式+箭头函数
      }
      //2.渲染虚拟DOM到页面
      ReactDOM.render(<Weather/>,document.getElementById('test'));
      ```

8. **组件实例三大核心属性之**：**props** （function函数组件可以有props - 因为函数可以接收参数）

   1. 理解：

      1. 每个组件都会有props（properties的简写）属性
      2. 组件标签的所有属性都保存在props中

   2. 作用：

      1. 通过标签属性从组件外向组件内传递变化的数据
      2. 注意：组件内部不需要修改props数据（只读）

   3. 知识巩固

      ```js
      let arr1 = [1,3,5,7];
      let arr2 = [2,4,6,8];
      console.log(...arr1); //展开数组分别输出
      let arr3 = [...arr1,...arr2];
      console.log(arr3); //连接数组
      function sum(...num){
          console.log('@',num); //数组(3)[1, 2, 3]
          return num.reduce((pre,current)=>{ //reduce有返回值
              return pre+current;
          })
      }
      console.log(sum(1,2,3));  //求和
      
      let person = {name:'tom',age:18};
      //let person2 = person; //复制对象，此处非复制，知识传递地址引用，深复制、浅复制
      let person2 = {...person}; //新语法，展开语法（火狐MDN），复制对象
      // console.log(...person);  //报错，无法展开一个对象
      console.log(person2.name);
      
      let person3 = {...person,name:'jack'};//合并，复制的同时修改属性值
      console.log(person3);
      ```

   4. 单个传递以及批量传递props（标签属性）

      ```jsx
      //创建组件
      class Person extends React.Component{
          render(){
              console.log(this); //props: {name: 'Tom', age: '18'}
              // const {name,age} = this.props;   解构赋值 Destructuring
              /*this.props.name = 'Jack';  报错，props是只读的*/
              return (
                  <ul>
                      <li>姓名：{this.props.name}</li>
                      //<li>姓名：{name}</li> 解构赋值
                      <li>性别：男</li>
                      <li>年龄：{this.props.age}</li>
                  </ul>
              );
          }
      }
      const p = {name:'老刘',age:18,sex:'女'};
      
      //渲染
      ReactDOM.render(<Person name="Tom2" age='18'/>,document.getElementById('test'));
      ReactDOM.render(<Person name="Tom2" age={18}/>,document.getElementById('test'));//Number是JS的所以要加{}
      
      ReactDOM.render(<Person name={p.name} age={p.age}/>,document.getElementById('test2'));
      ReactDOM.render(<Person {...p}/>,document.getElementById('test2')); //相当于上面一行的语法糖
      /*babel和react.development两个可以展开对象，但【仅限于】标签属性的传递，{...p}
          console.log('@',...p); //没有显示任何内容，对象的展开不能随便用
          正常情况JS不能展开对象*/
      ```

   5. props渲染传递时的注意点

      1. 对传递标签属性的类型限制，比如age
      2. 对于必要性的限制，比如某个属性必须要传
      3. 给某个属性指定默认值
      4. props是只读的

      ```html
      <script type="text/javascript" src="new/react.development.js"></script> <!--React-->
      <script type="text/javascript" src="new/react-dom.development.js"></script><!--ReactDOM-->
      <script type="text/javascript" src="new/babel.min.js"></script>
      <script type="text/javascript" src="new/prop-types.js"></script> <!--用于对组件标签属性进行限制 PropTypes-->
      <script type="text/babel">
          //创建组件
          class Person extends React.Component{
              render(){
                  return (
                      <ul>
                          <li>姓名：{this.props.name}</li>
                          //<li>姓名：{name}</li> 解构赋值
                          <li>性别：男</li>
                          <li>年龄：{this.props.age}</li>
                      </ul>
                  );
              }
          }
          
          //prop属性规则，固定名字，React会在类中的属性查找propTypes当作规则
          Person.propTypes = { 
              /*name:React.PropTypes.string, //具体的规则时大写PropTypes,此处适用于React版本15.5以前，让React不会太臃肿*/
              //React版本16对于规则限制需要导入：prop-types.js才有的PropTypes
              name:PropTypes.string.isRequired, 
              sex:PropTypes.string,
              age:PropTypes.number,
              speak:Proptypes.func,//限制传递需要传入函数
          }
          Person.defaultProps = {
              sex:'不男不女', //设置默认值
              age:18
          }
          
          const p = {name:'老刘',age:18,sex:'女'};
      	function speak(){
      		console.log('我说话了');
      	}
      	
          //渲染
          ReactDOM.render(<Person name="Tom2" age='18' speak={speak}/>,document.getElementById('test'));
      
      </script>
      ```

   6. props的简写

      ```jsx
      class Person extends React.Component{
          //规则写到类内部，让类自身有其属性
          static propTypes = { 
              name:PropTypes.string.isRequired, 
              sex:PropTypes.string,
              age:PropTypes.number,
              speak:Proptypes.func,
          };
          static defaultProps = {
              sex:'不男不女', 
              age:18
          };
          state = {}; //初始化状态
          render(){
              return (
                  <ul>
                      <li>姓名：{this.props.name}</li>
                      //<li>姓名：{name}</li> 解构赋值
                      <li>性别：男</li>
                      <li>年龄：{this.props.age}</li>
                  </ul>
              );
          }
      }
      
      const p = {name:'老刘',age:18,sex:'女'};
      function speak(){
          console.log('我说话了');
      }
      //渲染
      ReactDOM.render(<Person name="Tom2" age='18' speak={speak}/>,document.getElementById('test'));
      ```

   7. 通过类组件构造器给`props`传参的必要性

      1. 在React中，构造函数仅用于以下两种情况（官方）
         1. 通过给`this.state`赋值对象来初始化内部state
         2. 为事件处理函数绑定实例
      2. 在`React.Component`子类实现构造函数时，应在其他语句之前调用`super(props)`。否则，`this.props`在构造函数中可能会出现未定义的bug。

      ```js
      class Person extends React.Component{
          //要么不用构造器，要么有构造器必须传props
          constructor(props){
              super(props);
              console.log('constructor',this.props) //输出：正常对象{...}
              console.log('constructor',props)//与上面this.props一样
          }
          //有构造器不传props时，或者接props但不传给super时
          constructor(){
              super();
              console.log('constructor',this.props) //输出：undefined
          }
          state ={}; //初始化方法
          demo = ()=>{...} //自定义方法+箭头函数，this保持指向实例
          static propTypes = {..}//对标签属性进行类型、必要性的限制
          static defaultProps = {..}//指定标签默认属性值
          render(){...}
          ReactDome.render(<Person name="jerry"/>,document.getElementById('test'));
      }
      ```

   8. **函数式组件的 props**

      ```js
      function Person(props){
          console.log(props);
          return (
          	<ul>
              	<li>姓名：{props.name}</li>
      			<li>性别：{props.gender}</li>
      			<li>年龄：{props.age}</li>
              </ul>
          );
      }
      Person.propTypes = {..}//对标签属性进行类型、必要性的限制
      Person.defaultProps = {..}//指定标签默认属性值
      ReactDome.render(<Person name="jerry" gender="女" age={18}/>,document.getElementById('test'));
      ```

9. **组件实例三大核心属性之**：**refs** 

   1. 组件内的标签，可以定义ref属性来标识自己

   2. **字符串形式的ref**（最老式写法/过时） `<input ref="input1" />`

      1. 舍弃原因：过多使用string形式的ref，会有效率问题
      2. 未来版本可能会移除，当前学习版本16.8；弹幕 React 18.2已移除

      ```js
      //代码示例
       class Demo extends React.Component{
              showData = ()=>{
                  /*render中return的<input id="input1" type="text" placeholder="点击按钮提示数据"/>
                  const input1 = document.getElementById('input1');
                  alert(input1.value);*/
                  console.log(this);
                  console.log(this.refs.input1); //refs不获取虚拟DOM，而是获取真实DOM节点
                  debugger;
                  const {input1} = this.refs;
                  alert(input1.value);
              }
              showData2 = ()=>{
                  const {input2} = this.refs;
                  alert(input2.value);
              }
              render(){
                  return (
                      <div>
                          <input ref="input1" type="text" placeholder="点击按钮提示数据"/>&nbsp;
                          <button ref="button1" onClick={this.showData}>点我提示左侧的数据</button> &nbsp;
                          <input onBlur={this.showData2} ref="input2" type="text" placeholder="失去焦点提示数据"/>
                      </div>
                  );
              }
          }
      
          ReactDOM.render(<Demo/>,document.getElementById('test'));
      ```

   3. **回调形式的ref**  `<input ref={ (c)=>{this.input1=c} } />`

      1. 回调ref回调函数是以内联函数的方式定义的。
      2. 在**更新**过程中，ref会被执行两次，第一次传入参数null，第二次传入参数DOM元素。比如更新天气（非与用户输入交互）。
      3. 这是因为每次渲染时，会创建一个新的函数实例，所以React清空旧`render()`的ref并设置新的。通过将ref的回调函数定义成**class的绑定函数**的方式可以避免上述问题，但大多数清空下是无关要紧的。

      ```jsx
      class Demo extends React.Component{
          showData = ()=>{
              debugger;
              const {input1} = this; //解构赋值，直接从实例上取属性
              alert(input1.value);
          }
          showData2 = ()=>{
              const {input2} = this; //解构赋值，直接从实例上取属性
              alert(input2.value);
          }
          saveInput = ()=>{
              this.input1 = c;
              console.log('@',c);
          }
          render(){
              return (
                  <div>
                  <input ref={ (a)=>{ console.log(a) } } type="text" placeholder="点击按钮提示数据"/> 
                      {/*输出当前节点<input ../>，且此处书写形式称为内联函数*/}
          
          		<input ref={ (a)=>{ this.input1 = a } } placeholder="点击按钮提示数据"/>&nbsp; 
      			 {/*'this.input1=a'表示ref拿到当前节点'a'<input>放在组件自身上，给与属性input1，其中的this因为其是箭头函数，向上找调用者，在render()中的this，其调用者为组件实例对象*/}
      
      			<input ref={ (currentNode)=>{ this.input1 = currentNode } } placeholder="点击按钮提示数据"/>&nbsp;  {/*正常模式*/}
      
      			<input ref={ c=> this.input1 = c } placeholder="点击按钮提示数据"/>&nbsp;  
      			 {/*简写模式*/}
                  <input ref={ this.saveInput } placeholder="点击按钮提示数据"/>&nbsp; 
                   {/*class的绑定函数，上面的3-3, 不会造成像 ref与箭头函数 在更新过程中出现null值（就算有null值一般不会影响代码运行）*/}   
      		
      			<button onClick={this.showData}>点我提示左侧的数据</button> &nbsp;
      			<input onBlur={this.showData2} ref={ c=> this.input2 = c } type="text" placeholder="失去焦点提示数据"/>
          </div>
      		);
      	}
      }
      
      ReactDOM.render(<Demo/>,document.getElementById('test'));
      ```

   4. **createRef创建ref容器**（最新式写法）`myRef = React.createRef()`

      1. `React.createRef`调用后可以返回一个容器，该容器可以存储被ref 所标识的节点；该容器是”专人专用“的（只能存一个节点，后存的会覆盖前面的）

      ```jsx
          class Demo extends React.Component{
              
              myRef = React.createRef();
      
              showData = ()=>{
                  console.log(this.myRef); //输出 {current:input} - {键：值}
                  console.log(this.myRef.current.value);//输出 用户键入的值
              }
      
              render(){
                  return (
                      <div>
                          <input ref={this.myRef} type="text" placeholder="点击按钮提示数据"/>&nbsp;
                          {/*React.createRef得到一个容器myRef，当ref标记后，<input>节点会存到this.myRef中*/}
                          <button ref="button1" onClick={this.showData}>点我提示左侧的数据</button> &nbsp;
                      </div>
                  );
              }
          }
      
          ReactDOM.render(<Demo/>,document.getElementById('test'));
      
      ```

10. react中的事件处理

    1. 通过onXxx属性指定事件处理函数（注意大小写）
       1. React使用的是自定义（合成）事件，而不是使用的原生DOM事件 ---为了更好的兼容性
       2. React中的事件是通过事件委托方式处理的（委托给组件最外层的元素），比如`<ul><li>`冒泡 --- 为了高效
    2. 通过 Event.target 得到发生事件的DOM元素对象

## 组件

1. 表单的 非受控组件 - **UnControlled Component**：

   1. 现用现取的组件，用户提交表单后获取的数据，即为非受控组件

   2. 有多少个变量就需要写多少个 ref

      ```js
          class Login extends React.Component{
              handleSubmit = (event)=>{
                  event.preventDefault();//原生JS阻止表单提交（页面刷新跳转—）
                  const {username,password} = this;
                  alert(`你输入的用户名是${username.value}`);
              }
              render(){
                  return (
                      <form onSubmit={this.handleSubmit}>
                          用户名：<input ref={c=>this.username=c} type="text" name="username"/>
                          密码：  <input ref={c=>this.password=c} type="password" name="password"/>
                          <button>登录</button>
                      </form>
                  );
              }
          }
      
          ReactDOM.render(<Login/>,document.getElementById('test'));
      ```

2. 表单的 受控组件（Vue中的双向数据绑定）

   1. 获取的数据存到state状态中，用户但凡对表单有任何状态更改都可以即时接收到反馈。

   2. 不必写ref。（推荐，官方不推荐过度使用ref）

      ```js
       class Login extends React.Component{
              //初始化状态
              state = {
                  username:'',
                  password:''
              }
              //保存用户名到状态中
              saveUsername=(event)=>{
                  console.log(event.target.value);
                  this.setState({username:event.target.value}); //可查看开发者工具检查是否设置（最好初始化state）
              }
              handleSubmit = (event)=>{
                  event.preventDefault();//原生JS阻止表单提交（页面刷新跳转—）
                  alert(`你输入的用户名是${username}`);
              }
              render(){
                  return (
                      <form onSubmit={this.handleSubmit}>
                          用户名：<input onChange={this.saveUsername} type="text" name="username"/>
                          密码：  <input type="password" name="password"/>
                          <button>登录</button>
                      </form>
                  );
              }
          }
      
          ReactDOM.render(<Login/>,document.getElementById('test'));
      ```

3. 高阶函数 - 函数柯里化

   1. 优化受控组件代码，剔除重复代码

      ```js
       class Login extends React.Component{
              //初始化状态
              state = {
                  username:'',
                  password:''
              }
           	//保存表单数据到状态中
              saveFormData=(dataType)=>{  //此刻的SaveFormData就是【高级函数】
                  console.log(dataType);
                  return (event)=>{ //实际调用者onChange
                      console.log(dataType, event.target.value);
                      this.setState({[dataType]:event.target.value}); //注意此处用[]
                  } 
              }
              handleSubmit = (event)=>{
                  event.preventDefault();//原生JS阻止表单提交（页面刷新跳转—）
                  alert(`你输入的用户名是${username}`);
              }
              render(){
                  return (
                      <form onSubmit={this.handleSubmit}>
                          用户名：<input onChange={this.saveFormData('username')} type="text" name="username"/>
                         {/*onClick={this.changeWeather()} 不能加括号（），否则还没点击就被调用
                         onChange={this.saveFormData()} 当saveFormData没有返回箭头函数，只是this.setState时，表示把saveFormData函数的返回值给onChange（回调），此处是undefined值给onChange
                         onChange={this.saveFormData} 表示把saveFormData函数最为回调给onChange
                         本例中：saveFormData的返回值也是一个函数this.SaveFormData() 给onChange做回调*/}
                          密码：  <input onChange={this.saveFormData('password')} type="password" name="password"/>
                          <button>登录</button>
                      </form>
                  );
              }
          }
      
          ReactDOM.render(<Login/>,document.getElementById('test'));
      ```

   2. 复习，为啥上面存状态使用`[]`

      ```js
      //对象相关的知识
      let a = 'name';
      let oj = {} //让其变成这样 -> {name:'tom'}
      obj.name = 'tom'； //手动形式
      obj[a] = 'tom'; //自动形式
      console.log(obj);
      ```

   3. 高级函数：如果一个函数符合下面两个规范中的任何一个，那该函数就是高阶函数

      1. 若A函数，接收的参数是一个函数，那么A就可以成为高阶函数。
      2. 若A函数，调用的返回值依然是一个函数，那么A就可以称之为高阶函数。

   4. 常见的高阶函数：

      1. Promise： `new Promise( ()=>{} )`
      2. setTimeout：`setTimeout( ()=>{} )`
      3. arr.map() ...

   5. **函数的柯里化**：通过函数调用继续返回函数的方式，实现多次接收参数,最后统一处理的函数编码形式。

      ```js
      //普通写法
      function sum(a,b,c){
          return a+b+c;
      }
      const result = sum(1,2,3);
      //柯里化写法
      function sum(a){
          return (b)=>{
              return (c)=>{
                  return a+b+c; //统一处理
              }
          }
      }
      const result = sum(1)(2)(3);
      ```

   6. 不用柯里化的写法

      ```js
       class Login extends React.Component{
              //初始化状态
              state = {
                  username:'',
                  password:''
              }
           	//保存表单数据到状态中
              saveFormData=(dataType,value)=>{
                  this.setState({[dataType]:value}); //注意此处用[]
              }
           
              handleSubmit = (event)=>{
                  event.preventDefault();
                  alert(`你输入的用户名是${username}`);
              }
              render(){
                  return (
                      <form onSubmit={this.handleSubmit}>
                      	/*onChange需要一个函数，则给他一个()=>{}，在此调用的是onChange事件，所以有event*/
                          用户名：<input onChange={(event)=>{this.saveFormData('username',event.target.value)}} type="text" name="username"/>
                          密码：  <input onChange={(event)=>{this.saveFormData('password',event.target.value)}} type="password" name="password"/>
                          <button>登录</button>
                      </form>
                  );
              }
          }
      
          ReactDOM.render(<Login/>,document.getElementById('test'));
      ```

4. React组件生命周期

   ```js
    //创建组件
       class Life extends React.Component{
           /*挂载组件 mount
           卸载组件 unmount
           生命周期回调函数 <=> 生命周钩子函数
           */
           state = {opacity:0};
           death = ()=>{
               //卸载之前，清除定时器
               clearInterval(this.timer); //方式一
               //卸载组件
               ReactDOM.unmountComponentAtNode(document.getElementById('test')); //注意JS方式获取节点
           }
           //组件挂在页面之后调用（只调用一次）
           componentDidMount(){ /*componentDidMount与render均属于React预置函数， 类Life实例对象X.componentDidMount*/
               this.timer = setInterval(()=>{
                   let {opacity} = this.state;
                   //减小0.1
                   opacity -= 0.1;
                   if(opacity<=0) opacity = 1;
                   //设置新的透明度
                   this.setState({opacity:opacity}); /*同名简写：this.setState({opacity});*/
               },200);
           }
           //组件将要卸载的时候调用
           componentWillUnmount(){
               //卸载之前，清除定时器
               clearInterval(this.timer); //方式二
           }
           /*类中不可以随便写js代码，函数内可以*/
           //render调用的时机：初始化渲染、状态更新之后
           render(){ /*render每次改变状态state都会调用*/
               console.log('render()...')
               return (
                   <div>
                       <h2 style={{opacity:this.state.opacity}}>React学不会怎么办？</h2>
                       <button onClick={this.death}>不活了</button>
                   </div>
               )
           }
       }
       //渲染
       ReactDOM.render(<Life/>,document.getElementById('test'));
   
   ```

5. **生命周期流程图（旧 版本16）** ![旧流程图](https://github.com/brant8/vue2-3-Css/blob/main/pictures/react_lifecycle_old.png)

6. 生命流程图说明 - 案例

   1. 初始化阶段：由`ReactDOM.render()`触发 --- 初次渲染 (组件的使用)
      1. `constructor()`
      2. ``componentWillMount()` 
      3. `render()` ：必须用
      4. `componentDidMount()` ：常用于初始化的事，比如，开启定时器、发送网络请求、订阅消息
   2. 更新阶段：由组件内部`this.setState()`或`父组件`重新render触发
      1. `shouldComponentUpdate()`、`componentWillUpdate()`、`render()`、`componentDidUpdate()`
   3. 卸载组件：由`ReactDOM.unmountComponentAtNode()`触发
      1. `componentWillUnmount()`：常用在做一些收尾的事，比如，关闭定时器、取消订阅消息

   ```js
   //创建组件
       class Count extends React.Component{
           //挂载1. 构造器，当有构造器的时候，一般state写在构造器内部
           constructor(props) {
               super(props);
               console.log('Count - constructor...');
               this.state = {count:0};
           }
           //没有构造器的时候state写在外面
           //state= { count:0 };
           add = ()=>{
               //获取原状态
               const {count} = this.state;
               //更新状态
               this.setState({count:count+1});
           }
           //挂载2. 组件将要挂载的钩子， Warning: componentWillMount has been renamed
           componentWillMount(){
               console.log('Count - componentWillMount...');
           }
           //挂载4. 组件挂在完毕的钩子
           componentDidMount(){
               console.log('Count - componentDidMount...');
           }
   
           //挂载5.1 卸载组件按钮的回调
           remove=()=>{
               ReactDOM.unmountComponentAtNode(document.getElementById('test'));
           }
           /*【setState()】设置状态
           *【forceUpdate()】强制更新，跳过阀门，直接componentWillUpdate() */
           //强制更新回调
           force = ()=>{
               this.forceUpdate();
           }
   
           //挂载5.2 将要卸载钩子 ；  设置状态5.
           componentWillUnmount(){
               console.log('Count - componentWillUnmount...');
           }
   
           //设置状态1. 组件是否更新 ； 挂载时默认为true ；
           shouldComponentUpdate(){ /*相当于'阀门'，控制组件是否更新*/
               console.log('Count - shouldComponentUpdate...');
               return true; //手动写了阀门，必须写true or false
           }
   
           //设置状态2. 组件将要更新；
           componentWillUpdate(){
               console.log('Count - componentWillUpdate...');
           }
           //设置状态4. 组件更新完毕的钩子;
           componentDidUpdate(preProps,preState){
               console.log('Count - componentDidUpdate...',preProps,preState);
           }
           //挂载3. ； 设置状态3.
           render(){
               console.log('Count - render...');
               return (
                   <div>
                       <h2>当前求和: { this.state.count }</h2>
                       <button onClick={this.add}>点我+1</button>
                       <button onClick={this.remove}>卸载组件</button>
                       <button onClick={this.force}>不更改任何状态中的数据，强制更新以下</button>
                   </div>
               )
           }
       }
   
       class A extends React.Component{
           //初始化状态
           state = {carName:'奔驰'};
           changeCar = ()=>{
               this.setState({carName:'奥托'});
           };
           render(){
               return (
                   <div>
                       <div>我是A组件</div>
                       <button onClick={this.changeCar}>换车</button>
                       <B/>
                       <B carName={this.state.carName}/>
                   </div>
               );
           }
       }
   
       class B extends React.Component{
           componentDidMount(){
               console.log('B - componentDidMount...')
           }
           //组件将要接收新的props的钩子
           componentWillReceiveProps(props){ /*真正意思：componentWillReceiveNewProps*/
               console.log('B - componentWillReceiveProps...',props);
           }
           render(){
               return (/*页面打开的时候this.props.carName就有接收到钩子，但是componentWillReceiveProps得第二次（手动点击一次）才会有新的Props传递*/
                   <div>我是B组件，从A接收到的换车是: {this.props.carName}</div>
               );
           }
       }
       //渲染
       // ReactDOM.render(<Count/>,document.getElementById('test'));
       //让A和B形成父子关系：A为父，B为子
       ReactDOM.render(<A/>,document.getElementById('test'));
   
   ```

7. **react生命周期（新 版本17）** ![生命周期](https://github.com/brant8/vue2-3-Css/blob/main/pictures/react_lifecycle_new.png)

8. react新版17更改：所有Will的方法都改为UNSAFE，除Will了Unmount

   1. `componentWillUpdate` 改名为 `UNSAFE_componentWillUpdate`

   2. `componentWillMount` 改名为 `UNSAFE_componentWillMount`

   3. `componentWillReceiveProps` 改名为 `UNSAFE_componentWillReceiveProps`

   4. 更改原因：这些在未来的异步渲染中，容易被误解滥用，导致潜在问题。

   5. 未来版本强制使用UNSAFE，否则报错。

   6. 多出来的两个新钩子（实际用处不大）

      ```js
      //示例
      static getDerivedStateFromProps(props，state){
          console.log('Count - getDerivedStateFromProps...',props,state);
          return null; //必须由返回值，null或state Obj（状态对象），null不影响任何
          return {count:109}; //影响状态更新
          return props; //props被当作状态用，props来自<A name='Tom'/>
      }
      /*此方法适用于罕见的用例，即state的值在任何时候都取决于props。
      派生状态会导致代码冗余，并使组件难以维护*/
      ```

      ```js
      getSnapshotBeforeUpdate(preProps,preState){
          console.log('getSnapshotBeforeUpdate...',preProps,preState);
          return null; //返回null或者snapshot值（任何值）
      }
      /*在最近一次渲染输出（提交到DOM节点）之前调用。是的组件能在发生更改之前从DOM中捕获一些信息，比如：滚动位置。
      在生命周期的任何返回值将作为参数传递给componentDidUpdate(preProps,preState,snapshotValue)
      用法并不常见，但可能出现在UI处理中，比如需要以特殊方式处理滚动位置的聊天线程等*/
      /*
        getSnapshotBeforeUpdate
      	React更新DOM和refs ↓
        componentDidUpdate
      */
      ```

9. 重要的勾子

   1. render：初始化渲染或更新渲染调用
   2. componentDidMount：开启监听，发送ajax请求
   3. componentWillUnmount：做一些收尾工作，比如，清理定时器

10. 经典面试题

    1. react/vuew中的key由什么作用，内部原理事什么？

    2. 为什么遍历列表时，key最好不要用index？

    3. 虚拟DOM中key的作用：

       1. 简单的说：key事虚拟DOM对象的标识，在更新显示时，key起着及其重要的作用。
       2. 详细的说：当状态中的数据发生变化时，react会根据【新数据】生成【新的虚拟DOM】，随后React进行【新虚拟DOM】与【旧虚拟DOM】的diff比较，比较规则如下：
          1. 旧虚拟DOM找到了与新虚拟DOM相关的key
             1. 若虚拟DOM中内容没变，直接使用之前的真实DOM
             2. 若虚拟DOM中内容变了，则生成新的真实DOM，随后替换掉页面中之前的真实DOM
          2. 旧虚拟DOM中未找到与新虚拟DOM相同的key
             1. 根据数据创建新的真实DOM，随后渲染到页面

    4. 用index作为key*可能*会引发的问题

       1. 若对数据进行：逆序添加、逆序删除等破坏顺序操作（比如：添加数据在原数据之前）
          1. 会产生没有必要的真实DOM更新 --> 界面效果没有问题，但是效率低
       2. 如果结构中还包含输入类DOM
          1. 会产生错误DOM更新 --> 界面有问题
       3. 注意：如果不存在对数据逆序添加、逆序删除等破坏顺序操作，仅用于选哪让你列表作用于展示，使用index作为可以是没有问题的。

    5. 开发中如何选择key？

       1. 最好使用每条数据的唯一标识作为key，比如id、手机号、身份证号、学号等唯一值。
       2. 如果确定只是简单的展示数据，用index也是可以的。

    6. 演示代码

       ```js
       //创建组件
           class Person extends React.Component{
               state = {
                   persons:[
                       {id:1,name:'小张',age:18},
                       {id:2,name:'小李',age:19}
                   ]
               }
               add=()=>{
                   //获取原来的数据
                   const {persons} = this.state;
                   const p = {id:persons.length+1, name:'小王',age:20};
                   //更新数据状态
                   // this.setState({persons:[...persons,p]});
                   this.setState({persons:[p,...persons]});
               }
               render(){
                   return (
                       <div>
                           <h2>展示人员信息</h2>
                           <button onClick={this.add}>添加一个小王</button>
                           <h3>使用Index索引值作为key</h3>
                           <ul>
                           {this.state.persons.map((personObj,index)=>{
                               return <li key={index}>{personObj.id} - {personObj.name} - {personObj.age}<input type="text"/></li>
                               })}
                           </ul>
                           <hr/>
                           <h3>使用id唯一标识符作为key</h3>
                           <ul>
                               {this.state.persons.map((personObj,index)=>{
                                   return <li key={personObj.id}>{personObj.id} - {personObj.name} - {personObj.age}<input type="text"/></li>
                               })}
                           </ul>
                       </div>
                   )
               }
           }
           ReactDOM.render(<Person/>,document.getElementById('test'));
           /*慢动作回放：使用index索引值作为key
           * 初始数据：
           *       {id:1,name:'小张',age:18},
           *       {id:2,name:'小李',age:19}
           * 初始虚拟DOM：
           *       <li key=0>小张---18</li>
           *       <li key=1>小李---19</li>
           * 更新后的数据：
           *       {id:3,name:'小王',age:20},
           *       {id:1,name:'小张',age:18},
           *       {id:2,name:'小李',age:19}
           * 更新后的虚拟DOM：
           *       <li key=0>小王---20</li>
           *       <li key=1>小张---18</li>
           *       <li key=2>小李---19</li>
           * 索引值与对应的内容被打乱了，原来的DOM不能复用，真实DOM全部重新渲染
           * */
       ```

## React脚手架

1. react脚手架：

   1. 用来帮助程序员快速创建一个基于xx库的模板项目
      1. 包含了所有需要的配置（语法检查、jsx编译、devServer...）
      2. 下载好了所有相关的依赖
      3. 可以之间运行一个简单的效果
   2. react提供了一个用于react项目的脚手架库：`create-react-app`
   3. 项目整体技术架构：react + webpack + es6 + eslint
   4. 使用脚手架开发的项目特点：模块化，组件化，工程化

2. 创建项目并启动

   1. 第一步，全局安装：`npm install -g create-react-app`
   2. 第二步，切换到像创建项目的目录，使用敏玲：`create-react-app hello-react`
   3. 第三步，进入项目文件夹：`cd hello-react`
   4. 第四步，启动项目：`npm start`

3. 方式一：yarn相关命令

   ```js
   //如果有必要，安装yarn
   npm install -g yarn
   //starts the development server (常用)
   yarn start
   //bundles the app into static fiels for production
   yarn build
   //starts the test runner
   yarn test
   //removes this tool and copies build dependencies, configuration files and scripts into the app directory.即暴露所有webpack.config.js相关配置，并且不可后退！
   yarn eject
   ```

4. 方式二：npm相关命令

   ```js
   /*Created git commit.
   
   Success! Created react_staging_1 at C:\Users\win10pure\Desktop\otherDoc\react_cli\react_staging_1
   Inside that directory, you can run several commands:*/
   
     npm start
       //Starts the development server.
   
     npm run build
       //Bundles the app into static files for production.
   
     npm test
       //Starts the test runner.
   
     npm run eject
       /*Removes this tool and copies build dependencies, configuration files
       and scripts into the app directory. If you do this, you can’t go back!*/
   
   //We suggest that you begin by typing:
   
     cd react_staging_1
     npm start
   
   //Happy hacking!
   ```

5. 如何运行一个包：查看package.json，当中的`scripts:{ 命令 }`可做参考

6. React生成的目录解构

   1. node_modules：依赖

   2. public - 静态文件：`favicon.icon`（网站页签图标）、**`index.html`（主页面）**、`robots.txt`（爬虫规则文件）、`maifest.json`（应用加壳的配置文件）、`logo192.png`（logo图）、`logo512.png`（logo图）

      ```html
      <!DOCTYPE html> <!--index.html-->
      <html lang="en">
        <head>
          <meta charset="utf-8" />
          <!--%PUBLIC_URL%代表public文件夹的路径，浏览器favicon.ico小图标-->
          <link rel="icon" href="%PUBLIC_URL%/favicon.ico" />
          <!--开启理想视口，用于做移动端网页的适配-->
          <meta name="viewport" content="width=device-width, initial-scale=1" />
          <!--用于配置浏览器页签+地址栏的颜色（只在安卓手机浏览器有用）-->
          <meta name="theme-color" content="#000000" />
          <meta
            name="description"
            content="Web site created using create-react-app"
          />
          <!--苹果手机通过书签方式添加到手机桌面显示的图标-->
          <link rel="apple-touch-icon" href="%PUBLIC_URL%/logo192.png" />
          <!--应用加壳：浏览器应用加壳变成 安卓/iOS 手机应用 ； manifest.json用于配置手机图片、获取手机权限，比如声音等-->
          <link rel="manifest" href="%PUBLIC_URL%/manifest.json" />
          <title>React App</title>
        </head>
        <body>
          <!--若浏览器不支持JS时显示这句-->
          <noscript>You need to enable JavaScript to run this app.</noscript>
          <div id="root"></div>
      
        </body>
      </html>
      
      ```

   3. src - 资源文件：

      1. `App.css`（App组件的样式）、**`App.js`（App组件）**、`App.test.js`（几乎不用）、`index.css`（可迁徙到public目录中并在index.html引用）、**`index.js`（入口文件）**、`logo.svg`（首页logo图片）、`reportWebVitals.js`（页面性能分析文件，需要web-vitals库的支持）、`setupTest.js`（组件单元测试，需要jest-dom库支持）

         ```js
         //index.js 部分讲解
         ReactDOM.render(
           <React.StrictMode>
             <App />
           </React.StrictMode>,
           document.getElementById('root')
         );
         //类似于 ReactDOM.render(<App />, document.getElementById('root') );
         ```

7. 知识巩固 - export暴露 和 import导入

   ```js
   /*-----------module.js-----------*/
   const React = {a:1,b:2}; //定义React对象
   
   React.Component = class Component { //给React对象追加属性
   }
   export default React;
   //分别暴露（针对第二种导入方式）
   export class Component{
   }
   /*-----------index.js-------------*/
   //导入方式一
   import React from './module.js'
   //使用方式一
   console.log(React);
   new React.Component();
   //使用方式二
   const {Component} = React;
   new Component();
   //导入方式二
   import React,{Component} from 'react' //{Component}此处非解构赋值
   new Component();
   ```

8. ReactCLI自动生成目录及其文件例子：地址]()

9. ReactCLI手动生成案例

   1. 创建目录及文件

      1. `public/index.html`、`src/App.js`、`src/index.js`、`src/components/Hello.js`、`src/components/Hello.css`

   2. 普通js文件与组件区分的两种方式

      1. 组件使用大写字母标识，比如`Hello.js`，其他js使用小写形式开头
      2. 组件使用`.jsx`结尾，比如 `Hello.jsx`、`App.jsx`，导入import时,`.js`和`.jsx`可以省略

   3. 其他公司可能导入方式：

      1. `src/components/Hello/`目录下的`Hello.js`使用`index.js`命名，每个组件下都使用该方式
      2. `App.jsx`导入时，可以省略该`index`：`import Hello from './components/Hello'`即可。

   4. CSS样式冲突解决方式一(较少使用)

      1. 组件CSS文件命名为：`Hello/index.module.css`
      2. 在组件内部`Hello/index.jsx`导入使用：`import hello from './index.module.css'`
      3. 在render代码中使用：`<h2 className={hello.title}>Hello,React</h2>`

   5. CSS样式冲突解决方式二：使用less

      1. 在CSS中使用嵌套方式：`.hello{ .title{background-color:orange;} }`

   6. 代码示例

      ```js
      /*---------------index.js-----------------*/
      //引入React核心库
      import React from 'react';
      //引入ReactDOM
      import ReactDOM from 'react-dom';
      //引入App组件
      import App from './App';
      
      //渲染App到页面
      ReactDOM.render(<App/>,document.getElementById('root'));
      /*---------------App.js-----------------*/
      //创建外壳组件App
      import React from 'react';
      import Hello from './components/Hello'
      
      class App extends React.Component{
          render(){
              return (
                  <div>
                      <Hello/>
                  </div>
              );
          }
      }
      //暴露App组件
      export default App;
      /*---------------Hello.js-----------------*/
      import React,{Component} from "react";
      import './Hello.css'; //注意：资源文件需要带目录符号
      
      export default class Hello extends Component{
          render(){
              return (
                  <h1 className="title">Hello React</h1>
              );
          }
      }
      ```

   7. VSCode插件安装推荐：ES7 React/Redux/GraphQL

      1. 快捷键快速插入类组件模板：`rcc`
      2. 快捷键快速插入函数组件模板：`rfc`

10. **案例讲解：todoList**

    1. 子组件与父组件之间的**数据交互**

       1. App通过虚拟数据状态`state.todos`设置初始数据
       2. 通过调用子组件向组件传递数据`<Header a={this.a} addTodo={this.addTodo}/>`
       3. 父组件的方法使用箭头函数，让子组件向父组件传值`addTodo=(todoObj)=>{..}`
       4. 子组件通过组件赋值方式获得父组件传递来的值`const{todos}=this.props`
       5. 子组件同通过父组件传递过来的箭头函数，在子组件中获取的值对象当参数传递给箭头函数，进而给父组件传值`this.props.addTodo(todoObj)`
       6. 子组件通过`.map()`进行遍历数组对象`todos.map((todo,index)=>{...})`
       7. 在render中对于指定事件回调时`<li onMouseLeave={this.handleMouse(false)} ..`，有括号传参，需要使用让其调用的方法使用箭头函数并且返回一个箭头函数才行。`handleMouse=(flag)=>{..}`；关键字：高阶函数。
       8. 同样对事件回调不使用高阶函数，在调用时直接用箭头函数`<button onClick={()=>{this.handleDelete(id)}}`，然后在组件中定义`handleDelete=(id)=>{ console.log('通知App删除',id) }`，避开使用高阶函数
       9. 事件触发时，若使用的是箭头函数，调用者是诸如页面节点时，可以传递`event`
       10. 对propType进行限制，新版React需要额外安装：`npm i prop-types`
       11. 对于删除按钮，使用`window.confirm('确定删除吗')`进行条件判断，不让用户直接删除，从父组件传过来的方法，使用`this.props.deleteTodos(id)`进行接收调用
       12. 使用数组方法`.reduce((前一个值,当前值)=>{return ..},初始值)`进行数量统计，使用方法，查看[MDN](https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Array/Reduce)
       13. 勾选框中的`<div .. checked=true>`必须使用onchange函数，或者使用`defaultChecked`（可临时在Item组件使用），但是defaultChecked只能在第一次的时候奏效（页面第一次加载的时候）；`checked`可以指定多次，且以最后一次指定为主。`<div onChange={this.changeStatus} checked={..}`
       14. 使用`event`获取勾选框的勾选状态，常用在`onChange=XX`的回调函数上，`event.target.checked`；或者获取输入框的值`event.target.value`
       15. 每个App下面的函数调用要对数据进行操作的时候，都要先获取原数据`const {todos} = this.state`才能对数据进行操作加工
       16. 使用数组方法`.filter((objItem)=>{ return 条件 })`对数据进行筛选

    2. index.js（src目录下的）

       ```js
       //引入React核心库
       import React from 'react';
       //引入ReactDOM
       import ReactDOM from 'react-dom';
       //引入App组件
       import App from './App';
       
       //渲染App到页面
       ReactDOM.render(<App/>,document.getElementById('root'));
       ```

    3. App.jsx

       ```jsx
       //创建外壳组件App
       import React from 'react';
       import Header from './components/Header'
       import List from './components/List'
       import Footer from './components/Footer'
       import  './App.css';
       
       class App extends React.Component{
           //初始化状态
           state = {
               todos:[
                   {id:'001',name:'吃饭',done:true},
                   {id:'002',name:'睡觉',done:true},
                   {id:'003',name:'打代码',done:false},
               ]
           };
           //子传父方式：通过箭头函数，在App定义，调用子组件传递给子组件传递该箭头函数，
           a = (data)=>{
               console.log('APP...',data);
           }
           addTodo = (todoObj)=>{ //最好传递一个对象到子组件进行返回给数组对象
               console.log('App',todoObj);
               //获取原todos
               const {todos} = this.state;
               const newTodos = [todoObj,...todos];
               this.setState({todos: newTodos});
           }
           render(){
               const {todos} = this.state;
               return (
                   <div className="todo-container">
                       <div className="todo-wrap">
                           {/* <Header a={1}/>  通过App.js给Header组件传数据*/}
                           <Header a={this.a} addTodo={this.addTodo}/>
                           <List todos={todos}/> {/*todos变量在List组件中也要用todos接收*/}
                           <Footer />
                       </div>
                   </div>
               );
           }
       }
       //暴露App组件
       export default App;
       ```

    4. Header.jsx

       ```jsx
       import React, {Component} from 'react';
       import {nanoid} from 'nanoid';
       import './index.css';
       
       class Index extends Component {
           handleKeyUp = (event)=>{
               //结构赋值获取keyCode,target，下面的值可使用keyCode作为参数
               const {keyCode,target} = event;
               //event.keyCode获得回车确认
               if(keyCode !== 13) return
               //如果为空不能添加
               if(target.value.trim() ===''){
                   alert('输入不能为空')
                   return
               }
                   console.log(event.target.value,event.keyCode);
                   //通过App.jsx传递过来的回调函数，在子函数传递参数即可返回数据给父组件
                   this.props.a(event.target.value);
               //准备好一个todo对象,使用uuid进行唯一值的设定，'npm i uuid'（库占容量大，使用nanoid小巧同时也可以生成uuid）
               const todoObj = {id:nanoid(),name:target.value,done:false};
               //将todoObj传递给App
               this.props.addTodo(todoObj);
               //回车添加完毕后，清空输入
               target.value='';
           }
       
       
           render() {
               return (
                       <div className="todo-header">
                           <input onKeyUp={this.handleKeyUp} type="text" placeholder="请输入你的任务名称，按回车键确认"/>
                       </div>
               );
           }
       }
       
       export default Index;
       ```

    5. List.jsx

       ```jsx
       import React, {Component} from 'react';
       import Item from '../Item';
       import './index.css';
       
       class Index extends Component {
           render() {
               const {todos} = this.props;
               return (
                   <ul className="todo-main">
                       {
                           todos.map((todo,index)=>{
                               return <Item key={todo.id} {...todo}/> /*批量传递参数 ； 在Item中接收方式与傻瓜式传一样*/
                               /* 傻瓜式传递参数到ITEM中
                               return <Item key={todo.id} id={todo.id} name={todo.name} done={this.done}/> */
                               /*Warning:Each child in a list should have a unique "key" prop
                               * 能用id最好用id，否则用key*/
                           })
                       }
                   </ul>
               );
           }
       }
       
       export default Index;
       ```

    6. Item.jsx

       ```jsx
       import React, {Component} from 'react';
       import './index.css';
       
       class Index extends Component {
           render() {
               //接收从List传过来的参数
               const {id,name,done} = this.props;
               return (
                   <li>
                       <label>
                           <input type="checkbox" id={id} defaultChecked={done}/>
                           {/* 使用：checked={done} 报以下错误
                           Warning: You provided a `checked` prop to a form field without an `onChange` handler.
                           This will render a read-only field. If the field should be mutable use `defaultChecked`.
                           defaultChecked={done} 后期会由bug*/}
                           <span>{name}</span>
                       </label>
                       <button className="btn btn-danger" style={{display:'none'}}>删除</button>
                   </li>
               );
           }
       }
       
       export default Index;
       ```

    7. 报错信息部分提示

       ```js
       /**当更新状态非this.setState({mouse:flag});而是this.setState(flag);时报以下错
       * Uncaught Error: setState(...): takes an object of state variables to update or a function
       * which returns an object of state variables.*/
       ```

    8. 知识巩固（案例中用到类似的）

       ```js
       //更改对象中的值(浅拷贝)
       let obj = {a:1,b:2};
       let obj2 = {...obj,b:3}; 
       console.log{obj2}  //{a:1,b:3}
       ```

    9. toDoList案例总结：

       1. 拆分组件、实现静态组件，注意className、style的写法
       2. 动态初始化李彪，如何确定将数据放在哪个组件中的state中？
          1. 某个组件使用：放在自身的state中
          2. 某些组件使用：放在他们共同的父组件state中（官方称此操作为：状态提升）
       3. 关于父子之间通信：
          1. 【父组件】给【子组件】传递数据：通过props传递
          2. 【子组件】给【父组件】传递数据：通过props传递，要求父提前给子传递一个函数
       4. 注意defaultChecked和checked的区别，类似的还有 defaultValue 和 value
       5. 状态在哪里，操作状态的方法就在哪里

## React与Ajax

1. 说明

   1. React本身只关注于界面，并包含发送ajax 请求的代码。
   2. 前端应用需要通过ajax请求与后端进行交互（json 数据）
   3. react应用中需要继承第三方ajax（或自己封装）

2. 常用的ajax请求库

   1. jQuery：比较重，如果需要另外引入，不建议使用。
   2. Axios：轻量级，建议使用
      1. 封装XmlHttpRequest对象的ajax
      2. promise风格
      3. 可以用在浏览器端和node服务器端

3. 注意跨域问题：

   1. 同服务器不同端口，也属于跨域。
   2. React中，对于跨域问题，使用代理服务器解决。

4. 代理服务器：

   1. 【client:3000 - client:3000(代理) - server:5000】：中间人，没有ajax引擎，
   2. 跨域产生的根本：ajax引擎把响应给拦住了

5. **React配置代理的两种方式** （[尚硅谷笔记连接](https://github.com/brant8/vue2-3-Css/blob/main/%E5%B0%9A%E7%A1%85%E8%B0%B7reactCLI/react%E8%84%9A%E6%89%8B%E6%9E%B6%E9%85%8D%E7%BD%AE%E4%BB%A3%E7%90%86.md)）

   1. 方式一：配置`package.json` （单个代理服务器）

      ```json
      "proxy": "http://localhost:5000" //单独写一行，不能写localhost:5000/student具体接口，否则其他资源无法获取
      ```

      1. 在App中使用axios获取

         ```js
         getStudentData=()=>{
             axios.get('http://localhost:3000/students').then(
             //axios.get('http://localhost:3000/index.html') 得到的时本地public中的index.html代码输出
             //axios.get('http://localhost:3000/index2.html') 得到报错，当本地资源没有时，向服务器端获取，若服务器端也没有则报错
                 response=>{
                     console.log('成功了',response.data);
                 },
                 error=>{
                     console.log('失败了',error);
                 }
             )
         }
         ```

   2. 方式二：创建配置文件`src/setupProxy.js`

      1. 配置文件方式优点：配置多个代理；灵活控制请求是否走代理。但是配置繁琐

      ```js
      //React找到setupProxy文件，加载到webpack中的node里面
      /*低版本配置时：
      const proxy = require('http-proxy-middleware') //React脚手架初始化时自动下载好
       */
      /*高版本*/
      const {createProxyMiddleware} = require('http-proxy-middleware') //React脚手架初始化时自动下载好
      
      module.exports = function(app){ //app为服务对象
          app.use(
              // proxy('路径',{ 配置对象 })
              createProxyMiddleware('/api1',{
                  target:'http://localhost:5000',
                  secure:false,//验证是否https的安全证书
                  changeOrigin:true, //必须是true，控制服务器收到的响应头中Host字段的值
                  /*不加changeOrigin,服务器输出显示知道来自端口3000的请求
                  * 有了changeOrigin，服务器输出限制地址来自自身端口5000的请求*/
                  pathRewrite:{"^/api1":""} //重写请求路径
              }),
              createProxyMiddleware('/api2',{
                  target:'http://localhost:5001',
                  secure:false,
                  changeOrigin:true,
                  pathRewrite:{'^/api2':''}
              }),
          )
      }
      ```

      ```js
      import React, {Component} from 'react';
      import axios from 'axios';
      
      class App extends Component {
          getStudentData=()=>{
              axios.get('http://localhost:3000/api1/students0').then(
                  response=>{
                      console.log('成功了',response.data);
                  }).catch(
                  error=>{
                      console.log('失败了',error);
                  }
              )
          }
          getCarData=()=>{
              axios.get('http://localhost:3000/api2/cars').then(
                  response=>{
                      console.log('成功了',response.data);
                  },
                  error=>{
                      console.log('失败了',error);
                  }
              )
          }
          render() {
              return (
                  <div>
                      <button onClick={this.getStudentData}>点击获取学生数据</button>
                      <button onClick={this.getCarData}>点击获取学生数据</button>
                  </div>
              );
          }
      }
      
      export default App;
      ```

6. **案例：Github搜索用户**

   1. 使用bootstrap的CSS，对于有生态的静态文件，把其放在`/public`目录中，可以具体分类到`/public/css`下，在`/public/index.html`中进行引用。

   2. 自定义CSS可以放在APP中或者组件中进行分别引用。

   3. 使用`target="_blank"`控制跳转当前页还是到其他页，旧版需要配合使用`rel="noreferrer"`

   4. 在`<img>`标签中，需要使用`<img alter="xx" ..`来定义，当图片没有显示出来时使用文字输出。

      ```js
      //通过ref获取input用户输入的值
      console.log(this.keyWordElement.value);
      /*const {value} = this.keyWordElement; -> console.log(value)
              const {keyWordElement} = this;  -> console.log(keyWordElement.value) */
      const {keyWordElement:{value}} = this; //解构赋值的解构赋值；解构赋值连续写法;console.log(keyWordElement)报错
      //发送网络请求
      axios.get(`http://localhost:3000/api1/search/users?q=${value}`).then(...
          //axios.get(`/api1/search/users?q=${value}`)...   当在localhost:3000页面下，给localhost:3000发送请求，可以省略地址
      ```

   5. 当接收的数据来自 子组件的时候，在App根组件里的`state={data:[]}`先定于预留接收的参数名（初始化）

   6. 在List中，把获取到的`this.props`内容直接在`render`混合输出

      ```js
      class Index extends Component {
          render() {
              return (
                  <div className="row"> {/*一定要有根div*/}
                  {this.props.users.map((userObj)=>{
                      return (
                          <div key={userObj.id} className="card"> {/*遍历的内容，一定要加key*/}
                              <a href={userObj.html_url} rel="noreferrer" target="_blank">
                                  <img alt="avatar" src={userObj.avatar_url} style={{width: '100px'}}/>
                              </a>
                              <p className="card-text">{userObj.login}</p>
                          </div>
                      )
                    })
                  }
                  </div>
              );
          }
      }
      
      export default Index;
      ```

   7. 展示输出界面要有不同情况的输出

      1. 加载登陆后首次显示的内容
      2. 用户输入后显示加载中
      3. 从服务器获得数据后加载的内容
      4. 当服务器挂掉后显示的err内容

   8. render中的return里面：jsx不能写if没有返回结果的语句，可以用三元表达式

      ```js
      class Index extends Component {
          render() {
              const {users, isFirst, isLoading, err} = this.props; //使用props接收父组件传过来的state
              return (
                  <div className="row">
                  { /*jsx不能写if没有返回结果的语句，可以用三元表达式，三元可以使用多个表达*/
                      isFirst ? <h2>输入关键字后，点击搜索</h2> :
                      isLoading ? <h2>Loading...</h2> :
                      err ? <h2>{err}</h2> :
                      users.map((userObj)=>{
                      return (
                          <div key={userObj.id} className="card">
                              <a href={userObj.html_url} rel="noreferrer" target="_blank">
                                  <img alt="avatar" src={userObj.avatar_url} style={{width: '100px'}}/>
                              </a>
                              <p className="card-text">{userObj.login}</p>
                          </div>
                      )
                    })
                  }
                  </div>
              );
          }
      }
      
      export default Index;
      ```

   9. 父组件传递给子组件state，可以使用批量传递`<List {...this.state} />`

      ```js
      state = {
              users:[],
              isFirst:true, //是否第一次打开页面
              isLoading:false, //标识是否处于加载中
              err:''//存储请求相关的错误信息
          }//初始化数据
      ```

7. 其他知识巩固

   1. 对于解构赋值的用法：连续解构赋值

      ```js
      let obj={a:{b:{c:1}}}
      let obj2={a:{b:1}}
      console.log(obj.a.b.c); //1
      const {a:{b:{c}}} = obj
      console.log(c);//获取c -> 1
      const {a:{b}} = obj2
      console.log(b);//获取b
      //获取b的同时给b改名
      const {a:{b:data}} = obj2
      console.log(data); //1
      ```

## 消息订阅与发布机制

1. 基本 - 工具库：PubSubJS

   1. 安装命令：`npm install pubsub-js --save`

2. 使用：

   1. 引入 `import PubSub from 'pubsub-js'`
   2. 订阅 `PubSub.subscribe('delete',function(data){..})`
   3. 发布消息 `PubSub.publish('delete',data)`

3. 例如Github的Search搜索和List展示组件

   1. Search组件 - 发布消息

      ````js
      class Index extends Component {
          search=()=>{
              const {keyWordElement:{value}} = this; //解构赋值的解构赋值；解构赋值连续写法
              console.log('@@',value)
              //发送请求前通知List更新状态
              // this.props.updateAppState({isFirst:false,isLoading:true})
              PubSub.publish('atguigu',{isFirst:false,isLoading:true})
              //发送网络请求
              axios.get(`http://localhost:3000/api1/search/users?q=${value}`).then(
                  response =>{
                      console.log('成功了',response.data)
                      // this.props.updateAppState({isLoading:false,users:response.data.items})
                      PubSub.publish('atguigu',{isLoading:false,users:response.data.items})
                  },
                  error=>{
                      console.log('失败了',error)
                      // this.props.updateAppState({isLoading:false,err:'数据获取失败:'+error.message}) //不能存错误对象error，只能使用error.message
                      PubSub.publish('atguigu',{isLoading:false,err:'数据获取失败:'+error.message})
                  }
              )
          }
          render() {
              return (
                  <section className="jumbotron">
                      <h3 className="jumbotron-heading">搜索 Github 用户</h3>
                      <div>
                          <input ref={ c=>this.keyWordElement = c} type="text" placeholder="输入关键词点击搜索"/>&nbsp;
                          <button onClick={this.search}>搜索</button>
                      </div>
                  </section>
              );
          }
      }
      
      export default Index;
      ````

   2. List组件 - 接收/订阅消息

      ```js
      class Index extends Component {
          state = {
              users:[],
              isFirst:true, //是否第一次打开页面
              isLoading:false, //标识是否处于加载中
              err:''//存储请求相关的错误信息
          }
      
          componentDidMount() {
              /**订阅消息*/
              this.token = PubSub.subscribe('atguigu',(msg,stateObj)=>{
                  this.setState(stateObj);
              })
          }
          componentWillUnmount(){
              PubSub.unsubscribe(this.token) //类似于定时器关闭
          }
      
      
          render() {
              const {users, isFirst, isLoading, err} = this.state;
              return (
                  <div className="row">
                  { /*jsx不能写if没有返回结果的语句，可以用三元表达式*/
                      isFirst ? <h2>输入关键字后，点击搜索</h2> :
                      isLoading ? <h2>Loading...</h2> :
                      err ? <h2>{err}</h2> :
                      users.map((userObj)=>{
                      return (
                          <div key={userObj.id} className="card">
                              <a href={userObj.html_url} rel="noreferrer" target="_blank">
                                  <img alt="avatar" src={userObj.avatar_url} style={{width: '100px'}}/>
                              </a>
                              <p className="card-text">{userObj.login}</p>
                          </div>
                      )
                    })
                  }
                  </div>
              );
          }
      }
      
      export default Index;
      ```

4. 补充知识：

   1. jQuery和Axios都是对 `Ajax-XHR`**XMLHttpRequest** 的封装。

   2. 与 `Ajax-XHR`  同级别的时 `fetch`。

   3. `fetch`非第三方库，window自带，可直接使用，也是返回Promise。

   4. XHR发送json请求代码样式

      ```js
      var xhr = new XMLHttpRequest();
      xhr.open('GET',url);
      xhr.responseType = 'json';
      xhr.onload = function(){
          console.log(xhr.response);
      }
      xhr.onerror = function(){
          console.log("Oops,error");
      }
      xhr.send();
      ```

   5. 使用Fetch

      ```js
      fetch(url,{
          method:"POST",
          body:JSON.stringify(data),
          headers:{
              'Content-Type':'application/json'
          },
          credentials:'same-origin'
      }).then(function(response){
          response.status //=> number 100-599
          response.statusText //=>String
          response.headers //=>Headers
          response.url //=>String
          return response.json(); //或return response.text()
      }).then(function(){
          console.log(data);
      }).catch(function(e){
          console.log('Oops,error');
      })
      ```

   6. JSX中的JS代码可以使用`//#region 内容` 和 `//#endregion` 使代码可以折叠

5. Fetch在Github搜索用户案例中的使用场景

   1. 在Search发送请求中

      ```js
      //发送网络请求 -- fetch发送
      fetch(`/api1/search/users9?q=${value}`).then( //注意此处地址users9实际不存在
          response => {
              console.log('联系成功',response)}, 
          /*输出：联系成功 （即使返回404，也是服务器响应的）
          Response {type: 'basic', url: 'http://localhost:3000/api1/search/users9?q=23', redirected: false, status: 404, ok: false, …}*/
          error => {
              console.log('联系失败',error)}
          /*当服务器offline/down的时候，才会显示失败*/
      )
      ```

   2. 当使用`.json()`方法时

      ```js
      fetch(`/api1/search/users2?q=${value}`).then(
          response => {
              console.log('联系成功',response.json())},
          /*输出： 联系成功 Promise {<pending>}*/
      )
      ```

   3. 当使用Promise实例中有Promise对象时，使用return然后再来个`.then()`链式调用

      ```js
      fetch(`/api1/search/users2?q=${value}`).then(
          response => {
              console.log('联系服务器成功了');
          	return response.json();
          }, /*规矩1：若此处不是response.json()而是返回非Promise值(包含undefined)，则外层第一个.then的Promise返回值就是成功succeed的，其值就是return的返回值
          	规矩2：若此处返回值时Promise实例显示pending，则需要下一层的then调用进行获取数据*/
         	error => {..}
      ).then{
          response => {
              console.log('获取数据成功了',response);//此处response包含真实数据
          }
          error => {...}
      }
      ```

   4. 当浏览器offline（network）里面设置后，发送请求，会先失败然后成功，为防止后门，需要在失败时结束Promise。

      ```js
      fetch(`/api1/search/users2?q=${value}`).then(
          response => {
              console.log('联系服务器成功了');
          	return response.json();
          }, 
         	error => {
          	console.log('联系服务器失败了',error); 
          	return new Promise(()=>{}) //返回空Promise
              /*浏览器离线时发送请求输出1：联系失败 TypeError: Failed to fetch*/
          }
      ).then{
          response => {
              console.log('获取数据成功了',response);/*浏览器离线时发送请求输出2：数据成功 undefined*/
          }
          error => {...}
      }
      ```

   5. **优化步骤**4的代码，可以把then里面的error去除，然后用`.catch(){..}`抓捕错误，就不会有先输出错误又返回成功的场景。

   6. **使用await优化**步骤4的代码

      ```js
      search= async ()=>{
              /*..略去其他代码*/
              try{
                  const response = await fetch(`/api1/search/users2?q=${value}`); //查看与服务器连接情况
                  const data =  await response.json(); //真正的数据
                  console.log(data.items);
              }catch(error){
                  console.log('请求出错',error);
              }
          }
      /*优化规则：
      1. 步骤4通过两次then等待获取成功的值，await右侧一定是Promise实例
      2. 第一个await获取的需要在第二个 await里获取真实数据
      3. 在await最近的一个函数那里使用 async
      4. await只能等到成功的结果，异常不管，因此需要使用try..catch
      */
      ```

   7. fetch使用率不高，有些浏览器不兼容。

6. **Github用户案例尚硅谷总结**：

   1. 设计状态时，要考虑全面。例如带有网络请求的组件，要考虑失败怎么办。

   2. ES6小知识点：解构赋值+重命名

      ```js
      let obj = {a:{b:1}}
      const {a} = obj; //传统解构赋值
      const {a:{b}} = obj; //连续解构赋值
      const {a:{b:value}} = obj; //连续解构赋值+重命名
      ```

   3. 消息订阅与发布机制

      1. 先订阅，再发布（理解：有一种隔空对话的感觉）
      2. 适用于任意组件间的通信
      3. 要在组件的componentWillUnmount中取消订阅

   4. fetch发送请求（关注分离的设计思想）

## React路由

1. SPA的理解：单页Web应用

   1. 整个应用只有一个完整的页面
   2. 点击页面中的连接不会刷新页面，指挥做页面的局部更新
   3. 数据都需要通过**ajax请求获取**，并在前端异步展现。

2. 路由的理解

   1. 一个路由就是一个映射关系（key:value）
   2. key为路径，value可能是function 或 component

3. 路由的分类

   1. **后端路由**：
      1. 理解：value是 function，用来处理客户端提交的请求
      2. 注册路由：`router.get(path, function(req,res))`
      3. 工作过程：当node接收到一个请求时，根据请求路径找到匹配的路由，调用路由中的函数来处理请求，返回响应数据。
      4. 类似Nodejs的express，`app.get('url',fun(){..})`中url是key，fun是value。
   2. **前端路由**：
      1. 浏览器端路由，value是 component，用于展示页面内容。
      2. 注册路由：`<Route path="/test" component={Test} >`
      3. 工作过程：当浏览器的path变为`/test`时，当前路由组件就会变为Test组件。
      4. 前端路由的基石：history
   3. 浏览器：浏览器是栈结构，BOM的history中的push会压栈，replace替换当前栈。
      1. 操作方法一：直接使用H5推出的history身上的API。`History.createBrowserHistory()` [Histroy参考](https://www.npmjs.com/package/history)
      2. 操作方法二：hash值（锚点`#`井号），`History.createHashHistory()`
      3. 锚点跳转：不会引起页面的刷新，但是有历史记录可以后退前进。`<a href="#demo1">锚点1</a>`
      4. **锚点注意事项**：`http://url/#/abc`中的井号后面的路径或参数不会作为资源发给服务器作为参数传

4. React-router的理解

   1. `react-router`是react的一个插件库。
   2. 专门用来实现一个SPA应用。可以在不同场景使用
      1. web：实际学习的是 react-router-dom
      2. native
      3. any
   3. 基于react的项目基本都会用到此库。

5. react-router 相关 API

   1. 内置组件：`<BrowserRouter>`、`<HashRouter`、`<Route>`、`<Redirect>`、`<Link>`、`<NavLink>`、`<Switch>`
   2. 其他：history对象、match对象、withRouter函数

6. 中文文档整合：[印记中文](https://docschina.org/)

7. 安装命令：`npm install react-router-dom`

   1. 尚硅谷视频版本：`npm install react-router-dom@5`，最新版`@6.4`
   2. 前端路由 BrowserRouter会监听 路由的变化

8. react路由的基本使用：

   1. **导入**需要使用的路由API组件`import {Link,Route} from 'react-router-dom'`

   2. **前端路由监听**，在index.js中包裹APP，一劳永逸。因为页面两个路由器标签，标识两个不同路由器，无法切换内容

      ```js
      import {BrowserRouter} from "react-router-dom";
      //渲染App到页面
      ReactDOM.render(
          <BrowserRouter>
              <App/>
          </BrowserRouter>,
          document.getElementById('root')
      );
      //也可以在当前组件的根组件进行<BrowserRouter>包裹，但是效果不如在index.js中直接包裹整个根目录
      ```

   3. **使用路由**

      ```jsx
      import {Link,Route} from 'react-router-dom';
      
      <Link className="list-group-item active" to="/about">About</Link> //<Link>相当于<a>
      <Link className="list-group-item active" to="/home">Home</Link> //href 使用to 替代
      //<Link>等路由API都需要被Router中的BrowserRouter或者HashRouter包裹，比如index.js中已包裹的BrowserRouter
      ```

   4. **注册路由**

      ```jsx
      <Route path='/about' component={About}/>
      <Route path='/home' component={Home}/>
      ```

9. 组件的分类：

   1. 一般组件：`<List/>`组件标签直接使用渲染

      1. 一般放在`src/components/`目录下
      2. 一般组件在*渲染*、*不传递参数*的时候啥都收不到，传递参数后`<List a={1}/>`使用`this.props`接收

   2. 路由组件：`<Route path='/home' component={Home}/>`

      1. 一般路由组件放在`src/pages/`目录下

      2. 路由组件在*调用渲染*的时候自动接收`this.props`参数：

         ```js
         //路由组件默认props输出
         {history: {…}, location: {…}, match: {…}, staticContext: undefined}
         ```

      3. **路由组件详细props**（注意区别一般组件 ，一般组件没有`history`的方法，需要额外）

         ```properties
         history: 
             action: "PUSH" 
             block: ƒ block(prompt)
             createHref: ƒ createHref(location)
             go: ƒ go(n) 【常用】
             goBack: ƒ goBack() 【常用】
             goForward: ƒ goForward() 【常用】
             length: 6
             listen: ƒ listen(listener)
             location: {pathname: '/about', search: '', hash: '', state: undefined, key: 'jsqwe8'} 【与下面重复】
             push: ƒ push(path, state) 【常用】
             replace: ƒ replace(path, state) 【常用】
             [[Prototype]]: Object
         location: 
             hash: ""
             key: "jsqwe8"
             pathname: "/about" 【常用】
             search: "" 【常用】
             state: undefined 【常用】
             [[Prototype]]: Object
         match: 
             isExact: true 【模糊匹配、精确匹配】
             params: {} 【常用】
             path: "/about" 【常用】
             url: "/about" 【常用】
             [[Prototype]]: Object
         staticContext: undefined
         ```

   3. **动态高亮**：当选择某个`<li>`之类的时候需要高亮显示时，使用NavLink

      ```jsx
      import {NavLink,Route} from 'react-router-dom'
      
      <NavLink activeClassName="active" className="list-group-item" to="/about">About</NavLink>
      <NavLink activeClassName="active" className="list-group-item" to="/home">Home</NavLink>
      //默认activeClassName的值为active，所以若css高亮命名也是active时，可以省略
      //相当于 className="list-group-item active"
      //注意：若使用Bootstrap时，bootstrap权重比较中，需要使用'!Important'；如'.atgu{color:white !important;}'
      ```

   4. **封装`NavLink`**去除重复代码的使用方法

      ```jsx
      //APP.js
      <MyNavLink to="/about" title="About" />
      <MyNavLink to="/home" title="Home" />
      //注意此处的 MyNavLink封装为一般组件，且传参到组件
      ```

      1. MyNavLink一般组件写法

         ```js
         import {NavLink} from "react-router-dom";
         
         class Index extends Component {
             render() {
                 const {to,title} = this.props;
                 console.log(this.props); //点击跳转后收到的内容-> {to: '/about', children: 'About2'}
                 return (
                     <NavLink activeClassName="active" className="list-group-item" to={to}>{title}</NavLink>
                 {/*多参数情况懒人接收方式
                     <NavLink activeClassName="active" className="list-group-item" {...this.props}>{title}</NavLink>
                     */}
             );
             }
         }
         ```

      2. 额外说明：React中，标签体时特殊的标签属性，可以获取

         ```jsx
         <MyNavLink to="/about" title="About">About</MyNavLink>
         <标签名 标签属性>标签体</标签名>
         
         ```

   5. 配置**标签体**中的方式

      1. 在封装的MyNavLink中，使用`this.props.children`

         ```js
         <NavLink activeClassName="active" className="list-group-item" to={to}>{this.props.children}</NavLink>
         在APP.js中使用
         <MyNavLink to="/home">Home</MyNavLink>
         ```

      2. 自闭和形式

         ```js
         <NavLink activeClassName="active" className="list-group-item" {...this.props} />
         在APP.js中使用
         <MyNavLink to="/home" children="Home"/>
         /*效果：
         <a class="list-group-item active" href="/home" aria-current="page">Home</a>*/
         ```

   6. 当多个路由和组件有重复的时候：页面会显示全部匹配的组件内容

      1. `<switch>`提高路由匹配效率 `import {Switch} from 'react-router-dom'`

      ````jsx
      <Route path='/home' component={Home}/>
      <Route path='/home' component={Test}/>
      //输出： Home组件内容 Test组件内容
      //路由一样时，组件不一样，则输出两个组件的内容
          
      <Switch>{/*注意引用*/}
          <Route path='/about' component={About}/>
          <Route path='/home' component={Home}/>{/*当路由与组件匹配成功后，不会向下再继续匹配*/}
          <Route path='/home' component={Test}/>
      </Switch>
      ````

10. 扩展：路由多级情况下出现的问题

    1. 当页面地址访问不存在时，React内置服务器默认把`index.html`返回给用户，且如css资源返回的也是`index.html`

       ```jsx
        {/*访问地址: http://localhost:3000*/}
       <MyNavLink to="/atguigu/about" >About2</MyNavLink> 
       <MyNavLink to="/atguigu/home" >Home</MyNavLink>
        {/*注册路由：*/}
       <Switch>
           <Route path='/atguigu/about' component={About}/>
           <Route path='/atguigu/home' component={Home}/>
           <Route path='/atguigu/home' component={Test}/>
       </Switch>
       {/*
       1. 打开页面未任何操作时，地址为 http://localhost:3000
       页面为index.html且资源如bootstrap.css加载正常 【Network - Bootstrap - Response - css代码】
       2. 点击Nav后，页面地址为 http://localhost:3000/atguigu/home 
       页面加载正常，NavLink内容可以正常显示，如bootstrap.css加载正常
       3. 点击刷新后，页面地址为 http://localhost:3000/atguigu/home
       页面加载显示public/index.html，丢失CSS样式，NavLink内容可以切换显示，bootstrap.css资源查看Network，实为index.html返回的Response。
       4. 原因：加载页面后，页面均正常，因为前端路由点击页面内容时，不会发送网络请求。
       	点击切换NavLink时，无网络请求，但是地址栏已变更错误的地址 http://localhost:3000/atguigu/home，但是css等资源不变（缓存）,bootstrap资源地址为 http://localhost:3000/css/bootstrap.css
       	点击刷新后，地址为 http://localhost:3000/atguigu/home；页面向服务器重新请求，地址错误返回index.html，且bootstrap.css资源地址变为 http://localhost:3000/atguigu/css/bootstrap.css（实际内容index.html）
       */}
       ```

11. **多级路由资源**（CSS等）问题问题解决方法：(浏览器shift+F5强制刷新测试)

    1. 去掉`.`点的相对路径

       ```html
       <link rel="stylesheet" href="./css/bootstrap.css" />
       替换为
       <link rel="stylesheet" href="/css/bootstrap.css" />
       ```

    2. 使用`%PUBLIC_URL%`： public目录的绝对路径

       ```html
       <link rel="stylesheet" href="%PUBLIC_URL%/css/bootstrap.css" />
       ```

    3. 使用HashRouter（不常用）

       ```js
       /*资源路径形式还是使用相对形式，即有'.'*/
       import {HashRouter} from "react-router-dom"; //在地址栏加井号
       //引入App组件
       import App from './App';
       
       //渲染App到页面
       ReactDOM.render(
           <HashRouter>
               <App/>
           </HashRouter>,
           document.getElementById('root'));
       /*
       http://localhost:3000/#/atguigu/home
       */
       ```

12. 其他： 混用yarn和npm安装包，容易造成包丢失

13. **模糊匹配** - 默认

    ```jsx
    {/*导航区*/}
    {/*情况一：地址栏多级，内容区非多级，内容正常显示*/}
    <MyNavLink to="/about" >About2</MyNavLink> 
    <MyNavLink to="/home/a/b/c" >Home</MyNavLink> 
    {/*注册路由：内容展示区*/}
    <Switch>
        <Route path='/about' component={About}/>
        <Route path='/home' component={Home}/>
    </Switch>
    {/*情况二：内容多级，路由非多级，内容不显示*/}
    <MyNavLink to="/about" >About2</MyNavLink> 
    <MyNavLink to="/home" >Home</MyNavLink> 
    {/*注册路由：内容展示区*/}
    <Switch>
        <Route path='/about' component={About}/>
        <Route path='/home/a/b/c' component={Home}/>
    </Switch>
    {/*情况三：内容不显示*/}
    <MyNavLink to="d/home/a/b/c" >Home</MyNavLink>
    <Switch>
    	<MyNavLink to="/home" >Home</MyNavLink> 
    </Switch>
    ```

14. **精准匹配**

    ```jsx
    {/*导航区*/}
    <MyNavLink to="/about" >About2</MyNavLink> 
    <MyNavLink to="/home/a/b/c" >Home</MyNavLink> 
    {/*内容展示区*/}
    <Switch>
        <Route exact={true} path='/about' component={About}/>{/*开启精准匹配地址*/}
        <Route exact path='/home' component={Home}/>{/*开启可省略true*/}
    </Switch>
    ```

    1. 严格匹配不要随便开启，需要再开，有些时候开启会导致无法继续匹配二级路由。

15. **Redirect重定向**：`import {Redirect} from 'react-router-dom'`

    1. 当所有的路由都无法匹配时，跳转到Redirect指定的路由

    2. Redirect放在【路由注册】的最下方

       ```jsx
        {/*注册路由：什么路径看什么组件的内容*/}
       <Switch>
           <Route path='/about' component={About}/>
           <Route path='/home' component={Home}/>
           <Redirect to="/home"/>  {/*展示默认内容（也i按打开的时候）*/}
       </Switch>
       ```

16. **嵌套路由**

    1. 每次**路由匹配都是从最开始注册的路由到最后注册的路由挨个匹配**。

    2. 情景一：二级路由只写自己的路由时

       ```jsx
       //Home.js,路径 -> /home/news
       <MyNavLink to='/news'>News</MyNavLink>
       //APP.js主路由，注册的路由由上往下匹配，匹配不到/news则返回/about
       <Route path='/about' component={About}/>
       <Route path='/home' component={Home}/>
       <Redirect to='/about'/>
       ```

    3. 情景二：二级路由带有父路由时（需要使用模糊匹配，即路由注册中不能有`exact`）

       ```js
       //Home.js,路径 -> /home/news
       <MyNavLink to='/home/news'>News</MyNavLink>
       //APP.js主路由，注册的路由由上往下匹配
       <Route path='/about' component={About}/>
       <Route path='/home' component={Home}/> //模糊匹配到/home，然后到组件Home中查看，发现Home组件也有注册路由
       <Redirect to='/about'/>
       //Home.js注册路由，注意导入import {Route} from 'react-router-dom';
       <Route path="/home/news" component={News}/>
       <Route path="/home/message" component={Message}/>
       ```

    4. 注意事项，React V6版本 `/home/news`

       1. 一级路由在`path`后面加`/*`，比如`/home/*`
       2. 二级导航不变，二级路由path只写子路由，比如`/news`
       3. 其他弹幕:V6 Route写法：
          1. `<MyNavLink to="home/*">Home</MyNavLink>`
          2. `<Route path="home/*" element={<Home/>}/>`
          3. `<Route path="news" element={<News/>}/>`
       4. 其他弹幕:V6嵌套路由的路由注册还是写在App.js中，同时要在父组件中加入`<Outlet>`来保证子路由的展示区域

17. 扩展：Ajax传递参数的几种方式

    1. 使用query：`url/?a=xx&b=xx`
    2. 使用params：直接在url路径上写 `url/1/abc`匹配 `url/:id/:name`
    3. 使用body
       1. urlencode
       2. json

18. **向路由组件传递参数 - params形式**

    1. 数据 Message.js

       ```js
       state = {
           messageArr:[
               {id:'01',title:'消息1'},
               {id:'02',title:'消息2'},
               {id:'03',title:'消息3'}
           ]
       }
       ```

    2. 向路由组件传递params参数 并且接收参数

       ```jsx
       render() {
         const {messageArr} = this.state;
         return (
           <div>
             <ul>{
               messageArr.map((msgObj)=>{
                 return (
                   <li key={msgObj.id}>
                   {/*向路由组件传递携带params参数*/}
                    <NavLink to={`/home/message/detail/${msgObj.id}/${msgObj.title}`}>{msgObj.title}</NavLink>&nbsp;&nbsp;
                    </li>
                    )
                  })
                 }
              </ul>
              <hr/>
              {/*声明接收params参数*/}
              <Route exact path="/home/message/detail/:id/:title" component={Detail} />
           </div>
         );
       }
       ```

    3. Details.js 接收展示组件

       ```jsx
       //类外声明模拟数据
       const data = [
           {id:'01',content:'你好中国1'},
           {id:'02',content:'你好中国2'},
           {id:'03',content:'你好中国3'},
       ];
       //类中
       render() {
               console.log(this.props)
               /* match:
               *    params :{id: '02', title: '消息2'} */
               //接收params参数
               const {id,title} = this.props.match.params;
           	/*使用数组.find()搜索过滤数据*/
               const findResult = data.find((detailObj)=>{
                   return detailObj.id === id;
               })
               return (
                   <ul>
                       <li>ID2:{id}</li>
                       <li>Title:{title}</li>
                       <li>Content:{findResult.content}</li>
                   </ul>
               );
           }
       ```

    4. 要点：

       1. 组件传递参数使用 JSX + 模板字符串 + 模板字符串中使用变量`${X}`
       2. 接收参数使用`/:abc`预留
       3. 子组件展示使用`params`接收参数
       4. 使用数组方法`.find(()=>{return xx})`返回单个（第一个匹配）数据

19. **向路由组件传递参数 - search形式**

    1. search形式需要解析地址栏获取到的值：`?id=01&消息1` 分割出来转为对象

    2. React自带解析（qs来自node核心库）：

       1. `import QueryString from 'query-string'` （弹幕：需要手动安装，因为已弃用`npm i --save-dev query-string`）

       2. 使用`import qs from 'qs'` （新版Node.js可用）

          ```js
          import qs from 'querystring' //或者 import qs from 'query-string' （旧版NodeJS）
          //或者 import qs from 'qs' （可用）
          
          let obj = {name:'tom',age:18} 
          console.log(qs.stringify(obj)) 
          //-> name=tom&age=18； key=value&key=value 称为urlencoded编码
          
          let str = 'carName=奔驰&price=199';
          console.log(qs.parse(str))
          ```

       3. 去除问号`?id=02&消息2`： `(?id=02&title=消息2).slice(1)` 切割一下即可

    3. Message.js代码

       ```jsx
       render() {
         const {messageArr} = this.state;
         return (
           <div>
             <ul>{
               messageArr.map((msgObj)=>{
                  return (
                     <li key={msgObj.id}>
                     {/*向路由组件传递search参数*/}
                     <NavLink to={`/home/message/detail/?id=${msgObj.id}&title=${msgObj.title}`}>{msgObj.title}</NavLink>&nbsp;&nbsp;
                      </li>
                    )
                 })
                }
               </ul>
               <hr/>
               {/*声明接收search参数：无需声明接收，正常注册路由即可*/}
               <Route path="/home/message/detail" component={Detail} />
           </div>
          );
       }
       ```

    4. Details.js代码

       ```jsx
       render() {
               console.log(this.props)
               /* location:
                *   hash: ""
                *   key: "sugwfl"
                *   pathname: "/home/message/detail/"
                *   search: "?id=01&title=消息1" */
               //接收search参数
               const {search} = this.props.location;
               console.log(search) //?id=03&title=消息3
               const result = qs.parse(search.slice(1)); //截取问好
               console.log(result);//{id: '01', title: '消息1'}
               const {id,title} = result;
               const findResult = data.find((detailObj)=>{
                   return detailObj.id === id;
               })
               return (
                   <ul>
                       <li>ID2:{id}</li>
                       <li>Title:{title}</li>
                       <li>Content:{findResult.content}</li>
                   </ul>
               );
           }
       ```

    5. 注意：

       1. 使用search传递参数时，路由注册正常注册即可。
       2. 获取到的search是urlencoded编码字符串，需要借助querystring解析。

20. **向路由组件传递参数 - state(路由)形式**

    1. 注意此state是路由的属性，非组件属性。

    2. Message.js代码

       ```js
       render() {
         const {messageArr} = this.state;
         return (
           <div>
             <ul>{
               messageArr.map((msgObj)=>{
                  return (
                     <li key={msgObj.id}>
                     {/*向路由组件传递state参数 to={ {对象} }*/}
                     <NavLink to={{pathname:'/home/message/detail',state:{id:msgObj.id,title:msgObj.title}}}>{msgObj.title}</NavLink>&nbsp;&nbsp;
                      </li>
                    )
                 })
                }
               </ul>
               <hr/>
               {/*声明接收state参数：无需声明接收，正常注册路由即可*/}
               <Route path="/home/message/detail" component={Detail} />
           </div>
          );
       }
       ```

    3. Details.js代码

       ```js
       render() {
               console.log(this.props)
               /*location:
               *   hash: ""
               *   key: "ehe9rs"
               *   pathname: "/home/message/detail"
               *   search: ""
               *   state: {id: '01', title: '消息1'} */
               //接收state参数
               const {id,title} = this.props.location.state || {};//没有值就给空对象
               const findResult = data.find((detailObj)=>{
                   return detailObj.id === id;
               })
               return (
                   <ul>
                       <li>ID2:{id}</li>
                       <li>Title:{title}</li>
                       <li>Content:{findResult.content}</li>
                   </ul>
               );
           }
       ```

21. params、search、state三者之间区别

    1. params `url/a/b/c`与search `url/?a=x&b=y`的参数都会在地址栏显示，刷新的话参数也会依然起作用（根据地址栏获取参数）。

    2. state参数在地址栏中没有体现，刷新的话参数也依然起作用。

    3. 原因：BrowserHistory维护的是浏览器的history，浏览器中有个API - history.xxx。即使刷新浏览器也有记录。

    4. 若删除浏览器的cookie 缓存，state获取不到参数了，则会报错。

       ```js
        const {id,title} = this.props.location.state || {}; //防止网页报错，空值是给空对象
       //最多id、title是undefined
       ```

    5. 所有需要在页面显示的内容获取值时，若参数为空，获取值也要预留空值。

       ```js
       const findResult = data.find((detailObj)=>{
           return detailObj.id === id;
       }) || {}; //同样空值，否则页面内容显示错误
       ```

22. **开启replace路由模式**

    1. 默认路由是 `push`压栈模式，即可以后退前进。

    2. 开启`replace`模式，即点击该连接后，当前连接的压栈被替换称要去的地址。

       ```jsx
       <NavLink replace={true} to=”/home/news“>News</NavLink>
       //或者省略true
       <NavLink replace to=”/home/news“>News</NavLink>
       ```

23. **编程式路由导航**

    1. 编程式路由导航，使用代码进行跳转

    2. 注意子组件Detail接收参数也不同，有的需要query-string辅助

       ```jsx
       replaceShow = (id,title)=>{ //push(path[,state])
           //1.2 编写一段代码，让其实现跳转到Detail组件，且为replace跳转,【携带params参数】
           this.props.history.replace(`/home/message/detail/${id}/${title}`)
           
           //2.2 【携带search参数】
           this.props.history.replace(`/home/message/detail/?id=${id}&title=${title}`)
           
           //3.2 【携带state参数】
           this.props.history.replace(`/home/message/detail`,{id:id,title:title})
       }
       pushShow = (id,title)=>{//replace(path[,state])
           //1.3 编写一段代码，让其实现跳转到Detail组件，且为push跳转，【携带params参数】
           this.props.history.push(`/home/message/detail/${id}/${title}`)
           
           //2.3 【携带search参数】
           this.props.history.push(`/home/message/detail/?id=${id}&title=${title}`)
           
           //3.3 【携带state参数】
           this.props.history.push(`/home/message/detail`,{id:id,title:title})
       }
       
       render() {
         const {messageArr} = this.state;
         return (
           <div>
             <ul>{
               messageArr.map((msgObj)=>{
                  return (
                     <li key={msgObj.id}>
                    {/*1.1 向路由组件传递params参数*/}
                     <NavLink to={`/home/message/detail/${msgObj.id}/${msgObj.title}`}>{msgObj.title}</NavLink>     
                          
                    {/*2.1 向路由组件传递search参数,detail/可省略斜杠，也可以有斜杠*/}
                     <NavLink to={`/home/message/detail/?id=${msgObj.id}&title=${msgObj.title}`}>{msgObj.title}</NavLink>   
                          
                     {/*3.1 向路由组件传递state参数 to={ {对象} }*/}
                     <NavLink to={{pathname:'/home/message/detail',state:{id:msgObj.id,title:msgObj.title}}}>{msgObj.title}</NavLink>&nbsp;&nbsp;
                    
                     {/*【高阶函数】，传递参数，
                      * 此处this.replaceShow(..) 标识用户自己调用的，非React调用*/}     
               &nbsp;<button onClick={()=>this.pushShow(msgObj.id,msgObj.title)}>push查看</button>
               &nbsp;<button onClick={()=>this.replaceShow(msgObj.id,msgObj.title)}>replace查看</button>
                      </li>
                    )
                 })
                }
               </ul>
               <hr/>
               {/*1.4 声明接收params参数*/}
               <Route path="/home/message/detail/:id/:title" component={Detail} />
                 
               {/*2.4&3.4 声明接收search参数：无需声明接收，正常注册路由即可*/}
               <Route path="/home/message/detail" component={Detail} />
           </div>
          );
       }
       ```

    3. 后退、前进按钮类似

       ```jsx
       back=()=>{
           this.props.history.goBack()
       }
       forward=()=>{
           this.props.history.goForward()
       }
       //this.props.history.go(n) 表示前进/回退步数 正/负数
       render(){
           return (
           	<button onclick={this.back}>回退</button>
               <button onclick={this.forward}>前进</button>
           )
       }
       ```

    4. 注意：路由组件有`history`，一般组件没有`history`，因此一般组件不能前进后退。可以使用`withRouter`给与一般组件拥有`history`等方法

       ```jsx
       import {withRouter} from 'react-router-dom';
       
       class Header extends Component{
           back=()=>{
               this.props.history.goBack();
           }
           render(){
               return ( 
                   <button onclick={this.back}>回去</button>
               )
           }
       }
       export default withRouter(Header);//让一般组件拥有路由组件的API
       ```

    5. `withRouter`路由API可以加工一般组件，让一般组件具备路由组件特有的API。

       1. `withRouter`的返回值是一个新组件。

    6. BrowserHistory和HashHistory

       1. 底层原理
          1. `this.props.history`是React的封装
          2. BrowserRouter使用的是H5de history API，不兼容IE9以及以下版本。
          3. HashRouter使用的是URL的哈希值。井号后面的参数不会发送给服务器，但是可以形成历史记录。
       2. url（path）表现形式不一样
          1. BrowserRouter的路径没有`#`，例如：`localhost:3000/demo/test`
          2. HashRouter的路径包含`#`，例如：`localhost:3000/#/demo/tets`
       3. 刷新后对路由state参数的影响
          1. BrowserRouter没有任何影响，因为state保存在history对象中。
          2. HashRouter刷新后会导致路由state参数的丢失。HashRouter没有使用history对象保存数据。
       4. 备注：HashRouter可以用于解决一些路径错误相关的问题。
          1. 比如CSS资源在二级导航时出现问题。

## React UI

1. 流行的开源React UI组件库

   1. 国外：material-ui
   2. 国内：ant-design蚂蚁金服

2. 使用蚂蚁做案例演示学习，

   1. 安装命令：`npm install antd`

   2. 导入所需要的组件以及样式

      ```jsx
      import {Button} from 'antd';
      import 'antd/dist/antd.css'; //从node_modules中的antd目录下找css
      ```

   3. ![使用图示](https://github.com/brant8/vue2-3-Css/blob/main/pictures/react_antdemo.png)

3. 安装antd其他组件

   1. 若没有安装`antd`，需要额外安装icon：`npm install --save @ant-design/icons`

   2. 引入并使用图标

      ```jsx
      import {WechatOutlined，SearchOutlined} from '@ant-design/icons';
      render() {
          return (
              <div id="app">
                  <button>原生点击</button>
                  <Button type="primary">antd点击</Button> {/*属性参数可查看手册右侧API*/}
                  <Button type="link">antd点击</Button>
                  {/*使用Icon图标*/}
                  <WechatOutlined />
                  {/*使用Button & Icon图标*/}
                  <Button type="primary" icon={<SearchOutlined />}>
                         Search
                  </Button>
              </div>
          );
      }
      ```

   3. 使用日期 数据录入（Data Entry） - DatePicker

      ```jsx
      import React from 'react';
      import { DatePicker, Space } from 'antd';
      const { RangePicker } = DatePicker;
      render() {
          return (
              <div id="app">
       			<RangePicker 
               </div>
          );
      }
      ```

4. Antd被分割成多个组件库，需要引用哪些组件需要查看API。

   1. Ant Design官网最新版4.x部分**入门步骤**省略，版本3.x步骤会更详细。
   2. 在`create-react-app`中使用说明，[地址](https://3x.ant.design/docs/react/use-with-create-react-app)。
   3. 按需引入需要配置，即配置`webpack`，然而webpack的配置文件隐藏起来了，**且**暴露出来后无法更改。
   4. 创建 demo： `create-react-app demo`
   5. 暴露配置命令（在项目目录中）：`npm run eject` 或者 `yarn eject`
   6. 多出两个目录：`config`和`scripts`
   7. `config/webpack.config.js`
      1. webpack中的plugin必须先下载，然后引入再实例化才能用。
      2. loader的插件可以下载，然后直接使用

5. 【高级使用UI】[使用插件](https://3x.ant.design/docs/react/use-with-create-react-app#Advanced-Guides)，在不直接更改webpack.config的情况下修改启动配置【版本3.x（适用4.x）】

   1. 安装两个插件：`npm install react-app-rewired customized-cra`

      1. 真正修改借助于：`customized-cra`
      2. 当`customized-cra`通过`config-overrides.js`修改了配置，就不能使用`package.json`里面的命令了。只能通过`react-app-rewired`来启动脚手架

   2. `package.json`内容

      ```json
      "scripts": {
      -   "start": "react-scripts start", //此处相当于npm start启动脚手架 替换成reac-app-rewired的命令
      +   "start": "react-app-rewired start",
      -   "build": "react-scripts build",
      +   "build": "react-app-rewired build",
      -   "test": "react-scripts test",
      +   "test": "react-app-rewired test",
      }
      ```

   3. 【说明配置】然后项目目录创建一个`config-overrides.js`（与`src`同级别）用于修改默认配置。

      ```js
      module.exports = function override(config,env){
          return config;
      }
      /*react-app-rewired start命令中react-app-rewired会把配置传给config*/
      ```

   4. 安装`babel-plugin-import`：`npm install babel-plugin-import`

      1. 此插件时用于按需加载组件代码和样式的babel插件。

   5. 【实际配置】`config-overrides.js`

      ```js
      const { override, fixBabelImports } = require('customize-cra');
      module.exports = override(
           //import表示要做按需引入
            fixBabelImports('import', {
                libraryName: 'antd',//antd表示对antd按需引入
                libraryDirectory: 'es',//antd里面用es模块化规范
                style: 'css',//按需引入css
            }),
      );
      ```

      1. 注意：配置好此js文件后，在App.js中不需要引入`import '~antd/dist/antd.css';`

   6. 【4.x配置】[官网](https://ant.design/docs/react/use-with-create-react-app#Advanced-Guides)配置了简略版本步骤，使用craco配置，步骤比上面的简单。

      1. ` yarn add @craco/craco`

6. 自定义主题：

   1. Antd所有的样式都是通过`less`写的，通过编译成css进行使用。

   2. 安装less：`npm install less less-loader`

   3. 配置`config-overrides.js`

      ```js
      const { override, fixBabelImports, addLessLoader } = require('customize-cra');
      module.exports = override(
            fixBabelImports('import', {
                libraryName: 'antd',
                libraryDirectory: 'es',
                style: true,//变更参数，通过更改css样式按需引入
            }),
            //添加addLessLoader【旧版less】
            addLessLoader({ //解析loader
               javascriptEnabled:true, //允许修改底层less文件
               modifyVars:{ '@primary-color': 'orange' },//修改某个变量
            })
            //【新版less】
            addLessLoader({
               lessOptions:{
                   javascriptEnabled:true,
                   modifyVars:{ '@primary-color': 'orange' },
               }
             })
      );
      ```

   4. 修改后重启

   5. 推荐使用新版4.x配置简单。

## Redux

1. 介绍：

   1. redux时一个专门用于做状态管理的 JS库（非react插件库）。
   2. 它可以用在react，angular，vue等项目中，但基本与react配合使用。
      1. vue可以使用`vuex`进行管理。
   3. 作用：集中式管理react应用中多个组件共享的状态。

2. 什么情况下使用redux

   1. 某个组件的状态，需要让其他组件可以随时拿到（共享）。
   2. 一个组件需要改变另外一个组件的状态（通信）。
   3. 总体原则：能不用就不用，如果不用比较吃力才考虑使用。

3. ![redux原理图](https://github.com/brant8/vue2-3-Css/blob/main/pictures/redux_basic.png)

   1. 注：Reducers作为*初始化状态*和*加工状态*。初始化时，previousState的初始值时`undefined`。
   2. 注：action实际为对象，dispatch为方法。
   3. 注：type初始化状态值：`@@init@@`

4. Redux的三个核心概念

   1. action：
      1. 动作的对象
      2. 包含2个属性
         1. type：标识属性，值为字符串，唯一，必要属性。
         2. data：数据属性，值类型任意，可选属性
      3. 例子：`{type:'ADD_STUDENT,data:{name:'tom',age:18}}`
   2. reducer：
      1. 用于初始化状态、加工状态。
      2. 加工时，根据旧的state和action，产生新的state的纯函数。
   3. store
      1. 将state、action、reducer联系在一起的对象
      2. 如何得到此对象？
         1. `import {createStore} from 'redux'`
         2. `import reducer from './reducers'`
         3. `const store = createStore(reducer)`
      3. 此对象的功能
         1. `getState()`：得到state
         2. `dispatch(action)`：分发action，触发reducer调用，产生新的state
         3. `subscribe(listener)`：注册监听，当产生了新的state时，自动调用。
   4. 补充：`ref`用户获取用户输入的值、选择的值

5. 通过案例了解Redux

   1. **原始react方式**，求和，加、减一；奇数加一、异步加一

      ```jsx
      state={
          count:0
      }
      increment=()=>{
          const {value} = this.selectNumber; //获得的是字符串
          const {count} = this.state;
          this.setState({count:count+value*1});
      }
      decrement=()=>{
          const {value} = this.selectNumber; //获得的是字符串
          const {count} = this.state;
          this.setState({count:count-value*1});
      }
      incrementIfOdd=()=>{
          const {value} = this.selectNumber; //获得的是字符串
          const {count} = this.state;
          if(count%2 !==0) {
              this.setState({count: count + value * 1});
          }
      }
      incrementAsync=()=>{
          const {value} = this.selectNumber; //获得的是字符串
          const {count} = this.state;
          setTimeout(()=>{
              this.setState({count:count+value*1});
          },500)
      
      }
      
      render() {
          return (
              <div>
                  <h1>当前求和为：{this.state.count}</h1>
                  <select ref={c=>this.selectNumber=c}>
                      <option value="1">1</option>
                      <option value="2">2</option>
                      <option value="3">3</option>
                  </select> &nbsp;
                  <button onClick={this.increment}>+</button>&nbsp;
                  <button onClick={this.decrement}>-</button>&nbsp;
                  <button onClick={this.incrementIfOdd}>当前求和为奇数再加</button>&nbsp;
                  <button onClick={this.incrementAsync}>异步 加</button>
              </div>
          );
      }
      ```

   2. **redux版迷你方式**（跳过Action Creator）

      ```jsx
      //---- 一、【src/redux/store.js】----
      /*本文件专门用于暴露一个store对象，整个应用只有一个store对象*/
      //引入createStore，专门用于创建redux中最为核心的store对象
      import {createStore} from 'redux';
      //引入为Count组件服务的reducer
      import countReducer from './count_reducer'
      
      export default createStore(countReducer);
      //----- 二、【src/redux/count_reducer.js】-----
      /*
      * 1.该文件是为创建一个Count组件服务的reducer，reducer的本质就是一个函数。
      * 2.reducer函数会接收到两个参数，分别是：之前的状态PreState、动作对象action
      * 3.reducer是纯传函数
      * 4.记得暴露store对象
      **/
      /*----推荐初始化写法----
      const initState = 0;
      function countReducer(preState=initState,action){...}
      * */
      export default function countReducer(preState,action){
          if(preState === undefined) preState = 0;
          //console.log(action); 初始化时输出（尾部随机字符）： {type: '@@redux/INITs.t.y.h.e.9'}
          //从action对象中获取：type、data
          const {type,data} = action;
          //根据type决定如何加工数据，一般此处不用if判断
          switch(type){
              case 'increment':
                  return preState + data;
              case 'decrement':
                  return preState - data;
              default: //相当于初始化的时候赋值，默认赋值'undefined'
                  return preState;
          }
      }
      
      //------- 三、【src/components/Counter/index.js】--------------
      import React, {Component} from 'react';
      //引入store，用于获取redux中保存的状态（state={count:count}给redux管理）
      import store from '../../redux/store'
      
      class Index extends Component {
          //去除Count组件自身的状态State（共享的）
          
      	//【调用render方式刷新页面数据方式一】
          componentDidMount() {
              /*1.因为redux只负责管理状态，状态更新后不刷新页面
              * 2.监测redux中状态的变化，只要变化，就调用render
              **/
              store.subscribe(()=>{ //状态变化就会调用本回调
                  this.setState({});//调用setState赋值空对象，只为让其调用render更新redux中管理的状态
              })
          }
      
          increment=()=>{
              const {value} = this.selectNumber; //获得的是字符串
              //通知redux加value，本例跳过Action Creator，手动赋值对象给dispatch({..})
              store.dispatch({type:'increment',data:value*1})
          }
          decrement=()=>{
              const {value} = this.selectNumber; //获得的是字符串
              store.dispatch({type:'decrement',data:value*1})
          }
          incrementIfOdd=()=>{
              const {value} = this.selectNumber; //获得的是字符串
              const count = store.getState();//本例不用{count} = store.getState()因为案例穿的是一个数字
              if(count%2 !==0) {
                  store.dispatch({type:'increment',data:value*1})
              }
          }
          incrementAsync=()=>{
              const {value} = this.selectNumber; //获得的是字符串
              setTimeout(()=>{
                  store.dispatch({type:'increment',data:value*1})
              },500)
      
          }
      
          render() {
              return (
                  <div>
                      <h1>当前求和为：{store.getState()}</h1> {/*专门用于获取状态*/}
                      <select ref={c=>this.selectNumber=c}>
                          <option value="1">1</option>
                          <option value="2">2</option>
                          <option value="3">3</option>
                      </select> &nbsp;
                      <button onClick={this.increment}>+</button>&nbsp;
                      <button onClick={this.decrement}>-</button>&nbsp;
                      <button onClick={this.incrementIfOdd}>当前求和为奇数再加</button>&nbsp;
                      <button onClick={this.incrementAsync}>异步 加</button>
                  </div>
              );
          }
      }
      
      export default Index;
      
      //【调用render方式刷新页面数据方式二】 ：在----【src/index.js】-----更改
      import store from "./redux/store";
      
      store.subscribe(()=>{
           ReactDom.render(<App/>,document.getElementById('root'));
      })
      ```

      1. 注：在`/src/index.js`中刷新`<App/>`不会对整个页面全部组件刷新，关键字：DOM Diff算法。
      2. store.js：
         1. 引入redux中的createStore函数，创建一个store。
         2. createStore调用时要传入一个围棋服务的reducer
         3. 记得暴露store对象
      3. count_reducer.js：
         1. reducer的本质是一个函数，接收：preState、action，返回加工后的状态。
         2. reducer的两个作用：初始化状态，加工状态。
         3. reducer被第一次调用时，是store自动触发的
            1. 传递的preState是undefined
            2. 传递的acton是：{type:'@@REDUX/INIT_X.Y.A.X'}
      4. 在index.js中检测store中状态的改变，一旦发生改变重新渲染`<App/>`
         1. 备注：redux只负责管理状态，至于状态的改变驱动者页面的展示，要靠自己写。

   3. 知识补充

      ```js
      const a= ()=>1
      console.log(a()) //输出：1
      
      const a= b=>b+1
      console.log(a(9)) //输出：10
      
      const a= b=>{data:b} //输出对象
      console.log(a(9)) //输出：undefined
      
      const a= b=>{ return {data:b} } //输出对象
      console.log(a(9)) //输出：{data: 9}
      
      const a= b=>({data:b}) //输出对象
      console.log(a(9)) //输出：{data: 9}
      ```

   4. **redux完整版本（有action creator）**

      ```jsx
      //------ 【src/redux/count_action.js】-------
      /*该文件专门为Count组件生成action对象*/
      /*function createIncrementAction(data){
          return {type:'increment',data};
      }  有暴露需要，因此需要写下面方式*/
      export const createIncrementAction= (data)=>{
          return {type:'increment',data};
      }
      export const createDecrementAction= data=>({type:'decrement',data});
      
      //------ 【src/components/Counter/index.js】更改迷你版部分即可-------
      // 引入actionCreator，专门用于创建action对象
      import {createDecrementAction,createIncrementAction} from '../../redux/count_action'
      
      increment=()=>{
          const {value} = this.selectNumber; //获得的是字符串
          //通知redux加value，本例跳过Action Creator，手动赋值对象给dispatch({..})
          store.dispatch(createIncrementAction(value*1))
      }
      
      //------补充 【src/redux/constant.js】-------
      /*该模块用于定义action对象中type类型的常量值*/
      export const INCREMENT = 'increment';
      export const DECREMENT = 'decrement';
      ```

   5. 同步action与异步action：

      1. action可以是对象`Object {}`，也可以是函数ƒ `function`。

      2. 同步action，即对象。在action中的变量得到的是 Object的返回值。如下；

         ```js
         export const createIncrementAction = data=>({type:'increment',data});
         ```

      3. 异步action，即函数。在action中的返回值为 ƒ 函数。原因：可以在函数内部写任意代码开启异步任务。

         ```js
         export const createIncrementAction = (data,time)=>{
             return ()=>{
                 setTimeout(()=>{
                     store.dispatch({type:'increment',data});
                     /*或者使用现成的*/
                     store.dispatch(createIncrementAction(data));
                 },time)
             }
         }
         ```

   6. **redux的异步action使用**（上方例子setTimeout异步方法都是在组件中，非action中设置）

      1. 先安装中间件：`npm install redux-thunk`（非官方）

      2. 在store.js中引入且暴露使用

         ```jsx
         //导入applyMiddleware，应用中间件
         import {createStore,applyMiddleware} from 'redux';
         import countReducer from './count_reducer';
         //引入redux-thunk,用于支持异步action
         import thunk from 'redux-thunk'
         
         export default createStore(countReducer,applyMiddleware(thunk));
         ```

      3. 在action中使用

         ```js
         export const createIncrementAction= (data)=>{
             return {type:'increment',data};
         }
         export const createDecrementAction= data=>({type:'decrement',data});
         //异步action，就是指action的值为函数，异步action中【一般都会调用同步action】；异步action不是必须要用的
         export const createIncrementAsyncAction = (data,time)=>{
             //方式一：手动导入store
             return ()=>{
                 setTimeout(()=>{
                     store.dispatch(createIncrementAction(data));
                 },time);
             }
             //方式二：(非导入)return后实际是store调用该回调，所以可以接收到dispatch
             return (dispatch)=>{
                 setTimeout(()=>{
                     dispatch(createIncrementAction(data));
                 },time);
             }
             
         }
         ```

      4. 流程：

         1. `/Counter/index.js`中收到`store.dispatch(createIncrementAsyncAction(value*1,500))`调用请求（store的第一个dispatch）
         2. 然后异步时间到达后，store再次调用`store.dispatch(createIncrementAction(data));`（store的第二个dispatch）
         3. 官方：开启了中间件后，`dispatch()`如果发现action是一个函数，store调用，第二次dispatch把一般对象传给store，store在交给reducer加工。（个人理解：最后只能使用对象形式在reducer进行处理，函数形式需要多次返回得到对象 -- 同步处理）

      5. 异步总结：

         1. 明确：延迟的动作不想交给组件自身，香蕉给action
         2. 何时需要异步action：想要对状态进行操作，但是具体的数据靠异步任务返回。
         3. 具体编码：
            1. 安装reux-thunk，并配置在store中
            2. 创建action的函数不再返回一般对象，而是一个函数，该函数中写异步任务。
            3. 异步任务有结果后，分发一个同步的action去真正操作数据。
         4. 备注：异步action不是必须要写的，完全可以自己等待异步任务的结果了再去分发同步action。

   7. react-redux模型图

      ![图](https://github.com/brant8/vue2-3-Css/blob/main/pictures/react_redux_model.png)

   8. react-redux官方维护的redux

      1. 规定components只负责数据的展示，要用数据共享需要在外层套一个container。
      2. 目录分别components、container

   9. 使用react-redux

      1. 安装库：`npm install react-redux`

      2. `App.js`给容器组件传入`store`和在`src/index.js`对组件监听渲染

         ```jsx
         /*----src/index.js---*/
         import store from "./redux/store";
         ...
         ReactDom.render(<App/>,document.getElementById('root'));
         
         store.subscribe(()=>{
             ReactDom.render(<App/>,document.getElementById('root'));
         })
         /*----App.js----*/
         //注意此处用到的是containers，而不是components
         import Count from './containers/Count'
         import store from './redux/store'
         ...
         render() {
                 return (
                     <div>
                         {/*给容器组件传递store*/}
                         <Count store={store}/>
                     </div>
                 );
             }
         ```

      3. 在组件UI中正常使用组件参数

         ```jsx
         ... 
         increment=()=>{
                 const {value} = this.selectNumber; //获得的是字符串
                 this.props.jia(value*1) //来自组件容器的对象（方法）
         
             }
             decrement=()=>{
                 const {value} = this.selectNumber; //获得的是字符串
                 this.props.jian(value*1)
             }
             incrementIfOdd=()=>{
                 const {value} = this.selectNumber; //获得的是字符串
                 if(this.props.count%2 !== 0){
                     this.props.jia(value*1)
                 }
             }
             incrementAsync=()=>{
                 const {value} = this.selectNumber; //获得的是字符串
                 this.props.jiaAsync(value*1,500)
             }
         
             render() {
                 console.log(this.props)
                 return (
                     <div>
                         <h1>当前求和为：{this.props.count}</h1>
                         <select ref={c=>this.selectNumber=c}>
                             <option value="1">1</option>
                             <option value="2">2</option>
                             <option value="3">3</option>
                         </select> &nbsp;
                         <button onClick={this.increment}>+</button>&nbsp;
                         <button onClick={this.decrement}>-</button>&nbsp;
                         <button onClick={this.incrementIfOdd}>当前求和为奇数再加</button>&nbsp;
                         <button onClick={this.incrementAsync}>异步 加</button>
                     </div>
                 );
             }
         ```

      4. 正常使用redux，创建文件：`redux/count_action.js`、`redux/count_reducer.js`、`redux/store.js`

      5. 在组件容器container中使用react-redux连接redux和react

         ```js
         /*------src/containers/Count/index.js-------*/
         /*1. 引入Count的UI组件 */
         import CountUI from '../../components/Count'
         /*6. 引入react-redux中的store(测试)
         *	 这里不使用import导入store，
         * 	 需要在App中Count组件当作参数导入，直接使用store的方法dispatch
         */
         /*2.引入connect用于连接UI组件与redux */
         import {connect} from 'react-redux'
         import {createIncrementAction,createDecrementAction,createIncrementAsyncAction} from '../../redux/count_action'
         
         /*3. a函数【返回的对象】值作为状态传递给了UI组件的props的{key:value}
         *	 （根据模型图【容器与UI通过props传递状态】）
         *	 value就作为传递给UI组件props的value---状态
         * 	 react-redux调用a函数的时候，state传入到该函数，此函数名为a的，
         *	 官方使用mapStateToProps
         */
         function a(state){
             return {count:state};
             /*return 1; ---用于测试错误信息，输出官方方法命名---*/
         }
         /*4. 根据模型图 【UI通过props操作容器状态的方法】
         *	 此函数名为b的，官方使用mapDispatchToProps
         */
         function b(dispatch){
             return {
                 jia:(number)=>{
                     //注意此处相当于函数名为jia的方法对象
                     //通知redux执行加法(通过dispatch(action对象)，操作状态)
                     dispatch(createIncrementAction(number));
                 },
                 jian:(number)=>{ 
                     dispatch(createDecrementAction(number));
                 },
                 jiaAsync:(number,time)=>{
                     dispatch(createIncrementAsyncAction(number,time));
                 }
             }/*return 1; ---用于测试错误信息，输出官方方法命名---*/
         }
         
         /*5. 使用connect()()创建并暴露一个Count的容器组件
         *	（让compoents和containers的Count产生联系）
         *	 此处的函数a和函数b作为形参，官方有固定的名称
         */
         export default connect(a,b)(CountUI)
         ```

   10. **react-redux总结**

       1. 明确概念：
          1. UI组件：不能使用任何redux的API，只负责页面的呈现、交互等。
          2. 容器组件：负责和redux通信，将结果交给UI组件。
       2. 如何创建一个容器组件---靠react-redux的connect函数。
          1. `connect(mapStateToProps,mapDispatchToProps)(UI组件名)` 
             1. mapStateToProps：映射状态，返回值是一个对象
             2. mapDispatchToProps：映射操作状态的方法，返回值是一个对象
       3. 备注：容器组件中的store是靠props传进去的，而不是在容器组件中直接引入。

   11. **优化** - mapDispatchToProps的简写

       ```js
       //----src/containers/Count/index.js---
       //一般写法
       function mapDispatchToProps(dispatch){
           return {
               jia:(number)=>dispatch(createIncrementAction(number)),
               jian:(number)=>dispatch(createDecrementAction(number)),        
               jiaAsync:(number,time)=>dispatch(createIncrementAsyncAction(number,time))
           }
       }
       //简写：提供action方法，react-redux自动使用dispatch分发给UI组件
       function mapDispatchToProps(dispatch){
           return {
               jia:createIncrementAction,//从count.action.js中导入的函数
               jian:createDecrementAction,        
               jiaAsync:createIncrementAsyncAction,
           }
       }
       ```

   12. **优化** - src/index.js中可以省略的

       ```js
       //此处可省略store.subscribe，react-redux自动检测状态自动刷新render
       store.subscribe(()=>{
           ReactDom.render(<App/>,document.getElementById('root'));
       })
       ```

   13. **优化** - 简化App.js中的传递store方式

       ```jsx
       /*----App.js---*/
       import store from './redux/store'
       ...
       render() {
               return (
                   <div>
                       {/*给容器组件传递store*/}
                       <Count1 store={store}/>
       				<Count2 store={store}/>
          				<Count3 store={store}/>
                   </div>
               );
       }
       /*-----src/index.js----*/
       /* 在index.js中使用Provider替代App.js中的多次引用store
       *  Provider自动分配store给container
       */
       import store from './redux/store'
       import {Provider} from 'react-redux'
       ReactDom.render(
       	<Provider store={store}>
           	<App/>
           </Provider>,
           document.getElementById('root')
       )
       ```

   14. **优化** - 整合容器container和组件UI 一个文件到container中

       ```jsx
       //----src/containers/Count/index.jsx--------
       //---------UI引入----------
       import React, {Component} from 'react';
       //---------Container引入----------
       import CountUI from '../../components/Count'
       import {connect} from 'react-redux'
       import {createIncrementAction,createDecrementAction,createIncrementAsyncAction} from '../../redux/count_action'
       
       //#UI组件#
       class Index extends Component {
       
           increment=()=>{
               const {value} = this.selectNumber; //获得的是字符串
               this.props.jia(value*1)
           }
       	//...省略代码
           render() {
               console.log(this.props)
               return (
                   <div>
                       {/*...(省略代码)*/}
                   </div>
               );
           }
       }
       //#container容器#
       function a(state){
           return {count:state};
       }
       
       function b(dispatch){
           return {
               jia:(number)=>{
                   dispatch(createIncrementAction(number));
               },
               jian:(number)=>{ dispatch(createDecrementAction(number))},
               jiaAsync:(number,time)=>{dispatch(createIncrementAsyncAction(number,time));}
           }
       }
       //注意export default只能暴露一次，此处只需要暴露Container组件即可
       export default connect(a,b)(CountUI)  
       /**简化connect写法：
       connect(
       	state => ({key:value}), //映射状态
       	{key:xxxAction} 		//映射操作状态的方法
       )(UI组件)
       在UI组件中通过this.props.xxx读取和操作状态*/
       ```

   15. 多组件容器相互通信：统计人数和计算求和

       1. 在求和中显示总人数，在添加人数中显示计算求和

       2. 在组件容器中，使用`import {connect} from 'react-redux';`

          1. 目的：让该组件容器与redux通信![图](https://github.com/brant8/vue2-3-Css/blob/main/pictures/react_redux_person_connet.png)

       3. Person容器container组件代码

          ```jsx
          import React, {Component} from 'react';
          import {nanoid} from 'nanoid';
          import {connect} from 'react-redux';
          import {createAddPersonAction} from "../../redux/actions/person";
          
          class Index extends Component {
              addPerson=()=>{
                  const name = this.nameNode.value;
                  const age = this.ageNode.value;
                  const personObj = {id:nanoid(),name,age*1};//注意的大批的数字是字符串
                  this.props.jiaYiRen(personObj)
                  this.nameNode.value='';
                  this.ageNode.value='';
              }
              render() {
                  return (
                      <div>
                          <h1>Person添加人</h1>
                          <h2>上方求和结果：{this.props.he}</h2>
                          <input type="text" ref={c=>this.nameNode=c} placeholder="输入名字" />
                          <input type="text" ref={c=>this.ageNode=c} placeholder="年龄" />
                          <button onClick={this.addPerson}>添加</button>
                          <ul>
                              {
                                  this.props.yiduiren.map((p)=>{
                                      return <li key={p.id}>{p.name}---{p.age}</li>
                                  })
                              }
                          </ul>
                      </div>
                  );
              }
          }
          
          export default connect(
              state=>({yiduiren:state.rens,he:state.he}), //映射状态
              {jiaYiRen:createAddPersonAction}//映射操作状态的方法
          )(Index);
          ```

       4. person的action：

          ```jsx
          //----src/redux/actions/person.js----
          export const createAddPersonAction = personObj=> ( {type:'add_person',data:personObj} )
          ```

       5. person的reducers：

          ```js
          //----src/redux/reducers/person.js----
          const initState = [{id:'001',name:'zhangsan',age:18}]
          
          //此处的personReducer必须是纯函数，否则数据响应不刷新。如下面讲解的纯函数
          export default function personReducer(preState=initState,action){
              const {type,data} = action;
              switch (type) {
                  case 'add_person':
                      return [data,...preState];
                  default:
                      return preState;
              }
          }
          ```

       6. 中心store组件

          ```jsx
          import {createStore,applyMiddleware,combineReducers} from 'redux';
          import thunk from 'redux-thunk'
          
          import countReducer from './reducers/count';
          import personReducer from "./reducers/person";
          
          //需要合并Reducers；combineReducers传入的对象就是redux保存的的【总reducer】对象(单个reducer非对象，多个reducer为对象)
          const AllReducer = combineReducers({
              he:countReducer,
              rens:personReducer
          })
          
          export default createStore(AllReducer,applyMiddleware(thunk));
          ```

       7. 总结：

          1. 定义一个Person组件，和Count组件通过redux共享数据。
          2. 为Person组件编写：reducer、action，配置constant常量。
          3. 重点：Person的reducer和Count的Reducer要使用combineReducers进行合并，合并后的总状态是一个对象。
          4. 交给store的是总reducer，最后注意在组件中取出状态的时候，记得"取到位"。
          5. 若需要多个reducer汇总时，一般单独创建一个reducer文件再import到store中。

       8. 补充1：若在reducer中不使用ES6新语法`[data,...preState]`，而是使用`preState.unshift(data)`方式来添加数据到数组中，得不到数据。

          1. ```jsx
             //---reducer/person.js-----
             //正确代码
             switch (type) {
                 case 'add_person':
                     return [data,...preState];
                 default:
                     return preState;
             }
             //不正确代码
             switch (type) {
                 case 'add_person':
                     preState.unshift(data)
                     return preState; //浅比较
                 default:
                     return preState;
             }
             ```

          2. 原因：redux若返回的也是preState，进行浅比较，即地址值的比较，若一样，则不更新。

   16. 补充2：纯函数和高阶函数

       1. 纯函数：

          1. 一类特别的函数：只要是同样的输入（实参），必定得到同样的输出（返回）

             ```js
             function demo(a){ //非纯函数
                 a=9;//改写了数据
             }
             ```

          2. 必须遵守以下一些约束

             1. 不得改写参数数据
             2. 不会产生任何副作用，例如网络请求、输入和输出设备
             3. 不能调用`Data.now()`或者`Math.random()`等不纯的方法（同样调用，不同输出为不纯）

          3. redux的reducer函数必须是一个纯函数

       2. 高阶函数

          1. 理解：一类特别的函数
             1. 情况1：参数是函数
             2. 情况2：返回时函数
          2. 参见的高阶函数：
             1. 定时器设置函数
             2. 数组的`forEach()/map()/filter()/reduce()/find()/bind()`

       3. 使用redux-react开发者工具（浏览器插件）

          1. 到谷歌商店下载`Redux devTool`

          2. 窗口命令安装：`npm install redux-devtools-extension`

          3. 到`store.js`中添加代码：

             ```js
             import {composeWithDevTools} from 'redux-devtools-extension'
             ...
             export default createStore(AllReducer,applyMiddleware(thunk));
             //情景一，没有异步参数时，直接当第二参数，改为：
             export default createStore(AllReducer,composeWithDevTools());
             //情景二，有异步参数时，改为
             export default createStore(AllReducer,applyMiddleware(applyMiddleware（thunk)));
             ```

          4. 重启react

6. 打包React

   1. 停掉服务器的运行。

   2. 运行命令：`npm run build` 。 生成目录`build/static`即静态文件。

   3. 创建服务器

      1. 可以使用Node- express或者Java等

      2. 在想要开启服务器的目录下，使用serve运行。

         ```js
         The build folder is ready to be deployed.
         You may serve it with a static server:
         
           npm install -g serve
           serve -s build
         ```


## React扩展

1. ##### `setState`更新状态的2种写法

   1. `setState(stateChange, [callback])` -----**对象式的setState**

      1. stateChange为状态改变对象（该对象可以体现出状态的更改）
      2. callback是可选的回调函数，它在状态更新完毕、界面也更新后（render调用后）才被调用

      ```js
      state ={
              count:0
      }
      increment = ()=>{
          const {count} = this.state; //---需要手动获取原来状态值----
          console.log(count);	//第一次输出：0
          this.setState({count:count+1});//①
          this.setState({count:count+1},()=>{ //②
              console.log(this.state.count) //第一次输出：1
          });
          console.log("异步",this.state.count);//第一次输出：异步 0
      }
      /*
      ①：setState()小括号内是同步方法，但是，setState引起React后续状态更新的动作是异步的。
      即，先执行代码，再执行状态更新。
      ②：回调中可以查看状态更新后的数据
      */
      ```

   2. `setState(updater,[callback])` ---- **函数式的setState**

      1. updater为返回stateChange对象的函数。
      2. updater可以接收到state和props。
      3. callback时可选的回调函数，它在状态更新、界面也更新后（render调用后）才被调用。

      ```js
      decrement = ()=>{
          this.setState((state,props)=>{ //---不需要手动获取原来状态值，直接传入即可---
          /*该函数能从外面接收到的两个参数，比如App中传入<Demo x={100}*/
              console.log(state,props) //输出： {count:0} {x:100}
              return {count:state.count-1}
          })
      }
      ```

   3. 总结：

      1. 对象式的setState是函数式的setState的简写方式（语法糖）
      2. 使用原则：
         1. 如果新状态不依赖原装胎 ==> 使用对象方式
         2. 如果新状态依赖于原装胎 ==> 使用函数方式
         3. 如果需要在setState()执行后获取最新的状态数据，需要在第二个callback函数中读取。

2. ##### `lazyload`懒加载

   1. 一般做路由组件的懒加载

   2. 加载页面的时候，默认所有路由一次性加载，即使不点击使用。

   3. 懒加载`lazy`一般配合`Suspense`使用，用于网络延迟时显示的内容，*可以使用组件，也可以直接使用HTML*。

      ```js
      import React, {Component,lazy,Suspense} from 'react'; 
      //lazy懒加载；Suspense解决网络延迟没有及时响应给客户端的情况
      //1.通过React的lazy函数配合import()函数动态加载路由组件  ===> 路由组件代码会被分开打包
      //不再通过import Home from '...'方式加载组件
      const Login = lazy(()=>{import('@/page/Login')})
      //2.通过<Suspense>指定再加载得到路由打包文件前显示一个自定义loading界面
      <Suspense fallback{<h1>loading...</h1>}>
      	<Switch>
          	<Route path="/xxx" component={Xxx}/>
              <Redirect to="/login"/>
          </Switch>
      </Suspense>
      ```

3. ##### `Hooks/React Hook`

   1. Hook是React 16.8.0版本增加的新特性/新语法

      1. 可以再函数组件中使用state以及其他的React特性。

   2. 三个常用的Hook

      1. State Hook： `React.useState()`
      2. Effect Hook： `React.useEffect()`
      3. Ref Hook：`React.useRef()`

   3. *State Hook* **官方案例都是函数式组件**

      1. State Hook让函数组件也可以有state状态，并进行状态数据的读写操作。
      2. 语法：`const [xxx, setXxx] = React.useState(initValue)`
      3. `useState()`说明：
         1. 参数：第一次初始化指定的值再内部做缓存
         2. 返回值：包含2个元素的数组。第1个为内部当前状态值，第2个为更新状态值的函数。
      4. `setXxx()`2种写法：
         1. `setXxx(newValue)`：参数为非函数值，直接指定新的状态值，内部用其覆盖原来的状态值。
         2. `setXxx(value => newValue)`：参数为函数，接收原本的状态值，返回新的状态值，内部勇气覆盖原来的状态值。

      ```jsx
      //函数式组件
      function Demo(){
          console.log('Demo调用次数+1')
          /*React.useState()得到的是数组[]。
          数组[x,y]包含x状态(值)、y更新状态的方法。count于初始值0对应。*/
          const [count,setCount] = React.useState(0);//第二次指定Demo函数时，React覆盖初始值，count被缓存下来
          //加的回调
          function add(){
              /*第一种写法setCount()*/
              setCount(count+1);
              /*第二种写法setCount()*/
              setCount( (count)=>{count+1} )
          }
          return (
              <div>
                  <h2>当前求和为：{count}</h2>
                  <button onClick={add}>点击加一</button>
              </div>
          );
      }
      
      export default Demo;
      ```

   4. *Effect Hook*

      1. Effect Hook可以在函数组件中执行副作用操作（用于模拟类组件中的生命周期钩子）

      2. React中的副作用操作：

         1. 发ajax请求数据获取
         2. 设置订阅、启动定时器
         3. 手动更改真实DOM

      3. 语法和说明：

         ```js
         React.useEffect( ()=>{..} );//不带有[]，则监测所有状态变化，状态更新一次调用一次括号内函数
         React.useEffect( ()=>{..},[] );//带有空数组，只在第一次调用，其他时候不再调用括号内函数
         React.useEffect( ()=>{..},[stateValue] )//带有[参数]，只监测该状态的变化，每次该状态变化调用一次括号内函数
         //讲解：
         React.useEffect( ()=>{
             //在此可以执行任何带副作用操作
             return ()=>{ //在组件卸载前执行
                 //在此做一些首位工作，比如清除定时器、取消订阅等
             }
         },[stateValue] )//如果指定的是[]，回调函数只会在第一次render()后执行
         ```

      4. 可以把 useEffect Hook看作如下三个函数的结合

         1. `componentDidMount()`、`componentDidUpdate()`、`componentWillUnmount()`

      ```js
      React.useEffect(()=>{
          //操作内容相当于componentDidMount
          let timer = setInterval(()=>{
              setCount(count=>count+1)
          },1000);
          //return返回内容相当于unmountComponentAtNode
          return ()=>{
              //操作的内容相当于componentWillUnmount
              clearInterval(timer)
          }
      },[])
      ```

   5. *Ref Hook*

      1. Ref Hook可以在函数组件中存储、查找组件内的标签或任意其他数据
      2. 语法：`const refContainer  = useRef()`
      3. 作用：保存标签对象，功能于`React.createRef()`一样

      ```jsx
      //类式
          myRef = React.createRef()
          show = ()=>{
          	console.log(this.myRef.current.value);
          }
      render(){
          ...
          <input type="text" ref={this.myRef}/> <br/>
          <button onClick={this.show}>点击显示数据</button>
      }
      //函数式
          const myRef = React.useRef();
          function show(){
              console.log(myRef.current.value)
          }
      render(){
          <input type="text" ref={myRef}/>
          <button onClick={show}>点击提示输入数据</button>
      }
      ```

4. ##### Fragment

   1. 可以省略每个组件当中的`<div>`标签

   2. 比如，在组件中不想要标签包围着内容，可以用fragment

      ```jsx
      import React, {Component,Fragment} from 'react';
      ...
          render() {
              return (
                  <Fragment key={1}> {/**Fragment被React解析并在HTML去掉*/}
                      <input type="text"/>
                  </Fragment>
              );
          }
      ```

   3. 也可以使用空标签`<>`

      ```jsx
          render() {
              return (
                  <> 
                      <input type="text"/>
                  </>
              );
          }
      ```

   4. Fragment与空标签的区别：

      1. Fragment允许在遍历的组件中写`key`值。
      2. 空标签不允许写任何属性。

5. ##### Context容器对象

   1. Context式一种组件间通信方式，常用于【祖组件】与【后代组件】间通信

   2. `context`在类组件实例中的`this`可以观察到与`props`，`refs`，`state`同级。

   3. 使用

      1. 创建Context容器对象：

         1. `const XxxContext = React.createContext()`

      2. 渲染组件时，外面包裹`xxxContext.Provider`，通过`value`属性给后代组件传递数据；其中的Provider类似redux中的Provider。

         ```jsx
         <XxxContext.Provider value={数据}>
             子组件
         </XxxContext.Provider>
         ```

      3. 后代组件读取数据

         ```jsx
         //第一种方式：仅适用于类组件
         static contextType = xxxContext //声明接收context
         this.context //读取context中的value数据
         
         //第二种方式：【函数组件与类组件】都可以
         <XxxContext.Consumer>
           {
             value=>( //value就是context中的value数据
            	  要显示的内容
             )
           }
         </XxxContext.Consumer>
         ```

      4. 注意：在应用开发中一般不用context，一般用它的封装react插件，如react-redux。

6. 























































