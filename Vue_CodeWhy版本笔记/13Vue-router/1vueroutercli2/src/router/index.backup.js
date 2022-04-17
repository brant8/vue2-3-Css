import Vue from 'vue'
import Router from 'vue-router'         //导入路径，使用路由
import HelloWorld from '@/components/HelloWorld'
import Home from '../components/Home'
import About from '../components/About'
import User from '../components/User'

//1.通过Vue.use（插件），安装插件
//Vue.use(Router)

//2.创建VueRouter对象,传入$router到App.vue
// const router = new VueRouter({
//   //配置路由和组件之间的应用关系
//   routes:[
//    { }，
//    { }，
//   ]，
//   mode:'history'
// })

//B. 懒加载方式二
//const Home = ()=>import('../components/Home')
const HomeNews = ()=>import('../components/HomeNews')
const HomeMessage = ()=>import('../components/HomeMessage')
const Profile = ()=>import('../components/Profile')

// 3.将router对象传入到Vue实例
// export default router

//4.在main.js里面添加router路由使用（需添加router路径再在实例中使用路由）
//-----------------------------------
//原官方自带写法：
Vue.use(Router)

export default new Router({
  routes: [
    { //默认首页方法一：
      path:'',//默认值
      //component:Home
      //redirect重定向：
      redirect: '/home',
    },
    // { //默认首页方法二：
    //   path:'/',
    //   component: Home
    // },
    {
      path: '/home',
      name: 'HelloWorld',
      component: Home,
      children:[ //子组件嵌套
        {
          path:'',
          redirect:'news'
        },
        {
          path:'news',
          component: HomeNews //懒加载方式，在上方定义了const
        },
        {
          path:'message',
          component: HomeMessage
        },
      ],
    },
    {
      path:'/about',
      component:About,
    },
    // A. 懒加载方式一：不在顶部导入路径了
    // {
    //   path:'/about',
    //   component: ()=>import('../components/About')    //当点击或者需要About的时候才会加载，而不是到主页一次性加载，在生成文件会多出一个文件
    // },
    {
      path:'/user/:userid', //拼接用户id,注意User.vue的参数return.xx.userid要一致
      component: User,
    },
    {
      path:"/profile",
      component: Profile
    },
  ],
  mode:'history', //使用HTMl5的history去除#号url方式
  linkActiveClass:'active',

})
