<template>
  <div id="app">
<!--    修改路径方式-->
    <router-link to="/home">首页</router-link>
    <router-link to="/about">关于</router-link>
<!--    <router-link to="/user/123">用户默认</router-link>-->
    <router-link v-bind:to="'/user/'+userid">用户拼接1</router-link> <!--user为真实字符串-->
    <router-link v-bind:to="`/user/${userid}`">用户拼接2</router-link>
    <router-link to="/home" tag="button">首页</router-link>
    <router-link to="/about" tag="button">关于</router-link>
    <router-link to="/home" tag="button" replace>首页</router-link> <!--使用replaceState不能前进后退-->
    <router-link to="/about" tag="button" replace>关于</router-link>
    <router-link to="/home" tag="button" replace active-class="active">首页</router-link> <!--使用replaceState不能前进后退-->
    <router-link to="/about" tag="button" replace active-class="active">关于</router-link>
<!--    另外一种修改方法统一多个修改：在index.js里添加activeClass可以全部替换router-link-active为active-->
    <button @click="homeClick">首页</button>
    <button @click="aboutClick">关于</button>

    <router-link to="/profile">档案</router-link>
    <!--对象形式传入参数：v-bind:to="{对象}"-->
    <router-link v-bind:to="{path:'/profile',query:{name:'whye',age:18}}">档案</router-link>
    <button @click="userClick">用户</button>
    <button @click="profileClick">档案</button>

    <img src="./assets/logo.png">

    <keep-alive exclude="Profile"><!-- 保留当前路由中的操作记录到缓存 & 排除不被缓存的，如多个逗号间隔，不需要加空格 -->
      <router-view/><!-- 修改路径后的展示内容 -->
    </keep-alive>
  </div>
</template>

<script>
export default {
  name: 'App',
  data(){
    return{
      //$router:'',
      userid:'lisi',
    }
  },
  methods:{
    homeClick(){
      //通过代码方式修改路由 vue-router
      //console.log("home")
      //this为当前组件，每个组件都有$router, $router来自index.js的大路由对象
      this.$router.push('/home')
      //this.$router.replace('/home')
    },
    aboutClick(){
      //console.log("about")
      this.$router.push('/about')
    },
    userClick(){
      this.$router.push('/user/'+ this.userid)
    },
    profileClick(){
      this.$router.push({
        path:'/profile/',
        query:{
          name:'meili',
           age:20
        }
      })
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.router-link-active{
  color:red;
}
.active{
  color: red;
}
</style>
