import React, {Component} from 'react';
import axios from 'axios';

class Index extends Component {
    search=()=>{
        //通过ref获取用户输入的值
        console.log('@',this.keyWordElement.value);
        /*const {value} = this.keyWordElement; -> console.log(value)
        const {keyWordElement} = this;  -> console.log(keyWordElement.value) */
        const {keyWordElement:{value}} = this; //解构赋值的解构赋值；解构赋值连续写法
        console.log('@@',value)
        //发送请求前通知App更新状态
        this.props.updateAppState({isFirst:false,isLoading:true})
        //发送网络请求
        axios.get(`http://localhost:3000/api1/search/users?q=${value}`).then(
        //axios.get(`/api1/search/users?q=${value}`)...   当在localhost:3000页面下，给localhost:3000发送请求，可以省略地址
            response =>{
                console.log('成功了',response.data)
                this.props.updateAppState({isLoading:false,users:response.data.items})
            },
            error=>{
                console.log('失败了',error)
                this.props.updateAppState({isLoading:false,err:'数据获取失败:'+error.message}) //不能存错误对象error，只能使用error.message
            }
        )
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