const express = require('express');
const app = express();

app.get('/home',(request,response)=>{
    //响应一个页面
    response.sendFile(__dirname + '/6-index.html')
});
app.get('/data',(request, response)=>{
    response.send('用户数据');
});

//jsonp服务
app.all('/jsonp-server',(request,response)=>{
    // response.send('hello jsonp-server'); //->输出字符串 -> 前端使用<script src='localhost/jsonp-server'>无法识别返回的内容（非JS），报错
    // response.send('console.log("hello jsonp")');   //->返回js代码可在console输出
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

app.listen(9000, ()=>{
    console.log("服务器已经启动");
})