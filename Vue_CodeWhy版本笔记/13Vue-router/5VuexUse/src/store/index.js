import Vue from 'vue';
import Vuex from 'vuex'

//1.安装插件
Vue.use(Vuex)

/**Vuex状态管理操作流程
 * Actions     (Commit)->     Mutations    --- devTools
    ^                             |(Mutate)
    |(Dispatch)                   v
VueComponent    <-(Render)      State
 */
//2.创建对象
const store = new Vuex.Store({//真正创建的是Store
  state:{
    counter2:1000, //共享的状态,让App和Helloworld都可以用到counter2
    counter3:1000,
    students:[
      {id:1,age:20,name:"AB"},
      {id:2,age:15,name:"CD"},
      {id:3,age:25,name:"EF"}, // Dep->[Watcher]观察者模式
      //state对象响应规则：页面即时更新的为页面提前定好的属性，如果在students添加gender:”男“，添加的新属性不会响应在页面
    ],
    infos:{id:1,age:20,name:"AB"},
  },
  //mutations是给同步操作使用
  mutations:{//官方管理图推荐通过mutations更改state状态可以让Devtools插件跟踪state更改的状态
    increment(state){ //默认有一个参数state
      state.counter3++
    },
    decrement(state){
      state.counter3--
    },
    //手动改要加的数值，与App的methods搭配
    incrementCount(state,payload){ //从前端App的methods拿到的参数数值count
      //1.前端使用普通方法传入参数时，payload即count为数值/数字
      state.counter3+= payload
      //2.前端使用特殊方法提交时，传入的payload为对象，需从对象里拿参数
      //state.counter3+= payload.count
    },
    /**追加学生push到数组,参数被成为mutation的载荷（payload）
    */
    addStudent(state,stu){
      state.students.push(stu)
    },
    updateInfo(state){ //添加infos的属性并且让其在页面刷新展示
      state.info.name = 'liumang'//只该值会响应在页面变化
      state.info['email'] = 'pari@163.com' //此方法添加属性不会响应在页面刷新显示
      Vue.set(state.infos,'address','victoria park') //此方法把新加的属性加入到响应式可以显示在页面
      delete state.info.age //删掉属性，但是页面不会响应在页面
      Vue.delete(state.info,'age') //删掉属性，页面会响应式展示刷新后的内容
    }
  },
  actions:{ //异步操作：通过网络请求时通过actions修改mutations。无异步操作时，Vue Components可以直接操作mutations。
    aUpdateInfo(context){//默认参数不是state了，变成context
  //aUpdateInfo(context, payload){传递参数方法
      setTimeout(()=>{
        //context.state.info.name = 'codewhy' //错误方法：修改state途径一定要通过mutations才合规
        context.commit('updateInfo') //正确方法：从mutations中拿方法修改state
  //console.log(payload)
  //console.log(payload()) //如果传入的是个函数
      },1000)
    },
    aUpdateInfo2(context,payload){
      return new Promise((resolve, reject)=>{
        setTimeout(()=>{
          context.commit('updateInfo')
          console.log((payload))
          resolve()
        },1000)
      })
    }
  },
  getters:{
    powerCounter(state){ //有个默认参数state
      return state.counter3 * state.counter3
    },
    doubleResult(state,getters){
      return getters.powerCounter //使用getters返回数字
    },
    /**需求：动态过滤学生年龄大于某个值
    /*要点：1.filter高级函数条件过滤数组/对象
    *     2.返回值为函数即可传入参数
    */
    moreAgeStu(state){  //返回函数，相当于return $store.getters.moreAgeStu返回的是一个函数可以moreAgeStu()
      return function(age){ //有了moreAgeStu后，即可以在函数里传递参数moreAgeStu(x)，即此处的age
        //return state.students.filter(s => s.age > age)
      }
    }
  },
  modules:{
    a:{
      state:{ cell:12345},
      mutations:{},
    },
    b:{
      state:{},
      mutations:{},
    }
  }
});

/**
 * 通过索引值修改数组中的元素
 * this.letters[0] = 'bbbb'
 * this.letters.splice(0,1,'bbbb')
 * set(要修改的对象，索引值，修改后的值)
 * Vue.set(this.letters,0,'bbbb')
 */

//3.导出store独享
export default store
