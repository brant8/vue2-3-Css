// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  components: { App }, //先注册组件App然后在template里进行使用
  template: '<App/>'
})
/**
 * template 解析为->语法树 ast -> 编译 render -> virtual dom -> 真实dom -> UI
 */

/**
 *   components: { App },
 *   template: '<App/>'
 *   相当于render
 *   render: function(createElement){
 *     return createElement
 *   }
 *   说明语法：
 *   1.普通用法：createElement("标签"（比如"h2"），对象(比如：{class: 'box'})，内容(比如：['hello world‘]))
 *   会输出
 *   <h2 class="box">hello world</h2>
 *   还可以嵌套：createElement("h2"，{class: 'box'}，['hello world‘， createElement('button',['按钮'])])
 *   2.传入组件对象
 *   createElement(cpn)，不需要再在component注册cpn，直接传入到render
 */
