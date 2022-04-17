//最终编译使用prod+base
const TerserPlugin = require("terser-webpack-plugin");
const {webpackMerge} = require('webpack-merge')
const baseConfig = require('./base.config')

module.exports=webpackMerge(baseConfig,{

//module.exports= {     //此处跟baseConfig合并
    optimization: { //2.2uglifyjs无法使用，使用官方的压缩js
        minimize: true,
        minimizer: [new TerserPlugin()],
    },
//}
})


