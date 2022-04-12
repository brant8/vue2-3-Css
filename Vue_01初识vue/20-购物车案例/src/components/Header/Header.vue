<template>
  <div class="header">
      {{ title }}
  </div>
</template>

<script>
export default {
    props:{
        //声明title自定义属性，允许使用者自定义标题
        title:{
            default: '',
            type: String
        }
    }
}
</script>

<style>
.header{
    margin:0;
    padding: 0;
    height: 45px;
    line-height: 45px;
    background-color: skyblue;
    text-align: center;
    color:#fff
}
</style>