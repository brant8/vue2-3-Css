
const path = require('path')
//去node的包找叫做path的，此处必须要有path的包

const VueLoaderPlugin = require('vue-loader/lib/plugin')
const webpack = require('webpack');
//1.1导入HTML插件路径
const HtmlWebpackPlugin = require('html-webpack-plugin');
//1.2导入uglifyjs路径
//const UglifyjsWebpackPlugin = require('uglify-js');
const TerserPlugin = require("terser-webpack-plugin");

const WebpackDevServer = require('webpack-dev-server');

module.exports= {
    mode: 'development',
    entry: './src/main.js',
    output: {
        path: path.resolve(__dirname,'dist'), //__dirname为node参数，拼接dist组成绝对路径
        filename: 'bundle.js',
    },
    optimization: { //2.2uglifyjs无法使用，使用官方的压缩js
        minimize: true,
        minimizer: [new TerserPlugin()],
    },
    //2.注册插件
    plugins:[
        new VueLoaderPlugin(),
        new webpack.BannerPlugin('最终版权归Brant所有~'),
        new HtmlWebpackPlugin({
            template:'1Promise使用和链式调用.html', //根据根目录下的index生成新的index并且添加内容
        }),
        //new UglifyjsWebpackPlugin(), //2.1压缩js代码，无法使用
    ],
    resolve:{
      alias:{ // vue$表示vue结尾的，查看main.js里面的导入的vue
          'vue$':'vue/dist/vue.esm.js' //'vue/dist/vue.common.js' for webpack 1
      }
    },
    module:{
        rules:[
            {
                test:/\.css$/,
                use:['style-loader','css-loader'],
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
            {
                test:/\.vue$/,
                use:{
                    loader:'vue-loader',
                    //需要额外配置plugin,参考：https://www.npmjs.com/package/vue-loader-plugin
                },
            },
        ],
    },
    devServer:{
        static: {
            directory: path.join(__dirname, 'dist'),
        },
        compress: true,
        port: 9000,
    }
}


