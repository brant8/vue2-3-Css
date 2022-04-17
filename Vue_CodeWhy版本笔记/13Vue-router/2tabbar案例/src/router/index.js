import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

const Home = ()=> import('../views/home/Home')
const Category = ()=> import('../views/category/Category')
const Profile = ()=> import('../views/profile/Profile')
const Cart = ()=> import('../views/cart/Cart')

Vue.use(Router)

const routes = [
  {
    path:'/',
    component:Home
  },
  {
    path:'/home',
    component: Home
  },
  {
    path:'/category',
    component: Category
  },
  {
    path:'/cart',
    component: Cart
  },
  {
    path:'/profile',
    component: Profile
  },
]


const router = new Router({
  routes,
  mode:"history" //从hash模式切换到hisory去除井号链接
})

export default router
