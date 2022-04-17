function add( num1, num2){
    return num1+num2
}

function mul(num1, num2){
    return num1 * num2
}
//commonJS导出方式，需要webpack打包解析。正常情况mainUtils.js与main.js在index导入需要先后顺序
module.exports = {
    add,
    mul
}