<template>
  <div>
  <!--路由占位符-->
  <router-view />

  <!--Tabbar区域-->
  <van-tabbar route><!--v-model="active" 开启路由模式后自动高亮-->
    <van-tabbar-item icon="home-o" replace to="/">Home</van-tabbar-item>
    <van-tabbar-item icon="user-o" replace to="/user">Me</van-tabbar-item>
  </van-tabbar>

  </div>
</template>

<script>
export default {
  name:'App',
  data() {
      return {
        active: 0,
      };
    },
}
</script>

<style lang="less" scoped>

</style>