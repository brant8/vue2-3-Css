import React, {Component} from 'react';

class Index extends Component {

    increment=()=>{
        const {value} = this.selectNumber; //获得的是字符串
        this.props.jia(value*1)

    }
    decrement=()=>{
        const {value} = this.selectNumber; //获得的是字符串
        this.props.jian(value*1)
    }
    incrementIfOdd=()=>{
        const {value} = this.selectNumber; //获得的是字符串
        if(this.props.count%2 !== 0){
            this.props.jia(value*1)
        }
    }
    incrementAsync=()=>{
        const {value} = this.selectNumber; //获得的是字符串
        this.props.jiaAsync(value*1,500)
    }

    render() {
        console.log(this.props)
        return (
            <div>
                <h1>当前求和为：{this.props.count}</h1>
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