import React, {Component} from 'react';
import './index.css'

//创建Context容器对象
const UsernameContext = React.createContext()

export default class A extends Component {
    state={username:'tom',age:17}
    render() {
        return (
            <div className="parent">
                <h3>我是A组件</h3>
                <h4>我的用户名是：{this.state.username}</h4>
                {/*<B username={this.state.username}/>*/}
                <UsernameContext.Provider value={{username:this.state.username,age:this.state.age}}>
                    <B/>
                </UsernameContext.Provider>
            </div>
        );
    }
}

class B extends Component{
    render(){
        return (
            <div className="child">
                <h3>我是B组件</h3>
                <h4>我不声明就接收不到Context</h4>
                <C/>
            </div>
        );
    }
}

class C extends Component{
    //声明接收上下文传参
    static contextType = UsernameContext
    render(){
        return (
            <div className="grand">
                <h3>我是C组件</h3>
                <h4>我从B组件接收到的用户名是：{this.context.username} , 年纪是：{this.context.age}</h4>
                <D/>
            </div>
        );
    }
}
function D(){
    return (
        <div className="fnc">
            <h3>我是D函数组件</h3>
            <h4>我从C组件接收到的用户名是：
                <UsernameContext.Consumer>
                    {value => { return `${value.username}, 年龄是${value.age}`}}
                </UsernameContext.Consumer>
            </h4>
        </div>
    );
}


