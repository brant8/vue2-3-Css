<template>
  <div class="item">

      <input type="checkbox" :id="'cb'+id" :checked="state" @change="stateChange">
      <label :for="'cb'+id">
        <div class="image">
            <img class="image" :src="pic" alt="" />
        </div>
      </label>  
        <div class="describ"> 
            <p class="smTitle">{{ title }}</p>
            <p class="price">
                <span> {{ price }}$  </span>
                <span> <Counter :c="count" :id="id"></Counter> </span>
            </p>
        </div> 
      

  </div>
</template>

<script>
import Counter from '@/components/Counter/Counter.vue'

export default {
    components:{
        Counter
    },
    props:{
        //将来子组件商品勾选状态之后，通过子传父形式通知父组件
        id:{
            required:true,
            type:Number
        },
        // key:{
        //     default:'',
        //     type:Number
        // },
        title:{ //要渲染的标题
            default:'',
            type:String
        },
        pic:{//要渲染商品的图片
            default:'',
            type:String
        },
        price:{
            default:0,
            type: Number
        },
        state:{
            default:true,
            type:Boolean
        },
        count:{
            default:1,
            type:Number
        }
    },
    data(){
        return {
            items:[],
        }
    },
    methods:{
        stateChange(e){
            // console.log("eeee")
            // console.log(this.$emit)
            // console.log(e)
            const newState = e.target.checked
            // console.log(newState)
            // console.log(this)
            this.$emit('state-change',{id:this.id, value:newState})
        }
    },

}
</script>

<style>
.item{
    display: flex;
}
.item input{
    flex:1;
}
.item label{
    flex:4;
    width: 45px;
    height: 80px;
}
.item .image img{
    width: 100%;
}
.item .describ{
    flex:10;
    /* display: column; */
}
.price{
    color: red;
}

</style>