//开发的时候使用base+dev
const {webpackMerge} = require("webpack-merge");
const baseConfig = require('./base.config')

module.exports= webpackMerge(baseConfig,{
    devServer:{
        static: {
            directory: path.join(__dirname, 'dist'),
        },
        compress: true,
        port: 9000,
    }
})


