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