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
    )
}