import React, {Component} from 'react';
import './index.css'

export default class Index extends Component {
    state = {carName:"奔驰233"}
    changeCar = ()=>{
        this.setState({carName:"迈巴赫"})
    }

    shouldComponentUpdate(nextProps,nextState){
        console.log("目前的props和state: ",this.props,this.state);
        console.log("接下来props和state要变化成的结果: ",nextProps,nextState);
        // return true;//必须要有的返回值
        /**单个比较的时候*/
        if(this.state.carName === nextState.carName)
            return false;
        else
            return true;
        /**多个比较的时候，使用JSON.stringify或者其他方式*/
    }

    render() {
        console.log("Parent---render")
        return (
            <div className="parent">
                <h3>我是Optimize - Parent组件</h3>
                <span>我的车现在是：{this.state.carName}</span>
                <button onClick={this.changeCar}>点我换车</button>
                {/*<Child/>*/}
                <Child carName="奥托"/>
            </div>
        );
    }
}

class Child extends Component{
    //子组件更关注nextProps，若是最后一个子组件，则没必要传入nextState
    shouldComponentUpdate(nextProps,nextState){
        console.log("目前的props和state: ",this.props,this.state);
        console.log("接下来props和state要变化成的结果: ",nextProps,nextState);
        /**单个比较的时候(简化写法)*/
        return !this.props.carName === nextProps.carName;
    }

    render() {
        console.log("Child --- render")
        return (
            <div className="child">
                <h3>我是child组件</h3>
                <p>我收到的车是：{this.props.carName}</p>
            </div>
        )
    }
}
