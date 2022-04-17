<template>
<div>
  <h2>我是用户界面</h2>
  <p>{{userId}}</p>
  <p>{{$route.params.userid}}</p> <!--此处不用this相当于data的message在HTML标签不用this一样-->
  <p>我是嘿嘿嘿</p>
  <button @click="btnClick">按钮查看$router</button>
</div>
</template>

<script>
export default {
  name: "User",
  computed:{
    userId(){
      return this.$route.params.userid  //来自index.js的routes[]中的当前的活跃(active)路由
    }
  },
  methods:{
    btnClick(){
      console.log(this.$router);
    }
  }
}
</script>

<style scoped>

</style>
