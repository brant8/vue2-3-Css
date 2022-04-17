
const path = require('path')
const VueLoaderPlugin = require('vue-loader/lib/plugin')
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports= {
    mode: 'development',
    entry: './src/main.js',
    output: {
        path: path.resolve(__dirname,'../dist'),
        filename: 'bundle.js',
    },
    plugins:[
        new VueLoaderPlugin(),
        new webpack.BannerPlugin('最终版权归Brant所有~'),
        new HtmlWebpackPlugin({
            template:'1Promise使用和链式调用.html',
        }),
    ],
    resolve:{
        alias:{
            'vue$':'vue/dist/vue.esm.js'
        }
    },
    module:{
        rules:[
            {
                test:/\.css$/,
                use:['style-loader','css-loader'],
            },
            {
                test: /\.jpg/,
                type: 'asset/inline',
            },
            {
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
                        "presets": ["@babel/preset-env"],
                    }
                }
            },
            {
                test:/\.vue$/,
                use:{
                    loader:'vue-loader',
                },
            },
        ],
    },
}


