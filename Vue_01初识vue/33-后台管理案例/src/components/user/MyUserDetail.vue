<template>
  <div class="userdetail">
      <button class="btn btn-light btn-sm" @click="backwards">后退</button>
      <h1>用户详情 -- {{ this.$route.params.id}}  -- {{id}}</h1>
  </div>
</template>

<script>
export default {
name: 'UserDetail',
props:['id'],
methods:{
    backwards(){
        this.$router.back()
    }
}
}
</script>

<style>

</style>