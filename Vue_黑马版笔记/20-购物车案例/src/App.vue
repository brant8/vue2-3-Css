<template>
  <div class="parent"> 
    <Header :title="Title"></Header>
   <div class="app-container">
     <!-- {{ fullState }} -->
     <!-- <p> {{ amt }} 个</p> -->
     <Goods v-for="item in list" :key="item.id" :title="item.goods_name"
      :pic="item.goods_img" :price="item.goods_price" 
      :state="item.goods_state" :id="item.id" 
      @state-change="newStateChange"
      :count="item.goods_count"></Goods>
     <!-- <Goods></Goods> -->
   </div>

   <Footer :isFull="fullState" @full-change="getFullState" 
   :all="totalItm" :amount="amt"></Footer>
  </div>
</template>

<script>
//导入axios请求库
import axios from 'axios'
import Header from '@/components/Header/Header.vue'
import Goods from '@/components/Goods/Goods.vue'
import Footer from '@/components/Footer/Footer.vue'
import bus from '@/components/eventBus.js'


export default {
  data(){
    return{
      Title:"购物车案例HA",
      list:[],
    }
  },
  components:{
    Header,
    Goods,
    Footer,
    
  },
  methods: {
    //封装请求列表的数据
    async initCartList(){
      //调用axios的get方法，请求数据列表
      const {data:res}= await axios.get("https://www.escook.cn/api/cart")
      // const tete= await axios.get("https://www.escook.cn/api/cart")
      // console.log(res)
      if(res.status === 200){
        this.list = res.list
      }
    },
    //接收勾选状态更新
    newStateChange(val){
      // console.log("收到消息~")
      // console.log(val)
      this.list.some(item=>{
        if(item.id === val.id){
          item.goods_state = val.value
          return true
        }
      })
    },
    getFullState(val){//接收Footer传递过来的全选按钮的状态
      // console.log(val)
      //循环每一项
      this.list.forEach(element => {
        return element.goods_state = val
      });
    }
  },
  created(){
    // 调用请求数据的方法
    console.log(this)
    console.log(this.initCartList())

    bus.$on('share',(val)=>{
      // console.log("组件接收到了counter的值")
      this.list.some(item=>{
        if(item.id === val.id){
          item.goods_count = val.value
          return true
        }
      })
    })
  },
  computed:{
    fullState(){//计算list全选勾选框状态
      return this.list.every(item=>item.goods_state === true)
    },
    amt(){
      //1.先过滤勾选的项目
      return this.list
      .filter(item=>item.goods_state === true)
      .reduce((total,item)=>{
        return total += item.goods_price * item.goods_count
      },0)
    },
    totalItm(){
      return this.list.filter(item=>item.goods_state)
      .reduce((t,item)=>{
        return t+=item.goods_count
      },0)
    }
  }

}
</script>

<style lang="less" scoped>
html,body{
  // height: 100%; 
  margin: 0;
  padding: 0;
}
.app-container{
  // padding-top: 45px;
  // padding-bottom: 50px;
}
.parent{
  width: 100%;
  position: relative;
}
</style>
