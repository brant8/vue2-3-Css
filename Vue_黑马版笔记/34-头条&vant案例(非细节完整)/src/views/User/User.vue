<template>
  <div>
    <h1>User组件</h1>
    <van-image round width="8rem" height="8rem" src="https://img01.yzcdn.cn/vant/cat.jpeg"/>

    <van-cell-group>
      <van-cell title="单元格" value="内容" />
      <van-cell title="单元格" value="内容" label="描述信息" />
    </van-cell-group>
  </div>
</template>

<script>
// import axios from '@/utils/request.js'
import {getArticleListAPI} from '@/api/articleAPI.js'


// getArticleListAPI()

export default {
    name:'User',
    data(){
        return{
            page:1,
            limit:5
        }
    },
    methods: {
        async initArticleList(){
            const {data:res} = await getArticleListAPI(this.page, this.limit)
            console.log(res)
    },
},
    created(){
        this.initArticleList()
    }
}
</script>

<style>

</style>