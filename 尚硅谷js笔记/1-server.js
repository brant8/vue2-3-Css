//1.引入express
const express = require('express');

//创建应用对象
const app = express();

//3.创建路由规则
// request - 对请求报文的封装； response - 对响应报文的封装
app.get('/server',(request,response)=>{
    //设置响应头 设置允许跨域
    response.setHeader('Access-Control-Allow-Origin','*');
    //设置响应体
    response.send('Hello Ajax GET');
});

app.post('/server',(request,response)=>{
    //设置响应头 设置允许跨域
    response.setHeader('Access-Control-Allow-Origin','*');
    //响应头
    response.setHeader('Access-Control-Allow-Headers','*');
    //设置响应体
    response.send('Hello Ajax POST');
});
app.all('/server',(request,response)=>{
    response.setHeader('Access-Control-Allow-Origin','*');
    response.setHeader('Access-Control-Allow-Headers','*');
    response.send('Hello Ajax POST');
});
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