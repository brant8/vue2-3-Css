<template>
  <div class="tab-bar-item" @click="itemClick">
    <div  v-if="!isActive">
      <slot name="item-icon"></slot>
    </div>
    <slot v-else name="item-icon-active"></slot>
<!--    <div v-bind:class="{active:isActive}" >--> <!--更改颜色方法一：写成固定，在源代码更改-->
    <div v-bind:style="activeStyle" >              <!--更改颜色方法二：封装式写法，在前端App.vue可以更改其他颜色-->
      <slot name="item-text"></slot>
    </div>
<!--    所有的item都展示同一个图片内容，插条预留-->
<!--    <img src="../../assets/img/tabbar/home.svg" alt=""></img> &lt;!&ndash;img行内块，后面文字同一行，需要层级&ndash;&gt;-->
<!--    <div>首页</div>-->
  </div>
</template>

<script>
export default {
  name: "TabBarItem",
  props:{ //定义接收的 props 等待父组件传递
    path:String, //子组件传递参数props
    activeColor:{ //活跃时的字体颜色
      type: String, //预留可以在App.vue写自定义颜色
      default:'red'
    }
  },
  data(){
    return{
      //isActive:true,
      path:'/home'
    }
  },
  computed:{
    isActive(){//判断当前路径是否活跃
      //其他方法： this.$route.path == this.path
      return this.$route.path.indexOf(this.path) !== -1//哪个路由活跃就是哪个
      //当前活跃路径 /home -> 与item1的(/home)一致则 = true
      // /home -> item1(/category) = false
      // /home -> item1(/cart) = false
      // /home -> item1(/profile) = false
    },
    activeStyle(){ //动态显示当前活跃的字体颜色
      return this.isActive ? {color: this.activeColor} : {}
    }
  },
  methods:{
    itemClick(){
      this.$router.replace(this.path) //.catch(err=>err)
    }
  }
}
</script>

<style scoped>
.tab-bar-item{
  flex: 1;/*分布均等分1份*/
  text-align:center; /*文字放置均等份的中间*/
  height: 49px; /*改div高度*/
  font-size: 14px; /*调整文字大小*/
}
.tab-bar-item img{ /*图标大小*/
  width: 24px;
  height:24px;
  margin-top: 3px; /*调整图片距离顶部距离*/
  vertical-align: middle; /*图片底部距离下一个div文字有3px默认距离，middle平衡掉*/
}

.active{
  color: red;
}
</style>



