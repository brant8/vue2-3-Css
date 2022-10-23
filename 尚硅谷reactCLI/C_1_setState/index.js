import React, {Component} from 'react';

class Index extends Component {
    state ={
        count:0
    }
    increment = ()=>{
        const {count} = this.state;
        console.log(count)
        this.setState({count:count+1},()=>{
            console.log("异步回调",this.state.count)
        });
        console.log("同步",this.state.count);
    }
    decrement = ()=>{
        this.setState((state,props)=>{/*该函数能从外面接收到的两个参数，比如App中传入<Demo x={100}*/
            console.log(state,props) //输出： {count:0} {x:100}
            return {count:state.count-1}
        })
    }
    render() {
        return (
            <div>
                <h1>{this.state.count}</h1>
                <button onClick={this.increment}>点击+1</button>
                <button onClick={this.decrement}>点击-1</button>
            </div>
        );
    }
}

export default Index;