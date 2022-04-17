
const path = require('path')
//去node的包找叫做path的，此处必须要有path的包

module.exports= {
    entry: './src/main.js',
    output: {
        path: path.resolve(__dirname,'dist'), //__dirname为node参数，拼接dist组成绝对路径
        filename: 'bundle.js',
    },
    //1.配置css-loader
    module:{
        rules:[
            {   //  `/正则/` => `\.`转义保留`.`，并且以`css$`结尾
                test:/\.css$/,  //2.css-loader只负责将css文件进行加载，不负责解析
                use:['style-loader','css-loader'], //3.style-loader负责将样式添加到DOM中，使用多个loader时从右向左读，顺序不对报错
            },
        ],
    }

}


