<template>
<div>
  <h2>我是profile</h2>
  <p>{{ $route.query.name}}</p>
  <p>{{ $route.query.age}}</p>
  <p>$route来自index.js的routes[]中的当前的活跃(active)路由</p>
</div>
</template>

<script>
export default {
  name: "Profile"
}
</script>

<style scoped>

</style>
