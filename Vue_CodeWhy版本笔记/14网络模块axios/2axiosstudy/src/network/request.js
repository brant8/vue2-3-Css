import axios from 'axios'
//对axios进行封装

//export default只导出一个函数
export function request(config,success,failure){ //从前端返回success和failure来判断返回值
  //1.创建axios的实例
  const instance = axios.create({
    baseURL: 'http://123.207.32.32:8000',
    timeout:5000,
  })
  //发送真正的网络请求
  instance(config)
    .then(res=>{
      console.log(res)
      success(res) //前端的回调函数返回res到success
    })
    .catch(err=>{
      console.log(err)
      failure(err)
    })

}

//推荐方法 返回Promise，届时axios失效也可以用其他方法传递封装成Promise
export function instance2(){
  return new Promise((resolve,reject)=>{
    //1.创建axios的实例
    const instance = axios.create({
      baseURL: 'http://123.207.32.32:8000',
      timeout:5000,
    })
    //2.axios拦截器
    //A。axios.interceptors  //拦截全局的axios
    instance.interceptors.request.use(config=>{//拦截成功
      console.log(config)
      //一。比如config中的一些信息不符合服务器的要求
      //二。比如每次发送网络请求时，都希望再界面中显示一个请求的图标（比如loading...转圈）
      //显示请求图标show然后再隐藏
      //三。某些网络请求（比如登录token），必须携带一些特殊的信息

      return config //B。拦截成功后config后再还回去
    },err=>{//C。没有发送成功的情况下
      console.log(err)
    });
    instance.interceptors.response.use(result=>{
        console.log((result))
        return result.data //axios返回的有很多其他如header的信息，一般情况只需要返回data即可
    },error => {
        console.log(error)
    });

    //3.发送真正的网络请求
    instance(config)
      .then(res=>{
        console.log(res)
        success(res) //前端的回调函数返回res到success
      })
      .catch(err=>{
        console.log(err)
        failure(err)
      })
  })
}



