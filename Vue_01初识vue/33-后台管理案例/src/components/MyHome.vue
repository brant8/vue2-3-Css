<template>
  <div class="home">
      <my-header></my-header>
      
      <div class="content">
        <my-aside></my-aside>
        <div class="bodycontent">
            123
            <router-view></router-view>
        </div>
       </div> 
      
  </div>
</template>

<script>
import MyHeader from './subcomponents/MyHeader.vue'
import MyAside from './subcomponents/MyAside.vue'

export default {
    name:'MyHome',
    components:{
        MyHeader,
        MyAside,
    }
}
</script>

<style>
.content{
    width: 100%;
}
.bodycontent{
    float: left;
    width: 80%;
}

</style>