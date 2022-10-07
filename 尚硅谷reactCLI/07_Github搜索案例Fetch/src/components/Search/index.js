import React, {Component} from 'react';
import axios from 'axios';
import PubSub from "pubsub-js";

class Index extends Component {
    search= /*async*/ ()=>{
        const {keyWordElement:{value}} = this; //解构赋值的解构赋值；解构赋值连续写法
        console.log('@@',value)
        //发送请求前通知List更新状态
        // this.props.updateAppState({isFirst:false,isLoading:true})
        PubSub.publish('atguigu',{isFirst:false,isLoading:true})
        //#region 发送网络请求
        axios.get(`http://localhost:3000/api1/search/users2?q=${value}`).then(
        //axios.get(`/api1/search/users?q=${value}`)...   当在localhost:3000页面下，给localhost:3000发送请求，可以省略地址
            response =>{
                console.log('成功了',response.data)
                // this.props.updateAppState({isLoading:false,users:response.data.items})
                PubSub.publish('atguigu',{isLoading:false,users:response.data.items})
            },
            error=>{
                console.log('失败了',error)
                // this.props.updateAppState({isLoading:false,err:'数据获取失败:'+error.message}) //不能存错误对象error，只能使用error.message
                PubSub.publish('atguigu',{isLoading:false,err:'数据获取失败:'+error.message})
            }
        )
        //#endregion
        //发送网络请求 -- fetch发送
        fetch(`/api1/search/users2?q=${value}`).then(
            response => {
                console.log('联系成功',);
                return response.json(); },
            error => {
                console.log('联系失败',error)
                return new Promise(()=>{}) //浏览器离线后不会有后门
            }
        ).then(
            response => {
                console.log('数据成功',response)},
            error => {
                console.log('联系失败',error)}
        )
        // const response = await fetch(`/api1/search/users2?q=${value}`);
        // const data =  await response.json();
    }
    render() {
        return (
            <section className="jumbotron">
                <h3 className="jumbotron-heading">搜索 Github 用户</h3>
                <div>
                    <input ref={ c=>this.keyWordElement = c} type="text" placeholder="输入关键词点击搜索"/>&nbsp;
                    <button onClick={this.search}>搜索</button>
                </div>
            </section>
        );
    }
}

export default Index;