import React, {Component} from 'react';
import Search from './components/Search'
import List from './components/List'

class App extends Component {
    state = {
        users:[],
        isFirst:true, //是否第一次打开页面
        isLoading:false, //标识是否处于加载中
        err:''//存储请求相关的错误信息
    }//初始化数据
    //saveUsers更换为updateAppState，通用性更好
    updateAppState = (stateObj)=>{
        this.setState(stateObj)
    }
    render() {
        return (
            <div id="app">
                <div className="container">
                    <Search updateAppState={this.updateAppState}/>
                    <List {...this.state}/>
                </div>
            </div>
        );
    }
}

export default App;