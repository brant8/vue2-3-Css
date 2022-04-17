//入口js不放在js目录下
const {add,mul} = require('./js/mainUtils.js');

console.log(add(20,30));
console.log(mul(20,30));

import {name,age,height} from "./js/info";

console.log(name)
console.log(age)
console.log(height)

//依赖css文件，需要loader才能处理css文件
require('./css/normal.css')

//1.nom安装Vue模块，node_module里面有export default Vue所以可以用'vue'
import Vue from 'vue'
//2.此处相当于Vue实例template
//3.const app= new Vue({  //实际开发中不用const app,只方便在console使用app.xxx测试


//9.分离后不用const App，用导入方式
//import App from './vue/app.js';
// 11.注释掉原js部分，运行npm run build会报错，缺少vue-loader未配置和vue-template-compile编译配置
//具体命令：npm install --save-dev vue-loader vue-template-
//12.配置config

//10.把app.js的内容复制到App.vue，导入App.vue
import App from './vue/App.vue';

//6.组件抽取new Vue{}得到的内容
// const App={     //8.创建新文件夹vue把{}内容剪切到app.js分离
//     template:`
//       <div>
//         <h3>{{ message }}</h3>
//       </div>
//     `,
//     data(){ //组件的data表述得用返回
//         return {
//             message:"hello",
//         }
//     }
// }

new Vue({
    el:'#app',
    data:{}, //5.data抽取到const App中
    methods:{},
    template:`<App/>`, //4.template内容抽取到const App，只有一个元素时不需要根<div>
    components:{
        App //7.把const App组件注册到此
    }
});

