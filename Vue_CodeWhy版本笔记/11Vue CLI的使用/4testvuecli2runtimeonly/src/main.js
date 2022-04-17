import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

// console.log(App) 测试App是否还存在，对象不包含template，有render函数，
//打包后，所有的template都被vue-template-compiler编译成render函数

/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App)  //没有注册App,直接导入
})

//render -> vdom -> UI

/**
 * h => h(App)
 * 相当于function(h){
 *   return h(App)
 * }
 *
 * 测试App template是否存在，不需要template 解析为->语法树 ast
 */
