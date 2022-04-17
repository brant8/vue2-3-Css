<template>
  <div>
    <h3 class="title">{{ message }}</h3>
  </div>
</template>

<script>
export default {
  name: "App",
  data(){ //组件的data表述得用返回
    return {
      message:"hello",
    }
  }
}
</script>

<style scoped>
/*样式*/
.title{
  color: darkgreen;
}
</style>