<template>
  <div>
    <van-cell-group>
        <van-cell :title="title" value="内容" />
        <van-cell :title="author" value="内容" :label="cmtCount" />
        <van-image  round  width="10rem"  height="10rem" src="https://img01.yzcdn.cn/vant/cat.jpeg"/>
    </van-cell-group>
    <hr>
  </div>
</template>

<script>
export default {
name:'ArticleInfo',
props:{
    title:{
        type:String,
        default:''
    },
    author:{
        type:String,
        default:''
    },
    cmtCount:{
        type:[Number,String],
        default:0
    }
}
}
</script>

<style lang="less" scoped>

</style>
