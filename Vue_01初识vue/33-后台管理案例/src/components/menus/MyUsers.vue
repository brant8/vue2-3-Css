<template>
  <div class="users">
      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">姓名</th>
            <th scope="col">年龄</th>
            <th scope="col">头衔</th>
            <th scope="col">操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in userlist" :key="item.id">
            <th>{{ item.id }}</th>
            <td>{{ item.name }}</td>
            <td>{{ item.age }}</td>
            <td>{{ item.position }}</td>
            <td>
              <a href="#" @click.prevent="gotoDetails(item.id)">详情</a>
            </td>
          </tr>
        </tbody>
      </table>
  </div>
</template>

<script>
export default {
  name:'MyUsers',
  data(){
    return{
      userlist:[
        {id: 1, name:"lisi", age:30, position:"manager"},
        {id: 2, name:"wangwu", age:32, position:"employee"},
        {id: 3, name:"zhaoliu", age:23, position:"part time"},
      ]
    }
  },
  methods:{
    gotoDetails(id){
      this.$router.push('/home/userinfo/'+id)
    }
  }
}
</script>

<style>

</style>