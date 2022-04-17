import Vue from 'vue'
import VueRouter from 'vue-router'         //导入路径，使用路由
//import HelloWorld from '@/components/HelloWorld'
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
Vue.use(VueRouter)


const router = new VueRouter({
//export default new Router({
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
      meta:{
        title: '首页2' //配合router.beforeEach()使用更换每个页面的标题
      },
      children:[ //子组件嵌套
        {
          path:'',
          //redirect:'news', //redirect会与keep-alive部分功能冲突
          component: HomeNews, //redirect会与keep-alive部分功能冲突
        },
        {
          path:'news',
          component: HomeNews, //懒加载方式，在上方定义了const
          meta:{
            title: '新闻' //配合router.beforeEach()使用更换每个页面的标题
          },
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
      component: Profile,
      meta:{
        title: '档案index' //配合router.beforeEach()使用更换每个页面的标题
      },
    },
  ],
  mode:'history', //使用HTMl5的history去除#号url方式
  linkActiveClass:'active',
})

//前置守卫
// router.beforeEach(Function (to, from, next){..})
router.beforeEach( (to,from,next)=>{
  //从from路由跳转到to路由
  //配合route[]的meta更改网页标题
  //document.title = to.meta.title  //只用meta的时候对于有次级children的时候会有错误，title在matched数组里保证有数据
  document.title = to.matched[0].meta.title //title在matched和同级meta上均有title
  // console.log("index.js beforeEach...")
  // console.log(to)
  // console.log(from)
  next()//必须调用next（），否则无法跳转页面
})

//后置钩子
router.afterEach((to,from)=>{
  // console.log("index.js afterEach...")
  // console.log(to)
  // console.log(from)
})

export default router
