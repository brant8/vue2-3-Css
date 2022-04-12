<template>
  <div class="footer">

      <input type="checkbox" id="cdFull" :checked="isFull" @change="fullChange">
      <label for="cdFull">全选</label>
      <span>合计 <span style="color:red">{{ amount.toFixed(2) }} / {{ typeof(amount) }}</span> 元</span>
      <span><button type="submit">结算 ( {{all}} )</button></span>

  </div>
</template>

<script>
export default {
    props:{
        isFull:{
            type:Boolean,
            default:true
        },
        amount:{
            type:Number,
            default:0
        },
        all:{
            default:0,
            type:Number
        }
    },
    data(){
        return{

        }
    },
    methods:{
        fullChange(e){
            // console.log(e)
            // console.log(e.target)
            // console.log(e.target.checked)
            this.$emit('full-change',e.target.checked)
        }
    }

}
</script>

<style>
.footer{
    position: fixed;
    bottom: 0;
    background-color: #fff;
    width: 100%;
    height: 35px;
    line-height: 35px;
    /* z-index: 999; */
    text-align: center;
}
.footer span:nth-child(2){
    margin-left: 55px;
    margin-right: 55px;
}
</style>