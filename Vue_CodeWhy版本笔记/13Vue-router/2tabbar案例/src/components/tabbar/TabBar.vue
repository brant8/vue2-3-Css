<template>
    <div id="tab-bar"> <!--tab-bar只管理tabbar，其item可单独分离出去-->
      <slot></slot>
    </div>
</template>

<script>
export default {
  name: 'TabBar'
}
</script>

<style>
  #tab-bar{
    display: flex; /* 内容水平分布*/
    background-color: #dcd7d7; /*设置背景颜色*/
    /*固定菜单栏在底部*/
    position:fixed;
    left:0;
    right: 0;
    bottom:0;
    /*在菜单栏上方做阴影与body白色背景做缓冲*/
    box-shadow: 0px -3px 1px rgba(100,100,100,.3);/*参数： x轴(正值阴影向右走) y轴(正值向下走) 模糊度 颜色(rbga可额外设置透明度)*/
    /* rgba（x,x,x,透明度）*/
  }

</style>
