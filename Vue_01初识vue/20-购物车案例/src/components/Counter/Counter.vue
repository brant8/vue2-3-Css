<template>
  <div class="counter">
      <button type="button" class="btn btn-light btn-sm" @click="sub">-</button>
        {{ c }}
      <button type="button" class="btn btn-light btn-sm" @click="add">+</button>
  </div>    
</template>

<script>
import bus from '@/components/eventBus.js'

export default {
    props:{
        c:{ //接收Goods商品数量
            default:1,
            type:Number
        },
        id:{ //接收Goods商品ID
            default:'',
            type:Number
        }
    },
    methods: {
        add(){
            // bus.$emit('xxx',{id,this.c+1})
            const obj = { id:this.id, value:this.c+1 } //此处并无修改c的原值
            // console.log(obj)
            bus.$emit('share',obj)
        },
        sub(){
            bus.$emit('share',{id:this.id, value:this.c-1})
        }
    },

}
</script>

<style>
.counter{
    display: inline;
    margin-left: 20px;
}
</style>