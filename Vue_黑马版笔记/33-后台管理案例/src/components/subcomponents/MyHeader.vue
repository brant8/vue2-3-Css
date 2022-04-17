<template>

  <div class="header">
      <div class="desc">后台管理系统</div>
      <div class="logout"><button class="btn btn-primary" @click="logout">退出登录</button></div>  
  </div>

</template>

<script>
export default {
    name:'MyHeader',
    methods: {
        logout(){
            localStorage.removeItem("token")
            this.$router.push('/login')
        }
    },
}
</script>

<style>
.header{
    width: 100%;
    height: 50px;
    line-height: 50px;
    background-color: #fff;
}
.header .desc{
    float: left;
    width: 60%;
    font-size: 40px;
}
.header .logout{
    float: left;
    width: 40%;
}
</style>