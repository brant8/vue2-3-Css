<template>
<div>
  <h2>首页</h2>
  <p>首页内容...</p>
  <router-link to="/home/news">新闻</router-link>
  <router-link to="/home/message">消息</router-link>
  <router-view></router-view><!--占位：给message和news使用-->
</div>
</template>

<script>
export default {
  name: "Home",
  data(){
    return {
      message:'hello message...',
      path:'/home/news',//保留当前操作的子页面 与下方beforeLeaveRoute搭配，记录离开时的路径
    }
  },
  //声明周期函数
  created(){ //创建组件的时候回调
    console.log("Home created...")
    document.title = '首页1' //更改标题，使用全局导航守卫动态更改每个页面标题，在index.js。此处静态更改。
  },
  mounted(){ //当所有的template挂载在dom完以后执行的函数
    console.log("Home  mounted...")
  },
  updated() {//当页面有进行刷新的时候
    console.log("Home updated...")
  },
  destroyed() {
    console.log("Home destroyed...")
  },
  activated() {//活跃的时候,必须与keep-alive搭配才有用
    console.log("Home activated...")
    this.$router.push(this.path) //记录活跃时候的操作
  },
  deactivated() {//必须与keep-alive搭配才有用
    console.log("Home deactivated...")
  },
  beforeRouteLeave(to,from,next){
    console.log("home beforeRouteLeave...") //记录上一次离开时候的状态，可以与activated配合
    this.path = this.$route.path
    next()
  }
}
</script>

<style scoped>

</style>
