<template>
  <div class="home-container" >
      <van-nav-bar  title="黑马头条" :fixed=true />
        <!-- {{list.length}} -->

    <ArticleInfo v-for="item in list" :key="item.id" :title="item.title" :author="item.aut_name" :cmtCount="item.comm_count">

    </ArticleInfo>

  </div>
</template>

<script>
import request from '@/utils/request.js'
import ArticleInfo from '@/components/Article/ArticleInfo.vue'

export default {
name:"Home",
data(){
    return{
        page:1,
        limit:10,
        list:[]
    }
},
components:{
    ArticleInfo
},
created(){
    this.initArticleList()
},
methods:{
    //封装获取文章列表数据的方法
     async initArticleList(){
         const {data : res} = await request.get('/articles',{
            params:{
                //请求参数
                _page:this.page,
                _limit:this.limit
            }
        })
        // console.log(res)
        this.list = res
    }
}
}
</script>

<style lang="less" scoped>
.home-container{
    padding: 46px 0 50px 0;
}
.van-nav-bar{
    background-color: #007bf0;
    color: #fff;
}
/deep/ .van-nav-bar__title{
    color:#fff;
}
</style>