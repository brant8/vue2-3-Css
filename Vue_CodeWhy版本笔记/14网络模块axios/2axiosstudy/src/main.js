import Vue from 'vue'
import App from './App'
import router from './router'
//1.导入axios
import axios from 'axios'

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})

//2.基本使用
axios({
  //url:'httpbin.org'
  url:'http://123.207.32.32:8000/home/multidata',
  //method:'post',//默认使用get
  params:{ //专门针对get请求的参数拼接
    type:'pop',
    page:1
  }
}).then(res=>{ //支持返回promise
  console.log(res)
})


//3.axios发送并发请求
//axios.all( [axios(),axios()] ).then( results=>{} )
axios.all([
  axios({
  url:'http://123.207.32.32:8000/home/multidata',
}),axios({
  url:'http://123.207.32.32:8000/home/data',
  params:{
    type:'sell',
    page:4
  }
})]).then(results=>{
  console.log(results)
})

//4.抽取固定参数,抽取完后即可省略输入
axios.defaults.baseURL = 'http://123.207.32.32:8000'
axios.defaults.timeout = 5000

axios.all([
  axios({
    url:'http://123.207.32.32:8000/home/multidata',
  }),
  axios({
    baseURL:'http://123.207.32.32:8000',
    url:'/home/data',
    timeout:5, //5毫秒钟
    params:{
      type:'sell',
      page:4
    }
  })]).then(axios.spread((res1,res2)=>{ //分割输出结果
    console.log(res1)
    console.log(res2)
}))

//5.创建对应的实例,用于部署在不同服务器的ip地址
const instance1 = axios.create({
  baseURL:'http://11.11.11.11',
  timout:5000
})
const instance2 = axios.create({
  baseURL:'http://22.2.22.22',
  timout:5000
})
//通过实例发送请求
instance1({
  url:'/home/multidata',
}).then(res=>{
  console.log(res)
})
instance1({
  url:'/home/data',
  prarams:{
    type:'pop',
    page:1
  }
})


//6.封装request模块
import {request} from './network/request'

request({
  url:'/home/multidata' //第一个传入的参数config
},res=>{ //res为传入的第二个参数
  console.log(res) //传入到request的success并在此处回调函数输出内容
},err=>{ //err为传入的第三个参数
  console.log(err)
})
