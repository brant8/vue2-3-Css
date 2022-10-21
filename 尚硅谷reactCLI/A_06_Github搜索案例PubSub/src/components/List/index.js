import React, {Component} from 'react';
import PubSub from 'pubsub-js'
import './index.css'

class Index extends Component {
    state = {
        users:[],
        isFirst:true, //是否第一次打开页面
        isLoading:false, //标识是否处于加载中
        err:''//存储请求相关的错误信息
    }

    componentDidMount() {
        PubSub.subscribe('atguigu',(msg,stateObj)=>{
            console.log(stateObj);
            this.setState(stateObj);
        })
    }


    render() {
        const {users, isFirst, isLoading, err} = this.state;
        return (
            <div className="row">
            { /*jsx不能写if没有返回结果的语句，可以用三元表达式*/
                isFirst ? <h2>输入关键字后，点击搜索</h2> :
                isLoading ? <h2>Loading...</h2> :
                err ? <h2>{err}</h2> :
                users.map((userObj)=>{
                return (
                    <div key={userObj.id} className="card">
                        <a href={userObj.html_url} rel="noreferrer" target="_blank">
                            <img alt="avatar" src={userObj.avatar_url} style={{width: '100px'}}/>
                        </a>
                        <p className="card-text">{userObj.login}</p>
                    </div>
                )
              })
            }
            </div>
        );
    }
}

export default Index;