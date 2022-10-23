import React, {Component} from 'react';
import Child from './child'

class Parent extends Component {
    state = {
        hasError:''
    }
    //当Parent的子组件出现报错的时候，会触发getDerivedStateFromError调用，并携带错误信息
    static getDerivedStateFromError(error){
        console.log(error);
        return {hasError:error}
    }
    componentDidCatch(error, errorInfo) {
        console.log('渲染组件是出错：一般用于统计错误次数，发送给后台')
    }

    render() {
        return (
            <div>
                <h3>我是parent组件</h3>
                {/*不输出真的错误信息在页面*/}
                {this.state.hasError ? <h2>当前网络不稳定，稍后再试</h2> : <Child/>}
            </div>
        );
    }
}

export default Parent;