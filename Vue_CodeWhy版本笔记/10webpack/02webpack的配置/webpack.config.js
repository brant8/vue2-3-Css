
const path = require('path')
//1.去node的包找叫做path的，此处必须要有path的包
//2.在命令行输入`npm init`一路配置，部分步骤可省略
//3.会在项目根目录下生成package.json文件（node管理包）
//4.如果缺省文件可以再命令行输入`npm install`会自动安装依赖


module.exports= {
    entry: './src/main.js',
    output: {
        //path: './dist',
        //path: '（动态的获取路径）',
        path: path.resolve(__dirname,'dist'), //__dirname为node参数，拼接dist组成绝对路径
        filename: 'bundle.js',
    },
} //有了webpack.config.js后，直接在命令行输入webpack即可。

//`npm run serve`跑项目
//`npm run build`打包项目


