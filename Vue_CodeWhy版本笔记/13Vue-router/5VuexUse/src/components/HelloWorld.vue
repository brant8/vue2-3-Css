<template>
  <div class="hello">
    <h1>此处时Hello。。{{ counter }}</h1>
  </div>
</template>

<script>
export default {
  name: 'HelloWorld',
  props:{ //父参数传子组件
    counter:Number,//预定义该元素的属性
  },
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
