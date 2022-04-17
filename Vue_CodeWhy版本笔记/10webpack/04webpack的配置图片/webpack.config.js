
const path = require('path')
//去node的包找叫做path的，此处必须要有path的包

module.exports= {
    mode: 'development',
    entry: './src/main.js',
    output: {
        path: path.resolve(__dirname,'dist'), //__dirname为node参数，拼接dist组成绝对路径
        filename: 'bundle.js',
        //publicPath: '/dist/' //如果不显示图片，尝试添加此处，自动给图片加载/dist/...tabbar
        //assetModuleFilename:'tabbar/[name].[hash:8].[ext]',
    },
    //1.配置css-loader
    module:{
        rules:[
            {   //  `/正则/` => `\.`转义保留`.`，并且以`css$`结尾
                test:/\.css$/,  //2.css-loader只负责将css文件进行加载，不负责解析
                use:['style-loader','css-loader'], //3.style-loader负责将样式添加到DOM中，使用多个loader时从右向左读，顺序不对报错
            },
            //版本5使用asset/inline具体: https://webpack.js.org/guides/asset-modules/
            {
                test: /\.jpg/,
                type: 'asset/inline',
            },
            { //此处新版本把图片输出名做更改
                test: /\.jpg/,
                type: 'asset/resource',
                generator: {
                    filename: 'tabbar/[name].[hash:8].[ext]'
                }
            },
            {
                test:/\.js$/,
                exclude:/(node_modules|bower_components)/,
                use:{
                    loader:'babel-loader',
                    options:{
                        //presets:['es2015'],
                        "presets": ["@babel/preset-env"],//@babel/preset-env for compiling ES2015+ syntax
                    }
                }
            },
            // { //webpack自动生成的长名字为32位hash值，防止名字重复
            //   test:/\.(png|jpg|gif)$/,
            //   use:[
            //       {
            //           loader:'url-loader',
            //           options:{
            //               //当加载图片小于limit时候，会将图片编译成base64字符串格式。
            //               //大于limit时，使用file-loader模块进行加载
            //               limit:5000,
        //                   name:'tabbar/[name].[hash:8].[ext]',   //重命名图片文件hash：8值为8位
            //           }，
            //
            //       }
            //   ],
            // },
            // {
            //     test: /\.(png|jpe?g|gif)$/,
            //     use: [
            //         {
            //             loader: 'file-loader',
            //         }
            //     ],
            // },
        ],
    }

}


