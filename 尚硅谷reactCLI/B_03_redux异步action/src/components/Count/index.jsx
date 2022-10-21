import React, {Component} from 'react';
//引入store，用于获取redux中保存的状态（state={count:count}给redux管理）
import store from '../../redux/store'
// 引入actionCreator，专门用于创建action对象
import {createDecrementAction,createIncrementAction,createIncrementAsyncAction} from '../../redux/count_action'

class Index extends Component {

    componentDidMount() {
        /*1.因为redux只负责管理状态，状态更新后不刷新页面
        * 2.监测redux中状态的变化，只要变化，就调用render
        **/
        store.subscribe(()=>{ //状态变化就会调用本回调
            this.setState({});//调用setState赋值空对象，只为让其调用render更新redux中管理的状态
        })
    }

    increment=()=>{
        const {value} = this.selectNumber; //获得的是字符串
        //通知redux加value，本例跳过Action Creator，手动赋值对象给dispatch({..})
        store.dispatch(createIncrementAction(value*1))
    }
    decrement=()=>{
        const {value} = this.selectNumber; //获得的是字符串
        store.dispatch(createDecrementAction(value*1))
    }
    incrementIfOdd=()=>{
        const {value} = this.selectNumber; //获得的是字符串
        const count = store.getState();//本例不用{count} = store.getState()因为案例穿的是一个数字
        if(count%2 !==0) {
            store.dispatch(createIncrementAction(value*1))
        }
    }
    incrementAsync=()=>{
        const {value} = this.selectNumber; //获得的是字符串
        //setTimeout(()=>{
            store.dispatch(createIncrementAsyncAction(value*1,500))
        //},500)

    }

    render() {
        return (
            <div>
                <h1>当前求和为：{store.getState()}</h1>
                <select ref={c=>this.selectNumber=c}>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                </select> &nbsp;
                <button onClick={this.increment}>+</button>&nbsp;
                <button onClick={this.decrement}>-</button>&nbsp;
                <button onClick={this.incrementIfOdd}>当前求和为奇数再加</button>&nbsp;
                <button onClick={this.incrementAsync}>异步 加</button>
            </div>
        );
    }
}

export default Index;