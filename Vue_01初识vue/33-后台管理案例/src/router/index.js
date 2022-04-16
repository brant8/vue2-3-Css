import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '@/components/MyHome.vue'
import Login from '@/components/MyLogin.vue'

import Users from '@/components/menus/MyUsers.vue'
import Rights from '@/components/menus/MyRights.vue'
import Goods from '@/components/menus/MyGoods.vue'
import Settings from '@/components/menus/MySettings.vue'
import Orders from '@/components/menus/MyOrders.vue'
import UserDetail from '@/components/user/MyUserDetail.vue'


Vue.use(VueRouter)

const routes = [
    { path: '/', redirect: '/login' },
    { path: '/login', name: 'Login', component: Login },
    {
        path: '/home',
        name: 'Home',
        redirect: '/home/users',
        component: Home,
        children: [
            { path: 'users', component: Users },
            { path: 'orders', component: Orders },
            { path: 'rights', component: Rights },
            { path: 'settings', component: Settings },
            { path: 'goods', component: Goods },
            //用户详情的路由规则
            { path: 'userinfo/:id', component: UserDetail, props: true },
        ]
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach(function(to, from, next) {
    if (to.path === '/home') {
        const token = localStorage.getItem('token')
        if (token) {
            next()
        } else {
            next('/login')
        }
    } else {
        next()
    }
})


export default router