<template>
  <div id="app">
    <h2>{{message}}</h2>
<div>--------------------counter------------------------------</div>
    <h3>{{counter}}</h3>
    <button @click="counter++">++</button>
    <button @click="counter--">--</button>
    <hello-world :counter="counter"></hello-world>
<div>--------------$store.state.counter2---------------------</div>
    <h3>{{$store.state.counter2}}</h3><!--$store来自./store-->
    <button @click="$store.state.counter2++">++</button> <!--不推荐直接使用state更改状态-->
    <button @click="$store.state.counter2--">--</button>
<div>--------------$store.state.counter3---------------------</div>
    <button @click="addition">++</button> <!--不推荐直接使用state更改状态-->
    <button @click="subtraction">--</button>
<div>--------------$store.getters---------------------</div>
    <p>{{$store.getters.powerCounter}}</p>
<div>--------------$store.mutations---------------------</div>
    <button @click="addCount(5)">+5</button> <!--不推荐直接使用state更改状态-->
    <button @click="addCount(10)">+10</button>
    <button @click="addStudent">添加学生</button>
    <p>{{$store.state.students}}</p>
<div>--------------$store.modules的内容---------------------</div>
    <p>{{$store.state.a.name}}</p><!--module会被放进state里-->
    <router-view/>
  </div>
</template>

<script>

import HelloWorld from "./components/HelloWorld";
export default {
  name: 'App',
  components: {HelloWorld},//导入子组件让其可以使用counter
  data(){
    return{
      message:"hello worl..>",
      counter:0,
    }
  },
  computed:{
    //students=[{id:1,age:12},{id:2,age:13}]
    // idBigger2(){
    //   return this.students.filter(s=>{ //高阶函数条件过滤并返回对象/数组
    //     return s.id>1
    //   })
    // }
  },
  methods:{
    addition(){
      this.$store.commit('increment') //使用vuex中的mutation中的increment方法进行操作，方便管理追踪
    },
    subtraction(){
      this.$store.commit('decrenebt')
    },
    addCount(count){ //传入单个参数到mutations取更改状态state
      //1.普通的提交封装
      this.$store.commit('incrementCount',count)

      //2.特殊的提交封装
      this.$store.commit({
        type:'incrementCount',
        count:count //ES6语法直接写count即可，如组件一样
        //此方法传入的count为对象形式
      })
    },
    addStudent() { //传入对象到mutations
      const stu = {id:4,age:31,name:"Ha"}
      this.$store.commit('addStudent',stu) //调用mutations方法用commit
    },
    updateInf(){
      //this.$store.commit('updateInfo')  //错误
      this.$store.dispatch('aUpdateInfo') //调用actions的方法用dispatch
      //this.$store.dispatch('aUpdateInfo','我是payload')  //传入字符串
      //this.$store.dispatch('aUpdateInfo',()=>{ .. }) 传入函数
    },
    updateInf2(){
      this.$store
        .dispatch('aUpdateInfo2','我是携带的信息')
        .then(res=>{
          console.log("里面完成提交")
          console.log(res)
        })
    }
  }
}
</script>

<style>

</style>
